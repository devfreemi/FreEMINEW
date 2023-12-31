package com.freemi.services.interfaces;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.freemi.entity.bse.BseApiResponse;
import com.freemi.entity.bse.BseOrderPaymentRequest;
import com.freemi.entity.bse.BseOrderPaymentResponse;
import com.freemi.entity.bse.Bsepay;
import com.freemi.entity.bse.Nomineeregistrationresponse;
import com.freemi.entity.bse.Paymentgatewayresponse;
import com.freemi.entity.database.MfTopFundsInventory;
import com.freemi.entity.database.UserBankDetails;
import com.freemi.entity.general.ClientSystemDetails;
import com.freemi.entity.general.Datarquestresponse;
import com.freemi.entity.general.HttpClientResponse;
import com.freemi.entity.general.UserProfile;
import com.freemi.entity.investment.BseAllTransactionsView;
import com.freemi.entity.investment.BseBankid;
import com.freemi.entity.investment.BseFundsScheme;
import com.freemi.entity.investment.BseMFSelectedFunds;
import com.freemi.entity.investment.BseMFTop15lsSip;
import com.freemi.entity.investment.BseMandateDetails;
import com.freemi.entity.investment.BsemfTransactionHistory;
import com.freemi.entity.investment.Bseregistrationstatus;
import com.freemi.entity.investment.MFCamsFolio;
import com.freemi.entity.investment.MFCamsValueByCategroy;
import com.freemi.entity.investment.MFCustomers;
import com.freemi.entity.investment.MFKarvyValueByCategory2;
import com.freemi.entity.investment.MFNominationForm;
import com.freemi.entity.investment.MfAllInvestorValueByCategory;
import com.freemi.entity.investment.MfNavData;
import com.freemi.entity.investment.Nominee2faresponse;
import com.freemi.entity.investment.Nomineeverification;
import com.freemi.entity.investment.SelectMFFund;
import com.freemi.entity.investment.TransactionStatus;


@Service
public interface BseEntryManager {
	
	//Customer records related crud operation
	public BseApiResponse saveCustomerDetails(MFCustomers customerForm, String customerType, String customerlogged, String initiatedmobileid,ClientSystemDetails systemdetails);
	public BseApiResponse updateCustomerDetails(MFCustomers customerForm, String customerType, String customerlogged, String initiatedmobileid,ClientSystemDetails systemdetails);
	
	public BseApiResponse saveFatcaDetails(MFCustomers fatcaForm, String flag1, String dateformat, String clientcode,ClientSystemDetails systemdetails);
	public String updateFatcaStatus(String clientId, String status, String responseCode, String message);
	public HttpClientResponse completemfregistration(String mobile);
	
	
	public List<SelectMFFund> getMFOrderHistory(String customerId);
	public UserProfile getCustomerProfileDetailsByMobile(String mobile);
	public List<MFCustomers> getCustomerDetails(String customerId);
	public List<MFCustomers> getCustomerByPan(String pan);
	
	public boolean isExisitngCustomer(String mobile, String activeStatus);
	public boolean isExisitngBSECustomerByMobile(String mobile);
	public String getCustomerPanfromMobile(String mobile);
	public String getClientIdfromMobile(String mobile);
	public boolean updateCustomerData(MFCustomers custerProfileData);
	public boolean updateCustomerBankDetails(UserProfile investorData);
	public boolean updateCustomerAddress(UserProfile investorData);
	public String investmentProfileStatus(String mobileNumber);
	public Bseregistrationstatus getbseregistrationstatus(String mobile, String pan, String customeruniqid, String clientcode);
	
	public List<BseMandateDetails> getCustomerMandateDetails(String clientId, String accountNumber);
	
	public MFCustomers getCustomerInvestFormData(String mobile);
	
	public UserBankDetails getCustomerBankDetails(String clientCode);
	public UserBankDetails getCustomerBankDetails(String clientCode, Long bankidserial);
	public List<UserBankDetails> getAllCustomerBankDetails(String clientCode);
	
	public String getEmdandateDetails(String mobile, String clientCode,String mandateType, String accNumber);
	public BseApiResponse updateEmdandateStatus(String clientCode,String mandateType, String accNumber);
	
	public String upddateCustomerFormSignature(String mobile, String pan, String signatureData1, String signatureData2);
	public String uploadAOFFormStatus(String mobileNumber, String status);
	
	// Customer MF transactions
	public boolean checkIfTransIdExist(String generatedTransId);
	public TransactionStatus savetransactionDetails(SelectMFFund selectedMFFund, String mandateId);
	public List<BsemfTransactionHistory> getAllPurchaseHistory(String clientId);
	public boolean updateCancelledTransactionStatus(String mobile,String clientId, String orderNo, String transactionNo);
	public SelectMFFund getTransactionDetails(String transactionId, String clientId);
	public TransactionStatus cancelSIPOrder(String mobile, String orderNo, String clientid, String oldtransactionid,String newtransactionid,  String investype);
	public TransactionStatus cancelSIPOrderStatus(String orderNo, String clientid, String oldtransactionid);
	public void saveMFInitiatedTranasctionRequest(SelectMFFund fundDetails);
	
	public List<BseAllTransactionsView> getCustomerAllTransactionRecords(String clientID, String mobileNumber, String panNumber);
	public BseAllTransactionsView getFundDetailsForAdditionalPurchase(String portfolio, String schemeCode,String investType, String mobileNumber);
	public BseAllTransactionsView getFundDetailsForRedemption(String portfolio, String schemeCode,String investType, String mobileNumber);
	public BsemfTransactionHistory getOrderDetailsForCancel(String portfolio, String schemeCode,String investType, String mobileNumber,String category, String transactionId);
	
	//BSE MF related operations
	public List<MfTopFundsInventory> getTopMfFunds();
	public List<BseMFTop15lsSip> getTopFunds();
	
	@Deprecated
	//public List<BseMFSelectedFunds> getAllSelectedFunds();
	
	public List<BseMFSelectedFunds> getFundsByCategory(String category);
	public BseMFSelectedFunds getFundsByCode(String rtacode, String isin);
	public BseOrderPaymentResponse getpendingPaymentLinks(String userid,String callbackurl);
	
	public String getCustomerInvestmentValue(String mobile, String pan);
	
	
	public long getCurrentDayNextTransCount(Date date);
	
	//PORTFOLIO Crud respository
	public List<String> getSelectedAmcPortfolio(String amcCode, String clientId, String rtaAgent);
	public List<MFCamsFolio> getCamsPortfolio(String mobile, String pan);
	public List<MFCamsValueByCategroy> getCustomersCamsInvByCategory(String mobile, String pan);
	public List<MfAllInvestorValueByCategory> getCustomersAllFoliosByCategory(String mobile, String pan);
	public List<MFKarvyValueByCategory2> getCustomersKarvyInvByCategory2(String mobile, String pan);
	
	public MfAllInvestorValueByCategory getCamsFundsDetailsForRedeem(String channelPartnercode, String mobile, String folioNumber);
	public MfAllInvestorValueByCategory getKarvyFundsDetailsForRedeem(String customerFundsDetails, String mobile, String folioNumber);
	
	
	public Page<BseFundsScheme> getpaginatedFundsList(Pageable p);
	
	
//	NAV
	public List<MfNavData> getnavdataByISIN(String isin);
	
	public Object getMfRegistrationStatus(String mobile, String pan, String clientid);
	
	public String generateTransId();
	
	public BseApiResponse uploadAOFForm(String mobileNumber, String aoffolderLocation,String logolocation, String clientCode,MFCustomers investForm);
	
	public String BseOrderPaymentStatus(String clientid, String orderno);
	
	public BseOrderPaymentResponse getPaymentUrl(BseOrderPaymentRequest request);
	
	public Paymentgatewayresponse getPaymentGateway(Bsepay request);
	
	public BseApiResponse extractAllotmentstatement(String fromdate, String todate, String orderstatus, String ordertype, String settlementtype);
	
	public List<BseMandateDetails> getmandates(String mobile, String clientid, String otther1);
	
	public BseApiResponse getemandateauthurl(String clientid, String mandateid);
	
	public BseApiResponse getemandatestatus(String clientid, String mandateid);
	
	public Datarquestresponse checkifkeyregistered(String mobile, String pan, String searchtype, String filter1);
	
	public String getifscdetails(String ifsc);
	
	public MFNominationForm getnomineefetails(String mobile, String clientid);
	
	public Nomineeregistrationresponse submitnomineeverification(Nomineeverification nomineedata);
	
	public Nominee2faresponse authenticatenominee(String mobile, String clientid, String callbackurl);
	
	public List<BseBankid> getbankgateways(String paymentmethod, String razorpaybankname );
	
}
