package com.freemi.entity.database;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="freemi_purchase_records")
public class FreemiLoanForm implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="CUSTOMER_REQUEST_NUMBER")
	private int customerRequestNumber;
	
	@Column(name="")
	private String loan_category;
	
	@Column(name="PRODUCT_SUB_CATGORY_CODE")
	private String loanType;
	
	@Column(name="LOAN_AMOUNT")
	private int loanAmount;
	
	@Column(name="TENURE")
	private int loanTenure;
	
	@Column(name="LOCATION")
	private String city;
	
	@Column(name="MONTHLY_INCOMDE")
	private int netIncome;
	
	@Column(name="EMP_TENURE")
	private int employement;
	
	@Column(name="EXISTING_EMI_AMOUNT")
	private int emiOutflow;
	
	@Column(name="MONTHLY_GROSS_EXPENSE")
	private int rentOutflow;
	
	@Column(name="EMAIL_ID")
	private String emailId;
	
	@Column(name="IS_INDIAN")
	private char citizenship;
	
	
	public int getCustomerRequestNumber() {
		return customerRequestNumber;
	}
	public void setCustomerRequestNumber(int customerRequestNumber) {
		this.customerRequestNumber = customerRequestNumber;
	}
	public String getLoan_category() {
		return loan_category;
	}
	public void setLoan_category(String loan_category) {
		this.loan_category = loan_category;
	}
	public String getLoanType() {
		return loanType;
	}
	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}
	public int getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(int loanAmount) {
		this.loanAmount = loanAmount;
	}
	public int getLoanTenure() {
		return loanTenure;
	}
	public void setLoanTenure(int loanTenure) {
		this.loanTenure = loanTenure;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getNetIncome() {
		return netIncome;
	}
	public void setNetIncome(int netIncome) {
		this.netIncome = netIncome;
	}
	public int getEmployement() {
		return employement;
	}
	public void setEmployement(int employement) {
		this.employement = employement;
	}
	public int getEmiOutflow() {
		return emiOutflow;
	}
	public void setEmiOutflow(int emiOutflow) {
		this.emiOutflow = emiOutflow;
	}
	public int getRentOutflow() {
		return rentOutflow;
	}
	public void setRentOutflow(int rentOutflow) {
		this.rentOutflow = rentOutflow;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public char getCitizenship() {
		return citizenship;
	}
	public void setCitizenship(char citizenship) {
		this.citizenship = citizenship;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	
}
