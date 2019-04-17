package com.freemi.services.partners.Impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.freemi.common.util.CommonConstants;
import com.freemi.entity.bse.BseAOFUploadRequest;
import com.freemi.entity.bse.BseAOFUploadResponse;
import com.freemi.entity.bse.BseApiResponse;
import com.freemi.entity.bse.BseEMandateRegistration;
import com.freemi.entity.bse.BseFatcaForm;
import com.freemi.entity.bse.BseOrderEntry;
import com.freemi.entity.bse.BseOrderPaymentResponse;
import com.freemi.entity.bse.BsePanStatusResponse;
import com.freemi.entity.bse.BsePaymentStatus;
import com.freemi.entity.bse.BseRegistrationMFD;
import com.freemi.entity.bse.BseSipOrderEntry;
import com.freemi.entity.bse.BseXipISipOrderEntry;
import com.freemi.entity.database.UserBankDetails;
import com.freemi.entity.investment.BseMFInvestForm;
import com.freemi.entity.investment.BseOrderEntryResponse;
import com.freemi.entity.investment.SelectMFFund;

public class BseBeansMapper {

	private static final Logger logger = LogManager.getLogger(BseBeansMapper.class);



	public static BseRegistrationMFD  InvestmentFormToBseBeans(BseMFInvestForm registrationForm){

		BseRegistrationMFD clientFregirationForm = new  BseRegistrationMFD();

		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-mm-dd");
		SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("dd/mm/yyyy");

		clientFregirationForm.setClientCode(registrationForm.getClientID());
		clientFregirationForm.setClientHolding(registrationForm.getHoldingMode());
		clientFregirationForm.setClientTaxstatu(registrationForm.getTaxStatus());
		clientFregirationForm.setClientOccupationcod(registrationForm.getOccupation());
		clientFregirationForm.setClientAppname1(registrationForm.getInvName());

		//		Converting date format to BSE specific format dd/mm/yyyy
		/*try {
			
			Date date1 = simpleDateFormat1.parse(registrationForm.getInvDOB());
			String bseFormatDob = simpleDateFormat2.format(date1);
			clientFregirationForm.setClientDob(bseFormatDob);
		} catch (ParseException e) {
			logger.error("InvestmentFormToBseBeans(): failed to convert date: ",e);
		}	*/
		
		System.out.println("DOB- "+ registrationForm.getInvDOB());
		clientFregirationForm.setClientDob(registrationForm.getInvDOB());
		
		clientFregirationForm.setClientGender(registrationForm.getGender());
		clientFregirationForm.setClientPan(registrationForm.getPan1());
		clientFregirationForm.setClientType("P");			//P- Physical , D - Demat								// todo
		clientFregirationForm.setClientAcctype1(registrationForm.getBankDetails().getAccountType());		// todo - convert to Sb from map
		clientFregirationForm.setClientAccno1(registrationForm.getBankDetails().getAccountNumber());
		//		clientFregirationForm.setClientMicrno1("MICR");								//todo
		clientFregirationForm.setClientNeftIfsccode1(registrationForm.getBankDetails().getIfscCode().toUpperCase());						//todo
		clientFregirationForm.setDefaultBankFlag("Y");
		clientFregirationForm.setClientAdd1(registrationForm.getAddressDetails().getAddress1());
		clientFregirationForm.setClientCity(registrationForm.getAddressDetails().getCity());
		clientFregirationForm.setClientState(registrationForm.getAddressDetails().getState());	//todo - convert to code from map
		clientFregirationForm.setClientPincode(registrationForm.getAddressDetails().getPinCode());
		clientFregirationForm.setClientCountry(registrationForm.getAddressDetails().getCountry());
		clientFregirationForm.setClientEmail(registrationForm.getEmail());
		clientFregirationForm.setClientCommmode("E");			//todo // E- EMail, M - Mobile
		clientFregirationForm.setClientDivpaymode(registrationForm.getDividendPayMode());	//todo convert to code
		//		clientFregirationForm.setClientDivpaymode("02");	//todo convert to code
		clientFregirationForm.setCm_mobile(registrationForm.getMobile());
		clientFregirationForm.setClientCountry("INDIA");


		//		Joint Holder
		if(registrationForm.getHoldingMode().equals("JO") || registrationForm.getHoldingMode().equals("AS")  ){
			clientFregirationForm.setClientPan2(registrationForm.getPan2());
			clientFregirationForm.setClientAppname2(registrationForm.getApplicant2());
		}

		//		Nominee
		clientFregirationForm.setClientNominee(registrationForm.getNominee().getNomineeName());
		clientFregirationForm.setClientNomineeRelation(registrationForm.getNominee().getNomineeRelation());




		return clientFregirationForm;
	}

	public static BseFatcaForm  InvestmentFormToBseFATCABeans(BseMFInvestForm registrationForm){
		BseFatcaForm fatcaForm = new BseFatcaForm();

		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-mm-dd");
		SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("dd/mm/yyyy");

		fatcaForm.setPAN_RP(registrationForm.getPan1());
		fatcaForm.setINV_NAME(registrationForm.getInvName());

		//		Converting date format to BSE specific format dd/mm/yyyy
		/*try {
			Date date1 = simpleDateFormat1.parse(registrationForm.getInvDOB());
			String bseFormatDob = simpleDateFormat2.format(date1);
			fatcaForm.setDOB(bseFormatDob);
		} catch (ParseException e) {
			logger.error("InvestmentFormToBseFATCABeans(): failed to convert date: ",e);
		}*/
		System.out.println("DOB for fatca- "+registrationForm.getInvDOB() );
		fatcaForm.setDOB(registrationForm.getInvDOB());

		fatcaForm.setFR_NAME(registrationForm.getFatcaDetails().getFatherName());
		fatcaForm.setSP_NAME(registrationForm.getFatcaDetails().getSpouseName());
		fatcaForm.setTAX_STATUS(registrationForm.getTaxStatus());
		fatcaForm.setDATA_SRC(registrationForm.getFatcaDetails().getDataSource());
		fatcaForm.setADDR_TYPE(registrationForm.getFatcaDetails().getAddressType());
		fatcaForm.setPO_BIR_INC(registrationForm.getFatcaDetails().getPlaceOfBirth());
		fatcaForm.setCO_BIR_INC(registrationForm.getFatcaDetails().getCountryOfBirth());

		//		Fixed to INDIA
		fatcaForm.setTAX_RES1("IN");
		fatcaForm.setTPIN1(registrationForm.getPan1());
		fatcaForm.setID1_TYPE(registrationForm.getFatcaDetails().getIdentificationDocType());
		fatcaForm.setSRCE_WEALT(registrationForm.getFatcaDetails().getWealthSource());
		fatcaForm.setINC_SLAB(registrationForm.getFatcaDetails().getIncomeSlab());

		try{
			fatcaForm.setNET_WORTH(registrationForm.getFatcaDetails().getNetWordth()!=null?Double.toString(registrationForm.getFatcaDetails().getNetWordth()):"");
		}catch(Exception e){
			logger.error("InvestmentFormToBseFATCABeans(): Unable to convert net worth data.");
		}
		try {
			if(registrationForm.getFatcaDetails().getDateOfNetworth()!=null){
				Date date1 = simpleDateFormat1.parse(registrationForm.getInvDOB());
				String bseFormatDob = simpleDateFormat2.format(date1);
				fatcaForm.setNW_DATE(bseFormatDob);
			}
		} catch (ParseException e) {
			logger.error("InvestmentFormToBseFATCABeans(): failed to convert Networth date: ",e);
		}

		fatcaForm.setPEP_FLAG(registrationForm.getFatcaDetails().getPoliticalExposedPerson());
		fatcaForm.setOCC_CODE(registrationForm.getOccupation());
		fatcaForm.setOCC_TYPE(registrationForm.getFatcaDetails().getOccupationType());

		fatcaForm.setEXCH_NAME(registrationForm.getFatcaDetails().getExchangeName());

		fatcaForm.setUBO_APPL(registrationForm.getFatcaDetails().getUboApplicable());
		fatcaForm.setUBO_DF("N");

		fatcaForm.setNEW_CHANGE(registrationForm.getFatcaDetails().getNewUpdateIndicator());
		fatcaForm.setLOG_NAME(registrationForm.getPan1());


		return  fatcaForm;
	}


	public static BseOrderEntry transactionOrderToBseBeans(SelectMFFund fundDetails, String requestNumber){
		logger.info("transactionOrderToBseBeans()- Begin mapping");
		BseOrderEntry bseOrderForm = new BseOrderEntry();

		bseOrderForm.setUserID(CommonConstants.BSE_USER_ID);
		bseOrderForm.setMemberId(CommonConstants.BSE_MEMBER_ID);
		bseOrderForm.setClientCode(fundDetails.getClientID());		// Customer's client ID

		if(fundDetails.getTransactionType().equals("PURCHASE")){	//ADD For MODIFITATION late
			bseOrderForm.setTransCode("NEW");
			//			Determinining whether the schemecode to be of L1 category
			if(fundDetails.getInvestAmount()>199999){
				logger.info("Since the amount is above, marking the transaction as L1 category.. Proceeding by changing fundname to - "+ fundDetails.getSchemeCode()+"-L1");
				bseOrderForm.setSchemeCd(fundDetails.getSchemeCode()+"-L1");
				
			}else{
				bseOrderForm.setSchemeCd(fundDetails.getSchemeCode());
			}

			bseOrderForm.setBuySell("P");
			bseOrderForm.setRemarks("Purchase Request");
			bseOrderForm.setBuySellType("FRESH");

		}else if(fundDetails.getTransactionType().equals("REDEEM")){
			bseOrderForm.setTransCode(bseOrderForm.getFolioNo());
			bseOrderForm.setSchemeCd(fundDetails.getSchemeCode());
			bseOrderForm.setSchemeCd(fundDetails.getSchemeCode());
			bseOrderForm.setBuySell("R");
			bseOrderForm.setRemarks("Redemption Request");

			bseOrderForm.setBuySellType("FRESH");

		}else if(fundDetails.getTransactionType().equals("MOD")){
			bseOrderForm.setTransCode("MOD");
			bseOrderForm.setBuySell("P");
			bseOrderForm.setSchemeCd(fundDetails.getSchemeCode());
			bseOrderForm.setRemarks("Order modification Request");

			bseOrderForm.setBuySellType("FRESH");

		} else{
			bseOrderForm.setTransCode("CXL");
			bseOrderForm.setBuySell("P");
			bseOrderForm.setSchemeCd(fundDetails.getSchemeCode());
			bseOrderForm.setRemarks("Order cancel Request");
			bseOrderForm.setOrderId(fundDetails.getOrderNo());
			bseOrderForm.setBuySellType("FRESH");
		}

		bseOrderForm.setTransNo(requestNumber);

		bseOrderForm.setDPTxn("P");
		bseOrderForm.setOrderVal(fundDetails.getInvestAmount());	//to do for all redeem 
		bseOrderForm.setQty("");
		bseOrderForm.setAllRedeem("N");		// to do
		bseOrderForm.setFolioNo(fundDetails.getPortfolio());

		bseOrderForm.setKYCStatus("Y");
		bseOrderForm.setRefNo(fundDetails.getBseRefNo());
//		bseOrderForm.setEUIN(CommonConstants.EUIN_CODE);
		bseOrderForm.setEUINVal("N");
		bseOrderForm.setMinRedeem("Y");	//todo
		bseOrderForm.setDPC("Y");		//todo

		logger.info("transactionOrderToBseBeans()- mapping complete..");
		return bseOrderForm;
	}


	public static BseOrderEntry redeeemOrderToBseBeans(SelectMFFund fundDetails, String requestNumber){
		logger.info("redeeemOrderToBseBeans()- Redeem mapping begin");
		BseOrderEntry bseOrderForm = new BseOrderEntry();

		bseOrderForm.setUserID(CommonConstants.BSE_USER_ID);
		bseOrderForm.setMemberId(CommonConstants.BSE_MEMBER_ID);
		bseOrderForm.setClientCode(fundDetails.getClientID());		// Customer's client ID
		
		if(fundDetails.getTransactionType().equals("REDEEM")){
			bseOrderForm.setTransCode("NEW");
			bseOrderForm.setSchemeCd(fundDetails.getSchemeCode());
			bseOrderForm.setSchemeCd(fundDetails.getSchemeCode());
			bseOrderForm.setBuySell("R");
			bseOrderForm.setRemarks("Redemption Request");
			bseOrderForm.setBuySellType("FRESH");
		}

		bseOrderForm.setTransNo(requestNumber);

		bseOrderForm.setDPTxn("P");
		bseOrderForm.setOrderVal(fundDetails.getRedeemAmount());	//to do for all redeem 
		bseOrderForm.setQty("");
		bseOrderForm.setAllRedeem("N");		// to do
		bseOrderForm.setFolioNo(fundDetails.getPortfolio());

		bseOrderForm.setKYCStatus("Y");
		bseOrderForm.setRefNo(fundDetails.getBseRefNo());
//		bseOrderForm.setEUIN(CommonConstants.EUIN_CODE);
		bseOrderForm.setEUINVal("N");
		bseOrderForm.setMinRedeem("Y");	//todo
		bseOrderForm.setDPC("Y");		//todo
		logger.info("redeeemOrderToBseBeans()- Redeem mapping complete");
		return bseOrderForm;
	}
	
	public static BseOrderEntryResponse redeeemResponseToBean(BseOrderEntryResponse response,String responseText,String internalRefNo){

		List<String> res = Arrays.asList(responseText.split("\\|"));
		//		BseorderEntryResponse response = new BseorderEntryResponse();

		response.setTransactionType(res.get(0));
		response.setUniqueReferenceNo(res.get(1));
		response.setOrderNoOrSipRegNo(res.get(2));
		/*response.setClientCode(res.get(3));
		response.setUserId(res.get(4));
		response.setMemberId(res.get(5));*/
		response.setUserId(res.get(3));
		response.setMemberId(res.get(4));
		response.setClientCode(res.get(5));
		response.setBsereMarks(res.get(6));
		response.setSuccessFlag(res.get(7));
		response.setIntRefNo(internalRefNo);
		response.setCreatedOn(new Date());
		
		
		return response;
	}


	public static BseSipOrderEntry transactionSIPOrderToBseBeans(SelectMFFund fundDetails, String uniqueReferenceNo){

		BseSipOrderEntry bseOrderForm = new BseSipOrderEntry();

		if(fundDetails.getTransactionType().equals("PURCHASE")){	//ADD For MODIFITATION late
			bseOrderForm.setTransactionCode("NEW");
		}else{
			bseOrderForm.setTransactionCode("CXL");
		}
		bseOrderForm.setUniqueRefNo(uniqueReferenceNo);
		bseOrderForm.setUserID(CommonConstants.BSE_USER_ID);
		bseOrderForm.setMemberCode(CommonConstants.BSE_MEMBER_ID);
		bseOrderForm.setClientCode(fundDetails.getClientID());		// Customer's client ID
		bseOrderForm.setTransMode(CommonConstants.BSE_TRANS_MODE_PHYSICAL);
		bseOrderForm.setStartDate((new SimpleDateFormat("dd/MM/yyyy")).format(fundDetails.getSipStartDate()));
		bseOrderForm.setFrequencyType("MONTHLY");
		bseOrderForm.setFrequencyAllowed("1");
		bseOrderForm.setInstallmentAmount(Double.toString(fundDetails.getInvestAmount()));
		bseOrderForm.setNoOfInstallment(Integer.toString(fundDetails.getNoOfInstallments()));		//to do 5 years by default selected for now 

		if(fundDetails.isPayFirstInstallment()){
			bseOrderForm.setFirstOrderFlag("Y");		//to do for first payment
			bseOrderForm.setOrderVal(Double.toString(fundDetails.getInvestAmount()));	//to do for all redeem 
		}else{
			bseOrderForm.setFirstOrderFlag("N");		//to do for first payment
			bseOrderForm.setOrderVal("");	//to do for all redeem
		}
		bseOrderForm.setSchemeCode(fundDetails.getSchemeCode());
		bseOrderForm.setBuySell("P");
		bseOrderForm.setBuySellType("FRESH");
		bseOrderForm.setDpTxnMode("P");

		bseOrderForm.setQty("1");
		bseOrderForm.setAllRedeem("N");		// to do
		bseOrderForm.setFolioNo(fundDetails.getPortfolio().equalsIgnoreCase("NEW")?"":fundDetails.getPortfolio());
		bseOrderForm.setRemarks("Purchase Request");
		bseOrderForm.setKYCStatus("Y");
		bseOrderForm.setRefNo(fundDetails.getBseRefNo());
		bseOrderForm.setSubBrCode("");
		bseOrderForm.setEuin("");
		bseOrderForm.setEuinVal("N");
		bseOrderForm.setMinRedeem("Y");
		bseOrderForm.setDPC("N");
		bseOrderForm.setIPAdd("");
		bseOrderForm.setPassword("");
		bseOrderForm.setParma1("");
		bseOrderForm.setParam2("");
		bseOrderForm.setParam3("");

		return bseOrderForm;
	}

	public static BseXipISipOrderEntry transactionXSIPISIPOrderToBseBeans(SelectMFFund fundDetails,String uniqueReferenceNo, String mandateId){
		BseXipISipOrderEntry bseOrderForm = new BseXipISipOrderEntry();

		if(fundDetails.getTransactionType().equals("PURCHASE")){	//ADD For MODIFITATION late
			bseOrderForm.setTransactionCode("NEW");
		}else{
			bseOrderForm.setTransactionCode("CXL");
		}

		bseOrderForm.setUniqueRefNo(uniqueReferenceNo);
		bseOrderForm.setUserID(CommonConstants.BSE_USER_ID);
		bseOrderForm.setMemberCode(CommonConstants.BSE_MEMBER_ID);
		bseOrderForm.setClientCode(fundDetails.getClientID());		// Customer's client ID
		bseOrderForm.setTransMode(CommonConstants.BSE_TRANS_MODE_PHYSICAL);
		bseOrderForm.setStartDate((new SimpleDateFormat("dd/MM/yyyy")).format(fundDetails.getSipStartDate()));
		bseOrderForm.setFrequencyType("MONTHLY");
		bseOrderForm.setFrequencyAllowed("1");
		bseOrderForm.setInstallmentAmount(Double.toString(fundDetails.getInvestAmount()));
		bseOrderForm.setNoOfInstallment(Integer.toString(fundDetails.getNoOfInstallments()));		//to do 5 years by default selected for now 

		if(fundDetails.isPayFirstInstallment()){
			bseOrderForm.setFirstOrderFlag("Y");		//to do for first payment
			bseOrderForm.setOrderVal(Double.toString(fundDetails.getInvestAmount()));	//to do for all redeem 
		}else{
			bseOrderForm.setFirstOrderFlag(fundDetails.isPayFirstInstallment()?"Y":"N");		//to do for first payment
			bseOrderForm.setOrderVal("");	//to do for all redeem
		}
		bseOrderForm.setSchemeCode(fundDetails.getSchemeCode());
		bseOrderForm.setBuySell("P");
		bseOrderForm.setBuySellType("FRESH");
		bseOrderForm.setDpTxnMode("P");

		bseOrderForm.setQty("1");
		bseOrderForm.setAllRedeem("N");		// to do
		bseOrderForm.setFolioNo(fundDetails.getPortfolio().equalsIgnoreCase("NEW")?"":fundDetails.getPortfolio());
		bseOrderForm.setRemarks("Purchase Request");
		bseOrderForm.setKYCStatus("Y");
		bseOrderForm.setRefNo(fundDetails.getBseRefNo());
		bseOrderForm.setSubBrCode("");
		bseOrderForm.setEuin("");
		bseOrderForm.setEuinVal("N");
		bseOrderForm.setMinRedeem("Y");
		bseOrderForm.setDPC("N");
		bseOrderForm.setIPAdd("");
		bseOrderForm.setPassword("");
		bseOrderForm.setParma1("");
		//		bseOrderForm.setParam2("");
		bseOrderForm.setParam3("");

		if(fundDetails.getMandateType().equals(CommonConstants.BSE_XIP))
			bseOrderForm.setXSIPMANDATEID(mandateId);
		else{
			bseOrderForm.setParam2(mandateId);
		}

		return bseOrderForm;

	}


	public static BseOrderEntryResponse transactionOrderReponseToBeans(BseOrderEntryResponse response, String responseText, String internalRefNo){
		List<String> res = Arrays.asList(responseText.split("\\|"));
		//		BseorderEntryResponse response = new BseorderEntryResponse();

		response.setTransactionType(res.get(0));
		response.setUniqueReferenceNo(res.get(1));
		response.setOrderNoOrSipRegNo(res.get(2));
		/*response.setClientCode(res.get(3));
		response.setUserId(res.get(4));
		response.setMemberId(res.get(5));*/
		response.setUserId(res.get(3));
		response.setMemberId(res.get(4));
		response.setClientCode(res.get(5));
		response.setBsereMarks(res.get(6));
		response.setSuccessFlag(res.get(7));
		response.setIntRefNo(internalRefNo);
		response.setCreatedOn(new Date());

		return response;

	}


	public static BseOrderEntryResponse siptransactionOrderReponseToBeans(BseOrderEntryResponse response, String responseText, String internalRefNo){
		List<String> res = Arrays.asList(responseText.split("\\|"));
		//		BseorderEntryResponse response = new BseorderEntryResponse();
		//		SIP Response NEW|201902202627300003|26273|DEBA593C|SUMANTA1|214516|SIP HAS BEEN REGISTERED, SIP REG NO IS : 214516|0

		response.setTransactionType(res.get(0));
		response.setUniqueReferenceNo(res.get(1));
		response.setUserId(res.get(2));
		response.setMemberId(res.get(3));
		response.setClientCode(res.get(4));

		response.setOrderNoOrSipRegNo(res.get(5));
		response.setBsereMarks(res.get(6));
		response.setSuccessFlag(res.get(7));

		response.setIntRefNo(internalRefNo);
		response.setCreatedOn(new Date());

		return response;

	}

	public static BseAOFUploadRequest AOFFormtoBseBeanMapper(byte[] aoffile, String base64Image, String clientCode){
		BseAOFUploadRequest response = new BseAOFUploadRequest();
		StringBuffer fileName = new StringBuffer(CommonConstants.BSE_MEMBER_ID);
		fileName.append(clientCode).append(new SimpleDateFormat("ddMMyyyy").format(new Date())).append(".tiff");
		response.setFlag("UCC");
		response.setDocumentType("Image/tiff");
		response.setMemberCode(CommonConstants.BSE_MEMBER_ID);
		response.setClientCode(clientCode);
		response.setFileName(fileName.toString());
		//		response.setpFileBytes(aoffile);
		response.setpFileBytes(base64Image);
		response.setFiller1("NULL");
		response.setFiller2("NULL");
		return response;

	}


	public static BseOrderPaymentResponse bseOrderPayemtResultMapper(BseOrderPaymentResponse response, String responseText){
		List<String> res = Arrays.asList(responseText.split("\\|"));
		//		BseorderEntryResponse response = new BseorderEntryResponse();

		response.setStatusCode(res.get(0));
		response.setPayUrl(res.get(1));

		return response;



	}

	public static BseAOFUploadResponse bseAOFUploadResponsetoBean(BseAOFUploadResponse response, String responseText){
		List<String> res = Arrays.asList(responseText.split("\\|"));
		//		BseorderEntryResponse response = new BseorderEntryResponse();

		response.setStatusCode(res.get(0));
		response.setStatusMessage(res.get(1));

		return response;



	}


	public static BseApiResponse fatcaUploadResponseToBean(BseApiResponse response, String responseText){
		List<String> res = Arrays.asList(responseText.split("\\|"));
		//		BseorderEntryResponse response = new BseorderEntryResponse();

		response.setResponseCode(res.get(0));
		response.setRemarks(res.get(1));

		return response;

	}

	public static BsePaymentStatus BsePaymentStatusRequestToBse(String clientId, String orderNo){
		BsePaymentStatus requestForm = new BsePaymentStatus();
		requestForm.setClientcode(clientId);
		requestForm.setOrderno(orderNo);
		requestForm.setSegment("BSEMF");

		return requestForm;
	}

	public static BseEMandateRegistration bankDetailsToBseMandateBeans(UserBankDetails bankDetails,String mandateType, String amount, String clientCode,Date startDate, Date endDate){
		BseEMandateRegistration requestForm = new BseEMandateRegistration();

		requestForm.setClientCode(clientCode);
		//		requestForm.setAmount(amount);
		requestForm.setAmount("50000");
		requestForm.setMandateType(mandateType!="X"?mandateType:"X");
		requestForm.setAccountNo(bankDetails.getAccountNumber());
		requestForm.setAccType(bankDetails.getAccountType());
		requestForm.setIFSCCODE(bankDetails.getIfscCode().toUpperCase());
		requestForm.setSTARTDATE((new SimpleDateFormat("dd/MM/yyyy")).format(startDate));
		requestForm.setENDDATE((new SimpleDateFormat("dd/MM/yyyy")).format(endDate));

		return requestForm;
	}


	public static BsePanStatusResponse panStatusResponsetoBean(BsePanStatusResponse response, String responseText){
		List<String> res = Arrays.asList(responseText.split("\\|"));
		//		BseorderEntryResponse response = new BseorderEntryResponse();
		//		SIP Response NEW|201902202627300003|26273|DEBA593C|SUMANTA1|214516|SIP HAS BEEN REGISTERED, SIP REG NO IS : 214516|0

		response.setResponseCode(res.get(0));
		response.setPanNumber(res.get(1));
		response.setMfdStatus(res.get(2));
		response.setMfiStatus(res.get(3));
		response.setRfdStatus(res.get(4));

		response.setRfiStatus(res.get(5));
		response.setBseRemarks(res.get(6));

		return response;
	}


	public static BseApiResponse emandateRegResponseToBean(String apiResponse){
		BseApiResponse response = new BseApiResponse();

		try{
			List<String> res = Arrays.asList(apiResponse.split("\\|"));
			int length = res.size();
			if(length == 2){
				response.setStatusCode(res.get(0));
				response.setRemarks(res.get(1));
			}else if(length==3){
				response.setStatusCode(res.get(0));
				response.setRemarks(res.get(1));
				response.setResponseCode(res.get(2));
			}else{
				logger.error("emandateRegResponseToBean(): Response length expectation mismatch. Check response for more paramateres returned");
				response.setStatusCode(res.get(0));
				response.setRemarks(res.get(1));
			}
		}catch(Exception e){
			logger.error("emandateRegResponseToBean(): Error while parsing BSE api response",e);
			response.setStatusCode("000");
			response.setRemarks("Internal Error. Failed to parse Response.");
		}

		return response;
	}

	/*	public static void main(String[] args ){
		Date d  = new Date();
		Calendar c = Calendar.getInstance();
	    c.setTime(d);
	    c.add(Calendar.YEAR, 10);
	    d.setTime(c.getTimeInMillis());
	    System.out.println((new SimpleDateFormat("dd/MM/yyyy")).format(d));
	}*/

}
