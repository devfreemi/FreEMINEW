package com.freemi.entity.investment;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bsemf_all_transactions_view")
public class BseAllTransactionsView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="SL_NO")
	private String serial;
	
//	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="CLIENT_ID")
	private String clientID;
	
	@Column(name="SCHEME_CODE")
	private String schemeCode;
	
	@Column(name="PORTFOLIO")
	private String portfoilio;
	
	@Column(name="INVEST_TYPE")
	private String investType;
	
	@Column(name="SCHEME_NAME")
	private String schemeName;
	
	@Column(name="SCHEME_INVESTMENT")
	private double schemeInvestment;

	
	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getClientID() {
		return clientID;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}

	public String getSchemeCode() {
		return schemeCode;
	}

	public void setSchemeCode(String schemeCode) {
		this.schemeCode = schemeCode;
	}

	public String getPortfoilio() {
		return portfoilio;
	}

	public void setPortfoilio(String portfoilio) {
		this.portfoilio = portfoilio;
	}

	public String getInvestType() {
		return investType;
	}

	public void setInvestType(String investType) {
		this.investType = investType;
	}

	public String getSchemeName() {
		return schemeName;
	}

	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}

	public double getSchemeInvestment() {
		return schemeInvestment;
	}

	public void setSchemeInvestment(double schemeInvestment) {
		this.schemeInvestment = schemeInvestment;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
