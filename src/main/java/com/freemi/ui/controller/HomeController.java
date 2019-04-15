package com.freemi.ui.controller;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
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

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.freemi.common.util.CommonConstants;
import com.freemi.common.util.CommonTask;
import com.freemi.controller.interfaces.MailSenderHandler;
import com.freemi.database.service.DatabaseEntryManager;
import com.freemi.entity.database.CampaignSignupForm;
import com.freemi.entity.general.ClientSystemDetails;
import com.freemi.entity.general.ContactUsForm;
import com.freemi.entity.general.Folios;
import com.freemi.entity.general.ForgotPassword;
import com.freemi.entity.general.Login;
import com.freemi.ui.restclient.GoogleSecurity;
import com.freemi.ui.restclient.RestClient;
import com.freemi.ui.restclient.RestClientApps;
import com.freemi.ui.restclient.RestClientBse;



@Controller
@Scope("session")
public class HomeController {

	private static final Logger logger = LogManager.getLogger(HomeController.class);

	@Autowired
	private DatabaseEntryManager databaseEntryManager ;//= (DatabaseEntryManager) BeanUtil.getBean(DatabaseEntryService.class);

	@Autowired
	MailSenderHandler mailSenderHandler;

	@Autowired
	private Environment env;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model map) {
		//logger.info("@@@@ Inside Login..");
		logger.info("@@@@ HomeController @@@@");
		map.addAttribute("contextcdn", env.getProperty(CommonConstants.CDN_URL));
		/*return "index";*/
		return "redirect:/mutual-funds/funds-explorer";
	}


	@RequestMapping(value = "/login", method = RequestMethod.GET )
	public String login(@ModelAttribute("login")Login login,@ModelAttribute("otpForm")Login otpForm, @RequestParam(name="ref",required=false)String referrerUrl,@RequestParam(name="mf",required=false)String mfStatus,Model map, HttpServletRequest request, HttpSession session) {
		//logger.info("@@@@ Inside Login..");

		logger.info("@@@@ LoginController @@@@");
		System.out.println("Referrer url if passed-"+ referrerUrl!=null?referrerUrl:request.getHeader("Referer"));



		//		map.addAttribute("login", login);
		//		logger.info("Referer- "+ request.getHeader("Referer"));
		//		model.addAttribute("returnSite", request.getHeader("Referer"));
		System.out.println("url from referrer- "+ request.getHeader("Referer"));
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
			System.out.println("Set return url- "+ login.getReturnUrl());
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

			map.addAttribute("contextcdn", env.getProperty(CommonConstants.CDN_URL));

		}else{
			logger.info("User session is already detected. Preventing another attempt of login.");
			return "redirect:"+URI.create(request.getRequestURL().toString()).resolve(request.getContextPath()).toString();
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

	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String loginAttemptPost(@ModelAttribute("login") @Valid Login login, BindingResult bindingResult, ModelMap model, HttpServletRequest request, HttpSession session) {
		logger.info("@@@@ Inside Login do..");
		//		logger.info("Referer- "+ request.getHeader("Referer"));
		//		String referer = request.getHeader("Referer");
		System.out.println("Recpcha form resuest- "+ request.getParameter("g-recaptcha-response"));

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
		System.out.println("OTP login check- "+ login.isOtpLogin());

		String returnUrl="";
		String referer = (String) session.getAttribute("returnSite");
		logger.info(referer);
		returnUrl = redirectUrlAfterLogin(referer,request);

		RestClient client = new RestClient();
		ResponseEntity<String> response = null;


		try{
			response= client.login(login.getUsermobile(), login.getUserpassword(), ip);
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
				if(uri.getRawPath().contains("/register") || uri.getRawPath().contains("/forgotPassword") || uri.getRawPath().contains("/resetPassword")){
					//					returnUrl=uri.getRawPath().split("/products/")[1].replace(".do", "");
					//					returnUrl = referer.replace(".do", "");
//					returnUrl = "redirect:/products/";
					returnUrl = /*"redirect:"+*/ URI.create(request.getRequestURL().toString()).resolve(request.getContextPath()).toString();
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
				returnUrl =  uri.getRawPath().split("/products/")[1];
			}
		}else{
			logger.debug("Referer is null");

//			returnUrl = "redirect:/products/";
			returnUrl = /*"redirect:" +*/URI.create(request.getRequestURL().toString()).resolve(request.getContextPath()).toString();
		}

		logger.info("Redirect url after login- "+ returnUrl);
		return returnUrl;
	}



	@RequestMapping(value = "/login2.do", method = RequestMethod.POST)
	@ResponseBody
	public String loginwithJqueryAttemptPost(@ModelAttribute("login") @Valid Login login,ModelMap model, BindingResult bindingResult,HttpServletRequest request, HttpSession session) {
		logger.info("@@@@ Inside Login do..");	
		//		logger.info("Referer- "+ request.getHeader("Referer"));
		//		String referer = request.getHeader("Referer");
		System.out.println("Recpcha form resuest- "+ request.getParameter("g-recaptcha-response"));

		System.out.println("Fetching after login url- "+ login.getReturnUrl() + " OTPMIT - "+ login.isOtpSubmit());

		String ip = CommonTask.getClientSystemIp(request);

		if(bindingResult.hasErrors()){
			logger.info("Error in login form");
			//			model.addAttribute("error", "Invalid form data");
			model.addAttribute("error", bindingResult.getFieldError().getDefaultMessage());
			return "Invalid form data!";
		}
		if(request.getParameter("g-recaptcha-response")==""){
			logger.info("Security token not checked");
			model.addAttribute("error", "Please check the security verification");
			return "Security Captcha token missing!";
		}else{
			if(!GoogleSecurity.verifyRecaptcha(request.getParameter("g-recaptcha-response"), "N", ip, request.getRequestURL().toString())){
				logger.warn("Security token validation failed");
				model.addAttribute("error", "Security token validation failed!");
				return "Captcha validation failed!";
			}
		}

		//		logger.info("Beginning attemptAuthentication() from IP- "+ request.getRemoteHost()+ "/"+request.getHeader("X-Forwarded-for"));
		System.out.println("OTP login check- "+ login.isOtpLogin());

		String returnUrl="";
		String referer = (String) session.getAttribute("returnSite");
		System.out.println("login2.do referer- "+ referer);
		//		returnUrl = redirectUrlAfterLogin(referer);

		RestClient client = new RestClient();
		ResponseEntity<String> response = null;


		if(!login.isOtpSubmit()){

			if(login.isOtpLogin()){
				//			Process for OTP login
				try{

					//			Check if user exists and fetch email id

					response = client.validateuserIdAndGetMail(login.getUsermobile());
					String[] userStatus = response.getBody().toString().split(",");

					System.out.println(Arrays.asList(userStatus));
					if(userStatus[0].equalsIgnoreCase("VALID")){
						if(!userStatus[1].equals("NO_EMAIL")){
							String resultotp= RestClientBse.otpGeneration(login.getUsermobile());
							System.out.println("RECEIVED OTP RESPONSE: "+ resultotp);
							if(resultotp.contains("OTP")){
								//				Trigger mail 
								session.setAttribute("OTP", resultotp.split("=")[1]);
								try {
									mailSenderHandler.loginOTPMail(login.getUsermobile(), resultotp.split("=")[1], userStatus[1], "5");
									returnUrl="OTP_SENT";
								} catch (InterruptedException e) {
									logger.error("Failed to send mail for OTP- ",e);
								}

								returnUrl="OTP_SENT";
								login.setOtpSubmit(true);

							}
						}else{
							returnUrl = "No email to OTP. Kindly contact admin";
						}
					}else{
						returnUrl = "Invalid user id";
					}

				}catch(HttpStatusCodeException  e){
					logger.error("BSESERVICE LOGIN OTP service connection ailure - " ,e);
					if(e.getRawStatusCode()==401)
						returnUrl= "Invalid userid or password";
					if(e.getRawStatusCode() == 500)
						returnUrl="Unable to connect to service";
					if(e.getRawStatusCode() == 404)
						returnUrl="Service url down or not found!";

					//				returnUrl = Integer.toString(e.getRawStatusCode());
				}catch(Exception e){
					logger.error("BSESERVICE LOGIN OTP Error while trying to generate OTP",e);
					model.addAttribute("error", "Unable to process request currently");
					returnUrl="Internal error. Kindly contact admin";
				}

			}else{
				//				Direct login process with userid-password
				try{

					response= client.login(login.getUsermobile(), login.getUserpassword(), ip);
					//			model.addAttribute("token",response.getHeaders().get("Authorization").get(0));
					//			model.addAttribute("loggedInUser",response.getHeaders().get("fname").get(0).split(" ")[0]);

					session.setAttribute("loggedSession", response.getHeaders().get("fname").get(0).split(" ")[0]);
					session.setAttribute("token", response.getHeaders().get("Authorization").get(0));
					session.setAttribute("userid", response.getHeaders().get("userid").get(0));
					session.setAttribute("email",response.getHeaders().get("email").get(0));

//					Set session for other applciations
//					RestClientApps.setAllAppSession( response.getHeaders().get("userid").get(0), response.getHeaders().get("email").get(0), response.getHeaders().get("fname").get(0).split(" ")[0], response.getHeaders().get("Authorization").get(0));
					
					try{
					ServletContext servletContext =request.getSession().getServletContext().getContext("/{applicationContextRoot}");
					servletContext.setAttribute("loggedSession", response.getHeaders().get("fname").get(0).split(" ")[0]);
					servletContext.setAttribute("token", response.getHeaders().get("Authorization").get(0));
					servletContext.setAttribute("userid", response.getHeaders().get("userid").get(0));
					servletContext.setAttribute("email",response.getHeaders().get("email").get(0));
					}catch(Exception e){
						System.out.println("Error sessting servlet context");
					}
					
					logger.info(response.getHeaders().get("Authorization").get(0));
					returnUrl="SUCCESS";
				}
				catch(HttpStatusCodeException  e){
					logger.error("Connection failure - " ,e);
					if(e.getRawStatusCode()==401)
						returnUrl= "Invalid userid or password";
					if(e.getRawStatusCode() == 500)
						returnUrl="Unable to connect to server";
					if(e.getRawStatusCode() == 404)
						returnUrl="Service url down or not found!";

					//				returnUrl = Integer.toString(e.getRawStatusCode());
				}catch(Exception e){
					logger.error("Error while trying to login",e);
					model.addAttribute("error", "Unable to process request currently");
					returnUrl="Internal error. Kindly contact admin";
				}
			}

		}else{
			//			OTP process verification

			logger.info("Process OTP submit verfication for mobile number- "+ login.getUsermobile());

			String resultotp2="OTP_INVALID";
			resultotp2= RestClientBse.otpverify(login.getUsermobile(), login.getOtpVal());
			/*String otpFromSession = (String) session.getAttribute("OTP");
			if(otpFromSession!=null){
				if(session.getAttribute("OTP").toString().equalsIgnoreCase(login.getOtpVal())){
					resultotp2="Entered Otp is valid";
					session.removeAttribute("OTP");
				}

			}else{
				resultotp2="OTP_INVALIDATED";
			}*/

			logger.info("OTP validation respond- "+ resultotp2);
			if(resultotp2.equalsIgnoreCase("Entered Otp is valid")){
				//				Generate login session

				try {
					response= client.otpLogin(login);
					if(response.getBody().toString().equalsIgnoreCase("SUCCESS")){
						session.setAttribute("loggedSession", response.getHeaders().get("fname").get(0).split(" ")[0]);
						session.setAttribute("token", response.getHeaders().get("Authorization").get(0));
						session.setAttribute("userid", response.getHeaders().get("userid").get(0));
						session.setAttribute("email",response.getHeaders().get("email").get(0));

						logger.info(response.getHeaders().get("Authorization").get(0));

						returnUrl="SUCCESS";
					}else{
						returnUrl="OTP_LOGIN_FAIL";
					}
				} catch (JsonProcessingException e) {
					logger.error("Failed to parse data",e);
					returnUrl="OTP_VALID_FAIL";
				}catch(Exception e){
					returnUrl="OTP_VALID_FAIL";
					logger.error("Failed to process OTP login",e);
				}
				//			model.addAttribute("token",response.getHeaders().get("Authorization").get(0));
				//			model.addAttribute("loggedInUser",response.getHeaders().get("fname").get(0).split(" ")[0]);



			}else{
				returnUrl="OTP_INVALID";
			}

			//			returnUrl="SUCCESS";
		}

		model.addAttribute("error", "1st Attempt..");
		logger.info("Returning to URL- "+ returnUrl);
		return returnUrl;

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
		System.out.println("OTP login check- "+ login.isOtpLogin());

		String returnUrl="";
		String referer = (String) session.getAttribute("returnSite");
		logger.info(referer);
		returnUrl = redirectUrlAfterLogin(referer,request);

		RestClient client = new RestClient();
		ResponseEntity<String> response = null;


		try{
			response= client.otpLogin(login);
			//			model.addAttribute("token",response.getHeaders().get("Authorization").get(0));
			//			model.addAttribute("loggedInUser",response.getHeaders().get("fname").get(0).split(" ")[0]);
			if(response.getBody().equalsIgnoreCase("SUCCESS")){
				session.setAttribute("loggedSession", response.getHeaders().get("fname").get(0).split(" ")[0]);
				session.setAttribute("token", response.getHeaders().get("Authorization").get(0));
				session.setAttribute("userid", response.getHeaders().get("userid").get(0));
				session.setAttribute("email",response.getHeaders().get("email").get(0));
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


	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(ModelMap model, HttpServletRequest request,HttpServletResponse response, HttpSession session) {
		//logger.info("@@@@ Inside Login..");
		logger.info("@@@@ LogoutController @@@@");

		session.removeAttribute("loggedSession");
		session.removeAttribute("token");
		session.removeAttribute("userid");
		session.removeAttribute("email");
		
		try{
			logger.info("Clear loggin data from servlet context...");
			ServletContext servletContext =request.getSession().getServletContext().getContext("/{applicationContextRoot}");
			servletContext.removeAttribute("loggedSession");
			servletContext.removeAttribute("token");
			servletContext.removeAttribute("userid");
			servletContext.removeAttribute("email");
			
			
			}catch(Exception e){
				System.out.println("Error removing session data from servlet context during logout..");
		
			}
		session.invalidate();
		
		RestClientApps.logoutAllApplication("", "", "", "");
		return "redirect:/";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String logoutPost(ModelMap model, HttpSession session) {
		//logger.info("@@@@ Inside Login..");
		return "redirect:/logout";
	}



	@RequestMapping(value = "/forgotPassword", method = RequestMethod.GET)
	public String forgotPasswordDisplay(Model map) {
		//logger.info("@@@@ Inside Login..");
		logger.info("@@@@ ForgotPasswordController @@@@");
		map.addAttribute("forgotPasswordForm", new ForgotPassword());
		map.addAttribute("contextcdn", env.getProperty(CommonConstants.CDN_URL));

		return "forgotPassword";
	}

	@RequestMapping(value = "/forgotPassword.do", method = RequestMethod.GET)
	public String forgotPasswordGetSubmit(ModelMap model) {
		return "redirect:/forgotPassword";
	}

	@RequestMapping(value = "/forgotPassword.do", method = RequestMethod.POST)
	public String forgotPasswordSubmit(@ModelAttribute("forgotPasswordForm") @Valid ForgotPassword forgotPasswordForm, BindingResult bindingResult, Model model, HttpServletRequest request, HttpServletResponse resp) {
		logger.info("@@@@ Forgot password request submit..");

		if(bindingResult.hasErrors()){
			logger.info("Error in forgot form");
			model.addAttribute("error", bindingResult.getFieldError().getDefaultMessage());
			return "forgotPassword";
		}
		if(request.getParameter("g-recaptcha-response")==""){
			logger.info("Security token not checked");
			model.addAttribute("error", "Please check the security verification");
			return "forgotPassword";
		}else{
			if(!GoogleSecurity.verifyRecaptcha(request.getParameter("g-recaptcha-response"), "Y", CommonTask.getClientSystemIp(request), request.getRequestURL().toString())){
				logger.warn("Security token validation failed");
				model.addAttribute("error", "Security token validation failed!");
				return "forgotPassword";
			}
		}

		RestClient client = new RestClient();
		ResponseEntity<String> response = null;
		try {
			response = client.forgotPassword(forgotPasswordForm);
			logger.info(response.getBody());
			//			logger.info(response.getHeaders());
			model.addAttribute("success", "Password reset mail sent on registered email id.");
		}catch(HttpStatusCodeException  e){
			logger.info("test failure - " + e.getStatusCode());
			model.addAttribute("error", "Unable to process request curretnly");
		} catch (JsonProcessingException e) {
			model.addAttribute("error","Invalid form data");
		}catch(Exception e){
			model.addAttribute("error","Error processing request");
		}

		//		model.addAttribute("forgotPasswordForm", forgotPasswordForm);

		return "forgotPassword";
	}


	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public String contact( Model map, HttpServletRequest request, HttpSession session) {
		logger.info("@@@@ ContactController @@@@");

		ContactUsForm contactForm =new ContactUsForm();
		//		String 
		map.addAttribute("contactForm", contactForm);
		map.addAttribute("contextcdn", env.getProperty(CommonConstants.CDN_URL));

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
		RestClient client = new RestClient();
		ResponseEntity<String> response = null;
		try {
			response = client.contactUs(contactForm);
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
		map.addAttribute("contextcdn", env.getProperty(CommonConstants.CDN_URL));
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
				RestClient client = new RestClient();
				ResponseEntity<String> response = null;
				try {
					response = client.campaignSingUp(campaign);
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


}
