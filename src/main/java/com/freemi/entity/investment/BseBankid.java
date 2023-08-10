package com.freemi.entity.investment;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name="bsemf_bank_details")
@Proxy(lazy=false)
public class BseBankid implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="SL_NO")
	private String serial;
	
	@Column(name="TRANS_TYPE")
	private String transactiontype;
	
	@Column(name="PAY_MODE")
	private String paymode;
	
	@Column(name="BANK_NAME")
	private String bsebankname;
	
	@Column(name="BANK_NAME_RAZORPAY")
	private String razorpaybankname;
	
	@Column(name="BANK_ID")
	private String bankid;
	
	@Column(name="ACTIVE")
	private String active;
	
	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getTransactiontype() {
		return transactiontype;
	}

	public void setTransactiontype(String transactiontype) {
		this.transactiontype = transactiontype;
	}

	public String getPaymode() {
		return paymode;
	}

	public void setPaymode(String paymode) {
		this.paymode = paymode;
	}

	public String getBsebankname() {
		return bsebankname;
	}

	public void setBsebankname(String bsebankname) {
		this.bsebankname = bsebankname;
	}

	public String getRazorpaybankname() {
		return razorpaybankname;
	}

	public void setRazorpaybankname(String razorpaybankname) {
		this.razorpaybankname = razorpaybankname;
	}

	public String getBankid() {
		return bankid;
	}

	public void setBankid(String bankid) {
		this.bankid = bankid;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
