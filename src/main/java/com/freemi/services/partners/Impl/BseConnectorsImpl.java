package com.freemi.services.partners.Impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.freemi.controller.interfaces.InvestmentConnectorBseInterface;
import com.freemi.entity.bse.ClientMFD;
import com.freemi.entity.investment.BseMFInvestForm;
import com.freemi.ui.restclient.RestClientBse;

@Service
public class BseConnectorsImpl implements InvestmentConnectorBseInterface {

	private static final Logger logger = LogManager.getLogger(BseConnectorsImpl.class);
	
	@Override
	public String saveCustomerRegistration(BseMFInvestForm registrationForm, String field1) {
		String result = "";
		logger.info("Registration details processing to BSE platform begins");
		try{
			logger.info("Convert form data to BSE format data");
			ClientMFD bseregistrationForm=  BseBeansMapper.InvestmentFormToBseBeans(registrationForm);
			logger.info("Begin BSE service invoke process");
			 result= RestClientBse.registerUser(bseregistrationForm);
		}catch(Exception e){
			logger.error("Failed during proceesing of BSE customer details to BSE platform",e);
			result="BSE_CONN_FAIL";
		}
		
		return result;
	}

}
