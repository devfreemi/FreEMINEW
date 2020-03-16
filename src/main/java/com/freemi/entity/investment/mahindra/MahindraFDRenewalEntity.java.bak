package com.freemi.entity.investment.mahindra;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Lob;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


@Component
public class MahindraFDRenewalEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String cpTrnasRefNo;
	
	private String mfSysRefNo;
	
	@NotEmpty(message="Please select category") @NotNull(message = "Please select category")
	private String category;
	
	@Min(value = 10000, message = "Minimum saving amount is Rs. 10,000") @Max(value = 9999999, message = "Maximum saving amount is Rs. 9,999,999")
	private Integer saveAmount;
	
	@NotNull(message = "Please select tenure")
	private Integer saveTenure;
	
	@NotNull(message = "Mobile no. cannot be blank") @NotEmpty @Size(min=10,max=10) @Pattern(regexp="[6-9][0-9]{9}", message="Mobile nunmber format invalid")
	private String mobile;
	
	@Email(message = "Email format is invalid.")
	private String email;
	
	@NotNull @NotEmpty(message = "Select title")
	private String primaryHolderTitle; 
	
	@NotNull(message = "First name required") @NotEmpty(message = "First name is blank")
	private String firstName;
	
	
	private String middleName;
	
	@NotNull(message = "Last name required") @NotEmpty(message = "Last name is blank")
	private String lastName;
	
	@NotNull(message = "DOB cannot be null") @NotEmpty(message = "Select your DOB")
	private String dob;
	
	@NotNull(message = "PAN cannot be null") @NotEmpty(message = "PAN is mandatory for transaction.")@Pattern(regexp = "[a-zA-Z]{5}[0-9]{4}[a-zA-Z]{1}", message = "PAN no. format invalid.")
	private String pan;
	
	@NotNull @NotEmpty(message = "Select your holding option")
	private String holdingOptions;
	private boolean nomineechosen;
	
	@NotNull(message = "Scheme details required") @NotEmpty(message = "Scheme option is empty")
	private String scheme;
	
	@NotNull(message = "Schemecode cannot be null") @NotEmpty(message = "Schemecode is not populated. Kindly try again")
	private String schemeCode;
	
	private boolean autorenew;
	private String taxDeductAtSource;
	
	@NotNull(message = "Int. Frequency cannot be null") @NotEmpty(message = "Select interest frequency")
	private String intFreq;
	
	private Double interestRate=0.0;
	
	
	private String taxResidentOtherCountry="NO";
	private String greenCardHolder="NO";
	
	@NotNull(message = "Nationality cannot be null") @NotEmpty(message = "Select your nationality")
	private String nationality="IN";
	
	@NotNull(message = "Country of birth cannot be null") @NotEmpty(message = "Country of birth is required")
	private String countryOfBirth="IN";
	
	@NotNull (message = "City of birth cannot be null") @NotEmpty(message = "Provide your city of birth")
	private String cityOfBirth;
	
	@NotNull(message = "Gender cannot be null") @NotEmpty(message = "Select your Gender")
	private String gender;
	
	@NotNull(message = "Marital status cannot be null") @NotEmpty(message = "Marital status is mandatory")
	private String maritalStatus;
	
	@NotNull(message = "Occupatation details blank") @NotEmpty(message = "Occupation field is mandatory")
	private String occupation;
	private String citizenship;
	
	@NotNull(message ="Address1 is null") @NotEmpty(message = "Address1 is blank")
	private String address1;
	
	private String address2_1;
	private String address3_1;
	
	@NotNull(message = "Country is null") @NotEmpty(message = "Address country is mandatory")
	private String addCountry1;
	
	@NotNull(message = "State is null") @NotEmpty(message = "Select your address state")
	private String addressstate1;
	
	@NotNull(message = "District is null") @NotEmpty(message = "Select your address District")
	private String addressDistrict1;
	
	@NotNull(message = "City info is null") @NotEmpty(message = "Provide the address city")
	private String addressCity1;
	
	@NotNull (message = "Address PINCODE is mandatory")
	private Integer addresspincode1;
	
	private String address2;
	private String address2_2;
	private String address3_2;
	private String addCountry2;
	private String addressstate2;
	private String addressDistrict2;
	private String addressCity2;
	private Integer addresspincode2;
	
	@NotNull(message = "Bank name is null") @NotEmpty(message = "Bank name not found. Please check your banl IFSC code")
	private String bankname;
	
	@NotNull(message = "MICR code is null") @NotEmpty(message = "MICR not found. Please check your IFSC code")
	private String micrCode;
	
	@NotNull(message = "IFSC Code is null") @NotEmpty(message = "IFSC code is mandatory") @Pattern(regexp = "[a-zA-Z]{4}0[a-zA-Z0-9]{6}", message = "IFSC code format is incorrect")
	private String ifscCode;
	
	@NotNull(message = "Bank branch is null") @NotEmpty(message = "Bank branch missing. Please validate your IFSC code")
	private String bankbranch;
	
	@NotNull(message = "Account no. is null") @NotEmpty(message = "Account number is mandatory")@Length(max = 24, message = "Account number too long!")
	private String accountNumber;
	
	@Length(max = 24, message = "Confirm account number too long!")
	private String confirmaccountNumber;
	
	private String kycFatherPrefix;
	private String kycFatherFirstName;
	private String kycFatherMiddlename;
	private String kycFatherLastName;
	
	private String kycMotherPrefix;
	private String kycMotherFirstName;
	private String kycMotherMiddlename;
	private String kycMotherLastName;
	
	private String ckyc;
	
	@NotNull(message = "Please upload cancelled checque")
	private MultipartFile canecelledcheque;
	
	private String cancelledchecqueRefNo;
	
	@NotNull(message = "Please upload photo proof")
	private MultipartFile kycphotoproof;
	
	@NotNull(message = "Please upload PAN proof")
	private MultipartFile kycpanproof;
	
	@NotNull(message = "Please upload address proof")
	private MultipartFile kycaddressproof;
	
	private String addressproofType="NA";
	private String addressproofrefno;
	private String addressproofpxpirydate;
	
	private String nomineeprefix;
	private String nomineefirstname;
	private String nomineemiddlename;
	private String nomineelastname;
	private String nomineefullname;
	private String nomineerelation;
	
	private String nomineedob;
	private String nomineeminor="N";
	private String nomineeguardian="";
	private String nomineemobile;
	private String nomineeemail;
	private String nomineeaddress1;
	private String nomineeaddress2;
	private String nomineeaddress3;
	private String nomineestatecode;
	private String nomineedistrict;
	private String nomineecity;
	private String nomineecitypincode;
	
	
	List<MahindraOtherCountryTaxDetails> foreignTaxDetails;
	
	List<MahindraKycDocuments> kycdocuments;
	
	public String getCpTrnasRefNo() {
		return cpTrnasRefNo;
	}
	public void setCpTrnasRefNo(String cpTrnasRefNo) {
		this.cpTrnasRefNo = cpTrnasRefNo;
	}
	public String getMfSysRefNo() {
		return mfSysRefNo;
	}
	public void setMfSysRefNo(String mfSysRefNo) {
		this.mfSysRefNo = mfSysRefNo;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Integer getSaveAmount() {
		return saveAmount;
	}
	public void setSaveAmount(Integer saveAmount) {
		this.saveAmount = saveAmount;
	}
	public Integer getSaveTenure() {
		return saveTenure;
	}
	public void setSaveTenure(Integer saveTenure) {
		this.saveTenure = saveTenure;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName.toUpperCase();
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName.toUpperCase();
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName.toUpperCase();
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan.toUpperCase();
	}
	public String getHoldingOptions() {
		return holdingOptions;
	}
	public void setHoldingOptions(String holdingOptions) {
		this.holdingOptions = holdingOptions;
	}
	public boolean isNomineechosen() {
		return nomineechosen;
	}
	public void setNomineechosen(boolean nomineechosen) {
		this.nomineechosen = nomineechosen;
	}
	public String getScheme() {
		return scheme;
	}
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}
	public boolean isAutorenew() {
		return autorenew;
	}
	public void setAutorenew(boolean autorenew) {
		this.autorenew = autorenew;
	}
	public String getTaxDeductAtSource() {
		return taxDeductAtSource;
	}
	public void setTaxDeductAtSource(String taxDeductAtSource) {
		this.taxDeductAtSource = taxDeductAtSource;
	}
	public String getTaxResidentOtherCountry() {
		return taxResidentOtherCountry;
	}
	public void setTaxResidentOtherCountry(String taxResidentOtherCountry) {
		this.taxResidentOtherCountry = taxResidentOtherCountry;
	}
	public String getGreenCardHolder() {
		return greenCardHolder;
	}
	public void setGreenCardHolder(String greenCardHolder) {
		this.greenCardHolder = greenCardHolder;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getCountryOfBirth() {
		return countryOfBirth;
	}
	public void setCountryOfBirth(String countryOfBirth) {
		this.countryOfBirth = countryOfBirth;
	}
	public String getCityOfBirth() {
		return cityOfBirth;
	}
	public void setCityOfBirth(String cityOfBirth) {
		this.cityOfBirth = cityOfBirth;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getIntFreq() {
		return intFreq;
	}
	public void setIntFreq(String intFreq) {
		this.intFreq = intFreq;
	}
	
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2_1() {
		return address2_1;
	}
	public void setAddress2_1(String address2_1) {
		this.address2_1 = address2_1;
	}
	public String getAddress3_1() {
		return address3_1;
	}
	public void setAddress3_1(String address3_1) {
		this.address3_1 = address3_1;
	}
	public String getAddCountry1() {
		return addCountry1;
	}
	public void setAddCountry1(String addCountry1) {
		this.addCountry1 = addCountry1;
	}
	public String getAddressstate1() {
		return addressstate1;
	}
	public void setAddressstate1(String addressstate1) {
		this.addressstate1 = addressstate1;
	}
	public String getAddressDistrict1() {
		return addressDistrict1;
	}
	public void setAddressDistrict1(String addressDistrict1) {
		this.addressDistrict1 = addressDistrict1;
	}
	public String getAddressCity1() {
		return addressCity1;
	}
	public void setAddressCity1(String addressCity1) {
		this.addressCity1 = addressCity1;
	}
	public Integer getAddresspincode1() {
		return addresspincode1;
	}
	public void setAddresspincode1(Integer addresspincode1) {
		this.addresspincode1 = addresspincode1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getAddress2_2() {
		return address2_2;
	}
	public void setAddress2_2(String address2_2) {
		this.address2_2 = address2_2;
	}
	public String getAddress3_2() {
		return address3_2;
	}
	public void setAddress3_2(String address3_2) {
		this.address3_2 = address3_2;
	}
	public String getAddCountry2() {
		return addCountry2;
	}
	public void setAddCountry2(String addCountry2) {
		this.addCountry2 = addCountry2;
	}
	public String getAddressstate2() {
		return addressstate2;
	}
	public void setAddressstate2(String addressstate2) {
		this.addressstate2 = addressstate2;
	}
	public String getAddressDistrict2() {
		return addressDistrict2;
	}
	public void setAddressDistrict2(String addressDistrict2) {
		this.addressDistrict2 = addressDistrict2;
	}
	public String getAddressCity2() {
		return addressCity2;
	}
	public void setAddressCity2(String addressCity2) {
		this.addressCity2 = addressCity2;
	}
	public Integer getAddresspincode2() {
		return addresspincode2;
	}
	public void setAddresspincode2(Integer addresspincode2) {
		this.addresspincode2 = addresspincode2;
	}
	public String getCkyc() {
		return ckyc;
	}
	public void setCkyc(String ckyc) {
		this.ckyc = ckyc;
	}
	public String getBankname() {
		return bankname;
	}
	public void setBankname(String bankname) {
		this.bankname = bankname;
	}
	public String getMicrCode() {
		return micrCode;
	}
	public void setMicrCode(String micrCode) {
		this.micrCode = micrCode;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	
	public String getBankbranch() {
		return bankbranch;
	}
	public void setBankbranch(String bankbranch) {
		this.bankbranch = bankbranch;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getConfirmaccountNumber() {
		return confirmaccountNumber;
	}
	public void setConfirmaccountNumber(String confirmaccountNumber) {
		this.confirmaccountNumber = confirmaccountNumber;
	}
	public String getCitizenship() {
		return citizenship;
	}
	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
	}
	public String getPrimaryHolderTitle() {
		return primaryHolderTitle;
	}
	public void setPrimaryHolderTitle(String primaryHolderTitle) {
		this.primaryHolderTitle = primaryHolderTitle;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getKycFatherPrefix() {
		return kycFatherPrefix;
	}
	public void setKycFatherPrefix(String kycFatherPrefix) {
		this.kycFatherPrefix = kycFatherPrefix;
	}
	
	public String getKycFatherFirstName() {
		return kycFatherFirstName;
	}
	public void setKycFatherFirstName(String kycFatherFirstName) {
		this.kycFatherFirstName = kycFatherFirstName;
	}
	public String getKycFatherMiddlename() {
		return kycFatherMiddlename;
	}
	public void setKycFatherMiddlename(String kycFatherMiddlename) {
		this.kycFatherMiddlename = kycFatherMiddlename;
	}
	public String getKycFatherLastName() {
		return kycFatherLastName;
	}
	public void setKycFatherLastName(String kycFatherLastName) {
		this.kycFatherLastName = kycFatherLastName;
	}
	public String getKycMotherPrefix() {
		return kycMotherPrefix;
	}
	public void setKycMotherPrefix(String kycMotherPrefix) {
		this.kycMotherPrefix = kycMotherPrefix;
	}
	
	public String getKycMotherFirstName() {
		return kycMotherFirstName;
	}
	public void setKycMotherFirstName(String kycMotherFirstName) {
		this.kycMotherFirstName = kycMotherFirstName;
	}
	public String getKycMotherMiddlename() {
		return kycMotherMiddlename;
	}
	public void setKycMotherMiddlename(String kycMotherMiddlename) {
		this.kycMotherMiddlename = kycMotherMiddlename;
	}
	public String getKycMotherLastName() {
		return kycMotherLastName;
	}
	public void setKycMotherLastName(String kycMotherLastName) {
		this.kycMotherLastName = kycMotherLastName;
	}
	public String getSchemeCode() {
		return schemeCode;
	}
	public void setSchemeCode(String schemeCode) {
		this.schemeCode = schemeCode;
	}
	public Double getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(Double interestRate) {
		this.interestRate = interestRate;
	}
	
	public MultipartFile getCanecelledcheque() {
		return canecelledcheque;
	}
	public void setCanecelledcheque(MultipartFile canecelledcheque) {
		this.canecelledcheque = canecelledcheque;
	}
	public MultipartFile getKycphotoproof() {
		return kycphotoproof;
	}
	public void setKycphotoproof(MultipartFile kycphotoproof) {
		this.kycphotoproof = kycphotoproof;
	}
	public MultipartFile getKycpanproof() {
		return kycpanproof;
	}
	public void setKycpanproof(MultipartFile kycpanproof) {
		this.kycpanproof = kycpanproof;
	}
	public MultipartFile getKycaddressproof() {
		return kycaddressproof;
	}
	public void setKycaddressproof(MultipartFile kycaddressproof) {
		this.kycaddressproof = kycaddressproof;
	}
	
	public String getAddressproofType() {
		return addressproofType;
	}
	public void setAddressproofType(String addressproofType) {
		this.addressproofType = addressproofType;
	}
	public String getAddressproofrefno() {
		return addressproofrefno;
	}
	public void setAddressproofrefno(String addressproofrefno) {
		this.addressproofrefno = addressproofrefno;
	}
	public String getAddressproofpxpirydate() {
		return addressproofpxpirydate;
	}
	public void setAddressproofpxpirydate(String addressproofpxpirydate) {
		this.addressproofpxpirydate = addressproofpxpirydate;
	}
	public String getNomineeprefix() {
		return nomineeprefix;
	}
	public void setNomineeprefix(String nomineeprefix) {
		this.nomineeprefix = nomineeprefix;
	}
	public String getNomineefirstname() {
		return nomineefirstname;
	}
	public void setNomineefirstname(String nomineefirstname) {
		this.nomineefirstname = nomineefirstname;
	}
	public String getNomineemiddlename() {
		return nomineemiddlename;
	}
	public void setNomineemiddlename(String nomineemiddlename) {
		this.nomineemiddlename = nomineemiddlename;
	}
	public String getNomineelastname() {
		return nomineelastname;
	}
	public void setNomineelastname(String nomineelastname) {
		this.nomineelastname = nomineelastname;
	}
	public String getNomineefullname() {
		return nomineefullname;
	}
	public void setNomineefullname(String nomineefullname) {
		this.nomineefullname = nomineefullname;
	}
	public String getNomineerelation() {
		return nomineerelation;
	}
	public void setNomineerelation(String nomineerelation) {
		this.nomineerelation = nomineerelation;
	}
	public String getNomineedob() {
		return nomineedob;
	}
	public void setNomineedob(String nomineedob) {
		this.nomineedob = nomineedob;
	}
	public String getNomineeminor() {
		return nomineeminor;
	}
	public void setNomineeminor(String nomineeminor) {
		this.nomineeminor = nomineeminor;
	}
	public String getNomineeguardian() {
		return nomineeguardian;
	}
	public void setNomineeguardian(String nomineeguardian) {
		this.nomineeguardian = nomineeguardian;
	}
	public String getNomineemobile() {
		return nomineemobile;
	}
	public void setNomineemobile(String nomineemobile) {
		this.nomineemobile = nomineemobile;
	}
	public String getNomineeemail() {
		return nomineeemail;
	}
	public void setNomineeemail(String nomineeemail) {
		this.nomineeemail = nomineeemail;
	}
	public String getNomineeaddress1() {
		return nomineeaddress1;
	}
	public void setNomineeaddress1(String nomineeaddress1) {
		this.nomineeaddress1 = nomineeaddress1;
	}
	public String getNomineeaddress2() {
		return nomineeaddress2;
	}
	public void setNomineeaddress2(String nomineeaddress2) {
		this.nomineeaddress2 = nomineeaddress2;
	}
	public String getNomineeaddress3() {
		return nomineeaddress3;
	}
	public void setNomineeaddress3(String nomineeaddress3) {
		this.nomineeaddress3 = nomineeaddress3;
	}
	public String getNomineestatecode() {
		return nomineestatecode;
	}
	public void setNomineestatecode(String nomineestatecode) {
		this.nomineestatecode = nomineestatecode;
	}
	public String getNomineedistrict() {
		return nomineedistrict;
	}
	public void setNomineedistrict(String nomineedistrict) {
		this.nomineedistrict = nomineedistrict;
	}
	public String getNomineecity() {
		return nomineecity;
	}
	public void setNomineecity(String nomineecity) {
		this.nomineecity = nomineecity;
	}
	public String getNomineecitypincode() {
		return nomineecitypincode;
	}
	public void setNomineecitypincode(String nomineecitypincode) {
		this.nomineecitypincode = nomineecitypincode;
	}
	public List<MahindraOtherCountryTaxDetails> getForeignTaxDetails() {
		return foreignTaxDetails;
	}
	public void setForeignTaxDetails(List<MahindraOtherCountryTaxDetails> foreignTaxDetails) {
		this.foreignTaxDetails = foreignTaxDetails;
	}
	public List<MahindraKycDocuments> getKycdocuments() {
		return kycdocuments;
	}
	public void setKycdocuments(List<MahindraKycDocuments> kycdocuments) {
		this.kycdocuments = kycdocuments;
	}
	public String getCancelledchecqueRefNo() {
		return cancelledchecqueRefNo;
	}
	public void setCancelledchecqueRefNo(String cancelledchecqueRefNo) {
		this.cancelledchecqueRefNo = cancelledchecqueRefNo;
	}
	
		
}
