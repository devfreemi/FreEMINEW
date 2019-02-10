package com.freemi.ui.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.HttpStatusCodeException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.freemi.common.util.CommonConstants;
import com.freemi.common.util.CommonTask;
import com.freemi.common.util.InvestFormConstants;
import com.freemi.database.service.BseEntryManager;
//import com.freemi.database.service.BseEntryManager;
import com.freemi.entity.general.ProfilePasswordChangeForm;
import com.freemi.entity.general.ResetPassword;
import com.freemi.entity.general.UserProfile;
import com.freemi.entity.investment.BseAllTransactionsView;
//import com.freemi.entity.investment.BseAllTransactionsView;
import com.freemi.ui.restclient.GoogleSecurity;
import com.freemi.ui.restclient.RestClient;

@Controller
@Scope("session")
@SessionAttributes({"profileBasic","profileAccount","profileAddress"})
public class ProfileManageController{
	

	@Autowired
	private Environment environment;
	
	@Autowired
	BseEntryManager bseEntryManager;
	
	private static final Logger logger = LogManager.getLogger(ProfileManageController.class);

	
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String getProfile(Model model, HttpSession session, HttpServletRequest request) {
		logger.info("@@@@ Get profile details..");
		String returnurl = "";
		int error = 0;
		RestClient client = new RestClient();
		ResponseEntity<String> response = null;
		if(session.getAttribute("token") == null){
			returnurl="redirect:/login";
		}else{
			returnurl="profile";
			
//			User Profile collection from LDAP			
			try {
				response = client.getProfileData(session.getAttribute("userid").toString(), session.getAttribute("token").toString(), CommonTask.getClientSystemDetails(request).getClientIpv4Address());
//				logger.info(response.getBody());
//				logger.info(response.getHeaders());
				System.out.println(response.getBody());
				UserProfile profile = new ObjectMapper().readValue(response.getBody(), UserProfile.class);
//				logger.info(profile.getGender());
				
				model.addAttribute("profileBasic", profile);
				model.addAttribute("profileAccount", profile);
				model.addAttribute("profileAddress", profile);
				model.addAttribute("profilePasswordChangeForm", new ProfilePasswordChangeForm());
				logger.info(profile.getMobile());
			}catch(HttpStatusCodeException  e){
				logger.info("Unable to fetch profile details - " + e.getStatusCode());
				if(e.getStatusCode().value() == 401){
					model.addAttribute("error", "Token validation failed. Please check if it expired");
				}else{
				model.addAttribute("error", "Unable to process request curretnly");
				}
				error =1;
			} catch (JsonProcessingException e) {
				model.addAttribute("error","Invalid form data");
				error = 1;
			}catch(Exception e){
//				e.printStackTrace();
				model.addAttribute("error","Error processing request");
				error =1;
			}
			
			
//			User profile data collection from DB
			/*try{
				UserProfile profile = bseEntryManager.getCustomerDetailsByMobile(session.getAttribute("userid").toString());
				
				model.addAttribute("profileBasic", profile);
				model.addAttribute("profileAccount", profile);
				model.addAttribute("profileAddress", profile);
				model.addAttribute("profilePasswordChangeForm", new ProfilePasswordChangeForm());
				model.addAttribute("states", InvestFormConstants.states);
				model.addAttribute("bankNames", InvestFormConstants.bankNames);
				model.addAttribute("accountTypes", InvestFormConstants.accountTypes);
			}catch(Exception e){
				logger.error("Unable to fetch customer profile data from DB",e);
				error =1;
			}
			*/
			if(error == 1){
				model.addAttribute("error","Sorry. Unable to fetch your details currently.");
				model.addAttribute("profileBasic", new UserProfile());
				model.addAttribute("profileAccount", new UserProfile());
				model.addAttribute("profileAddress", new UserProfile());
				model.addAttribute("profilePasswordChangeForm", new ProfilePasswordChangeForm());
			}
			
			
		}
		
		model.addAttribute("contextcdn", environment.getProperty(CommonConstants.CDN_URL));
		logger.info("@@@@ ProfileController complete. @@@@");
		return returnurl;
	}

	@RequestMapping(value = "/profileBasic.do", method = RequestMethod.POST)
	public String postProfileBasicDo(@ModelAttribute("profileBasic") UserProfile profile,@ModelAttribute("profileAccount") UserProfile profileAccount,@ModelAttribute("profileAddress") UserProfile profileAddress ,@ModelAttribute("profilePasswordChangeForm") ProfilePasswordChangeForm passChangeForm,Model model, HttpSession session, HttpServletRequest request) {
		
		logger.info("@@@@ ProfileBasicDoController @@@@");
		String returnurl="";
		RestClient client = new RestClient();
		ResponseEntity<String> response = null;
		if(session.getAttribute("token") == null){
			returnurl="redirect:/login";
		}else{
			returnurl="profile";
			
//			Profile data change to ldap server
			try {
				response = client.updateProfileData(profile,session.getAttribute("userid").toString(), session.getAttribute("token").toString(),CommonTask.getClientSystemDetails(request).getClientIpv4Address());
				System.err.println(response.getBody());
//				logger.info(profile.getMobile());
				model.addAttribute("success", "User profile updated successfully");
				
				model.addAttribute("profileBasic", profile);
				model.addAttribute("states", InvestFormConstants.states);
				model.addAttribute("bankNames", InvestFormConstants.bankNames);
				model.addAttribute("accountTypes", InvestFormConstants.accountTypes);
				
			}catch(HttpStatusCodeException  e){
				logger.info("test failure - " + e.getStatusCode());
				model.addAttribute("error", "Unable to process request curretnly");
			} catch (JsonProcessingException e) {
				model.addAttribute("error","Invalid form data");
			}catch(Exception e){
				model.addAttribute("error","Error processing request");
			}finally{
				logger.info("entering finally");
			}
			
		}
		
		return returnurl;
	}
	
	@RequestMapping(value = "/profileAccount.do", method = RequestMethod.POST)
	public String postProfileAccountDo(@ModelAttribute("profileBasic") UserProfile profile,@ModelAttribute("profileAccount") UserProfile profileAccount,@ModelAttribute("profileAddress") UserProfile profileAddress ,@ModelAttribute("profilePasswordChangeForm") ProfilePasswordChangeForm passChangeForm,Model model, HttpSession session, HttpServletRequest request) {

		logger.info("@@@@ ProfileAccountDoController @@@@");
		String returnurl="";
		RestClient client = new RestClient();
		ResponseEntity<String> response = null;
		if(session.getAttribute("token") == null){
			returnurl="redirect:/login";
		}else{
			returnurl="profile";
			
//			Updating profile details into LDAP database
			try {
				response = client.updateProfileData(profileAccount,session.getAttribute("userid").toString(), session.getAttribute("token").toString(),CommonTask.getClientSystemDetails(request).getClientIpv4Address());
				if(response.getBody().equals("SUCCESS")){
					model.addAttribute("success", "Bank Account Details updated successfully");
				}
			}catch(HttpStatusCodeException  e){
				logger.info("test failure - " + e.getStatusCode());
				model.addAttribute("error", "Unable to process request curretnly");
			} catch (JsonProcessingException e) {
				model.addAttribute("error","Invalid form data");
			}catch(Exception e){
				model.addAttribute("error","Error processing request");
			}finally{
				logger.info("entering finally");
			}
			
			
//			Updating profile details in DB
			/*try{
				bseEntryManager.updateCustomerBankDetails(profileAccount);
				model.addAttribute("success", "Bank Account Details updated successfully");
				
				model.addAttribute("accountTypes", InvestFormConstants.accountTypes);
			}catch(Exception e){
				logger.error("Failed to update Investor bank details in profile", e);
				model.addAttribute("error", "Failed to update your bank details. Please contact admin.");
			}*/
			
		}
		
		model.addAttribute("profileBasic", profile);
		model.addAttribute("profileAddress", profileAddress);
		
		model.addAttribute("states", InvestFormConstants.states);
		model.addAttribute("bankNames", InvestFormConstants.bankNames);
		
		return returnurl;
	}
	
	@RequestMapping(value = "/profileAddress.do", method = RequestMethod.POST)
	public String postProfileAddressDo(@ModelAttribute("profileBasic") UserProfile profile,@ModelAttribute("profileAccount") UserProfile profileAccount,@ModelAttribute("profileAddress") UserProfile profileAddress ,@ModelAttribute("profilePasswordChangeForm") ProfilePasswordChangeForm passChangeForm,Model model, HttpSession session) {

		logger.info("@@@@ ProfileAddressDoController @@@@");
		
		
//		Save through Database
		/*try{
			bseEntryManager.updateCustomerAddress(profileAddress);
			model.addAttribute("success", "Address updated successfully");
			
		}catch(Exception e){
			logger.error("Failed to update Investor address in profile", e);
			model.addAttribute("error", "Failed to update Investor address. Please contact admin.");
		}*/
		
		model.addAttribute("profileBasic", profile);
		model.addAttribute("profileAccount", profileAccount);
		
		model.addAttribute("states", InvestFormConstants.states);
		model.addAttribute("bankNames", InvestFormConstants.bankNames);
		model.addAttribute("accountTypes", InvestFormConstants.accountTypes);
		
		return "profile";
	}

	@RequestMapping(value = "/profileBasic.do", method = RequestMethod.GET)
	public String getProfileDo(ModelMap model) {
		return "redirect:/profile";
	}
	
	
	// This functionality is through profile management after user is logged in.
	@RequestMapping(value = "/changePassword.do", method = RequestMethod.POST)
	public String postPassChangeDo(@ModelAttribute("profileBasic") UserProfile profile,@ModelAttribute("profileAccount") UserProfile profileAccount,@ModelAttribute("profileAddress") UserProfile profileAddress ,@ModelAttribute("profilePasswordChangeForm") ProfilePasswordChangeForm passChangeForm, Model model, HttpSession session, HttpServletRequest request, HttpServletResponse resp) {

		logger.info("@@@@ ProfilePasswordChange @@@@");
		String returnurl="";
		RestClient client = new RestClient();
		ResponseEntity<String> response = null;
		if(session.getAttribute("token") == null){
			returnurl="redirect:/login";
		}else{
			returnurl="profile";
			try {
				response = client.updateProfilePassword(passChangeForm,session.getAttribute("userid").toString(), session.getAttribute("token").toString(),CommonTask.getClientSystemDetails(request).getClientIpv4Address());
//				System.err.println(response.getBody());
				logger.info(response.getBody());
				if(response.getBody().equals("SUCCESS")){
					model.addAttribute("success", "Password updated successfully");
				}
				
			}catch(HttpStatusCodeException  e){
				logger.info("test failure - " + e.getStatusCode());
				model.addAttribute("error", "Unable to process request curretnly");
			} catch (JsonProcessingException e) {
				model.addAttribute("error","Invalid form data");
			}catch(Exception e){
				model.addAttribute("error","Error processing request");
			}finally{
				logger.info("entering finally");
			}
			
			
		}
		
		return returnurl;
	}
	

	@RequestMapping(value = "/my-dashboard/", method = RequestMethod.GET)
	public String getMyDashboardAlt(Model map,HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		return "redirect:/my-dashboard";
	
	}
	
	@RequestMapping(value = "/my-dashboard", method = RequestMethod.GET)
	public String getMyDashboard(Model map,HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		//logger.info("@@@@ Inside Login..");
		String returnurl = "";
		double totalAsset= 0;
		if(session.getAttribute("token") == null){
			returnurl="redirect:/login";
		}else{
			returnurl = "my-dashboard";
			// Get user's MF order history
			//This code is for new design. COmment out until deployed in prod
			try{
			List<BseAllTransactionsView> fundsOrder= bseEntryManager.getCustomerAllTransactionRecords(null,session.getAttribute("userid").toString(),null);
			if(fundsOrder.size()>=1){
			for(int i=0;i<fundsOrder.size();i++){
				totalAsset+=fundsOrder.get(i).getSchemeInvestment();
			}
			map.addAttribute("mforderhistory", fundsOrder);
			map.addAttribute("ORDERHISTORY", "SUCCESS");
			}else{
				map.addAttribute("ORDERHISTORY", "EMPTY");
			}
			}catch(Exception ex){
				map.addAttribute("ORDERHISTORY", "ERROR");
				logger.error("Failed to fetch cutomer Registry details \n", ex);
			}
			
//			Get Profile AOF Status
			String aofstatus=bseEntryManager.investmentProfileStatus(session.getAttribute("userid").toString());
			map.addAttribute("PROFILE_STATUS", aofstatus);
//			map.addAttribute("PROFILE_STATUS", "NOT_FOUND");
			
		}
		
		
		
		map.addAttribute("totalasset", totalAsset);
		map.addAttribute("contextcdn", environment.getProperty(CommonConstants.CDN_URL));
		logger.info("@@@@ DashboardController @@@@");
		return returnurl;
//		return "my-dashboard";
	}
	
	
	@RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
	public String resetPasswordValidator(@RequestParam("env")String env,@RequestParam("user")String user, @RequestParam("token")String token,Model map,HttpServletRequest request,HttpServletResponse response, HttpSession session) {
		logger.info("@@@@ resetPasswordController");
		logger.info("Forgot password reset request received from ip- "+ CommonTask.getClientSystemIp(request));
		logger.info("Reset password details- [user: "+user+"]"+" [token: "+token+"]");
		String returnurl = "";
//		logger.info(env + " " + user + " "+ token);
		
		if(token == "" || user == "" || env == ""){
			returnurl="redirect:/login";
		}else{
			//validate token
			RestClient client = new RestClient();
			ResponseEntity<String> responseEntity = null;
			
			try {
				responseEntity = client.validateResetPasswordToken(user, token, CommonTask.getClientSystemIp(request));
				logger.info("Token validations status received");
				logger.info("Validataion status for token of user - "+user +" --> "+responseEntity.getBody());
//				logger.info(profile.getMobile());
				
				if(!responseEntity.getBody().equalsIgnoreCase("VALID")){
					map.addAttribute("STATUS", "N");
					map.addAttribute("PWDCHANGE", "N");
					map.addAttribute("error", "Token validation failed.");
				}else{
					map.addAttribute("STATUS", "Y");
					map.addAttribute("PWDCHANGE", "N");
					map.addAttribute("success", "Set your new password");
					session.setAttribute("user", user);
					session.setAttribute("token", token);
				}
			}catch(HttpStatusCodeException  e){
				logger.info("Connection failure to reset password token validation link - " + e.getStatusCode());
				map.addAttribute("STATUS", "N");
				if( e.getStatusCode().value() == 401){
					map.addAttribute("error", "Token valiation failed. Please check if it exprired.");
				}else{
					map.addAttribute("error", "Unable to process request curretnly");
				}
			} catch (JsonProcessingException e) {
				map.addAttribute("STATUS", "N");
				map.addAttribute("error","Invalid form data");
			}catch(Exception e){
				logger.error("Error proceeing reset password token validation \n " + e.getMessage());
				e.printStackTrace();
				map.addAttribute("STATUS", "N");
				map.addAttribute("error","Error processing request");
			}	
			map.addAttribute("resetPassword", new ResetPassword());
			map.addAttribute("userid", user);
			
			map.addAttribute("contextcdn", environment.getProperty(CommonConstants.CDN_URL));
			returnurl = "reset-password";
		}
		
		return returnurl;
//		return "my-dashboard";
	}
	
	
	@RequestMapping(value = "/resetPassword.do", method = RequestMethod.GET)
	public String resetPasswordGetSubmit(ModelMap model) {
		return "redirect:/resetPassword";
	}

	/*@RequestMapping(value = "/resetPassword.do", method = RequestMethod.POST)
	public String resetPasswordSubmit(ModelMap model) {
		//logger.info("@@@@ Inside Login..");
		logger.info("@@@@ ResetPasswordDoController @@@@");
//		return "reset-password";
		return "redirect:/resetPassword";
	}*/
	
	@RequestMapping(value = "/resetPassword.do", method = RequestMethod.POST)
	public String ForgotPasswordResetSubmit(@ModelAttribute("resetPassword")ResetPassword resetPassForm,BindingResult bindingResult,Model model,HttpServletRequest request,HttpServletResponse response, HttpSession session) {
		//logger.info("@@@@ Inside Login..");
		logger.info("@@@@ ForgotPasswordresetController @@@@");
		System.out.println(request.getQueryString());
		String returnurl="";
		RestClient client = new RestClient();
		
		ResponseEntity<String> responseEntity = null;
		
		if(!resetPassForm.getNewpassword().equalsIgnoreCase(resetPassForm.getConfirmpassword())){
			model.addAttribute("STATUS", "N");
			model.addAttribute("error", "Confirm password do not match");
			return "reset-password";
		}
		if(bindingResult.hasErrors()){
			logger.info("Error in login form");
//			model.addAttribute("error", "Invalid form data");
			model.addAttribute("STATUS", "N");
			model.addAttribute("error", bindingResult.getFieldError().getDefaultMessage());
			return "reset-password";
		}
		if(request.getParameter("g-recaptcha-response")==""){
			logger.info("Security token not checked");
			model.addAttribute("STATUS", "N");
			model.addAttribute("error", "Please check the security verification");
			return "reset-password";
		}
		else{
			if(!GoogleSecurity.verifyRecaptcha(request.getParameter("g-recaptcha-response"), "Y", CommonTask.getClientSystemIp(request), request.getRequestURL().toString())){
				logger.warn("Security token validation failed");
				model.addAttribute("STATUS", "N");
				model.addAttribute("error", "Security token validation failed!");
				return "reset-password";
			}
		}
		
		if(session.getAttribute("token") == null){
			returnurl="redirect:/login";
		}else{
			returnurl="reset-password";
			try {
				responseEntity = client.forgotPasswordUpdate(resetPassForm,session.getAttribute("user").toString(), session.getAttribute("token").toString(),CommonTask.getClientSystemDetails(request).getClientIpv4Address());
//				System.err.println(response.getBody());
				logger.info(responseEntity.getBody());
				if(responseEntity.getBody().equals("SUCCESS")){
					model.addAttribute("STATUS", "Y");
					model.addAttribute("PWDCHANGE", "S");
					model.addAttribute("success", "Your account password updated successfully");
					model.addAttribute("resetPassword", new ResetPassword());
				}
				
			}catch(HttpStatusCodeException  e){
				logger.info("test failure - " + e.getStatusCode());
				
				model.addAttribute("error", "Unable to process request curretnly");
			} catch (JsonProcessingException e) {
				model.addAttribute("STATUS", "N");
				model.addAttribute("error","Invalid form data");
			}catch(Exception e){
				model.addAttribute("STATUS", "N");
				model.addAttribute("error","Error processing request");
			}
		}
		
		return returnurl;
	}
	
	
/*
	@RequestMapping(value = "/blogs", method = RequestMethod.GET)
	public String getBlogs(ModelMap model) {
		//logger.info("@@@@ Inside Login..");
		logger.info("@@@@ BlogsController @@@@");
		return "blogs";
	}
	*/
		
	@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseBody
	public String handleMissingParams(MissingServletRequestParameterException ex) {
	    String name = ex.getParameterName();
	    logger.info(name + " parameter is missing");
	    // Actual exception handling
		return "BAD REQUEST.";
	    
	}

}
