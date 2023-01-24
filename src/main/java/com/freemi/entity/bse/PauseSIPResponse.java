package com.freemi.entity.bse;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PauseSIPResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("RegistrationNumber")
	private String registrationno;
	
	@JsonProperty("BseRemarks")
	private String bseremarks;
	
	@JsonProperty("StatusFlag")
	private String statusflag;
	
	@JsonProperty("filler")
	private String filler;

	public String getRegistrationno() {
		return registrationno;
	}

	public void setRegistrationno(String registrationno) {
		this.registrationno = registrationno;
	}

	public String getBseremarks() {
		return bseremarks;
	}

	public void setBseremarks(String bseremarks) {
		this.bseremarks = bseremarks;
	}

	public String getStatusflag() {
		return statusflag;
	}

	public void setStatusflag(String statusflag) {
		this.statusflag = statusflag;
	}

	public String getFiller() {
		return filler;
	}

	public void setFiller(String filler) {
		this.filler = filler;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
