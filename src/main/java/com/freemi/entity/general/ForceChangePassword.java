package com.freemi.entity.general;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class ForceChangePassword {
	
	@NotEmpty @Length(min=10, max=10, message="Password should be 8-24 characters long")
	public String userid;
	
	private String accEnabledFlag;
	
	private String redirectUrl;
	
	@NotEmpty @Length(min=8, max=24, message="Temporary password should not be empty")
	public String temporaryPassword;
	
	@NotEmpty @Length(min=8, max=24, message="Password should be 8-24 characters long")
	public String newpassword;
	
	@NotEmpty @Length(min=8, max=24, message="Password should be 8-24 characters long")
	public String confirmnewpassword;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getAccEnabledFlag() {
		return accEnabledFlag;
	}

	public void setAccEnabledFlag(String accEnabledFlag) {
		this.accEnabledFlag = accEnabledFlag;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public String getTemporaryPassword() {
		return temporaryPassword;
	}

	public void setTemporaryPassword(String temporaryPassword) {
		this.temporaryPassword = temporaryPassword;
	}

	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

	public String getConfirmnewpassword() {
		return confirmnewpassword;
	}

	public void setConfirmnewpassword(String confirmnewpassword) {
		this.confirmnewpassword = confirmnewpassword;
	}
	
	

}
