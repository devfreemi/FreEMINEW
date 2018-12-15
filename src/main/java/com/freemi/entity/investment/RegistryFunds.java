package com.freemi.entity.investment;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class RegistryFunds implements Serializable{

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	private static final long serialVersionUID = 1L;

	private String fundName;
	private String FundType;
	private String schemeCode;
	private String schemeOption;
	private int fundRating;
	private String fundReturn;
	private String returnOneMonth;
	private String returnThreeMonths;
	private String returnSixMonths;
	private String returnTwelveMonths;
	private String monthlySavings;
	private String investType;
	
	public String getFundName() {
		return fundName;
	}
	public void setFundName(String fundName) {
		this.fundName = fundName;
	}
	
	
	public String getFundReturn() {
		return fundReturn;
	}
	public void setFundReturn(String fundReturn) {
		this.fundReturn = fundReturn;
	}
	public int getFundRating() {
		return fundRating;
	}
	public void setFundRating(int fundRating) {
		this.fundRating = fundRating;
	}
	public String getReturnOneMonth() {
		return returnOneMonth;
	}
	public void setReturnOneMonth(String returnOneMonth) {
		this.returnOneMonth = returnOneMonth;
	}
	public String getReturnThreeMonths() {
		return returnThreeMonths;
	}
	public void setReturnThreeMonths(String returnThreeMonths) {
		this.returnThreeMonths = returnThreeMonths;
	}
	public String getReturnSixMonths() {
		return returnSixMonths;
	}
	public void setReturnSixMonths(String returnSixMonths) {
		this.returnSixMonths = returnSixMonths;
	}
	public String getReturnTwelveMonths() {
		return returnTwelveMonths;
	}
	public void setReturnTwelveMonths(String returnTwelveMonths) {
		this.returnTwelveMonths = returnTwelveMonths;
	}
	public String getMonthlySavings() {
		return monthlySavings;
	}
	public void setMonthlySavings(String monthlySavings) {
		this.monthlySavings = monthlySavings;
	}
	public String getSchemeCode() {
		return schemeCode;
	}
	public void setSchemeCode(String schemeCode) {
		this.schemeCode = schemeCode;
	}
	public String getSchemeOption() {
		return schemeOption;
	}
	public void setSchemeOption(String schemeOption) {
		this.schemeOption = schemeOption;
	}
	public String getFundType() {
		return FundType;
	}
	public void setFundType(String fundType) {
		FundType = fundType;
	}
	public String getInvestType() {
		return investType;
	}
	public void setInvestType(String investType) {
		this.investType = investType;
	}
	
	
}
