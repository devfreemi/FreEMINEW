package com.freemi.ui.restclient;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.freemi.common.util.CommonConstants;
import com.freemi.entity.bse.BseAOFUploadRequest;
import com.freemi.entity.bse.BseEMandateRegistration;
import com.freemi.entity.bse.BseFatcaForm;
import com.freemi.entity.bse.BseOrderEntry;
import com.freemi.entity.bse.BseOrderPaymentRequest;
import com.freemi.entity.bse.BsePaymentStatus;
import com.freemi.entity.bse.BseRegistrationMFD;
import com.freemi.entity.bse.BseSipOrderEntry;
import com.freemi.entity.bse.BseXipISipOrderEntry;
import com.freemi.services.interfaces.BseRestClientService;
import com.google.gson.JsonObject;

@Component
public class RestClientBseImpl implements BseRestClientService {

	@Autowired
	Environment env;


	private static final Logger logger = LogManager.getLogger(RestClientBseImpl.class);


	@Override
	public String otpGeneration(String userid){
		logger.info("Beginning process to send generate OTP for Login to Portal..");

		final String url = env.getProperty("investment.bse.serviceurl") + "/generateotp";
		//		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();
		String formdata = null;
		ResponseEntity<?> response = null;
		String returnRes="FAIL";
		JsonObject form = new  JsonObject();
		form.addProperty("mobile", userid);
		form.addProperty("mail", "");

		formdata = form.toString();

		logger.info("OTP Login form- "+ formdata);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
		HttpEntity<String> entity = new HttpEntity<String>(formdata,headers);

		if(CommonConstants.BSE_OTP_ENABLED.equalsIgnoreCase("Y")){
			response= restTemplate.postForEntity(url, entity,  String.class);
			returnRes=response.getBody().toString();
			logger.info("Response- "+ response.getBody().toString());

		}else{
			returnRes = "OTP=123456";
		}
		return returnRes;
	}
	@Override
	public String otpverify(String userid,String otp){
		logger.info("Beginning process to send reuest to bse service for OTP.. "+ userid);

		final String url = env.getProperty("investment.bse.serviceurl") + "/validateotp";
		//		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();
		String formdata = null;
		ResponseEntity<?> response = null;
		String returnRes="FAIL";
		JsonObject form = new  JsonObject();
		form.addProperty("mobile", userid);
		form.addProperty("otpnum", otp);

		formdata = form.toString();

		logger.info("OTP for login verification- "+ formdata);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
		HttpEntity<String> entity = new HttpEntity<String>(formdata,headers);

		if(CommonConstants.BSE_OTP_ENABLED.equalsIgnoreCase("Y")){
			response= restTemplate.postForEntity(url, entity,  String.class);
			returnRes=response.getBody().toString();
			logger.info("Response for OTP verification- "+ response.getBody().toString());

		}else{
			returnRes = "Entered Otp is NOT valid. Please Retry!";
		}
		//		returnRes = "OTP=123456";
		return returnRes;
	}

	@Override
	public String registerUser(BseRegistrationMFD form){
		logger.info("registerUser() : Beginning process to send request to bse service for registration..");

		final String url = env.getProperty("investment.bse.serviceurl") + "/createuser";
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();
		String formdata = null;
		ResponseEntity<?> response = null;
		String returnRes="FAIL";
		try {
			formdata = mapper.writeValueAsString(form);
			logger.info("registerUser(): REGISTER USER BSE:-" +formdata);
			HttpHeaders headers = new HttpHeaders();
			headers.set("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
			HttpEntity<String> entity = new HttpEntity<String>(formdata,headers);

			if(env.getProperty(CommonConstants.BSE_CALL_TEST_ENABLED).equalsIgnoreCase("N")){
				response= restTemplate.postForEntity(url, entity,  String.class);
				returnRes=response.getBody().toString();
				logger.info("registerUser() : Register Request Response- "+ response.getBody().toString());
				if(returnRes.equalsIgnoreCase("100|RECORD INSERTED SUCCESSFULLY")){
					returnRes = "SUCCESS";
				}
			}else{
				//				returnRes = "SUCCESS";
				/*
				 * if(form.getClientDob()!="") returnRes =
				 * "101|FAILED: INVALID DATE OF BIRTH OF INDIVIDUAL"; else returnRes =
				 * "101|FAILED: DOB MANDATORY";
				 */
				returnRes = "SUCCESS";
//			    returnRes="101|FAILED: INVALID DATE OF BIRTH OF INDIVIDUAL";
//			    returnRes="100|RECORD INSERTED SUCCESSFULLY";
			}


		} catch (JsonProcessingException e) {
			logger.error("registerUser(): Failed to write form data", e);
			returnRes = "101|Error processing data";
		}

		return returnRes;
	}

	@Override
	public String purchaseRequestProcess(BseOrderEntry form){

		logger.info("purchaseRequestProcess(): Process to send transaction request...");

		final String url = env.getProperty("investment.bse.serviceurl") + "/orderlumpsum";
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();
		String formdata = null;
		String returnRes="FAIL";
		try {
			formdata = mapper.writeValueAsString(form);

			logger.info("purchaseRequestProcess(): Requesting lumpsum purchase with details- "+ form.getTransCode() + " : " +  formdata);
			HttpHeaders headers = new HttpHeaders();
			headers.set("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
			HttpEntity<String> entity = new HttpEntity<String>(formdata,headers);
			if(env.getProperty(CommonConstants.BSE_CALL_TEST_ENABLED).equalsIgnoreCase("N")){
				ResponseEntity<?> response= restTemplate.postForEntity(url, entity,  String.class);
				returnRes=response.getBody().toString();
				logger.info("purchaseRequestProcess(): Response for lumpsum purchase/cancellation/redemption/additional- "+ response.getBody().toString());
			}else{
				returnRes = "NEW|201902212627300013|1466093|SUMANTA1|26273|DEBA593C|ORD CONF: Your Request for FRESH PURCHASE 5000.000  in SCHEME: ID289-DR THRO : PHYSICAL is confirmed for CLIENT : DEBASISH SARKAR (Code: DEBA593C)  CONFIRMATION TIME: Feb 21 2019  9:28PM ENTRY BY:  ORDER NO: 1466093 OFFLINE ORDER WILL BE TRIGGERED  ON NEXT WORKING DAY|0";
				//				returnRes = "NEW|201902122627300002|0|SUMANTA1|26273|DEBA593C|FAILED: ORDER ENTRY NOT ALLOWED IN THE SCHEME|1";
				returnRes = "NEW|201904142627300052|1554280|SUMANTA1|26273|DEBA593C|ORD CONF: Your Request for FRESH REDEMPTION 500.000 AMOUNT in SCHEME: IDBI-NJGP-GR THRO : PHYSICAL is confirmed for CLIENT : DEBASISH SARKAR (Code: DEBA593C)  CONFIRMATION TIME: Apr 14 2019  9:32PM ENTRY BY:  ORDER NO: 1554280 OFFLINE ORDER WILL BE TRIGGERED  ON NEXT WORKING DAY|0";
			}

		} catch (JsonProcessingException e) {
			logger.error("purchaseRequestProcess(): Failed to write form data", e);
			returnRes = "101|Error processing data";
		}

		return returnRes;
	}


	@Override
	public String purchaseSIPRequestProcess(BseSipOrderEntry form){
		logger.info("purchaseSIPRequestProcess(): Process to send SIP request...");
		final String url = env.getProperty("investment.bse.serviceurl") + "/ordersip";
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();
		String formdata = null;
		String returnRes="FAIL";
		try {
			formdata = mapper.writeValueAsString(form);

			logger.info("purchaseSIPRequestProcess(): Requesting  SIP purchase with details- "+ formdata);
			HttpHeaders headers = new HttpHeaders();
			headers.set("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
			HttpEntity<String> entity = new HttpEntity<String>(formdata,headers);
			if(env.getProperty(CommonConstants.BSE_CALL_TEST_ENABLED).equalsIgnoreCase("N")){
				ResponseEntity<?> response= restTemplate.postForEntity(url, entity,  String.class);
				returnRes=response.getBody().toString();
				logger.info("purchaseSIPRequestProcess(): Response for SIP purchase- "+ response.getBody().toString());
			}else{
				returnRes = "NEW|201902202627300003|26273|DEBA593C|SUMANTA1|214516|SIP HAS BEEN REGISTERED, SIP REG NO IS : 214516|0";	
			}


		} catch (JsonProcessingException e) {
			logger.error("purchaseSIPRequestProcess(): Failed to write form data", e);
			returnRes = "101|Error processing data";
		}



		return returnRes;
	}

	@Override
	public String purchaseCancelXSIPISIPRequestProcess(BseXipISipOrderEntry form){

		logger.info("purchaseCancelXSIPISIPRequestProcess(): Process to send SIP request for type- "+ form.getTransactionCode());

		final String url = env.getProperty("investment.bse.serviceurl") + "/orderxsip";
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();
		String formdata = null;
		String returnRes="FAIL";
		try {
			formdata = mapper.writeValueAsString(form);

			logger.info("Requesting  X-SIP/I-SIP purchase with details- "+ formdata);
			HttpHeaders headers = new HttpHeaders();
			headers.set("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
			HttpEntity<String> entity = new HttpEntity<String>(formdata,headers);
			if(env.getProperty(CommonConstants.BSE_CALL_TEST_ENABLED).equalsIgnoreCase("N")){
				ResponseEntity<?> response= restTemplate.postForEntity(url, entity,  String.class);
				returnRes=response.getBody().toString();
				logger.info("Response for X-SIP I-SIP purchase- "+ response.getBody().toString());
			}else{
				returnRes = "NEW|201902202627300003|26273|DEBA593C|SUMANTA1|214516|SIP HAS BEEN REGISTERED, SIP REG NO IS : 214516|0";	
			}
		} catch (JsonProcessingException e) {
			logger.error("purchaseXSIPISIPRequestProcess(): Failed to write form data", e);

			returnRes = "101|Error processing data";
		}



		return returnRes;
	}

	@Override
	public String purchasePaymentLink(BseOrderPaymentRequest form){

		logger.info("purchasePaymentLink(): Process to send request...");
		final String url = env.getProperty("investment.bse.serviceurl") + "/orderpayment";
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();
		String formdata = null;
		String returnRes="FAIL";
		try {
			formdata = mapper.writeValueAsString(form);

			logger.info("Requesting for purchase paymentlink- "+ formdata);
			HttpHeaders headers = new HttpHeaders();
			headers.set("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
			HttpEntity<String> entity = new HttpEntity<String>(formdata,headers);

			if(env.getProperty(CommonConstants.BSE_CALL_TEST_ENABLED).equalsIgnoreCase("N")){
				ResponseEntity<?> response= restTemplate.postForEntity(url, entity,  String.class);
				returnRes=response.getBody().toString();
				logger.info("Response for order payment- "+ response.getBody().toString());
			}else{
				returnRes="100|http://bsestarmfdemo.bseindia.com/ClientOrderPayment.aspx?2GlYVCMUMEoqpv83kv+XG8zU3u2ilo1pSoS0oByWDDq7kAapBT2M6nBUJ3G1XrbWGSEE/veQ7c1pelDWLyaNKJGWs9JGorFhPgmZkRcjPPQbE0iS3MxSF6cHY20UNbh760nFus9nGlM=";
			}
		} catch (JsonProcessingException e) {
			logger.error("purchasePaymentLink(): Failed to write form data", e);
			returnRes = "101|Error processing data";
		}

		return returnRes;
	}

	@Override
	public String orderPaymentStatus(BsePaymentStatus form){
		logger.info("orderPaymentStatus(): Process to send request...");
		final String url = env.getProperty("investment.bse.serviceurl") + "/orderpaymentstatus";
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();
		String formdata = null;
		String returnRes="FAIL";
		try {
			formdata = mapper.writeValueAsString(form);

			logger.info("Requesting ORDER payment status- "+ formdata);
			HttpHeaders headers = new HttpHeaders();
			headers.set("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
			HttpEntity<String> entity = new HttpEntity<String>(formdata,headers);
			if(env.getProperty(CommonConstants.BSE_CALL_TEST_ENABLED).equalsIgnoreCase("N")){
				ResponseEntity<?> response= restTemplate.postForEntity(url, entity,  String.class);
				returnRes=response.getBody().toString();
				logger.info("orderPaymentStatus(): Response for order payment- "+ response.getBody().toString());
			}else{
				returnRes="100|PAYMENT NOT INITIATED FOR GIVEN ORDER";
			}

		} catch (JsonProcessingException e) {
			logger.error("orderPaymentStatus(): Failed to write form data", e);
			returnRes = "101|Error processing data";
		}

		return returnRes;
	}

	@Override
	public String eMandateRegistration(BseEMandateRegistration form){
		logger.info("eMandateRegistration(): Process to send request...");
		final String url = env.getProperty("investment.bse.serviceurl") + "/mandateregistration";
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();
		String formdata = null;
		String returnRes="FAIL";
		try {
			formdata = mapper.writeValueAsString(form);

			logger.info("Requesting E-mandate registartion to API- "+ formdata);
			HttpHeaders headers = new HttpHeaders();
			headers.set("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
			HttpEntity<String> entity = new HttpEntity<String>(formdata,headers);

			if(env.getProperty(CommonConstants.BSE_CALL_TEST_ENABLED).equalsIgnoreCase("N")){
				ResponseEntity<?> response= restTemplate.postForEntity(url, entity,  String.class);
				returnRes=response.getBody().toString();
				logger.info("Response for mandate registration- "+ response.getBody().toString());
			}else{
				//				returnRes = "101|FAILED: AMOUNT SHOULD NOT BE BLANK";
				//				returnRes = "101|FAILED: INVALID CLIENT ACCOUNT NUMBER";
				returnRes = "100|MANDATE REGISTRATION DONE SUCCESSFULLY|556918";
//				returnRes ="101|FAILED: AMOUNT SHOULD NOT BE 0";
			}

		} catch (JsonProcessingException e) {
			logger.error("eMandateRegistration(): Failed to write form data", e);
			returnRes = "101|Error processing data";
		}


		return returnRes;
	}

	@Override
	public String fatcaDeclaration(BseFatcaForm form){
		logger.info("fatcaDeclaration(): Process to send request...");
		final String url = env.getProperty("investment.bse.serviceurl") + "/fatcaupload";
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();
		String formdata = null;
		String returnRes="FAIL";
		try {
			formdata = mapper.writeValueAsString(form);

			logger.info("Requesting FATCA registration to API- "+ formdata);
			HttpHeaders headers = new HttpHeaders();
			headers.set("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
			HttpEntity<String> entity = new HttpEntity<String>(formdata,headers);

			if(env.getProperty(CommonConstants.BSE_CALL_TEST_ENABLED).equalsIgnoreCase("N")){
				ResponseEntity<?> response= restTemplate.postForEntity(url, entity,  String.class);
				returnRes=response.getBody().toString();
				logger.info("Response for fatca registration- "+ response.getBody().toString());
			}else{
				//				returnRes = "101|FAILED: AMOUNT SHOULD NOT BE BLANK";
				//				returnRes = "101|FAILED: INVALID CLIENT ACCOUNT NUMBER";
//				returnRes = "100|RECORD SAVED SUCCESSFULLY";
//				returnRes = "101|FAILED: INVALID ADDRESS TYPE";
			    	returnRes ="101|FAILED: INVALID DATE OF BIRTH FORMAT MM/DD/YYYY";
			}

		} catch (JsonProcessingException e) {
			logger.error("fatcaDeclaration(): Failed to write form data", e);
			returnRes = "101|Error processing data";
		}


		return returnRes;
	}

	@Override
	public String panStatusCheck(String panNumber){
		logger.info("panStatusCheck(): Process to send request...");
		final String url = env.getProperty("investment.bse.serviceurl") + "/panstatus";
		RestTemplate restTemplate = new RestTemplate();
		String returnRes="FAIL";

		try {
			JsonObject parametersMap = new  JsonObject();
			parametersMap.addProperty("PAN", panNumber);

			logger.info("Requesting FATCA registartion to API- "+ parametersMap.toString());

			HttpHeaders headers = new HttpHeaders();
			headers.set("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
			HttpEntity<String> entity = new HttpEntity<String>(parametersMap.toString(),headers);

			if(env.getProperty(CommonConstants.BSE_CALL_TEST_ENABLED).equalsIgnoreCase("N")){
				ResponseEntity<?> response= restTemplate.postForEntity(url, entity,  String.class);
				returnRes=response.getBody().toString();
				logger.info("Response for pan status check- "+ response.getBody().toString());
			}else{
				returnRes = "100|YUIII2345V|N|N|N|N|AOF PAN Search details|N|";
			}
		}catch(Exception e) {
			logger.error("panStatusCheck(): Failed to write form data", e);
			returnRes = "101|Error processing data";
		}

		return returnRes;
	}

	@Override
	public String uploadAOF(BseAOFUploadRequest form){
		logger.info("uploadAOF(): Process to send request...");
		final String url = env.getProperty("investment.bse.serviceurl") + "/uploadAOF";
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();
		String formdata = null;
		String returnRes="FAIL";
		try {
			formdata = mapper.writeValueAsString(form);
			
			logger.info("Requesting for AOF upload- "+ formdata);
			HttpHeaders headers = new HttpHeaders();
			headers.set("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
			HttpEntity<String> entity = new HttpEntity<String>(formdata,headers);

			if(env.getProperty(CommonConstants.BSE_CALL_TEST_ENABLED).equalsIgnoreCase("N")){
				ResponseEntity<?> response= restTemplate.postForEntity(url, entity,  String.class);
				returnRes=response.getBody().toString();
				logger.info("Response for AOF upload- "+ response.getBody().toString());
			}else{
				//			returnRes = "100|File Uploaded Successfully.";
				//			returnRes="101|Invalid FileType";
				//			returnRes="101|FAILED: PAN NO ALREADY APPROVED";
				returnRes = "101|FAILED: IMAGE IS ALREADY AVAILABLE AND IMAGE STATUS IS PENDING";

			}
		} catch (JsonProcessingException e) {
			logger.error("uploadAOF(): Failed to write form data", e);
			returnRes = "101|Error processing data";
		}
		
		return returnRes;
	}

}
