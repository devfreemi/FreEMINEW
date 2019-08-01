package com.freemi.entity.investment;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Proxy;

@Entity
//@Table(name="mailback_transactions_karvy_r201_view")
@Table(name = "investors_balance_view_karvy")

@Proxy(lazy=false)
public class MFKarvyValueByCategory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="SL_NO")
	private Integer serial;
	
	@Column(name="RTA_AGENT")
	private String rtaAgent;
	
	@Column(name="PAN")
	private String pan;
	
	@Column(name="FOLIO_NO")
	private String folioNumber;
	
	@Column(name="INVESTOR_NAME")
	private String investorName;
	
//	@Transient
	@Column(name="KARVY_PRODUCT_CODE")
	private String karvyProductCode;
	 
	@Column(name="INVESTMENT_AMOUNT")
	private Double invAmount;
	
	@Column(name="BALANCE_UNITS")
	private Double units;

	@Column(name="ISIN")
	private String isin;
	
	/*
	 * @Column(name="SCHEME_NAME") private String schemeName;
	 */
	
	@Column(name="BSEMF_FUND_NAME")
	private String fundDescription;
	
	@Column(name="BSEMF_SCHEME_CODE")
	private String schemeCode;

	@Column(name="AMC_NAME")
	private String amcName;
	
	@Column(name="AMC_SHORT")
	private String amcShort;
	
	
	@Column(name="ICONS")
	private String amcicon;
	
	
	@Column(name="NAV")
	private String nav;

	@Transient
	private String trasanctionType;
	
	@Column(name = "MARKET_VALUE")
	private String marketValue;
	
	public Integer getSerial() {
		return serial;
	}


	public void setSerial(Integer serial) {
		this.serial = serial;
	}


	public String getRtaAgent() {
		return rtaAgent;
	}


	public void setRtaAgent(String rtaAgent) {
		this.rtaAgent = rtaAgent;
	}


	public String getPan() {
		return pan;
	}


	public void setPan(String pan) {
		this.pan = pan;
	}


	public String getFolioNumber() {
		return folioNumber;
	}


	public void setFolioNumber(String folioNumber) {
		this.folioNumber = folioNumber;
	}


	public String getInvestorName() {
		return investorName;
	}


	public void setInvestorName(String investorName) {
		this.investorName = investorName;
	}


	public String getKarvyProductCode() {
		return karvyProductCode;
	}


	public void setKarvyProductCode(String karvyProductCode) {
		this.karvyProductCode = karvyProductCode;
	}


	public Double getInvAmount() {
		return invAmount;
	}


	public void setInvAmount(Double invAmount) {
		this.invAmount = invAmount;
	}


	public Double getUnits() {
		return units;
	}


	public void setUnits(Double units) {
		this.units = units;
	}


	public String getIsin() {
		return isin;
	}


	public void setIsin(String isin) {
		this.isin = isin;
	}


	public String getFundDescription() {
		return fundDescription;
	}


	public void setFundDescription(String fundDescription) {
		this.fundDescription = fundDescription;
	}


	public String getSchemeCode() {
		return schemeCode;
	}


	public void setSchemeCode(String schemeCode) {
		this.schemeCode = schemeCode;
	}


	public String getAmcShort() {
		return amcShort;
	}


	public void setAmcShort(String amcShort) {
		this.amcShort = amcShort;
	}


	public String getAmcicon() {
		return amcicon;
	}


	public void setAmcicon(String amcicon) {
		this.amcicon = amcicon;
	}

	public String getNav() {
		return nav;
	}


	public void setNav(String nav) {
		this.nav = nav;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public String getTrasanctionType() {
		return trasanctionType;
	}


	public void setTrasanctionType(String trasanctionType) {
		this.trasanctionType = trasanctionType;
	}


	public String getAmcName() {
		return amcName;
	}


	public void setAmcName(String amcName) {
		this.amcName = amcName;
	}


	public String getMarketValue() {
		return marketValue;
	}


	public void setMarketValue(String marketValue) {
		this.marketValue = marketValue;
	}
	
	

	
}
