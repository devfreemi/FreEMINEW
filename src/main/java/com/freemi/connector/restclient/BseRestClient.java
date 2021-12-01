package com.freemi.connector.restclient;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.freemi.common.util.CommonConstants;
import com.freemi.entity.bse.BseAOFUploadResponse;
import com.freemi.entity.bse.BseApiResponse;
import com.freemi.entity.bse.BseOrderPaymentRequest;
import com.freemi.entity.bse.BseOrderPaymentResponse;
import com.freemi.entity.database.MfTopFundsInventory;
import com.freemi.entity.database.UserBankDetails;
import com.freemi.entity.general.ClientSystemDetails;
import com.freemi.entity.general.Datarquestresponse;
import com.freemi.entity.general.HttpClientResponse;
import com.freemi.entity.general.UserProfile;
import com.freemi.entity.investment.AofSignaure;
import com.freemi.entity.investment.BseAllTransactionsView;
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
import com.freemi.entity.investment.MfAllInvestorValueByCategory;
import com.freemi.entity.investment.MfNavData;
import com.freemi.entity.investment.SelectMFFund;
import com.freemi.entity.investment.TransactionStatus;
import com.freemi.services.interfaces.BseEntryManager;
import com.google.gson.JsonObject;

//@Service
public class BseRestClient implements BseEntryManager {

    private static final Logger logger = LogManager.getLogger(BseRestClient.class);

    @Autowired
    private Environment env;
    
    
    public static final String AUTH_TOKEN="Basic YWRtaW46UGFzc3dvcmQx";
    
    @Override
    public BseApiResponse saveCustomerDetails(MFCustomers customerForm, String customerType, String customerlogged,
	    String initiatedmobileid, ClientSystemDetails systemdetails) {
	final String url = env.getProperty(CommonConstants.URL_SERVICE_MF_BSE) + "/api/transaction/capture-registration-request";
	logger.info(url);
	RestTemplate restTemplate = new RestTemplate();
	BseApiResponse result = null;
	String flag = "";
	try {
	    HttpHeaders headers = new HttpHeaders();	
	    headercommonvalues(headers);
	    headers.set("tokenexist", customerlogged);
	    headers.set("customertype", customerType);
	    headers.set("initiatedid", initiatedmobileid);
	    
	    HttpEntity<Object> entity = new HttpEntity<Object>(customerForm,headers);
	    result =  restTemplate.postForObject(url, entity, BseApiResponse.class);
	    flag = result.getRemarks();
	    
	    
	}catch(Exception e) {
	    logger.error("Error processing request..",e);
	    flag="Service not responding currently. Please try again";
	    result = new BseApiResponse();
	    result.setResponseCode(CommonConstants.TASK_FAILURE_S);
	    result.setRemarks(flag);
	}
	
	return result;
    }

    @Override
    public /*BseApiResponse*/ BseApiResponse saveFatcaDetails(MFCustomers fatcaForm, String flag1, String dateformat,String clientcode,ClientSystemDetails systemdetails) {
	final String url = env.getProperty(CommonConstants.URL_SERVICE_MF_BSE) + "/api/transaction/capture-fatca-registration";
	logger.info(url);
	RestTemplate restTemplate = new RestTemplate();
	BseApiResponse response = new BseApiResponse();
	BseApiResponse result = null;
	try {
	    HttpHeaders headers = new HttpHeaders();	
	    headercommonvalues(headers);
	    
	    HttpEntity<Object> entity = new HttpEntity<Object>(fatcaForm,headers);
	    result =  restTemplate.postForObject(url, entity, BseApiResponse.class);
	}catch(Exception e) {
	    logger.error("Error processing request..",e);
	    result.setStatusCode(CommonConstants.TASK_FAILURE_S);
	    result.setResponseCode("Service not responding currently. Please try again");
	}
	
//	return result;
//	NOTE - This response needs to be worked on
	return response;
    }

    @Override
    public String updateFatcaStatus(String clientId, String status, String responseCode, String message) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<SelectMFFund> getMFOrderHistory(String customerId) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public UserProfile getCustomerProfileDetailsByMobile(String mobile) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<MFCustomers> getCustomerDetails(String customerId) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<MFCustomers> getCustomerByPan(String pan) {
	final String url = env.getProperty(CommonConstants.URL_SERVICE_MF_BSE) + "/api/transaction/get-customer-by-pan";
	logger.info(url);
	RestTemplate restTemplate = new RestTemplate();
	List<MFCustomers> customerData = null;
	try {
	    HttpHeaders headers = new HttpHeaders();	
	    headercommonvalues(headers);
	    JsonObject form = new JsonObject();
	    form.addProperty("pan", pan);
	    
	    HttpEntity<Object> entity = new HttpEntity<Object>(form.toString(),headers);
	    MFCustomers[] result =  restTemplate.postForObject(url, entity, MFCustomers[].class);
	    if(result.length>0) {
		customerData = Arrays.asList(result);
	    }
	}catch(Exception e) {
	    logger.error("Error processing request..",e);
	}
	
	return customerData;
    }

    @Override
    public boolean isExisitngCustomer(String mobile, String activeStatus) {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public boolean isExisitngBSECustomerByMobile(String mobile) {
	final String url = env.getProperty(CommonConstants.URL_SERVICE_MF_BSE) + "/api/transaction/check-bse-registered-customer";
	logger.info(url);
	RestTemplate restTemplate = new RestTemplate();
	BseApiResponse result = null;
	boolean flag = false;
	try {
	    HttpHeaders headers = new HttpHeaders();	
	    headercommonvalues(headers);
	    JsonObject form = new JsonObject();
	    form.addProperty("mobile", mobile);
	    
	    HttpEntity<Object> entity = new HttpEntity<Object>(form.toString(),headers);
	     result =  restTemplate.postForObject(url, entity, BseApiResponse.class);
	     logger.info("Response - "+ result.getStatusCode());
	     if(result.getStatusCode().equals(CommonConstants.TASK_SUCCESS_S) && result.getRemarks().equals("Y")) {
		 flag = true;
	     }
	}catch(Exception e) {
	    logger.error("Error processing request..",e);
	}
	
	return flag;
    }

    @Override
    public String getCustomerPanfromMobile(String mobile) {
	final String url = env.getProperty(CommonConstants.URL_SERVICE_MF_BSE) + "/api/transaction/get-customer-pan";
	logger.info(url);
	RestTemplate restTemplate = new RestTemplate();
	BseApiResponse result = null;
	String flag = null;
	try {
	    HttpHeaders headers = new HttpHeaders();	
	    headercommonvalues(headers);
	    JsonObject form = new JsonObject();
	    form.addProperty("mobile", mobile);
	    
	    HttpEntity<Object> entity = new HttpEntity<Object>(form.toString(),headers);
	     result =  restTemplate.postForObject(url, entity, BseApiResponse.class);
	     logger.info("Response - "+ result.getStatusCode());
	     if(result.getStatusCode().equals(CommonConstants.TASK_SUCCESS_S)) {
		 flag= result.getRemarks();
	     }
	}catch(Exception e) {
	    logger.error("Error processing request..",e);
	}
	
	return flag;
    }

    @Override
    public String getClientIdfromMobile(String mobile) {
	final String url = env.getProperty(CommonConstants.URL_SERVICE_MF_BSE) + "/api/transaction/get-client-id";
	logger.info(url);
	RestTemplate restTemplate = new RestTemplate();
	BseApiResponse result = null;
	String flag = null;
	try {
	    HttpHeaders headers = new HttpHeaders();	
	    headercommonvalues(headers);
	    JsonObject form = new JsonObject();
	    form.addProperty("mobile", mobile);
	    
	    HttpEntity<Object> entity = new HttpEntity<Object>(form.toString(),headers);
	     result =  restTemplate.postForObject(url, entity, BseApiResponse.class);
	     logger.info("Response - "+ result.getStatusCode());
	     if(result.getStatusCode().equals(CommonConstants.TASK_SUCCESS_S)) {
		 flag= result.getRemarks();
	     }
	}catch(Exception e) {
	    logger.error("Error processing request..",e);
	}
	
	return flag;
    }

    @Override
    public boolean updateCustomerData(MFCustomers custerProfileData) {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public boolean updateCustomerBankDetails(UserProfile investorData) {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public boolean updateCustomerAddress(UserProfile investorData) {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public String investmentProfileStatus(String mobileNumber) {
	final String url = env.getProperty(CommonConstants.URL_SERVICE_MF_BSE) + "/api/transaction/get-mf-profile-status";
	logger.info(url);
	RestTemplate restTemplate = new RestTemplate();
	String status="";
	try {
	    HttpHeaders headers = new HttpHeaders();	
	    headercommonvalues(headers);
	    JsonObject form = new JsonObject();
	    form.addProperty("mobile", mobileNumber);
	    
	    HttpEntity<Object> entity = new HttpEntity<Object>(form.toString(),headers);
	    status =  restTemplate.postForObject(url, entity, String.class);
	  
	    
	}catch(Exception e) {
	    logger.error("Error processing request..",e);
	    status="SERVICE_ERROR";
	}
	
	return status;
    }

    @Override
    public List<BseMandateDetails> getCustomerMandateDetails(String clientId, String accountNumber) {
	final String url = env.getProperty(CommonConstants.URL_SERVICE_MF_BSE) + "/api/transaction/get-customer-mandate-list";
	logger.info(url);
	RestTemplate restTemplate = new RestTemplate();
	List<BseMandateDetails> mandatelist = null;
	BseMandateDetails[] mandates =null;
	try {
	    HttpHeaders headers = new HttpHeaders();	
	    headercommonvalues(headers);
	    JsonObject form = new JsonObject();
	    form.addProperty("clientid", clientId);
	    form.addProperty("accno", accountNumber);
	    
	    HttpEntity<Object> entity = new HttpEntity<Object>(form.toString(),headers);
	    mandates =  restTemplate.postForObject(url, entity, BseMandateDetails[].class);
	    if(mandates.length>0) {
		mandatelist = Arrays.asList(mandates);
	    }
	    
	}catch(Exception e) {
	    logger.error("Error processing request..",e);
	}
	
	
	return mandatelist;
    }

    @Override
    public MFCustomers getCustomerInvestFormData(String mobile) {
	final String url = env.getProperty(CommonConstants.URL_SERVICE_MF_BSE) + "/api/transaction/get-registered-details";
	logger.info(url);
	RestTemplate restTemplate = new RestTemplate();
	MFCustomers result = null;
	try {
	    HttpHeaders headers = new HttpHeaders();	
	    headercommonvalues(headers);
	    JsonObject form = new JsonObject();
	    form.addProperty("mobile", mobile);
	    
	    HttpEntity<Object> entity = new HttpEntity<Object>(form.toString(),headers);
	    result =  restTemplate.postForObject(url, entity, MFCustomers.class);
	    
	}catch(Exception e) {
	    logger.error("Error processing request..",e);
	}
	
	if(result == null) {
	    logger.info("Customer details null.. Set empty form.");
		
	}
	
	return result;
    }

    @Override
    public UserBankDetails getCustomerBankDetails(String clientCode) {
	final String url = env.getProperty(CommonConstants.URL_SERVICE_MF_BSE) + "/api/transaction/get-customer-bank-details";
	logger.info(url);
	RestTemplate restTemplate = new RestTemplate();
	UserBankDetails result = null;
	try {
	    HttpHeaders headers = new HttpHeaders();	
	    headercommonvalues(headers);
	    JsonObject form = new JsonObject();
	    form.addProperty("cliendid", clientCode);
	    
	    HttpEntity<Object> entity = new HttpEntity<Object>(form.toString(),headers);
	    result =  restTemplate.postForObject(url, entity, UserBankDetails.class);
	    
	}catch(Exception e) {
	    logger.error("Error processing request..",e);
	}
	
	return result;
    }

    @Override
    public String getEmdandateDetails(String mobile, String clientCode, String mandateType, String accNumber) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public BseApiResponse updateEmdandateStatus(String clientCode, String mandateType, String accNumber) {
	final String url = env.getProperty(CommonConstants.URL_SERVICE_MF_BSE) + "/api/transaction/register-emandate";
	logger.info(url);
	RestTemplate restTemplate = new RestTemplate();
	BseApiResponse result = new BseApiResponse();
	try {
	    HttpHeaders headers = new HttpHeaders();	
	    headercommonvalues(headers);
	    JsonObject form = new JsonObject();
	    form.addProperty("mobile",clientCode);
	    form.addProperty("mandatetype", mandateType);
	    form.addProperty("amount", accNumber);
	    
	    HttpEntity<Object> entity = new HttpEntity<Object>(form.toString(),headers);
	    result =  restTemplate.postForObject(url, entity, BseApiResponse.class);
	}catch(Exception e) {
	    logger.error("Error processing request..",e);
	    result.setStatusCode(CommonConstants.TASK_FAILURE_S);
	    result.setRemarks("Service not responding");
	}
	
	return result;
    }

    @Override
    public String upddateCustomerFormSignature(String mobile, String pan, String signatureData1,
	    String signatureData2) {
	final String url = env.getProperty(CommonConstants.URL_SERVICE_MF_BSE) + "/api/transaction/update-aof-details";
	logger.info(url);
	RestTemplate restTemplate = new RestTemplate();
	BseApiResponse result = null;
	String flag="";
	try {
	    HttpHeaders headers = new HttpHeaders();	
	    headercommonvalues(headers);
	    AofSignaure form = new AofSignaure();
	    form.setPan(pan);
	    form.setMobile(mobile);
	    form.setSign1(signatureData1);
	    form.setSign2(signatureData2);
	    
	    HttpEntity<Object> entity = new HttpEntity<Object>(form.toString(),headers);
	    result =  restTemplate.postForObject(url, entity, BseApiResponse.class);
	    flag = result.getRemarks();
	}catch(Exception e) {
	    logger.error("Error processing request..",e);
	    flag="Internal error";
	}
	
	
	return flag;
    }

    @Override
    public String uploadAOFFormStatus(String mobileNumber, String status) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public boolean checkIfTransIdExist(String generatedTransId) {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public TransactionStatus savetransactionDetails(SelectMFFund selectedMFFund, String mandateId) {
	final String url = env.getProperty(CommonConstants.URL_SERVICE_MF_BSE) + "/api/transaction/capture-purchase-order";
	logger.info(url);
	RestTemplate restTemplate = new RestTemplate();
	TransactionStatus result = null;
	try {
	    HttpHeaders headers = new HttpHeaders();	
	    headercommonvalues(headers);
	    headers.set("mandateid", mandateId);
	    HttpEntity<Object> entity = new HttpEntity<Object>(selectedMFFund,headers);
	     result =  restTemplate.postForObject(url, entity, TransactionStatus.class);
//	     logger.info("Response for initiated transaction - "+);
	}catch(Exception e) {
	    logger.error("Error processing request..",e);
	}
	return result;
    }

    @Override
    public List<BsemfTransactionHistory> getAllPurchaseHistory(String clientId) {
	final String url = env.getProperty(CommonConstants.URL_SERVICE_MF_BSE) + "/api/transaction/get-purchase-history";
	logger.info(url);
	RestTemplate restTemplate = new RestTemplate();
	List<BsemfTransactionHistory> purchaseHistoryList = null;
	try {
	    HttpHeaders headers = new HttpHeaders();	
	    headercommonvalues(headers);
	    
	    JsonObject form = new JsonObject();
	    form.addProperty("clientid", clientId);
	    HttpEntity<Object> entity = new HttpEntity<Object>(form.toString(),headers);
	    BsemfTransactionHistory[] data =  restTemplate.postForObject(url, entity, BsemfTransactionHistory[].class);
	    if(data.length>0) {
		purchaseHistoryList= Arrays.asList(data);
	    }
	}catch(Exception e) {
	    logger.error("Error processing request..",e);
	}
	return purchaseHistoryList;
    }

    @Override
    public boolean updateCancelledTransactionStatus(String mobile, String clientId, String orderNo,
	    String transactionNo) {
	final String url = env.getProperty(CommonConstants.URL_SERVICE_MF_BSE) + "/api/transaction/update-cancelled-order-status";
	logger.info(url);
	RestTemplate restTemplate = new RestTemplate();
	BseApiResponse result =null;
	boolean flag = false;
	try {
	    HttpHeaders headers = new HttpHeaders();	
	    headercommonvalues(headers);
	    
	    JsonObject form = new JsonObject();
	    form.addProperty("clientid", clientId);
	    form.addProperty("portfolio", orderNo);
	    form.addProperty("transactionid", transactionNo);
	    
	    HttpEntity<Object> entity = new HttpEntity<Object>(form.toString(),headers);
	    result =  restTemplate.postForObject(url, entity, BseApiResponse.class);
	    if(result.getRemarks().equals("Y")) {
		flag=true;
	    }
	}catch(Exception e) {
	    logger.error("Error processing request..",e);
	}
	return flag;
    }

    @Override
    public SelectMFFund getTransactionDetails(String transactionId, String clientId) {
	final String url = env.getProperty(CommonConstants.URL_SERVICE_MF_BSE) + "/api/transaction/get-transaction-details";
	logger.info(url);
	RestTemplate restTemplate = new RestTemplate();
	SelectMFFund fundTransaction = null;
	try {
	    HttpHeaders headers = new HttpHeaders();	
	    headercommonvalues(headers);
	    
	    JsonObject form = new JsonObject();
	    form.addProperty("clientid", clientId);
	    form.addProperty("transid", transactionId);
	    HttpEntity<Object> entity = new HttpEntity<Object>(form.toString(),headers);
	    fundTransaction =  restTemplate.postForObject(url, entity, SelectMFFund.class);
	}catch(Exception e) {
	    logger.error("Error processing request..",e);
	}
	return fundTransaction;
    }

    @Override
    public TransactionStatus cancelSIPOrder(String mobile, String orderNo, String clientid, String oldtransactionid,
	    String newtransactionid, String investype) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public TransactionStatus cancelSIPOrderStatus(String orderNo, String clientid, String oldtransactionid) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void saveMFInitiatedTranasctionRequest(SelectMFFund fundDetails) {
	final String url = env.getProperty(CommonConstants.URL_SERVICE_MF_BSE) + "/api/transaction/capture-initiated-transaction";
	logger.info(url);
	RestTemplate restTemplate = new RestTemplate();
	BseApiResponse result = null;
	try {
	    HttpHeaders headers = new HttpHeaders();	
	    headercommonvalues(headers);
	    HttpEntity<Object> entity = new HttpEntity<Object>(fundDetails,headers);
	     result =  restTemplate.postForObject(url, entity, BseApiResponse.class);
	     logger.info("Response for initiated transaction - "+ result.getStatusCode());
	}catch(Exception e) {
	    logger.error("Error processing request..",e);
	}
	
    }

    protected void headercommonvalues(HttpHeaders headers) {
	headers.set("Authorization",AUTH_TOKEN);
	headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
    }

    @Override
    public List<BseAllTransactionsView> getCustomerAllTransactionRecords(String clientID, String mobileNumber,
	    String panNumber) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public BseAllTransactionsView getFundDetailsForAdditionalPurchase(String portfolio, String schemeCode,
	    String investType, String mobileNumber) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public BseAllTransactionsView getFundDetailsForRedemption(String portfolio, String schemeCode, String investType,
	    String mobileNumber) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public BsemfTransactionHistory getOrderDetailsForCancel(String portfolio, String schemeCode, String investType,
	    String mobileNumber, String category, String transactionId) {
	final String url = env.getProperty(CommonConstants.URL_SERVICE_MF_BSE) + "/api/transaction/cancel-order-details";
	logger.info(url);
	RestTemplate restTemplate = new RestTemplate();
	BsemfTransactionHistory bseSeletedFundDetails = null;
	try {
	    HttpHeaders headers = new HttpHeaders();	
	    headercommonvalues(headers);
	    JsonObject form = new JsonObject();
	    form.addProperty("orderno", portfolio);
	    form.addProperty("schemecode",schemeCode );
	    form.addProperty("investtype",investType );
	    form.addProperty("mobile", mobileNumber);
	    form.addProperty("categry", category);
	    form.addProperty("transno", transactionId);
	    
	    HttpEntity<Object> entity = new HttpEntity<Object>(form.toString(),headers);
	    bseSeletedFundDetails =  restTemplate.postForObject(url, entity, BsemfTransactionHistory.class);
	    
	}catch(Exception e) {
	    logger.error("Error processing request..",e);
	}
	
	return bseSeletedFundDetails;
    }

    @Override
    public List<MfTopFundsInventory> getTopMfFunds() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<BseMFTop15lsSip> getTopFunds() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<BseMFSelectedFunds> getAllSelectedFunds() {
	final String url = env.getProperty(CommonConstants.URL_SERVICE_MF_BSE) + "/api/search/get-top-funds";
	logger.info(url);
	RestTemplate restTemplate = new RestTemplate();
	List<BseMFSelectedFunds> result = null;
	try {
	    HttpHeaders headers = new HttpHeaders();	
	    headercommonvalues(headers);
	    
	    HttpEntity<Object> entity = new HttpEntity<Object>(headers);
	    BseMFSelectedFunds[] str =  restTemplate.postForObject(url, entity,BseMFSelectedFunds[].class);
	    if(str.length>0) {
		result = Arrays.asList(str);
	    }
	}catch(Exception e) {
	    logger.error("Error processing request..",e);
	}
	return result;
    }

    @Override
    public List<BseMFSelectedFunds> getFundsByCategory(String category) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public BseMFSelectedFunds getFundsByCode(String rtacode, String isin) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public BseOrderPaymentResponse getpendingPaymentLinks(String userid, String callbackurl) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String getCustomerInvestmentValue(String mobile, String pan) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public long getCurrentDayNextTransCount(Date date) {
	// TODO Auto-generated method stub
	return 0;
    }

    @Override
    public List<String> getSelectedAmcPortfolio(String amcCode, String clientId, String rtaAgent) {
	final String url = env.getProperty(CommonConstants.URL_SERVICE_MF_BSE) + "/api/transaction/get-customer-portfolio";
	logger.info(url);
	RestTemplate restTemplate = new RestTemplate();
	List<String> result = null;
	try {
	    HttpHeaders headers = new HttpHeaders();	
	    headercommonvalues(headers);
	    JsonObject form = new JsonObject();
	    form.addProperty("amccode", amcCode);
	    form.addProperty("clientid", clientId);
	    form.addProperty("rtaagent", rtaAgent);
	    
	    HttpEntity<Object> entity = new HttpEntity<Object>(form.toString(),headers);
	    String[] str =  restTemplate.postForObject(url, entity,String[].class);
	    if(str.length>0) {
		result = Arrays.asList(str);
	    }
	}catch(Exception e) {
	    logger.error("Error processing request..",e);
	}
	return result;
    }

    @Override
    public List<MFCamsFolio> getCamsPortfolio(String mobile, String pan) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<MFCamsValueByCategroy> getCustomersCamsInvByCategory(String mobile, String pan) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<MfAllInvestorValueByCategory> getCustomersAllFoliosByCategory(String mobile, String pan) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<MFKarvyValueByCategory2> getCustomersKarvyInvByCategory2(String mobile, String pan) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public MfAllInvestorValueByCategory getCamsFundsDetailsForRedeem(String channelPartnercode, String mobile,
	    String folioNumber) {
	final String url = env.getProperty(CommonConstants.URL_SERVICE_MF_BSE) + "/api/transaction/get-cams-redemption-details";
	logger.info(url);
	RestTemplate restTemplate = new RestTemplate();
	MfAllInvestorValueByCategory result = null;
	try {
	    HttpHeaders headers = new HttpHeaders();	
	    headercommonvalues(headers);
	    
	    HttpEntity<Object> entity = new HttpEntity<Object>(headers);
	    result =  restTemplate.postForObject(url, entity, MfAllInvestorValueByCategory.class);
	}catch(Exception e) {
	    logger.error("Error processing request..",e);
	}
	
	return result;
    }

    @Override
    public MfAllInvestorValueByCategory getKarvyFundsDetailsForRedeem(String customerFundsDetails, String mobile,
	    String folioNumber) {
	final String url = env.getProperty(CommonConstants.URL_SERVICE_MF_BSE) + "/api/transaction/get-karvy-redemption-details";
	logger.info(url);
	RestTemplate restTemplate = new RestTemplate();
	MfAllInvestorValueByCategory result = null;
	try {
	    HttpHeaders headers = new HttpHeaders();	
	    headercommonvalues(headers);
	    
	    HttpEntity<Object> entity = new HttpEntity<Object>(headers);
	    result =  restTemplate.postForObject(url, entity, MfAllInvestorValueByCategory.class);
	}catch(Exception e) {
	    logger.error("Error processing request..",e);
	}
	
	return result;
    }

    @Override
    public Page<BseFundsScheme> getpaginatedFundsList(Pageable p) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<MfNavData> getnavdataByISIN(String isin) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Object getMfRegistrationStatus(String mobile, String pan, String clientid) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String generateTransId() {
	final String url = env.getProperty(CommonConstants.URL_SERVICE_MF_BSE) + "/api/transaction/get-transactionid";
	logger.info(url);
	RestTemplate restTemplate = new RestTemplate();
	String result = null;
	try {
	    HttpHeaders headers = new HttpHeaders();	
	    headercommonvalues(headers);
	    
	    HttpEntity<Object> entity = new HttpEntity<Object>(headers);
	    result =  restTemplate.postForObject(url, entity, String.class);
	}catch(Exception e) {
	    logger.error("Error processing request..",e);
	}
	
	return result;
    }

    @Override
    public BseApiResponse uploadAOFForm(String pan, String aoffolderLocation,String logolocation, String clientCode,MFCustomers investForm) {
	final String url = env.getProperty(CommonConstants.URL_SERVICE_MF_BSE) + "/api/transaction/send-aof-to-bse";
	logger.info(url);
	RestTemplate restTemplate = new RestTemplate();
	BseApiResponse response =new BseApiResponse();
	BseAOFUploadResponse result = null;
	try {
	    HttpHeaders headers = new HttpHeaders();	
	    headercommonvalues(headers);
	    JsonObject form = new JsonObject();
	    form.addProperty("pan", pan);
	    form.addProperty("clientid", clientCode);
	    
	    HttpEntity<Object> entity = new HttpEntity<Object>(form.toString(),headers);
	    result =  restTemplate.postForObject(url, entity, BseAOFUploadResponse.class);
	}catch(Exception e) {
	    logger.error("Error processing request..",e);
	    result.setStatusCode(CommonConstants.TASK_FAILURE_S);
	    result.setStatusMessage("Services not responding currently");
	}
//	NOTE - This reponse need to worked on as on 28-04-2021. Correct response
	return response;
    }

    
    @Override
    public String BseOrderPaymentStatus(String clientid, String orderno) {
	final String url = env.getProperty(CommonConstants.URL_SERVICE_MF_BSE) + "/api/transaction/order-payment-status";
	logger.info(url);
	RestTemplate restTemplate = new RestTemplate();
	BseApiResponse result = new BseApiResponse();
	try {
	    HttpHeaders headers = new HttpHeaders();	
	    headercommonvalues(headers);
	    JsonObject form = new JsonObject();
	    form.addProperty("clientid", clientid);
	    form.addProperty("orderno", orderno);
	    
	    HttpEntity<Object> entity = new HttpEntity<Object>(form.toString(),headers);
	    result =  restTemplate.postForObject(url, entity, BseApiResponse.class);
	}catch(Exception e) {
	    logger.error("Error processing request..",e);
	    result.setStatusCode(CommonConstants.TASK_FAILURE_S);
	    result.setRemarks("Services not responding currently");
	}
	
	return result.getRemarks(); 
    }

    @Override
    public BseOrderPaymentResponse getPaymentUrl(BseOrderPaymentRequest request) {
	final String url = env.getProperty(CommonConstants.URL_SERVICE_MF_BSE) + "/api/transaction/order-payment-link";
	logger.info(url);
	RestTemplate restTemplate = new RestTemplate();
	BseOrderPaymentResponse result = new BseOrderPaymentResponse();
	try {
	    HttpHeaders headers = new HttpHeaders();	
	    headercommonvalues(headers);
	    
	    HttpEntity<Object> entity = new HttpEntity<Object>(request,headers);
	    result =  restTemplate.postForObject(url, entity, BseOrderPaymentResponse.class);
	}catch(Exception e) {
	    logger.error("Error processing request..",e);
	}
	
	return result; 
    }

    @Override
    public BseApiResponse extractAllotmentstatement(String fromdate, String todate, String orderstatus,
	    String ordertype, String settlementtype) {
	// TODO Auto-generated method stub
	return null;
    }


	@Override
	public Datarquestresponse checkifkeyregistered(String mobile, String pan, String searchtype, String filter1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HttpClientResponse completemfregistration(String mobile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BseApiResponse updateCustomerDetails(MFCustomers customerForm, String customerType, String customerlogged,
			String initiatedmobileid, ClientSystemDetails systemdetails) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bseregistrationstatus getbseregistrationstatus(String mobile, String pan, String customeruniqid,
			String clientcode) {
		// TODO Auto-generated method stub
		return null;
	}


	

}
