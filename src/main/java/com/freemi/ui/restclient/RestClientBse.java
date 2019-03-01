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
import com.freemi.common.util.CommonConstants;
import com.freemi.entity.bse.BseAOFUploadRequest;
import com.freemi.entity.bse.BseEMandateRegistration;
import com.freemi.entity.bse.BseOrderEntry;
import com.freemi.entity.bse.BseOrderPaymentRequest;
import com.freemi.entity.bse.BsePaymentStatus;
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
		ResponseEntity<?> response = null;
		String returnRes="FAIL";
		try {
			formdata = mapper.writeValueAsString(form);
		} catch (JsonProcessingException e) {
			logger.error("registerUser(): Failed to write form data", e);
		}
		logger.info(formdata);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
		HttpEntity<String> entity = new HttpEntity<String>(formdata,headers);

		if(CommonConstants.BSE_CALL_TEST_ENABLED.equalsIgnoreCase("N")){
			response= restTemplate.postForEntity(url, entity,  String.class);
			returnRes=response.getBody().toString();
			logger.info("Response- "+ response.getBody().toString());
			if(returnRes.equalsIgnoreCase("100|RECORD INSERTED SUCCESSFULLY")){
				returnRes = "SUCCESS";
			}
		}else{
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
			logger.error("purchaseRequestProcess(): Failed to write form data", e);
		}
		logger.info("Requesting lumpsum purchase with details- "+ formdata);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
		HttpEntity<String> entity = new HttpEntity<String>(formdata,headers);
		if(CommonConstants.BSE_CALL_TEST_ENABLED.equalsIgnoreCase("N")){
			ResponseEntity<?> response= restTemplate.postForEntity(url, entity,  String.class);
			returnRes=response.getBody().toString();
			logger.info("Response for lumpsum purchase- "+ response.getBody().toString());
		}else{
			returnRes = "NEW|201902212627300013|1466093|SUMANTA1|26273|DEBA593C|ORD CONF: Your Request for FRESH PURCHASE 5000.000  in SCHEME: ID289-DR THRO : PHYSICAL is confirmed for CLIENT : DEBASISH SARKAR (Code: DEBA593C)  CONFIRMATION TIME: Feb 21 2019  9:28PM ENTRY BY:  ORDER NO: 1466093 OFFLINE ORDER WILL BE TRIGGERED  ON NEXT WORKING DAY|0";
//			returnRes = "NEW|201902122627300002|0|SUMANTA1|26273|DEBA593C|FAILED: ORDER ENTRY NOT ALLOWED IN THE SCHEME|1";
		}
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
			logger.error("purchaseSIPRequestProcess(): Failed to write form data", e);
		}
		logger.info("Requesting  SIP purchase with details- "+ formdata);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
		HttpEntity<String> entity = new HttpEntity<String>(formdata,headers);
		if(CommonConstants.BSE_CALL_TEST_ENABLED.equalsIgnoreCase("N")){
			ResponseEntity<?> response= restTemplate.postForEntity(url, entity,  String.class);
			returnRes=response.getBody().toString();
			logger.info("Response for SIP purchase- "+ response.getBody().toString());
		}else{
			returnRes = "NEW|201902202627300003|26273|DEBA593C|SUMANTA1|214516|SIP HAS BEEN REGISTERED, SIP REG NO IS : 214516|0";	
		}


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
			logger.error("purchasePaymentLink(): Failed to write form data", e);
		}
		logger.info("Requesting for purchase paymentlink- "+ formdata);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
		HttpEntity<String> entity = new HttpEntity<String>(formdata,headers);

		if(CommonConstants.BSE_CALL_TEST_ENABLED.equalsIgnoreCase("N")){
			ResponseEntity<?> response= restTemplate.postForEntity(url, entity,  String.class);
			returnRes=response.getBody().toString();
			logger.info("Response for order payment- "+ response.getBody().toString());
		}else{
			returnRes="100|http://bsestarmfdemo.bseindia.com/ClientOrderPayment.aspx?2GlYVCMUMEoqpv83kv+XG8zU3u2ilo1pSoS0oByWDDq7kAapBT2M6nBUJ3G1XrbWGSEE/veQ7c1pelDWLyaNKJGWs9JGorFhPgmZkRcjPPQbE0iS3MxSF6cHY20UNbh760nFus9nGlM=";
		}
		return returnRes;
	}

	public static String orderPaymentStatus(BsePaymentStatus form){

		final String url = SERVICE_URL1 + "/orderpaymentstatus";
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();
		String formdata = null;
		String returnRes="FAIL";
		try {
			formdata = mapper.writeValueAsString(form);
		} catch (JsonProcessingException e) {
			logger.error("orderPaymentStatus(): Failed to write form data", e);
		}
		logger.info("Requesting ORDER payment status- "+ formdata);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
		HttpEntity<String> entity = new HttpEntity<String>(formdata,headers);
		if(CommonConstants.BSE_CALL_TEST_ENABLED.equalsIgnoreCase("N")){
			ResponseEntity<?> response= restTemplate.postForEntity(url, entity,  String.class);
			returnRes=response.getBody().toString();
			logger.info("Response for order payment- "+ response.getBody().toString());
		}else{
			returnRes="100|http://bsestarmfdemo.bseindia.com/ClientOrderPayment.aspx?2GlYVCMUMEoqpv83kv+XG8zU3u2ilo1pSoS0oByWDDq7kAapBT2M6nBUJ3G1XrbWGSEE/veQ7c1pelDWLyaNKJGWs9JGorFhPgmZkRcjPPQbE0iS3MxSF6cHY20UNbh760nFus9nGlM=";
		}
		return returnRes;
	}

	public static String eMandateRegistration(BseEMandateRegistration form){

		final String url = SERVICE_URL1 + "/mandateregistration";
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();
		String formdata = null;
		String returnRes="FAIL";
		try {
			formdata = mapper.writeValueAsString(form);
		} catch (JsonProcessingException e) {
			logger.error("eMandateRegistration(): Failed to write form data", e);
		}
		logger.info("Requesting E-mandate registartion to API- "+ formdata);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
		HttpEntity<String> entity = new HttpEntity<String>(formdata,headers);

		if(CommonConstants.BSE_CALL_TEST_ENABLED.equalsIgnoreCase("N")){
			ResponseEntity<?> response= restTemplate.postForEntity(url, entity,  String.class);
			returnRes=response.getBody().toString();
			logger.info("Response for mandate registration- "+ response.getBody().toString());
		}else{
//			returnRes = "101|FAILED: AMOUNT SHOULD NOT BE BLANK";
//			returnRes = "101|FAILED: INVALID CLIENT ACCOUNT NUMBER";
			returnRes = "100|MANDATE REGISTRATION DONE SUCCESSFULLY|556918";
		}

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
			logger.error("uploadAOF(): Failed to write form data", e);
		}
		logger.info("Requesting for AOF upload- "+ formdata);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
		HttpEntity<String> entity = new HttpEntity<String>(formdata,headers);

		if(CommonConstants.BSE_CALL_TEST_ENABLED.equalsIgnoreCase("N")){
			ResponseEntity<?> response= restTemplate.postForEntity(url, entity,  String.class);
			returnRes=response.getBody().toString();
			logger.info("Response for AOF upload- "+ response.getBody().toString());
		}else{
			returnRes = "Uploaded";
		}
		return returnRes;
	}

}
