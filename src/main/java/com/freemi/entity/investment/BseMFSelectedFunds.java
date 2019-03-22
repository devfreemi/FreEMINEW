package com.freemi.entity.investment;

import java.io.Serializable;
import java.sql.Date;

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

	@Column(name="SCHEME_NAME")
	private String schemeName;
	
	@Column(name="CATEGORY_NAME")
	private String fundCatergory;
	
	@Id
	@Column(name="LUMPSUM_SCHEME_CODE")
	private String schemeCode;
	
	@Column(name="SIP_SCHEME_CODE")
	private String sipSchemeCode;
	
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
	
	@Column(name="SIP_FREQUENCY")
	private String sipFrequency;
	
	@Column(name="SIP_DATES")
	private String sipDates;
	
	@Column(name="SIP_MINIMUM_INSTALLMENT_AMOUNT")
	private String sipMinInstallAmnt;
	
	@Column(name="SIP_MAXIMUM_INSTALLMENT_AMOUNT")
	private String sipMaxInstallAmntl;
	
	@Column(name="SIP_MINIMUM_INSTALLMENT_NUMBERS")
	private String minSipInstallments;
	
	@Column(name="NAV_VALUE")
	private String nav;
	
	@Transient
//	@Column(name="NAV_DATE")
	private String lastnavDate;
	

	public String getSchemeCode() {
		return schemeCode;
	}

	public void setSchemeCode(String schemeCode) {
		this.schemeCode = schemeCode;
	}

	public String getAmcCode() {
		return amcCode;
	}

	public void setAmcCode(String amcCode) {
		this.amcCode = amcCode;
	}

	public String getSchemeName() {
		return schemeName;
	}

	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
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
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getNav() {
		return nav;
	}

	public void setNav(String nav) {
		this.nav = nav;
	}

	public String getLastnavDate() {
		return lastnavDate;
	}

	public void setLastnavDate(String lastnavDate) {
		this.lastnavDate = lastnavDate;
	}

	public String getFundCatergory() {
		return fundCatergory;
	}

	public void setFundCatergory(String fundCatergory) {
		this.fundCatergory = fundCatergory;
	}

	public String getSipSchemeCode() {
		return sipSchemeCode;
	}

	public void setSipSchemeCode(String sipSchemeCode) {
		this.sipSchemeCode = sipSchemeCode;
	}

	public String getMinSipInstallments() {
		return minSipInstallments;
	}

	public void setMinSipInstallments(String minSipInstallments) {
		this.minSipInstallments = minSipInstallments;
	}
	
	
}
