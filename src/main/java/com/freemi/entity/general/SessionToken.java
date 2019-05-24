package com.freemi.entity.general;

import java.io.Serializable;

public class SessionToken implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String token;
	private String userid;
	private String systemip;
	private String timeStamp;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getSystemip() {
		return systemip;
	}
	public void setSystemip(String systemip) {
		this.systemip = systemip;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
