package com.freemi.database.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.freemi.entity.bse.BseApiResponse;
import com.freemi.entity.database.MfTopFundsInventory;
import com.freemi.entity.database.UserBankDetails;
import com.freemi.entity.general.UserProfile;
import com.freemi.entity.investment.BseAllTransactionsView;
import com.freemi.entity.investment.BseFundsScheme;
import com.freemi.entity.investment.BseMFInvestForm;
import com.freemi.entity.investment.BseMFSelectedFunds;
import com.freemi.entity.investment.BseMFTop15lsSip;
import com.freemi.entity.investment.BseMandateDetails;
import com.freemi.entity.investment.BsemfTransactionHistory;
import com.freemi.entity.investment.MFCamsFolio;
import com.freemi.entity.investment.MFCamsValueByCategroy;
import com.freemi.entity.investment.MFFatcaDeclareForm;
import com.freemi.entity.investment.MFKarvyFundsView;
import com.freemi.entity.investment.MFKarvyValueByCategory;
import com.freemi.entity.investment.MFKarvyValueByCategory2;
import com.freemi.entity.investment.MfNavData;
import com.freemi.entity.investment.SelectMFFund;
import com.freemi.entity.investment.TransactionStatus;


@Service
public interface BseEntryManager {
	
	//Customer records related crud operation
	public String saveCustomerDetails(BseMFInvestForm customerForm);
	public BseApiResponse saveFatcaDetails(BseMFInvestForm fatcaForm);
	public String updateFatcaStatus(String clientId, String status, String responseCode, String message);
	
	public List<SelectMFFund> getMFOrderHistory(String customerId);
	public UserProfile getCustomerProfileDetailsByMobile(String mobile);
	public List<BseMFInvestForm> getCustomerDetails(String customerId);
	public List<BseMFInvestForm> getCustomerByPan(String pan);
	public boolean isExisitngCustomer(String pan, String mobile);
	public String getCustomerPanfromMobile(String mobile);
	public String getClientIdfromMobile(String mobile);
	public boolean updateCustomerData(BseMFInvestForm custerProfileData);
	public boolean updateCustomerBankDetails(UserProfile investorData);
	public boolean updateCustomerAddress(UserProfile investorData);
	public String investmentProfileStatus(String mobileNumber);
	
	public List<BseMandateDetails> getCustomerMandateDetails(String clientId, String accountNumber);
	
	public BseMFInvestForm getCustomerInvestFormData(String mobile);
	
	public UserBankDetails getCustomerBankDetails(String clientCode);
	public String getEmdandateDetails(String mobile, String clientCode,String mandateType, String accNumber);
	public BseApiResponse updateEmdandateStatus(String clientCode,String mandateType, String accNumber);
	
	public String upddateCustomerFormSignature(String mobile, String pan, String signatureData);
	public String uploadAOFFormStatus(String mobileNumber, String status);
	
	// Customer MF transactions
	public boolean checkIfTransIdExist(String generatedTransId);
	public TransactionStatus savetransactionDetails(SelectMFFund selectedMFFund, String mandateId);
	public List<BsemfTransactionHistory> getAllPurchaseHistory(String clientId);
	public boolean updateCancelledTransactionStatus(String mobile,String clientId, String orderNo, String transactionNo);
	public SelectMFFund getTransactionDetails(String transactionId, String clientId);
	
	public List<BseAllTransactionsView> getCustomerAllTransactionRecords(String clientID, String mobileNumber, String panNumber);
	public BseAllTransactionsView getFundDetailsForAdditionalPurchase(String portfolio, String schemeCode,String investType, String mobileNumber);
	public BseAllTransactionsView getFundDetailsForRedemption(String portfolio, String schemeCode,String investType, String mobileNumber);
	public BsemfTransactionHistory getOrderDetailsForCancel(String portfolio, String schemeCode,String investType, String mobileNumber,String category, String transactionId);
	
	//BSE MF related operations
	public List<MfTopFundsInventory> getTopMfFunds();
	public List<BseMFTop15lsSip> getTopFunds();
	public List<BseMFSelectedFunds> getAllSelectedFunds();
	public List<BseMFSelectedFunds> getFundsByCategory(String category);
	public BseMFSelectedFunds getFundsByCode(String rtacode, String isin);
	
	
	public long getCurrentDayNextTransCount(Date date);
	
	//PORTFOLIO Crud respository
	public List<String> getSelectedAmcPortfolio(String amcCode, String clientId, String rtaAgent);
	public List<MFCamsFolio> getCamsPortfolio(String mobile, String pan);
	public List<MFCamsValueByCategroy> getCustomersCamsInvByCategory(String mobile, String pan);
	public List<MFKarvyValueByCategory> getCustomersKarvyInvByCategory(String mobile, String pan);
	public List<MFKarvyValueByCategory2> getCustomersKarvyInvByCategory2(String mobile, String pan);
	
	public MFCamsFolio getCamsFundsDetailsForRedeem(String code, String mobile, String folioNumber);
	public MFKarvyValueByCategory getKarvyFundsDetailsForRedeem(String code, String mobile, String folioNumber);
	
	
	public Page<BseFundsScheme> getpaginatedFundsList(Pageable p);
	
	
//	NAV
	public List<MfNavData> getnavdataByISIN(String isin);
	
}
