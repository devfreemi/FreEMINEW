package com.freemi.entity.investment;

import org.springframework.stereotype.Component;

@Component
public class TransactionStatus {
	
//	This class is to accumulate to display all BSE related transaction status
	
	private String successFlag="";
	
	private String statusMsg="";
	
	private String inputInfo1="";
	
	private String fundName="";
	
	private String investmentType="";
	
	private String transactionReference="";
	
	private String bseOrderNoFromResponse="";
	
	private boolean isEmandateRequired=false;
	
	private String emandateStatusCode="";
	private String emandateRegisterRemark="";
	
	private String sipStartDate="";
	
	private String sipInstallements="";
	
	private String investFrequency="";

	public String getSuccessFlag() {
		return successFlag;
	}

	public void setSuccessFlag(String successFlag) {
		this.successFlag = successFlag;
	}

	public String getStatusMsg() {
		return statusMsg;
	}

	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}

	public String getInputInfo1() {
		return inputInfo1;
	}

	public void setInputInfo1(String inputInfo1) {
		this.inputInfo1 = inputInfo1;
	}

	public String getFundName() {
		return fundName;
	}

	public void setFundName(String fundName) {
		this.fundName = fundName;
	}

	public String getInvestmentType() {
		return investmentType;
	}

	public void setInvestmentType(String investmentType) {
		this.investmentType = investmentType;
	}

	public String getTransactionReference() {
		return transactionReference;
	}

	public void setTransactionReference(String transactionReference) {
		this.transactionReference = transactionReference;
	}

	public String getBseOrderNoFromResponse() {
		return bseOrderNoFromResponse;
	}

	public void setBseOrderNoFromResponse(String bseOrderNoFromResponse) {
		this.bseOrderNoFromResponse = bseOrderNoFromResponse;
	}

	public boolean isEmandateRequired() {
		return isEmandateRequired;
	}

	public void setEmandateRequired(boolean isEmandateRequired) {
		this.isEmandateRequired = isEmandateRequired;
	}

	public String getEmandateStatusCode() {
		return emandateStatusCode;
	}

	public void setEmandateStatusCode(String emandateStatusCode) {
		this.emandateStatusCode = emandateStatusCode;
	}

	public String getSipStartDate() {
		return sipStartDate;
	}

	public void setSipStartDate(String sipStartDate) {
		this.sipStartDate = sipStartDate;
	}

	public String getSipInstallements() {
		return sipInstallements;
	}

	public void setSipInstallements(String sipInstallements) {
		this.sipInstallements = sipInstallements;
	}

	public String getInvestFrequency() {
		return investFrequency;
	}

	public void setInvestFrequency(String investFrequency) {
		this.investFrequency = investFrequency;
	}

	public String getEmandateRegisterRemark() {
		return emandateRegisterRemark;
	}

	public void setEmandateRegisterRemark(String emandateRegisterRemark) {
		this.emandateRegisterRemark = emandateRegisterRemark;
	}
	

}
