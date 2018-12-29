package com.freemi.controller.implementations;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import com.freemi.common.util.CommonConstants;
import com.freemi.common.util.UIMessages;
import com.freemi.controller.interfaces.FolioManagementContoller;
import com.freemi.database.service.DatabaseEntryManager;
import com.freemi.database.service.FreemiServiceInterface;
import com.freemi.entity.Birla.SavePostPurchaseMultiRequestOutput;
import com.freemi.entity.Birla.SavePostSIPMultipleSchemesOutput;
import com.freemi.entity.Birla.ValidateAadhaarOTPOutput;
import com.freemi.entity.Birla.ValidateAadhaarOutput;
import com.freemi.entity.Birla.ValidatePANOutput;
import com.freemi.entity.general.ClientSystemDetails;
import com.freemi.entity.investment.AadhaarVerifyStatus;
import com.freemi.entity.investment.FolioCreationStatus;
import com.freemi.entity.investment.MFInvestForm;
import com.freemi.entity.investment.PanValidationStatus;
import com.freemi.services.partners.Impl.BirlaConnectorsImpl;
import com.freemi.services.partners.Impl.BirlaConnectorsImplTest;
import com.freemi.services.partners.Interfaces.InvestmentConnectorInterfaces;

@Service
public class FolioManagementImpl implements FolioManagementContoller {
	private static final Logger logger = LogManager.getLogger(FolioManagementImpl.class);


	@Autowired
	private DatabaseEntryManager databaseEntryManager ;

	@Autowired
	private Environment env;

	@Override
	public Object validatePANfromPartners(String pan, HttpSession session, ClientSystemDetails clientSystem) {

		ValidatePANOutput panOutput = null;
		PanValidationStatus panStatus = new PanValidationStatus();

		InvestmentConnectorInterfaces birla = new BirlaConnectorsImpl();
//		InvestmentConnectorInterfaces birla = new BirlaConnectorsImplTest();

		List<PanValidationStatus> panStatusList = null;

		if(env.getProperty(CommonConstants.ENV_INVESTMENT_ENABLED).equalsIgnoreCase("Y"))
		{
			try{
				logger.info("Checking local record for PAN status received from vendors from earlier check- "+ pan);
				panStatusList = databaseEntryManager.getkycfromsystem(pan);

				if(panStatusList.size()==0){
					logger.info("PAN kyc record not found in Freemi database. Querying the vendors for kyc status- "+ pan);

					panOutput= (ValidatePANOutput) birla.validatePAN(null, pan, null,clientSystem);

					if(panOutput!=null){
						logger.info("Result for user request PAN validation from Birla- "+ panOutput.getPanData().getReturnCode() + " -> "+ panOutput.getPanData().getReturnMsg());

						if(panOutput.getPanData().getReturnCode().equalsIgnoreCase("1") || panOutput.getPanData().getIsEKYCVerified().equalsIgnoreCase("Y") ){
							panStatus.setPanHolderName(panOutput.getPanData().getNameAsPerPAN());
							panStatus.setPanNumber(panOutput.getPanData().getPanNumber().toUpperCase());
							panStatus.setIsFactaVerified(panOutput.getPanData().getIsFATCAVerified());
							panStatus.setIsKYCVerified(panOutput.getPanData().getIsEKYCVerified());
							panStatus.setMessage(panOutput.getPanData().getReturnMsg());
							panStatus.setReturnCode(panOutput.getPanData().getReturnCode());
							panStatus.setVerifedFrom(501);
							panStatus.setVerificationDate(new Date());
							panStatus.setRequestorIp(clientSystem.getClientIpv4Address());
							panStatus.setRequestorOs(clientSystem.getClientBrowser());

							//Save record into local database
							databaseEntryManager.savekycstatus(panStatus);

						}else if(panOutput.getPanData().getIsEKYCVerified().equalsIgnoreCase("N")){
							panStatus.setPanNumber(panOutput.getPanData().getPanNumber());
							panStatus.setIsKYCVerified(panOutput.getPanData().getIsEKYCVerified());
							panStatus.setMessage(panOutput.getPanData().getReturnMsg());
							panStatus.setReturnCode(panOutput.getPanData().getReturnCode());
						}
						else{
							panStatus.setIsKYCVerified("E");
							panStatus.setMessage(UIMessages.PAN_VALIDATION_ERROR);
						}

					}else{
						logger.info("Error validating");
						panStatus = new PanValidationStatus();
						panStatus.setIsKYCVerified("E");
						panStatus.setMessage(UIMessages.PAN_VALIDATION_ERROR);
					}
				}else{
					panStatus=panStatusList.get(0);
				}

			}catch(Exception e){
				e.getMessage();
			}

		}else{
			logger.info("Investment process disabled");
			panStatus = new PanValidationStatus();
			panStatus.setIsKYCVerified("E");
			panStatus.setMessage("Services are currently deactivated.");
		}


		return panStatus;
	}

	@Override
	public Object generateNewFolio(MFInvestForm mfInvestForm,ValidateAadhaarOTPOutput aadhaarDetails, HttpSession session, ClientSystemDetails clientSystem) {
		// Call required AMC house to generate new FOlio number ang get response, convert the common object and pass it back to controller

		InvestmentConnectorInterfaces birla = new BirlaConnectorsImpl();
//		InvestmentConnectorInterfaces birla = new BirlaConnectorsImplTest();


		FolioCreationStatus status = new FolioCreationStatus();

		if(env.getProperty(CommonConstants.ENV_INVESTMENT_ENABLED).equalsIgnoreCase("Y"))
		{
			try{

				//			Birla connector
				//			if(mfInvestForm.getSelectedFund().getFundName().contains("birla")){
				//				status = birla.generateNewFolio(mfInvestForm,null);
				status =  (FolioCreationStatus) birla.processNewProfile(mfInvestForm, aadhaarDetails,clientSystem);
				//			}


				//

				/*CustomerKYSStatus kycdata = new CustomerKYSStatus();
			kycdata.setPanNumber("cqops7539c");
			kycdata.setPanHolderName("DEBASISH");
			kycdata.setKyc_status('Y');
			kycdata.setFacta_status('N');

			RepositoryOperation op = new RepositoryOperation();
			boolean flag = op.savekycstatus(kycdata);
			System.out.println("data saved- "+ flag);*/

				if(status!=null){
					if(status.isTransactionSuccessful()){

						status.setPurchaseDate(new Date());
						logger.info("Save successful transaction to database..");
						databaseEntryManager.saveRegistryPurchaseRecords(status);

						//send mail
					}
				}

			}catch(HttpServerErrorException exception){
				logger.error("Error processing form- ", exception.getStatusCode());

			}catch(Exception e){

				status.setTransactionSuccessful(false);
				status.setMessage("Error processing data. Mandatory data missing");
			}
		}else{
			status.setTransactionSuccessful(false);
			status.setMessage("Services are currently deactivated");
		}

		return status;
	}

	@Override
	public Object validateAADHAARNmber(String PAN, String aadhaar, String connector, HttpSession session, ClientSystemDetails clientSystem) {
		InvestmentConnectorInterfaces birla = new BirlaConnectorsImpl();
//		InvestmentConnectorInterfaces birla = new BirlaConnectorsImplTest();

		AadhaarVerifyStatus verifyStatus = new AadhaarVerifyStatus();
		ValidateAadhaarOutput output=  (ValidateAadhaarOutput) birla.validateAADHAARNumber(aadhaar, session,clientSystem);
		if(output==null){
			verifyStatus.setResponseCode("0");
			verifyStatus.setResponseMessage("Unable to verify aadhaar currently");
		}else{
			verifyStatus.setResponseCode(output.getReturnCode());
			verifyStatus.setResponseMessage(output.getReturnMsg());
			verifyStatus.setAadhaarReturnMessage(output.getAadharReturnMessage());
			verifyStatus.setRefId(output.getRefID());
			logger.info("RefId - "+ output.getRefID());
		}

		return verifyStatus;
	}

	@Override
	public Object validateAADHAAROTP(String refId, String aadhaar, String otp, String connector, HttpSession session, ClientSystemDetails clientSystem) {

		InvestmentConnectorInterfaces birla = new BirlaConnectorsImpl();
		ValidateAadhaarOTPOutput output= (ValidateAadhaarOTPOutput) birla.ValidateAADHAAROTP(aadhaar, otp, Integer.valueOf(refId), null,clientSystem);

		return output;
	}

	@Override
	public Object getBankDetails(String ifsc, String location, String bankName, HttpSession session, ClientSystemDetails clientSystem) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object savePrePurchaseSchemeDetails(MFInvestForm mfInvestForm, HttpSession session, ClientSystemDetails clientSystem) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object savePostPaymentPurchaseDetails(MFInvestForm mfInvestForm,FolioCreationStatus folioStatus, HttpSession session, ClientSystemDetails clientSystem) {

		logger.info("Processing payment details back to AMC for folio - "+ folioStatus.getFolioNumber() + " : Customer- "+ mfInvestForm.getPAN());

		InvestmentConnectorInterfaces birla = new BirlaConnectorsImpl();
//		InvestmentConnectorInterfaces birla = new BirlaConnectorsImplTest();

		if(mfInvestForm.getSelectedFund().getInvestType().equalsIgnoreCase("TARGET_PLAN")){
			// FOR LUMPSUM BASED TRANSACTIONS
			logger.info("LUMPSUM based purchase was selected.");
			SavePostPurchaseMultiRequestOutput lumpsumoutput=  (SavePostPurchaseMultiRequestOutput) birla.postLumsumPurchaseSave(folioStatus, "Y", null, mfInvestForm, null, clientSystem);
			if(lumpsumoutput!=null){
				if(lumpsumoutput.getReturnCode().equalsIgnoreCase("1")){
					logger.info("Post LUMSPUM transaction is carried for portfolio- "+ folioStatus.getFolioNumber() + " : TRANS_NO: "+ lumpsumoutput.getTransNo() + " : PayTrxnRefNo- "+ lumpsumoutput.getPaymentStatus());
					folioStatus.setSavePostSIPSuccessfull(true);
					folioStatus.setPayTrxnRefNo("NA");

					if("Y".equalsIgnoreCase(lumpsumoutput.getTransactionDetails().get(0).getCamsStatus())){

						logger.info("Cams Entry updated successfully: scheme reference ID: "+lumpsumoutput.getTransactionDetails().get(0).getSchemeReferenceId() +" : CAMS trans no: "+ (lumpsumoutput.getTransactionDetails().get(0)).getCamsTrxn_No());
						folioStatus.setCamsEntrySuccessful(true);

						folioStatus.setCamsEntryDate((lumpsumoutput.getTransactionDetails().get(0)).getCamsEntryDate());
						folioStatus.setCamsExpiryDate((lumpsumoutput.getTransactionDetails().get(0)).getCams_exp_date());
						folioStatus.setCamsStatus((lumpsumoutput.getTransactionDetails().get(0)).getCamsStatus());
						folioStatus.setCamsTransDate((lumpsumoutput.getTransactionDetails().get(0)).getCamsTransDate());
						folioStatus.setCamsTransNo((lumpsumoutput.getTransactionDetails().get(0)).getCamsTrxn_No());

					}else{
						logger.info("Failed to update CAMs entry for folio - "+ folioStatus.getFolioNumber() + " : TRANS_NO: "+ lumpsumoutput.getTransNo() + " : CAMS error code- "+ (lumpsumoutput.getTransactionDetails().get(0)).getCams_ReturnCode() + ": Return message- "+ (lumpsumoutput.getTransactionDetails().get(0)).getCams_ReturnMsg());
						folioStatus.setCamsEntrySuccessful(false);
					}
					folioStatus.setCamsStatus((lumpsumoutput.getTransactionDetails().get(0)).getCamsStatus());
					folioStatus.setCamsReturnCode((lumpsumoutput.getTransactionDetails().get(0)).getCams_ReturnCode());
					folioStatus.setCamsReturnMessage((lumpsumoutput.getTransactionDetails().get(0)).getCams_ReturnMsg());


				}else{
					logger.info("Unable to save postSIP transaction for folio- "+ folioStatus.getFolioNumber());
					logger.info("Unable to save post request transaction details to AMC: "+ " Error- "+ lumpsumoutput.getReturnCode() + " Reason from AMC: "+ lumpsumoutput.getReturnMsg());
					folioStatus.setSavePostSIPSuccessfull(false);
					folioStatus.setSavePostSIPMessage(lumpsumoutput.getReturnMsg());
				}
			}else {
				logger.info("Transaction failed for save LUMPSUM post transaction...");

			}
		}else{

			// SIP based transactions
			SavePostSIPMultipleSchemesOutput postTransaction=  (SavePostSIPMultipleSchemesOutput) birla.savePostSIPMultipleSchemes(folioStatus, "Y", null, mfInvestForm, null, clientSystem);
			if(postTransaction!=null) {
				if(postTransaction.getReturnCode().equalsIgnoreCase("1")){
					logger.info("Post SIP transaction is carried for portfolio- "+ folioStatus.getFolioNumber() + " : TRANS_NO: "+ postTransaction.getTrans_No() + " : PayTrxnRefNo- "+ postTransaction.getPayTrxnRefNo());
					folioStatus.setSavePostSIPSuccessfull(true);
					folioStatus.setPayTrxnRefNo(postTransaction.getPayTrxnRefNo()!=null?postTransaction.getPayTrxnRefNo():"");

					if("Y".equalsIgnoreCase((postTransaction.getTransactionDetails().get(0)).getCamsStatus())){

						logger.info("Cams Entry updated successfully: SIPTrxnRefNo: "+postTransaction.getSipTrxnRefNo() +" : CAMS trans no: "+ (postTransaction.getTransactionDetails().get(0)).getCamsTrxn_No());
						folioStatus.setCamsEntrySuccessful(true);

						folioStatus.setCamsEntryDate((postTransaction.getTransactionDetails().get(0)).getCamsEntryDate());
						folioStatus.setCamsExpiryDate((postTransaction.getTransactionDetails().get(0)).getCams_EXP_DATE());
						folioStatus.setCamsStatus((postTransaction.getTransactionDetails().get(0)).getCamsStatus());
						folioStatus.setCamsTransDate((postTransaction.getTransactionDetails().get(0)).getCamsTransDate());
						folioStatus.setCamsTransNo((postTransaction.getTransactionDetails().get(0)).getCamsTrxn_No());

					}else{
						logger.info("Failed to update CAMs entry for folio - "+ folioStatus.getFolioNumber() + " : TRANS_NO: "+ postTransaction.getTrans_No() + " : CAMS error code- "+ (postTransaction.getTransactionDetails().get(0)).getCams_ReturnCode() + ": Return message- "+ (postTransaction.getTransactionDetails().get(0)).getCams_ReturnMsg());
						folioStatus.setCamsEntrySuccessful(false);
					}
					folioStatus.setCamsStatus((postTransaction.getTransactionDetails().get(0)).getCamsStatus());
					folioStatus.setCamsReturnCode((postTransaction.getTransactionDetails().get(0)).getCams_ReturnCode());
					folioStatus.setCamsReturnMessage((postTransaction.getTransactionDetails().get(0)).getCams_ReturnMsg());


				}else{
					logger.info("Unable to save postSIP transaction for folio- "+ folioStatus.getFolioNumber());
					logger.info("Unable to save post request transaction details to AMC: "+ " Error- "+ postTransaction.getReturnCode() + " Reason from AMC: "+ postTransaction.getReturnMsg());
					folioStatus.setSavePostSIPSuccessfull(false);
					folioStatus.setSavePostSIPMessage(postTransaction.getReturnMsg());
				}
			}else {
				logger.info("Transaction failed for save SIP...");

			}
		}

		return folioStatus;
	}

	@Override
	public Object lumpSumPurchase(MFInvestForm mfInvestForm, FolioCreationStatus folioStatus, HttpSession session,
			ClientSystemDetails clientSystem) {
		// TODO Auto-generated method stub
		return null;
	}




}
