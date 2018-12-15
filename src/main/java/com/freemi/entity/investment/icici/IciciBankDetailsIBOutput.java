package com.freemi.entity.investment.icici;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IciciBankDetailsIBOutput implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("BANK_CODE")
	private String bank_code;

	@JsonProperty("BANK_NAME")
	private String bank_name;

	@JsonProperty("STATUS")
	private String status;

	@JsonProperty("LIQUID_ALLOWED")
	private String liquid_allowed;

	@JsonProperty("BRANCHNAME")
	private String branchname;

	@JsonProperty("IFSCCODE")
	private String ifsccode;

	@JsonProperty("ADDRESS")
	private String address;

	@JsonProperty("CITY")
	private String city;

	public String getBank_code() {
		return bank_code;
	}

	public void setBank_code(String bank_code) {
		this.bank_code = bank_code;
	}

	public String getBank_name() {
		return bank_name;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLiquid_allowed() {
		return liquid_allowed;
	}

	public void setLiquid_allowed(String liquid_allowed) {
		this.liquid_allowed = liquid_allowed;
	}

	public String getBranchname() {
		return branchname;
	}

	public void setBranchname(String branchname) {
		this.branchname = branchname;
	}

	public String getIfsccode() {
		return ifsccode;
	}

	public void setIfsccode(String ifsccode) {
		this.ifsccode = ifsccode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
