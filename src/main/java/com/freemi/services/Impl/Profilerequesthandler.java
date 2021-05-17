package com.freemi.services.Impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.freemi.common.util.CommonConstants;
import com.freemi.common.util.CommonTask;
import com.freemi.entity.general.HttpClientResponse;
import com.freemi.entity.general.Registerform;
import com.freemi.services.interfaces.ProfileRestClientService;
import com.freemi.ui.controller.BsemfController;

@Service
public class Profilerequesthandler {

	@Autowired
	ProfileRestClientService profileRestClientService;
	
	@Autowired
	Environment env;
	
	private static final Logger logger = LogManager.getLogger(Profilerequesthandler.class);
	
	public HttpClientResponse savebsecustomer(String fname, String lname, String mobile, String email, String passwordprocess, String registrationref, HttpServletRequest request) {

		logger.info("User profile registration request received for - "+ mobile);
		HttpClientResponse httpResponse = new HttpClientResponse();
		if(env.getProperty(CommonConstants.LDAP_USER_REGISTRATION_ENABLED).equalsIgnoreCase("Y")) {

			Registerform registerForm = new Registerform();
			registerForm.setMobile(mobile);
			registerForm.setEmail(email);
			registerForm.setFname(fname);
			registerForm.setLname(lname);
			String fullname = fname + " " + lname;
			registerForm.setFullName(fullname);

			if(passwordprocess.equals("GENERATE")) {
				registerForm.setPassword(CommonConstants.GENERATE_PASSWORD_BY_FREEMI); // Reserved token for generating
			}
			// token at profile side
			registerForm.setRegistrationref(registrationref);	//MF_REG_NEW


			httpResponse =  profileRestClientService.registerUser(registerForm,CommonTask.getClientSystemDetails(request));
			logger.info("Response received from LDAP account registration during MF account registrartion- "+ httpResponse.getResponseCode() + " : "+ httpResponse.getRetrunMessage());

			/*
		if(httpResponse.getResponseCode() == CommonConstants.HTTP_CLIENT_CALL_SUCCESS) {
			logger.info("registerBsepost(): Registration successful for mobile number during MF registration- "+ mobile);
			logger.info("registerBsepost(): User registration successful initiated during new customer registration. Setting parameter to false..");
//			investForm.setProfileRegRequired(false);
		} 
			 * else { validationpass =false; validationerrormsg=
			 * httpResponse.getRetrunMessage(); }
			 */
		}else {
			logger.info("LDAP registration is currently disabled in environment. Skipping reuqest");
			httpResponse.setResponseCode(CommonConstants.SERVICE_CATEGORY_DISABLED);
			httpResponse.setRetrunMessage("Registration process disabled. Please contact admin.");
		}
		return httpResponse;
	}
	
	public ResponseEntity<String> linkbseclientcodetoprofile(String mobile, String bseclientcode, String pan) {
		
		ResponseEntity<String> responsePortal = null;
		try {
			logger.info("MF REG - > Update LDAP with PAN and bseclientID....");
			responsePortal = profileRestClientService.linkmfaccountDetails(mobile, pan, bseclientcode );
			String status = responsePortal.getBody();
			logger.info("LDAP account update response- "+ status);
			if (status!=null && status.equals("SUCCESS")) {
				logger.info("registerBsepost(): Linking successful for mobile number with MF account- "+ mobile + " -> "+ bseclientcode);
			}  else {
				logger.info("registerBsepost(): Failed to link MF account details with LDAP account" + status);
			}

		} catch (Exception e) {
			logger.error("registerBsepost(): bsemfRegisterpost(): Exception proceesing linking PAN details to LDAP account",e);
		}
		
		return responsePortal;
		
	}
}
