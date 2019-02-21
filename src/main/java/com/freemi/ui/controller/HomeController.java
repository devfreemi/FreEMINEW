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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.freemi.common.util.CommonConstants;
import com.freemi.common.util.CommonTask;
import com.freemi.database.service.DatabaseEntryManager;
import com.freemi.entity.database.CampaignSignupForm;
import com.freemi.entity.general.ClientSystemDetails;
import com.freemi.entity.general.ContactUsForm;
import com.freemi.entity.general.Folios;
import com.freemi.entity.general.ForgotPassword;
import com.freemi.entity.general.Login;
import com.freemi.ui.restclient.GoogleSecurity;
import com.freemi.ui.restclient.RestClient;



@Controller
@Scope("session")
public class HomeController {
	
	private static final Logger logger = LogManager.getLogger(HomeController.class);

	@Autowired
	private DatabaseEntryManager databaseEntryManager ;//= (DatabaseEntryManager) BeanUtil.getBean(DatabaseEntryService.class);
	
	@Autowired
	private Environment env;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model map) {
		//logger.info("@@@@ Inside Login..");
		logger.info("@@@@ HomeController @@@@");
		map.addAttribute("contextcdn", env.getProperty(CommonConstants.CDN_URL));
		/*return "index";*/
		return "redirect:/registry-mutual-funds";
	}


	@RequestMapping(value = "/login", method = RequestMethod.GET )
	public String login(@RequestParam(name="ref",required=false)String referrerUrl,@RequestParam(name="mf",required=false)String msStatus,Model map, HttpServletRequest request, HttpSession session) {
		//logger.info("@@@@ Inside Login..");
		
		logger.info("@@@@ LoginController @@@@");
		System.out.println("Referrer url if passed-"+ referrerUrl!=null?referrerUrl:request.getHeader("Referer"));
		map.addAttribute("login", new Login());
		//		logger.info("Referer- "+ request.getHeader("Referer"));
		//		model.addAttribute("returnSite", request.getHeader("Referer"));
		System.out.println("url from referrer- "+ request.getHeader("Refrer"));
		try {
			session.setAttribute("returnSite", referrerUrl!=null?URLDecoder.decode(referrerUrl, StandardCharsets.UTF_8.toString()):request.getHeader("Referer"));
		} catch (UnsupportedEncodingException e) {
			logger.error("Failed to decode string",e);
		}
		map.addAttribute("contextcdn", env.getProperty(CommonConstants.CDN_URL));
		return "login";
	}

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
		String returnUrl="";
		String referer = (String) session.getAttribute("returnSite");
		logger.info(referer);
		if(referer!= null && !referer.isEmpty()){
			URL url = null;
			try {
				url = new URL(referer);
				URI uri = url.toURI();
				if(uri.getRawPath().contains("/register") || uri.getRawPath().contains("/forgotPassword") || uri.getRawPath().contains("/resetPassword")){
					returnUrl="redirect:/";
				}else
					returnUrl = "redirect:/"+ uri.getRawPath().split("/products/")[1];
//					returnUrl = "redirect:/"+uri.getRawPath();
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				//				e1.printStackTrace();
				logger.error("Logincontroller post: Failed to form the URL",e1);
				returnUrl = "redirect:/";
			}catch(ArrayIndexOutOfBoundsException e){
				returnUrl = "redirect:/";
			}catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				//				e.printStackTrace();
				returnUrl = "redirect:/";
			}catch (Exception e) {
				// TODO Auto-generated catch block
				//				e.printStackTrace();
				returnUrl = "redirect:/";
			}
		}else{
			logger.info("Referer is null");
			returnUrl = "redirect:/";
		}

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
			logger.error(e.getMessage());
			model.addAttribute("error", "Unable to process request currently");
			returnUrl="login";
		}
		logger.info("Returning to URL- "+ returnUrl);
		return returnUrl;
	}



	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(ModelMap model, HttpSession session) {
		//logger.info("@@@@ Inside Login..");
		logger.info("@@@@ LogoutController @@@@");

		session.removeAttribute("loggedSession");
		session.removeAttribute("token");
		session.removeAttribute("userid");
		session.removeAttribute("email");
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
	
}
