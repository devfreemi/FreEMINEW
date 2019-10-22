package com.freemi.entity.database;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="fsecure_request_records")
public class FSecure implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="REQUEST_NO")
	private String requestNo;
	
	@Column(name="REQUEST_DATE")
	private Date requestDate;
	
	@Column(name="GENDER")
	private String gender;
	
	@Column(name="CUSTOMER_ID")
	private String customerId;
	
	
	@Column(name="DOB")
	private String dob;
	
	@Column(name="POLICY_TERM")
	private String policyTerm;
	
	@Column(name="IS_EXISTING_INSURANCE")
	private String hasExistingPolicy;
	
	@Column(name="POLICY_NUMBER")
	private String existingPolicyNumber;
	
	@Column(name="IS_SMOKER")
	private String isSmoker;
	
	@Column(name="FNAME")
	private String fname;
	
	@Column(name="LNAME")
	private String lname;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="MOBILE")
	private String mobile;
	
	@Column(name="PRODUCT_SUB_CATEGORY_CODE")
	private String productCode;
	
	@Column(name="ALLOW_CALLS")
	private String allowcalls;
	
	public String getRequestNo() {
		return requestNo;
	}
	public void setRequestNo(String requestNo) {
		this.requestNo = requestNo;
	}
	public Date getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getPolicyTerm() {
		return policyTerm;
	}
	public void setPolicyTerm(String policyTerm) {
		this.policyTerm = policyTerm;
	}
	public String getHasExistingPolicy() {
		return hasExistingPolicy;
	}
	public void setHasExistingPolicy(String hasExistingPolicy) {
		this.hasExistingPolicy = hasExistingPolicy;
	}
	public String getExistingPolicyNumber() {
		return existingPolicyNumber;
	}
	public void setExistingPolicyNumber(String existingPolicyNumber) {
		this.existingPolicyNumber = existingPolicyNumber;
	}
	
	public String getIsSmoker() {
		return isSmoker;
	}
	public void setIsSmoker(String isSmoker) {
		this.isSmoker = isSmoker;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
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
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getAllowcalls() {
		return allowcalls;
	}
	public void setAllowcalls(String allowcalls) {
		this.allowcalls = allowcalls.equalsIgnoreCase("true")?"Y":"N";
	}
	
	
	
}
