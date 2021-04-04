package com.freemi.services.Impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freemi.database.interfaces.OtpvalidationCrudRepository;
import com.freemi.services.interfaces.EKYCValidation;

@Service
public class EKYCValidationImpl implements EKYCValidation {

	@Autowired
	OtpvalidationCrudRepository otpvalidationCrudRepository;
	
	private static final Logger logger = LogManager.getLogger(EKYCValidationImpl.class);
	
	@Override
	public boolean mobilenoverifiedduringregistration(String sesisonid, String mobileno, String submodule) {
		
		boolean flag = false;
		logger.info("Checking if mobile no verifed- "+ mobileno);
		try {
			if(otpvalidationCrudRepository.existsBySessionidAndMobileAndOtpverified(sesisonid,mobileno,"Y")) {
				logger.info("Mobile no is verified...");
				flag=true;
			}
		}catch(Exception e) {
			logger.error("Failed to verify mobile no verification status",e);
		}
		
		return flag;
	}

}
