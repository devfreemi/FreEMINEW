package com.freemi.controller.interfaces;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import com.freemi.entity.birla.ValidateAadhaarOTPOutput;
import com.freemi.entity.general.ClientSystemDetails;
import com.freemi.entity.investment.FolioCreationStatus;
import com.freemi.entity.investment.MFInvestForm;

@Component
public interface FolioManagementContoller {
	
	
	public Object validatePANfromPartners(String PAN, HttpSession session, ClientSystemDetails clientSystem);
	public Object validateAADHAARNmber(String PAN, String aadhaar, String connector, HttpSession session, ClientSystemDetails clientSystem);
	public Object validateAADHAAROTP(String variable1, String aadhaar, String otp, String connector, HttpSession session, ClientSystemDetails clientSystem);
	
	public Object getBankDetails(String ifsc, String location, String bankName, HttpSession session, ClientSystemDetails clientSystem);
	public Object generateNewFolio(MFInvestForm mfInvestForm,ValidateAadhaarOTPOutput aadhaarDetails, HttpSession session, ClientSystemDetails clientSystem);
	
	public Object savePrePurchaseSchemeDetails(MFInvestForm mfInvestForm, HttpSession session, ClientSystemDetails clientSystem);
	public Object savePostPaymentPurchaseDetails(MFInvestForm mfInvestForm,FolioCreationStatus folioStatus, HttpSession session, ClientSystemDetails clientSystem);
	
	public Object lumpSumPurchase(MFInvestForm mfInvestForm,FolioCreationStatus folioStatus, HttpSession session, ClientSystemDetails clientSystem);
	
	
}
