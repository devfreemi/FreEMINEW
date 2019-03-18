package com.freemi.entity.birla;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetFPurchaseBranchInput {

	@JsonProperty("Branch")
	private Branch branchObject;
	

	public Branch getBranchObject() {
		return branchObject;
	}

	public void setBranchObject(Branch branchObject) {
		this.branchObject = branchObject;
	}

	public static class Branch {

		@JsonProperty("UserId")
		private String userId;
		
		@JsonProperty("Password")
		private String password;
		
		@JsonProperty("BankId")
		private String bankId;
		
		@JsonProperty("BranchCity")
		private String branchCity;
		
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

		public String getBankId() {
			return bankId;
		}

		public void setBankId(String bankId) {
			this.bankId = bankId;
		}

		public String getBranchCity() {
			return branchCity;
		}

		public void setBranchCity(String branchCity) {
			this.branchCity = branchCity;
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
