package com.freemi.entity.birla;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SavePostPurchaseTransactionOutput {

	@JsonProperty("ReturnCode")
	private String returnCode;
	
	@JsonProperty("ReturnMsg")
	private String returnMsg;
	
	@JsonProperty("UDP")
	private String udp = null;
	
	@JsonProperty("CamsStatus")
	private String camsStatus;
	
	@JsonProperty("CamsTrxn_No")
	private String camsTrxn_No;
	
	@JsonProperty("Cams_ReturnCode")
	private String cams_ReturnCode;
	
	@JsonProperty("Cams_ReturnMsg")
	private String cams_ReturnMsg;
	
	@JsonProperty("CAMSTransDate")
	private String camsTransDate = null;
	
	@JsonProperty("CAMSEntryDate")
	private String camsEntryDate = null;

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

	public String getUdp() {
		return udp;
	}

	public void setUdp(String udp) {
		this.udp = udp;
	}

	public String getCamsStatus() {
		return camsStatus;
	}

	public void setCamsStatus(String camsStatus) {
		this.camsStatus = camsStatus;
	}

	public String getCamsTrxn_No() {
		return camsTrxn_No;
	}

	public void setCamsTrxn_No(String camsTrxn_No) {
		this.camsTrxn_No = camsTrxn_No;
	}

	public String getCams_ReturnCode() {
		return cams_ReturnCode;
	}

	public void setCams_ReturnCode(String cams_ReturnCode) {
		this.cams_ReturnCode = cams_ReturnCode;
	}

	public String getCams_ReturnMsg() {
		return cams_ReturnMsg;
	}

	public void setCams_ReturnMsg(String cams_ReturnMsg) {
		this.cams_ReturnMsg = cams_ReturnMsg;
	}

	public String getCamsTransDate() {
		return camsTransDate;
	}

	public void setCamsTransDate(String camsTransDate) {
		this.camsTransDate = camsTransDate;
	}

	public String getCamsEntryDate() {
		return camsEntryDate;
	}

	public void setCamsEntryDate(String camsEntryDate) {
		this.camsEntryDate = camsEntryDate;
	}
	
	
}
