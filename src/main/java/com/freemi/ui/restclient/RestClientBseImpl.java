package com.freemi.ui.restclient;

import java.util.Calendar;

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
import com.freemi.entity.bse.Uccregisterresponse;
import com.freemi.entity.investment.Emandatestaus;
import com.freemi.services.interfaces.BseRestClientService;
import com.google.gson.JsonObject;

@Component
public class RestClientBseImpl implements BseRestClientService {

	@Autowired
	Environment env;


	private static final Logger logger = LogManager.getLogger(RestClientBseImpl.class);

	
	@Deprecated
	@Override
	public String otpGeneration(String userid){
		logger.info("Beginning process to send generate OTP for Login to Portal..");

		final String url = env.getProperty(CommonConstants.URL_SERVICE_MF_BSE_V1) + "/generateotp";
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
	
	@Deprecated
	@Override
	public String otpverify(String userid,String otp){
		logger.info("Beginning process to send reuest to bse service for OTP.. "+ userid);

		final String url = env.getProperty(CommonConstants.URL_SERVICE_MF_BSE_V1) + "/validateotp";
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

	@Deprecated
	@Override
	public String registerUser(BseRegistrationMFD form){
		logger.info("registerUser() : Beginning process to send request to bse service for registration..");

		final String url = env.getProperty(CommonConstants.URL_SERVICE_MF_BSE_V1) + "/createuser";
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
	public String registeruserv2(BseRegistrationMFD form) {
		logger.info("registeruserv2() : Beginning process to send request to bse service for registration..");
		Uccregisterresponse response = new Uccregisterresponse();
		
//		final String url = env.getProperty(CommonConstants.URL_SERVICE_MF_BSE_UCC_REGISTRATION_V2) + "/ucc-register";
		//after migrating codebase into one
		final String url = env.getProperty(CommonConstants.URL_SERVICE_MF_BSE_V1) + "/ucc-register";
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();
		String formdata = null;
//		ResponseEntity<?> response = null;
		String returnRes="FAIL";
		try {
			formdata = mapper.writeValueAsString(form);
			logger.info("registeruserv2(): REGISTER USER BSE:-" +formdata);
			HttpHeaders headers = new HttpHeaders();
			headers.set("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
			HttpEntity<String> entity = new HttpEntity<String>(formdata,headers);

			if(env.getProperty(CommonConstants.BSE_CALL_TEST_ENABLED).equalsIgnoreCase("N")){
				response= restTemplate.postForObject(url, entity, Uccregisterresponse.class);
//				returnRes=response.getBody().toString();
				logger.info("registeruserv2() : Register Request Response- "+ mapper.writeValueAsString(response));
				if(response.getStatus().equals("0"))
				{ 
					returnRes = "SUCCESS";
				}else {
					returnRes= response.getStatus() + "|"+ response.getRemarks();
				}
				 
			}else{
				//				returnRes = "SUCCESS";
				/*
				 * if(form.getClientDob()!="") returnRes =
				 * "101|FAILED: INVALID DATE OF BIRTH OF INDIVIDUAL"; else returnRes =
				 * "101|FAILED: DOB MANDATORY";
				 */
				logger.info("registeruserv2(): Test phase enabled. Sending back dummy response.");
				returnRes = "SUCCESS";
//			    returnRes="101|FAILED: INVALID DATE OF BIRTH OF INDIVIDUAL";
//			    returnRes="100|RECORD INSERTED SUCCESSFULLY";
				response.setStatus("0");
				response.setRemarks("Success");
				
				
			}


		} catch (JsonProcessingException e) {
			logger.error("registeruserv2(): Failed to write form data", e);
			returnRes = "1|Error processing data";
		}

		return returnRes;
		
	}
	
	
	@Override
	public String purchaseRequestProcess(BseOrderEntry form){

		logger.info("purchaseRequestProcess(): Process to send transaction request...");

		final String url = env.getProperty(CommonConstants.URL_SERVICE_MF_BSE_V1) + "/orderlumpsum";
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
			    String order = Long.toString(Calendar.getInstance().getTimeInMillis()).substring(0,5);
//				returnRes = "NEW|201902212627300013|1466093|SUMANTA1|26273|DEBA593C|ORD CONF: Your Request for FRESH PURCHASE 5000.000  in SCHEME: ID289-DR THRO : PHYSICAL is confirmed for CLIENT : DEBASISH SARKAR (Code: DEBA593C)  CONFIRMATION TIME: Feb 21 2019  9:28PM ENTRY BY:  ORDER NO: 1466093 OFFLINE ORDER WILL BE TRIGGERED  ON NEXT WORKING DAY|0";
				//				returnRes = "NEW|201902122627300002|0|SUMANTA1|26273|DEBA593C|FAILED: ORDER ENTRY NOT ALLOWED IN THE SCHEME|1";
				returnRes = "NEW|201904142627300052|"+order+"|SUMANTA1|26273|DEBA593C|ORD CONF: Your Request for FRESH REDEMPTION 500.000 AMOUNT in SCHEME: IDBI-NJGP-GR THRO : PHYSICAL is confirmed for CLIENT : DEBASISH SARKAR (Code: DEBA593C)  CONFIRMATION TIME: Apr 14 2019  9:32PM ENTRY BY:  ORDER NO: 1554280 OFFLINE ORDER WILL BE TRIGGERED  ON NEXT WORKING DAY|0";
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
		final String url = env.getProperty(CommonConstants.URL_SERVICE_MF_BSE_V1) + "/ordersip";
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

		final String url = env.getProperty(CommonConstants.URL_SERVICE_MF_BSE_V1) + "/orderxsip";
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
			    String order = Long.toString(Calendar.getInstance().getTimeInMillis()).substring(0,5);
				returnRes = "NEW|201902202627300003|26273|DEBA593C|SUMANTA1|"+order+"|SIP HAS BEEN REGISTERED, SIP REG NO IS : 214516|0";	
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
		final String url = env.getProperty(CommonConstants.URL_SERVICE_MF_BSE_V1) + "/orderpayment";
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
		final String url = env.getProperty(CommonConstants.URL_SERVICE_MF_BSE_V1) + "/orderpaymentstatus";
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
		final String url = env.getProperty(CommonConstants.URL_SERVICE_MF_BSE_V1) + "/mandateregistration";
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
		final String url = env.getProperty(CommonConstants.URL_SERVICE_MF_BSE_V1) + "/fatcaupload";
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
				returnRes = "100|RECORD INSERTED SUCCESSFULLY";
//				returnRes = "101|FAILED: INVALID ADDRESS TYPE";
//			    	returnRes ="101|FAILED: INVALID DATE OF BIRTH FORMAT MM/DD/YYYY";
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
		final String url = env.getProperty(CommonConstants.URL_SERVICE_MF_BSE_V1) + "/panstatus";
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
		final String url = env.getProperty(CommonConstants.URL_SERVICE_MF_BSE_V1) + "/uploadAOF";
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
				logger.info("uploadAOF(): Test phase enabled. Sending back dummy response");
				returnRes = "101|FAILED: IMAGE IS ALREADY AVAILABLE AND IMAGE STATUS IS PENDING";
//				returnRes = "101|FAILED: CLIENT STATUS IS NOT ACTIVE";
			}
		} catch (JsonProcessingException e) {
			logger.error("uploadAOF(): Failed to write form data", e);
			returnRes = "101|Error processing data";
		}
		
		return returnRes;
	}
	@Override
	public String getallotmentstatement(String fromdate, String todate, String orderstatus, String ordertype,
		String settlementtype) {
	    
	    logger.info("getallotmentstatement(): Process to send request...");
		final String url = env.getProperty(CommonConstants.URL_SERVICE_MF_BSE_V1) + "/allotmentstatement";
		logger.info("BSE client- "+ url);
//		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();
		String returnRes="FAIL";
		try {
		    	JsonObject obj = new JsonObject();
		    	obj.addProperty("clientCode", "");
		    	obj.addProperty("Filler1", "");
		    	obj.addProperty("Filler2", "");
		    	obj.addProperty("Filler3", "");
		    	obj.addProperty("fromDate", fromdate);
		    	obj.addProperty("toDate", todate);
		    	obj.addProperty("memberCode", CommonConstants.BSE_MEMBER_ID);
		    	obj.addProperty("OrderNo", "");
		    	obj.addProperty("OrderStatus", orderstatus);
		    	obj.addProperty("Ordertype", ordertype);
		    	obj.addProperty("Password", "");
		    	obj.addProperty("SettlementType", settlementtype);
		    	obj.addProperty("SubOrdertype", "All");
		    	obj.addProperty("UserID", CommonConstants.BSE_USER_ID);
		    	
//			formdata = mapper.writeValueAsString(obj);
			
			HttpHeaders headers = new HttpHeaders();
			headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
			HttpEntity<String> entity = new HttpEntity<String>(obj.toString(),headers);

			if(env.getProperty(CommonConstants.BSE_CALL_TEST_ENABLED).equalsIgnoreCase("N")){
				ResponseEntity<?> response= restTemplate.postForEntity(url, entity,  String.class);
				returnRes=response.getBody().toString();
				logger.info("Response for Allotment statement data- "+ response.getBody().toString());
			}else{
				returnRes = "100|Allotment Statement Details|1000.0000|3890.9139|15230617/96|HDLFGN-GR|1000.0000|DEBASISH87|0.2570||Y|NRM|P|26273|2021006|T1||NRM|N|PSH871586332517499|null||NRM|0|||2020-04-13|HLFGN|Y|329546559||2020-04-16|INF179KB1HK0|0|2500.0000|104.0662|5192879/48|EPEG|2500.0000|DEEPTI790K|24.0230||Y|ISP|P|26273|2021006|T3|E241233|NRM|Y||null||ISP|5106046|20/04/2019||2020-04-13|TEPEG|Y|48602245||2020-04-16|INF277K01451|0|2000.0000|21.4484|1038871259|B303G|2000.0000|DEBASISH87|93.2470||Y|ISP|P|26273|2021006|T1||NRM|N||null||ISP|8684349|09/04/2020||2020-04-13|B303G|Y|227394973||2020-04-16|INF209K01603|0|2500.0000|377.3280|11408916/90|32|2500.0000|SOUMYO89|6.6260||Y|XSP|P|26273|2021006|T3|E241233|NRM|Y||null||XSP|4445520|09/02/2019||2020-04-13|H32|Y|328718728||2020-04-16|INF179K01BB8|0";

			}
		} catch (Exception e) {
			logger.error("getallotmentstatement(): Failed to write form data", e);
			returnRes = "101|Error processing data";
		}
		
		return returnRes;
	}
	@Override
	public String getifscbankdetails(String ifsc) {

		final String url = "https://ifsc.razorpay.com/"+ifsc;
		//		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();
		String formdata = null;
		ResponseEntity<?> response = null;
		String returnRes="FAIL";

		logger.info("OTP Login form- "+ formdata);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);

		if(CommonConstants.BSE_OTP_ENABLED.equalsIgnoreCase("Y")){
			response= restTemplate.getForEntity(url, String.class);
			returnRes=response.getBody().toString();
			logger.info("Response- "+ response.getBody().toString());

		}else{
			returnRes = "{\"STATE\":\"WEST BENGAL\",\"CONTACT\":\"\",\"CITY\":\"KOLKATA\",\"SWIFT\":\"\",\"NEFT\":true,\"MICR\":\"700229019\",\"DISTRICT\":\"KOLKATA\",\"IMPS\":true,\"RTGS\":true,\"CENTRE\":\"KOLKATA\",\"ADDRESS\":\"SWASTIK, GROUND FLOOR, NAZRUL ISLAM AVENUE,  TEGHARIA, VIP ROAD, KOLKATA   700059.\",\"UPI\":true,\"BRANCH\":\"VIP ROAD, KOLKATA\",\"BANK\":\"ICICI Bank\",\"BANKCODE\":\"ICIC\",\"IFSC\":\"ICIC0000371\"}";
		}
		
		return returnRes;
	}
	
	
	@Override
	public String getemandateauthrul(String clientid, String mandateid) {
		 
	    logger.info("getemandateauthrul(): Process to send request...");
		final String url = env.getProperty(CommonConstants.URL_SERVICE_MF_BSE_V1) + "/emandateauthrul";
		logger.info("BSE client- "+ url);
//		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();
		
		String returnRes="FAIL";
		try {
		    	JsonObject obj = new JsonObject();
		    	obj.addProperty("mandateid", "");
		    	obj.addProperty("clientid", "");
		    	obj.addProperty("responsecode", "");
		    	obj.addProperty("mandateurl", "");
			
			HttpHeaders headers = new HttpHeaders();
			headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
			HttpEntity<String> entity = new HttpEntity<String>(obj.toString(),headers);

			if(env.getProperty(CommonConstants.BSE_CALL_TEST_ENABLED).equalsIgnoreCase("N")){
				ResponseEntity<?> response= restTemplate.postForEntity(url, entity,  String.class);
				returnRes=response.getBody().toString();
				logger.info("Response for Emandateauthurl data- "+ response.getBody().toString());
			}else{
				returnRes = "100|www.google.in";

			}
		} catch (Exception e) {
			logger.error("getemandateauthrul(): Failed to process request", e);
			returnRes = "101|Error processing data";
		}
		
		return returnRes;
	}

	@Override
	public Emandatestaus getmandatestatus(String clientid, String mandateid) {
			
		   logger.info("getmandatestatus(): Process to send request...");
			final String url = env.getProperty(CommonConstants.URL_SERVICE_MF_BSE_V1) + "/emandate-status";
			logger.info("BSE client- "+ url);
			RestTemplate restTemplate = new RestTemplate();
			Emandatestaus apiresponse = new Emandatestaus();
			try {
			    	JsonObject obj = new JsonObject();
			    	obj.addProperty("mandateid", "");
			    	obj.addProperty("clientid", "");
			    	obj.addProperty("responsecode", "");
			    	obj.addProperty("mandateurl", "");
				
				HttpHeaders headers = new HttpHeaders();
				headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
				HttpEntity<String> entity = new HttpEntity<String>(obj.toString(),headers);

				if(env.getProperty(CommonConstants.BSE_CALL_TEST_ENABLED).equalsIgnoreCase("N")){
					apiresponse= restTemplate.postForObject(url, entity, Emandatestaus.class);
					
				}else{
					logger.warn("test enabled. Returning dummy emandate status..");
					apiresponse.setRequeststatus("100");
					apiresponse.setRequestmsg("Mandate Details");
					apiresponse.setRemarks("REGISTERED BY MEMBER");
					
				}
			} catch (Exception e) {
				logger.error("getmandatestatus(): Failed to process request", e);
				apiresponse.setRequeststatus("101");
				apiresponse.setRequestmsg("Failed to process request. Kindly try after sometime");
			}
			
			return apiresponse;
	}
	

}
