package com.freemi.entity.general;

import javax.validation.constraints.Pattern;

import com.freemi.entity.database.UserBankDetails;

public class InvestmentFormGeneral {

	@Pattern(regexp = "[a-zA-Z]{5}[0-9]{4}[a-zA-Z]{1}")
	private String PAN;
	private String holdingMode;
	private String email;
	private String mobile;
	private MFNominationForm nominee;
	private UserBankDetails bankDetails;

	
	public String getPAN() {
		return PAN;
	}

	public void setPAN(String pAN) {
		PAN = pAN;
	}

	public String getHoldingMode() {
		return holdingMode;
	}

	public void setHoldingMode(String holdingMode) {
		this.holdingMode = holdingMode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public MFNominationForm getNominee() {
		return nominee;
	}

	public void setNominee(MFNominationForm nominee) {
		this.nominee = nominee;
	}

	public UserBankDetails getBankDetails() {
		return bankDetails;
	}

	public void setBankDetails(UserBankDetails bankDetails) {
		this.bankDetails = bankDetails;
	}
	
	
	
	
	
}
