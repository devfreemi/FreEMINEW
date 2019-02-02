package com.freemi.controller.interfaces;

import org.springframework.stereotype.Service;

import com.freemi.entity.investment.BseMFInvestForm;
import com.freemi.entity.investment.BseOrderEntryResponse;
import com.freemi.entity.investment.SelectMFFund;

@Service
public interface InvestmentConnectorBseInterface {
	
	public String saveCustomerRegistration(BseMFInvestForm registrationForm,String field1);
	
	public BseOrderEntryResponse processCustomerPurchaseRequest(SelectMFFund selectedFund, String transactionNumber);

}
