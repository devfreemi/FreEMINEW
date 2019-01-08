package com.freemi.ui.restclient;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GoogleSecurity {
	private static final Logger logger = LogManager.getLogger(GoogleSecurity.class);
	private static final String URL ="https://www.google.com/recaptcha/api/siteverify";
	private static final String SECRET = "6LdvUoQUAAAAAC3PK7EhLRdEuru8EcF_2QEpMCAV";

	public static boolean verifyRecaptcha(String captchaRespone, String isEnabled, String requestorip, String requestingUrl){
		logger.info("Google security scan stated for url -" + requestingUrl + " received from ip - " + requestorip);
		boolean flag= true;
		if(isEnabled.equalsIgnoreCase("Y")){
			RestTemplate restTemplate = new RestTemplate();

			UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromHttpUrl(URL)
					.queryParam("systemip", requestorip)
					.queryParam("secret", SECRET)
					.queryParam("response", captchaRespone);

			ResponseEntity<String> response = null;
			try{
			System.out.println(urlBuilder.toUriString());
			response = restTemplate.exchange(urlBuilder.toUriString(), HttpMethod.POST, null, String.class);
			logger.debug("Response from google security api for mobile number - "+ response.getBody());
			
				JsonObject jsonObj = (JsonObject) new JsonParser().parse(response.getBody());
				if(!jsonObj.get("success").getAsBoolean()){
					flag=false;
				}
			}catch(Exception e){
				logger.error("Unable to prase google recptcha json recaptcha", e.getMessage());
				flag=false;
			}
		}else{
			logger.info("Google secutiry is tunrned off. Skipping validation.");
		}

		return  flag;
	}

}
