package com.freemi.entity.investment;

import java.io.Serializable;

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
	
	@Column(name="INVEST_TYPE")
	private String investype="SIP";	//SIP or lumpsum
	
	@Column(name="SCHEME_OPTION_TYPE")
	private String schemeOptionType;
	
	@Transient
	@Column(name="")
	private String schemeType;
	
	@Column(name="INVEST_AMOUNT")
	private long investAmount;
	
	@Column(name="SIP_DATE")
	private String sipDate;
	
	
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
	public long getInvestAmount() {
		return investAmount;
	}
	public void setInvestAmount(long investAmount) {
		this.investAmount = investAmount;
	}
	public String getSipDate() {
		return sipDate;
	}
	public void setSipDate(String sipDate) {
		this.sipDate = sipDate;
	}
	

}
