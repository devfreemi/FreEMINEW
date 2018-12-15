package com.freemi.entity.investment.icici;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IciciGetSavedDetailsOutput implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@JsonProperty("PRIMARY_PAN_NO")
	private String primary_pan_no;

	@JsonProperty("PRIMARY_INVESTOR_NAME")
	private String primary_investor_name;

	@JsonProperty("PRIMARY_DOB")
	private String primary_dob;

	@JsonProperty("SECOND_HOLDER_PAN")
	private String second_holder_pan;

	@JsonProperty("SECOND_INVESTOR_NAME")
	private String second_investor_name;

	@JsonProperty("SECONDARY_DOB")
	private String secondary_dob;

	@JsonProperty("THIRD_HOLDER_PAN")
	private String third_holder_pan;

	@JsonProperty("THIRD_INVESTOR_NAME")
	private String third_investor_name;

	@JsonProperty("THIRD_DOB")
	private String third_dob;

	@JsonProperty("MINOR_GUARD_PAN_NO")
	private String minor_guard_pan_no;

	@JsonProperty("MINOR_GUARD_NAME")
	private String minor_guard_name;

	@JsonProperty("TAX_STATUS")
	private String tax_status;

	@JsonProperty("MODE_OF_HOLDING")
	private String mode_of_holding;

	@JsonProperty("FH_OCCUPATION")
	private String fh_occupation;

	@JsonProperty("SH_OCCUPATION")
	private String sh_occupation;

	@JsonProperty("TH_OCCUPATION")
	private String th_occupation;

	@JsonProperty("INVGROSSANNUALINC")
	private String invgrossannualinc;

	@JsonProperty("J1GROSSANNUALINC")
	private String j1grossannualinc;

	@JsonProperty("J2GROSSANNUALINC")
	private String j2grossannualinc;

	@JsonProperty("INVINDIVIDUAL")
	private String invindividual;

	@JsonProperty("J1INDIVIDUAL")
	private String j1individual;

	@JsonProperty("J2INDIVIDUAL")
	private String j2individual;

	@JsonProperty("INVOTHERINFORMATION")
	private String invotherinformation;

	@JsonProperty("J1OTHERINFORMATION")
	private String j1otherinformation;

	@JsonProperty("J2OTHERINFORMATION")
	private String j2otherinformation;

	@JsonProperty("EMAIL_ID")
	private String email_id;

	@JsonProperty("OTP_EMAILID")
	private String otp_emailid;

	@JsonProperty("MOBILE_NO")
	private String mobile_no;

	@JsonProperty("OFFICELL")
	private String officell;

	@JsonProperty("RESIDENTLL")
	private String residentll;

	@JsonProperty("ISNOMINEEMINOR")
	private String isnomineeminor;

	@JsonProperty("NOMINEENAME")
	private String nomineename;

	@JsonProperty("NOMINEEADDR")
	private String nomineeaddr;

	@JsonProperty("NOMINEEDOB")
	private String nomineedob;

	@JsonProperty("RELWITHNOMINEE")
	private String relwithnominee;

	@JsonProperty("NOMINEEGUARD")
	private String nomineeguard;

	@JsonProperty("MODE_OF_PAYMENT")
	private String mode_of_payment;

	@JsonProperty("ACCOUNT_NUMBER")
	private String account_number;

	@JsonProperty("ACCOUNT_TYPE")
	private String account_type;

	@JsonProperty("BANK_NAME")
	private String bank_name;

	@JsonProperty("BRANCH_NAME")
	private String branch_name;

	@JsonProperty("BANK_CITY")
	private String bank_city;

	@JsonProperty("BANK_ADDRESS")
	private String bank_address;

	@JsonProperty("BANK_IFSCCODE")
	private String bank_ifsccode;

	@JsonProperty("TAX_DESC")
	private String tax_desc;

	@JsonProperty("OCC_NAME1")
	private String occ_name1;

	@JsonProperty("OCC_NAME2")
	private String occ_name2;

	@JsonProperty("OCC_NAME3")
	private String occ_name3;

	@JsonProperty("BIRTH_PLACE1")
	private String birth_place1;

	@JsonProperty("BIRTH_PLACE2")
	private String birth_place2;

	@JsonProperty("BIRTH_PLACE3")
	private String birth_place3;

	@JsonProperty("SOURCE_WEALTH1")
	private String source_wealth1;

	@JsonProperty("SOURCE_WEALTH2")
	private String source_wealth2;

	@JsonProperty("SOURCE_WEALTH3")
	private String source_wealth3;

	@JsonProperty("SOURCE_WEALTH1_TEXT")
	private String source_wealth1_text;

	@JsonProperty("SOURCE_WEALTH2_TEXT")
	private String source_wealth2_text;

	@JsonProperty("SOURCE_WEALTH3_TEXT")
	private String source_wealth3_text;

	@JsonProperty("ADDRESS_TYPE1")
	private String address_type1;

	@JsonProperty("ADDRESS_TYPE2")
	private String address_type2;

	@JsonProperty("ADDRESS_TYPE3")
	private String address_type3;

	@JsonProperty("ADDRESS_TYPE1_TEXT")
	private String address_type1_text;

	@JsonProperty("ADDRESS_TYPE2_TEXT")
	private String address_type2_text;

	@JsonProperty("ADDRESS_TYPE3_TEXT")
	private String address_type3_text;

	public String getPrimary_pan_no() {
		return primary_pan_no;
	}

	public void setPrimary_pan_no(String primary_pan_no) {
		this.primary_pan_no = primary_pan_no;
	}

	public String getPrimary_investor_name() {
		return primary_investor_name;
	}

	public void setPrimary_investor_name(String primary_investor_name) {
		this.primary_investor_name = primary_investor_name;
	}

	public String getPrimary_dob() {
		return primary_dob;
	}

	public void setPrimary_dob(String primary_dob) {
		this.primary_dob = primary_dob;
	}

	public String getSecond_holder_pan() {
		return second_holder_pan;
	}

	public void setSecond_holder_pan(String second_holder_pan) {
		this.second_holder_pan = second_holder_pan;
	}

	public String getSecond_investor_name() {
		return second_investor_name;
	}

	public void setSecond_investor_name(String second_investor_name) {
		this.second_investor_name = second_investor_name;
	}

	public String getSecondary_dob() {
		return secondary_dob;
	}

	public void setSecondary_dob(String secondary_dob) {
		this.secondary_dob = secondary_dob;
	}

	public String getThird_holder_pan() {
		return third_holder_pan;
	}

	public void setThird_holder_pan(String third_holder_pan) {
		this.third_holder_pan = third_holder_pan;
	}

	public String getThird_investor_name() {
		return third_investor_name;
	}

	public void setThird_investor_name(String third_investor_name) {
		this.third_investor_name = third_investor_name;
	}

	public String getThird_dob() {
		return third_dob;
	}

	public void setThird_dob(String third_dob) {
		this.third_dob = third_dob;
	}

	public String getMinor_guard_pan_no() {
		return minor_guard_pan_no;
	}

	public void setMinor_guard_pan_no(String minor_guard_pan_no) {
		this.minor_guard_pan_no = minor_guard_pan_no;
	}

	public String getMinor_guard_name() {
		return minor_guard_name;
	}

	public void setMinor_guard_name(String minor_guard_name) {
		this.minor_guard_name = minor_guard_name;
	}

	public String getTax_status() {
		return tax_status;
	}

	public void setTax_status(String tax_status) {
		this.tax_status = tax_status;
	}

	public String getMode_of_holding() {
		return mode_of_holding;
	}

	public void setMode_of_holding(String mode_of_holding) {
		this.mode_of_holding = mode_of_holding;
	}

	public String getFh_occupation() {
		return fh_occupation;
	}

	public void setFh_occupation(String fh_occupation) {
		this.fh_occupation = fh_occupation;
	}

	public String getSh_occupation() {
		return sh_occupation;
	}

	public void setSh_occupation(String sh_occupation) {
		this.sh_occupation = sh_occupation;
	}

	public String getTh_occupation() {
		return th_occupation;
	}

	public void setTh_occupation(String th_occupation) {
		this.th_occupation = th_occupation;
	}

	public String getInvgrossannualinc() {
		return invgrossannualinc;
	}

	public void setInvgrossannualinc(String invgrossannualinc) {
		this.invgrossannualinc = invgrossannualinc;
	}

	public String getJ1grossannualinc() {
		return j1grossannualinc;
	}

	public void setJ1grossannualinc(String j1grossannualinc) {
		this.j1grossannualinc = j1grossannualinc;
	}

	public String getJ2grossannualinc() {
		return j2grossannualinc;
	}

	public void setJ2grossannualinc(String j2grossannualinc) {
		this.j2grossannualinc = j2grossannualinc;
	}

	public String getInvindividual() {
		return invindividual;
	}

	public void setInvindividual(String invindividual) {
		this.invindividual = invindividual;
	}

	public String getJ1individual() {
		return j1individual;
	}

	public void setJ1individual(String j1individual) {
		this.j1individual = j1individual;
	}

	public String getJ2individual() {
		return j2individual;
	}

	public void setJ2individual(String j2individual) {
		this.j2individual = j2individual;
	}

	public String getInvotherinformation() {
		return invotherinformation;
	}

	public void setInvotherinformation(String invotherinformation) {
		this.invotherinformation = invotherinformation;
	}

	public String getJ1otherinformation() {
		return j1otherinformation;
	}

	public void setJ1otherinformation(String j1otherinformation) {
		this.j1otherinformation = j1otherinformation;
	}

	public String getJ2otherinformation() {
		return j2otherinformation;
	}

	public void setJ2otherinformation(String j2otherinformation) {
		this.j2otherinformation = j2otherinformation;
	}

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	public String getOtp_emailid() {
		return otp_emailid;
	}

	public void setOtp_emailid(String otp_emailid) {
		this.otp_emailid = otp_emailid;
	}

	public String getMobile_no() {
		return mobile_no;
	}

	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}

	public String getOfficell() {
		return officell;
	}

	public void setOfficell(String officell) {
		this.officell = officell;
	}

	public String getResidentll() {
		return residentll;
	}

	public void setResidentll(String residentll) {
		this.residentll = residentll;
	}

	public String getIsnomineeminor() {
		return isnomineeminor;
	}

	public void setIsnomineeminor(String isnomineeminor) {
		this.isnomineeminor = isnomineeminor;
	}

	public String getNomineename() {
		return nomineename;
	}

	public void setNomineename(String nomineename) {
		this.nomineename = nomineename;
	}

	public String getNomineeaddr() {
		return nomineeaddr;
	}

	public void setNomineeaddr(String nomineeaddr) {
		this.nomineeaddr = nomineeaddr;
	}

	public String getNomineedob() {
		return nomineedob;
	}

	public void setNomineedob(String nomineedob) {
		this.nomineedob = nomineedob;
	}

	public String getRelwithnominee() {
		return relwithnominee;
	}

	public void setRelwithnominee(String relwithnominee) {
		this.relwithnominee = relwithnominee;
	}

	public String getNomineeguard() {
		return nomineeguard;
	}

	public void setNomineeguard(String nomineeguard) {
		this.nomineeguard = nomineeguard;
	}

	public String getMode_of_payment() {
		return mode_of_payment;
	}

	public void setMode_of_payment(String mode_of_payment) {
		this.mode_of_payment = mode_of_payment;
	}

	public String getAccount_number() {
		return account_number;
	}

	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}

	public String getAccount_type() {
		return account_type;
	}

	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}

	public String getBank_name() {
		return bank_name;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}

	public String getBranch_name() {
		return branch_name;
	}

	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}

	public String getBank_city() {
		return bank_city;
	}

	public void setBank_city(String bank_city) {
		this.bank_city = bank_city;
	}

	public String getBank_address() {
		return bank_address;
	}

	public void setBank_address(String bank_address) {
		this.bank_address = bank_address;
	}

	public String getBank_ifsccode() {
		return bank_ifsccode;
	}

	public void setBank_ifsccode(String bank_ifsccode) {
		this.bank_ifsccode = bank_ifsccode;
	}

	public String getTax_desc() {
		return tax_desc;
	}

	public void setTax_desc(String tax_desc) {
		this.tax_desc = tax_desc;
	}

	public String getOcc_name1() {
		return occ_name1;
	}

	public void setOcc_name1(String occ_name1) {
		this.occ_name1 = occ_name1;
	}

	public String getOcc_name2() {
		return occ_name2;
	}

	public void setOcc_name2(String occ_name2) {
		this.occ_name2 = occ_name2;
	}

	public String getOcc_name3() {
		return occ_name3;
	}

	public void setOcc_name3(String occ_name3) {
		this.occ_name3 = occ_name3;
	}

	public String getBirth_place1() {
		return birth_place1;
	}

	public void setBirth_place1(String birth_place1) {
		this.birth_place1 = birth_place1;
	}

	public String getBirth_place2() {
		return birth_place2;
	}

	public void setBirth_place2(String birth_place2) {
		this.birth_place2 = birth_place2;
	}

	public String getBirth_place3() {
		return birth_place3;
	}

	public void setBirth_place3(String birth_place3) {
		this.birth_place3 = birth_place3;
	}

	public String getSource_wealth1() {
		return source_wealth1;
	}

	public void setSource_wealth1(String source_wealth1) {
		this.source_wealth1 = source_wealth1;
	}

	public String getSource_wealth2() {
		return source_wealth2;
	}

	public void setSource_wealth2(String source_wealth2) {
		this.source_wealth2 = source_wealth2;
	}

	public String getSource_wealth3() {
		return source_wealth3;
	}

	public void setSource_wealth3(String source_wealth3) {
		this.source_wealth3 = source_wealth3;
	}

	public String getSource_wealth1_text() {
		return source_wealth1_text;
	}

	public void setSource_wealth1_text(String source_wealth1_text) {
		this.source_wealth1_text = source_wealth1_text;
	}

	public String getSource_wealth2_text() {
		return source_wealth2_text;
	}

	public void setSource_wealth2_text(String source_wealth2_text) {
		this.source_wealth2_text = source_wealth2_text;
	}

	public String getSource_wealth3_text() {
		return source_wealth3_text;
	}

	public void setSource_wealth3_text(String source_wealth3_text) {
		this.source_wealth3_text = source_wealth3_text;
	}

	public String getAddress_type1() {
		return address_type1;
	}

	public void setAddress_type1(String address_type1) {
		this.address_type1 = address_type1;
	}

	public String getAddress_type2() {
		return address_type2;
	}

	public void setAddress_type2(String address_type2) {
		this.address_type2 = address_type2;
	}

	public String getAddress_type3() {
		return address_type3;
	}

	public void setAddress_type3(String address_type3) {
		this.address_type3 = address_type3;
	}

	public String getAddress_type1_text() {
		return address_type1_text;
	}

	public void setAddress_type1_text(String address_type1_text) {
		this.address_type1_text = address_type1_text;
	}

	public String getAddress_type2_text() {
		return address_type2_text;
	}

	public void setAddress_type2_text(String address_type2_text) {
		this.address_type2_text = address_type2_text;
	}

	public String getAddress_type3_text() {
		return address_type3_text;
	}

	public void setAddress_type3_text(String address_type3_text) {
		this.address_type3_text = address_type3_text;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
		
}
