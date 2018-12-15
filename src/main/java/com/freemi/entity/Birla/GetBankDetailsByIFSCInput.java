package com.freemi.entity.Birla;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetBankDetailsByIFSCInput {

	@JsonProperty("BankIFSC")
	private BankIFSC bankIFSCObject;
	
	

	public BankIFSC getBankIFSCObject() {
		return bankIFSCObject;
	}


	public void setBankIFSCObject(BankIFSC bankIFSCObject) {
		this.bankIFSCObject = bankIFSCObject;
	}


	public static class BankIFSC {
		
		@JsonProperty("UserId")
		private String userId;
		
		@JsonProperty("Password")
		private String password;
		
		@JsonProperty("IFSCCode")
		private String ifscCode;
		
		@JsonProperty("UDP")
		private String udp = null;
		
		@JsonProperty("ClientIpAddress")
		private String clientIpAddress = null;
		
		@JsonProperty("OS")
		private String os = null;
		
		@JsonProperty("IMEI")
		private String imei = null;
		
		@JsonProperty("UDP1")
		private String udp1 = null;
		
		@JsonProperty("UDP2")
		private String udp2 = null;
		
		@JsonProperty("UDP3")
		private String udp3 = null;
		
		@JsonProperty("UDP4")
		private String udp4 = null;
		
		@JsonProperty("UDP5")
		private String udp5 = null;

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

		public String getIfscCode() {
			return ifscCode;
		}

		public void setIfscCode(String ifscCode) {
			this.ifscCode = ifscCode;
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
		
		
		
	}

}
