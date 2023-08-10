package com.freemi.entity.bse;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.freemi.entity.investment.Errorparam;

public class Nomineeregistrationresponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("ErrorMessage")
	private Errorparam[] errormessage;
	
	@JsonProperty("StatusCode")
	private String statuscode;
	
	@JsonProperty("Type")
	private String type;
	
	@JsonProperty("Remarks")
	private String remarks;
	
	@JsonProperty("Filler1")
	private String filler1;
	
	@JsonProperty("Filler2")
	private String filler2;
	
	@JsonProperty("Filler3")
	private String filler3;
	
	public Errorparam[] getErrormessage() {
		return errormessage;
	}

	public void setErrormessage(Errorparam[] errormessage) {
		this.errormessage = errormessage;
	}

	public String getStatuscode() {
		return statuscode;
	}

	public void setStatuscode(String statuscode) {
		this.statuscode = statuscode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getFiller1() {
		return filler1;
	}

	public void setFiller1(String filler1) {
		this.filler1 = filler1;
	}

	public String getFiller2() {
		return filler2;
	}

	public void setFiller2(String filler2) {
		this.filler2 = filler2;
	}

	public String getFiller3() {
		return filler3;
	}

	public void setFiller3(String filler3) {
		this.filler3 = filler3;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
