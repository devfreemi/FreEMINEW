package com.freemi.database.implementations;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freemi.common.util.BseRelatedActions;
import com.freemi.controller.interfaces.InvestmentConnectorBseInterface;
import com.freemi.database.interfaces.BseCustomerAddressCrudRepository;
import com.freemi.database.interfaces.BseCustomerBankDetailsCrudRespository;
import com.freemi.database.interfaces.BseCustomerCrudRespository;
import com.freemi.database.interfaces.BseTransCrudRepository;
import com.freemi.database.interfaces.BseTransactionsView;
import com.freemi.database.interfaces.PortfolioCrudRepository;
import com.freemi.database.interfaces.TopFundsRepository;
import com.freemi.database.service.BseEntryManager;
import com.freemi.entity.database.MfTopFundsInventory;
import com.freemi.entity.database.UserBankDetails;
import com.freemi.entity.general.UserProfile;
import com.freemi.entity.investment.AddressDetails;
import com.freemi.entity.investment.BseAllTransactionsView;
import com.freemi.entity.investment.BseMFInvestForm;
import com.freemi.entity.investment.MFAdditionalPurchaseForm;
import com.freemi.entity.investment.SelectMFFund;

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
				bseCustomerCrudRespository.updateBseRegistrationStatus(customerid);
				logger.info("Customer registration status updated successfully");
			}else{
				logger.info("Failed to push customer details to BSE platform. Failure reason- "+ bseResponse);
				flag = bseResponse;
			}
			
		}
		
		return flag;
	}

	@Override
	public boolean savetransactionDetails(SelectMFFund selectedMFFund) {
		// TODO Auto-generated method stub
		boolean flag = true;

		//Generate client ID
		selectedMFFund.setOrderPlaceTime(new Date());
		logger.info("Transaction started to save BSE transaction data");
		bseTransCrudRepository.saveAndFlush(selectedMFFund);
		return flag;
	}

	@Override
	public List<SelectMFFund> getMFOrderHistory(String value) {
		String clientId = null;
		List<SelectMFFund> trasactionDetails = null;
		if(bseCustomerCrudRespository.existsByMobile(value)){
			 clientId = bseCustomerCrudRespository.getRegisteredUserClientId(value);
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
			client = bseCustomerCrudRespository.getRegisteredUserClientId(mobileNumber);
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

	@Override
	public UserProfile getCustomerDetailsByMobile(String mobile) {
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
		String clientId= bseCustomerCrudRespository.getRegisteredUserClientId(mobileNumber);
		
		BseAllTransactionsView selectedFolioTransDetails = bseTransactionsView.findOneByPortfoilioAndSchemeCodeAndClientIDAndInvestType(portfolio, schemeCode, clientId,investType);
		
		
		return selectedFolioTransDetails;
	}

	@Override
	public String getClientIdfromMobile(String mobile) {
		// TODO Auto-generated method stub
		return bseCustomerCrudRespository.getRegisteredUserClientId(mobile);
	}

	@Override
	public BseAllTransactionsView getFundDetailsForRedemption(String portfolio, String schemeCode,String investType,
			String mobileNumber) {
		String clientId= bseCustomerCrudRespository.getRegisteredUserClientId(mobileNumber);
		
		BseAllTransactionsView selectedFolioTransDetails = bseTransactionsView.findOneByPortfoilioAndSchemeCodeAndClientIDAndInvestType(portfolio, schemeCode, clientId,investType);
		
		return selectedFolioTransDetails;
	}


}
