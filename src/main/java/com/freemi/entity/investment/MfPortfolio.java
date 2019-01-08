package com.freemi.entity.investment;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="bsemf_customers_portfolio")
public class MfPortfolio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="SL_NO")
	private Long serial;
	
	@Column(name="CLIENT_ID")
	private String clientId;
	
	@Transient
	@Column(name="")
	private String pan;
	
	@Column(name="AMC_CODE_BSE")
	private String amcCode;
	
	@Column(name="FOLIO_ENTRY_DATE")
	private String portfolioCreationDate;
	
	@Column(name="PORTFOLIO_NO")
	private String portfolioNumber;
	
	
	public Long getSerial() {
		return serial;
	}
	public void setSerial(Long serial) {
		this.serial = serial;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getAmcCode() {
		return amcCode;
	}
	public void setAmcCode(String amcCode) {
		this.amcCode = amcCode;
	}
	public String getPortfolioCreationDate() {
		return portfolioCreationDate;
	}
	public void setPortfolioCreationDate(String portfolioCreationDate) {
		this.portfolioCreationDate = portfolioCreationDate;
	}
	public String getPortfolioNumber() {
		return portfolioNumber;
	}
	public void setPortfolioNumber(String portfolioNumber) {
		this.portfolioNumber = portfolioNumber;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

}
