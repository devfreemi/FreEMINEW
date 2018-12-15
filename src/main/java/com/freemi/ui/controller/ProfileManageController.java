package com.freemi.ui.controller;

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
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpStatusCodeException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.freemi.common.util.CommonConstants;
import com.freemi.common.util.CommonTask;
import com.freemi.entity.general.ProfilePasswordChangeForm;
import com.freemi.entity.general.ResetPassword;
import com.freemi.entity.general.UserProfile;
import com.freemi.ui.restclient.RestClient;

@Controller
@Scope("session")
public class ProfileManageController{
	

	@Autowired
	private Environment environment;
	
	private static final Logger logger = LogManager.getLogger(ProfileManageController.class);

	
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String getProfile(ModelMap model, HttpSession session, HttpServletRequest request) {
		//logger.info("@@@@ Inside Login..");
		String returnurl = "";
		RestClient client = new RestClient();
		ResponseEntity<String> response = null;
		if(session.getAttribute("token") == null){
			returnurl="redirect:/login";
		}else{
			returnurl="profile";
			try {
				response = client.getProfileData(session.getAttribute("userid").toString(), session.getAttribute("token").toString(), CommonTask.getClientSystemDetails(request).getClientIpv4Address());
//				logger.info(response.getBody());
//				logger.info(response.getHeaders());
				System.err.println(response.getBody());
				UserProfile profile = new ObjectMapper().readValue(response.getBody(), UserProfile.class);
//				logger.info(profile.getGender());
				
				model.addAttribute("profileBasic", profile);
				model.addAttribute("profileAccount", profile);
				model.addAttribute("profileAddress", profile);
				model.addAttribute("profilePasswordChangeForm", new ProfilePasswordChangeForm());
				logger.info(profile.getMobile());
			}catch(HttpStatusCodeException  e){
				logger.info("test failure - " + e.getStatusCode());
				model.addAttribute("error", "Unable to process request curretnly");
			} catch (JsonProcessingException e) {
				model.addAttribute("error","Invalid form data");
			}catch(Exception e){
//				e.printStackTrace();
				model.addAttribute("error","Error processing request");
			}finally{
				logger.info("entering finally");
//				model.addAttribute("profileBasic", new UserProfile());
//				model.addAttribute("profileAccount", new UserProfile());
//				model.addAttribute("profileAddress", new UserProfile());
			}
			
			
		}
		
		
		logger.info("@@@@ ProfileController @@@@");
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
			try {
				response = client.updateProfileData(profile,session.getAttribute("userid").toString(), session.getAttribute("token").toString(),CommonTask.getClientSystemDetails(request).getClientIpv4Address());
				System.err.println(response.getBody());
//				logger.info(profile.getMobile());
				model.addAttribute("success", "User profile updated successfully");
				
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
			try {
				response = client.updateProfileData(profileAccount,session.getAttribute("userid").toString(), session.getAttribute("token").toString(),CommonTask.getClientSystemDetails(request).getClientIpv4Address());
//				System.err.println(response.getBody());
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
			
			
		}
		
		return returnurl;
	}
	
	@RequestMapping(value = "/profileAddress.do", method = RequestMethod.POST)
	public String postProfileAddressDo(@ModelAttribute("profileBasic") UserProfile profile,@ModelAttribute("profileAccount") UserProfile profileAccount,@ModelAttribute("profileAddress") UserProfile profileAddress ,@ModelAttribute("profilePasswordChangeForm") ProfilePasswordChangeForm passChangeForm,Model model, HttpSession session) {

		logger.info("@@@@ ProfileAddressDoController @@@@");
		return "profile";
	}

	@RequestMapping(value = "/profileBasic.do", method = RequestMethod.GET)
	public String getProfileDo(ModelMap model) {
		return "redirect:/profile";
	}
	
	
	@RequestMapping(value = "/changePassword.do", method = RequestMethod.POST)
	public String postPassChangeDo(@ModelAttribute("profileBasic") UserProfile profile,@ModelAttribute("profileAccount") UserProfile profileAccount,@ModelAttribute("profileAddress") UserProfile profileAddress ,@ModelAttribute("profilePasswordChangeForm") ProfilePasswordChangeForm passChangeForm, Model model, HttpSession session, HttpServletRequest request) {

		
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
	

	@RequestMapping(value = "/my-dashboard", method = RequestMethod.GET)
	public String getMyDashboard(ModelMap model, HttpSession session) {
		//logger.info("@@@@ Inside Login..");
		String returnurl = "";
	
		if(session.getAttribute("token") == null){
			returnurl="redirect:/login";
		}else{
			returnurl = "my-dashboard";
		}
		logger.info("@@@@ DashboardController @@@@");
		return returnurl;
//		return "my-dashboard";
	}
	
	
	@RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
	public String resetPasswordValidator(@RequestParam("env")String env,@RequestParam("user")String user, @RequestParam("token")String token,Model map,HttpServletRequest request,HttpServletResponse response, HttpSession session) {
		//logger.info("@@@@ Inside Login..");
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
				logger.info("Validataion status for token of user - "+user +" --> "+responseEntity.getBody());
//				logger.info(profile.getMobile());
				
				if(!responseEntity.getBody().equalsIgnoreCase("VALID")){
					map.addAttribute("STATUS", "N");
					map.addAttribute("error", "Token validation failed.");
				}else{
					map.addAttribute("STATUS", "Y");
				}
			}catch(HttpStatusCodeException  e){
				logger.info("Connection failure - " + e.getStatusCode());
				map.addAttribute("STATUS", "N");
				map.addAttribute("error", "Unable to process request curretnly");
			} catch (JsonProcessingException e) {
				map.addAttribute("STATUS", "N");
				map.addAttribute("error","Invalid form data");
			}catch(Exception e){
				map.addAttribute("STATUS", "N");
				map.addAttribute("error","Error processing request");
			}	
			map.addAttribute("resetPassword", new ResetPassword());
			map.addAttribute("userid", user);
			
			map.addAttribute("contextcdn", environment.getProperty(CommonConstants.CDN_URL));
			returnurl = "reset-password";
		}
		logger.info("@@@@ DashboardController @@@@");
		
		return returnurl;
//		return "my-dashboard";
	}
	
	
	@RequestMapping(value = "/resetPassword.do", method = RequestMethod.GET)
	public String resetPasswordGetSubmit(ModelMap model) {
		return "redirect:/resetPassword";
	}

	@RequestMapping(value = "/resetPassword.do", method = RequestMethod.POST)
	public String resetPasswordSubmit(ModelMap model) {
		//logger.info("@@@@ Inside Login..");
		logger.info("@@@@ ResetPasswordDoController @@@@");
		return "reset-password";
	}

	@RequestMapping(value = "/blogs", method = RequestMethod.GET)
	public String getBlogs(ModelMap model) {
		//logger.info("@@@@ Inside Login..");
		logger.info("@@@@ BlogsController @@@@");
		return "blogs";
	}
	
		
	@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseBody
	public String handleMissingParams(MissingServletRequestParameterException ex) {
	    String name = ex.getParameterName();
	    logger.info(name + " parameter is missing");
	    // Actual exception handling
		return "BAD REQUEST.";
	    
	}

}
