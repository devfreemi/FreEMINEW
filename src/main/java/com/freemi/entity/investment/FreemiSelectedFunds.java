package com.freemi.entity.investment;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="freemi_funds_details_view")
public class FreemiSelectedFunds implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="AMC_NAME")
	private String amcname;
	
	@Column(name="AMC_ENABLED")
	private String amcenabled;
	
	@Column(name="FUND_CODE")
	private String freemifundcode;
	
	@Column(name="CATEGORY_NAME")
	private String fundCatergory;
	
	@Column(name="FRMI_FUND_NAME")
	private String schemeName;
	
	@Column(name="ACTIVE")
	private String fundactivefortransaction;
	
	@Column(name="TOP_FUNDS_VIEW")
	private String topfundsview;
	
	@Column(name="SCHEME_CODE_TYPE")	//DEFAULT/L1/INSURANCE
	private String investmenttype;
	
	@Id
	@Column(name="SCHEME_CODE")
	private String schemecode;
	
	@Column(name="Dividend_Reinvestment_Flag")
	private String reinvestmentflag;
	
	@Column(name="ISIN")
	private String isin;
	
	@Column(name="Minimum_Purchase_Amount")
	private String lumpsumminPurchaseAmt;
	
	/*
	 * @Column(name="LUMP_MAX_PURCHASE") private Integer lumpsummaxPurchaseAmt;
	 */
	@Column(name="Maximum_Purchase_Amount")
	private Integer lumpsummaximumpurchaseamnt;
	
	@Column(name="Purchase_Allowed")
	private String purchaseallowed;
	
	@Column(name="Redemption_Allowed")
	private String redemptionallowed;
	
	@Column(name="SIP_FLAG")
	private String sipflag;
	
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
	
	
	/*
	 * @Column(name="SIP_MINIMUM_INSTALLMENT_NUMBERS") private String
	 * minSipInstallments;
	 */
	
	@Column(name="nav")
	private String nav;
	
	@Column(name="nav_date")
	private String navdate;
	
	@Column(name="RTA_Agent_Code")
	private String rtaagent="";
	 
	
	@Column(name="ICONS")
	private String amcicon;

	
	public String getMinSipInstallments() {
		return minSipInstallments;
	}


	public void setMinSipInstallments(String minSipInstallments) {
		this.minSipInstallments = minSipInstallments;
	}


	public String getAmcname() {
		return amcname;
	}


	public void setAmcname(String amcname) {
		this.amcname = amcname;
	}


	public String getAmcenabled() {
		return amcenabled;
	}


	public void setAmcenabled(String amcenabled) {
		this.amcenabled = amcenabled;
	}


	public String getFreemifundcode() {
		return freemifundcode;
	}


	public void setFreemifundcode(String freemifundcode) {
		this.freemifundcode = freemifundcode;
	}


	public String getSchemeName() {
		return schemeName;
	}


	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}


	public String getFundactivefortransaction() {
		return fundactivefortransaction;
	}


	public void setFundactivefortransaction(String fundactivefortransaction) {
		this.fundactivefortransaction = fundactivefortransaction;
	}


	public String getInvestmenttype() {
		return investmenttype;
	}


	public void setInvestmenttype(String investmenttype) {
		this.investmenttype = investmenttype;
	}


	public String getSchemecode() {
		return schemecode;
	}


	public void setSchemecode(String schemecode) {
		this.schemecode = schemecode;
	}


	public String getReinvestmentflag() {
		return reinvestmentflag;
	}


	public void setReinvestmentflag(String reinvestmentflag) {
		this.reinvestmentflag = reinvestmentflag;
	}


	public String getIsin() {
		return isin;
	}


	public void setIsin(String isin) {
		this.isin = isin;
	}


	public String getLumpsumminPurchaseAmt() {
		return lumpsumminPurchaseAmt;
	}


	public void setLumpsumminPurchaseAmt(String lumpsumminPurchaseAmt) {
		this.lumpsumminPurchaseAmt = lumpsumminPurchaseAmt;
	}


	public Integer getLumpsummaximumpurchaseamnt() {
		return lumpsummaximumpurchaseamnt;
	}


	public void setLumpsummaximumpurchaseamnt(Integer lumpsummaximumpurchaseamnt) {
		this.lumpsummaximumpurchaseamnt = lumpsummaximumpurchaseamnt;
	}


	public String getPurchaseallowed() {
		return purchaseallowed;
	}


	public void setPurchaseallowed(String purchaseallowed) {
		this.purchaseallowed = purchaseallowed;
	}


	public String getRedemptionallowed() {
		return redemptionallowed;
	}


	public void setRedemptionallowed(String redemptionallowed) {
		this.redemptionallowed = redemptionallowed;
	}


	public String getSipflag() {
		return sipflag;
	}


	public void setSipflag(String sipflag) {
		this.sipflag = sipflag;
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


	public String getNav() {
		return nav;
	}


	public void setNav(String nav) {
		this.nav = nav;
	}


	public String getNavdate() {
		return navdate;
	}


	public void setNavdate(String navdate) {
		this.navdate = navdate;
	}

	public String getAmcicon() {
		return amcicon;
	}


	public void setAmcicon(String amcicon) {
		this.amcicon = amcicon;
	}

	public String getFundCatergory() {
		return fundCatergory;
	}


	public void setFundCatergory(String fundCatergory) {
		this.fundCatergory = fundCatergory;
	}


	public String getTopfundsview() {
		return topfundsview;
	}


	public void setTopfundsview(String topfundsview) {
		this.topfundsview = topfundsview;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public String getRtaagent() {
		return rtaagent;
	}


	public void setRtaagent(String rtaagent) {
		this.rtaagent = rtaagent;
	}	

	/*
	 * @Column(name="TOP_FUNDS_VIEW") private String topFundsView;
	 */
	
	
	
}
