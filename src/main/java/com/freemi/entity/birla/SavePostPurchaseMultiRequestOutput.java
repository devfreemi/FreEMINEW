package com.freemi.entity.birla;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SavePostPurchaseMultiRequestOutput {
	
	@JsonProperty("ReturnCode")
	private String returnCode;

	@JsonProperty("ReturnMsg")
	private String returnMsg;

	@JsonProperty("UDP")
	private String udp = null;

	@JsonProperty("PaymentStatus")
	private String paymentStatus;

	@JsonProperty("TransNo")
	private String transNo;

	@JsonProperty("TransactionDetails")
	ArrayList<TransactionDetails> transactionDetails = new ArrayList<TransactionDetails>();
	
	
	

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




	public String getPaymentStatus() {
		return paymentStatus;
	}




	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}




	public String getTransNo() {
		return transNo;
	}




	public void setTransNo(String transNo) {
		this.transNo = transNo;
	}


	public ArrayList<TransactionDetails> getTransactionDetails() {
		return transactionDetails;
	}




	public void setTransactionDetails(ArrayList<TransactionDetails> transactionDetails) {
		this.transactionDetails = transactionDetails;
	}









	public static class TransactionDetails{

		@JsonProperty("SchemeCode")
		private String schemeCode;

		@JsonProperty("SchemeReferenceId")
		private String schemeReferenceId;

		@JsonProperty("SchemeTransNo")
		private String schemeTransNo;

		@JsonProperty("CAMSEntryDate")
		private String camsEntryDate;

		@JsonProperty("CAMSTransDate")
		private String camsTransDate;

		@JsonProperty("CAMS_EXP_DATE")
		private String cams_exp_date;

		@JsonProperty("CamsStatus")
		private String camsStatus;

		@JsonProperty("CamsTrxn_No")
		private String camsTrxn_No;

		@JsonProperty("Cams_ReturnCode")
		private String cams_ReturnCode;

		@JsonProperty("Cams_ReturnMsg")
		private String cams_ReturnMsg;

		public String getSchemeCode() {
			return schemeCode;
		}

		public void setSchemeCode(String schemeCode) {
			this.schemeCode = schemeCode;
		}

		public String getSchemeReferenceId() {
			return schemeReferenceId;
		}

		public void setSchemeReferenceId(String schemeReferenceId) {
			this.schemeReferenceId = schemeReferenceId;
		}

		public String getSchemeTransNo() {
			return schemeTransNo;
		}

		public void setSchemeTransNo(String schemeTransNo) {
			this.schemeTransNo = schemeTransNo;
		}

		public String getCamsEntryDate() {
			return camsEntryDate;
		}

		public void setCamsEntryDate(String camsEntryDate) {
			this.camsEntryDate = camsEntryDate;
		}

		public String getCamsTransDate() {
			return camsTransDate;
		}

		public void setCamsTransDate(String camsTransDate) {
			this.camsTransDate = camsTransDate;
		}

		public String getCams_exp_date() {
			return cams_exp_date;
		}

		public void setCams_exp_date(String cams_exp_date) {
			this.cams_exp_date = cams_exp_date;
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
		
		
		
	}
}
