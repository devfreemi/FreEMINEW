package com.freemi.entity.birla;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetFPurchaseBanksCityInput {

	@JsonProperty("BranchCity")
	private BranchCity branchCity;


	public BranchCity getBranchCity() {
		return branchCity;
	}

	public void setBranchCity(BranchCity branchCity) {
		this.branchCity = branchCity;
	}

	public static class BranchCity{
		@JsonProperty("UserId")
		private String userId;

		@JsonProperty("Password")
		private String password;

		@JsonProperty("BankId")
		private String bankId;

		@JsonProperty("UDP")
		private String udp = "";

		@JsonProperty("ClientIpAddress")
		private String clientIpAddress = "";

		@JsonProperty("OS")
		private String os = "";

		@JsonProperty("IMEI")
		private String imei = "";

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

		public String getBankId() {
			return bankId;
		}

		public void setBankId(String bankId) {
			this.bankId = bankId;
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


	}
}
