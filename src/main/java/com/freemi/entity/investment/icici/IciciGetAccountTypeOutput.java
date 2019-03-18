package com.freemi.entity.investment.icici;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IciciGetAccountTypeOutput implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("BANK_ACT_TYPE")
	private String bank_act_type;
	
	@JsonProperty("BANK_ACT_VALUE")
	private String bank_act_value;

	public String getBank_act_type() {
		return bank_act_type;
	}

	public void setBank_act_type(String bank_act_type) {
		this.bank_act_type = bank_act_type;
	}

	public String getBank_act_value() {
		return bank_act_value;
	}

	public void setBank_act_value(String bank_act_value) {
		this.bank_act_value = bank_act_value;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
		
}
