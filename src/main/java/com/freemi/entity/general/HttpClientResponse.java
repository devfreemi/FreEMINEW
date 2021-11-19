package com.freemi.entity.general;

import java.io.Serializable;
import java.util.Map;

import org.springframework.http.ResponseEntity;

public class HttpClientResponse implements Serializable {
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	Http response code used by FreEMI developers to implement success/failure
	
	private int responseCode;
	
	private String retrunMessage;
	
	private String data1;
	
	private String data2;
	
	private Map<String, Object> datamap;
	
	private ResponseEntity<String> responseEntity;

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public String getRetrunMessage() {
		return retrunMessage;
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

	public Map<String, Object> getDatamap() {
		return datamap;
	}

	public void setDatamap(Map<String, Object> datamap) {
		this.datamap = datamap;
	}

	public void setRetrunMessage(String retrunMessage) {
		this.retrunMessage = retrunMessage;
	}

	public ResponseEntity<String> getResponseEntity() {
		return responseEntity;
	}

	public void setResponseEntity(ResponseEntity<String> responseEntity) {
		this.responseEntity = responseEntity;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

}
