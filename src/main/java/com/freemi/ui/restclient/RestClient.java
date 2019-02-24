package com.freemi.ui.restclient;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.freemi.entity.database.CampaignSignupForm;
import com.freemi.entity.general.ContactUsForm;
import com.freemi.entity.general.FSecure;
import com.freemi.entity.general.ForgotPassword;
import com.freemi.entity.general.ProfilePasswordChangeForm;
import com.freemi.entity.general.Registerform;
import com.freemi.entity.general.ResetPassword;
import com.freemi.entity.general.UserProfile;

//@PropertySource("classpath:application.properties")
public class RestClient {
	
	
	/*@Autowired
	Environment env;*/
	
//	private final String SERVICE_URL1 = "https://ec2-35-154-76-43.ap-south-1.compute.amazonaws.com/freemibackend";
//	private final String SERVICE_URL1 = "http://ec2-35-154-76-43.ap-south-1.compute.amazonaws.com:8080/freemibackend";
	
	/*local*/
//	private final String SERVICE_URL1 = "http://localhost:8090/freemibackend";
	
	/*production*/
//	private final String SERVICE_URL1 = "http://localhost:8080/freemibackend";
	
	/*DEV*/
	private final String SERVICE_URL1 = "http://dev.freemi.in:8080/freemibackend";
	
//	@Value("${profile.service.url}")
//	private String SERVICE_URL1 = env.getProperty("profile.service.url");

	private final String ANONYMOUS_TOKEN = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbm9ueW1vdXMifQ.Ch3VesT2dCyRIDandUkxL87dIoioHCAdsRZzoNx0xNw";
	
	private static final Logger logger = LogManager.getLogger(RestClient.class);
	
	
	public ResponseEntity<String> login(String userid, String password, String ip){
		
		logger.info("Requesting logging for profile- "+ userid);
		final String url = SERVICE_URL1+"/login";
		logger.info("Reuqesting url- "+ url);
		RestTemplate restTemplate = new RestTemplate();
		
//		HttpHeaders headers = new HttpHeaders();
//		headers.set("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbm9ueW1vdXMifQ.Ch3VesT2dCyRIDandUkxL87dIoioHCAdsRZzoNx0xNw");

//		MultiValueMap<String, String> parametersMap = new LinkedMultiValueMap<String, String>();
		
		
		
		Map<String, String> parametersMap = new HashMap<String, String>();
		parametersMap.put("username", userid);
		parametersMap.put("password", password);
		parametersMap.put("systemip", ip);
		ResponseEntity<String> response = null;
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("requestingIp", ip);
//		HttpEntity<String> entity = new HttpEntity<String>(parametersMap, headers);
//		ResponseEntity<String> result = restTemplate.postForObject(url, parametersMap, String.class);
		
		response = restTemplate.postForEntity(url, parametersMap, String.class);
		logger.info("Response from logging api - "+ response.getHeaders());
		return response;
	}
	
	public ResponseEntity<String> registerUser(Registerform registerForm) throws JsonProcessingException{
		
		
		final String url = SERVICE_URL1 + "/publicenv/registerUser";
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();
		String formdata= mapper.writeValueAsString(registerForm);
		System.out.println(registerForm);
		HttpHeaders headers = new HttpHeaders();	
		headers.set("authorization", ANONYMOUS_TOKEN);
		HttpEntity<String> entity = new HttpEntity<String>(formdata,headers);
		
		return restTemplate.postForEntity(url, entity,  String.class);
	}
	
	public ResponseEntity<String> contactUs(ContactUsForm contactUsData) throws JsonProcessingException{
		final String url = SERVICE_URL1 + "/publicenv/contactusrequest";
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();
		String formdata= mapper.writeValueAsString(contactUsData);
//		System.out.println(formdata);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", ANONYMOUS_TOKEN);
		HttpEntity<String> entity = new HttpEntity<String>(formdata, headers);
		
		return restTemplate.postForEntity(url, entity,  String.class);
	}
	
	public ResponseEntity<String> campaignSingUp(CampaignSignupForm campaignSignUpForm) throws JsonProcessingException{
		final String url = SERVICE_URL1 + "/publicenv/campaignSignUp";
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
	
	public ResponseEntity<String> fsecureRequest(FSecure contactUsData,String requestingIp) throws JsonProcessingException{
		final String url = SERVICE_URL1 + "/publicenv/fSecureRequest";
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();
		String formdata= mapper.writeValueAsString(contactUsData);
		
//		HttpHeaders headers = new HttpHeaders();
//		headers.set("Authorization", ANONYMOUS_TOKEN);
		HttpEntity<String> entity = new HttpEntity<String>(formdata);
		
		return restTemplate.postForEntity(url, entity,  String.class);
	}
	
	
	public ResponseEntity<String> forgotPassword(ForgotPassword forgotPasswordForm) throws JsonProcessingException{
		final String url = SERVICE_URL1 + "/forgotPassword/";
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", ANONYMOUS_TOKEN);
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		return restTemplate.postForEntity(url+forgotPasswordForm.getUsermobile(), entity,  String.class);
	}
	
	public ResponseEntity<String> getProfileData(String userid, String token, String requestingIp) throws JsonProcessingException{
		final String url = SERVICE_URL1 + "/getProfileData";
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", token);
		headers.set("requestingIp", requestingIp);
		headers.set("mobile", userid);
		String useridfromSession = "{userProfileID: "+userid+"}";
		
		HttpEntity<String> entity = new HttpEntity<String>(useridfromSession,headers);
		return restTemplate.postForEntity(url, entity,  String.class);
	}
	
	
	public ResponseEntity<String> updateProfileData(UserProfile profileData,String userid, String token, String requestingIp) throws JsonProcessingException{
		final String url = SERVICE_URL1 + "/updateProfileData/"+userid;
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();
		String formdata= mapper.writeValueAsString(profileData);
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", token);
		headers.set("requestingIp", requestingIp);
		
		HttpEntity<String> entity = new HttpEntity<String>(formdata,headers);
		return restTemplate.postForEntity(url, entity,  String.class);
	}
	
	public ResponseEntity<String> updateProfilePassword(ProfilePasswordChangeForm passchangeForm,String userid, String token,String requestingIp) throws JsonProcessingException{
		final String url = SERVICE_URL1 + "/profilePasswordChange/"+userid;
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();
		String formdata= mapper.writeValueAsString(passchangeForm);
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", token);
		headers.set("requestingIp", requestingIp);
		HttpEntity<String> entity = new HttpEntity<String>(formdata,headers);
		return restTemplate.postForEntity(url, entity,  String.class);
	}
	
	public ResponseEntity<String> forgotPasswordUpdate(ResetPassword forgotPasswordChangeForm,String userid, String token,String requestingIp) throws JsonProcessingException{
		final String url = SERVICE_URL1 + "/forgotPasswordReset/"+userid;
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
	
	
	
	public ResponseEntity<String> validateResetPasswordToken(String userid, String token,String requestingIp) throws JsonProcessingException{
		logger.info("Reset password token validation rest call- "+ token);
		final String url = SERVICE_URL1 + "/validatePasswordToken/"+userid;
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+token);
		headers.set("requestingIp", requestingIp);
		headers.set("userid", userid);
//		HttpEntity<String> entity = new HttpEntity<String>(formdata,headers);
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		return restTemplate.postForEntity(url, entity,  String.class);
	}
	
	public ResponseEntity<String> isUserExisitng(String mobile) throws JsonProcessingException{
		final String url = SERVICE_URL1 + "/checkUserExist/"+mobile;
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", ANONYMOUS_TOKEN);
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		return restTemplate.postForEntity(url, entity,  String.class);
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
