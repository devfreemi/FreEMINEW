package com.freemi.services.partners.Impl;

//import static org.assertj.core.api.Assertions.in;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import com.freemi.common.util.CommonConstants;
import com.freemi.entity.birla.NewFolioCreateOutput;
import com.freemi.entity.birla.NewFolioCreationInput;
import com.freemi.entity.birla.SavePostPurchaseMultiRequestInput;
import com.freemi.entity.birla.SavePostSIPMultipleSchemesInput;
import com.freemi.entity.birla.SavePrePurchaseMultiRequestInput;
import com.freemi.entity.birla.SavePreSIPMultipleSchemesInput;
import com.freemi.entity.birla.ValidateAadhaarOTPOutput;
import com.freemi.entity.birla.NewFolioCreationInput.Request;
import com.freemi.entity.birla.SavePostPurchaseMultiRequestInput.PostSaveJson;
import com.freemi.entity.birla.SavePostSIPMultipleSchemesInput.PostSIPMultipleSchemes;
import com.freemi.entity.birla.SavePreSIPMultipleSchemesInput.PreSIPMultipleSchemes;
import com.freemi.entity.birla.SavePreSIPMultipleSchemesInput.PreSIPMultipleSchemes.SchemeDetails;
import com.freemi.entity.database.UserBankDetails;
import com.freemi.entity.general.ClientSystemDetails;
import com.freemi.entity.investment.FolioCreationStatus;
import com.freemi.entity.investment.MFInvestForm;

public class BirlaBeansMapper {

	static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");


	/**
	 * Birla has its own specific set of JSON. Hence mapping the form data to Birla specific details 
	 * @param mfInvestForm
	 */
	public static NewFolioCreationInput toNewFolioCreationBean(MFInvestForm mfInvestForm, ValidateAadhaarOTPOutput aadhaarDetails, UserBankDetails birlaBankDetails ){

		DateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		DateFormat targetFormat = new SimpleDateFormat("dd-MMM-yyyy");

		NewFolioCreationInput newFolioInput = new NewFolioCreationInput();

		NewFolioCreationInput.Request folioData = new Request();


		folioData.setUserId(BirlaConnectorsImpl.USERID);
		folioData.setPassword(BirlaConnectorsImpl.PASSWORD);

		/*Personal Details*/
		folioData.setGender(aadhaarDetails!=null?aadhaarDetails.getEkycAdharDetailsObject().getGender():"As per Aadhar");

//		folioData.setId(mfInvestForm.getPanValidationStatus().getIsKYCVerified().equalsIgnoreCase("N")?mfInvestForm.getAadhaarbaseKYCrefId():"");	//to do
		folioData.setId(mfInvestForm.getAadhaarbaseKYCrefId());	//to do
		
		folioData.setAddress1(aadhaarDetails!=null?(aadhaarDetails.getEkycAdharDetailsObject().getHouse() + " "+ aadhaarDetails.getEkycAdharDetailsObject().getStreet()):"As per Aadhar");
		folioData.setAddress2(aadhaarDetails!=null?aadhaarDetails.getEkycAdharDetailsObject().getLocality():"As per Aadhar");
		folioData.setCity(aadhaarDetails!=null?aadhaarDetails.getEkycAdharDetailsObject().getCity():"As per Aadhar");
		folioData.setPinCode(aadhaarDetails!=null?aadhaarDetails.getEkycAdharDetailsObject().getPinCode():"As per Aadhar");
		folioData.setState(aadhaarDetails!=null?aadhaarDetails.getEkycAdharDetailsObject().getState():"");
		folioData.setCountry("104");	// Considering only Indian customers now 
		folioData.setBcnt_Flag("N");
		//		folioData.setDob((LocalDate.parse(mfInvestForm.getInvDOB(), DateTimeFormatter.ofPattern("yyyy-mm-dd"))).format(formatter));

		Date date2;
		if(mfInvestForm.getInvDOB()!=""){

			try {
				date2 = originalFormat.parse(mfInvestForm.getInvDOB());
				folioData.setDob(targetFormat.format(date2));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else{
			folioData.setDob("");
		}


		folioData.setApplicantName(mfInvestForm.getInvName());
		folioData.setValid_PAN("Y");
		folioData.setPan_card(mfInvestForm.getPAN().toUpperCase());
		folioData.setStatusCode(CommonConstants.STATUS_CODE_RESIDENT_INDIVIDUAL);		//Considering only Resident Individual now
		folioData.setHolding_Nature(CommonConstants.HOLDING_NATURE_NEW_SINGFLE_PAN);	//For new investor in SIngla PAN
		folioData.setEmail(mfInvestForm.getEmail());
		folioData.setMobile_No(mfInvestForm.getMobile());
		folioData.setBroker_Code(CommonConstants.BROKER_CODE);
		folioData.setGross_Annual_Income(getAnnualIncomeSlab(mfInvestForm.getAnnualIncome()));											//todo
		folioData.setOccupation_code(getOccupationCode(mfInvestForm.getOccupation()));												//todo

		folioData.setPep_Flag("NA");														// -- todo
		folioData.setUbo_DECLARATION("Y");												//T&C must be checked
		folioData.setCountry_of_birth("104");											// to check
		folioData.setCountry_of_citizen((folioData.getBcnt_Flag().equalsIgnoreCase("Y") || !folioData.getStatusCode().equalsIgnoreCase("01"))?"":"104"); // tocheck
		folioData.setPlaceOfBirth(mfInvestForm.getPlaceOfBirth()!=null?mfInvestForm.getPlaceOfBirth():"NA");


		/*if(mfInvestForm.getAadhaarVerifyStatusCode().equalsIgnoreCase("3")){
			System.out.println("eKYC already processed. Only investment not yet done.");
			folioData.setEkyc("N");
		}else{
			folioData.setEkyc(mfInvestForm.getPanValidationStatus().getIsKYCVerified().equalsIgnoreCase("Y")?"N":"Y");
		}*/
		
		folioData.setEkyc(mfInvestForm.getPanValidationStatus().getIsKYCVerified().equalsIgnoreCase("Y")?"N":"Y");
		
		
		folioData.setAadhaarNo(mfInvestForm.getPanValidationStatus().getIsKYCVerified().equalsIgnoreCase("Y")?"":mfInvestForm.getAadhaar());
		folioData.setFatherOrSpouse("NA");												//todo
		folioData.setMaritalStatus(mfInvestForm.getMaritalStatus()!=""?mfInvestForm.getMaritalStatus():"S");													//todo
		folioData.setApplicantPrefix(mfInvestForm.getGender().equalsIgnoreCase("M")?"Mr":"Ms");												//todo
		folioData.setMaidenPrefix(mfInvestForm.getGender().equalsIgnoreCase("M")?"Mr":"Ms");													//todo
		folioData.setMaidenName(mfInvestForm.getInvName());													//todo
		folioData.setMotherPrefix("Mrs");												//fixed to single value
		folioData.setMotherName("NA");													//todo
		folioData.setFatherPrefix("Mr");												//fixed to single value
		folioData.setDistrict(mfInvestForm.getDistrict()!=""?mfInvestForm.getDistrict():"NA");														//todo


		//Nominee Details

		if(mfInvestForm.getNominee().getIsNominate().equalsIgnoreCase("Y")){

			//Nominee Name
			folioData.setNominee_Name(mfInvestForm.getNominee().getNomineeName()!=null?mfInvestForm.getNominee().getNomineeName():"");
			//		folioData.setNominee_dob(mfInvestForm.getNominee().getNomineeDOB()!=null?(LocalDate.parse(mfInvestForm.getNominee().getNomineeDOB(), DateTimeFormatter.ofPattern("yyyy-mm-dd"))).format(formatter):"");

			if(mfInvestForm.getNominee().getNomineeName()!=null){
				folioData.setNominee_Relationship(mfInvestForm.getNominee().getNomineeRelation());
			}
			
			
			if(mfInvestForm.getNominee().getNomineeDOB()!=""){

				try {
					date2 = originalFormat.parse(mfInvestForm.getNominee().getNomineeDOB());
					folioData.setNominee_dob(targetFormat.format(date2));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}else{
				folioData.setNominee_dob("");
			}

			folioData.setNominee_Relationship(mfInvestForm.getNominee().getNomineeRelation()!=null?mfInvestForm.getNominee().getNomineeRelation():"");

		}


		folioData.setAddressType("02");

		
		// Bank details
		folioData.setBank_Name(birlaBankDetails.getBankName()!=null?birlaBankDetails.getBankName():"");
		folioData.setBranch_Name(birlaBankDetails.getBankBranch()!=null?birlaBankDetails.getBankBranch():"");
		folioData.setBranch_Address(birlaBankDetails.getBankAddress()!=null?birlaBankDetails.getBankAddress():"");
		//folioData.setBranch_Pin_Code("");

		folioData.setBranch_City(birlaBankDetails.getBankCity());
		folioData.setAccount_Type(getAccountTypeForBirla(mfInvestForm.getBankDetails().getAccountType(), "01"));
		folioData.setAccount_No(mfInvestForm.getBankDetails().getAccountNumber());
		folioData.setIfsc_Code(birlaBankDetails.getIfscCode());
		
		

		newFolioInput.setRequestObject(folioData);
		return newFolioInput;

	}

	public static FolioCreationStatus FromFolioCreationOutput(NewFolioCreateOutput output){
		FolioCreationStatus folioCrationResponse = new FolioCreationStatus();
		//		NewFolioCreationOutput output = new NewFolioCreationOutput();

		//		try {
		//			output = mapper.readValue(responseEntity.getBody(), NewFolioCreationOutput.class);
		if(output.getNewFolioCreationResult().getReturnCode().equals("1")){
			folioCrationResponse.setTransactionSuccessful(true);
			folioCrationResponse.setMessage(output.getNewFolioCreationResult().getReturnMsg());
			folioCrationResponse.setFolioNumber(output.getNewFolioCreationResult().getFolioNo());
			folioCrationResponse.setRefNumber(output.getNewFolioCreationResult().getRefNo());
			folioCrationResponse.setReturnCode(output.getNewFolioCreationResult().getReturnCode());
		}else{
			folioCrationResponse.setTransactionSuccessful(false);
			folioCrationResponse.setMessage(output.getNewFolioCreationResult().getReturnMsg());
			folioCrationResponse.setReturnCode(output.getNewFolioCreationResult().getReturnCode());
		}

		/*} catch (IOException e) {
			System.out.println("Unable to convert Birla New Folio Creation output response - "+ e.getMessage());
		}*/

		return folioCrationResponse;

	}

	public static SavePreSIPMultipleSchemesInput ToSavePreSIPSchmesInput(String primeFolio,String activeFolio, MFInvestForm investForm, UserBankDetails bankDetails ){

		SavePreSIPMultipleSchemesInput schemeInput = new SavePreSIPMultipleSchemesInput();
		SavePreSIPMultipleSchemesInput.PreSIPMultipleSchemes schemesObj = new PreSIPMultipleSchemes();
		SavePreSIPMultipleSchemesInput.PreSIPMultipleSchemes.SchemeDetails schemeDetails = new SchemeDetails();
		ArrayList<SavePreSIPMultipleSchemesInput.PreSIPMultipleSchemes.SchemeDetails> schemeDetailsList = new ArrayList<SavePreSIPMultipleSchemesInput.PreSIPMultipleSchemes.SchemeDetails>();
		DateFormat mergedFormat = new SimpleDateFormat("MM-yyyy");
		DateFormat targetFormat = new SimpleDateFormat("MMM-yyyy");

		schemesObj.setUserId(BirlaConnectorsImpl.USERID);
		schemesObj.setPassword(BirlaConnectorsImpl.PASSWORD);
		schemesObj.setBrokerCode(CommonConstants.BROKER_CODE);
		schemesObj.setEuin(CommonConstants.EUIN_CODE);

		schemesObj.setPrimeFolioNo(primeFolio);
		schemesObj.setActiveFolioNo(activeFolio);
//		schemesObj.setIsFirstInstallment(investForm.getFirstInstallment()==""?"N":"Y");
		
//		Regarding first installment. Need to by dynamic		
		schemesObj.setIsFirstInstallment("Y");
		schemesObj.setTotalFistInstallmentAmount(investForm.getSelectedFund().getMonthlySavings());
		schemesObj.setPaymentType("NETBANK");
		schemesObj.setPaymentBankName("Debit Card");		//to do
		schemesObj.setPaymentgateway("TP");
		
		
		schemesObj.setBankName(bankDetails.getBankName()!=null? bankDetails.getBankName().split("\\|")[1]:"");
		schemesObj.setBankCity(bankDetails.getBankCity());
		schemesObj.setBankBranch(bankDetails.getBankBranch().split("\\|")[1]);
		schemesObj.setIfscCode(bankDetails.getIfscCode());
		schemesObj.setAcctNumber(investForm.getBankDetails().getAccountNumber());
		schemesObj.setAcctType(investForm.getBankDetails().getAccountType());
		schemesObj.setUsPerson("NA");
		schemesObj.setOtmOrBiller("B");

		
		schemeDetails.setFirstInstallmentAmount(investForm.getSelectedFund().getMonthlySavings());
		schemeDetails.setSchemeCode(investForm.getSelectedFund().getSchemeCode());
		schemeDetails.setSchemeOption(investForm.getSelectedFund().getSchemeOption());
		schemeDetails.setSipInvAmt(investForm.getSelectedFund().getMonthlySavings());
		schemeDetails.setFrequency(investForm.getMfInvestDates().getInvFrequency());
		schemeDetails.setInvestmentDate(investForm.getMfInvestDates().getMonthlyInvestTriggerDate());
		schemeDetails.setIsCSIP("N");


		Date date2;

		try {
			date2 = mergedFormat.parse(investForm.getMfInvestDates().getSipPeriodFromMonth()+"-"+investForm.getMfInvestDates().getSipPeriodFromYear());
			//			schemeDetails.setDob(targetFormat.format(date2));
			schemeDetails.setSipFromPeriod(targetFormat.format(date2));

			date2 = mergedFormat.parse(investForm.getMfInvestDates().getSipPeriodToMonth()+"-"+investForm.getMfInvestDates().getSipPeriodToYear());
			schemeDetails.setSipToPeriod(targetFormat.format(date2));
		} catch (ParseException e) {
			e.printStackTrace();
		}



		schemeDetailsList.add(schemeDetails);
		schemesObj.setSchemeDetails(schemeDetailsList);
		schemeInput.setPreSIPMultipleSchemesObject(schemesObj);



		return schemeInput;
	}
	
	
	public static SavePostSIPMultipleSchemesInput ToSavepostSIPSchmesInput(FolioCreationStatus folioStatus ,String amount, String paymentStatus, ClientSystemDetails clientSystemDetails){
		
		SavePostSIPMultipleSchemesInput saveInput = new SavePostSIPMultipleSchemesInput();
		SavePostSIPMultipleSchemesInput.PostSIPMultipleSchemes schemes = new PostSIPMultipleSchemes();
		
		schemes.setUserId(BirlaConnectorsImpl.USERID);
		schemes.setPassword(BirlaConnectorsImpl.PASSWORD);
		schemes.setFolioNo(folioStatus.getFolioNumber());
		schemes.setPaymentStatus(paymentStatus);
		schemes.setTrans_No(folioStatus.getTransNo()!=null?folioStatus.getTransNo():"");
		schemes.setAmount(amount);
		schemes.setClientIpAddress(clientSystemDetails.getClientIpv4Address());
		saveInput.setPostSIPMultipleSchemesObject(schemes);
		return saveInput;
	}
	
	
	public static Object ToSavePreLumpsumSchmesInput(String primeFolio,String activeFolio, MFInvestForm investForm, UserBankDetails bankDetails,ClientSystemDetails clientSystem ){
		
		String epochTime = Long.toString(LocalDateTime.now(ZoneOffset.ofHoursMinutes(5, 30)).toInstant(ZoneOffset.ofHoursMinutes(5, 30)).toEpochMilli());
		
		
		SavePrePurchaseMultiRequestInput lumpsum = new SavePrePurchaseMultiRequestInput();
		SavePrePurchaseMultiRequestInput.LumpsumScheme input = new SavePrePurchaseMultiRequestInput.LumpsumScheme();
		SavePrePurchaseMultiRequestInput.LumpsumScheme.SchemeDetails scheme = new com.freemi.entity.birla.SavePrePurchaseMultiRequestInput.LumpsumScheme.SchemeDetails();
		ArrayList<SavePrePurchaseMultiRequestInput.LumpsumScheme.SchemeDetails> schemeDetails = new ArrayList<com.freemi.entity.birla.SavePrePurchaseMultiRequestInput.LumpsumScheme.SchemeDetails>();
		
		input.setUserId(BirlaConnectorsImpl.USERID);
		input.setPassword(BirlaConnectorsImpl.PASSWORD);
		input.setFolioNo(primeFolio);
		input.setPrimeFolioNo(primeFolio);
		input.setPaymentBankName("DEBIT");		//to do
		input.setPaymentType("DEBIT");
		input.setPaymentGateway("BD");
		input.setTotalAmount(investForm.getSelectedFund().getMonthlySavings());		//to do
		input.setBrokerCode(CommonConstants.BROKER_CODE);
		input.setEuin(CommonConstants.EUIN_CODE);
		input.setMerchantCode("N.A");
		
		scheme.setSchemeCode(investForm.getSelectedFund().getSchemeCode());
		scheme.setSchemeOption(investForm.getSelectedFund().getSchemeOption());
		scheme.setAmount(investForm.getSelectedFund().getMonthlySavings());			//to do
		scheme.setTransType("P");
		
		input.setSip_BankName(bankDetails.getBankName()!=null? bankDetails.getBankName().split("\\|")[1]:"");
		input.setSip_BankCity(bankDetails.getBankCity());
		input.setSip_BankBranch(bankDetails.getBankBranch().split("\\|")[1]);
		input.setSip_IFSCCode(bankDetails.getIfscCode());
		input.setSip_AcctNumber(investForm.getBankDetails().getAccountNumber());
		input.setSip_AcctType(investForm.getBankDetails().getAccountType());
		input.setSip_IsFirstInstallment("N");
		input.setSip_IsDirect("N");
		input.setSip_EUINdeclaration(true);
		input.setSkipValidation(false);
		input.setAdviceRefId(0);
		input.setLeadId(0);
		input.setSipAdviceId(0);
		input.setClientIpAddress(clientSystem.getClientIpv4Address());
		input.setModuleName("LPurchaseService");
		input.setMethodName("SavePrePurchaseMultiRequest");
		
		String etime = "\\/Date("+epochTime+")\\/";
		input.setRequestTime(etime);
		input.setResponseTime(etime);
		
		/*StringBuffer etime = new StringBuffer();
		etime.append("\\");
		etime.append("/Date(");
		etime.append(epochTime).append(")").append("\\").append("/");
		input.setRequestTime(etime.toString());
		input.setResponseTime(etime.toString());*/
		
		schemeDetails.add(scheme);
		input.setSchemeDetails(schemeDetails);
		
		lumpsum.setLumpsumscheme(input);
		
		return lumpsum;
	}
	
	
	public static Object ToSavePostLumpsumSchmesInput(FolioCreationStatus folioStatus ,String amount, String paymentStatus, MFInvestForm investForm, UserBankDetails bankDetails,ClientSystemDetails clientSystem ){
		
		SavePostPurchaseMultiRequestInput lumpsumPostInput = new SavePostPurchaseMultiRequestInput();
		SavePostPurchaseMultiRequestInput.PostSaveJson jsonBody = new PostSaveJson();
		
		jsonBody.setUserId(BirlaConnectorsImpl.USERID);
		jsonBody.setPassword(BirlaConnectorsImpl.PASSWORD);
		jsonBody.setFilterFor(folioStatus.getFolioNumber());
		jsonBody.setPaymentStatus(paymentStatus);
		jsonBody.setAmount(amount);
		jsonBody.setClientIpAddress(clientSystem.getClientIpv4Address());
		
		lumpsumPostInput.setPostSaveJson(jsonBody);
		
		return lumpsumPostInput;
	}


	protected static String getAccountTypeForBirla(String bankType, String TaxStatus){
		Map<String, String> accountTypes = new TreeMap<String,String>();
		accountTypes.put(CommonConstants.BANK_ACC_TYPE_SAVINGS, "SB");
		accountTypes.put(CommonConstants.BANK_ACC_TYPE_CURRENT, "CA");
		accountTypes.put(CommonConstants.BANK_ACC_TYPE_OVER_DRAFT, "OD");

		return accountTypes.get(bankType);
	}



	private static String getOccupationCode(String selectedOccupation) {

		Map<String, String> occupationList = new TreeMap<String,String>();
		occupationList.put("Business","01");
		occupationList.put("Bureaucrat","09");
		occupationList.put("Doctor","10");
		occupationList.put("Housewife","06");
		occupationList.put("Others","08");
		occupationList.put("Private Sector Service","41");
		occupationList.put("Professional","03");
		occupationList.put("Public Sector / Government Service","42");
		occupationList.put("Retired","05");
		occupationList.put("Service","02");
		occupationList.put("Student","07");
		occupationList.put("Unknown / Not Applicable","99");
		return occupationList.get(selectedOccupation);
	}

	private static String getAnnualIncomeSlab(String selectedIncomeSlab) {

		Map<String, String> incomeSlab = new TreeMap<String,String>();
		incomeSlab.put("Below 1 Lakh", "31");
		incomeSlab.put("> 1 <=5 Lacs", "32");
		incomeSlab.put(">5 <=10 Lacs", "33");
		incomeSlab.put(">10 <= 25 Lacs", "34");
		incomeSlab.put("> 25 Lacs < = 1 Crore", "35");
		incomeSlab.put("Above 1 Crore", "36");
		return incomeSlab.get(selectedIncomeSlab);
	}
}
