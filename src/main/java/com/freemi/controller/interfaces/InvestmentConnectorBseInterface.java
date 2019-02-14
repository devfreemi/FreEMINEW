package com.freemi.controller.interfaces;

import org.springframework.stereotype.Service;

import com.freemi.entity.bse.BseOrderPaymentRequest;
import com.freemi.entity.bse.BseOrderPaymentResponse;
import com.freemi.entity.investment.BseMFInvestForm;
import com.freemi.entity.investment.BseOrderEntryResponse;
import com.freemi.entity.investment.SelectMFFund;

@Service
public interface InvestmentConnectorBseInterface {
	
	public String saveCustomerRegistration(BseMFInvestForm registrationForm,String field1);
	
	public String uploadAOFForm(String mobileNumber, String aoffolderLocation, String clientCode);
	
	public BseOrderEntryResponse processCustomerPurchaseRequest(SelectMFFund selectedFund, String transactionNumber);
	
	public BseOrderPaymentResponse getPaymentUrl(BseOrderPaymentRequest request);
	
	public String BseOrderPaymentStatus(String clientId, String orderNo);

}
