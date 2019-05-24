package com.freemi.controller.implementations;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.freemi.common.util.CommonConstants;
import com.freemi.controller.interfaces.SmsSenderInterface;

@Component
public class SmsSenderImpl implements SmsSenderInterface {
	
	@Autowired
	Environment env;
	
	private static final Logger logger = LogManager.getLogger(SmsSenderImpl.class);

	@Override
	@Async
	public void sendOtp(String mobile, String otp, String validyTime, String other) {
		logger.info("Send OTP to mobile number- "+ mobile);
		if(env.getProperty(com.freemi.common.util.CommonConstants.SMS_SEND_ENABLED).equalsIgnoreCase("Y")){
			if(mobile!=null){
				String message= otp + " is your OTP to login to your FreEMI Account. The otp is valis for "+ validyTime + " minute(s)";
				prepareSms(mobile,message,env.getProperty(CommonConstants.SMS_ROUTE_TRANSACTIONAL));
			}else{
				logger.info("No mobile number found to send registration sms.");
			}
		}else{
			logger.info("SMS sending disabled. Skipping process...");
		}
		logger.info("Sending SMS process complete");
		
	}
	
	
	private void prepareSms(String mobile, String message, String msgRoute){
		RestTemplate template = new RestTemplate();
		HttpHeaders headers= new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
//		System.out.println("Enter");
		try{
		ResponseEntity<String> result = template.exchange(
				smsAPIBuilder(mobile,message,env.getProperty(CommonConstants.SMS_SENDER_ID),msgRoute
						).build().encode().toUri(),
				HttpMethod.GET,
				entity,
				String.class
				);
//		System.out.println("SMS sending response for campaign: "+ mobileNumber+ " : " +result);
		logger.info("SMS sending response for : "+ mobile+ " : " +result.getStatusCodeValue() + " : "+ result.getBody());
		}catch(Exception e){
			logger.error("Errors sending message- "+ e.getMessage());
		}
	}
	
	private UriComponentsBuilder smsAPIBuilder(String mobileNumber, String message, String sender, String route){
		UriComponentsBuilder uriBuilder = UriComponentsBuilder
					.fromHttpUrl(env.getProperty(CommonConstants.SERVICE_PROVIDER_URL))
//				.uri(URI.create("http://api.msg91.com/api/sendhttp.ph"))
				.queryParam("authkey", env.getProperty(CommonConstants.SERVICE_PROVIDER_AUTHKEY))
				.queryParam("mobiles", mobileNumber)
				.queryParam("message", message)
				.queryParam("sender", sender)
				.queryParam("route", route)
				.queryParam("country", "91")
				;
		
		logger.debug(uriBuilder.toUriString());
		return uriBuilder;
	}

}
