package com.freemi.common.util;


import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

@Service
public class OtpService {

	private static final Integer EXPIRE_MINS = 5;
	private static final Integer EXPIRE_MIN_OTP_REQUEST_COUNT = 120;
	private LoadingCache<String, String> otpCache;
	private LoadingCache<String, Integer> otpmaxresendcount;
	
	private static final Logger logger = LogManager.getLogger(OtpService.class);
	
	public OtpService(){
		super();
		otpCache = CacheBuilder.newBuilder().
				expireAfterWrite(EXPIRE_MINS, TimeUnit.MINUTES).build(new CacheLoader<String, String>() {
					public String load(String key) {
						return "";
					}
				});
		
		otpmaxresendcount = CacheBuilder.newBuilder().
				expireAfterWrite(EXPIRE_MIN_OTP_REQUEST_COUNT, TimeUnit.MINUTES).build(new CacheLoader<String, Integer>() {
					public Integer load(String key) {
						return 0;
					}
				});
	}

	public String generateOtp(String uid, int len, String testenabled, String smsvalidity, int maxsmsrequestcount) {
		
		if(len <= 0) {
			len = 6;
		}
		String chars = "123456789";
		String otp = "";
		int length = chars.length();

		if(testenabled.equalsIgnoreCase("Y")) {
//			logger.info("DUMMY OTP generated..");
			otp="987654";
		}else {
			for (int i = 0; i < len; i++) {
				otp += chars.split("")[(int) (Math.random() * (length - 1))];
			}
		}
		otpCache.put(uid, otp);
		
		return otp;
	}



	//This method is used to return the OTP number against Key->Key values is username
	public String getOtp(String key){ 
		try {
			return otpCache.get(key);
		} catch (Exception e){
			logger.error("Failed to fetch otp key- "+key);
			return ""; 
		}
	}
	
	public int getotprequestcount(String key){ 
		Integer count = 0;
		try {	
			count= otpmaxresendcount.getIfPresent(key);
			if(count!=null)
				return count;
			else {
				return 0;
			}
		} catch (Exception e){
			logger.error("Failed to fetch otp request count for key- "+key);
			return 0; 
		}
	}
	
	public void setotprequestcount(String key, int count){ 
		try {	
			otpmaxresendcount.put(key, count);
		} catch (Exception e){
			logger.error("Failed to set maximum count data- "+key);
		}
	}
	
	
	//This method is used to clear the OTP cached already
	public void clearOTP(String key){ 
		otpCache.invalidate(key);
	}
	
	//This method is used to clear the OTP cached already
		public void clearOTPrequestcount(String key){ 
			otpmaxresendcount.invalidate(key);
		}
	
}