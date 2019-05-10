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
	private double availableAmount=0.0;
	private String redeemByUnits="";
	private String redeemByAmounts="";
	private double redeemUnits=0.0;
	private double redeemAmounts=0.0;
	private double totalUnits=0.0;
	private double totalValue=0.0;
	private String registeredBank="";
	private boolean agreePolicy;
	private String requestorSystemIp="";
	private String requestorSystem="";
	private String cancelOrderTransId="";
	
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
	public String getFundName() {
		return fundName;
	}
	public void setFundName(String fundName) {
		this.fundName = fundName;
	}
	public String getRedeemByUnits() {
		return redeemByUnits;
	}
	public void setRedeemByUnits(String redeemByUnits) {
		this.redeemByUnits = redeemByUnits;
	}
	public String getRedeemByAmounts() {
		return redeemByAmounts;
	}
	public void setRedeemByAmounts(String redeemByAmounts) {
		this.redeemByAmounts = redeemByAmounts;
	}
	
	public double getRedeemUnits() {
		return redeemUnits;
	}
	public void setRedeemUnits(double redeemUnits) {
		this.redeemUnits = redeemUnits;
	}
	public double getRedeemAmounts() {
		return redeemAmounts;
	}
	public void setRedeemAmounts(double redeemAmounts) {
		this.redeemAmounts = redeemAmounts;
	}
	public double getTotalUnits() {
		return totalUnits;
	}
	public void setTotalUnits(double totalUnits) {
		this.totalUnits = totalUnits;
	}
	public double getTotalValue() {
		return totalValue;
	}
	public void setTotalValue(double totalValue) {
		this.totalValue = totalValue;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
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
	public String getInvestType() {
		return investType;
	}
	public void setInvestType(String investType) {
		this.investType = investType;
	}
	public double getAvailableAmount() {
		return availableAmount;
	}
	public void setAvailableAmount(double availableAmount) {
		this.availableAmount = availableAmount;
	}
	public String getCancelOrderTransId() {
		return cancelOrderTransId;
	}
	public void setCancelOrderTransId(String cancelOrderTransId) {
		this.cancelOrderTransId = cancelOrderTransId;
	}
	
	
}
