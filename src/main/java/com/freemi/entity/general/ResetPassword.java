package com.freemi.entity.general;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class ResetPassword {

	public String user;
	
	public String token;
	
	@NotEmpty @Length(min=8, max=24, message="Password should be 8-24 characters long")
	public String newpassword;
	
	@NotEmpty @Length(min=8, max=24, message="Password should be 8-24 characters long")
	public String confirmpassword;
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getNewpassword() {
		return newpassword;
	}
	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}
	public String getConfirmpassword() {
		return confirmpassword;
	}
	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}
	
	
}
