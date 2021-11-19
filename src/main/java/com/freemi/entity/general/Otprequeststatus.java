package com.freemi.entity.general;

import java.io.Serializable;

public class Otprequeststatus implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String mobile;
	
	private String statuscode;
	
	private int maxresendcount;
	
	private int otpretrycount;
	
	private String msg;
	
	private String errormsg;
	
	private Integer referencecode;

	public Integer getReferencecode() {
		return referencecode;
	}

	public void setReferencecode(Integer referencecode) {
		this.referencecode = referencecode;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getStatuscode() {
		return statuscode;
	}

	public void setStatuscode(String statuscode) {
		this.statuscode = statuscode;
	}
	
	public int getMaxresendcount() {
		return maxresendcount;
	}

	public void setMaxresendcount(int maxresendcount) {
		this.maxresendcount = maxresendcount;
	}
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getErrormsg() {
		return errormsg;
	}

	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}
	public int getOtpretrycount() {
		return otpretrycount;
	}

	public void setOtpretrycount(int otpretrycount) {
		this.otpretrycount = otpretrycount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
