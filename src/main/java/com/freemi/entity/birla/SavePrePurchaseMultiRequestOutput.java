package com.freemi.entity.birla;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SavePrePurchaseMultiRequestOutput {

	@JsonProperty("ReturnCode")
	private String returnCode;
	
	@JsonProperty("ReturnMsg")
	private String returnMsg;
	
	@JsonProperty("UDP")
	private String udp = null;
	
	@JsonProperty("TransNo")
	private String transNo;
	
	@JsonProperty("TransReferenceNo")
	private String transReferenceNo;
	
	@JsonProperty("SchemeDetails")
	ArrayList<SchemeDetails> schemeDetails = new ArrayList<SchemeDetails>();
	
	@JsonProperty("URNNumber")
	private String urnNumber;
	
	@JsonProperty("PaymentRefNo")
	private String paymentRefNo;
	
	@JsonProperty("TransactionDetails")
	private String transactionDetails = null;
	
	
	
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



	public String getTransNo() {
		return transNo;
	}



	public void setTransNo(String transNo) {
		this.transNo = transNo;
	}



	public String getTransReferenceNo() {
		return transReferenceNo;
	}



	public void setTransReferenceNo(String transReferenceNo) {
		this.transReferenceNo = transReferenceNo;
	}



	public ArrayList<SchemeDetails> getSchemeDetails() {
		return schemeDetails;
	}



	public void setSchemeDetails(ArrayList<SchemeDetails> schemeDetails) {
		this.schemeDetails = schemeDetails;
	}



	public String getUrnNumber() {
		return urnNumber;
	}



	public void setUrnNumber(String urnNumber) {
		this.urnNumber = urnNumber;
	}



	public String getPaymentRefNo() {
		return paymentRefNo;
	}



	public void setPaymentRefNo(String paymentRefNo) {
		this.paymentRefNo = paymentRefNo;
	}



	public String getTransactionDetails() {
		return transactionDetails;
	}



	public void setTransactionDetails(String transactionDetails) {
		this.transactionDetails = transactionDetails;
	}



	public static class SchemeDetails{
		@JsonProperty("SchemeReferenceId")
		private String schemeReferenceId;
		
		@JsonProperty("SchemeCode")
		private String schemeCode;
		
		@JsonProperty("SchemeTransNo")
		private String schemeTransNo;

		public String getSchemeReferenceId() {
			return schemeReferenceId;
		}

		public void setSchemeReferenceId(String schemeReferenceId) {
			this.schemeReferenceId = schemeReferenceId;
		}

		public String getSchemeCode() {
			return schemeCode;
		}

		public void setSchemeCode(String schemeCode) {
			this.schemeCode = schemeCode;
		}

		public String getSchemeTransNo() {
			return schemeTransNo;
		}

		public void setSchemeTransNo(String schemeTransNo) {
			this.schemeTransNo = schemeTransNo;
		}
		
		
	}
}
