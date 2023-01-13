package com.freemi.connector.restclient;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.freemi.common.util.CommonConstants;
import com.freemi.entity.general.OTPInfo;
import com.freemi.entity.general.OTPInfo2;
import com.freemi.entity.general.Otprequeststatus;

@Service
public class Communicationrestclient {

	private static final Logger logger = LogManager.getLogger(Communicationrestclient.class);

	@Autowired
	private Environment env;
	

	public Otprequeststatus sendOTP(OTPInfo otpinfo) {
		Otprequeststatus res = new Otprequeststatus();

		try {
			final String uri = env.getProperty(CommonConstants.URL_SERVICE_COMMUNICATION)+"/sendotp";
			logger.info("OTP request- "+ uri);
			HttpHeaders headers = new HttpHeaders();
			RestTemplate rt = new RestTemplate();
			HttpEntity<Object> entity = new HttpEntity<Object>(otpinfo,headers);
			logger.info("Calling URL - "+ uri);
			ObjectMapper mapper = new ObjectMapper();
			logger.info("SMS request- "+ mapper.writeValueAsString(otpinfo));
			logger.info("msgdata- "+ otpinfo.getMsgdata());
			res = rt.postForObject(uri, entity, Otprequeststatus.class );
			logger.info("Response- "+ mapper.writeValueAsString(res));
		} catch (Exception ex) {
			logger.error("Error calling /sendotp service- ",ex);
			res.setStatuscode("1");
			res.setErrormsg("Internal service failure. Please try after somtime.");
		}

		return res;
	}

	public Otprequeststatus checkOTP(OTPInfo otpinfo) {

		Otprequeststatus otpverifystatus = new Otprequeststatus();
		try {
			final String uri = env.getProperty(CommonConstants.URL_SERVICE_COMMUNICATION)+"/verify-otpsubmit";

			HttpHeaders headers = new HttpHeaders();	

			RestTemplate rt = new RestTemplate();
			
			HttpEntity<Object> entity = new HttpEntity<Object>(otpinfo,headers);
			logger.info("Calling URL - "+ uri);
//			ObjectMapper mapper = new ObjectMapper();
//			logger.info("SMS request- "+ mapper.writeValueAsString(otpinfo));
			otpverifystatus = rt.postForObject(uri, entity, Otprequeststatus.class );
		}catch (Exception ex) {
			logger.error("Failed to communicate with service",ex);
			otpverifystatus.setStatuscode("1");
			otpverifystatus.setErrormsg("Internal service failure. Please try after somtime.");
		}
		return otpverifystatus;
		
	}
	
	
	
	public Otprequeststatus sendemailOTP(OTPInfo2 otpinfo) {
		Otprequeststatus res = new Otprequeststatus();

		try {
			final String uri = env.getProperty(CommonConstants.URL_SERVICE_COMMUNICATION)+"/sendemailotp";
			logger.info("OTP request- "+ uri);
			HttpHeaders headers = new HttpHeaders();
			RestTemplate rt = new RestTemplate();
			HttpEntity<Object> entity = new HttpEntity<Object>(otpinfo,headers);
			logger.info("Calling URL - "+ uri);
//			ObjectMapper mapper = new ObjectMapper();
//			logger.info("SMS request- "+ mapper.writeValueAsString(otpinfo));
			res = rt.postForObject(uri, entity, Otprequeststatus.class );
		} catch (Exception ex) {
			logger.error("Error calling /sendotp service- ",ex);
			res.setStatuscode("1");
			res.setErrormsg("Internal service failure. Please try after somtime.");
		}

		return res;
	}

	public Otprequeststatus checkemailOTP(OTPInfo2 otpinfo) {

		Otprequeststatus otpverifystatus = new Otprequeststatus();
		try {
			final String uri = env.getProperty(CommonConstants.URL_SERVICE_COMMUNICATION)+"/verify-email-otpsubmit";

			HttpHeaders headers = new HttpHeaders();	

			RestTemplate rt = new RestTemplate();
			
			HttpEntity<Object> entity = new HttpEntity<Object>(otpinfo,headers);
			logger.info("Calling URL - "+ uri);
//			ObjectMapper mapper = new ObjectMapper();
//			logger.info("SMS request- "+ mapper.writeValueAsString(otpinfo));
			otpverifystatus = rt.postForObject(uri, entity, Otprequeststatus.class );
		}catch (Exception ex) {
			logger.error("Failed to communicate with service",ex);
			otpverifystatus.setStatuscode("1");
			otpverifystatus.setErrormsg("Internal service failure. Please try after somtime.");
		}
		return otpverifystatus;
		
	}
	
	/*
	public Otprequeststatus sendOTP(String mobileNum, String sessionId,String module, String submodule) {
		Otprequeststatus res;

		try {
			final String uri = env.getProperty(CommonConstants.HDFC_BACKEND_SERVICE_BASEURL_PROPERTIES)+"/sendotp";
			logger.info("OTP request- "+ uri);
			HttpHeaders headers = new HttpHeaders();	

			RestTemplate rt = new RestTemplate();
			rt.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
			rt.getMessageConverters().add(new StringHttpMessageConverter());
			Map<String, Object> h = new HashMap<>();
			h.put("mobileNumber", mobileNum);
			h.put("sessionId", sessionId);
			h.put("otprequestmodule", module);
			h.put("otprequestsubmodule", submodule);

			HttpEntity<Map<String,Object>> entity = new HttpEntity<Map<String,Object>>(h,headers);
			logger.info("Calling URL - "+ uri);
			res = rt.postForObject(uri, entity, Otprequeststatus.class );
		} catch (Exception ex) {
			logger.error("Error calling /sendotp service- ",ex);
			res = new Otprequeststatus();
			res.setStatuscode("99");
			res.setErrormsg("Failed to connect service. Please try after sometime");
		}

		return res;
	}

	public CCOTPResponse checkOTP(String mobileNum, String otp, String sessionId,String module, String submodule) {

		try {
			final String uri = env.getProperty(CommonConstants.HDFC_BACKEND_SERVICE_BASEURL_PROPERTIES)+"/checkotp";

			HttpHeaders headers = new HttpHeaders();	

			RestTemplate rt = new RestTemplate();

			Map<String, Object> h = new HashMap<>();
			h.put("mobileNumber", mobileNum);
			h.put("otp", otp);
			h.put("sessionId", sessionId);
			h.put("otprequestmodule", module);
			h.put("otprequestsubmodule", submodule);

			HttpEntity<Map<String,Object>> entity = 
					new HttpEntity<Map<String,Object>>(h,headers);
			logger.info("Calling URL - "+ uri);
			CCOTPResponse res = rt.postForObject(uri, entity, CCOTPResponse.class );
			return res;
		} catch (Exception ex) {
			return new CCOTPResponse(-1, "-1");
		}

	}
	*/
	
	
	private HttpHeaders setcommonheaderdetails(HttpHeaders headers) {

		headers.set("content-Type", MediaType.APPLICATION_JSON_VALUE);
		return headers;
	}
}
