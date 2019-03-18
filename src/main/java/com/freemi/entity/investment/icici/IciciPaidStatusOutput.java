package com.freemi.entity.investment.icici;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IciciPaidStatusOutput implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("STATUS")
	private String status;

	@JsonProperty("ERR_DESCRIPTION")
	private String err_description;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getErr_description() {
		return err_description;
	}

	public void setErr_description(String err_description) {
		this.err_description = err_description;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
