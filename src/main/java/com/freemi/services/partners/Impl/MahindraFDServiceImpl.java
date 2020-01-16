package com.freemi.services.partners.Impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.freemi.common.util.CommonConstants;
import com.freemi.common.util.CommonTask;
import com.freemi.database.respository.mahindra.MMCustAddressCrudRepository;
import com.freemi.database.respository.mahindra.MMCustKYCCrudRepository;
import com.freemi.database.respository.mahindra.MMCustKycDocumentRepository;
import com.freemi.database.respository.mahindra.MMCustNomineeCrudRepository;
import com.freemi.database.respository.mahindra.MMCustOtherCountryTaxDetailsRepository;
import com.freemi.database.respository.mahindra.MMCustomersBankRepository;
import com.freemi.database.respository.mahindra.MMCustomersCrudRepository;
import com.freemi.database.respository.mahindra.MMFDPurchaseStatusCrudRepository;
import com.freemi.database.respository.mahindra.MMFDTranactionCrudRepository;
import com.freemi.database.respository.mahindra.MMKycDocUploadRepository;
import com.freemi.database.respository.mahindra.MMLeadDetailsCrudRepository;
import com.freemi.database.respository.mahindra.MMMappedLocationsRepository;
import com.freemi.database.respository.mahindra.MMPurchaseHistoryRepository;
import com.freemi.database.respository.mahindra.MMSchemesCrudRepository;
import com.freemi.entity.investment.mahindra.MahindraApiResponse;
import com.freemi.entity.investment.mahindra.MahindraCustomerAddress;
import com.freemi.entity.investment.mahindra.MahindraCustomerBankDetails;
import com.freemi.entity.investment.mahindra.MahindraCustomerDetails;
import com.freemi.entity.investment.mahindra.MahindraCustomerKYCDetails;
import com.freemi.entity.investment.mahindra.MahindraFDEntity;
import com.freemi.entity.investment.mahindra.MahindraFDMappedLocations;
import com.freemi.entity.investment.mahindra.MahindraFDPurchaseRequest;
import com.freemi.entity.investment.mahindra.MahindraFDRenewalEntity;
import com.freemi.entity.investment.mahindra.MahindraFDSaveLeadDetails;
import com.freemi.entity.investment.mahindra.MahindraFDTransactionStatus;
import com.freemi.entity.investment.mahindra.MahindraKycDocUploadStatus;
import com.freemi.entity.investment.mahindra.MahindraKycDocuments;
import com.freemi.entity.investment.mahindra.MahindraNominee;
import com.freemi.entity.investment.mahindra.MahindraOtherCountryTaxDetails;
import com.freemi.entity.investment.mahindra.MahindraResponse;
import com.freemi.entity.investment.mahindra.MahindraSchemesList;
import com.freemi.entity.investment.mahindra.Mahindrapurchasehistory;
import com.freemi.services.interfaces.MahindraFDServiceInterface;
import com.freemi.services.interfaces.MailSenderInterface;
import com.freemi.ui.restclient.RestClientMahindraFD;

@Service
public class MahindraFDServiceImpl implements MahindraFDServiceInterface {

    @Autowired
    MahindraFDBeansMapper mahindraFDBeansMapper;

    @Autowired
    MMCustomersCrudRepository mmCustomersCrudRepository;

    @Autowired
    MMCustAddressCrudRepository mmCustAddressCrudRepository;

    @Autowired
    MMCustomersBankRepository mmCustomersBankRepository;

    @Autowired
    MMFDTranactionCrudRepository mmFDTranactionCrudRepository;

    @Autowired
    MMCustKYCCrudRepository mmCustKYCCrudRepository;

    @Autowired
    MMLeadDetailsCrudRepository mmLeadDetailsCrudRepository;

    @Autowired
    MMFDPurchaseStatusCrudRepository mmFDPurchaseStatusCrudRepository;

    @Autowired
    MMSchemesCrudRepository mmSchemesCrudRepository;

    @Autowired
    RestClientMahindraFD restClientMahindraFD;

    @Autowired
    MMMappedLocationsRepository mmMappedLocationsRepository;

    @Autowired
    MMCustNomineeCrudRepository mmCustNomineeCrudRepository;

    @Autowired
    MMCustOtherCountryTaxDetailsRepository mmCustOtherCountryTaxDetailsRepository;

    @Autowired 
    MMCustKycDocumentRepository mmCustKycDocumentRepository;

    @Autowired
    MMPurchaseHistoryRepository mmPurchaseHistoryRepository;

    @Autowired
    MMKycDocUploadRepository mmKycDocUploadRepository;

    @Autowired
    MailSenderInterface mailSenderInterface;

    private static final Logger logger = LogManager.getLogger(MahindraFDServiceImpl.class);

    SimpleDateFormat uifmt = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat dbfmt =new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public String generateCpTransctionId() {
	String cptransactionId = null;
	logger.info("Generate CP Transaction ID...");
	do {
	    cptransactionId = "MMFD"+ CommonTask.generateMMTransactionId();
	    logger.debug("Generated CP TRANSACTION ID- " + cptransactionId);
	}while(mmLeadDetailsCrudRepository.existsByCpTransRefNo(cptransactionId));


	logger.info("Final CP transaction ID- "+ cptransactionId);

	return cptransactionId;
    }


    @Override
    public List<MahindraSchemesList> getSchemesListBasedOnCriteria(String tenure, String schemetypeCode,
	    String customerCategory) {
	logger.info("Request received to fetch Mahindra FD schemedetails for customer type- "+ customerCategory);
	List<MahindraSchemesList> schemesList = null;
	try {
	    schemesList = mmSchemesCrudRepository.findAllBySchemetypecodeAndCustomercategoryAndActiveinactiveflagAndFundactive(schemetypeCode, customerCategory,"1","Y");
	    logger.info("getSchemesListBasedOnCriteria(): Total schemes listed- "+ schemesList.size());
	}catch(Exception e) {
	    logger.error("getSchemesListBasedOnCriteria(): Error fetching Mahindra FD schemeslist.",e);
	}

	return schemesList;
    }

    @Override
    public String generateTransactionId(String value) {

	String transactionId= "MF"+ Calendar.getInstance().getTimeInMillis();
	logger.info("Generate Transaction ID for MF FD purchase- "+ transactionId);
	return transactionId;

    }


    @Override
    public MahindraFDEntity getRegisteredDetails(MahindraFDEntity fdEntity) {
	logger.info("Populate customer form if already registered...");
	MahindraCustomerDetails customer = mmCustomersCrudRepository.findOneByMobile(fdEntity.getMobile());
	if(customer!=null) {

	    logger.info("Customer already exist. Pre filling data..");
	    List<MahindraCustomerAddress> customeraddress= mmCustAddressCrudRepository.findAllByCustomerId(customer.getCustomerId());
	    List<MahindraCustomerBankDetails> customerBankDetails = mmCustomersBankRepository.findByCustomerIdAndIsActive(customer.getCustomerId(),"Y");
	    List<MahindraNominee> nominee=  mmCustNomineeCrudRepository.findAllByCustomerIdAndActive(customer.getCustomerId(),"Y");
	    List<Object[]> customerkycDetails = mmCustKYCCrudRepository.getCustomerKycDetails(customer.getCustomerId());


	    logger.info("Total address found- "+ customeraddress.size());
	    logger.info("Total bank details enrty found- "+ customerBankDetails.size());

	    fdEntity = mahindraFDBeansMapper.mapCustomerDetailsToEntity(fdEntity, customer,customeraddress,customerBankDetails, customerkycDetails,nominee );

	}else {
	    logger.info("Customer do not exist...");
	}

	return fdEntity;
    }


    @Override
    public MahindraResponse saveCustomerDetails(MahindraFDEntity fdEntity, String transactionId) {

	MahindraResponse response = new MahindraResponse();
	logger.info("saveCustomerDetails(): Save/update customer details requesting for mobile- "+ fdEntity.getMobile());

	try {
	    if(mmCustomersCrudRepository.existsByMobile(fdEntity.getMobile())) {
		logger.info("saveCustomerDetails(): Customer details already present for given mobile no, update details/skip");
		MahindraCustomerDetails customerDetails=  mmCustomersCrudRepository.findOneByMobile(fdEntity.getMobile());
		customerDetails=  mahindraFDBeansMapper.updateFDCustomerDetails(fdEntity, customerDetails);
		if(customerDetails!=null) {

		    mmCustomersCrudRepository.save(customerDetails);
		    //					response.setStatusCode(CommonConstants.TASK_SKIPPED);
		    logger.info("Mahindra FD Customer details updated...");
		    response.setStatusCode(CommonConstants.TASK_UPDATED);
		    response.setResultData1(customerDetails.getCustomerId());
		}

	    }else {
		logger.info("saveCustomerDetails(): Data not found. Saving new customer lead..");
		MahindraCustomerDetails customerDetails=  mahindraFDBeansMapper.mapNewCustomerDetails(fdEntity, null);
		if(customerDetails!=null) {
		    mmCustomersCrudRepository.save(customerDetails);
		    response.setStatusCode(CommonConstants.TASK_SUCCESS);
		    response.setResultData1(customerDetails.getCustomerId());
		    logger.info("Mahindra New Customer details saved to database...");
		}else {
		    logger.info("saveCustomerDetails(): no mapped details to process for saving.. Check if any error..");
		}

	    }
	}catch(Exception e) {
	    response.setStatusCode(CommonConstants.TASK_FAILURE);
	    logger.error("saveCustomerDetails(): Error processing save request.", e);

	}

	return response;
    }

    @Override
    public MahindraResponse saveCustomerAddress(MahindraFDEntity fdEntity, String customerid) {
	MahindraResponse response = new MahindraResponse();
	logger.info("saveCustomerAddress(): Save/update customer address details requesting for mobile- "+ fdEntity.getMobile());

	try {
	    if(mmCustAddressCrudRepository.existsByCustomerIdAndIsActive(customerid,"Y")) {
		logger.info("saveCustomerAddress(): Customer address  details already present for given mobile no, update details/skip");
		List<MahindraCustomerAddress> address = mmCustAddressCrudRepository.findAllByCustomerIdAndIsActive(customerid,"Y");
		response.setStatusCode(CommonConstants.TASK_SKIPPED);
		response.setResultData1(address.get(0).getCustomerAddressId());
	    }else {
		logger.info("saveCustomerAddress(): Address Data not found. Saving new customer address details..");
		//				Generate AddressId
		String customerAddressId = null;
		int i=11;

		do {
		    customerAddressId = "A-"+fdEntity.getMobile().substring(5)+"-"+ fdEntity.getPan().substring(7)+"-"+(i++);
		    logger.debug("saveCustomerAddress():Generated address ID- " + customerAddressId);
		}while(mmCustAddressCrudRepository.existsByCustomerAddressId(customerAddressId));

		logger.info("Generated ID for new address- "+ customerAddressId);

		MahindraCustomerAddress address=  mahindraFDBeansMapper.mapCustomerAddress(fdEntity, null, customerid);

		address.setCustomerAddressId(customerAddressId);
		if(address!=null) {
		    logger.info("saveCustomerAddress(): Initiate save call..");
		    mmCustAddressCrudRepository.save(address);
		    response.setStatusCode(CommonConstants.TASK_SUCCESS);
		    response.setResultData1(customerAddressId);
		}

	    }
	}catch(Exception e) {
	    response.setStatusCode(CommonConstants.TASK_FAILURE);
	    logger.error("saveCustomerAddress(): Error processing save request.", e);
	}

	return response;
    }


    @Override
    public MahindraResponse saveBankDetails(MahindraFDEntity fdEntity, String customerid) {
	MahindraResponse response = new MahindraResponse();
	logger.info("saveBankDetails(): Save/update customer bank details requesting for mobile- "+ fdEntity.getMobile());

	try {
	    if(mmCustomersBankRepository.existsByCustomerIdAndIsActive(customerid,"Y")) {
		logger.info("saveBankDetails(): Customer bank  details already present for given mobile no, update details/skip");
		//				MahindraCustomerBankDetails bankDetails = mmCustomersBankRepository.findOneByAccountNumber(fdEntity.getAccountNumber());
		MahindraCustomerBankDetails bankDetails = mmCustomersBankRepository.findOneByAccountNumber(fdEntity.getAccountNumber());
		response.setStatusCode(CommonConstants.TASK_SKIPPED);
		response.setResultData1(bankDetails!=null?bankDetails.getBankDetailsId():"NULL");
	    }else {
		logger.info("saveBankDetails(): Bank details not found for customer... Save the new details..");
		//				Generate AddressId
		String bankDetailsId = null;
		int i=11;
		do {
		    bankDetailsId = "B-"+fdEntity.getMobile().substring(5)+"-"+ fdEntity.getPan().substring(7)+"-"+(i++);
		    logger.debug("saveBankDetails(): Generated BANK ID- " + bankDetailsId);
		}while(mmCustomersBankRepository.existsByBankDetailsId(bankDetailsId));

		logger.info("saveBankDetails(): Bank details Data not found. Saving new customer address details..");
		MahindraCustomerBankDetails bankDetails =  mahindraFDBeansMapper.mapBankDetails(fdEntity, null, customerid);

		bankDetails.setBankDetailsId(bankDetailsId);
		if(bankDetails!=null) {
		    logger.info("saveBankDetails(): Initiate save call..");
		    mmCustomersBankRepository.save(bankDetails);
		    response.setStatusCode(CommonConstants.TASK_SUCCESS);
		    response.setResultData1(bankDetailsId);
		}

	    }
	}catch(Exception e) {
	    response.setStatusCode(CommonConstants.TASK_FAILURE);
	    logger.error("saveBankDetails(): Error processing save request.", e);
	}

	return response;
    }


    /**
     *
     */
    @Override
    public MahindraResponse saveCustomerNominee(MahindraFDEntity fdEntity, String customerid) {
	MahindraResponse response = new MahindraResponse();

	logger.info("saveCustomerNominee(): Save/update customer nominee details requesting for mobile- "+ fdEntity.getMobile());

	try {
	    if(mmCustNomineeCrudRepository.existsByCustomerIdAndFnameAndActive(customerid,fdEntity.getNomineefirstname(), "Y")) {
		logger.info("saveCustomerNominee(): Customer nominee details already present for given mobile no, update details/skip");
		MahindraNominee nominee = mmCustNomineeCrudRepository.findAllByCustomerIdAndFnameAndActive(customerid,fdEntity.getNomineefirstname(),  "Y").get(0);
		response.setStatusCode(CommonConstants.TASK_SKIPPED);
		response.setResultData1(nominee.getNomineeDetailsId());
	    }else {
		logger.info("saveCustomerNominee(): Nominee details not found for customer... Save the new details..");
		//				Generate AddressId
		String nomineeUniqueId = null;
		int i=21;
		do {
		    nomineeUniqueId = "N-"+fdEntity.getMobile().substring(5)+"-"+ fdEntity.getPan().substring(7)+"-"+(i++);
		    logger.debug("saveCustomerNominee(): Generated INIQUE ID- " + nomineeUniqueId);
		}while(mmCustNomineeCrudRepository.existsByNomineeDetailsId(nomineeUniqueId));

		logger.info("saveCustomerNominee(): Map nominee details to entity for DB save");
		MahindraNominee nominee = mahindraFDBeansMapper.mapNomineeDetails(fdEntity, null, customerid, nomineeUniqueId);

		if(nominee!=null) {
		    logger.info("saveCustomerNominee(): Initiate save call..");
		    mmCustNomineeCrudRepository.save(nominee);
		    response.setStatusCode(CommonConstants.TASK_SUCCESS);
		    response.setResultData1(nomineeUniqueId);
		}

	    }
	}catch(Exception e) {
	    response.setStatusCode(CommonConstants.TASK_FAILURE);
	    logger.error("saveBankDetails(): Error processing save request.", e);
	}


	return response;


    }


    /**
     *@apiNote Save Mahindra customer KYC details
     */
    @Override
    public MahindraResponse saveCustomerKYCDetails(MahindraFDEntity fdEntity, String customerid) {
	MahindraResponse response = new MahindraResponse();
	logger.info("saveCustomerKYCDetails(): Save/update customer KYC details for customer - "+ fdEntity.getMobile());

	try {
	    if(mmCustKYCCrudRepository.existsByCustomerIdAndActive(customerid,"Y")) {
		logger.info("saveCustomerKYCDetails(): Customer KYC details already present for given mobile no, update details/skip");

		MahindraCustomerKYCDetails kycdetails = mmCustKYCCrudRepository.findOneByCustomerIdAndActive(customerid,"Y");
		kycdetails =  mahindraFDBeansMapper.mapkycDetails(fdEntity, kycdetails, customerid);

		if(kycdetails!=null) {
		    logger.info("saveBankDetails(): Initiate KYC update call..");
		    mmCustKYCCrudRepository.save(kycdetails);
		    response.setStatusCode(CommonConstants.TASK_UPDATED);
		    response.setResultData1(kycdetails.getKycid());
		}
		/*
		 * response.setStatusCode(CommonConstants.TASK_SKIPPED);
		 * response.setStatusMsg("Already Exist, skipped");
		 */
	    }else {
		logger.info("saveCustomerKYCDetails():KYC details not found for customer... Save the new details..");
		//				Generate AddressId
		String kycDetailsId = null;
		int i=11;
		do {
		    kycDetailsId = "K-"+fdEntity.getMobile().substring(5)+"-"+ fdEntity.getPan().substring(7)+"-"+(i++);
		    logger.debug("saveCustomerKYCDetails(): Generated KYC ID- " + kycDetailsId);
		}while(mmCustKYCCrudRepository.existsByKycid(kycDetailsId));

		logger.info("saveCustomerKYCDetails(): Generate KYC ID for customer- "+ kycDetailsId);
		MahindraCustomerKYCDetails kycdetails =  mahindraFDBeansMapper.mapkycDetails(fdEntity, null, customerid);
		kycdetails.setKycid(kycDetailsId);
		if(kycdetails!=null) {
		    logger.info("saveBankDetails(): Initiate save call..");
		    mmCustKYCCrudRepository.save(kycdetails);
		    response.setStatusCode(CommonConstants.TASK_SUCCESS);
		    response.setResultData1(kycDetailsId);
		}

	    }
	}catch(Exception e) {
	    response.setStatusCode(CommonConstants.TASK_FAILURE);
	    logger.error("saveCustomerKYCDetails(): Error processing save request.", e);
	}

	return response;
    }


    @Override
    public MahindraResponse saveFDPurchaseDetails(MahindraFDEntity fdEntity, String transactionId, String saveDetailsResponseCode, String addressDetailsId, String bankDetailsId,String nomineemapid,String customerId, String cptransactionId) {
	MahindraResponse response = new MahindraResponse();
	logger.info("saveFDPurchaseDetails(): Initiate purcahse Transaction for mobile- "+ fdEntity.getMobile());
	MahindraApiResponse apiresp=null;
	MahindraApiResponse apiresp2=null;
	try {
	    /*if(mmCustomersBankRepository.existsByMobile(fdEntity.getMobile())) {
				logger.info("Customer bank  details already present for given mobile no, update details/skip");
			}else {*/

	    boolean newCPidrequired=false;
	    boolean saveleaddetailsapicallrequired = false;
	    boolean saveDeailscomplete = true;


	    //			Call API First to save lead details
	    MahindraFDSaveLeadDetails saveLeadDetails = mahindraFDBeansMapper.mapSaveLeadDetails(fdEntity, null, cptransactionId,nomineemapid, customerId);

	    if(saveLeadDetails!=null) {
		//				Save to database first
		if(!mmLeadDetailsCrudRepository.existsByCpTransRefNo(cptransactionId)) {

		    mmLeadDetailsCrudRepository.save(saveLeadDetails);
		    logger.info("New MMFD Lead Details saved.. Process API call....");
		    apiresp = restClientMahindraFD.saveLeadDetails(saveLeadDetails);

		}else {
		    logger.info("Customer details already found in DB by CP Transaction ID. Check status for ID- "+ cptransactionId);
		    MahindraFDSaveLeadDetails getLeadDetails = mmLeadDetailsCrudRepository.findOneByCpTransRefNo(cptransactionId);
		    if(getLeadDetails.getMobile().equals(fdEntity.getMobile())) {
			logger.info("Save amount present-> "+ getLeadDetails.getSaveAmount() + " :> "+ fdEntity.getSaveAmount());
			if(getLeadDetails.getSaveAmount().intValue() == fdEntity.getSaveAmount().intValue()) {
			    if(getLeadDetails.getApiCallComplete().equals("N")) {
				logger.info("SAVE LEAD DETAILS API call pending...");
				saveleaddetailsapicallrequired =true;
			    }else {
				logger.info("Save lead details all complete already... Only need to proceed with transaction details...");
				apiresp = new MahindraApiResponse();
				apiresp.setStatus("OK");
				apiresp.setMfRefNo(getLeadDetails.getMfSysRefNo());
			    }
			}else {
			    logger.info("Saving amount has changed for passed CP transction ID. Set flag to generate new transaction ID");
			    saveleaddetailsapicallrequired =true;
			    newCPidrequired = true;
			    saveDeailscomplete=false;
			}
		    }else {
			logger.info("Mobile no tagged with existing CP do not match.. Set flag to generate new Transaction ID and proceed");
			saveleaddetailsapicallrequired =true;
			newCPidrequired = true;
			saveDeailscomplete=false;
		    }
		    //					Call API - 

		    if(newCPidrequired) {
			logger.info("Generate new CP TRANSACTION ID and save details...");
			cptransactionId = generateCpTransctionId();
			saveLeadDetails.setCpTransRefNo(cptransactionId);
		    }

		    if(!saveDeailscomplete) {
			logger.info("Save lead details with newly geneated CP transaction ID..");
			mmLeadDetailsCrudRepository.save(saveLeadDetails);

		    }
		    if(saveleaddetailsapicallrequired) {
			logger.info("lead Details API call with new CP ID...");
			apiresp = restClientMahindraFD.saveLeadDetails(saveLeadDetails);
		    }

		}

	    }



	    if(apiresp.getStatus().equals("OK")) {

		logger.info("saveLeadDetails is success.. Update DB with reference no - "+ apiresp.getMfRefNo());
		//				Update MF_REF_SYS_NO to saveDetails
		mmLeadDetailsCrudRepository.updateMfSysRefNo(apiresp.getMfRefNo(), cptransactionId);
		saveDetailsResponseCode = apiresp.getMfRefNo();

		logger.info("Start mapping of entity to transactiondetails...");
		MahindraFDPurchaseRequest transactionDetails =  mahindraFDBeansMapper.mapTransactionRequestDetails(fdEntity, null,cptransactionId,transactionId,addressDetailsId,bankDetailsId,saveDetailsResponseCode,nomineemapid, customerId);

		if(transactionDetails!=null) {
		    logger.info("Initiate DB call to save transaction details ..");
		    mmFDTranactionCrudRepository.save(transactionDetails);
		    response.setStatusCode(CommonConstants.TASK_SUCCESS);
		    response.setResultData1(bankDetailsId);
		    response.setMfSysRefNo(saveDetailsResponseCode);
		    logger.info("Transaction Data saved to DB.. Proceed with API call for transaction");
		    //					cptransactionId = "MM2254911442502203866";	//TO COMMENT/REMOVE THE LINE AFTER TESTING COMPLETE
		    apiresp2 = restClientMahindraFD.saveTransactionDetails(fdEntity, new MahindraCustomerKYCDetails(), transactionDetails, "TRANS_CUST", cptransactionId,  apiresp.getMfRefNo(), saveLeadDetails.getDob(), null);

		    if(apiresp2.getStatus().equals("OK")) {
			logger.info("Transaction application no received- "+ apiresp2.getApplicationNo());
			response.setStatusCode(CommonConstants.TASK_SUCCESS);
			response.setApplicationNo(apiresp2.getApplicationNo());
			response.setStatusMsg(apiresp2.getApplicationNo());
		    }else {
			response.setStatusCode(CommonConstants.TASK_API_RESPONSE_ERROR);
			response.setStatusMsg("API_RESPONSE_ERROR_SAVETRANSACTION_DETAILS");

		    }

		    logger.info("Save transction status to database along with application no..");
		    if(mmFDPurchaseStatusCrudRepository.existsByCustomerIdAndTransactionId(customerId, cptransactionId)) {
			logger.info("Tranasction status already exists for given Transaction ID. Update the info");
			MahindraFDTransactionStatus transactionStatus = mmFDPurchaseStatusCrudRepository.findOneByCustomerIdAndTransactionId(customerId, cptransactionId);
			transactionStatus=  mahindraFDBeansMapper.mapSaveTransactionStatus(fdEntity, transactionStatus, apiresp2, cptransactionId, customerId);
			if(transactionStatus!=null) {
			    mmFDPurchaseStatusCrudRepository.save(transactionStatus);
			    logger.info("Existing customer transaction status updated with current transaction details...");
			}
		    }else {
			logger.info("Save new transaction status to database for CP Trasaction ID- "+ cptransactionId);
			MahindraFDTransactionStatus transactionStatus=  mahindraFDBeansMapper.mapSaveTransactionStatus(fdEntity, null, apiresp2, cptransactionId, customerId);
			if(transactionStatus!=null) {
			    mmFDPurchaseStatusCrudRepository.save(transactionStatus);
			    logger.info("Existing customer transaction status updated with current transaction details...");
			}else {
			    logger.info("Transaction status object null.. skipping new entry...");
			}
		    }


		}
	    }else {
		logger.info("Mahindra API call response with error. Skipping transaction save");
		response.setStatusCode(CommonConstants.TASK_API_RESPONSE_ERROR);
		response.setStatusMsg("API_RESPONSE_ERROR_SAVE_DETAILS");
	    }
	    //			}
	}catch(Exception e) {
	    response.setStatusCode(CommonConstants.TASK_FAILURE);
	    response.setStatusMsg("EXCEPTION");
	    logger.error("saveFDPurchaseDetails(): Error processing save request.", e);
	}

	return response;
    }


    /**
     *@apiNote Fetch payment link for the application No
     */
    @Override
    public MahindraResponse getMMFDpaymentLink(String customerId, String mobile, String callbackUrl,String cpTransRefNo, String mfSysRefNo, String applicationNo) {
	logger.info("getMMFDpaymentLink():Get Payment Link for Mobile- "+ mobile + " : APPL_NO: "+ applicationNo);
	MahindraResponse response = new MahindraResponse();
	MahindraApiResponse apiresp = null;

	try {
	    if(mobile!=null) {
		if(applicationNo!=null) {
		    customerId = mmCustomersCrudRepository.getCustomerIdromMobile(mobile);
		    logger.info("getMMFDpaymentLink(): Retrieved customer ID- "+ customerId);
		    MahindraFDTransactionStatus getStatus = mmFDPurchaseStatusCrudRepository.findOneByCustomerIdAndTransactionApplicationNumber(customerId, applicationNo);
		    if(getStatus!=null) {
			if(getStatus.getIsPaymentComplete().equals("N")) {
			    logger.info("Payment status found incomplete.. Proceed to get payment link");
			    apiresp = restClientMahindraFD.getPaymentLink(cpTransRefNo, applicationNo, mfSysRefNo, callbackUrl);
			    if(apiresp.getStatus().equals("OK")) {
				response.setStatusCode(CommonConstants.TASK_SUCCESS);
				response.setPaymentLink(apiresp.getPaymentUrl());
			    }
			}else {
			    logger.info("getMMFDpaymentLink(): Payment status found to be complete.. Skip processing it again for application no- "+ applicationNo);
			    response.setStatusCode(CommonConstants.TASK_SKIPPED);
			    response.setStatusMsg("PAYMENT_ALREADY_PROCESSED");
			}
		    }else {
			logger.info("getMMFDpaymentLink(): No application found customer for application no - "+ applicationNo);
			response.setStatusCode(CommonConstants.TASK_INVALID);
			response.setStatusMsg("NO_DETAILS_FOUND");
		    }
		}else {
		    logger.warn("getMMFDpaymentLink(): application number is null.. Cannot process");
		    response.setStatusCode(CommonConstants.TASK_INVALID);
		    response.setStatusMsg("REQUIRED_DATA_MISSING");
		}
	    }else {
		logger.warn("getMMFDpaymentLink(): Mobile number is null.. Cannot process");
		response.setStatusCode(CommonConstants.TASK_INVALID);
		response.setStatusMsg("REQUIRED_DATA_MISSING");
	    }
	}catch(IncorrectResultSizeDataAccessException e) {
	    logger.error("getMMFDpaymentLink(): Error processing URL for payment..",e);
	    response.setStatusCode(CommonConstants.TASK_FAILURE);
	    response.setStatusMsg("MULTIPLE_APPL_NO_ENTRY_DETECTED");
	}
	catch(Exception e) {
	    logger.error("getMMFDpaymentLink(): Error processing URL for payment..",e);
	    response.setStatusCode(CommonConstants.TASK_FAILURE);
	    response.setStatusMsg("INTERNAL_ERROR");
	}
	return response;
    }


    @Override
    public MahindraResponse verifyPaymentResponse(String customerId, String mobile, String msgkey, String applicationNo) {
	logger.info("verifyPaymentResponse():Get payment confirmation fo application no"+ applicationNo);
	MahindraResponse response = new MahindraResponse();
	MahindraApiResponse apiresp = null;

	try {
	    if(mobile!=null) {

		//				if(customerId!=null || applicationNo!=null) {
		customerId = mmCustomersCrudRepository.getCustomerIdromMobile(mobile);
		logger.info("verifyPaymentResponse(): Retrieved customer ID- "+ customerId);
		if(customerId!=null) {
		    MahindraFDTransactionStatus getStatus = mmFDPurchaseStatusCrudRepository.findOneByCustomerIdAndTransactionApplicationNumber(customerId, applicationNo);
		    if(getStatus!=null) {
			//						if(getStatus.getIsPaymentComplete().equals("N")) {
			logger.info("Verify payment status after redirected from payment gateway...");
			apiresp = restClientMahindraFD.getPGResponseVerification(msgkey);
			if(apiresp.getStatus().equals("OK")) {
			    response.setStatusCode(CommonConstants.TASK_SUCCESS);
			    logger.info("update payment verify response status of application both for failure/success...");
			    int res = mmFDPurchaseStatusCrudRepository.updatePGVerifyResponse(apiresp.getPaymentVerifyResponse(), customerId, applicationNo);
			    logger.info("Payment verify status updated to database- "+ res);
			}else {
			    logger.info("PG verify failed.. Resturning");
			    response.setStatusCode(CommonConstants.TASK_FAILURE);
			}



			/*}else {
							logger.info("getMMFDpaymentLink(): Payment status found to be complete.. Skip processing it again for application no- "+ applicationNo);
							response.setStatusCode(CommonConstants.TASK_SKIPPED);
							response.setStatusMsg("PAYMENT_ALREADY_PROCESSED");
						}*/
		    }else {
			logger.info("verifyPaymentResponse(): No application found against customer for application no - "+ applicationNo);
			response.setStatusCode(CommonConstants.TASK_INVALID);
			response.setStatusMsg("NO_DETAILS_FOUND");
		    }
		}else {
		    logger.warn("verifyPaymentResponse(): No customer found with mobile no.. Rejecting request..");
		    response.setStatusCode(CommonConstants.TASK_INVALID);
		    response.setStatusMsg("REQUIRED_DATA_MISSING");
		}
	    }else {
		logger.warn("verifyPaymentResponse(): Mobile number is null.. Cannot process");
		response.setStatusCode(CommonConstants.TASK_INVALID);
		response.setStatusMsg("REQUIRED_DATA_MISSING");
	    }
	}catch(IncorrectResultSizeDataAccessException e) {
	    logger.error("verifyPaymentResponse(): Error processing PG verify rsponse",e);
	    response.setStatusCode(CommonConstants.TASK_FAILURE);
	    response.setStatusMsg("MULTIPLE_APPL_NO_ENTRY_DETECTED");
	}
	catch(Exception e) {
	    logger.error("verifyPaymentResponse(): Error verifying PG response",e);
	    response.setStatusCode(CommonConstants.TASK_FAILURE);
	    response.setStatusMsg("INTERNAL_ERROR");
	}
	return response;
    }

    @Override
    public List<MahindraFDMappedLocations> getAllDistrictFromState(String statecode) {
	logger.info("getAllDistrictFromState():Get All Districts and pincodes- for state "+ statecode);
	List<MahindraFDMappedLocations> result =null;

	try {
	    if(statecode!=null) {
		result = mmMappedLocationsRepository.findAllByStatecode(statecode);
		logger.info("getAllDistrictFromState(): Total data size- "+ result!=null?result.size():"No Data..");
	    }
	}catch(Exception e) {
	    logger.error("getAllDistrictFromState(): Error fetching data..",e);
	}
	return result;
    }

    @Override
    public MahindraResponse getBankDetailsFromIfsc(String ifscCode) {
	logger.info("getBankDetailsFromIfsc():Get bank details for IFSC-  "+ ifscCode);
	MahindraResponse resp = new MahindraResponse();
	MahindraApiResponse apiresp = null;
	try {
	    if(ifscCode!=null) {
		//				result = mmMappedLocationsRepository.findAllByStatecode(statecode);
		//				logger.info("getAllDistrictFromState(): Total data size- "+ result!=null?result.size():"No Data..");
		apiresp=restClientMahindraFD.getBankDetails(ifscCode);
		if(apiresp!=null) {
		    resp.setMicrCode(apiresp.getMicrCode());
		    resp.setBankName(apiresp.getBankName());
		    resp.setBankBranch(apiresp.getBankBranch());
		}
	    }
	}catch(Exception e) {
	    logger.error("getBankDetailsFromIfsc(): Error fetching Bank details..",e);
	}
	return resp;
    }

    @Override
    public List<Mahindrapurchasehistory> getPurchaseHistory(String mobile) {
	logger.info("getPurchaseHistory():Get Mahindra FD purchase history for customer  "+ mobile);
	List<Mahindrapurchasehistory> transList = null;
	try {
	    if(mobile!=null) {
		String customerId = mmCustomersCrudRepository.getCustomerIdromMobile(mobile);
		if(customerId!=null) {
		    transList = mmPurchaseHistoryRepository.findAllByCustomerid(customerId);
		    logger.info("Total Mahindra FD purchase history record fetched- "+ transList!=null?transList.size():"NO RESULT");
		}else {
		    logger.info("No Mahidra FD customer is registered with mobile no: "+ mobile);
		}
	    }else {
		logger.info("No mobile no to process FD purchase history for mobile"+ mobile);
	    }
	}catch(Exception e) {
	    logger.error("getPurchaseHistory(): Error fetching Bank details..",e);
	}
	return transList;
    }

    @Override
    public MahindraResponse verifyPaymentStatus(String customerId, String mobile, String applicationNo, String emailid) {
	logger.info("verifyPaymentResponse():Get payment confirmation fo application no"+ applicationNo);
	MahindraResponse response = new MahindraResponse();
	MahindraApiResponse apiresp = null;

	try {
	    if(mobile!=null) {

		//				if(customerId!=null || applicationNo!=null) {
		customerId = mmCustomersCrudRepository.getCustomerIdromMobile(mobile);
		logger.info("verifyPaymentStatus(): Retrieved customer ID- "+ customerId);
		if(customerId!=null) {
		    MahindraFDTransactionStatus getStatus = mmFDPurchaseStatusCrudRepository.findOneByCustomerIdAndTransactionApplicationNumber(customerId, applicationNo);
		    if(getStatus!=null) {
			//						if(getStatus.getIsPaymentComplete().equals("N")) {
			logger.info("Verify payment status after redirected from payment gateway...");
			apiresp = restClientMahindraFD.getpaymentStatus(applicationNo);
			if(apiresp.getStatus().equals("OK")) {

			    logger.info("update payment status of application both for failure/success...");
			    if(apiresp.getPaymentStatusCode().equals("MFFDCP-API-S-VAL-00003")) {
				response.setStatusCode(CommonConstants.TASK_SUCCESS);
				response.setStatusMsg(apiresp.getPaymentVerifyResponse());
				int res = mmFDPurchaseStatusCrudRepository.updatepaymentStaus(apiresp.getPaymentStatusCode(), "Y", customerId, applicationNo);
				logger.info("Payment verify status updated to database- "+ res);
				logger.info("Trigger Mahindra Acknowledgement mail...");
				mailAcknowledmentDocumentPostSuccessApplication(applicationNo, null, mobile,emailid,"");

			    }else {
				logger.info("verifyPaymentStatus(): Payment response incomplete");
				response.setStatusCode(CommonConstants.TASK_FAILURE);
				response.setStatusMsg(apiresp.getPaymentVerifyResponse());
			    }
			}else {
			    logger.info("verifyPaymentStatus(): Payment status verify failed.. Resturning");
			    response.setStatusCode(CommonConstants.TASK_FAILURE);
			    response.setStatusMsg("Failed to connect to services.");
			}

			/*}else {
							logger.info("getMMFDpaymentLink(): Payment status found to be complete.. Skip processing it again for application no- "+ applicationNo);
							response.setStatusCode(CommonConstants.TASK_SKIPPED);
							response.setStatusMsg("PAYMENT_ALREADY_PROCESSED");
						}*/
		    }else {
			logger.info("verifyPaymentStatus(): No application found against customer for application no - "+ applicationNo);
			response.setStatusCode(CommonConstants.TASK_INVALID);
			response.setStatusMsg("NO_DETAILS_FOUND");
		    }
		}else {
		    logger.warn("verifyPaymentStatus(): No customer found with mobile no.. Rejecting request..");
		    response.setStatusCode(CommonConstants.TASK_INVALID);
		    response.setStatusMsg("REQUIRED_DATA_MISSING");
		}
	    }else {
		logger.warn("verifyPaymentStatus(): Mobile number is null.. Cannot process");
		response.setStatusCode(CommonConstants.TASK_INVALID);
		response.setStatusMsg("REQUIRED_DATA_MISSING");
	    }
	}catch(IncorrectResultSizeDataAccessException e) {
	    logger.error("verifyPaymentStatus(): Error processing PG verify rsponse",e);
	    response.setStatusCode(CommonConstants.TASK_FAILURE);
	    response.setStatusMsg("MULTIPLE_APPL_NO_ENTRY_DETECTED");
	}
	catch(Exception e) {
	    logger.error("verifyPaymentStatus(): Error verifying PG response",e);
	    response.setStatusCode(CommonConstants.TASK_FAILURE);
	    response.setStatusMsg("INTERNAL_ERROR");
	}
	return response;
    }


    @Override
    public MahindraResponse saveOtherCountryTaxDetails(MahindraFDEntity fdEntity, String customerid) {
	logger.info("Saving customer provided residential tax details in other countries for customer id-"+ customerid + " ("+fdEntity.getMobile()+ " )");
	MahindraResponse response = new MahindraResponse();
	MahindraOtherCountryTaxDetails foreignTaxDetailsFromDB = null;
	MahindraOtherCountryTaxDetails details=null;
	List<MahindraOtherCountryTaxDetails> toinsertrecords = new ArrayList<MahindraOtherCountryTaxDetails>();
	String taxdetailsUniqId;
	try {
	    if(mmCustomersCrudRepository.existsByMobile(fdEntity.getMobile())) {
		logger.info("saveOtherCountryTaxDetails(): Valid customer found.. Proceeding to validate to create/update tax details..");

		for(int i=0;i<fdEntity.getForeignTaxDetails().size();i++) {
		    //					fdEntity.getForeignTaxDetails().get(i).setCustomerid(customerid);
		    details = fdEntity.getForeignTaxDetails().get(i);
		    details.setCustomerid(customerid);
		    foreignTaxDetailsFromDB = null;
		    foreignTaxDetailsFromDB = mmCustOtherCountryTaxDetailsRepository.findOneByCustomeridAndTaxCountryAndTaxidentificationtypeAndTaxidentificationnoAndActive(customerid, details.getTaxCountry(), details.getTaxidentificationtype(), details.getTaxidentificationno()	, "Y");
		    if(foreignTaxDetailsFromDB == null) {

			logger.info("Generate unique ID for FOREIGN TAX Details...");

			int count=101;
			do {
			    taxdetailsUniqId = "TFD-"+fdEntity.getMobile().substring(7)+"-"+ fdEntity.getPan().substring(8)+"-"+details.getTaxidentificationno().substring(0, 2)+"-" +(count++);
			    logger.debug("saveOtherCountryTaxDetails(): Generated UNIQUE ID- " + taxdetailsUniqId);
			}while(mmCustOtherCountryTaxDetailsRepository.existsByTaxid(taxdetailsUniqId));
			logger.info("Generated TFD unique ID- " + taxdetailsUniqId);
			details.setTaxid(taxdetailsUniqId);
			//						mmCustOtherCountryTaxDetailsRepository.save(details);
			logger.info("saveOtherCountryTaxDetails(): No records found with provided details. Inserting into database identification no- "+ details.getTaxidentificationno());
			toinsertrecords.add(details);
			logger.info("saveOtherCountryTaxDetails(): Record INSERTED into database for identification no- "+ details.getTaxidentificationno());

		    }else {
			logger.info("saveOtherCountryTaxDetails(): Details already found in database for the customer.. Update the details for txn no..."+ details.getTaxidentificationno());
			foreignTaxDetailsFromDB = mahindraFDBeansMapper.mapForeignTaxDetailsforcustomer(details, foreignTaxDetailsFromDB);
			mmCustOtherCountryTaxDetailsRepository.save(foreignTaxDetailsFromDB);
			logger.info("saveOtherCountryTaxDetails(): Record UPDATED into database for identification no- "+ details.getTaxidentificationno());

		    }

		}
		logger.info("saveOtherCountryTaxDetails(): Completed looping through the details for update/insert for customer ID-  "+ customerid);
		if(toinsertrecords.size()>0) {
		    logger.info("Process TRC list insert- ");
		    mmCustOtherCountryTaxDetailsRepository.save(toinsertrecords);
		    logger.info("saveOtherCountryTaxDetails(): Records inserted successfully for customer ID- "+ customerid);
		}

	    }else {
		logger.info("saveOtherCountryTaxDetails(): customer not found. Skipped saving details for mobile no: ."+ fdEntity.getMobile());


	    }
	}catch(Exception e) {
	    response.setStatusCode(CommonConstants.TASK_FAILURE);
	    logger.error("saveOtherCountryTaxDetails(): Error processing save request.", e);

	}

	return response;
    }


    @Override
    public MahindraResponse retryDocumentupload(String mobile, String customerid,String applicationo) {
	logger.info("Request received to retry failed document upload for application - "+ applicationo);
	MahindraResponse response = new MahindraResponse();
	MahindraKycDocuments details =null;
	MahindraApiResponse apiresp=null;
	try {
	    customerid = mmCustomersCrudRepository.getCustomerIdromMobile(mobile);
	    if(customerid!=null) {
		List<MahindraKycDocUploadStatus> faileddoctypes = mmKycDocUploadRepository.findAllByApplicationnoAndUploadstatus(applicationo, "N");
		if(faileddoctypes.size()>0) {
		    for(int i=0;i<faileddoctypes.size();i++) {
			logger.info("Process reupload for failed doc type- "+ faileddoctypes.get(i).getImagetype());
			details = mmCustKycDocumentRepository.findOneByImageTypeAndCustomeridAndActive(faileddoctypes.get(i).getImagetype(), customerid, "Y");
			apiresp = restClientMahindraFD.savekycdocuments(faileddoctypes.get(i).getHoldingtype(), details, faileddoctypes.get(i).getCptransrefid(), faileddoctypes.get(i).getMftransRefId(), faileddoctypes.get(i).getApplicationno());
			if(apiresp.getStatus().equals("OK")) {
			    logger.info("retryDocumentupload(): Re-upload successfull. Save doc Upload status to DB..");
			    int stat= mmKycDocUploadRepository.reuploaddocstatus("Y", apiresp.getResponsemsg(),faileddoctypes.get(i).getSerial(), customerid);
			    logger.info("retryDocumentupload: Reloaded status updated in DB- "+ stat);
			}else {
			    logger.info("Upload KYC doc failed for category.." + faileddoctypes.get(i).getImagetype());
			    //			kycuploadstatus.setUploadstatus("N");
			    //			kycuploadstatus.setApiresponse(apiresp.getResponsemsg());
			}

		    }
		}else {
		    logger.info("No failed documents found to preocess to re-upload...");
		    response.setStatusCode(CommonConstants.TASK_SUCCESS);
		}
	    }else {
		logger.info("retryDocumentupload(): No registered customer found for given mobile no to process KYC doc re-upload: "+ mobile);
	    }
	}catch(Exception e) {
	    response.setStatusCode(CommonConstants.TASK_FAILURE);
	    logger.error("retryDocumentupload(): Error processing save request.", e);

	}
	return response;

    }


    @Override
    public MahindraResponse mahindrauploadDocument(String mobile, String customerid, MahindraFDEntity fdEntity, String cprefno,String mfrefno,String applno) {
	logger.info("Request received to upload customer docuements to Mahindra...");
	MahindraApiResponse apiresp=null;
	MahindraResponse response = new MahindraResponse();
	List<MahindraKycDocuments> kycdocuments = new ArrayList<MahindraKycDocuments>();
	MahindraKycDocuments details =null;
	try {
	    if(mmCustomersCrudRepository.existsByMobile(fdEntity.getMobile())) {
		logger.info("mahindrauploadDocument(): Valid customer found.. Proceeding to validate to create/update document details..");

		//				mahindraFDBeansMapper.mapkycAndTRCDocuments(fdEntity, kycdocuments, customerid);

		//				Photograph
		details = mmCustKycDocumentRepository.findOneByImageTypeAndCustomeridAndActive("MFPH", customerid, "Y");
		if(details==null) {
		    logger.debug("No records found.. New documents entry will be inserted..");
		    details = new MahindraKycDocuments();
		}else{
		    logger.info("Photograph already exist for customer.. update details for the selected serial no.. ");
		}
		//				details = new MahindraKycDocuments();
		details.setMobile(fdEntity.getMobile());
		details.setCustomerid(customerid);
		details.setImageType("MFPH");
		if(fdEntity.getKycphotoproof()!=null) {
		    try {
			logger.debug("Photograph- "+FilenameUtils.getExtension(fdEntity.getKycphotoproof().getOriginalFilename()) + " -->"+Base64.encodeBase64String(fdEntity.getKycphotoproof().getBytes()));
			details.setImage(fdEntity.getKycphotoproof().getBytes());
			details.setImageextension(FilenameUtils.getExtension(fdEntity.getKycphotoproof().getOriginalFilename()));
		    }catch(Exception e){
			logger.error("Error processing uploaded image..... Skip type MFPH",e);
		    }
		}
		kycdocuments.add(details);

		//				PAN docuements
		logger.info("Searching for available PAN proof documents in system..");
		details = mmCustKycDocumentRepository.findOneByImageTypeAndCustomeridAndActive("C", customerid, "Y");
		if(details==null) {
		    logger.debug("No records found.. New documents entry will be inserted..");
		    details = new MahindraKycDocuments();
		}else{
		    logger.info("PAN documents already exist for customer.. update details for the selected serial no.. ");
		}
		//				details = new MahindraKycDocuments();
		details.setMobile(fdEntity.getMobile());
		details.setCustomerid(customerid);
		details.setImageType("C");
		details.setImagesubtype("Identity");
		if(fdEntity.getKycpanproof()!=null) {
		    try {
			logger.debug("PAN- "+FilenameUtils.getExtension(fdEntity.getKycpanproof().getOriginalFilename()) + " -->"+Base64.encodeBase64String(fdEntity.getKycphotoproof().getBytes()));
			details.setImage(fdEntity.getKycpanproof().getBytes());
			details.setImageextension(FilenameUtils.getExtension(fdEntity.getKycpanproof().getOriginalFilename()));
		    }catch(Exception e){
			logger.error("Error processing uploaded image..... Skip type C",e);
		    }
		}
		kycdocuments.add(details);

		logger.info("Searching for available Cancelled checque proof documents in system..");
		details = mmCustKycDocumentRepository.findOneByImageTypeAndCustomeridAndActive("MFCC", customerid, "Y");
		if(details==null) {
		    logger.debug("No records found.. New documents entry will be inserted..");
		    details = new MahindraKycDocuments();
		}else{
		    logger.info("Cancelled cheque documents already exist for customer.. update details for the selected serial no.. ");
		}
		//				details = new MahindraKycDocuments();
		details.setMobile(fdEntity.getMobile());
		details.setCustomerid(customerid);
		details.setImageType("MFCC");
		details.setImagesubtype("BankCancelledCheque");
		if(fdEntity.getCanecelledcheque()!=null) {
		    try {
			logger.debug("CANCELLED CHEQUE- "+FilenameUtils.getExtension(fdEntity.getCanecelledcheque().getOriginalFilename()) + " -->"+Base64.encodeBase64String(fdEntity.getKycphotoproof().getBytes()));
			details.setImage(fdEntity.getCanecelledcheque().getBytes());
			details.setImageextension(FilenameUtils.getExtension(fdEntity.getKycpanproof().getOriginalFilename()));
		    }catch(Exception e){
			logger.error("Error processing uploaded image..... Skip type MFCC",e);
		    }
		}
		kycdocuments.add(details);

		logger.info("Searching for available address proof documents in system..");
		details = mmCustKycDocumentRepository.findOneByImageTypeAndCustomeridAndActive(fdEntity.getAddressproofType(), customerid, "Y");
		if(details==null) {
		    logger.debug("No records found.. New documents entry will be inserted..");
		    details = new MahindraKycDocuments();
		}else{
		    logger.info("Address proof documents already exist for customer.. update details for the selected serial no.. ");
		}
		//				details = new MahindraKycDocuments();
		details.setMobile(fdEntity.getMobile());
		details.setCustomerid(customerid);
		details.setImageType(fdEntity.getAddressproofType());
		details.setImagesubtype("Address");
		if(fdEntity.getKycaddressproof()!=null) {
		    logger.debug("ADDRESS PROOF- "+FilenameUtils.getExtension(fdEntity.getKycaddressproof().getOriginalFilename()) + " -->"+Base64.encodeBase64String( fdEntity.getKycphotoproof().getBytes()));
		    details.setImage(fdEntity.getKycaddressproof().getBytes());
		    details.setImageextension(FilenameUtils.getExtension(fdEntity.getKycaddressproof().getOriginalFilename()));
		}
		details.setImagerefrenceno(fdEntity.getAddressproofrefno());

		try {
		    if(!fdEntity.getAddressproofpxpirydate().isEmpty()) {
			details.setImagexpirydate(dbfmt.format(uifmt.parse(fdEntity.getAddressproofpxpirydate())));
		    }
		} catch (ParseException e) {
		    logger.error("mapkycAndTRCDocuments(): Failed parsing Document expiry date... update date manully- "+ fdEntity.getAddressproofpxpirydate(),e);
		}
		kycdocuments.add(details);

		//				Add TRC documents if available
		if(fdEntity.getKycdocuments()!=null && fdEntity.getKycdocuments().size()>0) {
		    for(int i =0;i<fdEntity.getKycdocuments().size();i++) {
			kycdocuments.add(fdEntity.getKycdocuments().get(i));
		    }
		}else {
		    logger.info("No foreign TRC based documents to process for customer...");
		}

		mmCustKycDocumentRepository.save(kycdocuments);

		logger.info("mahindrauploadDocument(): Completed looping through the details for update/insert for customer ID-  "+ customerid);

		//				Start upload documents to Mahidnra
		logger.info("Proceeding with document upload to Mahindra API for cutomer- "+ customerid);
		List<MahindraKycDocUploadStatus> uploadStatus = new ArrayList<MahindraKycDocUploadStatus>();
		MahindraKycDocUploadStatus kycuploadstatus = null;
		for(int i=0;i<kycdocuments.size();i++) {
		    logger.info("Uploading document of category- "+ kycdocuments.get(i).getImageType());

		    kycuploadstatus = new MahindraKycDocUploadStatus();
		    kycuploadstatus.setMobile(fdEntity.getMobile());
		    kycuploadstatus.setCustomerid(customerid);
		    kycuploadstatus.setCptransrefid(cprefno);
		    kycuploadstatus.setMftransRefId(mfrefno);
		    kycuploadstatus.setApplicationno(applno);
		    kycuploadstatus.setImagetype( kycdocuments.get(i).getImageType());
		    kycuploadstatus.setImagesubtype(kycdocuments.get(i).getImagesubtype());
		    kycuploadstatus.setHoldingtype(fdEntity.getHoldingOptions());

		    apiresp = restClientMahindraFD.savekycdocuments(fdEntity.getHoldingOptions(), kycdocuments.get(i), cprefno, mfrefno, applno);
		    logger.info("Uploading document of category- "+ kycdocuments.get(i).getImageType() + " : response: -> "+ apiresp.getStatus());
		    if(apiresp.getStatus().equals("OK")) {
			logger.info("Save doc Upload status to DB-");
			//						int stat= mmCustKycDocumentRepository.uploaddocuploadstatus(kycdocuments.get(i).getImageType(), customerid, "Y");
			//						logger.debug("Mahindra FD KYC upload document DB save status- "+ stat);
			kycuploadstatus.setUploadstatus("Y");
			kycuploadstatus.setApiresponse(apiresp.getResponsemsg());
		    }else {
			logger.info("Upload KYC doc failed for category.." + kycdocuments.get(i).getImageType());
			kycuploadstatus.setUploadstatus("N");
			kycuploadstatus.setApiresponse(apiresp.getResponsemsg());
		    }

		    uploadStatus.add(kycuploadstatus);
		}

		logger.info("Upload all KYC upload status to database for application no- "+ applno + " : Total: "+ uploadStatus.size());
		mmKycDocUploadRepository.save(uploadStatus);
		logger.info("Upload complete.. "+ applno);

		response.setStatusCode(CommonConstants.TASK_SUCCESS);
		response.setStatusMsg("SUCCESS");

	    }else {
		logger.info("mahindrauploadDocument(): customer not found. Skipped saving details for mobile no: ."+ fdEntity.getMobile());
		response.setStatusCode(CommonConstants.TASK_FAILURE);
		response.setStatusMsg("MOBILE_NOT_REGISTERED");

	    }
	}catch(Exception e) {
	    response.setStatusCode(CommonConstants.TASK_FAILURE);
	    logger.error("mahindrauploadDocument(): Error processing save request.", e);

	}
	return response;
    }


    @Async
    @Override
    public void mailAcknowledmentDocumentPostSuccessApplication(String applicationno, String customerid,
	    String mobile,String email, String name) {
	logger.info("mailAcknowledmentDocumentPostSuccessApplication(): Request received to fetch application acknowledgement document and mail if application successful for mobile- ."+ mobile + " :application no: "+ applicationno);
	MahindraApiResponse apiresp=null;

	try {
	    if(mmCustomersCrudRepository.existsByMobile(mobile)) {
		apiresp = restClientMahindraFD.getacknowledgemetdocument(applicationno);
		if(apiresp.getStatus().equals("SUCCESS")) {
		    String doc= (apiresp.getAcknowledgementdoc().split(";")[1]).split(",")[1];
		    logger.info("Final split acknowledged doc- "+ doc);
		    mailSenderInterface.sendMahindraFDAcknowledgementdocument(doc, email, mobile, name,applicationno);
		}else {
		    logger.info("GenerateDoc status failed- "+ apiresp.getStatus());
		}
	    }else {
		logger.info("mailAcknowledmentDocumentPostSuccessApplication(): No customer details found. Skipped..");
	    }

	}catch(Exception e) {
	    logger.error("mailAcknowledmentDocumentPostSuccessApplication(): Error while processing acknowledgement document for customer..",e);
	}

	logger.info("mailAcknowledmentDocumentPostSuccessApplication(): returning");

    }


    @Override
    public MahindraFDRenewalEntity getFDRenewalDetails(String customerid, String mobile, String applicationno) {
	logger.info("getFDRenewalDetails(): Request received to fetch renewal details for application no: "+ applicationno + " : customerid: "+ customerid);

	MahindraFDRenewalEntity renewwaldetails = null;
	if(mmFDPurchaseStatusCrudRepository.existsByCustomerIdAndTransactionApplicationNumber(customerid, applicationno)) {

	}else {
	    logger.info("getFDRenewalDetails(): No details found for application for the customerid- "+ applicationno);
	}
	return renewwaldetails;
    }





}
