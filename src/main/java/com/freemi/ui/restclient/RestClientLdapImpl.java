package com.freemi.ui.restclient;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.freemi.common.util.CommonConstants;
import com.freemi.database.interfaces.PortalUsersCrudRepository;
import com.freemi.entity.database.CampaignSignupForm;
import com.freemi.entity.database.ContactUsForm;
import com.freemi.entity.database.FSecure;
import com.freemi.entity.database.PortalUsers;
import com.freemi.entity.general.ClientSystemDetails;
import com.freemi.entity.general.ForgotPassword;
import com.freemi.entity.general.HttpClientResponse;
import com.freemi.entity.general.Login;
import com.freemi.entity.general.ProfilePasswordChangeForm;
import com.freemi.entity.general.Registerform;
import com.freemi.entity.general.ResetPassword;
import com.freemi.entity.general.SessionToken;
import com.freemi.entity.general.UserProfile;
import com.freemi.entity.general.UserProfileLdap;
import com.freemi.services.interfaces.ProfileRestClientService;
import com.google.gson.JsonObject;

//@PropertySource("classpath:application.properties")
@Component
public class RestClientLdapImpl implements ProfileRestClientService {


	@Autowired
	Environment env;

	@Autowired
	PortalUsersCrudRepository portalUsersCrudRepository;

	private final String ANONYMOUS_TOKEN = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbm9ueW1vdXMifQ.Ch3VesT2dCyRIDandUkxL87dIoioHCAdsRZzoNx0xNw";

	private static final Logger logger = LogManager.getLogger(RestClientLdapImpl.class);

	@Override
	public ResponseEntity<String> login(String userid, String password, String ip){

		logger.info("Requesting logging for profile- "+ userid);
		final String url = env.getProperty(CommonConstants.URL_SERVICE_PROFILE)+"/login";
		logger.info("Reuqesting url- "+ url);
		RestTemplate restTemplate = new RestTemplate();

		Map<String, String> parametersMap = new HashMap<String, String>();
		parametersMap.put("username", userid);
		parametersMap.put("password", password);
		parametersMap.put("systemip", ip);
		ResponseEntity<String> response = null;

		HttpHeaders headers = new HttpHeaders();
		headers.set("requestingIp", ip);
		response = restTemplate.postForEntity(url, parametersMap, String.class);
		logger.info("Response from logging api - "+ response.getHeaders());
		return response;
	}

	@Override
	public ResponseEntity<String> validateuserIdAndGetMail(String userId) throws JsonProcessingException{

		logger.info("Initiaitng API call check if user exist and get email Id - "+ userId);
		final String url = env.getProperty(CommonConstants.URL_SERVICE_PROFILE) + "/publicenv/getemailidifexistforotp/"+userId;
		RestTemplate restTemplate = new RestTemplate();
		//		System.out.println(registerForm);
		HttpHeaders headers = new HttpHeaders();	
		headers.set("authorization", ANONYMOUS_TOKEN);
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.postForEntity(url, entity,  String.class);
	}

	@Override
	public HttpClientResponse registerUser(Registerform registerForm, ClientSystemDetails systemDetails) {

		logger.info("Initiating API call to register user- "+ registerForm.getMobile());
		HttpClientResponse httpClientResponse = new HttpClientResponse();
		try {
			registerForm.setRequestingip(systemDetails.getClientIpv4Address());
			registerForm.setClientbrowserdetails(systemDetails.getClientBrowser());
			ResponseEntity<String> response = null;
			final String url = env.getProperty(CommonConstants.URL_SERVICE_PROFILE) + "/publicenv/registerUser";
			ObjectMapper mapper = new ObjectMapper();
			RestTemplate restTemplate = new RestTemplate();
			String formdata= mapper.writeValueAsString(registerForm);
			HttpHeaders headers = new HttpHeaders();	
			headers.set("authorization", ANONYMOUS_TOKEN);
			HttpEntity<String> entity = new HttpEntity<String>(formdata,headers);

//			response = restTemplate.postForEntity(url, entity,  String.class);
			httpClientResponse = restTemplate.postForObject(url, entity,  HttpClientResponse.class);
//			httpClientResponse.setResponseEntity(response);
			
			/*
			String status = response.getHeaders().get("STATUS").get(0);
			if(status.equals("SUCCESS")){
				httpClientResponse.setResponseCode(CommonConstants.HTTP_CLIENT_CALL_SUCCESS);
				httpClientResponse.setRetrunMessage("Registration successful. Login to your account");
			}else if(status.equals("DUPLICATE ENTRY")){
				httpClientResponse.setResponseCode(CommonConstants.HTTP_CLIENT_CALL_FAIL);
				httpClientResponse.setRetrunMessage("Account already exist with this number.");

			}else if(status.equals("DUPLICATE EMAIL")){
				httpClientResponse.setResponseCode(CommonConstants.HTTP_CLIENT_CALL_FAIL);
				httpClientResponse.setRetrunMessage("Email Id is already registered. Kindly select another email id");
			}else if(status.equals("ERROR")){
				httpClientResponse.setResponseCode(CommonConstants.HTTP_CLIENT_CALL_FAIL);
				httpClientResponse.setRetrunMessage("Registration failed. Please try again after sometime");
			}else{
				httpClientResponse.setResponseCode(CommonConstants.HTTP_CLIENT_CALL_FAIL);
				httpClientResponse.setRetrunMessage("Unknown response");
			}
			*/
		}catch(HttpStatusCodeException  e){
			logger.error("registerUser(): registration failed for Link failure - ", e);
			httpClientResponse.setResponseCode(CommonConstants.HTTP_CLIENT_CALL_FAIL);
			httpClientResponse.setRetrunMessage("Unable to process request currently");

		} catch (JsonProcessingException e) {
			logger.error("registerUser(): JsonProcessingException - ",e);
			httpClientResponse.setResponseCode(CommonConstants.HTTP_CLIENT_CALL_FAIL);
			httpClientResponse.setRetrunMessage("Invalid form data");
		}catch(Exception e){
			logger.error("registerUser(): Error register user",e);
			httpClientResponse.setResponseCode(CommonConstants.HTTP_CLIENT_CALL_FAIL);
			httpClientResponse.setRetrunMessage("Error processing request. Please try after sometime");
		}
		return httpClientResponse;
	}

	@Override
	public ResponseEntity<String> otpLogin(Login loginForm, String ip) throws JsonProcessingException{

		logger.info("Initiating API call to login user by OTP- "+ loginForm.getUsermobile());
		final String url = env.getProperty(CommonConstants.URL_SERVICE_PROFILE) + "/publicenv/otplogin";
		RestTemplate restTemplate = new RestTemplate();
		//		ObjectMapper mapper = new ObjectMapper();

		//		String formdata= mapper.writeValueAsString(loginForm);

		/*Map<String, String> parametersMap = new HashMap<String, String>();
		parametersMap.put("username", loginForm.getUsermobile());
		parametersMap.put("password", "NA");
		parametersMap.put("systemip", "NA");*/
		JsonObject form = new  JsonObject();
		form.addProperty("username", loginForm.getUsermobile());
		form.addProperty("password", "NA");
		form.addProperty("systemip", ip);
		form.addProperty("loginProcess", "OTP");

		//		ResponseEntity<String> response = null;


		//		System.out.println(loginForm);
		HttpHeaders headers = new HttpHeaders();	
		headers.set("authorization", ANONYMOUS_TOKEN);
		HttpEntity<String> entity = new HttpEntity<String>(form.toString(),headers);
		return restTemplate.postForEntity(url, entity,  String.class);

	}

	@Override
	public ResponseEntity<String> contactUs(ContactUsForm contactUsData) throws JsonProcessingException{
		final String url = env.getProperty(CommonConstants.URL_SERVICE_PROFILE) + "/publicenv/contactusrequest";
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();
		String formdata= mapper.writeValueAsString(contactUsData);
		//		System.out.println(formdata);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", ANONYMOUS_TOKEN);
		HttpEntity<String> entity = new HttpEntity<String>(formdata, headers);

		return restTemplate.postForEntity(url, entity,  String.class);
	}

	@Override
	public ResponseEntity<String> campaignSingUp(CampaignSignupForm campaignSignUpForm) throws JsonProcessingException{
		final String url = env.getProperty(CommonConstants.URL_SERVICE_PROFILE) + "/publicenv/campaignSignUp";
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();
		String formdata= mapper.writeValueAsString(campaignSignUpForm);
		System.out.println(formdata);
		//		System.out.println(formdata);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", ANONYMOUS_TOKEN);
		HttpEntity<String> entity = new HttpEntity<String>(formdata, headers);

		return restTemplate.postForEntity(url, entity,  String.class);
	}


	@Override
	public ResponseEntity<String> fsecureRequest(FSecure contactUsData,String requestingIp) throws JsonProcessingException{
		final String url = env.getProperty(CommonConstants.URL_SERVICE_PROFILE) + "/publicenv/fSecureRequest";
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();
		String formdata= mapper.writeValueAsString(contactUsData);

		//		HttpHeaders headers = new HttpHeaders();
		//		headers.set("Authorization", ANONYMOUS_TOKEN);
		HttpEntity<String> entity = new HttpEntity<String>(formdata);

		return restTemplate.postForEntity(url, entity,  String.class);
	}

	@Override
	public ResponseEntity<String> forgotPassword(ForgotPassword forgotPasswordForm) throws JsonProcessingException{
		final String url = env.getProperty(CommonConstants.URL_SERVICE_PROFILE) + "/forgotPassword/";
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", ANONYMOUS_TOKEN);
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		return restTemplate.postForEntity(url+forgotPasswordForm.getUsermobile(), entity,  String.class);
	}

	@Override
	public UserProfileLdap getProfileData(String userid, String token, String requestingIp) throws JsonProcessingException{
		final String url = env.getProperty(CommonConstants.URL_SERVICE_PROFILE) + "/getProfileData";
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		UserProfileLdap userDetails = null;
		try {
		
		headers.set("Authorization", token);
		headers.set("requestingIp", requestingIp);
		headers.set("mobile", userid);
		String useridfromSession = "{userProfileID: "+userid+"}";

		HttpEntity<String> entity = new HttpEntity<String>(useridfromSession,headers);
		userDetails= restTemplate.postForObject(url, entity,  UserProfileLdap.class);
		
		}catch(Exception e) {
		    logger.info("Error fetching profile data",e);
		}
		
		return userDetails;
	}

	@Override
	public ResponseEntity<String> updateProfileData(UserProfile profileData,String userid, String token, String requestingIp) throws JsonProcessingException{
		final String url = env.getProperty(CommonConstants.URL_SERVICE_PROFILE) + "/updateProfileData/"+userid;
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();
		String formdata= mapper.writeValueAsString(profileData);

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", token);
		headers.set("requestingIp", requestingIp);

		HttpEntity<String> entity = new HttpEntity<String>(formdata,headers);
		return restTemplate.postForEntity(url, entity,  String.class);
	}

	@Override
	public ResponseEntity<String> updateProfilePassword(ProfilePasswordChangeForm passchangeForm,String userid, String token,String requestingIp) throws JsonProcessingException{
		final String url = env.getProperty(CommonConstants.URL_SERVICE_PROFILE) + "/profilePasswordChange/"+userid;
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();
		String formdata= mapper.writeValueAsString(passchangeForm);

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", token);
		headers.set("requestingIp", requestingIp);
		HttpEntity<String> entity = new HttpEntity<String>(formdata,headers);
		return restTemplate.postForEntity(url, entity,  String.class);
	}

	@Override
	public ResponseEntity<String> forgotPasswordUpdate(ResetPassword forgotPasswordChangeForm,String userid, String token,String requestingIp) throws JsonProcessingException{
		final String url = env.getProperty(CommonConstants.URL_SERVICE_PROFILE) + "/forgotPasswordReset/"+userid;
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();
		String formdata= mapper.writeValueAsString(forgotPasswordChangeForm);

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+token);
		headers.set("requestingIp", requestingIp);
		headers.set("userid", userid);
		HttpEntity<String> entity = new HttpEntity<String>(formdata,headers);
		return restTemplate.postForEntity(url, entity,  String.class);
	}


	@Override
	public ResponseEntity<String> validateResetPasswordToken(String userid, String token,String requestingIp) throws JsonProcessingException{
		logger.info("Reset password token validation rest call- "+ token);
		final String url = env.getProperty(CommonConstants.URL_SERVICE_PROFILE) + "/validatePasswordToken/"+userid;
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+token);
		headers.set("requestingIp", requestingIp);
		headers.set("userid", userid);
		//		HttpEntity<String> entity = new HttpEntity<String>(formdata,headers);
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		return restTemplate.postForEntity(url, entity,  String.class);
	}

	@Override
	public ResponseEntity<String> isUserExisitng(String mobile) throws JsonProcessingException{
		final String url = env.getProperty(CommonConstants.URL_SERVICE_PROFILE) + "/checkUserExist/"+mobile;
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", ANONYMOUS_TOKEN);
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		return restTemplate.postForEntity(url, entity,  String.class);
	}


	@Override
	public ResponseEntity<String> isEmailExisitng(String email) throws JsonProcessingException {
		final String url = env.getProperty(CommonConstants.URL_SERVICE_PROFILE) + "/checkEmailExist";
		RestTemplate restTemplate = new RestTemplate();
		ObjectMapper mapper = new ObjectMapper();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", ANONYMOUS_TOKEN);
		headers.set("content-Type", MediaType.APPLICATION_JSON_VALUE);
		HashMap<String, String> paramdata = new HashMap<String, String>();
		paramdata.put("email", email);
		logger.info("Calling URL- "+ url);
		HttpEntity<String> entity = new HttpEntity<String>(mapper.writeValueAsString(paramdata),headers);
		return restTemplate.postForEntity(url, entity,  String.class);
	}

	
	
	@Override
	public ResponseEntity<String> validateUserToken(String userid, String token, String requestingIp) {
		logger.info("Validate user session token - "+ token);
		final String url = env.getProperty(CommonConstants.URL_SERVICE_PROFILE) + "/validateSessionToken/"+userid;
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();
		SessionToken sessiontoken = new SessionToken();
		sessiontoken.setToken(token);
		sessiontoken.setUserid(userid);
		sessiontoken.setSystemip(requestingIp);
		ResponseEntity<String> response=null;

		String formdata =null;
		try {
			formdata= mapper.writeValueAsString(sessiontoken);
			HttpHeaders headers = new HttpHeaders();
			headers.set("Authorization", ANONYMOUS_TOKEN);
			headers.set("requestingIp", requestingIp);
			HttpEntity<String> entity = new HttpEntity<String>(formdata,headers);
			response = restTemplate.postForEntity(url, entity,  String.class);
			logger.debug("validateUserToken(): Session validation response- "+ response);
		} catch (JsonProcessingException e) {
			logger.info("Failed to carry out session validation for user - "+userid,e);
		}

		return response;
	}

	@Override
	public ResponseEntity<String> linkmfaccountDetails(String mobile, String pan, String bseclientId) {
		logger.info("Request received to link PAN to registered account with mobile no- "+ mobile);
		ResponseEntity<String> response = null;
		final String url = env.getProperty(CommonConstants.URL_SERVICE_PROFILE) + "/linkmfaccountinfo/";
		try {
			RestTemplate restTemplate = new RestTemplate();
			JsonObject form = new  JsonObject();
			form.addProperty("mobile", mobile);
			form.addProperty("pan", pan.toUpperCase());
			form.addProperty("bseclientid", bseclientId);

			HttpHeaders headers = new HttpHeaders();	
			headers.set("authorization", ANONYMOUS_TOKEN);
			HttpEntity<String> entity = new HttpEntity<String>(form.toString(),headers);
			logger.info("Calling url- "+ url);
			response= restTemplate.postForEntity(url, entity,  String.class);
			logger.info("Updating profile with PAN no status- "+ response.getBody());
		}catch(Exception e) {
			logger.error("Error updating PAN to LDAP account ",e);
		}
		return response;
	}

	@Override
	public String isPanExisitngForOthers(String mobile, String pan) {
	    	String response="E";
	    try {
	    	final String url = env.getProperty(CommonConstants.URL_SERVICE_PROFILE) + "/checkPanExistForOtherUsers"+"/" + mobile+"/"+pan;
		
	    	
	    	RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", ANONYMOUS_TOKEN);
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<String> resp =  restTemplate.postForEntity(url, entity,  String.class);
		logger.info("isPanExisitngForOthers(): Search response- " + resp.getBody());
		response= resp.getBody();
		
	    }catch(Exception e) {
		logger.error("Error requesting check for PAN search",e);
	    }
	    
	    return response;
	}

	@Override
	public ResponseEntity<String> isEmailExisitngforothers(String mobile, String email) throws JsonProcessingException {
		logger.info("Request received to link PAN to registered account with mobile no- "+ mobile);
		ResponseEntity<String> response = null;
		final String url = env.getProperty(CommonConstants.URL_SERVICE_PROFILE) + "/check-email-exist-for-others";
		RestTemplate restTemplate = new RestTemplate();
		JsonObject form = new  JsonObject();
		form.addProperty("mobile", mobile);
		form.addProperty("email", email);

		HttpHeaders headers = new HttpHeaders();	
		headers.set("authorization", ANONYMOUS_TOKEN);
		HttpEntity<String> entity = new HttpEntity<String>(form.toString(),headers);
		logger.info("Calling url- "+ url);
		response= restTemplate.postForEntity(url, entity,  String.class);
		logger.info("Email ID exist for others"+ response.getBody());
		return response;
	}

	@Override
	public ResponseEntity<String> updateprofiledetails(UserProfile profileData) {
		logger.info("Request received to update profile details for mobile no (general) - "+ profileData.getMobile());
		
		ResponseEntity<String> response = null;
		final String url = env.getProperty(CommonConstants.URL_SERVICE_PROFILE) + "/update-profile-details";
		try {
			RestTemplate restTemplate = new RestTemplate();
			ObjectMapper mapper = new ObjectMapper();
			HttpHeaders headers = new HttpHeaders();	
			headers.set("authorization", ANONYMOUS_TOKEN);
			HttpEntity<String> entity = new HttpEntity<String>(mapper.writeValueAsString(profileData),headers);
			logger.info("Calling url- "+ url);
			response= restTemplate.postForEntity(url, entity,  String.class);
			logger.info("Updating profile with details- "+ response.getBody());
		}catch(Exception e) {
			logger.error("Error updating PAN to LDAP account ",e);
		}
		
		return response;
	}



	/*public static void main(String[] args) {

		ResponseEntity<String> response = null;
		 RestClient c = new RestClient();
		 String token = "Bearer eyJyZXF1ZXN0ZWRJcCI6IjEyNy4wLjAuMSIsImFsZyI6IkhTMjU2IiwiYXV0aG9yaXRpZXMiOltdfQ.eyJzdWIiOiI5MDUxNDcyNjQ1IiwianRpIjoiOTA1MTQ3MjY0NSIsImlhdCI6MTUyNjM2MDAxOSwiZXhwIjoxNTI2Mzg3MDE5fQ.-2FkR8fH4pZUgmCDk2rRwJ953uQa1TAbjcHhtDJ3ymc";
		 try{
//		 c.login("9051472645", "Password1");
		 response =  c.getProfileData("9051472645", token);
		 UserProfile profile = new ObjectMapper().readValue(response.getBody(), UserProfile.class);

		 System.out.println(profile.getMobile());
		 }
			catch(HttpStatusCodeException  e){
				System.out.println("test failure - " + e.getStatusCode());
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
	}*/

}
