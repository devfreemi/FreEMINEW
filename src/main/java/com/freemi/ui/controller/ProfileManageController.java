package com.freemi.ui.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.freemi.controller.interfaces.ProfileRestClientService;
import com.freemi.database.service.BseEntryManager;
import com.freemi.entity.bse.BseFileUpload;
//import com.freemi.database.service.BseEntryManager;
import com.freemi.entity.general.ProfilePasswordChangeForm;
import com.freemi.entity.general.ResetPassword;
import com.freemi.entity.general.UserProfile;
import com.freemi.entity.general.UserProfileLdap;
import com.freemi.entity.investment.MFCamsFolio;
import com.freemi.entity.investment.MFCamsValueByCategroy;
import com.freemi.entity.investment.MFKarvyFundsView;
import com.freemi.entity.investment.MFKarvyValueByCategory;
//import com.freemi.entity.investment.BseAllTransactionsView;
import com.freemi.ui.restclient.GoogleSecurity;

@Controller
@Scope("session")
@SessionAttributes({"profileBasic","profileAccount","profileAddress"})
public class ProfileManageController{


	@Autowired
	private Environment environment;

	@Autowired
	BseEntryManager bseEntryManager;

	@Autowired
	ProfileRestClientService profileRestClientService;

	private static final Logger logger = LogManager.getLogger(ProfileManageController.class);


	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String getProfile(Model model, HttpSession session, HttpServletRequest request) {
		logger.info("@@@@ Get profile details..");
		String returnurl = "";
		int error = 0;
		//		RestClient client = new RestClient();
		ResponseEntity<String> response = null;
		UserProfileLdap userDetails = null;
		ProfilePasswordChangeForm passform =  new ProfilePasswordChangeForm();
		if(session.getAttribute("token") == null){
			try {
				returnurl="redirect:/login?ref="+ URLEncoder.encode(request.getRequestURL().toString(), StandardCharsets.UTF_8.toString());
			} catch (UnsupportedEncodingException e) {
				logger.error("profile(): failed to encode url- ",e);
				returnurl="redirect:/login";
			}
		}else{
			returnurl="profile2";
			
		
			passform.setMobile(session.getAttribute("userid").toString());
			
			//			User profile data collection from DB
			try{
				UserProfile profile = bseEntryManager.getCustomerProfileDetailsByMobile(session.getAttribute("userid").toString());
				if(profile!=null){
					model.addAttribute("profileBasic", profile);
					model.addAttribute("INV_PROF","Y");
				}else{
					model.addAttribute("INV_PROF","N");
				}
			}catch(Exception e){
				model.addAttribute("errorinv", "Unable to fetch Profiel details");
				logger.error("Unable to fetch your investment profile details.",e);
				error =1;
				model.addAttribute("profileBasic", new UserProfile());
			}

			//				START BASIC DETAILS QUERY
			logger.info("Fetch basic details...");
			//					Search Basic Details from LDAP
			try {
				response = profileRestClientService.getProfileData(session.getAttribute("userid").toString(), session.getAttribute("token").toString(), CommonTask.getClientSystemDetails(request).getClientIpv4Address());
				//						logger.info(response.getBody());
				//						logger.info(response.getHeaders());
				logger.info("Get Profile details response- "+ response.getBody());
				userDetails = new ObjectMapper().readValue(response.getBody(), UserProfileLdap.class);
				//						logger.info(profile.getGender());

				model.addAttribute("profilefreemi", userDetails);
				/*model.addAttribute("profileAccount", userDetails);
					model.addAttribute("profileAddress", userDetails);*/

				logger.info("Profile details retrieved for customer from LDAP- "+userDetails.getMobile());
			}catch(HttpStatusCodeException  e){
				logger.info("Unable to fetch profile details from LDAP- " + e.getStatusCode());
				if(e.getStatusCode().value() == 401){
					model.addAttribute("error", "Token validation failed. Please check if it expired");
				}else{
					model.addAttribute("error", "Unable to process request curretnly");
				}
				model.addAttribute("profilefreemi", new UserProfile());
			} catch (JsonProcessingException e) {
				model.addAttribute("error","Invalid form data");
				model.addAttribute("profilefreemi", new UserProfile());

			}catch(Exception e){
				//						e.printStackTrace();
				model.addAttribute("error","Error processing request");
				model.addAttribute("profilefreemi", new UserProfile());
			}


			//				----------------------



			/*	model.addAttribute("states", InvestFormConstants.states);
				model.addAttribute("bankNames", InvestFormConstants.bankNames);
				model.addAttribute("accountTypes", InvestFormConstants.accountTypes);*/


			if(error == 1){
				model.addAttribute("error","Sorry. Unable to fetch your details currently.");

				/*model.addAttribute("profileAccount", new UserProfile());
				model.addAttribute("profileAddress", new UserProfile());*/
			}


		}
		
		
		model.addAttribute("profilePasswordChangeForm",passform);
		model.addAttribute("contextcdn", environment.getProperty(CommonConstants.CDN_URL));
		logger.info("@@@@ ProfileController complete. @@@@");
		return returnurl;
	}

	@RequestMapping(value = "/profileBasic.do", method = RequestMethod.POST)
	public String postProfileBasicDo(@ModelAttribute("profileBasic") UserProfile profile,@ModelAttribute("profileAccount") UserProfile profileAccount,@ModelAttribute("profileAddress") UserProfile profileAddress ,@ModelAttribute("profilePasswordChangeForm") ProfilePasswordChangeForm passChangeForm,Model model, HttpSession session, HttpServletRequest request) {

		logger.info("@@@@ ProfileBasicDoController @@@@");
		String returnurl="";
		//		RestClient client = new RestClient();
		ResponseEntity<String> response = null;
		if(session.getAttribute("token") == null){
			returnurl="redirect:/login";
		}else{
			returnurl="profile";

			//			Profile data change to ldap server
			try {
				response = profileRestClientService.updateProfileData(profile,session.getAttribute("userid").toString(), session.getAttribute("token").toString(),CommonTask.getClientSystemDetails(request).getClientIpv4Address());
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
		//		RestClient client = new RestClient();
		ResponseEntity<String> response = null;
		if(session.getAttribute("token") == null){
			returnurl="redirect:/login";
		}else{
			returnurl="profile";

			//			Updating profile details into LDAP database
			try {
				response = profileRestClientService.updateProfileData(profileAccount,session.getAttribute("userid").toString(), session.getAttribute("token").toString(),CommonTask.getClientSystemDetails(request).getClientIpv4Address());
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
	@ResponseBody
	public String postPassChangeDo(/*@ModelAttribute("profileBasic") UserProfile profile,@ModelAttribute("profileAccount") UserProfile profileAccount,@ModelAttribute("profileAddress") UserProfile profileAddress ,*/@ModelAttribute("profilePasswordChangeForm") @Valid ProfilePasswordChangeForm passChangeForm,BindingResult bindingResult, HttpSession session, HttpServletRequest request, HttpServletResponse resp) {

		logger.info("@@@@ ProfilePasswordChange POST controller @@@@");
		String returnurl ="";
		//		RestClient client = new RestClient();
		ResponseEntity<String> response = null;
		try{
		if(bindingResult.hasErrors()){
//			model.addAttribute("error", bindingResult.getFieldError().getDefaultMessage());
			
			logger.info("Change password form validation failed- "+ bindingResult.getFieldError().getDefaultMessage());
			returnurl =  bindingResult.getFieldError().getDefaultMessage();
		}else if(session.getAttribute("token") == null){
//			returnurl="redirect:/login";
			logger.info("no session found of the user to change password.");
			returnurl ="Session invalid. Try again after login.";
		}else{
//			returnurl="redirect:/profile";
			try {
				response = profileRestClientService.updateProfilePassword(passChangeForm,session.getAttribute("userid").toString(), session.getAttribute("token").toString(),CommonTask.getClientSystemDetails(request).getClientIpv4Address());
				//				System.err.println(response.getBody());
				logger.info("Password change response-  " + response.getBody());
				if(response.getBody().equals("SUCCESS")){
//					model.addAttribute("passchange", "Password updated successfully");
					returnurl = "SUCCESS";
				}else if(response.getBody().equals("OLD_PASSWORD_INVALID")){
					returnurl = "Previous password do not match";
				}
				else{
//					model.addAttribute("passchange", "Failed to update. previous password may not be valid");
					returnurl = "Failed to change password. Contact Admin.";
				}

			}catch(HttpStatusCodeException  e){
				logger.error("Change profile password HttpStatusCodeException - ", e);
//				model.addAttribute("error", "Unable to process request curretnly");
				returnurl = "Unable to process request currently";
			} catch (JsonProcessingException e) {
				logger.error("error changing password JsonProcessingException()", e);
//				model.addAttribute("error","Invalid form data");
				returnurl ="Invalid form data";
			}catch(Exception e){
				logger.error("error changing password Exception()", e);
//				model.addAttribute("error","Error processing request");
				returnurl ="Error processing request";
			}


		}
		}catch(Exception e){
			returnurl = "Internal error.";
		}
		return returnurl;
	}


	@RequestMapping(value = "/my-dashboard/", method = RequestMethod.GET)
	public String getMyDashboardAlt(Model map,HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		return "redirect:/my-dashboard";

	}

	@RequestMapping(value = "/my-dashboard", method = RequestMethod.GET)
	public String myDashboardGet(@ModelAttribute("fileform") BseFileUpload fileform,Model map,HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		//logger.info("@@@@ Inside Login..");
		String returnurl = "";
		double totalAsset= 0.0;
		if(session.getAttribute("token") == null){
			try {
				returnurl="redirect:/login?ref="+ URLEncoder.encode(request.getRequestURL().toString(), StandardCharsets.UTF_8.toString());
			} catch (UnsupportedEncodingException e) {
				logger.error("my-dashboard(): failed to encode referrel url",e);
				returnurl="redirect:/login";
			}
		}else{
			returnurl = "my-dashboard";
			// Get user's MF order history
			//This code is for new design. COmment out until deployed in prod
			try{
				//			List<BseAllTransactionsView> fundsOrder= bseEntryManager.getCustomerAllTransactionRecords(null,session.getAttribute("userid").toString(),null);

				List<MFKarvyValueByCategory> karvyFunds= null;
				List<MFKarvyValueByCategory> categorykarvyFunds= new ArrayList<MFKarvyValueByCategory>();
				List<MFCamsFolio> fundsOrder = null;
				List<MFKarvyFundsView> karvyview = new ArrayList<MFKarvyFundsView>();
				List<MFCamsValueByCategroy> camscategoryFunds = bseEntryManager.getCustomersCamsInvByCategory(session.getAttribute("userid").toString(), null);

				if(camscategoryFunds!= null && camscategoryFunds.size()>=1){
					//			fundsOrder = bseEntryManager.getCamsPortfolio(session.getAttribute("userid").toString(), "");
					fundsOrder = bseEntryManager.getCamsPortfolio(session.getAttribute("userid").toString(), "");

					for(int i=0;i<camscategoryFunds.size();i++){
						List<MFCamsFolio> fundsOrderCategory = new ArrayList<MFCamsFolio>();
						//				totalAsset+=fundsOrder.get(i).getSchemeInvestment();
						totalAsset+=camscategoryFunds.get(i).getCollaboratedAmount();
						for(int j=0;j<fundsOrder.size();j++){
							if(camscategoryFunds.get(i).getFundName().equalsIgnoreCase(fundsOrder.get(j).getFundName())){
								fundsOrderCategory.add(fundsOrder.get(j));
							}
						}
						camscategoryFunds.get(i).setCamsFolioLists(fundsOrderCategory);
					}

					/*KARVY*/

					try{
						logger.info("Search for KARVY related folios for customer.");
						List<String> fundShort = new ArrayList<String>();

						karvyFunds = bseEntryManager.getCustomersKarvyInvByCategory(session.getAttribute("userid").toString(), null);
						if(karvyFunds !=null){
							logger.info("Total karvy Folio found of customer - " + karvyFunds.size());
							for(int j=0;j<karvyFunds.size();j++){
								fundShort.add(karvyFunds.get(j).getAmcShort());

								totalAsset+=karvyFunds.get(j).getInvAmount();
							}

							Set<String> uniqueAmcs = new HashSet<>(fundShort);
							fundShort = new ArrayList<String>(uniqueAmcs);
							logger.info("Unique funds from karvy house - "+ fundShort);

							for(int x=0;x<fundShort.size();x++){
								MFKarvyFundsView selectedKarvy = new MFKarvyFundsView();
								Double calcKarvyVal=0.0;
								for(int y=0;y<karvyFunds.size();y++){
									if(fundShort.get(x).equals(karvyFunds.get(y).getAmcShort())){
										calcKarvyVal+=karvyFunds.get(y).getInvAmount();

										if(selectedKarvy.getAmcicon()==null){
											selectedKarvy.setAmcicon(karvyFunds.get(y).getAmcicon());
										}
										if(selectedKarvy.getFunName()==null){
											selectedKarvy.setFunName(karvyFunds.get(y).getFundName());;
										}

										categorykarvyFunds.add(karvyFunds.get(y));


									}
									selectedKarvy.setTotalInvestment(calcKarvyVal);
									selectedKarvy.setCategorizedFund(categorykarvyFunds);
								}

								karvyview.add(selectedKarvy);
							}

							logger.info("karvy funds by category- " + karvyview.size());
						}

					}catch(Exception e){
						logger.error("Error handling Karvy folio query from controller",e);
					}


					map.addAttribute("camsorderhistory", camscategoryFunds);
					map.addAttribute("karvyorderhistory", karvyview);
					map.addAttribute("ORDERHISTORY", "SUCCESS");
				}else{
					map.addAttribute("ORDERHISTORY", "EMPTY");
				}
			}catch(Exception ex){
				map.addAttribute("ORDERHISTORY", "ERROR");
				logger.error("Failed to fetch cutomer Registry details \n", ex);
			}
			fileform.setFilecategory("AOF");
			fileform.setFileowner(session.getAttribute("userid").toString());


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
			//			RestClient client = new RestClient();
			ResponseEntity<String> responseEntity = null;

			try {
				responseEntity = profileRestClientService.validateResetPasswordToken(user, token, CommonTask.getClientSystemIp(request));
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
					/*session.setAttribute("user", user);
					session.setAttribute("token", token);*/
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
		//		RestClient client = new RestClient();

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
				responseEntity = profileRestClientService.forgotPasswordUpdate(resetPassForm,session.getAttribute("user").toString(), session.getAttribute("token").toString(),CommonTask.getClientSystemDetails(request).getClientIpv4Address());
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
