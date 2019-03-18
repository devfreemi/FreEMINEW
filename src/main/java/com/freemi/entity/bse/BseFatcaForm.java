package com.freemi.entity.bse;

import java.io.Serializable;
import java.util.Date;

public class BseFatcaForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String pan1;
	private String pan2;
	private String applicant1;
	private String applicant2;
	private Date applicant1DOB;
	private String taxStatus;
	public String getPan1() {
		return pan1;
	}
	public void setPan1(String pan1) {
		this.pan1 = pan1;
	}
	public String getPan2() {
		return pan2;
	}
	public void setPan2(String pan2) {
		this.pan2 = pan2;
	}
	public String getApplicant1() {
		return applicant1;
	}
	public void setApplicant1(String applicant1) {
		this.applicant1 = applicant1;
	}
	public String getApplicant2() {
		return applicant2;
	}
	public void setApplicant2(String applicant2) {
		this.applicant2 = applicant2;
	}
	public Date getApplicant1DOB() {
		return applicant1DOB;
	}
	public void setApplicant1DOB(Date applicant1dob) {
		applicant1DOB = applicant1dob;
	}
	public String getTaxStatus() {
		return taxStatus;
	}
	public void setTaxStatus(String taxStatus) {
		this.taxStatus = taxStatus;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

}
