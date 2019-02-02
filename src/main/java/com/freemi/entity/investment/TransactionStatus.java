package com.freemi.entity.investment;

import org.springframework.stereotype.Component;

@Component
public class TransactionStatus {
	
	private String successFlag;
	
	private String statusMsg;
	
	private String inputInfo1;

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
	
	
	

}
