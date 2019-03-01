package com.freemi.database.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.freemi.entity.bse.BseApiResponse;
import com.freemi.entity.database.MfTopFundsInventory;
import com.freemi.entity.database.UserBankDetails;
import com.freemi.entity.general.UserProfile;
import com.freemi.entity.investment.BseAllTransactionsView;
import com.freemi.entity.investment.BseMFInvestForm;
import com.freemi.entity.investment.BseMFTop15lsSip;
import com.freemi.entity.investment.BsemfTransactionHistory;
import com.freemi.entity.investment.SelectMFFund;
import com.freemi.entity.investment.TransactionStatus;

@Service
public interface BseEntryManager {
	
	//Customer records related crud operation
	public String saveCustomerDetails(BseMFInvestForm customerForm);
	public List<SelectMFFund> getMFOrderHistory(String customerId);
	public UserProfile getCustomerDetailsByMobile(String mobile);
	public List<BseMFInvestForm> getCustomerDetails(String customerId);
	public List<BseMFInvestForm> getCustomerByPan(String pan);
	public boolean isExisitngCustomer(String pan, String mobile);
	public String getCustomerPanfromMobile(String mobile);
	public String getClientIdfromMobile(String mobile);
	public boolean updateCustomerData(BseMFInvestForm custerProfileData);
	public boolean updateCustomerBankDetails(UserProfile investorData);
	public boolean updateCustomerAddress(UserProfile investorData);
	public String investmentProfileStatus(String mobileNumber);
	
	public BseMFInvestForm getCustomerInvetFormData(String mobile);
	
	public UserBankDetails getCustomerBankDetails(String clientCode);
	public BseApiResponse updateEmdandateStatus(String clientCode, String accNumber);
	
	public String upddateCustomerFormSignature(String mobile, String pan, String signatureData);
	public String uploadAOFFormStatus(String mobileNumber, String status);
	
	// Customer MF transactions
	public boolean checkIfTransIdExist(String generatedTransId);
	public TransactionStatus savetransactionDetails(SelectMFFund selectedMFFund);
	public List<BsemfTransactionHistory> getAllPurchaseHistory(String clientId);
	
	
	
	public List<BseAllTransactionsView> getCustomerAllTransactionRecords(String clientID, String mobileNumber, String panNumber);
	public BseAllTransactionsView getFundDetailsForAdditionalPurchase(String portfolio, String schemeCode,String investType, String mobileNumber);
	public BseAllTransactionsView getFundDetailsForRedemption(String portfolio, String schemeCode,String investType, String mobileNumber);
	
	//BSE MF related operations
	public List<MfTopFundsInventory> getTopMfFunds();
	public List<BseMFTop15lsSip> getTopFunds();
	
	public long getCurrentDayNextTransCount(Date date);
	
	//PORTFOLIO Crud respository
	public List<String> getSelectedAmcPortfolio(String amcCode, String clientId);
}
