package com.freemi.entity.investment;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name="mailback_data_cams_view")
@Proxy(lazy=false)
public class MFCamsFolio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="SL_NO")
	private Long serial;
	
	@Column(name="FOLIO_NO")
	private String folioNumber;
	
	@Column(name="FUNDNAME")
	private String fundName;
	
	@Column(name="SCHEME_CODE")
	private String rtaCode;
	
	@Column(name="SCHEME_NAME")
	private String schemeName;
	
	@Column(name="REGISTRATIONDATE")
	private String registrationDate;
	
	@Column(name="TRANSACTION_TYPE")
	private String trasanctionType;
	
	@Column(name="PAN")
	private String pan;
	
	@Column(name="AMOUNT")
	private Double invAmount;
	
	@Column(name="UNITS")
	private Double units;
	
	@Transient
	private String schemeCode;
	
	@Column(name="INVESTOR_NAME")
	private String investorName;
	
	public Long getSerial() {
		return serial;
	}
	public void setSerial(Long serial) {
		this.serial = serial;
	}
	public String getFolioNumber() {
		return folioNumber;
	}
	public void setFolioNumber(String folioNumber) {
		this.folioNumber = folioNumber;
	}
	
	public String getRtaCode() {
		return rtaCode;
	}
	public void setRtaCode(String rtaCode) {
		this.rtaCode = rtaCode;
	}
	
	public String getFundName() {
		return fundName;
	}
	public void setFundName(String fundName) {
		this.fundName = fundName;
	}
	public String getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}
	
	public String getTrasanctionType() {
		return trasanctionType;
	}
	public void setTrasanctionType(String trasanctionType) {
		this.trasanctionType = trasanctionType;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
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
	public Double getUnits() {
		return units;
	}
	public void setUnits(Double units) {
		this.units = units;
	}
	public String getSchemeCode() {
		return schemeCode;
	}
	public void setSchemeCode(String schemeCode) {
		this.schemeCode = schemeCode;
	}
	public String getInvestorName() {
		return investorName;
	}
	public void setInvestorName(String investorName) {
		this.investorName = investorName;
	}
	public String getSchemeName() {
		return schemeName;
	}
	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}
	
	
}
