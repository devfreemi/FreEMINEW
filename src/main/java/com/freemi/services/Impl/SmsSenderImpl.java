package com.freemi.services.Impl;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

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
import com.freemi.database.interfaces.OtpvalidationCrudRepository;
import com.freemi.entity.database.Mobileotpverifier;
import com.freemi.services.interfaces.SmsSenderInterface;
import com.freemi.ui.restclient.RestclientSmsSenderImpl;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

@Component
public class SmsSenderImpl implements SmsSenderInterface {

	@Autowired
	Environment env;

	@Autowired
	OtpvalidationCrudRepository otpvalidationCrudRepository;
	
	@Autowired
	RestclientSmsSenderImpl restclientSmsSenderImpl;

	private static Integer EXPIRE_MINS = 5;
	private LoadingCache<String, String> otpCache;

	/*
	 * public SmsSenderImpl(){ // super();
	 * 
	 * }
	 */
	
	@PostConstruct
    public void postConstruct() {
		logger.info("Gnerate cache"); 
		try {
			EXPIRE_MINS = Integer.valueOf(env.getProperty("sms.otp.validity"));
		}catch(Exception e) {
			logger.error("Failed to convert value");
		}
		logger.info("EXPIRE-minute- "+ EXPIRE_MINS);
		otpCache = CacheBuilder.newBuilder(). expireAfterWrite(EXPIRE_MINS, TimeUnit.MINUTES).build(new CacheLoader<String, String>() { 
			public String
			load(String key) { return ""; } }); 
    }


	private static final Logger logger = LogManager.getLogger(SmsSenderImpl.class);

	@Override
	@Async
	public void sendOtp(String mobile, String otp, String validyTime, String other) {
		logger.info("Send OTP to mobile number for login- "+ mobile);
		/*
		if(env.getProperty(com.freemi.common.util.CommonConstants.SMS_SEND_ENABLED).equalsIgnoreCase("Y")){
			if(mobile!=null){
				String message= otp + " is your OTP to login to your FreEMI Account. The otp is valid for "+ validyTime + " minute(s). Do not share OTP for security reasons.";
				prepareSms(mobile,message,env.getProperty(CommonConstants.SMS_ROUTE_TRANSACTIONAL));
			}else{
				logger.info("No mobile number found to send registration sms.");
			}
		}else{
			logger.info("SMS sending disabled. Skipping process...");
		}
		*/
		Map<String, String> msgdata = new HashMap<String,String>();
		msgdata.put("otpval", otp);
		msgdata.put("var", String.valueOf(EXPIRE_MINS));
		
		restclientSmsSenderImpl.sendsmsviaflow(mobile, "606aef141ed7d80dd2159b88", null, msgdata, null, null, null);
		
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


	@Override
	public String sendmobnoverifyotp(String mobile, int validyTime, String module, String submodule, String requestpath,String sessionid) {
		logger.info("Request received to generate OTP for mobile- "+ mobile + " :module- "+ module);
		String response="SUCCESS";
		String otpkey="";
		int otplength=0;
		String otp="";
		int maxtry=1;
		if(submodule!=null) {
			try {
				otpkey= mobile+"-"+sessionid+"-"+submodule.toUpperCase();
				do {
					otp = Long.toString(Math.round(Math.random()*1000000));
					otplength = otp.length();
					logger.info("OTP for- "+module + " :OTP: "+ otp );
				}while(otplength!=6 && maxtry++<=5);
				if(otplength==6) {
					Mobileotpverifier mobileotp = new Mobileotpverifier();
					mobileotp.setMobile(mobile);
					mobileotp.setModule(module);
					mobileotp.setSubmodule(submodule);
					mobileotp.setSessionid(sessionid);
					mobileotp.setOtp(otp);
					mobileotp.setServertimestamp(new Date());
					otpvalidationCrudRepository.saveAndFlush(mobileotp);

					otpCache.put(otpkey, otp);
					logger.info("OTP generated and placed in DB and cache for- " + otpkey);
					
					Map<String, String> msgdata = new HashMap<String,String>();
					msgdata.put("otp", otp);
					msgdata.put("var", String.valueOf(EXPIRE_MINS));
					
					restclientSmsSenderImpl.sendsmsviaflow(mobile, "606aefefd9d15c5e4515a4e0", null, msgdata, null, null, null);
					
				}else {	
					logger.error("Incorrect length OTP not generated.. Need to retry");
					response="RETRY";
				}
			}catch(Exception e){
				logger.error("Error processing OTP generation",e);
				response="INTERNAL_ERROR";
			}
		}
		else {
			response="INVALID_MODULE";
		}
		return response;
	}


	@Override
	public String verifyotp(String mobile, int validyTime, String otp,String module, String submodule, String requestpath,String sessionid,String other) {
		logger.info("Request received to verify OTP for mobile- "+ mobile + " - module - "+ module);
		String cacheotp;
		String result="INVALID";
		String otpkey="";
		if(submodule!=null) {
			otpkey= mobile+"-"+sessionid+"-"+submodule.toUpperCase();
			logger.info("OTPKEY- "+ otpkey);
			try {
				cacheotp= otpCache.get(otpkey);
				logger.info("OTP from cache- " + cacheotp);
				if(cacheotp!=null) {
					if(otp.equals(cacheotp)) {
						result="SUCCESS";
						otpCache.invalidate(mobile+"-"+module.toUpperCase());
						try {
							List<Mobileotpverifier> mobileotpdbrecord = otpvalidationCrudRepository.findAllByMobileAndSubmoduleAndSessionidAndOtp(mobile, submodule, sessionid,otp);
							if(mobileotpdbrecord!=null) {
								Mobileotpverifier correctdetails=mobileotpdbrecord.get(0);
								correctdetails.setOtpverified("Y");
								otpvalidationCrudRepository.saveAndFlush(correctdetails);
							}
						}catch(Exception e) {

						}

					}
				}else {
					result="OTP_EXPIRED";
				}
			} catch (Exception e){
				logger.error("Error while trying to validate OTP",e);
				result= "INTERNAL_ERROR"; 
			}
		}else {
			result="INVALID_MODULE";
		}
		logger.info("Response for OTP validation for modile- "+mobile + " : "+ result );
		return result;
	}


}
