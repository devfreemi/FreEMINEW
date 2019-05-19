package com.freemi.entity.general;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class ProfilePasswordChangeForm {

	@NotEmpty(message="Old password field cannot be empty.")
	private String oldPassword="";
	
	@NotEmpty(message="New password field cannot be empty") @Length(min=8, max=24, message="Password must be between 8 - 24 characters")
	private String newPassword="";
	
	@NotEmpty(message="Confirm password field cannot be empty") @Length(min=8, max=24, message="Password must be between 8 - 24 characters")
	private String confirmNewPassword="";
	
	private String mobile="";
	
	public String getConfirmNewPassword() {
		return confirmNewPassword;
	}
	public void setConfirmNewPassword(String confirmNewPassword) {
		this.confirmNewPassword = confirmNewPassword;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	
}
