package com.freemi.entity.investment.mahindra;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Proxy;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="mahindra_cusotmer_kyc_details")
@Proxy(lazy=false)
public class MahindraCustomerKYCDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "SL_NO")
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer serial;

	@Column(name = "CUSTOMER_ID")
	private String customerId;

	@Column(name = "MOBILE")
	private String mobile;

	@Column(name="KYC_ID_CODE")
	private String kycid;
	
	@Column(name = "CANCELLED_CHEQUE")
	@Lob
	private byte[] canecelledcheque;
	
	@Column(name="KYC_PHOTO")
	@Lob
	private byte[] kycUplaodedPhoto;

	@Column(name="KYC_IDENTITY_PROOF")
	@Lob
	private byte[] kycIdentityProofDoc;

	@Column(name="KYC_ADDRESS_PROOF")
	@Lob
	private byte[] kycAddressProofDoc;
	
	@Column(name = "ADD_PROOF_TYPE")
	private String addprooftype;
	
	@Column(name = "ADD_PROOF_REF_NO")
	private String addproofrefno;
	
	@Column(name = "ADD_PROOF_EXPIRY_DATE")
	private Date addproofexpiry;

	@Column(name="IS_ACTIVE")
	private String active="Y";

	@JsonProperty("Scp_Code")
	@Transient
	private String scpCode;
	
	@JsonProperty("Ref_Type")
	@Transient
	private String refType;
	
	@JsonProperty("Ref_Cust_Code")
	@Transient
	private String refCustCode;
	
	@JsonProperty("Ref_Rm_Code")
	@Transient
	private String refRmCode;
	
	@JsonProperty("Ref_Othr_Code")
	@Transient
	private String refOthrCode;
	
	@JsonProperty("Source_Sub_Type")
	@Transient
	private String sourceSubType;
	
	@JsonProperty("Holder_Type")
	@Transient
	private String holderType;
	
	@JsonProperty("Kyc_ConstiType")
	@Transient
	private String kycConstiType;
	
	@JsonProperty("Kyc_AccType")
	@Transient
	private String kycAccType;
	
	@JsonProperty("Kyc_NamePrefix")
	@Transient
	private String kycNamePrefix;
	
	@JsonProperty("Kyc_FirstName")
	@Transient
	private String kycFirstName;
	
	@JsonProperty("Kyc_MiddleName")
	@Transient
	private String kycMiddleName;
	
	@JsonProperty("Kyc_LastName")
	@Transient
	private String kycLastName;
	
	@JsonProperty("Kyc_FullName")
	@Transient
	private String kycFullName;
	
	@JsonProperty("Kyc_MaidenNamePrefix")
	@Transient
	private String kycMaidenNamePrefix;
	
	@JsonProperty("Kyc_MaidenFirstName")
	@Transient
	private String kycMaidenFirstName;
	
	@JsonProperty("Kyc_MaidenFullName")
	@Transient
	private String kycMaidenFullName;
	
	@JsonProperty("Kyc_MaidenLastName")
	@Transient
	private String kycMaidenLastName;
	
	@JsonProperty("Kyc_MaidenMiddleName")
	@Transient
	private String kycMaidenMiddleName;
	
	@JsonProperty("Kyc_FatherFirstName")
	@Column(name = "FATHER_FIRST_NAME")
	private String kycFatherFirstName;

	@JsonProperty("Kyc_FatherFullName")
	@Transient
	private String kycFatherFullName;

	@JsonProperty("Kyc_FatherLastName")
	@Column(name = "FATHER_LAST_NAME")
	private String kycFatherLastName;
	
	@JsonProperty("Kyc_FatherMiddleName")
	@Column(name = "FATHER_MIDDLE_NAME")
	private String kycFatherMiddleName;

	@JsonProperty("Kyc_FatherNamePrefix")
	@Column(name = "FATHER_NAME_PREFIX")
	private String kycFatherNamePrefix;
	
	@JsonProperty("Kyc_SpouseFirstName")
	@Column(name = "SPOUSE_FIRST_NAME")
	private String kycSpouseFirstName;
	
	@JsonProperty("Kyc_SpouseFullName")
	@Transient
	private String kycSpouseFullName;
	
	@JsonProperty("Kyc_SpouseLastName")
	@Column(name = "SPOUSE_LAST_NAME")
	private String kycSpouseLastName;
	
	@JsonProperty("Kyc_SpouseMiddleName")
	@Column(name = "SPOUSE_MIDDLE_NAME")
	private String kycSpouseMiddleName;
	
	@JsonProperty("Kyc_SpouseNamePrefix")
	@Column(name = "SPOUSE_NAME_PREFIX")
	private String kycSpouseNamePrefix;
	
	@JsonProperty("Kyc_MotherFirstName")
	@Column(name = "MOTHER_FIRST_NAME")
	private String kycMotherFirstName;
	
	@JsonProperty("Kyc_MotherFullName")
	@Transient
	private String kycMotherFullName;

	@JsonProperty("Kyc_MotherLastName")
	@Column(name = "MOTHER_LAST_NAME")
	private String kycMotherLastName;
	
	@JsonProperty("Kyc_MotherMiddletName")
	@Column(name = "MOTHER_MIDDLE_NAME")
	private String kycMotherMiddletName;
	
	@JsonProperty("Kyc_MotherNamePrefix")
	@Column(name = "MOTHER_NAME_PREFIX")
	private String kycMotherNamePrefix;
	
	@JsonProperty("Kyc_Gender")
	@Transient
	private String kycGender;

	@JsonProperty("Kyc_MaritalStatus")
	@Transient
	private String kycMaritalStatus;
	
	@JsonProperty("Kyc_Nationality_Code")
	@Transient
	private String kycNationalityCode;
	
	@JsonProperty("Kyc_Occupation_Code")
	@Transient
	private String kycOccupationCode;
	
	@JsonProperty("Kyc_DOB")
	@Transient
	private String kycDOB;
	
	@JsonProperty("Kyc_ResidentialStatus_Code")
	@Transient
	private String kycResidentialStatusCode;
	
	@JsonProperty("Kyc_TaxResidencyOutsideIndia_Code")
	@Column(name = "KYC_RESIDENCY_OUTSIDE_INDIA")
	private String kycTaxResidencyOutsideIndiaCode;
	
	@JsonProperty("Kyc_JurisdictionofRes_Code")
	@Transient
	private String kycJurisdictionofResCode;
	
	@JsonProperty("Kyc_TIN")
	@Transient
	private String kycTIN;
	
	@JsonProperty("Kyc_CountryOfBirth")
	@Transient
	private String kycCountryOfBirth;
	
	@JsonProperty("Kyc_PlaceOfBirth")
	@Transient
	private String kycPlaceOfBirth;
	
	@JsonProperty("Kyc_Per_AddType_Code")
	@Transient
	private String kycPerAddTypeCode;
	
	@JsonProperty("Kyc_Per_Add1")
	@Transient
	private String kycPerAdd1;
	
	@JsonProperty("Kyc_Per_Add2")
	@Transient
	private String kycPerAdd2;
	
	@JsonProperty("Kyc_Per_Add3")
	@Transient
	private String kycPerAdd3;
	
	@JsonProperty("Kyc_Per_AddCity_Desc")
	@Transient
	private String kycPerAddCityDesc;
	
	@JsonProperty("Kyc_Per_AddDistrict_Desc")
	@Transient
	private String kycPerAddDistrictDesc;
	
	@JsonProperty("Kyc_Per_AddState_Code")
	@Transient
	private String kycPerAddStateCode;
	
	@JsonProperty("Kyc_Per_AddCountry_Code")
	@Transient
	private String kycPerAddCountryCode;
	
	@JsonProperty("Kyc_Per_AddPin")
	@Transient
	private String kycPerAddPin;
	
	@JsonProperty("Kyc_Per_AddPOA")
	@Transient
	private String kycPerAddPOA;
	
	@JsonProperty("Kyc_Per_AddPOAOthers")
	@Transient
	private String kycPerAddPOAOthers;
	
	@JsonProperty("Kyc_Per_AddSameAsCor_Add")
	@Transient
	private String kycPerAddSameAsCorAdd;
	
	@JsonProperty("Kyc_Cor_Add1")
	@Transient
	private String kycCorAdd1;
	
	@JsonProperty("Kyc_Cor_Add2")
	@Transient
	private String kycCorAdd2;
	
	@JsonProperty("Kyc_Cor_Add3")
	@Transient
	private String kycCorAdd3;
	
	@JsonProperty("Kyc_Cor_AddCity_Desc")
	@Transient
	private String kycCorAddCityDesc;
	
	@JsonProperty("Kyc_Cor_AddDistrict_Desc")
	@Transient
	private String kycCorAddDistrictDesc;
	
	@JsonProperty("Kyc_Cor_AddState_Code")
	@Transient
	private String kycCorAddStateCode;
	
	@JsonProperty("Kyc_Cor_AddCountry_Code")
	@Transient
	private String kycCorAddCountryCode;
	
	@JsonProperty("Kyc_Cor_AddPin")
	@Transient
	private String kycCorAddPin;
	
	@JsonProperty("Kyc_PerAddSameAsJurAdd")
	@Transient
	private String kycPerAddSameAsJurAdd;
	
	@JsonProperty("Kyc_Jur_Add1")
	@Transient
	private String kycJurAdd1;
	
	@JsonProperty("Kyc_Jur_Add2")
	@Transient
	private String kycJurAdd2;
	
	@JsonProperty("Kyc_Jur_Add3")
	@Transient
	private String kycJurAdd3;
	
	@JsonProperty("Kyc_Jur_AddCity_Desc")
	@Transient
	private String kycJurAddCityDesc;
	
	@JsonProperty("Kyc_Jur_AddState_Desc")
	@Transient
	private String kycJurAddStateDesc;
	
	@JsonProperty("Kyc_Jur_AddCountry_Code")
	@Transient
	private String kycJurAddCountryCode;
	
	@JsonProperty("Kyc_Jur_AddPin")
	@Transient
	private String kycJurAddPin;
	
	@JsonProperty("Kyc_ResTelSTD")
	@Transient
	private String kycResTelSTD;
	
	@JsonProperty("Kyc_ResTelNumber")
	@Transient
	private String kycResTelNumber;
	
	@JsonProperty("Kyc_OffTelSTD")
	@Transient
	private String kycOffTelSTD;
	
	@JsonProperty("Kyc_OffTelNumber")
	@Transient
	private String kycOffTelNumber;
	
	@JsonProperty("Kyc_MobileISD")
	@Transient
	private String kycMobileISD;
	
	@JsonProperty("Kyc_MobileNumber")
	@Transient
	private String kycMobileNumber;
	
	@JsonProperty("Kyc_EmailAdd")
	@Transient
	private String kycEmailAdd;
	
	@JsonProperty("Kyc_FAXSTD")
	@Transient
	private String kycFAXSTD;
	
	@JsonProperty("Kyc_FaxNumber")
	@Transient
	private String kycFaxNumber;
	
	@JsonProperty("Kyc_TypeofDocSubmitted")
	@Transient
	private String kycTypeofDocSubmitted;
	
	public Integer getSerial() {
		return serial;
	}
	public void setSerial(Integer serial) {
		this.serial = serial;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getKycid() {
		return kycid;
	}
	public void setKycid(String kycid) {
		this.kycid = kycid;
	}
	
	public byte[] getCanecelledcheque() {
		return canecelledcheque;
	}
	public void setCanecelledcheque(byte[] canecelledcheque) {
		this.canecelledcheque = canecelledcheque;
	}
	public byte[] getKycUplaodedPhoto() {
		return kycUplaodedPhoto;
	}
	public void setKycUplaodedPhoto(byte[] kycUplaodedPhoto) {
		this.kycUplaodedPhoto = kycUplaodedPhoto;
	}
	public byte[] getKycIdentityProofDoc() {
		return kycIdentityProofDoc;
	}
	public void setKycIdentityProofDoc(byte[] kycIdentityProofDoc) {
		this.kycIdentityProofDoc = kycIdentityProofDoc;
	}
	public byte[] getKycAddressProofDoc() {
		return kycAddressProofDoc;
	}
	public void setKycAddressProofDoc(byte[] kycAddressProofDoc) {
		this.kycAddressProofDoc = kycAddressProofDoc;
	}
	public String getAddprooftype() {
		return addprooftype;
	}
	public void setAddprooftype(String addprooftype) {
		this.addprooftype = addprooftype;
	}
	public String getAddproofrefno() {
		return addproofrefno;
	}
	public void setAddproofrefno(String addproofrefno) {
		this.addproofrefno = addproofrefno;
	}
	public Date getAddproofexpiry() {
		return addproofexpiry;
	}
	public void setAddproofexpiry(Date addproofexpiry) {
		this.addproofexpiry = addproofexpiry;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public String getScpCode() {
		return scpCode;
	}
	public void setScpCode(String scpCode) {
		this.scpCode = scpCode;
	}
	public String getRefType() {
		return refType;
	}
	public void setRefType(String refType) {
		this.refType = refType;
	}
	public String getRefCustCode() {
		return refCustCode;
	}
	public void setRefCustCode(String refCustCode) {
		this.refCustCode = refCustCode;
	}
	public String getRefRmCode() {
		return refRmCode;
	}
	public void setRefRmCode(String refRmCode) {
		this.refRmCode = refRmCode;
	}
	public String getRefOthrCode() {
		return refOthrCode;
	}
	public void setRefOthrCode(String refOthrCode) {
		this.refOthrCode = refOthrCode;
	}
	public String getSourceSubType() {
		return sourceSubType;
	}
	public void setSourceSubType(String sourceSubType) {
		this.sourceSubType = sourceSubType;
	}
	public String getHolderType() {
		return holderType;
	}
	public void setHolderType(String holderType) {
		this.holderType = holderType;
	}
	public String getKycConstiType() {
		return kycConstiType;
	}
	public void setKycConstiType(String kycConstiType) {
		this.kycConstiType = kycConstiType;
	}
	public String getKycAccType() {
		return kycAccType;
	}
	public void setKycAccType(String kycAccType) {
		this.kycAccType = kycAccType;
	}
	public String getKycNamePrefix() {
		return kycNamePrefix;
	}
	public void setKycNamePrefix(String kycNamePrefix) {
		this.kycNamePrefix = kycNamePrefix;
	}
	public String getKycFirstName() {
		return kycFirstName;
	}
	public void setKycFirstName(String kycFirstName) {
		this.kycFirstName = kycFirstName;
	}
	public String getKycMiddleName() {
		return kycMiddleName;
	}
	public void setKycMiddleName(String kycMiddleName) {
		this.kycMiddleName = kycMiddleName;
	}
	public String getKycLastName() {
		return kycLastName;
	}
	public void setKycLastName(String kycLastName) {
		this.kycLastName = kycLastName;
	}
	public String getKycFullName() {
		return kycFullName;
	}
	public void setKycFullName(String kycFullName) {
		this.kycFullName = kycFullName;
	}
	public String getKycMaidenNamePrefix() {
		return kycMaidenNamePrefix;
	}
	public void setKycMaidenNamePrefix(String kycMaidenNamePrefix) {
		this.kycMaidenNamePrefix = kycMaidenNamePrefix;
	}
	public String getKycMaidenFirstName() {
		return kycMaidenFirstName;
	}
	public void setKycMaidenFirstName(String kycMaidenFirstName) {
		this.kycMaidenFirstName = kycMaidenFirstName;
	}
	public String getKycMaidenFullName() {
		return kycMaidenFullName;
	}
	public void setKycMaidenFullName(String kycMaidenFullName) {
		this.kycMaidenFullName = kycMaidenFullName;
	}
	public String getKycMaidenLastName() {
		return kycMaidenLastName;
	}
	public void setKycMaidenLastName(String kycMaidenLastName) {
		this.kycMaidenLastName = kycMaidenLastName;
	}
	public String getKycMaidenMiddleName() {
		return kycMaidenMiddleName;
	}
	public void setKycMaidenMiddleName(String kycMaidenMiddleName) {
		this.kycMaidenMiddleName = kycMaidenMiddleName;
	}
	public String getKycFatherFirstName() {
		return kycFatherFirstName;
	}
	public void setKycFatherFirstName(String kycFatherFirstName) {
		this.kycFatherFirstName = kycFatherFirstName;
	}
	public String getKycFatherFullName() {
		return kycFatherFullName;
	}
	public void setKycFatherFullName(String kycFatherFullName) {
		this.kycFatherFullName = kycFatherFullName;
	}
	public String getKycFatherLastName() {
		return kycFatherLastName;
	}
	public void setKycFatherLastName(String kycFatherLastName) {
		this.kycFatherLastName = kycFatherLastName;
	}
	public String getKycFatherMiddleName() {
		return kycFatherMiddleName;
	}
	public void setKycFatherMiddleName(String kycFatherMiddleName) {
		this.kycFatherMiddleName = kycFatherMiddleName;
	}
	public String getKycFatherNamePrefix() {
		return kycFatherNamePrefix;
	}
	public void setKycFatherNamePrefix(String kycFatherNamePrefix) {
		this.kycFatherNamePrefix = kycFatherNamePrefix;
	}
	public String getKycSpouseFirstName() {
		return kycSpouseFirstName;
	}
	public void setKycSpouseFirstName(String kycSpouseFirstName) {
		this.kycSpouseFirstName = kycSpouseFirstName;
	}
	public String getKycSpouseFullName() {
		return kycSpouseFullName;
	}
	public void setKycSpouseFullName(String kycSpouseFullName) {
		this.kycSpouseFullName = kycSpouseFullName;
	}
	public String getKycSpouseLastName() {
		return kycSpouseLastName;
	}
	public void setKycSpouseLastName(String kycSpouseLastName) {
		this.kycSpouseLastName = kycSpouseLastName;
	}
	public String getKycSpouseMiddleName() {
		return kycSpouseMiddleName;
	}
	public void setKycSpouseMiddleName(String kycSpouseMiddleName) {
		this.kycSpouseMiddleName = kycSpouseMiddleName;
	}
	public String getKycSpouseNamePrefix() {
		return kycSpouseNamePrefix;
	}
	public void setKycSpouseNamePrefix(String kycSpouseNamePrefix) {
		this.kycSpouseNamePrefix = kycSpouseNamePrefix;
	}
	public String getKycMotherFirstName() {
		return kycMotherFirstName;
	}
	public void setKycMotherFirstName(String kycMotherFirstName) {
		this.kycMotherFirstName = kycMotherFirstName;
	}
	public String getKycMotherFullName() {
		return kycMotherFullName;
	}
	public void setKycMotherFullName(String kycMotherFullName) {
		this.kycMotherFullName = kycMotherFullName;
	}
	public String getKycMotherLastName() {
		return kycMotherLastName;
	}
	public void setKycMotherLastName(String kycMotherLastName) {
		this.kycMotherLastName = kycMotherLastName;
	}
	public String getKycMotherMiddletName() {
		return kycMotherMiddletName;
	}
	public void setKycMotherMiddletName(String kycMotherMiddletName) {
		this.kycMotherMiddletName = kycMotherMiddletName;
	}
	public String getKycMotherNamePrefix() {
		return kycMotherNamePrefix;
	}
	public void setKycMotherNamePrefix(String kycMotherNamePrefix) {
		this.kycMotherNamePrefix = kycMotherNamePrefix;
	}
	public String getKycGender() {
		return kycGender;
	}
	public void setKycGender(String kycGender) {
		this.kycGender = kycGender;
	}
	public String getKycMaritalStatus() {
		return kycMaritalStatus;
	}
	public void setKycMaritalStatus(String kycMaritalStatus) {
		this.kycMaritalStatus = kycMaritalStatus;
	}
	public String getKycNationalityCode() {
		return kycNationalityCode;
	}
	public void setKycNationalityCode(String kycNationalityCode) {
		this.kycNationalityCode = kycNationalityCode;
	}
	public String getKycOccupationCode() {
		return kycOccupationCode;
	}
	public void setKycOccupationCode(String kycOccupationCode) {
		this.kycOccupationCode = kycOccupationCode;
	}
	public String getKycDOB() {
		return kycDOB;
	}
	public void setKycDOB(String kycDOB) {
		this.kycDOB = kycDOB;
	}
	public String getKycResidentialStatusCode() {
		return kycResidentialStatusCode;
	}
	public void setKycResidentialStatusCode(String kycResidentialStatusCode) {
		this.kycResidentialStatusCode = kycResidentialStatusCode;
	}
	public String getKycTaxResidencyOutsideIndiaCode() {
		return kycTaxResidencyOutsideIndiaCode;
	}
	public void setKycTaxResidencyOutsideIndiaCode(String kycTaxResidencyOutsideIndiaCode) {
		this.kycTaxResidencyOutsideIndiaCode = kycTaxResidencyOutsideIndiaCode;
	}
	public String getKycJurisdictionofResCode() {
		return kycJurisdictionofResCode;
	}
	public void setKycJurisdictionofResCode(String kycJurisdictionofResCode) {
		this.kycJurisdictionofResCode = kycJurisdictionofResCode;
	}
	public String getKycTIN() {
		return kycTIN;
	}
	public void setKycTIN(String kycTIN) {
		this.kycTIN = kycTIN;
	}
	public String getKycCountryOfBirth() {
		return kycCountryOfBirth;
	}
	public void setKycCountryOfBirth(String kycCountryOfBirth) {
		this.kycCountryOfBirth = kycCountryOfBirth;
	}
	public String getKycPlaceOfBirth() {
		return kycPlaceOfBirth;
	}
	public void setKycPlaceOfBirth(String kycPlaceOfBirth) {
		this.kycPlaceOfBirth = kycPlaceOfBirth;
	}
	public String getKycPerAddTypeCode() {
		return kycPerAddTypeCode;
	}
	public void setKycPerAddTypeCode(String kycPerAddTypeCode) {
		this.kycPerAddTypeCode = kycPerAddTypeCode;
	}
	public String getKycPerAdd1() {
		return kycPerAdd1;
	}
	public void setKycPerAdd1(String kycPerAdd1) {
		this.kycPerAdd1 = kycPerAdd1;
	}
	public String getKycPerAdd2() {
		return kycPerAdd2;
	}
	public void setKycPerAdd2(String kycPerAdd2) {
		this.kycPerAdd2 = kycPerAdd2;
	}
	public String getKycPerAdd3() {
		return kycPerAdd3;
	}
	public void setKycPerAdd3(String kycPerAdd3) {
		this.kycPerAdd3 = kycPerAdd3;
	}
	public String getKycPerAddCityDesc() {
		return kycPerAddCityDesc;
	}
	public void setKycPerAddCityDesc(String kycPerAddCityDesc) {
		this.kycPerAddCityDesc = kycPerAddCityDesc;
	}
	public String getKycPerAddDistrictDesc() {
		return kycPerAddDistrictDesc;
	}
	public void setKycPerAddDistrictDesc(String kycPerAddDistrictDesc) {
		this.kycPerAddDistrictDesc = kycPerAddDistrictDesc;
	}
	public String getKycPerAddStateCode() {
		return kycPerAddStateCode;
	}
	public void setKycPerAddStateCode(String kycPerAddStateCode) {
		this.kycPerAddStateCode = kycPerAddStateCode;
	}
	public String getKycPerAddCountryCode() {
		return kycPerAddCountryCode;
	}
	public void setKycPerAddCountryCode(String kycPerAddCountryCode) {
		this.kycPerAddCountryCode = kycPerAddCountryCode;
	}
	public String getKycPerAddPin() {
		return kycPerAddPin;
	}
	public void setKycPerAddPin(String kycPerAddPin) {
		this.kycPerAddPin = kycPerAddPin;
	}
	public String getKycPerAddPOA() {
		return kycPerAddPOA;
	}
	public void setKycPerAddPOA(String kycPerAddPOA) {
		this.kycPerAddPOA = kycPerAddPOA;
	}
	public String getKycPerAddPOAOthers() {
		return kycPerAddPOAOthers;
	}
	public void setKycPerAddPOAOthers(String kycPerAddPOAOthers) {
		this.kycPerAddPOAOthers = kycPerAddPOAOthers;
	}
	public String getKycPerAddSameAsCorAdd() {
		return kycPerAddSameAsCorAdd;
	}
	public void setKycPerAddSameAsCorAdd(String kycPerAddSameAsCorAdd) {
		this.kycPerAddSameAsCorAdd = kycPerAddSameAsCorAdd;
	}
	public String getKycCorAdd1() {
		return kycCorAdd1;
	}
	public void setKycCorAdd1(String kycCorAdd1) {
		this.kycCorAdd1 = kycCorAdd1;
	}
	public String getKycCorAdd2() {
		return kycCorAdd2;
	}
	public void setKycCorAdd2(String kycCorAdd2) {
		this.kycCorAdd2 = kycCorAdd2;
	}
	public String getKycCorAdd3() {
		return kycCorAdd3;
	}
	public void setKycCorAdd3(String kycCorAdd3) {
		this.kycCorAdd3 = kycCorAdd3;
	}
	public String getKycCorAddCityDesc() {
		return kycCorAddCityDesc;
	}
	public void setKycCorAddCityDesc(String kycCorAddCityDesc) {
		this.kycCorAddCityDesc = kycCorAddCityDesc;
	}
	public String getKycCorAddDistrictDesc() {
		return kycCorAddDistrictDesc;
	}
	public void setKycCorAddDistrictDesc(String kycCorAddDistrictDesc) {
		this.kycCorAddDistrictDesc = kycCorAddDistrictDesc;
	}
	public String getKycCorAddStateCode() {
		return kycCorAddStateCode;
	}
	public void setKycCorAddStateCode(String kycCorAddStateCode) {
		this.kycCorAddStateCode = kycCorAddStateCode;
	}
	public String getKycCorAddCountryCode() {
		return kycCorAddCountryCode;
	}
	public void setKycCorAddCountryCode(String kycCorAddCountryCode) {
		this.kycCorAddCountryCode = kycCorAddCountryCode;
	}
	public String getKycCorAddPin() {
		return kycCorAddPin;
	}
	public void setKycCorAddPin(String kycCorAddPin) {
		this.kycCorAddPin = kycCorAddPin;
	}
	public String getKycPerAddSameAsJurAdd() {
		return kycPerAddSameAsJurAdd;
	}
	public void setKycPerAddSameAsJurAdd(String kycPerAddSameAsJurAdd) {
		this.kycPerAddSameAsJurAdd = kycPerAddSameAsJurAdd;
	}
	public String getKycJurAdd1() {
		return kycJurAdd1;
	}
	public void setKycJurAdd1(String kycJurAdd1) {
		this.kycJurAdd1 = kycJurAdd1;
	}
	public String getKycJurAdd2() {
		return kycJurAdd2;
	}
	public void setKycJurAdd2(String kycJurAdd2) {
		this.kycJurAdd2 = kycJurAdd2;
	}
	public String getKycJurAdd3() {
		return kycJurAdd3;
	}
	public void setKycJurAdd3(String kycJurAdd3) {
		this.kycJurAdd3 = kycJurAdd3;
	}
	public String getKycJurAddCityDesc() {
		return kycJurAddCityDesc;
	}
	public void setKycJurAddCityDesc(String kycJurAddCityDesc) {
		this.kycJurAddCityDesc = kycJurAddCityDesc;
	}
	public String getKycJurAddStateDesc() {
		return kycJurAddStateDesc;
	}
	public void setKycJurAddStateDesc(String kycJurAddStateDesc) {
		this.kycJurAddStateDesc = kycJurAddStateDesc;
	}
	public String getKycJurAddCountryCode() {
		return kycJurAddCountryCode;
	}
	public void setKycJurAddCountryCode(String kycJurAddCountryCode) {
		this.kycJurAddCountryCode = kycJurAddCountryCode;
	}
	public String getKycJurAddPin() {
		return kycJurAddPin;
	}
	public void setKycJurAddPin(String kycJurAddPin) {
		this.kycJurAddPin = kycJurAddPin;
	}
	public String getKycResTelSTD() {
		return kycResTelSTD;
	}
	public void setKycResTelSTD(String kycResTelSTD) {
		this.kycResTelSTD = kycResTelSTD;
	}
	public String getKycResTelNumber() {
		return kycResTelNumber;
	}
	public void setKycResTelNumber(String kycResTelNumber) {
		this.kycResTelNumber = kycResTelNumber;
	}
	public String getKycOffTelSTD() {
		return kycOffTelSTD;
	}
	public void setKycOffTelSTD(String kycOffTelSTD) {
		this.kycOffTelSTD = kycOffTelSTD;
	}
	public String getKycOffTelNumber() {
		return kycOffTelNumber;
	}
	public void setKycOffTelNumber(String kycOffTelNumber) {
		this.kycOffTelNumber = kycOffTelNumber;
	}
	public String getKycMobileISD() {
		return kycMobileISD;
	}
	public void setKycMobileISD(String kycMobileISD) {
		this.kycMobileISD = kycMobileISD;
	}
	public String getKycMobileNumber() {
		return kycMobileNumber;
	}
	public void setKycMobileNumber(String kycMobileNumber) {
		this.kycMobileNumber = kycMobileNumber;
	}
	public String getKycEmailAdd() {
		return kycEmailAdd;
	}
	public void setKycEmailAdd(String kycEmailAdd) {
		this.kycEmailAdd = kycEmailAdd;
	}
	public String getKycFAXSTD() {
		return kycFAXSTD;
	}
	public void setKycFAXSTD(String kycFAXSTD) {
		this.kycFAXSTD = kycFAXSTD;
	}
	public String getKycFaxNumber() {
		return kycFaxNumber;
	}
	public void setKycFaxNumber(String kycFaxNumber) {
		this.kycFaxNumber = kycFaxNumber;
	}
	public String getKycTypeofDocSubmitted() {
		return kycTypeofDocSubmitted;
	}
	public void setKycTypeofDocSubmitted(String kycTypeofDocSubmitted) {
		this.kycTypeofDocSubmitted = kycTypeofDocSubmitted;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}



}
