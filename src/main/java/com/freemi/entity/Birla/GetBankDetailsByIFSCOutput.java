package com.freemi.entity.Birla;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetBankDetailsByIFSCOutput {

	@JsonProperty("ReturnCode")
	private String returnCode;
	
	@JsonProperty("ReturnMsg")
	private String returnMsg;
	
	@JsonProperty("UDP")
	private String udp = null;
	
	@JsonProperty("lstSIPBankDet")
	ArrayList<lstSIPBankDet> lstSIPBankDet = new ArrayList<lstSIPBankDet>();
	

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


	public ArrayList<lstSIPBankDet> getLstSIPBankDet() {
		return lstSIPBankDet;
	}

	public void setLstSIPBankDet(ArrayList<lstSIPBankDet> lstSIPBankDet) {
		this.lstSIPBankDet = lstSIPBankDet;
	}


	public static class lstSIPBankDet {
		
		@JsonProperty("AddedDate")
		private String addedDate;
		
		@JsonProperty("Address")
		private String address;
		
		@JsonProperty("Bank_Name")
		private String bank_Name;
		
		@JsonProperty("Bank_WebSite")
		private String bank_WebSite;
		
		@JsonProperty("BranchDetails")
		private String branchDetails = null;
		
		@JsonProperty("Branch_Name")
		private String branch_Name;
		
		@JsonProperty("Centre")
		private String centre;
		
		@JsonProperty("Contact_Details")
		private String contact_Details = null;
		
		@JsonProperty("District")
		private String district = null;
		
		@JsonProperty("IFSC_Code")
		private String ifscCode;
		
		@JsonProperty("IsActive")
		private String isActive = null;
		
		@JsonProperty("MICR_Code")
		private String micrCode = null;
		
		@JsonProperty("SrNo")
		private float srNo;
		
		@JsonProperty("State")
		private String state;
		

		public String getAddedDate() {
			return addedDate;
		}

		public void setAddedDate(String addedDate) {
			this.addedDate = addedDate;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getBank_Name() {
			return bank_Name;
		}

		public void setBank_Name(String bank_Name) {
			this.bank_Name = bank_Name;
		}

		public String getBank_WebSite() {
			return bank_WebSite;
		}

		public void setBank_WebSite(String bank_WebSite) {
			this.bank_WebSite = bank_WebSite;
		}

		public String getBranchDetails() {
			return branchDetails;
		}

		public void setBranchDetails(String branchDetails) {
			this.branchDetails = branchDetails;
		}

		public String getBranch_Name() {
			return branch_Name;
		}

		public void setBranch_Name(String branch_Name) {
			this.branch_Name = branch_Name;
		}

		public String getCentre() {
			return centre;
		}

		public void setCentre(String centre) {
			this.centre = centre;
		}

		public String getContact_Details() {
			return contact_Details;
		}

		public void setContact_Details(String contact_Details) {
			this.contact_Details = contact_Details;
		}

		public String getDistrict() {
			return district;
		}

		public void setDistrict(String district) {
			this.district = district;
		}

		public String getIfscCode() {
			return ifscCode;
		}

		public void setIfscCode(String ifscCode) {
			this.ifscCode = ifscCode;
		}

		public String getIsActive() {
			return isActive;
		}

		public void setIsActive(String isActive) {
			this.isActive = isActive;
		}

		public String getMicrCode() {
			return micrCode;
		}

		public void setMicrCode(String micrCode) {
			this.micrCode = micrCode;
		}

		public float getSrNo() {
			return srNo;
		}

		public void setSrNo(float srNo) {
			this.srNo = srNo;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		

	}

}
