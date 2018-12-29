package com.freemi.entity.database;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.freemi.entity.investment.BseMFInvestForm;

@Entity
@Table(name="bsemf_customers_bankdetails")
public class UserBankDetails implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="SL_NO")
	private Long serialNo;
	
	@Column(name="CLIENT_ID")
	private String clientID;
	
	@Transient
	@Column(name="")
	private String accountHolder;
	
	@Column(name="IFSC_CODE_1")
	private String ifscCode;
	
	@Column(name="BANK_NAME_1")
	private String bankName;
	
	@Column(name="ACCOUNT_NO_1")
	private String accountNumber;
	
	@Column(name="ACCOUNT_CITY_1")
	private String bankCity;
	
	@Column(name="ACCOUNT_STATE_1")
	private String branchState;
	
	@Transient
	@Column(name="")
	private String bankAddress;
	
	@Column(name="ACCOUNT_BRANCH_1")
	private String bankBranch;
	
	@Column(name="ACCOUNT_TYPE_1")
	private String accountType="Savings Account";
	
	@Transient
	@Column(name="")
	private String accountCountry;
	
	@OneToOne(fetch= FetchType.LAZY, optional=false, cascade=CascadeType.PERSIST)
	@JoinColumn(name="CLIENT_ID", nullable= false,insertable=false,updatable=false)
	private BseMFInvestForm mfForm;
	
	public Long getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(Long serialNo) {
		this.serialNo = serialNo;
	}
	public String getClientID() {
		return clientID;
	}
	public void setClientID(String clientID) {
		this.clientID = clientID;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getBankBranch() {
		return bankBranch;
	}
	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}
	public String getAccountHolder() {
		return accountHolder;
	}
	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getBankCity() {
		return bankCity;
	}
	public void setBankCity(String bankCity) {
		this.bankCity = bankCity;
	}
	public String getBankAddress() {
		return bankAddress;
	}
	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getAccountCountry() {
		return accountCountry;
	}
	public void setAccountCountry(String accountCountry) {
		this.accountCountry = accountCountry;
	}
	public String getBranchState() {
		return branchState;
	}
	public void setBranchState(String branchState) {
		this.branchState = branchState;
	}
	public BseMFInvestForm getMfForm() {
		return mfForm;
	}
	public void setMfForm(BseMFInvestForm mfForm) {
		this.mfForm = mfForm;
	}
	
	
	

}
