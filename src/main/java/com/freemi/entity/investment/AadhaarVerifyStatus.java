package com.freemi.entity.investment;

import java.util.Date;

public class AadhaarVerifyStatus {
	
	private Date timeStamp;
	private String aadhaarNUmber;
	private String responseCode;
	private String responseMessage;
	private String aadhaarReturnMessage;
	private String refId;
	
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	public String getAadhaarReturnMessage() {
		return aadhaarReturnMessage;
	}
	public void setAadhaarReturnMessage(String aadhaarReturnMessage) {
		this.aadhaarReturnMessage = aadhaarReturnMessage;
	}
	public String getRefId() {
		return refId;
	}
	public void setRefId(String refId) {
		this.refId = refId;
	}
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getAadhaarNUmber() {
		return aadhaarNUmber;
	}
	public void setAadhaarNUmber(String aadhaarNUmber) {
		this.aadhaarNUmber = aadhaarNUmber;
	}
	
	
	

}
