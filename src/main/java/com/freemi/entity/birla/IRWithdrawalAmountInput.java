package com.freemi.entity.birla;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IRWithdrawalAmountInput implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("WithdrawalAmountRequestObject")
	private WithdrawalAmountRequest withdrawalAmountRequestObject;
	
	

	public WithdrawalAmountRequest getWithdrawalAmountRequestObject() {
		return withdrawalAmountRequestObject;
	}



	public void setWithdrawalAmountRequestObject(WithdrawalAmountRequest withdrawalAmountRequestObject) {
		this.withdrawalAmountRequestObject = withdrawalAmountRequestObject;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public static class WithdrawalAmountRequest {
		
		@JsonProperty("UserId")
		private String userId;
		
		@JsonProperty("Password")
		private String password;
		
		@JsonProperty("FolioNo")
		private String folioNo;
		
		@JsonProperty("SchemeCode")
		private String schemeCode;
		
		@JsonProperty("Amount")
		private String amount;
		
		@JsonProperty("Bank_Name")
		private String bank_Name;
		
		@JsonProperty("BranchName")
		private String branchName;
		
		@JsonProperty("BankCity")
		private String bankCity;
		
		@JsonProperty("AccNo")
		private String accNo;
		
		@JsonProperty("AccType")
		private String accType;
		
		@JsonProperty("IFSCcode")
		private String ifsccode;
		
		@JsonProperty("RedeemPayout")
		private String redeemPayout;
		
		@JsonProperty("IsInstantRedemption")
		private String isInstantRedemption;
		
		@JsonProperty("IRHeaderId")
		private String irHeaderId;
		
		@JsonProperty("IsIRMAXAmount")
		private String isIRMAXAmount;
		
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

		public String getFolioNo() {
			return folioNo;
		}

		public void setFolioNo(String folioNo) {
			this.folioNo = folioNo;
		}

		public String getSchemeCode() {
			return schemeCode;
		}

		public void setSchemeCode(String schemeCode) {
			this.schemeCode = schemeCode;
		}

		public String getAmount() {
			return amount;
		}

		public void setAmount(String amount) {
			this.amount = amount;
		}

		public String getBank_Name() {
			return bank_Name;
		}

		public void setBank_Name(String bank_Name) {
			this.bank_Name = bank_Name;
		}

		public String getBranchName() {
			return branchName;
		}

		public void setBranchName(String branchName) {
			this.branchName = branchName;
		}

		public String getBankCity() {
			return bankCity;
		}

		public void setBankCity(String bankCity) {
			this.bankCity = bankCity;
		}

		public String getAccNo() {
			return accNo;
		}

		public void setAccNo(String accNo) {
			this.accNo = accNo;
		}

		public String getAccType() {
			return accType;
		}

		public void setAccType(String accType) {
			this.accType = accType;
		}

		public String getIfsccode() {
			return ifsccode;
		}

		public void setIfsccode(String ifsccode) {
			this.ifsccode = ifsccode;
		}

		public String getRedeemPayout() {
			return redeemPayout;
		}

		public void setRedeemPayout(String redeemPayout) {
			this.redeemPayout = redeemPayout;
		}

		public String getIsInstantRedemption() {
			return isInstantRedemption;
		}

		public void setIsInstantRedemption(String isInstantRedemption) {
			this.isInstantRedemption = isInstantRedemption;
		}

		public String getIrHeaderId() {
			return irHeaderId;
		}

		public void setIrHeaderId(String irHeaderId) {
			this.irHeaderId = irHeaderId;
		}

		public String getIsIRMAXAmount() {
			return isIRMAXAmount;
		}

		public void setIsIRMAXAmount(String isIRMAXAmount) {
			this.isIRMAXAmount = isIRMAXAmount;
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
		
		

	}
}
