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
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Proxy;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.freemi.common.util.CommonConstants;
import com.freemi.entity.database.UserBankDetails;

@Entity
@Table(name="bsemf_customers")
@Proxy(lazy=false)
public class MFCustomers implements Serializable {

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
	
	@NotBlank(message="Primary Holder PAN is mandatory")
	@Column(name="PAN_NO_1")
	private String pan1="";
	
	@Column(name= "PROFILE_UNIQUE_ID")
	private String profileuniqueid;
	
	@Column(name="PAN1_EXEMPT")
	private String pan1exempt="N";
	
	@Column(name="PAN1_EXEMPT_CATEGORY")
	private String pan1exemptcategory="";
	
	@Column(name="PAN_NO_2")
	private String pan2="";
	
	@Transient
	private String pan2exempt="";
	
	@Transient
	private String pan2exemptcategory="";
	
//	@NotBlank(message="Investor name is mandatory")
	@Column(name=" INVESTOR_1")
	private String invName="";
	
	@Column(name = "INVESTOR1_FNAME")
	@NotNull(message = "First Name is mandatory") @Size(max = 48, message = "First name max 48 characters allowed") @Pattern(regexp = "[a-zA-Z .]*", message = "No special charcaters allowed in name")
	private String fname;
	
	@Column(name="INVESTOR1_MNAME")
	private String mname;	//Added for v2
	
	@Column(name = "INVESTOR1_LNAME")
	@NotNull(message = "Last Name is mandatory") @Size(max = 48, message = "Last name max 48 characters allowed") @Pattern(regexp = "[a-zA-Z .]*", message = "No special charcaters allowed in name")
	private String lname;
	
	@Column(name="INVESTOR_2")
	private String applicant2="";
	
	@Column(name="INVESTOR2_FNAME")
	private String applicant2fname;
	
	@Column(name="INVESTOR2_MNAME")
	private String applicant2mname;
	
	@Column(name="INVESTOR2_LNAME")
	private String applicant2lname;
	
	@Column(name="INVESTOR1_DOB")
	private String invDOB="";
	
	@Column(name="INVESTOR2_DOB")
	private String inv2DOB;
	

//	@NotEmpty(message="Date of birth is mandatory")
	@JsonIgnore
	@Transient
	private String customerdob=""; 
	
	@NotBlank(message="Please provide your gender")
	@Column(name="GENDER")
	private String gender="";
	
	@Column(name="AADHAAR_INVESTOR_1")
	private String aadhaar;
	
	@Column(name="AADHAAR_UPDATED")
	private String aadhaarupdated="";
	
	@Column(name="PAPERLESS_FLAG")
	private String paperlessflag="Z";
	
	@Column(name="CLIENT_TYPE")
	private String clienttype="P";
	
	@Column(name="INVESTOR1_KYCTYPE")
	private String investor1kyctpe="K";
	
	@Column(name="INVESTOR1_CKYC")
	private String investor1ckycno="";
	
	@Column(name="INVESTOR2_KYCTYPE")
	private String investor2kyctpe;
	
	@Column(name="INVESTOR2_CKYC")
	private String investor2ckycno;
	 
	
	@NotBlank(message="Provide your holding mode")
	@Column(name="HOLDING_MODE")
	private String holdingMode="SI";
	
	@Transient
	@Column(name="")
	private boolean ubo;
	
	@Transient
	@Column(name="")
	private String declaration;
	
	@NotBlank(message="Email ID is mandatory")
	@Column(name="EMAIL")
	private String email="";
	
	@NotBlank(message="Mobile number is mandatory") @Size(min = 10, max = 10, message = "Invalid mobile no format")
	@Column(name="MOBILE_NO")
	private String mobile="";
	
	@Column(name="DIVIDEND_PAY_MODE")
	private String dividendPayMode="02";
	
	@Column(name="OCCUPATION_CODE")
	private String occupation="";
	
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
	
	@Column(name="TAX_STATUS")
	private String taxStatus="01";
	
	@Transient
	@Column(name="")
	private String investmentType;
	
	@Column(name="SIGNATURE_1")
	private String customerSignature1="";
	
	@Column(name="SIGNATURE_2")
	private String customerSignature2="";
	
	@Transient
	@Column(name="")
	private String aadhaarVerifyStatusCode="";
	
	@Column(name="BSE_REGISTRATION_SUCCESS")
	private String bseregistrationSuccess="N";
	
	@Column(name="AOF_UPLOAD_COMPLETE")
	private String aofuploadComplete="N";
	
	@Column(name = "RESGISTRATION_RESPONSE")
	private String registrationResponse;
	
	@Column(name = "AOF_UPLOAD_RESPONSE")
	private String aofUploadResponse;
	
	@Column(name="REGISTER_TIME")
	private Date registrationTime;
	
	@Transient
	@Column(name="")
	private String kycType="";
	
	@Column(name="CREATED_BY")
	private String createdBy=CommonConstants.BSE_USER_ID;
	
	@Transient
	private String customerRegistered="N";
	
	@Transient
	private boolean profileRegRequired=false;
	
	@Column(name="ACCOUNT_ACTIVE")
	private String accountActive="Y";
	
	@Column(name="ACCOUNT_SUSPENDED")
	private String accountSuspended="N";
	
	@Column(name="PAN1_VALIDITY")
	private String pan1verified="N";
	
	@Column(name="PAN2_VALIDITY")
	private String pan2verified="N";
	
	@Column(name="PAN1_KYC_VERIFIED")
	private String pan1KycVerified="N";
	
	@Column(name="LAST_MODIFIED_AT")
	private Date lastModifiedDate;
	
	@Column(name="SYSTEM_IP")
	private String systemip;
	
	@Column(name="SYSTEM_DETAILS")
	private String systemDetails;
	
	@JsonIgnore
	@Column(name="MOBILE_VERIFIED")
	@Pattern(regexp = "Y", message = "Mobile no is not verified")
	private String mobileverified="N";
	
	@JsonIgnore
	@Column(name = "EMAIL_VERIFIED")
	@Pattern(regexp = "Y", message = "Email ID is not verified")
	private String emailverified="N";
	
	@Valid
	@OneToOne(fetch=FetchType.EAGER, mappedBy="mfForm",cascade=CascadeType.ALL)
	private MFNominationForm nominee;
	
	@Valid
	@OneToOne(fetch=FetchType.EAGER, mappedBy="mfForm",cascade=CascadeType.ALL)
	private UserBankDetails bankDetails;
	
	@Valid
	@OneToOne(fetch=FetchType.EAGER, mappedBy="mfForm",cascade=CascadeType.ALL)
	private MFFatcaDeclareForm fatcaDetails;
	
	@Transient
	private RegistryFunds selectedFund;
	
	@Transient
	private PanValidationStatus panValidationStatus;
	
	@Transient
	private MFInvestmentDates mfInvestDates;
	
	@Valid
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

	public String getFname() {
	    return fname;
	}

	public void setFname(String fname) {
	    this.fname = fname;
	}

	public String getLname() {
	    return lname;
	}

	public void setLname(String lname) {
	    this.lname = lname;
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


	public String getCustomerSignature1() {
		return customerSignature1;
	}


	public void setCustomerSignature1(String customerSignature1) {
		this.customerSignature1 = customerSignature1;
	}


	public String getCustomerSignature2() {
		return customerSignature2;
	}


	public void setCustomerSignature2(String customerSignature2) {
		this.customerSignature2 = customerSignature2;
	}


	public boolean isProfileRegRequired() {
		return profileRegRequired;
	}


	public void setProfileRegRequired(boolean profileRegRequired) {
		this.profileRegRequired = profileRegRequired;
	}


	public MFFatcaDeclareForm getFatcaDetails() {
		return fatcaDetails;
	}


	public void setFatcaDetails(MFFatcaDeclareForm fatcaDetails) {
		this.fatcaDetails = fatcaDetails;
	}


	public String getCustomerRegistered() {
		return customerRegistered;
	}


	public void setCustomerRegistered(String customerRegistered) {
		this.customerRegistered = customerRegistered;
	}


	public String getAccountActive() {
		return accountActive;
	}


	public void setAccountActive(String accountActive) {
		this.accountActive = accountActive;
	}


	public String getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	public String getPan1verified() {
		return pan1verified;
	}


	public void setPan1verified(String pan1verified) {
		this.pan1verified = pan1verified;
	}


	public String getPan2verified() {
		return pan2verified;
	}


	public void setPan2verified(String pan2verified) {
		this.pan2verified = pan2verified;
	}


	public String getPan1KycVerified() {
		return pan1KycVerified;
	}


	public void setPan1KycVerified(String pan1KycVerified) {
		this.pan1KycVerified = pan1KycVerified;
	}


	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}


	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}


	public String getSystemip() {
		return systemip;
	}


	public void setSystemip(String systemip) {
		this.systemip = systemip;
	}


	public String getSystemDetails() {
		return systemDetails;
	}


	public void setSystemDetails(String systemDetails) {
		this.systemDetails = systemDetails;
	}


	public String getAccountSuspended() {
		return accountSuspended;
	}


	public void setAccountSuspended(String accountSuspended) {
		this.accountSuspended = accountSuspended;
	}


	public String getRegistrationResponse() {
	    return registrationResponse;
	}


	public void setRegistrationResponse(String registrationResponse) {
	    this.registrationResponse = registrationResponse;
	}


	public String getAofUploadResponse() {
	    return aofUploadResponse;
	}


	public void setAofUploadResponse(String aofUploadResponse) {
	    this.aofUploadResponse = aofUploadResponse;
	}


	public String getCustomerdob() {
		return customerdob;
	}


	public void setCustomerdob(String customerdob) {
		this.customerdob = customerdob;
	}


	public String getMname() {
		return mname;
	}


	public void setMname(String mname) {
		this.mname = mname;
	}


	public String getApplicant2fname() {
		return applicant2fname;
	}


	public void setApplicant2fname(String applicant2fname) {
		this.applicant2fname = applicant2fname;
	}


	public String getApplicant2mname() {
		return applicant2mname;
	}


	public void setApplicant2mname(String applicant2mname) {
		this.applicant2mname = applicant2mname;
	}


	public String getApplicant2lname() {
		return applicant2lname;
	}


	public void setApplicant2lname(String applicant2lname) {
		this.applicant2lname = applicant2lname;
	}


	public String getInv2DOB() {
		return inv2DOB;
	}


	public void setInv2DOB(String inv2dob) {
		inv2DOB = inv2dob;
	}


	public String getAadhaarupdated() {
		return aadhaarupdated;
	}


	public void setAadhaarupdated(String aadhaarupdated) {
		this.aadhaarupdated = aadhaarupdated;
	}


	public String getPaperlessflag() {
		return paperlessflag;
	}


	public void setPaperlessflag(String paperlessflag) {
		this.paperlessflag = paperlessflag;
	}


	public String getClienttype() {
		return clienttype;
	}


	public void setClienttype(String clienttype) {
		this.clienttype = clienttype;
	}


	public String getInvestor1kyctpe() {
		return investor1kyctpe;
	}


	public void setInvestor1kyctpe(String investor1kyctpe) {
		this.investor1kyctpe = investor1kyctpe;
	}


	public String getInvestor1ckycno() {
		return investor1ckycno;
	}


	public void setInvestor1ckycno(String investor1ckycno) {
		this.investor1ckycno = investor1ckycno;
	}


	public String getInvestor2kyctpe() {
		return investor2kyctpe;
	}


	public void setInvestor2kyctpe(String investor2kyctpe) {
		this.investor2kyctpe = investor2kyctpe;
	}


	public String getInvestor2ckycno() {
		return investor2ckycno;
	}


	public void setInvestor2ckycno(String investor2ckycno) {
		this.investor2ckycno = investor2ckycno;
	}


	public String getPan1exempt() {
		return pan1exempt;
	}


	public void setPan1exempt(String pan1exempt) {
		this.pan1exempt = pan1exempt;
	}


	public String getPan1exemptcategory() {
		return pan1exemptcategory;
	}


	public void setPan1exemptcategory(String pan1exemptcategory) {
		this.pan1exemptcategory = pan1exemptcategory;
	}


	public String getPan2exempt() {
		return pan2exempt;
	}


	public void setPan2exempt(String pan2exempt) {
		this.pan2exempt = pan2exempt;
	}


	public String getPan2exemptcategory() {
		return pan2exemptcategory;
	}


	public void setPan2exemptcategory(String pan2exemptcategory) {
		this.pan2exemptcategory = pan2exemptcategory;
	}


	public String getMobileverified() {
		return mobileverified;
	}


	public void setMobileverified(String mobileverified) {
		this.mobileverified = mobileverified;
	}


	public String getEmailverified() {
		return emailverified;
	}


	public void setEmailverified(String emailverified) {
		this.emailverified = emailverified;
	}


	public String getProfileuniqueid() {
		return profileuniqueid;
	}


	public void setProfileuniqueid(String profileuniqueid) {
		this.profileuniqueid = profileuniqueid;
	}

	
	
}
