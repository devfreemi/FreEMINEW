package com.freemi.entity.bse;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.freemi.entity.bse.Nomineerecords;

public class Nomineeregistrationrequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("Type")
	private String type;
	
	@JsonProperty("UserId")
	private String userid;
	
	@JsonProperty("MemberCode")
	private String membercode;
	
	@JsonProperty("Password")
	private String password;
	
	@JsonProperty("RegnType")
	private String registrationttype;
	
	@JsonProperty("FILLER1")
	private String filler1;
	
	@JsonProperty("FILLER2")
	private String filler2;
	
	@JsonProperty("FILLER3")
	private String filler3;
	
	@JsonProperty("Param")
	private Nomineerecords[] param;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getMembercode() {
		return membercode;
	}

	public void setMembercode(String membercode) {
		this.membercode = membercode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRegistrationttype() {
		return registrationttype;
	}

	public void setRegistrationttype(String registrationttype) {
		this.registrationttype = registrationttype;
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

	public Nomineerecords[] getParam() {
		return param;
	}

	public void setParam(Nomineerecords[] param) {
		this.param = param;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
