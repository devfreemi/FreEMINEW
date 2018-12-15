package com.freemi.entity.Birla;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetSIPBanksInput {

	@JsonProperty("Bank")
	private Bank bankObject;
	
	public Bank getBankObject() {
		return bankObject;
	}
	public void setBankObject(Bank bankObject) {
		this.bankObject = bankObject;
	}

	public static class Bank {
		@JsonProperty("UserId")
		private String userId;
		
		@JsonProperty("Password")
		private String password;
		
		@JsonProperty("UDP")
		private String udp = "";
		
		@JsonProperty("ClientIpAddress")
		private String clientIpAddress = "";
		
		@JsonProperty("OS")
		private String os = "";
		
		@JsonProperty("IMEI")
		private String imei = "";
		
		@JsonProperty("UDP1")
		private String udp1 = "";
		
		@JsonProperty("UDP2")
		private String udp2 = "";
		
		@JsonProperty("UDP3")
		private String udp3 = "";
		
		@JsonProperty("UDP4")
		private String udp4 = "";
		
		@JsonProperty("UDP5")
		private String udp5 = "";


	}
}
