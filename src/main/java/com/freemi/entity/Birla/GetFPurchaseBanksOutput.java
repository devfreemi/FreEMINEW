package com.freemi.entity.Birla;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetFPurchaseBanksOutput {

	@JsonProperty("FPurchaseBankslist")
	ArrayList<FPurchaseBankslist> fpPrchaseBankslist = new ArrayList<FPurchaseBankslist>();

	@JsonProperty("ReturnCode")
	private String returnCode;

	@JsonProperty("ReturnMsg")
	private String returnMsg;

	@JsonProperty("UDP")
	private String udp = null;




	public ArrayList<FPurchaseBankslist> getFpPrchaseBankslist() {
		return fpPrchaseBankslist;
	}


	public void setFpPrchaseBankslist(ArrayList<FPurchaseBankslist> fpPrchaseBankslist) {
		this.fpPrchaseBankslist = fpPrchaseBankslist;
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

	public static class FPurchaseBankslist {

		@JsonProperty("SL_No")
		private int sl_no;

		@JsonProperty("Bank_Name")
		private String bank_name;

		@JsonProperty("AccNoLength")
		private String accNoLength;

		public int getSl_no() {
			return sl_no;
		}

		public void setSl_no(int sl_no) {
			this.sl_no = sl_no;
		}

		public String getBank_name() {
			return bank_name;
		}

		public void setBank_name(String bank_name) {
			this.bank_name = bank_name;
		}

		public String getAccNoLength() {
			return accNoLength;
		}

		public void setAccNoLength(String accNoLength) {
			this.accNoLength = accNoLength;
		}



	}


}
