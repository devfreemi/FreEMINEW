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
import com.freemi.entity.bse.Nomineeregistrationrequest;
import com.freemi.entity.bse.Nomineeregistrationresponse;
import com.freemi.entity.bse.PauseSIP;
import com.freemi.entity.bse.PauseSIPResponse;
import com.freemi.entity.bse.Paymentgateway;
import com.freemi.entity.bse.Paymentgatewayresponse;
import com.freemi.entity.bse.Uccregisterresponse;
import com.freemi.entity.investment.Emandatestaus;
import com.freemi.entity.investment.Nominee2farequest;
import com.freemi.entity.investment.Nominee2faresponse;
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
				returnRes = "NEW|"+form.getTransNo()+"|"+order+"|SUMANTA1|26273|"+form.getClientCode()+"|DUMMY RESPONSE : ORD CONF:Your Request for FRESH REDEMPTION 500.000 AMOUNT in SCHEME: "+form.getSchemeCd()+" THRO : PHYSICAL is confirmed for CLIENT : DUMMY NAME (Code: "+form.getClientCode()+")  CONFIRMATION TIME: Apr 14 2019  9:32PM ENTRY BY:  ORDER NO: "+order+" OFFLINE ORDER WILL BE TRIGGERED  ON NEXT WORKING DAY|0";
//				returnRes = "NEW|"+form.getTransNo()+"|"+order+"|sumanta1|26273|TES654R451|FAILED: CLIENT NOMINEE AUTHENTICATION PENDING|1";
				
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
		    	obj.addProperty("mandateid", mandateid);
		    	obj.addProperty("clientid", clientid);
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
				returnRes = "100|https://www.google.com";

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
			ObjectMapper mapper =  new ObjectMapper();
			try {
			    	JsonObject obj = new JsonObject();
			    	obj.addProperty("mandateid", mandateid);
			    	obj.addProperty("clientid", clientid);
			    	obj.addProperty("responsecode", "");
			    	obj.addProperty("mandateurl", "");
				
				HttpHeaders headers = new HttpHeaders();
				headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
				HttpEntity<String> entity = new HttpEntity<String>(obj.toString(),headers);

				if(env.getProperty(CommonConstants.BSE_CALL_TEST_ENABLED).equalsIgnoreCase("N")){
					apiresponse= restTemplate.postForObject(url, entity, Emandatestaus.class);
					logger.info("Mandate status response- "+ mapper.writeValueAsString(apiresponse));
				}else{
					logger.warn("test enabled. Returning dummy emandate status..");
					apiresponse.setRequeststatus("100");
					apiresponse.setRequestmsg("Mandate Details");
					apiresponse.setRemarks("");
//					apiresponse.setStatus("PENDING");
					apiresponse.setStatus("REGISTERED BY MEMBER");
				}
			} catch (Exception e) {
				logger.error("getmandatestatus(): Failed to process request", e);
				apiresponse.setRequeststatus("101");
				apiresponse.setRequestmsg("Failed to process request. Kindly try after sometime");
			}
			
			return apiresponse;
	}

	@Override
	public PauseSIPResponse pausexsip(PauseSIP requestdata) {
		logger.info("pausexsip(): Process to send request...");
		final String url = env.getProperty(CommonConstants.URL_SERVICE_MF_BSE_V1) + "/pausexsip";
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();
		String formdata = null;
		PauseSIPResponse response = new PauseSIPResponse();
		try {
			formdata = mapper.writeValueAsString(requestdata);
			
			logger.info("Requesting for SIP pause- "+ formdata);
			HttpHeaders headers = new HttpHeaders();
			headers.set("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
			HttpEntity<String> entity = new HttpEntity<String>(formdata,headers);

			if(env.getProperty(CommonConstants.BSE_CALL_TEST_ENABLED).equalsIgnoreCase("N")){
				response= restTemplate.postForObject(url, entity, PauseSIPResponse.class);
				logger.info("Response from SIP pause- "+ mapper.writeValueAsString(response));
			}else{
				logger.info("pausexsip(): Test phase enabled. Sending back dummy response");
				response.setRegistrationno(requestdata.getRegistrationno());
				response.setStatusflag("0");
				response.setBseremarks("DATA SAVED SUCCESSFULLY");
			}
		} catch (Exception e) {
			logger.error("pausexsip(): Failed to write process data", e);
			response.setStatusflag("1");
			response.setBseremarks("Internal error. Please try after sometime");
		}
		
		return response;
	}

	@Override
	public Paymentgatewayresponse purchasepaymentgateway(Paymentgateway payrequest) {
		logger.info("purchasepaymentgateway(): Process to send request...");
		final String url = env.getProperty(CommonConstants.URL_SERVICE_MF_BSE_V1) + "/payment-new";
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();
		String formdata = null;
		Paymentgatewayresponse response = new Paymentgatewayresponse();
		try {
			formdata = mapper.writeValueAsString(payrequest);
			
			logger.info("Requesting for payment gateway- "+ formdata);
			HttpHeaders headers = new HttpHeaders();
			headers.set("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
			HttpEntity<String> entity = new HttpEntity<String>(formdata,headers);

			if(env.getProperty(CommonConstants.BSE_CALL_TEST_ENABLED).equalsIgnoreCase("N")){
				response= restTemplate.postForObject(url, entity, Paymentgatewayresponse.class);
				logger.info("Response from SIP pause- "+ mapper.writeValueAsString(response));
			}else{
				logger.info("purchasepaymentgateway(): Test phase enabled. Sending back dummy response");
				String dummydata = "{\r\n" + 
						"  \"responsestring\": \"\\r\\n\\r\\n\\r\\n\\r\\n<html>\\r\\n<head><title>Redirecting to Bank</title>\\r\\n<style>\\r\\n\\r\\n.bodytxt4 {\\r\\n\\r\\n\\tfont-family: Verdana, Arial, Helvetica, sans-serif;\\r\\n\\tfont-size: 12px;\\r\\n\\tfont-weight: bold;\\r\\n\\tcolor: #666666;\\r\\n}\\r\\n.bodytxt {\\r\\n\\tfont-family: Verdana, Arial, Helvetica, sans-serif;\\r\\n\\tfont-size: 13px;\\r\\n\\tfont-weight: normal;\\r\\n\\tcolor: #000000;\\r\\n\\r\\n}\\r\\n.bullet1 {\\r\\n\\r\\n\\tlist-style-type:\\tsquare;\\r\\n\\tlist-style-position: inside;\\r\\n\\tlist-style-image: none;\\r\\n\\tfont-family: Verdana, Arial, Helvetica, sans-serif;\\r\\n\\tfont-size: 10px;\\r\\n\\tfont-weight: bold;\\r\\n\\tcolor: #FF9900;\\r\\n}\\r\\n.bodytxt2 {\\r\\n\\tfont-family: Verdana, Arial, Helvetica, sans-serif;\\r\\n\\tfont-size: 8pt;\\r\\n\\tfont-weight: normal;\\r\\n\\tcolor: #333333;\\r\\n\\r\\n}\\r\\nA.sac2 {\\r\\n\\tCOLOR: #000000;\\r\\n\\tfont-family: Verdana, Arial, Helvetica, sans-serif;\\r\\n\\tfont-size: 10px;\\r\\n\\tfont-weight: bold;\\r\\n\\ttext-decoration: none;\\r\\n}\\r\\nA.sac2:visited {\\r\\n\\tCOLOR: #314D5A; TEXT-DECORATION: none\\r\\n}\\r\\nA.sac2:hover {\\r\\n\\tCOLOR: #FF9900; TEXT-DECORATION: underline\\r\\n}\\r\\n</style>\\r\\n\\r\\n</head>\\r\\n<script language=JavaScript>\\r\\n\\r\\n\\r\\nvar message=\\\"Function Disabled!\\\";\\r\\n\\r\\n\\r\\nfunction clickIE4(){\\r\\nif (event.button==2){\\r\\nreturn false;\\r\\n}\\r\\n}\\r\\n\\r\\nfunction clickNS4(e){\\r\\nif (document.layers||document.getElementById&&!document.all){\\r\\nif (e.which==2||e.which==3){\\r\\nreturn false;\\r\\n}\\r\\n}\\r\\n}\\r\\n\\r\\nif (document.layers){\\r\\ndocument.captureEvents(Event.MOUSEDOWN);\\r\\ndocument.onmousedown=clickNS4;\\r\\n}\\r\\nelse if (document.all&&!document.getElementById){\\r\\ndocument.onmousedown=clickIE4;\\r\\n}\\r\\n\\r\\ndocument.oncontextmenu=new Function(\\\"return false\\\")\\r\\n\\r\\n</script>\\r\\n<table width=\\\"100%\\\" border=\\\"0\\\" cellspacing=\\\"0\\\" cellpadding=\\\"0\\\">\\r\\n  <tr>\\r\\n    <td align=\\\"left\\\" valign=\\\"top\\\">\\r\\n<table width=\\\"100%\\\" border=\\\"0\\\" cellspacing=\\\"0\\\" cellpadding=\\\"0\\\">\\r\\n        <tr> \\r\\n          <td align=\\\"center\\\" valign=\\\"middle\\\"><table width=\\\"100%\\\" border=\\\"0\\\" cellspacing=\\\"0\\\" cellpadding=\\\"0\\\">\\r\\n             \\r\\n              <tr>\\r\\n                <td  align=\\\"center\\\"></td>\\r\\n              </tr>\\r\\n              <tr>\\r\\n                <td height=\\\"85\\\" align=\\\"center\\\"><br>\\r\\n                  <table width=\\\"80%\\\" border=\\\"0\\\" cellpadding=\\\"0\\\" cellspacing=\\\"1\\\" bgcolor=\\\"#CCCCCC\\\">\\r\\n                    <tr>\\r\\n                      <td bgcolor=\\\"#CCCCCC\\\"><table width=\\\"100%\\\" border=\\\"0\\\" cellpadding=\\\"6\\\" cellspacing=\\\"0\\\" bgcolor=\\\"#FFFFFF\\\">\\r\\n                          <tr> \\r\\n                            <td colspan=\\\"2\\\" align=\\\"left\\\" valign=\\\"bottom\\\"><span class=\\\"bodytxt4\\\">Your payment request is being processed...</span></td>\\r\\n                          </tr>\\r\\n                          <tr valign=\\\"top\\\"> \\r\\n                            <td colspan=\\\"2\\\" align=\\\"left\\\"><table width=\\\"100%\\\" border=\\\"0\\\" cellspacing=\\\"0\\\" cellpadding=\\\"0\\\">\\r\\n                                <tr> \\r\\n                                  <td width=\\\"87%\\\" bgcolor=\\\"#cccccc\\\" height=\\\"1\\\" align=\\\"center\\\"></td>\\r\\n                                </tr>\\r\\n                              </table></td>\\r\\n                          </tr>\\r\\n                          <tr> \\r\\n                            <td width=\\\"60%\\\" align=\\\"left\\\" valign=\\\"bottom\\\"><table width=\\\"95%\\\" border=\\\"0\\\" cellpadding=\\\"1\\\" cellspacing=\\\"0\\\" bgcolor=\\\"#FFFFFF\\\">\\r\\n                                <tr> \\r\\n                                  <td align=\\\"right\\\" valign=\\\"top\\\"></td>\\r\\n                                  <td class=\\\"bodytxt\\\">&nbsp;</td>\\r\\n                                </tr>\\r\\n                                <tr> \\r\\n                                  <td height=\\\"19\\\"  align=\\\"right\\\" valign=\\\"top\\\"><li class=\\\"bullet1\\\"></li></td>\\r\\n                                  <td class=\\\"bodytxt2\\\">This is a secure payment \\r\\n                                    gateway using 128 bit SSL encryption.</td>\\r\\n                                </tr>\\r\\n                                <tr> \\r\\n                                  <td align=\\\"right\\\" valign=\\\"top\\\"> <li class=\\\"bullet1\\\"></li></td>\\r\\n                                  <td class=\\\"bodytxt2\\\" >When you submit the transaction, \\r\\n                                    the server will take about 1 to 5 seconds \\r\\n                                    to process, but it may take longer at certain \\r\\n                                    times. </td>\\r\\n                                </tr>\\r\\n                                <tr> \\r\\n                                  <td align=\\\"right\\\" valign=\\\"top\\\"><li class=\\\"bullet1\\\"></li></td>\\r\\n                                  <td class=\\\"bodytxt2\\\" >Please do not press \\\"Submit\\\" \\r\\n                                    button once again or the \\\"Back\\\" or \\\"Refresh\\\" \\r\\n                                    buttons. </td>\\r\\n                                </tr>\\r\\n                              </table></td>\\r\\n                            <td align=\\\"right\\\" valign=\\\"bottom\\\"><table width=\\\"80%\\\" border=\\\"0\\\" cellpadding=\\\"1\\\" cellspacing=\\\"0\\\" bgcolor=\\\"#FFFFFF\\\">\\r\\n                                <tr bgcolor=\\\"#FFFCF8\\\"> \\r\\n                                  <td align=\\\"right\\\" bgcolor=\\\"#FFFFFF\\\"></td>\\r\\n                                </tr>\\r\\n                                <tr bgcolor=\\\"#FFFCF8\\\"> \\r\\n                                  <td align=\\\"right\\\" valign=\\\"middle\\\" bgcolor=\\\"#FFFFFF\\\" class=\\\"bodytxt2\\\">&nbsp;</td>\\r\\n                                </tr>\\r\\n                                <tr bgcolor=\\\"#FFFCF8\\\"> \\r\\n                                  <td align=\\\"right\\\" bgcolor=\\\"#FFFFFF\\\" class=\\\"bodytxt2\\\" >&nbsp;</td>\\r\\n                                </tr>\\r\\n                              </table></td>\\r\\n                          </tr>\\r\\n                        </table></td>\\r\\n                    </tr>\\r\\n                  </table>\\r\\n                  \\r\\n                </td>\\r\\n              </tr>\\r\\n            </table>\\r\\n           \\r\\n          \\r\\n         \\r\\n             </td>\\r\\n        </tr>  \\r\\n\\r\\n\\r\\n      </table></td>\\r\\n  </tr>\\r\\n  \\r\\n</table>\\r\\n\\r\\n\\r\\n\\r\\n<body>\\r\\n<form name=\\\"Bankfrm\\\" method=\\\"post\\\" action='https://shopping.icicibank.com/corp/BANKAWAY?IWQRYTASKOBJNAME=bay_mc_login&BAY_BANKID=ICI'>\\r\\n \\r\\n\\t\\t\\t  \\r\\n              <input type = \\\"hidden\\\" name = \\\"MD\\\" value=\\\"P\\\">\\r\\n\\t\\t\\t\\r\\n              \\r\\n\\t\\t\\t  \\r\\n              <input type = \\\"hidden\\\" name = \\\"PID\\\" value=\\\"000000001086\\\">\\r\\n\\t\\t\\t\\r\\n              \\r\\n\\t\\t\\t  \\r\\n              <input type = \\\"hidden\\\" name = \\\"ES\\\" value=\\\"hbVjLCMyDHSYxiBaT7dJgaVXbhCCcxOAk4mNJPkwEQlcdklihe4UQTNrhsjzGEl/ts8Sl9RCMvWWeSMU1MZ7vRMHGEv94hBmuaoqeg0CLXZGgqqZp0aRKazsBdLAYpqTZ94askMgUzU34Bcgb4dogol5jxM0AolY2RtMcDhHrEDjpD3ygzEOJaaT97DmUXVR7p9iQcr1q5TRPpyroTq1Urboe2XFC+91ndxTYa3AjkiPpI+6/JiAh/Wt2TMkWfwm\\\">\\r\\n\\t\\t\\t\\r\\n              \\r\\n\\t\\t\\t  \\r\\n              <input type = \\\"hidden\\\" name = \\\"SPID\\\" value=\\\"NA\\\">\\r\\n\\t\\t\\t\\r\\n              \\r\\n\\t</form>\\r\\n</body>\\r\\n<script>\\r\\ndocument.Bankfrm.submit();\\r\\n</script>\\r\\n</html>\\r\\n\",\r\n" + 
						"  \"statuscode\": \"100\",\r\n" + 
						"  \"internalrefno\": \"8226212776538751473\",\r\n" + 
						"  \"filler1\": \"\",\r\n" + 
						"  \"filler2\": \"\",\r\n" + 
						"  \"filler3\": \"\",\r\n" + 
						"  \"filler4\": \"\"\r\n" + 
						"}";
				response = mapper.readValue(dummydata, Paymentgatewayresponse.class);
			}
		} catch (Exception e) {
			logger.error("purchasepaymentgateway(): Failed to write process data", e);
			response.setStatuscode("101");
			response.setResponse("Internal error. Please try after sometime");
		}
		return response;
	}
	
	
	@Override
	public Nomineeregistrationresponse nomineeregister(Nomineeregistrationrequest nomineerequest) {
		logger.info("nomineeregister(): Process to send request...");
		final String url = env.getProperty(CommonConstants.URL_SERVICE_MF_BSE_V1) + "/nominee-register";
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();
		String formdata = null;
		logger.info("Connect URL - "+ url);
		Nomineeregistrationresponse response = new Nomineeregistrationresponse();
		try {
			formdata = mapper.writeValueAsString(nomineerequest);
			
			logger.info("Requesting for nominee register data- "+ formdata);
			HttpHeaders headers = new HttpHeaders();
			headers.set("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
			HttpEntity<String> entity = new HttpEntity<String>(formdata,headers);

			if(env.getProperty(CommonConstants.BSE_CALL_TEST_ENABLED).equalsIgnoreCase("N")){
				response= restTemplate.postForObject(url, entity, Nomineeregistrationresponse.class);
				logger.info("Response from nominee register- "+ mapper.writeValueAsString(response));
			}else{
				logger.info("nomineeregister(): Test phase enabled. Sending back dummy response");
				
				String dummydata = "{\r\n" + 
						" \"ErrorMessage\": [],\r\n" + 
						" \"StatusCode\": \"100\",\r\n" + 
						" \"Type\": \"NOMINEE\",\r\n" + 
						" \"Remarks\": \"NOMINATION DETAILS REGISTERED SUCCESSFULLY.\",\r\n" + 
						" \"Filler1\": \"\",\r\n" + 
						" \"Filler2\": \"\",\r\n" + 
						" \"Filler3\": \"\"\r\n" + 
						"}";
				
				/*
				String dummydata = "{\r\n" + 
						" \"ErrorMessage\": [\"Client do not exist\"],\r\n" + 
						" \"StatusCode\": \"101\",\r\n" + 
						" \"Type\": \"NOMINEE\",\r\n" + 
						" \"Remarks\": \"\",\r\n" + 
						" \"Filler1\": \"\",\r\n" + 
						" \"Filler2\": \"\",\r\n" + 
						" \"Filler3\": \"\"\r\n" + 
						"}";
				*/
				response = mapper.readValue(dummydata, Nomineeregistrationresponse.class);
				
				logger.info("Response (Dummy) - "+ mapper.writeValueAsString(response));
			}
		} catch (Exception e) {
			logger.error("nomineeregister(): Failed to process data", e);
			response.setStatuscode("101");
			response.setRemarks("Internal error. Please try after sometime");
		}
		return response;
	}
	
	
	@Override
	public Nominee2faresponse nomineeauthenticate(Nominee2farequest nomineerequest) {
		logger.info("nomineeauthenticate(): Process to send request...");
		final String url = env.getProperty(CommonConstants.URL_SERVICE_MF_BSE_V1) + "/nominee-authenticate";
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();
		String formdata = null;
		Nominee2faresponse response = new Nominee2faresponse();
		logger.info("Connect URL - "+ url);
		try {
			formdata = mapper.writeValueAsString(nomineerequest);
			
			logger.info("Requesting for nominee authenticate data- "+ formdata);
			HttpHeaders headers = new HttpHeaders();
			headers.set("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
			HttpEntity<String> entity = new HttpEntity<String>(formdata,headers);

			if(env.getProperty(CommonConstants.BSE_CALL_TEST_ENABLED).equalsIgnoreCase("N")){
				response= restTemplate.postForObject(url, entity, Nominee2faresponse.class);
				logger.info("Response from nominee register- "+ mapper.writeValueAsString(response));
			}else{
				logger.info("nomineeauthenticate(): Test phase enabled. Sending back dummy response");
				String dummydata = "{\r\n" + 
						" \"Filler1\": \"1\",\r\n" + 
						" \"Filler2\": \"\",\r\n" + 
						" \"Filler3\": \"\",\r\n" + 
						" \"Type\": \"NOMINEE\",\r\n" + 
						" \"LoopbackReturnUrl\": \"https://www.bseindia.com\",\r\n" + 
						" \"ErrorDescription\": \"HOLDER LINK GENRATED SUCCESSFULLY\",\r\n" + 
						" \"StatusCode\": \"100\",\r\n" + 
						" \"InternalRefrenceNo\": \" \",\r\n" + 
						" \"ReturnUrl\": \r\n" + 
						"\"https://bsestarmfdemo.bseindia.com/2FA_ClientMasterNominee.aspx?bTJ%2fS8L%2fHwHVQkd%2fwMnC5WaZIw7UIDlfEgVj%2b3mNYlsb%2bFBANqsnRUOJl%2fDUDp1QEQ2wE2KKd8%2f2Y6TrLKTa4pS6A2ZEycbwTVuIUG9sbPJ1JDOVvnkaoL32utq9ipgV7fLBGXq8aaZCf5bcp%2f2cvXw%2fuZhq\"\r\n" + 
						"}";
				response = mapper.readValue(dummydata, Nominee2faresponse.class);
			}
		} catch (Exception e) {
			logger.error("nomineeauthenticate(): Failed to write process data", e);
			response.setStatuscode("101");
			response.setErrordescription("Internal error. Please try after sometime");
		}
		return response;
	}
	
	

}
