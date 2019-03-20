package com.freemi.ui.controller;

import java.io.IOException;
import java.net.URI;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.freemi.common.util.BseAOFGenerator;
import com.freemi.common.util.BseRelatedActions;
import com.freemi.common.util.CommonConstants;
import com.freemi.common.util.InvestFormConstants;
import com.freemi.controller.interfaces.InvestmentConnectorBseInterface;
import com.freemi.controller.interfaces.MailSenderHandler;
import com.freemi.database.interfaces.ProductSchemeDetailService;
import com.freemi.database.service.BseEntryManager;
import com.freemi.entity.bse.BseAOFUploadResponse;
import com.freemi.entity.bse.BseApiResponse;
import com.freemi.entity.bse.BseFatcaForm;
import com.freemi.entity.bse.BseOrderPaymentRequest;
import com.freemi.entity.bse.BseOrderPaymentResponse;
import com.freemi.entity.database.UserBankDetails;
import com.freemi.entity.general.Registerform;
import com.freemi.entity.investment.BseAllTransactionsView;
import com.freemi.entity.investment.BseFundsScheme;
import com.freemi.entity.investment.BseMFInvestForm;
import com.freemi.entity.investment.BseMFTop15lsSip;
import com.freemi.entity.investment.BseMandateDetails;
import com.freemi.entity.investment.BsemfTransactionHistory;
import com.freemi.entity.investment.MFAdditionalPurchaseForm;
import com.freemi.entity.investment.MFRedeemForm;
import com.freemi.entity.investment.SelectMFFund;
import com.freemi.entity.investment.TransactionStatus;
import com.freemi.ui.restclient.RestClient;

@Controller
@Scope("session")
@SessionAttributes({"selectedFund","purchaseForm","mfRedeemForm"})
public class BsemfController {

	private static final Logger logger = LogManager.getLogger(BsemfController.class);

	@Autowired
	ProductSchemeDetailService productSchemeDetailService;

	/*
	@Autowired
	private DatabaseEntryManager databaseEntryManager ;//= (DatabaseEntryManager) BeanUtil.getBean(DatabaseEntryService.class);
	 */

	@Autowired
	private BseEntryManager bseEntryManager;

	@Autowired
	InvestmentConnectorBseInterface investmentConnectorBseInterface;

	@Autowired
	private Environment env;
	
	@Autowired
	MailSenderHandler mailSenderHandler;

	@RequestMapping(value = "/mutual-funds/register", method = RequestMethod.GET)
	public String registerUserMfGet(/*@ModelAttribute("selectedFund")SelectMFFund selectFund ,*/@RequestParam(name="mf",required=false)String userType,Model map, HttpServletRequest request,HttpSession session, HttpServletResponse response, Device device) {
		//logger.info("@@@@ Inside Login..");
		logger.info("@@@@ BSE New customer register @@@@");

		BseMFInvestForm investForm = new BseMFInvestForm();
		
		SelectMFFund selectFund = (SelectMFFund) session.getAttribute("selectedFund");
		
		if(selectFund!=null){
			investForm.setPan1(selectFund.getPan());
			investForm.setMobile(selectFund.getMobile());
		}else{
			logger.info("registerUserMfGet(): selectFund is null.");
		}

		if(userType.equalsIgnoreCase("02")){
			//			Marking this register customer in LDAP
			investForm.setProfileRegRequired(true);
		}
		
		if(userType.equalsIgnoreCase("04")){
			logger.info("Request received to complete profile registration. The reuest must come from dashbaord, check if logged in ");
			if(session.getAttribute("userid")!=null){
				investForm = bseEntryManager.getCustomerInvestFormData(session.getAttribute("userid").toString());
			}
		}else{
			investForm.setDividendPayMode("02");
			investForm.setOccupation("01");
		}
		

		map.addAttribute("mfInvestForm", investForm);
		
		map.addAttribute("holingNature", InvestFormConstants.holdingMode);
		map.addAttribute("dividendPayMode", InvestFormConstants.dividendPayMode);
		map.addAttribute("occupation", InvestFormConstants.occupationList);
		map.addAttribute("bankNames", InvestFormConstants.bankNames);
		map.addAttribute("accountTypes", InvestFormConstants.accountTypes);
		map.addAttribute("states", InvestFormConstants.states);
		map.addAttribute("contextcdn", env.getProperty(CommonConstants.CDN_URL));

		System.out.println(device.getDevicePlatform());

		return "bsemf/bse-form-new-customer";
		//		return "bsemf/test";
	}

	@RequestMapping(value = "/mutual-funds/mfInvestRegister.do", method = RequestMethod.POST)
	public String bsemfRegisterpost(@ModelAttribute("mfInvestForm") @Valid BseMFInvestForm investForm,BindingResult bindResult, Model map, HttpServletRequest request, HttpServletResponse response, RedirectAttributes attrs) {

		logger.info("BSE MF STAR Customer Register post controller");
		String returnUrl = "bsemf/bse-registration-status";
		String flag = "NOT_COMPLETE";

		//		String error = "N";

		if(!investForm.isUbo()){	
			map.addAttribute("error", "Confirm the policy");
			
			map.addAttribute("holingNature", InvestFormConstants.holdingMode);
			map.addAttribute("dividendPayMode", InvestFormConstants.dividendPayMode);
			map.addAttribute("occupation", InvestFormConstants.occupationList);
			map.addAttribute("bankNames", InvestFormConstants.bankNames);
			map.addAttribute("accountTypes", InvestFormConstants.accountTypes);
			map.addAttribute("states", InvestFormConstants.states);
			return "bsemf/bse-form-new-customer";
		}

		if(bindResult.hasErrors()){
			map.addAttribute("error", bindResult.getFieldError().getDefaultMessage());
			
			map.addAttribute("holingNature", InvestFormConstants.holdingMode);
			map.addAttribute("dividendPayMode", InvestFormConstants.dividendPayMode);
			map.addAttribute("occupation", InvestFormConstants.occupationList);
			map.addAttribute("bankNames", InvestFormConstants.bankNames);
			map.addAttribute("accountTypes", InvestFormConstants.accountTypes);
			map.addAttribute("states", InvestFormConstants.states);
			return "bsemf/bse-form-new-customer";
		}

		try{

			//			Check if user required to be registered at portal first

			if(investForm.isProfileRegRequired()){
				logger.info("This is a fresh customer. Register the user first for portal");
				Registerform registerForm = new Registerform();
				registerForm.setMobile(investForm.getMobile());
				registerForm.setEmail(investForm.getEmail());
				registerForm.setFullName(investForm.getInvName());
				registerForm.setPassword(CommonConstants.GENERATE_PASSWORD_BY_FREEMI);	//Reserved token for generating token at profile side
				registerForm.setRegistrationref("MF_REG_NEW");

				RestClient client = new RestClient();
				ResponseEntity<String> responsePortal = null;
				try {
					responsePortal = client.registerUser(registerForm);
					String status = responsePortal.getHeaders().get("STATUS").get(0);

					if(status.equals("SUCCESS")){
						logger.info("User registration successful initiated during new customer registration");

						logger.info("Registration successful. Login to your account");
					}else if(status.equals("DUPLICATE ENTRY")){
						logger.info("Account already exist.");
					}else if(status.equals("ERROR")){
						logger.info("Registration failed. Please try again after sometime");
					}else{
						logger.info(responsePortal.getHeaders().get("STATUS").get(0));
						//						model.addAttribute("error", "Unknown response");
						logger.info("Registration failed during MF user registration. Please check profile log");
					}

				}catch(HttpStatusCodeException  e){
					logger.error("bsemfRegisterpost(): Registartion Link failure",e);
				} catch (JsonProcessingException e) {
					logger.error("bsemfRegisterpost():invalid form data",e);
				}catch(Exception e){
					logger.error("bsemfRegisterpost(): Exception proceesing regidtration request.",e);
				}

			}

			flag = bseEntryManager.saveCustomerDetails(investForm);		//enable after test

			//			flag ="SUCCESS";
			logger.info("Customer MF registration status- "+ flag);
			if(!flag.equalsIgnoreCase("SUCCESS")){
				returnUrl= "bsemf/bse-form-new-customer";
				if(flag.equalsIgnoreCase("EXIST")){
					map.addAttribute("error", "Customer already exist with given PAN no.");
				}else if(flag.equalsIgnoreCase("BSE_CONN_FAIL")){
					map.addAttribute("error", "BSE endpoint connection failure!");
				}
				else{
					map.addAttribute("error", flag);
				}
				
				map.addAttribute("holingNature", InvestFormConstants.holdingMode);
				map.addAttribute("dividendPayMode", InvestFormConstants.dividendPayMode);
				map.addAttribute("occupation", InvestFormConstants.occupationList);
				map.addAttribute("bankNames", InvestFormConstants.bankNames);
				map.addAttribute("accountTypes", InvestFormConstants.accountTypes);
				map.addAttribute("states", InvestFormConstants.states);
			}else{
				returnUrl="redirect:/mutual-funds/mf-registration-status";
				attrs.addFlashAttribute("mfInvestForm", investForm);
				attrs.addAttribute("STATUS", "Y");
			}
			/*
			String customerid=BseRelatedActions.generateID(investForm.getInvName(), investForm.getPan1(), null, investForm.getMobile(),1);
			investForm.setClientID(customerid);
			String flag = investmentConnectorBseInterface.saveCustomerRegistration(investForm, null);
			if(flag.equalsIgnoreCase("false")){
			returnUrl= "bsemf/bse-form-new-customer";
			map.addAttribute("error", "Customer issue with given PAN no.");

		}*/
		}catch(Exception e){
			returnUrl= "bsemf/bse-form-new-customer";
			map.addAttribute("error", "Unable to register customer currently.");
			logger.error("Unable to save customer registration",e);
			//			e.printStackTrace();
		}
		return returnUrl;

	}

	@RequestMapping(value = "/mutual-funds/mf-registration-status", method = RequestMethod.GET)
	public String mfRegistrationStatusGet(@ModelAttribute("STATUS") String status,@ModelAttribute("mfInvestForm") BseMFInvestForm investForm, ModelMap map, HttpServletRequest request, HttpServletResponse response, HttpSession session) {

		logger.info("mf-registration-status Get controller");
		System.out.println("Type of holding- "+ investForm.getHoldingMode());
		String returnUrl = "bsemf/bse-registration-status";

		//		investForm.setPan1("as"); 	
		System.out.println(investForm.getPan1());
		session.setAttribute("mfRegisterdUser", investForm);
		/*if(investForm.getMobile()==null){
			map.clear();
			returnUrl= "redirect:/mutual-funds/top-performing";
		}*/
		map.addAttribute("investForm", investForm);
		return returnUrl;

	}

	@RequestMapping(value = "/mutual-funds/uploadsign", method = RequestMethod.POST)
	@ResponseBody
	public String mfRegisterSignature(/*@RequestBody CustomerSignature sign,@ModelAttribute("mfInvestForm") BseMFInvestForm investForm,*/ ModelMap map, HttpServletRequest request, HttpServletResponse response,HttpSession session) {

		logger.info("mf-signature-udpate post controller");
		String returnResponse = "SUCCESS";


		try{
			BseMFInvestForm investForm = (BseMFInvestForm) session.getAttribute("mfRegisterdUser");
			System.out.println(investForm);
			if(investForm.getMobile()!=""){

				String customerSignature1 = request.getParameter("sign1");
				System.out.println(request.getParameter("sign1"));
				System.out.println(request.getParameter("sign2"));
				System.out.println(request.getParameter("sign3"));
				//		System.out.println(sign.getSign1());

				System.out.println("Request received for- "+investForm.getMobile());
				investForm.setCustomerSignature(customerSignature1);
				String result="";
				String flag1= BseAOFGenerator.aofGenerator(investForm, investForm.getMobile(), env.getProperty("investment.bse.aoffile.logo"), "VERIFIED", env.getProperty(CommonConstants.BSE_AOF_GENERATION_FOLDR));
				if(flag1.equalsIgnoreCase("SUCCESS")){
					logger.info("Signed AOF file generation complete for customer- "+ investForm.getPan1());
					result= bseEntryManager.upddateCustomerFormSignature(investForm.getMobile(), investForm.getPan1(), investForm.getCustomerSignature());
				}
				if(result.equalsIgnoreCase("SUCCESS")){
					session.setAttribute("PURCHASE_TYPE", "NEW_CUSTOMER");
					map.addAttribute("AOF", "COMPLETE");
				}else{
					map.addAttribute("AOF", "FAIL");

				}
			}else{
				logger.info("User BSERegister form not found in session. Request denied. User need to login to complete task.");
				returnResponse = "REQUEST_DENIED";
			}
		}catch(NullPointerException ex){
			logger.error("No registration sesssion found, Rejecting request");
			returnResponse = "REQUEST_INVALID";
		}

		return returnResponse;

	}
	
	
	@RequestMapping(value = "/mutual-funds/uploadsignRegisteredCustomer", method = RequestMethod.POST)
	@ResponseBody
	public String mfaofSignUploadRegisteredCustomer(/*@RequestBody CustomerSignature sign,@ModelAttribute("mfInvestForm") BseMFInvestForm investForm,*/ ModelMap map, HttpServletRequest request, HttpServletResponse response,HttpSession session) {

		logger.info("mf-signature-udpate post for registered customer incomplete controller");
		String returnResponse = "SUCCESS";

		
		try{
			
//			BseMFInvestForm investForm = (BseMFInvestForm) session.getAttribute("mfRegisterdUser");
//			System.out.println(investForm);
			if( session.getAttribute("token")!=null &&  session.getAttribute("userid")!=null){
				logger.info("Upload sign for customer mobile from session- "+ session.getAttribute("userid"));
				BseMFInvestForm investForm= bseEntryManager.getCustomerInvestFormData(session.getAttribute("userid").toString());
				session.setAttribute("mfRegisterdUser", investForm);
				
				System.out.println("Invest form- " + investForm);
				
				String customerSignature1 = request.getParameter("sign1");
				System.out.println(request.getParameter("sign1"));
				System.out.println(request.getParameter("sign2"));
				System.out.println(request.getParameter("sign3"));
				//		System.out.println(sign.getSign1());

				System.out.println("AOF Sign Request received for- "+investForm.getMobile());
				investForm.setCustomerSignature(customerSignature1);
				String result="";
				returnResponse= BseAOFGenerator.aofGenerator(investForm, investForm.getMobile(), env.getProperty("investment.bse.aoffile.logo"), "VERIFIED", env.getProperty(CommonConstants.BSE_AOF_GENERATION_FOLDR));
				logger.info("AOF Form generation status- "+ returnResponse);
				if(returnResponse.equalsIgnoreCase("SUCCESS")){
					logger.info("mfaofSignUploadRegisteredCustomer(): Signed AOF file generation complete for customer- "+ investForm.getPan1());
					result= bseEntryManager.upddateCustomerFormSignature(investForm.getMobile(), investForm.getPan1(), customerSignature1);
				}
				
				/*if(result.equalsIgnoreCase("SUCCESS")){
					session.setAttribute("PURCHASE_TYPE", "NEW_CUSTOMER");
					map.addAttribute("AOF", "COMPLETE");
				}*/
				else{
					
					logger.info("Failed to generate AOF form for cusotmer"+ investForm.getMobile());

				}
			}else{
				logger.info("mfaofSignUploadRegisteredCustomer(): Customer session not found. Rejecting request");
				returnResponse = "NO_SESSION";
			}
		}catch(NullPointerException ex){
			logger.error("Error reading data.",ex);
			returnResponse = "REQUEST_INVALID";
		}

		return returnResponse;

	}
	

	@RequestMapping(value = "/mutual-funds/uploadsignedaof", method = RequestMethod.GET)
	@ResponseBody
	public String uploadSignedAOFFile(HttpServletRequest request, HttpServletResponse response,HttpSession session) {

		logger.info("mf-signature-udpate post controller");
		String result = "SUCCESS";

		try{
			BseMFInvestForm investForm = (BseMFInvestForm) session.getAttribute("mfRegisterdUser");

			if(investForm.getMobile()!=""){
				String requestedMobile= request.getParameter("mobile");
				System.out.println("Get mobile no- "+ request.getParameter("mobdata"));
				if(requestedMobile.equalsIgnoreCase(investForm.getMobile())){
					//					returnUrl="SUCCESS";
					String clientCode = bseEntryManager.getClientIdfromMobile(requestedMobile);
					//call api to upload pdf


					System.out.println("Call API");
					BseAOFUploadResponse aofresp1= investmentConnectorBseInterface.uploadAOFForm(requestedMobile, env.getProperty(CommonConstants.BSE_AOF_GENERATION_FOLDR), clientCode);
					logger.info("AOF upload status as received- "+ aofresp1.getStatusMessage());
					if(aofresp1.getStatusCode().equalsIgnoreCase("100")){
						String updateStatus= bseEntryManager.uploadAOFFormStatus(investForm.getMobile(), "Y");
						logger.info("AOF upload status to database- "+ updateStatus);
					}else{
						result = aofresp1.getStatusMessage();
					}
				}else{
					logger.info("Mobile number do not match with holding session form data. Request rejected. Session mobile no:"+ investForm.getMobile() + " : Requested mobile: "+ requestedMobile);
					result="SESSION_MOB_MISMATCH";
				}

				//		String result= bseEntryManager.upddateCustomerFormSignature(investForm.getMobile(), investForm.getPan1(), investForm.getCustomerSignature());

			}else{
				result="REQUEST_DENIED";
			}
		}catch(Exception e){
			logger.error("Error with service", e);
			result="INTERNAL_ERROR";
		}
		return result;

	}


	@RequestMapping(value = "/mutual-funds/top-performing", method = RequestMethod.GET)
	public String getTopPerformingFunds(@RequestParam(name="info",required=false)String correctInfoAfterLogin,Model map, HttpServletRequest request, HttpServletResponse response, HttpSession session) {

		logger.info("MF Funds Inventory");
		//			List<MfTopFundsInventory> topFunds = null;
		List<BseMFTop15lsSip> topFunds = null;

		/*List<ProductSchemeDetail> allFunds = productSchemeDetailService.findForRegistryBirthDay();
		map.addAttribute("mffunds", allFunds);
		System.out.println("Funds size- "+ allFunds.size());
		for(int i=0;i<allFunds.size();i++){
			System.out.println(allFunds.get(i).getFundName());
		}

		 */
		//				System.out.println("URL- "+ request.getRequestURL()+ " : "+ request.getContextPath() + " : "+ request.getProtocol() + " : "+ request.getServerName());
		//				System.out.println(URI.create(request.getRequestURL().toString()).resolve(request.getContextPath()));
		if(correctInfoAfterLogin!=null){
			logger.info("Error code received for transaction - "+ correctInfoAfterLogin);
			if(correctInfoAfterLogin.equals("01")){
				map.addAttribute("error", "Provided details mismatch with registered details. Transaction aborted. Please try again.");
			}
		}
		if(session.getAttribute("topFunds")!=null){
			//				topFunds = (List<MfTopFundsInventory>) session.getAttribute("topFunds");
			topFunds = (List<BseMFTop15lsSip>) session.getAttribute("topFunds");
			logger.info("Serving top funds from session - "+ topFunds.size());
			map.addAttribute("FUNDSFOUND", "Y");
			map.addAttribute("topFunds", topFunds);
			System.out.println("Total funds");
		}else{
			logger.info("Get top funds from database");
			try{
				//					topFunds =  bseEntryManager.getTopMfFunds();
				topFunds =  bseEntryManager.getTopFunds();
				map.addAttribute("FUNDSFOUND", "Y");
				map.addAttribute("topFunds", topFunds);
				session.setAttribute("topFunds", topFunds);
				System.out.println("Total funds received- "+ topFunds.size());
			}catch(Exception e){
				logger.error("Error fetching Funds list. Please try after sometime");
				map.addAttribute("FUNDSFOUND", "N");
			}
		}
		SelectMFFund fundChoice = new SelectMFFund();
		if(session.getAttribute("token")!=null){
			fundChoice.setMobile(session.getAttribute("userid").toString());
			try{
				String panNumber = bseEntryManager.getCustomerPanfromMobile(fundChoice.getMobile());
				fundChoice.setPan(panNumber);
			}catch(Exception e){
				logger.error("Database connect issue: unable to fetch customer PAN number", e);
			}
		}
		map.addAttribute("selectFund", fundChoice);
		map.addAttribute("contextcdn", env.getProperty(CommonConstants.CDN_URL));
		String returnUrl = "bsemf/top-performing-funds";

		return returnUrl;

	}
	
	
	@RequestMapping(value = "/mutual-funds/funds-explorer", method = RequestMethod.GET)
	public String getAllFundsExplorer(Model map, HttpServletRequest request, HttpServletResponse response, HttpSession session) {

		logger.info("MF All Funds Inventory");
		
		String returnUrl = "bsemf/all-funds";
		
		PageRequest p =new PageRequest(0, 200);
		Pageable pg = p.first();
		
		Page<BseFundsScheme> b=  bseEntryManager.getpaginatedFundsList(pg);
		System.out.println("Paginated fundss- "+ b.getSize());
		System.out.println("Total pages- "+ b.getTotalPages());
		
		List<BseFundsScheme> funds = b.getContent();
		
		SelectMFFund fundChoice = new SelectMFFund();
		if(session.getAttribute("token")!=null){
			fundChoice.setMobile(session.getAttribute("userid").toString());
			try{
				String panNumber = bseEntryManager.getCustomerPanfromMobile(fundChoice.getMobile());
				fundChoice.setPan(panNumber);
			}catch(Exception e){
				logger.error("Database connect issue: unable to fetch customer PAN number", e);
			}
		}
		
		map.addAttribute("fundsexplorer", funds);
		map.addAttribute("selectFund", fundChoice);
		map.addAttribute("contextcdn", env.getProperty(CommonConstants.CDN_URL));

		return returnUrl;

	}

/*
	@RequestMapping(value = "/mutual-funds/view-order-history", method = RequestMethod.GET)
	public String viewOrderHistory( Model map, HttpServletRequest request, HttpServletResponse response, HttpSession session) {

		logger.info("MF Funds view purchase history");
		String returnUrl = "bsemf/order-history";
		if(session.getAttribute("token")!=null){
		String clientId= bseEntryManager.getClientIdfromMobile(session.getAttribute("userid").toString());
		List<SelectMFFund> orderHistory = bseEntryManager.getMFOrderHistory(clientId);

		map.addAttribute("contextcdn", env.getProperty(CommonConstants.CDN_URL));
		
		}else{
			returnUrl="redirect:/login";
		}

		return returnUrl;

	}
*/

	@RequestMapping(value = "/mutual-funds/purchase.do", method = RequestMethod.GET)
	public String purchasemfbseGet(@RequestParam("schemeCode")String schemeCode,@RequestParam("schemeName")String schemeName,@RequestParam("amcCode")String amcCode,@RequestParam("investype")String investype,@RequestParam(name="sipDate",required=false)String sipDate, @RequestParam("investAmount")String investAmount,@RequestParam("mobile")String mobile,@RequestParam("pan")String pan, Model map, HttpServletRequest request, HttpServletResponse response, HttpSession session,RedirectAttributes redirectAttrs) {
		
		logger.info("BSE MF STAR Purchse.do post controller");
		String returnUrl = "redirect:/mutual-funds/top-performing";

		SelectMFFund selectedFund = new SelectMFFund();
		/*if(bindResult.hasErrors()){
			map.addAttribute("error", bindResult.getFieldError().getDefaultMessage());
			return "bsemf/bse-form-new-customer";
		}*/
		
		selectedFund.setAmcCode(amcCode);
		selectedFund.setSchemeCode(schemeCode);
		selectedFund.setSchemeName(schemeName);
		selectedFund.setInvestype(investype);
		selectedFund.setInvestAmount(Double.valueOf(investAmount));
		selectedFund.setMobile(mobile);
		selectedFund.setPan(pan);
		
		
		try{
			//Check if existing BSE registered customer or not
			boolean flag = bseEntryManager.isExisitngCustomer(pan, mobile);
			logger.info("Is existing customer? - "+ pan+ " : "+ flag);
			session.setAttribute("selectFund", selectedFund);
			if(flag && session.getAttribute("token")==null){

				session.setAttribute("NEXT_URL", "/mutual-funds/purchase");
				redirectAttrs.addAttribute("ref", URLEncoder.encode(request.getRequestURL().toString(), StandardCharsets.UTF_8.toString()));
				returnUrl="redirect:/login?mf=00";		//Already existing customer, just login and fetch customer details
			}else if((flag && session.getAttribute("token")!=null)){

				//					If logged in account PAN and mobile do not match with provided PAN. Revert back to page which should pick up correct details from session now overriding 

				pan = bseEntryManager.getCustomerPanfromMobile(session.getAttribute("userid").toString());

				if(!session.getAttribute("userid").toString().equalsIgnoreCase(selectedFund.getMobile()) && ! pan.equalsIgnoreCase(selectedFund.getPan())){
					logger.warn("Customer logged in with another mobile than provided during form fillup. Also the PAN number is different. Redirecting back to fund selection page and override information with current details");
					logger.warn("Data provided during form fillup:[ "+ selectedFund.getPan() + " : "+ selectedFund.getMobile() + "] Data from session user:["+pan+ " : "+session.getAttribute("userid").toString()+"]");

					redirectAttrs.addFlashAttribute("USERINFO", "01");
					returnUrl = "redirect:/mutual-funds/top-performing";
				}else{
					returnUrl="redirect:/mutual-funds/purchase";
				}

			}else{
				//					Check if he is registered customer form LDAP. If already registered customer, then only need to register for MF, else create profile password as well

				RestClient client = new RestClient();
				ResponseEntity<String> responseProfile = null;
				try {
					responseProfile = client.isUserExisitng(selectedFund.getMobile());
					logger.info("Response for user existing check- "+ responseProfile.getBody());

				}catch(HttpStatusCodeException  e){
					logger.info("Failed to check user exisitng status - " + e.getStatusCode());
				} catch (JsonProcessingException e) {
				}catch(Exception e){
				}
				if(responseProfile.getBody().equalsIgnoreCase("Y")){
					
					if(session.getAttribute("token")!=null){
						returnUrl="redirect:/mutual-funds/register?mf=01";
					}else{
						returnUrl="redirect:/login?mf=01";		// User exist, so just need to register MF profile, do not create profile
					}

				}else if(responseProfile.getBody().equalsIgnoreCase("N")){
					returnUrl="redirect:/mutual-funds/register?mf=02";	// Complete fresh customer. Create both profile and register for MF
					session.setAttribute("PURCHASE_TYPE", "NEW_CUSTOMER");
				}else{
					logger.warn("Failed to get cutomer status from LDAP");
					returnUrl="redirect:/mutual-funds/register?mf=03";

				}
				redirectAttrs.addFlashAttribute("selectedFund", selectedFund);
				//					returnUrl="redirect:/mutual-funds/register";
			}
		}catch(Exception e){
			logger.error("Failed to check customer in databaes",e);

		}
		/*try{
		boolean flag = bseEntryManager.savetransactionDetails(selectedFund);
		logger.info("Customer purchase transaction status- "+ flag);
		}catch(Exception e){
			logger.error("Unable to save customer transaction request",e.getMessage());
		}*/

		return returnUrl;

		/*redirectAttrs.addFlashAttribute("selectedFund", selectedFund);
		returnUrl="redirect:/mutual-funds/register";
		return returnUrl;*/
		
		
	}
	
	
	@RequestMapping(value = "/mutual-funds/purchase.do", method = RequestMethod.POST)
	public String purchasemfbsePost(@ModelAttribute("selectFund") SelectMFFund selectedFund,BindingResult bindResult, Model map, HttpServletRequest request, HttpServletResponse response, HttpSession session,RedirectAttributes redirectAttrs) {

		logger.info("BSE MF STAR Purchse.do post controller");
		String returnUrl = "redirect:/mutual-funds/top-performing";


		if(bindResult.hasErrors()){
			map.addAttribute("error", bindResult.getFieldError().getDefaultMessage());
			return "bsemf/bse-form-new-customer";
		}

		try{
			//Check if existing BSE registered customer or not
			boolean flag = bseEntryManager.isExisitngCustomer(selectedFund.getPan(), selectedFund.getMobile());
			logger.info("Is existing customer? - "+ selectedFund.getPan()+ " : "+ flag);
			session.setAttribute("selectFund", selectedFund);
			if(flag && session.getAttribute("token")==null){

				session.setAttribute("NEXT_URL", "/mutual-funds/purchase");
				redirectAttrs.addAttribute("ref", URLEncoder.encode(request.getRequestURL().toString(), StandardCharsets.UTF_8.toString()));
				returnUrl="redirect:/login?mf=00";		//Already existing customer, just login and fetch customer details
			}else if((flag && session.getAttribute("token")!=null)){

				//					If logged in account PAN and mobile do not match with provided PAN. Revert back to page which should pick up correct details from session now overriding 

				String pan = bseEntryManager.getCustomerPanfromMobile(session.getAttribute("userid").toString());

				if(!session.getAttribute("userid").toString().equalsIgnoreCase(selectedFund.getMobile()) && ! pan.equalsIgnoreCase(selectedFund.getPan())){
					logger.warn("Customer logged in with another mobile than provided during form fillup. Also the PAN number is different. Redirecting back to fund selection page and override information with current details");
					logger.warn("Data provided during form fillup:[ "+ selectedFund.getPan() + " : "+ selectedFund.getMobile() + "] Data from session user:["+pan+ " : "+session.getAttribute("userid").toString()+"]");

					redirectAttrs.addFlashAttribute("USERINFO", "01");
					returnUrl = "redirect:/mutual-funds/top-performing";
				}else{
					returnUrl="redirect:/mutual-funds/purchase";
				}

			}else{
				//					Check if he is registered customer form LDAP. If already registered customer, then only need to register for MF, else create profile password as well

				RestClient client = new RestClient();
				ResponseEntity<String> responseProfile = null;
				try {
					responseProfile = client.isUserExisitng(selectedFund.getMobile());
					logger.info("Response for user existing check- "+ responseProfile.getBody());

				}catch(HttpStatusCodeException  e){
					logger.info("Failed to check user exisitng status - " + e.getStatusCode());
				} catch (JsonProcessingException e) {
				}catch(Exception e){
				}
				if(responseProfile.getBody().equalsIgnoreCase("Y")){
					
					if(session.getAttribute("token")!=null){
						returnUrl="redirect:/mutual-funds/register?mf=01";
					}else{
						returnUrl="redirect:/login?mf=01";		// User exist, so just need to register MF profile, do not create profile
					}

				}else if(responseProfile.getBody().equalsIgnoreCase("N")){
					returnUrl="redirect:/mutual-funds/register?mf=02";	// Complete fresh customer. Create both profile and register for MF
					session.setAttribute("PURCHASE_TYPE", "NEW_CUSTOMER");
				}else{
					logger.warn("Failed to get cutomer status from LDAP");
					returnUrl="redirect:/mutual-funds/register?mf=03";

				}
				redirectAttrs.addFlashAttribute("selectedFund", selectedFund);
				//					returnUrl="redirect:/mutual-funds/register";
			}
		}catch(Exception e){
			logger.error("Failed to check customer in databaes",e);

		}
		/*try{
		boolean flag = bseEntryManager.savetransactionDetails(selectedFund);
		logger.info("Customer purchase transaction status- "+ flag);
		}catch(Exception e){
			logger.error("Unable to save customer transaction request",e.getMessage());
		}*/

		return returnUrl;

		/*redirectAttrs.addFlashAttribute("selectedFund", selectedFund);
		returnUrl="redirect:/mutual-funds/register";
		return returnUrl;*/

	}


	@RequestMapping(value = "/mutual-funds/purchase", method = RequestMethod.GET)
	public String bseMfPurchaseAfterLogin(Model map, HttpServletRequest request, HttpServletResponse response, HttpSession session,RedirectAttributes redirectAttrs) {

		logger.info("BSE MF STAR Purhcase Get controller");
		String returnUrl = "bsemf/bse-mf-purchase";

		List<BseMFInvestForm> customerData = null;	
		SelectMFFund selectedFund = null;
		List<String> customerPortfolios = new ArrayList<String>();

		try{
			System.out.println((session.getAttribute("PURCHASE_TYPE")!=null));
			System.out.println("PURCHASE_TYPE -> "+ session.getAttribute("PURCHASE_TYPE"));
		}catch(Exception e){
			logger.error("Error reading purchase type customer",e);
		}

		try{
			selectedFund = (SelectMFFund) session.getAttribute("selectFund");
			if(selectedFund==null){
				logger.info("No selected funds details found is session. Returning to fund selectin page.");
				return "redirect:/mutual-funds/top-performing";
			}

			if(session.getAttribute("token")!=null){


				logger.info("Purchase order start for- "+ selectedFund.getSchemeName() + " : "+ selectedFund.getSchemeCode());

				String pan = bseEntryManager.getCustomerPanfromMobile(session.getAttribute("userid").toString());

				if(!session.getAttribute("userid").toString().equalsIgnoreCase(selectedFund.getMobile()) || ! pan.equalsIgnoreCase(selectedFund.getPan())){
					logger.warn("Customer logged in with another mobile than provided during form fillup. Also the PAN number is different. Redirecting back to fund selection page and override information with current details");
					logger.warn("Data provided during form fillup:[ "+ selectedFund.getPan() + " : "+ selectedFund.getMobile() + "] Data from session user:["+pan+ " : "+session.getAttribute("userid").toString()+"]");

					redirectAttrs.addFlashAttribute("USERINFO", "01");
					//					returnUrl = "redirect:/mutual-funds/top-performing";
					logger.info("Further processing prevented. Return");
					return "redirect:/mutual-funds/top-performing";
				}else{
					logger.info("Customer log in data matches with form data.");

					customerData =  bseEntryManager.getCustomerByPan(selectedFund.getPan());
					//				System.out.println("Data size returned- "+ customerData.size());
					//Find customer's portfolio
					logger.info("Search for customer portfolio for details: "+ selectedFund.getAmcCode() + " :PAN : "+ customerData.get(0).getClientID());
					customerPortfolios = bseEntryManager.getSelectedAmcPortfolio(selectedFund.getAmcCode(), customerData.get(0).getClientID());

					System.out.println("Portfolio size- "+ customerPortfolios.size());
					if(customerPortfolios.size()== 0){
						customerPortfolios.add("NEW");
					}else{
						selectedFund.setPortfolio(customerPortfolios.get(0));
					}
					/*map.addAttribute("amcPortFolio", customerPortfolios);

						map.addAttribute("customerData", customerData.get(0));
						map.addAttribute("GETDATA", "S");*/
				}



				/*try{
				//Find customer's portfolio
				logger.info("Search for customer portfolio for details: "+ selectedFund.getAmcCode() + " : "+ customerData.get(0).getClientID());
				List<String> customerPortfolios = bseEntryManager.getSelectedAmcPortfolio(selectedFund.getAmcCode(), customerData.get(0).getClientID());

				//Generate Transaction ID and check if already existing
				String transId = generateTransId();

				selectedFund.setTransactionID(transId);

				System.out.println("Portfolio size- "+ customerPortfolios.size());
				if(customerPortfolios.size()== 0){
					customerPortfolios.add("NEW");
				}else{
					selectedFund.setPortfolio(customerPortfolios.get(0));
				}
				map.addAttribute("amcPortFolio", customerPortfolios);
			}catch(Exception e){
				logger.error("Failed to get customer portfolio");
				e.printStackTrace();
			}
				 */

			}else if((session.getAttribute("PURCHASE_TYPE")!=null && session.getAttribute("PURCHASE_TYPE").toString().equalsIgnoreCase("NEW_CUSTOMER"))){
				customerPortfolios.add("NEW");
				customerData =  bseEntryManager.getCustomerByPan(selectedFund.getPan());


			}else{
				logger.info("MF purchase request required customer to be either logged in or be a fresh customer to access the url. Returning");
				//				returnUrl="redirect:/mutual-funds/top-performing";
				return "redirect:/mutual-funds/top-performing";
			}

			//Set SIP start date from 
			if(selectedFund.getInvestype().equalsIgnoreCase("SIP")){
				int initialMonth = LocalDate.now().getMonth().getValue();
				int initialYear = LocalDate.now().getYear();
				int currentday = LocalDate.now().getDayOfMonth();
				if(Integer.valueOf(selectedFund.getSipDate())>currentday){
					selectedFund.setSipStartMonth(initialMonth);
				}else{
					selectedFund.setSipStartMonth(initialMonth+1);
				}
				selectedFund.setSipStartYear(initialYear);
			}

			if(customerData.size()!=0){
				selectedFund.setClientID(customerData.get(0).getClientID());
				logger.info("Investor name- "+ customerData.get(0).getInvName());
				//						System.out.println("Bank details- "+ customerData.get(0).getBankDetails().getBankName());


				UserBankDetails userbankDetails = bseEntryManager.getCustomerBankDetails(customerData.get(0).getClientID());
				
				if(userbankDetails!=null){
					
					List<BseMandateDetails> mandate = bseEntryManager.getCustomerMandateDetails(customerData.get(0).getClientID(), userbankDetails.getAccountNumber());
					logger.info("Total emdandates fetched-  "+ mandate.size());
					if(mandate.size()>0 && mandate.get(0).isMandateComplete()){
					
						selectedFund.seteMandateRegRequired(false);
					}else{
						selectedFund.seteMandateRegRequired(true);
					}

					map.addAttribute("bankacc", "XXXXXXXXX"+userbankDetails.getAccountNumber().substring(userbankDetails.getAccountNumber().length()-3));
					map.addAttribute("bankname", userbankDetails.getBankName());
					map.addAttribute("isEmandateComplete", !selectedFund.iseMandateRegRequired());	//should to opposite to emandatecompelete staus
				}else{
					logger.info("Customer bank details not found. Check server log for details");
				}
			}else{
				logger.info("No customer detaails found for purchase!");
			}

			//Generate Transaction ID and check if already existing
			String transId = generateTransId();

			selectedFund.setTransactionID(transId);

			map.addAttribute("amcPortFolio", customerPortfolios);
			map.addAttribute("customerData", customerData.get(0));
			map.addAttribute("GETDATA", "S");

			map.addAttribute("paymentMethod", InvestFormConstants.bsePaymentMethod);
			map.addAttribute("calendarmonths", InvestFormConstants.bseInvestMonths);
			map.addAttribute("sipyear", InvestFormConstants.bseInvestStartYear);
			map.addAttribute("selectedFund", selectedFund);

		}catch(Exception e){
			logger.error("Unable to query database to fetch customer data- ",e);
			map.addAttribute("GETDATA", "F");
		}

		return returnUrl;

	}

	private String generateTransId() {
		boolean transIdExist=false;
		String transId =null;
		do{
			transId = BseRelatedActions.generateTransactionId();
			transIdExist = bseEntryManager.checkIfTransIdExist(transId);
		}while(transIdExist);
		return transId;
	}

	@RequestMapping(value = "/mutual-funds/mfPurchaseConfirm.do", method = RequestMethod.GET)
	public String bsePurchaseConfirmGet(HttpServletRequest request, HttpServletResponse response) {
		return "return:/products/";

	}
	@RequestMapping(value = "/mutual-funds/mfPurchaseConfirm.do", method = RequestMethod.POST)
	public String bsePurchaseConfirmPost(@ModelAttribute("selectedFund") SelectMFFund selectedFund,BindingResult bindResult, Model map, HttpServletRequest request, HttpServletResponse response, HttpSession session,final RedirectAttributes redirectAttrs) {

		logger.info("@@ BSE MF STAR purchase confirm controller @@");
		String returnUrl = "redirect:/mutual-funds/bse-transaction-status";
		System.out.println("Client ID - "+ selectedFund.getClientID());
		System.out.println("Pay first install? "+ selectedFund.isPayFirstInstallment());
		TransactionStatus flag =null;
		
		

		//		set sip date if chosen
		//			boolean f = Integer.valueOf(selectedFund.getSipStartMonth())<10;
		if(selectedFund.getInvestype().equalsIgnoreCase("SIP")){
			String combineDate = (selectedFund.getSipDate().length()==1?"0"+selectedFund.getSipDate():selectedFund.getSipDate())+"/" +((Integer.valueOf(selectedFund.getSipStartMonth())<10)?"0"+Integer.toString(Integer.valueOf(selectedFund.getSipStartMonth())):Integer.toString(Integer.valueOf(selectedFund.getSipStartMonth())))+"/"+selectedFund.getSipStartYear();
			//			System.out.println(combineDate);
			//			validate date if prioor to today 	-todo

			try {
				selectedFund.setSipStartDate((new SimpleDateFormat("dd/MM/yyyy")).parse(combineDate));
			} catch (ParseException e) {
				logger.error("Failed to convert date to required format for SIP.",e);
			}
			selectedFund.setNoOfInstallments(60);		//Default SIP installments to 5 years -- todo dynamic
		}

		try{
			flag = bseEntryManager.savetransactionDetails(selectedFund);
			logger.info("Customer purchase transaction status- "+ flag.getSuccessFlag());


			if(flag.getSuccessFlag()!=null && flag.getSuccessFlag().equalsIgnoreCase("S")){
				
//				Trigger transaction mailer
				BseMFInvestForm userDetails = bseEntryManager.getCustomerInvestFormData(session.getAttribute("userid").toString());
				mailSenderHandler.mfpurchasenotofication(selectedFund, userDetails);
				
				redirectAttrs.addAttribute("TRANS_STATUS", "Y");
				redirectAttrs.addFlashAttribute("TRANS_ID", selectedFund.getTransactionID());
				redirectAttrs.addFlashAttribute("TRANS_MSG", flag.getStatusMsg());

				flag.setTransactionReference(selectedFund.getTransactionID());
				flag.setInvestmentType(selectedFund.getInvestype());
				flag.setFundName(selectedFund.getSchemeName());


//				Register customer bank account for e-mandate if not carried out already
				System.out.println("Emandate confirm- "+ selectedFund.iseMandateRegRequired());

				if(selectedFund.getInvestype().equalsIgnoreCase("SIP")){
					if(selectedFund.iseMandateRegRequired()){
						logger.info("Customer emandate registration need to be processed");
						flag.setEmandateRequired(true);
						BseApiResponse emandateResponse = bseEntryManager.updateEmdandateStatus(selectedFund.getMobile(), Double.toString(selectedFund.getInvestAmount()));
						if(emandateResponse!=null){
							if(emandateResponse.getStatusCode().equals("100")){
								redirectAttrs.addFlashAttribute("EMANDATE_STATUS", "S");
							}else{
								redirectAttrs.addFlashAttribute("EMANDATE_STATUS", "F");
							}
						}
						redirectAttrs.addFlashAttribute("EMANDATE_REMARKS", emandateResponse.getRemarks());
						flag.setEmandateStatusCode(emandateResponse.getStatusCode());
						flag.setEmandateRegisterRemark(emandateResponse.getRemarks());
					}else{
						logger.info("Emandate not required. Skipping the request.");
						redirectAttrs.addFlashAttribute("EMANDATE_STATUS", "NA");
					}
				}else{
					logger.info("Transaction type is lumpsum. Skip emandate requirement");
				}

				if(selectedFund.getInvestype().equalsIgnoreCase("LUMPSUM")){
					redirectAttrs.addFlashAttribute("FIRST_PAY", "Y");
				}else if(selectedFund.getInvestype().equalsIgnoreCase("SIP")){
					if(selectedFund.isPayFirstInstallment()){
						redirectAttrs.addFlashAttribute("FIRST_PAY", "Y");
					}else{
						redirectAttrs.addFlashAttribute("FIRST_PAY", "N");
					}
				}

			}else if(flag.getSuccessFlag()!=null && flag.getSuccessFlag().equalsIgnoreCase("F")){
				redirectAttrs.addAttribute("TRANS_STATUS", "N");
				redirectAttrs.addFlashAttribute("TRANS_MSG", flag.getStatusMsg());
			}else{
				redirectAttrs.addAttribute("TRANS_STATUS", "SF");
				redirectAttrs.addFlashAttribute("TRANS_MSG", flag.getStatusMsg());
			}

		}catch(Exception e){

			logger.error("Unable to save customer transaction request",e);
			redirectAttrs.addAttribute("TRANS_STATUS", "N");
		}
		redirectAttrs.addFlashAttribute("TRANS_TYPE", selectedFund.getTransactionType());
		redirectAttrs.addFlashAttribute("CLIENT_CODE", selectedFund.getClientID());	
		redirectAttrs.addFlashAttribute("TRANSACTION_REPORT_BEAN", flag);	
		return returnUrl;

	}

	@RequestMapping(value = "/my-dashboard/additional-purchase", method = RequestMethod.GET)
	public String bsemfFundsPurchaseModeGet(@RequestParam("p") String purchasedata,@ModelAttribute("TRANS_ID") String transId,Model map, HttpServletRequest request, HttpServletResponse response, HttpSession session) {

		logger.info("@@ BSE MF STAR purchase confirm controller @@");
		String returnUrl = "bsemf/bsemf-additional-purchase";

		List<String> decodedString= Arrays.asList(Base64Coder.decodeString(purchasedata).toString().split("\\|"));
		System.out.println(decodedString);

		MFAdditionalPurchaseForm purchaseForm = new MFAdditionalPurchaseForm();
		if(session.getAttribute("userid").toString()!=null || session.getAttribute("token").toString()!=null){
			if(purchasedata!=null){

				try{
					String portfolio =decodedString.get(0)!=null?decodedString.get(0):"NA";
					String schemeCode = decodedString.get(1)!=null?decodedString.get(1):"NA";
					String investType = decodedString.get(2)!=null?decodedString.get(2):"SIP";
					BseAllTransactionsView bseSeletedFundDetails= bseEntryManager.getFundDetailsForAdditionalPurchase(portfolio, schemeCode,investType, session.getAttribute("userid").toString());
					purchaseForm.setPortfolio(bseSeletedFundDetails.getPortfoilio());
					purchaseForm.setFundName(bseSeletedFundDetails.getSchemeName());
					purchaseForm.setSchemeCode(bseSeletedFundDetails.getSchemeCode());
					purchaseForm.setInvestType(bseSeletedFundDetails.getInvestType()!=null?bseSeletedFundDetails.getInvestType():"LUMPSUM");
					purchaseForm.setTotalAvailableAmount(bseSeletedFundDetails.getSchemeInvestment());
					String transactionId = generateTransId();
					logger.info("Generated transaction ID of initiated transaction for additional purhcase-  "+ transactionId);
					purchaseForm.setPurchaseTransid(transactionId);

				}catch(Exception e){
					logger.error("Failed to fetch selected funds's transaction details",e);
					map.addAttribute("error", "Failed to fetch the curret investment details");
				}
			}else{
				logger.warn("Purchase details missing. Redirecting to my dashboard");
				returnUrl="redirect:/my-dashboard";
			}
		}else{
			logger.warn("User session not found to carry out additional purcahse. Redirecting to login.");
			returnUrl="redirect:/login";
		}

		/*MFAdditionalPurchaseForm purchaseForm = new MFAdditionalPurchaseForm();
		purchaseForm.setPortfolio(portfolio);
		purchaseForm.setSchemeCode(schemeCode);
		purchaseForm.setInvestType(investType.toUpperCase());*/



		map.addAttribute("purchaseForm", purchaseForm);
		map.addAttribute("data", purchasedata);
		map.addAttribute("contextcdn", env.getProperty(CommonConstants.CDN_URL));
		return returnUrl;
	}

	@RequestMapping(value = "/mutual-funds/mfInvestAdditionalPurchase.do", method = RequestMethod.GET)
	public String bseAdditionalPurchaseGet(HttpServletRequest request, HttpServletResponse response) {
		return "redirect:/";
	}

	@RequestMapping(value = "/mutual-funds/mfInvestAdditionalPurchase.do", method = RequestMethod.POST)
	public String bseAdditionalPurchasePost(@ModelAttribute("purchaseForm") @Valid MFAdditionalPurchaseForm purchaseForm,BindingResult bindResult, Model map, HttpServletRequest request, HttpServletResponse response, HttpSession session,final RedirectAttributes redirectAttrs) {

		logger.info("@@ BSE MF STAR purchase confirm do controller @@");
		String returnUrl = "redirect:/mutual-funds/bse-transaction-status";
		System.out.println("Purchase initiated against Folio no - "+ purchaseForm.getPortfolio());
		String clientId = "";
		System.out.println("Is cutout policy agreed for purchase?- "+ purchaseForm.isAgreePolicy());

		if(bindResult.hasErrors()){
			logger.error("Error processing redeem request",bindResult.getFieldError().getDefaultMessage());
			map.addAttribute("error", bindResult.getFieldError().getDefaultMessage());
			return "bsemf/bsemf-additional-purchase";
		}
		if(!purchaseForm.isAgreePolicy()){
			logger.warn("Policy not agreed for transaction.");
			map.addAttribute("error", "Please agree to the policy for transaction.");
			return "bsemf/bsemf-additional-purchase";
		}

		if(session.getAttribute("purchaseForm")!=null){

			SelectMFFund fundTransaction = new SelectMFFund();
			try{
				clientId= bseEntryManager.getClientIdfromMobile(session.getAttribute("userid").toString());
				System.out.println("Client id - "+ clientId);
				fundTransaction.setClientID(clientId);
				fundTransaction.setPortfolio(purchaseForm.getPortfolio());
				fundTransaction.setSchemeCode(purchaseForm.getSchemeCode());
				fundTransaction.setTransactionID(purchaseForm.getPurchaseTransid());
				fundTransaction.setInvestype(fundTransaction.getInvestype());
				fundTransaction.setTransactionType("PURCHASE");
				fundTransaction.setInvestAmount(purchaseForm.getPurchaseAmounts());
				fundTransaction.setPaymentMethod(purchaseForm.getPaymentMode());

				TransactionStatus flag = bseEntryManager.savetransactionDetails(fundTransaction);
				logger.info("Customer purchase transaction status- "+ flag.getSuccessFlag());		//todo
				redirectAttrs.addAttribute("TRANS_STATUS", "Y");
				redirectAttrs.addAttribute("TRANS_TYPE", "ADDITIONAL");
				redirectAttrs.addFlashAttribute("TRANS_ID", purchaseForm.getPurchaseTransid());
			}catch(Exception e){

				logger.error("Unable to save customer transaction request for additional purchase",e);
				//			redirectAttrs.addAttribute("TRANS_STATUS", "N");
				map.addAttribute("error", "Failed to save your request for additional purchase. Please try again.");
				returnUrl="bsemf/bsemf-additional-purchase";
			}
		}else{
			logger.warn("Fund session not found for execution. Redirecting out");
			returnUrl ="redirect:/products/";
		}
		redirectAttrs.addFlashAttribute("CLIENT_CODE", clientId);	

		return returnUrl;

	}


	@RequestMapping(value = "/my-dashboard/funds-redeem", method = RequestMethod.GET)
	public String bsemfFundsRedeemGet(@RequestParam("r") String purchasedata,@ModelAttribute("TRANS_STATUS") String transStatus,@ModelAttribute("TRANS_ID") String transId,Model map, HttpServletRequest request, HttpServletResponse response, HttpSession session) {

		logger.info("@@ BSE MF STAR purchase confirm controller @@");
		String returnUrl = "bsemf/bsemf-redeem";

		List<String> decodedString= Arrays.asList(Base64Coder.decodeString(purchasedata).toString().split("\\|"));
		System.out.println(decodedString);

		MFRedeemForm redeemForm = new MFRedeemForm();

		try{
			String portfolio =decodedString.get(0)!=null?decodedString.get(0):"NA";
			String schemeCode = decodedString.get(1)!=null?decodedString.get(1):"NA";
			String investType = decodedString.get(2)!=null?decodedString.get(2):"NA";
			BseAllTransactionsView bseSeletedFundDetails= bseEntryManager.getFundDetailsForRedemption(portfolio, schemeCode,investType, session.getAttribute("userid").toString());

			if(bseSeletedFundDetails.getSchemeInvestment()<=0){
				map.addAttribute("FUNDAVAILABLE", "N");
				map.addAttribute("error", "No fund value to redeem. Please select appropriate fund for redemption.");
			}else{
				map.addAttribute("FUNDAVAILABLE", "Y");
				redeemForm.setPortfolio(bseSeletedFundDetails.getPortfoilio());
				redeemForm.setFundName(bseSeletedFundDetails.getSchemeName());
				redeemForm.setSchemeCode(bseSeletedFundDetails.getSchemeCode());
				redeemForm.setInvestType(bseSeletedFundDetails.getInvestType()!=null?bseSeletedFundDetails.getInvestType():"NA");
				redeemForm.setTotalValue(bseSeletedFundDetails.getSchemeInvestment());
				redeemForm.setRedeemAmounts(bseSeletedFundDetails.getSchemeInvestment());
				String transactionId = generateTransId();
				logger.info("Generated transaction ID of initiated transaction for additional purhcase-  "+ transactionId);
				redeemForm.setRedeemTransId(transactionId);
			}

		}catch(Exception e){
			logger.error("Failed to fetch selected funds's transaction details",e);
			map.addAttribute("error", "Failed to fetch the curret investment details");
		}

		map.addAttribute("mfRedeemForm", redeemForm);
		map.addAttribute("contextcdn", env.getProperty(CommonConstants.CDN_URL));
		return returnUrl;
	}

	@RequestMapping(value = "/mutual-funds/mfInvestRedeem.do", method = RequestMethod.GET)
	public String bseRedeemFundGet(HttpServletRequest request, HttpServletResponse response) {
		return "redirect:/";
	}

	@RequestMapping(value = "/mutual-funds/mfInvestRedeem.do", method = RequestMethod.POST)
	public String bseRedeemFundPost(@ModelAttribute("mfRedeemForm") @Valid MFRedeemForm redeemForm,BindingResult bindResult, Model map, HttpServletRequest request, HttpServletResponse response, HttpSession session,final RedirectAttributes redirectAttrs) {

		logger.info("@@ BSE MF STAR redeem process do controller @@");
		String returnUrl = "redirect:/mutual-funds/bse-transaction-status";
		System.out.println("Purchase initiated against Folio no - "+ redeemForm.getPortfolio());

		System.out.println("Is policy agreed?- "+ redeemForm.isAgreePolicy());


		if(bindResult.hasErrors()){
			logger.error("Error processing redeem request",bindResult.getFieldError());
			map.addAttribute("FUNDAVAILABLE", "Y");
			map.addAttribute("error", bindResult.getFieldError().getDefaultMessage());
			return "bsemf/bsemf-redeem";
		}
		if(!redeemForm.isAgreePolicy()){
			logger.warn("Policy not agreed for transaction.");
			map.addAttribute("error", "Please agree to the policy for transaction.");
			map.addAttribute("FUNDAVAILABLE", "Y");
			return "bsemf/bsemf-redeem";
		}

		if(session.getAttribute("mfRedeemForm")!=null){

			SelectMFFund fundTransaction = new SelectMFFund();
			try{
				String clientId= bseEntryManager.getClientIdfromMobile(session.getAttribute("userid").toString());
				System.out.println("Client id - "+ clientId);
				fundTransaction.setClientID(clientId);
				fundTransaction.setPortfolio(redeemForm.getPortfolio());
				fundTransaction.setSchemeCode(redeemForm.getSchemeCode());
				fundTransaction.setTransactionID(redeemForm.getRedeemTransId());
				fundTransaction.setInvestype(redeemForm.getInvestType());
				fundTransaction.setTransactionType("REDEEM");
				logger.info("Redemption amount selected- "+ redeemForm.getRedeemAmounts()* (-1));
				fundTransaction.setInvestAmount(redeemForm.getRedeemAmounts() * (-1));

				TransactionStatus flag = bseEntryManager.savetransactionDetails(fundTransaction);

				if(flag.getSuccessFlag().equalsIgnoreCase("S")){
					redirectAttrs.addFlashAttribute("CLIENT_CODE", fundTransaction.getClientID());						
				}
				logger.info("Customer purchase transaction status- "+ flag.getSuccessFlag());
				redirectAttrs.addAttribute("TRANS_STATUS", "Y");
				//				redirectAttrs.addAttribute("TRANS_TYPE", "REDEEM");				--todo

				redirectAttrs.addFlashAttribute("TRANS_ID", redeemForm.getRedeemTransId());

			}catch(Exception e){

				logger.error("Unable to save customer transaction request for additional purchase",e);
				map.addAttribute("FUNDAVAILABLE", "Y");
				map.addAttribute("error", "Failed to save your request for additional purchase. Please try again.");
				returnUrl="bsemf/bsemf-redeem";
			}
		}else{
			logger.warn("Redeem form data session not found. Redirecting out of transaction");
			returnUrl="redirect:/products/";
		}

		return returnUrl;

	}



	@RequestMapping(value = "/mutual-funds/bse-transaction-complete", method = RequestMethod.GET)
	public String bseMFTransactionCallback(@RequestParam("orderid")String orderid,Model map, HttpServletRequest request, HttpServletResponse response, HttpSession session) {

		logger.info("@@ BSE MF STAR purchase confirm controller after callback @@");
		String returnUrl = "bsemf/bse-purchase-status";
		//			System.out.println("Data- "+ clientId + " " + orderNo);
		if(session.getAttribute("token")==null){
			logger.info("No session found for requesting user. Invalidating request");
			returnUrl="redirect:/login";
		}else{
			if(orderid.equalsIgnoreCase("")){
				logger.info("Parameters data not found");
				returnUrl="redirect:/login";
			}else{
				String getClientId = bseEntryManager.getClientIdfromMobile(session.getAttribute("userid").toString());
				String resp= investmentConnectorBseInterface.BseOrderPaymentStatus(getClientId, orderid);

				List<String> res = Arrays.asList(resp.split("\\|"));
				/*if(res.get(0).equals("100")){

				}else{

				}*/
				
//				Clear session
				session.removeAttribute("selectedFund");
//				session.removeAttribute("purchaseForm");
				
//				session.invalidate();
				
				map.addAttribute("TRANS_STATUS", "COMPLETE");
				map.addAttribute("ORDER_STATUS", res.get(1));
			}
		}


		//		map.addAttribute("TRANS_ID",transId);
		logger.info("Checking order payment status after redirected from BSE callback url for orderid- "+ orderid);

		return returnUrl;
	}

	@RequestMapping(value = "/mutual-funds/bse-transaction-status", method = RequestMethod.GET)
	public String bseMFTransactionStatus(@ModelAttribute("TRANSACTION_REPORT_BEAN")TransactionStatus transReport,@ModelAttribute("TRANS_TYPE") String transyType,@ModelAttribute("FIRST_PAY") String firstPayRequire,@ModelAttribute("TRANS_STATUS") String transStatus,@ModelAttribute("CLIENT_CODE") String clienCode,@ModelAttribute("TRANS_ID") String transId,@ModelAttribute("TRANS_MSG")String transMessage,@ModelAttribute("EMANDATE_STATUS")String emandateStatus,@ModelAttribute("EMANDATE_REMARKS") String mandateRemarks, Model map, HttpServletRequest request, HttpServletResponse response, HttpSession session) {

		logger.info("@@ BSE MF STAR purchase Transaction status controller @@");
		String returnUrl = "bsemf/bse-purchase-status";

		if(clienCode.isEmpty()){
			returnUrl = "redirect:/mutual-funds/top-performing";
		}
		else{
			if(transStatus.equalsIgnoreCase("Y") || transStatus.equalsIgnoreCase("SF")){
				logger.info("Proceeding to generate pay url for order id - "+ transReport.getBseOrderNoFromResponse());
				BseOrderPaymentRequest orderUrl = new BseOrderPaymentRequest();
				orderUrl.setClientCode(clienCode);
				orderUrl.setMemberCode(CommonConstants.BSE_MEMBER_ID);
				//					orderUrl.setLogOutURL("http://localhost:8080/products/mutual-funds/bse-transaction-complete");
				String callbackUrl=URI.create(request.getRequestURL().toString()).resolve(request.getContextPath()).toString() + "/mutual-funds/bse-transaction-complete?orderid="+(!transReport.getBseOrderNoFromResponse().isEmpty()?transReport.getBseOrderNoFromResponse():"NA");
				logger.info("Callback url for payment- "+callbackUrl);
				orderUrl.setLogOutURL(callbackUrl);
				BseOrderPaymentResponse orderUrlReponse= investmentConnectorBseInterface.getPaymentUrl(orderUrl);
				map.addAttribute("orderUrl", orderUrlReponse);
			}
			logger.info("Emdanate status in case of SIP -"+ emandateStatus);
			map.addAttribute("TRANS_STATUS", transStatus);
			map.addAttribute("TRANS_ID",transId);
			map.addAttribute("MSG", transMessage);
			map.addAttribute("EMANDATE", emandateStatus);
			map.addAttribute("MANDATE_REMARKS", mandateRemarks);
			map.addAttribute("TRANS_TYPE", transyType);
			map.addAttribute("FIRST_PAY", firstPayRequire);
			map.addAttribute("TRANSACTION_REPORT", transReport);
		}
		return returnUrl;
	}

	@RequestMapping(value = "/mutual-funds/pending-payments", method = RequestMethod.GET)
	public String bseMFTransactionStatus(Model map, HttpServletRequest request, HttpServletResponse response, HttpSession session) {

		logger.info("@@ BSE MF STAR purchase confirm controller @@");
		String returnUrl = "refirect:/mutual-funds/my-dashboard";

		if(session.getAttribute("userid").toString()!=null || session.getAttribute("token").toString()!=null){
			String clientId= bseEntryManager.getClientIdfromMobile(session.getAttribute("userid").toString());
			BseOrderPaymentRequest orderUrl = new BseOrderPaymentRequest();
			orderUrl.setClientCode(clientId);
			orderUrl.setMemberCode(CommonConstants.BSE_MEMBER_ID);
			//				orderUrl.setLogOutURL("http://localhost:8080/products/mutual-funds/my-dashboard");
			orderUrl.setLogOutURL(URI.create(request.getRequestURL().toString()).resolve(request.getContextPath()).toString() + "/mutual-funds/my-dashboard");

			BseOrderPaymentResponse orderUrlReponse= investmentConnectorBseInterface.getPaymentUrl(orderUrl);
			map.addAttribute("orderUrl", orderUrlReponse);
			returnUrl="redirect:"+orderUrlReponse.getPayUrl();
		}
		else{

			returnUrl = "redirect:/login";

			/*map.addAttribute("TRANS_STATUS", transStatus);
			map.addAttribute("TRANS_ID",transId);*/
		}
		return returnUrl;
	}


	@RequestMapping(value = "/mutual-funds/view-purchase-history", method = RequestMethod.GET)
	public String bseMFViewpurchaseHistory(Model map, HttpServletRequest request, HttpServletResponse response, HttpSession session) {

		logger.info("@@ BSE MF STAR purchase confirm controller @@");
		String returnUrl = "bsemf/bsemf-purchase-history2";

		try{
			if(session.getAttribute("userid").toString()!=null || session.getAttribute("token").toString()!=null){
				String clientId= bseEntryManager.getClientIdfromMobile(session.getAttribute("userid").toString());
				if(!clientId.isEmpty()){
					List<BsemfTransactionHistory> purchaseHistoryList= bseEntryManager.getAllPurchaseHistory(clientId);
					if(purchaseHistoryList!=null){
						//						map.addAttribute("PURCHASE_LIST", "SUCCESS");
						if(purchaseHistoryList.size()>=1){
							map.addAttribute("PURCHASE_LIST", "SUCCESS");
							map.addAttribute("PURCHASE_ORDERS", purchaseHistoryList);
						}else{
							map.addAttribute("PURCHASE_LIST", "NONE");
						}

					}else{
						map.addAttribute("PURCHASE_LIST", "ERROR");
					}
				}else{
					logger.info("Customer client Id not found");
					map.addAttribute("PURCHASE_LIST", "ID_NOT_FOUND");
				}

			}
			else{
				returnUrl = "redirect:/login";
				/*map.addAttribute("TRANS_STATUS", transStatus);
			map.addAttribute("TRANS_ID",transId);*/
			}
		}catch(NullPointerException e){
			returnUrl = "redirect:/login";
		}
		return returnUrl;
	}


	@RequestMapping(value = "/mutual-funds/orderpaymentStatus", method = RequestMethod.GET)
	@ResponseBody
	public String getPurchasepaymentStatus(@RequestParam(name="client",required=false)String clientId, @RequestParam(name="order",required=false)String orderNo,Model map, HttpServletRequest request, HttpServletResponse response, HttpSession session) {

		logger.info("Order Payment status requested- ");
		String returnFlag ="FAIL";

		System.out.println("Data- "+ clientId + " " + orderNo);
		if(session.getAttribute("token")==null){
			logger.info("No session found for requesting user. Invalidating request");
			returnFlag="NO_SESSIION";
		}else{
			if(clientId==null || orderNo == null){
				logger.info("Parameters data not found");
				returnFlag="INVALID_REQUEST";
			}else{



				String resp= investmentConnectorBseInterface.BseOrderPaymentStatus(clientId, orderNo);

				List<String> res = Arrays.asList(resp.split("\\|"));
				/*if(res.get(0).equals("100")){

				}else{

				}*/
				returnFlag = res.get(1);
			}
		}
		
		map.addAttribute("contextcdn", env.getProperty(CommonConstants.CDN_URL));

		return returnFlag;
	}
	
	@RequestMapping(value = "/profile/fatca-declaration", method = RequestMethod.GET)
	public String bseFatcaDeclaration(@ModelAttribute("fatcaform") BseFatcaForm fatcaForm,Model map, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		logger.info("@@ BSE MF STAR FATCA FORM GET controller @@");
		String returnUrl = "bsemf/bsemf-fatca";
		
		map.addAttribute("fatcaform", fatcaForm);
		map.addAttribute("contextcdn", env.getProperty(CommonConstants.CDN_URL));
		return returnUrl;
	}


	@RequestMapping("/download/aof/{fileName:.+}")
	public void downloadPDFResource( HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable("fileName") String fileName,/*@ModelAttribute("mfInvestForm") BseMFInvestForm investForm,*/ @RequestHeader String referer,HttpSession session)	
	{
		System.out.println("File download");
		//If user is not authorized - he should be thrown out from here itself
		if(referer != null && !referer.isEmpty()) {
			//do nothing
			//or send error
		} 


		//Authorized user will download the file
		/*String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/downloads/pdf/");*/



		String dataDirectory = env.getProperty(CommonConstants.BSE_AOF_GENERATION_FOLDR);
		//		String dataDirectory = "E:\\AOF";
		Path file = Paths.get(dataDirectory, fileName);
		System.out.println(file.toString());
		String flag = "SUCCESS";

		if(!Files.exists(file)){

			BseMFInvestForm investForm =(BseMFInvestForm) session.getAttribute("mfRegisterdUser"); 	
			if(investForm!=null){
				BseAOFGenerator.aofGenerator(investForm,fileName, env.getProperty("investment.bse.aoffile.logo"), "VERIFIED", dataDirectory);

			}else{
				logger.info("No session data found to generate file!");
			}
		}

		if (Files.exists(file))
		{
			response.setContentType("application/pdf");
			//				response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition", "attachment; filename="+fileName);
			try
			{
				Files.copy(file, response.getOutputStream());
				response.getOutputStream().flush();
				System.out.println("Send response");
			}
			catch (IOException ex) {
				ex.printStackTrace();
			}
		}else{
			//			System.out.println("File not found");
			logger.info("No file exists or coud be generated!");

		}



	}


	@ModelAttribute("taxStatus")
	public Map<String, String> getTaxStatus() {
		Map<String, String> taxStatus = new HashMap<String,String>();
		taxStatus.put("01", "INDIVIDUAL");
		taxStatus.put("03", "HUF");
		taxStatus.put("04", "COMPANY");
		taxStatus.put("06", "PARTNERSHIP FIRM");
		taxStatus.put("07", "BODY CORPORATE");
		taxStatus.put("08", "TRUST");
		taxStatus.put("09", "SOCIETY");
		taxStatus.put("13", "SOLE PROPRIETORSHIP");
		return taxStatus;
	}


	/*@ModelAttribute("holingNature")
	public Map<String, String> mfHoldingNature() {

		// To keep the order as entered below
		Map<String, String> holdingNature = new LinkedHashMap<String,String>();
		holdingNature.put("SI", "Single");
		holdingNature.put("JO", "Joint");
		holdingNature.put("AS", "Anyone or Survivor");
		

		return holdingNature;
	}*/
	/*
	public static void main(String[] main){
		Long h = UUID.randomUUID().getMostSignificantBits();
		System.out.println(h);
		System.out.println(Math.abs(h));
	}*/

	/*public static void main(String[] args){
		System.out.println(Encryptors.text("9051472645", "12").encrypt("asdasd"));

		System.out.println(DigestUtils.md5Hex("sdfsdf"));
		System.out.println(Base64Coder.encodeString("sdfsdf"));
		System.out.println(Base64Coder.decodeString("c2Rmc2Rm"));


	}*/

	/*	private static byte[] hexStringToByteArray(String hex) {
        Pattern replace = Pattern.compile("^0x");
        String s = replace.matcher(hex).replaceAll("");

        byte[] b = new byte[s.length() / 2];
        for (int i = 0; i < b.length; i++) {
            int index = i * 2;
            int v = Integer.parseInt(s.substring(index, index + 2), 16);
            b[i] = (byte) v;
        }
        return b;
    }*/

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

		logger.error("Exception was reveived for missing parameters.",ex);
		String returnUrl ="redirect:/";
		logger.info(name + " missing");
		// Actual exception handling
		if(name.equals("selectedFund")){
			returnUrl = "redirect:/mutual-funds/top-performing";
		}
		ModelAndView view = new ModelAndView(returnUrl);
		return view;
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ModelAndView illegalArgumentException(IllegalArgumentException ex, HttpServletRequest request) {

		logger.error("illegalArgumentException was received for missing parameters.- "+ request.getRequestURI(), ex);
		String returnUrl ="redirect:/";
		// Actual exception handling
		returnUrl = "redirect:/mutual-funds/top-performing";
		ModelAndView view = new ModelAndView(returnUrl);
		return view;
	}

	/*@ExceptionHandler(ResourceNotFoundException.class)
		@ResponseStatus(value=HttpStatus.NOT_FOUND)
		public String pageNotFound(HttpServletRequest request, Exception ex){
			logger.info("Controlleradvice- page not found"+ request.getRequestURI());
			return "pagenotfound";
		}*/


}
