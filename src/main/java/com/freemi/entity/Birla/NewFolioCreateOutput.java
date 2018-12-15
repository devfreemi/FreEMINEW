package com.freemi.entity.Birla;

import com.fasterxml.jackson.annotation.JsonProperty;


public class NewFolioCreateOutput {
	
	@JsonProperty("NewFolioCreationResult")
	private NewFolioCreationResult newFolioCreationResult;
	
	public NewFolioCreationResult getNewFolioCreationResult() {
		return newFolioCreationResult;
	}

	public void setNewFolioCreationResult(NewFolioCreationResult newFolioCreationResult) {
		this.newFolioCreationResult = newFolioCreationResult;
	}


	public static class NewFolioCreationResult{
	
	@JsonProperty("AdharSeedlinkRef_Id")
	private String adharSeedlinkRef_Id;
	
	@JsonProperty("PEKRN")
	private String pekrn;
	

	@JsonProperty("ReturnCode")
	private String returnCode;

	@JsonProperty("ReturnMsg")
	private String returnMsg;

	@JsonProperty("FolioNo")
	private String folioNo;

	@JsonProperty("RefNo")
	private String refNo;

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
	public String getFolioNo() {
		return folioNo;
	}
	public void setFolioNo(String folioNo) {
		this.folioNo = folioNo;
	}
	public String getRefNo() {
		return refNo;
	}
	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}
	public String getUdp() {
		return udp;
	}
	public void setUdp(String udp) {
		this.udp = udp;
	}
	public String getAdharSeedlinkRef_Id() {
		return adharSeedlinkRef_Id;
	}
	public void setAdharSeedlinkRef_Id(String adharSeedlinkRef_Id) {
		this.adharSeedlinkRef_Id = adharSeedlinkRef_Id;
	}
	public String getPekrn() {
		return pekrn;
	}
	public void setPekrn(String pekrn) {
		this.pekrn = pekrn;
	}
	
	
	}


}
