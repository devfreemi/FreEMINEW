package com.freemi.ui.restclient;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonObject;

public class RestClientApps {

	private static final Logger logger = LogManager.getLogger(RestClientApps.class);

	private static final String SERVICE_URL1 = "http://localhost:8090/loans";


	public static String setAllAppSession(String userid, String email, String name, String token){
		logger.info("Beginning process to send session data to other apps");
		String returnRes="ERROR";
		try{
			final String url = SERVICE_URL1 + "/setLoginSession";
			RestTemplate restTemplate = new RestTemplate();
			String formdata = null;
			ResponseEntity<?> response = null;

			JsonObject form = new  JsonObject();
			form.addProperty("loggedSession", name);
			form.addProperty("token", token);
			form.addProperty("userid", userid);
			form.addProperty("email",email);

			formdata = form.toString();

			logger.info("OTP Login form- "+ formdata);
			HttpHeaders headers = new HttpHeaders();
			headers.set("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
			HttpEntity<String> entity = new HttpEntity<String>(formdata,headers);

			response= restTemplate.postForEntity(url, entity,  String.class);
			returnRes=response.getBody().toString();
			logger.info("Response received for setting session in appliaction loans- "+ response.getBody().toString());
		}catch(Exception e){
			logger.error("Failed to set session to other application. ",e);
		}
		return returnRes;
	}
	
	
	public static String logoutAllApplication(String userid, String email, String name, String token){
		logger.info("Beginning process to logout user session from other apps");
		String returnRes="ERROR";
		try{
			final String url = SERVICE_URL1 + "/logoutSession";
			RestTemplate restTemplate = new RestTemplate();
			String formdata = null;
			ResponseEntity<?> response = null;

			JsonObject form = new  JsonObject();
			form.addProperty("loggedSession", name);
			formdata = form.toString();

			logger.info("Logout session from other application for user- "+ formdata);
			HttpHeaders headers = new HttpHeaders();
			headers.set("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
			HttpEntity<String> entity = new HttpEntity<String>(formdata,headers);

			response= restTemplate.postForEntity(url, entity,  String.class);
			returnRes=response.getBody().toString();
			if(returnRes!=null){
				logger.info("Response received for logging out session in appliaction loans- "+ response.getBody().toString());
			}
			
		}catch(Exception e){
			logger.error("Failed to logouut session to other application. ",e);
		}
		return returnRes;
	}


}
