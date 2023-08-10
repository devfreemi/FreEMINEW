package com.freemi.entity.bse;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Paymentgatewayresponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("responsestring")
	private String response;
	
	@JsonProperty("statuscode")
	private String statuscode;
	
	@JsonProperty("internalrefno")
	private String internalrefno;
	
	@JsonProperty("filler1")
	private String filler1;
	
	@JsonProperty("filler2")
	private String filler2;
	
	@JsonProperty("filler3")
	private String filler3;
	
	@JsonProperty("filler4")
	private String filler4;

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getStatuscode() {
		return statuscode;
	}

	public void setStatuscode(String statuscode) {
		this.statuscode = statuscode;
	}

	public String getInternalrefno() {
		return internalrefno;
	}

	public void setInternalrefno(String internalrefno) {
		this.internalrefno = internalrefno;
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

	public String getFiller4() {
		return filler4;
	}

	public void setFiller4(String filler4) {
		this.filler4 = filler4;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
