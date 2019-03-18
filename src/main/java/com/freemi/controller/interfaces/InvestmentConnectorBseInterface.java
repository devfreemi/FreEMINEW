package com.freemi.controller.interfaces;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.freemi.entity.bse.BseAOFUploadResponse;
import com.freemi.entity.bse.BseApiResponse;
import com.freemi.entity.bse.BseOrderPaymentRequest;
import com.freemi.entity.bse.BseOrderPaymentResponse;
import com.freemi.entity.database.UserBankDetails;
import com.freemi.entity.investment.BseMFInvestForm;
import com.freemi.entity.investment.BseOrderEntryResponse;
import com.freemi.entity.investment.SelectMFFund;

@Service
public interface InvestmentConnectorBseInterface {
	
	public String generateOTPForLogin(String userid);
	
	public String verifyOTPForLogin(String userid);
	
	public String saveCustomerRegistration(BseMFInvestForm registrationForm,String field1);
	
	public BseAOFUploadResponse uploadAOFForm(String mobileNumber, String aoffolderLocation, String clientCode);
	
	public BseOrderEntryResponse processCustomerPurchaseRequest(SelectMFFund selectedFund, String transactionNumber);
	
	public BseOrderPaymentResponse getPaymentUrl(BseOrderPaymentRequest request);
	
	public String BseOrderPaymentStatus(String clientId, String orderNo);
	
	public BseApiResponse emandateRegistration(UserBankDetails bankDetails,String amount, String clientCode, Date startDate, Date endDate);

}
