package com.freemi.services.interfaces;

import org.springframework.stereotype.Component;

import com.freemi.entity.bse.BseAOFUploadRequest;
import com.freemi.entity.bse.BseEMandateRegistration;
import com.freemi.entity.bse.BseFatcaForm;
import com.freemi.entity.bse.BseOrderEntry;
import com.freemi.entity.bse.BseOrderPaymentRequest;
import com.freemi.entity.bse.BsePaymentStatus;
import com.freemi.entity.bse.BseRegistrationMFD;
import com.freemi.entity.bse.BseSipOrderEntry;
import com.freemi.entity.bse.BseXipISipOrderEntry;
import com.freemi.entity.bse.PauseSIP;
import com.freemi.entity.bse.PauseSIPResponse;
import com.freemi.entity.bse.Uccregisterresponse;
import com.freemi.entity.investment.Emandatestaus;

@Component
public interface BseRestClientService {
	
	@Deprecated
	public String otpGeneration(String userid);
	@Deprecated
	public String otpverify(String userid,String otp);
	
	@Deprecated
	public String registerUser(BseRegistrationMFD form);
	
	public String registeruserv2(BseRegistrationMFD form);
	public String purchaseRequestProcess(BseOrderEntry form);
	public String purchaseSIPRequestProcess(BseSipOrderEntry form);
	public String purchaseCancelXSIPISIPRequestProcess(BseXipISipOrderEntry form);
	public String purchasePaymentLink(BseOrderPaymentRequest form);
	public String orderPaymentStatus(BsePaymentStatus form);
	public String eMandateRegistration(BseEMandateRegistration form);
	public String fatcaDeclaration(BseFatcaForm form);
	public String panStatusCheck(String panNumber);
	public String uploadAOF(BseAOFUploadRequest form);
	public String getallotmentstatement(String fromdate, String todate, String orderstatus,
		    String ordertype, String settlementtype);
	
	public String getifscbankdetails(String ifsc);
	
	public String getemandateauthrul(String clientid, String mandateid);
	
	public Emandatestaus getmandatestatus(String clientid, String mandateid);
	
	public PauseSIPResponse pausexsip(PauseSIP requestdata);
}
