package com.freemi.entity.loan;

import java.io.Serializable;

public class Loanreqcompleteform implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String mobileno;
	private String loanbank;
	private String loanrefno;
	private String ackno;
	private String applno;
	private String action;
	private String other;
	
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	public String getLoanbank() {
		return loanbank;
	}
	public void setLoanbank(String loanbank) {
		this.loanbank = loanbank;
	}
	public String getLoanrefno() {
		return loanrefno;
	}
	public void setLoanrefno(String loanrefno) {
		this.loanrefno = loanrefno;
	}
	public String getAckno() {
		return ackno;
	}
	public void setAckno(String ackno) {
		this.ackno = ackno;
	}
	public String getApplno() {
		return applno;
	}
	public void setApplno(String applno) {
		this.applno = applno;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
