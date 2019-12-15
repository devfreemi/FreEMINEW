package com.freemi.entity.investment.mahindra;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name="mahindra_bank_details")
@Proxy(lazy=false)
public class MahindraCustomerBankDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "SL_NO")
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer serial;
	
	@Column(name = "CUSTOMER_REF_ID")
	private String customerId;
	
	@Column(name = "MOBILE")
	private String mobile;
	
	@Column(name = "BANK_DETAILS_ID")
	private String bankDetailsId;
	
	@Column(name = "ACCOUNT_NUMBER")
	private String accountNumber;
	
	@Column(name = "BANK_NAME")
	private String bankName;
	
	@Column(name = "IFSC_CODE")
	private String ifscCode;
	
	@Column(name = "MICR_CODE")
	private String micrCode;
	
	@Column(name = "BRANCH_NAME")
	private String branchName;
	
	@Column(name = "STATE")
	private String state;
	
	@Column(name = "IS_ACTIVE")
	private String isActive="Y";

	public Integer getSerial() {
		return serial;
	}

	public void setSerial(Integer serial) {
		this.serial = serial;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getBankDetailsId() {
		return bankDetailsId;
	}

	public void setBankDetailsId(String bankDetailsId) {
		this.bankDetailsId = bankDetailsId;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getMicrCode() {
		return micrCode;
	}

	public void setMicrCode(String micrCode) {
		this.micrCode = micrCode;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
