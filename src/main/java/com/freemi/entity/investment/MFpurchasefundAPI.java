package com.freemi.entity.investment;

import java.io.Serializable;

public class MFpurchasefundAPI implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String requestingproduct;
	
	private String username;
	
	private String passwrod;
	
	private String mobile;
	
	private String authtoken;
	
	private String requesttime;
	
	private String data1;
	
	private String data2;
	
	private String endpoint;
	
	private String systemip;
	
	private String systemdetails;
	
	private SelectMFFund selectedfund;

	public String getRequestingproduct() {
		return requestingproduct;
	}

	public void setRequestingproduct(String requestingproduct) {
		this.requestingproduct = requestingproduct;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswrod() {
		return passwrod;
	}

	public void setPasswrod(String passwrod) {
		this.passwrod = passwrod;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAuthtoken() {
		return authtoken;
	}

	public void setAuthtoken(String authtoken) {
		this.authtoken = authtoken;
	}

	public String getRequesttime() {
		return requesttime;
	}

	public void setRequesttime(String requesttime) {
		this.requesttime = requesttime;
	}

	public String getData1() {
		return data1;
	}

	public void setData1(String data1) {
		this.data1 = data1;
	}

	public String getData2() {
		return data2;
	}

	public void setData2(String data2) {
		this.data2 = data2;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String getSystemip() {
		return systemip;
	}

	public void setSystemip(String systemip) {
		this.systemip = systemip;
	}

	public String getSystemdetails() {
		return systemdetails;
	}

	public void setSystemdetails(String systemdetails) {
		this.systemdetails = systemdetails;
	}

	public SelectMFFund getSelectedfund() {
		return selectedfund;
	}

	public void setSelectedfund(SelectMFFund selectedfund) {
		this.selectedfund = selectedfund;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
