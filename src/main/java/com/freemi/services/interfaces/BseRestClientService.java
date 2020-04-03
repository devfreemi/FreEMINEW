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

@Component
public interface BseRestClientService {
	
	public String otpGeneration(String userid);
	public String otpverify(String userid,String otp);
	public String registerUser(BseRegistrationMFD form);
	public String purchaseRequestProcess(BseOrderEntry form);
	public String purchaseSIPRequestProcess(BseSipOrderEntry form);
	public String purchaseCancelXSIPISIPRequestProcess(BseXipISipOrderEntry form);
	public String purchasePaymentLink(BseOrderPaymentRequest form);
	public String orderPaymentStatus(BsePaymentStatus form);
	public String eMandateRegistration(BseEMandateRegistration form);
	public String fatcaDeclaration(BseFatcaForm form);
	public String panStatusCheck(String panNumber);
	public String uploadAOF(BseAOFUploadRequest form);

}
