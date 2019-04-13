package com.freemi.database.implementations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.freemi.common.util.BseRelatedActions;
import com.freemi.common.util.CommonConstants;
import com.freemi.controller.interfaces.InvestmentConnectorBseInterface;
import com.freemi.database.interfaces.BseCustomerAddressCrudRepository;
import com.freemi.database.interfaces.BseCustomerBankDetailsCrudRespository;
import com.freemi.database.interfaces.BseCustomerCrudRespository;
import com.freemi.database.interfaces.BseCustomerFATCACrudRepository;
import com.freemi.database.interfaces.BseCustomerNomineeCrudRepository;
import com.freemi.database.interfaces.BseFundsExplorerRepository;
import com.freemi.database.interfaces.BseMandateCrudRepository;
import com.freemi.database.interfaces.BseOrderEntryResponseRepository;
import com.freemi.database.interfaces.BseSelectedCategoryFundsRepository;
import com.freemi.database.interfaces.BseTop15lsSipViewCrudReositry;
import com.freemi.database.interfaces.BseTransCountCrudRepository;
import com.freemi.database.interfaces.BseTransCrudRepository;
import com.freemi.database.interfaces.BseTransHistoryViewCrudRepository;
import com.freemi.database.interfaces.BseTransactionsView;
import com.freemi.database.interfaces.MfCamsFolioCrudRepository;
import com.freemi.database.interfaces.PortfolioCrudRepository;
import com.freemi.database.interfaces.TopFundsRepository;
import com.freemi.database.service.BseEntryManager;
import com.freemi.entity.bse.BseApiResponse;
import com.freemi.entity.database.MfTopFundsInventory;
import com.freemi.entity.database.UserBankDetails;
import com.freemi.entity.general.UserProfile;
import com.freemi.entity.investment.AddressDetails;
import com.freemi.entity.investment.BseAllTransactionsView;
import com.freemi.entity.investment.BseDailyTransCounter;
import com.freemi.entity.investment.BseFundsScheme;
import com.freemi.entity.investment.BseMFInvestForm;
import com.freemi.entity.investment.BseMFSelectedFunds;
import com.freemi.entity.investment.BseMFTop15lsSip;
import com.freemi.entity.investment.BseMandateDetails;
import com.freemi.entity.investment.BseOrderEntryResponse;
import com.freemi.entity.investment.BsemfTransactionHistory;
import com.freemi.entity.investment.MFCamsFolio;
import com.freemi.entity.investment.MFFatcaDeclareForm;
import com.freemi.entity.investment.MFNominationForm;
import com.freemi.entity.investment.SelectMFFund;
import com.freemi.entity.investment.TransactionStatus;

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

	/*@Autowired
	MailSenderHandler mailSenderHandler;*/

	private static final Logger logger = LogManager.getLogger(BseEntryServiceImpl.class);

	@Override
	public String saveCustomerDetails(BseMFInvestForm customerForm) {
		logger.info("saveCustomerDetails(): Begin registration process for customer PAN- "+ customerForm.getPan1());
		String flag = "SUCCESS";
		String customerid ="";
		boolean registerCustomerToBse = false;


		if(bseCustomerCrudRespository.existsByPan1(customerForm.getPan1())){
			logger.info("Account already exist with given primary PAN number");
			//			Check account registered at BSE end. IF status is no, only try to push exiiting customer details to BSE
			String bseRegisterStatus = bseCustomerCrudRespository.getBseRegistrationStatus(customerForm.getPan1());
			if(bseRegisterStatus.equals("Y")){
				logger.info("Customer already registered both at FREEMI and BSE");
				flag="EXIST";
			}else{
				logger.info("Customer registered at FREEMI but BSE registration not complete. Try to register at BSE end only with current details");
				customerid= bseCustomerCrudRespository.getClientIdFromPan(customerForm.getPan1());
				customerForm.setClientID(customerid);
				customerForm.getBankDetails().setClientID(customerid);
				customerForm.getAddressDetails().setClientID(customerid);
				customerForm.getNominee().setClientID(customerid);
				registerCustomerToBse = true;
			}
		}else{

			//Generate client ID
			int loop =1;

			do{
				if(loop>=2){
					logger.warn("Previously generated client ID already exist for another- "+ customerid);
				}
				customerid=BseRelatedActions.generateID(customerForm.getInvName(), customerForm.getPan1(), null, customerForm.getMobile(),loop++);

			}while(bseCustomerCrudRespository.existsByClientID(customerid));

			System.out.println("Generated login ID for customer with PAN - "+customerForm.getPan1()+ " : " + customerid);
			customerForm.setClientID(customerid);
			customerForm.getBankDetails().setClientID(customerid);
			customerForm.getAddressDetails().setClientID(customerid);
			customerForm.getNominee().setClientID(customerid);
			customerForm.getFatcaDetails().setClientID(customerid);
			customerForm.setRegistrationTime(new Date());
			logger.info("Transaction started to save BSE customer registrastion data");

			bseCustomerCrudRespository.save(customerForm);
			bseCustomerCrudRespository.flush();
			registerCustomerToBse = true;
			logger.info("Customer record saved successfully to database");
		}

		if(registerCustomerToBse){

			logger.info("Customer registered at FREEMI portal. Begin to push customer details at BSE end");
			String bseResponse = investmentConnectorBseInterface.saveCustomerRegistration(customerForm, null);


			if(bseResponse.equalsIgnoreCase("SUCCESS")){
				//User registration successful at BSE portal
				try{
					bseCustomerCrudRespository.updateBseRegistrationStatus(customerid);
					/*//					Call FATCADeclaration
					fatcaResponse = investmentConnectorBseInterface.fatcaDeclaration(customerForm, null);
					if(fatcaResponse.getResponseCode().equalsIgnoreCase("100")){
						bseCustomerFATCACrudRepository.updateFatcaDeclarationStatus(true, customerid);
					}else{
						bseCustomerFATCACrudRepository.updateFatcaDeclarationStatus(false, customerid);
					}*/

				}catch(Exception e){
					logger.error("Failed to update customer successful registration status to database, notify admin");
				}
				logger.info("Customer registration status updated successfully");
			}else{
				logger.info("Failed to push customer details to BSE platform. Failure reason- "+ bseResponse);
				flag = bseResponse;
			}

		}

		return flag;
	}

	@Override
	public TransactionStatus savetransactionDetails(SelectMFFund selectedMFFund, String mandateId) {
		// TODO Auto-generated method stub
		boolean flag = true;

		//Save details to database, process to BSE, update database with response
		TransactionStatus transStatus = new TransactionStatus();
		BseOrderEntryResponse bseResult =null;
		try{
			selectedMFFund.setOrderPlaceTime(new Date());

			//Generate BSE related ref no
			StringBuffer ref = new StringBuffer();
			ref.append("P").append(selectedMFFund.getClientID().substring(6)).append(Calendar.getInstance().getTimeInMillis());
			selectedMFFund.setBseRefNo(ref.toString());

			logger.info("MF purchae request saved to database for transaction id- "+ selectedMFFund.getTransactionID());

			//		Generate BSE transaction Reference no
			Date date = new Date();
			StringBuffer transNumber = new StringBuffer();
			transNumber.append((new SimpleDateFormat("yyyyMMdd").format(date))).append(CommonConstants.BSE_MEMBER_ID);
			long counter= getCurrentDayNextTransCount(date);
			for(int i=1;i<(6-Long.toString(counter).length());i++){
				transNumber.append("0");
			}
			transNumber.append(Long.toString(counter));

			logger.info("Requesting BSE to register transaction for client id- : "+ selectedMFFund.getClientID() + " : TransactionCode: "+ transNumber.toString()+ ": Scheme code: "+ selectedMFFund.getSchemeCode() + " : Amount: "+ selectedMFFund.getInvestAmount());

			//Call BSE

			bseResult = investmentConnectorBseInterface.processCustomerPurchaseRequest(selectedMFFund, transNumber.toString(),mandateId);

			logger.info("Status of requested transaction - "+ bseResult.getSuccessFlag());

			if(bseResult.getSuccessFlag().equalsIgnoreCase("0")){
				logger.info("Transaction is successful. Saving request to Database");

				if(CommonConstants.BSE_CALL_TEST_ENABLED.equalsIgnoreCase("N")){
					selectedMFFund = bseTransCrudRepository.saveAndFlush(selectedMFFund);
					bseOrderEntryResponseRepository.saveAndFlush(bseResult);
				}

				transStatus.setSuccessFlag("S");
				transStatus.setStatusMsg(bseResult.getBsereMarks());
				transStatus.setBseOrderNoFromResponse(bseResult.getOrderNoOrSipRegNo());

			}else if (bseResult.getSuccessFlag().equalsIgnoreCase("000")){
				logger.info("Transaction disabled. Reason- "+bseResult.getBsereMarks());
				transStatus.setSuccessFlag("S");
				transStatus.setStatusMsg(bseResult.getBsereMarks());
			}else{
				logger.info("Transaction has failed. Transaction declined from saving to database. Reason- "+bseResult.getBsereMarks());
				transStatus.setSuccessFlag("F");
				transStatus.setStatusMsg(bseResult.getBsereMarks());
			}

		}catch(Exception e){
			logger.error("Failed to save transaction Details for MF Purchase",e);
			if(bseResult.getSuccessFlag().equalsIgnoreCase("0")){
				transStatus.setSuccessFlag("SF");		// success in 
				transStatus.setStatusMsg("Transaction successful in BSE but failed to save at FREEMI.");
				flag = false;
			}
		}


		return transStatus;
	}

	@Override
	public List<SelectMFFund> getMFOrderHistory(String value) {
		String clientId = null;
		List<SelectMFFund> trasactionDetails = null;
		if(bseCustomerCrudRespository.existsByMobile(value)){
			clientId = bseCustomerCrudRespository.getClientIdFromMobile(value);
			trasactionDetails =  bseTransCrudRepository.getByClientID(clientId);
		}else{
			logger.info("No registered BSE customer by mobile number found to show transaction data - "+ value);
		}

		return trasactionDetails;
	}

	@Override
	public List<BseMFInvestForm> getCustomerDetails(String customerId) {
		// TODO Auto-generated method stub

		return bseCustomerCrudRespository.getByClientID(customerId);
	}

	@Override
	public List<BseAllTransactionsView> getCustomerAllTransactionRecords(String clientID, String mobileNumber, String panNumber) {
		// TODO Auto-generated method stub
		String client=null;
		List<BseAllTransactionsView> groupedTransationDetails = null;
		if(bseCustomerCrudRespository.existsByMobile(mobileNumber)){
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
	public List<BseMFInvestForm> getCustomerByPan(String pan) {
		// TODO Auto-generated method stub
		return bseCustomerCrudRespository.findByPan1(pan);
	}

	@Override
	public List<String> getSelectedAmcPortfolio(String amcCode, String clientId) {
		// TODO Auto-generated method stub
		return portfolioCrudRepository.getSelectedPortFolio(amcCode, clientId);
	}

	@Override
	public String getCustomerPanfromMobile(String mobile) {
		// TODO Auto-generated method stub
		return bseCustomerCrudRespository.getCustomerPanNumberFromMobile(mobile);
	}

	@Override
	public boolean checkIfTransIdExist(String generatedTransId) {
		// TODO Auto-generated method stub
		return bseTransCrudRepository.existsByTransactionID(generatedTransId);
	}

	@Override
	public boolean updateCustomerData(BseMFInvestForm custerProfileData) {
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

		UserProfile userProfile = new UserProfile();

		BseMFInvestForm investorProfileData = bseCustomerCrudRespository.getByMobile(mobile);

		try{
			userProfile.setUid(investorProfileData.getClientID());
			userProfile.setMobile(investorProfileData.getMobile());
			userProfile.setMail(investorProfileData.getEmail());
			userProfile.setPan(investorProfileData.getPan1());
			userProfile.setFname(investorProfileData.getInvName());
			userProfile.setGender(investorProfileData.getGender());

			userProfile.setAccountHolder(investorProfileData.getInvName());

			userProfile.setAccountNumber(investorProfileData.getBankDetails().getAccountNumber());
			userProfile.setIfscCode(investorProfileData.getBankDetails().getIfscCode());
			userProfile.setBankName(investorProfileData.getBankDetails().getBankName());
			userProfile.setAccountType(investorProfileData.getBankDetails().getAccountType());
			userProfile.setBranch(investorProfileData.getBankDetails().getBankBranch());
			userProfile.setBranchCity(investorProfileData.getBankDetails().getBankCity());
			userProfile.setAccountState(investorProfileData.getBankDetails().getBranchState());

			userProfile.setHouseNumber(investorProfileData.getAddressDetails().getAddress1());
			userProfile.setAddress1(investorProfileData.getAddressDetails().getAddress2());
			userProfile.setAddress2(investorProfileData.getAddressDetails().getAddress3());
			//		userProfile.setAddress3(addressDetails[2]);
			userProfile.setCity(investorProfileData.getAddressDetails().getCity());
			userProfile.setState(investorProfileData.getAddressDetails().getState());
			userProfile.setPincode(investorProfileData.getAddressDetails().getPinCode());

		}catch(Exception e){
			logger.error("DB to UserProfile entity mapping error",e);
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

		BseAllTransactionsView selectedFolioTransDetails = bseTransactionsView.findOneByPortfoilioAndSchemeCodeAndClientIDAndInvestType(portfolio, schemeCode, clientId,investType);


		return selectedFolioTransDetails;
	}

	@Override
	public String getClientIdfromMobile(String mobile) {
		// TODO Auto-generated method stub
		return bseCustomerCrudRespository.getClientIdFromMobile(mobile);
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
	public MFCamsFolio getCamsFundsDetailsForRedeem(String code, String mobile, String folioNumber) {
		
//		CAMS funds details redemption
//		MFCamsFolio folio = 
		
//		for CAMS, need to map rta code to scheme code
		String schemeCode="";
		MFCamsFolio folio = null;
		List<BseFundsScheme> schemCodes = bseFundsExplorerRepository.findAllByRtaCode(code);
		logger.info("Total schemecode found for RTA code- "+ schemCodes.size());
		for(int i =0;i<schemCodes.size();i++){
			if(!schemCodes.get(i).getSettlementType().equalsIgnoreCase("L1")){
				schemeCode = schemCodes.get(i).getSchemeCode();
				break;
			}
		}
		logger.info("Scheme code for redemption of the fund - "+schemeCode);
		
		folio = mfCamsFolioCrudRepository.findOneByFolioNumber(folioNumber);
		if(folio!=null){
			folio.setSchemeCode(schemeCode);
		}else{
			logger.info("getCamsFundsDetailsForRedeem(): found no records agaisnt folio in database. for folio: ."+ folioNumber);
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
			e.printStackTrace();
		}
		return Long.valueOf(l);
	}


	@Override
	public String investmentProfileStatus(String mobileNumber) {
		logger.info("Search for customer BSE ID");
		String flag = "F";
		String aofUploadStatus = "";
		try{
			if(bseCustomerCrudRespository.existsByMobile(mobileNumber)){
				//				String registrationStatus = bseCustomerCrudRespository.getBseRegistrationStatus(mobileNumber);
				//				logger.info("Customer BSE registration status- "+ registrationStatus);
				//				if(registrationStatus.equalsIgnoreCase("Y")){
				aofUploadStatus=bseCustomerCrudRespository.getBseRegistrationAOFStatus(mobileNumber);
				logger.info("Customer BSE registration status- "+ aofUploadStatus);

				if(aofUploadStatus.split(",")[0].equals("Y") && aofUploadStatus.split(",")[1].equals("Y")){
					flag="PROFILE_READY";
				}else if(aofUploadStatus.split(",")[0].equals("Y") && aofUploadStatus.split(",")[1].equals("N")){
					flag="AOF_PENDING";
				}
				else{
					flag="REGISTRATION_INCOMPLETE";
				}
				logger.info("Customer registration aof upload status- "+ aofUploadStatus);
				//				flag= aofUploadStatus;
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
	public String upddateCustomerFormSignature(String mobile, String pan, String signatureData) {
		logger.info("Processing to update customer signature for BSE profile- "+ mobile);
		int result = 0;
		String flag="SUCCESS";
		try{
			if(bseCustomerCrudRespository.existsByMobile(mobile)){
				String clientId = bseCustomerCrudRespository.getClientIdFromMobile(mobile);
				result = bseCustomerCrudRespository.uploadCustomerSignature(clientId, pan, signatureData);
				logger.info("Status for signature update- "+ result);
			}else{
				logger.info("Cusotmer not found to update signature- "+ mobile);
			}
		}catch(Exception e){
			logger.error("Failed to update customer signature data. ",e);
			flag="FAIL";
		}
		return flag;
	}



	@Override
	public String uploadAOFFormStatus(String mobileNumber, String uploadStatus) {

		logger.info("Begining process to upload AOF Form.");
		String currentStatus = "SUCCESS";

		try{
			int i = bseCustomerCrudRespository.updateAofUploadStatus(mobileNumber);
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
		logger.info("Begining process to upload AOF Form.");
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
				mandate.setAmount(amount);
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
	public BseMFInvestForm getCustomerInvestFormData(String mobile) {
		logger.info("Querying to get registered investment form data for mobile- "+ mobile);
		BseMFInvestForm investmentFormdata = null;
		try{
			if(bseCustomerCrudRespository.existsByMobile(mobile)){
				String clientId = bseCustomerCrudRespository.getClientIdFromMobile(mobile);
				investmentFormdata = bseCustomerCrudRespository.findOneByClientID(clientId);
				AddressDetails address = bseCustomerAddressCrudRepository.findOneByClientID(clientId);
				UserBankDetails bank = bseCustomerBankDetailsCrudRespository.getOne(clientId);
				MFNominationForm nominee = bseCustomerNomineeCrudRepository.findOneByClientID(clientId);
				System.out.println("Address- "+ address + " : Bank: "+ bank);

				investmentFormdata.setAddressDetails(address!=null?address:new AddressDetails());
				investmentFormdata.setBankDetails(bank!=null?bank:new UserBankDetails());
				investmentFormdata.setNominee(nominee!=null?nominee:new MFNominationForm());

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

			mandateDetails = bseMandateCrudRepository.findAllByClientCodeAndAccountNumber(clientId, accountNumber);	

		}catch(Exception e){
			logger.error("Failed to query database to fetch mandate details",e);
		}

		return mandateDetails;
	}

	@Override
	public Page<BseFundsScheme> getpaginatedFundsList(Pageable p) {
		logger.info("Querying to schemes by page wise- ");
		Page<BseFundsScheme> fundDetails = null;
		try{

			fundDetails = bseFundsExplorerRepository.findAll(p);

		}catch(Exception e){
			logger.error("Failed to query database to fetch mandate details",e);
		}

		return fundDetails;
	}

	@Override
	public List<BseMFSelectedFunds> getAllSelectedFunds() {
		logger.info("Querying to schemes by page wise- ");
		List<BseMFSelectedFunds> fundDetails = null;
		try{

			fundDetails = bseSelectedCategoryFundsRepository.findAll();

		}catch(Exception e){
			logger.error("Failed to query database to fetch sellected category funds",e);
		}

		return fundDetails;
	}

	@Override
	public List<BseMFSelectedFunds> getFundsByCategory(String category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BseApiResponse saveFatcaDetails(BseMFInvestForm customerForm) {
		//					Call FATCADeclaration

		BseApiResponse fatcaResponse=null;
		String clientId=null;
		logger.info("Request received to process FATCA details fro customer- "+ customerForm.getPan1());
		if(bseCustomerCrudRespository.existsByMobile(customerForm.getMobile())){
			clientId = bseCustomerCrudRespository.getClientIdFromMobile(customerForm.getMobile());
			fatcaResponse = investmentConnectorBseInterface.fatcaDeclaration(customerForm, null);
			logger.info("FATCA upload status from BSE for customer- "+clientId + " : "  + fatcaResponse.getResponseCode());
			if(fatcaResponse.getResponseCode().equalsIgnoreCase("100")){
				logger.info("Updating FATCA status to database for customer- "+ clientId);
				try{
					int res= bseCustomerFATCACrudRepository.updateFatcaDeclarationStatus(true, clientId);
					logger.info("Returned FATCA status update to database for customer- "+ clientId + " : "+res);
				}catch(Exception e){
					logger.error("Failed to update FATCA status to database for customer- "+clientId ,e);
				}
			}

		}

		return fatcaResponse;
	}

	@Override
	public String updateFatcaStatus(String clientId, String status, String responseCode, String message) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEmdandateDetails(String mobile, String clientCode, String mandateType, String accNumber) {
		//		Call FATCADeclaration

		BseMandateDetails mandateId=null;
		String clientId=null;
		logger.info("Request received to fetch customer emandate registration details for client ID- "+ clientCode + " : mandate type: "+ mandateType);
		try{
		if(bseCustomerCrudRespository.existsByMobile(mobile)){
			UserBankDetails userbankDetails = getCustomerBankDetails(clientCode);
			
			logger.info("Look for mandate corresponding to registered bank- ");

			mandateId= bseMandateCrudRepository.findOneByClientCodeAndAccountNumberAndMandateActive(clientCode, userbankDetails.getAccountNumber(),"Y");
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
		if(bseCustomerCrudRespository.existsByMobile(mobile)){
			pan = bseCustomerCrudRespository.getCustomerPanNumberFromMobile(mobile);
			
			folios = mfCamsFolioCrudRepository.findAllByPan(pan);
			
			logger.info("Folio details look up complete");

		}
		}catch(Exception e){
			logger.error("Failed to query database to fetch exisint mandates. ",e);
		}

		return folios;
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
