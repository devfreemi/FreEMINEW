package com.freemi.entity.investment;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name="funds_explorer_all_master_nav")
@Proxy(lazy=false)
public class BseFundsScheme implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="SL_NO")
	private int serial;
	
	@Column(name="Scheme_Name")
	private String fundName;
	
	@Column(name="Scheme_Code")
	private String schemeCode;
	
	@Column(name="ISIN")
	private String isin;
	
	@Column(name="RTA_Scheme_Code")
	private String rtaCode;
	
	@Column(name="AMC_Code")
	private String amcCode;
	
	@Column(name="Scheme_Type")
	private String schemeType;
	
	@Column(name="Minimum_Purchase_Amount")
	private double minInv;
	
	@Column(name="NAV_VALUE")
	private String nav;
	
	@Column(name="SETTLEMENT_TYPE")
	private String settlementType;

	public int getSerial() {
		return serial;
	}

	public void setSerial(int serial) {
		this.serial = serial;
	}

	public String getFundName() {
		return fundName;
	}

	public void setFundName(String fundName) {
		this.fundName = fundName;
	}

	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getSchemeType() {
		return schemeType;
	}

	public void setSchemeType(String schemeType) {
		this.schemeType = schemeType;
	}

	public String getSchemeCode() {
		return schemeCode;
	}

	public void setSchemeCode(String schemeCode) {
		this.schemeCode = schemeCode;
	}

	public double getMinInv() {
		return minInv;
	}

	public void setMinInv(double minInv) {
		this.minInv = minInv;
	}

	public String getAmcCode() {
		return amcCode;
	}

	public void setAmcCode(String amcCode) {
		this.amcCode = amcCode;
	}

	public String getNav() {
		return nav;
	}

	public void setNav(String nav) {
		this.nav = nav;
	}

	public String getIsin() {
		return isin;
	}

	public void setIsin(String isin) {
		this.isin = isin;
	}

	public String getRtaCode() {
		return rtaCode;
	}

	public void setRtaCode(String rtaCode) {
		this.rtaCode = rtaCode;
	}

	public String getSettlementType() {
		return settlementType;
	}

	public void setSettlementType(String settlementType) {
		this.settlementType = settlementType;
	}
	
	
}
