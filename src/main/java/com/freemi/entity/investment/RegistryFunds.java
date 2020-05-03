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
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="registry_funds")
@Proxy(lazy=false)
public class RegistryFunds implements Serializable{

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "SL_NO")
	private Integer serial;
	
	@Column(name = "REGISTRY_FUND_CODE")
	private String registryfundCode;
	
	@Column(name = "SCHEME_CODE")
	private String schemeCode;	
	
	@Column(name="SIP_DATES")
	private String sipdates;

	@Column(name = "FUND_NAME")
	private String fundName;
	
	@Column(name = "FUND_TYPE")
	private String fundType;
	
	
	@Transient
	private String schemeOption;
	
	@Transient
	private String amount;
	
	@Transient
	private String person;
	
	@Transient
	private String date;
	
	@Transient
	private int fundRating;
	
	@Transient
	private String fundReturn;
	
	@Transient
	private String returnOneMonth;
	
	@Transient
	private String returnThreeMonths;
	
	@Transient
	private String returnSixMonths;
	
	@Transient
	private String returnTwelveMonths;
	
	@Transient
	private String monthlySavings;
	
	@Transient
	private String investType;
	
	@Column(name="ELIGIBILITY_CRITERIA")
	private String eligibilityCriteria;
	
	@Column(name="IS_ACTIVE")
	private String active;
	
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
	    return fundType;
	}
	public void setFundType(String fundType) {
	    this.fundType = fundType;
	}
	public String getInvestType() {
		return investType;
	}
	public void setInvestType(String investType) {
		this.investType = investType;
	}
	public Integer getSerial() {
	    return serial;
	}
	public void setSerial(Integer serial) {
	    this.serial = serial;
	}
	public String getRegistryfundCode() {
	    return registryfundCode;
	}
	public void setRegistryfundCode(String registryfundCode) {
	    this.registryfundCode = registryfundCode;
	}
	public String getEligibilityCriteria() {
	    return eligibilityCriteria;
	}
	public void setEligibilityCriteria(String eligibilityCriteria) {
	    this.eligibilityCriteria = eligibilityCriteria;
	}
	public String getActive() {
	    return active;
	}
	public void setActive(String active) {
	    this.active = active;
	}
	public String getAmount() {
	    return amount;
	}
	public void setAmount(String amount) {
	    this.amount = amount;
	}
	public String getPerson() {
	    return person;
	}
	public void setPerson(String person) {
	    this.person = person;
	}
	public String getDate() {
	    return date;
	}
	public void setDate(String date) {
	    this.date = date;
	}
	public String getSipdates() {
	    return sipdates;
	}
	public void setSipdates(String sipdates) {
	    this.sipdates = sipdates;
	}
	
	
}
