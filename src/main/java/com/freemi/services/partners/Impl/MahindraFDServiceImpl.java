package com.freemi.services.partners.Impl;

import java.util.Calendar;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Service;

import com.freemi.common.util.CommonConstants;
import com.freemi.common.util.CommonTask;
import com.freemi.database.respository.mahindra.MMCustAddressCrudRepository;
import com.freemi.database.respository.mahindra.MMCustKYCCrudRepository;
import com.freemi.database.respository.mahindra.MMCustomersBankRepository;
import com.freemi.database.respository.mahindra.MMCustomersCrudRepository;
import com.freemi.database.respository.mahindra.MMFDPurchaseStatusCrudRepository;
import com.freemi.database.respository.mahindra.MMFDTranactionCrudRepository;
import com.freemi.database.respository.mahindra.MMLeadDetailsCrudRepository;
import com.freemi.database.respository.mahindra.MMMappedLocationsRepository;
import com.freemi.database.respository.mahindra.MMSchemesCrudRepository;
import com.freemi.entity.investment.mahindra.MahindraApiResponse;
import com.freemi.entity.investment.mahindra.MahindraCustomerAddress;
import com.freemi.entity.investment.mahindra.MahindraCustomerBankDetails;
import com.freemi.entity.investment.mahindra.MahindraCustomerDetails;
import com.freemi.entity.investment.mahindra.MahindraCustomerKYCDetails;
import com.freemi.entity.investment.mahindra.MahindraFDEntity;
import com.freemi.entity.investment.mahindra.MahindraFDMappedLocations;
import com.freemi.entity.investment.mahindra.MahindraFDPurchaseRequest;
import com.freemi.entity.investment.mahindra.MahindraFDSaveLeadDetails;
import com.freemi.entity.investment.mahindra.MahindraFDTransactionStatus;
import com.freemi.entity.investment.mahindra.MahindraResponse;
import com.freemi.entity.investment.mahindra.MahindraSchemesList;
import com.freemi.services.interfaces.MahindraFDServiceInterface;
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

	/*
	 * @Autowired MMFDPurchaseStatusCrudRepository mmFDPurchaseStatusCrudRepository;
	 */


	private static final Logger logger = LogManager.getLogger(MahindraFDServiceImpl.class);


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

			logger.info("Total address found- "+ customeraddress.size());
			logger.info("Total bank details enrty found- "+ customerBankDetails.size());

			fdEntity = mahindraFDBeansMapper.mapCustomerDetailsToEntity(fdEntity, customer,customeraddress,customerBankDetails);

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
				response.setResultData1(bankDetails.getBankDetailsId());
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
	 *@apiNote Save Mahindra customer KYC details
	 */
	@Override
	public MahindraResponse saveCustomerKYCDetails(MahindraFDEntity fdEntity, String customerid) {
		MahindraResponse response = new MahindraResponse();
		logger.info("saveCustomerKYCDetails(): Save/update customer KYC details for customer - "+ fdEntity.getMobile());

		try {
			if(mmCustKYCCrudRepository.existsByCustomerIdAndActive(customerid,"Y")) {
				logger.info("saveCustomerKYCDetails(): Customer KYC details already present for given mobile no, update details/skip");

				response.setStatusCode(CommonConstants.TASK_SKIPPED);
				response.setStatusMsg("Already Exist, skipped");
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
	public MahindraResponse saveFDPurchaseDetails(MahindraFDEntity fdEntity, String transactionId, String saveDetailsResponseCode, String addressDetailsId, String bankDetailsId,String customerId) {
		MahindraResponse response = new MahindraResponse();
		logger.info("saveFDPurchaseDetails(): Initiate purcahse Transaction for mobile- "+ fdEntity.getMobile());
		MahindraApiResponse apiresp=null;
		MahindraApiResponse apiresp2=null;
		try {
			/*if(mmCustomersBankRepository.existsByMobile(fdEntity.getMobile())) {
				logger.info("Customer bank  details already present for given mobile no, update details/skip");
			}else {*/

			String cptransactionId = null;
			logger.info("Generate Tr");
			do {
				cptransactionId = "MM"+ CommonTask.generateMMTransactionId();
				logger.debug("Generated CP TRANSACTION ID- " + cptransactionId);
				//			}while(mmFDTranactionCrudRepository.existsByTransactionId(transactionIdgeneation));
			}while(mmLeadDetailsCrudRepository.existsByCpTransRefNo(cptransactionId));


			logger.info("Final transaction ID- "+ cptransactionId);

			//			Call API First to save lead details

			MahindraFDSaveLeadDetails saveLeadDetails = mahindraFDBeansMapper.mapSaveLeadDetails(fdEntity, null, cptransactionId, customerId);

			if(saveLeadDetails!=null) {
				//				Save to database first
				if(!mmLeadDetailsCrudRepository.existsByCpTransRefNo(cptransactionId));
				mmLeadDetailsCrudRepository.save(saveLeadDetails);

				//				Call API - 
				apiresp = restClientMahindraFD.saveLeadDetails(saveLeadDetails);
			}

			if(apiresp.getStatus().equals("OK")) {

				logger.info("saveLeadDetails is success.. Update DB with reference no - "+ apiresp.getMfRefNo());
				//				Update MF_REF_SYS_NO to saveDetails
				mmLeadDetailsCrudRepository.updateMfSysRefNo(apiresp.getMfRefNo(), cptransactionId);
				saveDetailsResponseCode = apiresp.getMfRefNo();

				logger.info("Start mapping of entity to transactiondetails...");
				MahindraFDPurchaseRequest transactionDetails =  mahindraFDBeansMapper.mapTransactionRequestDetails(fdEntity, null,cptransactionId,transactionId,addressDetailsId,bankDetailsId,saveDetailsResponseCode, customerId);

				if(transactionDetails!=null) {
					logger.info("Initiate DB call to save transaction details ..");
					mmFDTranactionCrudRepository.save(transactionDetails);
					response.setStatusCode(CommonConstants.TASK_SUCCESS);
					response.setResultData1(bankDetailsId);
					response.setMfSysRefNo(saveDetailsResponseCode);
					logger.info("Transaction Data saved to DB.. Proceed with API call for transaction");
					//					cptransactionId = "MM2254911442502203866";	//TO COMMENT/REMOVE THE LINE AFTER TESTING COMPLETE
					apiresp2 = restClientMahindraFD.saveTransactionDetails(fdEntity, new MahindraCustomerKYCDetails(), transactionDetails, "TRANS_CUST", cptransactionId,  apiresp.getMfRefNo(), saveLeadDetails.getDob());

					if(apiresp2.getStatus().equals("OK")) {
						logger.info("Transaction application no received- "+ apiresp2.getApplicationNo());
						response.setStatusCode(CommonConstants.TASK_SUCCESS);
						response.setApplicationNo(apiresp2.getApplicationNo());
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
	public List<MahindraFDTransactionStatus> getPurchaseHistory(String mobile) {
		logger.info("getPurchaseHistory():Get Mahindra FD purchase history for customer  "+ mobile);
		List<MahindraFDTransactionStatus> transList = null;
		try {
			if(mobile!=null) {
				String customerId = mmCustomersCrudRepository.getCustomerIdromMobile(mobile);
				if(customerId!=null) {
					transList = mmFDPurchaseStatusCrudRepository.findAllByCustomerId(customerId);
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
	public MahindraResponse verifyPaymentStatus(String customerId, String mobile, String msgkey, String applicationNo) {
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






}
