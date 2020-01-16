package com.freemi.entity.investment.mahindra;

public class MahindraApiResponse {
	
	private String status="NOT_SET";
	private String requestJson;
	private String outputMap;
	private String outputData;
	private String responsemsg;
	
	private String mfRefNo;
	private String applicationNo;
	private String paymentUrl;
	private String paymentVerifyResponse;
	
	private String paymentStatusCode;
	
	private String acknowledgementdoc;
	
	private String micrCode;
	private String bankBranch;
	private String bankName;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRequestJson() {
		return requestJson;
	}
	public void setRequestJson(String requestJson) {
		this.requestJson = requestJson;
	}
	public String getOutputMap() {
		return outputMap;
	}
	public void setOutputMap(String outputMap) {
		this.outputMap = outputMap;
	}
	public String getOutputData() {
		return outputData;
	}
	public void setOutputData(String outputData) {
		this.outputData = outputData;
	}
	
	public String getResponsemsg() {
	    return responsemsg;
	}
	public void setResponsemsg(String responsemsg) {
	    this.responsemsg = responsemsg;
	}
	public String getMfRefNo() {
		return mfRefNo;
	}
	public void setMfRefNo(String mfRefNo) {
		this.mfRefNo = mfRefNo;
	}
	public String getApplicationNo() {
		return applicationNo;
	}
	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}
	public String getPaymentUrl() {
		return paymentUrl;
	}
	public void setPaymentUrl(String paymentUrl) {
		this.paymentUrl = paymentUrl;
	}
	public String getPaymentVerifyResponse() {
		return paymentVerifyResponse;
	}
	public void setPaymentVerifyResponse(String paymentVerifyResponse) {
		this.paymentVerifyResponse = paymentVerifyResponse;
	}
	public String getMicrCode() {
		return micrCode;
	}
	public void setMicrCode(String micrCode) {
		this.micrCode = micrCode;
	}
	public String getBankBranch() {
		return bankBranch;
	}
	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getPaymentStatusCode() {
		return paymentStatusCode;
	}
	public void setPaymentStatusCode(String paymentStatusCode) {
		this.paymentStatusCode = paymentStatusCode;
	}
	public String getAcknowledgementdoc() {
		return acknowledgementdoc;
	}
	public void setAcknowledgementdoc(String acknowledgementdoc) {
		this.acknowledgementdoc = acknowledgementdoc;
	}
	
}
