package com.freemi.services.partners.Interfaces;

import javax.servlet.http.HttpSession;

import com.freemi.entity.Birla.ValidateAadhaarOTPOutput;
import com.freemi.entity.database.UserBankDetails;
import com.freemi.entity.general.ClientSystemDetails;
import com.freemi.entity.investment.FolioCreationStatus;
import com.freemi.entity.investment.MFInvestForm;

/**
 * @author FreEMI
 *
 */
public interface InvestmentConnectorInterfaces {
	
	
//	public String getCheckSum(String timestamp, String inputString, HttpSession session);
	public Object validatePAN(String accessToken, String PAN, HttpSession session, ClientSystemDetails clientSystem);
	public Object getStateCityList(HttpSession session, ClientSystemDetails clientSystem);
	public Object validateAADHAARNumber(String aadhaarNo,HttpSession session, ClientSystemDetails clientSystem);
	public Object ValidateAADHAAROTP(String aadhaar, String OTP, int refID,HttpSession session, ClientSystemDetails clientSystem);
	public FolioCreationStatus generateNewFolio(MFInvestForm  folioInput, UserBankDetails bankDetails, ValidateAadhaarOTPOutput aadhaarData, ClientSystemDetails clientSystem);
	
	public Object getFPurchaseBanks(String selectedAMC, Object accesToken, ClientSystemDetails clientSystem);
	public Object getFPurchaseBankCity(String bankName, String bankId, Object accessToken, ClientSystemDetails clientSystem);
	public Object getFPurchaseBankBranch(String bankName, String bankId, String city, Object accesToken, ClientSystemDetails clientSystem);
	
	public Object getProductDetails(String value1, String authCode, Object accessToken, Object mfInvestForm, ClientSystemDetails clientSystem);
	public Object getSChemeDetails(String value,String authCode, Object accessToken, Object mfInvestForm, ClientSystemDetails clientSystem );
	

	public Object getBankDetailsByIFSC(String ifsc, ClientSystemDetails clientSystem);
	
	public Object savePreSIPMultipleSchemes(String primeFolio, String activeFolioNumber,Object accessToken,MFInvestForm mfInvestForm, UserBankDetails bankDetails , ClientSystemDetails clientSystem);
	public Object savePostSIPMultipleSchemes(FolioCreationStatus folioStatus, String paymentStatus, Object accessToken,MFInvestForm mfInvestForm, UserBankDetails bankDetails, ClientSystemDetails clientSystem );
	
	public Object getInstantRedemebaleFolio(String folioNumber, String amcName, Object accessToken, ClientSystemDetails clientSystem);
	public Object InstandWithdrawAmount(String foliopNUmber, String amount, String amcName, Object accessToken, ClientSystemDetails clientSystem);
	
	public Object processNewProfile(MFInvestForm  investForm,ValidateAadhaarOTPOutput aadhaarData, ClientSystemDetails clientSystem);
	
	public Object getProductDetails(String value1, String authCode, Object accessToken, Object mfInvestForm);
	public Object getSChemeDetails(String value,String authCode, Object accessToken, Object mfInvestForm );
	
	public Object preLumsumPurchaseSave(String primeFolio, String activeFolioNumber,Object accessToken,MFInvestForm mfInvestForm, UserBankDetails bankDetails , ClientSystemDetails clientSystem);
	
	public Object postLumsumPurchaseSave(FolioCreationStatus folioStatus,String paymentStatus,Object accessToken,MFInvestForm mfInvestForm, UserBankDetails bankDetails , ClientSystemDetails clientSystem);
}
