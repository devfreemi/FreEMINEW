package com.freemi.entity.Birla;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NewFolioCreationInput implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("Request")
	private Request requestObject;
	
	


	public Request getRequestObject() {
		return requestObject;
	}




	public void setRequestObject(Request requestObject) {
		this.requestObject = requestObject;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}




	public static class Request {

		public Request() {
			userId="";
			password="";
			gender="";
			id="";
			address1="";
			address2="";
			city="";
			pinCode="";
			state="";
			country="";
			bcnt_Flag="";
			pan_card="";
			dob="";
			applicantName="";
			valid_PAN="";
			jo_Holder_PAN_1="";
			jo_Holder_DOB_1="";
			jo_Holder_Name_1="";
			j1_Valid_PAN="";
			jo_Holder_PAN_2="";
			jo_Holder_DOB_2="";
			jo_Holder_Name_2="";
			j2_Valid_PAN="";
			statusCode="";
			holding_Nature="";
			mobile_No="";
			phone_Off="";
			phone_Resi="";
			email="";
			broker_Code="";
			gross_Annual_Income="";
			jh1_Gross_Annual_Income="";
			jh2_Gross_Annual_Income="";
			net_Worth="";
			jh1_Net_Worth="";
			jh2_Net_Worth="";
			net_Worth_AsOf="";
			jh1_Net_Worth_AsOf="";
			jh2_Net_Worth_AsOf="";
			occupation_code="";
			jh1_OCCUPATION_CODE="";
			jh2_OCCUPATION_CODE="";
			pep_Flag="";
			jh1_Pep_Flag="";
			jh2_Pep_Flag="";
			addl_INFORMATION="";
			ubo_DECLARATION="";
			country_of_birth="";
			jh1_COUNTRY_OF_BIRTH="";
			jh2_COUNTRY_OF_BIRTH="";
			country_of_citizen="";
			jh1_COUNTRY_OF_CITIZEN="";
			jh2_COUNTRY_OF_CITIZEN="";
			country_taxresidency1="";
			tax_PAYER_ID_NO1="";
			country_TAXRESIDENCY2="";
			tax_PAYER_ID_NO2="";
			country_TAXRESIDENCY3="";
			tax_PAYER_ID_NO3="";
			jh1_COUNTRY_TAXRESIDENCY1="";
			jh1_TAX_PAYER_ID_NO1="";
			jh1_COUNTRY_TAXRESIDENCY2="";
			jh1_TAX_PAYER_ID_NO2="";
			jh1_COUNTRY_TAXRESIDENCY3="";
			jh1_TAX_PAYER_ID_NO3="";
			jh2_COUNTRY_TAXRESIDENCY1="";
			jh2_TAX_PAYER_ID_NO1="";
			jh2_COUNTRY_TAXRESIDENCY2="";
			jh2_TAX_PAYER_ID_NO2="";
			jh2_COUNTRY_TAXRESIDENCY3="";
			jh2_TAX_PAYER_ID_NO3="";
			bank_Name="";
			branch_Name="";
			branch_Address="";
			branch_Pin_Code="";
			branch_City="";
			account_Type="";
			account_No="";
			ifsc_Code="";
			nominee_Name="";
			nominee_dob="";
			nominee_Relationship="";
			source="";
			ekyc="";
			placeOfBirth="";
			jh1_PlaceOfBirth="";
			jh2_PlaceOfBirth="";
			aadhaarNo="";
			jh1_BCNT_Flag="";
			jh2_BCNT_Flag="";
			fatherOrSpouse="";
			maritalStatus="";
			ckycNo="";
			applicantPrefix="";
			maidenPrefix="";
			maidenName="";
			motherPrefix="";
			motherName="";
			fatherPrefix="";
			district="";
			addressType="";

		}

		@JsonProperty("UserId")
		private String userId;

		@JsonProperty("Password")
		private String password;

		@JsonProperty("Gender")
		private String gender;

		@JsonProperty("ID")
		private String id;

		@JsonProperty("Address1")
		private String address1;

		@JsonProperty("Address2")
		private String address2;

		@JsonProperty("City")
		private String city;

		@JsonProperty("PinCode")
		private String pinCode;

		@JsonProperty("State")
		private String state;

		@JsonProperty("Country")
		private String country;

		@JsonProperty("BCNT_Flag")
		private String bcnt_Flag;

		@JsonProperty("PAN_card")
		private String pan_card;

		@JsonProperty("DOB")
		private String dob;

		@JsonProperty("ApplicantName")
		private String applicantName;

		@JsonProperty("Valid_PAN")
		private String valid_PAN;

		@JsonProperty("JO_Holder_PAN_1")
		private String jo_Holder_PAN_1;

		@JsonProperty("JO_Holder_DOB_1")
		private String jo_Holder_DOB_1;

		@JsonProperty("JO_Holder_Name_1")
		private String jo_Holder_Name_1;


		@JsonProperty("J1_Valid_PAN")
		private String j1_Valid_PAN;

		@JsonProperty("JO_Holder_PAN_2")
		private String jo_Holder_PAN_2;

		@JsonProperty("JO_Holder_DOB_2")
		private String jo_Holder_DOB_2;

		@JsonProperty("JO_Holder_Name_2")
		private String jo_Holder_Name_2;

		@JsonProperty("J2_Valid_PAN")
		private String j2_Valid_PAN;

		@JsonProperty("StatusCode")
		private String statusCode;

		@JsonProperty("Holding_Nature")
		private String holding_Nature;

		@JsonProperty("Mobile_No")
		private String mobile_No;

		@JsonProperty("Phone_Off")
		private String phone_Off;

		@JsonProperty("Phone_Resi")
		private String phone_Resi;

		@JsonProperty("Email")
		private String email;

		@JsonProperty("Broker_Code")
		private String broker_Code;

		@JsonProperty("Gross_Annual_Income")
		private String gross_Annual_Income;

		@JsonProperty("JH1_Gross_Annual_Income")
		private String jh1_Gross_Annual_Income;

		@JsonProperty("JH2_Gross_Annual_Income")
		private String jh2_Gross_Annual_Income;

		@JsonProperty("Net_Worth")
		private String net_Worth;

		@JsonProperty("JH1_Net_Worth")
		private String jh1_Net_Worth;

		@JsonProperty("JH2_Net_Worth")
		private String jh2_Net_Worth;

		@JsonProperty("Net_Worth_AsOf")
		private String net_Worth_AsOf;

		@JsonProperty("JH1_Net_Worth_AsOf")
		private String jh1_Net_Worth_AsOf;

		@JsonProperty("JH2_Net_Worth_AsOf")
		private String jh2_Net_Worth_AsOf;

		@JsonProperty("OCCUPATION_CODE")
		private String occupation_code;

		@JsonProperty("JH1_OCCUPATION_CODE")
		private String jh1_OCCUPATION_CODE;

		@JsonProperty("JH2_OCCUPATION_CODE")
		private String jh2_OCCUPATION_CODE;

		@JsonProperty("Pep_Flag")
		private String pep_Flag;

		@JsonProperty("JH1_Pep_Flag")
		private String jh1_Pep_Flag;

		@JsonProperty("JH2_Pep_Flag")
		private String jh2_Pep_Flag;

		@JsonProperty("ADDL_INFORMATION")
		private String addl_INFORMATION;

		@JsonProperty("UBO_DECLARATION")
		private String ubo_DECLARATION;

		@JsonProperty("COUNTRY_OF_BIRTH")
		private String country_of_birth;

		@JsonProperty("JH1_COUNTRY_OF_BIRTH")
		private String jh1_COUNTRY_OF_BIRTH;

		@JsonProperty("JH2_COUNTRY_OF_BIRTH")
		private String jh2_COUNTRY_OF_BIRTH;

		@JsonProperty("COUNTRY_OF_CITIZEN")
		private String country_of_citizen;

		@JsonProperty("JH1_COUNTRY_OF_CITIZEN")
		private String jh1_COUNTRY_OF_CITIZEN;

		@JsonProperty("JH2_COUNTRY_OF_CITIZEN")
		private String jh2_COUNTRY_OF_CITIZEN;

		@JsonProperty("COUNTRY_TAXRESIDENCY1")
		private String country_taxresidency1;

		@JsonProperty("TAX_PAYER_ID_NO1")
		private String tax_PAYER_ID_NO1;

		@JsonProperty("COUNTRY_TAXRESIDENCY2")
		private String country_TAXRESIDENCY2;

		@JsonProperty("TAX_PAYER_ID_NO2")
		private String tax_PAYER_ID_NO2;

		@JsonProperty("COUNTRY_TAXRESIDENCY3")
		private String country_TAXRESIDENCY3;

		@JsonProperty("TAX_PAYER_ID_NO3")
		private String tax_PAYER_ID_NO3;

		@JsonProperty("JH1_COUNTRY_TAXRESIDENCY1")
		private String jh1_COUNTRY_TAXRESIDENCY1;

		@JsonProperty("JH1_TAX_PAYER_ID_NO1")
		private String jh1_TAX_PAYER_ID_NO1;

		@JsonProperty("JH1_COUNTRY_TAXRESIDENCY2")
		private String jh1_COUNTRY_TAXRESIDENCY2;

		@JsonProperty("JH1_TAX_PAYER_ID_NO2")
		private String jh1_TAX_PAYER_ID_NO2;

		@JsonProperty("JH1_COUNTRY_TAXRESIDENCY3")
		private String jh1_COUNTRY_TAXRESIDENCY3;

		@JsonProperty("JH1_TAX_PAYER_ID_NO3")
		private String jh1_TAX_PAYER_ID_NO3;

		@JsonProperty("JH2_COUNTRY_TAXRESIDENCY1")
		private String jh2_COUNTRY_TAXRESIDENCY1;

		@JsonProperty("JH2_TAX_PAYER_ID_NO1")
		private String jh2_TAX_PAYER_ID_NO1;

		@JsonProperty("JH2_COUNTRY_TAXRESIDENCY2")
		private String jh2_COUNTRY_TAXRESIDENCY2;

		@JsonProperty("JH2_TAX_PAYER_ID_NO2")
		private String jh2_TAX_PAYER_ID_NO2;

		@JsonProperty("JH2_COUNTRY_TAXRESIDENCY3")
		private String jh2_COUNTRY_TAXRESIDENCY3;

		@JsonProperty("JH2_TAX_PAYER_ID_NO3")
		private String jh2_TAX_PAYER_ID_NO3;

		@JsonProperty("Bank_Name")
		private String bank_Name;

		@JsonProperty("Branch_Name")
		private String branch_Name;

		@JsonProperty("Branch_Address")
		private String branch_Address;

		@JsonProperty("Branch_Pin_Code")
		private String branch_Pin_Code;

		@JsonProperty("Branch_City")
		private String branch_City;

		@JsonProperty("Account_Type")
		private String account_Type;

		@JsonProperty("Account_No")
		private String account_No;

		@JsonProperty("IFSC_Code")
		private String ifsc_Code;

		@JsonProperty("Nominee_Name")
		private String nominee_Name;

		@JsonProperty("Nominee_dob")
		private String nominee_dob;

		@JsonProperty("Nominee_Relationship")
		private String nominee_Relationship;

		@JsonProperty("Source")
		private String source;

		@JsonProperty("Ekyc")
		private String ekyc;

		@JsonProperty("PlaceOfBirth")
		private String placeOfBirth;

		@JsonProperty("JH1_PlaceOfBirth")
		private String jh1_PlaceOfBirth;

		@JsonProperty("JH2_PlaceOfBirth")
		private String jh2_PlaceOfBirth;

		@JsonProperty("AadhaarNo")
		private String aadhaarNo;

		@JsonProperty("JH1_BCNT_Flag")
		private String jh1_BCNT_Flag;

		@JsonProperty("JH2_BCNT_Flag")
		private String jh2_BCNT_Flag;

		@JsonProperty("FatherOrSpouse")
		private String fatherOrSpouse;

		@JsonProperty("MaritalStatus")
		private String maritalStatus;

		@JsonProperty("CKYCNo")
		private String ckycNo;

		@JsonProperty("ApplicantPrefix")
		private String applicantPrefix;

		@JsonProperty("MaidenPrefix")
		private String maidenPrefix;

		@JsonProperty("MaidenName")
		private String maidenName;

		@JsonProperty("MotherPrefix")
		private String motherPrefix;

		@JsonProperty("MotherName")
		private String motherName;

		@JsonProperty("FatherPrefix")
		private String fatherPrefix;

		@JsonProperty("District")
		private String district;

		@JsonProperty("AddressType")
		private String addressType;

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
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

		public String getPinCode() {
			return pinCode;
		}

		public void setPinCode(String pinCode) {
			this.pinCode = pinCode;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}

		public String getBcnt_Flag() {
			return bcnt_Flag;
		}

		public void setBcnt_Flag(String bcnt_Flag) {
			this.bcnt_Flag = bcnt_Flag;
		}

		public String getPan_card() {
			return pan_card;
		}

		public void setPan_card(String pan_card) {
			this.pan_card = pan_card;
		}

		public String getDob() {
			return dob;
		}

		public void setDob(String dob) {
			this.dob = dob;
		}

		public String getApplicantName() {
			return applicantName;
		}

		public void setApplicantName(String applicantName) {
			this.applicantName = applicantName;
		}

		public String getValid_PAN() {
			return valid_PAN;
		}

		public void setValid_PAN(String valid_PAN) {
			this.valid_PAN = valid_PAN;
		}

		public String getJo_Holder_PAN_1() {
			return jo_Holder_PAN_1;
		}

		public void setJo_Holder_PAN_1(String jo_Holder_PAN_1) {
			this.jo_Holder_PAN_1 = jo_Holder_PAN_1;
		}

		public String getJo_Holder_DOB_1() {
			return jo_Holder_DOB_1;
		}

		public void setJo_Holder_DOB_1(String jo_Holder_DOB_1) {
			this.jo_Holder_DOB_1 = jo_Holder_DOB_1;
		}

		public String getJo_Holder_Name_1() {
			return jo_Holder_Name_1;
		}

		public void setJo_Holder_Name_1(String jo_Holder_Name_1) {
			this.jo_Holder_Name_1 = jo_Holder_Name_1;
		}

		public String getJ1_Valid_PAN() {
			return j1_Valid_PAN;
		}

		public void setJ1_Valid_PAN(String j1_Valid_PAN) {
			this.j1_Valid_PAN = j1_Valid_PAN;
		}

		public String getJo_Holder_PAN_2() {
			return jo_Holder_PAN_2;
		}

		public void setJo_Holder_PAN_2(String jo_Holder_PAN_2) {
			this.jo_Holder_PAN_2 = jo_Holder_PAN_2;
		}

		public String getJo_Holder_DOB_2() {
			return jo_Holder_DOB_2;
		}

		public void setJo_Holder_DOB_2(String jo_Holder_DOB_2) {
			this.jo_Holder_DOB_2 = jo_Holder_DOB_2;
		}

		public String getJo_Holder_Name_2() {
			return jo_Holder_Name_2;
		}

		public void setJo_Holder_Name_2(String jo_Holder_Name_2) {
			this.jo_Holder_Name_2 = jo_Holder_Name_2;
		}

		public String getJ2_Valid_PAN() {
			return j2_Valid_PAN;
		}

		public void setJ2_Valid_PAN(String j2_Valid_PAN) {
			this.j2_Valid_PAN = j2_Valid_PAN;
		}

		public String getStatusCode() {
			return statusCode;
		}

		public void setStatusCode(String statusCode) {
			this.statusCode = statusCode;
		}

		public String getHolding_Nature() {
			return holding_Nature;
		}

		public void setHolding_Nature(String holding_Nature) {
			this.holding_Nature = holding_Nature;
		}

		public String getMobile_No() {
			return mobile_No;
		}

		public void setMobile_No(String mobile_No) {
			this.mobile_No = mobile_No;
		}

		public String getPhone_Off() {
			return phone_Off;
		}

		public void setPhone_Off(String phone_Off) {
			this.phone_Off = phone_Off;
		}

		public String getPhone_Resi() {
			return phone_Resi;
		}

		public void setPhone_Resi(String phone_Resi) {
			this.phone_Resi = phone_Resi;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getBroker_Code() {
			return broker_Code;
		}

		public void setBroker_Code(String broker_Code) {
			this.broker_Code = broker_Code;
		}

		public String getGross_Annual_Income() {
			return gross_Annual_Income;
		}

		public void setGross_Annual_Income(String gross_Annual_Income) {
			this.gross_Annual_Income = gross_Annual_Income;
		}

		public String getJh1_Gross_Annual_Income() {
			return jh1_Gross_Annual_Income;
		}

		public void setJh1_Gross_Annual_Income(String jh1_Gross_Annual_Income) {
			this.jh1_Gross_Annual_Income = jh1_Gross_Annual_Income;
		}

		public String getJh2_Gross_Annual_Income() {
			return jh2_Gross_Annual_Income;
		}

		public void setJh2_Gross_Annual_Income(String jh2_Gross_Annual_Income) {
			this.jh2_Gross_Annual_Income = jh2_Gross_Annual_Income;
		}

		public String getNet_Worth() {
			return net_Worth;
		}

		public void setNet_Worth(String net_Worth) {
			this.net_Worth = net_Worth;
		}

		public String getJh1_Net_Worth() {
			return jh1_Net_Worth;
		}

		public void setJh1_Net_Worth(String jh1_Net_Worth) {
			this.jh1_Net_Worth = jh1_Net_Worth;
		}

		public String getJh2_Net_Worth() {
			return jh2_Net_Worth;
		}

		public void setJh2_Net_Worth(String jh2_Net_Worth) {
			this.jh2_Net_Worth = jh2_Net_Worth;
		}

		public String getNet_Worth_AsOf() {
			return net_Worth_AsOf;
		}

		public void setNet_Worth_AsOf(String net_Worth_AsOf) {
			this.net_Worth_AsOf = net_Worth_AsOf;
		}

		public String getJh1_Net_Worth_AsOf() {
			return jh1_Net_Worth_AsOf;
		}

		public void setJh1_Net_Worth_AsOf(String jh1_Net_Worth_AsOf) {
			this.jh1_Net_Worth_AsOf = jh1_Net_Worth_AsOf;
		}

		public String getJh2_Net_Worth_AsOf() {
			return jh2_Net_Worth_AsOf;
		}

		public void setJh2_Net_Worth_AsOf(String jh2_Net_Worth_AsOf) {
			this.jh2_Net_Worth_AsOf = jh2_Net_Worth_AsOf;
		}

		public String getOccupation_code() {
			return occupation_code;
		}

		public void setOccupation_code(String occupation_code) {
			this.occupation_code = occupation_code;
		}

		public String getJh1_OCCUPATION_CODE() {
			return jh1_OCCUPATION_CODE;
		}

		public void setJh1_OCCUPATION_CODE(String jh1_OCCUPATION_CODE) {
			this.jh1_OCCUPATION_CODE = jh1_OCCUPATION_CODE;
		}

		public String getJh2_OCCUPATION_CODE() {
			return jh2_OCCUPATION_CODE;
		}

		public void setJh2_OCCUPATION_CODE(String jh2_OCCUPATION_CODE) {
			this.jh2_OCCUPATION_CODE = jh2_OCCUPATION_CODE;
		}

		public String getPep_Flag() {
			return pep_Flag;
		}

		public void setPep_Flag(String pep_Flag) {
			this.pep_Flag = pep_Flag;
		}

		public String getJh1_Pep_Flag() {
			return jh1_Pep_Flag;
		}

		public void setJh1_Pep_Flag(String jh1_Pep_Flag) {
			this.jh1_Pep_Flag = jh1_Pep_Flag;
		}

		public String getJh2_Pep_Flag() {
			return jh2_Pep_Flag;
		}

		public void setJh2_Pep_Flag(String jh2_Pep_Flag) {
			this.jh2_Pep_Flag = jh2_Pep_Flag;
		}

		public String getAddl_INFORMATION() {
			return addl_INFORMATION;
		}

		public void setAddl_INFORMATION(String addl_INFORMATION) {
			this.addl_INFORMATION = addl_INFORMATION;
		}

		public String getUbo_DECLARATION() {
			return ubo_DECLARATION;
		}

		public void setUbo_DECLARATION(String ubo_DECLARATION) {
			this.ubo_DECLARATION = ubo_DECLARATION;
		}

		public String getCountry_of_birth() {
			return country_of_birth;
		}

		public void setCountry_of_birth(String country_of_birth) {
			this.country_of_birth = country_of_birth;
		}

		public String getJh1_COUNTRY_OF_BIRTH() {
			return jh1_COUNTRY_OF_BIRTH;
		}

		public void setJh1_COUNTRY_OF_BIRTH(String jh1_COUNTRY_OF_BIRTH) {
			this.jh1_COUNTRY_OF_BIRTH = jh1_COUNTRY_OF_BIRTH;
		}

		public String getJh2_COUNTRY_OF_BIRTH() {
			return jh2_COUNTRY_OF_BIRTH;
		}

		public void setJh2_COUNTRY_OF_BIRTH(String jh2_COUNTRY_OF_BIRTH) {
			this.jh2_COUNTRY_OF_BIRTH = jh2_COUNTRY_OF_BIRTH;
		}

		public String getCountry_of_citizen() {
			return country_of_citizen;
		}

		public void setCountry_of_citizen(String country_of_citizen) {
			this.country_of_citizen = country_of_citizen;
		}

		public String getJh1_COUNTRY_OF_CITIZEN() {
			return jh1_COUNTRY_OF_CITIZEN;
		}

		public void setJh1_COUNTRY_OF_CITIZEN(String jh1_COUNTRY_OF_CITIZEN) {
			this.jh1_COUNTRY_OF_CITIZEN = jh1_COUNTRY_OF_CITIZEN;
		}

		public String getJh2_COUNTRY_OF_CITIZEN() {
			return jh2_COUNTRY_OF_CITIZEN;
		}

		public void setJh2_COUNTRY_OF_CITIZEN(String jh2_COUNTRY_OF_CITIZEN) {
			this.jh2_COUNTRY_OF_CITIZEN = jh2_COUNTRY_OF_CITIZEN;
		}

		public String getCountry_taxresidency1() {
			return country_taxresidency1;
		}

		public void setCountry_taxresidency1(String country_taxresidency1) {
			this.country_taxresidency1 = country_taxresidency1;
		}

		public String getTax_PAYER_ID_NO1() {
			return tax_PAYER_ID_NO1;
		}

		public void setTax_PAYER_ID_NO1(String tax_PAYER_ID_NO1) {
			this.tax_PAYER_ID_NO1 = tax_PAYER_ID_NO1;
		}

		public String getCountry_TAXRESIDENCY2() {
			return country_TAXRESIDENCY2;
		}

		public void setCountry_TAXRESIDENCY2(String country_TAXRESIDENCY2) {
			this.country_TAXRESIDENCY2 = country_TAXRESIDENCY2;
		}

		public String getTax_PAYER_ID_NO2() {
			return tax_PAYER_ID_NO2;
		}

		public void setTax_PAYER_ID_NO2(String tax_PAYER_ID_NO2) {
			this.tax_PAYER_ID_NO2 = tax_PAYER_ID_NO2;
		}

		public String getCountry_TAXRESIDENCY3() {
			return country_TAXRESIDENCY3;
		}

		public void setCountry_TAXRESIDENCY3(String country_TAXRESIDENCY3) {
			this.country_TAXRESIDENCY3 = country_TAXRESIDENCY3;
		}

		public String getTax_PAYER_ID_NO3() {
			return tax_PAYER_ID_NO3;
		}

		public void setTax_PAYER_ID_NO3(String tax_PAYER_ID_NO3) {
			this.tax_PAYER_ID_NO3 = tax_PAYER_ID_NO3;
		}

		public String getJh1_COUNTRY_TAXRESIDENCY1() {
			return jh1_COUNTRY_TAXRESIDENCY1;
		}

		public void setJh1_COUNTRY_TAXRESIDENCY1(String jh1_COUNTRY_TAXRESIDENCY1) {
			this.jh1_COUNTRY_TAXRESIDENCY1 = jh1_COUNTRY_TAXRESIDENCY1;
		}

		public String getJh1_TAX_PAYER_ID_NO1() {
			return jh1_TAX_PAYER_ID_NO1;
		}

		public void setJh1_TAX_PAYER_ID_NO1(String jh1_TAX_PAYER_ID_NO1) {
			this.jh1_TAX_PAYER_ID_NO1 = jh1_TAX_PAYER_ID_NO1;
		}

		public String getJh1_COUNTRY_TAXRESIDENCY2() {
			return jh1_COUNTRY_TAXRESIDENCY2;
		}

		public void setJh1_COUNTRY_TAXRESIDENCY2(String jh1_COUNTRY_TAXRESIDENCY2) {
			this.jh1_COUNTRY_TAXRESIDENCY2 = jh1_COUNTRY_TAXRESIDENCY2;
		}

		public String getJh1_TAX_PAYER_ID_NO2() {
			return jh1_TAX_PAYER_ID_NO2;
		}

		public void setJh1_TAX_PAYER_ID_NO2(String jh1_TAX_PAYER_ID_NO2) {
			this.jh1_TAX_PAYER_ID_NO2 = jh1_TAX_PAYER_ID_NO2;
		}

		public String getJh1_COUNTRY_TAXRESIDENCY3() {
			return jh1_COUNTRY_TAXRESIDENCY3;
		}

		public void setJh1_COUNTRY_TAXRESIDENCY3(String jh1_COUNTRY_TAXRESIDENCY3) {
			this.jh1_COUNTRY_TAXRESIDENCY3 = jh1_COUNTRY_TAXRESIDENCY3;
		}

		public String getJh1_TAX_PAYER_ID_NO3() {
			return jh1_TAX_PAYER_ID_NO3;
		}

		public void setJh1_TAX_PAYER_ID_NO3(String jh1_TAX_PAYER_ID_NO3) {
			this.jh1_TAX_PAYER_ID_NO3 = jh1_TAX_PAYER_ID_NO3;
		}

		public String getJh2_COUNTRY_TAXRESIDENCY1() {
			return jh2_COUNTRY_TAXRESIDENCY1;
		}

		public void setJh2_COUNTRY_TAXRESIDENCY1(String jh2_COUNTRY_TAXRESIDENCY1) {
			this.jh2_COUNTRY_TAXRESIDENCY1 = jh2_COUNTRY_TAXRESIDENCY1;
		}

		public String getJh2_TAX_PAYER_ID_NO1() {
			return jh2_TAX_PAYER_ID_NO1;
		}

		public void setJh2_TAX_PAYER_ID_NO1(String jh2_TAX_PAYER_ID_NO1) {
			this.jh2_TAX_PAYER_ID_NO1 = jh2_TAX_PAYER_ID_NO1;
		}

		public String getJh2_COUNTRY_TAXRESIDENCY2() {
			return jh2_COUNTRY_TAXRESIDENCY2;
		}

		public void setJh2_COUNTRY_TAXRESIDENCY2(String jh2_COUNTRY_TAXRESIDENCY2) {
			this.jh2_COUNTRY_TAXRESIDENCY2 = jh2_COUNTRY_TAXRESIDENCY2;
		}

		public String getJh2_TAX_PAYER_ID_NO2() {
			return jh2_TAX_PAYER_ID_NO2;
		}

		public void setJh2_TAX_PAYER_ID_NO2(String jh2_TAX_PAYER_ID_NO2) {
			this.jh2_TAX_PAYER_ID_NO2 = jh2_TAX_PAYER_ID_NO2;
		}

		public String getJh2_COUNTRY_TAXRESIDENCY3() {
			return jh2_COUNTRY_TAXRESIDENCY3;
		}

		public void setJh2_COUNTRY_TAXRESIDENCY3(String jh2_COUNTRY_TAXRESIDENCY3) {
			this.jh2_COUNTRY_TAXRESIDENCY3 = jh2_COUNTRY_TAXRESIDENCY3;
		}

		public String getJh2_TAX_PAYER_ID_NO3() {
			return jh2_TAX_PAYER_ID_NO3;
		}

		public void setJh2_TAX_PAYER_ID_NO3(String jh2_TAX_PAYER_ID_NO3) {
			this.jh2_TAX_PAYER_ID_NO3 = jh2_TAX_PAYER_ID_NO3;
		}

		public String getBank_Name() {
			return bank_Name;
		}

		public void setBank_Name(String bank_Name) {
			this.bank_Name = bank_Name;
		}

		public String getBranch_Name() {
			return branch_Name;
		}

		public void setBranch_Name(String branch_Name) {
			this.branch_Name = branch_Name;
		}

		public String getBranch_Address() {
			return branch_Address;
		}

		public void setBranch_Address(String branch_Address) {
			this.branch_Address = branch_Address;
		}

		public String getBranch_Pin_Code() {
			return branch_Pin_Code;
		}

		public void setBranch_Pin_Code(String branch_Pin_Code) {
			this.branch_Pin_Code = branch_Pin_Code;
		}

		public String getBranch_City() {
			return branch_City;
		}

		public void setBranch_City(String branch_City) {
			this.branch_City = branch_City;
		}

		public String getAccount_Type() {
			return account_Type;
		}

		public void setAccount_Type(String account_Type) {
			this.account_Type = account_Type;
		}

		public String getAccount_No() {
			return account_No;
		}

		public void setAccount_No(String account_No) {
			this.account_No = account_No;
		}

		public String getIfsc_Code() {
			return ifsc_Code;
		}

		public void setIfsc_Code(String ifsc_Code) {
			this.ifsc_Code = ifsc_Code;
		}

		public String getNominee_Name() {
			return nominee_Name;
		}

		public void setNominee_Name(String nominee_Name) {
			this.nominee_Name = nominee_Name;
		}

		public String getNominee_dob() {
			return nominee_dob;
		}

		public void setNominee_dob(String nominee_dob) {
			this.nominee_dob = nominee_dob;
		}

		public String getNominee_Relationship() {
			return nominee_Relationship;
		}

		public void setNominee_Relationship(String nominee_Relationship) {
			this.nominee_Relationship = nominee_Relationship;
		}

		public String getSource() {
			return source;
		}

		public void setSource(String source) {
			this.source = source;
		}

		public String getEkyc() {
			return ekyc;
		}

		public void setEkyc(String ekyc) {
			this.ekyc = ekyc;
		}

		public String getPlaceOfBirth() {
			return placeOfBirth;
		}

		public void setPlaceOfBirth(String placeOfBirth) {
			this.placeOfBirth = placeOfBirth;
		}

		public String getJh1_PlaceOfBirth() {
			return jh1_PlaceOfBirth;
		}

		public void setJh1_PlaceOfBirth(String jh1_PlaceOfBirth) {
			this.jh1_PlaceOfBirth = jh1_PlaceOfBirth;
		}

		public String getJh2_PlaceOfBirth() {
			return jh2_PlaceOfBirth;
		}

		public void setJh2_PlaceOfBirth(String jh2_PlaceOfBirth) {
			this.jh2_PlaceOfBirth = jh2_PlaceOfBirth;
		}

		public String getAadhaarNo() {
			return aadhaarNo;
		}

		public void setAadhaarNo(String aadhaarNo) {
			this.aadhaarNo = aadhaarNo;
		}

		public String getJh1_BCNT_Flag() {
			return jh1_BCNT_Flag;
		}

		public void setJh1_BCNT_Flag(String jh1_BCNT_Flag) {
			this.jh1_BCNT_Flag = jh1_BCNT_Flag;
		}

		public String getJh2_BCNT_Flag() {
			return jh2_BCNT_Flag;
		}

		public void setJh2_BCNT_Flag(String jh2_BCNT_Flag) {
			this.jh2_BCNT_Flag = jh2_BCNT_Flag;
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

		public String getCkycNo() {
			return ckycNo;
		}

		public void setCkycNo(String ckycNo) {
			this.ckycNo = ckycNo;
		}

		public String getApplicantPrefix() {
			return applicantPrefix;
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

		public String getAddressType() {
			return addressType;
		}

		public void setAddressType(String addressType) {
			this.addressType = addressType;
		}

	}
}
