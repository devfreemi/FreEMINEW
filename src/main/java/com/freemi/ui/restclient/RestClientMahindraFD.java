package com.freemi.ui.restclient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.freemi.common.util.CommonConstants;
import com.freemi.entity.investment.mahindra.MahindraApiResponse;
import com.freemi.entity.investment.mahindra.MahindraCustomerKYCDetails;
import com.freemi.entity.investment.mahindra.MahindraFDEntity;
import com.freemi.entity.investment.mahindra.MahindraFDPurchaseRequest;
import com.freemi.entity.investment.mahindra.MahindraFDSaveLeadDetails;

@Component
public class RestClientMahindraFD {

	@Autowired
	Environment env;

	private static final Logger logger = LogManager.getLogger(RestClientMahindraFD.class);

	public MahindraApiResponse saveLeadDetails(MahindraFDSaveLeadDetails leadDetails) {

		MahindraApiResponse apiResponse = new MahindraApiResponse();
		SimpleDateFormat apifmt = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss");
		SimpleDateFormat dbfmt =new SimpleDateFormat("yyyy-MM-dd");

		logger.info("saveLeadDetails(): Process data to initiate API call...");
		try {
			final String url = env.getProperty(CommonConstants.URL_SERVICE_MAHINDRA_FD)+"/SaveLeadDetails";
			logger.info("API Reuqesting url- "+ url);

			RestTemplate restTemplate = new RestTemplate();

			Map<String, Object> parametersMap = new HashMap<String, Object>();
			parametersMap.put("Scp_Code", leadDetails.getScpCode());
			parametersMap.put("Ref_Type", leadDetails.getRefType());
			parametersMap.put("Ref_Cust_Code" ,leadDetails.getRefCustCode());
			parametersMap.put("Ref_Rm_Code" , leadDetails.getRefRmCode());
			parametersMap.put("Ref_Othr_Code" , leadDetails.getRefOtherCode());
			parametersMap.put("CP_Trans_Ref_No" ,leadDetails.getCpTransRefNo());
			parametersMap.put("Name" , leadDetails.getFullName());
			parametersMap.put("Amount" ,Integer.toString(leadDetails.getSaveAmount()));

			//			fdEntity.setDob(customerDetails.getDob()!=null?(uifmt.format(dbfmt.parse(dbfmt.format(customerDetails.getDob())))):null);
			//			parametersMap.put("DOB" , leadDetails.getDob());
			parametersMap.put("DOB" , apifmt.format(dbfmt.parse(leadDetails.getDob())));
			parametersMap.put("EmailId" , leadDetails.getEmailId());
			parametersMap.put("LeadType" , leadDetails.getLeadType());
			parametersMap.put("CP_Location_Code" , leadDetails.getCpLocationCode());
			parametersMap.put("Mobile" , leadDetails.getMobile());
			parametersMap.put("PAN" , leadDetails.getPan());
			parametersMap.put("Salutation" , leadDetails.getSalutation());
			parametersMap.put("Source" , "CP");

			//			Map<String, Map<String,Object>> parametersMap2 = new HashMap<String, Map<String,Object>>();
			//			parametersMap2.put("LeadDetails", parametersMap2);

			ResponseEntity<String> response = null;

			requestBodyToJson(apiResponse, parametersMap);

			HttpHeaders headers = new HttpHeaders();
			//		headers.set("requestingIp", ip);
			response = restTemplate.postForEntity(url, parametersMap, String.class);
			logger.info("Response from saveleadDetails api - "+ response.getBody());

			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> jsonMap = mapper.readValue(response.getBody(),new TypeReference<Map<String,Object>>(){});
			/*
			 * for (String name : jsonMap.keySet()) { System.out.println("Key = " + name +
			 * ", Value = " + jsonMap.get(name)); }
			 */

			if(jsonMap.get("status").equals("OK")) {
				logger.info("Save details success");
				Map<String, String> outputData= (Map<String, String>) jsonMap.get("outputData");
				logger.info("Response code- "+ outputData.get("MF_SYS_REF_NO"));
				apiResponse.setStatus("OK");
				apiResponse.setMfRefNo(outputData.get("MF_SYS_REF_NO"));
			}else {
				logger.info("Restclient response with FAIL status");
				apiResponse.setStatus("FAIL");
			}
		}catch(Exception e) {
			apiResponse.setStatus("ERROR");
			logger.error("Mahindra API call failure...",e);
		}
		logger.info("saveLeadDetails(): API call complete... Return.");
		return apiResponse;
	}



	public MahindraApiResponse saveTransactionDetails(MahindraFDEntity fdEntity,MahindraCustomerKYCDetails kycDetails, MahindraFDPurchaseRequest purchaseRequest, String refType, String cpTransRefId, String mfSysRefNo, String dob) {

		MahindraApiResponse apiResponse = new MahindraApiResponse();
		logger.info("saveTransactionDetails(): Process data to initiate API call...");
		try {
			final String url = env.getProperty(CommonConstants.URL_SERVICE_MAHINDRA_FD)+"/SaveTransactionDetails";
			logger.info("API Reuqesting url- "+ url);

			RestTemplate restTemplate = new RestTemplate();

			//			Map<String, String> investmentDetails = saveTransactionDetailsAPI(fdEntity,kycDetails, purchaseRequest, refType, cpTransRefId,dob);
			ResponseEntity<String> response = null;

			Map<String, Object> parametersMap = saveTransactionDetailsAPI(fdEntity,kycDetails, purchaseRequest, refType, cpTransRefId,dob,mfSysRefNo);

			requestBodyToJson(apiResponse, parametersMap);

			HttpHeaders headers = new HttpHeaders();
			//			headers.set("requestingIp", ip);
			response = restTemplate.postForEntity(url, parametersMap, String.class);
			logger.info("Response from SaveTransactionDetails api - "+ response.getBody());

			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> jsonMap = mapper.readValue(response.getBody(),new TypeReference<Map<String,Object>>(){});

			/*
			 * for (String name : jsonMap.keySet()) { logger.info("Key = " + name
			 * +", Value = " + jsonMap.get(name)); }
			 */

			apiResponse.setStatus(jsonMap.get("status").toString());
			if(jsonMap.get("status").equals("OK")) {
				Map<String, String> outputData= (Map<String, String>) jsonMap.get("outputData");
				if(outputData.containsKey("msg") && outputData.get("msg").equalsIgnoreCase("SUCCESS")) {
					apiResponse.setApplicationNo(outputData.get("Appl_No"));
				}else {
					logger.info("Transaction failed... Not containing required data...");
					apiResponse.setStatus("FAIL");
				}
			}else {
				apiResponse.setStatus("FAIL");
			}
		}
		catch(Exception e) {
			apiResponse.setStatus("ERROR");

			logger.error("Mahindra API call failure...",e);
		}
		logger.info("saveTransactionDetails(): API call complete... Return.");
		return apiResponse;

	}


	/**
	 * @apiNote Get Bank details from IFSC code for Mahindra
	 * @param ifscCode
	 * @return
	 */
	public MahindraApiResponse getBankDetails(String ifscCode) {

		MahindraApiResponse apiResponse = new MahindraApiResponse();
		logger.info("getBankDetails(): Process data to initiate API call...");
		try {
			final String url = env.getProperty(CommonConstants.URL_SERVICE_MAHINDRA_FD)+"/getBankDetails";
			logger.info("API Reuqesting url- "+ url);

			RestTemplate restTemplate = new RestTemplate();

			Map<String, Object> parametersMap = new HashMap<String, Object>();
			parametersMap.put("ifsccode", ifscCode);

			ResponseEntity<String> response = null;

			requestBodyToJson(apiResponse, parametersMap);

			HttpHeaders headers = new HttpHeaders();
			//		headers.set("requestingIp", ip);
			response = restTemplate.postForEntity(url, parametersMap, String.class);
			logger.info("Response from api - "+ response.getBody());

			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			Map<String, Object> jsonMap = mapper.readValue(response.getBody(),new TypeReference<Map<String,Object>>(){});

			apiResponse.setStatus(jsonMap.get("status").toString());
			if(jsonMap.get("status").equals("OK")) {
				List<Map<String, String>> outputData= (List<Map<String, String>>) jsonMap.get("outputData");
				//				apiResponse.setPaymentUrl(outputData.get("result"));
				apiResponse.setMicrCode(outputData.get(0).get("MICR_CODE"));
				apiResponse.setBankName(outputData.get(0).get("BANK_NAME"));
				apiResponse.setBankBranch(outputData.get(0).get("BANK_BRANCH"));

			}else {
				logger.info("getBankDetails(): No data found...");
			}

		}catch(Exception e) {
			apiResponse.setStatus("ERROR");
			logger.error("getBankDetails(): Mahindra API call failure...",e);
		}

		logger.info("getBankDetails(): API call complete... Return.");
		return apiResponse;
	}



	public MahindraApiResponse getPaymentLink(String cpTransRefNo,String applicationNo, String mfSysRefNo, String callbackUrl) {

		MahindraApiResponse apiResponse = new MahindraApiResponse();
		logger.info("getPaymentLink(): Process data to initiate API call...");
		try {
			final String url = env.getProperty(CommonConstants.URL_SERVICE_MAHINDRA_FD)+"/getPaymentUrl";
			logger.info("API Reuqesting url- "+ url);

			RestTemplate restTemplate = new RestTemplate();

			Map<String, Object> parametersMap = new HashMap<String, Object>();
			parametersMap.put("Appl_No", applicationNo);
			parametersMap.put("CP_Trans_Ref_no", cpTransRefNo);
			parametersMap.put("MF_Sys_Ref_No", mfSysRefNo);
			parametersMap.put("CP_Payment_Response_Return_URL", callbackUrl);

			ResponseEntity<String> response = null;

			requestBodyToJson(apiResponse, parametersMap);

			HttpHeaders headers = new HttpHeaders();
			//		headers.set("requestingIp", ip);
			response = restTemplate.postForEntity(url, parametersMap, String.class);
			logger.info("Response from api - "+ response.getHeaders());

			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> jsonMap = mapper.readValue(response.getBody(),new TypeReference<Map<String,Object>>(){});
			//			logger.info("Response code- "+ outputData.get("msg"));

			apiResponse.setStatus(jsonMap.get("status").toString());
			if(jsonMap.get("status").equals("OK")) {
				Map<String, String> outputData= (Map<String, String>) jsonMap.get("outputData");
				apiResponse.setPaymentUrl(outputData.get("result"));
			}else {
				logger.info("getPaymentLink(): No data found...");
			}

		}catch(Exception e) {
			apiResponse.setStatus("ERROR");
			logger.error("getPaymentLink(): Mahindra API call failure...",e);
		}

		logger.info("getPaymentLink(): API call complete... Return.");
		return apiResponse;
	}


	/**
	 * @apiNote Verify payment status after redirected from payment gateway of mahindra
	 * @param msgkey
	 * @return
	 */
	public MahindraApiResponse getPGResponseVerification(String msgkey) {

		MahindraApiResponse apiResponse = new MahindraApiResponse();
		logger.info("getPGResponseVerification(): Process data to initiate API call...");
		try {
			final String url = env.getProperty(CommonConstants.URL_SERVICE_MAHINDRA_FD)+"/VerifyPGResponse";
			logger.info("API Reuqesting url- "+ url);

			RestTemplate restTemplate = new RestTemplate();

			Map<String, Object> parametersMap = new HashMap<String, Object>();
			parametersMap.put("PGText", msgkey);

			ResponseEntity<String> response = null;

			requestBodyToJson(apiResponse, parametersMap);

			HttpHeaders headers = new HttpHeaders();
			//		headers.set("requestingIp", ip);
			response = restTemplate.postForEntity(url, parametersMap, String.class);
			logger.info("Response from api - "+ response.getBody());

			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> jsonMap = mapper.readValue(response.getBody(),new TypeReference<Map<String,Object>>(){});
			//			logger.info("Response code- "+ outputData.get("msg"));

			apiResponse.setStatus(jsonMap.get("status").toString());
			if(jsonMap.get("status").equals("OK")) {
				
				Map<String, String> outputData= (Map<String, String>) jsonMap.get("outputData");
				apiResponse.setPaymentVerifyResponse(outputData.get("result"));
			}else {
				logger.info("getPGResponseVerification(): No data found...");
			}

		}catch(Exception e) {
			apiResponse.setStatus("ERROR");
			logger.error("getPGResponseVerification(): Mahindra API call failure...",e);
		}

		logger.info("getPGResponseVerification(): API call complete... Return.");
		return apiResponse;
	}
	
	
	
	/**
	 * @apiNote Verify payment status and application staus after redirected from payment gateway
	 * @param msgkey
	 * @return
	 */
	public MahindraApiResponse getpaymentStatus(String applicationNo) {

		MahindraApiResponse apiResponse = new MahindraApiResponse();
		logger.info("getpaymentStatus(): Process data to initiate API call...");
		try {
			final String url = env.getProperty(CommonConstants.URL_SERVICE_MAHINDRA_FD)+"/getfdpaymentstatus";
			logger.info("API Reuqesting url- "+ url);

			RestTemplate restTemplate = new RestTemplate();

			Map<String, Object> parametersMap = new HashMap<String, Object>();
			parametersMap.put("APPL_NO", applicationNo);

			ResponseEntity<String> response = null;

			requestBodyToJson(apiResponse, parametersMap);

			HttpHeaders headers = new HttpHeaders();
			//		headers.set("requestingIp", ip);
			response = restTemplate.postForEntity(url, parametersMap, String.class);
			logger.info("Response from api - "+ response.getBody());

			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> jsonMap = mapper.readValue(response.getBody(),new TypeReference<Map<String,Object>>(){});
			//			logger.info("Response code- "+ outputData.get("msg"));

			apiResponse.setStatus(jsonMap.get("status").toString());
			if(jsonMap.get("status").equals("OK")) {
				Map<String, String> outputData= (Map<String, String>) jsonMap.get("outputData");
				apiResponse.setPaymentStatusCode(outputData.get("Status"));
				apiResponse.setPaymentVerifyResponse(outputData.get("Status_Code"));
			}else {
				logger.info("getpaymentStatus(): No data found...");
			}

		}catch(Exception e) {
			apiResponse.setStatus("ERROR");
			logger.error("getpaymentStatus(): Mahindra API call failure...",e);
		}

		logger.info("getpaymentStatus(): API call complete... Return.");
		return apiResponse;
	}
	



	/**@apiNote Map Data
	 * @param apiResponse
	 * @param parametersMap
	 */
	protected void requestBodyToJson(MahindraApiResponse apiResponse, Map<String, Object> parametersMap) {
		logger.info("Converting map to JSON for saving to DB..");
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			String json = objectMapper.writeValueAsString(parametersMap);
			logger.info("Request body JSON for Mahindra API - "+ json);
			apiResponse.setRequestJson(json);
		} catch (JsonProcessingException e) {
			logger.error("Error converting to JSON..",e);
		}
	}



	protected Map<String, Object> saveTransactionDetailsAPI(MahindraFDEntity fdEntity, MahindraCustomerKYCDetails kycDetails,MahindraFDPurchaseRequest purchaseRequest, String refType, String cpTransRefId, String dob, String mfSysRefNo) throws ParseException 
	{
		SimpleDateFormat apifmt = new SimpleDateFormat("dd-MMM-yyyy");
		SimpleDateFormat dbfmt =new SimpleDateFormat("yyyy-MM-dd");
		
		Map<String, String> investmentDetails = new HashMap<String, String>();
		investmentDetails.put("Scp_Code","");
		investmentDetails.put("Ref_Type", refType);
		investmentDetails.put("Ref_Cust_Code" ,"");
		investmentDetails.put("Ref_Rm_Code" ,"");
		investmentDetails.put("Ref_Othr_Code" ,"");
		investmentDetails.put("CP_Trans_Ref_No" ,cpTransRefId);
		investmentDetails.put("MF_Sys_Ref_No" ,mfSysRefNo);
		investmentDetails.put("Category" ,fdEntity.getCategory());	//PU/SR
		investmentDetails.put("SchemeType_Code" ,fdEntity.getScheme());	//Micro/Cumulative/Non-cumulative
		investmentDetails.put("SchemeCode" ,fdEntity.getSchemeCode());
		investmentDetails.put("IntFreq" , fdEntity.getIntFreq());
		investmentDetails.put("IntRate" , fdEntity.getInterestRate().toString());
		investmentDetails.put("Tenure" , Integer.toString(fdEntity.getSaveTenure()));
		investmentDetails.put("IsAutoRenewal" , purchaseRequest.getIsAutoRenewal().equals("Y")?"True":"False");
		investmentDetails.put("Amount" ,Integer.toString(fdEntity.getSaveAmount()));
		investmentDetails.put("PaymentMode" , purchaseRequest.getPaymentMode());
		investmentDetails.put("PaymentInstruction" , purchaseRequest.getPaymentInstruction());
		investmentDetails.put("CP_Location_Code" ,purchaseRequest.getCpLocationCode() );
		investmentDetails.put("IsTnCAccepted" ,purchaseRequest.getIsTnCAccepted().equals("Y")?"True":"False" );
		investmentDetails.put("IsDecAccepted" , purchaseRequest.getIsDecAccepted().equals("Y")?"True":"False");
		investmentDetails.put("Is_TDS_Applicable" ,purchaseRequest.getIsTDSApplicable().equals("Y")?"True":"False" );
		investmentDetails.put("Is_Payment_Disc_Accepted" , purchaseRequest.getIsPaymentDiscAccepted().equals("Y")?"True":"False");
		investmentDetails.put("Is_Cersai_Disc_Accepted" , purchaseRequest.getIsCersaiDiscAccepted().equals("Y")?"True":"False");
		investmentDetails.put("Renewal_For" , purchaseRequest.getRenewalFor());

		Map<String, String> investorBankDtl = new HashMap<String, String>();
		investorBankDtl.put("Scp_Code" ,"");
		investorBankDtl.put("Ref_Type" ,purchaseRequest.getReferenceType() );
		investorBankDtl.put("Ref_Cust_Code" ,"" );
		investorBankDtl.put("Ref_Rm_Code" ,"" );
		investorBankDtl.put("Ref_Othr_Code" , "");
		investorBankDtl.put("MICRCode" , fdEntity.getMicrCode());
		investorBankDtl.put("NEFTCode" ,fdEntity.getIfscCode() );
		investorBankDtl.put("BankName" , fdEntity.getBankname());
		investorBankDtl.put("BranchName" , fdEntity.getBankbranch());
		investorBankDtl.put("BankAccountNo" , fdEntity.getAccountNumber());

		Map<String, String> investorAddDtls = new HashMap<String, String>();
		investorAddDtls.put("Scp_Code" , "");
		investorAddDtls.put("Ref_Type" , purchaseRequest.getReferenceType());
		investorAddDtls.put("Ref_Cust_Code" ,"" );
		investorAddDtls.put("Ref_Rm_Code" ,"" );
		investorAddDtls.put("Ref_Othr_Code" , "");
		investorAddDtls.put("First_Holder_Name" ,fdEntity.getFirstName()+ " "+ fdEntity.getMiddleName() + " "+ fdEntity.getLastName() );
		investorAddDtls.put("Mobile" ,fdEntity.getMobile() );
		investorAddDtls.put("EmailId" ,fdEntity.getEmail() );
		investorAddDtls.put("First_Holder_PAN" ,fdEntity.getPan() );
		investorAddDtls.put("Amount" ,Integer.toString(fdEntity.getSaveAmount()) );
		investorAddDtls.put("DOB" , apifmt.format(dbfmt.parse(dob)));
		investorAddDtls.put("Address1" ,fdEntity.getAddress1());
		investorAddDtls.put("Address2" ,fdEntity.getAddress2_1() );
		investorAddDtls.put("Address3" , fdEntity.getAddress3_1());
		investorAddDtls.put("CKC_StateCode" ,fdEntity.getAddressstate1() );
		investorAddDtls.put("DistrictName" , fdEntity.getAddressDistrict1());
		investorAddDtls.put("PinCode" ,Integer.toString(fdEntity.getAddresspincode1()) );
		investorAddDtls.put("First_Holder_Kyc_TaxResidencyOutsideIndia_Code" ,"02" );
		investorAddDtls.put("CKYC_No" ,fdEntity.getCkyc() );
		/*
		 * investorAddDtls.put("Second_Holder_Name" ,"" );
		 * investorAddDtls.put("Third_Holder_Name" , "");
		 * investorAddDtls.put("Second_Holder_CKYC_No" ,"" );
		 * investorAddDtls.put("Third_Holder_CKYC_No" , "");
		 * investorAddDtls.put("Second_Holder_PAN" , "");
		 * investorAddDtls.put("Second_Holder_Ovd_Type" ,"" );
		 * investorAddDtls.put("Second_Holder_Ovd_No" ,"" );
		 * investorAddDtls.put("Third_Holder_PAN" ,"" );
		 * investorAddDtls.put("Third_Holder_Ovd_Type" ,"" );
		 * investorAddDtls.put("Third_Holder_Ovd_No" , "");
		 * investorAddDtls.put("Second_Holder_DOB" , "");
		 * investorAddDtls.put("Third_Holder_DOB" ,"" );
		 */

		HashMap<String, String> kycDataDetails = new HashMap<String, String>();
		kycDataDetails.put("Scp_Code" , "");
		kycDataDetails.put("Ref_Type" , purchaseRequest.getReferenceType());
		kycDataDetails.put("Ref_Cust_Code" ,"" );
		kycDataDetails.put("Ref_Rm_Code" ,"" );
		kycDataDetails.put("Ref_Othr_Code" , "");
		kycDataDetails.put("Ref_Othr_Code" , "");
		kycDataDetails.put("Source_Sub_Type" , "MCP");
		kycDataDetails.put("Holder_Type" , fdEntity.getHoldingOptions());
		kycDataDetails.put("Kyc_ConstiType" , "01");
		kycDataDetails.put("Kyc_AccType" , "01");
		kycDataDetails.put("Kyc_NamePrefix" , fdEntity.getPrimaryHolderTitle());
		kycDataDetails.put("Kyc_FirstName" , fdEntity.getFirstName());
		kycDataDetails.put("Kyc_MiddleName" ,fdEntity.getMiddleName());
		kycDataDetails.put("Kyc_LastName" , fdEntity.getLastName());
		kycDataDetails.put("Kyc_FullName" , fdEntity.getFirstName()+  ((fdEntity.getMiddleName()!=null && !fdEntity.getMiddleName().isEmpty())?(" " +fdEntity.getMiddleName()):"") + fdEntity.getLastName()!=null?(" "+fdEntity.getLastName()):"");
		kycDataDetails.put("Kyc_MaidenNamePrefix" , "");
		kycDataDetails.put("Kyc_MaidenFirstName" , "");
		kycDataDetails.put("Kyc_MaidenFullName","");
		kycDataDetails.put("Kyc_MaidenLastName","");
		kycDataDetails.put("Kyc_MaidenMiddleName","");
		kycDataDetails.put("Kyc_FatherFirstName","Rangalal");
		kycDataDetails.put("Kyc_FatherFullName","Rangalal Sarkar");
		kycDataDetails.put("Kyc_FatherLastName","Sarkar");
		kycDataDetails.put("Kyc_FatherMiddleName","");
		kycDataDetails.put("Kyc_FatherNamePrefix","Mr");
		kycDataDetails.put("Kyc_SpouseFirstName","");
		kycDataDetails.put("Kyc_SpouseFullName","");
		kycDataDetails.put("Kyc_SpouseLastName","");
		kycDataDetails.put("Kyc_SpouseMiddleName","");
		kycDataDetails.put("Kyc_SpouseNamePrefix","");
		kycDataDetails.put("Kyc_MotherFirstName","");
		kycDataDetails.put("Kyc_MotherFullName","");
		kycDataDetails.put("Kyc_MotherLastName","");
		kycDataDetails.put("Kyc_MotherMiddletName","");
		kycDataDetails.put("Kyc_MotherNamePrefix","");
		kycDataDetails.put("Kyc_Gender",fdEntity.getGender());
		kycDataDetails.put("Kyc_MaritalStatus",fdEntity.getMaritalStatus());
		kycDataDetails.put("Kyc_Nationality_Code","IN");
		kycDataDetails.put("Kyc_Occupation_Code",fdEntity.getOccupation());
		kycDataDetails.put("Kyc_DOB",apifmt.format(dbfmt.parse(dob)));
		kycDataDetails.put("Kyc_ResidentialStatus_Code","01");
		kycDataDetails.put("Kyc_TaxResidencyOutsideIndia_Code","02");
		kycDataDetails.put("Kyc_JurisdictionofRes_Code","IN");
		kycDataDetails.put("Kyc_TIN","");
		kycDataDetails.put("Kyc_CountryOfBirth","");
		kycDataDetails.put("Kyc_PlaceOfBirth","");
		kycDataDetails.put("Kyc_Per_AddType_Code","02");
		kycDataDetails.put("Kyc_Per_Add1",fdEntity.getAddress1());
		kycDataDetails.put("Kyc_Per_Add2",fdEntity.getAddress2_1());
		kycDataDetails.put("Kyc_Per_Add3",fdEntity.getAddress3_1());
		kycDataDetails.put("Kyc_Per_AddCity_Desc",fdEntity.getAddressCity1());
		kycDataDetails.put("Kyc_Per_AddDistrict_Desc",fdEntity.getAddressDistrict1());
		kycDataDetails.put("Kyc_Per_AddState_Code",fdEntity.getAddressstate1());
		kycDataDetails.put("Kyc_Per_AddCountry_Code","IN");
		kycDataDetails.put("Kyc_Per_AddPin",Integer.toString(fdEntity.getAddresspincode1()));
		kycDataDetails.put("Kyc_Per_AddPOA","Y");
		kycDataDetails.put("Kyc_Per_AddPOAOthers","");
		kycDataDetails.put("Kyc_Per_AddSameAsCor_Add","Y");
//		kycDataDetails.put("Kyc_Cor_Add1","Cor");
		kycDataDetails.put("Kyc_Cor_Add1",fdEntity.getAddress1());
		kycDataDetails.put("Kyc_Cor_Add2",fdEntity.getAddress2_1());
		kycDataDetails.put("Kyc_Cor_Add3",fdEntity.getAddress3_1());
		kycDataDetails.put("Kyc_Cor_AddCity_Desc",fdEntity.getAddressCity1());
		kycDataDetails.put("Kyc_Cor_AddDistrict_Desc",fdEntity.getAddressDistrict1());
		kycDataDetails.put("Kyc_Cor_AddState_Code",fdEntity.getAddressstate1());
		kycDataDetails.put("Kyc_Cor_AddCountry_Code","IN");
		kycDataDetails.put("Kyc_Cor_AddPin",Integer.toString(fdEntity.getAddresspincode1()));
		kycDataDetails.put("Kyc_PerAddSameAsJurAdd","Y");
		kycDataDetails.put("Kyc_Jur_Add1","");
		kycDataDetails.put("Kyc_Jur_Add2","");
		kycDataDetails.put("Kyc_Jur_Add3","");
		kycDataDetails.put("Kyc_Jur_AddCity_Desc","");
		kycDataDetails.put("Kyc_Jur_AddState_Desc","");
		kycDataDetails.put("Kyc_Jur_AddCountry_Code","");
		kycDataDetails.put("Kyc_Jur_AddPin","");
		kycDataDetails.put("Kyc_ResTelSTD","");
		kycDataDetails.put("Kyc_ResTelNumber","");
		kycDataDetails.put("Kyc_OffTelSTD","");
		kycDataDetails.put("Kyc_OffTelNumber","");
		kycDataDetails.put("Kyc_MobileISD","");
		kycDataDetails.put("Kyc_MobileNumber",fdEntity.getMobile());
		kycDataDetails.put("Kyc_EmailAdd",fdEntity.getEmail());
		kycDataDetails.put("Kyc_FAXSTD","");
		kycDataDetails.put("Kyc_FaxNumber","");
		kycDataDetails.put("Kyc_TypeofDocSubmitted","");

		List<Map<String, String>> listOfMaps = new ArrayList<Map<String, String>>();
		listOfMaps.add(kycDataDetails);

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("InvestmentDetails", investmentDetails);
		parameters.put("InvestorBankDtl", investorBankDtl);
		parameters.put("InvestorAddDtls", investorAddDtls);
		parameters.put("KYCDataDetails", listOfMaps.toArray());

		return parameters;
	}


}