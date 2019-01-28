package com.freemi.entity.birla;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetSIPBanksOutput {

	@JsonProperty("lstSIPBankMaster")
	ArrayList<LstSIPBankMaster> lstSIPBankMaster = new ArrayList<LstSIPBankMaster>();

	@JsonProperty("ReturnCode")
	private String returnCode;
	
	@JsonProperty("ReturnMsg")
	private String returnMsg;
	
	@JsonProperty("UDP")
	private String udp = null;
	
	


	public ArrayList<LstSIPBankMaster> getLstSIPBankMaster() {
		return lstSIPBankMaster;
	}




	public void setLstSIPBankMaster(ArrayList<LstSIPBankMaster> lstSIPBankMaster) {
		this.lstSIPBankMaster = lstSIPBankMaster;
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




	public static class LstSIPBankMaster {
		
		@JsonProperty("SrNo")
		private float srNo;
		
		@JsonProperty("Bank_Name")
		private String bank_Name;
		
		@JsonProperty("Bank_Site")
		private String bank_Site;
		
		@JsonProperty("NRIActive")
		private String nriActive;
		
		@JsonProperty("AutoPayFlag")
		private String autoPayFlag;
		
		@JsonProperty("IsEmandate")
		private String isEmandate;
		
		@JsonProperty("AccountNoLength")
		private String accountNoLength;

		public float getSrNo() {
			return srNo;
		}

		public void setSrNo(float srNo) {
			this.srNo = srNo;
		}

		public String getBank_Name() {
			return bank_Name;
		}

		public void setBank_Name(String bank_Name) {
			this.bank_Name = bank_Name;
		}

		public String getBank_Site() {
			return bank_Site;
		}

		public void setBank_Site(String bank_Site) {
			this.bank_Site = bank_Site;
		}

		public String getNriActive() {
			return nriActive;
		}

		public void setNriActive(String nriActive) {
			this.nriActive = nriActive;
		}

		public String getAutoPayFlag() {
			return autoPayFlag;
		}

		public void setAutoPayFlag(String autoPayFlag) {
			this.autoPayFlag = autoPayFlag;
		}

		public String getIsEmandate() {
			return isEmandate;
		}

		public void setIsEmandate(String isEmandate) {
			this.isEmandate = isEmandate;
		}

		public String getAccountNoLength() {
			return accountNoLength;
		}

		public void setAccountNoLength(String accountNoLength) {
			this.accountNoLength = accountNoLength;
		}
		
		



	}


}
