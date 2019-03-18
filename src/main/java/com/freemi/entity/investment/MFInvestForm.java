package com.freemi.entity.investment;

import java.io.Serializable;

import com.freemi.entity.database.UserBankDetails;

public class MFInvestForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//@Pattern(regexp = "[a-zA-Z]{5}[0-9]{4}[a-zA-Z]{1}")
	
	private String PAN="";
	private String invName="";
	private String invDOB="";
	private String gender="";
	private String aadhaar;
	private String holdingMode;
	private String ubo;
	private String declaration;
	private String email;
	private String mobile;
	private String annualIncome="";
	private String occupation="";
	private String placeOfBirth;
	private String fatherOrSpouse="";
	private String maritalStatus="";
	private String applicantPrefix="";
	private String maidenPrefix="";
	private String maidenName="";
	private String motherPrefix="";
	private String motherName="";
	private String fatherPrefix="";
	private String district="";
	private String firstInstallment="";
	private String aadhaarbaseKYCrefId="";
	private String fatcaStatus;
	private String taxStatus;
	private String investmentType;
	private String aadhaarVerifyStatusCode="";
	private String kycType="";
	
	private MFNominationForm nominee;
	private UserBankDetails bankDetails;
	private RegistryFunds selectedFund;
	private PanValidationStatus panValidationStatus;
	private MFInvestmentDates mfInvestDates;
	
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getPAN() {
		return PAN;
	}

	public void setPAN(String pAN) {
		PAN = pAN;
	}
	
	
	public String getInvName() {
		return invName;
	}

	public void setInvName(String invName) {
		this.invName = invName;
	}

	

	public String getInvDOB() {
		return invDOB;
	}

	public void setInvDOB(String invDOB) {
		this.invDOB = invDOB;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAadhaar() {
		return aadhaar;
	}

	public void setAadhaar(String aadhaar) {
		this.aadhaar = aadhaar;
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
	
	
	public String getAnnualIncome() {
		return annualIncome;
	}
	

	public String getDeclaration() {
		return declaration;
	}

	public void setDeclaration(String declaration) {
		this.declaration = declaration;
	}

	public void setAnnualIncome(String annualIncome) {
		this.annualIncome = annualIncome;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	

	public String getPlaceOfBirth() {
		return placeOfBirth;
	}

	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}

	public String getFatherOrSpouse() {
		return fatherOrSpouse;
	}

	public void setFatherOrSpouse(String fatherOrSpouse) {
		this.fatherOrSpouse = fatherOrSpouse;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getApplicantPrefix() {
		return applicantPrefix;
	}
	

	public String getFatcaStatus() {
		return fatcaStatus;
	}

	public void setFatcaStatus(String fatcaStatus) {
		this.fatcaStatus = fatcaStatus;
	}

	public void setApplicantPrefix(String applicantPrefix) {
		this.applicantPrefix = applicantPrefix;
	}

	public String getMaidenPrefix() {
		return maidenPrefix;
	}

	public void setMaidenPrefix(String maidenPrefix) {
		this.maidenPrefix = maidenPrefix;
	}

	public String getMaidenName() {
		return maidenName;
	}

	public void setMaidenName(String maidenName) {
		this.maidenName = maidenName;
	}

	public String getMotherPrefix() {
		return motherPrefix;
	}

	public void setMotherPrefix(String motherPrefix) {
		this.motherPrefix = motherPrefix;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getFatherPrefix() {
		return fatherPrefix;
	}

	public void setFatherPrefix(String fatherPrefix) {
		this.fatherPrefix = fatherPrefix;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
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

	public RegistryFunds getSelectedFund() {
		return selectedFund;
	}

	public void setSelectedFund(RegistryFunds selectedFund) {
		this.selectedFund = selectedFund;
	}

	public PanValidationStatus getPanValidationStatus() {
		return panValidationStatus;
	}

	public void setPanValidationStatus(PanValidationStatus panValidationStatus) {
		this.panValidationStatus = panValidationStatus;
	}

	public MFInvestmentDates getMfInvestDates() {
		return mfInvestDates;
	}

	public void setMfInvestDates(MFInvestmentDates mfInvestDates) {
		this.mfInvestDates = mfInvestDates;
	}

	public String getFirstInstallment() {
		return firstInstallment;
	}

	public void setFirstInstallment(String firstInstallment) {
		this.firstInstallment = firstInstallment;
	}

	public String getAadhaarbaseKYCrefId() {
		return aadhaarbaseKYCrefId;
	}

	public void setAadhaarbaseKYCrefId(String aadhaarbaseKYCrefId) {
		this.aadhaarbaseKYCrefId = aadhaarbaseKYCrefId;
	}

	public String getUbo() {
		return ubo;
	}

	public void setUbo(String ubo) {
		this.ubo = ubo;
	}

	public String getTaxStatus() {
		return taxStatus;
	}

	public void setTaxStatus(String taxStatus) {
		this.taxStatus = taxStatus;
	}

	public String getInvestmentType() {
		return investmentType;
	}

	public void setInvestmentType(String investmentType) {
		this.investmentType = investmentType;
	}

	public String getAadhaarVerifyStatusCode() {
		return aadhaarVerifyStatusCode;
	}

	public void setAadhaarVerifyStatusCode(String aadhaarVerifyStatusCode) {
		this.aadhaarVerifyStatusCode = aadhaarVerifyStatusCode;
	}

	public String getKycType() {
		return kycType;
	}

	public void setKycType(String kycType) {
		this.kycType = kycType;
	}
	
	
}
