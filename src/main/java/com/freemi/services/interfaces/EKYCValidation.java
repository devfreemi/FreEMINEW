package com.freemi.services.interfaces;

import org.springframework.stereotype.Service;

public interface EKYCValidation {
	
	public boolean mobilenoverifiedduringregistration(String sesisonid, String mobileno, String submodule);
	
	

}
