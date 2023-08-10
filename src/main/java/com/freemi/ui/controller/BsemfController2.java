package com.freemi.ui.controller;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.pdfbox.contentstream.operator.state.SetRenderingIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.freemi.common.util.BseAOFGenerator;
import com.freemi.common.util.CommonConstants;
import com.freemi.common.util.CommonTask;
import com.freemi.common.util.InvestFormConstants;
import com.freemi.database.interfaces.ProductSchemeDetailService;
import com.freemi.entity.bse.BseApiResponse;
import com.freemi.entity.bse.BseFatcaForm;
import com.freemi.entity.bse.BseFileUpload;
import com.freemi.entity.bse.BseOrderPaymentRequest;
import com.freemi.entity.bse.BseOrderPaymentResponse;
import com.freemi.entity.bse.Bsepay;
import com.freemi.entity.bse.Mandatecheck;
import com.freemi.entity.database.UserBankDetails;
import com.freemi.entity.general.ClientSystemDetails;
import com.freemi.entity.general.HttpClientResponse;
import com.freemi.entity.general.Searchlocationdetials;
import com.freemi.entity.general.UserProfile;
import com.freemi.entity.general.UserProfileLdap;
import com.freemi.entity.investment.BseAOFDocument;
import com.freemi.entity.investment.BseFundsScheme;
import com.freemi.entity.investment.BseMFSelectedFunds;
import com.freemi.entity.investment.BseMFTop15lsSip;
import com.freemi.entity.investment.BseMandateDetails;
import com.freemi.entity.investment.BsemfTransactionHistory;
import com.freemi.entity.investment.Bseregistrationstatus;
import com.freemi.entity.investment.Emandatestaus;
import com.freemi.entity.investment.MFAdditionalPurchaseForm;
import com.freemi.entity.investment.MFCustomers;
import com.freemi.entity.investment.MFRedeemForm;
import com.freemi.entity.investment.MfAllInvestorValueByCategory;
import com.freemi.entity.investment.SelectMFFund;
import com.freemi.entity.investment.TransactionStatus;
import com.freemi.services.Impl.Profilerequesthandler;
import com.freemi.services.interfaces.Amcfundmanager;
import com.freemi.services.interfaces.BseEntryManager;
import com.freemi.services.interfaces.HdfcService;
import com.freemi.services.interfaces.InvestmentConnectorBseInterface;
import com.freemi.services.interfaces.MailSenderInterface;
import com.freemi.services.interfaces.ProfileRestClientService;

@Controller
@Scope("session")
//@SessionAttributes({"purchaseForm","mfRedeemForm"})
public class BsemfController2 {

	private static final Logger logger = LogManager.getLogger(BsemfController2.class);

	@Autowired
	ProfileRestClientService profileRestClientService;
	
	@Autowired
	Profilerequesthandler profilerequesthandler;

	@Autowired
	private BseEntryManager bseEntryManager;

	@Autowired
	Environment env;


	@RequestMapping(value = "/mutual-funds/mandate-status", method = RequestMethod.POST)
	@ResponseBody
	public BseApiResponse getemandatestatus(@RequestBody Mandatecheck data, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		
		BseApiResponse apiresponse = new BseApiResponse();
		try {
			
			if( session.getAttribute("token")!=null && session.getAttribute("userid")!=null) {
				String clientid = bseEntryManager.getClientIdfromMobile( session.getAttribute("userid").toString());
				
				if(clientid.equalsIgnoreCase(data.getClientid())) {
					apiresponse = bseEntryManager.getemandatestatus(data.getClientid(), data.getMandateid());
				}else {
					apiresponse.setStatusCode("101");
					apiresponse.setRemarks("Invalid client ID.");
				}
			}else {
				apiresponse.setStatusCode("101");
				apiresponse.setRemarks("Session expired. Kindly login again to check status.");
			}
		}catch(Exception e) {
			logger.error("Failed to process",e);
			apiresponse.setStatusCode("101");
			apiresponse.setRemarks("Internal failure");
		}
		return  apiresponse;
	}

	@RequestMapping(value = "/mutual-funds/emandateurl", method = RequestMethod.POST)
	@ResponseBody
	public BseApiResponse getmandateurl(@RequestBody Mandatecheck data, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		BseApiResponse apiresponse = new BseApiResponse();
		try {
			
			if( session.getAttribute("token")!=null && session.getAttribute("userid")!=null) {
				String clientid = bseEntryManager.getClientIdfromMobile( session.getAttribute("userid").toString());
				logger.info("Match client ID- "+ clientid + " : "+ data.getClientid());
				if(clientid.equalsIgnoreCase(data.getClientid())) {
					apiresponse = bseEntryManager.getemandateauthurl(data.getClientid(), data.getMandateid());
				}else {
					apiresponse.setStatusCode("101");
					apiresponse.setRemarks("Invalid client ID.");
				}
			}else {
				apiresponse.setStatusCode("101");
				apiresponse.setRemarks("Session expired. Kindly login again.");
			}
		}catch(Exception e) {
			logger.error("Failed to proess",e);
			apiresponse.setStatusCode("101");
			apiresponse.setRemarks("Failed!");
		}
		logger.info("Response- "+ apiresponse.getRemarks());
		return  apiresponse;
	}
	
	/*
	@RequestMapping(value = "/mutual-funds/test-status", method = RequestMethod.GET)
	public String bseteststatus(Model map, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {

		logger.info("bseMFTransactionCallback(): @@ BSE MF STAR purchase confirm controller after callback @@");
		String returnUrl = "bsemf/test-purchase-status";
		String clienCode="deba593c";
		String transStatus="Y";
		TransactionStatus transReport = new TransactionStatus();
		transReport.setInvestmentType("LUMPSUM");
		transReport.setBseOrderNoFromResponse("123456");
		transReport.setTransactionReference("KSDFSFSD");
		transReport.setFirstpay("Y");
		transReport.setEmandateStatusCode("NA");
		if (clienCode.isEmpty()) {
			logger.info("bseMFTransactionStatus(): Client code is empty.. Returning back to home page");
			returnUrl = "redirect:/";
		} else {
			if (transStatus.equalsIgnoreCase("Y") || transStatus.equalsIgnoreCase("SF")) {
				logger.info("Proceeding to generate pay url for order id - " + transReport.getBseOrderNoFromResponse());
				BseOrderPaymentRequest orderUrl = new BseOrderPaymentRequest();
				//		orderUrl.setClientCode(CommonTask.encryptText(clienCode));
				orderUrl.setClientCode(clienCode);
				orderUrl.setMemberCode(CommonConstants.BSE_MEMBER_ID);

				Base64 base64 = new Base64();
				String encodedClientCode = new String(base64.encode(CommonTask.encryptText(clienCode).getBytes()));

				String callbackUrl = URI.create(request.getRequestURL().toString()).resolve(request.getContextPath())
						.toString()
						+ "/mutual-funds/bse-transaction-complete?orderid="
						+ (!transReport.getBseOrderNoFromResponse().isEmpty()
								? transReport.getBseOrderNoFromResponse() + "&client=" + encodedClientCode
										: "NA");
				logger.info("Callback url for payment- " + callbackUrl);
				orderUrl.setLogOutURL(callbackUrl);
//				BseOrderPaymentResponse orderUrlReponse = investmentConnectorBseInterface.getPaymentUrl(orderUrl);
//				map.addAttribute("orderUrl", orderUrlReponse);
			}
			map.addAttribute("TRANSACTION_REPORT", transReport);
			map.addAttribute("TRANS_STATUS", "Y");
			map.addAttribute("FIRST_PAY", "Y");
			
			String data = "\\r\\n\\r\\n\\r\\n\\r\\n<html>\\r\\n<head><title>Redirecting to Bank</title>\\r\\n<style>\\r\\n\\r\\n.bodytxt4 {\\r\\n\\r\\n\\tfont-family: Verdana, Arial, Helvetica, sans-serif;\\r\\n\\tfont-size: 12px;\\r\\n\\tfont-weight: bold;\\r\\n\\tcolor: #666666;\\r\\n}\\r\\n.bodytxt {\\r\\n\\tfont-family: Verdana, Arial, Helvetica, sans-serif;\\r\\n\\tfont-size: 13px;\\r\\n\\tfont-weight: normal;\\r\\n\\tcolor: #000000;\\r\\n\\r\\n}\\r\\n.bullet1 {\\r\\n\\r\\n\\tlist-style-type:\\tsquare;\\r\\n\\tlist-style-position: inside;\\r\\n\\tlist-style-image: none;\\r\\n\\tfont-family: Verdana, Arial, Helvetica, sans-serif;\\r\\n\\tfont-size: 10px;\\r\\n\\tfont-weight: bold;\\r\\n\\tcolor: #FF9900;\\r\\n}\\r\\n.bodytxt2 {\\r\\n\\tfont-family: Verdana, Arial, Helvetica, sans-serif;\\r\\n\\tfont-size: 8pt;\\r\\n\\tfont-weight: normal;\\r\\n\\tcolor: #333333;\\r\\n\\r\\n}\\r\\nA.sac2 {\\r\\n\\tCOLOR: #000000;\\r\\n\\tfont-family: Verdana, Arial, Helvetica, sans-serif;\\r\\n\\tfont-size: 10px;\\r\\n\\tfont-weight: bold;\\r\\n\\ttext-decoration: none;\\r\\n}\\r\\nA.sac2:visited {\\r\\n\\tCOLOR: #314D5A; TEXT-DECORATION: none\\r\\n}\\r\\nA.sac2:hover {\\r\\n\\tCOLOR: #FF9900; TEXT-DECORATION: underline\\r\\n}\\r\\n</style>\\r\\n\\r\\n</head>\\r\\n<script language=JavaScript>\\r\\n\\r\\n\\r\\nvar message=\\\"Function Disabled!\\\";\\r\\n\\r\\n\\r\\nfunction clickIE4(){\\r\\nif (event.button==2){\\r\\nreturn false;\\r\\n}\\r\\n}\\r\\n\\r\\nfunction clickNS4(e){\\r\\nif (document.layers||document.getElementById&&!document.all){\\r\\nif (e.which==2||e.which==3){\\r\\nreturn false;\\r\\n}\\r\\n}\\r\\n}\\r\\n\\r\\nif (document.layers){\\r\\ndocument.captureEvents(Event.MOUSEDOWN);\\r\\ndocument.onmousedown=clickNS4;\\r\\n}\\r\\nelse if (document.all&&!document.getElementById){\\r\\ndocument.onmousedown=clickIE4;\\r\\n}\\r\\n\\r\\ndocument.oncontextmenu=new Function(\\\"return false\\\")\\r\\n\\r\\n</script>\\r\\n<table width=\\\"100%\\\" border=\\\"0\\\" cellspacing=\\\"0\\\" cellpadding=\\\"0\\\">\\r\\n  <tr>\\r\\n    <td align=\\\"left\\\" valign=\\\"top\\\">\\r\\n<table width=\\\"100%\\\" border=\\\"0\\\" cellspacing=\\\"0\\\" cellpadding=\\\"0\\\">\\r\\n        <tr> \\r\\n          <td align=\\\"center\\\" valign=\\\"middle\\\"><table width=\\\"100%\\\" border=\\\"0\\\" cellspacing=\\\"0\\\" cellpadding=\\\"0\\\">\\r\\n             \\r\\n              <tr>\\r\\n                <td  align=\\\"center\\\"></td>\\r\\n              </tr>\\r\\n              <tr>\\r\\n                <td height=\\\"85\\\" align=\\\"center\\\"><br>\\r\\n                  <table width=\\\"80%\\\" border=\\\"0\\\" cellpadding=\\\"0\\\" cellspacing=\\\"1\\\" bgcolor=\\\"#CCCCCC\\\">\\r\\n                    <tr>\\r\\n                      <td bgcolor=\\\"#CCCCCC\\\"><table width=\\\"100%\\\" border=\\\"0\\\" cellpadding=\\\"6\\\" cellspacing=\\\"0\\\" bgcolor=\\\"#FFFFFF\\\">\\r\\n                          <tr> \\r\\n                            <td colspan=\\\"2\\\" align=\\\"left\\\" valign=\\\"bottom\\\"><span class=\\\"bodytxt4\\\">Your payment request is being processed...</span></td>\\r\\n                          </tr>\\r\\n                          <tr valign=\\\"top\\\"> \\r\\n                            <td colspan=\\\"2\\\" align=\\\"left\\\"><table width=\\\"100%\\\" border=\\\"0\\\" cellspacing=\\\"0\\\" cellpadding=\\\"0\\\">\\r\\n                                <tr> \\r\\n                                  <td width=\\\"87%\\\" bgcolor=\\\"#cccccc\\\" height=\\\"1\\\" align=\\\"center\\\"></td>\\r\\n                                </tr>\\r\\n                              </table></td>\\r\\n                          </tr>\\r\\n                          <tr> \\r\\n                            <td width=\\\"60%\\\" align=\\\"left\\\" valign=\\\"bottom\\\"><table width=\\\"95%\\\" border=\\\"0\\\" cellpadding=\\\"1\\\" cellspacing=\\\"0\\\" bgcolor=\\\"#FFFFFF\\\">\\r\\n                                <tr> \\r\\n                                  <td align=\\\"right\\\" valign=\\\"top\\\"></td>\\r\\n                                  <td class=\\\"bodytxt\\\">&nbsp;</td>\\r\\n                                </tr>\\r\\n                                <tr> \\r\\n                                  <td height=\\\"19\\\"  align=\\\"right\\\" valign=\\\"top\\\"><li class=\\\"bullet1\\\"></li></td>\\r\\n                                  <td class=\\\"bodytxt2\\\">This is a secure payment \\r\\n                                    gateway using 128 bit SSL encryption.</td>\\r\\n                                </tr>\\r\\n                                <tr> \\r\\n                                  <td align=\\\"right\\\" valign=\\\"top\\\"> <li class=\\\"bullet1\\\"></li></td>\\r\\n                                  <td class=\\\"bodytxt2\\\" >When you submit the transaction, \\r\\n                                    the server will take about 1 to 5 seconds \\r\\n                                    to process, but it may take longer at certain \\r\\n                                    times. </td>\\r\\n                                </tr>\\r\\n                                <tr> \\r\\n                                  <td align=\\\"right\\\" valign=\\\"top\\\"><li class=\\\"bullet1\\\"></li></td>\\r\\n                                  <td class=\\\"bodytxt2\\\" >Please do not press \\\"Submit\\\" \\r\\n                                    button once again or the \\\"Back\\\" or \\\"Refresh\\\" \\r\\n                                    buttons. </td>\\r\\n                                </tr>\\r\\n                              </table></td>\\r\\n                            <td align=\\\"right\\\" valign=\\\"bottom\\\"><table width=\\\"80%\\\" border=\\\"0\\\" cellpadding=\\\"1\\\" cellspacing=\\\"0\\\" bgcolor=\\\"#FFFFFF\\\">\\r\\n                                <tr bgcolor=\\\"#FFFCF8\\\"> \\r\\n                                  <td align=\\\"right\\\" bgcolor=\\\"#FFFFFF\\\"></td>\\r\\n                                </tr>\\r\\n                                <tr bgcolor=\\\"#FFFCF8\\\"> \\r\\n                                  <td align=\\\"right\\\" valign=\\\"middle\\\" bgcolor=\\\"#FFFFFF\\\" class=\\\"bodytxt2\\\">&nbsp;</td>\\r\\n                                </tr>\\r\\n                                <tr bgcolor=\\\"#FFFCF8\\\"> \\r\\n                                  <td align=\\\"right\\\" bgcolor=\\\"#FFFFFF\\\" class=\\\"bodytxt2\\\" >&nbsp;</td>\\r\\n                                </tr>\\r\\n                              </table></td>\\r\\n                          </tr>\\r\\n                        </table></td>\\r\\n                    </tr>\\r\\n                  </table>\\r\\n                  \\r\\n                </td>\\r\\n              </tr>\\r\\n            </table>\\r\\n           \\r\\n          \\r\\n         \\r\\n             </td>\\r\\n        </tr>  \\r\\n\\r\\n\\r\\n      </table></td>\\r\\n  </tr>\\r\\n  \\r\\n</table>\\r\\n\\r\\n\\r\\n\\r\\n<body>\\r\\n<form name=\\\"Bankfrm\\\" method=\\\"post\\\" action='https://shopping.icicibank.com/corp/BANKAWAY?IWQRYTASKOBJNAME=bay_mc_login&BAY_BANKID=ICI'>\\r\\n \\r\\n\\t\\t\\t  \\r\\n              <input type = \\\"hidden\\\" name = \\\"MD\\\" value=\\\"P\\\">\\r\\n\\t\\t\\t\\r\\n              \\r\\n\\t\\t\\t  \\r\\n              <input type = \\\"hidden\\\" name = \\\"PID\\\" value=\\\"000000001086\\\">\\r\\n\\t\\t\\t\\r\\n              \\r\\n\\t\\t\\t  \\r\\n              <input type = \\\"hidden\\\" name = \\\"ES\\\" value=\\\"hbVjLCMyDHSYxiBaT7dJgaVXbhCCcxOAk4mNJPkwEQlcdklihe4UQTNrhsjzGEl/ts8Sl9RCMvWWeSMU1MZ7vRMHGEv94hBmuaoqeg0CLXZGgqqZp0aRKazsBdLAYpqTZ94askMgUzU34Bcgb4dogol5jxM0AolY2RtMcDhHrEDjpD3ygzEOJaaT97DmUXVR7p9iQcr1q5TRPpyroTq1Urboe2XFC+91ndxTYa3AjkiPpI+6/JiAh/Wt2TMkWfwm\\\">\\r\\n\\t\\t\\t\\r\\n              \\r\\n\\t\\t\\t  \\r\\n              <input type = \\\"hidden\\\" name = \\\"SPID\\\" value=\\\"NA\\\">\\r\\n\\t\\t\\t\\r\\n              \\r\\n\\t</form>\\r\\n</body>\\r\\n<script>\\r\\ndocument.Bankfrm.submit();\\r\\n</script>\\r\\n</html>\\r\\n";
			data = data.replace("\\n", "");
			data = data.replace("\\t", "");
			data = data.replace("\\r", "");
			data = data.replace("\\", "");
			
			map.addAttribute("data", "Test");
//			map.addAttribute("data", data);
			Bsepay pay = new Bsepay();
			pay.setAmount("500");
			pay.setBankacc("sdfsfsfd");
			pay.setOrderno(transReport.getBseOrderNoFromResponse());
			map.addAttribute("bsepay", pay);
		}
		
		if(transReport.getEmandateStatusCode().equals("S")) {
			logger.info("New E-mandate was generated. Get auth link");
			BseApiResponse resp = bseEntryManager.getemandateauthurl(transReport.getClientcode(), transReport.getMandateid());
			if(resp.getStatusCode().equals("100")) {
				transReport.setOther1("S");
				transReport.setOther2(resp.getRemarks());
			}
		}

		//	map.addAttribute("TRANS_STATUS", "Y");

		logger.info("bseMFTransactionStatus(): Return url- "+ returnUrl);
		return returnUrl;
	}
	
	
	
	@RequestMapping(value = "/mutual-funds/test-status", method = RequestMethod.POST)
	public String bsepaymentprocess(@ModelAttribute("bsepay")Bsepay pay,  Model map, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {

		logger.info("bsepaymentprocess(): @@ BSE MF STAR purchase confirm controller process for payment @@");
		String returnUrl = "bsemf/bse-payment-status";
		map.addAttribute("bsepay", pay);
		
		if(pay.getPayvia().equalsIgnoreCase("IB")) {
			//Pay via internet banking
			String data = "\\r\\n\\r\\n\\r\\n\\r\\n<html>\\r\\n<head><title>Redirecting to Bank</title>\\r\\n<style>\\r\\n\\r\\n.bodytxt4 {\\r\\n\\r\\n\\tfont-family: Verdana, Arial, Helvetica, sans-serif;\\r\\n\\tfont-size: 12px;\\r\\n\\tfont-weight: bold;\\r\\n\\tcolor: #666666;\\r\\n}\\r\\n.bodytxt {\\r\\n\\tfont-family: Verdana, Arial, Helvetica, sans-serif;\\r\\n\\tfont-size: 13px;\\r\\n\\tfont-weight: normal;\\r\\n\\tcolor: #000000;\\r\\n\\r\\n}\\r\\n.bullet1 {\\r\\n\\r\\n\\tlist-style-type:\\tsquare;\\r\\n\\tlist-style-position: inside;\\r\\n\\tlist-style-image: none;\\r\\n\\tfont-family: Verdana, Arial, Helvetica, sans-serif;\\r\\n\\tfont-size: 10px;\\r\\n\\tfont-weight: bold;\\r\\n\\tcolor: #FF9900;\\r\\n}\\r\\n.bodytxt2 {\\r\\n\\tfont-family: Verdana, Arial, Helvetica, sans-serif;\\r\\n\\tfont-size: 8pt;\\r\\n\\tfont-weight: normal;\\r\\n\\tcolor: #333333;\\r\\n\\r\\n}\\r\\nA.sac2 {\\r\\n\\tCOLOR: #000000;\\r\\n\\tfont-family: Verdana, Arial, Helvetica, sans-serif;\\r\\n\\tfont-size: 10px;\\r\\n\\tfont-weight: bold;\\r\\n\\ttext-decoration: none;\\r\\n}\\r\\nA.sac2:visited {\\r\\n\\tCOLOR: #314D5A; TEXT-DECORATION: none\\r\\n}\\r\\nA.sac2:hover {\\r\\n\\tCOLOR: #FF9900; TEXT-DECORATION: underline\\r\\n}\\r\\n</style>\\r\\n\\r\\n</head>\\r\\n<script language=JavaScript>\\r\\n\\r\\n\\r\\nvar message=\\\"Function Disabled!\\\";\\r\\n\\r\\n\\r\\nfunction clickIE4(){\\r\\nif (event.button==2){\\r\\nreturn false;\\r\\n}\\r\\n}\\r\\n\\r\\nfunction clickNS4(e){\\r\\nif (document.layers||document.getElementById&&!document.all){\\r\\nif (e.which==2||e.which==3){\\r\\nreturn false;\\r\\n}\\r\\n}\\r\\n}\\r\\n\\r\\nif (document.layers){\\r\\ndocument.captureEvents(Event.MOUSEDOWN);\\r\\ndocument.onmousedown=clickNS4;\\r\\n}\\r\\nelse if (document.all&&!document.getElementById){\\r\\ndocument.onmousedown=clickIE4;\\r\\n}\\r\\n\\r\\ndocument.oncontextmenu=new Function(\\\"return false\\\")\\r\\n\\r\\n</script>\\r\\n<table width=\\\"100%\\\" border=\\\"0\\\" cellspacing=\\\"0\\\" cellpadding=\\\"0\\\">\\r\\n  <tr>\\r\\n    <td align=\\\"left\\\" valign=\\\"top\\\">\\r\\n<table width=\\\"100%\\\" border=\\\"0\\\" cellspacing=\\\"0\\\" cellpadding=\\\"0\\\">\\r\\n        <tr> \\r\\n          <td align=\\\"center\\\" valign=\\\"middle\\\"><table width=\\\"100%\\\" border=\\\"0\\\" cellspacing=\\\"0\\\" cellpadding=\\\"0\\\">\\r\\n             \\r\\n              <tr>\\r\\n                <td  align=\\\"center\\\"></td>\\r\\n              </tr>\\r\\n              <tr>\\r\\n                <td height=\\\"85\\\" align=\\\"center\\\"><br>\\r\\n                  <table width=\\\"80%\\\" border=\\\"0\\\" cellpadding=\\\"0\\\" cellspacing=\\\"1\\\" bgcolor=\\\"#CCCCCC\\\">\\r\\n                    <tr>\\r\\n                      <td bgcolor=\\\"#CCCCCC\\\"><table width=\\\"100%\\\" border=\\\"0\\\" cellpadding=\\\"6\\\" cellspacing=\\\"0\\\" bgcolor=\\\"#FFFFFF\\\">\\r\\n                          <tr> \\r\\n                            <td colspan=\\\"2\\\" align=\\\"left\\\" valign=\\\"bottom\\\"><span class=\\\"bodytxt4\\\">Your payment request is being processed...</span></td>\\r\\n                          </tr>\\r\\n                          <tr valign=\\\"top\\\"> \\r\\n                            <td colspan=\\\"2\\\" align=\\\"left\\\"><table width=\\\"100%\\\" border=\\\"0\\\" cellspacing=\\\"0\\\" cellpadding=\\\"0\\\">\\r\\n                                <tr> \\r\\n                                  <td width=\\\"87%\\\" bgcolor=\\\"#cccccc\\\" height=\\\"1\\\" align=\\\"center\\\"></td>\\r\\n                                </tr>\\r\\n                              </table></td>\\r\\n                          </tr>\\r\\n                          <tr> \\r\\n                            <td width=\\\"60%\\\" align=\\\"left\\\" valign=\\\"bottom\\\"><table width=\\\"95%\\\" border=\\\"0\\\" cellpadding=\\\"1\\\" cellspacing=\\\"0\\\" bgcolor=\\\"#FFFFFF\\\">\\r\\n                                <tr> \\r\\n                                  <td align=\\\"right\\\" valign=\\\"top\\\"></td>\\r\\n                                  <td class=\\\"bodytxt\\\">&nbsp;</td>\\r\\n                                </tr>\\r\\n                                <tr> \\r\\n                                  <td height=\\\"19\\\"  align=\\\"right\\\" valign=\\\"top\\\"><li class=\\\"bullet1\\\"></li></td>\\r\\n                                  <td class=\\\"bodytxt2\\\">This is a secure payment \\r\\n                                    gateway using 128 bit SSL encryption.</td>\\r\\n                                </tr>\\r\\n                                <tr> \\r\\n                                  <td align=\\\"right\\\" valign=\\\"top\\\"> <li class=\\\"bullet1\\\"></li></td>\\r\\n                                  <td class=\\\"bodytxt2\\\" >When you submit the transaction, \\r\\n                                    the server will take about 1 to 5 seconds \\r\\n                                    to process, but it may take longer at certain \\r\\n                                    times. </td>\\r\\n                                </tr>\\r\\n                                <tr> \\r\\n                                  <td align=\\\"right\\\" valign=\\\"top\\\"><li class=\\\"bullet1\\\"></li></td>\\r\\n                                  <td class=\\\"bodytxt2\\\" >Please do not press \\\"Submit\\\" \\r\\n                                    button once again or the \\\"Back\\\" or \\\"Refresh\\\" \\r\\n                                    buttons. </td>\\r\\n                                </tr>\\r\\n                              </table></td>\\r\\n                            <td align=\\\"right\\\" valign=\\\"bottom\\\"><table width=\\\"80%\\\" border=\\\"0\\\" cellpadding=\\\"1\\\" cellspacing=\\\"0\\\" bgcolor=\\\"#FFFFFF\\\">\\r\\n                                <tr bgcolor=\\\"#FFFCF8\\\"> \\r\\n                                  <td align=\\\"right\\\" bgcolor=\\\"#FFFFFF\\\"></td>\\r\\n                                </tr>\\r\\n                                <tr bgcolor=\\\"#FFFCF8\\\"> \\r\\n                                  <td align=\\\"right\\\" valign=\\\"middle\\\" bgcolor=\\\"#FFFFFF\\\" class=\\\"bodytxt2\\\">&nbsp;</td>\\r\\n                                </tr>\\r\\n                                <tr bgcolor=\\\"#FFFCF8\\\"> \\r\\n                                  <td align=\\\"right\\\" bgcolor=\\\"#FFFFFF\\\" class=\\\"bodytxt2\\\" >&nbsp;</td>\\r\\n                                </tr>\\r\\n                              </table></td>\\r\\n                          </tr>\\r\\n                        </table></td>\\r\\n                    </tr>\\r\\n                  </table>\\r\\n                  \\r\\n                </td>\\r\\n              </tr>\\r\\n            </table>\\r\\n           \\r\\n          \\r\\n         \\r\\n             </td>\\r\\n        </tr>  \\r\\n\\r\\n\\r\\n      </table></td>\\r\\n  </tr>\\r\\n  \\r\\n</table>\\r\\n\\r\\n\\r\\n\\r\\n<body>\\r\\n<form name=\\\"Bankfrm\\\" method=\\\"post\\\" action='https://shopping.icicibank.com/corp/BANKAWAY?IWQRYTASKOBJNAME=bay_mc_login&BAY_BANKID=ICI'>\\r\\n \\r\\n\\t\\t\\t  \\r\\n              <input type = \\\"hidden\\\" name = \\\"MD\\\" value=\\\"P\\\">\\r\\n\\t\\t\\t\\r\\n              \\r\\n\\t\\t\\t  \\r\\n              <input type = \\\"hidden\\\" name = \\\"PID\\\" value=\\\"000000001086\\\">\\r\\n\\t\\t\\t\\r\\n              \\r\\n\\t\\t\\t  \\r\\n              <input type = \\\"hidden\\\" name = \\\"ES\\\" value=\\\"hbVjLCMyDHSYxiBaT7dJgaVXbhCCcxOAk4mNJPkwEQlcdklihe4UQTNrhsjzGEl/ts8Sl9RCMvWWeSMU1MZ7vRMHGEv94hBmuaoqeg0CLXZGgqqZp0aRKazsBdLAYpqTZ94askMgUzU34Bcgb4dogol5jxM0AolY2RtMcDhHrEDjpD3ygzEOJaaT97DmUXVR7p9iQcr1q5TRPpyroTq1Urboe2XFC+91ndxTYa3AjkiPpI+6/JiAh/Wt2TMkWfwm\\\">\\r\\n\\t\\t\\t\\r\\n              \\r\\n\\t\\t\\t  \\r\\n              <input type = \\\"hidden\\\" name = \\\"SPID\\\" value=\\\"NA\\\">\\r\\n\\t\\t\\t\\r\\n              \\r\\n\\t</form>\\r\\n</body>\\r\\n<script>\\r\\ndocument.Bankfrm.submit();\\r\\n</script>\\r\\n</html>\\r\\n";
			data = data.replace("\\n", "");
			data = data.replace("\\t", "");
			data = data.replace("\\r", "");
			data = data.replace("\\", "");
			map.addAttribute("data", data);
			
			
		}else if(pay.getPayvia().equalsIgnoreCase("UPI")) {
			map.addAttribute("msg", "Payment request sent.");
		}
		else {
			returnUrl="bsemf/test-purchase-status";
			return returnUrl;
		}
		
		return returnUrl;
	}
	*/
	
	
	
	@ModelAttribute("contextcdn") String contextcdn() {
		return env.getProperty(CommonConstants.CDN_URL);
	}
	
}
