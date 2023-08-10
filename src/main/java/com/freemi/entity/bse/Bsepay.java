package com.freemi.entity.bse;

import java.io.Serializable;

import com.freemi.entity.investment.TransactionStatus;

public class Bsepay implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long chosenbankid;
	
	private String bankgatewaycode;
	
	private String bankname;
	
	private String bankacc;
	
	private String payvia;
	
	private String paymentmode;
	
	private String ifscode;
	
	private String upiid;
	
	private String orderno;
	
	private String amount="0";
	
	private String mobile;
	
	private String investtype;
	
	private TransactionStatus transstatus;
	
	private String loopbackurl;

	public Long getChosenbankid() {
		return chosenbankid;
	}

	public void setChosenbankid(Long chosenbankid) {
		this.chosenbankid = chosenbankid;
	}
	
	public String getBankgatewaycode() {
		return bankgatewaycode;
	}

	public void setBankgatewaycode(String bankgatewaycode) {
		this.bankgatewaycode = bankgatewaycode;
	}

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public String getBankacc() {
		return bankacc;
	}

	public void setBankacc(String bankacc) {
		this.bankacc = bankacc;
	}

	public String getPayvia() {
		return payvia;
	}

	public void setPayvia(String payvia) {
		this.payvia = payvia;
	}

	public String getIfscode() {
		return ifscode;
	}

	public void setIfscode(String ifscode) {
		this.ifscode = ifscode;
	}

	public String getPaymentmode() {
		return paymentmode;
	}

	public void setPaymentmode(String paymentmode) {
		this.paymentmode = paymentmode;
	}

	public String getUpiid() {
		return upiid;
	}

	public void setUpiid(String upiid) {
		this.upiid = upiid;
	}

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getInvesttype() {
		return investtype;
	}

	public void setInvesttype(String investtype) {
		this.investtype = investtype;
	}

	public TransactionStatus getTransstatus() {
		return transstatus;
	}

	public void setTransstatus(TransactionStatus transstatus) {
		this.transstatus = transstatus;
	}

	public String getLoopbackurl() {
		return loopbackurl;
	}

	public void setLoopbackurl(String loopbackurl) {
		this.loopbackurl = loopbackurl;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
