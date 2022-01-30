package com.freemi.entity.bse;

import java.io.Serializable;

public class BseApiResponse implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String responseCode="";
	private String remarks="";
	private String statusCode="";
	private String data1;
	private String data2;
	private byte[] data3;
	
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
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
	public byte[] getData3() {
		return data3;
	}
	public void setData3(byte[] data3) {
		this.data3 = data3;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
