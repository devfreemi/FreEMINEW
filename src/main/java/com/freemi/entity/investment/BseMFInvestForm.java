package com.freemi.entity.investment;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Proxy;
import org.hibernate.validator.constraints.NotBlank;

import com.freemi.entity.database.UserBankDetails;

@Entity
@Table(name="bsemf_customers")
@Proxy(lazy=false)
public class BseMFInvestForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//@Pattern(regexp = "[a-zA-Z]{5}[0-9]{4}[a-zA-Z]{1}")
	
	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="SL_NO")
	private long serialNo;
	
	@Id
	@Column(name="CLIENT_ID")
	private String clientID;
	
	@NotBlank(message="Primary Holder PAn is mandatory")
	@Column(name="PAN_NO_1")
	private String pan1="";
	
	@Column(name="PAN_NO_2")
	private String pan2="";
	
	@NotBlank(message="Investor name is mandatory")
	@Column(name=" INVESTOR_1")
	private String invName="";
	
	@Column(name="INVESTOR_2")
	private String applicant2="";
	
	@NotBlank(message="Date of birth is mandatory")
	@Column(name="INVESTOR_DOB")
	private String invDOB="";
	
	@NotBlank(message="Please provide your gender")
	@Column(name="GENDER")
	private String gender="";
	
	@Column(name="AADHAAR_INVESTOR_1")
	private String aadhaar;
	
	@NotNull(message="Provide your holding mode")
	@Column(name="HOLDING_MODE")
	private String holdingMode="";
	
	@Transient
	@Column(name="")
	private boolean ubo;
	
	@Transient
	@Column(name="")
	private String declaration;
	
	@Column(name="EMAIL")
	private String email="";
	
	@NotNull(message="Mobile number is mandatory")
	@Column(name="MOBILE_NO")
	private String mobile="";
	
	@Column(name="DIVIDEND_PAY_MODE")
	private String dividendPayMode="02";
	
	@Column(name="OCCUPATION_CODE")
	private String occupation="02";
	
	@Transient
	@Column(name="")
	private String placeOfBirth;
	
	@Transient
	@Column(name="")
	private String fatherOrSpouse="";
	
	@Transient
	@Column(name="")
	private String maritalStatus="";
	
	@Transient
	@Column(name="")
	private String applicantPrefix="";
	
	@Transient
	@Column(name="")
	private String maidenPrefix="";
	
	@Transient
	@Column(name="")
	private String maidenName="";
	
	@Transient
	@Column(name="")
	private String motherPrefix="";
	
	@Transient
	@Column(name="")
	private String motherName="";
	
	@Transient
	@Column(name="")
	private String fatherPrefix="";
	
	@Transient
	@Column(name="")
	private String district="";
	
	@Transient
	@Column(name="")
	private String firstInstallment="";
	
	@Transient
	@Column(name="")
	private String aadhaarbaseKYCrefId="";
	
	@Transient
	@Column(name="")
	private String fatcaStatus;
	
	@Transient
	@Column(name="")
	private String taxStatus;
	
	@Transient
	@Column(name="")
	private String investmentType;
	
	@Column(name="SIGNATURE")
	private String customerSignature="";
	
	@Transient
	@Column(name="")
	private String aadhaarVerifyStatusCode="";
	
	@Column(name="BSE_REGISTRATION_SUCCESS")
	private String bseregistrationSuccess="N";
	
	@Column(name="AOF_UPLOAD_COMPLETE")
	private String aofuploadComplete="N";
	
	@Column(name="REGISTER_TIME")
	private Date registrationTime;
	
	
	
	@Transient
	@Column(name="")
	private String kycType="";
	
	@OneToOne(fetch=FetchType.EAGER, mappedBy="mfForm",cascade=CascadeType.ALL)
	private MFNominationForm nominee;
	
	@OneToOne(fetch=FetchType.EAGER, mappedBy="mfForm",cascade=CascadeType.ALL)
	private UserBankDetails bankDetails;
	
	@Transient
	private RegistryFunds selectedFund;
	
	@Transient
	private PanValidationStatus panValidationStatus;
	
	@Transient
	private MFInvestmentDates mfInvestDates;
	
	@OneToOne(fetch=FetchType.EAGER, mappedBy="mfForm",cascade=CascadeType.ALL)
	private AddressDetails addressDetails;
	
	@OneToMany(mappedBy="clientID", cascade=CascadeType.ALL)
	private List<SelectMFFund> fundPurchaseDetails;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	public long getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(long serialNo) {
		this.serialNo = serialNo;
	}
	
	public String getClientID() {
		return clientID;
	}


	public void setClientID(String clientID) {
		this.clientID = clientID;
	}


	public String getPan1() {
		return pan1;
	}


	public void setPan1(String pan1) {
		this.pan1 = pan1.toUpperCase();
	}


	public String getPan2() {
		return pan2;
	}


	public void setPan2(String pan2) {
		this.pan2 = pan2.toUpperCase();
	}


	public String getInvName() {
		return invName;
	}

	public void setInvName(String invName) {
		this.invName = invName;
	}
	
	

	public String getApplicant2() {
		return applicant2;
	}


	public void setApplicant2(String applicant2) {
		this.applicant2 = applicant2;
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
	

	public String getDividendPayMode() {
		return dividendPayMode;
	}


	public void setDividendPayMode(String dividendPayMode) {
		this.dividendPayMode = dividendPayMode;
	}


	public String getDeclaration() {
		return declaration;
	}

	public void setDeclaration(String declaration) {
		this.declaration = declaration;
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


	public boolean isUbo() {
		return ubo;
	}

	public void setUbo(boolean ubo) {
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


	public AddressDetails getAddressDetails() {
		return addressDetails;
	}


	public void setAddressDetails(AddressDetails addressDetails) {
		this.addressDetails = addressDetails;
	}


	public List<SelectMFFund> getFundPurchaseDetails() {
		return fundPurchaseDetails;
	}


	public void setFundPurchaseDetails(List<SelectMFFund> fundPurchaseDetails) {
		this.fundPurchaseDetails = fundPurchaseDetails;
	}


	public String getBseregistrationSuccess() {
		return bseregistrationSuccess;
	}


	public void setBseregistrationSuccess(String bseregistrationSuccess) {
		this.bseregistrationSuccess = bseregistrationSuccess;
	}
	

	public String getAofuploadComplete() {
		return aofuploadComplete;
	}


	public void setAofuploadComplete(String aofuploadComplete) {
		this.aofuploadComplete = aofuploadComplete;
	}


	public Date getRegistrationTime() {
		return registrationTime;
	}


	public void setRegistrationTime(Date registrationTime) {
		this.registrationTime = registrationTime;
	}


	public String getCustomerSignature() {
		return customerSignature;
	}


	public void setCustomerSignature(String customerSignature) {
		this.customerSignature = customerSignature;
	}
	
}
