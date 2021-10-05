package com.freemi.entity.loan;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TrackloanEnquiryResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("getStatusEnquiryResult")
	private TrackloanEnquiryResult statusenquiryresult;

	public TrackloanEnquiryResult getStatusenquiryresult() {
		return statusenquiryresult;
	}

	public void setStatusenquiryresult(TrackloanEnquiryResult statusenquiryresult) {
		this.statusenquiryresult = statusenquiryresult;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
