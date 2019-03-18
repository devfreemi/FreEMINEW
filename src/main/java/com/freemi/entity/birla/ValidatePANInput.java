package com.freemi.entity.birla;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ValidatePANInput implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@JsonProperty("ValidatePAN")
	private ValidatePAN pandata;
	
	
	
	/*private Application application;
	
	
	public Application getApplication() {
		return application;
	}
	public void setApplication(Application application) {
		this.application = application;
	}
	public class Application {

		@JsonProperty("ValidatePAN")
		ValidatePAN validatePANObject;


		// Getter Methods 

		public ValidatePAN getValidatePAN() {
			return validatePANObject;
		}

		// Setter Methods 

		public void setValidatePAN( ValidatePAN ValidatePANObject ) {
			this.validatePANObject = ValidatePANObject;
		}
	}
	public static class ValidatePAN {*/
	
	public ValidatePAN getPandata() {
		return pandata;
	}



	public void setPandata(ValidatePAN pandata) {
		this.pandata = pandata;
	}



	public static class ValidatePAN{
		
		public ValidatePAN() {
			userId="";
			password="";
			panNo="";
			authCode="";
			mobile="";
			emailId="";
			dob="";
			userName="";
			udp="";
			udp1="";
			udp2="";
			udp3="";
			udp4="";
			udp5="";
			os="";
			imei="";
			clientIpAddress="";
			deviceId="";
			googleAdID="";
			
		}
		
		@JsonProperty("UserId")
		private String userId;
		
		@JsonProperty("Password")
		private String password;
		
		@JsonProperty("PANNo")
		private String panNo;
		
		@JsonProperty("AuthCode")
		private String authCode;
		
		@JsonProperty("Mobile")
		private String mobile;
		
		@JsonProperty("EmailId")
		private String emailId;
		
		@JsonProperty("DOB")
		private String dob;
		
		@JsonProperty("UserName")
		private String userName;
		
		@JsonProperty("UDP")
		private String udp;
		
		@JsonProperty("UDP1")
		private String udp1;
		
		@JsonProperty("UDP2")
		private String udp2;
		
		@JsonProperty("UDP3")
		private String udp3;
		
		@JsonProperty("UDP4")
		private String udp4;
		
		@JsonProperty("UDP5")
		private String udp5;
		
		@JsonProperty("OS")
		private String os;
		
		@JsonProperty("IMEI")
		private String imei;
		
		@JsonProperty("ClientIpAddress")
		private String clientIpAddress;
		
		@JsonProperty("DeviceId")
		private String deviceId;
		
		@JsonProperty("GoogleAdID")
		private String googleAdID;

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

		public String getPanNo() {
			return panNo;
		}

		public void setPanNo(String panNo) {
			this.panNo = panNo;
		}

		public String getAuthCode() {
			return authCode;
		}

		public void setAuthCode(String authCode) {
			this.authCode = authCode;
		}

		public String getMobile() {
			return mobile;
		}

		public void setMobile(String mobile) {
			this.mobile = mobile;
		}

		public String getEmailId() {
			return emailId;
		}

		public void setEmailId(String emailId) {
			this.emailId = emailId;
		}

		public String getDob() {
			return dob;
		}

		public void setDob(String dob) {
			this.dob = dob;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getUdp() {
			return udp;
		}

		public void setUdp(String udp) {
			this.udp = udp;
		}

		public String getUdp1() {
			return udp1;
		}

		public void setUdp1(String udp1) {
			this.udp1 = udp1;
		}

		public String getUdp2() {
			return udp2;
		}

		public void setUdp2(String udp2) {
			this.udp2 = udp2;
		}

		public String getUdp3() {
			return udp3;
		}

		public void setUdp3(String udp3) {
			this.udp3 = udp3;
		}

		public String getUdp4() {
			return udp4;
		}

		public void setUdp4(String udp4) {
			this.udp4 = udp4;
		}

		public String getUdp5() {
			return udp5;
		}

		public void setUdp5(String udp5) {
			this.udp5 = udp5;
		}

		public String getOs() {
			return os;
		}

		public void setOs(String os) {
			this.os = os;
		}

		public String getImei() {
			return imei;
		}

		public void setImei(String imei) {
			this.imei = imei;
		}

		public String getClientIpAddress() {
			return clientIpAddress;
		}

		public void setClientIpAddress(String clientIpAddress) {
			this.clientIpAddress = clientIpAddress;
		}

		public String getDeviceId() {
			return deviceId;
		}

		public void setDeviceId(String deviceId) {
			this.deviceId = deviceId;
		}

		public String getGoogleAdID() {
			return googleAdID;
		}

		public void setGoogleAdID(String googleAdID) {
			this.googleAdID = googleAdID;
		}
		
	}	

//	}
}
