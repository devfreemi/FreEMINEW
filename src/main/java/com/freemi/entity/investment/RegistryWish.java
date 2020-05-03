package com.freemi.entity.investment;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name="registry_planner_records")
@Proxy(lazy=false)
public class RegistryWish implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "SL_NO")
	private Integer serial;
	
	@NotNull(message = "Wish category missing in request.") @Column(name = "WISH_TYPE")
	private String wishType;
	
	@Column(name = "REGISTRY_FUND_CODE")	
	private String registryfundcode;
	
	@Column(name="REGISTRY_NAME")
	private String registryname;
	
	@Column(name="TRANSACTION_ID")
	private String transactionid;
	
	@NotNull(message = "Please select your goal type") @Column(name = "INVESTMENT_TYPE")
	private String investType;
	
	@Column(name = "TENURE")
	@NotNull(message = "Calculated tenure data missing") @Min(value = 6, message = "Minimum tenure should be 6 months")
	private Integer tenure ;
	
	
	@Column(name = "AMOUNT")
	@NotNull(message = "Please provide your goal amount") @Min(value = 20000, message = "Your minimum target should be 20,000")
	private Integer amount;
	
	@Transient
	private String person;
	
	@Column(name = "MONTHLY_SAVINGS")
	@NotNull(message = "Montly Saving value is required")
	private Integer monthlySavings;
	
	@Column(name = "EVENT_DATE")
	@NotNull(message = "Event date is required")
	private String date;
	
	@NotNull(message = "Please Select a fund to invest for you goal")
	@Column(name = "CHOSEN_SCHEME_CODE")
	private String schemeCode;
	
	@Column(name = "CHOSEN_SCHEME_NAME")
	private String schemename;
	
	@Column(name="MOBILE")
	@NotNull(message = "Mobile is mandatory")
	private String mobile;
	
	@Column(name="PAN_NO")
	@NotNull(message = "PAN no is required")
	private String pan;
	
	@Transient
	private Long schemeId;
	
	@Column(name = "REQUESTING_SYSTEM_DETAILS")
	private String systemdetails;
	
	@Column(name = "CLIENT_BROWSER")
	private String clientbrowserdetails;

	public Integer getSerial() {
	    return serial;
	}

	public void setSerial(Integer serial) {
	    this.serial = serial;
	}
	
	public String getTransactionid() {
	    return transactionid;
	}

	public void setTransactionid(String transactionid) {
	    this.transactionid = transactionid;
	}

	public String getWishType() {
	    return wishType;
	}

	public void setWishType(String wishType) {
	    this.wishType = wishType;
	}

	public String getInvestType() {
	    return investType;
	}

	public void setInvestType(String investType) {
	    this.investType = investType;
	}

	public Integer getTenure() {
	    return tenure;
	}

	public void setTenure(Integer tenure) {
	    this.tenure = tenure;
	}

	public Integer getAmount() {
	    return amount;
	}

	public void setAmount(Integer amount) {
	    this.amount = amount;
	}

	public String getPerson() {
	    return person;
	}

	public void setPerson(String person) {
	    this.person = person;
	}

	public String getDate() {
	    return date;
	}

	public void setDate(String date) {
	    this.date = date;
	}

	public String getSchemeCode() {
	    return schemeCode;
	}

	public void setSchemeCode(String schemeCode) {
	    this.schemeCode = schemeCode;
	}

	public String getMobile() {
	    return mobile;
	}

	public void setMobile(String mobile) {
	    this.mobile = mobile;
	}

	public String getPan() {
	    return pan;
	}

	public void setPan(String pan) {
	    this.pan = pan;
	}

	public Long getSchemeId() {
	    return schemeId;
	}

	public void setSchemeId(Long schemeId) {
	    this.schemeId = schemeId;
	}

	public Integer getMonthlySavings() {
	    return monthlySavings;
	}

	public void setMonthlySavings(Integer monthlySavings) {
	    this.monthlySavings = monthlySavings;
	}

	public static long getSerialversionuid() {
	    return serialVersionUID;
	}

	public String getSystemdetails() {
	    return systemdetails;
	}

	public void setSystemdetails(String systemdetails) {
	    this.systemdetails = systemdetails;
	}

	public String getClientbrowserdetails() {
	    return clientbrowserdetails;
	}

	public void setClientbrowserdetails(String clientbrowserdetails) {
	    this.clientbrowserdetails = clientbrowserdetails;
	}

	public String getSchemename() {
	    return schemename;
	}

	public void setSchemename(String schemename) {
	    this.schemename = schemename;
	}

	public String getRegistryfundcode() {
	    return registryfundcode;
	}

	public void setRegistryfundcode(String registryfundcode) {
	    this.registryfundcode = registryfundcode;
	}

	public String getRegistryname() {
	    return registryname;
	}

	public void setRegistryname(String registryname) {
	    this.registryname = registryname;
	}
	
}
