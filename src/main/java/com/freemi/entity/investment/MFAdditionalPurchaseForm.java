package com.freemi.entity.investment;

import java.io.Serializable;
import java.util.Date;

//@Entity
public class MFAdditionalPurchaseForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String schemeCode;
	private String portfolio;
	private String unitHolderName;
	private Date purchaseTime;
	private String investType;
	private String fundName;
	private String redeemByUnits;
	private String redeemByAmounts;
	private double purchaseUnits;
	private double purchaseAmounts;
	private double totalAvailableUnits;
	private double totalAvailableAmount;
	private double currentNAV;
	private String purchaseTransid;
	private String paymentMode;
	private String registeredBank;
	private boolean agreePolicy;
	private String requestorSystemIp;
	private String requestorSystem;
	
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
	
	
	public double getPurchaseUnits() {
		return purchaseUnits;
	}
	public void setPurchaseUnits(double purchaseUnits) {
		this.purchaseUnits = purchaseUnits;
	}
	public double getPurchaseAmounts() {
		return purchaseAmounts;
	}
	public void setPurchaseAmounts(double purchaseAmounts) {
		this.purchaseAmounts = purchaseAmounts;
	}
	public double getTotalAvailableUnits() {
		return totalAvailableUnits;
	}
	public void setTotalAvailableUnits(double totalAvailableUnits) {
		this.totalAvailableUnits = totalAvailableUnits;
	}
	public double getTotalAvailableAmount() {
		return totalAvailableAmount;
	}
	public void setTotalAvailableAmount(double totalAvailableAmount) {
		this.totalAvailableAmount = totalAvailableAmount;
	}
	public void setTotalAvailableAmount(float totalAvailableAmount) {
		this.totalAvailableAmount = totalAvailableAmount;
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
	
	public String getInvestType() {
		return investType;
	}
	public void setInvestType(String investType) {
		this.investType = investType;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public double getCurrentNAV() {
		return currentNAV;
	}
	public void setCurrentNAV(double currentNAV) {
		this.currentNAV = currentNAV;
	}
	public Date getPurchaseTime() {
		return purchaseTime;
	}
	public void setPurchaseTime(Date purchaseTime) {
		this.purchaseTime = purchaseTime;
	}
	public String getPurchaseTransid() {
		return purchaseTransid;
	}
	public void setPurchaseTransid(String purchaseTransid) {
		this.purchaseTransid = purchaseTransid;
	}
	
	
	
}
