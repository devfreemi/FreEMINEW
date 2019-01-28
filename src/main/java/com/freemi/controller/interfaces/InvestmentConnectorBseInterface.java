package com.freemi.controller.interfaces;

import org.springframework.stereotype.Service;

import com.freemi.entity.investment.BseMFInvestForm;

@Service
public interface InvestmentConnectorBseInterface {
	
	public String saveCustomerRegistration(BseMFInvestForm registrationForm,String field1);

}
