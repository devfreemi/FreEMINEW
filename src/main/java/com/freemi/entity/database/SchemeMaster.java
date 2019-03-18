package com.freemi.entity.database;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Proxy;

import java.util.Date;


/**
 * The persistent class for the scheme_master database table.
 * 
 */
@Entity
@Table(name="scheme_master")
@NamedQuery(name="SchemeMaster.findAll", query="SELECT s FROM SchemeMaster s")
@Proxy(lazy=false)
public class SchemeMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="scheme_id")
	private Long schemeId;

	private String active;

	@Column(name="asset_class")
	private String assetClass;

	private String category;

	@Column(name="csip_first_int_max_amount")
	private double csipFirstIntMaxAmount;

	@Column(name="csip_first_int_min_amount")
	private double csipFirstIntMinAmount;

	@Column(name="csipmin_inv")
	private double csipminInv;

	@Column(name="csipmin_inv_q")
	private double csipminInvQ;

	private String dividend;

	@Column(name="dividend_flag")
	private String dividendFlag;

	@Temporal(TemporalType.DATE)
	private Date enddate;

	private String frequency;

	private String fundcode;

	private String fundname;

	private String fundtype;

	private String iscsipapplicable;

	private String isdirect;

	private String isetf;

	private String isinstantredeem;

	private String isliquid;

	private double maxinvamt;

	@Column(name="min_add_inv")
	private double minAddInv;

	@Column(name="min_inv")
	private double minInv;

	private String nfo;

	private String rtacode;

	@Column(name="scheme_code")
	private String schemeCode;

	@Column(name="scheme_name")
	private String schemeName;

	@Column(name="scheme_option")
	private String schemeOption;

	private String sipactive;

	@Column(name="sipmin_inv")
	private double sipminInv;

	private String solutionbucket;

	@Temporal(TemporalType.DATE)
	private Date startdate;

	private String stpfrom;

	private String stpto;

	private String subcategory;

	private String switchfrom;

	private String switchto;

	private String swpflag;

	private String triggerfrom;

	private String triggerto;

	public SchemeMaster() {
	}

	public Long getSchemeId() {
		return this.schemeId;
	}

	public void setSchemeId(Long schemeId) {
		this.schemeId = schemeId;
	}

	public String getActive() {
		return this.active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getAssetClass() {
		return this.assetClass;
	}

	public void setAssetClass(String assetClass) {
		this.assetClass = assetClass;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getCsipFirstIntMaxAmount() {
		return this.csipFirstIntMaxAmount;
	}

	public void setCsipFirstIntMaxAmount(double csipFirstIntMaxAmount) {
		this.csipFirstIntMaxAmount = csipFirstIntMaxAmount;
	}

	public double getCsipFirstIntMinAmount() {
		return this.csipFirstIntMinAmount;
	}

	public void setCsipFirstIntMinAmount(double csipFirstIntMinAmount) {
		this.csipFirstIntMinAmount = csipFirstIntMinAmount;
	}

	public double getCsipminInv() {
		return this.csipminInv;
	}

	public void setCsipminInv(double csipminInv) {
		this.csipminInv = csipminInv;
	}

	public double getCsipminInvQ() {
		return this.csipminInvQ;
	}

	public void setCsipminInvQ(double csipminInvQ) {
		this.csipminInvQ = csipminInvQ;
	}

	public String getDividend() {
		return this.dividend;
	}

	public void setDividend(String dividend) {
		this.dividend = dividend;
	}

	public String getDividendFlag() {
		return this.dividendFlag;
	}

	public void setDividendFlag(String dividendFlag) {
		this.dividendFlag = dividendFlag;
	}

	public Date getEnddate() {
		return this.enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public String getFrequency() {
		return this.frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getFundcode() {
		return this.fundcode;
	}

	public void setFundcode(String fundcode) {
		this.fundcode = fundcode;
	}

	public String getFundname() {
		return this.fundname;
	}

	public void setFundname(String fundname) {
		this.fundname = fundname;
	}

	public String getFundtype() {
		return this.fundtype;
	}

	public void setFundtype(String fundtype) {
		this.fundtype = fundtype;
	}

	public String getIscsipapplicable() {
		return this.iscsipapplicable;
	}

	public void setIscsipapplicable(String iscsipapplicable) {
		this.iscsipapplicable = iscsipapplicable;
	}

	public String getIsdirect() {
		return this.isdirect;
	}

	public void setIsdirect(String isdirect) {
		this.isdirect = isdirect;
	}

	public String getIsetf() {
		return this.isetf;
	}

	public void setIsetf(String isetf) {
		this.isetf = isetf;
	}

	public String getIsinstantredeem() {
		return this.isinstantredeem;
	}

	public void setIsinstantredeem(String isinstantredeem) {
		this.isinstantredeem = isinstantredeem;
	}

	public String getIsliquid() {
		return this.isliquid;
	}

	public void setIsliquid(String isliquid) {
		this.isliquid = isliquid;
	}

	public double getMaxinvamt() {
		return this.maxinvamt;
	}

	public void setMaxinvamt(double maxinvamt) {
		this.maxinvamt = maxinvamt;
	}

	public double getMinAddInv() {
		return this.minAddInv;
	}

	public void setMinAddInv(double minAddInv) {
		this.minAddInv = minAddInv;
	}

	public double getMinInv() {
		return this.minInv;
	}

	public void setMinInv(double minInv) {
		this.minInv = minInv;
	}

	public String getNfo() {
		return this.nfo;
	}

	public void setNfo(String nfo) {
		this.nfo = nfo;
	}

	public String getRtacode() {
		return this.rtacode;
	}

	public void setRtacode(String rtacode) {
		this.rtacode = rtacode;
	}

	public String getSchemeCode() {
		return this.schemeCode;
	}

	public void setSchemeCode(String schemeCode) {
		this.schemeCode = schemeCode;
	}

	public String getSchemeName() {
		return this.schemeName;
	}

	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}

	public String getSchemeOption() {
		return this.schemeOption;
	}

	public void setSchemeOption(String schemeOption) {
		this.schemeOption = schemeOption;
	}

	public String getSipactive() {
		return this.sipactive;
	}

	public void setSipactive(String sipactive) {
		this.sipactive = sipactive;
	}

	public double getSipminInv() {
		return this.sipminInv;
	}

	public void setSipminInv(double sipminInv) {
		this.sipminInv = sipminInv;
	}

	public String getSolutionbucket() {
		return this.solutionbucket;
	}

	public void setSolutionbucket(String solutionbucket) {
		this.solutionbucket = solutionbucket;
	}

	public Date getStartdate() {
		return this.startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public String getStpfrom() {
		return this.stpfrom;
	}

	public void setStpfrom(String stpfrom) {
		this.stpfrom = stpfrom;
	}

	public String getStpto() {
		return this.stpto;
	}

	public void setStpto(String stpto) {
		this.stpto = stpto;
	}

	public String getSubcategory() {
		return this.subcategory;
	}

	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}

	public String getSwitchfrom() {
		return this.switchfrom;
	}

	public void setSwitchfrom(String switchfrom) {
		this.switchfrom = switchfrom;
	}

	public String getSwitchto() {
		return this.switchto;
	}

	public void setSwitchto(String switchto) {
		this.switchto = switchto;
	}

	public String getSwpflag() {
		return this.swpflag;
	}

	public void setSwpflag(String swpflag) {
		this.swpflag = swpflag;
	}

	public String getTriggerfrom() {
		return this.triggerfrom;
	}

	public void setTriggerfrom(String triggerfrom) {
		this.triggerfrom = triggerfrom;
	}

	public String getTriggerto() {
		return this.triggerto;
	}

	public void setTriggerto(String triggerto) {
		this.triggerto = triggerto;
	}

}