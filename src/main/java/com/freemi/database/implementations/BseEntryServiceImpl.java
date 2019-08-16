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
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.freemi.common.util.BseRelatedActions;
import com.freemi.common.util.CommonConstants;
import com.freemi.common.util.InvestFormConstants;
import com.freemi.controller.interfaces.InvestmentConnectorBseInterface;
import com.freemi.database.interfaces.BseCamsByCategoryRepository;
import com.freemi.database.interfaces.BseCustomerAddressCrudRepository;
import com.freemi.database.interfaces.BseCustomerBankDetailsCrudRespository;
import com.freemi.database.interfaces.BseCustomerCrudRespository;
import com.freemi.database.interfaces.BseCustomerFATCACrudRepository;
import com.freemi.database.interfaces.BseCustomerNomineeCrudRepository;
import com.freemi.database.interfaces.BseFundsExplorerRepository;
import com.freemi.database.interfaces.BseCustomerMfRepository;
import com.freemi.database.interfaces.BseKarvyByCategoryRepository2;
import com.freemi.database.interfaces.BseMandateCrudRepository;
import com.freemi.database.interfaces.BseOrderEntryResponseRepository;
import com.freemi.database.interfaces.BseSelectedCategoryFundsRepository;
import com.freemi.database.interfaces.BseTop15lsSipViewCrudReositry;
import com.freemi.database.interfaces.BseTransCountCrudRepository;
import com.freemi.database.interfaces.BseTransCrudRepository;
import com.freemi.database.interfaces.BseTransHistoryViewCrudRepository;
import com.freemi.database.interfaces.BseTransactionsView;
import com.freemi.database.interfaces.MfCamsFolioCrudRepository;
import com.freemi.database.interfaces.MfNavDataCrudRepository;
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
import com.freemi.entity.investment.MFCamsValueByCategroy;
//import com.freemi.entity.investment.MFKarvyFundsView;
import com.freemi.entity.investment.MfAllInvestorValueByCategory;
import com.freemi.entity.investment.MFKarvyValueByCategory2;
import com.freemi.entity.investment.MFNominationForm;
import com.freemi.entity.investment.MfNavData;
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
	
	@Autowired
	BseCamsByCategoryRepository bseCamsByCategoryRepository;
	
	@Autowired
	BseCustomerMfRepository bseCustomerMfRepository;
	
	@Autowired
	BseKarvyByCategoryRepository2 bseKarvyByCategoryRepository2;
	
	@Autowired
	MfNavDataCrudRepository mfNavDataCrudRepository;
	

	/*@Autowired
	MailSenderHandler mailSenderHandler;*/

	private static final Logger logger = LogManager.getLogger(BseEntryServiceImpl.class);

	@Override
	public String saveCustomerDetails(BseMFInvestForm customerForm) {
		logger.info("saveCustomerDetails(): Begin registration process for customer PAN- "+ customerForm.getPan1());
		String flag = "SUCCESS";
		String customerid ="";
		boolean registerCustomerToBse = false;
		
		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-mm-dd");
		SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("dd/mm/yyyy");


		if(bseCustomerCrudRespository.existsByPan1(customerForm.getPan1())){
			logger.info("Account already exist with given primary PAN number");
			//			Check account registered at BSE end. IF status is no, only try to push existing customer details to BSE
			String bseRegisterStatus = bseCustomerCrudRespository.getBseRegistrationStatus(customerForm.getPan1());
			if(bseRegisterStatus.equals("Y")){
				logger.info("Customer already registered both at FREEMI and BSE");
				flag="EXIST";
			}else{
				logger.info("Customer registered at FREEMI but BSE registration not complete. Update current data and try to register at BSE end only with current details");
//				System.out.println("line- "+customerForm.getBankDetails().getSerialNo());
				customerid= bseCustomerCrudRespository.getClientIdFromPan(customerForm.getPan1());
				customerForm.setClientID(customerid);
				customerForm.getBankDetails().setClientID(customerid);
				customerForm.getAddressDetails().setClientID(customerid);
				customerForm.getNominee().setClientID(customerid);
				customerForm.getFatcaDetails().setClientID(customerid);
				
				BseMFInvestForm toUpdateForm = bseCustomerCrudRespository.getByMobile(customerForm.getMobile());
				mapUpdatedCustomerMfData(customerForm,toUpdateForm);
				bseCustomerCrudRespository.saveAndFlush(toUpdateForm);
				logger.info("Customer current details saved/updated in database...");
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

			logger.info("Generated login ID for customer with PAN - "+customerForm.getPan1()+ " : " + customerid);
			customerForm.setClientID(customerid);
			customerForm.getBankDetails().setClientID(customerid);
			customerForm.getAddressDetails().setClientID(customerid);
			customerForm.getNominee().setClientID(customerid);
			customerForm.getFatcaDetails().setClientID(customerid);
			customerForm.setRegistrationTime(new Date());
			logger.info("Transaction started to save BSE customer registration data");
			
			BseMFInvestForm customerCopy = customerForm;
//			Convert date to db format
			logger.info("saveCustomerDetails(): Convert DOB Format: "+ customerCopy.getInvDOB());
			try {
				Date date1 = simpleDateFormat2.parse(customerCopy.getInvDOB());
				String bseFormatDob = simpleDateFormat1.format(date1);
				logger.info("DOB field converted to DB format- "+ bseFormatDob);
				customerCopy.setInvDOB(bseFormatDob);
			} catch (ParseException e) {
				logger.error("saveCustomerDetails(): failed to convert date. Leaving date to default format. ",e);
				
			}
			
			bseCustomerCrudRespository.save(customerCopy);
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
					logger.info("BSE_SAVE_TRANSACTION is disabled. Trsansaction not saved  to databse..");
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
	public List<BseMFInvestForm> getCustomerDetails(String customerId) {
		// TODO Auto-generated method stub

		return bseCustomerCrudRespository.getByClientID(customerId);
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
	public List<BseMFInvestForm> getCustomerByPan(String pan) {
		// TODO Auto-generated method stub
		logger.info("getCustomerByPan(): Get customers investment profile- "+ pan);
		
		return bseCustomerCrudRespository.findByPan1(pan);
	}

	@Override
	public List<String> getSelectedAmcPortfolio(String schemeCode, String pan,String rtaAgent) {
		// TODO Auto-generated method stub
		logger.info("Querying to find exisitng portfolios for selected AMC for clientID- "+ pan);
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
		String pan="";
		pan= bseCustomerCrudRespository.getCustomerPanNumberFromMobile(mobile);
		return pan;
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

		UserProfile userProfile = null;

		try{
			if(bseCustomerCrudRespository.existsByMobileAndAccountActive(mobile,"Y")){
				

				BseMFInvestForm investorProfileData = bseCustomerCrudRespository.getByMobile(mobile);

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
			String pan = bseCustomerCrudRespository.getCustomerPanNumberFromMobile(mobile);
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
			String pan = bseCustomerCrudRespository.getCustomerPanNumberFromMobile(mobile);
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
			if(bseCustomerCrudRespository.existsByMobileAndAccountActive(mobileNumber,"Y")){
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
			if(bseCustomerCrudRespository.existsByMobileAndAccountActive(mobile,"Y")){
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
	@Cacheable(value = "mutualfundexplorerdata", unless = "#result == null")
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
	public BseApiResponse saveFatcaDetails(BseMFInvestForm customerForm) {
		//					Call FATCADeclaration

		BseApiResponse fatcaResponse=null;
		String clientId=null;
		logger.info("Request received to process FATCA details fro customer- "+ customerForm.getPan1());
		if(bseCustomerCrudRespository.existsByMobileAndAccountActive(customerForm.getMobile(),"Y")){
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
		if(bseCustomerCrudRespository.existsByMobileAndAccountActive(mobile,"Y")){
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
		if(bseCustomerCrudRespository.existsByMobileAndAccountActive(mobile,"Y")){
			pan = bseCustomerCrudRespository.getCustomerPanNumberFromMobile(mobile);
			
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
			pan = bseCustomerCrudRespository.getCustomerPanNumberFromMobile(mobile);
			
			folios = bseCamsByCategoryRepository.findAllByPan(pan);
			
			logger.info("Folio details by category look up complete");

		}
		}catch(Exception e){
			logger.error("Failed to query database to fetch exising customer. ",e);
		}

		return folios;
	}

	@Override
	public List<MfAllInvestorValueByCategory> getCustomersAllFoliosByCategory(String mobile, String pan) {
		List<MfAllInvestorValueByCategory> folios=null;
		logger.info("getCustomersKarvyInvByCategory(): Request received to fetch customer Karvy folio details by category for client ID- "+ mobile + " :PAN NO: "+ pan);
		
		try{
		if(bseCustomerCrudRespository.existsByMobileAndAccountActive(mobile,"Y")){
			pan = bseCustomerCrudRespository.getCustomerPanNumberFromMobile(mobile);
			
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
			pan = bseCustomerCrudRespository.getCustomerPanNumberFromMobile(mobile);
			
			folios =bseKarvyByCategoryRepository2.getAllByPan(pan);
			
			logger.info("getCustomersKarvyInvByCategory2(): Karvy Folio details by category look up complete");

		}
		}catch(Exception e){
			logger.error("getCustomersKarvyInvByCategory2(): Failed to query database to fetch exising customer karvy folio. ",e);
		}
		
		return folios;
	}
	
	
	private BseMFInvestForm mapUpdatedCustomerMfData(BseMFInvestForm customerForm,BseMFInvestForm toupdateForm){
		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-mm-dd");
		SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("dd/mm/yyyy");
		
		//		Basic details
		
		
		
		toupdateForm.setPan2(customerForm.getPan2());
		toupdateForm.setInvName(customerForm.getInvName());
		toupdateForm.setApplicant2(customerForm.getApplicant2());
//		toupdateForm.setInvDOB(customerForm);
		
		try {
			Date date1 = simpleDateFormat2.parse(customerForm.getInvDOB());
			String bseFormatDob = simpleDateFormat1.format(date1);
			logger.info("DOB field converted to DB format for record update- "+ bseFormatDob);
			toupdateForm.setInvDOB(bseFormatDob);
		} catch (ParseException e) {
			logger.error("mapUpdatedCustomerMfData(): failed to convert date. Leaving date to default format. ",e.getMessage());
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
		
		toupdateForm.getNominee().setNomineeName(customerForm.getNominee().getNomineeName());
		toupdateForm.getNominee().setNomineeRelation(customerForm.getNominee().getNomineeRelation());
		
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
		toupdateForm.setLastModifiedDate(new Date());
		return toupdateForm;
	}

	@Override
	@Cacheable(value = "isinnavhistory", unless = "#result == null", key = "#isin")
	public List<MfNavData> getnavdataByISIN(String isin) {
		logger.info("Query database to fetch ISIN NAV data- "+ isin);
		List<MfNavData> navdata = new ArrayList<MfNavData>();
		
		try{
			navdata= mfNavDataCrudRepository.getAllNavOfIsin(isin);
			 
		}catch(Exception e){
			logger.info("Failed to fecth NAV hsitory.",e);
		}
		
		return navdata;
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
