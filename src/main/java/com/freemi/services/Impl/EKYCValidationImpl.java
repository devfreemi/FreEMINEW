package com.freemi.services.Impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freemi.connector.restclient.Communicationrestclient;
import com.freemi.database.interfaces.OtpvalidationCrudRepository;
import com.freemi.entity.general.OTPInfo;
import com.freemi.entity.general.Otprequeststatus;
import com.freemi.services.interfaces.EKYCValidation;

@Service
public class EKYCValidationImpl implements EKYCValidation {

	@Autowired
	OtpvalidationCrudRepository otpvalidationCrudRepository;
	
	@Autowired
	Communicationrestclient communicationclient;
	
	private static final Logger logger = LogManager.getLogger(EKYCValidationImpl.class);
	
	@Override
	public boolean mobilenoverifiedduringregistration(String sesisonid, String mobileno, String submodule) {
		
		boolean flag = false;
		logger.info("Checking if mobile no verifed- "+ mobileno);
		try {
			/*
			 * if(otpvalidationCrudRepository.existsBySessionidAndMobileAndOtpverified(
			 * sesisonid,mobileno,"Y")) { logger.info("Mobile no is verified...");
			 * flag=true; }
			 */
			OTPInfo otpinfo = new OTPInfo();
			otpinfo.setMobileNumber(mobileno);
			otpinfo.setSubModule(mobileno);
			otpinfo.setSessionId(sesisonid);
			
			Otprequeststatus status= communicationclient.checkOTPverifystatus(otpinfo);
			logger.info("OTP verification status- "+ status.getStatuscode());
			if(status.getStatuscode().equalsIgnoreCase("0")) {
				flag=true;
			}
			
		}catch(Exception e) {
			logger.error("Failed to verify mobile no verification status",e);
		}
		
		return flag;
	}

}
