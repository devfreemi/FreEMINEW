package com.freemi.entity.investment;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Nominee2farequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("LoginId")
	private String loginid;
	
	@JsonProperty("Password")
	private String password;
	
	@JsonProperty("MemberCode")
	private String membercode;
	
	@JsonProperty("clientCode")
	private String clientcode;
	
	@JsonProperty("InternalRefrenceNo")
	private String internalrefno;
	
	@JsonProperty("AllowLoopBack")
	private String allowloopback;
	
	@JsonProperty("Filler1")
	private String filler1;
	
	@JsonProperty("Filler2")
	private String filler2;
	
	@JsonProperty("Filler3")
	private String filler3;
	
	@JsonProperty("LoopbackReturnUrl")
	private String loopbackurl;
	
	@JsonProperty("type")
	private String type;

	public String getLoginid() {
		return loginid;
	}

	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMembercode() {
		return membercode;
	}

	public void setMembercode(String membercode) {
		this.membercode = membercode;
	}

	public String getClientcode() {
		return clientcode;
	}

	public void setClientcode(String clientcode) {
		this.clientcode = clientcode;
	}

	public String getInternalrefno() {
		return internalrefno;
	}

	public void setInternalrefno(String internalrefno) {
		this.internalrefno = internalrefno;
	}

	public String getAllowloopback() {
		return allowloopback;
	}

	public void setAllowloopback(String allowloopback) {
		this.allowloopback = allowloopback;
	}

	public String getFiller1() {
		return filler1;
	}

	public void setFiller1(String filler1) {
		this.filler1 = filler1;
	}

	public String getFiller2() {
		return filler2;
	}

	public void setFiller2(String filler2) {
		this.filler2 = filler2;
	}

	public String getFiller3() {
		return filler3;
	}

	public void setFiller3(String filler3) {
		this.filler3 = filler3;
	}

	public String getLoopbackurl() {
		return loopbackurl;
	}

	public void setLoopbackurl(String loopbackurl) {
		this.loopbackurl = loopbackurl;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
