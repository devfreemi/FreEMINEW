package com.freemi.entity.investment;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name="mailback_data_karvy_union_view")
@Proxy(lazy=false)
public class MFKarvyValueByCategory2 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="Transaction_ID")
	private String serial;
	
	@Column(name="Product_Code")
	private String productCode;
	
	@Column(name="Fund")
	private String fund;
	
	@Column(name="Folio_Number")
	private String folioNo;
	
//	@Transient
	@Column(name="Scheme_Code")
	private String schemeCode;
	
	@Column(name="Transaction_Type")
	private String trasanctionType;
	
	@Column(name="Transaction_Flag")
	private String trasanctionFlag;
	
	public String getTrasanctionFlag() {
		return trasanctionFlag;
	}

	public void setTrasanctionFlag(String trasanctionFlag) {
		this.trasanctionFlag = trasanctionFlag;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	@Column(name="PAN1")
	private String pan;
	
	@Column(name="Amount")
	private Double invAmount;
	
	@Column(name="Units")
	private Double units;
	
	@Column(name="Transaction_Date")
	private Date transactionDate;

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getFund() {
		return fund;
	}

	public void setFund(String fund) {
		this.fund = fund;
	}

	public String getFolioNo() {
		return folioNo;
	}

	public void setFolioNo(String folioNo) {
		this.folioNo = folioNo;
	}

	public String getSchemeCode() {
		return schemeCode;
	}

	public void setSchemeCode(String schemeCode) {
		this.schemeCode = schemeCode;
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

	public Double getUnits() {
		return units;
	}

	public void setUnits(Double units) {
		this.units = units;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
