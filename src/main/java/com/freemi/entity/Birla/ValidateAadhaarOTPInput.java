package com.freemi.entity.Birla;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ValidateAadhaarOTPInput implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("ValidateAdharOTPRequest")
	ValidateAdharOTPRequest validateAdharOTPRequestObject;
	
	

	public ValidateAdharOTPRequest getValidateAdharOTPRequestObject() {
		return validateAdharOTPRequestObject;
	}



	public void setValidateAdharOTPRequestObject(ValidateAdharOTPRequest validateAdharOTPRequestObject) {
		this.validateAdharOTPRequestObject = validateAdharOTPRequestObject;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public static class ValidateAdharOTPRequest {
		
		@JsonProperty("UserId")
		private String userId;
		
		@JsonProperty("Password")
		private String password;
		
		@JsonProperty("AdharNo")
		private String adharNo;
		
		@JsonProperty("OTPCode")
		private String otpCode;
		
		@JsonProperty("UDP")
		private String udp;
		
		@JsonProperty("Source")
		private String source = null;
		
		@JsonProperty("RefID")
		private int refID;
		
		@JsonProperty("ClientIpAddress")
		private String clientIpAddress;
		
		@JsonProperty("IMEI")
		private String imei;
		
		@JsonProperty("OS")
		private String os;

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

		public String getAdharNo() {
			return adharNo;
		}

		public void setAdharNo(String adharNo) {
			this.adharNo = adharNo;
		}

		public String getOtpCode() {
			return otpCode;
		}

		public void setOtpCode(String otpCode) {
			this.otpCode = otpCode;
		}

		public String getUdp() {
			return udp;
		}

		public void setUdp(String udp) {
			this.udp = udp;
		}

		public String getSource() {
			return source;
		}

		public void setSource(String source) {
			this.source = source;
		}

		

		public int getRefID() {
			return refID;
		}

		public void setRefID(int refID) {
			this.refID = refID;
		}

		public String getClientIpAddress() {
			return clientIpAddress;
		}

		public void setClientIpAddress(String clientIpAddress) {
			this.clientIpAddress = clientIpAddress;
		}

		public String getImei() {
			return imei;
		}

		public void setImei(String imei) {
			this.imei = imei;
		}

		public String getOs() {
			return os;
		}

		public void setOs(String os) {
			this.os = os;
		}
		
		


	}

}
