package com.freemi.entity.general;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

@Component
public class Registerform implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull @NotEmpty @Size(min=4,max=128,message="Name should be 4-128 characters") @Pattern(regexp="[a-zA-Z .]*", message="Name should not contain special characters")
	private String fullName;
	
	@NotNull @NotEmpty @Size(min=8,max=24,message="password length should be 8-24 characters")
	private String password;
	
	@Email(message="Email format invalid")
	private String email;
	
	@NotNull @NotEmpty @Size(min=10,max=10) @Pattern(regexp="[6-9][0-9]{9}", message="Mobile nunmber format invalid")
	private String mobile;
	private String customerID;
	private String registrationref="DEFAULT";
	
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
	
	
	public String getRegistrationref() {
		return registrationref;
	}
	public void setRegistrationref(String registrationref) {
		this.registrationref = registrationref;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
	
}
