package com.freemi.services.partners.Impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.freemi.common.util.CommonConstants;
import com.freemi.controller.interfaces.InvestmentConnectorBseInterface;
import com.freemi.entity.bse.BseAOFUploadRequest;
import com.freemi.entity.bse.BseAOFUploadResponse;
import com.freemi.entity.bse.BseOrderEntry;
import com.freemi.entity.bse.BseOrderPaymentRequest;
import com.freemi.entity.bse.BseOrderPaymentResponse;
import com.freemi.entity.bse.BsePaymentStatus;
import com.freemi.entity.bse.BseRegistrationMFD;
import com.freemi.entity.bse.BseSipOrderEntry;
import com.freemi.entity.investment.BseMFInvestForm;
import com.freemi.entity.investment.BseOrderEntryResponse;
import com.freemi.entity.investment.SelectMFFund;
import com.freemi.ui.restclient.RestClientBse;

@Service
public class BseConnectorsImpl implements InvestmentConnectorBseInterface {

	private static final Logger logger = LogManager.getLogger(BseConnectorsImpl.class);

	@Autowired
	Environment env;

	@Override
	public String saveCustomerRegistration(BseMFInvestForm registrationForm, String field1) {
		String result = "";
		logger.info("Registration details processing to BSE platform begins");
		if(env.getProperty(CommonConstants.BSE_ENABLED).equalsIgnoreCase("Y")){
			try{
				logger.info("Convert form data to BSE format data");
				BseRegistrationMFD bseregistrationForm=  BseBeansMapper.InvestmentFormToBseBeans(registrationForm);
				logger.info("Begin BSE service invoke process");
				result= RestClientBse.registerUser(bseregistrationForm);
			}catch(Exception e){
				logger.error("Failed during proceesing of BSE customer details to BSE platform",e);
				result="BSE_CONN_FAIL";
			}
		}else{
			logger.info("BSE endpoint connection is currently disabled");
			result="INV_DISABLED";
		}

		return result;
	}

	@Override
	public BseOrderEntryResponse processCustomerPurchaseRequest(SelectMFFund selectedFund, String transactionNumber) {
		String result = "";
		BseOrderEntryResponse bseOrderResp= new BseOrderEntryResponse();

		logger.info("Purchase details processing to BSE platform begins");
		if(env.getProperty(CommonConstants.BSE_ENABLED).equalsIgnoreCase("Y")){
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
			}else{
				logger.warn("Unsupported transaction type selected- "+ selectedFund.getInvestype());
				bseOrderResp.setSuccessFlag("UNSUPPORTED_INV_TYPE");
				bseOrderResp.setBsereMarks("Invest type not supported.");
			}
		}else{
			logger.info("BSE Investment is currently disabled");
			bseOrderResp.setClientCode("000");
			bseOrderResp.setBsereMarks("INV_DISABLED");
		}
		return bseOrderResp;
	}

	@Override
	public BseOrderPaymentResponse getPaymentUrl(BseOrderPaymentRequest request) {
		logger.info("Get payment url for current transaction of customer- "+ request.getClientCode());
		String response = null;
		BseOrderPaymentResponse orderResponse = new BseOrderPaymentResponse();
		if(env.getProperty(CommonConstants.BSE_ENABLED).equalsIgnoreCase("Y")){
			try{
				//		BseSipOrderEntry bseMfSipOrderForm=  BseBeansMapper.transactionSIPOrderToBseBeans(selectedFund, transactionNumber);
				logger.info("Begin BSE service invoke process");
				response = RestClientBse.purchasePaymentLink(request);
				BseBeansMapper.bseOrderPayemtResultMapper(orderResponse, response);
			}catch(Exception e){
				logger.error("Failed during proceesing of BSE SIP registration details to BSE platform",e);
				//			result="BSE_CONN_FAIL";
				orderResponse.setStatusCode("101");
			}
		}else{
			logger.info("BSE connction is currently disabled");
			orderResponse.setStatusCode("000");
		}
		return orderResponse;
	}

	@Override
	public String uploadAOFForm(String mobileNumber, String aoffolderLocation, String clientCode) {
		logger.info("Convert AOF form details to BSE format and process");
		String flag="SUCCESS";
		BseAOFUploadResponse aofresp = new BseAOFUploadResponse();
		byte[] filearray = null;
		Path filepath = Paths.get(aoffolderLocation, mobileNumber+".pdf");
		if(Files.exists(filepath)){
			try {
				filearray = Files.readAllBytes(new File(filepath.toString()).toPath());
				BseAOFUploadRequest r = BseBeansMapper.AOFFormtoBseBeanMapper(filearray, clientCode);
				String responseText = RestClientBse.uploadAOF(r);
				logger.info("Response for AOF upload against customer : "+ clientCode + " : "+ responseText);
//				BseBeansMapper.BseAOFUploadResponsetoBean(aofresp, responseText);
				if(!responseText.equalsIgnoreCase("Uploaded")){
//					logger
					flag="FAIL";
				}
			} catch (IOException e) {
				logger.error("Failed to query BSE to upload AOF form", e);
				aofresp.setStatusCode("999");
				aofresp.setStatusCode("FAILED_CONN");
				flag="ERROR";
			}
		}else{
			logger.info("AOF File does not exist for upload!");
			flag="NO_FILE";
		}
		return flag;
	}
	
	@Override
	public String BseOrderPaymentStatus(String clientId, String orderNo) {
		logger.info("Get payment status fro payment order- "+ orderNo);
		String response = "";
		BseOrderPaymentResponse orderResponse = new BseOrderPaymentResponse();
		if(env.getProperty(CommonConstants.BSE_ENABLED).equalsIgnoreCase("Y")){
			try{
				BsePaymentStatus requestForm=  BseBeansMapper.BsePaymentStatusRequestToBse(clientId, orderNo);
				logger.info("Begin BSE service invoke process for payment status");
				response = RestClientBse.orderPaymentStatus(requestForm);
//				BseBeansMapper.bseOrderPayemtResultMapper(orderResponse, response);
				
				
			}catch(Exception e){
				logger.error("Failed during proceesing of BSE SIP registration details to BSE platform",e);
				//			result="BSE_CONN_FAIL";
				response= "ERROR";
			}
		}else{
			logger.info("BSE connction is currently disabled");
			orderResponse.setStatusCode("000");
		}
		return response;
	}

/*
	public static void main(String[] args){
		Path filepath = Paths.get("E:/AOF/", "8777777777"+".pdf");
		if(Files.exists(filepath)){
			System.out.println("file exist");
			try {
				byte[] array = Files.readAllBytes(filepath);
				System.out.println(array);
				System.out.println();

				BseAOFUploadRequest r = BseBeansMapper.AOFFormtoBseBeanMapper(array, "DEBA593C");
				String s= RestClientBse.uploadAOF(r);
				System.out.println(s);

				OutputStream 
				os 
				= new FileOutputStream("E:/AOF/b5.pdf"); 
				os.write(array);
				os.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			System.out.println("No file");
		}
	}*/

	

}
