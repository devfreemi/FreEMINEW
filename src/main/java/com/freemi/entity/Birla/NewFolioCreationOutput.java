package com.freemi.entity.Birla;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NewFolioCreationOutput {

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
	
	
}
