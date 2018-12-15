package com.freemi.entity.Birla;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ValidateAadhaarOTPOutput {

//	public class Application {

		@JsonProperty("Flag")
		private boolean vlag;
		
		@JsonProperty("Message")
		private String message;
		
		@JsonProperty("ReturnCode")
		private String returnCode;
		
		@JsonProperty("ReturnMsg")
		private String returnMsg;
		
		@JsonProperty("UDP")
		private String udp = null;
		
		@JsonProperty("EKYCAdharDetails")
		EKYCAdharDetails ekycAdharDetailsObject;

		public boolean isVlag() {
			return vlag;
		}

		public void setVlag(boolean vlag) {
			this.vlag = vlag;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public String getReturnCode() {
			return returnCode;
		}

		public void setReturnCode(String returnCode) {
			this.returnCode = returnCode;
		}

		public String getReturnMsg() {
			return returnMsg;
		}

		public void setReturnMsg(String returnMsg) {
			this.returnMsg = returnMsg;
		}

		public String getUdp() {
			return udp;
		}

		public void setUdp(String udp) {
			this.udp = udp;
		}

		public EKYCAdharDetails getEkycAdharDetailsObject() {
			return ekycAdharDetailsObject;
		}

		public void setEkycAdharDetailsObject(EKYCAdharDetails ekycAdharDetailsObject) {
			this.ekycAdharDetailsObject = ekycAdharDetailsObject;
		}
		
//	}
	
	
	public class EKYCAdharDetails {
		
		@JsonProperty("AadhaarPrint")
		private String aadhaarPrint;
		
		@JsonProperty("AdharNo")
		private String adharNo;
		
		@JsonProperty("CareOfPerson")
		private String careOfPerson;
		
		@JsonProperty("City")
		private String city;
		
		@JsonProperty("DOB")
		private String dob;
		
		@JsonProperty("District")
		private String district;
		
		@JsonProperty("Email")
		private String email;
		
		@JsonProperty("Gender")
		private String gender;
		
		@JsonProperty("House")
		private String house;
		
		@JsonProperty("Landmark")
		private String landmark;
		
		@JsonProperty("Locality")
		private String locality;
		
		@JsonProperty("Name")
		private String name;
		
		@JsonProperty("Phone")
		private String phone;
		
		@JsonProperty("Photo")
		private String photo;
		
		@JsonProperty("PinCode")
		private String pinCode;
		
		@JsonProperty("PostOfficeName")
		private String postOfficeName;
		
		@JsonProperty("State")
		private String state;
		
		@JsonProperty("Street")
		private String street;
		
		@JsonProperty("SubDistrict")
		private String subDistrict;
		
		@JsonProperty("TransactionCode")
		private String transactionCode;
		
		@JsonProperty("UserDetailId")
		private String userDetailId = null;
		
		@JsonProperty("RefNo")
		private String refNo;
		
		@JsonProperty("Source")
		private String source;
		
		@JsonProperty("strOpr")
		private String strOpr;
		
		
		public String getAadhaarPrint() {
			return aadhaarPrint;
		}

		public void setAadhaarPrint(String aadhaarPrint) {
			this.aadhaarPrint = aadhaarPrint;
		}

		public String getAdharNo() {
			return adharNo;
		}

		public void setAdharNo(String adharNo) {
			this.adharNo = adharNo;
		}

		public String getCareOfPerson() {
			return careOfPerson;
		}

		public void setCareOfPerson(String careOfPerson) {
			this.careOfPerson = careOfPerson;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getDob() {
			return dob;
		}

		public void setDob(String dob) {
			this.dob = dob;
		}

		public String getDistrict() {
			return district;
		}

		public void setDistrict(String district) {
			this.district = district;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public String getHouse() {
			return house;
		}

		public void setHouse(String house) {
			this.house = house;
		}

		public String getLandmark() {
			return landmark;
		}

		public void setLandmark(String landmark) {
			this.landmark = landmark;
		}

		public String getLocality() {
			return locality;
		}

		public void setLocality(String locality) {
			this.locality = locality;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getPhoto() {
			return photo;
		}

		public void setPhoto(String photo) {
			this.photo = photo;
		}

		public String getPinCode() {
			return pinCode;
		}

		public void setPinCode(String pinCode) {
			this.pinCode = pinCode;
		}

		public String getPostOfficeName() {
			return postOfficeName;
		}

		public void setPostOfficeName(String postOfficeName) {
			this.postOfficeName = postOfficeName;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public String getStreet() {
			return street;
		}

		public void setStreet(String street) {
			this.street = street;
		}

		public String getSubDistrict() {
			return subDistrict;
		}

		public void setSubDistrict(String subDistrict) {
			this.subDistrict = subDistrict;
		}

		public String getTransactionCode() {
			return transactionCode;
		}

		public void setTransactionCode(String transactionCode) {
			this.transactionCode = transactionCode;
		}

		public String getUserDetailId() {
			return userDetailId;
		}

		public void setUserDetailId(String userDetailId) {
			this.userDetailId = userDetailId;
		}

		public String getRefNo() {
			return refNo;
		}

		public void setRefNo(String refNo) {
			this.refNo = refNo;
		}

		public String getSource() {
			return source;
		}

		public void setSource(String source) {
			this.source = source;
		}

		public String getStrOpr() {
			return strOpr;
		}

		public void setStrOpr(String strOpr) {
			this.strOpr = strOpr;
		}
		
		
	}

}
