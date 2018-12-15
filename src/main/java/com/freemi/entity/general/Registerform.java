package com.freemi.entity.general;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class Registerform implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String fullName;
	private String password;
	private String email;
	private String mobile;
	private String customerID;
	
/*	private String username;
	private String registermobile;
	private String useremail;
	private String registerpassword;*/
	
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
	
}
