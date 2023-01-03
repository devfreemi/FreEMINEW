package com.freemi.entity.investment;

import java.io.Serializable;
import java.util.Date;

//@Entity
public class MFRedeemForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String redeemTransId="";
	private String schemeCode="";
	private String portfolio="";
	private String unitHolderName="";
	private Date redemptionTime;
	private String fundName="";
	private String investType="";
	private Double availableAmount=0.0;
	private String redeemByUnits="";
	private boolean redeemAll;
	private String redeemByAmounts="";
	private Double redeemUnits=0.0;
	private Double redeemAmounts=0.0;
	private Double totalUnits=0.0;
	private Double totalValue=0.0;
	private String currentnav;
	private String navDate;
	private Double marketValue;
	
	private String registeredBank="";
	private boolean agreePolicy;
	private String requestorSystemIp="";
	private String requestorSystem="";
	private String cancelOrderTransId="";
	private Long bankaccountforredeem;
	
	public String getRedeemTransId() {
		return redeemTransId;
	}
	public void setRedeemTransId(String redeemTransId) {
		this.redeemTransId = redeemTransId;
	}
	public String getSchemeCode() {
		return schemeCode;
	}
	public void setSchemeCode(String schemeCode) {
		this.schemeCode = schemeCode;
	}
	public String getPortfolio() {
		return portfolio;
	}
	public void setPortfolio(String portfolio) {
		this.portfolio = portfolio;
	}
	public String getUnitHolderName() {
		return unitHolderName;
	}
	public void setUnitHolderName(String unitHolderName) {
		this.unitHolderName = unitHolderName;
	}
	public Date getRedemptionTime() {
		return redemptionTime;
	}
	public void setRedemptionTime(Date redemptionTime) {
		this.redemptionTime = redemptionTime;
	}
	public String getFundName() {
		return fundName;
	}
	public void setFundName(String fundName) {
		this.fundName = fundName;
	}
	public String getInvestType() {
		return investType;
	}
	public void setInvestType(String investType) {
		this.investType = investType;
	}
	public Double getAvailableAmount() {
		return availableAmount;
	}
	public void setAvailableAmount(Double availableAmount) {
		this.availableAmount = availableAmount;
	}
	public String getRedeemByUnits() {
		return redeemByUnits;
	}
	public void setRedeemByUnits(String redeemByUnits) {
		this.redeemByUnits = redeemByUnits;
	}
	public boolean isRedeemAll() {
		return redeemAll;
	}
	public void setRedeemAll(boolean redeemAll) {
		this.redeemAll = redeemAll;
	}
	public String getRedeemByAmounts() {
		return redeemByAmounts;
	}
	public void setRedeemByAmounts(String redeemByAmounts) {
		this.redeemByAmounts = redeemByAmounts;
	}
	public Double getRedeemUnits() {
		return redeemUnits;
	}
	public void setRedeemUnits(Double redeemUnits) {
		this.redeemUnits = redeemUnits;
	}
	public Double getRedeemAmounts() {
		return redeemAmounts;
	}
	public void setRedeemAmounts(Double redeemAmounts) {
		this.redeemAmounts = redeemAmounts;
	}
	public Double getTotalUnits() {
		return totalUnits;
	}
	public void setTotalUnits(Double totalUnits) {
		this.totalUnits = totalUnits;
	}
	public Double getTotalValue() {
		return totalValue;
	}
	public void setTotalValue(Double totalValue) {
		this.totalValue = totalValue;
	}
	public String getCurrentnav() {
		return currentnav;
	}
	public void setCurrentnav(String currentnav) {
		this.currentnav = currentnav;
	}
	public String getNavDate() {
		return navDate;
	}
	public void setNavDate(String navDate) {
		this.navDate = navDate;
	}
	public Double getMarketValue() {
		return marketValue;
	}
	public void setMarketValue(Double marketValue) {
		this.marketValue = marketValue;
	}
	public String getRegisteredBank() {
		return registeredBank;
	}
	public void setRegisteredBank(String registeredBank) {
		this.registeredBank = registeredBank;
	}
	public boolean isAgreePolicy() {
		return agreePolicy;
	}
	public void setAgreePolicy(boolean agreePolicy) {
		this.agreePolicy = agreePolicy;
	}
	public String getRequestorSystemIp() {
		return requestorSystemIp;
	}
	public void setRequestorSystemIp(String requestorSystemIp) {
		this.requestorSystemIp = requestorSystemIp;
	}
	public String getRequestorSystem() {
		return requestorSystem;
	}
	public void setRequestorSystem(String requestorSystem) {
		this.requestorSystem = requestorSystem;
	}
	public String getCancelOrderTransId() {
		return cancelOrderTransId;
	}
	public void setCancelOrderTransId(String cancelOrderTransId) {
		this.cancelOrderTransId = cancelOrderTransId;
	}
	
	public Long getBankaccountforredeem() {
		return bankaccountforredeem;
	}
	public void setBankaccountforredeem(Long bankaccountforredeem) {
		this.bankaccountforredeem = bankaccountforredeem;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
		
}
