package com.freemi.entity.general;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

@Component
public class Login {

	@NotNull @NotEmpty @Size(min=10,max=10)
	private String usermobile;
	
	@NotNull @NotEmpty @Size(min=8,max=24)
	private String userpassword;
	
	private String systemip="";
	
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
	
	
}
