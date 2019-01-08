package com.freemi.entity.investment;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name="bsemf_transactions")
@Proxy(lazy=false)
public class SelectMFFund implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="SL_NO")
	private long serialNo;
	
	@Column(name="CLIENT_ID")
	private String clientID;
	
	@Column(name="SCHEME_CODE")
	private String schemeCode;
	
	@Transient
	@Column(name="")
	private String schemeName;
	
	@Column(name="TRANSACTION_ID")
	private String transactionID;
	
	@Transient
	private String amcCode;
	
	@Column(name="TRANSACTION_TYPE")
	private String transactionType="PURCHASE";
	
	@Column(name="INVEST_TYPE")
	private String investype="SIP";	//SIP or lumpsum
	
	@Column(name="SCHEME_OPTION_TYPE")
	private String schemeOptionType;
	
	@Transient
	@Column(name="")
	private String schemeType;
	
	@Column(name="INVEST_AMOUNT")
	private double investAmount;
	
	@Column(name="PAYMENT_METHOD")
	private String paymentMethod;
	
	@Column(name="SIP_DATE")
	private String sipDate;
	
	@Column(name="PORTFOLIO")
	private String portfolio;
	
	@Transient
	private String mobile;
	
	@Transient
	private String pan;
	
	@Column(name="ORDER_PLACE_TIME")
	private Date orderPlaceTime;
	
	public long getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(long serialNo) {
		this.serialNo = serialNo;
	}
	public String getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getClientID() {
		return clientID;
	}
	public void setClientID(String clientID) {
		this.clientID = clientID;
	}
	
	public String getSchemeCode() {
		return schemeCode;
	}
	public void setSchemeCode(String schemeCode) {
		this.schemeCode = schemeCode;
	}
	public String getSchemeName() {
		return schemeName;
	}
	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}
	public String getInvestype() {
		return investype;
	}
	public void setInvestype(String investype) {
		this.investype = investype;
	}
	public String getSchemeOptionType() {
		return schemeOptionType;
	}
	public void setSchemeOptionType(String schemeOptionType) {
		this.schemeOptionType = schemeOptionType;
	}
	public String getSchemeType() {
		return schemeType;
	}
	public void setSchemeType(String schemeType) {
		this.schemeType = schemeType;
	}
	
	public double getInvestAmount() {
		return investAmount;
	}
	public void setInvestAmount(double investAmount) {
		this.investAmount = investAmount;
	}
	public String getSipDate() {
		return sipDate;
	}
	public void setSipDate(String sipDate) {
		this.sipDate = sipDate;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getAmcCode() {
		return amcCode;
	}
	public void setAmcCode(String amcCode) {
		this.amcCode = amcCode;
	}
	public String getPortfolio() {
		return portfolio;
	}
	public void setPortfolio(String portfolio) {
		this.portfolio = portfolio;
	}
	public Date getOrderPlaceTime() {
		return orderPlaceTime;
	}
	public void setOrderPlaceTime(Date orderPlaceTime) {
		this.orderPlaceTime = orderPlaceTime;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	

}
