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
@Table(name="bsemf_initiated_transactions")
@Proxy(lazy=false)
public class MFinitiatedTrasactions implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="SL_NO")
	private Long serialNo;
	
	@Column(name="MOBILE_NO")
	private String mobile;
	
	@Column(name="PAN")
	private String pan;
	
	@Column(name="TRANSACTION_TYPE")
	private String transactionType="PURCHASE";
	
	@Column(name="SCHEME_CODE")
	private String schemeCode;
	
	@Column(name="SCHEME_NAME")
	private String schemeName;
	
	@Column(name="INVEST_TYPE")
	private String investype="SIP";	//SIP or lumpsum
	
	@Column(name="INVEST_AMOUNT")
	private double investAmount;
	
	@Column(name="SCHEME_OPTION_TYPE")
	private String schemeOptionType;
	
	@Column(name="SIP_DATE")
	private String sipDate;
	
	@Transient
	private String amcCode;
	
	@Column(name="ORDER_PLACE_TIME")
	private Date orderPlaceTime;
	
	@Transient
	private String rtaAgent="";
	
	@Column(name="CLIENT_IP")
	private String clientIp;
	
	@Column(name="CLIENT_BROWSER")
	private String clientBrowser;

	public Long getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(Long serialNo) {
		this.serialNo = serialNo;
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

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
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

	public double getInvestAmount() {
		return investAmount;
	}

	public void setInvestAmount(double investAmount) {
		this.investAmount = investAmount;
	}

	public String getSchemeOptionType() {
		return schemeOptionType;
	}

	public void setSchemeOptionType(String schemeOptionType) {
		this.schemeOptionType = schemeOptionType;
	}

	public String getSipDate() {
		return sipDate;
	}

	public void setSipDate(String sipDate) {
		this.sipDate = sipDate;
	}

	public String getAmcCode() {
		return amcCode;
	}

	public void setAmcCode(String amcCode) {
		this.amcCode = amcCode;
	}

	public Date getOrderPlaceTime() {
		return orderPlaceTime;
	}

	public void setOrderPlaceTime(Date orderPlaceTime) {
		this.orderPlaceTime = orderPlaceTime;
	}

	public String getRtaAgent() {
		return rtaAgent;
	}

	public void setRtaAgent(String rtaAgent) {
		this.rtaAgent = rtaAgent;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public String getClientBrowser() {
		return clientBrowser;
	}

	public void setClientBrowser(String clientBrowser) {
		this.clientBrowser = clientBrowser;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
