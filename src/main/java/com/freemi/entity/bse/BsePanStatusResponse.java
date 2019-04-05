package com.freemi.entity.bse;

public class BsePanStatusResponse {
	
	private String responseCode;
	private String panNumber;
	private String mfdStatus;
	private String rfdStatus;
	private String mfiStatus;
	private String rfiStatus;
	private String bseRemarks;
	private String others1;
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getPanNumber() {
		return panNumber;
	}
	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}
	public String getMfdStatus() {
		return mfdStatus;
	}
	public void setMfdStatus(String mfdStatus) {
		this.mfdStatus = mfdStatus;
	}
	public String getRfdStatus() {
		return rfdStatus;
	}
	public void setRfdStatus(String rfdStatus) {
		this.rfdStatus = rfdStatus;
	}
	public String getMfiStatus() {
		return mfiStatus;
	}
	public void setMfiStatus(String mfiStatus) {
		this.mfiStatus = mfiStatus;
	}
	public String getRfiStatus() {
		return rfiStatus;
	}
	public void setRfiStatus(String rfiStatus) {
		this.rfiStatus = rfiStatus;
	}
	public String getBseRemarks() {
		return bseRemarks;
	}
	public void setBseRemarks(String bseRemarks) {
		this.bseRemarks = bseRemarks;
	}
	public String getOthers1() {
		return others1;
	}
	public void setOthers1(String others1) {
		this.others1 = others1;
	}
	
	

}
