package com.freemi.entity.investment.icici;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IciciPurchaseBankListIBOutput implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3460171461142531065L;

	@JsonProperty("BANK_CODE")
	private float bank_code;

	@JsonProperty("BANK_NAME")
	private String bank_name;

	@JsonProperty("STATUS")
	private String status;

	@JsonProperty("LIQUID_ALLOWED")
	private String liquid_allowed;

	public float getBank_code() {
		return bank_code;
	}

	public void setBank_code(float bank_code) {
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
