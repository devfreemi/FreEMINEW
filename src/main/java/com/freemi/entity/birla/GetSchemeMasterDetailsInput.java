package com.freemi.entity.birla;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetSchemeMasterDetailsInput {
	
	@JsonProperty("Request")
	private Request requestObject;
	
	public Request getRequestObject() {
		return requestObject;
	}

	public void setRequestObject(Request requestObject) {
		this.requestObject = requestObject;
	}

	public static class Request {
		
		@JsonProperty("UserId")
		private String userId;
		
		@JsonProperty("Password")
		private String password;
		
		@JsonProperty("TransType")
		private String transType;
		
		@JsonProperty("UDP")
		private String udp;
		
		@JsonProperty("ClientIpAddress")
		private String clientIpAddress;
		
		@JsonProperty("OS")
		private String os;
		
		@JsonProperty("IMEI")
		private String imei;
		
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
		
		@JsonProperty("GoogleAdID")
		private String googleAdID;
		
		@JsonProperty("DeviceId")
		private String deviceId;
		
		@JsonProperty("AuthCode")
		private String authCode="";

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

		public String getTransType() {
			return transType;
		}

		public void setTransType(String transType) {
			this.transType = transType;
		}

		public String getUdp() {
			return udp;
		}

		public void setUdp(String udp) {
			this.udp = udp;
		}

		public String getClientIpAddress() {
			return clientIpAddress;
		}

		public void setClientIpAddress(String clientIpAddress) {
			this.clientIpAddress = clientIpAddress;
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

		public String getGoogleAdID() {
			return googleAdID;
		}

		public void setGoogleAdID(String googleAdID) {
			this.googleAdID = googleAdID;
		}

		public String getDeviceId() {
			return deviceId;
		}

		public void setDeviceId(String deviceId) {
			this.deviceId = deviceId;
		}

		public String getAuthCode() {
			return authCode;
		}

		public void setAuthCode(String authCode) {
			this.authCode = authCode;
		}
		
		

	}
}
