package com.freemi.entity.birla;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SavePrePurchaseTransRequestOutput {

	@JsonProperty("ReturnCode")
	private String returnCode;
	
	@JsonProperty("ReturnMsg")
	private String returnMsg;
	
	@JsonProperty("TransNo")
	private String transNo;
	
	@JsonProperty("TransReferenceNo")
	private String TransReferenceNo;
	
	@JsonProperty("UDP")
	private String udp;

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

	public String getTransNo() {
		return transNo;
	}

	public void setTransNo(String transNo) {
		this.transNo = transNo;
	}

	public String getTransReferenceNo() {
		return TransReferenceNo;
	}

	public void setTransReferenceNo(String transReferenceNo) {
		TransReferenceNo = transReferenceNo;
	}

	public String getUdp() {
		return udp;
	}

	public void setUdp(String udp) {
		this.udp = udp;
	}
	
	
	
}
