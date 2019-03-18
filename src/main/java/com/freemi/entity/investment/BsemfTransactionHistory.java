package com.freemi.entity.investment;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bsemf_transaction_history_view")
public class BsemfTransactionHistory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Column(name="CLIENT_ID")
	private String clienId;
	
	@Id
	@Column(name="TRANSACTION_ID")
	private String transactionId;
	
	
	@Column(name="TRANSACTION_TYPE")
	private String transctionType;
	
	@Column(name="PORTFOLIO")
	private String portfolio;
	
	@Column(name="INVEST_TYPE")
	private String investType;
	
	@Column(name="SCHEME_CODE")
	private String schemeCode;
	
	@Column(name="INVEST_AMOUNT")
	private String investAmount;
	
	@Column(name="SIP_START_FROM")
	private String sipStartDate;
	
	@Column(name="SIP_END_DATE")
	private String sipEndDate;
	
	@Column(name="ORDER_PLACE_TIME")
	private String orderTime;
	
	@Column(name="ORDER_OR_REG_NO")
	private String orderNo;
	
	
	public String getClienId() {
		return clienId;
	}
	public void setClienId(String clienId) {
		this.clienId = clienId;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getTransctionType() {
		return transctionType;
	}
	public void setTransctionType(String transctionType) {
		this.transctionType = transctionType;
	}
	public String getPortfolio() {
		return portfolio;
	}
	public void setPortfolio(String portfolio) {
		this.portfolio = portfolio;
	}
	public String getSchemeCode() {
		return schemeCode;
	}
	public void setSchemeCode(String schemeCode) {
		this.schemeCode = schemeCode;
	}
	public String getInvestAmount() {
		return investAmount;
	}
	public void setInvestAmount(String investAmount) {
		this.investAmount = investAmount;
	}
	public String getSipStartDate() {
		return sipStartDate;
	}
	public void setSipStartDate(String sipStartDate) {
		this.sipStartDate = sipStartDate;
	}
	public String getSipEndDate() {
		return sipEndDate;
	}
	public void setSipEndDate(String sipEndDate) {
		this.sipEndDate = sipEndDate;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getInvestType() {
		return investType;
	}
	public void setInvestType(String investType) {
		this.investType = investType;
	}
	
}
