package com.freemi.services.partners.Impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.freemi.entity.investment.MFCustomers;
import com.freemi.entity.investment.Allotmentstatement;
import com.freemi.entity.investment.BseMandateDetails;
import com.freemi.entity.investment.BseOrderEntryResponse;
import com.freemi.entity.investment.SelectMFFund;

public class BseBeansMapper {

	private static final Logger logger = LogManager.getLogger(BseBeansMapper.class);



	/**
	 * @param registrationForm
	 * @return
	 */
	public static BseRegistrationMFD  InvestmentFormToBseBeans(MFCustomers registrationForm){

		BseRegistrationMFD clientFregirationForm = new  BseRegistrationMFD();

		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-mm-dd");
		SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("dd/mm/yyyy");

		clientFregirationForm.setClientCode(registrationForm.getClientID());
		clientFregirationForm.setClientHolding(registrationForm.getHoldingMode());
		clientFregirationForm.setClientTaxstatu(registrationForm.getTaxStatus());
		clientFregirationForm.setClientOccupationcod(registrationForm.getOccupation());
		clientFregirationForm.setClientAppname1(registrationForm.getInvName());
		clientFregirationForm.setApplicant1fname(registrationForm.getFname());
		clientFregirationForm.setApplicant1mname(registrationForm.getMname());
		clientFregirationForm.setApplicant1lname(registrationForm.getLname());

		clientFregirationForm.setApplicant1kyctype(registrationForm.getInvestor1kyctpe());
		//	clientFregirationForm.setApplicant1kyctype("K");
		clientFregirationForm.setApplicant1ckycno(registrationForm.getInvestor1ckycno());

		clientFregirationForm.setApplicant1aadhaarupdated(registrationForm.getAadhaarupdated());
		clientFregirationForm.setPaperlessflag(registrationForm.getPaperlessflag());

		//	clientFregirationForm.setApplicant1kraexemptno();

		//		Converting date format to BSE specific format dd/mm/yyyy
		logger.info("InvestmentFormToBseBeans(): Received DOB format: "+ registrationForm.getCustomerdob());

		clientFregirationForm.setClientDob(registrationForm.getCustomerdob());
		/*
	try {

	    Date date1 = simpleDateFormat2.parse(registrationForm.getCustomerdob());
	    String bseFormatDob = simpleDateFormat1.format(date1);
	    clientFregirationForm.setClientDob(bseFormatDob);
	} catch (ParseException e) {
	    logger.error("InvestmentFormToBseBeans(): failed to convert date. Setting the data as is format ",e.getMessage());
	    clientFregirationForm.setClientDob(registrationForm.getInvDOB());

	}
		 */

		System.out.println("After format DOB- "+ clientFregirationForm.getClientDob());
		//		clientFregirationForm.setClientDob(registrationForm.getInvDOB());

		if(registrationForm.getTaxStatus().equals("01") || registrationForm.getTaxStatus().equals("02") || registrationForm.getTaxStatus().equals("03")) {
			clientFregirationForm.setClientGender(registrationForm.getGender());
		}

		clientFregirationForm.setClientPan(registrationForm.getPan1());
		clientFregirationForm.setApplicant1panexempt(registrationForm.getPan1exempt());
		clientFregirationForm.setApplicant1panexemptcategory(registrationForm.getPan1exemptcategory());
		
		clientFregirationForm.setClientType(registrationForm.getClienttype());			//P- Physical , D - Demat								// todo

		clientFregirationForm.setClientAcctype1(registrationForm.getBankDetails().getAccountType());		// todo - convert to Sb from map
		clientFregirationForm.setClientAccno1(registrationForm.getBankDetails().getAccountNumber());
		//		clientFregirationForm.setClientMicrno1("MICR");								//todo
		clientFregirationForm.setClientNeftIfsccode1(registrationForm.getBankDetails().getIfscCode().toUpperCase());						//todo
		clientFregirationForm.setDefaultBankFlag("Y");
		clientFregirationForm.setClientAdd1(registrationForm.getAddressDetails().getAddress1());
		clientFregirationForm.setClientAdd2(registrationForm.getAddressDetails().getAddress2());
		clientFregirationForm.setClientAdd3(registrationForm.getAddressDetails().getAddress3());
		clientFregirationForm.setClientCity(registrationForm.getAddressDetails().getCity());
		clientFregirationForm.setClientState(registrationForm.getAddressDetails().getState());	//todo - convert to code from map
		clientFregirationForm.setClientPincode(registrationForm.getAddressDetails().getPinCode());
		clientFregirationForm.setClientCountry(registrationForm.getAddressDetails().getCountry());
		clientFregirationForm.setClientEmail(registrationForm.getEmail());
		clientFregirationForm.setClientCommmode("E");			//todo // E- EMail, M - Mobile
		clientFregirationForm.setClientDivpaymode(registrationForm.getDividendPayMode());	//todo convert to code
		//		clientFregirationForm.setClientDivpaymode("02");	//todo convert to code
		clientFregirationForm.setCm_mobile(registrationForm.getMobile());
		clientFregirationForm.setClientCountry(registrationForm.getAddressDetails().getCountry());


		//		Joint Holder
		if(registrationForm.getHoldingMode().equals("JO") || registrationForm.getHoldingMode().equals("AS")  ){
			clientFregirationForm.setClientPan2(registrationForm.getPan2());
			clientFregirationForm.setClientAppname2(registrationForm.getApplicant2());
		}

		//		Nominee
		clientFregirationForm.setClientNominee(registrationForm.getNominee().getNomineeName());
		clientFregirationForm.setClientNomineeRelation(registrationForm.getNominee().getNomineeRelation());
		clientFregirationForm.setNominee1minorflag(registrationForm.getNominee().getIsNomineeMinor());
		clientFregirationForm.setNominee1applicable(registrationForm.getNominee().getNomineePercentage());
		clientFregirationForm.setNominee1minorflag(registrationForm.getNominee().getIsNomineeMinor());
		clientFregirationForm.setNominee1guardianname(registrationForm.getNominee().getNomineeGuardian());
		clientFregirationForm.setNominee1dob(registrationForm.getNominee().getNomineeDOB());
		clientFregirationForm.setMobiledecflag(registrationForm.getMobiledecflag());
		clientFregirationForm.setEmaildecflag(registrationForm.getEmaildeclareflag());

		return clientFregirationForm;
	}



	/**
	 * @param registrationForm
	 * @param dateformat
	 * @return
	 */
	public static BseFatcaForm  InvestmentFormToBseFATCABeans(MFCustomers registrationForm, String dateformat){
		BseFatcaForm fatcaForm = new BseFatcaForm();

		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-mm-dd");
		//	SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(dateformat!=null?dateformat:"dd/mm/yyyy");
		SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat(dateformat!=null?dateformat:"mm/dd/yyyy");
		//
		fatcaForm.setPAN_RP(registrationForm.getPan1());
		fatcaForm.setINV_NAME(registrationForm.getInvName());

		//		Converting date format to BSE specific format dd/mm/yyyy
		logger.info("InvestmentFormToBseFATCABeans(): Received DOB format- "+ registrationForm.getInvDOB());
		try {
			Date date1 = simpleDateFormat1.parse(registrationForm.getInvDOB());
			String bseFormatDob = simpleDateFormat3.format(date1);
			fatcaForm.setDOB(bseFormatDob);
		} catch (ParseException e) {
			logger.error("InvestmentFormToBseFATCABeans(): failed to convert date: Setting default format.",e.getMessage());
			fatcaForm.setDOB(registrationForm.getInvDOB());
		}
		logger.info("DOB for fatca- "+ fatcaForm.getDOB());
		//		fatcaForm.setDOB(registrationForm.getInvDOB());

		fatcaForm.setFR_NAME(registrationForm.getFatcaDetails().getFatherName());
		fatcaForm.setSP_NAME(registrationForm.getFatcaDetails().getSpouseName());
		fatcaForm.setTAX_STATUS(registrationForm.getTaxStatus());
		fatcaForm.setDATA_SRC(registrationForm.getFatcaDetails().getDataSource());
		fatcaForm.setADDR_TYPE(registrationForm.getFatcaDetails().getAddressType());
		fatcaForm.setPO_BIR_INC(registrationForm.getFatcaDetails().getPlaceOfBirth());
		fatcaForm.setCO_BIR_INC(registrationForm.getFatcaDetails().getCountryOfBirth());

		//		Fixed to INDIA
//		fatcaForm.setTAX_RES1("IN");
		fatcaForm.setTAX_RES1(registrationForm.getFatcaDetails().getResidenceCountry1());
		fatcaForm.setTPIN1(registrationForm.getPan1());
		fatcaForm.setID1_TYPE(registrationForm.getFatcaDetails().getIdentificationDocType());
		fatcaForm.setSRCE_WEALT(registrationForm.getFatcaDetails().getWealthSource());
		fatcaForm.setINC_SLAB(registrationForm.getFatcaDetails().getIncomeSlab());
		
		
		try{
			fatcaForm.setNET_WORTH(registrationForm.getFatcaDetails().getNetWordth()!=null?Double.toString(registrationForm.getFatcaDetails().getNetWordth()):"");
		}catch(Exception e){
			logger.error("InvestmentFormToBseFATCABeans(): Unable to convert net worth data.");
		}
		if(registrationForm.getFatcaDetails().getDateOfNetworth()!=null){
			String bseFormatDob = simpleDateFormat3.format(new Date());
			fatcaForm.setNW_DATE(bseFormatDob);
		}
	
		fatcaForm.setPEP_FLAG(registrationForm.getFatcaDetails().getPoliticalExposedPerson());
		fatcaForm.setOCC_CODE(registrationForm.getFatcaDetails().getOccupationCode());
		//	fatcaForm.setOCC_TYPE(registrationForm.getFatcaDetails().getOccupationType());
		
		fatcaForm.setOCC_TYPE(registrationForm.getFatcaDetails().getOccupationType());
		

		fatcaForm.setEXCH_NAME(registrationForm.getFatcaDetails().getExchangeName());

		fatcaForm.setUBO_APPL(registrationForm.getFatcaDetails().getUboApplicable());
		fatcaForm.setUBO_DF(registrationForm.getFatcaDetails().getUbodf());

		fatcaForm.setNEW_CHANGE(registrationForm.getFatcaDetails().getNewUpdateIndicator());
		fatcaForm.setLOG_NAME(registrationForm.getFatcaDetails().getLogName());


		return  fatcaForm;
	}


	/**
	 * @param fundDetails
	 * @param requestNumber
	 * @return
	 */
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
				bseOrderForm.setSchemeCd(fundDetails.getInvCategory().equalsIgnoreCase("Z")? fundDetails.getSchemeCode():fundDetails.getReinvSchemeCode()+"-L1");

			}else{
				bseOrderForm.setSchemeCd(fundDetails.getInvCategory().equalsIgnoreCase("Z")? fundDetails.getSchemeCode():fundDetails.getReinvSchemeCode());
			}

			bseOrderForm.setBuySell("P");
			bseOrderForm.setRemarks("Purchase Request");
			//			bseOrderForm.setBuySellType("FRESH");
			bseOrderForm.setBuySellType(fundDetails.getBuySellType());

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
			bseOrderForm.setIPAdd(fundDetails.getClientIp());
		}

		bseOrderForm.setTransNo(requestNumber);

		bseOrderForm.setDPTxn("P");
		bseOrderForm.setOrderVal(fundDetails.getInvestAmount());	//to do for all redeem 
		bseOrderForm.setQty("");
		bseOrderForm.setAllRedeem("N");		// to do
		bseOrderForm.setFolioNo(fundDetails.getPortfolio().equalsIgnoreCase("NEW")?"":fundDetails.getPortfolio());

		bseOrderForm.setKYCStatus("Y");
		bseOrderForm.setRefNo(fundDetails.getBseRefNo());
		//		bseOrderForm.setEUIN(CommonConstants.EUIN_CODE);
		bseOrderForm.setEUINVal("N");
		bseOrderForm.setMinRedeem("Y");	//todo
		bseOrderForm.setDPC("Y");		//todo

		logger.info("transactionOrderToBseBeans()- mapping complete..");
		return bseOrderForm;
	}


	/**
	 * @param fundDetails
	 * @param requestNumber
	 * @return
	 */
	public static BseOrderEntry redeeemOrderToBseBeans(SelectMFFund fundDetails, String requestNumber){
		logger.info("redeeemOrderToBseBeans()- Redeem/canel mapping begin");
		BseOrderEntry bseOrderForm = new BseOrderEntry();

		bseOrderForm.setUserID(CommonConstants.BSE_USER_ID);
		bseOrderForm.setMemberId(CommonConstants.BSE_MEMBER_ID);
		bseOrderForm.setClientCode(fundDetails.getClientID());		// Customer's client ID

		
		if(fundDetails.getRedeemAll().equals("N")){
			bseOrderForm.setAllRedeem("N");
			bseOrderForm.setOrderVal(fundDetails.getRedeemAmount());	//to do for all redeem
			bseOrderForm.setQty("");
		}else{

			bseOrderForm.setAllRedeem("Y");
			bseOrderForm.setOrderVal(0);	//to do for all redeem
			bseOrderForm.setQty("");
		}
		
		if(fundDetails.getTransactionType().equals("REDEEM")){
			bseOrderForm.setTransCode("NEW");
			bseOrderForm.setSchemeCd(fundDetails.getSchemeCode());
			bseOrderForm.setSchemeCd(fundDetails.getSchemeCode());
			bseOrderForm.setBuySell("R");
			bseOrderForm.setRemarks("Redemption Request");
			bseOrderForm.setBuySellType("FRESH");
			bseOrderForm.setParam3(fundDetails.getBanbkaccount());
		}
		if(fundDetails.getTransactionType().equals("CXL")){
			bseOrderForm.setTransCode("CXL");
			bseOrderForm.setOrderId(fundDetails.getOrderNo());
			bseOrderForm.setSchemeCd(fundDetails.getSchemeCode());
			bseOrderForm.setSchemeCd(fundDetails.getSchemeCode());
			bseOrderForm.setBuySell("R");
			bseOrderForm.setRemarks("Order cancel Request");
			bseOrderForm.setBuySellType("FRESH");
			logger.info("Cancel amount-" + fundDetails.getInvestAmount());
			bseOrderForm.setOrderVal(fundDetails.getInvestAmount()); // overwrite above data.
		}

		bseOrderForm.setTransNo(requestNumber);

		bseOrderForm.setDPTxn("P");

		

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
		bseOrderForm.setDPC("Y");
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
			bseOrderForm.setRemarks("SIP Purchase Request");
		}else{
			bseOrderForm.setTransactionCode("CXL");
			bseOrderForm.setXSIP_RegId(fundDetails.getOrderNo());
			bseOrderForm.setRemarks("SIP cancel Request");
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

		//		bseOrderForm.setSchemeCode(fundDetails.getInvCategory().equalsIgnoreCase("Z")? fundDetails.getSchemeCode():fundDetails.getReinvSchemeCode());
		if(fundDetails.getInvestAmount()>199999){
			logger.info("Since the amount is above, marking the transaction as L1 category.. Proceeding by changing fundname to - "+ fundDetails.getSchemeCode()+"-L1");
			bseOrderForm.setSchemeCode(fundDetails.getInvCategory().equalsIgnoreCase("Z")? fundDetails.getSchemeCode():fundDetails.getReinvSchemeCode()+"-L1");

		}else{
			bseOrderForm.setSchemeCode(fundDetails.getInvCategory().equalsIgnoreCase("Z")? fundDetails.getSchemeCode():fundDetails.getReinvSchemeCode());
		}


		bseOrderForm.setBuySell("P");
		bseOrderForm.setBuySellType("FRESH");
		bseOrderForm.setDpTxnMode("P");

		bseOrderForm.setQty("1");
		bseOrderForm.setAllRedeem("N");		// to do
		bseOrderForm.setFolioNo(fundDetails.getPortfolio().equalsIgnoreCase("NEW")?"":fundDetails.getPortfolio());

		bseOrderForm.setKYCStatus("Y");
		bseOrderForm.setRefNo(fundDetails.getBseRefNo());
		bseOrderForm.setSubBrCode("");
		bseOrderForm.setEuin("");
		bseOrderForm.setEuinVal("N");
		bseOrderForm.setMinRedeem("Y");
		bseOrderForm.setDPC("Y");
		bseOrderForm.setIPAdd("");
		bseOrderForm.setPassword("");
		bseOrderForm.setParma1("");
		//		bseOrderForm.setParam2("");
		bseOrderForm.setParam3("");

		if(fundDetails.getMandateType().equals(CommonConstants.BSE_XIP) || fundDetails.getMandateType().equals(CommonConstants.BSE_ENACH)) {
			bseOrderForm.setXSIPMANDATEID(mandateId);	//BSE mandate ID (XSIP/Emandate) for XSIP Orders
		}else if(fundDetails.getMandateType().equals(CommonConstants.BSE_ISIP)){
			bseOrderForm.setParam2(mandateId);	//ISIP mandate
		}else {
			logger.info("Unidentified MANDATE TYPE. Not Setting value for mandatetype- "+ fundDetails.getMandateType());
			
		}

		return bseOrderForm;

	}

	public static BseXipISipOrderEntry cancelXSIPISIPOrderToBseBeans(SelectMFFund fundDetails,String uniqueReferenceNo, String orderno, String refno){
		BseXipISipOrderEntry bseOrderForm = new BseXipISipOrderEntry();

		bseOrderForm.setTransactionCode("CXL");
		bseOrderForm.setXSIP_RegId(orderno);
		bseOrderForm.setRemarks("SIP cancel Request");

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

		bseOrderForm.setBuySell("S");
		bseOrderForm.setBuySellType("FRESH");
		bseOrderForm.setDpTxnMode("P");

		bseOrderForm.setQty("1");
		bseOrderForm.setAllRedeem("N");		// to do
		bseOrderForm.setFolioNo(fundDetails.getPortfolio().equalsIgnoreCase("NEW")?"":fundDetails.getPortfolio());

		bseOrderForm.setKYCStatus("Y");
		bseOrderForm.setRefNo(refno);
		bseOrderForm.setSubBrCode("");
		bseOrderForm.setEuin("");
		bseOrderForm.setEuinVal("N");
		bseOrderForm.setMinRedeem("Y");
		bseOrderForm.setDPC("Y");
		bseOrderForm.setIPAdd("");
		bseOrderForm.setPassword("");
		bseOrderForm.setParma1("");
		bseOrderForm.setParam2(fundDetails.getMandateId());
		bseOrderForm.setParam3("");

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


	public static BseAOFUploadRequest AOFFormtoBseBeanMapper(byte[] aoffile, String base64Image, String clientCode, String filenamedata){
		BseAOFUploadRequest response = new BseAOFUploadRequest();
		StringBuffer filename = new StringBuffer(CommonConstants.BSE_MEMBER_ID);
		
		if(filenamedata!=null) {
			response.setFileName(filenamedata);
		}else {
			filename.append(clientCode).append(new SimpleDateFormat("ddMMyyyy").format(new Date())).append(".tiff");
			filenamedata = filename.toString();
			response.setFileName(filenamedata);
		}
		
		response.setFlag("UCC");
//		response.setDocumentType("Image/tiff");
		response.setDocumentType("NRM");
		response.setMemberCode(CommonConstants.BSE_MEMBER_ID);
		response.setClientCode(clientCode);
		
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
		/*
		BSEMF- when MF Order is placed
		SGB- when SGB order is placed
		*/
		requestForm.setSegment("BSEMF");

		return requestForm;
	}

	public static BseEMandateRegistration bankDetailsToBseMandateBeans(BseMandateDetails mandatedetails, UserBankDetails bankDetails,String mandateType, String amount, String clientCode,Date startDate, Date endDate){
		BseEMandateRegistration requestForm = new BseEMandateRegistration();

		requestForm.setClientCode(clientCode);
		//		requestForm.setAmount(amount);
		requestForm.setAmount(mandatedetails.getAmount());
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


	public static List<Allotmentstatement> allotmentstatementdata(String apiResponse){

		List<Allotmentstatement> statement=null;
		Allotmentstatement record;
		SimpleDateFormat dbfmt = new SimpleDateFormat("yyyy-mm-dd");
		SimpleDateFormat bsefmt1 = new SimpleDateFormat("dd/mm/yyyy");
		try{
			if(apiResponse!=null && !apiResponse.isEmpty()) {

				List<String> data = Arrays.asList(apiResponse.split("\\|"));

				if(data.get(0).equals(CommonConstants.TASK_SUCCESS_S)) {
					logger.info("Allotment statement has data to proceed..");
					int counter=0;
					statement = new ArrayList<Allotmentstatement>();
					//			record = new Allotmentstatement();

					List<String> parseddata = new ArrayList<String>();
					List<List<String>> accumulateddata = new ArrayList<List<String>>();
					for(int i=2;i<data.size();i++) {

						counter+=1;
						parseddata.add(data.get(i));
						//			    System.out.print(data.get(i) + "-");
						if(counter%32==0) {
							//   		        counter=1;
							logger.info("Pasrsed line- "+ parseddata);
							accumulateddata.add(parseddata);
							parseddata = new ArrayList<String>();
						}
					}

					logger.info("Total allotment statement records of the day-  "+ accumulateddata.size());
					for(int j=0;j<accumulateddata.size();j++) {
						record = new Allotmentstatement();
						List<String> ex= accumulateddata.get(j);
						record.setAmountcheck(Double.valueOf(ex.get(0)));
						record.setAllottednav(Double.valueOf(ex.get(1)));
						record.setFoliono(ex.get(2));
						record.setSchemecode(ex.get(3));
						record.setAmount(Double.valueOf(ex.get(4)));
						record.setClientcode(ex.get(5));
						record.setAllottedunit(Double.valueOf(ex.get(6)));
						record.setBeneficiaryid(ex.get(7));
						record.setDpcflag(ex.get(8));
						record.setOrdertype(ex.get(9));
						record.setDptrans(ex.get(10));
						record.setMembercode(ex.get(11));
						record.setSettno(ex.get(12));
						record.setSetttype(ex.get(13));
						record.setEuin(ex.get(14));
						record.setSubordertype(ex.get(15));
						record.setEuindecl(ex.get(16));
						record.setInternalrefno(ex.get(17));
						record.setKycflag(ex.get(18));
						record.setRemarks(ex.get(19));
						record.setOrdertype2(ex.get(20));
						record.setSipregnno(ex.get(21));
						try {
							record.setSipregndate(dbfmt.parse(dbfmt.format(bsefmt1.parse(ex.get(22)))));
						}catch(Exception e) {
							logger.error("Allotmentstatemenet: Error parsing SIP reg date: ",e.getMessage());
							record.setSipregndate(null);
						}

						record.setSubbrcode(ex.get(23));
						try {
							//				record.setOrderdate(dbfmt.parse(dbfmt.format(bsefmt.parse(ex.get(24)))));
							record.setOrderdate(dbfmt.parse(ex.get(24)));
						}catch(Exception e) {
							logger.error("Allotmentstatemenet: Error parsing order date: ",e.getMessage());
							record.setOrderdate(null);
						}

						record.setRtaschemecode(ex.get(25));
						record.setValidflag(ex.get(26));
						record.setRtatransno(ex.get(27));
						record.setBeneficiary2(ex.get(28));
						try {
							//				record.setReportdate(dbfmt.parse(dbfmt.format(bsefmt.parse(ex.get(29)))));
							record.setReportdate(dbfmt.parse(ex.get(29)));
						}catch(Exception e) {
							logger.error("Allotmentstatemenet: Error parsing report date: ",e.getMessage());
							record.setReportdate(null);
						}
						record.setIsin(ex.get(30));
						record.setQty(ex.get(31));
						statement.add(record);
					}
					logger.info("Finished parsing allotment statement. Total records- "+ statement.size());
				}else {
					logger.info("No records to process...");
				}
			}else {
				logger.info("Allotmentb statement response is blank. ");
			}
		}catch(Exception e){
			logger.error("emandateRegResponseToBean(): Error while parsing BSE api response",e);
		}

		return statement;
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
