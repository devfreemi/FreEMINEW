package com.freemi.entity.general;

import java.io.Serializable;

import org.springframework.http.ResponseEntity;

public class HttpClientResponse implements Serializable {
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	Http response code used by FreEMI developers to implement success/failure
	
	private int responseCode;
	
	private String retrunMessage;
	
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
