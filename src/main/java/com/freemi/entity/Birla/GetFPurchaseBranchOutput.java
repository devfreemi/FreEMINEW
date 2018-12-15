package com.freemi.entity.Birla;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetFPurchaseBranchOutput {

	@JsonProperty("ReturnCode")
	private String returnCode;
	
	@JsonProperty("ReturnMsg")
	private String returnMsg;
	
	@JsonProperty("UDP")
	private String udp = null;
	
	@JsonProperty("FPurchaseBranches")
	ArrayList<FPurchaseBranches> fpPrchaseBranches = new ArrayList<FPurchaseBranches>();
	
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



	public ArrayList<FPurchaseBranches> getFpPrchaseBranches() {
		return fpPrchaseBranches;
	}



	public void setFpPrchaseBranches(ArrayList<FPurchaseBranches> fpPrchaseBranches) {
		this.fpPrchaseBranches = fpPrchaseBranches;
	}



	public static class FPurchaseBranches{
		
		@JsonProperty("Bank_Id")
		private float bank_Id;
		
		@JsonProperty("Branch_Id")
		private String branch_Id;
		
		@JsonProperty("Bank_Name")
		private String bank_Name = null;
		
		@JsonProperty("Branch_Name")
		private String branch_Name;
		
		@JsonProperty("Branch_Address")
		private String branch_Address;
		
		@JsonProperty("Branch_City")
		private String branch_City = null;
		
		@JsonProperty("IFSC_Code")
		private String ifsc_Code;
		
		@JsonProperty("MICR_Code")
		private String micr_Code;
		
		@JsonProperty("Branch_District")
		private String branch_District;
		
		@JsonProperty("Branch_State")
		private String branch_State;

		public float getBank_Id() {
			return bank_Id;
		}

		public void setBank_Id(float bank_Id) {
			this.bank_Id = bank_Id;
		}
		
		

		public String getBranch_Id() {
			return branch_Id;
		}

		public void setBranch_Id(String branch_Id) {
			this.branch_Id = branch_Id;
		}

		public String getBank_Name() {
			return bank_Name;
		}

		public void setBank_Name(String bank_Name) {
			this.bank_Name = bank_Name;
		}

		public String getBranch_Name() {
			return branch_Name;
		}

		public void setBranch_Name(String branch_Name) {
			this.branch_Name = branch_Name;
		}

		public String getBranch_Address() {
			return branch_Address;
		}

		public void setBranch_Address(String branch_Address) {
			this.branch_Address = branch_Address;
		}

		public String getBranch_City() {
			return branch_City;
		}

		public void setBranch_City(String branch_City) {
			this.branch_City = branch_City;
		}

		public String getIfsc_Code() {
			return ifsc_Code;
		}

		public void setIfsc_Code(String ifsc_Code) {
			this.ifsc_Code = ifsc_Code;
		}

		public String getMicr_Code() {
			return micr_Code;
		}

		public void setMicr_Code(String micr_Code) {
			this.micr_Code = micr_Code;
		}

		public String getBranch_District() {
			return branch_District;
		}

		public void setBranch_District(String branch_District) {
			this.branch_District = branch_District;
		}

		public String getBranch_State() {
			return branch_State;
		}

		public void setBranch_State(String branch_State) {
			this.branch_State = branch_State;
		}
		
		
	}
}
