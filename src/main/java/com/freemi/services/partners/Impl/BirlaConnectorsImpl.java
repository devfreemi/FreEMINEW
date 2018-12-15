package com.freemi.services.partners.Impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.freemi.common.util.CommonTask;
import com.freemi.entity.Birla.GetBankDetailsByIFSCInput;
import com.freemi.entity.Birla.GetBankDetailsByIFSCInput.BankIFSC;
import com.freemi.entity.Birla.GetBankDetailsByIFSCOutput;
import com.freemi.entity.Birla.GetFPurchaseBanksInput;
import com.freemi.entity.Birla.GetFPurchaseBanksInput.Bank;
import com.freemi.entity.Birla.GetFPurchaseBanksOutput;
import com.freemi.entity.Birla.GetFPurchaseBranchInput;
import com.freemi.entity.Birla.GetFPurchaseBranchInput.Branch;
import com.freemi.entity.Birla.GetFPurchaseBranchOutput;
import com.freemi.entity.Birla.GetIRDetailsForFolioInput;
import com.freemi.entity.Birla.GetIRDetailsForFolioInput.FolioSchemeRequest;
import com.freemi.entity.Birla.GetIRDetailsForFolioOutput;
import com.freemi.entity.Birla.GetProductDetailsInput;
import com.freemi.entity.Birla.GetProductDetailsInput.ProductDetailsRequest;
import com.freemi.entity.Birla.GetProductDetailsOutput;
import com.freemi.entity.Birla.GetSchemeMasterDetailsInput;
import com.freemi.entity.Birla.GetSchemeMasterDetailsInput.Request;
import com.freemi.entity.Birla.GetSchemeMasterDetailsOutput;
import com.freemi.entity.Birla.NewFolioCreateOutput;
import com.freemi.entity.Birla.NewFolioCreationInput;
import com.freemi.entity.Birla.SavePostPurchaseMultiRequestInput;
import com.freemi.entity.Birla.SavePostPurchaseMultiRequestOutput;
import com.freemi.entity.Birla.SavePostSIPMultipleSchemesInput;
import com.freemi.entity.Birla.SavePostSIPMultipleSchemesOutput;
import com.freemi.entity.Birla.SavePrePurchaseMultiRequestInput;
import com.freemi.entity.Birla.SavePrePurchaseMultiRequestOutput;
import com.freemi.entity.Birla.SavePreSIPMultipleSchemesInput;
import com.freemi.entity.Birla.SavePreSIPMultipleSchemesOutput;
import com.freemi.entity.Birla.StateCityListInput;
import com.freemi.entity.Birla.StateCityListInput.StateCityRequest;
import com.freemi.entity.Birla.StateCityListOutput;
import com.freemi.entity.Birla.ValidateAadhaarInput;
import com.freemi.entity.Birla.ValidateAadhaarInput.ValidateAadharNoRequest;
import com.freemi.entity.Birla.ValidateAadhaarOTPInput;
import com.freemi.entity.Birla.ValidateAadhaarOTPInput.ValidateAdharOTPRequest;
import com.freemi.entity.Birla.ValidateAadhaarOTPOutput;
import com.freemi.entity.Birla.ValidateAadhaarOutput;
import com.freemi.entity.Birla.ValidatePANInput;
import com.freemi.entity.Birla.ValidatePANInput.ValidatePAN;
import com.freemi.entity.Birla.ValidatePANOutput;
import com.freemi.entity.general.ClientSystemDetails;
import com.freemi.entity.general.OauthAccessToken;
import com.freemi.entity.general.UserBankDetails;
import com.freemi.entity.investment.FolioCreationStatus;
import com.freemi.entity.investment.MFInvestForm;
import com.freemi.services.partners.Interfaces.InvestmentConnectorInterfaces;


/**
 * @author Win 8
 *
 */
public final class BirlaConnectorsImpl implements InvestmentConnectorInterfaces {

	OauthAccessToken token = null;
	private static final Logger logger = LogManager.getLogger(BirlaConnectorsImpl.class);

	private static final String CONSUMER_KEY="ElkD44Ckq2WpTOFTnh9PNbDUwBwa";
	private static final String CONSUMER_SECRET="mNV_iwpK_sAo82Jrmtm5gl1UXuYa";
	private static final String BASE_URL ="https://mfapiuat.birlasunlife.com";
	private static final String SALT = "RN1DF139-8B0B-018A-B27C-B141EC7BC396";
	private static final String IV = "e141f413e018f396";
	private static final String PASSWD = "Pass@w3rd96";

	protected static final String USERID = "ARN-141396";
	protected static final String PASSWORD = "MTQxMzk2";

	static DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("ddMMyyHHmmss");
	static DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss a");

	//	API URL
	private static final String GET_PRODUCT_DETAILS_URI = "/GetProductDetails/1.0.0";
	private static final String GET_PRODUCT_SCHEME_DETAILS_URI = "/GetSchemeMasterDetails/1.0.0";

	private static final String GET_CITY_STATE_LIST_URI = "/GetStateCityList/1.0.0";

	private static final String VALIDATE_PAN_URI = "/ValidatePAN/1.0.0";
	private static final String VALIDATE_AADHAAR_URI = "/ValidateAadharNo/1.0.0";
	private static final String VALIDATE_AADHAAR_OTP_URI = "/ValidateAdharOTP/1.0.0";
	private static final String NEW_FOLIO_CREATION_URI = "/NewFolioCreation/1.0.0";


	private static final String GET_LIST_BANKS_URI="/GetFPurchaseBanks/1.0.0";
	private static final String GET_CITY_BANK_BRANCH_URI = "/GetFPurchaseBranch/1.0.0";

//	private static final String GET_LIST_BANK_CITY_URI = "/GetFPurchaseBranchCity/1.0.0";
	private static final String GET_SIP_BANK_IFSC_URI = "/GetISIPBankIFSC/1.0.0";

	private static final String SAVE_PRE_SIP_MULTIPLE_SCHEMES = "/SavePreSIPMultipleSchemes/1.0.0";
	private static final String SAVE_POST_SIP_MULTIPLE_SCHEMES = "/SavePostSIPMultipleSchemes/1.0.0";

	private static final String SAVE_PRE_LUMPSUM_PURCHASE_SCHEMES = "/SavePrePurchaseMultiRequest/1.0.0";
	private static final String SAVE_POST_LUMPSUM_PURCHASE_SCHEMES = "/SavePrePurchaseMultiRequest/1.0.0";

	private static final String GET_IR_FOLIO_DETAILS = "/GetIRDetailForFolio/1.0.0";
//	private static final String GET_IR_WITHDRAW_FUND = "/IRWithdrawalAmount/1.0.0";


	/* Generates CheckSum which is is required for integrigity validation at Birla. Generation logic provided by Birla.
	 * @see com.app.services.partners.Interfaces.BirlaConnectorInterfaces#getCheckSum(java.lang.String, java.lang.String)
	 */



	private String getCheckSum(String timestamp, String inputString) {
		logger.info("Generating checksum");
		String checksum="";

		try {
			checksum = BirlaCheckSumGeneration.encrypt(inputString, PASSWD, BirlaCheckSumGeneration.getEncodedSaltValue(timestamp, SALT, inputString), IV);
			logger.info("Time: "+ timestamp + " checksum: "+checksum);
		} catch (Exception e) {
			logger.error("Error generatinng checksum-\n"+ e.getLocalizedMessage());
			e.printStackTrace();
		}
		//		logger.info("Checksum- "+ checksum);
		return checksum;
	}


	/* Verifies the customer's PAN number through Birla
	 * @see com.app.services.partners.Interfaces.BirlaConnectorInterfaces#validatePAN(java.lang.String, java.lang.String)
	 * @Param PAN
	 */
	@Override
	public Object validatePAN(String token, String PAN,HttpSession session, ClientSystemDetails clientSystem) {
		ObjectMapper mapper = new ObjectMapper();  
		ValidatePANInput validatePANDetails = new ValidatePANInput();
		ValidatePANInput.ValidatePAN panData = new ValidatePAN();
		ValidatePANOutput panSearchResult = null;
		String resquestBody="";

		panData.setUserId(USERID);
		panData.setPassword(PASSWORD);
		panData.setPanNo(PAN.toUpperCase());
		panData.setClientIpAddress(clientSystem.getClientIpv4Address());
		panData.setOs(CommonTask.getSystemOS());

		validatePANDetails.setPandata(panData);

		try {
			resquestBody = mapper.writeValueAsString(validatePANDetails);
			logger.info(resquestBody);
			ResponseEntity<String> response =  postcallwithHeaders(getUri(VALIDATE_PAN_URI), new HttpEntity<String>(resquestBody,generateHttpHeaders(PAN.toUpperCase())));
			logger.info("PAN validation response- "+ response.getBody());
			panSearchResult = mapper.readValue(response.getBody(), ValidatePANOutput.class);

		} catch (JsonProcessingException e1) {
			logger.error("PANVAlidation error- JsonProcessingException- "+ e1.getMessage());
			e1.printStackTrace();
		} catch (IOException e) {
			logger.error("PANVAlidation error- IOException- "+ e.getMessage());
			e.printStackTrace();
		} catch (HttpServerErrorException e){
			logger.error("Error conecting to endpoint- "+ e.getRawStatusCode());
		} catch(Exception e){
			logger.error("PANVAlidation error- "+ e.getMessage());
		}


		/*try {
			String text ="{ \"ValidatePANResult\": { \"Folios\": [ { \"ActivatedAmount\": \"N.A\", \"DefaultFlag\": \"Y\", \"EmailID\": \"tarunkp2@gmail.com\", \"Folio_No\": \"1016779822\", \"InvestorName\": \"DEBASISH SARKAR\", \"LiquidFlag\": \"YES\", \"MAXDATE\": \"03-OCT-16\", \"MobileNumber\": \"+919702903111\", \"PANNo\": null } ], \"IsEKYCVerified\": \"Y\", \"IsExistingInvestor\": \"Y\", \"NameAsPerPAN\": \"DEBASISH SARKAR\", \"PANNumber\": \"CQOPS7539C\", \"ReturnCode\": \"1\", \"ReturnMsg\": \"Record retrieved successfully\", \"UDP\": \"\", \"isFATCAVerified\": \"N.A.\" } }";
			//		String text ="{\"ValidatePANResult\":{\"AadharNo\":null,\"Folios\":null,\"IsEKYCVerified\":\"N\",\"IsExistingInvestor\":\"N\",\"KYC_Mode\":null,\"NameAsPerPAN\":null,\"PANNumber\":\"CKOPK7504F\",\"PanResponseLog\":null,\"ReturnCode\":0,\"ReturnMsg\":\"No Record Found\",\"UDP\":null,\"isFATCAVerified\":\"N.A.\"}}";
			panSearchResult = mapper.readValue(text, ValidatePANOutput.class);
		} catch (IOException e) {
			e.printStackTrace();
		}*/

		return panSearchResult;
	}



	/* API call to get all city list of states in India. Lists all cities recognized by Birla
	 * @see com.app.services.partners.Interfaces.BirlaConnectorInterfaces#getStateCityList()
	 */
	@Override
	public Object getStateCityList(HttpSession session, ClientSystemDetails clientSystem) {

		logger.info("Requesting for state city list from Birla");

		ObjectMapper mapper = new ObjectMapper();
		StateCityListInput cityRequest = new StateCityListInput();
		StateCityListInput.StateCityRequest cityRequestInfo = new StateCityRequest();
		StateCityListOutput cityListOutput = new StateCityListOutput();
		String resquestBody="";

		cityRequestInfo.setUserId(USERID);
		cityRequestInfo.setPassword(PASSWORD);
		cityRequestInfo.setClientIpAddress(clientSystem.getClientIpv4Address());
		cityRequestInfo.setOs(CommonTask.getSystemOS());

		cityRequest.setStateCityRequestObject(cityRequestInfo);

		try {
			resquestBody = mapper.writeValueAsString(cityRequest);
			ResponseEntity<String> response =  postcallwithHeaders(getUri(GET_CITY_STATE_LIST_URI), new HttpEntity<String>(resquestBody,generateHttpHeaders(USERID)));

			logger.info(response.getBody());
			cityListOutput = mapper.readValue(response.getBody(), StateCityListOutput.class);
		} catch (JsonProcessingException e1) {
			e1.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}


		logger.info("City list request complate");
		return cityListOutput;

	}



	/* Validates customer's aadhaar number
	 * @see com.app.services.partners.Interfaces.BirlaConnectorInterfaces#validateAADHAARNumber(java.lang.String)
	 */
	@Override
	public Object validateAADHAARNumber(String aadhaarNo,HttpSession session, ClientSystemDetails clientSystem) {

		logger.info("Requesting to validate customer AADHAAR number from Birla- "+ aadhaarNo.replace(aadhaarNo.substring(0, 7), "XXXXXXXX") );
		ObjectMapper mapper = new ObjectMapper();  
		ValidateAadhaarInput aadhaar = new ValidateAadhaarInput();
		ValidateAadhaarInput.ValidateAadharNoRequest aadhaarInfo = new ValidateAadharNoRequest();
		ValidateAadhaarOutput aadhaarOutput =null;

		String resquestBody="";


		/*Form Input body for request*/
		aadhaarInfo.setUserId(USERID);
		aadhaarInfo.setPassword(PASSWORD);
		aadhaarInfo.setAdharNo(aadhaarNo);
		aadhaarInfo.setClientIpAddress(clientSystem.getClientIpv4Address());
		aadhaarInfo.setOs(CommonTask.getSystemOS());

		aadhaar.setAadhaarInfo(aadhaarInfo);



		try {
			resquestBody = mapper.writeValueAsString(aadhaar);
			logger.info(resquestBody);
			ResponseEntity<String> response =  postcallwithHeaders(getUri(VALIDATE_AADHAAR_URI), new HttpEntity<String>(resquestBody,generateHttpHeaders(aadhaarNo)));
			logger.info(response.getBody());

			aadhaarOutput = mapper.readValue(response.getBody(), ValidateAadhaarOutput.class);


		} catch (JsonProcessingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch(HttpServerErrorException e2){
			logger.info("Aadhaar verification failed-" + e2.getRawStatusCode() + " : "+ e2.getStatusText());
			aadhaarOutput=null;
		} catch(Exception e){
			e.printStackTrace();
		}



		//For test purposes 
		/*		try {
//			String text="{\"AadharReturnCode\":null,\"AadharReturnMessage\":\"OTP Sent \",\"AppKYCMode\":null,\"Flag\":true,\"IRISRefId\":null,\"PAN_PEKRN\":null,\"RefID\":11004,\"ReturnCode\":1,\"ReturnMsg\":\"Record retrieved successfully\",\"UDP\":null}";
//			String text="{\"AadharReturnCode\":null,\"AadharReturnMessage\":\"Aadhaar number is incorrect. Resident shall use correct Aadhaar\",\"AppKYCMode\":null,\"Flag\":false,\"IRISRefId\":null,\"PAN_PEKRN\":null,\"RefID\":11005,\"ReturnCode\":0,\"ReturnMsg\":\"Aadhaar number is incorrect. Resident shall use correct Aadhaar\",\"UDP\":null}";
			String text="{\"AadharReturnCode\":null,\"AadharReturnMessage\":null,\"AppKYCMode\":null,\"Flag\":false,\"IRISRefId\":null,\"PAN_PEKRN\":null,\"RefID\":11058,\"ReturnCode\":-1,\"ReturnMsg\":\"There seems to be some Technical problem. Please try after sometime\",\"UDP\":null}";
			aadhaarOutput = mapper.readValue(text, ValidateAadhaarOutput.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		 */

		logger.info("AADHAAR verification request complate");

		return aadhaarOutput;
	}




	/* (non-Javadoc)
	 * @see com.app.services.partners.Interfaces.BirlaConnectorInterfaces#ValidateAADHAAROTP(java.lang.String, java.lang.String, int, javax.servlet.http.HttpSession)
	 */
	@Override
	public Object ValidateAADHAAROTP(String aadhaarNo, String otp, int refId,HttpSession session, ClientSystemDetails clientSystem) {
		logger.info("Requesting to validate customer AADHAAR OTP from Birla- "+ aadhaarNo.replace(aadhaarNo.substring(0, 7), "XXXXXXXX") );

		ObjectMapper mapper = new ObjectMapper();
		ValidateAadhaarOTPInput input = new ValidateAadhaarOTPInput();
		ValidateAadhaarOTPInput.ValidateAdharOTPRequest otpRequest = new ValidateAdharOTPRequest();
		ValidateAadhaarOTPOutput otpOutput = null;


		String resquestBody="";

		otpRequest.setUserId(USERID);
		otpRequest.setPassword(PASSWORD);
		otpRequest.setAdharNo(aadhaarNo);
		otpRequest.setOtpCode(otp);
		otpRequest.setRefID(refId);
		otpRequest.setClientIpAddress(clientSystem.getClientIpv4Address());
		otpRequest.setOs(CommonTask.getSystemOS());

		input.setValidateAdharOTPRequestObject(otpRequest);


		try {
			resquestBody = mapper.writeValueAsString(input);
			logger.info(resquestBody);
			ResponseEntity<String> response =  postcallwithHeaders(getUri(VALIDATE_AADHAAR_OTP_URI), new HttpEntity<String>(resquestBody,generateHttpHeaders(aadhaarNo)));
			logger.info(response.getBody());
			otpOutput = mapper.readValue(response.getBody(), ValidateAadhaarOTPOutput.class);
		} catch (JsonProcessingException e1) {
			e1.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}catch(HttpServerErrorException e){
			e.getRawStatusCode();
		}catch(Exception e){
			e.printStackTrace();
		}

		/*try{
			String ob="{\"EKYCAdharDetails\":{\"AadhaarPrint\":null,\"AdharNo\":\"As per Aadhar\",\"CareOfPerson\":\"As per Aadhar\",\"City\":\"As per Aadhar\",\"DOB\":\"As per Aadhar\",\"District\":\"As per Aadhar\",\"Email\":\"As per Aadhar\",\"Gender\":\"As per Aadhar\",\"House\":\"As per Aadhar\",\"Landmark\":\"As per Aadhar\",\"Locality\":\"As per Aadhar\",\"Name\":\"Debasish Sarkar\",\"Phone\":\"As per Aadhar\",\"Photo\":null,\"PinCode\":\"As per Aadhar\",\"PostOfficeName\":\"As per Aadhar\",\"RefNo\":null,\"Source\":null,\"State\":\"As per Aadhar\",\"Street\":\"As per Aadhar\",\"SubDistrict\":\"As per Aadhar\",\"TransactionCode\":\"As per Aadhar\",\"UserDetailId\":null,\"strOpr\":null},\"Flag\":true,\"Message\":null,\"ReturnCode\":1,\"ReturnMsg\":\"Record retrieved successfully\",\"UDP\":null}";
			otpOutput = mapper.readValue(ob, ValidateAadhaarOTPOutput.class);
		}catch(Exception e){
			e.printStackTrace();
		}*/

		return otpOutput;
	}


	/* (non-Javadoc)
	 * @see com.app.services.partners.Interfaces.InvestmentConnectorInterfaces#generateNewFolio(com.app.beans.entity.investment.MFInvestForm)
	 */
	@Override
	public FolioCreationStatus generateNewFolio(MFInvestForm mfInvestForm,UserBankDetails birlaBankDetails,ValidateAadhaarOTPOutput aadhaarData, ClientSystemDetails clientSystem) {
		logger.info("Requesting for New folio creation for user- "+ mfInvestForm.getPAN());
		String resquestBody="";

		ObjectMapper mapper = new ObjectMapper();
		FolioCreationStatus status = null;
		NewFolioCreateOutput folioOutput =null;

		try {

			NewFolioCreationInput folioInput= BirlaBeansMapper.toNewFolioCreationBean(mfInvestForm, aadhaarData, birlaBankDetails);
			resquestBody = mapper.writeValueAsString(folioInput);
			logger.info(resquestBody);
			ResponseEntity<String> response =  postcallwithHeaders(getUri(NEW_FOLIO_CREATION_URI), new HttpEntity<String>(resquestBody,generateHttpHeaders((mfInvestForm.getPAN().toUpperCase()+"|"+(mfInvestForm.getPanValidationStatus().getIsKYCVerified().equalsIgnoreCase("Y")?"": mfInvestForm.getAadhaar())))));
			logger.info(response.getBody());
			folioOutput = mapper.readValue(response.getBody(), NewFolioCreateOutput.class);
			status = BirlaBeansMapper.FromFolioCreationOutput(folioOutput);

		} catch(Exception e){
			e.printStackTrace();

		}


		/*try {
			String t ="{\"NewFolioCreationResult\":{\"AdharSeedlinkRef_Id\":null,\"FolioNo\":1017634210,\"PEKRN\":null,\"RefNo\":null,\"ReturnCode\":1,\"ReturnMsg\":\"Record retrieved successfully\",\"UDP\":null}}";
			//		String t="{\"NewFolioCreationResult\":{\"AdharSeedlinkRef_Id\":null,\"FolioNo\":null,\"PEKRN\":null,\"RefNo\":null,\"ReturnCode\":\"E754\",\"ReturnMsg\":\"Please enter Politically Exposed Person Details flag For First Applicant.\",\"UDP\":null}}";
			folioOutput = mapper.readValue(t, NewFolioCreateOutput.class);

			status = BirlaBeansMapper.FromFolioCreationOutput(folioOutput);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

		return status;
	}



	@Override
	public Object getBankDetailsByIFSC(String ifsc, ClientSystemDetails clientSystem) {
		//		Get bank details by IFSC
		// TODO Auto-generated method stub

		logger.info("Requesting bank details by IFSC code-  "+ ifsc );

		ObjectMapper mapper = new ObjectMapper(); 

		GetBankDetailsByIFSCInput ifscInput = new GetBankDetailsByIFSCInput();
		GetBankDetailsByIFSCInput.BankIFSC bankIfsc = new BankIFSC();
		GetBankDetailsByIFSCOutput ifscOutput = null;

		String resquestBody="";

		bankIfsc.setUserId(USERID);
		bankIfsc.setPassword(PASSWORD);
		bankIfsc.setIfscCode(ifsc.toUpperCase());
		bankIfsc.setClientIpAddress(clientSystem!=null?clientSystem.getClientIpv4Address():"");

		ifscInput.setBankIFSCObject(bankIfsc);

		try {
			resquestBody = mapper.writeValueAsString(ifscInput);
			logger.info(resquestBody);
			ResponseEntity<String> response =  postcallwithHeaders(getUri(GET_SIP_BANK_IFSC_URI), new HttpEntity<String>(resquestBody,generateHttpHeaders(ifsc.toUpperCase())));
			logger.debug(response.getBody());
			System.out.println(response.getBody());
			ifscOutput = mapper.readValue(response.getBody(), GetBankDetailsByIFSCOutput.class);
			logger.info("Total Bank details returned by IFSC- "+ ifscOutput.getLstSIPBankDet().size());
			System.out.println((ifscOutput.getLstSIPBankDet().get(0)).getBranch_Name());

		} catch (JsonProcessingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch(HttpServerErrorException e){
			e.getRawStatusCode();
		} catch(Exception e){
			e.printStackTrace();
		}


		/*try {
					//		String text="{\"AadharReturnCode\":null,\"AadharReturnMessage\":\"OTP Sent \",\"AppKYCMode\":null,\"Flag\":true,\"IRISRefId\":null,\"PAN_PEKRN\":null,\"RefID\":11004,\"ReturnCode\":1,\"ReturnMsg\":\"Record retrieved successfully\",\"UDP\":null}";
					//		String text="{\"AadharReturnCode\":null,\"AadharReturnMessage\":\"Aadhaar number is incorrect. Resident shall use correct Aadhaar\",\"AppKYCMode\":null,\"Flag\":false,\"IRISRefId\":null,\"PAN_PEKRN\":null,\"RefID\":11005,\"ReturnCode\":0,\"ReturnMsg\":\"Aadhaar number is incorrect. Resident shall use correct Aadhaar\",\"UDP\":null}";
					String text="{\"FPurchaseBranches\":[{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"AE MARKRT SALT LAKE CITY KOLKATA W.B. 24 PARGANAS N\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":70451,\"Branch_Name\":\"A.E MARKET\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0006794\",\"MICR_Code\":700002110},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"6 DR. S P MUKHERJEE ROAD, BELGHORIA, CALCUTTA - 70056,W.B., DIST 24 PARGANAS NORTH\",\"Branch_City\":null,\"Branch_District\":\"NORTH 24 PARGANAS\",\"Branch_Id\":71638,\"Branch_Name\":\"AGARPARA TEXMACO AREA \",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0008046\",\"MICR_Code\":700002149},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"AKRASTATIONROAD,POPSMAHESHTALA,KOLKATA,DISTT.SOUTH24PARGANAS,WESTBENGAL700141.\",\"Branch_City\":null,\"Branch_District\":\"24 PARGANAS S\",\"Branch_Id\":125349,\"Branch_Name\":\"AKRA STATION ROAD\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0018128\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"52 BY 2 SURYA SEN ROAD ALAMBAZAR 24 PARGANAS N  WEST BENGAL PIN 700035\",\"Branch_City\":null,\"Branch_District\":\"NORTH 24 PARGANAS\",\"Branch_Id\":65300,\"Branch_Name\":\"ALAM BAZAR\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001356\",\"MICR_Code\":700002111},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"24 BY 1 BY 1  ALIPORE ROAD   T  KOLKATA  W BENGAL 700027\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":63952,\"Branch_Name\":\"ALIPORE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0000004\",\"MICR_Code\":700002002},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"12, BIPLABI KANAI BHATTACHRJEE SARANI, KOLKATA 700027\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":73323,\"Branch_Name\":\"ALIPORE COURT TREASURY\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0009884\",\"MICR_Code\":700002189},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"1B,MAHENDRA SRIMANI,KOLKATA,WEST BENGAL\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65740,\"Branch_Name\":\"AMHERST STREET\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001800\",\"MICR_Code\":700002003},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"SIDDHA, 5TH AVENUE, 179 ANADAPUR, DISTT. 24 PARGANAS (SOUTH). KOLKATA. WEST BENGAL-700107\",\"Branch_City\":null,\"Branch_District\":\"24 PARGANAS(SOUTH)\",\"Branch_Id\":79294,\"Branch_Name\":\"ANANDAPUR\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0016634\",\"MICR_Code\":\"NON-MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"1A,ASHUTOSH MUKHERJEE ROAD NEAR ELGIN ROAD CROSSING BHAVANIPUR,CALCUTTA 700020\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":80296,\"Branch_Name\":\"ASHUTOSH MUKHERJEE ROAD, KOLKATA.       \",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0060393\",\"MICR_Code\":700002346},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"ABHOY GUHA ROAD, DEYS MARKET, KOLKATA 700006\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":132557,\"Branch_Name\":\"AZAD HIND BAGH, KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0031638\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"GUNADHAR BABU LANE DISTT. KOLKATA WEST BENGAL 700012\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":70445,\"Branch_Name\":\"B B GANGULLY STREET\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0006788\",\"MICR_Code\":700002015},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"DIST   KOLKATA  WEST BENGAL 700001\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65166,\"Branch_Name\":\"B R B B ROAD\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001218\",\"MICR_Code\":700002018},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"DIST   KOLKATA  WEST BENGAL 700002\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":67607,\"Branch_Name\":\"B T ROAD\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0003721\",\"MICR_Code\":700002004},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"104 BY 2, B.K.PAUL AVENUE, DISTT. KOLKATA, WEST BENGAL 700005\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65707,\"Branch_Name\":\"B. K. PAUL AVENUE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001767\",\"MICR_Code\":700002005},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"29,GANESH CHANDRA AVENUE,BIPLABI ANKUL CHANDRA STREET, ,KOLKATA, WEST BENGAL ,PIN - 700013\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":69278,\"Branch_Name\":\"BAC STREET\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0005583\",\"MICR_Code\":700002083},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"KATHOR MORE, BADU ROAD, P.O.-BADU, KOLKATA, WEST BENGAL-700 128\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":77501,\"Branch_Name\":\"BADU ROAD\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0014537\",\"MICR_Code\":\"NON-MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"2A,GIRISH AVENUE, CALCUTTA, WEST BENGAL, WEST BENGAL 700003\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65593,\"Branch_Name\":\"BAGH BAZAR\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001652\",\"MICR_Code\":700002007},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"1A ASHOK ROAD, KOLKATA, WEST BENGAL, PIN 700084\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":70446,\"Branch_Name\":\"BAGHAJATIN BZR.\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0006789\",\"MICR_Code\":700002006},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"P36,C.I.T.SCHEME,MVII.HS.XII,CALCUTTA.PIN.700054\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":67568,\"Branch_Name\":\"BAGMARI\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0003682\",\"MICR_Code\":700002008},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"23 BRRB ROAD  DISTT  KOLKATA  WEST BENGAL 700001\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65688,\"Branch_Name\":\"BAGRI MARKET\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001748\",\"MICR_Code\":700002009},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"BAKHRAHAT ROAD, KOLKATA,DIST.KOLKATA, WESTBENGAL-700104\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":137403,\"Branch_Name\":\"BAKHRAHAT ROAD\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0018122\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"24 PARGANAS SOUTH , WEST BENGAL, PIN  700061\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65431,\"Branch_Name\":\"BAKUTALA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001489\",\"MICR_Code\":700002118},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"CALCUTTA,\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":63966,\"Branch_Name\":\"BALLYGUNGE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0000018\",\"MICR_Code\":700002010},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"DIST. KOLKATA, WEST BENGAL\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":74912,\"Branch_Name\":\"BALLYGUNGE PARK\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0011534\",\"MICR_Code\":\"NON MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"54 EKDALIA RD KOLKATA WEST BENGAL\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":67834,\"Branch_Name\":\"BALLYGUNGE RLY. STN.\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0003951\",\"MICR_Code\":700002011},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"208, RASHBEHARI AVENUE, BALLYGUNJ, KOLKATA 700001\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":138086,\"Branch_Name\":\"BALLYGUNJ, KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0031376\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"87,BANGUR AVENUE, BLOCK A,P.O. ,BANGUR AVENUE, KOLKATA , PIN - 700055\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":72805,\"Branch_Name\":\"BANGUR AVENUE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0009289\",\"MICR_Code\":700002210},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"334 BY 1 NSC BOSE ROAD,KOLKATA\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":74914,\"Branch_Name\":\"BANSDRONI\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0011536\",\"MICR_Code\":700002325},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"BAUR PURATAN BAZAR, DIST-SOUTH 24 PARAGANAS, WEST BENGAL\",\"Branch_City\":null,\"Branch_District\":\"SOUTH 24 PARAGANAS\",\"Branch_Id\":77113,\"Branch_Name\":\"BARUIPUR BAZAR\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0014031\",\"MICR_Code\":\"NON-MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"85B,BEADON STREET, CALCUTTA\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65884,\"Branch_Name\":\"BEADON STREET\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001957\",\"MICR_Code\":700002012},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"163 BY 1  DIAMOND HARBOUR RD  DISTT  KOLKATA  WEST BENGAL 700034\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65464,\"Branch_Name\":\"BEHALA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001522\",\"MICR_Code\":700002115},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"7A, SOURIN ROY RD, 1ST FLOOR, BEHALA KOLKITTA 700034\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":100221,\"Branch_Name\":\"BEHALA CHOWRASTHA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0040708\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"64 BY 1 BY 46A  BELGACHIA ROAD  DISTT  KOLKATA  WEST BENGAL 700037\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":69064,\"Branch_Name\":\"BELGACHIA DUTTABAGAN MILK COLONY\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0005367\",\"MICR_Code\":700002013},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"25,BELIAGHATA.MAIN.ROAD,CALCUTTA.PIN.700010\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65738,\"Branch_Name\":\"BELIAGHATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001798\",\"MICR_Code\":700002014},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"64 BY 3 BY 1 DR S C BANERJEE RD  DISTT  KOLKATA  WEST BENGAL 700010\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":72020,\"Branch_Name\":\"BELIAGHATA C.I.T BUILDING AREA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0008440\",\"MICR_Code\":700002172},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"FIRST FLOOR TOBACCO HOUSE, BR ABOURNE RD KOLKOTTA\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":140872,\"Branch_Name\":\"BENTINCK STREET\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0040259\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"388 BAGHAJATIN PLACE, KOLKATA, DISTT.SOUTH 24 PGS. WEST BENGAL-700086\",\"Branch_City\":null,\"Branch_District\":\"SOUTH 24 PARGANAS\",\"Branch_Id\":79291,\"Branch_Name\":\"BHAGHAJATIN STATION ROAD BRANCH\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0016629\",\"MICR_Code\":\"NON-MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"1, ALLENBY ROAD, NORTHEN PARK, KOLKATA 700020\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":132560,\"Branch_Name\":\"BHAWANIPUR, KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0031641\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"6 BY 1, RAMESH MITRA ROAD, DISTT. KOLKATA, WEST BENGAL 700025\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":63988,\"Branch_Name\":\"BHOWANIPORE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0000040\",\"MICR_Code\":700002016},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"15 ASHUTOSH MUKHERJEE MARG BHOWANIPUR KOLKATA\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":103455,\"Branch_Name\":\"BHOWANIPUR,KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0050808\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"49A, SHYAMBAZAR STREET, DISTT. KOLKATA, WEST BENGAL 700004\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":70421,\"Branch_Name\":\"BHUPEN BOSE AVENUE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0006764\",\"MICR_Code\":700002017},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"BIDHAN SARANI, B/156/5/H/8, ACHARJEE PRAFULLA CHANDRA ROAD, DISTT.KOLKATA. WEST BENGAL-700006\",\"Branch_City\":null,\"Branch_District\":\"SOUTH 24 PGS\",\"Branch_Id\":79292,\"Branch_Name\":\"BIDHAN SARANI\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0016630\",\"MICR_Code\":\"NON-MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"SALT LAKE SECTOR 2  DISTT  NORTH 24 PARAGANAS  WEST BENGAL 700091\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":71420,\"Branch_Name\":\"BIKASH BHAVAN G.O.C\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0007816\",\"MICR_Code\":700002114},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"206,M.B.ROAD,BIRATI,KOLKATA51,W.B.,24.PARGANASNORTH,PIN.700051.\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":68405,\"Branch_Name\":\"BIRATI\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0004604\",\"MICR_Code\":700002209},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"89 BY 3 RAIMOHAN BANERJEE RD BONHOOGHLY KOLKATA 24 PARGANAS NWB\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65953,\"Branch_Name\":\"BONHOOGHLY I.E\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0002029\",\"MICR_Code\":700002150},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"BORAL MAIN ROAD, GARIA, KOLKATA, WEST BENGAL 700084\",\"Branch_City\":null,\"Branch_District\":\"SOUTH 24 PARGANAS\",\"Branch_Id\":77493,\"Branch_Name\":\"BORAL\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0014525\",\"MICR_Code\":\"NON-MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"3OSAROADKOLKAA\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":126486,\"Branch_Name\":\"BRABOURNE RD\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0020273\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"9, BRABOURNE ROAD\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":79574,\"Branch_Name\":\"BRABOURNE ROAD BRANCH, KOLKATA \",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0030146\",\"MICR_Code\":700005002},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"89,BRAHMAPUR.BATTALA,GARIA,KOLKATA700096\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":74909,\"Branch_Name\":\"BRAHMAPUR\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0011531\",\"MICR_Code\":700002326},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"19B BROAD STREET, KOLKATA, DISTT.KOLKATA. WEST BENGAL 700019\",\"Branch_City\":null,\"Branch_District\":\"NORTH 24 PARGANAS\",\"Branch_Id\":123110,\"Branch_Name\":\"BROAD STREET\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0016628\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"195, M G ROAD, KOLKATA, WEST BENGAL, PIN 700007\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":63998,\"Branch_Name\":\"BURRA BAZAR\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0000050\",\"MICR_Code\":700002019},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"2UNEDKLKT\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":126553,\"Branch_Name\":\"BURRA BAZAR KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0020360\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"1C/2, CHOWBAGA ROAD, KOLKATA - 700039\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":77487,\"Branch_Name\":\"C N ROY ROAD\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0014517\",\"MICR_Code\":\"NON-MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"150, CHITTARANJAN AVENUE, KOLKATA  WEST BENGAL 700073\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":71036,\"Branch_Name\":\"C R AVENUE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0007406\",\"MICR_Code\":700002025},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"SBI BALLYGUNGE BRANCH PREMISES, 50A, GARIAHAT ROAD, 2ND FLOOR, KOLKATA, WEST BENGAL - 700019\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":73780,\"Branch_Name\":\"CAC, BALLYGUNGE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0010377\",\"MICR_Code\":\"NON-MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"SBI SERAMPORE BRANCH PREMISES, SERAMPORE 1ST FLOOR, HOOGHLY, DIST:HOOGHLY-712201\",\"Branch_City\":null,\"Branch_District\":\"HOOGLY\",\"Branch_Id\":76637,\"Branch_Name\":\"CAC, HOOGHLY\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0013418\",\"MICR_Code\":\"NON-MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"34, JAWAHARLAL NEHRU ROAD, KOLKATA - 700 071\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":73428,\"Branch_Name\":\"CAG, KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0009998\",\"MICR_Code\":700002199},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"COLLEGE STREET,KOLKATA,W.B.,PIN 700073\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":71375,\"Branch_Name\":\"CALCUTTA UNIVERSITY\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0007766\",\"MICR_Code\":700002022},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"16 A,SHAKESPEARE SARANI,KOLKATA,DISTT.KOLKATA.WEST BENGAL 700071\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":133645,\"Branch_Name\":\"CAMAC STREET\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0017362\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"DIST   KOLKATA STATE  WEST BENGAL PIN   700001 \",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65924,\"Branch_Name\":\"CAO, KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001999\",\"MICR_Code\":700002991},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"GILLANDER HOUSE BLOCK D, 1ST AND 2ND FLR., 8 N S ROAD, KOLKATA WEST BENGAL 700 001\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":67979,\"Branch_Name\":\"CB N S ROAD\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0004125\",\"MICR_Code\":700002202},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"PLOT NO X-1 8 BY 1 BLOCK-EPSECTOR-V, SALT LAKE ELECTRONICS COMPLEX ,KOLKATA ,PIN - 700091\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":68117,\"Branch_Name\":\"CB SALT LAKE, KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0004289\",\"MICR_Code\":700002226},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"KOLKATA\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":71592,\"Branch_Name\":\"CENTRAL STATIONERY DEPARTMENT\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0007999\",\"MICR_Code\":700002501},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"83 BY 1-A VIVEKANANDA ROAD\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":68276,\"Branch_Name\":\"CENTRALISED PENSION PROCESSING CENTRE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0004473\",\"MICR_Code\":700002801},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"CF-351, SECTOR-1,SALT LAKE CITY, BIDHANNAGAR, KOLKATA - 700064\",\"Branch_City\":null,\"Branch_District\":\"NORTH 24 PARGANAS\",\"Branch_Id\":75679,\"Branch_Name\":\"CF BLOCK,SALT LAKE \",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0012360\",\"MICR_Code\":\"NON - MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"16A, DESHAPRAN SASHMAL ROAD, KOLKATA-700033\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":77486,\"Branch_Name\":\"CHARU MARKET\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0014516\",\"MICR_Code\":\"NON-MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"19,CHETLA CENTRAL ROAD,KOLKATA,W.B.,PIN 700027\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":67577,\"Branch_Name\":\"CHETLA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0003691\",\"MICR_Code\":700002024},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"ETRA76,METRPLITANPERATIVEHUINGIETYLTD,ANALUTHRAD,HINGRIGHATA,KLKATA700105\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":132733,\"Branch_Name\":\"CHIRANGI GHATA KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0031920\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"38B  JAWARLAL NEHRU ROAD  DISTT  KOLKATA  WEST BENGAL 700071\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65002,\"Branch_Name\":\"CHOWRINGHEE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001054\",\"MICR_Code\":700002026},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"P35  CIT ROAD  DISTT  KOLKATA  WEST BENGAL 700014\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65088,\"Branch_Name\":\"CIT ROAD\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001140\",\"MICR_Code\":700002020},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"KOLKATA,WEST BENGAL\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":73792,\"Branch_Name\":\"CLEARING CENTRALISED PROCESSING CELL, KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0010391\",\"MICR_Code\":700002990},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"223, BIDHAN SARANI, CALCUTTA 700006, BENGAL\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":69414,\"Branch_Name\":\"COLLEGE STREET MATKET, KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0005723\",\"MICR_Code\":700002027},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"66A,COLOOTOLA STREET,KOLKATA,KOLKATA,WEST BENGAL,PIN - 700073\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":71795,\"Branch_Name\":\"COLOOTOLA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0008209\",\"MICR_Code\":700002170},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"50A,GARIAHAT ROAD, 5TH FLOOR, CALCUTTA,\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":67987,\"Branch_Name\":\"COMM BR BALLYGUNGE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0004140\",\"MICR_Code\":700002205},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"2411,ALIPORE.ROAD,KOLKATA,24.PARGANASSOUTH,WEST.BENGAL,PIN.700027.\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":68093,\"Branch_Name\":\"COMM. BRANCH ALIPORE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0004263\",\"MICR_Code\":700002217},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"24, PARK STREET, KOLKATA - 700 016\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":71126,\"Branch_Name\":\"COMMERCIAL BRANCH, KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0007502\",\"MICR_Code\":700002120},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"57, PARK MANSION, PART STREET, KOLAKTA 700016\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":132530,\"Branch_Name\":\"COMMERCIAL BRANCH, PARK STREET, KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0031604\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"JEEVAN DEEP  11TH FLOOR , 1, MIDDLETON ROW, KOLKATA, W.BENGAL, PIN - 700071\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":68116,\"Branch_Name\":\"COMMERCIAL JAWAHARLAL NEHRU RD \",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0004288\",\"MICR_Code\":700002225},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"62 BY 1 BY 1, KASHINATH DUTTA, ROAD, DISTT. KOLKATA, WEST BENGAL 700 036\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65967,\"Branch_Name\":\"COSSIPORE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0002043\",\"MICR_Code\":700002028},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"161, CHITTARANJAN AVENUE, OPP. MAHAJATI SADAN\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":79690,\"Branch_Name\":\"COTTON STREET KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0030266\",\"MICR_Code\":700005003},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"2/1, RUSSEL STREET, KANKARIA CENTRE,KOLKATA - 700071\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":78454,\"Branch_Name\":\"CSPPC\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0015666\",\"MICR_Code\":\"NON-MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"SBI HOWRAH BRANCH PREMISES,9 G.T. ROAD (SOUTH), GROUND FLOOR,HOWRAH, WEST BENGAL-711101\",\"Branch_City\":null,\"Branch_District\":\"HOWRAH\",\"Branch_Id\":76636,\"Branch_Name\":\"CURRENCY ADMINISTRATION CELL, HOWRAH\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0013417\",\"MICR_Code\":\"NON-MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"511,DAKSHINBEHALAROAD,KOLKATA,DISTT.SOUTH24PARGANAS,WESTBENGAL700061\",\"Branch_City\":null,\"Branch_District\":\"24 PARGANAS S\",\"Branch_Id\":135522,\"Branch_Name\":\"DAKSHIN BEHALA ROAD\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0018121\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"57/1 SYAMNAGAR ROAD, KOLKATA - 700 055\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":75695,\"Branch_Name\":\"DAKSHINPARA BAGUIATI\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0012378\",\"MICR_Code\":\"NON MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"2  B B D BUG  DISTT  KOLKATA  WEST BENGAL 700001\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65344,\"Branch_Name\":\"DALHOUSIE SQUARE (EAST)\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001401\",\"MICR_Code\":700002029},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"12  AND  14E GARAIAHAT ROAD SOUTH   DISTT  KOLKATA  WEST BENGAL 700031\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65348,\"Branch_Name\":\"DHAKURIA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001405\",\"MICR_Code\":700002030},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"10,BUDGE BUDGE ROAD,MOLLARGATE,MAHESHTALA,KOLKATA,DISTT.KOLKATA.WEST BENGAL 700141\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":123363,\"Branch_Name\":\"DOCUMENT ARCHIVAL CENTRE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0017192\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"5 BY 1,JESSORE ROAD,KOLKATTA DT24 PARGANAS N,W. BENGAL 700028\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65978,\"Branch_Name\":\"DUM DUM\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0002054\",\"MICR_Code\":700002122},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"181, DUM DUM ROAD, KOLKATA - 700074\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":75691,\"Branch_Name\":\"DUM DUM ROAD\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0012373\",\"MICR_Code\":\"NON-MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"13,ASOKGARH,24.PGS.NORTH,KOLKATA700108\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":74918,\"Branch_Name\":\"DUNLOP BRIDGE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0011540\",\"MICR_Code\":700002329},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"CPO29POLKLATA\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":134427,\"Branch_Name\":\"EAST JADAVPUR BR KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0021403\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"101, RAJDANGA, DISHA APPARTMENT, NAWA PALLY\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":79853,\"Branch_Name\":\"EKTP(NABAPALLY), KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0030445\",\"MICR_Code\":700005006},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"91B  CHOWRINGHEE ROAD  DISTT  KOLKATA  WEST BENGAL 700020\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65708,\"Branch_Name\":\"ELGIN ROAD\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001768\",\"MICR_Code\":700002031},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"54,RAFI AHMED KIDWAI ROAD,CALCUTTA,WEST BENGAL 700016\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65732,\"Branch_Name\":\"ELLIOT ROAD, KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001792\",\"MICR_Code\":700002032},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"34 GIRISH CH. BOSE ROAD, DISTT. KOLKATA, WEST BENGAL 700 014\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65770,\"Branch_Name\":\"ENTALLY\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001830\",\"MICR_Code\":700002033},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"9B, SIDHU KANHU DAHAR, DISTT. KOLKATA,WEST BENGAL,  700069\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65898,\"Branch_Name\":\"ESPLANADE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001971\",\"MICR_Code\":700002034},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"40,EZRA STREET,KOLKATA,WEST BENGAL,PIN - 700001\",\"Branch_City\":null,\"Branch_District\":\"GREATER BOMBAY\",\"Branch_Id\":72169,\"Branch_Name\":\"EZRA STREET, KOLKATA\",\"Branch_State\":\"MAHARASHTRA\",\"IFSC_Code\":\"SBIN0008600\",\"MICR_Code\":700002176},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"KOLKATA WB\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65059,\"Branch_Name\":\"FD KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001111\",\"MICR_Code\":700002998},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"ST. GEROGES GATE, DISTT. KOLKATA WEST BENGAL 700021\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65539,\"Branch_Name\":\"FORT WILLIAM\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001597\",\"MICR_Code\":700002035},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"SAMRIDDHI,BHAVAN,1,STRAND,ROAD,KOLKATA\",\"Branch_City\":null,\"Branch_District\":\"GORAKPUR \",\"Branch_Id\":72456,\"Branch_Name\":\"FUND SETTLEMENT LINK OFFICE, KOLKATA\",\"Branch_State\":\"UTTAR PRADESH\",\"IFSC_Code\":\"SBIN0008913\",\"MICR_Code\":700002999},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"51 BY B, GARCHA ROAD, DISTT. KOLKATA, WEST BENGAL 700019\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":67578,\"Branch_Name\":\"GARCHA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0003692\",\"MICR_Code\":700002036},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"SOUTH EASTERN RLY HQ, GARDEN REACH, KOLKATA, 24 PARGANAS S WB, 700043\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65345,\"Branch_Name\":\"GARDEN REACH\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001402\",\"MICR_Code\":700002126},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"3 GARFA MAIN ROAD KOLKATTA DT 24 PARGANAS S, W. BENGAL 700078\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65392,\"Branch_Name\":\"GARFA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001450\",\"MICR_Code\":700002125},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"283 A NSC BOSE ROAD  DISTT  KOLKATA WEST BENGAL 700047\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":69065,\"Branch_Name\":\"GARIA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0005368\",\"MICR_Code\":700002037},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"510 JOSHPUR PARK, DISTT. KOLKATA WEST BENGAL 700 068\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":71430,\"Branch_Name\":\"GARIAHAT CIVIC CENTRE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0007826\",\"MICR_Code\":700002038},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"33, SAHITYA PARISHAD ST, PLOT NO. 6, KOLKATA, WB\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":68572,\"Branch_Name\":\"GOABAGAN\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0004773\",\"MICR_Code\":700002040},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"DIST NORTH 24 PARGANAS,WEST BENGAL 743252\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":72325,\"Branch_Name\":\"GOBARDANGA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0008766\",\"MICR_Code\":700002559},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"INSTITUTION OF ENGINEERING BLDG,8,GOKHALE RD,KOLKATA\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65986,\"Branch_Name\":\"GOKHALE ROAD\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0002062\",\"MICR_Code\":700002039},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"GOLF GREEN,PHASE II,APARTMENT OWNERS ASSOCIATION,ADMIN BUILDING,DISTT.KOLKATA.WEST BENGAL 700095\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":123113,\"Branch_Name\":\"GOLF GREEN\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0016635\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"GOLF GREEN KOLKOTTA,WEST BENGAL,DT 12 A, UDAYASHANKAR SARANI KOLKATTA 95\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":100178,\"Branch_Name\":\"GOLF GREEN KOLKOTTA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0040643\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"133 SOUTHERN AVENUE, KOLKATA,  WB 700029\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":70841,\"Branch_Name\":\"GOLPARK\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0007195\",\"MICR_Code\":700002041},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"DIST  KOLKATA, WEST BENGAL 700 014\",\"Branch_City\":null,\"Branch_District\":\"MADHEPURA \",\"Branch_Id\":72151,\"Branch_Name\":\"GORACHAND ROAD, KOLKATA\",\"Branch_State\":\"BIHAR\",\"IFSC_Code\":\"SBIN0008581\",\"MICR_Code\":700002175},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"196AG,ARABINDA SARANI,KOLKATA,WEST BENGAL,PIN 700004\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65690,\"Branch_Name\":\"GREY STREET EXTN.BRANCH \",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001750\",\"MICR_Code\":700002042},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"P.O.HALDIA OIL REFINERY GATE1,MIDNAPORE,DI. PASCHIM MEDINIPUR,WEST BENGAL\",\"Branch_City\":null,\"Branch_District\":\"EAST MEDINIPUR \",\"Branch_Id\":72896,\"Branch_Name\":\"HALDIA P. C\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0009390\",\"MICR_Code\":721002004},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"DIST KOLKATASTATE WEST BENGAL\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":74908,\"Branch_Name\":\"HARIDEVPUR\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0011530\",\"MICR_Code\":700002349},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"102B, HARISH MUKHERJEE ROAD, KOLKATA, WEST BENGAL 700025\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":67844,\"Branch_Name\":\"HARISH MUKHERJEE ROAD\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0003961\",\"MICR_Code\":700002043},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"URMIMALA HOUSE, JYANGRA KALITALA, HATIARA ROAD, BAGUIATI, KOLKATA - 700 059\",\"Branch_City\":null,\"Branch_District\":\"NORTH 24 PARGANAS\",\"Branch_Id\":75693,\"Branch_Name\":\"HATIARA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0012376\",\"MICR_Code\":\"NON-MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"77 BY 2A, HAZRA RD,KOLKATA, WB\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65590,\"Branch_Name\":\"HAZRA ROAD\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001649\",\"MICR_Code\":700002045},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"2ND AND 3RD FLOOR,  C  D BLOCK, SAMRIDDHI BHAVAN, KOLKATA - 700001\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":77491,\"Branch_Name\":\"HIGH COURT BRANCH\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0014523\",\"MICR_Code\":\"NON-MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"1895 MUKUNDAPUR, HILAND PARK, DIST: KOLKATA, WEST BENGAL-700 075\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":77488,\"Branch_Name\":\"HIGH LAND PARK, KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0014520\",\"MICR_Code\":\"NON-MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"HILI, DAKSHIN DINAJPUR DISTRICT, WEST BENGAL - 733126\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":75728,\"Branch_Name\":\"HILI\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0012415\",\"MICR_Code\":\"NON-MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"6, SAMBHU NATH PANDIT STREET, KOLKATA, DISTT.KOLKATA. WEST BENGAL 700025\",\"Branch_City\":null,\"Branch_District\":\"NORTH 24 PARGANAS\",\"Branch_Id\":138311,\"Branch_Name\":\"HNI ELGIN ROAD\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0016624\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"2PANCHANANTALAROADHOWRAH\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":127727,\"Branch_Name\":\"HOWRA BRANCH KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0020811\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"CONSTANTIA BLDG., 11,U.N.BRAHMACHARI STREET, KOLKATA -700 017\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65865,\"Branch_Name\":\"IFB, KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001936\",\"MICR_Code\":700002194},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"315, INDIA EXCHANGE PLACE, DISTT. KOLKATA, WEST BENGAL 700012\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65087,\"Branch_Name\":\"INDIA EXCH. PLACE EXTN.\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001139\",\"MICR_Code\":700002047},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"P19TARATALAROAD,DISTT.KOLKATA,WESTBENGAL700088.\",\"Branch_City\":null,\"Branch_District\":\"24 PARGANAS N\",\"Branch_Id\":138286,\"Branch_Name\":\"INDIAN MARITIME UNIVERSITY\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0018118\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"76, A.J.C.BOSE ROAD, KOLKATA WEST BENGAL 700014\",\"Branch_City\":null,\"Branch_District\":\"PUNE\",\"Branch_Id\":72324,\"Branch_Name\":\"IT BLDG AREA BENIAPUKUR\",\"Branch_State\":\"MAHARASHTRA\",\"IFSC_Code\":\"SBIN0008762\",\"MICR_Code\":700002178},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"57/4 BARAKHOLA, KALIKAPUR, P.S.- SURVEY PARK, DISTT.24 PARGANAS (SOUTH). WEST BENGAL-700099\",\"Branch_City\":null,\"Branch_District\":\"DISTT 24 PARGANAS SOUTH\",\"Branch_Id\":79296,\"Branch_Name\":\"JADAVPUR STADIUM\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0016637\",\"MICR_Code\":\"NON-MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"RAJA SUBODH MULLICK ROAD  DISTT  KOLKATA  WEST BENGAL 700032\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":64041,\"Branch_Name\":\"JADAVPUR UNIVERSITY\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0000093\",\"MICR_Code\":700002048},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"46 CHAKRABERIA ROAD, DISTT. KOLKATA, WEST BENGAL 700020\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":69426,\"Branch_Name\":\"JADUBABU'S BAZAR\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0005736\",\"MICR_Code\":700002049},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"11A,JATINDRA MOHAN AVENUE,KOLKATA WEST BENGAL 700006\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65921,\"Branch_Name\":\"JATINDRA MOHAN AVENUE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001994\",\"MICR_Code\":700002051},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"1, MIDDLETON STREET, DISTT. KOLKATA, WEST BENGAL 700071\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":67648,\"Branch_Name\":\"JEEVANDEEP\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0003762\",\"MICR_Code\":700002052},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"394A GARIAHAT ROADSOUTH,KOLKATA700068\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":74917,\"Branch_Name\":\"JODHPUR PARK\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0011539\",\"MICR_Code\":700002334},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"44A.CIT.ROAD,CALCUTTA.700010,W.B.\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":67753,\"Branch_Name\":\"JORAMANDIR\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0003868\",\"MICR_Code\":700002053},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"1 BY 1A NANDA MULLICK LANE  DISTT  KOLKATA  WEST BENGAL 700006\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65152,\"Branch_Name\":\"JORASANKO\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001204\",\"MICR_Code\":700002050},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"VILL TAMACHANDRAPUR P.O. ,R C THAKURANI, DT24 PARGANAS, W. BENGAL , PIN - 700104\",\"Branch_City\":null,\"Branch_District\":\"SOUTH 24 PARGANAS\",\"Branch_Id\":72871,\"Branch_Name\":\"KABARDANGA MORE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0009358\",\"MICR_Code\":700002190},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"79 BY C, SHYAMA PRASAD MUKHEE ROAD, KOLKATA 700026\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65663,\"Branch_Name\":\"KALIGHAT\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001722\",\"MICR_Code\":700002054},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"78,PURBACHAL,KALITALA ROAD,KOLKATA\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":67790,\"Branch_Name\":\"KALIKAPUR\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0003907\",\"MICR_Code\":700002317},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"KAMALGAZIMORE, 1613KAMALGAZI, KOLKATA, DISTT.KOLKATA.WESTBENGAL700103\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":98959,\"Branch_Name\":\"KAMALGAZI MORE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0018125\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"1,B.T. ROAD,24 PARGANAS N.,W.B. 700058\",\"Branch_City\":null,\"Branch_District\":\"NORTH 24 PARGANAS\",\"Branch_Id\":65347,\"Branch_Name\":\"KAMARHATI\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001404\",\"MICR_Code\":700002132},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"PLOT NO.79, KALINDI HOUSING ESTATE, KOLKTA, WEST BENGAL ,PIN - 700089\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":71555,\"Branch_Name\":\"KANLINDI HOUSING ESTATE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0007961\",\"MICR_Code\":700002159},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"250,B.B.CHATTERJEE ROAD,CALCUTTA PIN700042\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65428,\"Branch_Name\":\"KASBA KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001486\",\"MICR_Code\":700002055},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"GROUND FLOOR,40 B,KEORAPUKUR,M G ROAD,KOLKATA,DISTT.KOLKATA.WEST BENGAL 700082\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":124220,\"Branch_Name\":\"KEORAPUKUR\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0017363\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"DEEP BHAWAN, KRISHNAPUR MAJHERPARA, PO-KRISHNAPUR, KOLKATA-700102, WEST BENGAL\",\"Branch_City\":null,\"Branch_District\":\"NORTH 24 PARGANAS\",\"Branch_Id\":77500,\"Branch_Name\":\"KESTOPUR\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0014534\",\"MICR_Code\":\"NON-MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"128 BY 15  HAZRA ROAD  DISTT  KOLKATA  WEST BENGAL 700026\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":68571,\"Branch_Name\":\"KHIRODE GHOSH MARKET\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0004772\",\"MICR_Code\":700002056},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"77 D H ROAD, DISTT. KOLKATA, WEST BENGAL 700023\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":67230,\"Branch_Name\":\"KIDDERPORE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0003334\",\"MICR_Code\":700002057},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"DIST  KOLKATA, WEST BENGAL 700 043\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":71515,\"Branch_Name\":\"KIDDERPORE DOCKYARD AREA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0007920\",\"MICR_Code\":700002160},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"16DRSUDHIRBOSEROAD,PBNO10621,KIDDERPOREPO700023KIDDERPOREATSBTCOIN\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":134210,\"Branch_Name\":\"KIDDERPORE KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0070575\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"DIST   NORTH 24 PARGANAS  WEST BENGAL 700052\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":66931,\"Branch_Name\":\"KOLKATA AIR PORT\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0003029\",\"MICR_Code\":700002119},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"333,AJAY NAGAR,KOLKATA 700075\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":132722,\"Branch_Name\":\"KOLKATA AJAY NAGAR\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0031895\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"71,JODHPUR PARK,KOLKATA 700068\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":132721,\"Branch_Name\":\"KOLKATA JODHPUR PARK\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0031894\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"176,SARATBOSEROAD,NEARDESHAPRIYAPARK,SARATBOSEROAD,LAKEMARKET700026LAKEMARKETATSBTCOIN\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":106312,\"Branch_Name\":\"KOLKATA LAKE MARKET\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0070500\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"SAMRIDDHI BHAWAN, 1 STRAND ROAD, KOLKATA 700 001\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":63949,\"Branch_Name\":\"KOLKATA MAIN\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0000001\",\"MICR_Code\":700002021},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"APEEJAY HOUSE,15,PARK STREET,KOLKATA 700 016\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":99392,\"Branch_Name\":\"KOLKATA PARK STREET BRANCH\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0032608\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"132 BY 1,MAHATMA GANDHI ROAD KAVERI HOUSE BURTALLA STREET,CALCUTTA 700007\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":80211,\"Branch_Name\":\"KOLKATA, BURTALLA ST                    \",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0060302\",\"MICR_Code\":700002345},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"9,BRABOURNE ROAD,PB NO 2305 CALCUTTA 700001 WEST BENGAL\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":80060,\"Branch_Name\":\"KOLKATA, MAIN                           \",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0060145\",\"MICR_Code\":700002344},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"CAMAC STREETSHANTI NIKETANKOLKATA700017\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":136170,\"Branch_Name\":\"KOLKATA- CAMAC STREET\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0050271\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"276RAVINDRA SARNI NEAR GANESH TALKIESBURRA BAZARKOLKATA 700007\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":101990,\"Branch_Name\":\"KOLKATA-BURRA BAZAR\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0050474\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"PBNO9036,36,CHOWRINGHEEROAD,MIDDLETOWNROWPO700071CALCUTTAATSBTCOIN\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":105344,\"Branch_Name\":\"KOLKATAMAIN\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0070248\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"11 LOUDON STREET, DISTT. KOLKATA, WEST BENGAL 700017\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":67567,\"Branch_Name\":\"LA MARTINIERE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0003681\",\"MICR_Code\":700002058},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"C I T ROAD,KOLKATA,DISTT.KOLKATA.WEST BENGAL 700014\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":123112,\"Branch_Name\":\"LADIES PARK\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0016632\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"161BY1BY2, LAKE GARDENS, KOLKATA, WEST BENGAL, PIN 700045\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65742,\"Branch_Name\":\"LAKE GARDENS\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001802\",\"MICR_Code\":700002059},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"P BY 871, BLOCK-A, LAKE TOWN,  DISTT. NORTH 24 PARGANAS, WEST BENGAL 700 089\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65448,\"Branch_Name\":\"LAKE TOWN\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001506\",\"MICR_Code\":700002133},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"R1UADGO1014JRARADAJRBUDG1TFRJRBAGURRG\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":129940,\"Branch_Name\":\"LAKETOWN\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0021997\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"3SARATBOSEROADCALCUTTATELEX21816\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":98937,\"Branch_Name\":\"LANSDOWNE KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0020272\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"DIST  KOLKATASTATE WEST BENGAL\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":68948,\"Branch_Name\":\"LIABILITY CPC KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0005237\",\"MICR_Code\":700002249},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"230 BY 1 A J C  BOSE ROAD  DIST  KOLKATA  WEST BENGAL 700020\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":70974,\"Branch_Name\":\"LOWER CIRCULAR ROAD\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0007335\",\"MICR_Code\":700002060},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"210MGROAD,KOLKATA,DIST.SOUTH24PARGANAS,WESTBENGAL700063\",\"Branch_City\":null,\"Branch_District\":\"24 PARGANAS S\",\"Branch_Id\":125345,\"Branch_Name\":\"M G ROAD\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0018120\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"35 AMHERST STREET, KOLKATA, WB 700009\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":69132,\"Branch_Name\":\"M G ROAD (DAFTARIPARA)\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0005437\",\"MICR_Code\":700002061},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"WELKIN.HEIGHTS,1648.GARIA.MAIN.ROAD,GARIA,KOLKATA\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":74746,\"Branch_Name\":\"MAHAMAYATALA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0011363\",\"MICR_Code\":700002323},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"294.2 BY 1, A.P.C. ROAD, DISTT. KOLKATA, WEST BENGAL 700009\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65656,\"Branch_Name\":\"MANICKTALA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001715\",\"MICR_Code\":700002062},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"CIT SCHEME VII M VIP ROAD, DISTT. KOLKATA, WEST BENGAL 700054\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":71092,\"Branch_Name\":\"MANIKTALA CIVIC CENTRE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0007468\",\"MICR_Code\":700002063},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"MEGA DOCUMENT ARCIVAL CENTRE KOLKATA,FPN ENG PVT.LTD,48 TARTALA RD,KOLKATA,WEST BENGAL-700078\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":125752,\"Branch_Name\":\"MEGA DAC KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0018722\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"DIST  KOLKATA, WEST BENGAL 700024\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":67765,\"Branch_Name\":\"METIABURUZ\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0003881\",\"MICR_Code\":700002135},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"JEEVAN SUDHA, 42C J.L.NEHRU ROAD ,DIST KOLKATA STATE WEST BENGAL PIN 700071 \",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":68099,\"Branch_Name\":\"MICR CHEQUE PROCESSING CENTRE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0004269\",\"MICR_Code\":700002000},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"KOLKATA,BENGAL\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":73879,\"Branch_Name\":\"MID CORPORATE LOAN ADMINISTRATION UNIT\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0010481\",\"MICR_Code\":700002807},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"DIST   KOLKATA  WEST BENGAL 700071\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65070,\"Branch_Name\":\"MIDDLETON ROW\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001122\",\"MICR_Code\":700002064},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"10 BY 1H DH ROAD  DISTT  KOLKATA  WEST BENGAL 700027\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65923,\"Branch_Name\":\"MOMINPUR\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001996\",\"MICR_Code\":700002065},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"STATE BANK OF INDIAMONEY SHOPNIZAM PALACEA CBOSE ROADKOLKATAWEST BENGALPIN 700020\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":103906,\"Branch_Name\":\"MONEY SHOP\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0051487\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"PLOT 513 BY A,JESSORE ROAD,3 MOTILAL COLONY,KOLKATA 700081\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":75694,\"Branch_Name\":\"MOTILAL COLONY\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0012377\",\"MICR_Code\":700002555},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"2,DR.SURESH SARKAR RD,KOLKATA\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":71796,\"Branch_Name\":\"MOULALI\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0008210\",\"MICR_Code\":700002174},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"KAVERI NAGAR, DISTT. KARUR, TAMIL NADU 639104\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":69828,\"Branch_Name\":\"MUCHIPARA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0006145\",\"MICR_Code\":700002066},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"1413 MUKUNDAPUR,KOLKATA\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":74907,\"Branch_Name\":\"MUKUNDAPUR\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0011529\",\"MICR_Code\":700002362},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"14, N S ROAD, KOLKATA 700001\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":131247,\"Branch_Name\":\"N S ROAD, KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0031004\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"DA - 6 , DA BLOCK, SALT LAKE, KOLKATA - 700064\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":75719,\"Branch_Name\":\"N.R.I. SALT LAKE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0012402\",\"MICR_Code\":\"NON - MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"21A,DUM.DUM.ROAD,CALCUTTA.PIN.700074\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":68406,\"Branch_Name\":\"NAGERBAZAR\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0004605\",\"MICR_Code\":700002137},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"370 BY 1 BY 7,NETAJI SUBHAS CHANDRA BOSE ROAD, CALCUTTA\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65452,\"Branch_Name\":\"NAKTALA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001510\",\"MICR_Code\":700002068},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"RAMKRISHNA MISSION ASHRAM COMPLEX,P.O. NARENDRAPUR,KOLKATA 700103\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":75662,\"Branch_Name\":\"NARENDRAPUR\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0012341\",\"MICR_Code\":\"NON MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"P 274 C I T SCHEME IV M    C I T ROAD  DT  KOLKATA  WEST BENGAL 700011\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65522,\"Branch_Name\":\"NARKELDANGA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001580\",\"MICR_Code\":700002069},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"NAYABAD ROAD, KOLKATA, DIST.KOLKATA, WEST BENGAL- 700 099\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":125346,\"Branch_Name\":\"NAYABAD\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0018123\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"NETAJI NAGAR, N S C BOSE ROAD, KOLKATA, DIST.KOLKATA, WEST BENGAL- 700 040\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":139231,\"Branch_Name\":\"NETAJI NAGAR\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0018126\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"NETAJI SUBHAS ROAD, KOLKATA 700 001\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":64092,\"Branch_Name\":\"NETAJI SUBHASH ROAD\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0000144\",\"MICR_Code\":700002070},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"23 A BY 76 DH ROAD BLOCK E NEW ALIPORE  DISTT  KOLKATA  WEST BENGAL 700053\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":64153,\"Branch_Name\":\"NEW ALIPORE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0000205\",\"MICR_Code\":700002071},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"HALTU MAIN ROAD, PO.KASBA CALCUTTA WEST BENGAL ,PIN - 700078\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":71051,\"Branch_Name\":\"NEW BALLYGUNGE,\"KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0007423\",\"MICR_Code\":700002136},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"28 BY 9 NEW BALLYGUNGE ROAD, DISTT. KOLKATA WEST BENGAL 700 039\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":71861,\"Branch_Name\":\"NEW BALLYGUNGE, KASBA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0008277\",\"MICR_Code\":700002161},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"13,S.N.BANERJEE ROAD,KOLKATA,W.B.,PIN 700013\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":68463,\"Branch_Name\":\"NEW MARKET\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0004662\",\"MICR_Code\":700002072},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"466.BY.1.M.B.ROAD,CULTURE.MORE,KOLKATA700049\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":74919,\"Branch_Name\":\"NIMTA BELGHORIA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0011541\",\"MICR_Code\":700002330},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"24B NIMTALA GHAT STREET KOLKATA WEST BENGAL\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65594,\"Branch_Name\":\"NIMTALLAH\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001653\",\"MICR_Code\":700002073},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"100/2SUBHAM PLAZA  B.T. ROAD BONHOOGHLY\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":79875,\"Branch_Name\":\"NIOH CAMPUS KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0030468\",\"MICR_Code\":700005007},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"18B,DUM DUM ROAD,KOLKATA WEST BENGAL 700030\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65834,\"Branch_Name\":\"NORTHERN AVENUE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001895\",\"MICR_Code\":700002074},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"JEEVAN SUDHA BUILDING,42 BY C,CHOWRINGEE RD,CALCUTTA\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":69962,\"Branch_Name\":\"NRI KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0006284\",\"MICR_Code\":700002192},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"SAMRIDDHI,BHAVAN1,STRAND,ROADKOLKATA\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":67853,\"Branch_Name\":\"OAD, LHO, KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0003970\",\"MICR_Code\":700002899},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"PO AGARPARA,24 PARGANAS NORTH,WEST BENGAL 700109\",\"Branch_City\":null,\"Branch_District\":\"NORTH 24 PARAGANAS\",\"Branch_Id\":75683,\"Branch_Name\":\"OSMANPUR (BATTALA)\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0012365\",\"MICR_Code\":700002359},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"SAMRIDDHI BHAWAN, 1, STRAND ROAD, KOLKATA - 700 001\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":68604,\"Branch_Name\":\"OVERSEAS BRANCH, KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0004805\",\"MICR_Code\":700002075},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"EHIK13TBOEOCLCUTT\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":127722,\"Branch_Name\":\"P AND S B BRANCH KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0020806\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"1/504 GARIAHAT ROAD, KOLKATA, DISTT.KOLKATA. WEST BENGAL 700068\",\"Branch_City\":null,\"Branch_District\":\"NORTH 24 PARGANAS\",\"Branch_Id\":123109,\"Branch_Name\":\"P.B.B. JODHPUR PARK\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0016626\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"6D,NORTHERN.AVENUE,KOLKATA700037\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65687,\"Branch_Name\":\"PAIKAPARA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001747\",\"MICR_Code\":700002076},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"47,GARFA MAIN ROAD,KOLKATA,WEST BENGAL,PIN - 700075\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":71664,\"Branch_Name\":\"PALBAZAR\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0008077\",\"MICR_Code\":700002162},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"196B DIAMOND HARBOUR, OPP; BEHALA FIRE STATION, DIST.;SOUTH 24 PGS, WEST BENGAL-700008\",\"Branch_City\":null,\"Branch_District\":\"SOUTH 24 PGS\",\"Branch_Id\":79293,\"Branch_Name\":\"PANCHANANTALA(BEHALA)\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0016633\",\"MICR_Code\":\"NON-MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"9 SYED AMIR ALI AVENUE KOLKATA WEST BENGAL\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65689,\"Branch_Name\":\"PARK CIRCUS\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001749\",\"MICR_Code\":700002077},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"12B-PARK STREET, KOLKATA 700071\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":64098,\"Branch_Name\":\"PARK STREET\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0000150\",\"MICR_Code\":700002078},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"ANSL3AKSEECALCUA\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":139120,\"Branch_Name\":\"PARKSTREET KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0020833\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"394 PARNASHREE PALLY KOLKATA  24 PARGANAS SOUTH W B 700060\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65940,\"Branch_Name\":\"PARNASHREE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0002016\",\"MICR_Code\":700002138},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"261 BY 11,PRINCE ANWAR SHAH ROAD,KOLKATTA ,WEST BENGAL ,PIN - 700033\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":69068,\"Branch_Name\":\"PAS ROAD\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0005371\",\"MICR_Code\":700002082},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"FLOOR NO. 1, UPOHAR TOWN CENTER, 2052 UPTC 0117, CHAK GARIA KOLKATA,WEST BENGAL 700107\",\"Branch_City\":null,\"Branch_District\":\"SOUTH 24 PARGANAS\",\"Branch_Id\":79295,\"Branch_Name\":\"PATULI BRANCH\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0016636\",\"MICR_Code\":\"NON MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"148, RASH BEHARI AVENUE KOLKATA, WB 700029\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":68065,\"Branch_Name\":\"PBB DESHPRIYA PARK\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0004233\",\"MICR_Code\":700002211},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"C.I.T.BUILDING,1 BY 16,V.I.P.ROAD KANKURGACHI, WB 700054\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":68037,\"Branch_Name\":\"PBB KANKURGACHI\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0004203\",\"MICR_Code\":700002208},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"CAMAC STREET,KOLKATA,DISTT.KOLKATA.WEST BENGAL 700071\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":123108,\"Branch_Name\":\"PBB PARK STREET\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0016625\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"HA-291,SECTOR-III, SALT LAKE,CALCUTTA,DT 24 PARGANAS  N , W. B 700091\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":68038,\"Branch_Name\":\"PBB SALT LAKE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0004204\",\"MICR_Code\":700002216},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"232 BY  3 PICNIC GARDEN ROAD  CALCUTTA \",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":69296,\"Branch_Name\":\"PICNIC GARDEN\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0005602\",\"MICR_Code\":700002079},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"35C,CHRISTOPHER ROAD,CALCUTTA,KOLKATTA,WEST BENGAL,PIN - 700046\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":72019,\"Branch_Name\":\"POTTERY ROAD\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0008439\",\"MICR_Code\":700002168},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"AB30.BY.1.PRAFULLA.KANAN.WEST,KOLKATA700101\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":74753,\"Branch_Name\":\"PRAFULLA KANAN\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0011370\",\"MICR_Code\":700002340},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"32, GANGAPURI, PURBA PUTIARI CALCUTTA , WEST BENGAL ,PIN - 700093\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":70200,\"Branch_Name\":\"PURBA PUTIARY\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0006528\",\"MICR_Code\":700002200},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"27.MANMATHA.NATH.GANGULY.ROAD,KOLKATA700002\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":74915,\"Branch_Name\":\"R G KAR MEDICAL COLLEGE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0011537\",\"MICR_Code\":700002324},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"RABINDRA BHARATI UNIVERSITY CAMPUS , 56A B T ROAD , KOLKATA ,700050\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":73815,\"Branch_Name\":\"RABINDRA BHARATI UNIVERSITY CAMPUS\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0010417\",\"MICR_Code\":700002313},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"24B RABINDRA SARANI, DISTT. KOLKATA, WEST BENGAL 700007\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":67129,\"Branch_Name\":\"RABINDRA SARANI\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0003231\",\"MICR_Code\":700002084},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"3, MAHENDRA BANERJEE ROAD,  RABINDRANAGAR BEHALA, KOLKATA  700060\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":75668,\"Branch_Name\":\"RABINDRANAGAR\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0012348\",\"MICR_Code\":\"NON-MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"DIST  KOLKATA\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":68293,\"Branch_Name\":\"RACPC, KOKLATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0004490\",\"MICR_Code\":700002995},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"106C, RAJA DINENDRA ST, DISTT. KOLKATA WEST BENGAL  700004\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65922,\"Branch_Name\":\"RAJA D STREET\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001995\",\"MICR_Code\":700002085},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"59/B, RAJA RAM MOHAN ROY ROAD, KOLKATA- 700008\",\"Branch_City\":null,\"Branch_District\":\"NORTH 24 PARAGANAS\",\"Branch_Id\":77490,\"Branch_Name\":\"RAJA RAM MOHAN ROY ROAD\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0014522\",\"MICR_Code\":\"NON-MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"44, GARDEN REACH ROADKOLKATA\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":67042,\"Branch_Name\":\"RAJABAGAN DOCKYARD\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0003142\",\"MICR_Code\":700002140},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"G 4 AND 6 GROUND FLOOR,VIP RD KOYLA VIHAR ABHINANDAN PO AIRPORT KILKOTTA\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":100223,\"Branch_Name\":\"RAJARHAT\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0040711\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"RLRNATURALNESTRAJARHATMEACITYKLKTTABESIDECHARNCKHSPITALKLKATA\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":134425,\"Branch_Name\":\"RAJARHAT\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0021347\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"SWAGATCHINARCOMPLEX,BLOCKA,GROUNDFLOOR,RAJARHATEXPRESSWAY,NEARCHINARPARKCROSSING,KOLKAT\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":106557,\"Branch_Name\":\"RAJARHAT\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0070816\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"PIN743358\",\"Branch_City\":null,\"Branch_District\":\"SOUTH 24 PARGANAS\",\"Branch_Id\":65393,\"Branch_Name\":\"RAJPUR KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001451\",\"MICR_Code\":700002164},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"47 BY A RASH BEHARI AVENUE,KOLKATA, BENGAL, PIN 700026\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65589,\"Branch_Name\":\"RASH BEHAR AVENUE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001648\",\"MICR_Code\":700002086},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"RASH BEHARI AVENUE KOLKATA\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":99665,\"Branch_Name\":\"RASHBEHARI AVENUE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0040229\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"KOLKATA,WEST BENGAL\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":73676,\"Branch_Name\":\"RASMECCC, TITAGARH\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0010265\",\"MICR_Code\":700002395},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"JEEVANDEEP BUILDING, 1 MIDDLETON STREET, 10TH FLOOR, KOLKATA-700071\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":77751,\"Branch_Name\":\"RBO 1, KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0014862\",\"MICR_Code\":\"NON-MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"ZILLA PARISHAD BHAWAN, SBI BARUIPUR, KOLKATA - 700144\",\"Branch_City\":null,\"Branch_District\":\"SOUTH 24 PARAGANAS\",\"Branch_Id\":77071,\"Branch_Name\":\"RCPC, BARUIPUR\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0013986\",\"MICR_Code\":\"NON-MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"DIST   KOLKATA  WEST BENGAL 700032\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":68573,\"Branch_Name\":\"REGENT ESTATE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0004774\",\"MICR_Code\":700002087},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"RIFLE CLUB ROAD BANSDRONI,105 BY 1 NSC BOSE ROAD,KOLKATA,DISTKOLKATA700070\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":74916,\"Branch_Name\":\"RIFLE CLUB ROAD (BANSDRONI)\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0011538\",\"MICR_Code\":700002351},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"5 BY 6  RIFLE ROAD  DISTT  KOLKATA WEST BENGAL  700019\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":71798,\"Branch_Name\":\"RIFLE ROAD\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0008212\",\"MICR_Code\":700002165},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"12 A SOURIN ROY ROAD,BEHALA,KOLKATA,DISTT.KOLKATA.WEST BENGAL 700034\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":138312,\"Branch_Name\":\"ROY BAHADUR ROAD BEHALA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0016768\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"P 27,PHASE I KASBA IND ESTATE EM BYPASS EAST KOLKOTTA\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":135270,\"Branch_Name\":\"RUBY PARK\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0040640\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"87 BY 49A BOSEPUKUR ROAD, DISTT. KOLKATA , WEST BENGAL 700042 700042\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":72018,\"Branch_Name\":\"RUBY PARK\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0008438\",\"MICR_Code\":700002169},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"124 BY 1 B B GANGULY STREET  DISTT  KOLKATA  WEST BENGAL 700012\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":69458,\"Branch_Name\":\"S B DEY STREET\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0005768\",\"MICR_Code\":700002089},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"PURTA BHAVAN,GROUND FLOOR,P.O.SRIPALLY,BURDWAN,WEST BENGAL\",\"Branch_City\":null,\"Branch_District\":\"BARDHAMAN \",\"Branch_Id\":71600,\"Branch_Name\":\"SADARGHAT, SRIPALLY\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0008007\",\"MICR_Code\":713002105},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"JHEEL ROAD, KOLKATA, DIST.KOLKATA. WEST BENGAL-700 031\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":134566,\"Branch_Name\":\"SAHIDNAGAR\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0018116\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"15, DIAMOND HARBOUR RD, KOLKATA, 24 PARGANAS SOUTH ,  WEST BENGAL, PIN  700008\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":66041,\"Branch_Name\":\"SAKHERBAZAR\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0002117\",\"MICR_Code\":700002144},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"14  BELIAGHATA  DISTT  KOLKATA  WEST BENGAL 700015\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":70428,\"Branch_Name\":\"SALES TAX BLDG\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0006771\",\"MICR_Code\":700002088},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"DB-2, SECTOR-I, SALT LAKE CITY, DIST NORTH 24 PARGANAS, WEST BENGAL 700064\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65554,\"Branch_Name\":\"SALT LAKE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001612\",\"MICR_Code\":700002145},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"AC 19, SALT LAKE, SECTOR-1, BIDHANAGAR, KOLKATA, DISTT. 24 PARGANAS(NORTH). WEST BENGAL-700064\",\"Branch_City\":null,\"Branch_District\":\"SOUTH 24 PARGANAS\",\"Branch_Id\":79303,\"Branch_Name\":\"SALT LAKE AC BLOCK\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0016648\",\"MICR_Code\":\"NON-MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"C1 25 26 SECTOR II SALT LAKE CITY KARUNAMOYEE KOLOKOTTA\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":100097,\"Branch_Name\":\"SALT LAKE CITY\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0040539\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"HA-1,SECTORIII,BIDHANNAGAR,SALTLAKECITY700097SALTLAKEATSBTCOIN\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":106450,\"Branch_Name\":\"SALT LAKE CITY KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0070682\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"F330SALTLAKEITYSETOR1KOLKATTA\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":129366,\"Branch_Name\":\"SALT LAKE CITY KOLKATTA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0021258\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"FE 233,SECTOR 3,BIDHAN NAGAR,SALT LAKE,KOLKATA 700091\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":132583,\"Branch_Name\":\"SALT LAKE, KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0031677\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"KOLKATA,WB\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":67993,\"Branch_Name\":\"SAMB, KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0004151\",\"MICR_Code\":700002207},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"NAGALAND HOUSE SHAKESPEARE SARANI KOLKATA WEST BENGAL700071\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":125397,\"Branch_Name\":\"SAMB-II KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0018192\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"JEEVANSUDHA BUILDING, 1 ST FLOOR, 42C J.L. NEHRU ROAD, KOLKATA\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":125400,\"Branch_Name\":\"SAMRO -EAST, KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0018197\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"SANTOSHPUR,49,SANTOSHPUR AVENUE,KOLKATA,DISTKOLKATA700075\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":74913,\"Branch_Name\":\"SANTOSHPUR\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0011535\",\"MICR_Code\":700002350},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"5A, N C DUTTA SARANI, 1ST FLOOR, DISTT. KOLKATA, WEST BENGAL 700001\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":70427,\"Branch_Name\":\"SARAT BOSE ROAD\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0006770\",\"MICR_Code\":700002316},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"KOLKATA\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":68888,\"Branch_Name\":\"SARC, KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0005171\",\"MICR_Code\":700002997},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"166/179, SARSUNA MAIN ROAD, KOLKATA - 700061\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":75667,\"Branch_Name\":\"SARSUNA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0012347\",\"MICR_Code\":\"NON-MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"51,KABI.SUKANTA.SARANI,CALCUTTA,PIN.700085\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":71838,\"Branch_Name\":\"SASTHITALA, KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0008253\",\"MICR_Code\":700002166},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"DR MEGHNATH SAHA SARANI, KOLKATA,WEST BENGAL-700029\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":126071,\"Branch_Name\":\"SBI INTOUCH++ SOUTHERN AVENUE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0019033\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"SAMBHUNATH PANDIT STREET, KOLKATA, DIST.KOLKATA, WEST BENGAL-700025\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":126140,\"Branch_Name\":\"SBIINTOUCH  ELGIN ROAD\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0019124\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"CAMAC STREET,NEW B.K.MARKET, SHAKESPEARE SARANI,KOLKATA, DIST.KOLKATA, WEST BENGAL-700071\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":126141,\"Branch_Name\":\"SBIINTOUCH CAMAC STREET\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0019125\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"PRIMISESNO.80C,ALIPOREROAD,KOLKATA,DISTT.KOLKATA.WESTBENGAL700027\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":126142,\"Branch_Name\":\"SBIINTOUCH NEW ALIPORE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0019127\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"PRINCE ANWAR SHAH ROAD, OPP. SOUTH CITY MALL, KOLKATA, DIST.KOLKATA, WEST BENGAL-700045\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":126070,\"Branch_Name\":\"SBIINTOUCH P.A.SHAH ROAD\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0019032\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"SYED AMIR ALI AVENUE, KOLKATA, DIST.KOLKATA, WEST BENGAL-700017\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":126069,\"Branch_Name\":\"SBIINTOUCH QUEST MALL\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0019031\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"SARAT BOSE ROAD, KOLKATA, DIST.KOLKATA, WB-7000126\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":126214,\"Branch_Name\":\"SBIINTOUCH SARAT BOSE ROAD\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0019210\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"302,A.P.C.ROAD,CALCUTTA,WEST BENGAL,PIN 700009\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":66985,\"Branch_Name\":\"SEALDAH\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0003084\",\"MICR_Code\":700002090},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"BJ-114, SECTOR-II, SALT LAKE , KOLKATA - 700091\",\"Branch_City\":null,\"Branch_District\":\"NORTH 24 PARAGANAS\",\"Branch_Id\":75680,\"Branch_Name\":\"SECTOR - II, SALT LAKE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0012361\",\"MICR_Code\":\"NON-MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"IB20 SECTOR III,SALT LAKE CITY KOLKATA\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":139935,\"Branch_Name\":\"SECTOR III SALT LAKE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0040662\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"JEEVAN DEEP,6 AND 7TH FLOOR,1 MIDDLETON STREET,DISTT KOLKATA,W B 700001\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":72125,\"Branch_Name\":\"SERVICE BRANCH , KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0008554\",\"MICR_Code\":700002001},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"9, BRABOUANE ROAD\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":79821,\"Branch_Name\":\"SERVICE BRANCH, KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0030409\",\"MICR_Code\":700005001},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"DIST   KOLKATA  WEST BENGAL 700071\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":66933,\"Branch_Name\":\"SHAKESPEARE SARANI \",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0003031\",\"MICR_Code\":700002091},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"24A PARIJAT SHAKESPEARE SARANI KOLKOTTA\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":137089,\"Branch_Name\":\"SHAKESPEARE SARANI\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0040343\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"ACHARYA PRAFULLA CHANDER RAI ROAD, SHYAM BAZAR, KOLKATA 700004\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":132439,\"Branch_Name\":\"SHYAM BAZAR, KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0031486\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"124A BIDHAN SARANI  CALCUTTA \",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":64128,\"Branch_Name\":\"SHYAMBAZAR \",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0000180\",\"MICR_Code\":700002092},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"MAHARANI VILLA, A/6 E VIDYASAGAR SARANI(JAMES LONG SARANI), DISTT. SOUTH 24 PGS. WEST BENGAL-700063\",\"Branch_City\":null,\"Branch_District\":\"SOUTH 24 PGS\",\"Branch_Id\":77489,\"Branch_Name\":\"SILPARA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0014521\",\"MICR_Code\":\"NON-MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"423D MOTILAL GUPTA ROAD,KOLKATA 700082\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":74911,\"Branch_Name\":\"SIRITI - MUCHIPARA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0011533\",\"MICR_Code\":700002348},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"RASH BEHARI AVENUE, KOLKATA, PIN - 700026\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65712,\"Branch_Name\":\"SITALATALA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001772\",\"MICR_Code\":700002093},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"XI, 8 1 E P BLOCK SALT LAKE ,SECTOR-VKOLKATA PIN - 700091\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":73415,\"Branch_Name\":\"SLE COMPLEX, KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0009985\",\"MICR_Code\":700002193},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"63,NALINI RANJAN AVENUE,KOLKATA 700053\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":75628,\"Branch_Name\":\"SME NEW ALIPORE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0012305\",\"MICR_Code\":700002353},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"OM TOWER, 1ST FLOOR, 36C, B.T. ROAD, KOLKATA - 700002\",\"Branch_City\":null,\"Branch_District\":\"NORTH 24 PARGANAS\",\"Branch_Id\":75669,\"Branch_Name\":\"SME(NORTH KOLKATA) CHIRIAMORE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0012349\",\"MICR_Code\":\"NON-MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"BENGAL\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":68750,\"Branch_Name\":\"SMECC (NEW BRANCH)\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0005011\",\"MICR_Code\":700002299},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"SKY STAR BUILDING, 4TH FLOOR, SEVOKE ROAD, SILIGURI, DIST: DARJEELING, WEST BENGAL: 734001\",\"Branch_City\":null,\"Branch_District\":\"DARJEELING\",\"Branch_Id\":78529,\"Branch_Name\":\"SMECC,SILIGURI\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0015747\",\"MICR_Code\":\"NON-MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"1, MIDDLETON STREET,JEEVANDEEP BUILDING, 5TH FLOOR,KOLKATA - 700071\",\"Branch_City\":null,\"Branch_District\":\"NORTH 24 PARAGANAS\",\"Branch_Id\":78527,\"Branch_Name\":\"SMECCC,BALLYGUNGE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0015743\",\"MICR_Code\":\"NON-MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"SODEPUR STATION ROAD PIN743178\",\"Branch_City\":null,\"Branch_District\":\"NORTH 24 PARGANAS\",\"Branch_Id\":65736,\"Branch_Name\":\"SODEPUR KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001796\",\"MICR_Code\":700002167},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"4TH FLOOR JEEVANSUDHA BUILDING J L NEHRU ROAD 24PARGANASSWEST BENGAL700071\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":124670,\"Branch_Name\":\"SOUTH 24 PARGANAS ZONE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0017988\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"66A, KALICHARAN GHOSH RD, CALCUTTA 700050\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":69071,\"Branch_Name\":\"SOUTH SINTHEE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0005374\",\"MICR_Code\":700002094},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"129 S P MUKHERJEE ROAD  DISTT  KOLKATA  WEST BENGAL 700026\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65447,\"Branch_Name\":\"SOUTHERN AVENUE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001505\",\"MICR_Code\":700002095},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"485,RABINDRA SARANI,KOLKATTA, WEST BENGAL 700005\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65588,\"Branch_Name\":\"SOVABAZAAR, KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001647\",\"MICR_Code\":700002096},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"SAMRIDDHI BHAVAN, 4TH FLOOR, 1, STRAND ROAD, KOLKATA - 700001\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":77492,\"Branch_Name\":\"SPECIALISED INSTITUTIONAL BANKING, KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0014524\",\"MICR_Code\":\"NON-MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"9, BRABOURNE ROAD, KOLKATA -700001\",\"Branch_City\":null,\"Branch_District\":\"NORTH 24 PARAGANAS\",\"Branch_Id\":78029,\"Branch_Name\":\"SPECIALISED TEA BRANCH\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0015197\",\"MICR_Code\":\"NON-MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"39 JAWAHARLAL  NEHRU  ROAD, DIST :  KOLKATA, WEST BENGAL - 700071\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":77713,\"Branch_Name\":\"SPL. CURRENCY ADMINISTRATIVE BRANCH\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0014821\",\"MICR_Code\":\"NON-MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"1, MIDDLETON STREET,JEEVANDEEP BUILDING, 5TH FLOOR,KOLKATA - 700071\",\"Branch_City\":null,\"Branch_District\":\"NORTH 24 PARAGANAS\",\"Branch_Id\":78028,\"Branch_Name\":\"SPL.CHOWRINGHEE SME \",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0015196\",\"MICR_Code\":\"NON-MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"BHAWANIEXOTICA,9971DAKSHINDARIROAD,KOLKATA,DISTT.24PARGANS(N),WESTBENGAL700048\",\"Branch_City\":null,\"Branch_District\":\"24 PARGANAS N\",\"Branch_Id\":125351,\"Branch_Name\":\"SREE BHUMI\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0018131\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"22 BY 1  AND  22 BY 3 SREEPALLY PALTA  P O  BENGAL ENAMEL  PALTA   743122\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":73513,\"Branch_Name\":\"SREEPALLY PALTA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0010091\",\"MICR_Code\":700002312},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"11 DR. U.N.BRAMHACHARI ST KOLKATA WEST BENGAL\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":68630,\"Branch_Name\":\"SSI BHOWANIPORE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0004833\",\"MICR_Code\":700002197},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"KOLKATA, WEST BENGAL, PIN - 700012\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65446,\"Branch_Name\":\"SUBODH MULLICK SQUARE, KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001504\",\"MICR_Code\":700002098},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"C211SURVEYPARK,SANTOSHPUR,KOLKATA,DISTT.KOLKATA.WESTBENGAL700075\",\"Branch_City\":null,\"Branch_District\":\"24 PARGANAS N\",\"Branch_Id\":125347,\"Branch_Name\":\"SURVEY PARK\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0018124\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"5.72,SURYA.SEN.STREET,CALCUTTA,PIN.700012\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":67386,\"Branch_Name\":\"SURYA SEN STREET\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0003496\",\"MICR_Code\":700002099},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"INSTITUTE OF  FAMILY AND HEALTH WELFARE  29 GN BLOCK  SEC V  BIDHANNAGAR  KOLKATA 700091\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":73470,\"Branch_Name\":\"SWASTHYA BHAWAN\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0010043\",\"MICR_Code\":700002311},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"47/3, PURNA CHANDRA MITRA LANE, TOLLY GUNJ, SWISS PARK\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":79711,\"Branch_Name\":\"SWISS PARK KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0030287\",\"MICR_Code\":700005005},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"3 WAVELY LANE  DISTT  KOLKATA  WEST BENGAL 700013\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":68570,\"Branch_Name\":\"TALTALA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0004771\",\"MICR_Code\":700002100},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"4A PAGLADANGS 1ST LANE, DISTT. KOLKATA, WEST BENGAL 700015\",\"Branch_City\":null,\"Branch_District\":\"LUCKNOW\",\"Branch_Id\":72323,\"Branch_Name\":\"TANGRA PANCHANANTALA\",\"Branch_State\":\"UTTAR PRADESH\",\"IFSC_Code\":\"SBIN0008761\",\"MICR_Code\":700002179},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"42 BY A, DIAMOND HARBOUR ROAD, DISTT. 24 PARGANAS WEST BENGAL 700 038\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":68448,\"Branch_Name\":\"TARATALA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0004647\",\"MICR_Code\":700002146},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"P 1  TARATALA ROAD  DISTT  24 PARGANAS SOUTH    WEST BENGAL  700088\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":70674,\"Branch_Name\":\"TARATALA INDUSTRIAL ESTATE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0007026\",\"MICR_Code\":700002148},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"VILL. RAGHUNATHPUR, P.O. TEGHORIA - BARASAT, P.S.RAJHAT,24 PGS N ,W.B., PIN - 700059\",\"Branch_City\":null,\"Branch_District\":\"BIRBHUM\",\"Branch_Id\":72298,\"Branch_Name\":\"TEGHORIA RAGHUNATHPUR, KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0008735\",\"MICR_Code\":700002183},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"THAKURPUKUR,P.O.BARISHA,KOLKATA,DIST.24.PARGANASSOUTH\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65301,\"Branch_Name\":\"THAKURPUKUR, KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001357\",\"MICR_Code\":700002147},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"13 B T ROAD,KOLKATTA ,DT24 PARGANAS  N , W. BENGAL ,PIN - 743188\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":68526,\"Branch_Name\":\"TITAGARH\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0004727\",\"MICR_Code\":700002212},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"63 NSC BOSE ROAD  DISTT  KOLKATA  WEST BENGAL 700040\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65660,\"Branch_Name\":\"TOLLYGUNGE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001719\",\"MICR_Code\":700002103},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"KOLKATA,WEST BENGAL\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65741,\"Branch_Name\":\"TOLLYGUNGE CIR. ROAD\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001801\",\"MICR_Code\":700002102},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"151.CHANDI.GHOSH.ROAD,KOLKATA,WEST.BENGAL,PIN.700040.\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":69374,\"Branch_Name\":\"TOLLYGUNGE NEW THEATER\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0005680\",\"MICR_Code\":700002104},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"23P DRAMBEDKAR SRANI,2ND FLOOR KOLKATA700046\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":74910,\"Branch_Name\":\"TOPSIA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0011532\",\"MICR_Code\":700002331},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"KOLKATA WB\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":68783,\"Branch_Name\":\"TRADE FINANCE CPC KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0005052\",\"MICR_Code\":700002806},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"100 RASH BEHARI AVENUE KOLKATA WEST BENGAL\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65739,\"Branch_Name\":\"TRIANGULAR PARK\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001799\",\"MICR_Code\":700002105},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"P185 CIT SCHEME VII M,KOLKATA 700 054,W.B.\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65771,\"Branch_Name\":\"ULTADANGA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001831\",\"MICR_Code\":700002106},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"GOPAL MISHRA ROAD, BEHALA, KOLKATA, DIST.KOLKATA, WEST BENGAL-700 034\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":136393,\"Branch_Name\":\"UNIQUE PARK\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0018119\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"83 BY 1A, VIVEKANANDA ROAD, KOLKATA 700006\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65592,\"Branch_Name\":\"VIVEKANAND BRANCH\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001651\",\"MICR_Code\":700002107},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"2061F, VIDHAN SARANI, KOLKATA 700006\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":132482,\"Branch_Name\":\"VIVEKANAND ROAD, KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0031539\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"1,HAJI MHD.MOHASIN SQUARE, KOLKATA,KOLKATA, WEST BENGAL 700016\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65664,\"Branch_Name\":\"WELLESLEY PARK\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001723\",\"MICR_Code\":700002108},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"63,NSCBOSE ROAD2ND FLOOR\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":74729,\"Branch_Name\":\"ZONAL INSPECTION OFFICE, KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0011344\",\"MICR_Code\":700002996},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"11 AND 13, SHAKESPEARE SARANIKOLKATA\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":70536,\"Branch_Name\":\"ZONAL OFFICE,  KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0006884\",\"MICR_Code\":700002888},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"ZONAL OFFICE,BIDHAN NAGAR\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":70578,\"Branch_Name\":\"ZONAL OFFICE, BIDHAN NAGAR\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0006928\",\"MICR_Code\":700002777}],\"ReturnCode\":1,\"ReturnMsg\":\"Record retrieved successfully\",\"UDP\":null}";
					branchOutput = mapper.readValue(text, GetFPurchaseBranchOutput.class);
				} catch (IOException e) {
					e.printStackTrace();
				}*/



		logger.info("getBankDetailsByIFSCl() - complete");
		return ifscOutput;

	}

	/* (non-Javadoc)
	 * @see com.app.services.partners.Interfaces.InvestmentConnectorInterfaces#SavePreSIPMultipleSchemes(java.lang.String, java.lang.Object, com.app.beans.entity.investment.MFInvestForm)
	 */
	@Override
	public Object savePreSIPMultipleSchemes(String primeFlio, String activeFolioNumber, Object accessToken, MFInvestForm mfInvestForm, UserBankDetails bankDetails, ClientSystemDetails clientSystem) {
		logger.info("Save preSIP Multiple schemes " );
		ObjectMapper mapper = new ObjectMapper();  

		SavePreSIPMultipleSchemesInput schemeInput = new SavePreSIPMultipleSchemesInput();
		SavePreSIPMultipleSchemesOutput schemeOutput =null;
		String firstInstallment="";
		String resquestBody="";

		firstInstallment=mfInvestForm.getSelectedFund().getMonthlySavings();
		try {
			schemeInput = BirlaBeansMapper.ToSavePreSIPSchmesInput(primeFlio, activeFolioNumber, mfInvestForm, bankDetails);
			resquestBody = mapper.writeValueAsString(schemeInput);
			logger.info(resquestBody);

			ResponseEntity<String> response =  postcallwithHeaders(getUri(SAVE_PRE_SIP_MULTIPLE_SCHEMES), new HttpEntity<String>(resquestBody,generateHttpHeaders((primeFlio==null?"":primeFlio)+"|"+(activeFolioNumber==null?"":activeFolioNumber)+"|"+firstInstallment)));
			logger.info(response.getBody());

			schemeOutput = mapper.readValue(response.getBody(), SavePreSIPMultipleSchemesOutput.class);

			logger.info("Birla save pre schemes retrieved successfully");
		}catch (JsonProcessingException e1) {
			logger.error("savePreSIPMultipleSchemes(): Error processing json "+ e1.getMessage());
		}catch (IOException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}

		/*try {

			schemeInput = BirlaBeansMapper.ToSavePreSIPSchmesInput(primeFlio, activeFolioNumber, mfInvestForm, bankDetails);
			String text="{ \"ReturnCode\": 1, \"ReturnMsg\": \"Record retrieved successfully\", \"SchemeDetails\": [ { \"SchemeCode\": \"106D\", \"SchemeReferenceId\": null, \"SchemeTransNo\": null } ], \"TransNo\": 27561, \"TransReferenceNo\": \"SP27561\", \"TransactionDetails\": [ null ], \"UDP\": null, \"URNNumber\": \"BS141396-027561\" }";
			schemeOutput = mapper.readValue(text, SavePreSIPMultipleSchemesOutput.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

		logger.info("Save Pre-SIP Multiple schemes - complete");
		return schemeOutput;
	}


	/* (non-Javadoc)
	 * @see com.app.services.partners.Interfaces.InvestmentConnectorInterfaces#savePostSIPMultipleSchemes(java.lang.String, java.lang.Object, com.app.beans.entity.investment.MFInvestForm)
	 */
	@Override
	public Object savePostSIPMultipleSchemes(FolioCreationStatus folioStatus, String paymentStatus, Object accessToken, MFInvestForm mfInvestForm, UserBankDetails bankDetails, ClientSystemDetails clientSystem) {
		logger.info("Save post SIP Multiple schemes " );
		ObjectMapper mapper = new ObjectMapper();  


		SavePostSIPMultipleSchemesOutput schemesOutput = new SavePostSIPMultipleSchemesOutput();

//		String transNo=""; // reference number returned in SavePreSIPMultipleSchemes
		String resquestBody="";


		try {
			SavePostSIPMultipleSchemesInput saveInput = BirlaBeansMapper.ToSavepostSIPSchmesInput(folioStatus, mfInvestForm.getSelectedFund().getMonthlySavings(), paymentStatus, clientSystem);

			resquestBody = mapper.writeValueAsString(saveInput);
			logger.info(resquestBody);

			ResponseEntity<String> response =  postcallwithHeaders(getUri(SAVE_POST_SIP_MULTIPLE_SCHEMES), new HttpEntity<String>(resquestBody,generateHttpHeaders((folioStatus.getFolioNumber()==null?"":folioStatus.getFolioNumber())+"|"+folioStatus.getTransNo())));
			logger.info("Response post scheme details save from AMC- "+ response.getBody());

			schemesOutput = mapper.readValue(response.getBody(), SavePostSIPMultipleSchemesOutput.class);
			logger.info("Birla save post schemes retrieved successfully");
		}catch(Exception e){
			logger.error("Unable to SavePostSIPMultipleSchemesInput for folio number- "+ folioStatus.getFolioNumber());
			logger.error(e.getMessage());
			e.printStackTrace();
			schemesOutput=null;
		}

		logger.info("Save POST SIP Multiple schemes - complete");
		return schemesOutput;
	}



	/* (non-Javadoc)
	 * List the schemes based on the category (P/SIP/CSIP/SCTP)
	 * @see com.app.services.partners.Interfaces.InvestmentConnectorInterfaces#getSChemeDetails(java.lang.String, java.lang.Object, java.lang.Object)
	 */
	@Override
	public Object getSChemeDetails(String transType,String authCode, Object accessToken, Object mfInvestForm, ClientSystemDetails clientSystem) {
		logger.info("Requesting scheme detailed list for the day. " );
		ObjectMapper mapper = new ObjectMapper();  

		GetSchemeMasterDetailsInput detailsInput= new GetSchemeMasterDetailsInput();
		GetSchemeMasterDetailsInput.Request request = new Request();
		GetSchemeMasterDetailsOutput detailOutput = null;

		String resquestBody="";

		/*Generate checksum and get access token*/

		request.setUserId(USERID);
		request.setPassword(PASSWORD);
		request.setTransType(transType==null?"":transType);				// Accepted values- P/S/C/STPF/STPT/SWP/SWITCHF/SWITCHT/RD/TRFROM/TRTO/IR/<blank for all type transactions>
		request.setAuthCode(authCode==null?"":authCode);
		detailsInput.setRequestObject(request);

		try {

			resquestBody = mapper.writeValueAsString(detailsInput);
			logger.info(resquestBody);

			ResponseEntity<String> response =  postcallwithHeaders(getUri(GET_PRODUCT_SCHEME_DETAILS_URI), new HttpEntity<String>(resquestBody,generateHttpHeaders((transType==null?"":transType)+"|"+(authCode==null?"":authCode))));
			logger.info(response.getBody());

			detailOutput = mapper.readValue(response.getBody(), GetSchemeMasterDetailsOutput.class);
			logger.info("Total SCHEMES- "+ detailOutput.getSchemeDetails().size());


			logger.info("Birla proudtc details retrieved successfully");
		}catch (JsonProcessingException e1) {
			e1.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}catch(HttpServerErrorException e){
			e.getRawStatusCode();
		}catch(Exception e){
			e.printStackTrace();
		}


		logger.info("GetProductdetails - complete");
		return detailOutput;
	}


	/* (non-Javadoc)
	 * List of SIP and details of the scheme
	 * @see com.app.services.partners.Interfaces.InvestmentConnectorInterfaces#getProductDetails(java.lang.String)
	 */
	@Override
	public Object getProductDetails(String transactionType,String authCode,  Object accessToken, Object mfInvestForm, ClientSystemDetails clientSystem) {
		logger.info("Requesting product details for the day. " );
		ObjectMapper mapper = new ObjectMapper();  

		GetProductDetailsInput productInput = new GetProductDetailsInput();
		GetProductDetailsInput.ProductDetailsRequest productInputDetails = new ProductDetailsRequest();
		GetProductDetailsOutput detailsOutput = null;
		String resquestBody="";

		/*Generate checksum and get access token*/

		productInputDetails.setUserId(USERID);
		productInputDetails.setPassword(PASSWORD);
		productInput.setProductDetailsRequestObject(productInputDetails);


		try {
			resquestBody = mapper.writeValueAsString(productInput);
			logger.info(resquestBody);
			ResponseEntity<String> response =  postcallwithHeaders(getUri(GET_PRODUCT_DETAILS_URI), new HttpEntity<String>(resquestBody,generateHttpHeaders(USERID)));
			logger.info(response.getBody());
			detailsOutput = mapper.readValue(response.getBody(), GetProductDetailsOutput.class);
			logger.info("Total AUMs- "+ detailsOutput.getSchemeDetails().size());
			logger.info("Birla proudtc details retrieved successfully");

		} catch (JsonProcessingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch(HttpServerErrorException e){
			e.getRawStatusCode();
		} catch(Exception e){
			e.printStackTrace();
		}

		logger.info("GetProductdetails - complete");
		return detailsOutput;
	}


	/* (non-Javadoc)
	 * @see com.app.services.partners.Interfaces.InvestmentConnectorInterfaces#getSIPBankDEtails(java.lang.String)
	 */
	@Override
	public Object getFPurchaseBanks(String selectedAMC, Object token, ClientSystemDetails clientSystem) {

		logger.info("Requesting list of banks from Birla- " );
		ObjectMapper mapper = new ObjectMapper(); 

		GetFPurchaseBanksInput banksInput = new GetFPurchaseBanksInput();
		GetFPurchaseBanksInput.Bank bankDetails = new Bank();
		GetFPurchaseBanksOutput banksOutput = null;

		String resquestBody="";

		bankDetails.setUserId(USERID);
		bankDetails.setPassword(PASSWORD);

		banksInput.setBankObject(bankDetails);

		try {
			resquestBody = mapper.writeValueAsString(banksInput);
			logger.info(resquestBody);
			ResponseEntity<String> response =  postcallwithHeaders(getUri(GET_LIST_BANKS_URI), new HttpEntity<String>(resquestBody,generateHttpHeaders(USERID)));
			//			logger.info(response.getBody());
			logger.debug(response.getBody());
			banksOutput = mapper.readValue(response.getBody(), GetFPurchaseBanksOutput.class);
			logger.info("Total Banks list received- "+ banksOutput.getFpPrchaseBankslist().size());
			logger.info("Birla proudtc details retrieved successfully");
		} catch (JsonProcessingException e1) {
			e1.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}catch(HttpServerErrorException e){
			e.getRawStatusCode();
		} catch(Exception e){
			e.printStackTrace();
		}

		logger.info("getFPurchaseBanks - complete");
		return banksOutput;

	}


	/* (non-Javadoc)
	 * @see com.app.services.partners.Interfaces.InvestmentConnectorInterfaces#getFPurchaseBankCity(java.lang.String, java.lang.String, java.lang.Object)
	 */
	@Override
	public Object getFPurchaseBankCity(String bankName, String bankId, Object token, ClientSystemDetails clientSystem) {
		return null;
	}



	/* (non-Javadoc)
	 * @see com.app.services.partners.Interfaces.InvestmentConnectorInterfaces#getFPurchaseBankBranch(java.lang.String, java.lang.String, java.lang.String, java.lang.Object)
	 */
	@Override
	public Object getFPurchaseBankBranch(String bankName, String bankId, String branchCity, Object token, ClientSystemDetails clientSystem) {
		// TODO Auto-generated method stub

		logger.info("Requesting branch of bank in city from Birla for bank ID-  "+ bankId +", city: "+branchCity );
		logger.info("Requesting branch of bank in city from Birla for bank ID-  "+ bankId +", city: "+branchCity );

		ObjectMapper mapper = new ObjectMapper(); 

		GetFPurchaseBranchInput branchInput = new GetFPurchaseBranchInput();
		GetFPurchaseBranchInput.Branch branch = new Branch();
		GetFPurchaseBranchOutput branchOutput = null;

		String resquestBody="";

		branch.setUserId(USERID);
		branch.setPassword(PASSWORD);
		branch.setBankId(bankId);
		branch.setBranchCity(branchCity);
		branchInput.setBranchObject(branch);


		try {
			resquestBody = mapper.writeValueAsString(branchInput);
			logger.info(resquestBody);
			ResponseEntity<String> response =  postcallwithHeaders(getUri(GET_CITY_BANK_BRANCH_URI), new HttpEntity<String>(resquestBody,generateHttpHeaders(bankId+"|"+branchCity.toUpperCase())));
			//			logger.info(response.getBody());
			logger.debug(response.getBody());
			branchOutput = mapper.readValue(response.getBody(), GetFPurchaseBranchOutput.class);
			logger.info("Total Branch- "+ branchOutput.getFpPrchaseBranches().size());
			logger.info("Birla proudtc details retrieved successfully");

		} catch (JsonProcessingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch(HttpServerErrorException e){
			e.getRawStatusCode();
		} catch(Exception e){
			e.printStackTrace();
		}


		/*try {
			//		String text="{\"AadharReturnCode\":null,\"AadharReturnMessage\":\"OTP Sent \",\"AppKYCMode\":null,\"Flag\":true,\"IRISRefId\":null,\"PAN_PEKRN\":null,\"RefID\":11004,\"ReturnCode\":1,\"ReturnMsg\":\"Record retrieved successfully\",\"UDP\":null}";
			//		String text="{\"AadharReturnCode\":null,\"AadharReturnMessage\":\"Aadhaar number is incorrect. Resident shall use correct Aadhaar\",\"AppKYCMode\":null,\"Flag\":false,\"IRISRefId\":null,\"PAN_PEKRN\":null,\"RefID\":11005,\"ReturnCode\":0,\"ReturnMsg\":\"Aadhaar number is incorrect. Resident shall use correct Aadhaar\",\"UDP\":null}";
			String text="{\"FPurchaseBranches\":[{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"AE MARKRT SALT LAKE CITY KOLKATA W.B. 24 PARGANAS N\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":70451,\"Branch_Name\":\"A.E MARKET\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0006794\",\"MICR_Code\":700002110},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"6 DR. S P MUKHERJEE ROAD, BELGHORIA, CALCUTTA - 70056,W.B., DIST 24 PARGANAS NORTH\",\"Branch_City\":null,\"Branch_District\":\"NORTH 24 PARGANAS\",\"Branch_Id\":71638,\"Branch_Name\":\"AGARPARA TEXMACO AREA \",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0008046\",\"MICR_Code\":700002149},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"AKRASTATIONROAD,POPSMAHESHTALA,KOLKATA,DISTT.SOUTH24PARGANAS,WESTBENGAL700141.\",\"Branch_City\":null,\"Branch_District\":\"24 PARGANAS S\",\"Branch_Id\":125349,\"Branch_Name\":\"AKRA STATION ROAD\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0018128\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"52 BY 2 SURYA SEN ROAD ALAMBAZAR 24 PARGANAS N  WEST BENGAL PIN 700035\",\"Branch_City\":null,\"Branch_District\":\"NORTH 24 PARGANAS\",\"Branch_Id\":65300,\"Branch_Name\":\"ALAM BAZAR\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001356\",\"MICR_Code\":700002111},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"24 BY 1 BY 1  ALIPORE ROAD   T  KOLKATA  W BENGAL 700027\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":63952,\"Branch_Name\":\"ALIPORE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0000004\",\"MICR_Code\":700002002},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"12, BIPLABI KANAI BHATTACHRJEE SARANI, KOLKATA 700027\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":73323,\"Branch_Name\":\"ALIPORE COURT TREASURY\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0009884\",\"MICR_Code\":700002189},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"1B,MAHENDRA SRIMANI,KOLKATA,WEST BENGAL\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65740,\"Branch_Name\":\"AMHERST STREET\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001800\",\"MICR_Code\":700002003},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"SIDDHA, 5TH AVENUE, 179 ANADAPUR, DISTT. 24 PARGANAS (SOUTH). KOLKATA. WEST BENGAL-700107\",\"Branch_City\":null,\"Branch_District\":\"24 PARGANAS(SOUTH)\",\"Branch_Id\":79294,\"Branch_Name\":\"ANANDAPUR\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0016634\",\"MICR_Code\":\"NON-MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"1A,ASHUTOSH MUKHERJEE ROAD NEAR ELGIN ROAD CROSSING BHAVANIPUR,CALCUTTA 700020\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":80296,\"Branch_Name\":\"ASHUTOSH MUKHERJEE ROAD, KOLKATA.       \",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0060393\",\"MICR_Code\":700002346},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"ABHOY GUHA ROAD, DEYS MARKET, KOLKATA 700006\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":132557,\"Branch_Name\":\"AZAD HIND BAGH, KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0031638\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"GUNADHAR BABU LANE DISTT. KOLKATA WEST BENGAL 700012\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":70445,\"Branch_Name\":\"B B GANGULLY STREET\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0006788\",\"MICR_Code\":700002015},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"DIST   KOLKATA  WEST BENGAL 700001\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65166,\"Branch_Name\":\"B R B B ROAD\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001218\",\"MICR_Code\":700002018},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"DIST   KOLKATA  WEST BENGAL 700002\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":67607,\"Branch_Name\":\"B T ROAD\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0003721\",\"MICR_Code\":700002004},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"104 BY 2, B.K.PAUL AVENUE, DISTT. KOLKATA, WEST BENGAL 700005\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65707,\"Branch_Name\":\"B. K. PAUL AVENUE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001767\",\"MICR_Code\":700002005},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"29,GANESH CHANDRA AVENUE,BIPLABI ANKUL CHANDRA STREET, ,KOLKATA, WEST BENGAL ,PIN - 700013\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":69278,\"Branch_Name\":\"BAC STREET\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0005583\",\"MICR_Code\":700002083},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"KATHOR MORE, BADU ROAD, P.O.-BADU, KOLKATA, WEST BENGAL-700 128\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":77501,\"Branch_Name\":\"BADU ROAD\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0014537\",\"MICR_Code\":\"NON-MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"2A,GIRISH AVENUE, CALCUTTA, WEST BENGAL, WEST BENGAL 700003\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65593,\"Branch_Name\":\"BAGH BAZAR\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001652\",\"MICR_Code\":700002007},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"1A ASHOK ROAD, KOLKATA, WEST BENGAL, PIN 700084\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":70446,\"Branch_Name\":\"BAGHAJATIN BZR.\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0006789\",\"MICR_Code\":700002006},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"P36,C.I.T.SCHEME,MVII.HS.XII,CALCUTTA.PIN.700054\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":67568,\"Branch_Name\":\"BAGMARI\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0003682\",\"MICR_Code\":700002008},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"23 BRRB ROAD  DISTT  KOLKATA  WEST BENGAL 700001\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65688,\"Branch_Name\":\"BAGRI MARKET\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001748\",\"MICR_Code\":700002009},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"BAKHRAHAT ROAD, KOLKATA,DIST.KOLKATA, WESTBENGAL-700104\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":137403,\"Branch_Name\":\"BAKHRAHAT ROAD\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0018122\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"24 PARGANAS SOUTH , WEST BENGAL, PIN  700061\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65431,\"Branch_Name\":\"BAKUTALA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001489\",\"MICR_Code\":700002118},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"CALCUTTA,\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":63966,\"Branch_Name\":\"BALLYGUNGE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0000018\",\"MICR_Code\":700002010},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"DIST. KOLKATA, WEST BENGAL\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":74912,\"Branch_Name\":\"BALLYGUNGE PARK\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0011534\",\"MICR_Code\":\"NON MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"54 EKDALIA RD KOLKATA WEST BENGAL\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":67834,\"Branch_Name\":\"BALLYGUNGE RLY. STN.\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0003951\",\"MICR_Code\":700002011},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"208, RASHBEHARI AVENUE, BALLYGUNJ, KOLKATA 700001\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":138086,\"Branch_Name\":\"BALLYGUNJ, KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0031376\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"87,BANGUR AVENUE, BLOCK A,P.O. ,BANGUR AVENUE, KOLKATA , PIN - 700055\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":72805,\"Branch_Name\":\"BANGUR AVENUE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0009289\",\"MICR_Code\":700002210},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"334 BY 1 NSC BOSE ROAD,KOLKATA\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":74914,\"Branch_Name\":\"BANSDRONI\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0011536\",\"MICR_Code\":700002325},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"BAUR PURATAN BAZAR, DIST-SOUTH 24 PARAGANAS, WEST BENGAL\",\"Branch_City\":null,\"Branch_District\":\"SOUTH 24 PARAGANAS\",\"Branch_Id\":77113,\"Branch_Name\":\"BARUIPUR BAZAR\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0014031\",\"MICR_Code\":\"NON-MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"85B,BEADON STREET, CALCUTTA\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65884,\"Branch_Name\":\"BEADON STREET\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001957\",\"MICR_Code\":700002012},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"163 BY 1  DIAMOND HARBOUR RD  DISTT  KOLKATA  WEST BENGAL 700034\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65464,\"Branch_Name\":\"BEHALA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001522\",\"MICR_Code\":700002115},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"7A, SOURIN ROY RD, 1ST FLOOR, BEHALA KOLKITTA 700034\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":100221,\"Branch_Name\":\"BEHALA CHOWRASTHA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0040708\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"64 BY 1 BY 46A  BELGACHIA ROAD  DISTT  KOLKATA  WEST BENGAL 700037\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":69064,\"Branch_Name\":\"BELGACHIA DUTTABAGAN MILK COLONY\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0005367\",\"MICR_Code\":700002013},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"25,BELIAGHATA.MAIN.ROAD,CALCUTTA.PIN.700010\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65738,\"Branch_Name\":\"BELIAGHATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001798\",\"MICR_Code\":700002014},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"64 BY 3 BY 1 DR S C BANERJEE RD  DISTT  KOLKATA  WEST BENGAL 700010\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":72020,\"Branch_Name\":\"BELIAGHATA C.I.T BUILDING AREA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0008440\",\"MICR_Code\":700002172},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"FIRST FLOOR TOBACCO HOUSE, BR ABOURNE RD KOLKOTTA\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":140872,\"Branch_Name\":\"BENTINCK STREET\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0040259\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"388 BAGHAJATIN PLACE, KOLKATA, DISTT.SOUTH 24 PGS. WEST BENGAL-700086\",\"Branch_City\":null,\"Branch_District\":\"SOUTH 24 PARGANAS\",\"Branch_Id\":79291,\"Branch_Name\":\"BHAGHAJATIN STATION ROAD BRANCH\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0016629\",\"MICR_Code\":\"NON-MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"1, ALLENBY ROAD, NORTHEN PARK, KOLKATA 700020\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":132560,\"Branch_Name\":\"BHAWANIPUR, KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0031641\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"6 BY 1, RAMESH MITRA ROAD, DISTT. KOLKATA, WEST BENGAL 700025\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":63988,\"Branch_Name\":\"BHOWANIPORE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0000040\",\"MICR_Code\":700002016},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"15 ASHUTOSH MUKHERJEE MARG BHOWANIPUR KOLKATA\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":103455,\"Branch_Name\":\"BHOWANIPUR,KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0050808\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"49A, SHYAMBAZAR STREET, DISTT. KOLKATA, WEST BENGAL 700004\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":70421,\"Branch_Name\":\"BHUPEN BOSE AVENUE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0006764\",\"MICR_Code\":700002017},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"BIDHAN SARANI, B/156/5/H/8, ACHARJEE PRAFULLA CHANDRA ROAD, DISTT.KOLKATA. WEST BENGAL-700006\",\"Branch_City\":null,\"Branch_District\":\"SOUTH 24 PGS\",\"Branch_Id\":79292,\"Branch_Name\":\"BIDHAN SARANI\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0016630\",\"MICR_Code\":\"NON-MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"SALT LAKE SECTOR 2  DISTT  NORTH 24 PARAGANAS  WEST BENGAL 700091\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":71420,\"Branch_Name\":\"BIKASH BHAVAN G.O.C\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0007816\",\"MICR_Code\":700002114},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"206,M.B.ROAD,BIRATI,KOLKATA51,W.B.,24.PARGANASNORTH,PIN.700051.\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":68405,\"Branch_Name\":\"BIRATI\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0004604\",\"MICR_Code\":700002209},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"89 BY 3 RAIMOHAN BANERJEE RD BONHOOGHLY KOLKATA 24 PARGANAS NWB\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65953,\"Branch_Name\":\"BONHOOGHLY I.E\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0002029\",\"MICR_Code\":700002150},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"BORAL MAIN ROAD, GARIA, KOLKATA, WEST BENGAL 700084\",\"Branch_City\":null,\"Branch_District\":\"SOUTH 24 PARGANAS\",\"Branch_Id\":77493,\"Branch_Name\":\"BORAL\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0014525\",\"MICR_Code\":\"NON-MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"3OSAROADKOLKAA\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":126486,\"Branch_Name\":\"BRABOURNE RD\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0020273\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"9, BRABOURNE ROAD\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":79574,\"Branch_Name\":\"BRABOURNE ROAD BRANCH, KOLKATA \",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0030146\",\"MICR_Code\":700005002},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"89,BRAHMAPUR.BATTALA,GARIA,KOLKATA700096\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":74909,\"Branch_Name\":\"BRAHMAPUR\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0011531\",\"MICR_Code\":700002326},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"19B BROAD STREET, KOLKATA, DISTT.KOLKATA. WEST BENGAL 700019\",\"Branch_City\":null,\"Branch_District\":\"NORTH 24 PARGANAS\",\"Branch_Id\":123110,\"Branch_Name\":\"BROAD STREET\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0016628\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"195, M G ROAD, KOLKATA, WEST BENGAL, PIN 700007\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":63998,\"Branch_Name\":\"BURRA BAZAR\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0000050\",\"MICR_Code\":700002019},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"2UNEDKLKT\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":126553,\"Branch_Name\":\"BURRA BAZAR KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0020360\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"1C/2, CHOWBAGA ROAD, KOLKATA - 700039\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":77487,\"Branch_Name\":\"C N ROY ROAD\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0014517\",\"MICR_Code\":\"NON-MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"150, CHITTARANJAN AVENUE, KOLKATA  WEST BENGAL 700073\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":71036,\"Branch_Name\":\"C R AVENUE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0007406\",\"MICR_Code\":700002025},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"SBI BALLYGUNGE BRANCH PREMISES, 50A, GARIAHAT ROAD, 2ND FLOOR, KOLKATA, WEST BENGAL - 700019\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":73780,\"Branch_Name\":\"CAC, BALLYGUNGE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0010377\",\"MICR_Code\":\"NON-MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"SBI SERAMPORE BRANCH PREMISES, SERAMPORE 1ST FLOOR, HOOGHLY, DIST:HOOGHLY-712201\",\"Branch_City\":null,\"Branch_District\":\"HOOGLY\",\"Branch_Id\":76637,\"Branch_Name\":\"CAC, HOOGHLY\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0013418\",\"MICR_Code\":\"NON-MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"34, JAWAHARLAL NEHRU ROAD, KOLKATA - 700 071\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":73428,\"Branch_Name\":\"CAG, KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0009998\",\"MICR_Code\":700002199},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"COLLEGE STREET,KOLKATA,W.B.,PIN 700073\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":71375,\"Branch_Name\":\"CALCUTTA UNIVERSITY\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0007766\",\"MICR_Code\":700002022},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"16 A,SHAKESPEARE SARANI,KOLKATA,DISTT.KOLKATA.WEST BENGAL 700071\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":133645,\"Branch_Name\":\"CAMAC STREET\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0017362\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"DIST   KOLKATA STATE  WEST BENGAL PIN   700001 \",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65924,\"Branch_Name\":\"CAO, KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001999\",\"MICR_Code\":700002991},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"GILLANDER HOUSE BLOCK D, 1ST AND 2ND FLR., 8 N S ROAD, KOLKATA WEST BENGAL 700 001\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":67979,\"Branch_Name\":\"CB N S ROAD\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0004125\",\"MICR_Code\":700002202},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"PLOT NO X-1 8 BY 1 BLOCK-EPSECTOR-V, SALT LAKE ELECTRONICS COMPLEX ,KOLKATA ,PIN - 700091\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":68117,\"Branch_Name\":\"CB SALT LAKE, KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0004289\",\"MICR_Code\":700002226},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"KOLKATA\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":71592,\"Branch_Name\":\"CENTRAL STATIONERY DEPARTMENT\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0007999\",\"MICR_Code\":700002501},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"83 BY 1-A VIVEKANANDA ROAD\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":68276,\"Branch_Name\":\"CENTRALISED PENSION PROCESSING CENTRE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0004473\",\"MICR_Code\":700002801},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"CF-351, SECTOR-1,SALT LAKE CITY, BIDHANNAGAR, KOLKATA - 700064\",\"Branch_City\":null,\"Branch_District\":\"NORTH 24 PARGANAS\",\"Branch_Id\":75679,\"Branch_Name\":\"CF BLOCK,SALT LAKE \",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0012360\",\"MICR_Code\":\"NON - MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"16A, DESHAPRAN SASHMAL ROAD, KOLKATA-700033\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":77486,\"Branch_Name\":\"CHARU MARKET\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0014516\",\"MICR_Code\":\"NON-MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"19,CHETLA CENTRAL ROAD,KOLKATA,W.B.,PIN 700027\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":67577,\"Branch_Name\":\"CHETLA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0003691\",\"MICR_Code\":700002024},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"ETRA76,METRPLITANPERATIVEHUINGIETYLTD,ANALUTHRAD,HINGRIGHATA,KLKATA700105\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":132733,\"Branch_Name\":\"CHIRANGI GHATA KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0031920\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"38B  JAWARLAL NEHRU ROAD  DISTT  KOLKATA  WEST BENGAL 700071\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65002,\"Branch_Name\":\"CHOWRINGHEE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001054\",\"MICR_Code\":700002026},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"P35  CIT ROAD  DISTT  KOLKATA  WEST BENGAL 700014\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65088,\"Branch_Name\":\"CIT ROAD\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001140\",\"MICR_Code\":700002020},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"KOLKATA,WEST BENGAL\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":73792,\"Branch_Name\":\"CLEARING CENTRALISED PROCESSING CELL, KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0010391\",\"MICR_Code\":700002990},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"223, BIDHAN SARANI, CALCUTTA 700006, BENGAL\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":69414,\"Branch_Name\":\"COLLEGE STREET MATKET, KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0005723\",\"MICR_Code\":700002027},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"66A,COLOOTOLA STREET,KOLKATA,KOLKATA,WEST BENGAL,PIN - 700073\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":71795,\"Branch_Name\":\"COLOOTOLA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0008209\",\"MICR_Code\":700002170},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"50A,GARIAHAT ROAD, 5TH FLOOR, CALCUTTA,\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":67987,\"Branch_Name\":\"COMM BR BALLYGUNGE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0004140\",\"MICR_Code\":700002205},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"2411,ALIPORE.ROAD,KOLKATA,24.PARGANASSOUTH,WEST.BENGAL,PIN.700027.\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":68093,\"Branch_Name\":\"COMM. BRANCH ALIPORE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0004263\",\"MICR_Code\":700002217},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"24, PARK STREET, KOLKATA - 700 016\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":71126,\"Branch_Name\":\"COMMERCIAL BRANCH, KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0007502\",\"MICR_Code\":700002120},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"57, PARK MANSION, PART STREET, KOLAKTA 700016\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":132530,\"Branch_Name\":\"COMMERCIAL BRANCH, PARK STREET, KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0031604\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"JEEVAN DEEP  11TH FLOOR , 1, MIDDLETON ROW, KOLKATA, W.BENGAL, PIN - 700071\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":68116,\"Branch_Name\":\"COMMERCIAL JAWAHARLAL NEHRU RD \",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0004288\",\"MICR_Code\":700002225},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"62 BY 1 BY 1, KASHINATH DUTTA, ROAD, DISTT. KOLKATA, WEST BENGAL 700 036\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65967,\"Branch_Name\":\"COSSIPORE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0002043\",\"MICR_Code\":700002028},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"161, CHITTARANJAN AVENUE, OPP. MAHAJATI SADAN\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":79690,\"Branch_Name\":\"COTTON STREET KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0030266\",\"MICR_Code\":700005003},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"2/1, RUSSEL STREET, KANKARIA CENTRE,KOLKATA - 700071\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":78454,\"Branch_Name\":\"CSPPC\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0015666\",\"MICR_Code\":\"NON-MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"SBI HOWRAH BRANCH PREMISES,9 G.T. ROAD (SOUTH), GROUND FLOOR,HOWRAH, WEST BENGAL-711101\",\"Branch_City\":null,\"Branch_District\":\"HOWRAH\",\"Branch_Id\":76636,\"Branch_Name\":\"CURRENCY ADMINISTRATION CELL, HOWRAH\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0013417\",\"MICR_Code\":\"NON-MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"511,DAKSHINBEHALAROAD,KOLKATA,DISTT.SOUTH24PARGANAS,WESTBENGAL700061\",\"Branch_City\":null,\"Branch_District\":\"24 PARGANAS S\",\"Branch_Id\":135522,\"Branch_Name\":\"DAKSHIN BEHALA ROAD\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0018121\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"57/1 SYAMNAGAR ROAD, KOLKATA - 700 055\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":75695,\"Branch_Name\":\"DAKSHINPARA BAGUIATI\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0012378\",\"MICR_Code\":\"NON MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"2  B B D BUG  DISTT  KOLKATA  WEST BENGAL 700001\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65344,\"Branch_Name\":\"DALHOUSIE SQUARE (EAST)\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001401\",\"MICR_Code\":700002029},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"12  AND  14E GARAIAHAT ROAD SOUTH   DISTT  KOLKATA  WEST BENGAL 700031\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65348,\"Branch_Name\":\"DHAKURIA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001405\",\"MICR_Code\":700002030},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"10,BUDGE BUDGE ROAD,MOLLARGATE,MAHESHTALA,KOLKATA,DISTT.KOLKATA.WEST BENGAL 700141\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":123363,\"Branch_Name\":\"DOCUMENT ARCHIVAL CENTRE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0017192\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"5 BY 1,JESSORE ROAD,KOLKATTA DT24 PARGANAS N,W. BENGAL 700028\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65978,\"Branch_Name\":\"DUM DUM\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0002054\",\"MICR_Code\":700002122},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"181, DUM DUM ROAD, KOLKATA - 700074\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":75691,\"Branch_Name\":\"DUM DUM ROAD\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0012373\",\"MICR_Code\":\"NON-MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"13,ASOKGARH,24.PGS.NORTH,KOLKATA700108\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":74918,\"Branch_Name\":\"DUNLOP BRIDGE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0011540\",\"MICR_Code\":700002329},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"CPO29POLKLATA\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":134427,\"Branch_Name\":\"EAST JADAVPUR BR KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0021403\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"101, RAJDANGA, DISHA APPARTMENT, NAWA PALLY\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":79853,\"Branch_Name\":\"EKTP(NABAPALLY), KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0030445\",\"MICR_Code\":700005006},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"91B  CHOWRINGHEE ROAD  DISTT  KOLKATA  WEST BENGAL 700020\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65708,\"Branch_Name\":\"ELGIN ROAD\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001768\",\"MICR_Code\":700002031},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"54,RAFI AHMED KIDWAI ROAD,CALCUTTA,WEST BENGAL 700016\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65732,\"Branch_Name\":\"ELLIOT ROAD, KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001792\",\"MICR_Code\":700002032},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"34 GIRISH CH. BOSE ROAD, DISTT. KOLKATA, WEST BENGAL 700 014\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65770,\"Branch_Name\":\"ENTALLY\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001830\",\"MICR_Code\":700002033},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"9B, SIDHU KANHU DAHAR, DISTT. KOLKATA,WEST BENGAL,  700069\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65898,\"Branch_Name\":\"ESPLANADE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001971\",\"MICR_Code\":700002034},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"40,EZRA STREET,KOLKATA,WEST BENGAL,PIN - 700001\",\"Branch_City\":null,\"Branch_District\":\"GREATER BOMBAY\",\"Branch_Id\":72169,\"Branch_Name\":\"EZRA STREET, KOLKATA\",\"Branch_State\":\"MAHARASHTRA\",\"IFSC_Code\":\"SBIN0008600\",\"MICR_Code\":700002176},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"KOLKATA WB\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65059,\"Branch_Name\":\"FD KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001111\",\"MICR_Code\":700002998},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"ST. GEROGES GATE, DISTT. KOLKATA WEST BENGAL 700021\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65539,\"Branch_Name\":\"FORT WILLIAM\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001597\",\"MICR_Code\":700002035},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"SAMRIDDHI,BHAVAN,1,STRAND,ROAD,KOLKATA\",\"Branch_City\":null,\"Branch_District\":\"GORAKPUR \",\"Branch_Id\":72456,\"Branch_Name\":\"FUND SETTLEMENT LINK OFFICE, KOLKATA\",\"Branch_State\":\"UTTAR PRADESH\",\"IFSC_Code\":\"SBIN0008913\",\"MICR_Code\":700002999},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"51 BY B, GARCHA ROAD, DISTT. KOLKATA, WEST BENGAL 700019\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":67578,\"Branch_Name\":\"GARCHA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0003692\",\"MICR_Code\":700002036},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"SOUTH EASTERN RLY HQ, GARDEN REACH, KOLKATA, 24 PARGANAS S WB, 700043\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65345,\"Branch_Name\":\"GARDEN REACH\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001402\",\"MICR_Code\":700002126},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"3 GARFA MAIN ROAD KOLKATTA DT 24 PARGANAS S, W. BENGAL 700078\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65392,\"Branch_Name\":\"GARFA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001450\",\"MICR_Code\":700002125},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"283 A NSC BOSE ROAD  DISTT  KOLKATA WEST BENGAL 700047\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":69065,\"Branch_Name\":\"GARIA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0005368\",\"MICR_Code\":700002037},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"510 JOSHPUR PARK, DISTT. KOLKATA WEST BENGAL 700 068\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":71430,\"Branch_Name\":\"GARIAHAT CIVIC CENTRE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0007826\",\"MICR_Code\":700002038},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"33, SAHITYA PARISHAD ST, PLOT NO. 6, KOLKATA, WB\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":68572,\"Branch_Name\":\"GOABAGAN\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0004773\",\"MICR_Code\":700002040},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"DIST NORTH 24 PARGANAS,WEST BENGAL 743252\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":72325,\"Branch_Name\":\"GOBARDANGA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0008766\",\"MICR_Code\":700002559},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"INSTITUTION OF ENGINEERING BLDG,8,GOKHALE RD,KOLKATA\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65986,\"Branch_Name\":\"GOKHALE ROAD\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0002062\",\"MICR_Code\":700002039},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"GOLF GREEN,PHASE II,APARTMENT OWNERS ASSOCIATION,ADMIN BUILDING,DISTT.KOLKATA.WEST BENGAL 700095\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":123113,\"Branch_Name\":\"GOLF GREEN\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0016635\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"GOLF GREEN KOLKOTTA,WEST BENGAL,DT 12 A, UDAYASHANKAR SARANI KOLKATTA 95\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":100178,\"Branch_Name\":\"GOLF GREEN KOLKOTTA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0040643\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"133 SOUTHERN AVENUE, KOLKATA,  WB 700029\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":70841,\"Branch_Name\":\"GOLPARK\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0007195\",\"MICR_Code\":700002041},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"DIST  KOLKATA, WEST BENGAL 700 014\",\"Branch_City\":null,\"Branch_District\":\"MADHEPURA \",\"Branch_Id\":72151,\"Branch_Name\":\"GORACHAND ROAD, KOLKATA\",\"Branch_State\":\"BIHAR\",\"IFSC_Code\":\"SBIN0008581\",\"MICR_Code\":700002175},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"196AG,ARABINDA SARANI,KOLKATA,WEST BENGAL,PIN 700004\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65690,\"Branch_Name\":\"GREY STREET EXTN.BRANCH \",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001750\",\"MICR_Code\":700002042},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"P.O.HALDIA OIL REFINERY GATE1,MIDNAPORE,DI. PASCHIM MEDINIPUR,WEST BENGAL\",\"Branch_City\":null,\"Branch_District\":\"EAST MEDINIPUR \",\"Branch_Id\":72896,\"Branch_Name\":\"HALDIA P. C\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0009390\",\"MICR_Code\":721002004},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"DIST KOLKATASTATE WEST BENGAL\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":74908,\"Branch_Name\":\"HARIDEVPUR\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0011530\",\"MICR_Code\":700002349},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"102B, HARISH MUKHERJEE ROAD, KOLKATA, WEST BENGAL 700025\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":67844,\"Branch_Name\":\"HARISH MUKHERJEE ROAD\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0003961\",\"MICR_Code\":700002043},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"URMIMALA HOUSE, JYANGRA KALITALA, HATIARA ROAD, BAGUIATI, KOLKATA - 700 059\",\"Branch_City\":null,\"Branch_District\":\"NORTH 24 PARGANAS\",\"Branch_Id\":75693,\"Branch_Name\":\"HATIARA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0012376\",\"MICR_Code\":\"NON-MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"77 BY 2A, HAZRA RD,KOLKATA, WB\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65590,\"Branch_Name\":\"HAZRA ROAD\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001649\",\"MICR_Code\":700002045},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"2ND AND 3RD FLOOR,  C  D BLOCK, SAMRIDDHI BHAVAN, KOLKATA - 700001\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":77491,\"Branch_Name\":\"HIGH COURT BRANCH\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0014523\",\"MICR_Code\":\"NON-MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"1895 MUKUNDAPUR, HILAND PARK, DIST: KOLKATA, WEST BENGAL-700 075\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":77488,\"Branch_Name\":\"HIGH LAND PARK, KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0014520\",\"MICR_Code\":\"NON-MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"HILI, DAKSHIN DINAJPUR DISTRICT, WEST BENGAL - 733126\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":75728,\"Branch_Name\":\"HILI\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0012415\",\"MICR_Code\":\"NON-MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"6, SAMBHU NATH PANDIT STREET, KOLKATA, DISTT.KOLKATA. WEST BENGAL 700025\",\"Branch_City\":null,\"Branch_District\":\"NORTH 24 PARGANAS\",\"Branch_Id\":138311,\"Branch_Name\":\"HNI ELGIN ROAD\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0016624\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"2PANCHANANTALAROADHOWRAH\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":127727,\"Branch_Name\":\"HOWRA BRANCH KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0020811\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"CONSTANTIA BLDG., 11,U.N.BRAHMACHARI STREET, KOLKATA -700 017\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65865,\"Branch_Name\":\"IFB, KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001936\",\"MICR_Code\":700002194},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"315, INDIA EXCHANGE PLACE, DISTT. KOLKATA, WEST BENGAL 700012\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65087,\"Branch_Name\":\"INDIA EXCH. PLACE EXTN.\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001139\",\"MICR_Code\":700002047},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"P19TARATALAROAD,DISTT.KOLKATA,WESTBENGAL700088.\",\"Branch_City\":null,\"Branch_District\":\"24 PARGANAS N\",\"Branch_Id\":138286,\"Branch_Name\":\"INDIAN MARITIME UNIVERSITY\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0018118\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"76, A.J.C.BOSE ROAD, KOLKATA WEST BENGAL 700014\",\"Branch_City\":null,\"Branch_District\":\"PUNE\",\"Branch_Id\":72324,\"Branch_Name\":\"IT BLDG AREA BENIAPUKUR\",\"Branch_State\":\"MAHARASHTRA\",\"IFSC_Code\":\"SBIN0008762\",\"MICR_Code\":700002178},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"57/4 BARAKHOLA, KALIKAPUR, P.S.- SURVEY PARK, DISTT.24 PARGANAS (SOUTH). WEST BENGAL-700099\",\"Branch_City\":null,\"Branch_District\":\"DISTT 24 PARGANAS SOUTH\",\"Branch_Id\":79296,\"Branch_Name\":\"JADAVPUR STADIUM\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0016637\",\"MICR_Code\":\"NON-MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"RAJA SUBODH MULLICK ROAD  DISTT  KOLKATA  WEST BENGAL 700032\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":64041,\"Branch_Name\":\"JADAVPUR UNIVERSITY\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0000093\",\"MICR_Code\":700002048},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"46 CHAKRABERIA ROAD, DISTT. KOLKATA, WEST BENGAL 700020\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":69426,\"Branch_Name\":\"JADUBABU'S BAZAR\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0005736\",\"MICR_Code\":700002049},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"11A,JATINDRA MOHAN AVENUE,KOLKATA WEST BENGAL 700006\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65921,\"Branch_Name\":\"JATINDRA MOHAN AVENUE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001994\",\"MICR_Code\":700002051},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"1, MIDDLETON STREET, DISTT. KOLKATA, WEST BENGAL 700071\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":67648,\"Branch_Name\":\"JEEVANDEEP\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0003762\",\"MICR_Code\":700002052},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"394A GARIAHAT ROADSOUTH,KOLKATA700068\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":74917,\"Branch_Name\":\"JODHPUR PARK\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0011539\",\"MICR_Code\":700002334},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"44A.CIT.ROAD,CALCUTTA.700010,W.B.\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":67753,\"Branch_Name\":\"JORAMANDIR\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0003868\",\"MICR_Code\":700002053},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"1 BY 1A NANDA MULLICK LANE  DISTT  KOLKATA  WEST BENGAL 700006\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65152,\"Branch_Name\":\"JORASANKO\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001204\",\"MICR_Code\":700002050},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"VILL TAMACHANDRAPUR P.O. ,R C THAKURANI, DT24 PARGANAS, W. BENGAL , PIN - 700104\",\"Branch_City\":null,\"Branch_District\":\"SOUTH 24 PARGANAS\",\"Branch_Id\":72871,\"Branch_Name\":\"KABARDANGA MORE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0009358\",\"MICR_Code\":700002190},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"79 BY C, SHYAMA PRASAD MUKHEE ROAD, KOLKATA 700026\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65663,\"Branch_Name\":\"KALIGHAT\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001722\",\"MICR_Code\":700002054},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"78,PURBACHAL,KALITALA ROAD,KOLKATA\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":67790,\"Branch_Name\":\"KALIKAPUR\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0003907\",\"MICR_Code\":700002317},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"KAMALGAZIMORE, 1613KAMALGAZI, KOLKATA, DISTT.KOLKATA.WESTBENGAL700103\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":98959,\"Branch_Name\":\"KAMALGAZI MORE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0018125\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"1,B.T. ROAD,24 PARGANAS N.,W.B. 700058\",\"Branch_City\":null,\"Branch_District\":\"NORTH 24 PARGANAS\",\"Branch_Id\":65347,\"Branch_Name\":\"KAMARHATI\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001404\",\"MICR_Code\":700002132},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"PLOT NO.79, KALINDI HOUSING ESTATE, KOLKTA, WEST BENGAL ,PIN - 700089\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":71555,\"Branch_Name\":\"KANLINDI HOUSING ESTATE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0007961\",\"MICR_Code\":700002159},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"250,B.B.CHATTERJEE ROAD,CALCUTTA PIN700042\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65428,\"Branch_Name\":\"KASBA KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001486\",\"MICR_Code\":700002055},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"GROUND FLOOR,40 B,KEORAPUKUR,M G ROAD,KOLKATA,DISTT.KOLKATA.WEST BENGAL 700082\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":124220,\"Branch_Name\":\"KEORAPUKUR\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0017363\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"DEEP BHAWAN, KRISHNAPUR MAJHERPARA, PO-KRISHNAPUR, KOLKATA-700102, WEST BENGAL\",\"Branch_City\":null,\"Branch_District\":\"NORTH 24 PARGANAS\",\"Branch_Id\":77500,\"Branch_Name\":\"KESTOPUR\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0014534\",\"MICR_Code\":\"NON-MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"128 BY 15  HAZRA ROAD  DISTT  KOLKATA  WEST BENGAL 700026\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":68571,\"Branch_Name\":\"KHIRODE GHOSH MARKET\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0004772\",\"MICR_Code\":700002056},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"77 D H ROAD, DISTT. KOLKATA, WEST BENGAL 700023\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":67230,\"Branch_Name\":\"KIDDERPORE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0003334\",\"MICR_Code\":700002057},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"DIST  KOLKATA, WEST BENGAL 700 043\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":71515,\"Branch_Name\":\"KIDDERPORE DOCKYARD AREA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0007920\",\"MICR_Code\":700002160},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"16DRSUDHIRBOSEROAD,PBNO10621,KIDDERPOREPO700023KIDDERPOREATSBTCOIN\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":134210,\"Branch_Name\":\"KIDDERPORE KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0070575\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"DIST   NORTH 24 PARGANAS  WEST BENGAL 700052\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":66931,\"Branch_Name\":\"KOLKATA AIR PORT\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0003029\",\"MICR_Code\":700002119},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"333,AJAY NAGAR,KOLKATA 700075\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":132722,\"Branch_Name\":\"KOLKATA AJAY NAGAR\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0031895\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"71,JODHPUR PARK,KOLKATA 700068\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":132721,\"Branch_Name\":\"KOLKATA JODHPUR PARK\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0031894\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"176,SARATBOSEROAD,NEARDESHAPRIYAPARK,SARATBOSEROAD,LAKEMARKET700026LAKEMARKETATSBTCOIN\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":106312,\"Branch_Name\":\"KOLKATA LAKE MARKET\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0070500\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"SAMRIDDHI BHAWAN, 1 STRAND ROAD, KOLKATA 700 001\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":63949,\"Branch_Name\":\"KOLKATA MAIN\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0000001\",\"MICR_Code\":700002021},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"APEEJAY HOUSE,15,PARK STREET,KOLKATA 700 016\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":99392,\"Branch_Name\":\"KOLKATA PARK STREET BRANCH\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0032608\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"132 BY 1,MAHATMA GANDHI ROAD KAVERI HOUSE BURTALLA STREET,CALCUTTA 700007\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":80211,\"Branch_Name\":\"KOLKATA, BURTALLA ST                    \",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0060302\",\"MICR_Code\":700002345},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"9,BRABOURNE ROAD,PB NO 2305 CALCUTTA 700001 WEST BENGAL\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":80060,\"Branch_Name\":\"KOLKATA, MAIN                           \",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0060145\",\"MICR_Code\":700002344},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"CAMAC STREETSHANTI NIKETANKOLKATA700017\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":136170,\"Branch_Name\":\"KOLKATA- CAMAC STREET\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0050271\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"276RAVINDRA SARNI NEAR GANESH TALKIESBURRA BAZARKOLKATA 700007\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":101990,\"Branch_Name\":\"KOLKATA-BURRA BAZAR\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0050474\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"PBNO9036,36,CHOWRINGHEEROAD,MIDDLETOWNROWPO700071CALCUTTAATSBTCOIN\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":105344,\"Branch_Name\":\"KOLKATAMAIN\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0070248\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"11 LOUDON STREET, DISTT. KOLKATA, WEST BENGAL 700017\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":67567,\"Branch_Name\":\"LA MARTINIERE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0003681\",\"MICR_Code\":700002058},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"C I T ROAD,KOLKATA,DISTT.KOLKATA.WEST BENGAL 700014\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":123112,\"Branch_Name\":\"LADIES PARK\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0016632\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"161BY1BY2, LAKE GARDENS, KOLKATA, WEST BENGAL, PIN 700045\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65742,\"Branch_Name\":\"LAKE GARDENS\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001802\",\"MICR_Code\":700002059},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"P BY 871, BLOCK-A, LAKE TOWN,  DISTT. NORTH 24 PARGANAS, WEST BENGAL 700 089\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65448,\"Branch_Name\":\"LAKE TOWN\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001506\",\"MICR_Code\":700002133},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"R1UADGO1014JRARADAJRBUDG1TFRJRBAGURRG\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":129940,\"Branch_Name\":\"LAKETOWN\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0021997\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"3SARATBOSEROADCALCUTTATELEX21816\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":98937,\"Branch_Name\":\"LANSDOWNE KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0020272\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"DIST  KOLKATASTATE WEST BENGAL\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":68948,\"Branch_Name\":\"LIABILITY CPC KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0005237\",\"MICR_Code\":700002249},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"230 BY 1 A J C  BOSE ROAD  DIST  KOLKATA  WEST BENGAL 700020\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":70974,\"Branch_Name\":\"LOWER CIRCULAR ROAD\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0007335\",\"MICR_Code\":700002060},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"210MGROAD,KOLKATA,DIST.SOUTH24PARGANAS,WESTBENGAL700063\",\"Branch_City\":null,\"Branch_District\":\"24 PARGANAS S\",\"Branch_Id\":125345,\"Branch_Name\":\"M G ROAD\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0018120\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"35 AMHERST STREET, KOLKATA, WB 700009\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":69132,\"Branch_Name\":\"M G ROAD (DAFTARIPARA)\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0005437\",\"MICR_Code\":700002061},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"WELKIN.HEIGHTS,1648.GARIA.MAIN.ROAD,GARIA,KOLKATA\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":74746,\"Branch_Name\":\"MAHAMAYATALA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0011363\",\"MICR_Code\":700002323},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"294.2 BY 1, A.P.C. ROAD, DISTT. KOLKATA, WEST BENGAL 700009\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65656,\"Branch_Name\":\"MANICKTALA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001715\",\"MICR_Code\":700002062},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"CIT SCHEME VII M VIP ROAD, DISTT. KOLKATA, WEST BENGAL 700054\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":71092,\"Branch_Name\":\"MANIKTALA CIVIC CENTRE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0007468\",\"MICR_Code\":700002063},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"MEGA DOCUMENT ARCIVAL CENTRE KOLKATA,FPN ENG PVT.LTD,48 TARTALA RD,KOLKATA,WEST BENGAL-700078\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":125752,\"Branch_Name\":\"MEGA DAC KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0018722\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"DIST  KOLKATA, WEST BENGAL 700024\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":67765,\"Branch_Name\":\"METIABURUZ\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0003881\",\"MICR_Code\":700002135},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"JEEVAN SUDHA, 42C J.L.NEHRU ROAD ,DIST KOLKATA STATE WEST BENGAL PIN 700071 \",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":68099,\"Branch_Name\":\"MICR CHEQUE PROCESSING CENTRE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0004269\",\"MICR_Code\":700002000},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"KOLKATA,BENGAL\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":73879,\"Branch_Name\":\"MID CORPORATE LOAN ADMINISTRATION UNIT\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0010481\",\"MICR_Code\":700002807},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"DIST   KOLKATA  WEST BENGAL 700071\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65070,\"Branch_Name\":\"MIDDLETON ROW\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001122\",\"MICR_Code\":700002064},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"10 BY 1H DH ROAD  DISTT  KOLKATA  WEST BENGAL 700027\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65923,\"Branch_Name\":\"MOMINPUR\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001996\",\"MICR_Code\":700002065},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"STATE BANK OF INDIAMONEY SHOPNIZAM PALACEA CBOSE ROADKOLKATAWEST BENGALPIN 700020\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":103906,\"Branch_Name\":\"MONEY SHOP\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0051487\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"PLOT 513 BY A,JESSORE ROAD,3 MOTILAL COLONY,KOLKATA 700081\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":75694,\"Branch_Name\":\"MOTILAL COLONY\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0012377\",\"MICR_Code\":700002555},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"2,DR.SURESH SARKAR RD,KOLKATA\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":71796,\"Branch_Name\":\"MOULALI\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0008210\",\"MICR_Code\":700002174},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"KAVERI NAGAR, DISTT. KARUR, TAMIL NADU 639104\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":69828,\"Branch_Name\":\"MUCHIPARA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0006145\",\"MICR_Code\":700002066},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"1413 MUKUNDAPUR,KOLKATA\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":74907,\"Branch_Name\":\"MUKUNDAPUR\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0011529\",\"MICR_Code\":700002362},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"14, N S ROAD, KOLKATA 700001\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":131247,\"Branch_Name\":\"N S ROAD, KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0031004\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"DA - 6 , DA BLOCK, SALT LAKE, KOLKATA - 700064\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":75719,\"Branch_Name\":\"N.R.I. SALT LAKE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0012402\",\"MICR_Code\":\"NON - MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"21A,DUM.DUM.ROAD,CALCUTTA.PIN.700074\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":68406,\"Branch_Name\":\"NAGERBAZAR\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0004605\",\"MICR_Code\":700002137},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"370 BY 1 BY 7,NETAJI SUBHAS CHANDRA BOSE ROAD, CALCUTTA\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65452,\"Branch_Name\":\"NAKTALA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001510\",\"MICR_Code\":700002068},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"RAMKRISHNA MISSION ASHRAM COMPLEX,P.O. NARENDRAPUR,KOLKATA 700103\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":75662,\"Branch_Name\":\"NARENDRAPUR\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0012341\",\"MICR_Code\":\"NON MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"P 274 C I T SCHEME IV M    C I T ROAD  DT  KOLKATA  WEST BENGAL 700011\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65522,\"Branch_Name\":\"NARKELDANGA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001580\",\"MICR_Code\":700002069},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"NAYABAD ROAD, KOLKATA, DIST.KOLKATA, WEST BENGAL- 700 099\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":125346,\"Branch_Name\":\"NAYABAD\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0018123\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"NETAJI NAGAR, N S C BOSE ROAD, KOLKATA, DIST.KOLKATA, WEST BENGAL- 700 040\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":139231,\"Branch_Name\":\"NETAJI NAGAR\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0018126\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"NETAJI SUBHAS ROAD, KOLKATA 700 001\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":64092,\"Branch_Name\":\"NETAJI SUBHASH ROAD\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0000144\",\"MICR_Code\":700002070},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"23 A BY 76 DH ROAD BLOCK E NEW ALIPORE  DISTT  KOLKATA  WEST BENGAL 700053\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":64153,\"Branch_Name\":\"NEW ALIPORE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0000205\",\"MICR_Code\":700002071},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"HALTU MAIN ROAD, PO.KASBA CALCUTTA WEST BENGAL ,PIN - 700078\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":71051,\"Branch_Name\":\"NEW BALLYGUNGE,\"KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0007423\",\"MICR_Code\":700002136},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"28 BY 9 NEW BALLYGUNGE ROAD, DISTT. KOLKATA WEST BENGAL 700 039\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":71861,\"Branch_Name\":\"NEW BALLYGUNGE, KASBA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0008277\",\"MICR_Code\":700002161},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"13,S.N.BANERJEE ROAD,KOLKATA,W.B.,PIN 700013\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":68463,\"Branch_Name\":\"NEW MARKET\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0004662\",\"MICR_Code\":700002072},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"466.BY.1.M.B.ROAD,CULTURE.MORE,KOLKATA700049\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":74919,\"Branch_Name\":\"NIMTA BELGHORIA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0011541\",\"MICR_Code\":700002330},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"24B NIMTALA GHAT STREET KOLKATA WEST BENGAL\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65594,\"Branch_Name\":\"NIMTALLAH\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001653\",\"MICR_Code\":700002073},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"100/2SUBHAM PLAZA  B.T. ROAD BONHOOGHLY\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":79875,\"Branch_Name\":\"NIOH CAMPUS KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0030468\",\"MICR_Code\":700005007},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"18B,DUM DUM ROAD,KOLKATA WEST BENGAL 700030\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65834,\"Branch_Name\":\"NORTHERN AVENUE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001895\",\"MICR_Code\":700002074},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"JEEVAN SUDHA BUILDING,42 BY C,CHOWRINGEE RD,CALCUTTA\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":69962,\"Branch_Name\":\"NRI KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0006284\",\"MICR_Code\":700002192},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"SAMRIDDHI,BHAVAN1,STRAND,ROADKOLKATA\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":67853,\"Branch_Name\":\"OAD, LHO, KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0003970\",\"MICR_Code\":700002899},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"PO AGARPARA,24 PARGANAS NORTH,WEST BENGAL 700109\",\"Branch_City\":null,\"Branch_District\":\"NORTH 24 PARAGANAS\",\"Branch_Id\":75683,\"Branch_Name\":\"OSMANPUR (BATTALA)\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0012365\",\"MICR_Code\":700002359},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"SAMRIDDHI BHAWAN, 1, STRAND ROAD, KOLKATA - 700 001\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":68604,\"Branch_Name\":\"OVERSEAS BRANCH, KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0004805\",\"MICR_Code\":700002075},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"EHIK13TBOEOCLCUTT\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":127722,\"Branch_Name\":\"P AND S B BRANCH KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0020806\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"1/504 GARIAHAT ROAD, KOLKATA, DISTT.KOLKATA. WEST BENGAL 700068\",\"Branch_City\":null,\"Branch_District\":\"NORTH 24 PARGANAS\",\"Branch_Id\":123109,\"Branch_Name\":\"P.B.B. JODHPUR PARK\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0016626\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"6D,NORTHERN.AVENUE,KOLKATA700037\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65687,\"Branch_Name\":\"PAIKAPARA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001747\",\"MICR_Code\":700002076},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"47,GARFA MAIN ROAD,KOLKATA,WEST BENGAL,PIN - 700075\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":71664,\"Branch_Name\":\"PALBAZAR\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0008077\",\"MICR_Code\":700002162},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"196B DIAMOND HARBOUR, OPP; BEHALA FIRE STATION, DIST.;SOUTH 24 PGS, WEST BENGAL-700008\",\"Branch_City\":null,\"Branch_District\":\"SOUTH 24 PGS\",\"Branch_Id\":79293,\"Branch_Name\":\"PANCHANANTALA(BEHALA)\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0016633\",\"MICR_Code\":\"NON-MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"9 SYED AMIR ALI AVENUE KOLKATA WEST BENGAL\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65689,\"Branch_Name\":\"PARK CIRCUS\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001749\",\"MICR_Code\":700002077},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"12B-PARK STREET, KOLKATA 700071\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":64098,\"Branch_Name\":\"PARK STREET\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0000150\",\"MICR_Code\":700002078},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"ANSL3AKSEECALCUA\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":139120,\"Branch_Name\":\"PARKSTREET KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0020833\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"394 PARNASHREE PALLY KOLKATA  24 PARGANAS SOUTH W B 700060\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65940,\"Branch_Name\":\"PARNASHREE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0002016\",\"MICR_Code\":700002138},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"261 BY 11,PRINCE ANWAR SHAH ROAD,KOLKATTA ,WEST BENGAL ,PIN - 700033\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":69068,\"Branch_Name\":\"PAS ROAD\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0005371\",\"MICR_Code\":700002082},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"FLOOR NO. 1, UPOHAR TOWN CENTER, 2052 UPTC 0117, CHAK GARIA KOLKATA,WEST BENGAL 700107\",\"Branch_City\":null,\"Branch_District\":\"SOUTH 24 PARGANAS\",\"Branch_Id\":79295,\"Branch_Name\":\"PATULI BRANCH\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0016636\",\"MICR_Code\":\"NON MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"148, RASH BEHARI AVENUE KOLKATA, WB 700029\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":68065,\"Branch_Name\":\"PBB DESHPRIYA PARK\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0004233\",\"MICR_Code\":700002211},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"C.I.T.BUILDING,1 BY 16,V.I.P.ROAD KANKURGACHI, WB 700054\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":68037,\"Branch_Name\":\"PBB KANKURGACHI\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0004203\",\"MICR_Code\":700002208},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"CAMAC STREET,KOLKATA,DISTT.KOLKATA.WEST BENGAL 700071\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":123108,\"Branch_Name\":\"PBB PARK STREET\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0016625\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"HA-291,SECTOR-III, SALT LAKE,CALCUTTA,DT 24 PARGANAS  N , W. B 700091\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":68038,\"Branch_Name\":\"PBB SALT LAKE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0004204\",\"MICR_Code\":700002216},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"232 BY  3 PICNIC GARDEN ROAD  CALCUTTA \",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":69296,\"Branch_Name\":\"PICNIC GARDEN\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0005602\",\"MICR_Code\":700002079},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"35C,CHRISTOPHER ROAD,CALCUTTA,KOLKATTA,WEST BENGAL,PIN - 700046\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":72019,\"Branch_Name\":\"POTTERY ROAD\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0008439\",\"MICR_Code\":700002168},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"AB30.BY.1.PRAFULLA.KANAN.WEST,KOLKATA700101\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":74753,\"Branch_Name\":\"PRAFULLA KANAN\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0011370\",\"MICR_Code\":700002340},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"32, GANGAPURI, PURBA PUTIARI CALCUTTA , WEST BENGAL ,PIN - 700093\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":70200,\"Branch_Name\":\"PURBA PUTIARY\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0006528\",\"MICR_Code\":700002200},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"27.MANMATHA.NATH.GANGULY.ROAD,KOLKATA700002\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":74915,\"Branch_Name\":\"R G KAR MEDICAL COLLEGE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0011537\",\"MICR_Code\":700002324},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"RABINDRA BHARATI UNIVERSITY CAMPUS , 56A B T ROAD , KOLKATA ,700050\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":73815,\"Branch_Name\":\"RABINDRA BHARATI UNIVERSITY CAMPUS\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0010417\",\"MICR_Code\":700002313},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"24B RABINDRA SARANI, DISTT. KOLKATA, WEST BENGAL 700007\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":67129,\"Branch_Name\":\"RABINDRA SARANI\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0003231\",\"MICR_Code\":700002084},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"3, MAHENDRA BANERJEE ROAD,  RABINDRANAGAR BEHALA, KOLKATA  700060\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":75668,\"Branch_Name\":\"RABINDRANAGAR\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0012348\",\"MICR_Code\":\"NON-MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"DIST  KOLKATA\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":68293,\"Branch_Name\":\"RACPC, KOKLATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0004490\",\"MICR_Code\":700002995},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"106C, RAJA DINENDRA ST, DISTT. KOLKATA WEST BENGAL  700004\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65922,\"Branch_Name\":\"RAJA D STREET\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001995\",\"MICR_Code\":700002085},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"59/B, RAJA RAM MOHAN ROY ROAD, KOLKATA- 700008\",\"Branch_City\":null,\"Branch_District\":\"NORTH 24 PARAGANAS\",\"Branch_Id\":77490,\"Branch_Name\":\"RAJA RAM MOHAN ROY ROAD\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0014522\",\"MICR_Code\":\"NON-MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"44, GARDEN REACH ROADKOLKATA\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":67042,\"Branch_Name\":\"RAJABAGAN DOCKYARD\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0003142\",\"MICR_Code\":700002140},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"G 4 AND 6 GROUND FLOOR,VIP RD KOYLA VIHAR ABHINANDAN PO AIRPORT KILKOTTA\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":100223,\"Branch_Name\":\"RAJARHAT\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0040711\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"RLRNATURALNESTRAJARHATMEACITYKLKTTABESIDECHARNCKHSPITALKLKATA\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":134425,\"Branch_Name\":\"RAJARHAT\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0021347\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"SWAGATCHINARCOMPLEX,BLOCKA,GROUNDFLOOR,RAJARHATEXPRESSWAY,NEARCHINARPARKCROSSING,KOLKAT\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":106557,\"Branch_Name\":\"RAJARHAT\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0070816\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"PIN743358\",\"Branch_City\":null,\"Branch_District\":\"SOUTH 24 PARGANAS\",\"Branch_Id\":65393,\"Branch_Name\":\"RAJPUR KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001451\",\"MICR_Code\":700002164},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"47 BY A RASH BEHARI AVENUE,KOLKATA, BENGAL, PIN 700026\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65589,\"Branch_Name\":\"RASH BEHAR AVENUE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001648\",\"MICR_Code\":700002086},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"RASH BEHARI AVENUE KOLKATA\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":99665,\"Branch_Name\":\"RASHBEHARI AVENUE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0040229\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"KOLKATA,WEST BENGAL\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":73676,\"Branch_Name\":\"RASMECCC, TITAGARH\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0010265\",\"MICR_Code\":700002395},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"JEEVANDEEP BUILDING, 1 MIDDLETON STREET, 10TH FLOOR, KOLKATA-700071\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":77751,\"Branch_Name\":\"RBO 1, KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0014862\",\"MICR_Code\":\"NON-MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"ZILLA PARISHAD BHAWAN, SBI BARUIPUR, KOLKATA - 700144\",\"Branch_City\":null,\"Branch_District\":\"SOUTH 24 PARAGANAS\",\"Branch_Id\":77071,\"Branch_Name\":\"RCPC, BARUIPUR\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0013986\",\"MICR_Code\":\"NON-MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"DIST   KOLKATA  WEST BENGAL 700032\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":68573,\"Branch_Name\":\"REGENT ESTATE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0004774\",\"MICR_Code\":700002087},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"RIFLE CLUB ROAD BANSDRONI,105 BY 1 NSC BOSE ROAD,KOLKATA,DISTKOLKATA700070\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":74916,\"Branch_Name\":\"RIFLE CLUB ROAD (BANSDRONI)\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0011538\",\"MICR_Code\":700002351},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"5 BY 6  RIFLE ROAD  DISTT  KOLKATA WEST BENGAL  700019\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":71798,\"Branch_Name\":\"RIFLE ROAD\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0008212\",\"MICR_Code\":700002165},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"12 A SOURIN ROY ROAD,BEHALA,KOLKATA,DISTT.KOLKATA.WEST BENGAL 700034\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":138312,\"Branch_Name\":\"ROY BAHADUR ROAD BEHALA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0016768\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"P 27,PHASE I KASBA IND ESTATE EM BYPASS EAST KOLKOTTA\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":135270,\"Branch_Name\":\"RUBY PARK\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0040640\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"87 BY 49A BOSEPUKUR ROAD, DISTT. KOLKATA , WEST BENGAL 700042 700042\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":72018,\"Branch_Name\":\"RUBY PARK\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0008438\",\"MICR_Code\":700002169},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"124 BY 1 B B GANGULY STREET  DISTT  KOLKATA  WEST BENGAL 700012\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":69458,\"Branch_Name\":\"S B DEY STREET\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0005768\",\"MICR_Code\":700002089},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"PURTA BHAVAN,GROUND FLOOR,P.O.SRIPALLY,BURDWAN,WEST BENGAL\",\"Branch_City\":null,\"Branch_District\":\"BARDHAMAN \",\"Branch_Id\":71600,\"Branch_Name\":\"SADARGHAT, SRIPALLY\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0008007\",\"MICR_Code\":713002105},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"JHEEL ROAD, KOLKATA, DIST.KOLKATA. WEST BENGAL-700 031\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":134566,\"Branch_Name\":\"SAHIDNAGAR\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0018116\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"15, DIAMOND HARBOUR RD, KOLKATA, 24 PARGANAS SOUTH ,  WEST BENGAL, PIN  700008\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":66041,\"Branch_Name\":\"SAKHERBAZAR\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0002117\",\"MICR_Code\":700002144},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"14  BELIAGHATA  DISTT  KOLKATA  WEST BENGAL 700015\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":70428,\"Branch_Name\":\"SALES TAX BLDG\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0006771\",\"MICR_Code\":700002088},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"DB-2, SECTOR-I, SALT LAKE CITY, DIST NORTH 24 PARGANAS, WEST BENGAL 700064\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65554,\"Branch_Name\":\"SALT LAKE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001612\",\"MICR_Code\":700002145},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"AC 19, SALT LAKE, SECTOR-1, BIDHANAGAR, KOLKATA, DISTT. 24 PARGANAS(NORTH). WEST BENGAL-700064\",\"Branch_City\":null,\"Branch_District\":\"SOUTH 24 PARGANAS\",\"Branch_Id\":79303,\"Branch_Name\":\"SALT LAKE AC BLOCK\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0016648\",\"MICR_Code\":\"NON-MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"C1 25 26 SECTOR II SALT LAKE CITY KARUNAMOYEE KOLOKOTTA\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":100097,\"Branch_Name\":\"SALT LAKE CITY\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0040539\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"HA-1,SECTORIII,BIDHANNAGAR,SALTLAKECITY700097SALTLAKEATSBTCOIN\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":106450,\"Branch_Name\":\"SALT LAKE CITY KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0070682\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"F330SALTLAKEITYSETOR1KOLKATTA\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":129366,\"Branch_Name\":\"SALT LAKE CITY KOLKATTA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0021258\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"FE 233,SECTOR 3,BIDHAN NAGAR,SALT LAKE,KOLKATA 700091\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":132583,\"Branch_Name\":\"SALT LAKE, KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0031677\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"KOLKATA,WB\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":67993,\"Branch_Name\":\"SAMB, KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0004151\",\"MICR_Code\":700002207},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"NAGALAND HOUSE SHAKESPEARE SARANI KOLKATA WEST BENGAL700071\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":125397,\"Branch_Name\":\"SAMB-II KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0018192\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"JEEVANSUDHA BUILDING, 1 ST FLOOR, 42C J.L. NEHRU ROAD, KOLKATA\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":125400,\"Branch_Name\":\"SAMRO -EAST, KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0018197\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"SANTOSHPUR,49,SANTOSHPUR AVENUE,KOLKATA,DISTKOLKATA700075\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":74913,\"Branch_Name\":\"SANTOSHPUR\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0011535\",\"MICR_Code\":700002350},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"5A, N C DUTTA SARANI, 1ST FLOOR, DISTT. KOLKATA, WEST BENGAL 700001\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":70427,\"Branch_Name\":\"SARAT BOSE ROAD\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0006770\",\"MICR_Code\":700002316},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"KOLKATA\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":68888,\"Branch_Name\":\"SARC, KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0005171\",\"MICR_Code\":700002997},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"166/179, SARSUNA MAIN ROAD, KOLKATA - 700061\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":75667,\"Branch_Name\":\"SARSUNA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0012347\",\"MICR_Code\":\"NON-MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"51,KABI.SUKANTA.SARANI,CALCUTTA,PIN.700085\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":71838,\"Branch_Name\":\"SASTHITALA, KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0008253\",\"MICR_Code\":700002166},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"DR MEGHNATH SAHA SARANI, KOLKATA,WEST BENGAL-700029\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":126071,\"Branch_Name\":\"SBI INTOUCH++ SOUTHERN AVENUE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0019033\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"SAMBHUNATH PANDIT STREET, KOLKATA, DIST.KOLKATA, WEST BENGAL-700025\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":126140,\"Branch_Name\":\"SBIINTOUCH  ELGIN ROAD\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0019124\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"CAMAC STREET,NEW B.K.MARKET, SHAKESPEARE SARANI,KOLKATA, DIST.KOLKATA, WEST BENGAL-700071\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":126141,\"Branch_Name\":\"SBIINTOUCH CAMAC STREET\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0019125\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"PRIMISESNO.80C,ALIPOREROAD,KOLKATA,DISTT.KOLKATA.WESTBENGAL700027\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":126142,\"Branch_Name\":\"SBIINTOUCH NEW ALIPORE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0019127\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"PRINCE ANWAR SHAH ROAD, OPP. SOUTH CITY MALL, KOLKATA, DIST.KOLKATA, WEST BENGAL-700045\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":126070,\"Branch_Name\":\"SBIINTOUCH P.A.SHAH ROAD\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0019032\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"SYED AMIR ALI AVENUE, KOLKATA, DIST.KOLKATA, WEST BENGAL-700017\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":126069,\"Branch_Name\":\"SBIINTOUCH QUEST MALL\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0019031\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"SARAT BOSE ROAD, KOLKATA, DIST.KOLKATA, WB-7000126\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":126214,\"Branch_Name\":\"SBIINTOUCH SARAT BOSE ROAD\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0019210\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"302,A.P.C.ROAD,CALCUTTA,WEST BENGAL,PIN 700009\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":66985,\"Branch_Name\":\"SEALDAH\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0003084\",\"MICR_Code\":700002090},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"BJ-114, SECTOR-II, SALT LAKE , KOLKATA - 700091\",\"Branch_City\":null,\"Branch_District\":\"NORTH 24 PARAGANAS\",\"Branch_Id\":75680,\"Branch_Name\":\"SECTOR - II, SALT LAKE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0012361\",\"MICR_Code\":\"NON-MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"IB20 SECTOR III,SALT LAKE CITY KOLKATA\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":139935,\"Branch_Name\":\"SECTOR III SALT LAKE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0040662\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"JEEVAN DEEP,6 AND 7TH FLOOR,1 MIDDLETON STREET,DISTT KOLKATA,W B 700001\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":72125,\"Branch_Name\":\"SERVICE BRANCH , KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0008554\",\"MICR_Code\":700002001},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"9, BRABOUANE ROAD\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":79821,\"Branch_Name\":\"SERVICE BRANCH, KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0030409\",\"MICR_Code\":700005001},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"DIST   KOLKATA  WEST BENGAL 700071\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":66933,\"Branch_Name\":\"SHAKESPEARE SARANI \",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0003031\",\"MICR_Code\":700002091},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"24A PARIJAT SHAKESPEARE SARANI KOLKOTTA\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":137089,\"Branch_Name\":\"SHAKESPEARE SARANI\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0040343\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"ACHARYA PRAFULLA CHANDER RAI ROAD, SHYAM BAZAR, KOLKATA 700004\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":132439,\"Branch_Name\":\"SHYAM BAZAR, KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0031486\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"124A BIDHAN SARANI  CALCUTTA \",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":64128,\"Branch_Name\":\"SHYAMBAZAR \",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0000180\",\"MICR_Code\":700002092},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"MAHARANI VILLA, A/6 E VIDYASAGAR SARANI(JAMES LONG SARANI), DISTT. SOUTH 24 PGS. WEST BENGAL-700063\",\"Branch_City\":null,\"Branch_District\":\"SOUTH 24 PGS\",\"Branch_Id\":77489,\"Branch_Name\":\"SILPARA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0014521\",\"MICR_Code\":\"NON-MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"423D MOTILAL GUPTA ROAD,KOLKATA 700082\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":74911,\"Branch_Name\":\"SIRITI - MUCHIPARA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0011533\",\"MICR_Code\":700002348},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"RASH BEHARI AVENUE, KOLKATA, PIN - 700026\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65712,\"Branch_Name\":\"SITALATALA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001772\",\"MICR_Code\":700002093},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"XI, 8 1 E P BLOCK SALT LAKE ,SECTOR-VKOLKATA PIN - 700091\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":73415,\"Branch_Name\":\"SLE COMPLEX, KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0009985\",\"MICR_Code\":700002193},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"63,NALINI RANJAN AVENUE,KOLKATA 700053\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":75628,\"Branch_Name\":\"SME NEW ALIPORE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0012305\",\"MICR_Code\":700002353},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"OM TOWER, 1ST FLOOR, 36C, B.T. ROAD, KOLKATA - 700002\",\"Branch_City\":null,\"Branch_District\":\"NORTH 24 PARGANAS\",\"Branch_Id\":75669,\"Branch_Name\":\"SME(NORTH KOLKATA) CHIRIAMORE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0012349\",\"MICR_Code\":\"NON-MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"BENGAL\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":68750,\"Branch_Name\":\"SMECC (NEW BRANCH)\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0005011\",\"MICR_Code\":700002299},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"SKY STAR BUILDING, 4TH FLOOR, SEVOKE ROAD, SILIGURI, DIST: DARJEELING, WEST BENGAL: 734001\",\"Branch_City\":null,\"Branch_District\":\"DARJEELING\",\"Branch_Id\":78529,\"Branch_Name\":\"SMECC,SILIGURI\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0015747\",\"MICR_Code\":\"NON-MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"1, MIDDLETON STREET,JEEVANDEEP BUILDING, 5TH FLOOR,KOLKATA - 700071\",\"Branch_City\":null,\"Branch_District\":\"NORTH 24 PARAGANAS\",\"Branch_Id\":78527,\"Branch_Name\":\"SMECCC,BALLYGUNGE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0015743\",\"MICR_Code\":\"NON-MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"SODEPUR STATION ROAD PIN743178\",\"Branch_City\":null,\"Branch_District\":\"NORTH 24 PARGANAS\",\"Branch_Id\":65736,\"Branch_Name\":\"SODEPUR KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001796\",\"MICR_Code\":700002167},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"4TH FLOOR JEEVANSUDHA BUILDING J L NEHRU ROAD 24PARGANASSWEST BENGAL700071\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":124670,\"Branch_Name\":\"SOUTH 24 PARGANAS ZONE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0017988\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"66A, KALICHARAN GHOSH RD, CALCUTTA 700050\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":69071,\"Branch_Name\":\"SOUTH SINTHEE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0005374\",\"MICR_Code\":700002094},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"129 S P MUKHERJEE ROAD  DISTT  KOLKATA  WEST BENGAL 700026\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65447,\"Branch_Name\":\"SOUTHERN AVENUE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001505\",\"MICR_Code\":700002095},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"485,RABINDRA SARANI,KOLKATTA, WEST BENGAL 700005\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65588,\"Branch_Name\":\"SOVABAZAAR, KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001647\",\"MICR_Code\":700002096},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"SAMRIDDHI BHAVAN, 4TH FLOOR, 1, STRAND ROAD, KOLKATA - 700001\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":77492,\"Branch_Name\":\"SPECIALISED INSTITUTIONAL BANKING, KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0014524\",\"MICR_Code\":\"NON-MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"9, BRABOURNE ROAD, KOLKATA -700001\",\"Branch_City\":null,\"Branch_District\":\"NORTH 24 PARAGANAS\",\"Branch_Id\":78029,\"Branch_Name\":\"SPECIALISED TEA BRANCH\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0015197\",\"MICR_Code\":\"NON-MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"39 JAWAHARLAL  NEHRU  ROAD, DIST :  KOLKATA, WEST BENGAL - 700071\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":77713,\"Branch_Name\":\"SPL. CURRENCY ADMINISTRATIVE BRANCH\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0014821\",\"MICR_Code\":\"NON-MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"1, MIDDLETON STREET,JEEVANDEEP BUILDING, 5TH FLOOR,KOLKATA - 700071\",\"Branch_City\":null,\"Branch_District\":\"NORTH 24 PARAGANAS\",\"Branch_Id\":78028,\"Branch_Name\":\"SPL.CHOWRINGHEE SME \",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0015196\",\"MICR_Code\":\"NON-MICR\"},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"BHAWANIEXOTICA,9971DAKSHINDARIROAD,KOLKATA,DISTT.24PARGANS(N),WESTBENGAL700048\",\"Branch_City\":null,\"Branch_District\":\"24 PARGANAS N\",\"Branch_Id\":125351,\"Branch_Name\":\"SREE BHUMI\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0018131\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"22 BY 1  AND  22 BY 3 SREEPALLY PALTA  P O  BENGAL ENAMEL  PALTA   743122\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":73513,\"Branch_Name\":\"SREEPALLY PALTA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0010091\",\"MICR_Code\":700002312},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"11 DR. U.N.BRAMHACHARI ST KOLKATA WEST BENGAL\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":68630,\"Branch_Name\":\"SSI BHOWANIPORE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0004833\",\"MICR_Code\":700002197},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"KOLKATA, WEST BENGAL, PIN - 700012\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65446,\"Branch_Name\":\"SUBODH MULLICK SQUARE, KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001504\",\"MICR_Code\":700002098},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"C211SURVEYPARK,SANTOSHPUR,KOLKATA,DISTT.KOLKATA.WESTBENGAL700075\",\"Branch_City\":null,\"Branch_District\":\"24 PARGANAS N\",\"Branch_Id\":125347,\"Branch_Name\":\"SURVEY PARK\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0018124\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"5.72,SURYA.SEN.STREET,CALCUTTA,PIN.700012\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":67386,\"Branch_Name\":\"SURYA SEN STREET\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0003496\",\"MICR_Code\":700002099},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"INSTITUTE OF  FAMILY AND HEALTH WELFARE  29 GN BLOCK  SEC V  BIDHANNAGAR  KOLKATA 700091\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":73470,\"Branch_Name\":\"SWASTHYA BHAWAN\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0010043\",\"MICR_Code\":700002311},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"47/3, PURNA CHANDRA MITRA LANE, TOLLY GUNJ, SWISS PARK\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":79711,\"Branch_Name\":\"SWISS PARK KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0030287\",\"MICR_Code\":700005005},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"3 WAVELY LANE  DISTT  KOLKATA  WEST BENGAL 700013\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":68570,\"Branch_Name\":\"TALTALA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0004771\",\"MICR_Code\":700002100},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"4A PAGLADANGS 1ST LANE, DISTT. KOLKATA, WEST BENGAL 700015\",\"Branch_City\":null,\"Branch_District\":\"LUCKNOW\",\"Branch_Id\":72323,\"Branch_Name\":\"TANGRA PANCHANANTALA\",\"Branch_State\":\"UTTAR PRADESH\",\"IFSC_Code\":\"SBIN0008761\",\"MICR_Code\":700002179},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"42 BY A, DIAMOND HARBOUR ROAD, DISTT. 24 PARGANAS WEST BENGAL 700 038\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":68448,\"Branch_Name\":\"TARATALA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0004647\",\"MICR_Code\":700002146},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"P 1  TARATALA ROAD  DISTT  24 PARGANAS SOUTH    WEST BENGAL  700088\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":70674,\"Branch_Name\":\"TARATALA INDUSTRIAL ESTATE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0007026\",\"MICR_Code\":700002148},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"VILL. RAGHUNATHPUR, P.O. TEGHORIA - BARASAT, P.S.RAJHAT,24 PGS N ,W.B., PIN - 700059\",\"Branch_City\":null,\"Branch_District\":\"BIRBHUM\",\"Branch_Id\":72298,\"Branch_Name\":\"TEGHORIA RAGHUNATHPUR, KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0008735\",\"MICR_Code\":700002183},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"THAKURPUKUR,P.O.BARISHA,KOLKATA,DIST.24.PARGANASSOUTH\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65301,\"Branch_Name\":\"THAKURPUKUR, KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001357\",\"MICR_Code\":700002147},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"13 B T ROAD,KOLKATTA ,DT24 PARGANAS  N , W. BENGAL ,PIN - 743188\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":68526,\"Branch_Name\":\"TITAGARH\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0004727\",\"MICR_Code\":700002212},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"63 NSC BOSE ROAD  DISTT  KOLKATA  WEST BENGAL 700040\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65660,\"Branch_Name\":\"TOLLYGUNGE\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001719\",\"MICR_Code\":700002103},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"KOLKATA,WEST BENGAL\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65741,\"Branch_Name\":\"TOLLYGUNGE CIR. ROAD\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001801\",\"MICR_Code\":700002102},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"151.CHANDI.GHOSH.ROAD,KOLKATA,WEST.BENGAL,PIN.700040.\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":69374,\"Branch_Name\":\"TOLLYGUNGE NEW THEATER\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0005680\",\"MICR_Code\":700002104},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"23P DRAMBEDKAR SRANI,2ND FLOOR KOLKATA700046\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":74910,\"Branch_Name\":\"TOPSIA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0011532\",\"MICR_Code\":700002331},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"KOLKATA WB\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":68783,\"Branch_Name\":\"TRADE FINANCE CPC KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0005052\",\"MICR_Code\":700002806},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"100 RASH BEHARI AVENUE KOLKATA WEST BENGAL\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65739,\"Branch_Name\":\"TRIANGULAR PARK\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001799\",\"MICR_Code\":700002105},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"P185 CIT SCHEME VII M,KOLKATA 700 054,W.B.\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65771,\"Branch_Name\":\"ULTADANGA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001831\",\"MICR_Code\":700002106},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"GOPAL MISHRA ROAD, BEHALA, KOLKATA, DIST.KOLKATA, WEST BENGAL-700 034\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":136393,\"Branch_Name\":\"UNIQUE PARK\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0018119\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"83 BY 1A, VIVEKANANDA ROAD, KOLKATA 700006\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65592,\"Branch_Name\":\"VIVEKANAND BRANCH\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001651\",\"MICR_Code\":700002107},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"2061F, VIDHAN SARANI, KOLKATA 700006\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":132482,\"Branch_Name\":\"VIVEKANAND ROAD, KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0031539\",\"MICR_Code\":null},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"1,HAJI MHD.MOHASIN SQUARE, KOLKATA,KOLKATA, WEST BENGAL 700016\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":65664,\"Branch_Name\":\"WELLESLEY PARK\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0001723\",\"MICR_Code\":700002108},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"63,NSCBOSE ROAD2ND FLOOR\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":74729,\"Branch_Name\":\"ZONAL INSPECTION OFFICE, KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0011344\",\"MICR_Code\":700002996},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"11 AND 13, SHAKESPEARE SARANIKOLKATA\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":70536,\"Branch_Name\":\"ZONAL OFFICE,  KOLKATA\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0006884\",\"MICR_Code\":700002888},{\"Bank_Id\":0,\"Bank_Name\":null,\"Branch_Address\":\"ZONAL OFFICE,BIDHAN NAGAR\",\"Branch_City\":null,\"Branch_District\":\"KOLKATA\",\"Branch_Id\":70578,\"Branch_Name\":\"ZONAL OFFICE, BIDHAN NAGAR\",\"Branch_State\":\"WEST BENGAL\",\"IFSC_Code\":\"SBIN0006928\",\"MICR_Code\":700002777}],\"ReturnCode\":1,\"ReturnMsg\":\"Record retrieved successfully\",\"UDP\":null}";
			branchOutput = mapper.readValue(text, GetFPurchaseBranchOutput.class);
		} catch (IOException e) {
			e.printStackTrace();
		}*/



		logger.info("getFPurchaseBankBranch - complete");
		return branchOutput;

	}


	@Override
	public Object getInstantRedemebaleFolio(String folioNumber, String amcName, Object accessToken, ClientSystemDetails clientSystem) {


		logger.info("Requesting list of redembale folio from Birla- " );
		ObjectMapper mapper = new ObjectMapper(); 

		GetIRDetailsForFolioInput folioInput = new GetIRDetailsForFolioInput();
		GetIRDetailsForFolioInput.FolioSchemeRequest request = new FolioSchemeRequest();
		GetIRDetailsForFolioOutput folioOutput = null;


		String resquestBody="";

		request.setUserId(USERID);
		request.setPassword(PASSWORD);
		request.setFolioNo(folioNumber);

		folioInput.setFolioSchemeRequestObject(request);

		try {
			resquestBody = mapper.writeValueAsString(folioInput);
			logger.info(resquestBody);
			ResponseEntity<String> response =  postcallwithHeaders(getUri(GET_IR_FOLIO_DETAILS), new HttpEntity<String>(resquestBody,generateHttpHeaders(folioNumber)));
			logger.info(response.getBody());
			folioOutput = mapper.readValue(response.getBody(), GetIRDetailsForFolioOutput.class);
			logger.info("Birla IR folio details retrieved successfully");
		} catch (JsonProcessingException e1) {
			e1.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}catch(HttpServerErrorException e){
			e.getRawStatusCode();
		} catch(Exception e){
			e.printStackTrace();
		}

		logger.info("getFPurchaseBanks - complete");
		return folioOutput;
	}


	@Override
	public Object InstandWithdrawAmount(String foliopNUmber, String amount, String amcName, Object accessToken, ClientSystemDetails clientSystem) {



		return null;
	}

	@Override
	public Object processNewProfile(MFInvestForm investForm, ValidateAadhaarOTPOutput aadhaarData, ClientSystemDetails clientSystem) {

		// Map bank details from Birla
		logger.info("Processing new profile request for user with PAN for Birla-"+ investForm.getPAN());

//		ObjectMapper mapper = new ObjectMapper();
		UserBankDetails birlaBankDetails = null;
		GetFPurchaseBranchOutput branch = null;
		GetFPurchaseBranchOutput.FPurchaseBranches branchData = null;
		SavePreSIPMultipleSchemesOutput schemeOutput =null;

//		GetBankDetailsByIFSCOutput bankIfcOutput = new GetBankDetailsByIFSCOutput();
//		GetBankDetailsByIFSCOutput.lstSIPBankDet sipBankDetails = new lstSIPBankDet();

		FolioCreationStatus status = new FolioCreationStatus();
		SavePrePurchaseMultiRequestOutput lumpsumSchemePreOutput = null;
		//		NewFolioCreateOutput folioCreateResponse = null;
		String bankNameId ="";
		String ifscCode ="";

		try{

			bankNameId=getBirlaBankName(investForm.getBankDetails().getBankName());
			ifscCode=investForm.getBankDetails().getIfscCode().toUpperCase();
			logger.info("Bank ifsc code- " + ifscCode);
			if(ifscCode!=null){

				branch = (GetFPurchaseBranchOutput) getFPurchaseBankBranch("", bankNameId.split("\\|")[0], investForm.getBankDetails().getBankCity().toUpperCase(), null,clientSystem);
				//				bankIfcOutput = (GetBankDetailsByIFSCOutput) getBankDetailsByIFSC(ifscCode, clientSystem);
				if(branch!=null){
					//				if(bankIfcOutput!=null && branch!=null && bankIfcOutput.getReturnCode().equalsIgnoreCase("1")){

					for(int i=0;i<branch.getFpPrchaseBranches().size();i++){
						branchData = branch.getFpPrchaseBranches().get(i);
						if(branchData.getIfsc_Code().trim().equalsIgnoreCase(investForm.getBankDetails().getIfscCode().trim())){
							logger.info("Found Branch Details- "+ branchData.getBranch_Id()+"|"+branchData.getBranch_Name());
							birlaBankDetails= new UserBankDetails();

							birlaBankDetails.setBankName(bankNameId);
							birlaBankDetails.setBankBranch(branchData.getBranch_Id()+"|"+branchData.getBranch_Name());
							//							birlaBankDetails.setBankBranch(branchData.getBranch_Id()+"|"+(bankIfcOutput.getLstSIPBankDet().get(0)).getBranch_Name());
							birlaBankDetails.setBankAddress(branchData.getBranch_Address());
							//							birlaBankDetails.setBankAddress((bankIfcOutput.getLstSIPBankDet().get(0)).getAddress());
							birlaBankDetails.setIfscCode(branchData.getIfsc_Code());
							birlaBankDetails.setBankCity(branchData.getBranch_City()!=null?branchData.getBranch_City():investForm.getBankDetails().getBankCity());
							//							birlaBankDetails.setBankCity((bankIfcOutput.getLstSIPBankDet().get(0)).getCentre());

							break;

						}
					}

					/*
					birlaBankDetails= new UserBankDetails(); 
					birlaBankDetails.setBankName((bankIfcOutput.getLstSIPBankDet().get(0)).getSrNo()+"|"+(bankIfcOutput.getLstSIPBankDet().get(0)).getBank_Name());
					birlaBankDetails.setBankBranch((bankIfcOutput.getLstSIPBankDet().get(0)).getBranch_Name());
					birlaBankDetails.setBankAddress((bankIfcOutput.getLstSIPBankDet().get(0)).getAddress());
					birlaBankDetails.setIfscCode((bankIfcOutput.getLstSIPBankDet().get(0)).getIfscCode());
					birlaBankDetails.setBankCity((bankIfcOutput.getLstSIPBankDet().get(0)).getCentre());*/

					//Call to generate new folio
					status = generateNewFolio(investForm, birlaBankDetails,aadhaarData,clientSystem);

					//If status is successful and folio number generated then process to save SIP details for the selected fund under the folio
					if(status!=null){
						if(status.isTransactionSuccessful()){

							if(investForm.getSelectedFund().getInvestType().equalsIgnoreCase("TARGET_PLAN")){
								/*Lumsum purchase*/

								lumpsumSchemePreOutput= (SavePrePurchaseMultiRequestOutput) preLumsumPurchaseSave(status.getFolioNumber(), investForm.getSelectedFund().getMonthlySavings(), null, investForm, birlaBankDetails, clientSystem);
								if(lumpsumSchemePreOutput!=null){
									if(lumpsumSchemePreOutput.getReturnCode().equalsIgnoreCase("1")){

										logger.info("Pre Lumpsumpurchase save is successful for generated folio- "+ status.getFolioNumber());
										status.setUrnNumber("Not Applicable for lumpsum");
										status.setTransNo(lumpsumSchemePreOutput.getTransNo());
										status.setSchemeCode(lumpsumSchemePreOutput.getSchemeDetails()!=null? lumpsumSchemePreOutput.getSchemeDetails().get(0).getSchemeCode(): "NA");
										status.setTransRefNo(lumpsumSchemePreOutput.getTransReferenceNo());
										status.setSavePreSIPCode(lumpsumSchemePreOutput.getReturnCode());
										status.setSavePreSIPSuccessful(true);
										status.setSavePreSIPMessage(lumpsumSchemePreOutput.getReturnMsg());

									}else{
										logger.info("SavePrePurchaseMultiRequest : lumpsum is unsuccessful. reason for folio-  "+ status.getFolioNumber()/*+ " :Reason- "+ schemeOutput.getReturnMsg()*/);
										status.setSavePreSIPSuccessful(false);
										status.setSavePreSIPMessage(lumpsumSchemePreOutput.getReturnMsg());
									}
								}else{
									status.setSavePreSIPSuccessful(false);
									status.setSavePreSIPMessage("Unable to process lumpsum purchase. Please try again.");
								}
							}else{
								/*------------------------------------------------------- SIP type purchase ------------------------------------------------------------------------------------------------------*/
								schemeOutput= (SavePreSIPMultipleSchemesOutput) savePreSIPMultipleSchemes(status.getFolioNumber(), status.getFolioNumber(), null, investForm, birlaBankDetails,clientSystem);
								if(schemeOutput!=null){
									status.setPan(investForm.getPAN().toUpperCase());
									if(schemeOutput.getReturnCode().equalsIgnoreCase("1")){
										logger.info("savePreSIPMultipleSchemes is successful for generated folio- "+ status.getFolioNumber());
										status.setUrnNumber(schemeOutput.getUrnNumber()!=null?schemeOutput.getUrnNumber():"");
										status.setTransNo(schemeOutput.getTransNo());
										status.setSchemeCode(schemeOutput.getSchemeDetails()!=null? schemeOutput.getSchemeDetails().get(0).getSchemeCode(): "NA");
										status.setTransRefNo(schemeOutput.getTransReferenceNo());
										status.setSavePreSIPCode(schemeOutput.getReturnCode());
										status.setSavePreSIPSuccessful(true);
										status.setSavePreSIPMessage(schemeOutput.getReturnMsg());
									}else{
										logger.info("savePreSIPMultipleSchemes is unsuccessful. reason for folio-  "+ status.getFolioNumber()+ " :Reason- "+ schemeOutput.getReturnMsg());
										status.setSavePreSIPSuccessful(false);
										status.setSavePreSIPMessage(schemeOutput.getReturnMsg());
									}
								}else{

									status.setSavePreSIPSuccessful(false);
									status.setSavePreSIPMessage("Unable to process. Please try again.");
								}

							}	
						}else{
							logger.info("Error in cams entry from birla");
							status.setSavePreSIPSuccessful(false);
							status.setSavePreSIPMessage("Error in processing at AMC");
						}
					}

				}
			}else{
				logger.info("Error while processing bank details by IFSC code. Not processing request for new folio creation.");
			}

		}catch(Exception e){
			logger.error("Unable to process birla new profile.", e.getMessage());
		}



		/*
		//Testing purpose code
		status = generateNewFolio(investForm, birlaBankDetails,aadhaarData, clientSystem);
		bankNameId=getBirlaBankName(investForm.getBankDetails().getBankName());

		birlaBankDetails= new UserBankDetails();

		birlaBankDetails.setBankName(bankNameId);
		birlaBankDetails.setBankBranch("65687"+"|"+"PAIKPARA");
		birlaBankDetails.setBankAddress("47,GARFA MAIN ROAD,KOLKATA,WEST BENGAL,PIN - 700075");
		birlaBankDetails.setIfscCode("SBIN0001747");
		birlaBankDetails.setBankCity("KOLKATA");

		schemeOutput= (SavePreSIPMultipleSchemesOutput) savePreSIPMultipleSchemes("1017634227", "1017634227", null, investForm, birlaBankDetails,clientSystem);

			if(schemeOutput!=null){
				if(schemeOutput.getReturnCode().equalsIgnoreCase("1")){
//					status.setCamsEntryDate((schemeOutput.getTransactionDetails().get(0).getCamsEntryDate()));
//					status.setCamsTransDate(schemeOutput.getTransactionDetails().get(0).getCamsTransDate());
					status.setUrnNumber(schemeOutput.getUrnNumber()!=null?schemeOutput.getUrnNumber():"");
					status.setTransNo(schemeOutput.getTransNo());
					status.setTransRefNo(schemeOutput.getTransReferenceNo());
					status.setSavePreSIPCode(schemeOutput.getReturnCode());
					status.setSavePreSIPSuccessful(true);
					status.setSavePreSIPMessage(schemeOutput.getReturnMsg());
				}else{
					status.setSavePreSIPSuccessful(false);
					status.setSavePreSIPMessage(schemeOutput.getReturnMsg());
				}
			}*/

		return status;
	}



	private HttpHeaders generateHttpHeaders(String requestParameter){

		LocalDateTime date = LocalDateTime.now(ZoneOffset.ofHoursMinutes(5, 30));
		String checkSum= getCheckSum(date.format(formatter1),requestParameter );
		OauthAccessToken accessToken = getAccessToken();

		HttpHeaders headers =  setDefaultHeaders(checkSum, accessToken, date.format(formatter2));

		return headers;
	}


	private HttpHeaders setDefaultHeaders(String checkSum, OauthAccessToken accessToken, String timeStamp) {
		logger.info(timeStamp);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+ accessToken.getAccess_token());
		headers.set("CheckSum",checkSum);
		headers.set("DateTimeStamp", timeStamp);
		headers.set("Content-Type", "application/json");

		return headers;
	}





	/**
	 * Get access token of Birla
	 * @author FREEMI
	 * @return OauthAccessToken
	 */
	private OauthAccessToken getAccessToken() {

		if(token == null){
			String encodedToken ="";
			try {
				encodedToken= Base64.getEncoder().encodeToString((CONSUMER_KEY+":"+CONSUMER_SECRET).getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e) {
				logger.info(e.getMessage());
			}

			HttpHeaders headers = new HttpHeaders();
			headers.set("Authorization", "Basic "+encodedToken);
			headers.set("Content-Type", "application/x-www-form-urlencoded");
			HttpEntity<String> entity = new HttpEntity<String>("grant_type=client_credentials", headers);
			ResponseEntity<String> response =  postcallwithHeaders(getUri("/token"), entity);

			ObjectMapper mapper = new ObjectMapper();
			//		OauthAccessToken token = new OauthAccessToken();
			try {
				token = mapper.readValue(response.getBody(), OauthAccessToken.class);
				logger.info(token.getExpires_in());

			} catch (IOException e) {
				e.printStackTrace();
			}
			logger.info("Access token - "+ token.getAccess_token());
			return token;
		}else{
			logger.info("Token already exist. Return token");
			return token;
		}
	}


	private URI getUri(String passedURI){
		URI uri = null;
		try {
			uri = new URI(passedURI);
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
		return uri;
	}

	private ResponseEntity<String> postcallwithHeaders(URI uri,HttpEntity<String> entity){

		logger.info("Requested url- "+ uri==null?"":uri.getPath());
		UriComponents uricomp = UriComponentsBuilder.fromUriString(BASE_URL)
				.path(uri==null?"":uri.getPath()).build();

		RestTemplate restTemplate = new RestTemplate();
		logger.info("REquest to - "+uricomp.toString());
		return restTemplate.postForEntity(uricomp.toString(), entity,  String.class);
	}


	/* (non-Javadoc)
	 * List the schemes based on the category (P/SIP/CSIP/SCTP)
	 * @see com.app.services.partners.Interfaces.InvestmentConnectorInterfaces#getSChemeDetails(java.lang.String, java.lang.Object, java.lang.Object)
	 */
	@Override
	public Object getSChemeDetails(String transType,String authCode, Object accessToken, Object mfInvestForm) {
		logger.info("Requesting scheme detailed list for the day. " );
		ObjectMapper mapper = new ObjectMapper();  

		GetSchemeMasterDetailsInput detailsInput= new GetSchemeMasterDetailsInput();
		GetSchemeMasterDetailsInput.Request request = new Request();
		GetSchemeMasterDetailsOutput detailOutput = null;

		String resquestBody="";

		/*Generate checksum and get access token*/

		request.setUserId(USERID);
		request.setPassword(PASSWORD);
		request.setTransType(transType==null?"":transType);				// Accepted values- P/S/C/STPF/STPT/SWP/SWITCHF/SWITCHT/RD/TRFROM/TRTO/IR/<blank for all type transactions>
		request.setAuthCode(authCode==null?"":authCode);
		detailsInput.setRequestObject(request);

		try {

			resquestBody = mapper.writeValueAsString(detailsInput);
			logger.info(resquestBody);

			ResponseEntity<String> response =  postcallwithHeaders(getUri(GET_PRODUCT_SCHEME_DETAILS_URI), new HttpEntity<String>(resquestBody,generateHttpHeaders((transType==null?"":transType)+"|"+(authCode==null?"":authCode))));
			logger.info(response.getBody());

			detailOutput = mapper.readValue(response.getBody(), GetSchemeMasterDetailsOutput.class);
			logger.info("Total SCHEMES- "+ detailOutput.getSchemeDetails().size());


			logger.info("Birla proudtc details retrieved successfully");
		}catch (JsonProcessingException e1) {
			e1.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}catch(HttpServerErrorException e){
			e.getStatusCode();
		}catch(Exception e){
			e.printStackTrace();
		}


		logger.info("GetProductdetails - complete");
		return detailOutput;
	}


	/* (non-Javadoc)
	 * List of SIP and details of the scheme
	 * @see com.app.services.partners.Interfaces.InvestmentConnectorInterfaces#getProductDetails(java.lang.String)
	 */
	@Override
	public Object getProductDetails(String transactionType,String authCode,  Object accessToken, Object mfInvestForm) {
		logger.info("Requesting product details for the day. " );
		ObjectMapper mapper = new ObjectMapper();  

		GetProductDetailsInput productInput = new GetProductDetailsInput();
		GetProductDetailsInput.ProductDetailsRequest productInputDetails = new ProductDetailsRequest();
		GetProductDetailsOutput detailsOutput = null;
		String resquestBody="";
		String responseBody="";
		/*Generate checksum and get access token*/

		productInputDetails.setUserId(USERID);
		productInputDetails.setPassword(PASSWORD);
		productInput.setProductDetailsRequestObject(productInputDetails);


		try {
			resquestBody = mapper.writeValueAsString(productInput);

			logger.info(resquestBody);
			ResponseEntity<String> response =  postcallwithHeaders(getUri(GET_PRODUCT_DETAILS_URI), new HttpEntity<String>(resquestBody,generateHttpHeaders(USERID)));
			responseBody= response.getBody();
			logger.info(responseBody);
			detailsOutput = mapper.readValue(responseBody, GetProductDetailsOutput.class);
			logger.info("Total AUMs- "+ detailsOutput.getSchemeDetails().size());
			logger.info("Birla proudtc details retrieved successfully");

		} catch (JsonProcessingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch(HttpServerErrorException e){
			e.getStatusCode();
		} catch(Exception e){
			e.printStackTrace();
		}

		logger.info("GetProductdetails - complete");
		return detailsOutput;
	}




	/*

	public static void main(String[] args){


		URI uri = null;
		try {
			uri = new URI("/token");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		logger.info(uri==null);
//		logger.info(uri.getPath());
		UriComponents uricomp = UriComponentsBuilder.fromUriString(BASE_URL)
				.path(uri==null?"":uri.getPath()).build();
		logger.info(uricomp.toString());

		ResponseEntity<String> response =null;
		BirlaConnectorsImpl n = new BirlaConnectorsImpl();

//		n.getStateCityList();

//		n.validatePAN("", "CQOPS7539C");

//		n.validateAADHAARNumber("637518724398");

//		String text ="{\"ValidatePANResult\":{\"AadharNo\":null,\"Folios\":null,\"IsEKYCVerified\":\"Y\",\"IsExistingInvestor\":\"N\",\"KYC_Mode\":0,\"NameAsPerPAN\":\"DEBASISH  SARKAR\",\"PANNumber\":\"CQOPS7539C\",\"PanResponseLog\":null,\"ReturnCode\":0,\"ReturnMsg\":\"No Record Found\",\"UDP\":null,\"isFATCAVerified\":\"N.A.\"}}";

		String text ="{ \"ValidatePANResult\": { \"Folios\": [ { \"ActivatedAmount\": \"N.A\", \"DefaultFlag\": \"Y\", \"EmailID\": \"tarunkp2@gmail.com\", \"Folio_No\": \"1016779822\", \"InvestorName\": \"TARUN K POOJARY\", \"LiquidFlag\": \"YES\", \"MAXDATE\": \"03-OCT-16\", \"MobileNumber\": \"+919702903111\", \"PANNo\": null } ], \"IsEKYCVerified\": \"Y\", \"IsExistingInvestor\": \"Y\", \"NameAsPerPAN\": \"TARUN K POOJARY\", \"PANNumber\": \"AACPP0404B\", \"ReturnCode\": \"1\", \"ReturnMsg\": \"Record retrieved successfully\", \"UDP\": \"\", \"isFATCAVerified\": \"N.A.\" } }";
		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//		mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
		ValidatePANOutput out1 = new ValidatePANOutput();

		try {
//			logger.info(mapper.writeValueAsString(out1));
			out1 = mapper.readValue(text, ValidatePANOutput.class);
			logger.info(out1.getPanData().getIsEKYCVerified());
		} catch (IOException e1) {
			e1.printStackTrace();
		}


		try {
			out1 = mapper.readValue(text,  ValidatePANOutput.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.info("Complete");
//		logger.info(out1.getValidatePANResultObject().getIsEKYCVerified());

	}

	 */



	private String getBirlaBankName(String commonBankName){
		HashMap<String, String> bankNames= new HashMap<String,String>();
		bankNames.put("ICICI Bank", "12|ICICI BANK LIMITED");
		bankNames.put("State Bank of India", "23|STATE BANK OF INDIA");
		bankNames.put("A.P. Mahesh Co-operative Urban Bank","6099|THE A.P. MAHESH COOPERATIVE URBAN BANK LIMITED");
		bankNames.put("Axis Bank","3|AXIS BANK");
		bankNames.put("Bank of Baroda","4|BANK OF BARODA");
		bankNames.put("Airtel Payments Bank","6004|AIRTEL PAYMENTS BANK LIMITED");
		//		bankNames.put("Andhra Bank","Andhra Bank");
		//		bankNames.put("Bank of Bahrein and Kuwait","Bank of Bahrein and Kuwait");
		bankNames.put("Central Bank of India","3201|CENTRAL BANK OF INDIA");
		bankNames.put("Corporation Bank","8|CORPORATION BANK");
		bankNames.put("IDBI","13|IDBI BANK");
		bankNames.put("Dena Bank","9|DENA BANK");
		bankNames.put("Himachal Pradesh State Co-operative Bank","6037|HIMACHAL PRADESH STATE COOPERATIVE BANK LTD");
		bankNames.put("Indian Overseas Bank","15|INDIAN OVERSEAS BANK");
		bankNames.put("Indusind Bank","16|INDUSIND BANK");
		bankNames.put("Syndicate Bank","29|SYNDICATE BANK");
		bankNames.put("Yes Bank","36|YES BANK");
		bankNames.put("IDFC Bank","5088|IDFC BANK LIMITED");
		bankNames.put("Prathama Bank","6068|PRATHAMA BANK");
		bankNames.put("Karnataka Vikas Grameena Bank","6051|KARNATAKA VIKAS GRAMEENA BANK");
		bankNames.put("City Union Bank","3304|CITY UNION BANK LIMITED");
		bankNames.put("Abhyudaya Co-operative Bank","6001|ABHYUDAYA COOPERATIVE BANK LIMITED");
		bankNames.put("Almora Urban Co-operative Bank","6006|ALMORA URBAN COOPERATIVE BANK LIMITED");
		bankNames.put("Nagpur Nagarik Sahakari Bank","6060|NAGPUR NAGARIK SAHAKARI BANK LIMITED");
		bankNames.put("Jammu and Kashmir Bank","4689|JAMMU AND KASHMIR BANK LIMITED");
		bankNames.put("Dhanlaxmi Bank","31|DHANALAKSHMI BANK");
		bankNames.put("Shivalik Mercantile Co-operative Bank","6083|SHIVALIK MERCANTILE CO OPERATIVE BANK LTD");
		bankNames.put("Delhi State Co-operative Bank","6105|THE DELHI STATE COOPERATIVE BANK LIMITED");
		bankNames.put("Punjab National Bank","19|PUNJAB NATIONAL BANK");
		bankNames.put("Thane Bharat Sahakari Bank","6129|THE THANE BHARAT SAHAKARI BANK LIMITED");
		bankNames.put("Janata Sahakari Bank (Pune)","6046|JANATA SAHAKARI BANK LIMITED");
		bankNames.put("Saraswat Co-operative Bank","2420|SARASWAT COOPERATIVE BANK LIMITED");
		bankNames.put("Akola Janata Commercial Co-operative Bank","6005|AKOLA JANATA COMMERCIAL COOPERATIVE BANK");
		bankNames.put("Canara Bank","6|CANARA BANK");
		//		bankNames.put("NKGSB Co-operative Bank","NKGSB Co-operative Bank");
		bankNames.put("West Bengal State Co-operative Bank","6133|THE WEST BENGAL STATE COOPERATIVE BANK");
		bankNames.put("Karad Urban Co-operative Bank","6113|THE KARAD URBAN COOPERATIVE BANK LIMITED");
		bankNames.put("Development Bank of Singapore","6026|DEVELOPMENT BANK OF SINGAPORE");
		bankNames.put("Suryoday Small Finance Bank","6094|SURYODAY SMALL FINANCE BANK LIMITED");
		bankNames.put("Andhra Pradesh State Co-operative Bank","6101|THE ANDHRA PRADESH STATE COOPERATIVE BANK LIMITED");
		bankNames.put("Janaseva Sahakari Bank","6045|JANASEVA SAHAKARI BANK LIMITED");
		bankNames.put("Equitas Small Finance Bank","6030|EQUITAS SMALL FINANCE BANK LIMITED");
		bankNames.put("United Bank of India","35|UNITED BANK OF INDIA");
		bankNames.put("Vijaya Bank","115|VIJAYA BANK");
		bankNames.put("Shamrao Vithal Co-operative Bank","1584|THE SHAMRAO VITHAL COOPERATIVE BANK");
		bankNames.put("Pragathi Krishna Gramin Bank","6067|PRAGATHI KRISHNA GRAMIN BANK");
		bankNames.put("Union Bank of India","34|UNION BANK OF INDIA");
		bankNames.put("HDFC Bank","10|HDFC BANK");
		bankNames.put("Karur Vysya Bank","4912|KARUR VYSYA BANK");
		bankNames.put("Rajasthan State Co-operative Bank","6123|THE RAJASTHAN STATE COOPERATIVE BANK LIMITED");
		bankNames.put("Indian Bank","14|INDIAN BANK");
		bankNames.put("Oriental Bank of Commerce","911|ORIENTAL BANK OF COMMERCE");
		bankNames.put("The Sindhudurg District Central Co-operative Bank","6126|THE SINDHUDURG DISTRICT CENTRAL COOP BANK LTD");
		bankNames.put("Kallappanna Awade Ichalkaranji Janata Sahakari Bank","6048|KALLAPPANNA AWADE ICHALKARANJI JANATA SAHAKARI BANK LIMITED");
		bankNames.put("Capital Small Finance Bank","6019|CAPITAL SMALL FINANCE BANK LIMITED");
		bankNames.put("Apna Sahakari Bank","6008|APNA SAHAKARI BANK LIMITED");
		bankNames.put("South Indian Bank","2984|SOUTH INDIAN BANK");
		bankNames.put("Bharat Co-operative Bank","6018|BHARAT COOPERATIVE BANK MUMBAI LIMITED");
		bankNames.put("Dombivli Nagari Sahakari Bank","6029|DOMBIVLI NAGARI SAHAKARI BANK LIMITED");
		//		bankNames.put("Rajarambapu Sahakari Bank","Rajarambapu Sahakari Bank");
		bankNames.put("Karnataka Bank","4894|KARNATAKA BANK LIMITED");
		bankNames.put("Tamilnadu Mercantile Bank","361|TAMILNAD MERCANTILE BANK LIMITED");
		bankNames.put("Punjab & Sind Bank","1166|PUNJAB AND SIND BANK");
		bankNames.put("Kangra Central Co-operative Bank","6111|THE KANGRA CENTRAL COOPERATIVE BANK LIMITED");
		bankNames.put("UCO Bank","33|UCO BANK");
		bankNames.put("Bank of India","5|BANK OF INDIA");
		bankNames.put("Bank of Maharashtra","5087|BANK OF MAHARASHTRA");
		bankNames.put("CITI Bank","7|CITI BANK");
		bankNames.put("Utkarsh Small Finance Bank","6140|UTKARSH SMALL FINANCE BANK");
		bankNames.put("Allahabad Bank","1|ALLAHABAD BANK");
		bankNames.put("Maharashtra Gramin Bank","6055|MAHARASHTRA GRAMIN BANK");
		bankNames.put("Fino Payments Bank","6033|FINO PAYMENTS BANK");
		bankNames.put("Nasik Merchants Co-operative Bank","6120|THE NASIK MERCHANTS COOPERATIVE BANK LIMITED");
		bankNames.put("Pandharpur Urban Co Op. Bank Pandharpur","6122|THE PANDHARPUR URBAN CO OP. BANK LTD. PANDHARPUR");
		bankNames.put("Kotak Mahindra Bank","18|KOTAK MAHINDRA BANK LIMITED");
		bankNames.put("Rajgurunagar Sahakari Bank","6073|RAJGURUNAGAR SAHAKARI BANK LIMITED");
		//		bankNames.put("Thane Janata Sahakari Bank","Thane Janata Sahakari Bank");
		bankNames.put("Paytm Payments Bank","6066|PAYTM PAYMENTS BANK LTD");
		bankNames.put("Zoroastrian Co-operative Bank","6134|THE ZOROASTRIAN COOPERATIVE BANK LIMITED");
		bankNames.put("Kerala Gramin Bank","6053|KERALA GRAMIN BANK");
		bankNames.put("AU Small Finance Bank","6009|AU SMALL FINANCE BANK LIMITED");
		//		bankNames.put("Kozhikode District Co-operatiave Bank","Kozhikode District Co-operatiave Bank");
		//		bankNames.put("Gurgaon Gramin Bank","Gurgaon Gramin Bank");
		bankNames.put("Gopinath Patil Parsik Janata Sahakari Bank","6035|G P PARSIK BANK");		//tocheck
		bankNames.put("DCB Bank","3443|DCB BANK LIMITED");
		bankNames.put("Citizen Credit Co-operative Bank","6021|CITIZEN CREDIT COOPERATIVE BANK LIMITED");
		bankNames.put("Telangana State Co-operative Apex Bank","6096|TELANGANA STATE COOP APEX BANK");
		//		bankNames.put("Kurla Nagarik Sahakari Bank","Kurla Nagarik Sahakari Bank");
		bankNames.put("Esaf Small Finance Bank","6031|ESAF SMALL FINANCE BANK LIMITED");
		bankNames.put("Punjab & Maharashtra Co-operative Bank","6071|PUNJAB AND MAHARSHTRA COOPERATIVE BANK");
		bankNames.put("Ujjivan Small Finance Bank","6138|UJJIVAN SMALL FINANCE BANK LIMITED");
		//		bankNames.put("North East Small Finance Bank","North East Small Finance Bank");
		bankNames.put("Reserve Bank of India","6076|RESERVE BANK OF INDIA, PAD");
		bankNames.put("RBL Bank","1306|RBL BANK LIMITED");
		bankNames.put("Cosmos Co-operative Bank","6104|THE COSMOS CO OPERATIVE BANK LIMITED");
		bankNames.put("Surat District Co-operative Bank","6127|THE SURAT DISTRICT COOPERATIVE BANK LIMITED");
		bankNames.put("Mumbai District Central Co-operative Bank","6117|THE MUMBAI DISTRICT CENTRAL COOPERATIVE BANK LIMITED");
		bankNames.put("Nainital Bank","6119|THE NAINITAL BANK LIMITED");
		bankNames.put("Akola District Central Co-operative Bank","6100|THE AKOLA DISTRICT CENTRAL COOPERATIVE BANK");
		bankNames.put("Nutan Nagarik Sahakari Bank","6065|NUTAN NAGARIK SAHAKARI BANK LIMITED");
		bankNames.put("Bandhan Bank","6012|BANDHAN BANK LIMITED");
		bankNames.put("Laxmi Vilas Bank","831|LAXMI VILAS BANK");
		bankNames.put("Andhra Pragathi Grameena Bank","6007|ANDHRA PRAGATHI GRAMEENA BANK");
		bankNames.put("Federal Bank","4402|FEDERAL BANK");
		bankNames.put("Ahmedabad Mercantile Co-operative Bank","6003|AHMEDABAD MERCANTILE COOPERATIVE BANK");
		bankNames.put("Thane District Central Co-operative Bank","6130|THE THANE DISTRICT CENTRAL COOPERATIVE BANK LIMITED");
		bankNames.put("Shri Chhatrapati Rajashri Shahu Urban Co-operative Bank","6084|SHRI CHHATRAPATI RAJASHRI SHAHU URBAN COOPERATIVE BANK LIMITED");
		bankNames.put("Jalgaon Peoples Co-operative Bank","6110|THE JALGAON PEOPELS COOPERATIVE BANK LIMITED");
		//		bankNames.put("Mysore Chamarajanagar District Co-operative Bank","Mysore Chamarajanagar District Co-operative Bank");
		bankNames.put("Municipal Co-operative Bank","6118|THE MUNICIPAL COOPERATIVE BANK LIMITED");
		bankNames.put("Solapur Janata Sahakari Bank","6086|SOLAPUR JANATA SAHAKARI BANK LIMITED");
		bankNames.put("Catholic Syrian Bank","3191|CATHOLIC SYRIAN BANK LIMITED");
		bankNames.put("Standard Chartered Bank","20|STANDARD CHARTERED BANK");
		bankNames.put("Sutex Co-operative.bank","6095|SUTEX COOPERATIVE BANK LIMITED");

		return bankNames.get(commonBankName);

	}


	@Override
	public Object preLumsumPurchaseSave(String primeFolio, String totalAmount, Object accessToken,
			MFInvestForm mfInvestForm, UserBankDetails bankDetails, ClientSystemDetails clientSystem) {
		logger.info("Save pre LUMPSUM purchase schemes started " );
		ObjectMapper mapper = new ObjectMapper();  

		SavePrePurchaseMultiRequestInput preSchemeInput = new SavePrePurchaseMultiRequestInput();
		SavePostPurchaseMultiRequestOutput postSchemeOutput = new SavePostPurchaseMultiRequestOutput();

		String resquestBody="";

		try {
			preSchemeInput = (SavePrePurchaseMultiRequestInput) BirlaBeansMapper.ToSavePreLumpsumSchmesInput(primeFolio, "", mfInvestForm, bankDetails,clientSystem);
			resquestBody = mapper.writeValueAsString(preSchemeInput).replace("\\\\", "\\");
			logger.info("Lumpsum purchase request body- "+ resquestBody);

			ResponseEntity<String> response =  postcallwithHeaders(getUri(SAVE_PRE_LUMPSUM_PURCHASE_SCHEMES), new HttpEntity<String>(resquestBody,generateHttpHeaders((totalAmount==null?"":totalAmount)+"|"+(primeFolio==null?"":primeFolio))));
			logger.info("Response from lumpsum purchase presave - Aditya Birla: "+ response.getBody());

			postSchemeOutput = mapper.readValue(response.getBody(), SavePostPurchaseMultiRequestOutput.class);

			logger.info("Birla save lumpsum purchase schemes api executed successfully");
		}catch (JsonProcessingException e1) {
			logger.error("preLumsumPurchaseSave(): Error processing json "+ e1.getMessage());
		}catch (IOException e) {
			logger.error("IOException with lumspumpurchase Aditya API", e.getMessage());
			e.printStackTrace();
		}catch(Exception e){
			logger.error("Exceptiopn with lumspumpurchase Aditya API", e.getMessage());
			e.printStackTrace();
		}

		/*try {

		schemeInput = BirlaBeansMapper.ToSavePreSIPSchmesInput(primeFlio, activeFolioNumber, mfInvestForm, bankDetails);
		String text="{ \"ReturnCode\": 1, \"ReturnMsg\": \"Record retrieved successfully\", \"SchemeDetails\": [ { \"SchemeCode\": \"106D\", \"SchemeReferenceId\": null, \"SchemeTransNo\": null } ], \"TransNo\": 27561, \"TransReferenceNo\": \"SP27561\", \"TransactionDetails\": [ null ], \"UDP\": null, \"URNNumber\": \"BS141396-027561\" }";
		schemeOutput = mapper.readValue(text, SavePreSIPMultipleSchemesOutput.class);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/

		logger.info("Save Pre-SIP Multiple schemes - complete");
		return postSchemeOutput;
	}


	@Override
	public Object postLumsumPurchaseSave(FolioCreationStatus folioStatus,String paymentStatus, Object accessToken,
			MFInvestForm mfInvestForm, UserBankDetails bankDetails, ClientSystemDetails clientSystem) {

		logger.info("Save post lumpsum purchase " );
		ObjectMapper mapper = new ObjectMapper();  


		SavePostPurchaseMultiRequestOutput schemePostOutput = new SavePostPurchaseMultiRequestOutput();

		String resquestBody="";

		try {
			SavePostPurchaseMultiRequestInput saveInput = (SavePostPurchaseMultiRequestInput) BirlaBeansMapper.ToSavePostLumpsumSchmesInput(folioStatus, mfInvestForm.getSelectedFund().getMonthlySavings(), paymentStatus, null, null, clientSystem);

			resquestBody = mapper.writeValueAsString(saveInput);
			logger.info("Lumpsum post save input request date- "+ resquestBody);

			ResponseEntity<String> response =  postcallwithHeaders(getUri(SAVE_POST_LUMPSUM_PURCHASE_SCHEMES), new HttpEntity<String>(resquestBody,generateHttpHeaders((folioStatus.getTransNo()==null?"":folioStatus.getFolioNumber())+"|"+folioStatus.getTransNo()+"|"+paymentStatus+"|"+mfInvestForm.getSelectedFund().getMonthlySavings())));
			logger.info("Response post save scheme lumpsum details save from AMC- "+ response.getBody());

			schemePostOutput = mapper.readValue(response.getBody(), SavePostPurchaseMultiRequestOutput.class);
			logger.info("Birla save post lumpsum schemes processed successfully");
		}catch(Exception e){
			logger.error("Unable to SavePostPurchaseMultiRequest for folio number- "+ folioStatus.getFolioNumber());
			logger.error(e.getMessage());
			e.printStackTrace();
			schemePostOutput=null;
		}

		logger.info("Save POST LUMPSUM schemes from Aditya - complete");
		return schemePostOutput;
	}



	public static void testMethod(){
		BirlaConnectorsImpl n = new BirlaConnectorsImpl();
//		ResponseEntity<String> res1 = null;
		String inputString = "";

		//			inputString="ARN-141396";
		inputString="CQOPS7539C|";
		//			inputString="23|KOLKATA";
		//			inputString="1017634435|";				
		//			inputString="'C|";
		//			inputString="ALL";
		//			inputString="1017634435|1017634435|";		//pre sip
		//			inputString="1017634438|28088";				//post  sip

		//			inputString="CKOPK7504F|848709615072";	//madhu
		//			inputString = "CKOPK7504F|";
		//			inputString = "AFFPH1631C|";			//asgar
		//			inputString="AKQPH7781M|601446739568"; //Sudeshna
		//			inputString="ALL";

		//			inputString = "1017634456|1017634456|2000";
		//			inputString = "1017634456|28235";

		//			inputString = "SBIN0001747";


		//			IR
		//			inputString = "1037558756";


		inputString="5000|1017634477";		//lumpsum
		inputString="1326|1017634477|Y|7000";
		//			inputString="AKQPH7781M|601446739568";

		LocalDateTime date = LocalDateTime.now(ZoneOffset.ofHoursMinutes(5, 30));
		System.out.println(n.getCheckSum(date.format(formatter1), inputString));
		System.out.println(date.format(formatter2));

		System.out.println(date.toInstant(ZoneOffset.ofHoursMinutes(5, 30)).toEpochMilli());

		StringBuffer b = new StringBuffer();
		b.append("\\");
		b.append("/Date(1538714507231)\\/");

		System.out.println(b.toString());
//		String text = "{\"ReturnCode\":1,\"ReturnMsg\":\"Record retrieved successfully\",\"UDP\":null,\"lstSIPBankDet\":[{\"AddedDate\":null,\"Address\":\"6D,NORTHERN.AVENUE,KOLKATA700037\",\"Bank_Name\":\"STATE BANK OF INDIA\",\"Bank_WebSite\":\"http://www.onlinesbi.com/\",\"BranchDetails\":null,\"Branch_Name\":\"PAIKAPARA\",\"Centre\":\"KOLKATA\",\"Contact_Details\":null,\"District\":null,\"IFSC_Code\":\"SBIN0001747\",\"IsActive\":null,\"MICR_Code\":null,\"SrNo\":23,\"State\":\"Y\"}]}";


		/*
	GetBankDetailsByIFSCOutput o = null;
	ObjectMapper mapper = new ObjectMapper();

	try {
		o = mapper.readValue(text, GetBankDetailsByIFSCOutput.class);
		System.out.println((o.getLstSIPBankDet().get(0)).getBank_Name());
	} catch (JsonParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (JsonMappingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/

		//	n.getBankDetailsByIFSC("asdf32435", null);
	}


	/*
	public static void main(String[] args) throws ParseException{
		BirlaConnectorsImpl.testMethod();
		
	}*/
	


}
