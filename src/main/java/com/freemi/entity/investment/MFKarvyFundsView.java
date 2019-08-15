package com.freemi.entity.investment;

import java.util.List;

public class MFKarvyFundsView {
	
	private String funName;
	private Double totalInvestment=0.0;
	private Double totalMarketValue=0.0;
	private String amcicon;
	private double xirr;
	private List<MfAllInvestorValueByCategory> categorizedFund;
	
	public String getFunName() {
		return funName;
	}
	public void setFunName(String funName) {
		this.funName = funName;
	}
	public Double getTotalInvestment() {
		return totalInvestment;
	}
	public void setTotalInvestment(Double totalInvestment) {
		this.totalInvestment = totalInvestment;
	}
	public String getAmcicon() {
		return amcicon;
	}
	public void setAmcicon(String amcicon) {
		this.amcicon = amcicon;
	}
	public double getXirr() {
		return xirr;
	}
	public void setXirr(double xirr) {
		this.xirr = xirr;
	}
	public List<MfAllInvestorValueByCategory> getCategorizedFund() {
		return categorizedFund;
	}
	public void setCategorizedFund(List<MfAllInvestorValueByCategory> categorizedFund) {
		this.categorizedFund = categorizedFund;
	}
	public Double getTotalMarketValue() {
		return totalMarketValue;
	}
	public void setTotalMarketValue(Double totalMarketValue) {
		this.totalMarketValue = totalMarketValue;
	}
	
	

}
