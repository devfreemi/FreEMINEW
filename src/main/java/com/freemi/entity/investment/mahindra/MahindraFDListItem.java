package com.freemi.entity.investment.mahindra;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/*@Entity
@Table(name="mahindra_fd_list")
@Proxy(lazy=false)*/
@JsonIgnoreProperties(ignoreUnknown = true)
public class MahindraFDListItem implements Serializable {
private static final long serialVersionUID = 1L;
	
    /*@Column(name = "SL_NO")
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer serial;*/
	
	
	@JsonProperty("FOLIO")
	@Column(name = "FOLIO")  
	private String folio;
	
	@JsonProperty("FDR_NO")
	@Column(name = "FDR_NO") 
	private String fdrNo;
	
	@JsonProperty("APL_NO")
	@Column(name = "APL_NO")
	private String applicationNo;
	
	@JsonProperty("FDR_DATE")
	@Column(name = "FDR_DATE") 
	private String fdrDate;
	
	@JsonProperty("PRINC_AMT")
	@Column(name = "PRINCIPAL_AMT")
	private Integer principalAmt;
	
	
	@JsonProperty("MATU_AMT")
	@Column(name = "MATURITY_AMT")
	private Integer maturityAmt;
	
	
	@JsonProperty("MATU_DATE")
	@Column(name = "MATURITY_DATE")
	private String maturityDate;
	
	
	@JsonProperty("INVESTOR_NAME")
	@Column(name = "INVESTOR_NAME") 
	private String investorName;
	
	@JsonProperty("D_OF_B")
	@Column(name = "INVESTOR_DOB") 
	private String investorDOB;
	
	@JsonProperty("PAN")
	@Column(name = "INVESTOR_PAN") 
	private String investorPAN;
	
	@JsonProperty("SCH_CODE")
	@Column(name = "PRODUCT_SCHEME_CODE")
	private String productSchemeCode;
	
	@JsonProperty("SCHEMETYPE_CODE")
	@Column(name = "SCHEMETYPE_CODE")
	private String schemeTypeCode;
	

	@JsonProperty("PERIOD_MM")
	@Column(name = "PERIOD")
	private String period;
	
	@JsonProperty("INT_RATE")
	@Column(name = "INTEREST_RATE")
	private String interestRate;
	
	@JsonProperty("RENEWAL_ELIGIBILITY")
	@Column(name = "RENEWAL_ELI")
	private String renewalEligibility; 
	
	@JsonProperty("CONDITIONAL_REASON")
	@Column(name = "CONDITIONAL")
	private String conditional;
	
	@JsonProperty("DEP_STATUS_CODE")
	@Column(name = "STATUS_CODE")
	private String statusCode;
	
	@JsonProperty("DEP_STATUS_DESC")
	@Column(name = "STATUS_DESC")
	private String statusDesc;
	
	
	@Column(name = "FETCH_DATE")
	private Date fetchDate;
	
	
    /*public Integer getSerial() {
    	return serial;
    }
    
    public void setSerial(Integer serial) {
    	this.serial = serial;
    }*/

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public String getFdrNo() {
		return fdrNo;
	}

	public void setFdrNo(String fdrNo) {
		this.fdrNo = fdrNo;
	}

	public String getApplicationNo() {
		return applicationNo;
	}

	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}

	public String getFdrDate() {
		return fdrDate;
	}

	public void setFdrDate(String fdrDate) {
		this.fdrDate = fdrDate;
	}

	public Integer getPrincipalAmt() {
		return principalAmt;
	}

	public void setPrincipalAmt(Integer principalAmt) {
		this.principalAmt = principalAmt;
	}

	public Integer getMaturityAmt() {
		return maturityAmt;
	}

	public void setMaturityAmt(Integer maturityAmt) {
		this.maturityAmt = maturityAmt;
	}

	public String getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(String maturityDate) {
		this.maturityDate = maturityDate;
	}

	public String getInvestorName() {
		return investorName;
	}

	public void setInvestorName(String investorName) {
		this.investorName = investorName;
	}

	public String getInvestorDOB() {
		return investorDOB;
	}

	public void setInvestorDOB(String investorDOB) {
		this.investorDOB = investorDOB;
	}

	public String getInvestorPAN() {
		return investorPAN;
	}

	public void setInvestorPAN(String investorPAN) {
		this.investorPAN = investorPAN;
	}

	public String getProductSchemeCode() {
		return productSchemeCode;
	}

	public void setProductSchemeCode(String productSchemeCode) {
		this.productSchemeCode = productSchemeCode;
	}

	public String getSchemeTypeCode() {
		return schemeTypeCode;
	}

	public void setSchemeTypeCode(String schemeTypeCode) {
		this.schemeTypeCode = schemeTypeCode;
	}

	

	public String getRenewalEligibility() {
		return renewalEligibility;
	}

	public void setRenewalEligibility(String renewalEligibility) {
		this.renewalEligibility = renewalEligibility;
	}

	public String getConditional() {
		return conditional;
	}

	public void setConditional(String conditional) {
		this.conditional = conditional;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate;
	} 

	public Date getFetchDate() {
		return fetchDate;
	}

	public void setFetchDate(Date fetchDate) {
		this.fetchDate = fetchDate;
	}

	public String getStatusCode() {
	    return statusCode;
	}

	public void setStatusCode(String statusCode) {
	    this.statusCode = statusCode;
	}

	public String getStatusDesc() {
	    return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
	    this.statusDesc = statusDesc;
	}

	public static long getSerialversionuid() {
	    return serialVersionUID;
	}
	
}