package com.freemi.entity.loan;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TrackloanEnquiryResponseObj implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("getStatusEnquiryResponse")
	private TrackloanEnquiryResponse statusenquiryresponse;

	public TrackloanEnquiryResponse getStatusenquiryresponse() {
		return statusenquiryresponse;
	}

	public void setStatusenquiryresponse(TrackloanEnquiryResponse statusenquiryresponse) {
		this.statusenquiryresponse = statusenquiryresponse;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
