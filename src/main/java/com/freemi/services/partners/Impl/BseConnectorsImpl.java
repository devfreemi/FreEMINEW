package com.freemi.services.partners.Impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.freemi.common.util.CommonConstants;
import com.freemi.entity.bse.BseAOFUploadRequest;
import com.freemi.entity.bse.BseAOFUploadResponse;
import com.freemi.entity.bse.BseApiResponse;
import com.freemi.entity.bse.BseEMandateRegistration;
import com.freemi.entity.bse.BseFatcaForm;
import com.freemi.entity.bse.BseOrderEntry;
import com.freemi.entity.bse.BseOrderPaymentRequest;
import com.freemi.entity.bse.BseOrderPaymentResponse;
import com.freemi.entity.bse.BsePanStatusResponse;
import com.freemi.entity.bse.BsePaymentStatus;
import com.freemi.entity.bse.BseRegistrationMFD;
import com.freemi.entity.bse.BseXipISipOrderEntry;
import com.freemi.entity.database.UserBankDetails;
import com.freemi.entity.investment.MFCustomers;
import com.freemi.entity.investment.Allotmentstatement;
import com.freemi.entity.investment.BseOrderEntryResponse;
import com.freemi.entity.investment.SelectMFFund;
import com.freemi.services.interfaces.BseRestClientService;
import com.freemi.services.interfaces.InvestmentConnectorBseInterface;

@Service
public class BseConnectorsImpl implements InvestmentConnectorBseInterface {

	private static final Logger logger = LogManager.getLogger(BseConnectorsImpl.class);

	@Autowired
	Environment env;

	@Autowired
	BseRestClientService bseRestClientService; 

	@Override
	public String saveCustomerRegistration(MFCustomers registrationForm, String field1) {
		String result = "";
		logger.info("Registration details processing to BSE platform begins");
		if(env.getProperty(CommonConstants.BSE_ENABLED).equalsIgnoreCase("Y")){
			try{
				logger.info("Convert form data to BSE format data");
				BseRegistrationMFD bseregistrationForm=  BseBeansMapper.InvestmentFormToBseBeans(registrationForm);
				logger.info("Begin BSE service invoke process");
				result= bseRestClientService.registerUser(bseregistrationForm);
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
	public BseOrderEntryResponse processCustomerTransactionbsaRequest(SelectMFFund selectedFund, String transactionNumber,String mandateId) {
		String result = "";
		BseOrderEntryResponse bseOrderResp= new BseOrderEntryResponse();

		logger.info("Transaction details processing to BSE platform begins of category- "+ selectedFund.getTransactionType() + " for customer: "+ selectedFund.getClientID());
		if(env.getProperty(CommonConstants.BSE_ENABLED).equalsIgnoreCase("Y")){

			if(selectedFund.getTransactionType().equals("REDEEM")){

				try{
					logger.info("Convert form data to Folio to redeem form");
					BseOrderEntry bseMfOrderForm=  BseBeansMapper.redeeemOrderToBseBeans(selectedFund, transactionNumber);
					logger.info("Begin BSE service invoke process for redeem transaction: "+ selectedFund.getTransactionID());
					result = bseRestClientService.purchaseRequestProcess(bseMfOrderForm);
					BseBeansMapper.redeeemResponseToBean(bseOrderResp, result, selectedFund.getBseRefNo());
				}catch(Exception e){
					logger.error("Failed during proceesing of BSE REDEEM details to BSE platform",e);
					//			result="BSE_CONN_FAIL";
					bseOrderResp.setSuccessFlag("BSE_CONN_FAIL");
				}

			}else{

				//		Order Entry processing for lumpsum - fresh
				if(selectedFund.getInvestype().equals("LUMPSUM")){
					try{
						logger.info("Convert form data to BSE lumpsum format data");
						BseOrderEntry bseMfOrderForm=  BseBeansMapper.transactionOrderToBseBeans(selectedFund, transactionNumber);
						logger.info("Begin BSE service invoke process");
						result = bseRestClientService.purchaseRequestProcess(bseMfOrderForm);
						BseBeansMapper.transactionOrderReponseToBeans(bseOrderResp, result, selectedFund.getBseRefNo());
					}catch(Exception e){
						logger.error("Failed during proceesing of BSE customer details to BSE platform",e);
						//			result="BSE_CONN_FAIL";
						bseOrderResp.setSuccessFlag("BSE_CONN_FAIL");
					}
				}
				//		Order process request for SIP
				else if(selectedFund.getInvestype().equals("SIP")){
					logger.info("Convert form data to BSE SIP Request format data");
					try{
						BseXipISipOrderEntry bseMfSipOrderForm=  BseBeansMapper.transactionXSIPISIPOrderToBseBeans(selectedFund, transactionNumber,mandateId);
						logger.info("Begin BSE service invoke process");
						result = bseRestClientService.purchaseCancelXSIPISIPRequestProcess(bseMfSipOrderForm);
						BseBeansMapper.siptransactionOrderReponseToBeans(bseOrderResp, result, selectedFund.getBseRefNo());
					}catch(Exception e){
						logger.error("Failed during proceesing of BSE X-SIP I-SIP registration details to BSE platform",e);
						//			result="BSE_CONN_FAIL";
						bseOrderResp.setSuccessFlag("BSE_CONN_FAIL");
					}
				}else{
					logger.warn("Unsupported transaction type selected- "+ selectedFund.getInvestype());
					bseOrderResp.setSuccessFlag("UNSUPPORTED_INV_TYPE");
					bseOrderResp.setBsereMarks("Invest type not supported.");
				}
			}
		}else{
			logger.info("BSE Investment is currently disabled");
			//			bseOrderResp.setClientCode(CommonConstants.BSE_API_SERVICE_DISABLED);
			bseOrderResp.setSuccessFlag(CommonConstants.BSE_API_SERVICE_DISABLED);
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
				response = bseRestClientService.purchasePaymentLink(request);
				BseBeansMapper.bseOrderPayemtResultMapper(orderResponse, response);
			}catch(Exception e){
				logger.error("Failed to request pending payments url",e);
				//			result="BSE_CONN_FAIL";
				orderResponse.setStatusCode("101");
			}
		}else{
			logger.info("BSE connction is currently disabled");
			orderResponse.setStatusCode(CommonConstants.BSE_API_SERVICE_DISABLED);
		}
		return orderResponse;
	}

	@Override
	public BseAOFUploadResponse uploadAOFFormtoBSE(String fileName, String aoffolderLocation, String clientCode) {
		logger.info("Convert AOF form details to BSE format and process for file of PAN- "+ fileName);
		BseAOFUploadResponse aofresp = new BseAOFUploadResponse();
		byte[] filearray = null;
		Path filepath = Paths.get(aoffolderLocation, fileName+".pdf");
		Path tifffilepath = Paths.get(aoffolderLocation, fileName+".tiff");
		PDDocument document = null;
		logger.info("Looking for AOF file- "+ filepath);
		if(Files.exists(filepath)){
			try {
				//				filearray = Files.readAllBytes(new File(filepath.toString()).toPath());

				String extension = "tiff";
				document = PDDocument.load(new File(filepath.toString()));
				PDFRenderer pdfRenderer = new PDFRenderer(document);

				BufferedImage bim = pdfRenderer.renderImageWithDPI(0, 300, ImageType.RGB);

				ImageIOUtil.writeImage(bim, String.format(tifffilepath.toString(), extension), 300);


				document.close();

				logger.info("Converted PDF file to tiff for customer- "+ clientCode);

				filearray = Files.readAllBytes(new File(tifffilepath.toString()).toPath());
				String encodedString = Base64.getEncoder().encodeToString(filearray);
				logger.debug("Uploading file to bse- "+ tifffilepath.toString());
				logger.info("AOF image converted to bse base64 format for client- "+ clientCode);
				BseAOFUploadRequest r = BseBeansMapper.AOFFormtoBseBeanMapper(filearray,encodedString, clientCode);
				
				String responseText = bseRestClientService.uploadAOF(r);
				logger.info("Response for AOF upload against customer : "+ clientCode + " : "+ responseText);
				BseBeansMapper.bseAOFUploadResponsetoBean(aofresp, responseText);
				if(!aofresp.getStatusCode().equalsIgnoreCase("100")){
					logger.info("AOF upload status not successul.Reason- "+ aofresp.getStatusMessage());
				}
			} catch (IOException e) {
				logger.error("Failed to query BSE to upload AOF form", e);
				aofresp.setStatusCode("999");
				aofresp.setStatusMessage("FAILED_CONN");
				try{
					document.close();
				}catch(Exception ex){
					logger.error("Failed to close AOF file", ex);
				}
			}catch(Exception ex1){
				logger.info("AOF image conversion error- ", ex1);
				aofresp.setStatusCode("999");
				aofresp.setStatusMessage("INTERNAL_ERROR_AOF");
			}
		}else{
			logger.info("AOF File does not exist for upload!");
			aofresp.setStatusCode("998");
			aofresp.setStatusMessage("File not found!");
		}
		return aofresp;
	}

	@Override
	public String BseOrderPaymentStatus(String clientId, String orderNo) {
		logger.info("Get payment status fro payment order- "+ orderNo);
		String response = "101|Failed to check status";
		BseOrderPaymentResponse orderResponse = new BseOrderPaymentResponse();
		if(env.getProperty(CommonConstants.BSE_ENABLED).equalsIgnoreCase("Y")){
			try{
				BsePaymentStatus requestForm=  BseBeansMapper.BsePaymentStatusRequestToBse(clientId, orderNo);
				logger.info("Begin BSE service invoke process for payment status");
				response = bseRestClientService.orderPaymentStatus(requestForm);
				//				BseBeansMapper.bseOrderPayemtResultMapper(orderResponse, response);


			}catch(Exception e){
				logger.error("Failed during proceesing of BSE SIP registration details to BSE platform",e);
				//			result="BSE_CONN_FAIL";
				response= "ERROR";
			}
		}else{
			logger.info("BSE connction is currently disabled");
			orderResponse.setStatusCode(CommonConstants.BSE_API_SERVICE_DISABLED);
		}
		return response;
	}

	@Override
	public BseApiResponse emandateRegistration(UserBankDetails bankDetails,String mandateType, String amount, String clientCode, Date startDate, Date endDate) {
		logger.info("Get bank account e-mandate registered for client id- "+ clientCode + " : bank no- " + bankDetails.getAccountNumber().substring(bankDetails.getAccountNumber().length()-4));
		String response = "";
		BseApiResponse bseresponse = new BseApiResponse();
		if(env.getProperty(CommonConstants.BSE_ENABLED).equalsIgnoreCase("Y")){
			try{
				//				BsePaymentStatus requestForm=  BseBeansMapper.BsePaymentStatusRequestToBse(clientId, orderNo);
				BseEMandateRegistration registerForm = BseBeansMapper.bankDetailsToBseMandateBeans(bankDetails,mandateType, amount, clientCode,startDate,endDate);
				logger.info("Begin BSE service invoke process for payment status");
				response = bseRestClientService.eMandateRegistration(registerForm);
				bseresponse= BseBeansMapper.emandateRegResponseToBean(response);

			}catch(Exception e){
				logger.error("Failed during proceesing of BSE SIP registration details to BSE platform",e);
				//			result="BSE_CONN_FAIL";
				response= "ERROR";
			}
		}else{
			logger.info("BSE connction is currently disabled");
			bseresponse.setStatusCode(CommonConstants.BSE_API_SERVICE_DISABLED);
			bseresponse.setRemarks("BSE Service is disabled");
		}
		return bseresponse;
	}

	@Override
	public String generateOTPForLogin(String userid) {
		/*logger.info("Get OTP for Freemi Portal Login for user id"+ userid );
		String response = "";
		BseApiResponse bseresponse = new BseApiResponse();
		if(env.getProperty(CommonConstants.BSE_ENABLED).equalsIgnoreCase("Y")){
			try{
//				BsePaymentStatus requestForm=  BseBeansMapper.BsePaymentStatusRequestToBse(clientId, orderNo);
				BseEMandateRegistration registerForm = BseBeansMapper.bankDetailsToBseBeans(bankDetails, amount, clientCode,startDate,endDate);
				logger.info("Begin BSE service invoke process for payment status");
				response = bseRestClientService.eMandateRegistration(registerForm);
				bseresponse= BseBeansMapper.emandateRegResponseToBean(response);

			}catch(Exception e){
				logger.error("Failed during proceesing of BSE SIP registration details to BSE platform",e);
				//			result="BSE_CONN_FAIL";
				response= "ERROR";
			}
		}else{
			logger.info("BSE connction is currently disabled");
			bseresponse.setStatusCode(CommonConstants.BSE_API_SERVICE_DISABLED);
			bseresponse.setRemarks("BSE Service is disabled");
		}*/
		return null;
	}

	@Override
	public String verifyOTPForLogin(String userid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String checkForExitingPAN(String pan) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BseApiResponse fatcaDeclaration(MFCustomers registrationForm, String field1) {
		logger.info("FATCA declaration process received for customer- "+ registrationForm.getPan1());
		String response = "";
		BseApiResponse fatcaResponse = new BseApiResponse();
		if(env.getProperty(CommonConstants.BSE_ENABLED).equalsIgnoreCase("Y")){
			try{
				//				BsePaymentStatus requestForm=  BseBeansMapper.BsePaymentStatusRequestToBse(clientId, orderNo);
				BseFatcaForm fatcaForm = BseBeansMapper.InvestmentFormToBseFATCABeans(registrationForm,field1);
				logger.info("Begin BSE service invoke process for FATCA declaration for customer-" + registrationForm.getPan1());

				response = bseRestClientService.fatcaDeclaration(fatcaForm);
				logger.info("Response from BSE declaration- " + response);
				BseBeansMapper.fatcaUploadResponseToBean(fatcaResponse, response);

			}catch(Exception e){
				logger.error("Failed during proceesing of BSE FATCA registration details to BSE platform",e);
				fatcaResponse.setResponseCode("999");
				fatcaResponse.setRemarks("ERROR");
			}
		}else{
			logger.info("BSE connction is currently disabled");
			fatcaResponse.setResponseCode(CommonConstants.BSE_API_SERVICE_DISABLED);
			fatcaResponse.setRemarks("BSE Service is disabled");
		}
		return fatcaResponse;
	}

	@Override
	public BsePanStatusResponse panStatusCheck(String panNumber) {
		logger.info("Checking PAN status in BSE portal "+ panNumber);
		String response = "";
		BsePanStatusResponse statusResponse = new BsePanStatusResponse();
		if(env.getProperty(CommonConstants.BSE_ENABLED).equalsIgnoreCase("Y")){
			try{
				//				BsePaymentStatus requestForm=  BseBeansMapper.BsePaymentStatusRequestToBse(clientId, orderNo);
				logger.info("Begin BSE service invoke process for FATCA declaration for customer-" + panNumber);

				response = bseRestClientService.panStatusCheck(panNumber);
				logger.info("Response from BSE declaration- " + response);
				BseBeansMapper.panStatusResponsetoBean(statusResponse, response);

			}catch(Exception e){
				logger.error("Failed during proceesing of BSE FATCA registration details to BSE platform",e);
				statusResponse.setResponseCode("999");
				statusResponse.setBseRemarks("ERROR");
			}
		}else{
			logger.info("BSE connction is currently disabled");
			statusResponse.setResponseCode(CommonConstants.BSE_API_SERVICE_DISABLED);
			statusResponse.setBseRemarks("BSE Service is disabled");
		}
		return statusResponse;
	}

	@Override
	public BseOrderEntryResponse cancelSipOrder(SelectMFFund selectedFund, String uniqueReferenceNo,
			String orderno, String refno) {
		String result = "";
		BseOrderEntryResponse bseOrderResp= new BseOrderEntryResponse();

		logger.info("Start mapping data to bean for cancellation for transaction ID- "+ uniqueReferenceNo);
		if(env.getProperty(CommonConstants.BSE_ENABLED).equalsIgnoreCase("Y")){
			if(selectedFund.getInvestype().equals("SIP")){
				logger.info("Selected transaction type is SIP ");
				try{
					BseXipISipOrderEntry bseMfSipOrderForm=  BseBeansMapper.cancelXSIPISIPOrderToBseBeans(selectedFund, uniqueReferenceNo,orderno, refno);
					logger.info("Begin BSE service invoke process for CXL");
					result = bseRestClientService.purchaseCancelXSIPISIPRequestProcess(bseMfSipOrderForm);
					BseBeansMapper.siptransactionOrderReponseToBeans(bseOrderResp, result, selectedFund.getBseRefNo());
				}catch(Exception e){
					logger.error("Failed during proceesing of BSE X-SIP I-SIP cancellation details to BSE platform",e);
					bseOrderResp.setSuccessFlag("BSE_CONN_FAIL");
				}
			}else{
				logger.warn("Mismtach in transaction type of selected record. DB record- "+ selectedFund.getInvestype());
				bseOrderResp.setSuccessFlag("UNSUPPORTED_INV_TYPE");
				bseOrderResp.setBsereMarks("Invest type do not match with record.");
			}
		}else{
			logger.info("BSE Investment is currently disabled");
			//			bseOrderResp.setClientCode(CommonConstants.BSE_API_SERVICE_DISABLED);
			bseOrderResp.setSuccessFlag(CommonConstants.BSE_API_SERVICE_DISABLED);
			bseOrderResp.setBsereMarks("INV_DISABLED");
		}
		return bseOrderResp;
	}

	@Override
	public List<Allotmentstatement> getAllotmentstatement(String fromdate, String todate, String orderstatus, String ordertype,
			String settlementtype) {

		List<Allotmentstatement> responsedata = null;
		try {

			String response = bseRestClientService.getallotmentstatement(fromdate, todate, orderstatus, ordertype, settlementtype);
			if(response!=null ) {
				responsedata= BseBeansMapper.allotmentstatementdata(response);
			}else {
				logger.info("No respond to process..");
			}
		}catch(Exception e) {
			logger.error("getAllotmentstatement(): Error processing request",e);
		}

		return responsedata;
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
				String s= bseRestClientService.uploadAOF(r);
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
