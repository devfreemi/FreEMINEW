package com.freemi.services.partners.Impl;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.freemi.entity.Birla.ValidateAadhaarOTPOutput;
import com.freemi.entity.general.ClientSystemDetails;
import com.freemi.entity.general.UserBankDetails;
import com.freemi.entity.investment.FolioCreationStatus;
import com.freemi.entity.investment.MFInvestForm;
import com.freemi.services.partners.Interfaces.InvestmentConnectorInterfaces;

public class IciciConnectorsImpl implements InvestmentConnectorInterfaces {
	
	private static final Logger logger = LogManager.getLogger(IciciConnectorsImpl.class);
	private static final String CONSUMER_KEY="ElkD44Ckq2WpTOFTnh9PNbDUwBwa";
	private static final String CONSUMER_SECRET="mNV_iwpK_sAo82Jrmtm5gl1UXuYa";
	private static final String BASE_URL ="https://recycle.icicipruamc.com/Distributorsvcs/InvestorService.svc/JSON";
	

	@Override
	public Object validatePAN(String accessToken, String PAN, HttpSession session, ClientSystemDetails clientSystem) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getStateCityList(HttpSession session, ClientSystemDetails clientSystem) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object validateAADHAARNumber(String aadhaarNo, HttpSession session, ClientSystemDetails clientSystem) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object ValidateAADHAAROTP(String aadhaar, String OTP, int refID, HttpSession session,
			ClientSystemDetails clientSystem) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FolioCreationStatus generateNewFolio(MFInvestForm folioInput, UserBankDetails bankDetails,
			ValidateAadhaarOTPOutput aadhaarData, ClientSystemDetails clientSystem) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getFPurchaseBanks(String selectedAMC, Object accesToken, ClientSystemDetails clientSystem) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getFPurchaseBankCity(String bankName, String bankId, Object accessToken,
			ClientSystemDetails clientSystem) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getFPurchaseBankBranch(String bankName, String bankId, String city, Object accesToken,
			ClientSystemDetails clientSystem) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getProductDetails(String value1, String authCode, Object accessToken, Object mfInvestForm,
			ClientSystemDetails clientSystem) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getSChemeDetails(String value, String authCode, Object accessToken, Object mfInvestForm,
			ClientSystemDetails clientSystem) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getBankDetailsByIFSC(String ifsc, ClientSystemDetails clientSystem) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object savePreSIPMultipleSchemes(String primeFolio, String activeFolioNumber, Object accessToken,
			MFInvestForm mfInvestForm, UserBankDetails bankDetails, ClientSystemDetails clientSystem) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object savePostSIPMultipleSchemes(FolioCreationStatus folioStatus, String paymentStatus, Object accessToken,
			MFInvestForm mfInvestForm, UserBankDetails bankDetails, ClientSystemDetails clientSystem) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getInstantRedemebaleFolio(String folioNumber, String amcName, Object accessToken,
			ClientSystemDetails clientSystem) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object InstandWithdrawAmount(String foliopNUmber, String amount, String amcName, Object accessToken,
			ClientSystemDetails clientSystem) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object processNewProfile(MFInvestForm investForm, ValidateAadhaarOTPOutput aadhaarData,
			ClientSystemDetails clientSystem) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getProductDetails(String value1, String authCode, Object accessToken, Object mfInvestForm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getSChemeDetails(String value, String authCode, Object accessToken, Object mfInvestForm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object preLumsumPurchaseSave(String primeFolio, String activeFolioNumber, Object accessToken,
			MFInvestForm mfInvestForm, UserBankDetails bankDetails, ClientSystemDetails clientSystem) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object postLumsumPurchaseSave(FolioCreationStatus folioStatus, String paymentStatus, Object accessToken,
			MFInvestForm mfInvestForm, UserBankDetails bankDetails, ClientSystemDetails clientSystem) {
		// TODO Auto-generated method stub
		return null;
	}

}
