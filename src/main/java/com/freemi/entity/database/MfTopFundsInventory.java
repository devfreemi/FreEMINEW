package com.freemi.entity.database;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="top_funds")
public class MfTopFundsInventory implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="SCHEME_CODE")
	private String schemeCode;
	
	@Column(name="SCHEME_NAME")
	private String schemeName;
	
	@Column(name="SCHEME_TYPE")
	private String schemeType;
	
	@Column(name="MIN_PURCHASE")
	private String minPurchase;
	
	@Column(name="AMC_CODE")
	private String amcCode;
	
	@Transient
	private String pan;
	
	@Transient
	private String mobile;

	public String getSchemeCode() {
		return schemeCode;
	}

	public void setSchemeCode(String schemeCode) {
		this.schemeCode = schemeCode;
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

	public String getMinPurchase() {
		return minPurchase;
	}

	public void setMinPurchase(String minPurchase) {
		this.minPurchase = minPurchase;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getAmcCode() {
		return amcCode;
	}

	public void setAmcCode(String amcCode) {
		this.amcCode = amcCode;
	}
	

}
