package com.freemi.services.interfaces;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.freemi.entity.bse.BseAOFUploadResponse;
import com.freemi.entity.bse.BseApiResponse;
import com.freemi.entity.bse.BseOrderPaymentRequest;
import com.freemi.entity.bse.BseOrderPaymentResponse;
import com.freemi.entity.bse.BsePanStatusResponse;
import com.freemi.entity.bse.Bsepay;
import com.freemi.entity.bse.Nomineeregistrationresponse;
import com.freemi.entity.bse.PauseSIP;
import com.freemi.entity.bse.PauseSIPResponse;
import com.freemi.entity.bse.Paymentgatewayresponse;
import com.freemi.entity.database.UserBankDetails;
import com.freemi.entity.investment.MFCustomers;
import com.freemi.entity.investment.Nominee2farequest;
import com.freemi.entity.investment.Nominee2faresponse;
import com.freemi.entity.investment.Nomineeverification;
import com.freemi.entity.investment.Allotmentstatement;
import com.freemi.entity.investment.BseMandateDetails;
import com.freemi.entity.investment.BseOrderEntryResponse;
import com.freemi.entity.investment.Emandatestaus;
import com.freemi.entity.investment.SelectMFFund;

@Service
public interface InvestmentConnectorBseInterface {
	
	public BsePanStatusResponse panStatusCheck(String panNumber);
	
	public String generateOTPForLogin(String userid);
	
	public String verifyOTPForLogin(String userid);
	
	public String checkForExitingPAN(String pan);
	
	public BseApiResponse fatcaDeclaration(MFCustomers registrationForm,String field1);
	
	public String saveCustomerRegistration(MFCustomers registrationForm,String field1);
	
	public BseAOFUploadResponse uploadAOFFormtoBSE(String mobileNumber, String aoffolderLocation, String clientCode, String filename);
	
	public BseOrderEntryResponse processCustomerTransactionbsaRequest(SelectMFFund selectedFund, String transactionNumber, String mandateId);
	
	public BseOrderEntryResponse cancelSipOrder(SelectMFFund selectedFund, String uniqueReferenceNo, String orderno, String refno);
	
	public BseOrderPaymentResponse getPaymentUrl(BseOrderPaymentRequest request);
	
	public Paymentgatewayresponse getPaymentGetway(Bsepay request);
	
	public String BseOrderPaymentStatus(String clientId, String orderNo);
	
	public BseApiResponse emandateRegistration(BseMandateDetails mandatedetails, UserBankDetails bankDetails,String mandateType, String amount, String clientCode, Date startDate, Date endDate);
	
	public List<Allotmentstatement> getAllotmentstatement(String fromdate, String todate, String orderstatus,
		    String ordertype, String settlementtype);

	public String getifscdetails(String ifsc);
	
	public BseApiResponse getmandateauthurl(String clientid, String mandateid);
	
	public Emandatestaus getmandatestatus(String clientid, String mandateid);
	
	public PauseSIPResponse pausesip(String clientid,String registrationno, String noofinstallments);
	
	public Nomineeregistrationresponse verifynominee(Nomineeverification nomineedata, String regtype);
	
	public Nominee2faresponse nomineeauthenticate(Nominee2farequest request);
	
}
