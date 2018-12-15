package com.freemi.entity.Birla;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ValidateAadhaarOutput {


		@JsonProperty("AadharReturnCode")
		private String aadharReturnCode = null;
		
		@JsonProperty("AadharReturnMessage")
		private String aadharReturnMessage;
		
		@JsonProperty("AppKYCMode")
		private String appKYCMode = null;
		
		@JsonProperty("Flag")
		private boolean flag;
		
		@JsonProperty("IRISRefId")
		private String irisRefId = null;
		
		@JsonProperty("PAN_PEKRN")
		private String panPEKRN = null;
		
		@JsonProperty("RefID")
		private String refID;
		
		@JsonProperty("ReturnCode")
		private String returnCode;
		
		@JsonProperty("ReturnMsg")
		private String returnMsg;
		
		@JsonProperty("UDP")
		private String udp = null;

		public String getAadharReturnCode() {
			return aadharReturnCode;
		}

		public void setAadharReturnCode(String aadharReturnCode) {
			this.aadharReturnCode = aadharReturnCode;
		}

		public String getAadharReturnMessage() {
			return aadharReturnMessage;
		}

		public void setAadharReturnMessage(String aadharReturnMessage) {
			this.aadharReturnMessage = aadharReturnMessage;
		}

		public String getAppKYCMode() {
			return appKYCMode;
		}

		public void setAppKYCMode(String appKYCMode) {
			this.appKYCMode = appKYCMode;
		}

		public boolean isFlag() {
			return flag;
		}

		public void setFlag(boolean flag) {
			this.flag = flag;
		}

		public String getIrisRefId() {
			return irisRefId;
		}

		public void setIrisRefId(String irisRefId) {
			this.irisRefId = irisRefId;
		}

		public String getPanPEKRN() {
			return panPEKRN;
		}

		public void setPanPEKRN(String panPEKRN) {
			this.panPEKRN = panPEKRN;
		}

		public String getRefID() {
			return refID;
		}

		public void setRefID(String refID) {
			this.refID = refID;
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

		public String getUdp() {
			return udp;
		}

		public void setUdp(String udp) {
			this.udp = udp;
		}
		

}
