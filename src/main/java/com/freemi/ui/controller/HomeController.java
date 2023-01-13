package com.freemi.ui.controller;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.freemi.common.util.CommonConstants;
import com.freemi.common.util.CommonTask;
import com.freemi.entity.database.CampaignSignupForm;
import com.freemi.entity.database.ContactUsForm;
import com.freemi.entity.database.EmailUnsubscribeForm;
import com.freemi.entity.general.ClientSystemDetails;
import com.freemi.entity.general.Folios;
import com.freemi.entity.general.ForgotPassword;
import com.freemi.entity.general.Login;
import com.freemi.entity.general.Otpform;
import com.freemi.entity.general.Otprequeststatus;
import com.freemi.services.interfaces.BseRestClientService;
import com.freemi.services.interfaces.DatabaseEntryManager;
import com.freemi.services.interfaces.MailSenderInterface;
import com.freemi.services.interfaces.ProfileRestClientService;
import com.freemi.services.interfaces.SmsSenderInterface;
import com.freemi.services.interfaces.Verifydetailsinterface;
import com.freemi.ui.restclient.GoogleSecurity;

@Controller
@Scope("session")
public class HomeController {

    private static final Logger logger = LogManager.getLogger(HomeController.class);
    
    private static final String ERROR_S = "1";
    private static final String SUCCESS_S = "0";
    
    @Autowired
    private DatabaseEntryManager databaseEntryManager ;//= (DatabaseEntryManager) BeanUtil.getBean(DatabaseEntryService.class);

    @Autowired
    MailSenderInterface mailSenderInterface;

    @Autowired
    BseRestClientService bseRestClientService;

    @Autowired
    ProfileRestClientService profileRestClientService;

    @Autowired
    SmsSenderInterface smsSenderInterface;
    
    @Autowired
	Verifydetailsinterface verifydetailsinterface;

    @Autowired
    private Environment env;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model map) {
	//logger.info("@@@@ Inside Login..");
	logger.info("@@@@ HomeController @@@@");
	//		map.addAttribute("contextcdn", env.getProperty(CommonConstants.CDN_URL));
	/*return "index";*/
	return "redirect:/mutual-funds/funds-explorer";
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET )
    public String login(@ModelAttribute("login")Login login,@ModelAttribute("otpForm")Login otpForm, @RequestParam(name="ref",required=false)String referrerUrl,@RequestParam(name="mf",required=false)String mfStatus,@RequestParam(name="id",required=false)String loginid, Model map, HttpServletRequest request, HttpSession session, HttpServletResponse response) {
	//logger.info("@@@@ Inside Login..");

	logger.info("@@@@ LoginController @@@@ -"  + session.getId() );
	logger.debug("Referrer url if passed-"+ referrerUrl!=null?referrerUrl:request.getHeader("Referer"));



	//		map.addAttribute("login", login);
	//		logger.info("Referer- "+ request.getHeader("Referer"));
	//		model.addAttribute("returnSite", request.getHeader("Referer"));
	logger.info("url from referrer- "+ referrerUrl);
	try {
	    //			login.setReturnUrl(referrerUrl!=null?URLDecoder.decode(referrerUrl, StandardCharsets.UTF_8.toString()):request.getHeader("Referer"));

	    /*if(referrerUrl==null || referrerUrl.contains("/register") || referrerUrl.contains("/forgotPassword")){
				referrerUrl= URI.create(request.getRequestURL().toString()).resolve(request.getContextPath()).toString();
				logger.info("Modified refereele url to- "+ referrerUrl);
			}*/
	    String returnUrl = redirectUrlAfterLogin(referrerUrl!=null?URLDecoder.decode(referrerUrl, StandardCharsets.UTF_8.toString()):request.getHeader("Referer")!=null?request.getHeader("Referer"):URI.create(request.getRequestURL().toString()).resolve(request.getContextPath()).toString(),request);
	    login.setReturnUrl(returnUrl);
	    otpForm.setReturnUrl(returnUrl);
	    session.setAttribute("returnSite", referrerUrl!=null?URLDecoder.decode(referrerUrl, StandardCharsets.UTF_8.toString()):request.getHeader("Referer"));
	    logger.debug("Set return url- "+ login.getReturnUrl());
	} catch (UnsupportedEncodingException e) {
	    logger.error("Failed to decode string",e);
	}

	if(session.getAttribute("token") ==null){

	    if(mfStatus!=null){
		if(mfStatus.equals("00")){
		    map.addAttribute("info", "Kindly login to complete your purchase.");
		}
		else if(mfStatus.equals("01"))
		{
		    map.addAttribute("info", "Kindly login to complete your registration process.");
		}else{
		    map.addAttribute("info", "");
		}
	    }


	}else{
	    logger.info("User session is already detected. Preventing another attempt of login. Update session cookie with current details");
	    resetcookiesesiionvalue(session, response);
	    String returnurl = request.getHeader("Referer")!=null?request.getHeader("Referer").toString():URI.create(request.getRequestURL().toString()).resolve(request.getContextPath()).toString();
	    logger.info("Return url from login- "+ returnurl);
	    return "redirect:"+returnurl;
	}

	if(loginid!=null) {
	    login.setUsermobile(CommonTask.decryptText(loginid).equals("NA")?null:CommonTask.decryptText(loginid));
	}

	return "login";
    }

    /*@RequestMapping(value = "/login", method = RequestMethod.GET )
	public String login(@ModelAttribute("otpForm")Login otpForm, @RequestParam(name="ref",required=false)String referrerUrl,@RequestParam(name="mf",required=false)String mfStatus,Model map, HttpServletRequest request, HttpSession session) {
		//logger.info("@@@@ Inside Login..");

		logger.info("@@@@ OTPController @@@@");
		System.out.println("Referrer url if passed-"+ referrerUrl!=null?referrerUrl:request.getHeader("Referer"));
		//		map.addAttribute("login", login);
		//		logger.info("Referer- "+ request.getHeader("Referer"));
		//		model.addAttribute("returnSite", request.getHeader("Referer"));
		System.out.println("url from referrer- "+ request.getHeader("Referer"));
		try {
			//			login.setReturnUrl(referrerUrl!=null?URLDecoder.decode(referrerUrl, StandardCharsets.UTF_8.toString()):request.getHeader("Referer"));
			String returnUrl = redirectUrlAfterLogin(referrerUrl!=null?URLDecoder.decode(referrerUrl, StandardCharsets.UTF_8.toString()):request.getHeader("Referer"));
			login.setReturnUrl(returnUrl);
			otpForm.setReturnUrl(returnUrl);
			session.setAttribute("returnSite", referrerUrl!=null?URLDecoder.decode(referrerUrl, StandardCharsets.UTF_8.toString()):request.getHeader("Referer"));
			System.out.println("Set return url- "+ login.getReturnUrl());
		} catch (UnsupportedEncodingException e) {
			logger.error("Failed to decode string",e);
		}

		if(mfStatus!=null){
			if(mfStatus.equals("00")){
				map.addAttribute("info", "Kindly login to complete your purchase.");
			}
			else if(mfStatus.equals("01"))
			{
				map.addAttribute("info", "Kindly login to complete your registration process.");
			}else{
				map.addAttribute("info", "");
			}
		}

		map.addAttribute("contextcdn", env.getProperty(CommonConstants.CDN_URL));
		return "login";
	}*/

    @RequestMapping(value = "/login.do", method = RequestMethod.GET)
    public String loginAttemptGet(ModelMap model) {
	return "redirect:/login";
    }

//    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    public String loginAttemptPost(@ModelAttribute("login") @Valid Login login, BindingResult bindingResult, ModelMap model, HttpServletRequest request, HttpSession session) {
	logger.info("@@@@ Inside Login do..");
	//		logger.info("Referer- "+ request.getHeader("Referer"));
	//		String referer = request.getHeader("Referer");
	logger.debug("Recpcha form resuest- "+ request.getParameter("g-recaptcha-response"));

	String ip = CommonTask.getClientSystemIp(request);

	if(bindingResult.hasErrors()){
	    logger.info("Error in login form");
	    //			model.addAttribute("error", "Invalid form data");
	    model.addAttribute("error", bindingResult.getFieldError().getDefaultMessage());
	    return "login";
	}
	if(request.getParameter("g-recaptcha-response")==""){
	    logger.info("Security token not checked");
	    model.addAttribute("error", "Please check the security verification");
	    return "login";
	}else{
	    if(!GoogleSecurity.verifyRecaptcha(request.getParameter("g-recaptcha-response"), "N", ip, request.getRequestURL().toString())){
		logger.warn("Security token validation failed");
		model.addAttribute("error", "Security token validation failed!");
		return "login";
	    }
	}

	//		logger.info("Beginning attemptAuthentication() from IP- "+ request.getRemoteHost()+ "/"+request.getHeader("X-Forwarded-for"));
	logger.debug("OTP login check- "+ login.isOtpLogin());

	String returnUrl="";
	String referer = (String) session.getAttribute("returnSite");
	logger.info(referer);
	returnUrl = redirectUrlAfterLogin(referer,request);

	//		RestClient client = new RestClient();
	ResponseEntity<String> response = null;


	try{
	    response= profileRestClientService.login(login.getUsermobile(), login.getUserpassword(), ip);
	    //			model.addAttribute("token",response.getHeaders().get("Authorization").get(0));
	    //			model.addAttribute("loggedInUser",response.getHeaders().get("fname").get(0).split(" ")[0]);

	    session.setAttribute("loggedSession", response.getHeaders().get("fname").get(0).split(" ")[0]);
	    session.setAttribute("token", response.getHeaders().get("Authorization").get(0));
	    session.setAttribute("userid", response.getHeaders().get("userid").get(0));
	    session.setAttribute("email",response.getHeaders().get("email").get(0));

	    logger.info(response.getHeaders().get("Authorization").get(0));
	}
	catch(HttpStatusCodeException  e){
	    logger.error("Connection failure - " + e.getStatusCode());
	    if(e.getRawStatusCode()==401)
		model.addAttribute("error", "Invalid userid or password");
	    if(e.getRawStatusCode() == 500)
		model.addAttribute("error", "Unable to connect to server");
	    if(e.getRawStatusCode() == 404)
		model.addAttribute("error", "Service url not found");
	    returnUrl="login";
	}catch(Exception e){
	    logger.error("Error while trying to login",e);
	    model.addAttribute("error", "Unable to process request currently");
	    returnUrl="login";
	}
	logger.info("Returning to URL- "+ returnUrl);
	return returnUrl;
    }


    private String redirectUrlAfterLogin(String referer, HttpServletRequest request) {
	String returnUrl;
	logger.info("Url for processing after login- "+ referer);
	/*if(referer!= null && !referer.isEmpty()){
			URL url = null;
			try {
				url = new URL(referer);
				URI uri = url.toURI();
				if(uri.getRawPath().contains("/register") || uri.getRawPath().contains("/forgotPassword") || uri.getRawPath().contains("/resetPassword")){
					returnUrl="redirect:/";
				}else
					returnUrl = "redirect:/"+ uri.getRawPath().split("/products/")[1].replace(".do", "");
			} catch (MalformedURLException e1) {
				logger.error("Logincontroller post: Failed to form the URL",e1);
				returnUrl = "redirect:/";
			}catch(ArrayIndexOutOfBoundsException e){
				returnUrl = "redirect:/";
			}catch (URISyntaxException e) {
				returnUrl = "redirect:/";
			}catch (Exception e) {
				returnUrl = "redirect:/";
			}
		}else{
			logger.info("Referer is null");
			returnUrl = "redirect:/";
		}*/

	if(referer!= null && !referer.isEmpty()){
	    URL url = null;
	    URI uri = null;
	    try {
		url = new URL(referer);
		uri = url.toURI();
		logger.info("uri - "+ uri);
		if(uri.getRawPath().contains("/register") || uri.getRawPath().contains("/forgotPassword") || uri.getRawPath().contains("/resetPassword")){
		    //					returnUrl=uri.getRawPath().split("/products/")[1].replace(".do", "");
		    //					returnUrl = referer.replace(".do", "");
		    //					returnUrl = "redirect:/products/";
			
			if(uri.getRawPath().contains("mutual-funds/register")) {
				returnUrl = referer.replace(".do", "");
			}else {
				returnUrl = /*"redirect:"+*/ URI.create(request.getRequestURL().toString()).resolve(request.getContextPath()).toString();
			}
		}else
		    //					returnUrl = uri.getRawPath().split("/products/")[1].replace(".do", "");
		    returnUrl = referer.replace(".do", "");

	    } /*catch (MalformedURLException e1) {
				logger.error("Logincontroller post: Failed to form the URL",e1);
				returnUrl =  uri.getRawPath().split("/products/")[1];
			}catch(ArrayIndexOutOfBoundsException e){
				returnUrl =  uri.getRawPath().split("/products/")[1];
			}catch (URISyntaxException e) {
				returnUrl =  uri.getRawPath().split("/products/")[1];
			}*/catch (Exception e) {
			    logger.error("redirectUrlAfterLogin(): Exception while processing...",e);
			    returnUrl =  uri.getRawPath().split("/products/")[1];
			}
	}else{
	    logger.info("redirectUrlAfterLogin(): Referer is null....");

	    //			returnUrl = "redirect:/products/";
	    returnUrl = /*"redirect:" +*/URI.create(request.getRequestURL().toString()).resolve(request.getContextPath()).toString();
	}

	logger.info("Redirect url after login- "+ returnUrl);
	return returnUrl;
    }



//    @RequestMapping(value = "/login2.do", method = RequestMethod.POST)
    @ResponseBody
    public Otprequeststatus loginwithJqueryAttemptPost2(@ModelAttribute("login") @Valid Login login,ModelMap model, BindingResult bindingResult,HttpServletRequest request,HttpServletResponse response, HttpSession session) {
	logger.info("@@@@ Inside Login do loginwithJqueryAttemptPost2().. /login2.do");	
	//		logger.info("Referer- "+ request.getHeader("Referer"));
	//		String referer = request.getHeader("Referer");

	logger.debug("loginwithJqueryAttemptPost2(): Recpcha form resuest- "+ request.getParameter("g-recaptcha-response"));

	logger.debug("loginwithJqueryAttemptPost2(): Fetching after login url- "+ login.getReturnUrl() + " OTPMIT - "+ login.isOtpSubmit());
	Otprequeststatus requeststatus = new Otprequeststatus();
	String ip = CommonTask.getClientSystemIp(request);

	if(bindingResult.hasErrors()){
	    logger.info("loginwithJqueryAttemptPost2(): Error in login form");
	    //			model.addAttribute("error", "Invalid form data");
	    model.addAttribute("error", bindingResult.getFieldError().getDefaultMessage());
//	    return "Invalid form data!";
	    requeststatus.setStatuscode(ERROR_S);
		requeststatus.setErrormsg("Invalid form data!");
		return requeststatus;
	    
	}
	if(request.getParameter("g-recaptcha-response")==""){
	    logger.info("loginwithJqueryAttemptPost2(): Security token not checked");
	    model.addAttribute("error", "Please check the security verification");
//	    return "Security Captcha token missing!";
	    requeststatus.setStatuscode(ERROR_S);
		requeststatus.setErrormsg("Please check the security verification");
		return requeststatus;
	}else{
	    if(!GoogleSecurity.verifyRecaptcha(request.getParameter("g-recaptcha-response"), "N", ip, request.getRequestURL().toString())){
		logger.warn("loginwithJqueryAttemptPost2(): Security token validation failed");
		model.addAttribute("error", "Security token validation failed!");
//		return "Captcha validation failed!";
		requeststatus.setStatuscode(ERROR_S);
		requeststatus.setErrormsg("Captcha validation failed!");
		return requeststatus;
	    }
	}
	logger.info("loginwithJqueryAttemptPost2(): Session id during login- "+ session.getId() + " :mobile : "+ login.getUsermobile());
	//		logger.info("Beginning attemptAuthentication() from IP- "+ request.getRemoteHost()+ "/"+request.getHeader("X-Forwarded-for"));
	logger.debug("loginwithJqueryAttemptPost2(): OTP login check- "+ login.isOtpLogin());

	String returnUrl="";
	String referer = (String) session.getAttribute("returnSite");
	logger.debug("loginwithJqueryAttemptPost2(): login2.do referer- "+ referer);
	//		returnUrl = redirectUrlAfterLogin(referer);

	//		RestClient client = new RestClient();
	ResponseEntity<String> responseEntity = null;


	if(!login.isOtpSubmit()){

	    if(login.isOtpLogin()){
		//			Process for OTP login
		try{

		    //			Check if user exists and fetch email id

		    responseEntity = profileRestClientService.validateuserIdAndGetMail(login.getUsermobile());
		    String[] userStatus = responseEntity.getBody().toString().split(",");

		    logger.info(Arrays.asList(userStatus));
		    if(userStatus[0].equalsIgnoreCase("VALID")){
			if(!userStatus[1].equals("NO_EMAIL")){
				/*
			    String resultotp= bseRestClientService.otpGeneration(login.getUsermobile());
			    logger.debug("loginwithJqueryAttemptPost2(): RECEIVED OTP RESPONSE: "+ resultotp);
			    if(resultotp.contains("OTP")){
				//				Trigger mail 
				session.setAttribute("OTP", resultotp.split("=")[1]);
				try {
				    mailSenderInterface.loginOTPMail(login.getUsermobile(), resultotp.split("=")[1], userStatus[1], "5");
				    smsSenderInterface.sendOtp(login.getUsermobile(), resultotp.split("=")[1], "5", null);
				    //									returnUrl="OTP_SENT";
				} catch (InterruptedException e) {
				    logger.error("Failed to send mail for OTP- ",e);
				}

				returnUrl="OTP_SENT";
				login.setOtpSubmit(true);

			    }
			    */
				Otpform otpform = new Otpform();
				otpform.setKey(login.getUsermobile());
				otpform.setModule("LOGIN");
				otpform.setSubmodule("WEB_UI_LOGIN");
				ClientSystemDetails systemdetails = new ClientSystemDetails();
				systemdetails = CommonTask.getClientSystemDetails(request);
				requeststatus = verifydetailsinterface.generatemobileotp(otpform,systemdetails, request.getSession().getId());
				
			}else{
//			    returnUrl = "No email to OTP. Kindly contact admin";
				requeststatus.setStatuscode(ERROR_S);
				requeststatus.setMsg("No email to OTP. Kindly contact admin");
			}
		    }else{
//			returnUrl = "Invalid user id";
			requeststatus.setStatuscode(ERROR_S);
			requeststatus.setMsg("Invalid user id");
		    }

		}catch(HttpStatusCodeException  e){
		    logger.error("loginwithJqueryAttemptPost2(): BSESERVICE LOGIN OTP service connection ailure - " ,e);
		    if(e.getRawStatusCode()==HttpStatus.UNAUTHORIZED.value())
			returnUrl= "Invalid userid or password";
		    else if(e.getRawStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR.value())
			returnUrl="Unable to connect to service";
		    else if(e.getRawStatusCode() == HttpStatus.NOT_FOUND.value())
			returnUrl="Service url down or not found!";
		    else if(e.getRawStatusCode() == HttpStatus.LOCKED.value())
			returnUrl="Service url down or not found!";
		    else {
			returnUrl="Internal error. Please try after sometime";
		    }
		    
		    requeststatus.setStatuscode(ERROR_S);
			requeststatus.setMsg(returnUrl);
		    
		}catch(Exception e){
		    logger.error("loginwithJqueryAttemptPost2(): BSESERVICE LOGIN OTP Error while trying to generate OTP",e);
		    model.addAttribute("error", "Unable to process request currently");
		    returnUrl="Internal error. Kindly contact admin";
		    requeststatus.setStatuscode(ERROR_S);
			requeststatus.setMsg(returnUrl);
		    
		}

	    }else{
		//				Direct login process with userid-password
		try{

		    responseEntity= profileRestClientService.login(login.getUsermobile(), login.getUserpassword(), ip);
		    logger.info("loginwithJqueryAttemptPost2(): Session id during login- "+ session.getId());
		    setloggedsessiondata(session, responseEntity);
		    try{
			setloggedsessioncookie(response, responseEntity);
		    }catch(Exception e){
			logger.error("Error setting session cookie ..",e);
		    }

		    logger.info(responseEntity.getHeaders().get("Authorization").get(0));
		    returnUrl="SUCCESS";
		    requeststatus.setStatuscode(SUCCESS_S);
			requeststatus.setMsg(returnUrl);
		}catch(HttpStatusCodeException  e){
		    logger.error("loginwithJqueryAttemptPost2(): Login failure - " ,e.getMessage());
		    if(e.getRawStatusCode()==HttpStatus.UNAUTHORIZED.value()) 
			returnUrl= "Invalid userid or password";
		    else if(e.getRawStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR.value())
			returnUrl="Unable to connect to server";
		    else if(e.getRawStatusCode() == HttpStatus.NOT_FOUND.value())
			returnUrl="Service url down or not found!";
		    else if(e.getRawStatusCode() == HttpStatus.LOCKED.value())
			returnUrl="Account is locked. Kindly get in touch with our support team to unlock your account.";
		    else{
			returnUrl="Internal error. Please try after some time or contact admin if prolem persist.";
		    }
		    requeststatus.setStatuscode(ERROR_S);
			requeststatus.setMsg(returnUrl);
		    
		}catch(Exception e){
		    logger.error("Error while trying to set cookie after login",e);
		    model.addAttribute("error", "Unable to process request currently");
		    returnUrl="Internal error. Kindly contact admin";
		    requeststatus.setStatuscode(ERROR_S);
			requeststatus.setMsg(returnUrl);
		}
	    }

	}else{

	    String resultotp2="OTP_INVALID";

	    try{
		if(!login.getUsermobile().isEmpty() && !login.getOtpVal().isEmpty()){
		    logger.info("Process OTP submit verfication for mobile number- "+ login.getUsermobile());
//		    resultotp2= bseRestClientService.otpverify(login.getUsermobile(), login.getOtpVal());
		    Otpform otpform = new Otpform();
		    otpform.setKey(login.getUsermobile());
			otpform.setModule("LOGIN");
			otpform.setSubmodule("WEB_UI_LOGIN");
			ClientSystemDetails systemdetails = new ClientSystemDetails();
			systemdetails = CommonTask.getClientSystemDetails(request);
			requeststatus = verifydetailsinterface.verifymobileotp(otpform,systemdetails, request.getSession().getId());

		    logger.info("OTP validation respond- "+ resultotp2);
//		    if(resultotp2.equalsIgnoreCase("Entered Otp is valid")){
		    if(requeststatus.getStatuscode().equals(SUCCESS_S)) {
			//				Generate login session

			try {
			    responseEntity= profileRestClientService.otpLogin(login,ip);
			    if(responseEntity.getBody().toString().equalsIgnoreCase("SUCCESS")){
				logger.debug("Authorization token - "+ responseEntity.getHeaders().get("Authorization").get(0));
				
				setloggedsessiondata(session, responseEntity);
				    try{
					setloggedsessioncookie(response, responseEntity);
				    }catch(Exception e){
					logger.error("Error setting session cookie ..",e);
				    }
				
				
				/*session.setAttribute("loggedSession", responseEntity.getHeaders().get("fname").get(0).split(" ")[0]);
				session.setAttribute("token", responseEntity.getHeaders().get("Authorization").get(0));
				session.setAttribute("userid", responseEntity.getHeaders().get("userid").get(0));
				if(responseEntity.getHeaders().get("email")!=null){
				    session.setAttribute("email",responseEntity.getHeaders().get("email")!=null?responseEntity.getHeaders().get("email").get(0):"");
				}
				if(responseEntity.getHeaders().get("pan")!=null){
				    session.setAttribute("pan",responseEntity.getHeaders().get("pan").get(0));
				}*/
				
				

				/*
								try{
				//				    adding the cookie to the HttpResponse
								    logger.info("Setting session in cookie for customer after OTP validation- "+ responseEntity.getHeaders().get("userid").get(0));	
								    Cookie ssokCookie = new Cookie("loggedSession", responseEntity.getHeaders().get("fname").get(0).split(" ")[0]);
								    ssokCookie.setPath("/");
								    response.addCookie(setSessionCookie("loggedSession", responseEntity.getHeaders().get("fname").get(0).split(" ")[0]));
								    response.addCookie(setSessionCookie("userid", responseEntity.getHeaders().get("userid").get(0)));
								    response.addCookie(setSessionCookie("email",responseEntity.getHeaders().get("email").get(0)));
				
								    Cookie sessioncookie = new Cookie("token", responseEntity.getHeaders().get("Authorization").get(0));
								    sessioncookie.setMaxAge(300);
								    response.addCookie(sessioncookie);
				
								    if(responseEntity.getHeaders().get("pan")!=null){
									response.addCookie(setSessionCookie("pan", CommonTask.encryptText(responseEntity.getHeaders().get("pan").get(0))));
								    }
				
								    logger.info("Setting session in cookie for customer is complete after OTP validation.");
								}catch(Exception e){
								    logger.error("Error setting cookie in session..",e);
								}
				*/
				returnUrl="SUCCESS";
				 requeststatus.setStatuscode(SUCCESS_S);
				requeststatus.setMsg(returnUrl);
				
			    }else{
				returnUrl="OTP_LOGIN_FAIL";
				 requeststatus.setStatuscode(ERROR_S);
				requeststatus.setMsg(returnUrl);
			    }
			}catch(HttpStatusCodeException  e){
			    logger.error("loginwithJqueryAttemptPost2(): Login failure during OTP authentication generation - " ,e.getMessage());
			    if(e.getRawStatusCode()==HttpStatus.UNAUTHORIZED.value()) 
				returnUrl= "Invalid userid or password";
			    else if(e.getRawStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR.value())
				returnUrl="Unable to connect to server";
			    else if(e.getRawStatusCode() == HttpStatus.NOT_FOUND.value())
				returnUrl="Service url down or not found!";
			    else if(e.getRawStatusCode() == HttpStatus.LOCKED.value())
				returnUrl="Account is locked. Kindly get in touch with our support team to unlock your account.";
			    else{
				returnUrl="Internal error. Please try after some time or contact admin if prolem persist.";
			    }
			    requeststatus.setStatuscode(ERROR_S);
				requeststatus.setMsg(returnUrl);
			} catch (JsonProcessingException e) {
			    logger.error("Failed to parse data",e);
			    returnUrl="OTP_VALID_FAIL";
			    requeststatus.setStatuscode(ERROR_S);
				requeststatus.setMsg(returnUrl);
			}catch(Exception e){
			    returnUrl="OTP_VALID_FAIL";
			    logger.error("Failed to process OTP login",e);
			}
			//			model.addAttribute("token",response.getHeaders().get("Authorization").get(0));
			//			model.addAttribute("loggedInUser",response.getHeaders().get("fname").get(0).split(" ")[0]);
			 requeststatus.setStatuscode(ERROR_S);
			requeststatus.setMsg(returnUrl);
		    }else{
			returnUrl="OTP_INVALID";
			requeststatus.setStatuscode(ERROR_S);
			requeststatus.setMsg(returnUrl);
		    }
		}else{
		    returnUrl="Missing required data!";
		    requeststatus.setStatuscode(ERROR_S);
			requeststatus.setMsg(returnUrl);
		}
	    }catch(HttpServerErrorException e){
		logger.error("loginwithJqueryAttemptPost(): Error processing OTP verification prorcess: ", e.getStatusCode(),e);
		 returnUrl="OTP_VALID_FAIL";
		 requeststatus.setStatuscode(ERROR_S);
		 requeststatus.setMsg(returnUrl);
	    }
	    catch(Exception e){
	    	returnUrl="OTP_VALID_FAIL";
		logger.error("loginwithJqueryAttemptPost(): Error with OTP verification.",e);
		 requeststatus.setStatuscode(ERROR_S);
		requeststatus.setMsg(returnUrl);
	    }


	}

	model.addAttribute("error", "1st Attempt..");
	logger.info("Returning to URL- "+ returnUrl);
//	return returnUrl;
	return requeststatus;

    }

    protected void resetcookiesesiionvalue(HttpSession session, HttpServletResponse response) {
	if(session.getAttribute("token")!=null) {
	    try {
		response.addCookie(setSessionCookie("token", URLEncoder.encode(session.getAttribute("token").toString(),StandardCharsets.UTF_8.toString()),true));
	    } catch (UnsupportedEncodingException e) {
		logger.error("Error resetting token value in cookie",e);
	    }
	}
    }
    
    protected void setloggedsessioncookie(HttpServletResponse response, ResponseEntity<String> responseEntity) {
	logger.info("loginwithJqueryAttemptPost2(): Setting session in cookie for customer- "+ responseEntity.getHeaders().get("userid").get(0));	

	if(responseEntity.getHeaders().get("fname")!=null){
	    response.addCookie(setSessionCookie("loggedSession", responseEntity.getHeaders().get("fname").get(0).split(" ")[0],false));
//	    response.setHeader("Set-Cookie", "loggedSession="+responseEntity.getHeaders().get("fname").get(0)+" ;HttpOnly;Path=/; SameSite=strict; max-age=7200");
	}
	if(responseEntity.getHeaders().get("email")!=null){
	    response.addCookie(setSessionCookie("email", responseEntity.getHeaders().get("email").get(0),false));
//	    response.setHeader("Set-Cookie", "email="+responseEntity.getHeaders().get("email").get(0)+" ;HttpOnly;Path=/; SameSite=strict; max-age=7200");
	}
	
	if(responseEntity.getHeaders().get("userid")!=null){
	    response.addCookie(setSessionCookie("userid", responseEntity.getHeaders().get("userid").get(0),false));
//	    response.setHeader("Set-Cookie", "userid="+responseEntity.getHeaders().get("userid").get(0)+" ;HttpOnly;Path=/; SameSite=strict; max-age=7200");
	}
	
	if(responseEntity.getHeaders().get("pan")!=null){
	    response.addCookie(setSessionCookie("pan", CommonTask.encryptText(responseEntity.getHeaders().get("pan").get(0)),true));
	}

	try {
	    response.addCookie(setSessionCookie("token", URLEncoder.encode(responseEntity.getHeaders().get("Authorization").get(0),StandardCharsets.UTF_8.toString()),true));
	    
	    
	} catch (UnsupportedEncodingException e) {
	    logger.error("Error setting token in cookie",e);
	}

	logger.info("Setting session in cookie for customer is complete.");
    }


    protected void setloggedsessiondata(HttpSession session, ResponseEntity<String> responseEntity) {
    logger.info("Session ID- "+ session.getId());
	if(responseEntity.getHeaders().get("fname")!=null){
	    session.setAttribute("loggedSession", responseEntity.getHeaders().get("fname").get(0).split(" ")[0]);
	}
	session.setAttribute("token", responseEntity.getHeaders().get("Authorization").get(0));
	session.setAttribute("userid", responseEntity.getHeaders().get("userid").get(0));

	if(responseEntity.getHeaders().get("email")!=null){
	    session.setAttribute("email",responseEntity.getHeaders().get("email")!=null?responseEntity.getHeaders().get("email").get(0):"");
	}
	if(responseEntity.getHeaders().get("pan")!=null){
	    session.setAttribute("pan",responseEntity.getHeaders().get("pan").get(0));
	}
    }



    @RequestMapping(value = "/otpverify.do", method = RequestMethod.POST)
    @ResponseBody
    public String loginbyotp(@ModelAttribute("otpForm") @Valid Login login,ModelMap model, BindingResult bindingResult,HttpServletRequest request, HttpSession session) {

	logger.info("@@@@ Inside OTP Login verfiy  do..");	
	//		logger.info("Referer- "+ request.getHeader("Referer"));
	//		String referer = request.getHeader("Referer");
	//		System.out.println("Recpcha form resuest- "+ request.getParameter("g-recaptcha-response"));

	String ip = CommonTask.getClientSystemIp(request);

	if(bindingResult.hasErrors()){
	    logger.info("Error in login form");
	    //			model.addAttribute("error", "Invalid form data");
	    model.addAttribute("error", bindingResult.getFieldError().getDefaultMessage());
	    return "FORM_ERROR";
	}
	if(request.getParameter("g-recaptcha-response")==""){
	    logger.info("Security token not checked");
	    model.addAttribute("error", "Please check the security verification");
	    return "login";
	}else{
	    if(!GoogleSecurity.verifyRecaptcha(request.getParameter("g-recaptcha-response"), "N", ip, request.getRequestURL().toString())){
		logger.warn("Security token validation failed");
		model.addAttribute("error", "Security token validation failed!");
		return "login";
	    }
	}

	//		logger.info("Beginning attemptAuthentication() from IP- "+ request.getRemoteHost()+ "/"+request.getHeader("X-Forwarded-for"));
	logger.info("OTP login check for mobile: "  + login.getUsermobile() +  " : OTP login check- "+ login.isOtpLogin() );

	String returnUrl="";
	String referer = (String) session.getAttribute("returnSite");
	logger.info("Login2 referrer- "+ referer);
	returnUrl = redirectUrlAfterLogin(referer,request);

	//		RestClient client = new RestClient();
	ResponseEntity<String> response = null;


	try{
	    response= profileRestClientService.otpLogin(login,CommonTask.getClientSystemIp(request));
	    //			model.addAttribute("token",response.getHeaders().get("Authorization").get(0));
	    //			model.addAttribute("loggedInUser",response.getHeaders().get("fname").get(0).split(" ")[0]);
	    if(response.getBody().equalsIgnoreCase("SUCCESS")){

		if(response.getHeaders().get("fname")!=null){
		    session.setAttribute("loggedSession", response.getHeaders().get("fname").get(0).split(" ")[0]);
		}

		session.setAttribute("token", response.getHeaders().get("Authorization").get(0));

		session.setAttribute("userid", response.getHeaders().get("userid").get(0));

		if(response.getHeaders().get("email")!=null){
		    session.setAttribute("email",response.getHeaders().get("email").get(0));
		}
	    }else{

	    }
	    logger.info(response.getHeaders().get("Authorization").get(0));
	}
	catch(HttpStatusCodeException  e){
	    logger.error("Connection failure - " + e.getStatusCode());
	    if(e.getRawStatusCode()==401)
		model.addAttribute("error", "Invalid userid or password");
	    if(e.getRawStatusCode() == 500)
		model.addAttribute("error", "Unable to connect to server");
	    if(e.getRawStatusCode() == 404)
		model.addAttribute("error", "Service url not found");
	    returnUrl="login";
	}catch(Exception e){
	    logger.error("Error while trying to login",e);
	    model.addAttribute("error", "Unable to process request currently");
	    returnUrl="login";
	}

	model.addAttribute("error", "1st Attempt..");
	logger.info("Returning to URL- "+ returnUrl);
	return returnUrl;
    }
    
    
    
    @RequestMapping(value = "/login-otp", method = RequestMethod.POST)
    @ResponseBody
    public Otprequeststatus loginwithJqueryAttemptPost3(@RequestBody @Valid Login login, 
    		BindingResult bindingResult,ModelMap model, HttpServletRequest request,HttpServletResponse response, HttpSession session) {
	logger.info("@@@@ Inside Login do loginwithJqueryAttemptPost3().. /login-otp");	
	//		logger.info("Referer- "+ request.getHeader("Referer"));
	//		String referer = request.getHeader("Referer");


	Otprequeststatus requeststatus = new Otprequeststatus();
	String ip = CommonTask.getClientSystemIp(request);

	if(bindingResult.hasErrors()){
	    logger.info("loginwithJqueryAttemptPost2(): Error in login form- "+ bindingResult.getFieldError().getField());
	    //			model.addAttribute("error", "Invalid form data");
	    model.addAttribute("error", bindingResult.getFieldError().getDefaultMessage());
//	    return "Invalid form data!";
	    requeststatus.setStatuscode(ERROR_S);
		requeststatus.setErrormsg(bindingResult.getFieldError().getDefaultMessage());
		return requeststatus;
	    
	}
	if(request.getParameter("g-recaptcha-response")==""){
	    logger.info("loginwithJqueryAttemptPost2(): Security token not checked");
	    model.addAttribute("error", "Please check the security verification");
//	    return "Security Captcha token missing!";
	    requeststatus.setStatuscode(ERROR_S);
		requeststatus.setErrormsg("Please check the security verification");
		return requeststatus;
	}else{
	    if(!GoogleSecurity.verifyRecaptcha(request.getParameter("g-recaptcha-response"), "N", ip, request.getRequestURL().toString())){
		logger.warn("loginwithJqueryAttemptPost2(): Security token validation failed");
		model.addAttribute("error", "Security token validation failed!");
//		return "Captcha validation failed!";
		requeststatus.setStatuscode(ERROR_S);
		requeststatus.setErrormsg("Captcha validation failed!");
		return requeststatus;
	    }
	}
	logger.info("loginwithJqueryAttemptPost2(): Session id during login- "+ session.getId() + " :mobile : "+ login.getUsermobile());
	//		logger.info("Beginning attemptAuthentication() from IP- "+ request.getRemoteHost()+ "/"+request.getHeader("X-Forwarded-for"));
	logger.debug("loginwithJqueryAttemptPost2(): OTP login check- "+ login.isOtpLogin());

	String returnmsg="";
	String referer = (String) session.getAttribute("returnSite");
	logger.debug("loginwithJqueryAttemptPost2(): login2.do referer- "+ referer);
	//		returnUrl = redirectUrlAfterLogin(referer);

	//		RestClient client = new RestClient();
	ResponseEntity<String> responseEntity = null;

	    if(login.isOtpLogin()){
		//			Process for OTP login
		try{

		    //			Check if user exists and fetch email id

		    responseEntity = profileRestClientService.validateuserIdAndGetMail(login.getUsermobile());
		    String[] userStatus = responseEntity.getBody().toString().split(",");

		    logger.info(Arrays.asList(userStatus));
		    if(userStatus[0].equalsIgnoreCase("VALID")){
			if(!userStatus[1].equals("NO_EMAIL")){
				
				Otpform otpform = new Otpform();
				otpform.setKey(login.getUsermobile());
				otpform.setModule("LOGIN");
				otpform.setSubmodule("WEB_UI_LOGIN");
				ClientSystemDetails systemdetails = new ClientSystemDetails();
				systemdetails = CommonTask.getClientSystemDetails(request);
				requeststatus = verifydetailsinterface.generatemobileotp(otpform,systemdetails, request.getSession().getId());
				
				if(requeststatus.getStatuscode().equals(SUCCESS_S)) {
					login.setOtpSubmit(true);
				}
				
			}else{
//			    returnUrl = "No email to OTP. Kindly contact admin";
				requeststatus.setStatuscode(ERROR_S);
				requeststatus.setErrormsg("No email to OTP. Kindly contact admin");
			}
		    }else{
//			returnUrl = "Invalid user id";
			requeststatus.setStatuscode(ERROR_S);
			requeststatus.setErrormsg("Invalid user id");
		    }

		}catch(HttpStatusCodeException  e){
		    logger.error("loginwithJqueryAttemptPost2(): BSESERVICE LOGIN OTP service connection ailure - " ,e);
		    if(e.getRawStatusCode()==HttpStatus.UNAUTHORIZED.value())
			returnmsg= "Invalid userid or password";
		    else if(e.getRawStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR.value())
			returnmsg="Unable to connect to service";
		    else if(e.getRawStatusCode() == HttpStatus.NOT_FOUND.value())
			returnmsg="Service url down or not found!";
		    else if(e.getRawStatusCode() == HttpStatus.LOCKED.value())
			returnmsg="Service url down or not found!";
		    else {
			returnmsg="Internal error. Please try after sometime";
		    }
		    
		    requeststatus.setStatuscode(ERROR_S);
			requeststatus.setErrormsg(returnmsg);
		    
		}catch(Exception e){
		    logger.error("loginwithJqueryAttemptPost2(): BSESERVICE LOGIN OTP Error while trying to generate OTP",e);
		    model.addAttribute("error", "Unable to process request currently");
		    returnmsg="Internal error. Kindly contact admin";
		    requeststatus.setStatuscode(ERROR_S);
			requeststatus.setErrormsg(returnmsg);
		    
		}

	    }else{
	    	returnmsg="Use profile credentials to login.";
	    	requeststatus.setStatuscode(ERROR_S);
			requeststatus.setErrormsg(returnmsg);
	    }


	model.addAttribute("error", "1st Attempt..");
	logger.info("Returning to URL- "+ returnmsg);
//	return returnUrl;
	return requeststatus;

    }


    @RequestMapping(value = "/login-verify", method = RequestMethod.POST)
    @ResponseBody
    public Otprequeststatus loginverify(@RequestBody @Valid Login login, BindingResult bindingResult,ModelMap model, HttpServletRequest request,HttpServletResponse response, HttpSession session) {
	logger.info("@@@@ Inside Login verification()..");	

	Otprequeststatus requeststatus = new Otprequeststatus();
	String ip = CommonTask.getClientSystemIp(request);

	if(bindingResult.hasErrors()){
	    logger.info("loginverify(): Error in login form");
	    requeststatus.setStatuscode(ERROR_S);
		requeststatus.setErrormsg("Invalid form data!");
		return requeststatus;
	}
	if(request.getParameter("g-recaptcha-response")==""){
	    logger.info("loginverify(): Security token not checked");
	    requeststatus.setStatuscode(ERROR_S);
		requeststatus.setErrormsg("Please check the security verification");
		return requeststatus;
	}else{
	    if(!GoogleSecurity.verifyRecaptcha(request.getParameter("g-recaptcha-response"), "N", ip, request.getRequestURL().toString())){
		logger.warn("loginverify(): Security token validation failed");
		requeststatus.setStatuscode(ERROR_S);
		requeststatus.setErrormsg("Captcha validation failed!");
		return requeststatus;
	    }
	}
	logger.info("loginverify(): Session id during login verify- "+ session.getId() + " :mobile : "+ login.getUsermobile());
	logger.debug("loginverify(): OTP login check- "+ login.isOtpLogin());

	String returnmsg="";
	String referer = (String) session.getAttribute("returnSite");
	logger.debug("loginverify(): login verify referer- "+ referer);

	ResponseEntity<String> responseEntity = null;

	if(!login.isOtpLogin()){
		logger.info("Proceed with credential based login........................");
		/* ------------------------ Direct login process with userid-password -------------------------------- */
		try{
		    responseEntity= profileRestClientService.login(login.getUsermobile(), login.getUserpassword(), ip);
		    logger.info("loginverify(): login sucess with password. Session id during login- "+ session.getId());
		    setloggedsessiondata(session, responseEntity);
		    setloggedsessioncookie(response, responseEntity);

		    logger.info(responseEntity.getHeaders().get("Authorization").get(0));
		    returnmsg="SUCCESS";
		    requeststatus.setStatuscode(SUCCESS_S);
			requeststatus.setMsg(returnmsg);
		}catch(HttpStatusCodeException  e){
		    logger.error("loginverify(): Login failure - " ,e.getMessage());
		    if(e.getRawStatusCode()==HttpStatus.UNAUTHORIZED.value()) 
			returnmsg= "Invalid userid or password";
		    else if(e.getRawStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR.value())
			returnmsg="Unable to connect to server";
		    else if(e.getRawStatusCode() == HttpStatus.NOT_FOUND.value())
			returnmsg="Service url down or not found!";
		    else if(e.getRawStatusCode() == HttpStatus.LOCKED.value())
			returnmsg="Account is locked. Kindly get in touch with our support team to unlock your account.";
		    else{
			returnmsg="Internal error. Please try after some time or contact admin if prolem persist.";
		    }
		    requeststatus.setStatuscode(ERROR_S);
			requeststatus.setErrormsg(returnmsg);
		    
		}catch(Exception e){
		    logger.error("Error while trying to set cookie after login success",e);
		    returnmsg="Internal error. Kindly contact admin";
		    requeststatus.setStatuscode(ERROR_S);
			requeststatus.setErrormsg(returnmsg);
		}

	}else{
		/*--------------- OTP based login ------------------------*/
		logger.info("Proceed with OTP based login........................");
		if(login.isOtpSubmit()) {
	    try{
		    logger.info("Process OTP submit verfication for mobile number- "+ login.getUsermobile());
		    Otpform verifyform = new Otpform();
		    verifyform.setKey(login.getUsermobile());
			verifyform.setModule("LOGIN");
			verifyform.setSubmodule("WEB_UI_LOGIN");
			verifyform.setOtp(login.getOtpVal());
			ClientSystemDetails systemdetails = new ClientSystemDetails();
			systemdetails = CommonTask.getClientSystemDetails(request);
			requeststatus = verifydetailsinterface.verifymobileotp(verifyform,systemdetails,request.getSession().getId());
		    logger.info("OTP validation respond- "+ requeststatus.getStatuscode());
		    if(requeststatus.getStatuscode().equals(SUCCESS_S)) {
			//				Generate login session

			try {
			    responseEntity= profileRestClientService.otpLogin(login,ip);
			    if(responseEntity.getBody().toString().equalsIgnoreCase("SUCCESS")){
				logger.debug("Authorization token - "+ responseEntity.getHeaders().get("Authorization").get(0));
				
				setloggedsessiondata(session, responseEntity);
				    try{
					setloggedsessioncookie(response, responseEntity);
				    }catch(Exception e){
					logger.error("Error setting session cookie ..",e);
				    }
				returnmsg="SUCCESS";
				requeststatus.setMsg(returnmsg);
			    }else{
			    	returnmsg="Failed to process login request. Please try after sometime.";
			    	requeststatus.setStatuscode(ERROR_S);
			    	requeststatus.setErrormsg(returnmsg);
			    }
			}catch(HttpStatusCodeException  e){
			    logger.error("loginverify(): Login failure during OTP authentication generation - " ,e.getMessage());
			    if(e.getRawStatusCode()==HttpStatus.UNAUTHORIZED.value()) 
				returnmsg= "Invalid userid or password";
			    else if(e.getRawStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR.value())
				returnmsg="Unable to connect to server";
			    else if(e.getRawStatusCode() == HttpStatus.NOT_FOUND.value())
				returnmsg="Service url down or not found!";
			    else if(e.getRawStatusCode() == HttpStatus.LOCKED.value())
				returnmsg="Account is locked. Kindly get in touch with our support team to unlock your account.";
			    else{
				returnmsg="Internal error. Please try after some time or contact admin if prolem persist.";
			    }
			    requeststatus.setStatuscode(ERROR_S);
				requeststatus.setErrormsg(returnmsg);
			} catch (JsonProcessingException e) {
			    logger.error("Failed to parse data",e);
			    returnmsg="Internal error. Kindly contact admin.";
			    requeststatus.setStatuscode(ERROR_S);
				requeststatus.setErrormsg(returnmsg);
			}catch(Exception e){
			    returnmsg="Internal error. Kindly contact admin.";
			    logger.error("Failed to process OTP login for- ",login.getUsermobile() ,e);
			    requeststatus.setStatuscode(ERROR_S);
				requeststatus.setErrormsg(returnmsg);
			}
			 
		    }
	    }catch(HttpServerErrorException e){
		logger.error("loginverify(): Error processing OTP verification prorcess: ", e.getStatusCode(),e);
		 returnmsg="Internal error. Kindly contact admin.";
		 requeststatus.setStatuscode(ERROR_S);
		 requeststatus.setErrormsg(returnmsg);
	    }catch(Exception e){
	    	returnmsg="Internal error. Kindly contact admin.";
	    	logger.error("loginverify(): Error with OTP verification.",e);
	    	requeststatus.setStatuscode(ERROR_S);
	    	requeststatus.setErrormsg(returnmsg);
	    }}else {
	    	logger.warn("loginverify(): OTP value not passed");
	    	requeststatus.setStatuscode(ERROR_S);
	    	requeststatus.setErrormsg("Submit OTP for verification.");
	    }
	}
	logger.info("Returning to URL- "+ returnmsg);
	return requeststatus;

    }
    


    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(ModelMap model, HttpServletRequest request,HttpServletResponse response, HttpSession session) {
	//logger.info("@@@@ Inside Login..");
	logger.info("@@@@ LogoutController @@@@");

	try{
	    logger.info("Clear logging data from cookie...");
	    String domain = env.getProperty("server.session.cookie.domain");
	    
	    Cookie ssokCookie = new Cookie("loggedSession", "");
	    ssokCookie.setMaxAge(0);
	    ssokCookie.setPath("/");
	    ssokCookie.setDomain(domain);
	    response.addCookie(ssokCookie);

	    ssokCookie = new Cookie("email", "");
	    ssokCookie.setMaxAge(0);
	    ssokCookie.setPath("/");
	    ssokCookie.setDomain(domain);
	    response.addCookie(ssokCookie);

	    ssokCookie = new Cookie("userid", "");
	    ssokCookie.setMaxAge(0);
	    ssokCookie.setPath("/");
	    ssokCookie.setDomain(domain);
	    response.addCookie(ssokCookie);
	    
	    ssokCookie = new Cookie("token", "");
	    ssokCookie.setMaxAge(0);
	    ssokCookie.setPath("/");
	    ssokCookie.setDomain(domain);
	    response.addCookie(ssokCookie);
	    
	    ssokCookie = new Cookie("pan", "");
	    ssokCookie.setMaxAge(0);
	    ssokCookie.setPath("/");
	    ssokCookie.setDomain(domain);
	    response.addCookie(ssokCookie);
	    
	    ssokCookie = new Cookie("mf_user", "");
	    ssokCookie.setMaxAge(0);
	    ssokCookie.setPath("/");
	    ssokCookie.setDomain(domain);
	    response.addCookie(ssokCookie);
	    
	    logger.info("Cookie unset complete for user");
	    
	    
	    

	}catch(Exception e){
	   logger.error("Error removing session data from cookie during logout..",e);

	}
	
	session.removeAttribute("loggedSession");
	session.removeAttribute("token");
	session.removeAttribute("userid");
	session.removeAttribute("email");
	session.removeAttribute("pan");
	session.invalidate();


	//		RestClientApps.logoutAllApplication("", "", "", "");
	return "redirect:/";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logoutPost(ModelMap model, HttpSession session) {
	//logger.info("@@@@ Inside Login..");
	return "redirect:/logout";
    }



    @RequestMapping(value = "/forgotPassword", method = RequestMethod.GET)
    public String forgotPasswordDisplay(Model map, HttpServletRequest request, HttpServletResponse response) {
	//logger.info("@@@@ Inside Login..");
	logger.info("@@@@ ForgotPasswordController @@@@");
	map.addAttribute("forgotPasswordForm", new ForgotPassword());

	return "forgotPassword";
    }

    @RequestMapping(value = "/forgotPassword.do", method = RequestMethod.GET)
    public String forgotPasswordGetSubmit(ModelMap model) {
	return "redirect:/forgotPassword";
    }

    @RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
    public String forgotPasswordSubmit(@ModelAttribute("forgotPasswordForm") @Valid ForgotPassword forgotPasswordForm, BindingResult bindingResult, Model model, HttpServletRequest request, HttpServletResponse resp,RedirectAttributes redirectAttrs) {
	logger.info("@@@@ Forgot password request submit POSTController.. /forgotPassword.do");
	String returnpage="forgotPassword";
	
	if(bindingResult.hasErrors()){
	    logger.info("Error in forgotpassword form- "+ bindingResult.getFieldError());
	    model.addAttribute("error", bindingResult.getFieldError().getDefaultMessage());
	    return returnpage;
	}
	if(request.getParameter("g-recaptcha-response")==""){
	    logger.info("Security token not checked");
	    model.addAttribute("error", "Please check the security verification");
	    return returnpage;
	}else{
	    if(!GoogleSecurity.verifyRecaptcha(request.getParameter("g-recaptcha-response"), "Y", CommonTask.getClientSystemIp(request), request.getRequestURL().toString())){
		logger.warn("Security token validation failed");
		model.addAttribute("error", "Security token validation failed!");
		return returnpage;
	    }
	}

	//		RestClient client = new RestClient();
	ResponseEntity<String> response = null;
	try {
	    logger.info("Beginning process to request password change process for mobile..." + forgotPasswordForm.getUsermobile());
	    response = profileRestClientService.forgotPassword(forgotPasswordForm);
	    logger.info("Forgot password change response for mobile:  "+forgotPasswordForm.getUsermobile() +" : " + (response!=null?response.getBody():"NULL"));
	    //			logger.info(response.getHeaders());
	    model.addAttribute("success", "Password reset mail sent on registered email id.");
	    redirectAttrs.addFlashAttribute("msg", "Password reset mail sent on registered email id.");
	    redirectAttrs.addFlashAttribute("forgotPasswordForm", forgotPasswordForm);
	    returnpage = "redirect:/forgotPassword/success";
	}catch(HttpStatusCodeException  e){
	    logger.error("Forgot password httpexception - ",e.getStatusCode(),e);
	    model.addAttribute("error", "Unable to process request curretnly");
	} 
	 /* catch (JsonProcessingException e) {
	    logger.error("Forgot password JSONProcessing exception - ",e);
	    model.addAttribute("error","Invalid form data");
	}*/catch(Exception e){
	    logger.error("Forgot password handling exception - ",e);
	    model.addAttribute("error","Error processing request");
	}

	//		model.addAttribute("forgotPasswordForm", forgotPasswordForm);

	return returnpage;
    }
    
    
    @RequestMapping(value = "/forgotPassword/success", method = RequestMethod.GET)
    public String forgotPasswordsuccess(@ModelAttribute("forgotPasswordForm") ForgotPassword passwordform, @ModelAttribute("msg") String msg, Model map, HttpServletRequest request, HttpServletResponse response) {
	//logger.info("@@@@ Inside Login..");
	logger.info("@@@@ ForgotPassword Success @@@@");
	
	if(passwordform!=null) {
		logger.info("Password reset link sent successfully to mobile no account- "+ passwordform.getUsermobile());
	}else {
		logger.info("No form data or page refreshed or loaded blank, redirecting..");
		return "redirect:/forgotPassword";
	}
	map.addAttribute("msg", msg);
	
	return "forgotPassword-success";
    }
    

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String contact( Model map, HttpServletRequest request, HttpSession session) {
	logger.info("@@@@ ContactController @@@@");

	ContactUsForm contactForm =new ContactUsForm();
	//		String 
	map.addAttribute("contactForm", contactForm);

	return "contact";
    }

    @RequestMapping(value = "/contact.do", method = RequestMethod.GET)
    public String contactDo( ModelMap model) {

	logger.info("@@@@ ContactDoController @@@@");
	return "redirect:/contact";
    }

    @RequestMapping(value = "/contact.do", method = RequestMethod.POST)
    public String contactRequestSubmit(@ModelAttribute("contactForm") ContactUsForm contactForm,Model model) {
	//logger.info("@@@@ Inside Login..");
	logger.info("@@@@ ContactDoController @@@@");
	//		RestClient client = new RestClient();
	ResponseEntity<String> response = null;
	try {
	    response = profileRestClientService.contactUs(contactForm);
	    logger.info(response.getBody());
	    //			logger.info(response.getHeaders());
	    model.addAttribute("success", response.getBody());
	    model.addAttribute("contactForm", new ContactUsForm());
	}catch(HttpStatusCodeException  e){
	    logger.info("test failure - " + e.getStatusCode());
	    model.addAttribute("error", "Unable to process request curretnly");
	} catch (JsonProcessingException e) {
	    model.addAttribute("error","Invalid form data");
	}catch(Exception e){
	    model.addAttribute("error","Error processing request");
	}


	return "contact";
    }




    /*	@RequestMapping(value = "/terms-conditions", method = RequestMethod.GET)
	public String getTermsConditions(Model map) {
		//logger.info("@@@@ Inside Login..");
		logger.info("@@@@ Terms & conditions Controller @@@@");
		map.addAttribute("contextcdn", env.getProperty(CommonConstants.CDN_URL));
		return "terms-conditions";
	}

	@RequestMapping(value = "/privacy-policy", method = RequestMethod.GET)
	public String getprivacyPolicy(Model map) {
		//logger.info("@@@@ Inside Login..");
		logger.info("@@@@ Privacy-policyController @@@@");
		map.addAttribute("contextcdn", env.getProperty(CommonConstants.CDN_URL));
		return "privacy-policy";
	}*/

    @RequestMapping(value = "/terms-of-use", method = RequestMethod.GET)
    public String termsOfUse(Model map) {
	//logger.info("@@@@ Inside Login..");
	logger.info("@@@@ Terms of user controller @@@@");
	return "terms-of-use";
    }


    @RequestMapping(value = "/campaignsignup.do", method = RequestMethod.GET)
    @ResponseBody
    public String campaignSingup(@RequestParam("mobile") String mobile,@RequestParam("email") String email, @RequestParam("location") String location,@RequestParam("agree") String agree, HttpServletRequest request, HttpSession session) {
	//logger.info("@@@@ Inside Login..");
	logger.info("@@@@ CampaignSignupController @@@@");

	CampaignSignupForm campaign = new CampaignSignupForm();
	session.setAttribute("campaignsubmitted", 1);
	ClientSystemDetails details= CommonTask.getClientSystemDetails(request);

	try{
	    if(!mobile.isEmpty()){
		campaign.setMobile(mobile);
		campaign.setEmail(email);
		campaign.setLocation(location);
		campaign.setUserrequestingagent(details.getClientBrowser());
		campaign.setUsersystemip(details.getClientIpv4Address());
		campaign.setTimeStamp(new Date());
		campaign.setAgree(agree);

		databaseEntryManager.saveCampaignEntry(campaign);


		// CAll to send mail from rest api
		//				RestClient client = new RestClient();
		ResponseEntity<String> response = null;
		try {
		    response = profileRestClientService.campaignSingUp(campaign);
		    logger.debug("Campaign mail send response- "+ response.getBody());

		}catch(HttpStatusCodeException  e){
		    logger.info("test failure - " + e.getStatusCode());
		} catch (JsonProcessingException e) {
		}catch(Exception e){
		}

	    }}catch(Exception e){
		logger.error("Unable to save campaign data-"+ e.getMessage());
	    }

	return "SUCCESS";
    }


    @RequestMapping(value = "/closewindow", method = RequestMethod.GET)
    public void windowClose(HttpServletRequest request,HttpServletResponse response, HttpSession session) {
	logger.info("Window closed... clear all session...");
	session.removeAttribute("loggedSession");
	session.removeAttribute("token");
	session.removeAttribute("userid");
	session.removeAttribute("email");

	try{
	    /*logger.info("Clear loggin data from servlet context...");
			ServletContext servletContext =request.getSession().getServletContext().getContext("/{applicationContextRoot}");
			servletContext.removeAttribute("loggedSession");
			servletContext.removeAttribute("token");
			servletContext.removeAttribute("userid");
			servletContext.removeAttribute("email");*/

	    Cookie ssokCookie;
	    /* ssokCookie = new Cookie("loggedSession", "");
	    ssokCookie.setMaxAge(0);
	    ssokCookie.setPath("/");
	    response.addCookie(ssokCookie);
	    
	    ssokCookie = new Cookie("email", "");
	    ssokCookie.setMaxAge(0);
	    ssokCookie.setPath("/");
	    response.addCookie(ssokCookie);
	    
	    ssokCookie = new Cookie("userid", "");
	    ssokCookie.setMaxAge(0);
	    ssokCookie.setPath("/");*/
	    
	    ssokCookie = new Cookie("token", "");
	    ssokCookie.setMaxAge(0);
	    ssokCookie.setPath("/");
	    /* 
	    ssokCookie = new Cookie("mf_user", "");
	    ssokCookie.setMaxAge(0);
	    ssokCookie.setPath("/");*/

	    response.addCookie(ssokCookie);



	}catch(Exception e){
	    System.out.println("Error removing session data from cookie during window close..");

	}
	session.invalidate();


    }

    @ModelAttribute("blogList")
    public Map<String, String> getBlogList() {
	Map<String, String> investmentType = new HashMap<String, String>();
	investmentType.put("why-insurance", "Why insurance?");
	investmentType.put("all-about-life-insurance", "All About Life Insurance");
	return investmentType;
    }
    @ModelAttribute("contactType")
    public Map<String, String> getContactType() {
	Map<String, String> contactType = new HashMap<String, String>();
	contactType.put("Feedback", "Feedback");
	contactType.put("Customer Service", "Customer Service");
	contactType.put("Suggestions", "Suggestions");
	return contactType;
    }


    /*	public static void main(String[] args){
		try {
			logger.info(InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/


    @RequestMapping(value = "/downloadPDF", method = RequestMethod.GET)
    public ModelAndView downloadExcel() {
	// create some sample data
	System.out.println("PDF view");
	List<Folios> folioList = new ArrayList<Folios>();
	Folios f1 = new Folios();
	f1.setFolio_No("sdfsdfsd");
	f1.setInvestorName("adadad");

	// return a view which will be resolved by an excel view resolver
	return new ModelAndView("pdfView", "folioList", folioList);
    }


    @RequestMapping(value = "/mailer/unsubscribe", method = RequestMethod.GET)
    public ModelAndView unsubscribeUserFormMailer(@Param("id") String emailid,@Param("c") String mailer_categorym,HttpServletRequest request, HttpServletResponse response) {
	// create some sample data
	logger.info("Unsubscribe user from mailer request received");
	EmailUnsubscribeForm unsubscribeform = new EmailUnsubscribeForm();

	// return a view which will be resolved by an excel view resolver
	return new ModelAndView("email-unsubscribe", "unsubscribeform", unsubscribeform);
    }

    @RequestMapping(value = "/mailer/unsubscribe.do", method = RequestMethod.POST)
    public String unsubscribeUserFormMailerDo(@ModelAttribute("unsubscribeform") EmailUnsubscribeForm form, HttpServletRequest request, HttpServletResponse response, RedirectAttributes attrs) {
	// create some sample data
	logger.info("Unsubscribe user from mailer request received do controller");

	attrs.addAttribute("emailid", form.getEmail());
	return "redirect:/mailer/unsubscribe-complete";
    }

    @RequestMapping(value = "/mailer/unsubscribe-complete", method = RequestMethod.GET)
    public String unsubscribeUserFormMailerDo(@ModelAttribute("emailid") String emailid, Model map, HttpServletRequest request, HttpServletResponse response) {
	// create some sample data
	logger.info("Unsubscribe user from mailer request complete for - "+ emailid);

	map.addAttribute("emailid", emailid);
	return "email-unsubscribe-complete";
    }

    private Cookie setSessionCookie(String cookieName, String cookieValue, boolean httponly){
	logger.info("Setting session cookie- "+ cookieName);
	Cookie ssoCookie = new Cookie(cookieName,cookieValue);
	ssoCookie.setPath("/");
	ssoCookie.setMaxAge(Integer.valueOf(env.getProperty("server.session.cookie.max-age")));
	ssoCookie.setSecure(env.getProperty("server.session.cookie.secure").equalsIgnoreCase("true")?true:false);
	ssoCookie.setHttpOnly(httponly);
	ssoCookie.setDomain(env.getProperty("server.session.cookie.domain"));
	ssoCookie.setComment(";SameSite=Strict");
	return ssoCookie;
    }



    /*	private Map<String, String> blogLinks(){
		Map<String, String> blogs = new HashMap<String,String>();
		blogs.put("Why Insuracne??", "/products/blogs/why-insurance");
		blogs.put("All about life insurance", "/products/blogs/why-insurance");

		return blogs;


	}
     */

    /*@RequestMapping(value = "/products/error", method = RequestMethod.GET)
	    public ModelAndView renderErrorPage(HttpServletRequest httpRequest) {

	        ModelAndView errorPage = new ModelAndView("errorPage");
	        String errorMsg = "";
	        int httpErrorCode = getErrorCode(httpRequest);

	        System.out.println("Error code generated- "+ httpErrorCode);
	        switch (httpErrorCode) {
	            case 400: {
	                errorMsg = "Http Error Code: 400. Bad Request";
	                break;
	            }
	            case 401: {
	                errorMsg = "Http Error Code: 401. Unauthorized";
	                break;
	            }
	            case 404: {
	                errorMsg = "Http Error Code: 404. Resource not found";
	                break;
	            }
	            case 500: {
	                errorMsg = "Http Error Code: 500. Internal Server Error";
	                break;
	            }
	        }
	        errorPage.addObject("errorMsg", errorMsg);
	        return errorPage;
	    }

	 private int getErrorCode(HttpServletRequest httpRequest) {
	        return (Integer) httpRequest
	          .getAttribute("javax.servlet.error.status_code");
	    }*/
    
    
    @ModelAttribute("contextcdn") String contextcdn() {
		return env.getProperty(CommonConstants.CDN_URL);
	}
    
    public String test(String data){
    	return data;
    }


}
