package com.freemi.entity.Birla;

import java.io.Serializable;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ValidatePANOutput implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	@JsonProperty("ValidatePANResult")
	private ValidatePANResult panData;
	
	

	public ValidatePANResult getPanData() {
		return panData;
	}



	public void setPanData(ValidatePANResult panData) {
		this.panData = panData;
	}
	
	



	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public class ValidatePANResult {
		@JsonProperty("Folios")
		ArrayList<Object> folios = new ArrayList<Object>();
		
		@JsonProperty("AadharNo")
		private String aadharNo = null;

		@JsonProperty("IsEKYCVerified")
		private String isEKYCVerified;
		
		@JsonProperty("IsExistingInvestor")
		private String isExistingInvestor;
		
		@JsonProperty("KYC_Mode")
		private String kyc_Mode;
		
		@JsonProperty("NameAsPerPAN")
		private String nameAsPerPAN;
		
		@JsonProperty("PANNumber")
		private String panNumber;
		
		@JsonProperty("PanResponseLog")
		private String panResponseLog = null;
		
		@JsonProperty("ReturnCode")
		private String returnCode;
		
		@JsonProperty("ReturnMsg")
		private String returnMsg;
		
		@JsonProperty("UDP")
		private String udp = null;
		
		@JsonProperty("isFATCAVerified")
		private String isFATCAVerified;

		public String getAadharNo() {
			return aadharNo;
		}

		public void setAadharNo(String aadharNo) {
			this.aadharNo = aadharNo;
		}

		/*public String getFolios() {
			return folios;
		}

		public void setFolios(String folios) {
			this.folios = folios;
		}*/

		public String getIsEKYCVerified() {
			return isEKYCVerified;
		}

		public void setIsEKYCVerified(String isEKYCVerified) {
			this.isEKYCVerified = isEKYCVerified;
		}

		public String getIsExistingInvestor() {
			return isExistingInvestor;
		}

		public void setIsExistingInvestor(String isExistingInvestor) {
			this.isExistingInvestor = isExistingInvestor;
		}

		

		public String getKyc_Mode() {
			return kyc_Mode;
		}

		public void setKyc_Mode(String kyc_Mode) {
			this.kyc_Mode = kyc_Mode;
		}

		public String getNameAsPerPAN() {
			return nameAsPerPAN;
		}

		public void setNameAsPerPAN(String nameAsPerPAN) {
			this.nameAsPerPAN = nameAsPerPAN;
		}

		public String getPanNumber() {
			return panNumber;
		}

		public void setPanNumber(String panNumber) {
			this.panNumber = panNumber;
		}

		public String getPanResponseLog() {
			return panResponseLog;
		}

		public void setPanResponseLog(String panResponseLog) {
			this.panResponseLog = panResponseLog;
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

		public String getIsFATCAVerified() {
			return isFATCAVerified;
		}

		public void setIsFATCAVerified(String isFATCAVerified) {
			this.isFATCAVerified = isFATCAVerified;
		}

		public ArrayList<Object> getFolios() {
			return folios;
		}

		public void setFolios(ArrayList<Object> folios) {
			this.folios = folios;
		}
		

		

	}
}
