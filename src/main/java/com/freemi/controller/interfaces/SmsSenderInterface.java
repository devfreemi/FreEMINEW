package com.freemi.controller.interfaces;

import org.springframework.stereotype.Service;

@Service
public interface SmsSenderInterface {
	
	public void sendOtp(String mobile, String otp, String validyTime,String other);

}