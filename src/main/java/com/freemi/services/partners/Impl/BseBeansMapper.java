package com.freemi.services.partners.Impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.freemi.entity.bse.ClientMFD;
import com.freemi.entity.investment.BseMFInvestForm;

public class BseBeansMapper {
	
	private static final Logger logger = LogManager.getLogger(BseBeansMapper.class);
	
	public static ClientMFD  InvestmentFormToBseBeans(BseMFInvestForm registrationForm){
		
		ClientMFD clientFregirationForm = new  ClientMFD();
		
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

}
