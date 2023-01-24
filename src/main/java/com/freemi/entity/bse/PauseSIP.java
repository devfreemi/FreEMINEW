package com.freemi.entity.bse;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PauseSIP implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("LoginId")
	private String loginid;
	
	@JsonProperty("MemberCode")
	private String membercode;
	
	@JsonProperty("Password")
	private String password;
	
	@JsonProperty("ClientCode")
	private String clientid;
	
	@JsonProperty("RegistrationType")
	private String registrationtype;
	
	@JsonProperty("RegistrationNumber")
	private String registrationno;
	
	@JsonProperty("ModificationType")
	private String modificationtype;
	
	@JsonProperty("NoOfInstalments")
	private String noofinstallments;
	
	private String filler1;
	
	private String filler2;
	
	private String filler3;
	
	private String filler4;
	
	private String filler5;

	public String getLoginid() {
		return loginid;
	}

	public void setLoginid(String loginid) {
		this.loginid = loginid;
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

	public String getClientid() {
		return clientid;
	}

	public void setClientid(String clientid) {
		this.clientid = clientid;
	}

	public String getRegistrationtype() {
		return registrationtype;
	}

	public void setRegistrationtype(String registrationtype) {
		this.registrationtype = registrationtype;
	}

	public String getRegistrationno() {
		return registrationno;
	}

	public void setRegistrationno(String registrationno) {
		this.registrationno = registrationno;
	}

	public String getModificationtype() {
		return modificationtype;
	}

	public void setModificationtype(String modificationtype) {
		this.modificationtype = modificationtype;
	}

	public String getNoofinstallments() {
		return noofinstallments;
	}

	public void setNoofinstallments(String noofinstallments) {
		this.noofinstallments = noofinstallments;
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

	public String getFiller4() {
		return filler4;
	}

	public void setFiller4(String filler4) {
		this.filler4 = filler4;
	}

	public String getFiller5() {
		return filler5;
	}

	public void setFiller5(String filler5) {
		this.filler5 = filler5;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
