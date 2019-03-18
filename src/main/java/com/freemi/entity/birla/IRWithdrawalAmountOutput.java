package com.freemi.entity.birla;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IRWithdrawalAmountOutput implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("CAMSTransactionNo")
	private String camsTransactionNo = null;
	
	@JsonProperty("Cams_Response_Time")
	private String cams_Response_Time = null;
	
	@JsonProperty("EntryDate")
	private String entryDate;
	
	@JsonProperty("ReturnCode")
	private String returnCode;
	
	@JsonProperty("ReturnMsg")
	private String returnMsg;
	
	@JsonProperty("TradeDate")
	private String tradeDate;
	
	@JsonProperty("TransactionReferenceNo")
	private String transactionReferenceNo;
	
	@JsonProperty("CAMS_ReturnCode")
	private String cams_ReturnCode;
	
	@JsonProperty("CAMS_ReturnMsg")
	private String cams_ReturnMsg;
	
	@JsonProperty("IMPS_Status")
	private String imps_Status;
	
	@JsonProperty("IMPS_ReturnMsg")
	private String imps_ReturnMsg;
	
	@JsonProperty("UDP")
	private String udp = null;

	public String getCamsTransactionNo() {
		return camsTransactionNo;
	}

	public void setCamsTransactionNo(String camsTransactionNo) {
		this.camsTransactionNo = camsTransactionNo;
	}

	public String getCams_Response_Time() {
		return cams_Response_Time;
	}

	public void setCams_Response_Time(String cams_Response_Time) {
		this.cams_Response_Time = cams_Response_Time;
	}

	public String getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

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

	public String getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}

	public String getTransactionReferenceNo() {
		return transactionReferenceNo;
	}

	public void setTransactionReferenceNo(String transactionReferenceNo) {
		this.transactionReferenceNo = transactionReferenceNo;
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

	public String getImps_Status() {
		return imps_Status;
	}

	public void setImps_Status(String imps_Status) {
		this.imps_Status = imps_Status;
	}

	public String getImps_ReturnMsg() {
		return imps_ReturnMsg;
	}

	public void setImps_ReturnMsg(String imps_ReturnMsg) {
		this.imps_ReturnMsg = imps_ReturnMsg;
	}

	public String getUdp() {
		return udp;
	}

	public void setUdp(String udp) {
		this.udp = udp;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	


}
