package com.freemi.entity.bse;

import java.io.Serializable;

public class BseSipOrderEntry implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 private String transactionCode;
	 private String uniqueRefNo;
	 private String schemeCode;
	 private String memberCode;
	 private String clientCode;
	 private String userID;
	 private String transMode;
	 private String startDate;
	 private String frequencyType;
	 private String frequencyAllowed;
	 private String installmentAmount;
	 private String noOfInstallments;
	 private String folioNo="";
	 private String firstOrderFlag;
	 private String euin;
	 private String euinVal;
	 private String dpc;
	 private String password;
	public String getTransactionCode() {
		return transactionCode;
	}
	public void setTransactionCode(String transactionCode) {
		this.transactionCode = transactionCode;
	}
	public String getUniqueRefNo() {
		return uniqueRefNo;
	}
	public void setUniqueRefNo(String uniqueRefNo) {
		this.uniqueRefNo = uniqueRefNo;
	}
	public String getSchemeCode() {
		return schemeCode;
	}
	public void setSchemeCode(String schemeCode) {
		this.schemeCode = schemeCode;
	}
	public String getMemberCode() {
		return memberCode;
	}
	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}
	public String getClientCode() {
		return clientCode;
	}
	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getTransMode() {
		return transMode;
	}
	public void setTransMode(String transMode) {
		this.transMode = transMode;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getFrequencyType() {
		return frequencyType;
	}
	public void setFrequencyType(String frequencyType) {
		this.frequencyType = frequencyType;
	}
	public String getFrequencyAllowed() {
		return frequencyAllowed;
	}
	public void setFrequencyAllowed(String frequencyAllowed) {
		this.frequencyAllowed = frequencyAllowed;
	}
	public String getInstallmentAmount() {
		return installmentAmount;
	}
	public void setInstallmentAmount(String installmentAmount) {
		this.installmentAmount = installmentAmount;
	}
	public String getNoOfInstallments() {
		return noOfInstallments;
	}
	public void setNoOfInstallments(String noOfInstallments) {
		this.noOfInstallments = noOfInstallments;
	}
	public String getFolioNo() {
		return folioNo;
	}
	public void setFolioNo(String folioNo) {
		this.folioNo = folioNo;
	}
	public String getFirstOrderFlag() {
		return firstOrderFlag;
	}
	public void setFirstOrderFlag(String firstOrderFlag) {
		this.firstOrderFlag = firstOrderFlag;
	}
	public String getEuin() {
		return euin;
	}
	public void setEuin(String euin) {
		this.euin = euin;
	}
	public String getEuinVal() {
		return euinVal;
	}
	public void setEuinVal(String euinVal) {
		this.euinVal = euinVal;
	}
	public String getDpc() {
		return dpc;
	}
	public void setDpc(String dpc) {
		this.dpc = dpc;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	 
	 

}
