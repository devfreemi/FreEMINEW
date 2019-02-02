package com.freemi.services.partners.Impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.freemi.controller.interfaces.InvestmentConnectorBseInterface;
import com.freemi.entity.bse.BseOrderEntry;
import com.freemi.entity.bse.BseRegistrationMFD;
import com.freemi.entity.bse.BseSipOrderEntry;
import com.freemi.entity.investment.BseMFInvestForm;
import com.freemi.entity.investment.BseOrderEntryResponse;
import com.freemi.entity.investment.SelectMFFund;
import com.freemi.entity.investment.TransactionStatus;
import com.freemi.ui.restclient.RestClientBse;

@Service
public class BseConnectorsImpl implements InvestmentConnectorBseInterface {

	private static final Logger logger = LogManager.getLogger(BseConnectorsImpl.class);

	@Override
	public String saveCustomerRegistration(BseMFInvestForm registrationForm, String field1) {
		String result = "";
		logger.info("Registration details processing to BSE platform begins");
		try{
			logger.info("Convert form data to BSE format data");
			BseRegistrationMFD bseregistrationForm=  BseBeansMapper.InvestmentFormToBseBeans(registrationForm);
			logger.info("Begin BSE service invoke process");
			result= RestClientBse.registerUser(bseregistrationForm);
		}catch(Exception e){
			logger.error("Failed during proceesing of BSE customer details to BSE platform",e);
			result="BSE_CONN_FAIL";
		}

		return result;
	}

	@Override
	public BseOrderEntryResponse processCustomerPurchaseRequest(SelectMFFund selectedFund, String transactionNumber) {
		String result = "";
		BseOrderEntryResponse bseOrderResp= new BseOrderEntryResponse();
		
		logger.info("Purchase details processing to BSE platform begins");
		
//		Order Entry processing for lumpsum - fresh
		if(selectedFund.getInvestype().equals("LUMPSUM")){
		try{
			logger.info("Convert form data to BSE format data");
			BseOrderEntry bseMfOrderForm=  BseBeansMapper.transactionOrderToBseBeans(selectedFund, transactionNumber);
			logger.info("Begin BSE service invoke process");
			result = RestClientBse.purchaseRequestProcess(bseMfOrderForm);
			BseBeansMapper.transactionOrderReponseToBeans(bseOrderResp, result, selectedFund.getBseRefNo());
		}catch(Exception e){
			logger.error("Failed during proceesing of BSE customer details to BSE platform",e);
			//			result="BSE_CONN_FAIL";
			bseOrderResp.setSuccessFlag("BSE_CONN_FAIL");
		}
		}
//		Order process request for SIP
		else if(selectedFund.getInvestype().equals("SIP")){
			logger.info("Convert form data to BSE format data");
			try{
			BseSipOrderEntry bseMfSipOrderForm=  BseBeansMapper.transactionSIPOrderToBseBeans(selectedFund, transactionNumber);
			logger.info("Begin BSE service invoke process");
			result = RestClientBse.purchaseSIPRequestProcess(bseMfSipOrderForm);
			BseBeansMapper.transactionOrderReponseToBeans(bseOrderResp, result, selectedFund.getBseRefNo());
			}catch(Exception e){
				logger.error("Failed during proceesing of BSE SIP registration details to BSE platform",e);
				//			result="BSE_CONN_FAIL";
				bseOrderResp.setSuccessFlag("BSE_CONN_FAIL");
			}
		}

		return bseOrderResp;
	}

}
