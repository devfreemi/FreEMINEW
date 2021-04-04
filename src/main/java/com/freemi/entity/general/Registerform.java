package com.freemi.entity.general;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Component
public class Registerform implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
//	@NotNull @NotEmpty @Size(min=4,max=128,message="Name should be 4-128 characters") @Pattern(regexp="[a-zA-Z .]*", message="Name should not contain special characters")
	private String fullName;
	
	@NotNull(message = "Mention first name") @NotEmpty @Size(min=4,max=128,message="First Name should be 2-64 characters") @Pattern(regexp="[a-zA-Z .]*", message="Name should not contain special characters")
	private String fname;
	
	@NotNull(message = "Mention last name") @NotEmpty @Size(min=4,max=128,message="Last Name should be 2-64 characters") @Pattern(regexp="[a-zA-Z .]*", message="Name should not contain special characters")
	private String lname;
	
	@NotNull(message="Provide a password for your account") @NotEmpty @Size(min=8,max=24,message="password length should be 8-24 characters")
	private String password;
	
	@Email(message="Email format invalid")
	private String email;
	
	@NotNull(message = "Mobile no is mandatory") @NotEmpty(message = "Mobile no should not be empty") @Size(min=10,max=10, message = "10 digit valid mobile no accepted") @Pattern(regexp="[6-9][0-9]{9}", message="Mobile no format should be 10 digit beginning with number 6-9")
	private String mobile;
	private String customerID;
	private String registrationref="DEFAULT";
	private String requestingip;
	private String clientbrowserdetails;
	private String mobileimei;
	
	@JsonIgnore
	private String otp;
	
	private String otpverified="N";
	
	@JsonIgnore
	private String sessionid;
	
	public String getFullName() {
	    return fullName;
	}
	public void setFullName(String fullName) {
	    this.fullName = fullName.trim();
	}
	public String getFname() {
	    return fname;
	}
	public void setFname(String fname) {
	    this.fname = fname.trim();
	}
	public String getLname() {
	    return lname;
	}
	public void setLname(String lname) {
	    this.lname = lname;
	}
	public String getPassword() {
	    return password;
	}
	public void setPassword(String password) {
	    this.password = password.trim();
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
	public String getRequestingip() {
	    return requestingip;
	}
	public void setRequestingip(String requestingip) {
	    this.requestingip = requestingip;
	}
	public String getClientbrowserdetails() {
	    return clientbrowserdetails;
	}
	public void setClientbrowserdetails(String clientbrowserdetails) {
	    this.clientbrowserdetails = clientbrowserdetails;
	}
	public String getMobileimei() {
	    return mobileimei;
	}
	public void setMobileimei(String mobileimei) {
	    this.mobileimei = mobileimei;
	}
	
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	
	public String getOtpverified() {
		return otpverified;
	}
	public void setOtpverified(String otpverified) {
		this.otpverified = otpverified;
	}
	public String getSessionid() {
		return sessionid;
	}
	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}
	public static long getSerialversionuid() {
	    return serialVersionUID;
	}
	
	
}
