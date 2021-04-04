package com.freemi.services.interfaces;

import org.springframework.stereotype.Service;

/**
 * @author DEBASISH SARKAR
 *
 */
@Service
public interface SmsSenderInterface {
	
	public void sendOtp(String mobile, String otp, String validyTime,String other);
	
	
	/**
	 * @apiNote Call to generate OTP for a module
	 * @param mobile
	 * @param validyTime
	 * @param module
	 * @param submodule
	 * @param requestpath
	 * @param sessionid
	 * @return String messsage SUCCESS/ERROR
	 * 
	 */
	public String sendmobnoverifyotp(String mobile, int validyTime,String module, String submodule, String requestpath, String sessionid);
	
	public String verifyotp(String mobile, int validyTime, String otp,String module, String submodule, String requestpath, String sessionid,String other);

}
