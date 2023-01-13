package com.freemi.entity.general;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Component
public class Login {

	@NotNull(message = "Mobile no is not shared") @NotEmpty(message = "Mobile no is not shared") @Size(min=10,max=10) @Pattern(regexp="[6-9][0-9]{9}", message="Mobile nunmber format invalid")
	private String usermobile;
	
	@NotNull(message = "Credentials not given.") @NotEmpty(message = "Credentials not given.") @Size(min=8,max=24, message="Password minimum length requirement not met")
	private String userpassword;
	
//	@JsonIgnore
	private boolean otpLogin =false;
	
//	@JsonIgnore
	private String otpVal;
	
//	@JsonIgnore
	private String returnUrl;
	
//	@JsonIgnore
	private boolean otpSubmit=false;
	
	private String systemip="";
	
	private String loginProcess="PSWD";
	
	public String getUsermobile() {
		return usermobile;
	}
	public void setUsermobile(String usermobile) {
		this.usermobile = usermobile;
	}
	public String getUserpassword() {
		return userpassword;
	}
	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}
	public String getSystemip() {
		return systemip;
	}
	public void setSystemip(String systemip) {
		this.systemip = systemip;
	}
	public boolean isOtpLogin() {
		return otpLogin;
	}
	public void setOtpLogin(boolean otpLogin) {
		this.otpLogin = otpLogin;
	}
	public String getOtpVal() {
		return otpVal;
	}
	public void setOtpVal(String otpVal) {
		this.otpVal = otpVal;
	}
	public String getReturnUrl() {
		return returnUrl;
	}
	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}
	public String getLoginProcess() {
		return loginProcess;
	}
	public void setLoginProcess(String loginProcess) {
		this.loginProcess = loginProcess;
	}
	public boolean isOtpSubmit() {
		return otpSubmit;
	}
	public void setOtpSubmit(boolean otpSubmit) {
		this.otpSubmit = otpSubmit;
	}
	
}
