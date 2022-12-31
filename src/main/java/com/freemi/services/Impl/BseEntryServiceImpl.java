package com.freemi.services.Impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.freemi.common.util.BseAOFGenerator;
import com.freemi.common.util.BseRelatedActions;
import com.freemi.common.util.CommonConstants;
import com.freemi.common.util.CommonTask;
import com.freemi.common.util.InvestFormConstants;
import com.freemi.database.interfaces.AllotmentStmntCrudRepository;
import com.freemi.database.interfaces.BseCamsByCategoryRepository;
import com.freemi.database.interfaces.BseCustomerAOFRepository;
import com.freemi.database.interfaces.BseCustomerAddressCrudRepository;
import com.freemi.database.interfaces.BseCustomerBankDetailsCrudRespository;
import com.freemi.database.interfaces.BseCustomerCrudRespository;
import com.freemi.database.interfaces.BseCustomerFATCACrudRepository;
import com.freemi.database.interfaces.BseCustomerMfRepository;
import com.freemi.database.interfaces.BseCustomerNomineeCrudRepository;
import com.freemi.database.interfaces.BseFundsExplorerRepository;
import com.freemi.database.interfaces.BseKarvyByCategoryRepository2;
import com.freemi.database.interfaces.BseMandateCrudRepository;
import com.freemi.database.interfaces.BseOrderEntryResponseRepository;
import com.freemi.database.interfaces.BseSelectedCategoryFundsRepository;
import com.freemi.database.interfaces.BseTop15lsSipViewCrudReositry;
import com.freemi.database.interfaces.BseTransCountCrudRepository;
import com.freemi.database.interfaces.BseTransCrudRepository;
import com.freemi.database.interfaces.BseTransHistoryViewCrudRepository;
import com.freemi.database.interfaces.BseTransactionsView;
import com.freemi.database.interfaces.MFInitiatedTransactionCrudRepository;
import com.freemi.database.interfaces.MfCamsFolioCrudRepository;
import com.freemi.database.interfaces.MfNavDataCrudRepository;
import com.freemi.database.interfaces.PortfolioCrudRepository;
import com.freemi.database.interfaces.TopFundsRepository;
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
import com.freemi.entity.investment.AddressDetails;
import com.freemi.entity.investment.Allotmentstatement;
import com.freemi.entity.investment.BseAOFDocument;
import com.freemi.entity.investment.BseAllTransactionsView;
import com.freemi.entity.investment.BseDailyTransCounter;
import com.freemi.entity.investment.BseFundsScheme;
import com.freemi.entity.investment.BseMFSelectedFunds;
import com.freemi.entity.investment.BseMFTop15lsSip;
import com.freemi.entity.investment.BseMandateDetails;
import com.freemi.entity.investment.BseOrderEntryResponse;
import com.freemi.entity.investment.BsemfTransactionHistory;
import com.freemi.entity.investment.Bseregistrationstatus;
import com.freemi.entity.investment.MFCamsFolio;
import com.freemi.entity.investment.MFCamsValueByCategroy;
import com.freemi.entity.investment.MFCustomers;
import com.freemi.entity.investment.MFFatcaDeclareForm;
import com.freemi.entity.investment.MFKarvyValueByCategory2;
import com.freemi.entity.investment.MFNominationForm;
import com.freemi.entity.investment.MFinitiatedTrasactions;
//import com.freemi.entity.investment.MFKarvyFundsView;
import com.freemi.entity.investment.MfAllInvestorValueByCategory;
import com.freemi.entity.investment.MfNavData;
import com.freemi.entity.investment.SelectMFFund;
import com.freemi.entity.investment.TransactionStatus;
import com.freemi.services.interfaces.BseEntryManager;
import com.freemi.services.interfaces.InvestmentConnectorBseInterface;
import com.freemi.services.interfaces.MailSenderInterface;
import com.freemi.services.interfaces.ProfileRestClientService;

@Service
public class BseEntryServiceImpl implements BseEntryManager {

    @Autowired
    BseCustomerCrudRespository bseCustomerCrudRespository;

    @Autowired
    BseCustomerAddressCrudRepository bseCustomerAddressCrudRepository;

    @Autowired
    BseCustomerBankDetailsCrudRespository bseCustomerBankDetailsCrudRespository;

    @Autowired
    BseTransCrudRepository bseTransCrudRepository;

    @Autowired
    InvestmentConnectorBseInterface investmentConnectorBseInterface;

    @Autowired
    TopFundsRepository topFundsRepository;

    @Autowired
    PortfolioCrudRepository portfolioCrudRepository;

    @Autowired
    BseTransactionsView bseTransactionsView;

    @Autowired
    BseTransCountCrudRepository bseTransCountCrudRepository;

    @Autowired
    BseOrderEntryResponseRepository bseOrderEntryResponseRepository;

    @Autowired
    BseTransHistoryViewCrudRepository bseTransHistoryViewCrudRepository;

    @Autowired
    BseTop15lsSipViewCrudReositry bseTop15lsSipViewCrudReositry;

    @Autowired
    BseCustomerNomineeCrudRepository bseCustomerNomineeCrudRepository;

    @Autowired
    BseMandateCrudRepository bseMandateCrudRepository;

    @Autowired
    BseFundsExplorerRepository bseFundsExplorerRepository;

    @Autowired
    BseSelectedCategoryFundsRepository bseSelectedCategoryFundsRepository;

    @Autowired
    BseCustomerFATCACrudRepository bseCustomerFATCACrudRepository;

    @Autowired
    MfCamsFolioCrudRepository mfCamsFolioCrudRepository;

    @Autowired
    BseCamsByCategoryRepository bseCamsByCategoryRepository;

    @Autowired
    BseCustomerMfRepository bseCustomerMfRepository;

    @Autowired
    BseKarvyByCategoryRepository2 bseKarvyByCategoryRepository2;

    @Autowired
    MfNavDataCrudRepository mfNavDataCrudRepository;

    @Autowired
    MFInitiatedTransactionCrudRepository mfInitiatedTransactionCrudRepository;

    @Autowired
    ProfileRestClientService profileRestClientService;

    @Autowired
    AllotmentStmntCrudRepository allotmentStmntCrudRepository;
    

    @Autowired
    MailSenderInterface mailSenderInterface;
    
    @Autowired
    BseCustomerAOFRepository bseaofepository;
    
    @Autowired
    Environment env;
    
    SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-mm-dd");
	 SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("dd/mm/yyyy");


    private static final Logger logger = LogManager.getLogger(BseEntryServiceImpl.class);

    @Override
    public BseApiResponse saveCustomerDetails(MFCustomers customerForm, String customerType, String customerlogged, String initiatedid,ClientSystemDetails systemdetails) {
    	logger.info("saveCustomerDetails(): Begin registration process for customer PAN- "+ customerForm.getPan1() + " :mobile: "+ customerForm.getMobile() + " : customer type- "+ customerType + " : LOGGED: "+ customerlogged);
    	String flag = CommonConstants.TASK_SUCCESS_S;
    	String customerid ="";
    	boolean registerCustomerToBse = false;
    	boolean transactionfailed = false;
    	BseApiResponse response = new BseApiResponse();

    	SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-mm-dd");
    	SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("dd/mm/yyyy");
    	String bseregistrattioncomplete="N";

    	
    	MFCustomers toUpdateForm = bseCustomerCrudRespository.getByMobileAndAccountActive(customerForm.getMobile(),"Y");
    	
    	//		if(bseCustomerCrudRespository.existsByPan1(customerForm.getPan1())){
//    	if(bseCustomerCrudRespository.existsByMobileAndAccountActive(customerForm.getMobile(),"Y")){
    	if(toUpdateForm!=null){
    		logger.info("Account already exist with given mobile number.. Request data should be updated only if new customer ");
//    		String registeredpan = bseCustomerCrudRespository.getCustomerPanNumberFromMobileAndActive(customerForm.getMobile(), "Y");
    		String registeredpan = toUpdateForm.getPan1();
    		logger.info("Registered PAN - "+ registeredpan);
    		if( (!registeredpan.equalsIgnoreCase(customerForm.getPan1()) && bseCustomerCrudRespository.existsByPan1(customerForm.getPan1())) || (profileRestClientService.isPanExisitngForOthers(customerForm.getMobile(), customerForm.getPan1()).equals("Y")) ) {
    			logger.info("Registered PAN different than submitted. PAN already found to be existing. Duplicate PAN entry disallowed..");
    			flag="PAN_DUPLICATE";
    			registerCustomerToBse = false;
    		}else {

    			//			Check account registered at BSE end. IF status is no, only try to push existing customer details to BSE
    			//			String bseRegisterStatus = bseCustomerCrudRespository.getBseRegistrationStatus(registeredpan);
    			
//    			String bseRegisterStatus = bseCustomerCrudRespository.getBseRegistrationStatusByMobile(customerForm.getMobile(),"Y");
    			String bseRegisterStatus = toUpdateForm.getBseregistrationSuccess();
    			
    			logger.info("BSE registration status retrieved - "+ bseRegisterStatus);

    			if(bseRegisterStatus!=null && bseRegisterStatus.equals("Y")){
    				logger.info("Customer already registered both at FREEMI and BSE");
    				flag="BSE_REGISTRATION_COMPLETE";
    				customerid = toUpdateForm.getClientID();
    				if(customerid == null) {
    					customerid= bseCustomerCrudRespository.getClientIdFromMobile(customerForm.getMobile());
    				}
    				
    				customerForm.setClientID(customerid);
    				customerForm.getBankDetails().setClientID(customerid);
    				customerForm.getAddressDetails().setClientID(customerid);
    				customerForm.getNominee().setClientID(customerid);
    				customerForm.getFatcaDetails().setClientID(customerid);
    				
    				mapUpdatedCustomerMfData(customerForm,toUpdateForm,systemdetails);
//    				bseCustomerCrudRespository.saveAndFlush(toUpdateForm);
    				
    				logger.info("Already registrered in BSE Customer current details not saved/updated in database.. Data not pushed to BSE");
    				
    			} else{
    				logger.info("Customer registered at FREEMI but BSE registration not complete. Update current data and try to register at BSE end only with current details");
    				//				System.out.println("line- "+customerForm.getBankDetails().getSerialNo());
    				//			    customerid= bseCustomerCrudRespository.getClientIdFromPan(customerForm.getPan1());
    				customerid= bseCustomerCrudRespository.getClientIdFromMobile(customerForm.getMobile());

    				customerForm.setClientID(customerid);
    				customerForm.getBankDetails().setClientID(customerid);
    				customerForm.getAddressDetails().setClientID(customerid);
    				customerForm.getNominee().setClientID(customerid);
    				customerForm.getFatcaDetails().setClientID(customerid);

    				logger.info("Search for exisiting record to update : " + customerForm.getMobile() + " : "+ customerForm.getPan1());

    				//			    MFCustomers toUpdateForm = bseCustomerCrudRespository.getByMobileAndPan1AndAccountActive(customerForm.getMobile(),customerForm.getPan1(),"Y");
//    				MFCustomers toUpdateForm = bseCustomerCrudRespository.getByMobileAndAccountActive(customerForm.getMobile(),"Y");

    				logger.debug("TO UPDATE BSE  data - "+ toUpdateForm);
    				mapUpdatedCustomerMfData(customerForm,toUpdateForm,systemdetails);
    				bseCustomerCrudRespository.saveAndFlush(toUpdateForm);
    				logger.info("Customer current details saved/updated in database...");
    				registerCustomerToBse = true;
    			}

    		}

    	}else{
    		logger.info("Mobile no is not found in database in active status.. Process with new registration");

    		if( bseCustomerCrudRespository.existsByPan1(customerForm.getPan1()) || (profileRestClientService.isPanExisitngForOthers(customerForm.getMobile(), customerForm.getPan1()).equals("Y") ) ) {
    			//			if(bseCustomerCrudRespository.existsByPan1andAccountActive(customerForm.getPan1(),"Y")) {
    			logger.info("PAN already found to be existing. Duplicate PAN entry disallowed..");
    			flag="PAN_DUPLICATE";
    			registerCustomerToBse = false;
    		}else {

    			//Generate client ID
    			int loop =1;

    			do{
    				customerid=BseRelatedActions.generateID(customerForm.getInvName(), customerForm.getPan1(), null, customerForm.getMobile(),loop++);
    				logger.info("Generated BSE Client ID for mobile- "+customerForm.getMobile() + " : "+ customerid);
    			}while(bseCustomerCrudRespository.existsByClientID(customerid));

    			logger.info("Final Generated login ID for customer with PAN - "+customerForm.getPan1()+ " : " + customerid);
    			customerForm.setClientID(customerid);
    			customerForm.getBankDetails().setClientID(customerid);
    			customerForm.getAddressDetails().setClientID(customerid);
    			customerForm.getNominee().setClientID(customerid);
    			customerForm.getFatcaDetails().setClientID(customerid);
    			customerForm.setRegistrationTime(new Date());
    			
    			customerForm.setSystemip(systemdetails.getClientIpv4Address());
    			customerForm.setSystemDetails(systemdetails.getClientBrowser());
    			
    			logger.info("Transaction started to save BSE customer registration data for generated client ID - " + customerid);

//    			MFCustomers customerCopy = customerForm;
    			
    			//			Convert date to db format
    			logger.info("saveCustomerDetails(): Convert DOB Format: "+ customerForm.getCustomerdob());
    			try {
    				Date date1 = simpleDateFormat2.parse(customerForm.getCustomerdob());
    				String bseFormatDob = simpleDateFormat1.format(date1);
    				logger.info("DOB field converted to DB format- "+ bseFormatDob);
    				customerForm.setInvDOB(bseFormatDob);
    			} catch (ParseException e) {
    				logger.error("saveCustomerDetails(): failed to convert date. Leaving date to default format. ",e);

    			}
//    			updatecommonfield(customerForm,null);
    			customerForm.setFatcaDetails(updatefatcafield(customerForm,customerForm.getFatcaDetails(),null,systemdetails));
    			
    			bseCustomerCrudRespository.saveAndFlush(customerForm);
    			//		bseCustomerCrudRespository.flush();
    			registerCustomerToBse = true;
    			logger.info("Customer record saved successfully to database");

    		}

    	}
    	
    	if(registerCustomerToBse){
    		logger.info("Customer registered at FREEMI portal. Begin to push customer details at BSE end");
    		String bseResponse = investmentConnectorBseInterface.saveCustomerRegistration(customerForm, null);
    		
    		if(bseResponse.equalsIgnoreCase("SUCCESS")){
    			bseregistrattioncomplete="Y";
    			response.setData1(customerForm.getClientID());
    		}else{
    			logger.info("Failed to push customer details to BSE platform. Failure reason- "+ bseResponse);
    			flag = bseResponse;
    			transactionfailed =true;
    		}

    		logger.info("Update Registration status in DB - "+ bseregistrattioncomplete);
    		try{
    			logger.info("Updating BSE registration status to database...");
    			bseCustomerCrudRespository.updateBseRegistrationStatusAndRegistrationResponse(customerid, bseResponse,bseregistrattioncomplete);

    		}catch(Exception e){
    			logger.error("Failed to update customer BSE registration status to database, notify admin",e);
    			mailSenderInterface.notifyTransactionErrorToAdmin(null, flag, null, "Mutual Fund Transaction", "Account Registration- internal error : " + e.getLocalizedMessage(), customerForm.getMobile(), customerForm.getInvName());
    		}
    		logger.info("Customer registration status updated successfully");


    	}

    	if(transactionfailed) {
    		logger.info("BSE Registration failed... Notify admin by email");
    		mailSenderInterface.notifyTransactionErrorToAdmin(null, flag, null, "Mutual Fund Transaction", "Account Registration failed", customerForm.getMobile(), customerForm.getInvName());
    	}

    	if(flag.equalsIgnoreCase(CommonConstants.TASK_SUCCESS_S)) {
    		response.setResponseCode(CommonConstants.TASK_SUCCESS_S);
    		response.setRemarks("BSE account registration successul");
    	}else if(flag.equalsIgnoreCase("BSE_REGISTRATION_COMPLETE")){
    		logger.info("BSE registratrion is already complete. Hence main form will not be updated. Only flag fields will be updated");
    		response.setResponseCode(CommonConstants.TASK_SUCCESS_S);
    		response.setRemarks("BSE account registration successul");
    	}else {
    		response.setResponseCode(CommonConstants.TASK_FAILURE_S);
    		/*if (flag.equalsIgnoreCase("EXIST")) {
    			response.setRemarks("Customer already exist with given PAN no.");
    		} else*/ if (flag.equalsIgnoreCase("PAN_DUPLICATE")) {
    			response.setRemarks("PAN already registered. Kindly contact admin in case of discrepancy.");
    		}else if (flag.equalsIgnoreCase("MOBILE_DUPLICATE")) {
    			response.setRemarks("Mobile no already registered. Login to complete registration.");
    		} else if (flag.equalsIgnoreCase("BSE_CONN_FAIL")) {
    			response.setRemarks("BSE endpoint connection failure!");
    		} else {
    			response.setRemarks(flag);
    		}
    	}

    	return response;
    }

    @Override
    public TransactionStatus savetransactionDetails(SelectMFFund selectedMFFund, String mandateId) {
    	logger.info("BSE TRANSCTION ... Process...");

    	boolean transactionfailed = false;
    	//Save details to database, process to BSE, update database with response
    	TransactionStatus transStatus = new TransactionStatus();
    	BseOrderEntryResponse bseResult =null;
    	try{
    		selectedMFFund.setOrderPlaceTime(new Date());

    		//Generate BSE related ref no
    		StringBuffer ref = new StringBuffer();
    		if(selectedMFFund.getTransactionType().equals("CXL")){
    			logger.info("Order cancellation request received for previous transaction ID- "+ selectedMFFund.getTransactionID());
    			ref.append("C").append(selectedMFFund.getClientID().substring(6)).append(Calendar.getInstance().getTimeInMillis());
    		}else if(selectedMFFund.getTransactionType().equals("REDEEM")){
    			ref.append("R").append(selectedMFFund.getClientID().substring(6)).append(Calendar.getInstance().getTimeInMillis());
    		}else if(selectedMFFund.getTransactionType().equals("MOD")){
    			ref.append("M").append(selectedMFFund.getClientID().substring(6)).append(Calendar.getInstance().getTimeInMillis());
    		}else{
    			ref.append("P").append(selectedMFFund.getClientID().substring(6)).append(Calendar.getInstance().getTimeInMillis());
    		}
    		selectedMFFund.setBseRefNo(ref.toString());

    		logger.info("New transaction request received of category:  "+selectedMFFund.getTransactionType()+" .Generated Transaction reference ID: "+ selectedMFFund.getTransactionID());

    		//		Generate BSE transaction Reference no
    		Date date = new Date();
    		StringBuffer transNumber = new StringBuffer();
    		transNumber.append((new SimpleDateFormat("yyyyMMdd").format(date))).append(CommonConstants.BSE_MEMBER_ID);
    		long counter= getCurrentDayNextTransCount(date);
    		for(int i=1;i<(6-Long.toString(counter).length());i++){
    			transNumber.append("0");
    		}
    		transNumber.append(Long.toString(counter));

    		logger.info("Requesting BSE to register transaction for client id- : "+ selectedMFFund.getClientID() + " : Transaction code: "+ transNumber.toString()+ ": Scheme code: "+ selectedMFFund.getSchemeCode() + " : Amount: "+ selectedMFFund.getInvestAmount());

    		//Call BSE

    		bseResult = investmentConnectorBseInterface.processCustomerTransactionbsaRequest(selectedMFFund, transNumber.toString(),mandateId);

    		logger.info("Status of requested transaction - "+ bseResult.getSuccessFlag());

    		if(bseResult.getSuccessFlag().equalsIgnoreCase("0")){

    			if(CommonConstants.BSE_SAVE_TRANSACTION.equalsIgnoreCase("Y")){
    				logger.info("Transaction is successful. Saving transaction request to Database");
    				try{
    					selectedMFFund = bseTransCrudRepository.saveAndFlush(selectedMFFund);
    					logger.info("Save transaction response from BSE to database");
    					bseOrderEntryResponseRepository.saveAndFlush(bseResult);
    				}catch(Exception e){
    					logger.error("BSE transaction is successful but failed to save data to dabtabase for transaction ID: "+ selectedMFFund.getTransactionID(),e);
    				}

    			}else{
    				logger.info("BSE_SAVE_TRANSACTION is disabled. Trsansaction not saved  to databse.");
    				//		    transactionfailed =true;

    			}

    			transStatus.setSuccessFlag("S");
    			transStatus.setStatusMsg(bseResult.getBsereMarks());
    			transStatus.setBseOrderNoFromResponse(bseResult.getOrderNoOrSipRegNo());

    		}else if (bseResult.getSuccessFlag().equalsIgnoreCase(CommonConstants.BSE_API_SERVICE_DISABLED)){
    			logger.info("Transaction disabled. Reason- "+bseResult.getBsereMarks());
    			transStatus.setSuccessFlag("D");
    			transStatus.setStatusMsg(bseResult.getBsereMarks());
    		}else{
    			logger.info("Transaction has failed. Transaction declined from saving to database. Reason- "+bseResult.getBsereMarks());
    			transStatus.setSuccessFlag("F");
    			transStatus.setStatusMsg(bseResult.getBsereMarks()!=null?bseResult.getBsereMarks():bseResult.getSuccessFlag());
    			transactionfailed = true;
    		}

    	}catch(Exception e){
    		logger.error("Failed to save transaction Details for MF Purchase",e);
    		if(bseResult.getSuccessFlag().equalsIgnoreCase("0")){
    			transStatus.setSuccessFlag("SF");		// success in 
    			transStatus.setStatusMsg("Transaction successful in BSE but failed to save at FREEMI.");
    			transactionfailed = true;
    		}
    	}

    	if(transactionfailed) {
    		logger.info("Transaction failed... Notify admin by email");
    		mailSenderInterface.notifyTransactionErrorToAdmin(null, transStatus.getStatusMsg(), null, "Mutual Fund Transaction", "Purcahse failure", selectedMFFund.getMobile(), selectedMFFund.getInvestorName());
    	}

    	return transStatus;
    }



    @Override
    public boolean updateCancelledTransactionStatus(String mobile, String clientId, String orderNo,
	    String transactionNo) {
	logger.info("Request received to update transaction status to mark the transaction as cancelled for order ID-" + orderNo + " : transactionID: "+ transactionNo +" : client ID: "+ clientId);
	boolean flag=false;

	try{
	    int i = bseTransCrudRepository.disablePurchaseTransaction(clientId, transactionNo);
	    logger.info("Is the status of transaction updated: "+ i);
	    if(i==1){
		flag=true;
	    }
	}catch(Exception e){
	    logger.info("Failed to update cancelled order status for forder ID: "+ orderNo);
	}

	return flag;
    }



    @Override
    public List<SelectMFFund> getMFOrderHistory(String value) {
	String clientId = null;
	List<SelectMFFund> trasactionDetails = null;
	if(bseCustomerCrudRespository.existsByMobileAndAccountActive(value,"Y")){
	    clientId = bseCustomerCrudRespository.getClientIdFromMobile(value);
	    trasactionDetails =  bseTransCrudRepository.getByClientID(clientId);
	}else{
	    logger.info("No registered BSE customer by mobile number found to show transaction data - "+ value);
	}

	return trasactionDetails;
    }

    @Override
    public List<MFCustomers> getCustomerDetails(String customerId) {
	// TODO Auto-generated method stub

	//		return bseCustomerCrudRespository.getByClientID(customerId);
	return bseCustomerCrudRespository.getByClientIDAndAccountActive(customerId,"Y");

    }

    @Override
    public List<BseAllTransactionsView> getCustomerAllTransactionRecords(String clientID, String mobileNumber, String panNumber) {
	// TODO Auto-generated method stub
	String client=null;
	List<BseAllTransactionsView> groupedTransationDetails = null;
	if(bseCustomerCrudRespository.existsByMobileAndAccountActive(mobileNumber,"Y")){
	    client = bseCustomerCrudRespository.getClientIdFromMobile(mobileNumber);
	    groupedTransationDetails = bseTransactionsView.findAllByClientID(client);
	}else{
	    logger.info("No registered BSE customer by mobile number found to show transaction data - "+ mobileNumber);
	}

	return groupedTransationDetails;
    }

    @Override
    public List<MfTopFundsInventory> getTopMfFunds() {
	// TODO Auto-generated method stub
	return topFundsRepository.findAll();

    }

    @Override
    public boolean isExisitngCustomer(String pan, String mobile) {
	// TODO Auto-generated method stub
	return bseCustomerCrudRespository.existsByPan1(pan);
    }

    @Override
    public boolean isExisitngBSECustomerByMobile(String mobile) {
	return bseCustomerCrudRespository.existsByMobileAndAccountActive(mobile, "Y");
    }

    @Override
    public List<MFCustomers> getCustomerByPan(String pan) {
	// TODO Auto-generated method stub
	logger.info("getCustomerByPan(): Get customers investment profile- "+ pan);

	//		return bseCustomerCrudRespository.findByPan1(pan);
	return bseCustomerCrudRespository.findByPan1AndAccountActive(pan,"Y");
    }

    @Override
    public List<String> getSelectedAmcPortfolio(String schemeCode, String pan,String rtaAgent) {
	// TODO Auto-generated method stub
	logger.info("Querying to find exisitng portfolios for selected AMC for PAN- "+ pan + " :schemecode- "+ schemeCode);
	List<String> portfolios = null;
	/*
	 * if(rtaAgent.equalsIgnoreCase("CAMS")){
	 * logger.info("Querying CAMS feedback DB for list of portfolio for customer: "+
	 * clientId); portfolios=
	 * bseCamsByCategoryRepository.getSelectedPortFolio(amcCode, clientId); }else{
	 * logger.info("Querying KARVY feedback for portfolio list..."); }
	 */
	portfolios= bseCustomerMfRepository.getCustomerFoliosForPurchase(pan, schemeCode);

	logger.info("Total portfolios found- "+ portfolios);

	return portfolios;
    }

    @Override
    public String getCustomerPanfromMobile(String mobile) {
	// TODO Auto-generated method stub
	String pan=null;
	//		pan= bseCustomerCrudRespository.getCustomerPanNumberFromMobile(mobile);
	pan= bseCustomerCrudRespository.getCustomerPanNumberFromMobileAndActive(mobile, "Y");
	logger.info("getCustomerPanfromMobile(): Returning active account PAN for mobile- "+ mobile + " : "+ pan);
	return pan;
    }

    @Override
    public boolean checkIfTransIdExist(String generatedTransId) {
	// TODO Auto-generated method stub
	return bseTransCrudRepository.existsByTransactionID(generatedTransId);
    }

    @Override
    public boolean updateCustomerData(MFCustomers custerProfileData) {
	// TODO Auto-generated method stub
	return false;
    }

    /*@Override
	public BseMFInvestForm getCustomerDetailsByMobile(String mobile) {
		BseMFInvestForm investorProfileData = bseCustomerCrudRespository.getByMobile(mobile);

		return investorProfileData;
	}*/

    @Override
    public UserProfile getCustomerProfileDetailsByMobile(String mobile) {
	// TODO Auto-generated method stub

	UserProfile userProfile = null;

	try{
	    if(bseCustomerCrudRespository.existsByMobileAndAccountActive(mobile,"Y")){


		//				BseMFInvestForm investorProfileData = bseCustomerCrudRespository.getByMobile(mobile);
		MFCustomers investorProfileData = bseCustomerCrudRespository.getByMobileAndAccountActive(mobile,"Y");

		userProfile = new UserProfile();
		userProfile.setUid(investorProfileData.getClientID());
		userProfile.setMobile(investorProfileData.getMobile());
		userProfile.setDob(investorProfileData.getInvDOB());
		userProfile.setMail(investorProfileData.getEmail());
		userProfile.setPan1(investorProfileData.getPan1());
		userProfile.setPan2(investorProfileData.getPan2());
		userProfile.setPan2Verified(investorProfileData.getPan2verified());
		userProfile.setPan1verified(investorProfileData.getPan1verified());
		userProfile.setFname(investorProfileData.getInvName());
		userProfile.setGender(investorProfileData.getGender());
		userProfile.setTaxStatus(InvestFormConstants.fatcaTaxStatusIndividual.get(investorProfileData.getTaxStatus()));
		userProfile.setHoldingMode(InvestFormConstants.holdingMode.get(investorProfileData.getHoldingMode()));
		userProfile.setPan1Verifiedkyc(investorProfileData.getPan1KycVerified());
		userProfile.setAccountHolder(investorProfileData.getInvName());

		userProfile.setAccountNumber(investorProfileData.getBankDetails().getAccountNumber());
		userProfile.setIfscCode(investorProfileData.getBankDetails().getIfscCode());
		userProfile.setBankName(investorProfileData.getBankDetails().getBankName());
		userProfile.setAccountType(InvestFormConstants.accountTypes.get(investorProfileData.getBankDetails().getAccountType()));
		userProfile.setBranch(investorProfileData.getBankDetails().getBankBranch());
		userProfile.setBranchCity(investorProfileData.getBankDetails().getBankCity());
		userProfile.setAccountState(investorProfileData.getBankDetails().getBranchState());

		userProfile.setHouseNumber(investorProfileData.getAddressDetails().getAddress1());
		userProfile.setAddress1(investorProfileData.getAddressDetails().getAddress2());
		userProfile.setAddress2(investorProfileData.getAddressDetails().getAddress3());
		//		userProfile.setAddress3(addressDetails[2]);
		userProfile.setCity(investorProfileData.getAddressDetails().getCity());
		userProfile.setState(InvestFormConstants.states.get(investorProfileData.getAddressDetails().getState()));
		userProfile.setPincode(investorProfileData.getAddressDetails().getPinCode());

		userProfile.setNomineeAvailable(investorProfileData.getNominee().getIsNominate());
		userProfile.setNomineeName(investorProfileData.getNominee().getNomineeName());
		userProfile.setNomineeRelation(investorProfileData.getNominee().getNomineeRelation());



	    }
	}catch(Exception e){
	    logger.error("DB to UserProfile entity mapping error",e);
	    userProfile = new UserProfile();
	}
	return userProfile;
    }

    @Override
    public boolean updateCustomerBankDetails(UserProfile investorBankData) {
	// TODO Auto-generated method stub
	boolean flag=true;
	UserBankDetails userBankDetails = new UserBankDetails();
	logger.info("Updating bank details for BSE customer ID- "+ investorBankData.getUid());
	userBankDetails.setClientID(investorBankData.getUid());

	userBankDetails.setIfscCode(investorBankData.getIfscCode());
	userBankDetails.setAccountNumber(investorBankData.getAccountNumber());
	userBankDetails.setBankBranch(investorBankData.getBranch());
	userBankDetails.setBankName(investorBankData.getBankName());
	userBankDetails.setBankCity(investorBankData.getBranchCity());
	userBankDetails.setAccountType(investorBankData.getAccountType());
	userBankDetails.setBranchState(investorBankData.getAccountState());

	bseCustomerBankDetailsCrudRespository.save(userBankDetails);
	return flag;
    }

    @Override
    public boolean updateCustomerAddress(UserProfile investorData) {
	boolean flag=true;
	AddressDetails investorAddress = new AddressDetails();

	logger.info("Updating Customer address for BSE customer ID- "+ investorData.getUid());

	investorAddress.setClientID(investorData.getUid());
	investorAddress.setAddress1(investorData.getHouseNumber());
	investorAddress.setAddress2(investorData.getAddress1());
	investorAddress.setAddress3(investorData.getAddress2());
	investorAddress.setCity(investorData.getCity());
	investorAddress.setState(investorData.getState());
	investorAddress.setPinCode(investorData.getPincode());

	bseCustomerAddressCrudRepository.save(investorAddress);
	return flag;
    }

    @Override
    public BseAllTransactionsView getFundDetailsForAdditionalPurchase(String portfolio, String schemeCode,String investType,
	    String mobileNumber) {
	String clientId= bseCustomerCrudRespository.getClientIdFromMobile(mobileNumber);

	BseAllTransactionsView selectedFolioTransDetails = null;
	selectedFolioTransDetails = bseTransactionsView.findOneByPortfoilioAndSchemeCodeAndClientIDAndInvestType(portfolio, schemeCode, clientId,investType);


	return selectedFolioTransDetails;
    }

    @Override
    public String getClientIdfromMobile(String mobile) {
    	String clientcode=null;
    	try {
    		clientcode= bseCustomerCrudRespository.getClientIdFromMobile(mobile);
    	}catch(Exception e) {
    		logger.error("Failed to get customer clientcode for mobile- "+ mobile);
    	}
    	return clientcode;
    }

    @Override
    public BseAllTransactionsView getFundDetailsForRedemption(String portfolio, String schemeCode,String investType,
	    String mobileNumber) {
	String clientId= bseCustomerCrudRespository.getClientIdFromMobile(mobileNumber);

	BseAllTransactionsView selectedFolioTransDetails =null;
	selectedFolioTransDetails = bseTransactionsView.findOneByPortfoilioAndSchemeCodeAndClientIDAndInvestType(portfolio, schemeCode, clientId,investType);

	return selectedFolioTransDetails;
    }


    @Override
    public MfAllInvestorValueByCategory getCamsFundsDetailsForRedeem(String channelPartnerCode, String mobile, String folioNumber) {

	//		CAMS funds details redemption
	//		MFCamsFolio folio = 

	//		for CAMS, need to map rta code to scheme code
	/*
		String schemeCode="";
		MFCamsFolio folio = null;
		List<BseFundsScheme> schemCodes = bseFundsExplorerRepository.findAllByRtaCode(camsProductCode);
		logger.info("Total schemecode found for CAMS RTA code- "+ schemCodes.size());
		for(int i =0;i<schemCodes.size();i++){
			if(!schemCodes.get(i).getSettlementType().equalsIgnoreCase("L1")){
				schemeCode = schemCodes.get(i).getSchemeCode();
				break;
			}
		}
		logger.info("Scheme code for redemption of the fund - "+schemeCode);

		folio = mfCamsFolioCrudRepository.findOneByFolioNumberAndRtaCode(folioNumber,camsProductCode);
		if(folio!=null){
			folio.setSchemeCode(schemeCode);
		}else{
			logger.info("getCamsFundsDetailsForRedeem(): found no records agaisnt folio in database. for folio: ."+ folioNumber);
		}
	 */

	MfAllInvestorValueByCategory folio=null;
	try {
	    //			String pan = bseCustomerCrudRespository.getCustomerPanNumberFromMobile(mobile);
	    String pan = bseCustomerCrudRespository.getCustomerPanNumberFromMobileAndActive(mobile,"Y");
	    logger.info("getCamsFundsDetailsForRedeem(): PAN of the logged in user requesting rdemption: "+ pan);
	    folio = bseCustomerMfRepository.findOneByPanAndChannelProductCodeAndRtaAgentAndFolioNumber(pan, channelPartnerCode, "CAMS", folioNumber);
	}catch(Exception e) {
	    logger.error("getCamsFundsDetailsForRedeem(): Failed to query database",e);
	}

	return folio;

    }

    @Override
    public MfAllInvestorValueByCategory getKarvyFundsDetailsForRedeem(String channelPartnerCode, String mobile, String folioNumber){

	logger.info("getKarvyFundsDetailsForRedeem(): Request received to fetch customer investment profile details for redeem with mobile number: "+ mobile);
	String schemeCode="";
	MfAllInvestorValueByCategory folio = null;
	/*List<BseFundsScheme> schemCodes = bseFundsExplorerRepository.findAllByRtaCode(karvyProductCode);
		logger.info("Total schemecode found for RTA code- "+ schemCodes.size());
		for(int i =0;i<schemCodes.size();i++){
			if(!schemCodes.get(i).getSettlementType().equalsIgnoreCase("L1")){
				schemeCode = schemCodes.get(i).getSchemeCode();
				break;
			}
		}*/

	/*
	 * try{ logger.
	 * info("getKarvyFundsDetailsForRedeem(): Searching Karvy folio details by [karvyProductCode:folioNumber:PAN] - "
	 * +schemeCode + ":"+folioNumber); folio =
	 * bseCustomerMfRepository.getOneByChannelProductCodeAndFolioNumber(
	 * karvyProductCode, folioNumber); }catch(Exception e){
	 * logger.error("getKarvyFundsDetailsForRedeem(): Error querying database. ",e);
	 * }
	 */

	try {
	    //			String pan = bseCustomerCrudRespository.getCustomerPanNumberFromMobile(mobile);
	    String pan = bseCustomerCrudRespository.getCustomerPanNumberFromMobileAndActive(mobile,"Y");
	    logger.info("getCamsFundsDetailsForRedeem(): PAN of the logged in user requesting rdemption: "+ pan);
	    folio = bseCustomerMfRepository.findOneByPanAndChannelProductCodeAndRtaAgentAndFolioNumber(pan, channelPartnerCode, "KARVY", folioNumber);
	}catch(Exception e) {
	    logger.error("getCamsFundsDetailsForRedeem(): Failed to query database",e);
	}

	return folio;
    }


    @Override
    public long getCurrentDayNextTransCount(Date date) {
	// TODO Auto-generated method stub
	BseDailyTransCounter b;
	SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
	ArrayList<BseDailyTransCounter> v;
	int i;
	String l="0";
	try{
	    l= bseTransCountCrudRepository.findOneByDate(d.parse(d.format(date)));
	    if(l!=null){
		i = bseTransCountCrudRepository.increaseCounterByOne(d.parse(d.format(date)));
		l = bseTransCountCrudRepository.findOneByDate(d.parse(d.format(date)));
	    }else{
		logger.info("Current day counter not yet set. Setting the value and returning");
		b = new BseDailyTransCounter();
		b.setDate(date);
		b.setDayCounter(1);
		bseTransCountCrudRepository.saveAndFlush(b);
		/* v = (ArrayList<BseDailyTransCounter>) bseTransCountCrudRepository.findAll();
			 for(int j=0;j<v.size();j++){
				 b= v.get(j);
			 }*/
		l= bseTransCountCrudRepository.findOneByDate(d.parse(d.format(date)));

	    }
	}catch(ParseException e){
	  logger.error("Error gettimng BSE transaction count..",e);
	}
	return Long.valueOf(l);
    }


    @Override
    public String investmentProfileStatus(String mobileNumber) {
	logger.info("Search for customer BSE ID");
	String flag = "F";
//	String aofUploadStatus[] = null;
	String[] aofUploadStatus;
//	List<String[]> aofUploadStatus = null;
	try{
	    if(bseCustomerCrudRespository.existsByMobileAndAccountActive(mobileNumber,"Y")){
		//				String registrationStatus = bseCustomerCrudRespository.getBseRegistrationStatus(mobileNumber);
		//				logger.info("Customer BSE registration status- "+ registrationStatus);
		//				if(registrationStatus.equalsIgnoreCase("Y")){
	    String registrationStatus=bseCustomerCrudRespository.getBseRegistrationAOFStatus(mobileNumber);
	    aofUploadStatus = registrationStatus.split(",");
	    
//		logger.info("Customer BSE registration status- "+ Arrays.asList(aofUploadStatus) + " ->" + aofUploadStatus[0]);
//		String[] status = aofUploadStatus.get(0);
		logger.info("Customer BSE registration status- "+ Arrays.asList(aofUploadStatus) + " ->" + aofUploadStatus[0]);
		/*if(aofUploadStatus.split(",")[0].equals("Y") && aofUploadStatus.split(",")[1].equals("Y")){
		    flag="PROFILE_READY";
		}else if(aofUploadStatus.split(",")[0].equals("Y") && aofUploadStatus.split(",")[1].equals("N")){
		    flag="AOF_PENDING";
		}*/
		
		
		if((aofUploadStatus[0]).equalsIgnoreCase("Y")) {
			if(aofUploadStatus[2].equals("1")) {
				if(( aofUploadStatus[4]).equalsIgnoreCase("Y")) {
					flag="PROFILE_READY";
				}else {
					flag="AOF_PENDING";
				}
			}else {
				flag="FATCA_AOF_PENDING";
			}
		}else{
			flag="REGISTRATION_INCOMPLETE";
		}
		
	    }else{
	    	flag="NOT_FOUND";
	    }
	}catch(Exception e){
	    logger.error("Failed to query database to get customer AOF upload status", e);
	    flag="ERROR";
	}
	logger.info("Returning customer status- "+ flag);
	return flag;
    }

    @Override
    public String upddateCustomerFormSignature(String mobile, String pan, String signatureData1, String signatureData2) {
	logger.info("Processing to update customer signature for BSE profile- "+ mobile);
	int result = 0;
	String flag="SUCCESS";
	try{
	    if(bseCustomerCrudRespository.existsByMobileAndAccountActive(mobile,"Y")){
		String clientId = bseCustomerCrudRespository.getClientIdFromMobile(mobile);
		result = bseCustomerCrudRespository.uploadCustomerSignature(clientId, pan, signatureData1, signatureData2);
		logger.info("Status for signature update- "+ result);
	    }else{
		logger.info("Cusotmer not found to update signature- "+ mobile);
		flag="NO_CUSTOMER";
	    }
	}catch(Exception e){
	    logger.error("Failed to update customer signature data. ",e);
	    flag="INERNAL_ERROR";
	}
	return flag;
    }



    @Override
    public String uploadAOFFormStatus(String mobileNumber, String uploadStatus) {

	logger.info("uploadAOFFormStatus(): Begining process to upload AOF Form status to DB for mobile- ."+ mobileNumber + " : "+ uploadStatus);
	String currentStatus = "SUCCESS";
	String success = uploadStatus.split(";")[0];
	String message = uploadStatus.split(";")[1];

	try{
	    //	   int i = bseCustomerCrudRespository.updateAofUploadStatus(mobileNumber);
	    int i = bseCustomerCrudRespository.updateAofUploadStatus(mobileNumber,success,message);
	    if(i==0){
		currentStatus ="FAIL";
	    }
	}catch(Exception e){
	    logger.error("Failed to update cusotmer AOF upload status to database", e);
	    currentStatus="ERROR";
	}
	return currentStatus;
    }

    @Override
    public List<BsemfTransactionHistory> getAllPurchaseHistory(String clientId) {
    	logger.info("Get purchase history from DB");
    	List<BsemfTransactionHistory> getAllOrders = null;

    	try{
    		//				if(bseCustomerCrudRespository.existsByMobile(mobileNumber)){
    		//				getAllOrders=bseOrderEntryResponseRepository.findAllByClientCode(clientId);
    		getAllOrders = bseTransHistoryViewCrudRepository.findAllByClienId(clientId);
    		logger.info("Total purchase history found for custmer- "+ clientId + " : "+ getAllOrders.size());

    	}catch(Exception e){
    		logger.error("Failed to query database to get customer AOF upload status and upload", e);
    	}
    	return getAllOrders;
    }

    @Override
    public List<BseMFTop15lsSip> getTopFunds() {

    	return bseTop15lsSipViewCrudReositry.findAll();
    }

    @Override
    public UserBankDetails getCustomerBankDetails(String clientCode) {

    	UserBankDetails bank = null;
    	try{
    		bank= bseCustomerBankDetailsCrudRespository.findOneByClientID(clientCode);
    	}catch(Exception e){
    		logger.error("Failed to query database to fetch customer bank details",e);
    	}

    	return bank;
    }

    @Override
    public BseApiResponse updateEmdandateStatus(String mobileNumber,String mandateType, String amount) {
	logger.info("Emandate update process begin for customer- "+ mobileNumber + " : amount- "+ amount);

	BseApiResponse apiresponse =null;
	try{
	    String clientCode = bseCustomerCrudRespository.getClientIdFromMobile(mobileNumber);
	    UserBankDetails bankDetails = bseCustomerBankDetailsCrudRespository.findOneByClientID(clientCode);
	    Date startDate= new Date();
	    //				Setting registration for 10 years
	    Date endDate  = new Date();
	    Calendar c = Calendar.getInstance();
	    c.setTime(endDate);
	    c.add(Calendar.YEAR, 10);
	    endDate.setTime(c.getTimeInMillis());

	    apiresponse = investmentConnectorBseInterface.emandateRegistration(bankDetails,mandateType, amount,clientCode, startDate,endDate);
	    if(apiresponse.getStatusCode().equals("100")){
		logger.info("Emandate completed for customer successfully for cusotmner-" + mobileNumber + ": Response code: "+ apiresponse.getStatusCode() + " : "+ apiresponse.getRemarks());
		logger.info("Update bank emandate status to database...");
		//				SimpleDateFormat dbFormat = new SimpleDateFormat("yyyy-MM-dd");
		//					SimpleDateFormat bseFormat = new SimpleDateFormat("dd/MM/yyyy");
		/*bseCustomerBankDetailsCrudRespository.updateEmandateStatus(clientCode, 
						bankDetails.getAccountNumber(), true, 
						dbFormat.parse(dbFormat.format(startDate)), 
						dbFormat.parse(dbFormat.format(endDate)), 
						apiresponse.getResponseCode(), 
						dbFormat.parse(dbFormat.format(new Date()))
						);*/
		//				bseMandateCrudRepository.updateMandateRegisterResponse(clientCode, "", mandateId, reponseMsg)
		BseMandateDetails mandate = new BseMandateDetails();
		mandate.setClientCode(clientCode);
		mandate.setAccountNumber(bankDetails.getAccountNumber());
		mandate.setSipStartDate(startDate);
		mandate.setSipEndDate(endDate);
		mandate.setMandateType(mandateType);
		mandate.setMandateId(apiresponse.getResponseCode());
		mandate.setAmount("50000");
		mandate.setIfscCode(bankDetails.getIfscCode());
		mandate.setCreationDate(startDate);
		mandate.setMandateComplete(true);

		bseMandateCrudRepository.save(mandate);

	    }else{
		logger.info("Failed to update e-mandate. Reason: "+ apiresponse.getStatusCode() + " : "+ apiresponse.getRemarks());
	    }

	}catch(Exception e){
	    logger.error("updateEmdandateStatus(): error while trying to perform e-mandate registration",e);
	}

	return apiresponse;
    }

    @Override
    public MFCustomers getCustomerInvestFormData(String mobile) {
    	logger.info("getCustomerInvestFormData(): Querying to get registered investment form data for mobile- "+ mobile);
    	MFCustomers investmentFormdata = null;
    	try{
    		if(bseCustomerCrudRespository.existsByMobileAndAccountActive(mobile,"Y")){
    			String clientId = bseCustomerCrudRespository.getClientIdFromMobile(mobile);
    			investmentFormdata = bseCustomerCrudRespository.findOneByClientID(clientId);
    			AddressDetails address = bseCustomerAddressCrudRepository.findOneByClientID(clientId);
    			UserBankDetails bank = bseCustomerBankDetailsCrudRespository.getOne(clientId);
    			MFNominationForm nominee = bseCustomerNomineeCrudRepository.findOneByClientID(clientId);
    			//				System.out.println("Address- "+ address + " : Bank: "+ bank);
    			investmentFormdata.setAddressDetails(address!=null?address:new AddressDetails());
    			investmentFormdata.setBankDetails(bank!=null?bank:new UserBankDetails());
    			investmentFormdata.setNominee(nominee!=null?nominee:new MFNominationForm());
    			
    			try {
    				logger.info("getCustomerInvestFormData() : Investor DOB: " + investmentFormdata.getCustomerdob());
    				investmentFormdata.setCustomerdob(simpleDateFormat2.format(simpleDateFormat1.parse(investmentFormdata.getInvDOB())));
    				logger.info("getCustomerInvestFormData(): Investor DOB is in desired format. Proceed");

    			} catch (Exception e) {
    				logger.error("getCustomerInvestFormData(): Error converting date",e);
    			}
    			
    		}else{
    			logger.info("Cusotmer invest form data not found!");
    		}
    	}catch(Exception e){
    		logger.error("Failed to query database to fetch customer details",e);
    	}

    	return investmentFormdata;
    }

    @Override
    public List<BseMandateDetails> getCustomerMandateDetails(String clientId, String accountNumber) {
	logger.info("Querying to get bank mandate details for client mobile- "+ clientId);
	List<BseMandateDetails> mandateDetails = null;
	try{

//	    mandateDetails = bseMandateCrudRepository.findAllByClientCodeAndAccountNumber(clientId, accountNumber);	
	    mandateDetails = bseMandateCrudRepository.findAllByClientCodeAndAccountNumberAndMandateTypeAndMandateActive(clientId,accountNumber,"I","Y");
	}catch(Exception e){
	    logger.error("Failed to query database to fetch mandate details",e);
	}

	return mandateDetails;
    }

    @Override
    public Page<BseFundsScheme> getpaginatedFundsList(Pageable p) {
	logger.info("getpaginatedFundsList(): Querying to schemes by page wise- ");
	Page<BseFundsScheme> fundDetails = null;
	try{

	    fundDetails = bseFundsExplorerRepository.findAll(p);

	}catch(Exception e){
	    logger.error("Failed to query database to fetch mandate details",e);
	}

	return fundDetails;
    }

    /*
    @Override
    @Cacheable(value = "mutualfundexplorerdata", unless = "#result == null")
    public List<BseMFSelectedFunds> getAllSelectedFunds() {
	logger.info("getAllSelectedFunds(): get all selected funds- ");

	List<BseMFSelectedFunds> fundDetails = null;
	try{

	    fundDetails = bseSelectedCategoryFundsRepository.getAllByTopFundsView("Y");

	}catch(Exception e){
	    logger.error("Failed to query database to fetch sellected category funds",e);
	}

	return fundDetails;
    }
    */
    

    @Override
    public List<BseMFSelectedFunds> getFundsByCategory(String category) {
	// TODO Auto-generated method stub
	return null;
    }


    @Override
    public BseMFSelectedFunds getFundsByCode(String rtacode, String isin) {
	logger.info("Querying to get BSE Fund details by RTA code from view for  "+ rtacode + " : "+isin );
	BseMFSelectedFunds fundDetails = null;
	try{
	    if(isin == null){
		fundDetails = bseSelectedCategoryFundsRepository.findOneByRtaCode(rtacode);
	    }else{
		fundDetails = bseSelectedCategoryFundsRepository.findOneByRtaCodeAndIsin(rtacode,isin);
	    }

	}catch(Exception e){
	    logger.error("Failed to query database to fetch selected fund details by RTA code",e);
	}

	return fundDetails;
    }

    @Override
    public BseApiResponse saveFatcaDetails(MFCustomers customerForm,String flag1, String dateformat,String clientcode,ClientSystemDetails systemdetails) {
    	//					Call FATCADeclaration

    	BseApiResponse fatcaResponse= new BseApiResponse();;
//    	String clientId=null;
    	boolean transactionfailed=false;
    	int res=0;

    	logger.info("Request received to process FATCA details for customer- "+ customerForm.getPan1() +  " BSE registration status-  "+ flag1);
    	try{

    		if(bseCustomerCrudRespository.existsByMobileAndAccountActive(customerForm.getMobile(),"Y")){
    			
    			if(flag1==null) {
    				logger.info("saveFatcaDetails() : Query DB to get BSERegiistration status");
    				flag1 = bseCustomerCrudRespository.getBseRegistrationStatusByMobile(customerForm.getMobile(), "Y");
    			}
    			if(flag1.equalsIgnoreCase("Y")) {
    				if(clientcode ==null) {
    					logger.info("FATCA module did not receive clientcode. Looking for clientcode for mobile -"+ customerForm.getMobile());
    					clientcode = bseCustomerCrudRespository.getClientIdFromMobile(customerForm.getMobile());
    				}
    				customerForm.setClientID(clientcode);
    				
    				/*
    				if(customerForm.getFatcaDetails().getResidenceCountry1()==null || customerForm.getFatcaDetails().getResidenceCountry1().isEmpty()) {
    					customerForm.getFatcaDetails().setResidenceCountry1(customerForm.getFatcaDetails().getCountryOfBirth());
    				}
    				*/
    				
    				MFFatcaDeclareForm fatcadetails = bseCustomerFATCACrudRepository.findByClientID(clientcode);
    				
    				if(fatcadetails==null) {
    					fatcadetails = new MFFatcaDeclareForm();
    					fatcadetails.setClientID(clientcode);
    					fatcadetails.setFatcaUploaded(false);
    				}
    				
    				updatefatcafield(customerForm,fatcadetails,"yyyy-mm-dd",systemdetails);
    				customerForm.setFatcaDetails(fatcadetails);
    				
    				logger.info("FATCA upload status for customer- "+ customerForm.getMobile() + " -> "+ clientcode + " ->"+ (fatcadetails!=null?fatcadetails.isFatcaUploaded():"NULL") );
    				if(fatcadetails!=null) {
    				if(!fatcadetails.isFatcaUploaded()) {
    					logger.info("Proceeding with FATCA upload.");

    					fatcaResponse = investmentConnectorBseInterface.fatcaDeclaration(customerForm, dateformat);
    					logger.info("FATCA upload status from BSE for customer- "+clientcode + " : "  + fatcaResponse.getResponseCode());

    					logger.info("Updating FATCA status to database for customer- "+ clientcode);
    					if(fatcaResponse.getResponseCode().equalsIgnoreCase("100")){
    						
    						fatcadetails.setFatcaUploaded(true);
    						fatcadetails.setUploadResponse(fatcaResponse.getRemarks());
    						
//    						res= bseCustomerFATCACrudRepository.updateFatcaDeclarationStatus(true,fatcaResponse.getRemarks(), clientcode);
//    						logger.info("Returned FATCA status update to database for customer- "+ clientcode + " : "+res);
    					} else {
    						fatcadetails.setFatcaUploaded(false);
    						fatcadetails.setUploadResponse(fatcaResponse.getRemarks());
//    						res= bseCustomerFATCACrudRepository.updateFatcaDeclarationStatus(false,fatcaResponse.getRemarks(), clientcode);
//    						logger.info("Returned FATCA status update to database for customer- "+ clientcode + " : "+res);
    						transactionfailed=true;
    					}
    					
    					bseCustomerFATCACrudRepository.save(fatcadetails);
						logger.info("FATCA form saved with status FATCA DECLARED?-> "+ fatcadetails.isFatcaUploaded());
    					
    				}else {
    					logger.info("Skipping FATCA upload");
    					fatcaResponse.setResponseCode("100");
    					fatcaResponse.setRemarks("Fatca already uploaded. Duplicate request skipped for clientcode- "+ clientcode);
    				}
    				}else {
    					logger.info("Rewcord not found in DB to proceed!");
    					fatcaResponse.setResponseCode("101");
    					fatcaResponse.setRemarks("Fatca already uploaded. Duplicate request skipped for clientcode- "+ clientcode);
    				}
    			}else {
    				logger.info("BSE REGISTRATION is not complete. Skipping fatca upload for customer- "+ customerForm.getMobile());
    				fatcaResponse.setResponseCode("101");
    				fatcaResponse.setRemarks("BSE customer registration not yet complete. Skipping FATCA upload request");
    			}
    		}else {
    			logger.info("BSE registered account not found"+ customerForm.getMobile());
    			fatcaResponse.setResponseCode("101");
    			fatcaResponse.setRemarks("Customer not yet registered in portal. Skipping FATCA upload");
    		}
    		if(transactionfailed) {
    			logger.info("BSE FATCA upload failed... Notify admin by email");
    			mailSenderInterface.notifyTransactionErrorToAdmin(null, fatcaResponse.getRemarks(), null, "Mutual Fund Transaction", "FATCA Registraion failed", customerForm.getMobile(), customerForm.getInvName());
    		}

    	}catch(Exception e){
    		logger.error("Failed to update FATCA status to database for customer- "+clientcode ,e);
    		mailSenderInterface.notifyTransactionErrorToAdmin(null, fatcaResponse.getRemarks()+ " ->"+ e.getLocalizedMessage(), null, "Mutual Fund Transaction", "Exception caught during FATCA request process", customerForm.getMobile(), customerForm.getInvName());
    		fatcaResponse.setResponseCode("101");
    		fatcaResponse.setRemarks("Internal error. Admin notified.");
    	}
    	fatcaResponse.setStatusCode(Integer.toString(res));

    	return fatcaResponse;
    }

    @Override
    public String updateFatcaStatus(String clientId, String status, String responseCode, String message) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String getEmdandateDetails(String mobile, String clientCode, String mandateType, String accNumber) {

	BseMandateDetails mandateId=null;
	String clientId=null;
	logger.info("Request received to fetch customer emandate registration details for client ID- "+ clientCode + " : mandate type: "+ mandateType);
	try{
	    if(bseCustomerCrudRespository.existsByMobileAndAccountActive(mobile,"Y")){
		UserBankDetails userbankDetails = getCustomerBankDetails(clientCode);

		logger.info("Look for mandate corresponding to registered bank- ");

		mandateId= bseMandateCrudRepository.findAllByClientCodeAndAccountNumberAndMandateTypeAndMandateActive(clientCode, userbankDetails.getAccountNumber(),"I","Y").get(0);
		logger.info("Mandate ID receieved for "+clientId + " : mandate type:  " +mandateType +" : "+ mandateId);
	    }
	}catch(Exception e){
	    logger.error("Failed to query database to fetch exisint mandates. ",e);
	}

	return mandateId.getMandateId();
    }

    @Override
    public List<MFCamsFolio> getCamsPortfolio(String mobile, String pan) {

	List<MFCamsFolio> folios=null;
	logger.info("Request received to fetch customer cams folio details for client ID- "+ mobile + " : mandate type: "+ pan);
	try{
	    if(bseCustomerCrudRespository.existsByMobileAndAccountActive(mobile,"Y")){
		//			pan = bseCustomerCrudRespository.getCustomerPanNumberFromMobile(mobile);
		pan = bseCustomerCrudRespository.getCustomerPanNumberFromMobileAndActive(mobile,"Y");

		folios = mfCamsFolioCrudRepository.findAllByPan(pan);

		logger.info("Folio details look up complete");

	    }
	}catch(Exception e){
	    logger.error("Failed to query database to fetch exisint mandates. ",e);
	}

	return folios;
    }

    @Override
    public BsemfTransactionHistory getOrderDetailsForCancel(String orderNo, String schemeCode, String investType,
	    String mobileNumber,String category,String transactionId) {
	logger.info("Get order details for cancel from DB for transaction ID: " + transactionId);
	BsemfTransactionHistory getOrderDetails = null;

	try{
	    //				if(bseCustomerCrudRespository.existsByMobile(mobileNumber)){
	    //				getAllOrders=bseOrderEntryResponseRepository.findAllByClientCode(clientId);
	    String clientId = bseCustomerCrudRespository.getClientIdFromMobile(mobileNumber);
	    getOrderDetails = bseTransHistoryViewCrudRepository.findOneByClienIdAndTransctionTypeAndTransactionId(clientId,category, transactionId);
	    logger.info("Total purchase history found for custmer- "+ clientId + " : "+ orderNo);

	}catch(Exception e){
	    logger.error("Failed to query database to get customer transaction details for the order number", e);
	}
	return getOrderDetails;
    }

    @Override
    public SelectMFFund getTransactionDetails(String transactionId, String clientId) {
	logger.info("Fetching transaction details for ID from DB: "+ transactionId);

	SelectMFFund fund = null;
	try{
	    fund = bseTransCrudRepository.findOneByTransactionIDAndClientID(transactionId, clientId);
	}catch(Exception e){
	    logger.error("Failed to fetch particular fund details: ",e);
	}
	return fund;
    }

    @Override
    public List<MFCamsValueByCategroy> getCustomersCamsInvByCategory(String mobile, String pan) {

	List<MFCamsValueByCategroy> folios=null;
	logger.info("Request received to fetch customer cams folio details by category for client ID- "+ mobile + " :PAN NO: "+ pan);
	try{
	    if(bseCustomerCrudRespository.existsByMobileAndAccountActive(mobile,"Y")){
		//			pan = bseCustomerCrudRespository.getCustomerPanNumberFromMobile(mobile);
		pan = bseCustomerCrudRespository.getCustomerPanNumberFromMobileAndActive(mobile,"Y");

		folios = bseCamsByCategoryRepository.findAllByPan(pan);

		logger.info("Folio details by category look up complete");

	    }
	}catch(Exception e){
	    logger.error("Failed to query database to fetch exising customer. ",e);
	}

	return folios;
    }

    @Override
    //	@Cacheable(value = "mffundhistory", unless = "#result == null", key= "#folios")
    public List<MfAllInvestorValueByCategory> getCustomersAllFoliosByCategory(String mobile, String pan) {
	List<MfAllInvestorValueByCategory> folios=null;
	logger.info("getCustomersAllFoliosByCategory(): Request received to fetch customer Karvy folio details by category for client ID- "+ mobile + " :PAN NO: "+ pan);

	try{
	    if(bseCustomerCrudRespository.existsByMobileAndAccountActive(mobile,"Y")){
		//			pan = bseCustomerCrudRespository.getCustomerPanNumberFromMobile(mobile);
		pan = bseCustomerCrudRespository.getCustomerPanNumberFromMobileAndActive(mobile,"Y");

		folios =bseCustomerMfRepository.getAllByPan(pan);

		logger.info("Karvy Folio details by category look up complete. Total- "+ folios.size());

	    }
	}catch(Exception e){
	    logger.error("Failed to query database to fetch exising customer karvy folio. ",e);
	}

	return folios;
    }

    @Override
    public List<MFKarvyValueByCategory2> getCustomersKarvyInvByCategory2(String mobile, String pan) {
	List<MFKarvyValueByCategory2> folios=null;
	logger.info("getCustomersKarvyInvByCategory2(): Request received to fetch customer Karvy folio details by category from UNION view for client ID- "+ mobile + " :PAN NO: "+ pan);

	try{
	    if(bseCustomerCrudRespository.existsByMobileAndAccountActive(mobile,"Y")){
		//			pan = bseCustomerCrudRespository.getCustomerPanNumberFromMobile(mobile);
		pan = bseCustomerCrudRespository.getCustomerPanNumberFromMobileAndActive(mobile,"Y");

		folios =bseKarvyByCategoryRepository2.getAllByPan(pan);

		logger.info("getCustomersKarvyInvByCategory2(): Karvy Folio details by category look up complete");

	    }
	}catch(Exception e){
	    logger.error("getCustomersKarvyInvByCategory2(): Failed to query database to fetch exising customer karvy folio. ",e);
	}

	return folios;
    }


    /**
     * @param customerForm
     * @param toupdateForm
     * @return
     */
    private MFCustomers mapUpdatedCustomerMfData(MFCustomers customerForm,MFCustomers toupdateForm, ClientSystemDetails systemdetails){
	SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-mm-dd");
	SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("dd/mm/yyyy");

	//		Basic details

	toupdateForm.setProfileuniqueid(customerForm.getProfileuniqueid());
	
	toupdateForm.setFname(customerForm.getFname());
	toupdateForm.setMname(customerForm.getMname());
	toupdateForm.setLname(customerForm.getLname());
	
	toupdateForm.setPan1(customerForm.getPan1());
	toupdateForm.setPan2(customerForm.getPan2());
	toupdateForm.setInvName(customerForm.getInvName());
	toupdateForm.setApplicant2(customerForm.getApplicant2());
	//		toupdateForm.setInvDOB(customerForm);

	try {
//	    Date date1 = simpleDateFormat2.parse(customerForm.getInvDOB());
		Date date1 = simpleDateFormat2.parse(customerForm.getCustomerdob());
	    String bseFormatDob = simpleDateFormat1.format(date1);
	    logger.info("DOB field converted to DB format for record update- "+ bseFormatDob);
	    toupdateForm.setInvDOB(bseFormatDob);
	} catch (ParseException e) {
	    logger.error("mapUpdatedCustomerMfData(): failed to convert date. Leaving date to default format. ",e);
	    toupdateForm.setInvDOB(customerForm.getInvDOB());

	}

	toupdateForm.setGender(customerForm.getGender());
	toupdateForm.setAadhaar(customerForm.getAadhaar());
	toupdateForm.setHoldingMode(customerForm.getHoldingMode());
	toupdateForm.setEmail(customerForm.getEmail());
	//		toupdateForm.setMobile(customerForm.getMobile());
	toupdateForm.setDividendPayMode(customerForm.getDividendPayMode());
	toupdateForm.setDeclaration(customerForm.getDeclaration());
	toupdateForm.setOccupation(customerForm.getOccupation());
	toupdateForm.setTaxStatus(customerForm.getTaxStatus());
	//		toupdateForm.setPlaceOfBirth(customerForm.getPlaceOfBirth());
	//		toupdateForm.setFatherOrSpouse(customerForm.getFatherOrSpouse());
	toupdateForm.setCustomerSignature1(customerForm.getCustomerSignature1());
	
	
	toupdateForm.getNominee().setNomineeName(customerForm.getNominee().getNomineeName());
	toupdateForm.getNominee().setNomineeRelation(customerForm.getNominee().getNomineeRelation());
	toupdateForm.getNominee().setIsNomineeMinor(customerForm.getNominee().getIsNomineeMinor());
	toupdateForm.getNominee().setNomineeGuardian(customerForm.getNominee().getNomineeGuardian());
	toupdateForm.getNominee().setNomineeDOB(customerForm.getNominee().getNomineeDOB());

	toupdateForm.getBankDetails().setAccountNumber(customerForm.getBankDetails().getAccountNumber());
	toupdateForm.getBankDetails().setAccountType(customerForm.getBankDetails().getAccountType());
	toupdateForm.getBankDetails().setIfscCode(customerForm.getBankDetails().getIfscCode());
	toupdateForm.getBankDetails().setBankName(customerForm.getBankDetails().getBankName());
	toupdateForm.getBankDetails().setBankBranch(customerForm.getBankDetails().getBankBranch());
	toupdateForm.getBankDetails().setBankAddress(customerForm.getBankDetails().getBankAddress());
	toupdateForm.getBankDetails().setBankCity(customerForm.getBankDetails().getBankCity());
	toupdateForm.getBankDetails().setBranchState(customerForm.getBankDetails().getBranchState());

	toupdateForm.getAddressDetails().setAddress1(customerForm.getAddressDetails().getAddress1());
	toupdateForm.getAddressDetails().setAddress2(customerForm.getAddressDetails().getAddress2());
	toupdateForm.getAddressDetails().setAddress3(customerForm.getAddressDetails().getAddress3());
	toupdateForm.getAddressDetails().setCity(customerForm.getAddressDetails().getCity());
	toupdateForm.getAddressDetails().setPinCode(customerForm.getAddressDetails().getPinCode());
	toupdateForm.getAddressDetails().setState(customerForm.getAddressDetails().getState());
	toupdateForm.getFatcaDetails().setAddressType(customerForm.getFatcaDetails().getAddressType());


	//		toupdateForm.setPanValidationStatus(customerForm);

	//		toupdateForm.setInvestmentType(customerForm.getInvestmentType());
	//		toupdateForm.setAadhaarVerifyStatusCode(customerForm);
	//		toupdateForm.setKycType(customerForm.getKycType());
	//		toupdateForm.setAddressDetails(customerForm);
	//		toupdateForm.setRegistrationTime(customerForm.getRegistrationTime());
	//		toupdateForm.setFatcaDetails(customerForm);
	//		toupdateForm.setAccountActive(customerForm);
	//		toupdateForm.setPan1verified(customerForm);
	//		toupdateForm.setPan2verified(customerForm);
	//		toupdateForm.setPan1KycVerified(customerForm);
	

//	updatecommonfield(toupdateForm,null);
	toupdateForm.setSystemip(systemdetails.getClientIpv4Address());
	toupdateForm.setSystemDetails(systemdetails.getClientBrowser());
	
	
//	toupdateForm.setFatcaDetails(updatefatcafield(customerForm, customerForm.getFatcaDetails(), "yyyy-mm-dd",systemdetails));
	
	
//	FATCA DECLARATION

	String dateformat="yyyy-mm-dd";
	//	SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-mm-dd");
	//	SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(dateformat!=null?dateformat:"dd/mm/yyyy");
//	SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat(dateformat!=null?dateformat:"mm/dd/yyyy");
	
	/*
	List<String> occupationservicelist = new ArrayList<String>();
	occupationservicelist.add("02");
	occupationservicelist.add("03");
	occupationservicelist.add("04");
	occupationservicelist.add("09");
	occupationservicelist.add("41");
	occupationservicelist.add("42");
	occupationservicelist.add("44");
	*/
	
	toupdateForm.getFatcaDetails().setIncomeSlab(customerForm.getFatcaDetails().getIncomeSlab());
	toupdateForm.getFatcaDetails().setPlaceOfBirth(customerForm.getFatcaDetails().getPlaceOfBirth());
	toupdateForm.getFatcaDetails().setWealthSource(customerForm.getFatcaDetails().getWealthSource());
	toupdateForm.getFatcaDetails().setPoliticalExposedPerson(customerForm.getFatcaDetails().getPoliticalExposedPerson());
	toupdateForm.getFatcaDetails().setFatherName(customerForm.getFatcaDetails().getFatherName());
	toupdateForm.getFatcaDetails().setSpouseName(customerForm.getFatcaDetails().getSpouseName());
	toupdateForm.getFatcaDetails().setCountryOfBirth(customerForm.getFatcaDetails().getCountryOfBirth());
	
	toupdateForm.getFatcaDetails().setTaxStatus(customerForm.getTaxStatus());
	if(customerForm.getOccupation().equals("01") || customerForm.getOccupation().equals("43")) {
		toupdateForm.getFatcaDetails().setOccupationType("B"); // Business
//	}else if(occupationservicelist.contains(customerForm.getOccupation())) {
	}else if(InvestFormConstants.occupationservicelist.contains(customerForm.getOccupation())) {
		toupdateForm.getFatcaDetails().setOccupationType("S"); // Service
	}else {
		toupdateForm.getFatcaDetails().setOccupationType("O"); // Others
	}
	toupdateForm.getFatcaDetails().setOccupationCode(customerForm.getOccupation());
	toupdateForm.getFatcaDetails().setUboApplicable("N");
	toupdateForm.getFatcaDetails().setUbodf("N"); 	// For individual
	
	toupdateForm.getFatcaDetails().setUscanadaCitizen(customerForm.getFatcaDetails().isUsCitizenshipCheck()?"Y":"N");
	
	toupdateForm.getFatcaDetails().setNetWordth(null);
		/*
		if(mfcustomer.getFatcaDetails().getDateOfNetworth()!=null){
		String bseFormatDob = simpleDateFormat3.format(new Date());
	}
		*/
	toupdateForm.getFatcaDetails().setLogName(customerForm.getPan1());
	toupdateForm.getFatcaDetails().setResidenceCountry1(customerForm.getFatcaDetails().getResidenceCountry1()!=null?customerForm.getFatcaDetails().getResidenceCountry1():"IN");
	
	toupdateForm.getFatcaDetails().setIdentificationDocType("C");
	toupdateForm.getFatcaDetails().setDaclarationDate(new Date());
	toupdateForm.getFatcaDetails().setCreatedBy("SELF REGISTRATION");

	toupdateForm.getFatcaDetails().setSystemip(systemdetails.getClientIpv4Address());
	toupdateForm.getFatcaDetails().setSystemDetails(systemdetails.getClientBrowser());
	toupdateForm.getFatcaDetails().setUscanadaCitizen(customerForm.getFatcaDetails().isUsCitizenshipCheck() ? "Y" : "N");
	
	
	
	toupdateForm.setLastModifiedDate(new Date());
	return toupdateForm;
    }
    
    
   
    
    
    
    private MFFatcaDeclareForm updatefatcafield(MFCustomers mfcustomer, MFFatcaDeclareForm mffatca, String dateformat, ClientSystemDetails systemdetails) {
    	
//    	FATCA DECLARATION
    	SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-mm-dd");
		//	SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(dateformat!=null?dateformat:"dd/mm/yyyy");
		SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat(dateformat!=null?dateformat:"mm/dd/yyyy");
    	
		/*
    	List<String> occupationservicelist = new ArrayList<String>();
    	occupationservicelist.add("02");
    	occupationservicelist.add("03");
    	occupationservicelist.add("04");
    	occupationservicelist.add("09");
    	occupationservicelist.add("41");
    	occupationservicelist.add("42");
    	occupationservicelist.add("44");
    	*/
    	
    	
    	if(mffatca == null) {
    		logger.info("FATCA entry is not found in DB. Create new instance");
    		mffatca = new MFFatcaDeclareForm();
    		mffatca.setClientID(mffatca.getClientID());
    	}
    	
    	mffatca.setIncomeSlab(mfcustomer.getFatcaDetails().getIncomeSlab());
    	mffatca.setPlaceOfBirth(mfcustomer.getFatcaDetails().getPlaceOfBirth());
    	mffatca.setWealthSource(mfcustomer.getFatcaDetails().getWealthSource());
    	mffatca.setPoliticalExposedPerson(mfcustomer.getFatcaDetails().getPoliticalExposedPerson());
    	mffatca.setFatherName(mfcustomer.getFatcaDetails().getFatherName());
    	mffatca.setSpouseName(mfcustomer.getFatcaDetails().getSpouseName());
    	mffatca.setCountryOfBirth(mfcustomer.getFatcaDetails().getCountryOfBirth());
    	
    	mffatca.setTaxStatus(mfcustomer.getTaxStatus());
    	if(mfcustomer.getOccupation().equals("01") || mfcustomer.getOccupation().equals("43")) {
//    		fatcaForm.setOCC_TYPE("B");
    		mffatca.setOccupationType("B"); // Business
//    	}else if(occupationservicelist.contains(mfcustomer.getOccupation())) {
    	}else if(InvestFormConstants.occupationservicelist.contains(mfcustomer.getOccupation())) {
//    		fatcaForm.setOCC_TYPE("S");
    		mffatca.setOccupationType("S"); // Service
    	}else {
//    		fatcaForm.setOCC_TYPE("O");
    		mffatca.setOccupationType("O"); // Others
    	}
    	mffatca.setOccupationCode(mfcustomer.getOccupation());
    	mffatca.setUboApplicable("N");
    	mffatca.setUbodf("N"); 	// For individual
    	
    	mffatca.setUscanadaCitizen(mfcustomer.getFatcaDetails().isUsCitizenshipCheck()?"Y":"N");
    	
    	mffatca.setNetWordth(null);
  		/*
  		if(mfcustomer.getFatcaDetails().getDateOfNetworth()!=null){
			String bseFormatDob = simpleDateFormat3.format(new Date());
		}
  		*/
    	mffatca.setLogName(mfcustomer.getPan1());
  		mffatca.setResidenceCountry1(mfcustomer.getFatcaDetails().getResidenceCountry1()!=null?mfcustomer.getFatcaDetails().getResidenceCountry1():"IN");
    	
    	mffatca.setIdentificationDocType("C");
    	mffatca.setDaclarationDate(new Date());
    	mffatca.setCreatedBy("SELF REGISTRATION");

    	mffatca.setSystemip(systemdetails.getClientIpv4Address());
    	mffatca.setSystemDetails(systemdetails.getClientBrowser());
    	mffatca.setUscanadaCitizen(mfcustomer.getFatcaDetails().isUsCitizenshipCheck() ? "Y" : "N");
    	
    	return mffatca;
    }
    
    @Override
    @Cacheable(value = "isinnavhistory", unless = "#result == null", key = "#isin")
    public List<MfNavData> getnavdataByISIN(String isin) {
	logger.info("Query database to fetch last 5 years NAV data for ISISN- "+ isin);
	List<MfNavData> navdata = new ArrayList<MfNavData>();
	Date today = Calendar.getInstance().getTime();

	Calendar previousTime  = Calendar.getInstance();
	previousTime.add(Calendar.YEAR, -5);
	Date fiveYearsEralier = previousTime.getTime();

	logger.debug("Fetching NAV data between : "+ fiveYearsEralier + " -> " + today);

	try{
	    navdata= mfNavDataCrudRepository.get5YearsNavData(isin,fiveYearsEralier,today);
	    logger.info("Total NAV data - "+ navdata.size());

	}catch(Exception e){
	    logger.info("Failed to fecth NAV hsitory.",e);
	}

	return navdata;
    }

    @Override
    public String getCustomerInvestmentValue(String mobile, String pan) {
	String investmentresult = null;
	logger.info("getCustomerInvestmentValue(): Get customer invest value for mobile- "+ pan);

	try {
	    investmentresult=  bseCustomerMfRepository.getCustomerMFInvestmentAmount(pan);
	    logger.info("getCustomerInvestmentValue(): investment value- "+ investmentresult);
	}catch(Exception e) {
	    logger.error("Failed to fetch investment value - ",e);
	}
	return investmentresult;
    }

    @Async
    @Override
    public void saveMFInitiatedTranasctionRequest(SelectMFFund fundDetails) {

	logger.info("saveMFInitiatedTranasctionRequest(): Saving initiated MF transaction request ");
	try {
	    MFinitiatedTrasactions transactions  = new MFinitiatedTrasactions();
	    transactions.setMobile(fundDetails.getMobile());
	    transactions.setPan(fundDetails.getPan().toUpperCase());
	    transactions.setTransactionType(fundDetails.getTransactionType());
	    transactions.setSchemeName(fundDetails.getSchemeName());
	    transactions.setSchemeCode(fundDetails.getSchemeCode());
	    transactions.setSipDate(fundDetails.getSipDate());
	    transactions.setInvestAmount(fundDetails.getInvestAmount());
	    transactions.setOrderPlaceTime(new Date());
	    mfInitiatedTransactionCrudRepository.save(transactions);

	    logger.info("saveMFInitiatedTranasctionRequest(): Request details saved.. Drop mail");
	    mailSenderInterface.sendMFInitiatedNotice(transactions);

	}catch(Exception e) {
	    logger.error("saveMFInitiatedTranasctionRequest(): Failed to save initiated transaction request data - ",e);
	}
    }

    @Override
    public BseOrderPaymentResponse getpendingPaymentLinks(String userid,String callbackurl) {

	logger.info("Request received to fetch BSE MF pending payments link for user id- ");
	BseOrderPaymentResponse orderUrlReponse = new BseOrderPaymentResponse();
	String clientId = getClientIdfromMobile(userid);
	if(clientId!=null) {
	    BseOrderPaymentRequest orderUrl = new BseOrderPaymentRequest();
	    orderUrl.setClientCode(clientId);
	    orderUrl.setMemberCode(CommonConstants.BSE_MEMBER_ID);
	    // orderUrl.setLogOutURL("http://localhost:8080/products/mutual-funds/my-dashboard");

	    logger.info("bsePendingPayments(): Pending order callback url- " + callbackurl);
	    // logger.info("Pending order callback base64- " +
	    // Base64.encodeBase64String(orderCallUrl.getBytes()));
	    // orderUrl.setLogOutURL(Base64.encodeBase64String(orderCallUrl.getBytes()));
	    orderUrl.setLogOutURL(callbackurl.replace("http://", "https://"));
	    orderUrlReponse = investmentConnectorBseInterface.getPaymentUrl(orderUrl);
	    logger.info("bsePendingPayments(): Pending payments URL fetch request response code- " + orderUrlReponse.getStatusCode());

	}else {
	    logger.info("No BSE linked account found for mobile no - "+ userid);
	    orderUrlReponse.setStatusCode("101");
	    orderUrlReponse.setPayUrl("No customer found to process");
	}

	return orderUrlReponse;

    }

    @Override
    public Object getMfRegistrationStatus(String mobile, String pan, String clientid) {
	logger.info("Process request to check mutual fund registartion status for mobile -" +  mobile);
	Map<String, String> status = new HashMap<String, String>();
	try {
	    if(bseCustomerCrudRespository.existsByMobileAndAccountActive(mobile, "Y")) {
		status.put("EXISTS", "Y");
		String queryresult = bseCustomerCrudRespository.getBseRegistrationAOFStatus(mobile); 
		String[] bseregistered= queryresult.split(",");
//		status.put("BSEREGISTERED", bseregistered.split(",")[0]);
//		status.put("AOFREGISTERED", bseregistered.split(",")[1]);
		status.put("BSEREGISTERED",  bseregistered[0]);
		status.put("FATCAREGISTERED", bseregistered[2]);
		status.put("AOFREGISTERED",  bseregistered[4]);
	    }else {
		status.put("EXISTS", "N");
		status.put("BSEREGISTERED", "NA");
		status.put("FATCAREGISTERED", "NA");
		status.put("AOFREGISTERED", "NA");
	    }
	}catch(Exception e) {
	    logger.error("Error querying to database..",e);
	    status.put("EXISTS", "E");
	    status.put("BSEREGISTERED", "NA");
	    status.put("FATCAREGISTERED", "NA");
	    status.put("AOFREGISTERED", "NA");
	}
	return status;
    }

    @Override
    public TransactionStatus cancelSIPOrder(String mobile, String orderNo, String clientid, String oldtransactionid,String newtransactionid, String investype) {

	logger.info("Process request to cancel SIP order- "+ orderNo + " : clientid- "+ clientid);
	TransactionStatus transStatus = new TransactionStatus();

	try {
	    String clientidfromdb =bseCustomerCrudRespository.getClientIdFromMobile(mobile);
	    if(clientidfromdb!=null && clientid.equalsIgnoreCase(clientidfromdb)) {
		if(bseTransHistoryViewCrudRepository.existsByClienIdAndTransactionIdAndOrderNo(clientid, oldtransactionid,orderNo) ) {
		    logger.info("Order details matched. Proceed with SIP cancellation..");

		    SelectMFFund transactiondetails = bseTransCrudRepository.findOneByTransactionIDAndClientID(oldtransactionid, clientid);

		    if(transactiondetails.getOrdercancelled() ==null || transactiondetails.getOrdercancelled().equals("N") ) {

			//		Generate BSE transaction Reference no
			Date date = new Date();
			StringBuffer uniqueReferenceNo = new StringBuffer();
			uniqueReferenceNo.append((new SimpleDateFormat("yyyyMMdd").format(date))).append(CommonConstants.BSE_MEMBER_ID);
			long counter= getCurrentDayNextTransCount(date);
			for(int i=1;i<(6-Long.toString(counter).length());i++){
			    uniqueReferenceNo.append("0");
			}
			uniqueReferenceNo.append(Long.toString(counter));
			StringBuffer ref = new StringBuffer();


			ref.append("C").append(transactiondetails.getClientID().substring(6)).append(Calendar.getInstance().getTimeInMillis());
//			transactiondetails.setBseRefNo(ref.toString());
//			transactiondetails.setOrderNo(orderNo);
			BseOrderEntryResponse bseResult = investmentConnectorBseInterface.cancelSipOrder(transactiondetails, uniqueReferenceNo.toString(), orderNo, ref.toString());
			
			if(bseResult.getSuccessFlag().equalsIgnoreCase("0")){

			    transStatus.setSuccessFlag("S");
			    transStatus.setStatusMsg(bseResult.getBsereMarks());
			    transStatus.setBseOrderNoFromResponse(bseResult.getOrderNoOrSipRegNo());
			    
			    try{
				    if(CommonConstants.BSE_SAVE_TRANSACTION.equalsIgnoreCase("Y")){
					logger.info("Transaction is successful. Saving transaction request to Database");
					logger.info("Save SIP cancel transaction response from BSE to database");
					bseOrderEntryResponseRepository.saveAndFlush(bseResult);
//					Update transaction record
					bseTransCrudRepository.changesipcancelstatus(bseResult.getBsereMarks(), ref.toString(),new Date(), clientidfromdb, oldtransactionid);
				    }else{
					logger.info("BSE_SAVE_TRANSACTION is disabled. Trsansaction not saved  to databse.");
				    }

				}catch(Exception e){
				    logger.error("BSE SIP cancellation is successful but failed to save data to dabtabase for transaction ID: "+ oldtransactionid,e);
				}
			    
			}else if (bseResult.getSuccessFlag().equalsIgnoreCase(CommonConstants.BSE_API_SERVICE_DISABLED)){
			    logger.info("Transaction disabled. Reason- "+bseResult.getBsereMarks());
			    transStatus.setSuccessFlag("D");
			    transStatus.setStatusMsg(bseResult.getBsereMarks());
			}else{
			    logger.info("Transaction has failed. Transaction declined from saving to database. Reason- "+bseResult.getBsereMarks());
			    transStatus.setSuccessFlag("F");
			    transStatus.setStatusMsg(bseResult.getBsereMarks()!=null?bseResult.getBsereMarks():bseResult.getSuccessFlag());
			}
		    }else {
			transStatus.setSuccessFlag("F");
			transStatus.setStatusMsg("SIP is already cancelled!");
		    }
		}else {
		    transStatus.setSuccessFlag("F");
		    transStatus.setStatusMsg("No record found process!");
		}
	    }else {
		logger.info("Cancellation request details do not match for client ID. Transaction rejected");
		transStatus.setSuccessFlag("F");
		transStatus.setSuccessFlag("Invalid record details");
	    }



	}catch(Exception e) {
	    logger.error("cancelSIPOrder(): Error processing request..",e);
	    transStatus.setSuccessFlag("F");
		transStatus.setSuccessFlag("Internal Error. Please try after sometime.");
	}
	return transStatus;
    }

    @Override
    public String generateTransId() {
	//	boolean transIdExist = false;
	String transId = null;
	do {
	    transId = BseRelatedActions.generateTransactionId();
	    //    		transIdExist = bseEntryManager.checkIfTransIdExist(transId);
	} while (bseTransCrudRepository.existsByTransactionID(transId));
	return transId;
    }

    @Override
    public TransactionStatus cancelSIPOrderStatus(String orderNo, String clientid, String oldtransactionid) {
	
	
	return null;
    }
    

    @Override
    public BseApiResponse uploadAOFForm(String mobileNumber, String aoffolderLocation,String logolocation, String clientCode,MFCustomers investForm) {

    	logger.info("Request received to upload customer AOF form to BSE for mobile- "+ mobileNumber);
//    	String bseregistered[] = null;
    	String[] bseregistered =  null;
    	String fileName = investForm.getPan1() + ".pdf";
    	String aofuploadstatus="N";
    	BseApiResponse requestresult = new BseApiResponse();
    	int databasesavesatatus=0;
    	String bseuploadstatus="N";
    	try {
    		if(clientCode!=null) {
    			logger.info("Checking AOF upload status from database for customer- "+ mobileNumber + " ->"+ clientCode);
    			String queryresult = bseCustomerCrudRespository.getBseRegistrationAOFStatus(mobileNumber); 
    			bseregistered= queryresult.split(",");
//    			aofuploadstatus = bseregistered.split(",")[1];
    			aofuploadstatus = bseregistered[4];
    		}

//    		if(bseregistered.split(",")[0].equalsIgnoreCase("Y")) {
    		if(( bseregistered[0]).equalsIgnoreCase("Y")) {
    		if(aofuploadstatus.equalsIgnoreCase("Y")) {
    			logger.info("AOF upload status is already comoplete. Skipping reupload for clientcode- "+ clientCode);
//    			requestresult.setResponseCode("998");
    			requestresult.setResponseCode(CommonConstants.TASK_SUCCESS_S);
    			requestresult.setRemarks("ALREDY_UPLOADED");
//    			requestresult.setStatusCode("1");
    			requestresult.setStatusCode(CommonConstants.TASK_SUCCESS_S);
    		}else {
//    			BseAOFDocument flag1 = null;
    			
    			
    			BseAOFDocument aofrecord = bseaofepository.findOneByClientid(clientCode);
    			
    			aofrecord = BseAOFGenerator.aofGenerator(aofrecord,investForm, fileName, logolocation, "VERIFIED", aoffolderLocation);
    			
    			if(aofrecord.getClientid() ==null) {
    				aofrecord.setClientid(clientCode);
    			}
    			
    			StringBuffer filename = new StringBuffer(CommonConstants.BSE_MEMBER_ID);
    			filename.append(clientCode).append(new SimpleDateFormat("ddMMyyyy").format(new Date())).append(".tiff");
    			aofrecord.setFileuploadname(filename.toString());
    			
    			bseaofepository.save(aofrecord);
    			
    			/*
    			if(flag1 == null) {
    				
    				logger.info("uploadsign(): Signed AOF file generation complete for customer- " + mobileNumber + "->" +investForm.getPan1() + "-> "+ clientCode + " -> "+ flag1);
    				if(flag1.getFilegenerationstatus().equalsIgnoreCase("SUCCESS")) {
    					flag1.setClientid(clientCode);
    					bseaofepository.save(flag1);
    				}
    			}
    			*/
    			
    			if (aofrecord.getFilegenerationstatus().equalsIgnoreCase("SUCCESS")) {
    				//			Send AOF to BSE
//    				BseAOFUploadResponse aofresp1 = investmentConnectorBseInterface.uploadAOFFormtoBSE(mobileNumber, aoffolderLocation, clientCode);
    				BseAOFUploadResponse aofresp1 = investmentConnectorBseInterface.uploadAOFFormtoBSE(investForm.getPan1(), aoffolderLocation, clientCode, filename.toString());
        			logger.info("uploadAOFForm(): AOF upload status from BSE as received- " + aofresp1.getStatusMessage());
        			requestresult.setResponseCode(aofresp1.getStatusCode());
        			requestresult.setRemarks(aofresp1.getStatusMessage());
        			if (
        					aofresp1.getStatusCode().equalsIgnoreCase("100") || 
        					(
        							aofresp1.getStatusCode().equalsIgnoreCase("101")
        					&& 
        						(
        							aofresp1.getStatusMessage().equalsIgnoreCase("Exception caught at Service Application")
        							|| aofresp1.getStatusMessage().equalsIgnoreCase("PAN NO ALREADY APPROVED")
        							|| aofresp1.getStatusMessage().equalsIgnoreCase("FAILED: IMAGE IS ALREADY AVAILABLE AND IMAGE STATUS IS PENDING")
        						)
        					)
        				) {
        				logger.info("AOF upload is considered successful.. Proceed with success");
        				bseuploadstatus="Y";
        				
        				requestresult.setStatusCode(CommonConstants.TASK_SUCCESS_S);
        				requestresult.setRemarks("Failed to generate AOF file!");
        				
        				requestresult.setData3(aofrecord.getAofpdf());
        			} else {
        				logger.info("AOF upload not success...");
        				bseuploadstatus="N";
        				
        				requestresult.setStatusCode(CommonConstants.TASK_FAILURE_S);
        				requestresult.setRemarks(aofresp1.getStatusMessage());
        			}
//        			databasesavesatatus = bseCustomerCrudRespository.updateAofUploadStatus(mobileNumber,"N",aofresp1.getStatusMessage());
        			databasesavesatatus = bseCustomerCrudRespository.updateAofUploadStatus(mobileNumber,bseuploadstatus,aofresp1.getStatusMessage());
        			bseaofepository.updateAofUploadStatus(clientCode, bseuploadstatus, aofresp1.getStatusCode(), aofresp1.getStatusMessage());
        			logger.info("uploadSignedAOFFile(): AOF upload status to database for mobile- "+mobileNumber + " -> status "+ bseuploadstatus+ " -> " + databasesavesatatus);

    			}else {
    				logger.info("AOF PDF file generation failed for mobile- +"+mobileNumber + ". SKipped uploading to BSE");
    				requestresult.setStatusCode(CommonConstants.TASK_FAILURE_S);
    				requestresult.setRemarks("Failed to generate AOF file!");
    			}
//    			requestresult.setStatusCode(Integer.toString(databasesavesatatus));
    		}
    		}else {
    			logger.info("BSE customer registration not complete, hence skipping AOF upload for customer- "+ mobileNumber + " -> "+ clientCode);
    			requestresult.setResponseCode(CommonConstants.TASK_FAILURE_S);
    			requestresult.setRemarks("Customer registration is not yet complete.");
    		}
    	}catch(Exception e) {
    		logger.error("Failed to process AOF status update to database",e);
    		requestresult.setResponseCode(CommonConstants.TASK_FAILURE_S);
			requestresult.setRemarks("Internal error. Please contact admin");
//			requestresult.setStatusCode("0");
			requestresult.setStatusCode(CommonConstants.TASK_FAILURE_S);
    	}

    	return requestresult;

    }

    @Override
    public String BseOrderPaymentStatus(String clientid, String orderno) {
	return investmentConnectorBseInterface.BseOrderPaymentStatus(clientid, orderno);
    }

    @Override
    public BseOrderPaymentResponse getPaymentUrl(BseOrderPaymentRequest request) {
	return investmentConnectorBseInterface.getPaymentUrl(request);
    }

    @Override
    public BseApiResponse extractAllotmentstatement(String fromdate, String todate, String orderstatus,
	    String ordertype, String settlementtype) {
	
	logger.info("Start executing to getAllotment statement");
	List<Allotmentstatement> data = investmentConnectorBseInterface.getAllotmentstatement(fromdate, todate, orderstatus, ordertype, settlementtype);
	List<Allotmentstatement> tosave= new ArrayList<Allotmentstatement>();
	BseApiResponse response = new BseApiResponse();
	try {
	if(data!=null) {
	    for(int i=0;i<data.size();i++) {
		logger.info("Check if TRX already exist- "+ data.get(i).getRtatransno());
		if(!allotmentStmntCrudRepository.existsByRtatransno(data.get(i).getRtatransno())) {
		    tosave.add(data.get(i));
		}else {
		    logger.info("Allotment statement already found in DB for RTA trans no: "+ data.get(i).getRtatransno() );
		}
	    }
	    
	    logger.info("Total records to save- "+ tosave.size());
	    allotmentStmntCrudRepository.save(tosave);
	    response.setResponseCode(CommonConstants.TASK_SUCCESS_S);
	    response.setRemarks("Total processed- "+ tosave!=null?Integer.toString(tosave.size()):"NULL");   
	}else {
	    response.setResponseCode(CommonConstants.TASK_FAILURE_S);
	    response.setRemarks("No records");
	}
	 
	
	}catch(Exception e) {
	    logger.info("extractAllotmentstatement(): Error saving data to DB/processing",e);
	    response.setResponseCode(CommonConstants.TASK_FAILURE_S);
	    response.setRemarks(e.getMessage());
	}
	return response ;
    }

    @Override
    public Bseregistrationstatus getbseregistrationstatus(String mobile, String pan, String customeruniqid,
    		String clientcode) {
    	logger.info("Get BSE registration status for mobile- "+ mobile);
    	Map<String, String> result = new HashMap<String, String>();
    	String[] aofUploadStatus = null;
    	Bseregistrationstatus response = new Bseregistrationstatus();
    	try{
    		if(bseCustomerCrudRespository.existsByMobileAndAccountActive(mobile,"Y")){
//    			result.put("ACCOUNTEXIST", "Y");
    			response.setAccountexist("Y");
    			
    			String queryresult = bseCustomerCrudRespository.getBseRegistrationAOFStatus(mobile); 
    			aofUploadStatus= queryresult.split(",");
    			logger.info("getbseregistrationstatus(): Customer BSE registration status- "+ Arrays.asList(aofUploadStatus));
//    			result.put("BSEREGISTER", aofUploadStatus[0]);
//    			result.put("FATCAUPLOAD", aofUploadStatus[2]);
//    			result.put("AOFUPLOAD", aofUploadStatus[4]);
    			response.setBseregisterstatus(aofUploadStatus[0]);
    			response.setFatcadeclared(aofUploadStatus[2]);
    			response.setAofuploadstatus(aofUploadStatus[4]);
    		}else{
//    			result.put("ACCOUNTEXIST", "N");
    			response.setAccountexist("N");
    		}
    	}catch(Exception e){
    		logger.error("Failed to query database to get customer BSE upload status for customer- ",mobile, e);
//    		result.put("ACCOUNTEXIST", "E");
    		response.setAccountexist("E");
    	}
    	logger.info("Returning BSE registraiton status status- "+ response.getAccountexist());
    	
    	return response;
    }

	@Override
	public Datarquestresponse checkifkeyregistered(String mobile, String pan, String searchtype, String filter1) {
		
		Datarquestresponse response = new Datarquestresponse();
		try {
		if( bseCustomerCrudRespository.existsByPan1AndMobileNot(pan,mobile) || (profileRestClientService.isPanExisitngForOthers(mobile, pan).equals("Y") ) ) {
			response.setStatus("1");;
			response.setMsg("Pan already registered with alternate account.");
		}else {
			response.setStatus("0");
			response.setMsg("Valid PAN");
		}
		}catch(Exception e) {
			logger.error("checkifkeyregistered(): Failed to get submitted PAN details for verification",e);
			response.setStatus("1");
			response.setMsg("Internal error");
		}
		
		return response;
	}

	
	@Deprecated
	@Override
	public HttpClientResponse completemfregistration(String mobile) {

		HttpClientResponse result = new HttpClientResponse();
		try {
			String clientCode = bseCustomerCrudRespository.getClientIdFromMobile(mobile);;
			String panForAOfFile = bseCustomerCrudRespository.getCustomerPanNumberFromMobileAndActive(mobile, "Y");
			// call api to upload pdf

			String queryresult = bseCustomerCrudRespository.getBseRegistrationAOFStatus(mobile); 
			String[] aofUploadStatus= queryresult.split(",");
			logger.info("Current users registration status- "+ Arrays.asList(aofUploadStatus));
			if(aofUploadStatus[4].equals("N")) {

				logger.info("uploadSignedAOFFile(): Call API uploadAOFForm");
				BseAOFUploadResponse aofresp1 = investmentConnectorBseInterface.uploadAOFFormtoBSE(null,panForAOfFile,
						env.getProperty(CommonConstants.BSE_AOF_GENERATION_FOLDR), clientCode);
				logger.info("uploadSignedAOFFile(): AOF upload status as received- " + aofresp1.getStatusMessage());
				if (aofresp1.getStatusCode().equalsIgnoreCase("100") || (aofresp1.getStatusCode()
						.equalsIgnoreCase("101")
						&& (aofresp1.getStatusMessage().contains("Exception caught at Service Application")
								|| aofresp1.getStatusMessage().contains("PAN NO ALREADY APPROVED")
								|| aofresp1.getStatusMessage()
								.contains("IMAGE IS ALREADY AVAILABLE AND IMAGE STATUS IS PENDING")))) {
					String success = "Y";
					String message = aofresp1.getStatusMessage();

					int i = bseCustomerCrudRespository.updateAofUploadStatus(mobile,success,message);
					result.setResponseCode(CommonConstants.TASK_SUCCESS);
					result.setRetrunMessage("SUCCESS");

					logger.info("uploadSignedAOFFile(): AOF upload status to database- " + i);
				} else {

					String success = "N";
					String message = aofresp1.getStatusMessage();

					result.setResponseCode(CommonConstants.TASK_FAILURE);
					result.setRetrunMessage(message);

					bseCustomerCrudRespository.updateAofUploadStatus(mobile,success,message);
				}
			}else {
				logger.info("AOF already uploaded successfully. Prevent reinitiate");
				result.setResponseCode(CommonConstants.TASK_FAILURE);
				result.setRetrunMessage("AOF upload already complete.");
			}

		}catch(Exception e) {
			logger.error("completemfregistration(): Failed to process request",e);
			result.setResponseCode(CommonConstants.TASK_FAILURE);
			result.setRetrunMessage("Internal error");
		}
		return result;
	}

	@Override
	public BseApiResponse updateCustomerDetails(MFCustomers customerForm, String customerType, String customerlogged,
			String initiatedmobileid,ClientSystemDetails systemdetails) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getifscdetails(String ifsc) {
		return investmentConnectorBseInterface.getifscdetails(ifsc);
	}


    /*		public static void main(String[] args){
		//		System.out.println(new Random());
		String s ="NEW|201901302627300006|0|SUMANTA1|26273|DEBA593C|FAILED: DIRECT INV TYPE SCHEME NOT ALLOWED|1";

		List<String> a = Arrays.asList(s.split("\\|"));
		System.out.println(a);
		System.out.println(a.get(1));
		System.out.println(Calendar.getInstance().getTimeInMillis());
		String k = "<html><body> This is my Project </body></html>";



		System.out.println("copmplete");


	}*/

}
