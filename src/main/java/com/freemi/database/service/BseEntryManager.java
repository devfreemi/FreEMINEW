package com.freemi.database.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.freemi.entity.database.MfTopFundsInventory;
import com.freemi.entity.general.UserProfile;
import com.freemi.entity.investment.BseAllTransactionsView;
import com.freemi.entity.investment.BseMFInvestForm;
import com.freemi.entity.investment.SelectMFFund;

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
	public boolean updateCustomerData(BseMFInvestForm custerProfileData);
	public boolean updateCustomerBankDetails(UserProfile investorData);
	public boolean updateCustomerAddress(UserProfile investorData);
	
	// Customer MF transactions
	public boolean checkIfTransIdExist(String generatedTransId);
	public boolean savetransactionDetails(SelectMFFund selectedMFFund);
	public List<BseAllTransactionsView> getCustomerAllTransactionRecords(String clientID, String mobileNumber, String panNumber);
	
	
	//BSE MF related operations
	public List<MfTopFundsInventory> getTopMfFunds();
	
	//PORTFOLIO Crud respository
	public List<String> getSelectedAmcPortfolio(String amcCode, String clientId);
}
