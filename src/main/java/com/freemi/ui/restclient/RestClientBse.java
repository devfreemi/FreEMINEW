package com.freemi.ui.restclient;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.freemi.entity.bse.BseAOFUploadRequest;
import com.freemi.entity.bse.BseOrderEntry;
import com.freemi.entity.bse.BseOrderPaymentRequest;
import com.freemi.entity.bse.BseRegistrationMFD;
import com.freemi.entity.bse.BseSipOrderEntry;

public class RestClientBse {
	
	private static final Logger logger = LogManager.getLogger(RestClientBse.class);
	
//	private final String SERVICE_URL1 = "https://ec2-35-154-76-43.ap-south-1.compute.amazonaws.com/freemibackend";
//	private final String SERVICE_URL1 = "http://ec2-35-154-76-43.ap-south-1.compute.amazonaws.com:8080/freemibackend";
//	private final String SERVICE_URL1 = "http://localhost:8090/freemibackend";
//	private final String SERVICE_URL1 = "http://localhost:8080/freemibackend";
	private static final String SERVICE_URL1 = "http://dev.freemi.in:8090/bsemfservice";
	
	public static String registerUser(BseRegistrationMFD form){
		logger.info("Beginning process to send reuest to bse service for registration..");
		
		final String url = SERVICE_URL1 + "/createuser";
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();
		String formdata = null;
		String returnRes="FAIL";
		try {
			formdata = mapper.writeValueAsString(form);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info(formdata);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
		HttpEntity<String> entity = new HttpEntity<String>(formdata,headers);
		
		ResponseEntity<?> response= restTemplate.postForEntity(url, entity,  String.class);
		returnRes=response.getBody().toString();
			logger.info("Response- "+ response.getBody().toString());
		if(returnRes.equalsIgnoreCase("100|RECORD INSERTED SUCCESSFULLY")){
			returnRes = "SUCCESS";
		}
		return returnRes;
	}
	
	
	public static String purchaseRequestProcess(BseOrderEntry form){
		
		final String url = SERVICE_URL1 + "/orderlumpsum";
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();
		String formdata = null;
		String returnRes="FAIL";
		try {
			formdata = mapper.writeValueAsString(form);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("Requesting purchase with details- "+ formdata);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
		HttpEntity<String> entity = new HttpEntity<String>(formdata,headers);
		
		ResponseEntity<?> response= restTemplate.postForEntity(url, entity,  String.class);
		returnRes=response.getBody().toString();
			logger.info("Response for purchase- "+ response.getBody().toString());
		/*if(returnRes.equalsIgnoreCase("100|RECORD INSERTED SUCCESSFULLY")){
			returnRes = "SUCCESS";
		}*/
		return returnRes;
	}
	
public static String purchaseSIPRequestProcess(BseSipOrderEntry form){
		
		final String url = SERVICE_URL1 + "/ordersip";
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();
		String formdata = null;
		String returnRes="FAIL";
		try {
			formdata = mapper.writeValueAsString(form);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("Requesting  SIP purchase with details- "+ formdata);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
		HttpEntity<String> entity = new HttpEntity<String>(formdata,headers);
		
		ResponseEntity<?> response= restTemplate.postForEntity(url, entity,  String.class);
		returnRes=response.getBody().toString();
			logger.info("Response for purchase- "+ response.getBody().toString());
		/*if(returnRes.equalsIgnoreCase("100|RECORD INSERTED SUCCESSFULLY")){
			returnRes = "SUCCESS";
		}*/
		return returnRes;
	}

	public static String purchasePaymentLink(BseOrderPaymentRequest form){
		
		final String url = SERVICE_URL1 + "/orderpayment";
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();
		String formdata = null;
		String returnRes="FAIL";
		try {
			formdata = mapper.writeValueAsString(form);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("Requesting  SIP purchase with details- "+ formdata);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
		HttpEntity<String> entity = new HttpEntity<String>(formdata,headers);
		
		ResponseEntity<?> response= restTemplate.postForEntity(url, entity,  String.class);
		returnRes=response.getBody().toString();
			logger.info("Response for order payment- "+ response.getBody().toString());
		/*if(returnRes.equalsIgnoreCase("100|RECORD INSERTED SUCCESSFULLY")){
			returnRes = "SUCCESS";
		}*/
		return returnRes;
	}
	
public static String uploadAOF(BseAOFUploadRequest form){
		
		final String url = SERVICE_URL1 + "/uploadAOF";
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();
		String formdata = null;
		String returnRes="FAIL";
		try {
			formdata = mapper.writeValueAsString(form);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("Requesting  SIP purchase with details- "+ formdata);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
		HttpEntity<String> entity = new HttpEntity<String>(formdata,headers);
		
		ResponseEntity<?> response= restTemplate.postForEntity(url, entity,  String.class);
		returnRes=response.getBody().toString();
			logger.info("Response for AOF upload- "+ response.getBody().toString());
		/*if(returnRes.equalsIgnoreCase("100|RECORD INSERTED SUCCESSFULLY")){
			returnRes = "SUCCESS";
		}*/
		return returnRes;
	}

}
