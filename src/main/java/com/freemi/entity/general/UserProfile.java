package com.freemi.entity.general;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class UserProfile implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String uid;
	private String mobile;
	private String mail;
	private String fname;
	private String mname;
	private String lname;
	private String displayname;
	private String investor2;
	private String dob;
	private String accountHolder;
	private String holdingMode;
	private String taxStatus;
	private String pan1;
	private String pan2;
	
	private String pan1verified;
	private String pan2Verified;
	
	private String pan1Verifiedkyc;
	
	private String ifscCode;
	private String bankName;
	private String accountNumber;
	private String accountType;
	private String branch;
	private String branchCity;
	private String accountState;
	
	private String aadhaar;
	
	private String gender;
	private String houseNumber;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String pincode;
	private String landmark;
	
	private String emailidvalidated;
	private String panidvalidated;
	private String userPassword;
	private String bseclientid;
	private String mobilenovalidated;
	private String nomineeAvailable;
	private String nomineeName;
	private String nomineeRelation;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	public String getDisplayname() {
		return displayname;
	}
	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getInvestor2() {
		return investor2;
	}
	public void setInvestor2(String investor2) {
		this.investor2 = investor2;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getAccountHolder() {
		return accountHolder;
	}
	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}
	public String getHoldingMode() {
		return holdingMode;
	}
	public void setHoldingMode(String holdingMode) {
		this.holdingMode = holdingMode;
	}
	public String getTaxStatus() {
		return taxStatus;
	}
	public void setTaxStatus(String taxStatus) {
		this.taxStatus = taxStatus;
	}
	public String getPan1() {
		return pan1;
	}
	public void setPan1(String pan1) {
		this.pan1 = pan1;
	}
	public String getPan2() {
		return pan2;
	}
	public void setPan2(String pan2) {
		this.pan2 = pan2;
	}
	
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getPan1verified() {
		return pan1verified;
	}
	public void setPan1verified(String pan1verified) {
		this.pan1verified = pan1verified;
	}
	public String getPan2Verified() {
		return pan2Verified;
	}
	public void setPan2Verified(String pan2Verified) {
		this.pan2Verified = pan2Verified;
	}
	public String getPan1Verifiedkyc() {
		return pan1Verifiedkyc;
	}
	public void setPan1Verifiedkyc(String pan1Verifiedkyc) {
		this.pan1Verifiedkyc = pan1Verifiedkyc;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getBranchCity() {
		return branchCity;
	}
	public void setBranchCity(String branchCity) {
		this.branchCity = branchCity;
	}
	public String getAccountState() {
		return accountState;
	}
	public void setAccountState(String accountState) {
		this.accountState = accountState;
	}
	public String getAadhaar() {
		return aadhaar;
	}
	public void setAadhaar(String aadhaar) {
		this.aadhaar = aadhaar;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getHouseNumber() {
		return houseNumber;
	}
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getLandmark() {
		return landmark;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	public String getEmailidvalidated() {
		return emailidvalidated;
	}
	public void setEmailidvalidated(String emailidvalidated) {
		this.emailidvalidated = emailidvalidated;
	}
	public String getPanidvalidated() {
		return panidvalidated;
	}
	public void setPanidvalidated(String panidvalidated) {
		this.panidvalidated = panidvalidated;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getBseclientid() {
		return bseclientid;
	}
	public void setBseclientid(String bseclientid) {
		this.bseclientid = bseclientid;
	}
	public String getMobilenovalidated() {
		return mobilenovalidated;
	}
	public void setMobilenovalidated(String mobilenovalidated) {
		this.mobilenovalidated = mobilenovalidated;
	}
	public String getNomineeAvailable() {
		return nomineeAvailable;
	}
	public void setNomineeAvailable(String nomineeAvailable) {
		this.nomineeAvailable = nomineeAvailable;
	}
	public String getNomineeName() {
		return nomineeName;
	}
	public void setNomineeName(String nomineeName) {
		this.nomineeName = nomineeName;
	}
	public String getNomineeRelation() {
		return nomineeRelation;
	}
	public void setNomineeRelation(String nomineeRelation) {
		this.nomineeRelation = nomineeRelation;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
