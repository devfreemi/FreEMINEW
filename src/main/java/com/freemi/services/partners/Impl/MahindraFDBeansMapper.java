package com.freemi.services.partners.Impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.freemi.entity.investment.mahindra.MahindraApiResponse;
import com.freemi.entity.investment.mahindra.MahindraCustomerAddress;
import com.freemi.entity.investment.mahindra.MahindraCustomerBankDetails;
import com.freemi.entity.investment.mahindra.MahindraCustomerDetails;
import com.freemi.entity.investment.mahindra.MahindraCustomerKYCDetails;
import com.freemi.entity.investment.mahindra.MahindraFDEntity;
import com.freemi.entity.investment.mahindra.MahindraFDPurchaseRequest;
import com.freemi.entity.investment.mahindra.MahindraFDSaveLeadDetails;
import com.freemi.entity.investment.mahindra.MahindraFDTransactionStatus;

@Component
public class MahindraFDBeansMapper {

	private static final Logger logger = LogManager.getLogger(MahindraFDBeansMapper.class);
	
	SimpleDateFormat uifmt = new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat dbfmt =new SimpleDateFormat("yyyy-MM-dd");
	
	protected MahindraFDEntity mapCustomerDetailsToEntity(MahindraFDEntity fdEntity, MahindraCustomerDetails customerDetails, List<MahindraCustomerAddress> custAddress, List<MahindraCustomerBankDetails> bankdetails) {
		/*
		 * SimpleDateFormat dbfmt =new SimpleDateFormat("yyyy-MM-dd"); SimpleDateFormat
		 * uifmt =new SimpleDateFormat("dd/MM/yyyy");
		 */
		
		
		fdEntity.setFirstName(customerDetails.getFirstName());
		fdEntity.setMiddleName(customerDetails.getMiddleName());
		fdEntity.setLastName(customerDetails.getLastName());
		fdEntity.setEmail(customerDetails.getEmail());
		fdEntity.setMaritalStatus(customerDetails.getMaritalStatus());
		fdEntity.setGender(customerDetails.getGender());
		fdEntity.setOccupation(customerDetails.getOccupation());
		fdEntity.setHoldingOptions(customerDetails.getHolderType());
		try {
//			System.out.println("DOB - "+ dbfmt.parse(dbfmt.format(customerDetails.getDob())));
			System.out.println("DOB - "+ dbfmt.format(customerDetails.getDob()));
			fdEntity.setDob(customerDetails.getDob()!=null?(uifmt.format(dbfmt.parse(dbfmt.format(customerDetails.getDob())))):null);
			System.out.println("Converted Date- "+ fdEntity.getDob());
		} catch (ParseException e) {
			fdEntity.setDob(null);
		}
		
		
		fdEntity.setNationality(customerDetails.getNationality());
		fdEntity.setCountryOfBirth(customerDetails.getCountryOfBirth());
		fdEntity.setCityOfBirth(customerDetails.getCityOfBirth());
		
		if(custAddress.size()!=0) {
			logger.info("Found addresses to map to fdEntity");
			fdEntity.setAddress1(custAddress.get(0).getAddress11());
			fdEntity.setAddress2_1(custAddress.get(0).getAddress12());
			fdEntity.setAddress3_1(custAddress.get(0).getAddress13());
			fdEntity.setAddressCity1(custAddress.get(0).getCity1());
			fdEntity.setAddressDistrict1(custAddress.get(0).getDistrict1());
			fdEntity.setAddressstate1(custAddress.get(0).getState1());
			fdEntity.setAddCountry1(custAddress.get(0).getCountry1());
			fdEntity.setAddresspincode1(custAddress.get(0).getPincode1());
		}
		
		if(bankdetails.size()!=0) {
			logger.info("Found bank details to map to fdEntity");
			fdEntity.setIfscCode(bankdetails.get(0).getIfscCode());
			fdEntity.setBankname(bankdetails.get(0).getBankName());
			fdEntity.setBankbranch(bankdetails.get(0).getBranchName());
			fdEntity.setMicrCode(bankdetails.get(0).getMicrCode());
			fdEntity.setAccountNumber(bankdetails.get(0).getAccountNumber());
		}
		
		return fdEntity;
	}
	
	protected MahindraCustomerDetails mapNewCustomerDetails(MahindraFDEntity fdEntity, MahindraCustomerDetails customerDetails) {

		
		//		MahindraCustomerDetails customerDetails = null;
		try {
			if(fdEntity!=null) {
				if(customerDetails == null) {
					logger.info("Genereate new bean for customerDetails");
					customerDetails = new MahindraCustomerDetails();
				}else {
					logger.info("Request for processing new customer..");
				}
				customerDetails.setMobile(fdEntity.getMobile());
				customerDetails.setCustomerId(fdEntity.getMobile()+"-"+fdEntity.getPan());
				
				customerDetails.setFirstName(fdEntity.getFirstName());
				customerDetails.setMiddleName(fdEntity.getMiddleName());
				customerDetails.setLastName(fdEntity.getLastName());
				customerDetails.setEmail(fdEntity.getEmail());
				customerDetails.setPan(fdEntity.getPan());
				
				customerDetails.setDob(fdEntity.getDob()!=null?(dbfmt.parse(dbfmt.format(uifmt.parse(fdEntity.getDob())))):null);
				
//				customerDetails.setDob(fdEntity.getDob());
				customerDetails.setGender(fdEntity.getGender());
				customerDetails.setPrimaryHolderTitle(fdEntity.getPrimaryHolderTitle());
				customerDetails.setMaritalStatus(fdEntity.getMaritalStatus());
				customerDetails.setOccupation(fdEntity.getOccupation());
				customerDetails.setHolderType(fdEntity.getHoldingOptions());
				customerDetails.setCityOfBirth(fdEntity.getCityOfBirth());
				customerDetails.setCountryOfBirth(fdEntity.getCountryOfBirth());
				customerDetails.setNationality(fdEntity.getNationality());
			}
		}catch(Exception e) {
			logger.error("mapSaveLeadDetails(): Error mapping data- ",e);
		}
		return  customerDetails;

	}
	
protected MahindraCustomerDetails updateFDCustomerDetails(MahindraFDEntity fdEntity, MahindraCustomerDetails customerDetails) {

		logger.info("updateFDCustomerDetails(): Update exisitng customer data...");
		//		MahindraCustomerDetails customerDetails = null;
		try {
			if(fdEntity!=null) {
				if(customerDetails != null) {
					logger.info("Genereate new bean for customerDetails");
//					customerDetails = new MahindraCustomerDetails();
//					customerDetails.setMobile(fdEntity.getMobile());
//					customerDetails.setCustomerId(fdEntity.getMobile()+"-"+fdEntity.getPan());
					
//					customerDetails.setFirstName(fdEntity.getFirstName());
					customerDetails.setMiddleName(fdEntity.getMiddleName());
					customerDetails.setLastName(fdEntity.getLastName());
//					customerDetails.setEmail(fdEntity.getEmail());
//					customerDetails.setPan(fdEntity.getPan());
					
					customerDetails.setDob(fdEntity.getDob()!=null?(dbfmt.parse(dbfmt.format(uifmt.parse(fdEntity.getDob())))):null);
					
//					customerDetails.setDob(fdEntity.getDob());
					customerDetails.setGender(fdEntity.getGender());
					customerDetails.setPrimaryHolderTitle(fdEntity.getPrimaryHolderTitle());
					customerDetails.setMaritalStatus(fdEntity.getMaritalStatus());
					customerDetails.setOccupation(fdEntity.getOccupation());
					
					customerDetails.setCityOfBirth(fdEntity.getCityOfBirth());
					customerDetails.setCountryOfBirth(fdEntity.getCountryOfBirth());
					customerDetails.setNationality(fdEntity.getNationality());
				}else {
					logger.info("updateFDCustomerDetails(): No data tom ap for update..");
				}

			}
		}catch(Exception e) {
			logger.error("mapSaveLeadDetails(): Error mapping data- ",e);
		}
		return  customerDetails;

	}


	protected MahindraCustomerAddress mapCustomerAddress(MahindraFDEntity fdEntity, MahindraCustomerAddress addressDetails, String customerid) {


		//		MahindraCustomerDetails customerDetails = null;
		try {
			if(fdEntity!=null) {
				if(addressDetails == null) {
					logger.info("mapCustomerAddress(): Genereate new bean for customer address Details");
					addressDetails = new MahindraCustomerAddress();
				}
				logger.info("Request for processing mapping customer address..");
//				System.out.println(fdEntity.getMobile() + " -> "+fdEntity.getAddress1() + " -> "+  fdEntity.getAddress3_1());
				addressDetails.setCustomerId(customerid);
				addressDetails.setMobile(fdEntity.getMobile());
				addressDetails.setAddress11(fdEntity.getAddress1());
				addressDetails.setAddress12(fdEntity.getAddress2_1());
				addressDetails.setAddress13(fdEntity.getAddress3_1());
				addressDetails.setCountry1(fdEntity.getAddCountry1());
				addressDetails.setState1(fdEntity.getAddressstate1());
				addressDetails.setDistrict1(fdEntity.getAddressDistrict1());
				addressDetails.setCity1(fdEntity.getAddressCity1());
				addressDetails.setPincode1(fdEntity.getAddresspincode1());

				//			Generate ID for 
//				System.out.println(addressDetails.getAddress11());

			}else {
				logger.info("mapCustomerAddress(): fdEntity is null.... ");
			}
		}catch(Exception e) {
			logger.error("mapSaveLeadDetails(): Error mapping data- ",e);
		}
		return  addressDetails;

	}
	
	
	protected MahindraCustomerBankDetails mapBankDetails(MahindraFDEntity fdEntity, MahindraCustomerBankDetails bankDetails, String customerid) {


		//		MahindraCustomerDetails customerDetails = null;
		try {
			if(fdEntity!=null) {
				if(bankDetails == null) {
					logger.info("mapBankDetails(): Genereate new bean for customer Bank Details");
					bankDetails = new MahindraCustomerBankDetails();
				}
				logger.info("mapBankDetails(): Request for processing customer address..");
				bankDetails.setCustomerId(customerid);
				bankDetails.setMobile(fdEntity.getMobile());
				
				bankDetails.setAccountNumber(fdEntity.getAccountNumber());
				bankDetails.setBankName(fdEntity.getBankname());
				bankDetails.setIfscCode(fdEntity.getIfscCode());
				bankDetails.setMicrCode(fdEntity.getMicrCode());
				bankDetails.setBranchName(fdEntity.getBankbranch());
//				bankDetails.setState(fdEntity.getb);

			}else {
				logger.info("mapBankDetails(): fdEntity is null.... ");
			}
		}catch(Exception e) {
			logger.error("mapBankDetails(): Error mapping data- ",e);
		}
		return  bankDetails;

	}
	
	
	protected MahindraCustomerKYCDetails mapkycDetails(MahindraFDEntity fdEntity,MahindraCustomerKYCDetails kycdetails, String customerid) {
		//		MahindraCustomerDetails customerDetails = null;
		logger.debug("mapkycDetails(): Map Mahindra KYC details....");
		try {
			if(fdEntity!=null) {
				if(kycdetails == null) {
					logger.info("mapkycDetails(): Genereate new bean for customer KYC Details");
					kycdetails = new MahindraCustomerKYCDetails();
				}
				logger.info("mapkycDetails(): Request for processing customer KYC..");
				kycdetails.setCustomerId(customerid);
				kycdetails.setMobile(fdEntity.getMobile());
				kycdetails.setKycUplaodedPhoto(fdEntity.getKycphotoproof().getBytes());
				kycdetails.setKycIdentityProofDoc(fdEntity.getKycpanproof().getBytes());
				kycdetails.setKycAddressProofDoc(fdEntity.getKycaddressproof().getBytes());
			}else {
				logger.info("mapkycDetails(): kycdetails is null.... ");
			}
		}catch(Exception e) {
			logger.error("mapkycDetails(): Error mapping data- ",e);
		}
		return  kycdetails;

	}
	
	
	protected MahindraFDPurchaseRequest mapTransactionRequestDetails(MahindraFDEntity fdEntity, MahindraFDPurchaseRequest purchaseRequest,String transactionId,  String cpTransactionId, String addressUsedId, String bankDetailsId, String saveDetailsResponseCode, String customerid) {

		//		MahindraCustomerDetails customerDetails = null;
		try {
			if(fdEntity!=null) {
				if(purchaseRequest == null) {
					logger.info("mapTransactionRequestDetails(): Genereate new bean for Transaction Details");
					purchaseRequest = new MahindraFDPurchaseRequest();
				}
				
				logger.info("Request for processing new transaction for customer..");
				
				purchaseRequest.setMobile(fdEntity.getMobile());
				purchaseRequest.setCustomerId(customerid);
				purchaseRequest.setTransactionType("NEW");	//NEW OR ADDTIONAL. Internal flag
				purchaseRequest.setMfSysRefNo(saveDetailsResponseCode);
				purchaseRequest.setCpTransRefNo(cpTransactionId);
				purchaseRequest.setSchemeCode(fdEntity.getSchemeCode());
				purchaseRequest.setTransactionInitiationTime(new Date());
				purchaseRequest.setReferenceType("TRANS_CUST");
				purchaseRequest.setCategory(fdEntity.getCategory());
				purchaseRequest.setSavingAmount(fdEntity.getSaveAmount());
				purchaseRequest.setSchemeTypeCode(fdEntity.getScheme());	//Micro/Cumulative/Non-cumulative
				purchaseRequest.setInterestFrequency(fdEntity.getIntFreq());
				purchaseRequest.setTenure(fdEntity.getSaveTenure());
				purchaseRequest.setInterestRate(fdEntity.getInterestRate());
				purchaseRequest.setIsAutoRenewal(fdEntity.isAutorenew()?"Y":"N");
				purchaseRequest.setPaymentMode("1");
				purchaseRequest.setPaymentInstruction("F");
				purchaseRequest.setCpLocationCode("KOLKATA");
				purchaseRequest.setAddressReferenceNo(addressUsedId);
				purchaseRequest.setRepaymentBankRefId(bankDetailsId);
				purchaseRequest.setIsTnCAccepted("Y");
				purchaseRequest.setIsNomineeChosen(fdEntity.isNomieechosen()?"Y":"N");
				purchaseRequest.setIsDecAccepted("Y");
				purchaseRequest.setPaymentRefNo("");
				purchaseRequest.setIsTDSApplicable("Y");
				purchaseRequest.setIsPaymentDiscAccepted("Y");
				purchaseRequest.setIsCersaiDiscAccepted("Y");
				purchaseRequest.setRenewalFor("F");
				purchaseRequest.setMaritalStatus(fdEntity.getMaritalStatus());

			}else {
				logger.info("mapTransactionRequestDetails(): fdEntity is null.... ");
			}
		}catch(Exception e) {
			logger.error("mapTransactionRequestDetails(): Error mapping data- ",e);
		}
		return  purchaseRequest;

	}
	
	
	protected MahindraFDSaveLeadDetails mapSaveLeadDetails(MahindraFDEntity fdEntity, MahindraFDSaveLeadDetails saveLeadDetails,String transactionId, String customerId) {
		/*
		 * SimpleDateFormat uifmt = new SimpleDateFormat("dd/mm/yyyy"); SimpleDateFormat
		 * dbfmt = new SimpleDateFormat("dd-mmm-yyyy");
		 */
		
		try {
			if(fdEntity!=null) {
				if(saveLeadDetails == null) {
					logger.info("mapSaveLeadDetails(): Genereate new bean for customer Bank Details");
					saveLeadDetails = new MahindraFDSaveLeadDetails();
				}
				saveLeadDetails.setCustomerId(customerId);
				saveLeadDetails.setMobile(fdEntity.getMobile());
				
				saveLeadDetails.setFullName(fdEntity.getFirstName() + " "+ fdEntity.getMiddleName() + " "+ fdEntity.getLastName());
				saveLeadDetails.setEmailId(fdEntity.getEmail());
				saveLeadDetails.setDob(fdEntity.getDob()!=null?(dbfmt.format(uifmt.parse(fdEntity.getDob()))):null );
				saveLeadDetails.setPan(fdEntity.getPan());
				
				saveLeadDetails.setSaveAmount(fdEntity.getSaveAmount());
				saveLeadDetails.setCpTransRefNo(transactionId);
				
				saveLeadDetails.setSalutation(fdEntity.getPrimaryHolderTitle());
				saveLeadDetails.setCpLocationCode("Kolkata");
//				saveLeadDetails.setLeadType("01");
				saveLeadDetails.setLeadType(fdEntity.getHoldingOptions());
				saveLeadDetails.setRefType("TRANS_CUST");

			}else {
				logger.info("mapSaveLeadDetails(): fdEntity is null.... ");
			}
		}catch(Exception e) {
			logger.error("mapSaveLeadDetails(): Error mapping data- ",e);
		}
		return  saveLeadDetails;
		
	}
	
	/**
	 *  @apiNote Map transaction response data from API to save in database
	 * @param fdEntity
	 * @param transStatus
	 * @param tranasctionApiResponse
	 * @param transactionId
	 * @param customerId
	 * @return
	 */
	protected MahindraFDTransactionStatus mapSaveTransactionStatus(MahindraFDEntity fdEntity,MahindraFDTransactionStatus transStatus, MahindraApiResponse tranasctionApiResponse,String transactionId, String customerId) {
		
		try {
			if(fdEntity!=null) {
				if(transStatus == null) {
					logger.info("mapSaveTransactionStatus(): Genereate new bean for TransactionStatus Details");
					transStatus = new MahindraFDTransactionStatus();
				}
				
				transStatus.setCustomerId(customerId);
				transStatus.setTransactionId(transactionId);
				transStatus.setSaveTransactionRequestBody(tranasctionApiResponse.getRequestJson());
				transStatus.setTransactionStatus(tranasctionApiResponse.getStatus());
				transStatus.setTransactionApplicationNumber(tranasctionApiResponse.getStatus().equals("OK")?tranasctionApiResponse.getApplicationNo():null);
				transStatus.setIsTransactionComplete(tranasctionApiResponse.getStatus().equals("OK")?"Y":"N");
				
			}else {
				logger.info("mapSaveTransactionStatus(): fdEntity is null.... ");
			}
		}catch(Exception e) {
			logger.error("mapSaveTransactionStatus(): Error mapping data- ",e);
		}
		
		return transStatus;
		
	}


}
