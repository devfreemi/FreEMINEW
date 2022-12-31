package com.freemi.entity.investment;

import java.io.Serializable;
import java.util.Map;

public class MFmanagementapiresponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String statuscode;
	
	private String message;
	
	private String reponsedata1;
	
	private String mobile;
	
	private String responsedata2;
	
	private Map<String, Object> responsedata3;

	public String getStatuscode() {
		return statuscode;
	}

	public void setStatuscode(String statuscode) {
		this.statuscode = statuscode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getReponsedata1() {
		return reponsedata1;
	}

	public void setReponsedata1(String reponsedata1) {
		this.reponsedata1 = reponsedata1;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getResponsedata2() {
		return responsedata2;
	}

	public void setResponsedata2(String responsedata2) {
		this.responsedata2 = responsedata2;
	}

	public Map<String, Object> getResponsedata3() {
		return responsedata3;
	}

	public void setResponsedata3(Map<String, Object> responsedata3) {
		this.responsedata3 = responsedata3;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	

}
