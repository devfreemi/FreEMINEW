package com.freemi.entity.loan;

import java.io.Serializable;

public class Trackloanstatusapiresponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String uniqreference;
	
	private String mobile;
	
	private String applicaitonid;
	
	private String applicationstatus;
	
	private String trackreqsuccess;
	
	private TrackloanEnquiryResponseObj loanstatus;

	public String getUniqreference() {
		return uniqreference;
	}

	public void setUniqreference(String uniqreference) {
		this.uniqreference = uniqreference;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getApplicaitonid() {
		return applicaitonid;
	}

	public void setApplicaitonid(String applicaitonid) {
		this.applicaitonid = applicaitonid;
	}

	public String getApplicationstatus() {
		return applicationstatus;
	}

	public void setApplicationstatus(String applicationstatus) {
		this.applicationstatus = applicationstatus;
	}
	
	public String getTrackreqsuccess() {
		return trackreqsuccess;
	}

	public void setTrackreqsuccess(String trackreqsuccess) {
		this.trackreqsuccess = trackreqsuccess;
	}

	public TrackloanEnquiryResponseObj getLoanstatus() {
		return loanstatus;
	}

	public void setLoanstatus(TrackloanEnquiryResponseObj loanstatus) {
		this.loanstatus = loanstatus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
