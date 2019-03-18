package com.freemi.entity.investment.icici;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IciciPurchaseBankListDCOutput implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@JsonProperty("BANK_CODE")
	private String bank_code;

	@JsonProperty("BANK_NAME")
	private String bank_name;

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
