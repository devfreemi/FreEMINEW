package com.freemi.entity.investment;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="select_fund_details_view")
public class BseMFSelectedFunds implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="FUND_NAME")
	private String schemeName;
	
	@Column(name="RTA_Scheme_Code")
	private String rtaCode;
	
	@Column(name="CATEGORY_NAME")
	private String fundCatergory;
	
	@Id
	@Column(name="GROWTH_SCHEME_CODE")
	private String growthSchemeCode;
	
	@Column(name="ICONS")
	private String amcicon;
	
	@Column(name="REINV_SCHEME_CODE")
	private String reinvSchemeCode;
	
	@Column(name="AMC_Code")
	private String amcCode;
	
	
	@Column(name="SCHEME_TYPE")
	private String schemeType;
	
	@Column(name="LUMP_MIN_PURCHASE")
	private String lumpsumminPurchaseAmt;
	
	@Column(name="LUMP_MAX_PURCHASE")
	private String lumpsummaxPurchaseAmt;
	
	@Column(name="SETTLEMENT_TYPE")
	private String settlementType;
	
//	@Column(name="SIP_FREQUENCY")
	@Transient
	private String sipFrequency;
	
	@Column(name="SIP_DATES")
	private String sipDates;
	
	@Column(name="SIP_MINIMUM_INSTALLMENT_AMOUNT")
	private String sipMinInstallAmnt;
	
	@Column(name="SIP_MAXIMUM_INSTALLMENT_AMOUNT")
	private String sipMaxInstallAmntl;
	
	@Column(name="SIP_MINIMUM_INSTALLMENT_NUMBERS")
	private String minSipInstallments;
	
	@Column(name="NAV_VALUE_G")
	private String nav;
	
	@Column(name="NAV_VALUE_DR")
	private String reinvNav;
	
	@Column(name="")
	private String rtaAgent="";
	
	@Column(name="FUND_ISIN")
	private String isin="";
	
	@Column(name="NAV_DATE_G")
	private String gNavDate;
	
	@Column(name="NAV_DATE_DR")
	private String rNavDate;

	public String getSchemeName() {
		return schemeName;
	}

	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}

	public String getFundCatergory() {
		return fundCatergory;
	}

	public void setFundCatergory(String fundCatergory) {
		this.fundCatergory = fundCatergory;
	}

	public String getGrowthSchemeCode() {
		return growthSchemeCode;
	}

	public void setGrowthSchemeCode(String growthSchemeCode) {
		this.growthSchemeCode = growthSchemeCode;
	}

	public String getReinvSchemeCode() {
		return reinvSchemeCode;
	}

	public void setReinvSchemeCode(String reinvSchemeCode) {
		this.reinvSchemeCode = reinvSchemeCode;
	}

	public String getAmcCode() {
		return amcCode;
	}

	public void setAmcCode(String amcCode) {
		this.amcCode = amcCode;
	}

	public String getSchemeType() {
		return schemeType;
	}

	public void setSchemeType(String schemeType) {
		this.schemeType = schemeType;
	}

	public String getLumpsumminPurchaseAmt() {
		return lumpsumminPurchaseAmt;
	}

	public void setLumpsumminPurchaseAmt(String lumpsumminPurchaseAmt) {
		this.lumpsumminPurchaseAmt = lumpsumminPurchaseAmt;
	}

	public String getLumpsummaxPurchaseAmt() {
		return lumpsummaxPurchaseAmt;
	}

	public void setLumpsummaxPurchaseAmt(String lumpsummaxPurchaseAmt) {
		this.lumpsummaxPurchaseAmt = lumpsummaxPurchaseAmt;
	}

	public String getSettlementType() {
		return settlementType;
	}

	public void setSettlementType(String settlementType) {
		this.settlementType = settlementType;
	}

	public String getSipFrequency() {
		return sipFrequency;
	}

	public void setSipFrequency(String sipFrequency) {
		this.sipFrequency = sipFrequency;
	}

	public String getSipDates() {
		return sipDates;
	}

	public void setSipDates(String sipDates) {
		this.sipDates = sipDates;
	}

	public String getSipMinInstallAmnt() {
		return sipMinInstallAmnt;
	}

	public void setSipMinInstallAmnt(String sipMinInstallAmnt) {
		this.sipMinInstallAmnt = sipMinInstallAmnt;
	}

	public String getSipMaxInstallAmntl() {
		return sipMaxInstallAmntl;
	}

	public void setSipMaxInstallAmntl(String sipMaxInstallAmntl) {
		this.sipMaxInstallAmntl = sipMaxInstallAmntl;
	}

	public String getMinSipInstallments() {
		return minSipInstallments;
	}

	public void setMinSipInstallments(String minSipInstallments) {
		this.minSipInstallments = minSipInstallments;
	}

	public String getNav() {
		return nav;
	}

	public void setNav(String nav) {
		this.nav = nav;
	}

	public String getLastnavDate() {
		return gNavDate;
	}

	public void setLastnavDate(String lastnavDate) {
		this.gNavDate = lastnavDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getRtaCode() {
		return rtaCode;
	}

	public void setRtaCode(String rtaCode) {
		this.rtaCode = rtaCode;
	}

	public String getReinvNav() {
		return reinvNav;
	}

	public void setReinvNav(String reinvNav) {
		this.reinvNav = reinvNav;
	}

	public String getRtaAgent() {
		return rtaAgent;
	}

	public void setRtaAgent(String rtaAgent) {
		this.rtaAgent = rtaAgent;
	}

	public String getIsin() {
		return isin;
	}

	public void setIsin(String isin) {
		this.isin = isin;
	}

	public String getgNavDate() {
		return gNavDate;
	}

	public void setgNavDate(String gNavDate) {
		this.gNavDate = gNavDate;
	}

	public String getrNavDate() {
		return rNavDate;
	}

	public void setrNavDate(String rNavDate) {
		this.rNavDate = rNavDate;
	}

	public String getAmcicon() {
		return amcicon;
	}

	public void setAmcicon(String amcicon) {
		this.amcicon = amcicon;
	}
	
	
}
