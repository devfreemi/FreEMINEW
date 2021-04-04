package com.freemi.ui.controller;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.freemi.common.util.CommonConstants;
import com.freemi.common.util.CommonTask;
import com.freemi.database.interfaces.ProductSchemeDetailService;
import com.freemi.entity.database.FSecure;
import com.freemi.entity.database.FreemiLoanForm;
import com.freemi.entity.database.FreemiLoanQuery;
import com.freemi.entity.database.ProductSchemeDetail;
import com.freemi.entity.general.AadhaarOTP;
import com.freemi.entity.general.ClientSystemDetails;
import com.freemi.entity.general.HttpClientResponse;
import com.freemi.entity.general.Registerform;
import com.freemi.entity.general.RegistryChildEducation;
import com.freemi.entity.general.RegistryOfficeExpense;
import com.freemi.entity.general.RegistryRetirement;
import com.freemi.entity.general.RegistryTravel;
import com.freemi.entity.investment.MFInvestForm;
import com.freemi.entity.investment.MFInvestmentDates;
import com.freemi.entity.investment.RegistryFunds;
import com.freemi.entity.investment.RegistryWish;
import com.freemi.services.interfaces.EKYCValidation;
import com.freemi.services.interfaces.FreemiServiceInterface;
import com.freemi.services.interfaces.ProfileRestClientService;

@Controller
@Scope("session")
@SessionAttributes({"mfInvestForm"})
public class ProductsController {
	private static final Logger logger = LogManager.getLogger(ProductsController.class);

	private static String WISH_DATE_FORMAT = "yyyy-MM-dd";

	@Autowired
	ProductSchemeDetailService productSchemeDetailService;

	@Autowired
	FreemiServiceInterface freemiServiceInterface; 
	
	@Autowired
	ProfileRestClientService profileRestClientService;

	@Autowired
	private Environment env;
	
	@Autowired
	EKYCValidation ekycValidation;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerUserGet(Model map, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		//logger.info("@@@@ Inside Login..");
		logger.info(request.getRequestURI() + " -"+ request.getSession().getId());
		
		Registerform register = new Registerform();
		register.setSessionid(session.getId());
		
		if(session.getAttribute("token") == null){
		    map.addAttribute("registerForm", register);
		return "register";
		}else{
			logger.info("User session is already detected. Preventing another attempt of register.");
			return "redirect:"+URI.create(request.getRequestURL().toString()).resolve(request.getContextPath()).toString();
		}
	}

	@RequestMapping(value = "/register.do", method = RequestMethod.GET)
	public String registerUserDoGet(ModelMap model) {
		return "redirect:/register";
	}

	@RequestMapping(value = "/register.do", method = RequestMethod.POST)
	public String registerUserPost(@ModelAttribute("registerForm") @Valid Registerform registerForm, BindingResult bindingResult, ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		logger.info("@@@@ Inside Register do registerUser().. " + request.getHeader("user-agent") + " - " + request.getSession().getId());
		
		registerForm.setOtp(null);
		if(bindingResult.hasErrors()){
			logger.info("REGISTRATION FORM VALIDATION ERROR : user -  "+ registerForm.getMobile() + " : "+ registerForm.getEmail() +" : Error -" + bindingResult.getFieldError().getDefaultMessage());
			model.addAttribute("message", bindingResult.getFieldError().getDefaultMessage());
			model.addAttribute("registerForm", new Registerform());
			return "register";
		}
		
		
		if(!ekycValidation.mobilenoverifiedduringregistration(registerForm.getSessionid(), registerForm.getMobile(), "REGISTRATION")) {
			logger.info("Mobile no not verified...");
			model.addAttribute("message", "Mobile no. verification required.");
			registerForm.setOtpverified("N");
			model.addAttribute("registerForm", registerForm);
			return "register";
		}
		
		
		try {
		registerForm.setFullName(registerForm.getFname() + " "+ registerForm.getLname());
		registerForm.setRegistrationref("DIRECT");
		
		HttpClientResponse httpResponse =  profileRestClientService.registerUser(registerForm,CommonTask.getClientSystemDetails(request));
		logger.info("REGISTRATION STATUS : "+ registerForm.getMobile() + " : "+ httpResponse.getRetrunMessage() );
		
		if(httpResponse.getResponseCode() == CommonConstants.HTTP_CLIENT_CALL_SUCCESS) {
			model.addAttribute("message",httpResponse.getRetrunMessage());
			model.addAttribute("registerForm", new Registerform());
		}else {
			model.addAttribute("message", httpResponse.getRetrunMessage());
			model.addAttribute("registerForm", registerForm);
		}
		}catch(Exception e) {
		    logger.error("REGISTRATION ERROR - Error processing registration request.",e);
		    model.addAttribute("message","Failed to prrocess. Kindly try again.");
		    model.addAttribute("registerForm", registerForm);
		}
		
		return "register";
	}


	@RequestMapping(value = "/freemi", method = RequestMethod.GET)
	public String freemiReplaced(ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		return "redirect:/loans";
	}

	@RequestMapping(value = "/registry", method = RequestMethod.GET)
	public String registryReplaced(ModelMap map, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
//		return "redirect:/registry-mutual-funds";
	    
	   try{
	List<ProductSchemeDetail> productlist = productSchemeDetailService.findForRegistryBirthDay();
	List<ProductSchemeDetail> taxsavingSchemelist = productSchemeDetailService.findForTaxSaving();
	List<ProductSchemeDetail> travelSchemelist = productSchemeDetailService.findForTravel();
	
	List<ProductSchemeDetail> birthDaytlist = new ArrayList<ProductSchemeDetail>();
	List<ProductSchemeDetail> annivesarylist = new ArrayList<ProductSchemeDetail>();
	List<ProductSchemeDetail> taxsavinglist = new ArrayList<ProductSchemeDetail>();
	if(productlist.size() > 4) {
		birthDaytlist = new ArrayList<ProductSchemeDetail>(productlist.subList(0, 4));
	}else {
		birthDaytlist = new ArrayList<ProductSchemeDetail>(productlist.subList(0, productlist.size()));
	}
	
	if(taxsavingSchemelist.size() > 4) {
		taxsavinglist = new ArrayList<ProductSchemeDetail>(taxsavingSchemelist.subList(0, 4));
	}else {
		taxsavinglist = new ArrayList<ProductSchemeDetail>(taxsavingSchemelist.subList(0, taxsavingSchemelist.size()));
	}
	
	if(travelSchemelist.size() > 4) {
		annivesarylist = new ArrayList<ProductSchemeDetail>(travelSchemelist.subList(0, 4));
	}else {
		annivesarylist = new ArrayList<ProductSchemeDetail>(travelSchemelist.subList(0, travelSchemelist.size()));
	}
	map.addAttribute("DATA", "Y");
	map.addAttribute("productlist", birthDaytlist);
	map.addAttribute("annivesarylist", annivesarylist);
	map.addAttribute("taxsavinglist", taxsavinglist);
	
	}catch(Exception e){
	logger.error("RegistryController - Failed to get data",e);
	map.addAttribute("DATA","N");
	map.addAttribute("error", "Failed to get funds. Please try again");
	}
	
	logger.info("@@@@ RegistryController Data Load Comleted @@@@");

//	return "registry/registry-home";
	return "registry3";
	    
	}


	@RequestMapping(value = "/fsecure", method = RequestMethod.GET)
	public String fsecureReplaced(ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		return "redirect:/fsecure-insurance";
	}

	
//	Temporary down
	/*@RequestMapping(value = "/registry-mutual-funds", method = RequestMethod.GET)
	public String registryMutualReplaced(ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		return "redirect:/registry-mutual-funds/";
	}


	@RequestMapping(value = "/registry-mutual-funds/", method = RequestMethod.GET)
	public String registryDisplay(Model map) {
		logger.info("@@@@ Registry mutual funds @@@@");

		try{
			List<ProductSchemeDetail> productlist = productSchemeDetailService.findForRegistryBirthDay();
			List<ProductSchemeDetail> taxsavingSchemelist = productSchemeDetailService.findForTaxSaving();
			List<ProductSchemeDetail> travelSchemelist = productSchemeDetailService.findForTravel();

			List<ProductSchemeDetail> birthDaytlist = new ArrayList<ProductSchemeDetail>();
			List<ProductSchemeDetail> annivesarylist = new ArrayList<ProductSchemeDetail>();
			List<ProductSchemeDetail> taxsavinglist = new ArrayList<ProductSchemeDetail>();
			if(productlist.size() > 4) {
				birthDaytlist = new ArrayList<ProductSchemeDetail>(productlist.subList(0, 4));
			}else {
				birthDaytlist = new ArrayList<ProductSchemeDetail>(productlist.subList(0, productlist.size()));
			}

			if(taxsavingSchemelist.size() > 4) {
				taxsavinglist = new ArrayList<ProductSchemeDetail>(taxsavingSchemelist.subList(0, 4));
			}else {
				taxsavinglist = new ArrayList<ProductSchemeDetail>(taxsavingSchemelist.subList(0, taxsavingSchemelist.size()));
			}

			if(travelSchemelist.size() > 4) {
				annivesarylist = new ArrayList<ProductSchemeDetail>(travelSchemelist.subList(0, 4));
			}else {
				annivesarylist = new ArrayList<ProductSchemeDetail>(travelSchemelist.subList(0, travelSchemelist.size()));
			}
			map.addAttribute("DATA", "Y");
			map.addAttribute("productlist", birthDaytlist);
			map.addAttribute("annivesarylist", annivesarylist);
			map.addAttribute("taxsavinglist", taxsavinglist);

		}catch(Exception e){
			logger.error("RegistryController - Failed to get data",e);
			map.addAttribute("DATA","N");
			map.addAttribute("error", "Failed to get funds. Please try again");
		}
		
		logger.info("@@@@ RegistryController Data Load Comleted @@@@");

		map.addAttribute("contextcdn", env.getProperty(CommonConstants.CDN_URL));
		return "registry3";
	}*/
	
	@RequestMapping(value = {"/registry-mutual-funds","/registry-mutual-funds/"}, method = RequestMethod.GET)
	public String registryMutualReplaced(ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		return "redirect:/mutual-funds/funds-explorer";
		
//		return "registry3";
	}



	@RequestMapping(value = "/loans", method = RequestMethod.GET)
	public String freemiDisplay(Model map, HttpServletRequest request, HttpServletResponse response, HttpSession session) {

		FreemiLoanQuery personalLoan = new FreemiLoanQuery();
		FreemiLoanQuery homeLoan = new FreemiLoanQuery();
		FreemiLoanQuery creditCard = new FreemiLoanQuery();
		personalLoan.setRequestCategory("PL");
		homeLoan.setRequestCategory("HL");
		creditCard.setRequestCategory("CC");

		map.addAttribute("personalLoan", personalLoan);
		map.addAttribute("homeLoan", homeLoan);
		map.addAttribute("creditCard", creditCard);

		logger.info("@@@@ FreemiController @@@@");
		return "loans";
	}

	@RequestMapping(value = "/freemi.do", method = RequestMethod.GET)
	public String freemiDoPost(ModelMap model) {
		//logger.info("@@@@ Inside Login..");
		return "redirect:/loans";
	}

	@RequestMapping(value = "/freemi.do", method = RequestMethod.POST)
	public String freemiSubmit(ModelMap model) {
		//logger.info("@@@@ Inside Login..");
		model.addAttribute("freemiLoanForm", new FreemiLoanForm());
		logger.info("@@@@ FreemiDoController @@@@");
		return "loans";
	}

	@RequestMapping(value = "/personalLoan.do", method = RequestMethod.POST)
	public String personalLoanQuerySubmit(@ModelAttribute("personalLoan") FreemiLoanQuery personalLoan,Model model,HttpServletRequest request, HttpServletResponse response, RedirectAttributes rAttr) {
		logger.info("@@@@ PersonalLoadController @@@@");
		logger.info(personalLoan.getMobile());


		ClientSystemDetails system= CommonTask.getClientSystemDetails(request);

		personalLoan.setRequestTime(new Date());
		personalLoan.setSystemIp(system.getClientIpv4Address());
		personalLoan.setBrowserClient(system.getClientBrowser());

		logger.info(personalLoan.getDndAgree());

		if(freemiServiceInterface.savePersonalLoanQuery(personalLoan)){
			model.addAttribute("REQUESTSUCCESS","Y");
			model.addAttribute("requestmessage", "Thank you. Our personal finance executive will contact you to process your requirements.");

			rAttr.addFlashAttribute("REQUESTSUCCESS","Y");
			rAttr.addFlashAttribute("requestmessage", "Thank you. Our personal finance executive will contact you to process your requirements.");
		}else{
			model.addAttribute("REQUESTSUCCESS","N");

			rAttr.addFlashAttribute("REQUESTSUCCESS","N");
			logger.info("Unable to save the pl request. Check logs");
		}
		return "redirect:/loans/loan-request-processed";
	}

	@RequestMapping(value = "/homeLoan.do", method = RequestMethod.POST)
	public String homeLoanQuerySubmit(@ModelAttribute("homeLoan") FreemiLoanQuery homeLoan,Model model,HttpServletRequest request, HttpServletResponse response, RedirectAttributes rAttr) {
		logger.info("@@@@ PersonalLoadController @@@@");

		ClientSystemDetails system= CommonTask.getClientSystemDetails(request);
		logger.info("Home loan request received for - "+ homeLoan.getMobile());
		homeLoan.setRequestTime(new Date());
		homeLoan.setSystemIp(system.getClientIpv4Address());
		homeLoan.setBrowserClient(system.getClientBrowser());
		if(homeLoan.getMobile()!=null){
			if(freemiServiceInterface.saveHomeLoanQuery(homeLoan)){
				model.addAttribute("REQUESTSUCCESS","Y");
				model.addAttribute("requestmessage", "Thank you. Our personal finance executive will contact you to process your requirements.");

				rAttr.addFlashAttribute("REQUESTSUCCESS","Y");
				rAttr.addFlashAttribute("requestmessage", "Thank you. Our personal finance executive will contact you to process your requirements.");
			}else{
				//			session.setAttribute("hlsaved", "N");
				model.addAttribute("REQUESTSUCCESS","N");

				rAttr.addFlashAttribute("REQUESTSUCCESS","N");
				logger.info("Unable to save the hl request. Check logs");
			}
		}else{
			logger.info("Home loan data missing;");
		}
		//		return "redirect:/freemi-loan";
		return "redirect:/loans/loan-request-processed";
	}

	@RequestMapping(value = "/creditCard.do", method = RequestMethod.POST)
	public String creditCardQuerySubmit(@ModelAttribute("creditCard") FreemiLoanQuery creditCard,Model model,HttpServletRequest request, HttpServletResponse response, RedirectAttributes rAttr) {
		logger.info("@@@@ PersonalLoadController @@@@");
		//		logger.info(creditCard.getMobile());

		ClientSystemDetails system= CommonTask.getClientSystemDetails(request);

		creditCard.setRequestTime(new Date());
		creditCard.setSystemIp(system.getClientIpv4Address());
		creditCard.setBrowserClient(system.getClientBrowser());

		if(freemiServiceInterface.saveCreditCardLoanQuery(creditCard)){
			model.addAttribute("REQUESTSUCCESS","Y");
			model.addAttribute("requestmessage", "Thank you. Our personal finance executive will contact you to process your requirements.");

			rAttr.addFlashAttribute("REQUESTSUCCESS","Y");
			rAttr.addFlashAttribute("requestmessage", "Thank you. Our personal finance executive will contact you to process your requirements.");
		}else{
			model.addAttribute("REQUESTSUCCESS","N");
			rAttr.addFlashAttribute("REQUESTSUCCESS","N");
			logger.info("Unable to save the cccard request. Check logs");
		}
		//		return "redirect:/freemi-loan";
		return "redirect:/loans/loan-request-processed";
	}

	@RequestMapping(value = "/loans/loan-request-processed", method = RequestMethod.GET)
	public ModelAndView LoanRequestProcessedGet(@ModelAttribute("REQUESTSUCCESS")String requestStatus,@ModelAttribute("requestmessage")String requestmessage,Model map, HttpServletRequest request, HttpServletResponse response) {
		//		String returnUrl="loan-request-success";
		ModelAndView view = new ModelAndView("loan-request-success");

		if(requestStatus.isEmpty()){
			//			logger.info("Emplty");
			//			returnUrl="redirect:/loans";
			view.setViewName("redirect:/loans");
			map.asMap().clear();

		}else{
			logger.info("Status of loan request -" + requestStatus);
			map.addAttribute("REQUESTSUCCESS",requestStatus);
			map.addAttribute("requestmessage",requestmessage);
		}
		//		return returnUrl;
		return view;
	}

	@RequestMapping(value = "/personalLoan.do", method = RequestMethod.GET)
	public String personalLoanGet(HttpServletRequest request, HttpServletResponse response) {
		return "redirect:/loans";
	}
	@RequestMapping(value = "/homeLoan.do", method = RequestMethod.GET)
	public String homeLoanGet(HttpServletRequest request, HttpServletResponse response) {
		return "redirect:/loans";
	}
	@RequestMapping(value = "/creditCard.do", method = RequestMethod.GET)
	public String credirCardGet(HttpServletRequest request, HttpServletResponse response) {
		return "redirect:/loans";
	}

	@RequestMapping(value = "/fsecure-insurance", method = RequestMethod.GET)
	public String fsecureDisplay(Model map) {
		//logger.info("@@@@ Inside Login..");
		map.addAttribute("fsecureForm", new FSecure());
		logger.info("@@@@ FsecureController @@@@");
		return "fsecure-insurance";
	}

	@RequestMapping(value = "/fsecure.do", method = RequestMethod.GET)
	public String fsecureDoDisplay(ModelMap model) {
		return "redirect:/fsecure-insurance";
	}

	@RequestMapping(value = "/fsecure.do", method = RequestMethod.POST)
	public String fseucreSubmit(@ModelAttribute("fsecureForm") FSecure fsecureForm,ModelMap model, HttpServletRequest request) {
		//logger.info("@@@@ Inside Login..");
		//		logger.info(fsecureForm.getDob());

		fsecureForm.setProductCode("2001");
//		RestClient client = new RestClient();
		ResponseEntity<String> response = null;
		try {
			response = profileRestClientService.fsecureRequest(fsecureForm,CommonTask.getClientSystemDetails(request).getClientIpv4Address());
			//			logger.info(response.getBody());
			//			logger.info(response.getHeaders());
			//			model.addAttribute("success", response.getBody());
			//			model.addAttribute("contactForm", new ContactUsForm());
			logger.info(response.getHeaders());
			logger.info(response.getHeaders().get("STATUS").get(0));
		}catch(HttpStatusCodeException  e){
			logger.info("test failure - " + e.getStatusCode());
			//			model.addAttribute("error", "Unable to process request curretnly");
		} catch (JsonProcessingException e) {
			//			model.addAttribute("error","Invalid form data");
		}catch(Exception e){
			//			model.addAttribute("error","Error processing request");
		}



		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-d");
		LocalDate d = LocalDate.parse("2000-06-25" , format);

		//		model.addAttribute("fsecureForm", new FSecure());

		logger.info("@@@@ FsecureDoController @@@@");
		//        return "fsecure";
		return "redirect:https://onlineinsurance.hdfclife.com/ocp?sumAssured=&premium=&freq=&state=&city=&prodcd=C2P3DPER&option=&pptOption=&source=P_FREEMI_C2P3DP&agentcode=00702816&term=&ppt=&topupFlag=N&ADBPercentage=&topupPercentage=&lumpsum=&monthlyIncome=&incomeTerm=&increaseIncomePercentage=&jrnyFlow=Choose&ocpflag=N"
		+"&fname="+fsecureForm.getFname()
		+"&lname="+fsecureForm.getLname()
		+"&email="+fsecureForm.getEmail()
		+"&gender="+fsecureForm.getGender()
		+"&mobno="+fsecureForm.getMobile()
		+"&dob="+d.getDayOfMonth()+"/"+String.format("%02d" , d.getMonthValue())+"/"+d.getYear()
		+"&tobstatus="+fsecureForm.getIsSmoker()
		+"&checkboxdisclaimer="+fsecureForm.getAllowcalls()
		;
	}
	/*
	@RequestMapping(value = "/registry-mutual-funds", method = RequestMethod.GET)
	public String registryDisplay(ModelMap model) {
		//logger.info("@@@@ Inside Login..");
		//		model.addAttribute("fsecureForm", new FSecure());
		logger.info("@@@@ RegistryController @@@@");
		List<ProductSchemeDetail> productlist = productSchemeDetailService.findForRegistryBirthDay();
		List<ProductSchemeDetail> taxsavingSchemelist = productSchemeDetailService.findForTaxSaving();
		List<ProductSchemeDetail> travelSchemelist = productSchemeDetailService.findForTravel();

		List<ProductSchemeDetail> birthDaytlist = new ArrayList<ProductSchemeDetail>();
		List<ProductSchemeDetail> annivesarylist = new ArrayList<ProductSchemeDetail>();
		List<ProductSchemeDetail> taxsavinglist = new ArrayList<ProductSchemeDetail>();
		if(productlist.size() > 4) {
			birthDaytlist = new ArrayList<ProductSchemeDetail>(productlist.subList(0, 4));
		}else {
			birthDaytlist = new ArrayList<ProductSchemeDetail>(productlist.subList(0, productlist.size()));
		}

		if(taxsavingSchemelist.size() > 4) {
			taxsavinglist = new ArrayList<ProductSchemeDetail>(taxsavingSchemelist.subList(0, 4));
		}else {
			taxsavinglist = new ArrayList<ProductSchemeDetail>(taxsavingSchemelist.subList(0, taxsavingSchemelist.size()));
		}

		if(travelSchemelist.size() > 4) {
			annivesarylist = new ArrayList<ProductSchemeDetail>(travelSchemelist.subList(0, 4));
		}else {
			annivesarylist = new ArrayList<ProductSchemeDetail>(travelSchemelist.subList(0, travelSchemelist.size()));
		}


		model.addAttribute("productlist", birthDaytlist);
		model.addAttribute("annivesarylist", annivesarylist);
		model.addAttribute("taxsavinglist", taxsavinglist);
		return "registry-mutual-funds";
	}
	 */
	
	

	@RequestMapping(value = "/registry-mutual-funds/registry-wish", method = RequestMethod.GET)
	public String registryWishDisplay(@RequestParam(value="category") String category, @RequestParam(value="type") String type, @RequestParam(value="schemeId") Long schemeId, Model model) {
		//logger.info("@@@@ Inside Login..");
		String event_based_image = "";
		RegistryWish registryWish = new RegistryWish(); 
		logger.info("@@@@ scheme="+schemeId);

		if(type.equals("marriage")){
			event_based_image="registry_marriage.png";
		}else if(type.equals("parties")){
			event_based_image="registry_parties.png";
		}else if(type.equals("anniversary")){
			event_based_image="registry_anniversary.png";
		}else{
			event_based_image= "registry_birthday.png";
		}

		Map<String, String> personType = new HashMap<String, String>();
		if(category.equals("family")){
			personType.put("daughter","Daughter");
			personType.put("son","Son");
			personType.put("others","Others");
		}
		else if(category.equals("friends")){
			personType.put("his","His");
			personType.put("her","Her");
			personType.put("others","Others");
		}
		else if(category.equals("office")){
			personType.put("colleagues","Colleagues");
		}else{
			personType.put("others","Others");
		}

		model.addAttribute("event_based_image",event_based_image);
		model.addAttribute("category", category);
		model.addAttribute("type", type);
		model.addAttribute("personType", personType);

		registryWish.setWishType(type);
		registryWish.setSchemeId(schemeId);
		model.addAttribute("registryWishForm", registryWish);
		model.addAttribute("schemeId", schemeId);
		
		logger.info("@@@@ RegistryWishController :: schemeId"+schemeId);
		return "registry-wish";
	}

	/*	@RequestMapping(value = "/registry-mutual-funds/registry-wish", method = RequestMethod.GET)
	public String registryWishDisplay(@RequestParam(value="category") String category, @RequestParam(value="type") String type,  Model model) {
		//logger.info("@@@@ Inside Login..");
		String event_based_image = "";
		RegistryWish registryWish = new RegistryWish(); 
//		logger.info("@@@@ scheme="+schemeId);

		if(type.equals("marriage")){
			event_based_image="registry_marriage.png";
		}else if(type.equals("parties")){
			event_based_image="registry_parties.png";
		}else if(type.equals("anniversary")){
			event_based_image="registry_anniversary.png";
		}else{
			event_based_image= "registry_birthday.png";
		}

		Map<String, String> personType = new HashMap<String, String>();
		if(category.equals("family")){
			personType.put("daughter","Daughter");
			personType.put("son","Son");
			personType.put("others","Others");
		}
		else if(category.equals("friends")){
			personType.put("his","His");
			personType.put("her","Her");
			personType.put("others","Others");
		}
		else if(category.equals("office")){
			personType.put("colleagues","Colleagues");
		}else{
			personType.put("others","Others");
		}

		model.addAttribute("event_based_image",event_based_image);
		model.addAttribute("category", category);
		model.addAttribute("type", type);
		model.addAttribute("personType", personType);

		registryWish.setWishType(type);
		registryWish.setSchemeId(113L);
		model.addAttribute("registryWishForm", registryWish);
		model.addAttribute("schemeId", 113L);
		logger.info("@@@@ RegistryWishController :: schemeId"+113L);

		return "registry-wish";
	}
	 */



	@RequestMapping(value = "/registry-mutual-funds/registryWish.do", method = RequestMethod.GET)
	public String registryWishSubmitGet(@RequestParam(value="schemeId") String schemeId, Model model) {
		logger.info("@@@@ Inside registryWishSubmitGet scheme="+schemeId);
		return "redirect:/registry-mutual-funds";
	}




	/*f*/

	@RequestMapping(value = "/registry-mutual-funds/registryWish.do", method = RequestMethod.POST)
	public String registryWishSubmit(@ModelAttribute("type") String type, @ModelAttribute("registryWishForm") RegistryWish registryWish, RegistryFunds chooseFund, ModelMap model) {

		String returnUrl="";
		if(registryWish == null){
			returnUrl = "registry-wish";
		}else{
			logger.info("type- "+ type);
			logger.info("wish type from form- "+ registryWish.getWishType());
			model.addAttribute("type", registryWish.getWishType() );
			logger.info("SchemeId= "+ registryWish.getSchemeId());
			ProductSchemeDetail pdetails = productSchemeDetailService.getById(registryWish.getSchemeId());
			RegistryFunds funds = new RegistryFunds();

			ArrayList<RegistryFunds> fundsList = new ArrayList<RegistryFunds>();
			funds.setFundName("Aditya Birla Sun Life Income Fund - Growth - Regular Plan");
			funds.setSchemeCode("301G");
			funds.setSchemeOption("Z");
			funds.setFundRating(2);
			funds.setFundReturn("7.904");
			funds.setReturnOneMonth("0.71");
			funds.setReturnThreeMonths("1.776");
			funds.setReturnSixMonths("3.397");
			funds.setReturnThreeMonths("6.763");
			funds.setMonthlySavings("2000");
			fundsList.add(funds);

			funds = new RegistryFunds();

			funds.setFundName("Aditya Birla Sun Life Equity Savings Fund - Gr. REGULAR");
			funds.setSchemeCode("249B");
			funds.setSchemeOption("Z");
			funds.setFundRating(2);
			funds.setFundReturn("4.904");
			funds.setReturnOneMonth("0.71");
			funds.setReturnThreeMonths("1.776");
			funds.setReturnSixMonths("3.397");
			funds.setReturnThreeMonths("6.763");
			funds.setMonthlySavings("3000");
			fundsList.add(funds);

			model.addAttribute("fundsList", fundsList);
			model.addAttribute("fundSelect", chooseFund);

			logger.info("@@@@ RegistryWishDoController @@@@");
			returnUrl = "registry-funds";
		}
		return returnUrl;
	}

	@RequestMapping(value = "/registry-mutual-funds/registryFunds.do", method = RequestMethod.POST)
	public String getRegistryFundsSubmit( @ModelAttribute("registryWishForm") RegistryWish registryWish,@ModelAttribute("fundSelect") RegistryFunds chosenFund, MFInvestForm mfInvestForm, ModelMap model) {
		logger.info("@@@@ registryFunds.doPostController @@@@");

		ProductSchemeDetail pdetails = productSchemeDetailService.getById(registryWish.getSchemeId());
		RegistryFunds funds = new RegistryFunds();
		funds.setFundName(pdetails.getSchemeName());
		funds.setSchemeCode(pdetails.getSchemeCode());
		funds.setSchemeOption(pdetails.getSchemeOption());
		funds.setFundRating(2);
		funds.setFundReturn(""+pdetails.getPReturn_1Y());
		funds.setReturnOneMonth(""+pdetails.getPReturn_1M());
		funds.setReturnThreeMonths(""+pdetails.getPReturn_3M());
		funds.setReturnSixMonths(""+pdetails.getPReturn_6M());
		//funds.setReturnThreeMonths("6.763");
		funds.setMonthlySavings(Integer.toString(registryWish.getAmount()));
		funds.setInvestType(registryWish.getInvestType());

		//fundsList.add(funds);

		mfInvestForm.setSelectedFund(funds);
		mfInvestForm.setInvestmentType(registryWish.getInvestType());

		try{
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(WISH_DATE_FORMAT);

			LocalDate wishDate = LocalDate.parse(registryWish.getDate(), formatter);

			System.out.println(registryWish.getDate());
			System.out.println(wishDate.getDayOfMonth());
			System.out.println(wishDate.getMonthValue());
			System.out.println(wishDate.getYear());
			MFInvestmentDates wishDates = new MFInvestmentDates();
			wishDates.setInvFrequency(Integer.toString(registryWish.getTenure()));
			wishDates.setSipPeriodToMonth(Integer.toString(wishDate.getMonthValue()));
			wishDates.setSipPeriodToYear(Integer.toString(wishDate.getYear()));
			mfInvestForm.setMfInvestDates(wishDates);
		}catch(Exception e){
			logger.error("Unable to parse date- ", e.getMessage());
		}

		logger.info("Selected- "+ funds.getFundRating());
		model.addAttribute("mfInvestForm", mfInvestForm);
		model.addAttribute("aadhaarotp", new AadhaarOTP());
		model.addAttribute("showAadhaar", false);
		
		return "mfekyc";
	}



	/*@RequestMapping(value = "/registry-mutual-funds/registryFunds.do/{fundName}", method = RequestMethod.GET)
	public String registryFundsSubmit(@ModelAttribute("fundSelect") RegistryFunds registryFunds,@PathVariable("fundName") String fundName ,MFInvestForm mfInvestForm, ModelMap model) {
		//logger.info("@@@@ Inside Login..");

		logger.info("@@@@ registryFunds.doGetController @@@@");
		logger.info(fundName);
		mfInvestForm.setSelectedFund(registryFunds);
		logger.info("Selected- "+ registryFunds.getFundName());
		model.addAttribute("mfInvestForm", mfInvestForm);
		model.addAttribute("aadhaarotp", new AadhaarOTP());
		model.addAttribute("showAadhaar", false);
		return "mfekyc";
	}
	 */

	/*	@RequestMapping(value = "/registry-mutual-funds/registryFunds.do", method = RequestMethod.POST)
	public String registryFundsSubmit(@ModelAttribute("fundSelect") RegistryFunds registryFunds,MFInvestForm mfInvestForm, ModelMap model) {
		//logger.info("@@@@ Inside Login..");

		logger.info("@@@@ registryFunds.doGetController @@@@");
//		logger.info(fundName);
		mfInvestForm.setSelectedFund(registryFunds);
		logger.info("Selected- "+ registryFunds.getFundName());
		model.addAttribute("mfInvestForm", mfInvestForm);
		model.addAttribute("aadhaarotp", new AadhaarOTP());
		model.addAttribute("showAadhaar", false);
		return "mfekyc";
	}
	 */



	@RequestMapping(value = "/registry-mutual-funds/registry-retirement", method = RequestMethod.GET)
	public String registryRetirementGet(Model model) {
		logger.info("RegistryRetirementGetController");
		model.addAttribute("retirementForm", new RegistryRetirement());

		return "registry-retirement";
	}

	@RequestMapping(value = "/registry-mutual-funds/registry-retirement.do", method = RequestMethod.GET)
	public String registryRetirementDoGet(Model model) {
		logger.info("RegistryRetirementDoGetController");

		return "redirect:/registry-mutual-funds/registry-retirement";
	}

	@RequestMapping(value = "/registry-mutual-funds/registry-retirement.do", method = RequestMethod.POST)
	public String registryRetirementDoget(@ModelAttribute("retirementForm") RegistryRetirement retirementForm,Model model) {
		logger.info("RegistryRetirementDoPostController");
		return "registry-retirement";
	}



	@RequestMapping(value = "/registry-mutual-funds/registry-child-education", method = RequestMethod.GET)
	public String registryChildEducationGet(Model model) {
		logger.info("RegistryChildEducationGetController");
		model.addAttribute("childEducationForm", new RegistryChildEducation());

		return "registry-child-education";
	}

	@RequestMapping(value = "/registry-mutual-funds/registry-child-education.do", method = RequestMethod.GET)
	public String registryChildEducationDoGet(Model model) {
		logger.info("RegistryChildEducationDoGetController");

		return "redirect:/registry-mutual-funds/registry-child-education";
	}

	@RequestMapping(value = "/registry-mutual-funds/registry-child-education.do", method = RequestMethod.POST)
	public String registryChildEducation(@ModelAttribute("childEducationForm") RegistryChildEducation childEducationForm,Model model) {
		logger.info("RegistryChildEducationDoPostController");

		return "registry-child-education";
	}

	@RequestMapping(value = "/registry-mutual-funds/registry-travel", method = RequestMethod.GET)
	public String registryTravelGet(Model model) {
		logger.info("RegistryTravelGetController");
		model.addAttribute("travelForm", new RegistryTravel());

		return "registry-travel";
	}

	@RequestMapping(value = "/registry-mutual-funds/registry-travel.do", method = RequestMethod.GET)
	public String registryTravelDoGet(Model model) {
		logger.info("RegistryTravelDoGetController");

		return "redirect:/registry-mutual-funds/registry-travel";
	}
	@RequestMapping(value = "/registry-mutual-funds/registry-travel.do", method = RequestMethod.POST)
	public String registryTravelDoPost(@ModelAttribute("travelForm") RegistryTravel travelForm,Model model) {
		logger.info("RegistryTravelDoPostController");

		return "registry-travel";
	}

	@RequestMapping(value = "/registry-mutual-funds/office-expense", method = RequestMethod.GET)
	public String OfficeExpenseGet(Model model) {
		logger.info("OfficeExpenseGetController");
		model.addAttribute("officeExpenseForm", new RegistryOfficeExpense());

		return "registry-office-expense";
	}
	@RequestMapping(value = "/registry-mutual-funds/office-expense.do", method = RequestMethod.GET)
	public String OfficeExpenseDoGet(Model model) {
		logger.info("OfficeExpenseDoGetController");

		return "redirect:/registry-mutual-funds/registry-office-expense";
	}
	@RequestMapping(value = "/registry-mutual-funds/office-expense.do", method = RequestMethod.POST)
	public String OfficeExpenseDoPost(@ModelAttribute("officeExpenseForm") RegistryOfficeExpense officeExpenseForm,Model model) {
		logger.info("OfficeExpenseDoPostController");

		return "registry-office-expense";

	}



	@RequestMapping(value = "/signupcamp", method = RequestMethod.GET)
	public String SignupCampGet(Model model) {
		logger.info("OfficeExpenseDoGetController");

		return "campaign/signup";
	}


	@ModelAttribute("creditType")
	public Map<String, String> getCreditType() {
		Map<String, String> creditType = new HashMap<String, String>();
		creditType.put("Contingency", "Contingency");
		creditType.put("Emergency", "Emergency");
		creditType.put("Medical", "Medical");
		creditType.put("Restructure debt", "Restructure debt");
		return creditType;
	}

	@ModelAttribute("loanTenureList")
	public Map<Integer, String> getLonTenureList() {
		Map<Integer, String> loanTenureList = new HashMap<Integer, String>();
		loanTenureList.put(3, "3");
		loanTenureList.put(5, "5");
		loanTenureList.put(7, "7");
		loanTenureList.put(9, "9");
		loanTenureList.put(11, "11");
		return loanTenureList;
	}

	@ModelAttribute("cityList")
	public Map<String, String> getCityList() {
		Map<String, String> cityList = new HashMap<String, String>();
		cityList.put("KOL", "Kolkata");
		cityList.put("DUR", "Durgapur");
		cityList.put("Siliguri", "Siliguri");
		cityList.put("Malda", "Malda");
		return cityList;
	}




	@ModelAttribute("investmentType")
	public Map<String, String> getinvestmentType() {
		Map<String, String> investmentType = new HashMap<String, String>();
		investmentType.put("SIPT", "SIP based on your target amount");
		investmentType.put("SIPM", "SIP based contribution");
		investmentType.put("TARGET_PLAN", "Target Plan");
		return investmentType;
	}


	@ModelAttribute("freemiLoanCategory")
	public Map<String, String> getloanCategory() {
		Map<String, String> investmentType = new HashMap<String, String>();
		investmentType.put("FIXED", "Fixed Credit");
		investmentType.put("FLEXIBLE", "Flexible Credit");
		investmentType.put("NOCOST", "No Cost EMI");
		return investmentType;
	}

	@ModelAttribute("officeRole")
	public Map<String, String> officeRoles() {
		Map<String, String> officeDesignation = new HashMap<String, String>();
		officeDesignation.put("Officer", "Officer");
		officeDesignation.put("Executive", "Executive");
		officeDesignation.put("Manager", "Manager");
		officeDesignation.put("Management", "Management");
		officeDesignation.put("Others", "Others");

		return officeDesignation;
	}

	@ModelAttribute("transportType")
	public Map<String, String> transportType() {
		Map<String, String> transportType = new HashMap<String, String>();
		transportType.put("Bike", "Bike");
		transportType.put("Car", "Car");
		transportType.put("Taxi-Hailing App", "Taxi-Hailing App");
		transportType.put("Auto", "Auto");
		transportType.put("Bus", "Bus");
		transportType.put("Shuttle", "Shuttle");
		transportType.put("Train", "Train");

		return transportType;
	}

	@ModelAttribute("contextcdn") String contextcdn() {
		return env.getProperty(CommonConstants.CDN_URL);
	}


	/*	public static void main(String[] args) throws ParseException{

		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-d");
		LocalDate d = LocalDate.parse("2000-01-25" , format);
		logger.info(d);
		logger.info(d.getDayOfMonth());
		logger.info(d.getMonthValue());
		logger.info( String.format("%02d" , d.getMonthValue()));
		logger.info(d.getYear());
	}*/

}
