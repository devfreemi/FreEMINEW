package com.freemi.entity.investment;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name="mailback_transactions_karvy_r201_view")
@Proxy(lazy=false)
public class MFKarvyValueByCategory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="Transaction_ID")
	private String serial;
	
	@Column(name="AMC_SHORT")
	private String amcShort;
	
	@Column(name="Folio_number")
	private String folioNumber;
	
	@Column(name="AMC_NAME")
	private String fundName;
	
//	@Transient
	@Column(name="KARVY_PRODUCT_CODE")
	private String rtaSchemeCode;
	
	@Column(name="BSEMF_FUND_NAME")
	private String fundDescription;
	
//	@Transient
	@Column(name="Scheme_Code")		//This does not represent the rta code. 
	private String rtaCode;
	
	@Column(name="Transaction_Flag")
	private String trasanctionType;
	
	@Column(name="PAN1")
	private String pan;
	
	@Column(name="INVESTMENT_AMOUNT")
	private Double invAmount;
	
	@Column(name="BALANCE_UNITS")
	private Double units;
	
	@Transient
//	@Column(name="NAV")
	private Double nav;
	
	@Column(name="BSEMF_SCHEME_CODE")
	private String schemeCode;
	
	@Column(name="RTA_AGENT")
	private String rtaAgent;
	
	@Column(name="ISIN")
	private String isin;
	
	@Column(name="ICONS")
	private String amcicon;
	
	@Column(name="Investor_Name")
	private String investorName;

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getFolioNumber() {
		return folioNumber;
	}

	public void setFolioNumber(String folioNumber) {
		this.folioNumber = folioNumber;
	}

	public String getFundName() {
		return fundName;
	}

	public void setFundName(String fundName) {
		this.fundName = fundName;
	}

	public String getRtaCode() {
		return rtaCode;
	}

	public void setRtaCode(String rtaCode) {
		this.rtaCode = rtaCode;
	}

	public String getTrasanctionType() {
		return trasanctionType;
	}

	public void setTrasanctionType(String trasanctionType) {
		this.trasanctionType = trasanctionType;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public Double getInvAmount() {
		return invAmount;
	}

	public void setInvAmount(Double invAmount) {
		this.invAmount = invAmount;
	}

	public Double getNav() {
		return nav;
	}

	public void setNav(Double nav) {
		this.nav = nav;
	}

	public String getSchemeCode() {
		return schemeCode;
	}

	public void setSchemeCode(String schemeCode) {
		this.schemeCode = schemeCode;
	}

	public String getAmcicon() {
		return amcicon;
	}

	public void setAmcicon(String amcicon) {
		this.amcicon = amcicon;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getAmcShort() {
		return amcShort;
	}

	public void setAmcShort(String amcShort) {
		this.amcShort = amcShort;
	}

	public String getFundDescription() {
		return fundDescription;
	}

	public void setFundDescription(String fundDescription) {
		this.fundDescription = fundDescription;
	}

	public String getRtaAgent() {
		return rtaAgent;
	}

	public void setRtaAgent(String rtaAgent) {
		this.rtaAgent = rtaAgent;
	}

	public String getRtaSchemeCode() {
		return rtaSchemeCode;
	}

	public void setRtaSchemeCode(String rtaSchemeCode) {
		this.rtaSchemeCode = rtaSchemeCode;
	}

	public String getInvestorName() {
		return investorName;
	}

	public void setInvestorName(String investorName) {
		this.investorName = investorName;
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
	
	
	
	
}
