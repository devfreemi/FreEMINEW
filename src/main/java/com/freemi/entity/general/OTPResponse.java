package com.freemi.entity.general;

public class OTPResponse {
	private int status;
	private String tss;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public String getTss() {
		return tss;
	}
	public void setTss(String tss) {
		this.tss = tss;
	}
	public OTPResponse() {}
	public OTPResponse(int status, String tss) {
		this.status = status;
		this.tss = tss;
	}
}
