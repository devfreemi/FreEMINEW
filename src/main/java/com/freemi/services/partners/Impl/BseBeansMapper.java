package com.freemi.services.partners.Impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.freemi.common.util.CommonConstants;
import com.freemi.entity.bse.BseOrderEntry;
import com.freemi.entity.bse.BseRegistrationMFD;
import com.freemi.entity.bse.BseSipOrderEntry;
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
		try {
			Date date1 = simpleDateFormat1.parse(registrationForm.getInvDOB());
			String bseFormatDob = simpleDateFormat2.format(date1);
			clientFregirationForm.setClientDob(bseFormatDob);
		} catch (ParseException e) {
			logger.error("InvestmentFormToBseBeans(): failed to convert date: ",e);
		}	
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
		
		return clientFregirationForm;
	}
	
	
	public static BseOrderEntry transactionOrderToBseBeans(SelectMFFund fundDetails, String requestNumber){
		
		BseOrderEntry bseOrderForm = new BseOrderEntry();
		
		bseOrderForm.setUserID(CommonConstants.BSE_USER_ID);
		bseOrderForm.setMemberId(CommonConstants.BSE_MEMBER_ID);
		bseOrderForm.setClientCode(fundDetails.getClientID());		// Customer's client ID
		
		if(fundDetails.getTransactionType().equals("PURCHASE")){	//ADD For MODIFITATION late
			bseOrderForm.setTransCode("NEW");
		}else{
			bseOrderForm.setTransCode("CXL");
		}
		
		bseOrderForm.setTransNo(requestNumber);
		
		bseOrderForm.setSchemeCd(fundDetails.getSchemeCode());
		bseOrderForm.setBuySell("P");
		bseOrderForm.setBuySellType("FRESH");
		bseOrderForm.setDPTxn("P");
		bseOrderForm.setOrderVal(fundDetails.getInvestAmount());	//to do for all redeem 
		bseOrderForm.setQty("");
		bseOrderForm.setAllRedeem("N");		// to do
		bseOrderForm.setFolioNo(fundDetails.getPortfolio());
		bseOrderForm.setRemarks("Purchase Request");
		bseOrderForm.setKYCStatus("Y");
		bseOrderForm.setRefNo(fundDetails.getBseRefNo());
		bseOrderForm.setEUIN(CommonConstants.EUIN_CODE);
		bseOrderForm.setEUINVal("Y");
		bseOrderForm.setMinRedeem("Y");	//todo
		bseOrderForm.setDPC("Y");		//todo
		
		return bseOrderForm;
	}
	
public static BseSipOrderEntry transactionSIPOrderToBseBeans(SelectMFFund fundDetails, String uniqueReferenceNo){
		
		BseSipOrderEntry bseOrderForm = new BseSipOrderEntry();
		
		if(fundDetails.getTransactionType().equals("PURCHASE")){	//ADD For MODIFITATION late
			bseOrderForm.setTransactionCode("NEW");
		}else{
			bseOrderForm.setTransactionCode("CXL");
		}
		bseOrderForm.setUniqueRefNo(uniqueReferenceNo);
		bseOrderForm.setMemberCode(CommonConstants.BSE_MEMBER_ID);
		bseOrderForm.setClientCode(fundDetails.getClientID());		// Customer's client ID
		bseOrderForm.setUserID(CommonConstants.BSE_USER_ID);
		
		bseOrderForm.setTransMode("P");
		
		//todo - dp transactionMode
		
		bseOrderForm.setStartDate(fundDetails.getSipDate());
		
		bseOrderForm.setFrequencyType("MONTHLY");
		bseOrderForm.setFrequencyAllowed("1");
		
		bseOrderForm.setInstallmentAmount(Double.toString(fundDetails.getInvestAmount()));
		bseOrderForm.setNoOfInstallments("60");		//to do 5 years by default selected for now 
		bseOrderForm.setFolioNo(fundDetails.getPortfolio().equalsIgnoreCase("NEW")?"":fundDetails.getPortfolio());
		
		bseOrderForm.setFirstOrderFlag("N");		//to do for first payment
		bseOrderForm.setEuin(CommonConstants.EUIN_CODE);
		
//		todo euin flag
		
		bseOrderForm.setDpc("N");
		bseOrderForm.setPassword("");
		
		/*bseOrderForm.setSchemeCd(fundDetails.getSchemeCode());
		bseOrderForm.setBuySell("P");
		bseOrderForm.setBuySellType("FRESH");
		bseOrderForm.setDPTxn("P");
		bseOrderForm.setOrderVal(fundDetails.getInvestAmount());	//to do for all redeem 
		bseOrderForm.setQty("");
		bseOrderForm.setAllRedeem("N");		// to do
		bseOrderForm.setFolioNo(fundDetails.getPortfolio());
		bseOrderForm.setRemarks("Purchase Request");
		bseOrderForm.setKYCStatus("Y");
		bseOrderForm.setRefNo(fundDetails.getBseRefNo());
		bseOrderForm.setEUIN(CommonConstants.EUIN_CODE);
		bseOrderForm.setEUINVal("Y");
		bseOrderForm.setMinRedeem("Y");	//todo
		bseOrderForm.setDPC("Y");		//todo
*/		
		return bseOrderForm;
	}
	
	
	public static BseOrderEntryResponse transactionOrderReponseToBeans(BseOrderEntryResponse response, String responseText, String internalRefNo){
		List<String> res = Arrays.asList(responseText.split("\\|"));
//		BseorderEntryResponse response = new BseorderEntryResponse();
		
		response.setTransactionType(res.get(0));
		response.setUniqueReferenceNo(res.get(1));
		response.setMemberId(res.get(2));
		response.setClientCode(res.get(3));
		response.setUserId(res.get(4));
		response.setOrderNoOrSipRegNo(res.get(5));
		response.setBsereMarks(res.get(6));
		response.setSuccessFlag(res.get(7));
		response.setIntRefNo(internalRefNo);
		response.setCreatedOn(new Date());
		
		return response;
		
		
		
	}

}
