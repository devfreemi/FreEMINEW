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
	
	
	@ModelAttribute("contextcdn") String contextcdn() {
		return env.getProperty(CommonConstants.CDN_URL);
	}
	
}
