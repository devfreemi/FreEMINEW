package com.freemi.entity.birla;

import java.io.Serializable;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetIRDetailsForFolioOutput implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("BankDetails")
	ArrayList<BankDetails> bankDetails = new ArrayList<BankDetails>();
	
	@JsonProperty("FolioNo")
	private String folioNo;
	
	@JsonProperty("IRHeaderId")
	private String irHeaderId;
	
	@JsonProperty("ReturnCode")
	private String returnCode;
	
	@JsonProperty("ReturnMsg")
	private String returnMsg;
	
	@JsonProperty("IRPerDayAmount")
	private float irPerDayAmount;
	
	@JsonProperty("SchemeDetails")
	ArrayList<SchemeDetails> schemeDetails = new ArrayList<SchemeDetails>();
	
	@JsonProperty("UDP")
	private String udp;
	
	

	public ArrayList<BankDetails> getBankDetails() {
		return bankDetails;
	}

	public void setBankDetails(ArrayList<BankDetails> bankDetails) {
		this.bankDetails = bankDetails;
	}

	public String getFolioNo() {
		return folioNo;
	}

	public void setFolioNo(String folioNo) {
		this.folioNo = folioNo;
	}

	public String getIrHeaderId() {
		return irHeaderId;
	}

	public void setIrHeaderId(String irHeaderId) {
		this.irHeaderId = irHeaderId;
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

	public float getIrPerDayAmount() {
		return irPerDayAmount;
	}

	public void setIrPerDayAmount(float irPerDayAmount) {
		this.irPerDayAmount = irPerDayAmount;
	}

	public ArrayList<SchemeDetails> getSchemeDetails() {
		return schemeDetails;
	}

	public void setSchemeDetails(ArrayList<SchemeDetails> schemeDetails) {
		this.schemeDetails = schemeDetails;
	}

	public String getUdp() {
		return udp;
	}

	public void setUdp(String udp) {
		this.udp = udp;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static class BankDetails{
		
		@JsonProperty("AccountNumber")
		private String accountNumber;
		
		@JsonProperty("AccountType")
		private String accountType;
		
		@JsonProperty("BankEligible")
		private String bankEligible;
		
		@JsonProperty("BankInfo")
		private String bankInfo;
		
		@JsonProperty("BankName")
		private String bankName;
		
		@JsonProperty("BranchCity")
		private String branchCity;
		
		@JsonProperty("BranchName")
		private String branchName;
		
		@JsonProperty("IFSCCode")
		private String ifscCode;
		
		@JsonProperty("PayoutID")
		private String payoutID;

		public String getAccountNumber() {
			return accountNumber;
		}

		public void setAccountNumber(String accountNumber) {
			this.accountNumber = accountNumber;
		}

		public String getAccountType() {
			return accountType;
		}

		public void setAccountType(String accountType) {
			this.accountType = accountType;
		}

		public String getBankEligible() {
			return bankEligible;
		}

		public void setBankEligible(String bankEligible) {
			this.bankEligible = bankEligible;
		}

		public String getBankInfo() {
			return bankInfo;
		}

		public void setBankInfo(String bankInfo) {
			this.bankInfo = bankInfo;
		}

		public String getBankName() {
			return bankName;
		}

		public void setBankName(String bankName) {
			this.bankName = bankName;
		}

		public String getBranchCity() {
			return branchCity;
		}

		public void setBranchCity(String branchCity) {
			this.branchCity = branchCity;
		}

		public String getBranchName() {
			return branchName;
		}

		public void setBranchName(String branchName) {
			this.branchName = branchName;
		}

		public String getIfscCode() {
			return ifscCode;
		}

		public void setIfscCode(String ifscCode) {
			this.ifscCode = ifscCode;
		}

		public String getPayoutID() {
			return payoutID;
		}

		public void setPayoutID(String payoutID) {
			this.payoutID = payoutID;
		}

		
		

	}

	public static class SchemeDetails{

		@JsonProperty("ClosingBalance")
		private String closingBalance;
		
		@JsonProperty("DefaultBankAccountNo")
		private String defaultBankAccountNo;
		
		@JsonProperty("DefaultBankAvailable")
		private String defaultBankAvailable;
		
		@JsonProperty("MarketValue")
		private String marketValue;
		
		@JsonProperty("Max_Amount")
		private String max_Amount;
		
		@JsonProperty("SchemeCode")
		private String schemeCode;
		
		@JsonProperty("SchemeName")
		private String schemeName;
		
		@JsonProperty("TaxStatus")
		private String taxStatus;

		public String getClosingBalance() {
			return closingBalance;
		}

		public void setClosingBalance(String closingBalance) {
			this.closingBalance = closingBalance;
		}

		public String getDefaultBankAccountNo() {
			return defaultBankAccountNo;
		}

		public void setDefaultBankAccountNo(String defaultBankAccountNo) {
			this.defaultBankAccountNo = defaultBankAccountNo;
		}

		public String getDefaultBankAvailable() {
			return defaultBankAvailable;
		}

		public void setDefaultBankAvailable(String defaultBankAvailable) {
			this.defaultBankAvailable = defaultBankAvailable;
		}

		public String getMarketValue() {
			return marketValue;
		}

		public void setMarketValue(String marketValue) {
			this.marketValue = marketValue;
		}

		public String getMax_Amount() {
			return max_Amount;
		}

		public void setMax_Amount(String max_Amount) {
			this.max_Amount = max_Amount;
		}

		public String getSchemeCode() {
			return schemeCode;
		}

		public void setSchemeCode(String schemeCode) {
			this.schemeCode = schemeCode;
		}

		public String getSchemeName() {
			return schemeName;
		}

		public void setSchemeName(String schemeName) {
			this.schemeName = schemeName;
		}

		public String getTaxStatus() {
			return taxStatus;
		}

		public void setTaxStatus(String taxStatus) {
			this.taxStatus = taxStatus;
		}

		
		
		
	}

}
