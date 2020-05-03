package com.freemi.services.Impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freemi.common.util.CommonConstants;
import com.freemi.common.util.CommonTask;
import com.freemi.database.interfaces.BseMandateCrudRepository;
import com.freemi.database.interfaces.BseOrderEntryResponseRepository;
import com.freemi.database.interfaces.BseTransCrudRepository;
import com.freemi.database.interfaces.RegistryFundsCrudRepository;
import com.freemi.database.interfaces.RegistryWishCrudRepository;
import com.freemi.entity.bse.BseApiResponse;
import com.freemi.entity.general.MethodRequestResponse;
import com.freemi.entity.investment.BseMandateDetails;
import com.freemi.entity.investment.BseOrderEntryResponse;
import com.freemi.entity.investment.RegistryFunds;
import com.freemi.entity.investment.RegistryWish;
import com.freemi.entity.investment.SelectMFFund;
import com.freemi.entity.investment.TransactionStatus;
import com.freemi.services.interfaces.BseEntryManager;
import com.freemi.services.interfaces.InvestmentConnectorBseInterface;
import com.freemi.services.interfaces.MailSenderInterface;
import com.freemi.services.interfaces.RegistryManager;

@Service
public class RegistryHandlerImpl implements RegistryManager{

    private static final Logger logger = LogManager.getLogger(RegistryHandlerImpl.class);
    
    @Autowired
    RegistryFundsCrudRepository registryFundsCrudRepository;
    
    @Autowired
    RegistryWishCrudRepository registryWishCrudRepository;
    
    @Autowired
    BseEntryManager bseEntryManager;
    
    @Autowired
    BseOrderEntryResponseRepository bseOrderEntryResponseRepository;
    
    @Autowired
    InvestmentConnectorBseInterface investmentConnectorBseInterface;
    
    @Autowired
    BseTransCrudRepository bseTransCrudRepository;

    @Autowired
    BseMandateCrudRepository bseMandateCrudRepository;
    
    @Autowired
    MailSenderInterface mailSenderInterface;
    
    SimpleDateFormat DB_FMT = new SimpleDateFormat("yyyy-mm-dd");
    SimpleDateFormat UI_FMT = new SimpleDateFormat("mm/dd/yyyy");
    
    @Override
    public RegistryFunds serachregistryfund(String schemecode) {
	
	RegistryFunds fund = null;
	try {
	    fund = registryFundsCrudRepository.findOneBySchemeCode(schemecode);
	}catch(Exception e) {
	    logger.error("Error searching for fund.. ",e);
	}
	
	return fund;
    }
    
    @Override
    public List<RegistryFunds> getRegistryfunds(String criteria, String obj2) {
	
	List<RegistryFunds> funds=null;
	try {
	    funds = registryFundsCrudRepository.findAll();
	}catch(Exception e) {
	    logger.error("Error fetching registry funds list..");
	}
	
	return funds;
    }

    @Override
    public MethodRequestResponse saveRegistryplannerrequest(RegistryWish registrywish) {
	logger.info("Process to save registry planner request..");
	MethodRequestResponse response = new MethodRequestResponse();
	try {
	    
	    Date date1 = UI_FMT.parse(registrywish.getDate());
	    String formatteddata = DB_FMT.format(date1);
	    registrywish.setDate(formatteddata);
	    
	    logger.info("Generate Transaction ID...");
	    String transactionid="";
	    do {
		transactionid= CommonTask.generateRegistryTransactionID();
	    }while(registryWishCrudRepository.existsByTransactionid(transactionid));
	    logger.debug("Final Registry transaction ID- "+ transactionid);
	    
	    registrywish.setTransactionid(transactionid);
	    
	    registryWishCrudRepository.save(registrywish);
	    response.setResponseCode(CommonConstants.TASK_SUCCESS_S);
	    response.setRemarks(transactionid);
	}catch(Exception e) {
	    logger.error("Error saving registry planner...",e);
	    response.setResponseCode(CommonConstants.TASK_FAILURE_S);
	}
	
	return response;
	
    }

    @Override
    public RegistryWish getregistryDetails(String registryrequestid) {
	
	RegistryWish registrywish=null;
	try {
	    registrywish = registryWishCrudRepository.findOneByTransactionid(registryrequestid);
	}catch(Exception e) {
	    logger.error("Error fetching registry wish details...");
	}
		
	return registrywish;
    }

    @Override
    public TransactionStatus purchaseregistrysip(SelectMFFund selectedMFFund) {
		logger.info("REGISTRY BSE SIP TRANSACTION .... Process..");
	
		boolean transactionfailed = false;
		//Save details to database, process to BSE, update database with response
		TransactionStatus transStatus = new TransactionStatus();
		BseOrderEntryResponse bseResult =null;
		String mandateId="";
		boolean mandareGenerated =false;
		try{
		    selectedMFFund.setOrderPlaceTime(new Date());

		    //Generate BSE related ref no
		    StringBuffer ref = new StringBuffer();
		    ref.append("RSTRY").append(selectedMFFund.getClientID().substring(6)).append(Calendar.getInstance().getTimeInMillis());
		    
		    selectedMFFund.setBseRefNo(ref.toString());

		    logger.info("Registry New transaction request received of category:  "+selectedMFFund.getTransactionType()+" .Generated Transaction reference ID: "+ selectedMFFund.getTransactionID());

		    //		Generate BSE transaction Reference no
		    Date date = new Date();
		    StringBuffer transNumber = new StringBuffer();
		    transNumber.append((new SimpleDateFormat("yyyyMMdd").format(date))).append(CommonConstants.BSE_MEMBER_ID);
		    long counter= bseEntryManager.getCurrentDayNextTransCount(date);
		    for(int i=1;i<(6-Long.toString(counter).length());i++){
			transNumber.append("0");
		    }
		    transNumber.append(Long.toString(counter));

		    logger.info("Requesting BSE to register transaction for client id- : "+ selectedMFFund.getClientID() + " : Transaction code: "+ transNumber.toString()+ ": Scheme code: "+ selectedMFFund.getSchemeCode() + " : Amount: "+ selectedMFFund.getInvestAmount());

		    //Call BSE
		    if(!bseMandateCrudRepository.existsByClientCodeAndMandateTypeAndMandateActive(selectedMFFund.getClientID(), "I","Y" ) ) {
			List<BseMandateDetails> madatedetails = bseMandateCrudRepository.findAllByClientCodeAndMandateTypeAndMandateActive(selectedMFFund.getClientID(), "I", "Y");
			mandateId = madatedetails.get(0).getMandateId();
		    }else {
			logger.info("Customer has no mandate details. Regiter mandate first..");
			
			   BseApiResponse emandateResponse = bseEntryManager.updateEmdandateStatus(selectedMFFund.getMobile(), "I", "50000");
			    if (emandateResponse != null && emandateResponse.getStatusCode().equals("100")) {
				//						if (emandateResponse.getStatusCode().equals("100")) {
				mandareGenerated = true;
				mandateId = emandateResponse.getResponseCode();
			
		    }
		    }
		    
		    if(mandareGenerated) {
		    bseResult = investmentConnectorBseInterface.processCustomerTransactionbsaRequest(selectedMFFund, transNumber.toString(),mandateId);

		    logger.info("Status of requested transaction - "+ bseResult.getSuccessFlag());

		    if(bseResult.getSuccessFlag().equalsIgnoreCase("0")){

			if(CommonConstants.BSE_SAVE_TRANSACTION.equalsIgnoreCase("Y")){
			  logger.info("Registry SIP Transaction is successful. Saving transaction request to Database");
			    try{
				selectedMFFund = bseTransCrudRepository.saveAndFlush(selectedMFFund);
				logger.info("Save Registry transaction response from BSE to database");
				bseOrderEntryResponseRepository.saveAndFlush(bseResult);
			    }catch(Exception e){
				logger.error("Registry SIP transaction is successful but failed to save data to dabtabase for transaction ID: "+ selectedMFFund.getTransactionID(),e);
			    }

			}else{
			    logger.info("purchaseregistrysip(): BSE_SAVE_TRANSACTION is disabled. Trsansaction not saved  to databse.");

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
		    }else {
			transStatus.setSuccessFlag("F");
			transStatus.setStatusMsg("Failed to generate SIP mandate currently. Could not complete RegistRy creation");
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
		    mailSenderInterface.notifyTransactionErrorToAdmin(null, transStatus.getStatusMsg(), null, "Registry Mutual Fund Transaction", "SIP Registration failure", selectedMFFund.getMobile(), selectedMFFund.getInvestorName());
		}

		return transStatus;
    }

  

}
