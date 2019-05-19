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
@Table(name="mailback_data_karvy_view")
@Proxy(lazy=false)
public class MFKarvyValueByCategory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="SL_NO")
	private Long serial;
	
	@Column(name="AMC_SHORT")
	private String amcShort;
	
	@Column(name="Folio_number")
	private String folioNumber;
	
	@Column(name="AMC_NAME")
	private String fundName;
	
	@Column(name="Fund_Description")
	private String fundDescription;
	
	@Column(name="Fund")
	private String rtaCode;
	
	@Column(name="Transaction_Type")
	private String trasanctionType;
	
	@Column(name="PAN")
	private String pan;
	
	@Column(name="AUM")
	private Double invAmount;
	
	@Column(name="NAV")
	private Double nav;
	
	@Column(name="productCode")
	private String schemeCode;
	
	@Column(name="ICONS")
	private String amcicon;

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
	
	
	
	
}
