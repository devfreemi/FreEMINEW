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
import com.freemi.entity.bse.Nomineerecords;
import com.freemi.entity.bse.Nomineeregistrationresponse;
import com.freemi.entity.bse.Paymentgatewayresponse;
import com.freemi.entity.database.UserBankDetails;
import com.freemi.entity.general.ClientSystemDetails;
import com.freemi.entity.general.HttpClientResponse;
import com.freemi.entity.general.Searchlocationdetials;
import com.freemi.entity.general.UserProfile;
import com.freemi.entity.general.UserProfileLdap;
import com.freemi.entity.investment.BseAOFDocument;
import com.freemi.entity.investment.BseBankid;
import com.freemi.entity.investment.BseFundsScheme;
import com.freemi.entity.investment.BseMFSelectedFunds;
import com.freemi.entity.investment.BseMFTop15lsSip;
import com.freemi.entity.investment.BseMandateDetails;
import com.freemi.entity.investment.BsemfTransactionHistory;
import com.freemi.entity.investment.Bseregistrationstatus;
import com.freemi.entity.investment.MFAdditionalPurchaseForm;
import com.freemi.entity.investment.MFCustomers;
import com.freemi.entity.investment.MFNominationForm;
import com.freemi.entity.investment.MFRedeemForm;
import com.freemi.entity.investment.MfAllInvestorValueByCategory;
import com.freemi.entity.investment.Nominee2faresponse;
import com.freemi.entity.investment.Nomineeverification;
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
public class BsemfController {

	private static final Logger logger = LogManager.getLogger(BsemfController.class);

	@Autowired
	ProductSchemeDetailService productSchemeDetailService;

	@Autowired
	ProfileRestClientService profileRestClientService;
	
	@Autowired
	Profilerequesthandler profilerequesthandler;

	@Autowired
	private BseEntryManager bseEntryManager;

	@Autowired
	InvestmentConnectorBseInterface investmentConnectorBseInterface;

	@Autowired
	Environment env;

	@Autowired
	MailSenderInterface mailSenderInterface;
	
	 @Autowired
	 HdfcService hdfcService;
	 
	 @Autowired
	 Amcfundmanager amcfundmanager;
	 
	 SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-mm-dd");
	 SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("dd/mm/yyyy");

	
	@RequestMapping(value = "/mutual-funds/top-performing", method = RequestMethod.GET)
	public String getTopPerformingFunds(@RequestParam(name = "info", required = false) String correctInfoAfterLogin, 
			Model map, HttpServletRequest request, HttpServletResponse response, HttpSession session) {

		logger.info("getTopPerformingFunds(): MF Funds Inventory");
		// List<MfTopFundsInventory> topFunds = null;
		List<BseMFTop15lsSip> topFunds = null;

		if (correctInfoAfterLogin != null) {
			logger.info("getTopPerformingFunds(): Error code received for transaction - " + correctInfoAfterLogin);
			if (correctInfoAfterLogin.equals("01")) {
				map.addAttribute("error",
						"Provided details mismatch with registered details. Transaction aborted. Please try again.");
			}
		}
		if (session.getAttribute("topFunds") != null) {
			// topFunds = (List<MfTopFundsInventory>) session.getAttribute("topFunds");
			topFunds = (List<BseMFTop15lsSip>) session.getAttribute("topFunds");
			logger.info("getTopPerformingFunds(): Serving top funds from session - " + topFunds.size());
			map.addAttribute("FUNDSFOUND", "Y");
			map.addAttribute("topFunds", topFunds);
			logger.info("getTopPerformingFunds(): Total funds from session - " + topFunds.size());
		} else {
			logger.info("getTopPerformingFunds(): Get top funds from database");
			try {
				// topFunds = bseEntryManager.getTopMfFunds();
				topFunds = bseEntryManager.getTopFunds();
				map.addAttribute("FUNDSFOUND", "Y");
				map.addAttribute("topFunds", topFunds);
				session.setAttribute("topFunds", topFunds);
				logger.info("getTopPerformingFunds(): Total funds received- " + topFunds.size());
			} catch (Exception e) {
				logger.error("getTopPerformingFunds(): Error fetching Funds list. Please try after sometime");
				map.addAttribute("FUNDSFOUND", "N");
			}
		}
		SelectMFFund fundChoice = new SelectMFFund();
		if (session.getAttribute("token") != null) {
			fundChoice.setMobile(session.getAttribute("userid").toString());
			try {
				String panNumber = bseEntryManager.getCustomerPanfromMobile(fundChoice.getMobile());
				fundChoice.setPan(panNumber);
			} catch (Exception e) {
				logger.error("getTopPerformingFunds(): Database connect issue: unable to fetch customer PAN number", e);
			}
		}
		map.addAttribute("selectFund", fundChoice);
		String returnUrl = "bsemf/top-performing-funds";

		return returnUrl;

	}


	@RequestMapping(value = "/mutual-funds/funds-explorer", method = RequestMethod.GET)
	public String getSelectFundsExplorer(@ModelAttribute("selectedFund") SelectMFFund fundChoice,  @ModelAttribute("error1") String error1, @RequestParam(value ="msg", required = false ) String msg, Model map, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {

		logger.info("getSelectFundsExplorer(): MF All Funds Inventory");

		String returnUrl = "bsemf/select-fund-explorer";
		// System.out.println("Funds explorer- session: "+ session.getId());
		/*
		 * PageRequest p =new PageRequest(0, 200); Pageable pg = p.first();
		 */
		
		List<BseMFSelectedFunds> funds = null;
//		funds = bseEntryManager.getAllSelectedFunds();
		funds = amcfundmanager.getAllSelectedFunds();
		
		
//		logger.info("getSelectFundsExplorer(): Total selected funds to display- " + (funds != null ? funds.size() : "NULL returned"));
		/*
		 * logger.info("Paginated fundss- "+ b.getSize()); logger.info("Total pages- "+
		 * b.getTotalPages());
		 * 
		 * List<BseFundsScheme> funds = b.getContent();
		 */
	
//		SelectMFFund fundChoice = new SelectMFFund();
		logger.info("Selected fund amoount- "+ fundChoice.getInvestAmount());
		if (session.getAttribute("token") != null) {
			fundChoice.setMobile(session.getAttribute("userid") != null ? session.getAttribute("userid").toString()
					: null);
			try {
				if(session.getAttribute("pan") != null) {
					logger.info("Set customer PAN retrieved from session data");
					fundChoice.setPan(session.getAttribute("pan").toString());
				}else {
					logger.info("Fetch PAN if customer is registered.");
					String panNumber = bseEntryManager.getCustomerPanfromMobile(fundChoice.getMobile());
					fundChoice.setPan(panNumber);
				}
			} catch (Exception e) {
				logger.error("getSelectFundsExplorer(): Database connect issue: unable to fetch customer PAN number",
						e);
			}
		}
		map.addAttribute("error1", error1);
		map.addAttribute("fundsexplorer", funds);
		map.addAttribute("selectFund", fundChoice);

		return returnUrl;

	}

	@RequestMapping(value = "/mutual-funds/purchase.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView purchasemfbsePost(@ModelAttribute("selectFund") SelectMFFund selectedFund, BindingResult bindResult,
			Model map, HttpServletRequest request, HttpServletResponse response, HttpSession session,
			RedirectAttributes redirectAttrs) {
		ModelAndView view = new ModelAndView();
//		logger.info("purchasemfbsePost(): JSESSIONID- "+ request.getCookies().g);
		logger.info("purchasemfbsePost(): BSE MF STAR Purchse.do controller from url - " + request.getHeader("Referer") + " : Request type - " + request.getMethod());
		//		String returnUrl = "redirect:/mutual-funds/funds-explorer";
		String returnUrl = "mutual-funds/funds-explorer";
		logger.info("purchasemfbsePost():MF purchase initiated by- "+ selectedFund.getMobile() + " :Re-invest code- " + selectedFund.getReinvSchemeCode());
		String customertype="NEW_CUSTOMER";

		String referrerUrl = request.getHeader("Referer");
		
		//		String returnUrl2 = "";
		RedirectView redview = new RedirectView();
		//		redview.setExposeModelAttributes(false);
		try {
			//returnUrl = redirectaftermfpurchaseerror(referrerUrl!=null?URLDecoder.decode(referrerUrl, StandardCharsets.UTF_8.toString()):URI.create(request.getRequestURL().toString()).resolve(request.getContextPath()).toString(),request);

		} catch (Exception e1) {
			logger.error("Error parsing referrer url",e1);
		}


		//		redview.setContextRelative(true);

		if (bindResult.hasErrors()) {
			logger.info("purchasemfbsePost(): Form has error- "+ bindResult.getFieldError().getDefaultMessage());
			//			redirectAttrs.addFlashAttribute("error1", bindResult.getFieldError().getDefaultMessage());
			//return "bsemf/bse-form-new-customer2";
			//			redirectAttrs.addFlashAttribute("selectedFund", selectedFund);
			//			redview.setUrl(returnUrl);
			view.addObject("errro1", bindResult.getFieldError().getDefaultMessage());
			redview.setUrl(referrerUrl);
			view.setView(redview);
			return view;
		}

		if (selectedFund.getMobile() == null || selectedFund.getPan() == null || selectedFund.getMobile().isEmpty() || selectedFund.getPan().isEmpty()) {
			logger.info("purchasemfbsePost(): selectfund mobile/pan not found");
			//			redirectAttrs.addFlashAttribute("error1", "Please provide valid PAN and mobile no.");
			view.addObject("errro1", "Please provide valid PAN and mobile no.");
			//			return "redirect:/mutual-funds/funds-explorer";
			redirectAttrs.addFlashAttribute("selectedFund", selectedFund);
			//			return returnUrl;
			redview.setUrl(returnUrl);
			view.setView(redview);
			return view;
		}
		
		HttpClientResponse sessionvalidation = profileRestClientService.validateusersession(request, session, selectedFund.getMobile(), null, selectedFund.getPan(),null,CommonTask.getClientSystemIp(request), false);

//		if(session.getAttribute("token")!=null && session.getAttribute("userid")!=null && !session.getAttribute("userid").toString().equalsIgnoreCase(selectedFund.getMobile())) {
		if(sessionvalidation.getResponseCode() == CommonConstants.TASK_FAILURE && sessionvalidation.getRetrunMessage().equalsIgnoreCase("MOB_MISMATCH")) {	
			
			//			redirectAttrs.addFlashAttribute("error1", "Mobile mismatch for registered customer");
			view.addObject("errro1", "Mobile mismatch for registered customer");
			redirectAttrs.addFlashAttribute("selectedFund", selectedFund);
			//			return referrerUrl;
			redview.setUrl(returnUrl);
			view.setView(redview);
			return view;
		}

		try {
			session.setAttribute("INITIATED_MOBILE",selectedFund.getMobile());
			//			Record initiated transaction request


			// Check if existing BSE registered customer or not
			//			boolean flag = bseEntryManager.isExisitngBSECustomerByMobile(selectedFund.getMobile());

			Bseregistrationstatus registrationstatus = bseEntryManager.getbseregistrationstatus(selectedFund.getMobile(), selectedFund.getPan(), null, null);

			logger.info("purchasemfbsePost(): Is existing customer by mobile? - " + selectedFund.getMobile() + " : " + registrationstatus.getAccountexist());

			logger.info("purchasemfbsePost(): Setting session for current selected fund with customer details");
			session.removeAttribute("selectedFund");
			session.setAttribute("selectedFund", selectedFund);
			//			if (flag && session.getAttribute("token") == null) {
			String nexturl;
			if (registrationstatus.getAccountexist().equalsIgnoreCase("Y")){

//				if (session.getAttribute("token") == null) {
					if(sessionvalidation.getRetrunMessage().equalsIgnoreCase("NO_TOKEN") ) {
					session.setAttribute("NEXT_URL", "/mutual-funds/purchase");
					logger.info("Account exist. Redirect to login page and transfer to fund purchase. Current referrel URL-");
//					redirectAttrs.addAttribute("ref", URLEncoder.encode(request.getRequestURL().toString(), StandardCharsets.UTF_8.toString()));
					
					if(registrationstatus.getBseregisterstatus().equals("Y") && registrationstatus.getFatcadeclared().equals("1")  && registrationstatus.getAofuploadstatus().equals("Y") ) {
						nexturl = request.getRequestURL().toString().split(request.getContextPath())[0]+ request.getContextPath() + "/mutual-funds/purchase"; //Registered customer,BSE complete, go to purchase page after login
						customertype="EXISTING";
						bseEntryManager.saveMFInitiatedTranasctionRequest(selectedFund);
					}else {
						nexturl = request.getRequestURL().toString().split(request.getContextPath())[0] + request.getContextPath()+ "/mutual-funds/register?mf=04"; // Complete fresh customer. Create both profile and register for MF
						customertype="COMPLETE_REGISTRATION";
					}
					logger.info("Next URL to - "+ nexturl);
					redirectAttrs.addAttribute("ref", URLEncoder.encode(nexturl, StandardCharsets.UTF_8.toString()));
					returnUrl = "/login?mf=00&id="+ CommonTask.encryptText(selectedFund.getMobile()); // Already existing customer, just login and fetch customer details
					redirectAttrs.addFlashAttribute("uid", selectedFund.getMobile());
					redview.setContextRelative(true);

//				} else if (session.getAttribute("token") != null) {
					} else if (sessionvalidation.getResponseCode() == CommonConstants.TASK_SUCCESS) {
					// If logged in account PAN and mobile do not match with provided PAN. Revert
					// back to page which should pick up correct details from session now overriding

					String pan = bseEntryManager.getCustomerPanfromMobile(session.getAttribute("userid").toString());

					if(!pan.equalsIgnoreCase(selectedFund.getPan())) {
						logger.info("purchasemfbsePost(): Customer PAN provided is different from registered PAN");
						logger.warn("purchasemfbsePost(): Data provided during form fillup:[ " + selectedFund.getPan() + " : " + selectedFund.getMobile() + "] Data from session user:[" + pan + " : "+ session.getAttribute("userid").toString() + "]");
						redirectAttrs.addFlashAttribute("USERINFO", "01");
						view.addObject("error1", "PAN mismatch for registered customer");
						returnUrl = referrerUrl;
					} else {

						if(registrationstatus.getBseregisterstatus().equals("Y") && registrationstatus.getFatcadeclared().equals("1")  && registrationstatus.getAofuploadstatus().equals("Y") ) {
							logger.info("Account full registration is complete. Go to purchase page"+ returnUrl);	
							returnUrl = "/mutual-funds/purchase";
							customertype="EXISTING";
							bseEntryManager.saveMFInitiatedTranasctionRequest(selectedFund);
							redview.setContextRelative(true);
						}else {
							logger.info("Logged in customer but BSE registration was not complete. Send to registration page for completion");
							returnUrl = "/mutual-funds/register?mf=04"; // Complete fresh customer. Create both profile and register for MF
							customertype="COMPLETE_REGISTRATION";
							redview.setContextRelative(true);
						}

					} 
				}
			}else {
				// Check if he is registered customer form LDAP. If already registered customer,
				// then only need to register for MF, else create profile password as well

				ResponseEntity<String> responseProfile = null;

				try {
					responseProfile = profileRestClientService.isUserExisitng(selectedFund.getMobile());
					logger.info("purchasemfbsePost(): Response for user existing check- " + responseProfile.getBody());

				} catch (HttpStatusCodeException e) {
					logger.info("purchasemfbsePost(): Failed to check user exisitng status - " + e.getStatusCode());
				} catch (JsonProcessingException e) {
					logger.error("purchasemfbsePost(): Json parsing excetption- ", e);
				} catch (Exception e) {
					logger.error("purchasemfbsePost(): Error processing request- ", e);
				}
				if (responseProfile.getBody().equalsIgnoreCase("Y")) {

					if (session.getAttribute("token") != null) {
						//						returnUrl = "redirect:/mutual-funds/register?mf=01";
						returnUrl = "/mutual-funds/register?mf=01";
						customertype="LOGGED_NEW_CUSTOMER";
						redview.setContextRelative(true);
					} else {
						returnUrl = "/login?mf=01&id="+ CommonTask.encryptText(selectedFund.getMobile()); // User exist in LDAP, so just need to register MF profile, do not create profile
						customertype="LOGGED_NEW_CUSTOMER";
						redview.setContextRelative(true);
					}

				} else if (responseProfile.getBody().equalsIgnoreCase("N")) {
					//					returnUrl = "redirect:/mutual-funds/register?mf=02"; // Complete fresh customer. Create both profile and register for MF
					returnUrl = request.getRequestURL().toString().split(request.getContextPath())[0] + request.getContextPath()+"/mutual-funds/register?mf=02"; // Complete fresh customer. Create both profile and register for MF
					customertype="NEW_CUSTOMER";
				} else {
					logger.warn("purchasemfbsePost(): Failed to get cutomer status from LDAP");
					//					returnUrl = "redirect:/mutual-funds/register?mf=03";
					returnUrl = "/mutual-funds/register?mf=03";
					customertype="FAILED_SEARCH";

				}

				// returnUrl="redirect:/mutual-funds/register";
			}
		} catch (Exception e) {
			logger.error("purchasemfbsePost(): Failed to check customer in databases/LDAP", e);
			//			redirectAttrs.addFlashAttribute("error1", "Error processing request. Please try after sometime");
			view.addObject("error1", "Error processing request. Please try after sometime");
			//			returnUrl = "redirect:/mutual-funds/funds-explorer?msg=SRV_NOT_RESPONDING";
			returnUrl = returnUrl+"?msg=SRV_NOT_RESPONDING";

		}
		redirectAttrs.addFlashAttribute("selectedFund", selectedFund);
		/*
		 * try{ boolean flag = bseEntryManager.savetransactionDetails(selectedFund);
		 * logger.info("Customer purchase transaction status- "+ flag); }catch(Exception
		 * e){
		 * logger.error("Unable to save customer transaction request",e.getMessage()); }
		 */

		session.setAttribute("PURCHASE_TYPE",customertype);
		session.setAttribute("REQ_MOB",selectedFund.getMobile());

		//		return returnUrl;
		logger.info("purchasemfbsePost(): Rdirectreview- "+ returnUrl);

		//		redview.setUrl(returnUrl);

		redview.setUrl(returnUrl);
		view.setView(redview);

		return view;
		/*
		 * redirectAttrs.addFlashAttribute("selectedFund", selectedFund);
		 * returnUrl="redirect:/mutual-funds/register"; return returnUrl;
		 */

	}


	@RequestMapping(value = "/mutual-funds/register", method = RequestMethod.GET)
	public String registerUserMfGet(
			/* @ModelAttribute("selectedFund")SelectMFFund selectFund , */@RequestParam(name = "mf", required = false) String userType,
			Model map, @ModelAttribute("mfInvestForm") MFCustomers investForm, HttpServletRequest request, HttpSession session, HttpServletResponse response, Device device) {
		// logger.info("@@@@ Inside Login..");
		logger.info("registerUserMfGet(): @@@@ BSE New customer register @@@@");

//		MFCustomers investForm = new MFCustomers();

		UserProfileLdap userDetails = null;
		SelectMFFund selectFund = (SelectMFFund) session.getAttribute("selectedFund");

		if (selectFund != null) {
			logger.info("registerUserMfGet(): Setting register form with mobile and PAN from selected fund details...");
			investForm.setPan1(selectFund.getPan());
			investForm.setMobile(selectFund.getMobile());
		} else {
			logger.info("registerUserMfGet(): selectFund is null.");

		}
		/*
		Category-
		00 - 
		01 - Logged in customer new BSE profile registration
		02 - Fresh customer, both profile and BSE registration required
		04 - Registered customer, incomplete BSE registration
		*/

		if (userType.equalsIgnoreCase("01")) {		// Logged in customer new BSE profile registration
			if (session.getAttribute("token") != null) {
				map.addAttribute("LOGGED", "Y");
				investForm.setMobile(session.getAttribute("userid").toString());
				investForm.setEmail(session.getAttribute("email").toString());
				try {
					userDetails =  profileRestClientService.getProfileData(session.getAttribute("userid").toString(), session.getAttribute("token").toString(), CommonTask.getClientSystemDetails(request).getClientIpv4Address());
					//		    UserProfileLdap userDetails = new ObjectMapper().readValue(profresponse.getBody(), UserProfileLdap.class);
					investForm.setFname(userDetails.getFname());
					investForm.setLname(userDetails.getLname());
					if(userDetails.getPan()!=null) {
						logger.info("Customer already have registered PAN. overwriting form with profile registered PAN..");
						investForm.setPan1(userDetails.getPan());
						session.setAttribute("ACCOUNT_PAN", userDetails.getPan());
					}
					
					if(userDetails.getMail()!=null) {
						investForm.setEmail(userDetails.getMail());
					}
					
					investForm.getAddressDetails().setAddress1(userDetails.getAddress1());
					investForm.getAddressDetails().setAddress2(userDetails.getAddress2());
					investForm.getAddressDetails().setCity(userDetails.getCity());
					investForm.getAddressDetails().setPinCode(userDetails.getPincode());
					investForm.getAddressDetails().setState(userDetails.getState());
					investForm.setMobileverified(userDetails.getMobilenovalidated()!=null?userDetails.getMobilenovalidated():"N");
					investForm.setEmailverified(userDetails.getEmailidvalidated()!=null?userDetails.getEmailidvalidated():"N");


				}catch(Exception e){
					logger.info("Failed to fetch customer details..",e);
				}

			}

		}
		if (userType.equalsIgnoreCase("02")) {
			// Marking this register customer in LDAP
			investForm.setProfileRegRequired(true);
		}

		if (userType.equalsIgnoreCase("04")) {
			logger.info("registerUserMfGet(): Request received to complete profile registration. The request must come from dashbaord or purchase page, check if logged in ");
			if (session.getAttribute("userid") != null) {
				
				try {
					userDetails =  profileRestClientService.getProfileData(session.getAttribute("userid").toString(), session.getAttribute("token").toString(), CommonTask.getClientSystemDetails(request).getClientIpv4Address());
				}catch(Exception e) {
					logger.error("Failed to fetch profile details",e);
				}
				investForm = bseEntryManager.getCustomerInvestFormData(session.getAttribute("userid").toString());
				
				
				if(userDetails!=null && userDetails.getMobilenovalidated()!=null) {
					investForm.setMobileverified(userDetails.getMobilenovalidated());
				}else if(investForm.getMobileverified()== null || investForm.getMobileverified().isEmpty()) {
					investForm.setMobileverified("N");
				}
				
				
				if(userDetails!=null && userDetails.getEmailidvalidated()!=null) {
					investForm.setEmailverified(userDetails.getEmailidvalidated());
				}
				else if(investForm.getEmailverified()==null || investForm.getEmailverified().isEmpty()) {
					investForm.setEmailverified("N");
				}
				
				
				if(session.getAttribute("email")!=null && !session.getAttribute("email").toString().equalsIgnoreCase(investForm.getEmail())) {
					investForm.setEmail(session.getAttribute("email").toString());
				}
				
				logger.info("State found- "+ investForm.getAddressDetails().getState());
			}
		} else {
			investForm.setDividendPayMode("02");
			investForm.setOccupation("01");
		}

		//		AddressDetails d = new AddressDetails();
		//		d.setState("WB");
		//		investForm.setAddressDetails(d);
		map.addAttribute("mfInvestForm", investForm);
		
		
		if(investForm.getAddressDetails()!=null &&  investForm.getAddressDetails().getState()!=null) {
			Map<String,String> data = new HashMap<String, String>();
			String statefrommapping = InvestFormConstants.hdfcstatekey.get(investForm.getAddressDetails().getState());
			
			Map<String,String> filters = new HashMap<String, String>();
			filters.put("stateid", statefrommapping);
			filters.put("search",investForm.getAddressDetails().getCity());
			filters.put("cityvaluetype", "CITYNAME");
			Searchlocationdetials data2 = new Searchlocationdetials();
			data2.stateid = statefrommapping;
			data2.cityid = investForm.getAddressDetails().getCity();
			data = hdfcService.searchcitypincode2(data2,filters, data2.stateid,data2.cityid);
			logger.info("Pincode list retrieved- "+ data.size());
			map.addAttribute("pincode",data);
		}
		
		if(investForm.getAddressDetails()!=null &&  investForm.getAddressDetails().getState()!=null) {
			Map<String,String> data = new HashMap<String, String>();
			String statefrommapping = InvestFormConstants.hdfcstatekey.get(investForm.getAddressDetails().getState());
			
			Map<String,String> filters = new HashMap<String, String>();
			filters.put("stateid", statefrommapping);
			filters.put("search","city");
			data = hdfcService.searchcity2(filters, statefrommapping);
			logger.info("City list retrieved- "+ data.size());
			map.addAttribute("cities",data);
		}
		

		logger.info("registerUserMfGet(): Get device platform during MF registration -" + device.getDevicePlatform());

		//		return "bsemf/bse-form-new-customer2";
		return "bsemf/bse-form-version2";

	}

	@RequestMapping(value = "/mutual-funds/register", method = RequestMethod.POST)
	public String registerBsepost(@Valid @ModelAttribute("mfInvestForm") MFCustomers investForm,
			BindingResult bindResult, Model map, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes attrs, HttpSession session) {

		logger.info("registerBsepost(): BSE MF STAR Customer Register post controller");
//		String returnUrl = "bsemf/bse-registration-status";
		String returnUrl = "redirect:/mutual-funds/mf-registration-status";
		String returnurlsuccess="redirect:/mutual-funds/mf-registration-status";
		String returnurlonerror= "bsemf/bse-form-version2";
		String mfRegflag = "NOT_COMPLETE";
		BseApiResponse bseregistrationstatus = new BseApiResponse();
		//		String fatcaFlag = "FAIL";
		String validationerrormsg = "";
		BseApiResponse fatcaresponse = null;
		boolean validationpass = true;
		boolean existdata=false;
		
		String bseclientcode = null;
		BseApiResponse aofuploadresponse = null;
		
		boolean bseregistersuccess=false, fatcauploadsuccess =false, aofuploadsuccess =false;
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			logger.info("Submitted form data -"+ mapper.writeValueAsString(investForm));
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if (bindResult.hasErrors()) {
			validationpass = false;
			validationerrormsg = bindResult.getFieldError().getDefaultMessage();
		} else if (!investForm.isUbo()) {
			validationpass = false;
			validationerrormsg = "Please confirm the policy";

		} else if (session.getAttribute("userid")!=null && !session.getAttribute("userid").toString().equalsIgnoreCase(investForm.getMobile())) {
			logger.info("Logged session found.. Session and provdided user ID do not match..");
			validationpass = false;
			validationerrormsg = "Mobile no. must be same as that of logged in account. ";
		} else if(session.getAttribute("email") != null && !session.getAttribute("email").toString().equalsIgnoreCase(investForm.getEmail())) {
			logger.info("Logged session found.. Session and provdided email ID do not match..");
			validationpass = false;
			validationerrormsg = "Email ID must be same as that of logged in account. ";

		} else if(session.getAttribute("ACCOUNT_PAN")!=null && !session.getAttribute("ACCOUNT_PAN").toString().equalsIgnoreCase(investForm.getPan1())) {
			logger.info("Account is already registered with different PAN. New PAN tagging will not be permitted- "+ investForm.getPan1());
			validationpass=false;
			validationerrormsg = "Your Account is already registered with another PAN. Different PAN not permitted";
		}else if(session.getAttribute("pan")!=null && !session.getAttribute("pan").toString().equalsIgnoreCase(investForm.getPan1())) {
			logger.info("Account is already registered with different PAN. New PAN tagging will not be permitted- "+ investForm.getPan1());
			validationpass=false;
			validationerrormsg = "Your Account is already registered with another PAN. Different PAN not permitted";
		}  else {
			if(investForm.getCustomerdob()!=null && !investForm.getCustomerdob().isEmpty()) {
			
			try {
				logger.info("registerBsepost() : Investor DOB: " + investForm.getCustomerdob());
				Date dob = simpleDateFormat2.parse(investForm.getCustomerdob());
				Calendar dobdt = Calendar.getInstance();
				dobdt.setTime(dob);

				Calendar todaydt = Calendar.getInstance();
				todaydt.setTime(new Date());
				if (dob.after(new Date())) {
					validationpass = false;
					validationerrormsg = "DOB given is future date!";
				}
				int age = todaydt.get(Calendar.YEAR) - dobdt.get(Calendar.YEAR);
				if (age < 18 || age > 65) {
					validationpass = false;
					validationerrormsg = "Allowed Investment age is 18-65 years";
				}

				/*
				 * System.out.println("is date future -"+ dob.after(new Date()));
				 * 
				 * System.out.println("Age- "+ (todaydt.get(Calendar.YEAR) -
				 * dobdt.get(Calendar.YEAR) ));
				 */
				
				
				String bseFormatDob = simpleDateFormat1.format(dob);
				logger.info("DOB field converted to DB format for record update- "+ bseFormatDob);
				investForm.setInvDOB(bseFormatDob);
				
				logger.info("registerBsepost(): Investor DOB is in desired format. Proceed");

			} catch (Exception e) {
				validationpass = false;
				validationerrormsg = "Invalid date of birth format";
			}
			}else {
				validationpass = false;
				validationerrormsg = "Date of birth is required.";
			}
		}

		if(validationpass) {
			logger.info("Basic validation passed... Check mobile no change....");
			if(session.getAttribute("INITIATED_MOBILE")!=null && !session.getAttribute("INITIATED_MOBILE").toString().equalsIgnoreCase(investForm.getMobile())) {
				logger.info("Mobile no changed from initiated mobile no."+session.getAttribute("INITIATED_MOBILE").toString()+ ". Validate if exisitng");
				existdata = bseEntryManager.isExisitngBSECustomerByMobile(investForm.getMobile());
				if(existdata) {
					logger.info("Mobile is changed during registration which is already existing.... Prevent registration...");
					validationpass = false;
					validationerrormsg = "Updated mobile already registered. Login to complete registartion or change it.";
				}else {
					if(investForm.isProfileRegRequired() == false) {
						logger.info("Mobile no changed which requires validation in LDAP to pass... Setting to true..");
						investForm.setProfileRegRequired(true);
					}
				}

			}
		}


		if (!validationpass) {
			logger.info("registerBsepost(): Error validating form data: " + validationerrormsg);
			map.addAttribute("error", validationerrormsg);
//			return "bsemf/bse-form-new-customer2";
			return returnurlonerror;
		}

		try {
			
			// Check if user required to be registered at portal first
			logger.info("registerBsepost(): Is profile generation required during MF profile registration? - " + investForm.isProfileRegRequired());
			if (investForm.isProfileRegRequired()) {
				if(session.getAttribute("token") == null ) {
					logger.info("registerBsepost(): This is a fresh customer. Register the user first for portal");
					
					HttpClientResponse httpResponse = profilerequesthandler.savebsecustomer(investForm, "GENERATE", "MF_REG_NEW", request);
					
					if(httpResponse.getResponseCode() == CommonConstants.HTTP_CLIENT_CALL_SUCCESS) {
						logger.info("registerBsepost(): Registration successful for mobile number during new BSE MF registration- "+ investForm.getMobile());
						investForm.setProfileRegRequired(false);
						investForm.setProfileuniqueid(httpResponse.getData1());
					}else {
						validationpass =false;
						if(httpResponse.getRetrunMessage().equalsIgnoreCase("ERROR")) {
							validationerrormsg = "Error encountered during registration. Please try again.";
						}else {
							validationerrormsg= httpResponse.getRetrunMessage();
						}
					}
				}else {
					logger.info("registerBsepost(): ACCOUNT ALREADY LOGGED IN as token found... Skipping ldap registration "+ investForm.getMobile());
				}
			} else {
				if(investForm.getProfileuniqueid()==null) {
					if(session.getAttribute("token")!=null) {
						ResponseEntity<String> profileidfromsession= profileRestClientService.extractdatafromtoken(session.getAttribute("userid").toString(), session.getAttribute("token").toString(), CommonTask.getClientSystemIp(request));
						if(!profileidfromsession.getBody().equals("INVALID")) {
							logger.info("Setting profile uniue ID retrived from session token..");
							investForm.setProfileuniqueid(profileidfromsession.getBody());
						}
					}
				}
			}

			if (!validationpass) {
				logger.info("registerBsepost(): Error validating form data: " + validationerrormsg);

				map.addAttribute("error", validationerrormsg);
//				return "bsemf/bse-form-new-customer2";
				return returnurlonerror;

			}
			
			Bseregistrationstatus bseregisterstatus = bseEntryManager.getbseregistrationstatus(investForm.getMobile(), null, null, null);
			ClientSystemDetails systemdetails = CommonTask.getClientSystemDetails(request);
//			logger.info("KYC verified? "+ investForm.getPan1KycVerified());
			// Map other required fields for FATCA based on PAN
			// ----------------------------------------------------------------------------
			
//			investForm.getFatcaDetails().setIdentificationDocType("C");
//			investForm.getFatcaDetails().setDaclarationDate(new Date());
//			investForm.getFatcaDetails().setCreatedBy("SELF REGISTRATION");

//			investForm.getFatcaDetails().setSystemip(systemDet.getClientIpv4Address());
//			investForm.getFatcaDetails().setSystemDetails(systemDet.getClientBrowser());
//			investForm.getFatcaDetails().setUscanadaCitizen(investForm.getFatcaDetails().isUsCitizenshipCheck() ? "Y" : "N");
			// -----------------------------------------------------------------------------

			// Save customer registration details
			logger.info("registerBsepost(): Checking if customer already registered with bean flag- " + investForm.getCustomerRegistered());
			
			
//			if (investForm.getCustomerRegistered().equalsIgnoreCase("N")) {
			if (bseregisterstatus.getAccountexist().equalsIgnoreCase("N") || bseregisterstatus.getBseregisterstatus().equalsIgnoreCase("N")) {
				
				
				logger.info("Customer type- "+ session.getAttribute("PURCHASE_TYPE"));
				String customertype= session.getAttribute("PURCHASE_TYPE")!=null?session.getAttribute("PURCHASE_TYPE").toString():"NA";

				bseregistrationstatus = bseEntryManager.saveCustomerDetails(investForm,customertype,session.getAttribute("token") != null?"Y":"N", session.getAttribute("token") != null?session.getAttribute("userid").toString() : (session.getAttribute("INITIATED_MOBILE")!=null?session.getAttribute("INITIATED_MOBILE").toString():"NA"),systemdetails );

				logger.info("registerBsepost(): Customer MF registration status completed in DB- " + bseregistrationstatus.getResponseCode() + " -> " +bseregistrationstatus.getRemarks());
				if (bseregistrationstatus.getResponseCode().equalsIgnoreCase(CommonConstants.TASK_SUCCESS_S)) {
					logger.info("registerBsepost(): Customer saved to database. Setting register flag to yes for customer - "+ investForm.getMobile());
					investForm.setCustomerRegistered("Y");
					bseregistersuccess = true;
					//Link customer PAN and BSE client ID in LDAP account
//					bseclientcode= bseEntryManager.getClientIdfromMobile(investForm.getMobile());
					bseclientcode = bseregistrationstatus.getData1();
					
					ResponseEntity<String> responsePortal = null;
					logger.info("MF REG - > Update LDAP with PAN and bseclientID....");
//					responsePortal = profileRestClientService.linkmfaccountDetails(investForm.getMobile(), investForm.getPan1(), bseclientcode);
					
					UserProfile profileupdate = new UserProfile();
					profileupdate.setMobile(investForm.getMobile());
					profileupdate.setPan1(investForm.getPan1());
					profileupdate.setBseclientid(bseclientcode);
					profileupdate.setMobilenovalidated(investForm.getMobileverified());
					profileupdate.setEmailidvalidated(investForm.getEmailverified());
					
					responsePortal = profileRestClientService.updateprofiledetails(profileupdate);
					
					String status = responsePortal.getBody();
					logger.info("registerBsepost(): Linking successful for mobile numberwith MF account- "+ investForm.getMobile() + " -> "+ status);

//					Send mail notification to SUPPORT team
					logger.info("registerBsepost(): Customer BSE registration successful. Drop mail to support team.. ");
					mailSenderInterface.sendMFRegisterNotification("MF_REG_NOTIFICATION", investForm.getInvName(), investForm.getMobile(), null, "NA", investForm.getPan1(), investForm.getPan1KycVerified());
					
					/*
					if (fatresponse.getResponseCode().equals("100")) {
						logger.info("registerBsepost(): FATCA save status to database and registration success. Set CUSTOMER_TYPE");
						returnUrl = "redirect:/mutual-funds/mf-registration-status";
						session.setAttribute("CUSTOMER_TYPE", "NEW_CUSTOMER");
						attrs.addFlashAttribute("mfInvestForm", investForm);
						attrs.addAttribute("STATUS", "Y");
						attrs.addFlashAttribute("KYCVERIFIED", investForm.getPan1KycVerified());

					}else {
						logger.info("MF registered but FATCA save failed. Return with result..");
						map.addAttribute("error", "Registration success. FATCA declaration failed for- " + fatresponse.getRemarks());
						returnUrl = "bsemf/bse-form-new-customer2";
					}
					*/
					
				}else {
					logger.info("BSE registration failed. Return back to registration page for mobile- "+ investForm.getMobile());
					validationerrormsg = bseregistrationstatus.getRemarks();
				}

			} else {
				if(bseregisterstatus.getAccountexist().equalsIgnoreCase("Y")) {
					if(bseregisterstatus.getBseregisterstatus().equalsIgnoreCase("Y")) {
						logger.info("Submitted form shows customer BSE account registration complete.. Proceed with FATCA and AOF.. ");
						bseregistersuccess = true;
					}
				}else {
					logger.info("BSE registration not complte but getregistered is set to true. Resetting status");
					investForm.setCustomerRegistered("N");
					validationerrormsg="Registration not complete. Please try again.";
				}
			}
			
			
				if(bseregistersuccess) {
					if(bseclientcode == null) {
						logger.info("Get BSE client code from database for user- " +  investForm.getMobile());
						bseclientcode = bseEntryManager.getClientIdfromMobile(investForm.getMobile());
					}
					logger.info("Proceed with FATCA upload. Current status- "+ bseregisterstatus.getFatcadeclared());
//					Send FATCA Declaration. New code added on 24-04-21 to replace existing logic
//					if(bseregisterstatus.getFatcadeclared().equals("0")) {
					if(bseregisterstatus.getFatcadeclared().equals("N") || bseregisterstatus.getFatcadeclared().equals("0")) {
						fatcaresponse = bseEntryManager.saveFatcaDetails(investForm,(bseregistersuccess?"Y":"N"),null,bseclientcode,systemdetails);
					}else {
						logger.info("FATCA is already declared.. Skipping the step and declare false state");
						fatcaresponse = new BseApiResponse();
						
//						fatcaresponse.setResponseCode(CommonConstants.TASK_FAILURE_S);
//						fatcaresponse.setRemarks("Failed to validate registraition status!");
						
						fatcaresponse.setResponseCode(CommonConstants.TASK_SUCCESS_S);
						fatcaresponse.setRemarks("FATCA_UPLOAD_SUCCESS");
					}
					
					if (fatcaresponse.getResponseCode().equals(CommonConstants.TASK_SUCCESS_S)) {
						logger.info("registerBsepost(): FATCA  complete for customer- " + investForm.getPan1());
						
						/*
						returnUrl = "redirect:/mutual-funds/mf-registration-status";
						session.setAttribute("CUSTOMER_TYPE", "NEW_CUSTOMER");
						attrs.addFlashAttribute("mfInvestForm", investForm);
						attrs.addAttribute("STATUS", "Y");
						*/
						fatcauploadsuccess =true;

					} else {
						logger.info("registerBsepost(): MF registered but FATCA save failed. Return with result..");
						// map.addAttribute("success", "Your registration partially complete. Your FATCA
						// declaration failed for below reason-");
//						map.addAttribute("error", "Registration success. FATCA declaration failed for- " + fatresponse.getRemarks());
						validationerrormsg= "Registration partially successful! FATCA declaration submit failed for- " + fatcaresponse.getRemarks();
					}


					//				Send AOF to BSE
					if(bseclientcode!=null) {
						if(fatcauploadsuccess) {
							aofuploadresponse = bseEntryManager.uploadAOFForm(investForm.getMobile(), env.getProperty(CommonConstants.BSE_AOF_GENERATION_FOLDR),env.getProperty(CommonConstants.BSE_AOF_LOGO_LOCATION), bseclientcode,investForm);
							
							if(aofuploadresponse!=null && aofuploadresponse.getStatusCode().equalsIgnoreCase(CommonConstants.TASK_SUCCESS_S)) {
								aofuploadsuccess = true;
								
								
							}else {
								validationerrormsg= "Registration partially successful! AOF uplaod not complete for- " + aofuploadresponse.getRemarks();
							}
							
						}else {
							logger.info("FATCA upload was not success hence skipping AOF generation...");
						}
					}else {
						logger.info("Clientcode not found. Skip AOF upload call for mobile- "+ investForm.getMobile());
					}
					
					//Send request for Nominee authentication
					if(bseclientcode!=null) {
						Nomineeverification verify = new Nomineeverification();
						MFNominationForm nomineedetails = new MFNominationForm();
						Nomineerecords records = new Nomineerecords();
						
						nomineedetails.setClientID(bseclientcode);
						records.setClientcode(bseclientcode);
						records.setNominationopt("Y");
						records.setNominationauthmode("O");
						records.setNomineepan1(investForm.getNominee().getNominee1pan());
						records.setNomineeguardianpan1(investForm.getNominee().getNominee1guardianpan());
						
						verify.setParam(records);
						verify.setNomineedetails(nomineedetails);
						Nomineeregistrationresponse registrationresponse = bseEntryManager.submitnomineeverification(verify);
//						investForm.setNomineeregresponse(registrationresponse);
						investForm.setNomineeregcomplete(true);
						if(registrationresponse.getStatuscode().equals("100")) {
						
						String callbackUrl = URI.create(request.getRequestURL().toString()).resolve(request.getContextPath())
								.toString()
								+ "/nominee-registration/nominee-verify-status";
						
						Nominee2faresponse nominee2faresponse = bseEntryManager.authenticatenominee(investForm.getMobile(), bseclientcode,callbackUrl);
						investForm.setNomineeregresponse(nominee2faresponse);
						}
						
					}

					
				}
				
				
				

			// flag ="SUCCESS";

			/*
			 * String customerid=BseRelatedActions.generateID(investForm.getInvName(),
			 * investForm.getPan1(), null, investForm.getMobile(),1);
			 * investForm.setClientID(customerid); String flag =
			 * investmentConnectorBseInterface.saveCustomerRegistration(investForm, null);
			 * if(flag.equalsIgnoreCase("false")){ returnUrl= "bsemf/bse-form-new-customer2";
			 * map.addAttribute("error", "Customer issue with given PAN no.");
			 * 
			 * }
			 */
		} catch (Exception e) {
//			returnUrl = "bsemf/bse-form-new-customer2";
			returnUrl = returnurlonerror;
			map.addAttribute("error", "Unable to register customer currently.");
			validationerrormsg="Internal error. Unable to register customer currently. Please try again after sometime";
			logger.error("registerBsepost(): Unable to save customer registration", e);

		}

		try {
			if(session.getAttribute("selectedFund")!=null) {
				logger.info("Update session selectedfund mobile and pan data...");
				SelectMFFund selectedfund = (SelectMFFund) session.getAttribute("selectedFund");
				session.removeAttribute("selectedFund");
				selectedfund.setMobile(investForm.getMobile());
				selectedfund.setPan(investForm.getPan1());
				selectedfund.setClientID(bseclientcode);
				
				session.setAttribute("selectedFund", selectedfund);
			}
		}catch(Exception e) {
			logger.error("Error updating fund details in session..",e);
		}

		if(bseregistersuccess && fatcauploadsuccess && aofuploadsuccess) {
//			returnUrl = "redirect:/mutual-funds/mf-registration-status";
			returnUrl = returnurlsuccess;
			session.setAttribute("CUSTOMER_TYPE", "NEW_CUSTOMER");
			attrs.addFlashAttribute("mfInvestForm", investForm);
			session.removeAttribute("mfInvestForm");
			session.setAttribute("mfInvestForm", investForm);
			attrs.addAttribute("STATUS", "Y");
			
		}else {
//			returnUrl = "bsemf/bse-form-new-customer2";
			returnUrl = returnurlonerror;
			map.addAttribute("error", validationerrormsg);
			
			/*
			try {
				logger.info("registerBsepost(): Convert DOB to datepicker format dd/mm/yyyy  before returning to page");
				Date dob = simpleDateFormat1.parse(investForm.getInvDOB());
				String convt = simpleDateFormat2.format(dob);
				investForm.setInvDOB(convt);
			} catch (Exception e) {
				logger.error("registerBsepost(): Failed converting DOB format. Setting as it is...",e);
			}
			*/
		}
		logger.info("registerBsepost(): Current date format before returning to registration page- - " + investForm.getInvDOB());
		
		return returnUrl;

	}

	@RequestMapping(value = "/mutual-funds/mf-registration-status", method = RequestMethod.GET)
	public String mfRegistrationStatusGet(@ModelAttribute("STATUS") String status, @ModelAttribute("KYCVERIFIED") String pan1verified,
			/* @ModelAttribute("mfInvestForm") MFCustomers investForm, */ ModelMap map, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		logger.info("mf-registration-status Get controller");
		String returnUrl = "bsemf/bse-registration-status2";
		List<String> customerPortfolios = new ArrayList<String>();
		MFCustomers investForm = null;
		SelectMFFund selectedfund = null;
		String purchasetype= (String) session.getAttribute("PURCHASE_TYPE");
		logger.info("PURCHASE_TYPE info- " + purchasetype);
		try {
			
			investForm = (MFCustomers) session.getAttribute("mfInvestForm");
			selectedfund = (SelectMFFund) session.getAttribute("selectedFund");
			session.removeAttribute("selectedFund");
			
			if(investForm==null) {
				investForm = new MFCustomers();
			}
			if(selectedfund==null) {
				logger.info("No selected fund details found... Request may be from dashboard..");
				selectedfund = new SelectMFFund();
				map.addAttribute("FUND_DATA","EMPTY");
			}else {
				map.addAttribute("FUND_DATA","PROVIDED");
				// Set SIP start date from
				if (selectedfund.getInvestype().equalsIgnoreCase("SIP")) {
					int initialMonth = LocalDate.now().getMonth().getValue();
					int initialYear = LocalDate.now().getYear();
					int currentday = LocalDate.now().getDayOfMonth();
					if (Integer.valueOf(selectedfund.getSipDate()) > currentday) {
						selectedfund.setSipStartMonth(initialMonth);
					} else {
						selectedfund.setSipStartMonth(initialMonth + 1);
					}
					selectedfund.setSipStartYear(initialYear);

					selectedfund.setNoOfInstallments(120); // Default SIP installments to 5 years -- todo dynamic
					
					selectedfund.seteMandateRegRequired(true);
					selectedfund.setMandateType("N");
					
					/*
					map.addAttribute("bankacc",
							userbankDetails.getAccountNumber() != null
							? "XXXXXXXXX" + userbankDetails.getAccountNumber()
							.substring(userbankDetails.getAccountNumber().length() - 3)
							: "NOT AVAILABLE");
					
					*/
//					map.addAttribute("bankname", userbankDetails.getBankName());
					/* map.addAttribute("ifsc", userbankDetails.getIfscCode()); */
//					map.addAttribute("isEmandateComplete", !selectedFund.iseMandateRegRequired()); // should to opposite
				}
				
				String transId = bseEntryManager.generateTransId();
				logger.info("purchaseBseMfAfterLogin(): Generated transation ID for current order of customer: " + selectedfund.getClientID() + " : " + transId);

				selectedfund.setTransactionID(transId);
				selectedfund.setInvestorName(investForm.getInvName());
				selectedfund.setPortfolio("NEW");
				selectedfund.setAmcimg(InvestFormConstants.amchouseicon.get(selectedfund.getAmcCode()));
				
				map.addAttribute("paymentMethod", InvestFormConstants.bsePaymentMethod);
			}
			
			logger.info("Details of investor- " + (investForm!= null?investForm.getPan1():"NULL")+ "->"+  (investForm!= null?investForm.getHoldingMode():"NULL") + "-> "+ pan1verified);
			//		String returnUrl = "bsemf/bse-registration-status";
			
//			session.setAttribute("mfRegisterdUser", investForm);
			
			
			
//			Map fund transaction details
			if ((session.getAttribute("PURCHASE_TYPE") != null && session.getAttribute("PURCHASE_TYPE").toString().equalsIgnoreCase("NEW_CUSTOMER"))) {

				logger.info("purchaseBseMfAfterLogin(): Customer is processing to purchase after completing registration without login with mobile- " + investForm.getMobile() + " : PAN: " + investForm.getPan1());
				
			} 
			


			
//			selectedfund.setClientID();
			
			/*
			if (customerData.size() != 0) {
				selectedFund.setClientID(customerData.get(0).getClientID());
				logger.info("purchaseBseMfAfterLogin(): Investor name- " + customerData.get(0).getInvName());
				// logger.info("Bank details- "+
				// customerData.get(0).getBankDetails().getBankName());

				UserBankDetails userbankDetails = bseEntryManager.getCustomerBankDetails(customerData.get(0).getClientID());

				if (userbankDetails != null) {

					List<BseMandateDetails> mandate = bseEntryManager.getCustomerMandateDetails(
							customerData.get(0).getClientID(), userbankDetails.getAccountNumber());
					logger.info("purchaseBseMfAfterLogin(): Total emdandates fetched-  " + mandate.size());
					if (mandate.size() > 0 && mandate.get(0).isMandateComplete()) {
						logger.info("purchaseBseMfAfterLogin(): Emandate found for current customer.");
						selectedFund.setMandateType("I");
						selectedFund.seteMandateRegRequired(false);
						selectedFund.setMandateId(mandate.get(0).getMandateId());
						List<String> manadates = new ArrayList<String>(); 
						for(int i=0;i<mandate.size();i++) {
							manadates.add(mandate.get(i).getMandateId());
						}
						map.addAttribute("allmandates", manadates);
					} else {
						logger.info("purchaseBseMfAfterLogin(): No emnadate found for customer..");
						selectedFund.seteMandateRegRequired(true);
					}

					map.addAttribute("bankacc",
							userbankDetails.getAccountNumber() != null
							? "XXXXXXXXX" + userbankDetails.getAccountNumber()
							.substring(userbankDetails.getAccountNumber().length() - 3)
							: "NOT AVAILABLE");
					map.addAttribute("bankname", userbankDetails.getBankName());
					map.addAttribute("isEmandateComplete", !selectedFund.iseMandateRegRequired()); // should to opposite
				} else {
					logger.info(
							"purchaseBseMfAfterLogin(): Customer bank details not found. Check server log for details");
				}
			} else {
				logger.info("purchaseBseMfAfterLogin(): No customer detaails found for purchase!");
			}
			*/

			//			String transId = generateTransId();
			
			
//			------------------------------------------------			
			
			
		}catch(Exception e) {
			logger.error("Failed to retrieve session details",e);
		}
		
//		map.addAttribute("customerData", investForm);

		
		map.addAttribute("selectedFund", selectedfund);
		map.addAttribute("PURCHASE_TYPE",purchasetype);
		map.addAttribute("investForm", investForm);
		if(investForm.getNomineeregresponse()!=null && investForm.getNomineeregresponse().getStatuscode().equals("100") ) {
			map.addAttribute("n2faurl", investForm.getNomineeregresponse().getReturnrul());
		}else {
			map.addAttribute("n2faurl", "#");
			
		}
//		map.addAttribute("KYCVERIFIED", pan1verified);
//		session.setAttribute("userid", investForm.getMobile());
//		session.setAttribute("token", "NEW_USER");
		
		return returnUrl;

	}

	
	@RequestMapping(value = { "/mutual-funds/mfInvestRegister" }, method = RequestMethod.GET)
	public String defaultPostUrl(@Valid @ModelAttribute("mfInvestForm") MFCustomers investForm,
			BindingResult bindResult, Model map, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes attrs, HttpSession session) {
		return "redirect:/";

	}


	@RequestMapping(value = "/mutual-funds/purchase", method = RequestMethod.GET)
	public String purchaseBseMfAfterLogin(Model map, HttpServletRequest request, HttpServletResponse response,
			HttpSession session, RedirectAttributes redirectAttrs) {

		logger.info("purchaseBseMfAfterLogin(): BSE MF STAR Purchase Get controller");
		String returnUrl = "bsemf/bse-mf-purchase";

		List<MFCustomers> customerData = null;
		SelectMFFund selectedFund = null;
		List<String> customerPortfolios = new ArrayList<String>();

		try {
			logger.info("purchaseBseMfAfterLogin(): PURCHASE_TYPE null? "+ (session.getAttribute("PURCHASE_TYPE") == null));
			logger.info("purchaseBseMfAfterLogin(): PURCHASE_TYPE -> " + session.getAttribute("PURCHASE_TYPE"));
		} catch (Exception e) {
			logger.error("purchaseBseMfAfterLogin(): Error reading purchase type customer", e);
		}

		try {
			selectedFund = (SelectMFFund) session.getAttribute("selectedFund");
			if (selectedFund == null) {
				logger.info("purchaseBseMfAfterLogin(): No selected funds details found in session. Returning to fund selection page.");
				redirectAttrs.addFlashAttribute("error1", "Funds details was not found in request. Please select again.");
				return "redirect:/mutual-funds/funds-explorer";
			}

			if (session.getAttribute("token") != null) {

				logger.info("purchaseBseMfAfterLogin(): Purchase order start for- " + selectedFund.getSchemeName()
				+ " : " + selectedFund.getSchemeCode());

				String pan = bseEntryManager.getCustomerPanfromMobile(session.getAttribute("userid").toString());

				if (!session.getAttribute("userid").toString().equalsIgnoreCase(selectedFund.getMobile()) || !pan.equalsIgnoreCase(selectedFund.getPan())) {
					logger.warn("purchaseBseMfAfterLogin(): Customer logged in with another mobile than provided during form fillup. Also the PAN number is different. Redirecting back to fund selection page and override information with current details");
					logger.warn("purchaseBseMfAfterLogin(): Data provided during form fillup:[ " + selectedFund.getPan() + " : " + selectedFund.getMobile() + "] Data from session user:[" + pan + " : " + session.getAttribute("userid").toString() + "]");
					redirectAttrs.addFlashAttribute("error1", "Mobile or PAN mismatch for registered customer");
					redirectAttrs.addFlashAttribute("USERINFO", "01");
					// returnUrl = "redirect:/mutual-funds/top-performing";
					logger.info("purchaseBseMfAfterLogin(): Further processing prevented. Return");
					return "redirect:/mutual-funds/funds-explorer";
				} else {
					logger.info("purchaseBseMfAfterLogin(): Customer log in data matches with form data.");

					customerData = bseEntryManager.getCustomerByPan(selectedFund.getPan());
					// logger.info("Data size returned- "+ customerData.size());
					// Find customer's portfolio

					if (customerData.get(0).getBseregistrationSuccess().equalsIgnoreCase("N")) {
						map.addAttribute("REG_COMPLETE", "N");
					} else {
						map.addAttribute("REG_COMPLETE", "Y");
					}

					logger.info("purchaseBseMfAfterLogin(): Search for customer portfolio for details: "
							+ selectedFund.getSchemeCode()  + " :CLIENT ID : " + customerData.get(0).getClientID()
							+ " : RTA Agent: " + selectedFund.getRtaAgent() + " PAN: "+ selectedFund.getPan());

					customerPortfolios = bseEntryManager.getSelectedAmcPortfolio(selectedFund.getSchemeCode(),
							selectedFund.getPan(), selectedFund.getRtaAgent());

					if (customerPortfolios != null) {
						logger.info("purchaseBseMfAfterLogin(): Portfolio size- " + customerPortfolios != null
								? customerPortfolios.size()
										: "No portfolio data found..");
						/*
						 * if(customerPortfolios.size()== 0){ customerPortfolios.add("NEW"); }else{
						 * selectedFund.setPortfolio(customerPortfolios.get(0)); }
						 */
						if (customerPortfolios.size() > 0) {
							selectedFund.setPortfolio(customerPortfolios.get(0));
						}
					}
					/*
					 * map.addAttribute("amcPortFolio", customerPortfolios);
					 * 
					 * map.addAttribute("customerData", customerData.get(0));
					 * map.addAttribute("GETDATA", "S");
					 */
				}

			} else if ((session.getAttribute("PURCHASE_TYPE") != null && session.getAttribute("PURCHASE_TYPE").toString().equalsIgnoreCase("NEW_CUSTOMER"))) {

				logger.info("purchaseBseMfAfterLogin(): Customer is processing to purchase after completing registration without login with mobile- " + selectedFund.getMobile() + " : PAN: " + selectedFund.getPan());
				customerPortfolios.add("NEW");
				customerData = bseEntryManager.getCustomerByPan(selectedFund.getPan());

				if (customerData.get(0).getBseregistrationSuccess().equalsIgnoreCase("N")) {
					logger.info("purchaseBseMfAfterLogin(): registartion process not complete... ");
					map.addAttribute("REG_COMPLETE", "N");
				} else {
					map.addAttribute("REG_COMPLETE", "Y");
				}

			} else {
				logger.info("purchaseBseMfAfterLogin(): MF purchase request required customer to be either logged in or be a fresh customer to access the url. Returning");
				// returnUrl="redirect:/mutual-funds/top-performing";
				redirectAttrs.addFlashAttribute("error1", "Only logged in or new customer can place order!");
				return "redirect:/mutual-funds/funds-explorer";
			}

			// Set SIP start date from
			if (selectedFund.getInvestype().equalsIgnoreCase("SIP")) {
				int initialMonth = LocalDate.now().getMonth().getValue();
				int initialYear = LocalDate.now().getYear();
				int currentday = LocalDate.now().getDayOfMonth();
				if (Integer.valueOf(selectedFund.getSipDate()) > currentday) {
					selectedFund.setSipStartMonth(initialMonth);
				} else {
					selectedFund.setSipStartMonth(initialMonth + 1);
				}
				selectedFund.setSipStartYear(initialYear);

				selectedFund.setNoOfInstallments(60); // Default SIP installments to 5 years -- todo dynamic
			}

			if (customerData.size() != 0) {
				selectedFund.setClientID(customerData.get(0).getClientID());
				logger.info("purchaseBseMfAfterLogin(): Investor name- " + customerData.get(0).getInvName());
				// logger.info("Bank details- "+
				// customerData.get(0).getBankDetails().getBankName());

				UserBankDetails userbankDetails = bseEntryManager.getCustomerBankDetails(customerData.get(0).getClientID());

				if (userbankDetails != null) {

					getmandatedetails(map, customerData, selectedFund, userbankDetails);
					
					String bankref= userbankDetails.getAccountNumber() != null ? "XXXXXXXXX" + userbankDetails.getAccountNumber().substring(userbankDetails.getAccountNumber().length() - 3): "NOT AVAILABLE";
					map.addAttribute("bankacc",bankref);
					map.addAttribute("bankname", userbankDetails.getBankName());
					selectedFund.setBanbkaccount(bankref);
					selectedFund.setOther1(userbankDetails.getBankName());
					
					/* map.addAttribute("ifsc", userbankDetails.getIfscCode()); */
					map.addAttribute("isEmandateComplete", !selectedFund.iseMandateRegRequired()); // should to opposite
					// to
					// emandatecompelete
					// staus
				} else {
					logger.info(
							"purchaseBseMfAfterLogin(): Customer bank details not found. Check server log for details");
				}
			} else {
				logger.info("purchaseBseMfAfterLogin(): No customer detaails found for purchase!");
			}

			// Generate Transaction ID and check if already existing
			//			String transId = generateTransId();
			String transId = bseEntryManager.generateTransId();
			logger.info("purchaseBseMfAfterLogin(): Generated transation ID for current order of customer: " + customerData.get(0).getClientID() + " : " + transId);

			selectedFund.setTransactionID(transId);
			selectedFund.setInvestorName(customerData.get(0).getInvName());
			
			map.addAttribute("amcPortFolio", customerPortfolios);
			map.addAttribute("customerData", customerData.get(0));
			map.addAttribute("GETDATA", "S");

			map.addAttribute("paymentMethod", InvestFormConstants.bsePaymentMethod);
			//	    map.addAttribute("calendarmonths", InvestFormConstants.bseInvestMonths);
			//	    map.addAttribute("sipyear", InvestFormConstants.bseInvestStartYear);
			map.addAttribute("selectedFund", selectedFund);

		} catch (Exception e) {
			logger.error("purchaseBseMfAfterLogin(): Unable to query database to fetch customer data- ", e);
			map.addAttribute("GETDATA", "F");
		}



		logger.info("purchaseBseMfAfterLogin(): Returning to view " + returnUrl);
		return returnUrl;

	}


	private void getmandatedetails(Model map, List<MFCustomers> customerData, SelectMFFund selectedFund,
			UserBankDetails userbankDetails) {
		List<BseMandateDetails> mandate = bseEntryManager.getCustomerMandateDetails(
				customerData.get(0).getClientID(), userbankDetails.getAccountNumber());
		logger.info("purchaseBseMfAfterLogin(): Total emdandates fetched-  " + mandate.size());
		if (mandate.size() > 0 && mandate.get(0).isMandateComplete()) {
			logger.info("purchaseBseMfAfterLogin(): Emandate found for current customer.");
//						selectedFund.setMandateType("I");
			selectedFund.setMandateType("N");
			selectedFund.seteMandateRegRequired(false);
			selectedFund.setMandateId(mandate.get(0).getMandateId());
			List<String> manadates = new ArrayList<String>(); 
			for(int i=0;i<mandate.size();i++) {
				manadates.add(mandate.get(i).getMandateId());
			}
			map.addAttribute("allmandates", manadates);
		} else {
			logger.info("purchaseBseMfAfterLogin(): No emnadate found for customer..");
			selectedFund.seteMandateRegRequired(true);
		}
	}

	@RequestMapping(value = "/mutual-funds/mfPurchaseConfirm.do", method = RequestMethod.GET)
	public String purchaseConfirmGet(HttpServletRequest request, HttpServletResponse response) {
		return "return:/products/";

	}

	@RequestMapping(value = "/mutual-funds/mfPurchaseConfirm.do", method = RequestMethod.POST)
	public String purchaseConfirmPost(@Valid @ModelAttribute("selectedFund") SelectMFFund selectedFund,
			BindingResult bindResult, Model map, HttpServletRequest request, HttpServletResponse response,
			HttpSession session, final RedirectAttributes redirectAttrs) {

		logger.info("@@ BSE MF STAR purchaseConfirmPost controller @@");
		String returnUrl = "redirect:/mutual-funds/bse-transaction-status";
		boolean requestcomplete=false;
		map.addAttribute("GETDATA", "S");
		logger.info("purchaseConfirmPost(): Client ID - " + selectedFund.getClientID() + " : mobile- "+ selectedFund.getMobile());
		String errormsg="Request not complete!";
		TransactionStatus transationResult = new TransactionStatus();
		String mandateId = "";
		String emandatestatus="";
		boolean mandareGenerated = false;
		List<String> customerPortfolios = new ArrayList<String>();
		BseApiResponse emandateResponse = new BseApiResponse();
		if (bindResult.hasErrors()) {
			/*
			map.addAttribute("errormsg", bindResult.getFieldError().getDefaultMessage());
			map.addAttribute("paymentMethod", InvestFormConstants.bsePaymentMethod);
			map.addAttribute("selectedFund", selectedFund);
			customerPortfolios = bseEntryManager.getSelectedAmcPortfolio(selectedFund.getSchemeCode(),
					selectedFund.getPan(), selectedFund.getRtaAgent());
			map.addAttribute("amcPortFolio", customerPortfolios);
			*/
			errormsg=bindResult.getFieldError().getDefaultMessage();
			purchaserequestfailparam(selectedFund, map, errormsg,customerPortfolios);
			
			return "bsemf/bse-mf-purchase";
		}

		
		
		try {

			if( session.getAttribute("token")!=null && session.getAttribute("userid")!=null && (session.getAttribute("userid").toString().equalsIgnoreCase(selectedFund.getMobile()) )) {
				
				logger.info("Selected scheme type- " +selectedFund.getInvCategory() + " --> (G)"+   selectedFund.getSchemeCode() + " ->(R) "+ selectedFund.getReinvSchemeCode());
				transationResult.setMobile(session.getAttribute("userid").toString());
				//	Check customer status before carrying out transaction - 
				String profileStatus=bseEntryManager.investmentProfileStatus(selectedFund.getMobile());
				logger.info("purchaseConfirmPost(): Profile status of customer- "+ selectedFund.getMobile() + " : "+ profileStatus);

				if(profileStatus.equals("PROFILE_READY")) {

					map.addAttribute("REG_COMPLETE", "Y");
					selectedFund.setSchemeOptionType(selectedFund.getInvCategory());

					// set sip date if chosen
					// boolean f = Integer.valueOf(selectedFund.getSipStartMonth())<10;
					if (selectedFund.getInvestype().equalsIgnoreCase("SIP")) {

						try {
							String combineDate = (selectedFund.getSipDate().length() == 1 ? "0" + selectedFund.getSipDate(): selectedFund.getSipDate())
									+ "/"
									+ ((Integer.valueOf(selectedFund.getSipStartMonth()) < 10)
											? "0" + Integer.toString(Integer.valueOf(selectedFund.getSipStartMonth()))
											: Integer.toString(Integer.valueOf(selectedFund.getSipStartMonth())))
									+ "/" + selectedFund.getSipStartYear();
							selectedFund.setSipStartDate((new SimpleDateFormat("dd/MM/yyyy")).parse(combineDate));
						} catch (Exception e) {
							logger.error("Failed to convert date to required format for SIP.", e);
							errormsg="Failed to process the date!";
							/*
							 map.addAttribute("errormsg", "Failed to process the date!");
							map.addAttribute("paymentMethod", InvestFormConstants.bsePaymentMethod);
							map.addAttribute("selectedFund", selectedFund);
							
							*/
							errormsg=bindResult.getFieldError().getDefaultMessage();
							purchaserequestfailparam(selectedFund, map, errormsg,customerPortfolios);
							return "bsemf/bse-mf-purchase";
						}
						logger.info("Is emandate registration required?- " + selectedFund.iseMandateRegRequired());
						// Get MANDATE ID
						if (selectedFund.iseMandateRegRequired()) {
							logger.info("Customer emandate registration need to be processed first...");
							/* flag.setEmandateRequired(true); */
							logger.info("Mandate type- "+ selectedFund.getMandateType());
							if(selectedFund.getMandateType()==null || selectedFund.getMandateType().isEmpty()) {
								logger.warn("Madatetype null.. Set to E-Nach");
								selectedFund.setMandateType("N");
							}
							emandateResponse = bseEntryManager.updateEmdandateStatus(selectedFund.getMobile(),
									selectedFund.getMandateType(), Double.toString(selectedFund.getInvestAmount()));
							if (emandateResponse != null && emandateResponse.getStatusCode().equals("100")) {
								//						if (emandateResponse.getStatusCode().equals("100")) {
								mandareGenerated = true;
								mandateId = emandateResponse.getResponseCode();
								logger.info("E-mandate registration completed successfully for Cleint ID- " + selectedFund.getClientID() + " .Mandate ID generater-" + emandateResponse.getResponseCode());
//								redirectAttrs.addFlashAttribute("EMANDATE_STATUS", "S");
								emandatestatus="S";
							} else {
								logger.info("emandate response is null... Returning to confirm page with error..");
//								redirectAttrs.addFlashAttribute("EMANDATE_STATUS", "F");
								emandatestatus="F";
								
								errormsg="Mandate registration failed for- " + (emandateResponse!=null?emandateResponse.getRemarks():"N/A");
								purchaserequestfailparam(selectedFund, map, errormsg,customerPortfolios);
//								returnUrl ="bsemf/bse-mf-purchase";
								return "bsemf/bse-mf-purchase";
							}
//							redirectAttrs.addFlashAttribute("EMANDATE_REMARKS", emandateResponse.getRemarks());
							
//							transationResult.setEmandateRegisterRemark(emandateResponse.getRemarks());
//							transationResult.setMandateid(emandateResponse.getResponseCode());
							mandateId = emandateResponse.getResponseCode();
						} else {

							logger.info("Emandate not required. Skipping the request. Get existing mandate ID for client." + selectedFund.getClientID());
//							redirectAttrs.addFlashAttribute("EMANDATE_STATUS", "SELECTED");
							emandatestatus="SELECTED";
							mandateId = selectedFund.getMandateId();
							logger.info("Selected Mandate ID from form- "+ mandateId);
						}

					} else {
						logger.info("Transaction type is lumpsum. Skip emandate registration and generating SIP date");
					}

					try {
						ClientSystemDetails requestingsytemDetails = CommonTask.getClientSystemDetails(request);
						selectedFund.setClientIp(requestingsytemDetails.getClientIpv4Address());
						selectedFund.setClientBrowser(requestingsytemDetails.getClientBrowser());

						if (selectedFund.getInvestype().equalsIgnoreCase("SIP")) {
							logger.info("Transaction is SIP based...");
							logger.info("purchaseConfirmPost(): Pay first install? " + selectedFund.isPayFirstInstallment());
							if ((selectedFund.iseMandateRegRequired() && mandareGenerated)
									|| (!selectedFund.iseMandateRegRequired())) {
								logger.info("Processing SIP order ...");
								selectedFund.setSipfrequency("MONYHLY");
								transationResult = bseEntryManager.savetransactionDetails(selectedFund, mandateId);
								logger.info("Customer purchase transaction status for SIP- " + transationResult.getSuccessFlag());
								
								transationResult.setEmandateRegisterRemark(emandateResponse.getRemarks());
								transationResult.setMandateid(mandateId);
								transationResult.setEmandateStatusCode(emandatestatus);
								
							} else {
								logger.info("Skipping transation process as failed to generate EMANDATE...");
							}
						} else {
							logger.info("Transaction is LUMSUM BASED. Carry out transaction staright forward..");
							transationResult = bseEntryManager.savetransactionDetails(selectedFund, mandateId);
						}
						
						transationResult.setInvestamount(selectedFund.getInvestAmount());
						transationResult.setTransactiontype(selectedFund.getTransactionType());
						transationResult.setInvestmentType(selectedFund.getInvestype());
						transationResult.setFundName(selectedFund.getSchemeName());
						
						if (transationResult.getSuccessFlag() != null && transationResult.getSuccessFlag().equalsIgnoreCase("S")) {
							requestcomplete=true;
							try {
								// Trigger transaction mailer
								MFCustomers userDetails = bseEntryManager.getCustomerInvestFormData(
										session.getAttribute("userid") != null ? session.getAttribute("userid").toString()
												: selectedFund.getMobile());

								logger.info("Transaction processed successfully.. Processing to send mail for transaction id- "
										+ selectedFund.getTransactionID());
								mailSenderInterface.mfpurchasenotofication(selectedFund, userDetails, "purchase");
							} catch (Exception e) {
								logger.error("Failed to send mail to customer after purchase..", e);
							}

//							redirectAttrs.addAttribute("TRANS_STATUS", "Y");
//							redirectAttrs.addFlashAttribute("TRANS_ID", selectedFund.getTransactionID());
//							redirectAttrs.addFlashAttribute("TRANS_MSG", transationResult.getStatusMsg());
							
							transationResult.setTransactionstatus("Y");
							transationResult.setTransactionmsg(transationResult.getStatusMsg());
//							transationResult.setTransactionReference(selectedFund.getTransactionID());
							

							if (selectedFund.getInvestype().equalsIgnoreCase("LUMPSUM")) {
//								redirectAttrs.addFlashAttribute("FIRST_PAY", "Y");
								transationResult.setFirstpay("Y");
							} else if (selectedFund.getInvestype().equalsIgnoreCase("SIP")) {
								if (selectedFund.isPayFirstInstallment()) {
//									redirectAttrs.addFlashAttribute("FIRST_PAY", "Y");
									transationResult.setFirstpay("Y");
								} else {
//									redirectAttrs.addFlashAttribute("FIRST_PAY", "N");
									transationResult.setFirstpay("N");
								}
							}

						} else if (transationResult.getSuccessFlag() != null
								&& transationResult.getSuccessFlag().equalsIgnoreCase("F")) {
//							redirectAttrs.addAttribute("TRANS_STATUS", "N");
//							redirectAttrs.addFlashAttribute("TRANS_MSG", transationResult.getStatusMsg());
							transationResult.setTransactionstatus("N");
							transationResult.setTransactionmsg(transationResult.getStatusMsg());
							
						} else if (transationResult.getSuccessFlag() != null
								&& transationResult.getSuccessFlag().equalsIgnoreCase("D")) {
//							redirectAttrs.addAttribute("TRANS_STATUS", "N");
//							redirectAttrs.addFlashAttribute("TRANS_MSG","Fund transaction is currently disabled by Admin. Please try after sometime.");
							transationResult.setTransactionstatus("D");
							transationResult.setTransactionmsg("Fund transaction is currently disabled by Admin. Please try after sometime.");
							
						} else {
//							redirectAttrs.addAttribute("TRANS_STATUS", "SF");
//							redirectAttrs.addFlashAttribute("TRANS_MSG", transationResult.getStatusMsg());
							transationResult.setTransactionstatus("SF");
							transationResult.setTransactionmsg(transationResult.getStatusMsg());
						}

					} catch (Exception e) {

						logger.error("Unable to save customer transaction request", e);
						
						/*
						map.addAttribute("errormsg", "Internal error! Kindly contact admin to help resolve your issue.");
						map.addAttribute("paymentMethod", InvestFormConstants.bsePaymentMethod);
						map.addAttribute("selectedFund", selectedFund);
						*/
						errormsg="Internal error! Kindly contact admin to help resolve your issue.";
						purchaserequestfailparam(selectedFund, map, errormsg,customerPortfolios);
						return "bsemf/bse-mf-purchase";

					}
//					redirectAttrs.addFlashAttribute("TRANS_TYPE", selectedFund.getTransactionType());
//					redirectAttrs.addFlashAttribute("CLIENT_CODE", selectedFund.getClientID());
					transationResult.setClientcode(selectedFund.getClientID());
					transationResult.setInvestmentType(selectedFund.getInvestype());
					redirectAttrs.addFlashAttribute("TRANSACTION_REPORT_BEAN", transationResult);
				}else {

					logger.info("Customer profile is not yet ready for transaction. Need to complete Profile first");
					map.addAttribute("REG_COMPLETE", "N");
					/*
					map.addAttribute("errormsg", "Your Mutual Account registration is not complete. Visit your dashboard and complete your registration to be enable to transact");
					map.addAttribute("paymentMethod", InvestFormConstants.bsePaymentMethod);
					map.addAttribute("selectedFund", selectedFund);
					*/
					errormsg="Your Mutual Account registration is not complete. Visit your dashboard and complete your registration to be enable to transact";
					purchaserequestfailparam(selectedFund, map, errormsg,customerPortfolios);
					return "bsemf/bse-mf-purchase";
				}
			}else {
				logger.info("BSE purchase session data and form mobile no mismatches. Request denied. "+ selectedFund.getMobile() + " -->session user --> "+ session.getAttribute("userid")!=null?session.getAttribute("userid").toString():"NULL");
				map.addAttribute("errormsg", "Mobile no mismatch for logged in user");
				returnUrl ="bsemf/bse-mf-purchase";
			}

		}catch(Exception e) {
			logger.error("ERROR PROCESSING PURCHASE: ",e );
			map.addAttribute("errormsg", "Error processing request. Please try after again.");
			returnUrl ="bsemf/bse-mf-purchase";
		}
		
		
		if(requestcomplete) {
			logger.info("Purchase transaction loop complete.. Proceeding to next page with transaction status..");
		}else {
			logger.info("Purchase request failed. Returning back to page..");
			purchaserequestfailparam(selectedFund, map, errormsg,customerPortfolios);

		}
		
		return returnUrl;

	}


	private void purchaserequestfailparam(SelectMFFund selectedFund, Model map, String errormsg,List<String> customerPortfolios) {
		map.addAttribute("errormsg",errormsg );
		map.addAttribute("paymentMethod", InvestFormConstants.bsePaymentMethod);
		map.addAttribute("selectedFund", selectedFund);
		customerPortfolios = bseEntryManager.getSelectedAmcPortfolio(selectedFund.getSchemeCode(),
				selectedFund.getPan(), selectedFund.getRtaAgent());
		map.addAttribute("amcPortFolio", customerPortfolios);
	}
	
	
	@RequestMapping(value = "/mutual-funds/mfpurchasefund.do", method = RequestMethod.POST)
	public String purchasefundConfirmPost(@Valid @ModelAttribute("selectedFund") SelectMFFund selectedFund,
			BindingResult bindResult, Model map, HttpServletRequest request, HttpServletResponse response,
			HttpSession session, final RedirectAttributes redirectAttrs) {

		logger.info("@@ BSE MF STAR purchase confirm controller for new user @@");
		String returnUrl = "redirect:/mutual-funds/bse-transaction-status";
		map.addAttribute("GETDATA", "S");
		logger.info("purchasefundConfirmPost(): Client ID - " + selectedFund.getClientID() + " : mobile- "+ selectedFund.getMobile());

		BseApiResponse emandateResponse = new BseApiResponse();
		TransactionStatus transationResult = new TransactionStatus();
		
		String mandateId = "";
		boolean mandareGenerated = false;

		if (bindResult.hasErrors()) {
			map.addAttribute("errormsg", bindResult.getFieldError().getDefaultMessage());
			map.addAttribute("paymentMethod", InvestFormConstants.bsePaymentMethod);
			map.addAttribute("selectedFund", selectedFund);

			return "bsemf/bse-registration-status2";
		}
		
		
		try {
			MFCustomers customer = (MFCustomers) session.getAttribute("mfInvestForm");
			logger.info("Customer type- "+ session.getAttribute("PURCHASE_TYPE"));
			
			if(customer!=null & customer.getMobile().equals(selectedFund.getMobile())) {
//			if( session.getAttribute("PURCHASE_TYPE")!=null && session.getAttribute("PURCHASE_TYPE").toString().equalsIgnoreCase("NEW_USER") && customer!=null && customer.getMobile().equalsIgnoreCase(selectedFund.getMobile() )) {
				logger.info("Selected scheme type- " +selectedFund.getInvCategory() + " --> (G)"+   selectedFund.getSchemeCode() + " ->(R) "+ selectedFund.getReinvSchemeCode());
				transationResult.setMobile(selectedFund.getMobile());
				//	Check customer status before carrying out transaction - 
				String profileStatus=bseEntryManager.investmentProfileStatus(selectedFund.getMobile());
				logger.info("purchasefundConfirmPost(): Profile status of customer- "+ selectedFund.getMobile() + " : "+ profileStatus);

				if(profileStatus.equals("PROFILE_READY")) {

					map.addAttribute("REG_COMPLETE", "Y");
					selectedFund.setSchemeOptionType(selectedFund.getInvCategory());

					// set sip date if chosen
					if (selectedFund.getInvestype().equalsIgnoreCase("SIP")) {

						try {
							String combineDate = (selectedFund.getSipDate().length() == 1 ? "0" + selectedFund.getSipDate(): selectedFund.getSipDate())
									+ "/"
									+ ((Integer.valueOf(selectedFund.getSipStartMonth()) < 10)
											? "0" + Integer.toString(Integer.valueOf(selectedFund.getSipStartMonth()))
											: Integer.toString(Integer.valueOf(selectedFund.getSipStartMonth())))
									+ "/" + selectedFund.getSipStartYear();
							selectedFund.setSipStartDate((new SimpleDateFormat("dd/MM/yyyy")).parse(combineDate));
						} catch (Exception e) {
							logger.error("purchasefundConfirmPost(): Failed to convert date to required format for SIP.", e);
							map.addAttribute("errormsg", "Failed to process the date!");
							map.addAttribute("paymentMethod", InvestFormConstants.bsePaymentMethod);
							map.addAttribute("selectedFund", selectedFund);
							return "bsemf/bse-registration-status2";
						}
						logger.info("Is emandate registration required?- " + selectedFund.iseMandateRegRequired());
						// Get MANDATE ID
						if (selectedFund.iseMandateRegRequired()) {
							logger.info("purchasefundConfirmPost(): Customer emandate registration need to be processed first...");
							/* flag.setEmandateRequired(true); */
							emandateResponse = bseEntryManager.updateEmdandateStatus(selectedFund.getMobile(),
									selectedFund.getMandateType(), Double.toString(selectedFund.getInvestAmount()));
							if (emandateResponse != null && emandateResponse.getStatusCode().equals("100")) {
								mandareGenerated = true;
								mandateId = emandateResponse.getResponseCode();
								logger.info("purchasefundConfirmPost(): E-mandate registration completed successfully for Cleint ID- "
										+ selectedFund.getClientID() + " .Mandate ID generated-"
										+ emandateResponse.getResponseCode());
								redirectAttrs.addFlashAttribute("EMANDATE_STATUS", "S");
								selectedFund.setMandateId(mandateId);
								selectedFund.seteMandateRegRequired(false);
							} else {
								logger.info("purchasefundConfirmPost(): emandate response is null... Returning to confirm page with error..");
								redirectAttrs.addFlashAttribute("EMANDATE_STATUS", "F");
								map.addAttribute("errormsg","Mandate registration failed for- " + (emandateResponse!=null?emandateResponse.getRemarks():"No response" ));
								map.addAttribute("paymentMethod", InvestFormConstants.bsePaymentMethod);
								map.addAttribute("selectedFund", selectedFund);
								return "bsemf/bse-registration-status2";
							}
//							redirectAttrs.addFlashAttribute("EMANDATE_REMARKS", emandateResponse.getRemarks());
							/*
							transationResult.setEmandateStatusCode(emandateResponse.getStatusCode());
							transationResult.setEmandateRegisterRemark(emandateResponse.getRemarks());
							transationResult.setMandateid(emandateResponse.getResponseCode());
							*/
							
						} else {

							logger.info("purchasefundConfirmPost(): Emandate not required. Skipping the request. Get existing mandate ID for client." + selectedFund.getClientID());
							mandateId = selectedFund.getMandateId();
							logger.info("purchasefundConfirmPost(): Mandate ID - "+ mandateId);
						}

					} else {
						logger.info("purchasefundConfirmPost(): Transaction type is lumpsum. Skip emandate registration and generating SIP date");
					}

					try {
						ClientSystemDetails requestingsytemDetails = CommonTask.getClientSystemDetails(request);
						selectedFund.setClientIp(requestingsytemDetails.getClientIpv4Address());
						selectedFund.setClientBrowser(requestingsytemDetails.getClientBrowser());

						if (selectedFund.getInvestype().equalsIgnoreCase("SIP")) {
							logger.info("purchasefundConfirmPost(): Transaction is SIP based...");
							logger.info("purchasefundConfirmPost(): Pay first install? " + selectedFund.isPayFirstInstallment());
							if ((selectedFund.iseMandateRegRequired() && mandareGenerated)
									|| (!selectedFund.iseMandateRegRequired())) {
								logger.info("purchasefundConfirmPost(): Processing SIP order ...");
								selectedFund.setSipfrequency("MONYHLY");
								transationResult = bseEntryManager.savetransactionDetails(selectedFund, mandateId);
								transationResult.setEmandateStatusCode(emandateResponse.getStatusCode());
								transationResult.setEmandateRegisterRemark(emandateResponse.getRemarks());
								transationResult.setMandateid(emandateResponse.getResponseCode());
								
								
								logger.info("purchasefundConfirmPost(): Customer purchase transaction status for SIP- " + transationResult.getSuccessFlag());
							} else {
								logger.info("purchasefundConfirmPost(): Skipping transation process as failed to generate EMANDATE...");
							}
						} else {
							logger.info("purchasefundConfirmPost(): Transaction is LUMSUM BASED. Carry out transaction staright forward..");
							transationResult = bseEntryManager.savetransactionDetails(selectedFund, mandateId);
						}
						
						transationResult.setInvestamount(selectedFund.getInvestAmount());
						transationResult.setTransactiontype(selectedFund.getTransactionType());
						
						transationResult.setInvestmentType(selectedFund.getInvestype());
						transationResult.setFundName(selectedFund.getSchemeName());
						transationResult.setClientcode(selectedFund.getClientID());
						
						if (transationResult.getSuccessFlag() != null && transationResult.getSuccessFlag().equalsIgnoreCase("S")) {

							try {
								// Trigger transaction mailer
								MFCustomers userDetails = bseEntryManager.getCustomerInvestFormData(
										session.getAttribute("userid") != null ? session.getAttribute("userid").toString()
												: selectedFund.getMobile());

								logger.info("Transaction processed successfully.. Processing to send mail for transaction id- "
										+ selectedFund.getTransactionID());
								mailSenderInterface.mfpurchasenotofication(selectedFund, userDetails, "purchase");
							} catch (Exception e) {
								logger.error("Failed to send mail to customer after purchase..", e);
							}

//							redirectAttrs.addAttribute("TRANS_STATUS", "Y");
//							redirectAttrs.addFlashAttribute("TRANS_ID", selectedFund.getTransactionID());
//							redirectAttrs.addFlashAttribute("TRANS_MSG", transationResult.getStatusMsg());

							transationResult.setTransactionstatus("Y");
							transationResult.setTransactionmsg(transationResult.getStatusMsg());
//							transationResult.setTransactionReference(selectedFund.getTransactionID());
//							transationResult.setInvestmentType(selectedFund.getInvestype());
//							transationResult.setFundName(selectedFund.getSchemeName());
							
							

							if (selectedFund.getInvestype().equalsIgnoreCase("LUMPSUM")) {
//								redirectAttrs.addFlashAttribute("FIRST_PAY", "Y");
								transationResult.setFirstpay("Y");
							} else if (selectedFund.getInvestype().equalsIgnoreCase("SIP")) {
								if (selectedFund.isPayFirstInstallment()) {
//									redirectAttrs.addFlashAttribute("FIRST_PAY", "Y");
									transationResult.setFirstpay("Y");
								} else {
//									redirectAttrs.addFlashAttribute("FIRST_PAY", "N");
									transationResult.setFirstpay("N");
								}
							}

						} else if (transationResult.getSuccessFlag() != null
								&& transationResult.getSuccessFlag().equalsIgnoreCase("F")) {
//							redirectAttrs.addAttribute("TRANS_STATUS", "N");
//							redirectAttrs.addFlashAttribute("TRANS_MSG", transationResult.getStatusMsg());
							transationResult.setTransactionstatus("N");
							transationResult.setTransactionmsg(transationResult.getStatusMsg());
						} else if (transationResult.getSuccessFlag() != null
								&& transationResult.getSuccessFlag().equalsIgnoreCase("D")) {
//							redirectAttrs.addAttribute("TRANS_STATUS", "N");
//							redirectAttrs.addFlashAttribute("TRANS_MSG", "Fund transaction is currently disabled by Admin. Please try after sometime.");
							transationResult.setTransactionstatus("D");
							transationResult.setTransactionmsg("Fund transaction is currently disabled by Admin. Please try after sometime.");
						} else {
//							redirectAttrs.addAttribute("TRANS_STATUS", "SF");
//							redirectAttrs.addFlashAttribute("TRANS_MSG", transationResult.getStatusMsg());
							transationResult.setTransactionstatus("SF");
							transationResult.setTransactionmsg("Fund transaction is currently disabled by Admin. Please try after sometime.");
						}

					} catch (Exception e) {

						logger.error("Unable to save customer transaction request", e);

						map.addAttribute("errormsg", "Internal error! Kindly contact admin to help resolve your issue.");
						map.addAttribute("paymentMethod", InvestFormConstants.bsePaymentMethod);
						map.addAttribute("selectedFund", selectedFund);
//						purchaserequestfailparam(selectedFund, map, errormsg,customerPortfolios);
						return "bsemf/bse-registration-status2";

					}
//					redirectAttrs.addFlashAttribute("TRANS_TYPE", selectedFund.getTransactionType());
//					redirectAttrs.addFlashAttribute("CLIENT_CODE", selectedFund.getClientID());
//					redirectAttrs.addFlashAttribute("TRANSACTION_REPORT_BEAN", transationResult);
					
//					transationResult.setClientcode(selectedFund.getClientID());
//					transationResult.setInvestmentType(selectedFund.getInvestype());
					redirectAttrs.addFlashAttribute("TRANSACTION_REPORT_BEAN", transationResult);
				}else {

					logger.info("Customer profile is not yet ready for transaction. Need to complete Profile first");
					map.addAttribute("REG_COMPLETE", "N");
					map.addAttribute("errormsg", "Your Mutual Account registration is not complete. Visit your dashboard and complete your registration to be enable to transact");
					map.addAttribute("paymentMethod", InvestFormConstants.bsePaymentMethod);
					map.addAttribute("selectedFund", selectedFund);
					return "bsemf/bse-registration-status2";
				}
				
				
			}else {
				logger.info("BSE purchase new user session data and form mobile no mismatches. Request denied. "+ selectedFund.getMobile());
				map.addAttribute("errormsg", "Mobile no mismatch for logged in user");
				returnUrl ="bsemf/bse-registration-status2";
			}

		}catch(Exception e) {
			logger.error("ERROR PROCESSING PURCHASE: ",e );
			map.addAttribute("errormsg", "Error processing request. Please try after again.");
			returnUrl ="bsemf/bse-registration-status2";
		}
		
		return returnUrl;

	}
	

	@RequestMapping(value = "/my-dashboard/additional-purchase", method = RequestMethod.GET)
	public String bsemfAdditionalFundsPurchaseModeGet(@RequestParam("p") String purchasedata,
			@ModelAttribute("TRANS_ID") String transId, Model map, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {

		logger.info("bsemfAdditionalFundsPurchaseModeGet() - Additional purchase request");
		String returnUrl = "bsemf/bsemf-additional-purchase";

		List<String> decodedString = Arrays.asList(Base64Coder.decodeString(purchasedata).toString().split("\\|"));
		logger.debug("Additional purchase -" + decodedString);

		MFAdditionalPurchaseForm purchaseForm = new MFAdditionalPurchaseForm();
		MfAllInvestorValueByCategory customerFundDetails = null;
		//		MFCamsFolio folioDetails = null;

		if (session.getAttribute("userid") != null || session.getAttribute("token") != null) {
			if (purchasedata != null) {

				try {
					String portfolio = decodedString.get(0) != null ? decodedString.get(0) : "NA";
					String rtaCode = decodedString.get(1) != null ? decodedString.get(1) : "NA";
					String investType = decodedString.get(2) != null ? decodedString.get(2) : "SIP";
					String rtaAgent = decodedString.get(3) != null ? decodedString.get(3) : "BLANK";
					String productCode = decodedString.get(4) != null ? decodedString.get(4) : "BLANK";
					//					BseMFSelectedFunds selectedCodeFundDetails = null;
					// BseAllTransactionsView bseSeletedFundDetails=
					// bseEntryManager.getFundDetailsForAdditionalPurchase(portfolio,
					// schemeCode,investType, session.getAttribute("userid").toString());
					logger.info(
							"bsemfAdditionalFundsPurchaseModeGet(): Details of request for additional purchase:[portfolio:rtacode:rtaAgent:productCode:investType]"
									+ portfolio + ":" + rtaCode + ":" + rtaAgent + ":" + productCode + ":"
									+ investType);
					if (rtaAgent.equals("CAMS")) {
						customerFundDetails = bseEntryManager.getCamsFundsDetailsForRedeem(productCode,
								session.getAttribute("userid").toString(), portfolio);
						//						selectedCodeFundDetails = bseEntryManager.getFundsByCode(productCode,null);

						/*
						 * purchaseForm.setPortfolio(folioDetails.getFolioNumber());
						 * purchaseForm.setFundName(folioDetails.getFundName());
						 * purchaseForm.setTotalAvailableAmount(folioDetails.getInvAmount());
						 * purchaseForm.setUnitHolderName(folioDetails.getInvestorName());
						 */
					} else if (rtaAgent.equals("KARVY")) {
						customerFundDetails = bseEntryManager.getKarvyFundsDetailsForRedeem(productCode,session.getAttribute("userid").toString(), portfolio);
						/*
						 * selectedCodeFundDetails =
						 * bseEntryManager.getFundsByCode(rtaCode,customerFundDetails.getIsin());
						 * 
						 * purchaseForm.setPortfolio(customerFundDetails.getFolioNumber());
						 * purchaseForm.setFundName(customerFundDetails.getFundDescription());
						 * purchaseForm.setTotalAvailableAmount(customerFundDetails.getInvAmount());
						 * purchaseForm.setUnitHolderName(customerFundDetails.getInvestorName());
						 */
					} else {
						logger.info("Provided RTA AGENT not supported : " + rtaAgent);
					}

					if (customerFundDetails == null) {
						logger.info(
								"Failed to find related BSE scheme details with scheme code / RTA code- " + rtaCode);

					} else {
						purchaseForm.setGrowthSchemeCode(customerFundDetails.getBsemfschemeCode());
						purchaseForm.setPortfolio(customerFundDetails.getFolioNumber());
						purchaseForm.setFundName(customerFundDetails.getFundDescription());
						purchaseForm.setTotalAvailableAmount(customerFundDetails.getInvAmount());
						purchaseForm.setUnitHolderName(customerFundDetails.getInvestorName());
						//						purchaseForm.setGrowthSchemeCode(customerFundDetails.getGrowthSchemeCode());
						//						purchaseForm.setReinvSchemeCode(customerFundDetails.getReinvSchemeCode());
					}

					// purchaseForm.setSchemeCode(bseSeletedFundDetails.getSchemeCode());

					// purchaseForm.setInvestType(folioDetails.getTrasanctionType()!=null?folioDetails.getTrasanctionType():"LUMPSUM");
					purchaseForm.setInvestType("LUMPSUM");

					//					String transactionId = generateTransId();
					String transactionId = bseEntryManager.generateTransId();
					logger.info("Generated transaction ID of initiated transaction for additional purhcase-  "
							+ transactionId);
					purchaseForm.setPurchaseTransid(transactionId);

				} catch (Exception e) {
					logger.error("Failed to fetch selected funds's transaction details", e);
					map.addAttribute("error", "Failed to fetch the curret investment details");
				}
			} else {
				logger.warn("Purchase details missing. Redirecting to my dashboard");
				returnUrl = "redirect:/my-dashboard";
			}
		} else {
			logger.warn("User session not found to carry out additional purcahse. Redirecting to login.");
			returnUrl = "redirect:/login?p=" + purchasedata;
		}

		/*
		 * MFAdditionalPurchaseForm purchaseForm = new MFAdditionalPurchaseForm();
		 * purchaseForm.setPortfolio(portfolio); purchaseForm.setSchemeCode(schemeCode);
		 * purchaseForm.setInvestType(investType.toUpperCase());
		 */

		map.addAttribute("purchaseForm", purchaseForm);
		map.addAttribute("data", purchasedata);
		return returnUrl;
	}

	@RequestMapping(value = "/mutual-funds/mfInvestAdditionalPurchase.do", method = RequestMethod.GET)
	public String bseAdditionalPurchaseGet(HttpServletRequest request, HttpServletResponse response) {
		return "redirect:/";
	}

	@RequestMapping(value = "/mutual-funds/mfInvestAdditionalPurchase.do", method = RequestMethod.POST)
	public String bseAdditionalPurchasePost(
			@ModelAttribute("purchaseForm") @Valid MFAdditionalPurchaseForm purchaseForm, BindingResult bindResult,
			Model map, HttpServletRequest request, HttpServletResponse response, HttpSession session,
			final RedirectAttributes redirectAttrs) {

		logger.info("bseAdditionalPurchasePost(): @@ BSE MF STAR purchase confirm do controller @@");
		String returnUrl = "redirect:/mutual-funds/bse-transaction-status";
		logger.info("bseAdditionalPurchasePost(): Purchase initiated against Folio no - " + purchaseForm.getPortfolio());
		String clientId = "";
		logger.info("bseAdditionalPurchasePost(): Is cutout policy agreed for purchase?- " + purchaseForm.isAgreePolicy());

		if (bindResult.hasErrors()) {
			logger.error("bseAdditionalPurchasePost(): Error processing redeem request", bindResult.getFieldError().getDefaultMessage());
			map.addAttribute("error", bindResult.getFieldError().getDefaultMessage());
			return "bsemf/bsemf-additional-purchase";
		}
		if (!purchaseForm.isAgreePolicy()) {
			logger.warn("bseAdditionalPurchasePost(): Policy not agreed for transaction.");
			map.addAttribute("error", "Please agree to the policy for transaction.");
			return "bsemf/bsemf-additional-purchase";
		}

		// if(session.getAttribute("purchaseForm")!=null){
		if(session.getAttribute("userid") != null && session.getAttribute("userid")!=null ){
			SelectMFFund fundTransaction = new SelectMFFund();
			try {
				clientId = bseEntryManager.getClientIdfromMobile(session.getAttribute("userid").toString());
				logger.info("bseAdditionalPurchasePost(): Client id - " + clientId + " : transaction type for additional fund- "
						+ purchaseForm.getInvestType());
				fundTransaction.setClientID(clientId);
				fundTransaction.setPortfolio(purchaseForm.getPortfolio());
				fundTransaction.setSchemeCode(purchaseForm.getGrowthSchemeCode());
				fundTransaction.setReinvSchemeCode(purchaseForm.getReinvSchemeCode());
				fundTransaction.setSchemeName(purchaseForm.getFundName());

				fundTransaction.setInvCategory(purchaseForm.getFundCategory());
				fundTransaction.setSchemeName(purchaseForm.getFundName());
				fundTransaction.setTransactionID(purchaseForm.getPurchaseTransid());
				fundTransaction.setInvestype(purchaseForm.getInvestType());
				fundTransaction.setTransactionType("PURCHASE");

				ClientSystemDetails systemDetails=  CommonTask.getClientSystemDetails(request);

				fundTransaction.setClientIp(systemDetails.getClientIpv4Address());
				fundTransaction.setClientBrowser(systemDetails.getClientBrowser());

				if (purchaseForm.getInvestType().equalsIgnoreCase("LUMPSUM")) {
					fundTransaction.setBuySellType("ADDITIONAL");
				}

				fundTransaction.setInvestAmount(purchaseForm.getPurchaseAmounts());
				fundTransaction.setPaymentMethod(purchaseForm.getPaymentMode());

				TransactionStatus flag = bseEntryManager.savetransactionDetails(fundTransaction, "");
				logger.info("bseAdditionalPurchasePost(): Customer additional purchase transaction status- " + flag.getSuccessFlag()); // todo
				/*
				 * redirectAttrs.addAttribute("TRANS_STATUS", "Y");
				 * redirectAttrs.addAttribute("TRANS_TYPE", "ADDITIONAL");
				 * redirectAttrs.addFlashAttribute("TRANS_ID",
				 * purchaseForm.getPurchaseTransid());
				 */

				if (flag.getSuccessFlag().equalsIgnoreCase("S")) {

					logger.info("bseAdditionalPurchasePost(): Additional purchase order ID- " + flag.getBseOrderNoFromResponse());
//					redirectAttrs.addFlashAttribute("CLIENT_CODE", fundTransaction.getClientID());
//					redirectAttrs.addFlashAttribute("TRANS_STATUS", "Y");
//					redirectAttrs.addFlashAttribute("TRANS_TYPE", "PURCHASE");
					
					flag.setTransactionstatus("Y");
					flag.setTransactiontype(fundTransaction.getTransactionType());
					
//					redirectAttrs.addFlashAttribute("FIRST_PAY", "Y");
					flag.setFirstpay("Y");
					flag.setFundName(purchaseForm.getFundName());

					try {
						// Trigger transaction mailer

						MFCustomers userDetails = bseEntryManager.getCustomerInvestFormData(session.getAttribute("userid") != null ? session.getAttribute("userid").toString() : fundTransaction.getMobile());

						logger.info("bseAdditionalPurchasePost(): Transaction processed successfully.. Processing to send mail for transaction id- "
								+ fundTransaction.getTransactionID());
						mailSenderInterface.mfpurchasenotofication(fundTransaction, userDetails, "purchase");
					} catch (Exception e) {
						logger.error("bseAdditionalPurchasePost(): Failed to send mail to customer after purchase..", e);
					}
					
					//flag.setClientcode(fundTransaction.getClientID());
					flag.setInvestmentType(fundTransaction.getInvestype());
					

				} else if (flag.getSuccessFlag().equalsIgnoreCase("SF")) {
					returnUrl = "bsemf/bsemf-additional-purchase";
					map.addAttribute("error", "Transaction successful, but failed to interanlly capture your request.");
					map.addAttribute("FUNDAVAILABLE", "Y");
				} else {
					returnUrl = "bsemf/bsemf-additional-purchase";
					map.addAttribute("error", flag.getStatusMsg());
					map.addAttribute("FUNDAVAILABLE", "Y");
				}

				redirectAttrs.addFlashAttribute("TRANS_ID", purchaseForm.getPurchaseTransid());
				redirectAttrs.addFlashAttribute("CLIENT_CODE", clientId);
				
				flag.setInvestamount(fundTransaction.getInvestAmount());
				flag.setTransactionReference(purchaseForm.getPurchaseTransid()); // Validate mappimg
				flag.setClientcode(clientId);
				redirectAttrs.addFlashAttribute("TRANSACTION_REPORT_BEAN", flag);
			} catch (Exception e) {

				logger.error("bseAdditionalPurchasePost(): Unable to save customer transaction request for additional purchase", e);
				// redirectAttrs.addAttribute("TRANS_STATUS", "N");
				map.addAttribute("error", "Failed to save your request for additional purchase. Please try again.");
				returnUrl = "bsemf/bsemf-additional-purchase";
			}
		}else {
			logger.info("bseAdditionalPurchasePost(): Session not found during purchase post. Redircting back to login page...");
			returnUrl="redirect:/login";
		}
		/*
		 * }else{ logger.warn("Fund session not found for execution. Redirecting out");
		 * returnUrl ="redirect:/products/"; }
		 */


		return returnUrl;

	}

	@RequestMapping(value = "/my-dashboard/funds-redeem", method = RequestMethod.GET)
	public String bseRedeemMfFundsGet(@RequestParam("r") String purchasedata,
			@ModelAttribute("TRANS_STATUS") String transStatus, @ModelAttribute("TRANS_ID") String transId, Model map,
			HttpServletRequest request, HttpServletResponse response, HttpSession session) {

		logger.info("@@ BSE MF STAR MF redeem controller @@");
		String returnUrl = "bsemf/bsemf-redeem";

		List<String> decodedString = Arrays.asList(Base64Coder.decodeString(purchasedata).toString().split("\\|"));
		logger.info("bseRedeemMfFundsGet(): Decoded string from pathparam- " + decodedString);

		MFRedeemForm redeemForm = new MFRedeemForm();
		MfAllInvestorValueByCategory customerFundDetails = null;
		//		MFCamsFolio folioDetails = null;

		try {
			String portfolio = decodedString.get(0) != null ? decodedString.get(0) : "NA";
			String bsemfSchemeCode = decodedString.get(1) != null ? decodedString.get(1) : "NA";
			String investType = decodedString.get(2) != null ? decodedString.get(2) : "NA";

			String rtaAgent = decodedString.get(3) != null ? decodedString.get(3) : "BLANK";
			String channelPartnerCode = decodedString.get(4) != null ? decodedString.get(4) : "BLANK";
			//			BseMFSelectedFunds selectedCodeFundDetails = null;

			logger.info(
					"bsemfAdditionalFundsPurchaseModeGet(): Details of request for additional purchase:[portfolio:rtacode:rtaAgent:productCode:investType]"
							+ portfolio + ":" + bsemfSchemeCode + ":" + rtaAgent + ":" + channelPartnerCode + ":"
							+ investType);
			// BseAllTransactionsView bseSeletedFundDetails=
			// bseEntryManager.getFundDetailsForRedemption(portfolio, schemeCode,investType,
			// session.getAttribute("userid").toString());
			// MFCamsFolio folioDetails =
			// bseEntryManager.getCamsFundsDetailsForRedeem(schemeCode,
			// session.getAttribute("userid").toString(), portfolio);

			//			logger.info("Details requested for redeem- "+ portfolio + " : "+ bsemfSchemeCode + " : "+ rtaAgent + " : "+ channelPartnerCode);

			if (rtaAgent.equals("CAMS")) {
				logger.info("Redeeming CAMS category fund");
				customerFundDetails = bseEntryManager.getCamsFundsDetailsForRedeem(channelPartnerCode,
						session.getAttribute("userid").toString(), portfolio);
				//				selectedCodeFundDetails = bseEntryManager.getFundsByCode(rtaCode,null);

				/*
				 * if(folioDetails == null){
				 * logger.info("No CAMS funds found to process for redeem for portfolio: "+
				 * portfolio); map.addAttribute("FUNDAVAILABLE", "N"); map.addAttribute("error",
				 * "No fund value to redeem. Please select appropriate fund for redemption.");
				 * }else{ map.addAttribute("FUNDAVAILABLE", "Y");
				 * redeemForm.setPortfolio(folioDetails.getFolioNumber());
				 * redeemForm.setFundName(folioDetails.getFundName());
				 * redeemForm.setRedeemAmounts(folioDetails.getInvAmount());
				 * redeemForm.setUnitHolderName(folioDetails.getInvestorName());
				 * 
				 * redeemForm.setInvestType(folioDetails.getTrasanctionType()!=null?folioDetails
				 * .getTrasanctionType():"NA");
				 * redeemForm.setTotalValue(folioDetails.getInvAmount());
				 * 
				 * redeemForm.setSchemeCode(folioDetails.getSchemeCode()); }
				 */
			} else if (rtaAgent.equals("KARVY")) {
				//				karvyFund = bseEntryManager.getKarvyFundsDetailsForRedeem(rtaCode, session.getAttribute("userid").toString(), portfolio);
				logger.info("Redeeming KARVY category fund");
				customerFundDetails = bseEntryManager.getKarvyFundsDetailsForRedeem(channelPartnerCode,
						session.getAttribute("userid").toString(), portfolio);
			} else {
				logger.info("bseRedeemMfFundsGet(): Invalid RTA agent. Cannot process redemption.- " + rtaAgent);
			}

			if (customerFundDetails == null) {
				logger.info(
						"No related funds found to process for portfolio for the customer with folio: " + portfolio);
				map.addAttribute("FUNDAVAILABLE", "N");
				map.addAttribute("error", "No fund value to redeem. Please select appropriate fund for redemption.");
			} else {
				//				selectedCodeFundDetails = bseEntryManager.getFundsByCode(productCode,karvyFund.getIsin());
				map.addAttribute("FUNDAVAILABLE", "Y");
				redeemForm.setPortfolio(customerFundDetails.getFolioNumber());
				redeemForm.setFundName(customerFundDetails.getFundDescription());
				//				redeemForm.setRedeemAmounts(customerFundDetails.getInvAmount());
				redeemForm.setUnitHolderName(customerFundDetails.getInvestorName());

				redeemForm.setInvestType(
						customerFundDetails.getTrasanctionType() != null ? customerFundDetails.getTrasanctionType()
								: "NA");
				redeemForm.setTotalValue(customerFundDetails.getInvAmount());

				try {
					//					redeemForm.setRedeemAmounts(0.0);
					redeemForm.setMarketValue(customerFundDetails.getMarketValue().equals("NA")?0.0:Double.valueOf(customerFundDetails.getMarketValue()));
					redeemForm.setCurrentnav(customerFundDetails.getNav());
					redeemForm.setNavDate(customerFundDetails.getNavdate());

				} catch (Exception e) {
					logger.error("bseRedeemMfFundsGet(): error setting redeem value ", e);
				}
				redeemForm.setTotalUnits(customerFundDetails.getUnits());
				redeemForm.setSchemeCode(customerFundDetails.getBsemfschemeCode());
				

				String clientid = bseEntryManager.getClientIdfromMobile(session.getAttribute("userid").toString());

				List<UserBankDetails> userbankDetails = bseEntryManager.getAllCustomerBankDetails(clientid);
				Map<Long,String> selectbank = new HashMap<Long,String>();
				if(userbankDetails!=null) {
					for(int i=0;i<userbankDetails.size();i++)
						selectbank.put(userbankDetails.get(i).getSerialno(), "XXXXXXXXX" + (userbankDetails.get(i).getAccountNumber().substring(userbankDetails.get(i).getAccountNumber().length() - 3)));
					map.addAttribute("accounts", selectbank);
				}
				
				//				String transactionId = generateTransId();
				String transactionId = bseEntryManager.generateTransId();
				logger.info("Generated transaction ID of initiated transaction for redeem  " + transactionId);
				redeemForm.setRedeemTransId(transactionId);

			}

			/*
			 * if(folioDetails == null){ map.addAttribute("FUNDAVAILABLE", "N");
			 * map.addAttribute("error",
			 * "No fund value to redeem. Please select appropriate fund for redemption.");
			 * }else{ map.addAttribute("FUNDAVAILABLE", "Y");
			 * 
			 * redeemForm.setPortfolio(folioDetails.getFolioNumber());
			 * redeemForm.setFundName(folioDetails.getFundName());
			 * redeemForm.setSchemeCode(folioDetails.getSchemeCode()); //
			 * redeemForm.setSchemeCode(schemeCode);
			 * redeemForm.setInvestType(folioDetails.getTrasanctionType()!=null?folioDetails
			 * .getTrasanctionType():"NA");
			 * redeemForm.setTotalValue(folioDetails.getInvAmount());
			 * redeemForm.setRedeemAmounts(folioDetails.getInvAmount());
			 * redeemForm.setUnitHolderName(folioDetails.getInvestorName());
			 * 
			 * }
			 */

		} catch (Exception e) {
			logger.error("Failed to fetch selected funds's transaction details", e);
			map.addAttribute("error", "Failed to fetch the curret investment details");
			map.addAttribute("FUNDAVAILABLE", "N");
		}

		map.addAttribute("mfRedeemForm", redeemForm);
		// return returnUrl;

		return returnUrl;
	}

	@RequestMapping(value = "/mutual-funds/mfInvestRedeem.do", method = RequestMethod.GET)
	public String bsemfFundsRedeemDoGet(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

		return "redirect:/my-dashboard";
	}

	@RequestMapping(value = "/mutual-funds/mfInvestRedeem.do", method = RequestMethod.POST)
	public String bseRedeemMfFundsPost(@ModelAttribute("mfRedeemForm") MFRedeemForm redeemForm, Model map,
			HttpServletRequest request, HttpServletResponse response, final RedirectAttributes redirectAttrs,
			HttpSession session) {

		logger.info("bseRedeemMfFundsPost(): REDEEM REQUEST POST CONTROLLER");



		if (!redeemForm.isAgreePolicy()) {
			logger.warn("Policy not agreed for redeem transaction.");
			map.addAttribute("error", "Please agree to the policy for transaction.");
			map.addAttribute("FUNDAVAILABLE", "Y");
			//			map.addAttribute("mfRedeemForm", redeemForm);
			return "bsemf/bsemf-redeem";
		}

		logger.info("Redeem amount selected- "+  redeemForm.getRedeemByAmounts() + " : Redeem All Flag- "+ redeemForm.isRedeemAll());

		if (redeemForm.getRedeemAmounts() < 500 && !redeemForm.isRedeemAll()) {
			logger.warn("Minimum redemption amount should be Rs. 500");
			map.addAttribute("error", "Minimum redemption amount should be Rs. 500");
			map.addAttribute("FUNDAVAILABLE", "Y");
			//			map.addAttribute("mfRedeemForm", redeemForm);
			return "bsemf/bsemf-redeem";
		}

		String returnUrl = "redirect:/mutual-funds/bse-transaction-status";
		TransactionStatus transReport = new TransactionStatus();

		if (session.getAttribute("token") == null) {
			try {
				returnUrl = "redirect:/login?ref="
						+ URLEncoder.encode(request.getRequestURL().toString(), StandardCharsets.UTF_8.toString());
			} catch (UnsupportedEncodingException e) {
				logger.error("Failed to form URL", e);
				returnUrl = "redirect:/login";
			}
		} else {
			// Process redeem
			SelectMFFund fundTransaction = new SelectMFFund();
			try {
				String clientId = bseEntryManager.getClientIdfromMobile(session.getAttribute("userid").toString());
				logger.info("Client id for redeem- " + clientId);
				fundTransaction.setClientID(clientId);
				fundTransaction.setPortfolio(redeemForm.getPortfolio());
				fundTransaction.setSchemeCode(redeemForm.getSchemeCode());
				fundTransaction.setSchemeName(redeemForm.getFundName());
				fundTransaction.setTransactionID(redeemForm.getRedeemTransId());
				fundTransaction.setInvestype(redeemForm.getInvestType());
				fundTransaction.setTransactionType("REDEEM");
				fundTransaction.setSchemeName(redeemForm.getFundName());
				fundTransaction.setInvestAmount(redeemForm.getRedeemAmounts() * (-1));
				fundTransaction.setRedeemAmount(redeemForm.getRedeemAmounts());

				ClientSystemDetails systemDetails=  CommonTask.getClientSystemDetails(request);

				fundTransaction.setClientIp(systemDetails.getClientIpv4Address());
				fundTransaction.setClientBrowser(systemDetails.getClientBrowser());
				
				UserBankDetails bankdetails = bseEntryManager.getCustomerBankDetails(clientId,redeemForm.getBankaccountforredeem());
				if(bankdetails!=null) {
					logger.info("Bank details retrieved. Setting for redemption");
					fundTransaction.setBanbkaccount(bankdetails.getAccountNumber());
				}
					
				logger.info("Redemption amount selected- " + (redeemForm.isRedeemAll() ? "REDEEM ALL" : redeemForm.getRedeemAmounts() * (-1)));

				fundTransaction.setRedeemAll(redeemForm.isRedeemAll() ? "Y" : "N");

				//				Begin Redeem process
				transReport = bseEntryManager.savetransactionDetails(fundTransaction, "");

				logger.info("Customer redeem transaction status- " + transReport.getSuccessFlag());

				if (transReport.getSuccessFlag().equalsIgnoreCase("S")) {

					logger.info("Redeem order ID- " + transReport.getBseOrderNoFromResponse());
					redirectAttrs.addFlashAttribute("CLIENT_CODE", fundTransaction.getClientID());
					redirectAttrs.addFlashAttribute("TRANS_STATUS", "Y");
					redirectAttrs.addFlashAttribute("TRANS_TYPE", "REDEEM");

					redirectAttrs.addFlashAttribute("FIRST_PAY", "N");
					transReport.setFundName(redeemForm.getFundName());

					try {
						// Trigger transaction mailer

						MFCustomers userDetails = bseEntryManager.getCustomerInvestFormData(
								session.getAttribute("userid") != null ? session.getAttribute("userid").toString()
										: fundTransaction.getMobile());

						logger.info("Transaction processed successfully.. Processing to send mail for transaction id- "
								+ fundTransaction.getTransactionID());
						mailSenderInterface.mfpurchasenotofication(fundTransaction, userDetails, "redemption");
					} catch (Exception e) {
						logger.error("Failed to send mail to customer after purchase..", e);
					}

					redirectAttrs.addFlashAttribute("TRANSACTION_REPORT_BEAN", transReport);

				} else {
					returnUrl = "bsemf/bsemf-redeem";
					map.addAttribute("error", transReport.getStatusMsg());
					map.addAttribute("FUNDAVAILABLE", "Y");
					
//					Refetch bank details. THis code need to be optimized along with GET method
					String clientid = bseEntryManager.getClientIdfromMobile(session.getAttribute("userid").toString());

					List<UserBankDetails> userbankDetails = bseEntryManager.getAllCustomerBankDetails(clientid);
					Map<Long,String> selectbank = new HashMap<Long,String>();
					if(userbankDetails!=null) {
						for(int i=0;i<userbankDetails.size();i++)
							selectbank.put(userbankDetails.get(i).getSerialno(), "XXXXXXXXX" + (userbankDetails.get(i).getAccountNumber().substring(userbankDetails.get(i).getAccountNumber().length() - 3)));
						map.addAttribute("accounts", selectbank);
					}
					
				}

				redirectAttrs.addFlashAttribute("TRANS_ID", redeemForm.getRedeemTransId());

			} catch (Exception e) {

				logger.error("Unable to save customer transaction request for redeem", e);
				map.addAttribute("FUNDAVAILABLE", "Y");
				map.addAttribute("error", "Failed to process your redeem. Please retry after sometime.");
				map.addAttribute("mfRedeemForm", redeemForm);
				returnUrl = "bsemf/bsemf-redeem";
			}

		}
		logger.info("Returning to url after redeem process complete- " + returnUrl);
		return returnUrl;

	}

	@RequestMapping(value = "/my-dashboard/cancel-order", method = RequestMethod.GET)
	public String bsemfCancelOrderGet(@RequestParam("ref") String requestData, Model map, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {

		logger.info("@@ BSE MF STAR MF redeem controller @@");
		String returnUrl = "bsemf/bsemf-cancel-order";

		List<String> decodedString = Arrays.asList(Base64Coder.decodeString(requestData).toString().split("\\|"));
		logger.info(decodedString);

		MFRedeemForm orderCancelForm = new MFRedeemForm();

		try {
			String schemeCode = decodedString.get(0) != null ? decodedString.get(0) : "NA";
			String orderNo = decodedString.get(1) != null ? decodedString.get(1) : "NA";
			String investType = decodedString.get(2) != null ? decodedString.get(2) : "NA";
			String category = decodedString.get(3) != null ? decodedString.get(3) : "NA";
			String transactionNo = decodedString.get(4) != null ? decodedString.get(4) : "NA";

			BsemfTransactionHistory bseSeletedFundDetails = bseEntryManager.getOrderDetailsForCancel(orderNo,
					schemeCode, investType, session.getAttribute("userid").toString(), category, transactionNo);

			if (bseSeletedFundDetails == null) {
				map.addAttribute("FUNDAVAILABLE", "N");
				map.addAttribute("error", "No fund value to cancel. Please select appropriate fund for cancellation.");
			} else {
				map.addAttribute("FUNDAVAILABLE", "Y");
				orderCancelForm.setPortfolio(bseSeletedFundDetails.getOrderNo());
				// orderCancelForm.setFundName(bseSeletedFundDetails.);
				orderCancelForm.setSchemeCode(bseSeletedFundDetails.getSchemeCode());
				orderCancelForm.setFundName(bseSeletedFundDetails.getSchemeName());
				orderCancelForm.setInvestType(
						bseSeletedFundDetails.getInvestType() != null ? bseSeletedFundDetails.getInvestType() : "NA");
				orderCancelForm.setTotalValue(Double.valueOf(bseSeletedFundDetails.getInvestAmount()));
				// orderCancelForm.setRedeemAmounts(bseSeletedFundDetails.getSchemeInvestment());
				orderCancelForm.setCancelOrderTransId(bseSeletedFundDetails.getTransactionId());
				//				String transactionId = generateTransId();
				String transactionId = bseEntryManager.generateTransId();
				logger.info("Generated transaction ID of initiated transaction for cancel order-  " + transactionId);
				orderCancelForm.setRedeemTransId(transactionId);
			}

		} catch (Exception e) {
			logger.error("Failed to fetch selected funds's transaction details", e);
			map.addAttribute("error", "Failed to fetch the curret investment details");
		}

		map.addAttribute("mfCencelForm", orderCancelForm);
		return returnUrl;
	}

	@RequestMapping(value = "/mutual-funds/cancelOrder.do", method = RequestMethod.GET)
	public String bseCancelOrderGet(HttpServletRequest request, HttpServletResponse response) {
		return "redirect:/";
	}

	@RequestMapping(value = "/mutual-funds/cancelOrder.do", method = RequestMethod.POST)
	public String bsecancelOrderPost(@Valid @ModelAttribute("mfCencelForm") MFRedeemForm cancelOrderForm,
			BindingResult bindResult, Model map, HttpServletRequest request, HttpServletResponse response,
			HttpSession session, final RedirectAttributes redirectAttrs) {

		logger.info("bsecancelOrderPost(): @@ BSE MF STAR cancel order do controller @@");
		String returnUrl = "redirect:/mutual-funds/bse-transaction-status";
		logger.info("bsecancelOrderPost(): Redeem initiated against Folio no - " + cancelOrderForm.getPortfolio());

		logger.info("bsecancelOrderPost(): Is policy agreed?- " + cancelOrderForm.isAgreePolicy());

		if (bindResult.hasErrors()) {
			logger.error("bsecancelOrderPost(): Error processing cancel request", bindResult.getFieldError());
			map.addAttribute("FUNDAVAILABLE", "Y");
			map.addAttribute("error", bindResult.getFieldError().getDefaultMessage());
			return "bsemf/bsemf-cancel-order";
		}
		if (!cancelOrderForm.isAgreePolicy()) {
			logger.warn("bsecancelOrderPost(): Policy not agreed for transaction.");
			map.addAttribute("error", "Please agree to the policy for transaction.");
			map.addAttribute("FUNDAVAILABLE", "Y");
			return "bsemf/bsemf-cancel-order";
		}

		SelectMFFund fundTransaction = new SelectMFFund();
		//		SelectMFFund fundTransaction = null;
		try {
			String clientId = bseEntryManager.getClientIdfromMobile(session.getAttribute("userid").toString());
			logger.info("bsecancelOrderPost(): Client id - " + clientId);

			logger.info("bsecancelOrderPost(): Get transaction details of selected transaciton ID: " + cancelOrderForm.getCancelOrderTransId());

			/*
			 * if (cancelOrderForm.getInvestType().equalsIgnoreCase("SIP")) { logger.
			 * info("bsecancelOrderPost(): Get all transaction details of the fund. for cancel for SIP."
			 * ); fundTransaction =
			 * bseEntryManager.getTransactionDetails(cancelOrderForm.getCancelOrderTransId()
			 * ,clientId); }
			 */

			fundTransaction = bseEntryManager.getTransactionDetails(cancelOrderForm.getCancelOrderTransId(),clientId);

			fundTransaction.setClientID(clientId);
			fundTransaction.setSchemeCode(cancelOrderForm.getSchemeCode());
			fundTransaction.setSchemeName(cancelOrderForm.getFundName());
			fundTransaction.setTransactionID(cancelOrderForm.getRedeemTransId());
			fundTransaction.setInvestype(cancelOrderForm.getInvestType());
			fundTransaction.setTransactionType("CXL");
			fundTransaction.setInvestAmount(cancelOrderForm.getTotalValue());
			fundTransaction.setOrderNo(cancelOrderForm.getPortfolio());
			ClientSystemDetails systemDetails=  CommonTask.getClientSystemDetails(request);

			fundTransaction.setClientIp(systemDetails.getClientIpv4Address());
			fundTransaction.setClientBrowser(systemDetails.getClientBrowser());

			TransactionStatus flag = bseEntryManager.savetransactionDetails(fundTransaction, "");

			/*
			 * if(flag.getSuccessFlag().equalsIgnoreCase("S")){
			 * redirectAttrs.addFlashAttribute("CLIENT_CODE",
			 * fundTransaction.getClientID()); }
			 */
			// logger.info("Customer redeem transaction status- "+ flag.getSuccessFlag());
			// redirectAttrs.addAttribute("TRANS_STATUS", "Y");
			redirectAttrs.addFlashAttribute("TRANS_ID", cancelOrderForm.getRedeemTransId());

			if (flag.getSuccessFlag().equalsIgnoreCase("S")) {
				logger.info("bsecancelOrderPost(): Cancel order ID- " + flag.getBseOrderNoFromResponse());
				logger.info(
						"bsecancelOrderPost(): Order cancelled successfully. Process to update the status to NO for existing transaction number: "
								+ cancelOrderForm.getCancelOrderTransId());
				boolean updateStatus = bseEntryManager.updateCancelledTransactionStatus(null, clientId,
						cancelOrderForm.getPortfolio(), cancelOrderForm.getCancelOrderTransId());
				logger.info("bsecancelOrderPost(): updateCancelledTransactionStatus- " + updateStatus);

				redirectAttrs.addFlashAttribute("CLIENT_CODE", fundTransaction.getClientID());
				redirectAttrs.addFlashAttribute("TRANS_STATUS", "Y");
				redirectAttrs.addFlashAttribute("TRANS_TYPE", "CXL");

				redirectAttrs.addFlashAttribute("FIRST_PAY", "N");
				flag.setFundName(cancelOrderForm.getFundName());

				try {
					// Trigger transaction mailer

					MFCustomers userDetails = bseEntryManager.getCustomerInvestFormData(
							session.getAttribute("userid") != null ? session.getAttribute("userid").toString()
									: fundTransaction.getMobile());

					logger.info("bsecancelOrderPost(): Transaction processed successfully.. Processing to send mail for transaction id- "+ fundTransaction.getTransactionID());
					mailSenderInterface.mfpurchasenotofication(fundTransaction, userDetails, "cancel");
				} catch (Exception e) {
					logger.error("bsecancelOrderPost(): Failed to send mail to customer after purchase..", e);
				}

				redirectAttrs.addFlashAttribute("TRANSACTION_REPORT_BEAN", flag);

			} else {
				logger.info("Order cancel request failed. Returning to page.");
				returnUrl = "bsemf/bsemf-cancel-order";
				map.addAttribute("error", flag.getStatusMsg());
				map.addAttribute("FUNDAVAILABLE", "Y");
			}

		} catch (Exception e) {

			logger.error("Unable to save customer transaction request for cancellation", e);
			map.addAttribute("FUNDAVAILABLE", "Y");
			map.addAttribute("error", "Failed to process your cancel request. Please try again.");
			returnUrl = "bsemf/bsemf-cancel-order";
		}
		return returnUrl;

	}

	@RequestMapping(value = "/mutual-funds/bse-transaction-complete", method = RequestMethod.GET)
	public String bseMFTransactionCallback(@RequestParam("orderid") String orderid,
			@RequestParam("client") String clientid, Model map, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {

		logger.info("bseMFTransactionCallback(): @@ BSE MF STAR purchase confirm controller after callback @@");
		String returnUrl = "bsemf/bse-purchase-status";
		// logger.info("Data- "+ clientId + " " + orderNo);
		/*
		 * if(session.getAttribute("token")==null){
		 * logger.info("No session found for requesting user. Invalidating request");
		 * returnUrl="redirect:/login"; }else{
		 */
//		String clientCode = CommonTask.decryptText(clientid);
		Base64 base64 = new Base64();
		String decodedClientCode = CommonTask.decryptText(new String(base64.decode(clientid)));
		
		if (orderid.equalsIgnoreCase("")) {
			logger.info("bseMFTransactionCallback(): Parameter orderid data not found");
			returnUrl = "redirect:/";
		} else {
			// String getClientId =
			// bseEntryManager.getClientIdfromMobile(session.getAttribute("userid")!=null?session.getAttribute("userid").toString():"NA");
			logger.info("bseMFTransactionCallback(): Transaction complete callback received for order id: " + orderid
					+ " : client: " + decodedClientCode);
//			Base64 base64 = new Base64();
//			String decodedClientCode = new String(base64.decode(clientCode.getBytes()));

			String resp = investmentConnectorBseInterface.BseOrderPaymentStatus(decodedClientCode, orderid);

			List<String> res = Arrays.asList(resp.split("\\|"));
			/*
			 * if(res.get(0).equals("100")){
			 * 
			 * }else{
			 * 
			 * }
			 */

			// Clear session
			session.removeAttribute("selectedFund");
			// session.removeAttribute("purchaseForm");

			// session.invalidate();
			map.addAttribute("orderno", orderid);
			map.addAttribute("TRANS_STATUS", "COMPLETE");
			map.addAttribute("ORDER_STATUS", res.get(1));
		}
		/* } */

		// map.addAttribute("TRANS_ID",transId);
		logger.info(
				"bseMFTransactionCallback(): Checking order payment status after redirected from BSE callback url for orderid- "
						+ orderid);
		return returnUrl;
	}

	@RequestMapping(value = "/mutual-funds/bse-transaction-status", method = RequestMethod.GET)
	public String bseMFTransactionStatus(@ModelAttribute("TRANSACTION_REPORT_BEAN") TransactionStatus transReport,
			/*
			@ModelAttribute("TRANS_TYPE") String transyType, @ModelAttribute(name = "FIRST_PAY") String firstPayRequire,
			@ModelAttribute("TRANS_STATUS") String transStatus, @ModelAttribute("CLIENT_CODE") String clienCode,
			@ModelAttribute("TRANS_ID") String transId, @ModelAttribute("TRANS_MSG") String transMessage,
			@ModelAttribute("EMANDATE_STATUS") String emandateStatus,
			@ModelAttribute("EMANDATE_REMARKS") String mandateRemarks,*/ Model map, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
			
		logger.info("@@ BSE MF STAR Transaction status controller @@");
		String returnUrl = "bsemf/bse-purchase-status";
		
		Map<String,String> paymentgatewaylist = new HashMap<String,String>();
		Bsepay pay = new Bsepay();
		logger.info("Transaction status for client- "+ transReport.getClientcode() + " -> "+ transReport.getTransactionstatus());
		
		
		if (transReport.getClientcode().isEmpty()) {
			logger.info("bseMFTransactionStatus(): Client code is empty.. Returning back to home page");
			returnUrl = "redirect:/";
		} else {
			if (transReport.getTransactionstatus().equalsIgnoreCase("Y") || transReport.getTransactionstatus().equalsIgnoreCase("SF")) {
				UserBankDetails bandetails = bseEntryManager.getCustomerBankDetails(transReport.getClientcode());
				
				pay.setChosenbankid(bandetails.getSerialno());
				pay.setBankname(bandetails.getBankName());
				pay.setBankacc(bandetails.getAccountNumber().replaceAll(bandetails.getAccountNumber().substring(0, bandetails.getAccountNumber().length()-2), "xxxxxxxx") );
				String bankname = bandetails.getBankName(); 
				
				getbankgatewaydetails(paymentgatewaylist, bankname);
				
				map.addAttribute("gatewaydata", paymentgatewaylist);
				
				/*
				logger.info("Proceeding to generate pay url for order id - " + transReport.getBseOrderNoFromResponse());
				BseOrderPaymentRequest orderUrl = new BseOrderPaymentRequest();
				//		orderUrl.setClientCode(CommonTask.encryptText(clienCode));
				orderUrl.setClientCode(transReport.getClientcode());
				orderUrl.setMemberCode(CommonConstants.BSE_MEMBER_ID);

				Base64 base64 = new Base64();
				String encodedClientCode = new String(base64.encode(CommonTask.encryptText(transReport.getClientcode()).getBytes()));

				String callbackUrl = URI.create(request.getRequestURL().toString()).resolve(request.getContextPath())
						.toString()
						+ "/mutual-funds/bse-transaction-complete?orderid="
						+ (!transReport.getBseOrderNoFromResponse().isEmpty()
								? transReport.getBseOrderNoFromResponse() + "&client=" + encodedClientCode
										: "NA");
				logger.info("Callback url for payment- " + callbackUrl);
				orderUrl.setLogOutURL(callbackUrl);
				BseOrderPaymentResponse orderUrlReponse = investmentConnectorBseInterface.getPaymentUrl(orderUrl);
				map.addAttribute("orderUrl", orderUrlReponse);
				*/
				
			}

			logger.info("Emdanate status in case of SIP -" + transReport.getEmandateStatusCode());
			//map.addAttribute("TRANS_STATUS", transReport.getTransactionstatus());
			/*
			
			map.addAttribute("TRANS_ID", transId);
			map.addAttribute("MSG", transMessage);
			map.addAttribute("EMANDATE", emandateStatus);
			map.addAttribute("MANDATE_REMARKS", mandateRemarks);
			map.addAttribute("TRANS_TYPE", transyType);
			map.addAttribute("FIRST_PAY", firstPayRequire);
			*/
			

		}
		
		if(transReport.getEmandateStatusCode().equals("S")) {
			
			logger.info("New E-mandate was generated. Get auth link..");
			BseApiResponse resp = bseEntryManager.getemandateauthurl(transReport.getClientcode(), transReport.getMandateid());
			if(resp.getStatusCode().equals("100")) {
				transReport.setOther1("S");
				transReport.setOther2(resp.getRemarks());
			}
		}
		pay.setTransstatus(transReport);
		
		map.addAttribute("bsepay", pay);
		map.addAttribute("TRANS_STATUS", transReport.getTransactionstatus());
		//map.addAttribute("TRANSACTION_REPORT", transReport);
		logger.info("bseMFTransactionStatus(): Return url- "+ returnUrl);
		return returnUrl;
	}


	private void getbankgatewaydetails(Map<String, String> paymentgatewaylist, String bankname) {
		List<BseBankid> gatewaylist = bseEntryManager.getbankgateways(null, bankname);
		if(gatewaylist!=null) {
			for(BseBankid data : gatewaylist) {
				if(data.getActive().equalsIgnoreCase("Y"))
					paymentgatewaylist.put((data.getTransactiontype()+"-"+data.getPaymode()+"-"+data.getBankid()),(data.getPaymode()+ " - "+ data.getBsebankname()));
			}
			logger.info("Total active gateway mode- "+ gatewaylist.size());
			
		}else {
			logger.info("No gateway list to process..");
			paymentgatewaylist.put("NA-NA-NA","No Gateway available");
		}
	}
	
	
	@RequestMapping(value = "/mutual-funds/bse-transaction-status", method = RequestMethod.POST)
	public String bseMFTransactionStatusPost(@ModelAttribute("bsepay")Bsepay pay,  Model map, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		
		logger.info("bsepaymentprocess(): @@ BSE MF STAR purchase confirm controller process for payment @@");
		String returnUrl = "bsemf/bse-payment-status";
		Map<String,String> paymentgatewaylist = new HashMap<String,String>();
		Paymentgatewayresponse payresponse = new Paymentgatewayresponse();
		
		TransactionStatus status = new TransactionStatus();
		String[] payviadata = pay.getPayvia().split("-");
		if(payviadata[0].equalsIgnoreCase("INTERNET_BANKING")) {
			//Pay via internet banking
			/*
			String data = "\\r\\n\\r\\n\\r\\n\\r\\n<html>\\r\\n<head><title>Redirecting to Bank</title>\\r\\n<style>\\r\\n\\r\\n.bodytxt4 {\\r\\n\\r\\n\\tfont-family: Verdana, Arial, Helvetica, sans-serif;\\r\\n\\tfont-size: 12px;\\r\\n\\tfont-weight: bold;\\r\\n\\tcolor: #666666;\\r\\n}\\r\\n.bodytxt {\\r\\n\\tfont-family: Verdana, Arial, Helvetica, sans-serif;\\r\\n\\tfont-size: 13px;\\r\\n\\tfont-weight: normal;\\r\\n\\tcolor: #000000;\\r\\n\\r\\n}\\r\\n.bullet1 {\\r\\n\\r\\n\\tlist-style-type:\\tsquare;\\r\\n\\tlist-style-position: inside;\\r\\n\\tlist-style-image: none;\\r\\n\\tfont-family: Verdana, Arial, Helvetica, sans-serif;\\r\\n\\tfont-size: 10px;\\r\\n\\tfont-weight: bold;\\r\\n\\tcolor: #FF9900;\\r\\n}\\r\\n.bodytxt2 {\\r\\n\\tfont-family: Verdana, Arial, Helvetica, sans-serif;\\r\\n\\tfont-size: 8pt;\\r\\n\\tfont-weight: normal;\\r\\n\\tcolor: #333333;\\r\\n\\r\\n}\\r\\nA.sac2 {\\r\\n\\tCOLOR: #000000;\\r\\n\\tfont-family: Verdana, Arial, Helvetica, sans-serif;\\r\\n\\tfont-size: 10px;\\r\\n\\tfont-weight: bold;\\r\\n\\ttext-decoration: none;\\r\\n}\\r\\nA.sac2:visited {\\r\\n\\tCOLOR: #314D5A; TEXT-DECORATION: none\\r\\n}\\r\\nA.sac2:hover {\\r\\n\\tCOLOR: #FF9900; TEXT-DECORATION: underline\\r\\n}\\r\\n</style>\\r\\n\\r\\n</head>\\r\\n<script language=JavaScript>\\r\\n\\r\\n\\r\\nvar message=\\\"Function Disabled!\\\";\\r\\n\\r\\n\\r\\nfunction clickIE4(){\\r\\nif (event.button==2){\\r\\nreturn false;\\r\\n}\\r\\n}\\r\\n\\r\\nfunction clickNS4(e){\\r\\nif (document.layers||document.getElementById&&!document.all){\\r\\nif (e.which==2||e.which==3){\\r\\nreturn false;\\r\\n}\\r\\n}\\r\\n}\\r\\n\\r\\nif (document.layers){\\r\\ndocument.captureEvents(Event.MOUSEDOWN);\\r\\ndocument.onmousedown=clickNS4;\\r\\n}\\r\\nelse if (document.all&&!document.getElementById){\\r\\ndocument.onmousedown=clickIE4;\\r\\n}\\r\\n\\r\\ndocument.oncontextmenu=new Function(\\\"return false\\\")\\r\\n\\r\\n</script>\\r\\n<table width=\\\"100%\\\" border=\\\"0\\\" cellspacing=\\\"0\\\" cellpadding=\\\"0\\\">\\r\\n  <tr>\\r\\n    <td align=\\\"left\\\" valign=\\\"top\\\">\\r\\n<table width=\\\"100%\\\" border=\\\"0\\\" cellspacing=\\\"0\\\" cellpadding=\\\"0\\\">\\r\\n        <tr> \\r\\n          <td align=\\\"center\\\" valign=\\\"middle\\\"><table width=\\\"100%\\\" border=\\\"0\\\" cellspacing=\\\"0\\\" cellpadding=\\\"0\\\">\\r\\n             \\r\\n              <tr>\\r\\n                <td  align=\\\"center\\\"></td>\\r\\n              </tr>\\r\\n              <tr>\\r\\n                <td height=\\\"85\\\" align=\\\"center\\\"><br>\\r\\n                  <table width=\\\"80%\\\" border=\\\"0\\\" cellpadding=\\\"0\\\" cellspacing=\\\"1\\\" bgcolor=\\\"#CCCCCC\\\">\\r\\n                    <tr>\\r\\n                      <td bgcolor=\\\"#CCCCCC\\\"><table width=\\\"100%\\\" border=\\\"0\\\" cellpadding=\\\"6\\\" cellspacing=\\\"0\\\" bgcolor=\\\"#FFFFFF\\\">\\r\\n                          <tr> \\r\\n                            <td colspan=\\\"2\\\" align=\\\"left\\\" valign=\\\"bottom\\\"><span class=\\\"bodytxt4\\\">Your payment request is being processed...</span></td>\\r\\n                          </tr>\\r\\n                          <tr valign=\\\"top\\\"> \\r\\n                            <td colspan=\\\"2\\\" align=\\\"left\\\"><table width=\\\"100%\\\" border=\\\"0\\\" cellspacing=\\\"0\\\" cellpadding=\\\"0\\\">\\r\\n                                <tr> \\r\\n                                  <td width=\\\"87%\\\" bgcolor=\\\"#cccccc\\\" height=\\\"1\\\" align=\\\"center\\\"></td>\\r\\n                                </tr>\\r\\n                              </table></td>\\r\\n                          </tr>\\r\\n                          <tr> \\r\\n                            <td width=\\\"60%\\\" align=\\\"left\\\" valign=\\\"bottom\\\"><table width=\\\"95%\\\" border=\\\"0\\\" cellpadding=\\\"1\\\" cellspacing=\\\"0\\\" bgcolor=\\\"#FFFFFF\\\">\\r\\n                                <tr> \\r\\n                                  <td align=\\\"right\\\" valign=\\\"top\\\"></td>\\r\\n                                  <td class=\\\"bodytxt\\\">&nbsp;</td>\\r\\n                                </tr>\\r\\n                                <tr> \\r\\n                                  <td height=\\\"19\\\"  align=\\\"right\\\" valign=\\\"top\\\"><li class=\\\"bullet1\\\"></li></td>\\r\\n                                  <td class=\\\"bodytxt2\\\">This is a secure payment \\r\\n                                    gateway using 128 bit SSL encryption.</td>\\r\\n                                </tr>\\r\\n                                <tr> \\r\\n                                  <td align=\\\"right\\\" valign=\\\"top\\\"> <li class=\\\"bullet1\\\"></li></td>\\r\\n                                  <td class=\\\"bodytxt2\\\" >When you submit the transaction, \\r\\n                                    the server will take about 1 to 5 seconds \\r\\n                                    to process, but it may take longer at certain \\r\\n                                    times. </td>\\r\\n                                </tr>\\r\\n                                <tr> \\r\\n                                  <td align=\\\"right\\\" valign=\\\"top\\\"><li class=\\\"bullet1\\\"></li></td>\\r\\n                                  <td class=\\\"bodytxt2\\\" >Please do not press \\\"Submit\\\" \\r\\n                                    button once again or the \\\"Back\\\" or \\\"Refresh\\\" \\r\\n                                    buttons. </td>\\r\\n                                </tr>\\r\\n                              </table></td>\\r\\n                            <td align=\\\"right\\\" valign=\\\"bottom\\\"><table width=\\\"80%\\\" border=\\\"0\\\" cellpadding=\\\"1\\\" cellspacing=\\\"0\\\" bgcolor=\\\"#FFFFFF\\\">\\r\\n                                <tr bgcolor=\\\"#FFFCF8\\\"> \\r\\n                                  <td align=\\\"right\\\" bgcolor=\\\"#FFFFFF\\\"></td>\\r\\n                                </tr>\\r\\n                                <tr bgcolor=\\\"#FFFCF8\\\"> \\r\\n                                  <td align=\\\"right\\\" valign=\\\"middle\\\" bgcolor=\\\"#FFFFFF\\\" class=\\\"bodytxt2\\\">&nbsp;</td>\\r\\n                                </tr>\\r\\n                                <tr bgcolor=\\\"#FFFCF8\\\"> \\r\\n                                  <td align=\\\"right\\\" bgcolor=\\\"#FFFFFF\\\" class=\\\"bodytxt2\\\" >&nbsp;</td>\\r\\n                                </tr>\\r\\n                              </table></td>\\r\\n                          </tr>\\r\\n                        </table></td>\\r\\n                    </tr>\\r\\n                  </table>\\r\\n                  \\r\\n                </td>\\r\\n              </tr>\\r\\n            </table>\\r\\n           \\r\\n          \\r\\n         \\r\\n             </td>\\r\\n        </tr>  \\r\\n\\r\\n\\r\\n      </table></td>\\r\\n  </tr>\\r\\n  \\r\\n</table>\\r\\n\\r\\n\\r\\n\\r\\n<body>\\r\\n<form name=\\\"Bankfrm\\\" method=\\\"post\\\" action='https://shopping.icicibank.com/corp/BANKAWAY?IWQRYTASKOBJNAME=bay_mc_login&BAY_BANKID=ICI'>\\r\\n \\r\\n\\t\\t\\t  \\r\\n              <input type = \\\"hidden\\\" name = \\\"MD\\\" value=\\\"P\\\">\\r\\n\\t\\t\\t\\r\\n              \\r\\n\\t\\t\\t  \\r\\n              <input type = \\\"hidden\\\" name = \\\"PID\\\" value=\\\"000000001086\\\">\\r\\n\\t\\t\\t\\r\\n              \\r\\n\\t\\t\\t  \\r\\n              <input type = \\\"hidden\\\" name = \\\"ES\\\" value=\\\"hbVjLCMyDHSYxiBaT7dJgaVXbhCCcxOAk4mNJPkwEQlcdklihe4UQTNrhsjzGEl/ts8Sl9RCMvWWeSMU1MZ7vRMHGEv94hBmuaoqeg0CLXZGgqqZp0aRKazsBdLAYpqTZ94askMgUzU34Bcgb4dogol5jxM0AolY2RtMcDhHrEDjpD3ygzEOJaaT97DmUXVR7p9iQcr1q5TRPpyroTq1Urboe2XFC+91ndxTYa3AjkiPpI+6/JiAh/Wt2TMkWfwm\\\">\\r\\n\\t\\t\\t\\r\\n              \\r\\n\\t\\t\\t  \\r\\n              <input type = \\\"hidden\\\" name = \\\"SPID\\\" value=\\\"NA\\\">\\r\\n\\t\\t\\t\\r\\n              \\r\\n\\t</form>\\r\\n</body>\\r\\n<script>\\r\\ndocument.Bankfrm.submit();\\r\\n</script>\\r\\n</html>\\r\\n";
			data = data.replace("\\n", "");
			data = data.replace("\\t", "");
			data = data.replace("\\r", "");
			data = data.replace("\\", "");
			
			map.addAttribute("data", data);
			*/
			
			Base64 base64 = new Base64();
			String encodedClientCode = new String(base64.encode(CommonTask.encryptText(pay.getTransstatus().getClientcode()).getBytes()));

			String callbackUrl = URI.create(request.getRequestURL().toString()).resolve(request.getContextPath())
					.toString()
					+ "/mutual-funds/bse-transaction-complete?orderid="
					+ (!pay.getTransstatus().getBseOrderNoFromResponse().isEmpty()
							? pay.getTransstatus().getBseOrderNoFromResponse() + "&client=" + encodedClientCode
									: "NA");
			logger.info("Callback url for payment- " + callbackUrl);
			pay.setLoopbackurl(callbackUrl);
			
			payresponse = bseEntryManager.getPaymentGateway(pay);
			
			if(payresponse.getStatuscode().equals("101")) {
				logger.info("Payment request failed for- "+ payresponse.getResponse());
				map.addAttribute("TRANS_STATUS", "RETRY");
				status = pay.getTransstatus();
				status.setTransactionmsg(payresponse.getResponse());
//				map.addAttribute("TRANSACTION_REPORT", status);
				getbankgatewaydetails(paymentgatewaylist, pay.getBankname());
				map.addAttribute("gatewaydata", paymentgatewaylist);
				
				map.addAttribute("error", payresponse.getResponse());
				returnUrl = "bsemf/bse-purchase-status";
			}else {
				map.addAttribute("data", payresponse.getResponse());
			}
			pay.setTransstatus(status);
		}else if(payviadata[0].equalsIgnoreCase("UPI")) {
			
			payresponse = bseEntryManager.getPaymentGateway(pay);
			
			if(payresponse.getStatuscode().equals("101")) {
				logger.info("UPI Payment request failed for- "+ payresponse.getResponse());
				map.addAttribute("TRANS_STATUS", "RETRY");
				status = pay.getTransstatus();
				status.setTransactionmsg(payresponse.getResponse());
				map.addAttribute("error", payresponse.getResponse());
				getbankgatewaydetails(paymentgatewaylist, pay.getBankname());
				map.addAttribute("gatewaydata", paymentgatewaylist);
				
				returnUrl = "bsemf/bse-purchase-status";
			}else {
				map.addAttribute("data", payresponse.getResponse());
				map.addAttribute("TRANS_STATUS", "UPI_REQUEST");
			}
			pay.setTransstatus(status);
		}else {
			logger.info("Invalid payment method selected.. Returning back to page...");
			map.addAttribute("TRANS_STATUS", "RETRY");
			map.addAttribute("error", "Invalid payment method.");
			getbankgatewaydetails(paymentgatewaylist, pay.getBankname());
			map.addAttribute("gatewaydata", paymentgatewaylist);
			
			returnUrl = "bsemf/bse-purchase-status";
		}
		
		
		map.addAttribute("bsepay", pay);
		
		return returnUrl;
	}



	@RequestMapping(value = "/mutual-funds/view-purchase-history", method = RequestMethod.GET)
	public String bseMFViewpurchaseHistory(Model map, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {

		logger.info("@@ BSE MF STAR purchase history controller @@");
		String returnUrl = "bsemf/bsemf-purchase-history2";

		try {
			if (session.getAttribute("userid").toString() != null || session.getAttribute("token").toString() != null) {
				String clientId = bseEntryManager.getClientIdfromMobile(session.getAttribute("userid").toString());
				if (!clientId.isEmpty()) {
					List<BsemfTransactionHistory> purchaseHistoryList = bseEntryManager.getAllPurchaseHistory(clientId);
					if (purchaseHistoryList != null) {
						// map.addAttribute("PURCHASE_LIST", "SUCCESS");
						if (purchaseHistoryList.size() >= 1) {
							map.addAttribute("PURCHASE_LIST", "SUCCESS");
							map.addAttribute("PURCHASE_ORDERS", purchaseHistoryList);
						} else {
							map.addAttribute("PURCHASE_LIST", "NONE");
						}

					} else {
						map.addAttribute("PURCHASE_LIST", "ERROR");
					}
				} else {
					logger.info("Customer client Id not found");
					map.addAttribute("PURCHASE_LIST", "ID_NOT_FOUND");
				}

			} else {
				returnUrl = "redirect:/login?ref="+URLEncoder.encode(request.getRequestURL().toString(), StandardCharsets.UTF_8.toString());
				/*
				 * map.addAttribute("TRANS_STATUS", transStatus);
				 * map.addAttribute("TRANS_ID",transId);
				 */
			}
		} catch (Exception e) {
			logger.error("bseMFViewpurchaseHistory(): Error processing request",e);
			returnUrl = "redirect:/login";
		}

		return returnUrl;
	}

	@RequestMapping(value = "/mutual-funds/orderpaymentStatus", method = RequestMethod.GET)
	@ResponseBody
	public String getPurchasepaymentStatus(@RequestParam(name = "client", required = false) String clientId,
			@RequestParam(name = "order", required = false) String orderNo, Model map, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {

		logger.info("Order Payment status requested- ");
		String returnFlag = "FAIL";

		logger.info("Data- " + clientId + " " + orderNo);
		if (session.getAttribute("token") == null) {
			logger.info("No session found for requesting user. Invalidating request");
			returnFlag = "NO_SESSIION";
		} else {
			if (clientId == null || orderNo == null) {
				logger.info("Parameters data not found");
				returnFlag = "INVALID_REQUEST";
			} else {

				String resp = investmentConnectorBseInterface.BseOrderPaymentStatus(clientId, orderNo);

				List<String> res = Arrays.asList(resp.split("\\|"));
				/*
				 * if(res.get(0).equals("100")){
				 * 
				 * }else{
				 * 
				 * }
				 */
				returnFlag = res.get(1);
			}
		}


		return returnFlag;
	}
	
	
	@RequestMapping(value = "/mutual-funds/getFunds", method = RequestMethod.POST)
	public String getFunds(Model map, HttpServletRequest request, HttpServletResponse response, HttpSession session) {

		logger.info("getFunds(): MF All Funds Inventory");

		String returnUrl = "bsemf/select-fund-explorer";

		PageRequest p = new PageRequest(0, 200);
		Pageable pg = p.first();

		Page<BseFundsScheme> b = bseEntryManager.getpaginatedFundsList(pg);
		logger.info("getFunds(): Paginated fundss- " + b.getSize());
		logger.info("getFunds(): Total pages- " + b.getTotalPages());

		List<BseFundsScheme> funds = b.getContent();

		SelectMFFund fundChoice = new SelectMFFund();
		if (session.getAttribute("token") != null) {
			fundChoice.setMobile(session.getAttribute("userid").toString());
			try {
				if(session.getAttribute("pan") != null) {
					logger.info("Set customer PAn retrieved from session data");
					fundChoice.setPan(session.getAttribute("pan").toString());
				}else {
					logger.info("Fetch PAN if customer is registered.");
					String panNumber = bseEntryManager.getCustomerPanfromMobile(fundChoice.getMobile());
					fundChoice.setPan(panNumber);
				}
			} catch (Exception e) {
				logger.error("getFunds(): Database connect issue: unable to fetch customer PAN number", e);
			}
		}

		map.addAttribute("fundsexplorer", funds);
		map.addAttribute("selectFund", fundChoice);

		return returnUrl;

	}

	@RequestMapping(value = "/profile/fatca-declaration", method = RequestMethod.GET)
	public String bseFatcaDeclaration(@ModelAttribute("fatcaform") BseFatcaForm fatcaForm, Model map,
			HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		logger.info("@@ BSE MF STAR FATCA FORM GET controller @@");
		String returnUrl = "bsemf/bsemf-fatca";

		map.addAttribute("fatcaform", fatcaForm);
		return returnUrl;
	}

	/*
	 * @RequestMapping(value = "/uploadfile", method = RequestMethod.GET) public
	 * String uploadfile(@ModelAttribute("fileform") BseFileUpload fileform,Model
	 * map, HttpServletRequest request, HttpServletResponse response, HttpSession
	 * session) { logger.info("@@ BSE MF STAR FILE UPLOADcontroller @@"); String
	 * returnUrl = "bsemf/file-upload";
	 * 
	 * map.addAttribute("contextcdn", env.getProperty(CommonConstants.CDN_URL));
	 * return returnUrl; }
	 */

	@RequestMapping(value = "/uploadaoffile.do", method = RequestMethod.POST)
	public String uploadfileStorePost(@ModelAttribute("fileform") BseFileUpload fileform, Model map,
			HttpServletRequest request, HttpServletResponse response, HttpSession session,
			RedirectAttributes redirectAttributes) {
		logger.info("@@ BSE MF STAR FILE POSt UPLOADcontroller @@..");
		String returnUrl = "redirect:/my-dashboard";
		MultipartFile file = fileform.getFile();
		logger.info(file.getOriginalFilename() + " " + file.getContentType());
		if (file.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
			redirectAttributes.addFlashAttribute("FILE_UPLOAD", "F");
			return "redirect:/my-dashboard";

		} else if (!file.getContentType().equalsIgnoreCase("application/pdf")) {
			redirectAttributes.addFlashAttribute("FILE_UPLOAD", "F");
			redirectAttributes.addFlashAttribute("message", "Invalid file type");
			return "redirect:/my-dashboard";
		} else {
			try {

				// Get the file and save it somewhere
				String pan = bseEntryManager.getCustomerPanfromMobile(fileform.getFileowner());
				if (pan != null) {
					byte[] bytes = file.getBytes();
					Path path = Paths.get(env.getProperty(CommonConstants.BSE_AOF_GENERATION_FOLDR) + pan + ".pdf");
					Files.write(path, bytes);
					redirectAttributes.addFlashAttribute("message", "File uploaded successfully");
					redirectAttributes.addFlashAttribute("FILE_UPLOAD", "S");
				} else {
					redirectAttributes.addFlashAttribute("message", "Invalid customer.File upload denied.");
					redirectAttributes.addFlashAttribute("FILE_UPLOAD", "F");
				}

			} catch (Exception e) {
				logger.error("uploadfileStorePost(): Upload failed",e);
			}
		}

		return returnUrl;
	}
	
	
	@CrossOrigin(origins = {"https://www.freemi.in"})
	@RequestMapping(value = "/mutual-funds/uploadsign", method = RequestMethod.POST)
	@ResponseBody
	public String uploadsign(ModelMap map, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {

		logger.info("mfRegisterSignature():  mf-signature-update post controller from URL- " + request.getRequestURI());
		String returnResponse = "SUCCESS";
		String mobile = "";

		String orderCallUrl = URI.create(request.getRequestURL().toString()).resolve(request.getContextPath()).toString();
		//		System.out.println(orderCallUrl);
		logger.info("uploadsign(): Rordercallback url- "+ orderCallUrl );
		logger.info("uploadsign(): Referrer URL for upload sign- "+ request.getParameter("referrer") );
		MFCustomers investForm = null;
		boolean proceedwithsign = false;
		logger.info("uploadsign(): Checking for CUSTOMER_TYPE: "+ session.getAttribute("CUSTOMER_TYPE"));

		try {
			if (request.getParameter("referrer").contains("/products/my-dashboard") ) {
				logger.info( "uploadsign(): Signature request received from my-dashboad. User session must be present valid to proceed.");
				if (session.getAttribute("token") != null && session.getAttribute("userid") != null) {

					try {
						ResponseEntity<String> apiresponse = profileRestClientService.validateUserToken(session.getAttribute("userid").toString(),session.getAttribute("token").toString(), CommonTask.getClientSystemIp(request));
						logger.debug("uploadsign(): RESPONSE- " + apiresponse.getBody());
						if (apiresponse.getBody().equals("VALID")) {
							logger.info("uploadsign(): Session found to be valid.. Proceed with upload");
							mobile = session.getAttribute("userid").toString();

							logger.info("uploadsign(): Upload sign for customer mobile from session- " + session.getAttribute("userid"));
							investForm = bseEntryManager.getCustomerInvestFormData(session.getAttribute("userid").toString());

							//				Validate customer KYC status
							if(investForm.getPan1KycVerified().equals("Y") || env.getProperty("investment.bse.sign.bypasskyc").equalsIgnoreCase("Y")) {
								logger.info("uploadsign(): Customer KYC is found verified/bypassed. Continue with sign upload.. ");
								proceedwithsign = true;
							}else {
								logger.info("Customer PAN is not yet KYC verfied.");
								returnResponse = "KYC_NOT_VERIFIED";
							}

						} else if (apiresponse.getBody().equals("EXPIRED")) {
							logger.info("uploadsign(): Session has expired for requesting user- "+ mobile);
							returnResponse = "SESSION_EXPIRED";

						} else {
							logger.info("uploadsign(): Session mismtach or invalid.. Not allowing to access.");
							returnResponse = "REQUEST_DENIED";
						}
					} catch (Exception e) {
						logger.error("uploadsign(): Failed to validate session for logged in user..", e);
						returnResponse = "INTERNAL_ERROR";
					}

				} else {
					logger.info("uploadsign(): User session not found. user need to login to proceed.. Request denied for user with mobile no: " + mobile);
					returnResponse = "NO_SESSION";
				}
			} else if (request.getParameter("referrer").contains("/mutual-funds/mf-registration-status")) {
				logger.info( "uploadsign(): Signature request received from mf-registration-status. CUSTOMER_TYPE must be present in session to proceed");

				if (session.getAttribute("token") != null && session.getAttribute("userid") != null) {
					mobile = session.getAttribute("userid").toString();
					logger.info("Mobile number form session- "+ mobile);

					try {
						ResponseEntity<String> apiresponse = profileRestClientService.validateUserToken(session.getAttribute("userid").toString(),session.getAttribute("token").toString(), CommonTask.getClientSystemIp(request));
						logger.debug("uploadsign(): RESPONSE- " + apiresponse.getBody());
						if (apiresponse.getBody().equals("VALID")) {
							logger.info("uploadsign(): Session found to be valid.. Proceed with upload");

							mobile = session.getAttribute("userid").toString();
							logger.info("uploadsign(): Upload sign for customer mobile from session- " + session.getAttribute("userid"));
							investForm = bseEntryManager.getCustomerInvestFormData(session.getAttribute("userid").toString());

							if(investForm.getPan1KycVerified().equals("Y") || env.getProperty("investment.bse.sign.bypasskyc").equalsIgnoreCase("Y")) {
								logger.info("uploadsign(): Customer KYC is found verified/bypassed. Continue with sign upload.. ");
								proceedwithsign = true;
							}else {
								logger.info("uploadsign(): Customer PAN is not yet KYC verfied.");
								returnResponse = "KYC_NOT_VERIFIED";
							}

						} else if (apiresponse.getBody().equals("EXPIRED")) {
							logger.info("uploadsign(): Session has expired for requesting user- "+ mobile);
							returnResponse = "SESSION_EXPIRED";

						} else {
							logger.info("uploadsign(): Session mismtach or invalid.. Not allowing to access.");
							returnResponse = "REQUEST_DENIED";
						}
					} catch (Exception e) {
						logger.error("uploadsign(): Failed to validate session for logged in user..", e);
						returnResponse = "INTERNAL_ERROR";
					}

				}
				else if (session.getAttribute("CUSTOMER_TYPE") != null) {
					if (session.getAttribute("CUSTOMER_TYPE").toString().equals("NEW_CUSTOMER")) {


						investForm = (MFCustomers) session.getAttribute("mfRegisterdUser");
						logger.info("uploadsign(): investForm from session - " + investForm);

						mobile = investForm.getMobile();

						logger.info("uploadsign(): Mobile no from purchase form - "+ mobile);

						if(investForm.getPan1KycVerified().equals("Y") || env.getProperty("investment.bse.sign.bypasskyc").equalsIgnoreCase("Y")) {
							logger.info("uploadsign(): Customer PAN is marked KYC verified/bypassed. Allow to update sign for Account");
							proceedwithsign = true;
						}else {
							logger.info("Customer PAN is not yet KYC verfied for new customer.");
							returnResponse = "KYC_NOT_VERIFIED";
						}

					} else {
						logger.info("uploadsign(): CUSTOMER_TYPE not valid type to accept request");
						returnResponse = "REQUEST_DENIED";
					}
				} else {
					logger.info("uploadsign(): CUSTOMER_TYPE not found to proceed with request..");
					returnResponse = "REQUEST_DENIED";
				}

			} else {
				logger.info("uploadsign(): Request received from unregistered URL to register sign");
				returnResponse = "REQUEST_DENIED";
			}

			if (proceedwithsign && !mobile.isEmpty()) {

				logger.info("uploadsign(): sign1 - " + request.getParameter("sign1"));
				logger.info("uploadsign(): sign2 - " + request.getParameter("sign2"));
				//				logger.info("mfRegisterSignature(): savesign: "+  request.getParameter("savesign"));
				logger.info("Referrer URL to sign upload- " + request.getParameter("referrer"));

				String customerSignature1 = request.getParameter("sign1");
				String customerSignature2 = request.getParameter("sign2");

				logger.info("uploadsign(): Request received for- " + mobile);
				//				investForm.setCustomerSignature(customerSignature1);
				investForm.setCustomerSignature1(customerSignature1);
				investForm.setCustomerSignature2(customerSignature2);

				if ((investForm.getHoldingMode().equals("AS") || investForm.getHoldingMode().equals("JO")) && (customerSignature2.isEmpty() || customerSignature2.split(",")[1].length() <=1594 || customerSignature1.equals(customerSignature2) ) ) {
					logger.info("uploadsign(): Signature required from both applicant.");
					returnResponse = "APP2_SIGN_REQUIRED";
				} else if (customerSignature1.equals(customerSignature2)) {
					logger.info("mfRegisterSignature(): both signature same");
					returnResponse = "BOTH_SIGN_SAME";
				} else {

					String result = "";
					String fileName = investForm.getPan1() + ".pdf";
					BseAOFDocument flag1 = BseAOFGenerator.aofGenerator(null,investForm, fileName,
							env.getProperty("investment.bse.aoffile.logo"), "VERIFIED",
							env.getProperty(CommonConstants.BSE_AOF_GENERATION_FOLDR));
					logger.info("uploadsign(): Status of AOF generation- " + flag1);
					if (flag1.getFilegenerationstatus().equalsIgnoreCase("SUCCESS")) {
						logger.info("uploadsign(): Signed AOF file generation complete for customer- "
								+ investForm.getPan1());
						result = bseEntryManager.upddateCustomerFormSignature(investForm.getMobile(),
								investForm.getPan1(), investForm.getCustomerSignature1(),
								investForm.getCustomerSignature2());
						if (result.equalsIgnoreCase("SUCCESS")) {
							session.setAttribute("PURCHASE_TYPE", "NEW_CUSTOMER");
							map.addAttribute("AOF", "COMPLETE");
						} else {
							map.addAttribute("AOF", "FAIL");
						}
					} else {
						logger.info("uploadsign(): Failed to generate AOF. Check internal system.");
						returnResponse = "INTERNAL_ERROR";
					}
				}
			} else {
				logger.info( "uploadsign(): Upload sign not processed. Request denied. Return respective response");
				//				returnResponse = "REQUEST_DENIED";
			}
		} catch (Exception ex) {
			logger.error("uploadsign(): No registration sesssion found, Rejecting request.", ex);
			returnResponse = "INTERNAL_ERROR";
		}
		logger.info("uploadsign(): returnResponse - " + returnResponse);
		return returnResponse;

	}


	@RequestMapping(value = "/mutual-funds/uploadsignedaof", method = RequestMethod.POST)
	@ResponseBody
	public HttpClientResponse uploadSignedAOFFile(@RequestBody Map<String, String> requestdata, HttpServletRequest request, HttpServletResponse response, HttpSession session) {

		logger.info("uploadSignedAOFFile(): mf-signature-udpate post controller");
		String mobile = "";
		String requestedMobile = "";
		HttpClientResponse requeststatus = new HttpClientResponse();
		requeststatus.setResponseCode(CommonConstants.TASK_FAILURE);
		
		try {
		if(requestdata.containsKey("mobile")) {
			
		if (session.getAttribute("token") != null && session.getAttribute("userid") != null) {
				ResponseEntity<String> apiresponse = profileRestClientService.validateUserToken(session.getAttribute("userid").toString(),session.getAttribute("token").toString(), CommonTask.getClientSystemIp(request));
				logger.debug("uploadSignedAOFFile(): RESPONSE- " + apiresponse.getBody());
				if (apiresponse.getBody().equals("VALID")) {
					logger.info("uploadSignedAOFFile(): Session found to be valid.. Proceed with upload");
					mobile = session.getAttribute("userid").toString();
					requestedMobile = mobile;

					logger.info("uploadSignedAOFFile(): Upload sign for customer mobile from session- " + session.getAttribute("userid"));
					
					if (requestdata.get("mobile").equalsIgnoreCase(mobile)) {
//						requeststatus = bseEntryManager.completemfregistration(requestedMobile);
						MFCustomers investForm = bseEntryManager.getCustomerInvestFormData(mobile);
						BseApiResponse aofuplaodresponse = bseEntryManager.uploadAOFForm(investForm.getMobile(), env.getProperty(CommonConstants.BSE_AOF_GENERATION_FOLDR),env.getProperty(CommonConstants.BSE_AOF_LOGO_LOCATION), investForm.getClientID(),investForm);
						if(aofuplaodresponse!=null && aofuplaodresponse.getStatusCode().equalsIgnoreCase(CommonConstants.TASK_SUCCESS_S)) {
							requeststatus.setResponseCode(CommonConstants.TASK_SUCCESS);
							requeststatus.setRetrunMessage(aofuplaodresponse.getRemarks());
						}else {
							requeststatus.setRetrunMessage(aofuplaodresponse.getRemarks());
						}
						
					} else {
						logger.info("Mobile number do not match with holding session form data. Request rejected. Session mobile no:"+ mobile + " : Requested mobile: " + requestedMobile);
						requeststatus.setRetrunMessage("Session data mismatch. Kindly contact admin.");
					}
					
				} else if (apiresponse.getBody().equals("EXPIRED")) {
					logger.info("uploadSignedAOFFile(): Session has expired for requesting user- "+ mobile);
					requeststatus.setRetrunMessage("Session token expired. Please login again to complete request.");
				} 

		}else {
			requeststatus.setRetrunMessage("Session lost. Kindly login to complete registration");
		}
		}else {
			requeststatus.setRetrunMessage("Invalid request. Missing required paramaters.");
		}
		
		} catch (Exception e) {
			logger.error("uploadSignedAOFFile(): Failed to validate session for logged in user..", e);
			requeststatus.setRetrunMessage("Internal error. Please try after sometime.");
		}
		
		logger.info("uploadSignedAOFFile(): returnResponse - " + requeststatus.getResponseCode());
		return requeststatus;

	}
	

	@RequestMapping("/download/aof/{fileName:.+}")
	public void downloadPDFResource(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("fileName") String fileName,
			/* @ModelAttribute("mfInvestForm") BseMFInvestForm investForm, */ /* @RequestHeader String referer, */HttpSession session) {
		logger.info("downloadPDFResource(): AOF File download request received for customer- "+ fileName);
		BseAOFDocument aofresult;
		MFCustomers investForm =null;
		boolean proceedwithdownload = false;
		String dataDirectory = env.getProperty(CommonConstants.BSE_AOF_GENERATION_FOLDR);
		// String dataDirectory = "E:\\AOF";
		Path file = Paths.get(dataDirectory, fileName);

		logger.info("downloadPDFResource(): file path- "+ file.toString());
		//		String flag = "SUCCESS";
		logger.info("Request received from URL- "+ request.getParameter("referrer"));
		String mobile="";
		//		if (request.getParameter("referrer").contains("/products/my-dashboard") ) {

		if (session.getAttribute("token") != null && session.getAttribute("userid") != null) {
			logger.info( "downloadPDFResource(): AOF File download request received. Account must be logged in to proceed.");
			try {
				ResponseEntity<String> apiresponse = profileRestClientService.validateUserToken(session.getAttribute("userid").toString(),session.getAttribute("token").toString(), CommonTask.getClientSystemIp(request));
				logger.debug("downloadPDFResource(): RESPONSE- " + apiresponse.getBody());
				if (apiresponse.getBody().equals("VALID")) {
					logger.info("downloadPDFResource(): Session found to be valid.. Proceed with DOWNLOAD");
					mobile = session.getAttribute("userid").toString();

					logger.info("downloadPDFResource(): Upload sign for customer mobile from session- " + session.getAttribute("userid"));
					investForm = bseEntryManager.getCustomerInvestFormData(session.getAttribute("userid").toString());

					if(fileName.substring(0, fileName.indexOf(".")).equalsIgnoreCase(investForm.getPan1())) {
						logger.info("Requested filename matches with customer registered PAN from dashboard. Proceed with download");
						proceedwithdownload = true;	
					}else {
						logger.info("Requested filename do not match with customer registered PAN. Download prevented.");
					}


				} else if (apiresponse.getBody().equals("EXPIRED")) {
					logger.info("downloadPDFResource(): Session has expired for requesting user- "+ mobile);

				} else {
					logger.info("downloadPDFResource(): Session mismtach or invalid.. Not allowing to access.");
				}
			} catch (Exception e) {
				logger.error("downloadPDFResource(): Failed to validate session for logged in user..", e);
			}
		} else {
			logger.info("downloadPDFResourcenew registered customer AOF download file request for PAN" + fileName);
			//			returnResponse = "NO_SESSION";
			if (session.getAttribute("CUSTOMER_TYPE") != null) {
				if (session.getAttribute("CUSTOMER_TYPE").toString().equals("NEW_CUSTOMER")) {

					investForm = (MFCustomers) session.getAttribute("mfRegisterdUser");

					if(fileName.equalsIgnoreCase(investForm.getPan1())) {
						logger.info("downloadPDFResource(): Requested filename matches with customer registered PAN for new customer. Proceed with download");
						proceedwithdownload = true;	
					}else {
						logger.info("downloadPDFResource(): Requested filename do not match with customer registered PAN for new customer. Download prevented.");
					}


				}else {
					logger.info("downloadPDFResource():  CUSTOMER_TYPE data not valid. Request not valid");
				}
			}else {
				logger.info("downloadPDFResource():  CUSTOMER_TYPE not found Request not valid");
			}
		}


		if(proceedwithdownload) {
			logger.info("Proceeding with AOF download...");
			if (!Files.exists(file)) {
				logger.info("AOF file not found. Generating new one...");

				if (investForm != null) {
					aofresult =  BseAOFGenerator.aofGenerator(null,investForm, fileName, env.getProperty("investment.bse.aoffile.logo"),"VERIFIED", dataDirectory);
					logger.info("downloadPDFResource(): AOF generation status without signature for download- "+ aofresult.getFilegenerationstatus());
				} else {
					logger.info("Investform data blank.AOF generation skipped. ");
				}
			}

			if (Files.exists(file)) {
				// response.setHeader("Content-Encoding", "UTF-8");
				response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
				// response.setContentLength((int) f.length());

				response.setContentType("application/pdf");
				// response.setContentType("application/octet-stream");
				// response.addHeader("Content-Disposition", "attachment; filename="+fileName);
				try {
					logger.info("downloadPDFResource(): Send response of file download...");
					Files.copy(file, response.getOutputStream());
					response.getOutputStream().close();
					response.getOutputStream().flush();

				} catch (Exception ex) {
					logger.error("downloadPDFResource(): Error downloading AOF file .",ex);
				}
			} else {
				logger.info("downloadPDFResource(): No AOF file exists or coud be generated for PAN- "+ fileName);

			}
		}else {
			logger.info("AOF fille download not proceseed.. ");
		}

	}


	//    @Scheduled(cron = "0 0 7 * * ?")
	@RequestMapping(value = "/processallotmentstatement/{datedifference}", method = RequestMethod.GET)
	@ResponseBody
	public String getallotmentstatement(@PathVariable("datedifference") String datediff, Model map,
			HttpServletRequest request, HttpServletResponse response, HttpSession session,
			RedirectAttributes redirectAttributes) {

		String str = allotmentstatementcall(datediff);


		return  str;

	}

//	@Scheduled(cron = "0 0 23 * * ?")
	public void scheduledgetallotmentstatement() {
		allotmentstatementcall("0");
	}


	protected String allotmentstatementcall(String datediff) {
		logger.info("getallotmentstatement(): Request received to process allotment statement for datediff: "+ datediff);
		BseApiResponse resp = new BseApiResponse();
		SimpleDateFormat dtfmt = new SimpleDateFormat("dd/MM/yyyy");
		String str="NA";

		try {
			String todate = dtfmt.format(new Date());
			Calendar cal =Calendar.getInstance();
			cal.setTime(new Date());
			cal.add(Calendar.DAY_OF_MONTH, (-1)* Integer.valueOf(datediff!=null?datediff:"1"));
			String fromdate = dtfmt.format(cal.getTime());

			if(cal.after(todate)) {
				logger.info("From date is future date, rquested rejetced");
				str="FUTURE DATE Rejected.";
			}else {
				logger.info("Querying: "+ fromdate + " --> "+ todate);
				resp = bseEntryManager.extractAllotmentstatement(fromdate, todate, "VALID", "All", "All");
				str = resp.getResponseCode() + " -> "+ resp.getRemarks();
			}

		}catch(Exception e) {
			logger.error("Error Processing: ",e);
			str = e.getMessage();
		}
		return str;
	}


	@ModelAttribute("taxStatus")
	public Map<String, String> getTaxStatus() {
		return  InvestFormConstants.taxStatus;
	}

	@ModelAttribute("bsecountryofbirth")
	public Map<String, String> bseCountryList() {
		return InvestFormConstants.bseCountryList;
	}

	@ModelAttribute("holingNature") 
	public Map<String, String> mfHoldingNature()
	{
		return InvestFormConstants.holdingMode; 
	}

	@ModelAttribute("dividendPayMode") 
	public Map<String, String> dividendPayMode()
	{
		return InvestFormConstants.dividendPayMode; 
	}

	@ModelAttribute("occupation") 
	public Map<String, String> occupation()
	{
		
		return InvestFormConstants.occupationList; 
	}

	@ModelAttribute("bankNames") 
	public Map<String, String> bankNames()
	{
		return InvestFormConstants.bankNames; 
	}

	@ModelAttribute("accountTypes")
	public Map<String, String> accountTypes()
	{
		return InvestFormConstants.accountTypes; 
	}

	@ModelAttribute("states") 
	public Map<String, String> states()
	{
		logger.info("Return state list...");
		return InvestFormConstants.states; 
	}
	
	
	@ModelAttribute("idbelongsto") 
	public Map<String, String> getwhoseId()
	{
		logger.info("Return ID belongs to...");
		return InvestFormConstants.idbelongsto; 
	}

	
	@ModelAttribute(name="cities") 
	public Map<String,String> cities(@ModelAttribute("mfInvestForm") MFCustomers investformui)
	{
		Map<String,String> data = new HashMap<String, String>();
		/**
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			logger.info("Data- "+ mapper.writeValueAsString(investformui));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}*/
		if(investformui!=null) {
			if(investformui.getAddressDetails()!=null &&  investformui.getAddressDetails().getState()!=null) {
				String statefrommapping = InvestFormConstants.hdfcstatekey.get(investformui.getAddressDetails().getState());
				
				Map<String,String> filters = new HashMap<String, String>();
				filters.put("stateid", statefrommapping);
				filters.put("search","city");
				data = hdfcService.searchcity2(filters, statefrommapping);
				logger.info("City list retrieved- "+ data.size());
			}
		}
		
		return data;
	}
	


	@ModelAttribute("pincode") 
	public Map<String,String> pincode(@ModelAttribute("mfInvestForm") MFCustomers investForm, HttpServletRequest request)
	{
		Map<String,String> data = new HashMap<String, String>();
		if(investForm!=null) {
			if(investForm.getAddressDetails()!=null &&  investForm.getAddressDetails().getState()!=null) {
				String statefrommapping = InvestFormConstants.hdfcstatekey.get(investForm.getAddressDetails().getState());
				Map<String,String> filters = new HashMap<String, String>();
				filters.put("stateid", statefrommapping);
				filters.put("search",investForm.getAddressDetails().getCity());
				filters.put("cityvaluetype", "CITYNAME");
				Searchlocationdetials data2 = new Searchlocationdetials();
				data2.stateid = statefrommapping;
				data2.cityid = investForm.getAddressDetails().getCity();
				data = hdfcService.searchcitypincode2(data2,filters, data2.stateid,data2.cityid);
				logger.info("Pincode list retrieved- "+ data.size());
			}
		}
		return data;
	}

	public String getmandateurl(String clientid, String mandateid) {
		BseApiResponse response = new BseApiResponse();
		
		response = bseEntryManager.getemandateauthurl(clientid, mandateid);
		
		return  response.getStatusCode()+"|"+response.getRemarks();
	}
	
	@ModelAttribute("wealthSource") 
	public Map<String, String> wealthSource()
	{
		return InvestFormConstants.fatcaWealthSource; 
	}

	@ModelAttribute("incomeSlab") 
	public Map<String, String> incomeSlab()
	{
		return InvestFormConstants.fatcaIncomeSlab; 
	}

	@ModelAttribute("politicalView") 
	public Map<String, String> politicalView()
	{
		return InvestFormConstants.fatcaPoliticalView; 
	}

	@ModelAttribute("occupationType") 
	public Map<String, String> occupationType()
	{
		return InvestFormConstants.fatcaOccupationType; 
	}

	@ModelAttribute("nomineeRelation") 
	public Map<String, String> nomineeRelation()
	{
		return InvestFormConstants.nomineeRelation; 
	}

	@ModelAttribute("addressType") 
	public Map<String, String> addressType()
	{
		return InvestFormConstants.fatcaAddressType; 
	}

	@ModelAttribute("calendarmonths")
	public Map<Integer, String> getcalendarmonths() {
		return InvestFormConstants.bseInvestMonths;
	}
	@ModelAttribute("sipyear")
	public Map<Integer, String> getsipyear() {
		return InvestFormConstants.bseInvestStartYear;
	}

	@ModelAttribute("contextcdn") String contextcdn() {
		return env.getProperty(CommonConstants.CDN_URL);
	}
	
	/*
	@ModelAttribute("fundsexplorer")
	public List<BseMFSelectedFunds> funddetails() {
		return bseEntryManager.getAllSelectedFunds();
	}
	*/
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ModelAndView handleMissingParams(MissingServletRequestParameterException ex) {
		String name = ex.getParameterName();
		logger.info(name + " parameter is missing on requested url. Returning to home url");
		// Actual exception handling
		ModelAndView view = new ModelAndView("redirect:/");
		return view;
	}

	@ExceptionHandler(HttpSessionRequiredException.class)
	public ModelAndView handleMissingSessionHandler(HttpSessionRequiredException ex, HttpServletRequest request) {
		String name = ex.getExpectedAttribute();

		logger.error("Exception was reveived for missing parameters.", ex);
		String returnUrl = "redirect:/";
		logger.info(name + " missing");
		// Actual exception handling
		if (name.equals("selectedFund")) {
			returnUrl = "redirect:/mutual-funds/funds-explorer";
		}
		ModelAndView view = new ModelAndView(returnUrl);
		return view;
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ModelAndView illegalArgumentException(IllegalArgumentException ex, HttpServletRequest request) {

		logger.error("illegalArgumentException was received for missing parameters.- " + request.getRequestURI(), ex);
		String returnUrl = "redirect:/";
		// Actual exception handling
		returnUrl = "redirect:/mutual-funds/funds-explorer";
		ModelAndView view = new ModelAndView(returnUrl);
		return view;
	}
	
	
	private String redirectaftermfpurchaseerror(String referer, HttpServletRequest request) {
		String returnUrl;
		logger.info("Url for processing after MF initiated transaction error- "+ referer);
		
		/*
		if(referer!= null && !referer.isEmpty()){
		    URL url = null;
		    URI uri = null;
		    try {
			url = new URL(referer);
			uri = url.toURI();
			logger.info("uri - "+ uri);
			if(uri.getRawPath().contains("/mutual-funds/mutual-fund-explorer") || uri.getRawPath().contains("/forgotPassword") || uri.getRawPath().contains("/resetPassword")){
			    returnUrl =  URI.create(request.getRequestURL().toString()).resolve(request.getContextPath()).toString();
			}else
			    returnUrl = referer.replace(".do", "");

		    } catch (Exception e) {
				    logger.error("redirectUrlAfterLogin(): Exception while processing...",e);
				    returnUrl =  uri.getRawPath().split("/products/")[1];
				}
		}else{
		    logger.info("redirectUrlAfterLogin(): Referer is null....");

		    returnUrl = URI.create(request.getRequestURL().toString()).resolve(request.getContextPath()).toString();
		}
		*/
//		returnUrl = "redirect:" +URI.create(request.getRequestURL().toString()).resolve(request.getContextPath()).toString();
		
		URL url = null;
	    URI uri = null;
	    String tosend="";
	    try {
		url = new URL(referer);
		uri = url.toURI();
		
		logger.info("uri - "+ uri.getPath() + " -> "+ uri.getRawPath());
			/*
			 * if(uri.getRawPath().contains("/mutual-funds/mutual-fund-explorer") ||
			 * uri.getRawPath().contains("/") || uri.getRawPath().contains("/")){ returnUrl
			 * =
			 * URI.create(request.getRequestURL().toString()).resolve(request.getContextPath
			 * ()).toString(); }else returnUrl = referer.replace(".do", "");
			 */
		
		if(uri.getRawPath().contains("/mutual-funds/funds-explorer") ){
			tosend = uri.getRawPath().split("/products/")[1];
		}else {
			tosend = uri.getPath();
//			tosend = uri.getPath().replaceFirst("/", "");
		}
	    } catch (Exception e) {
			    logger.error("redirectUrlAfterLogin(): Exception while processing...",e);
			    returnUrl =  uri.getRawPath().split("/products/")[1];
			}
		
		
//		returnUrl = "redirect:/" + tosend;
	    returnUrl = tosend;
		logger.info("Redirect url after MF intitiated transaction- "+ returnUrl);
		return returnUrl;
	    }



}
