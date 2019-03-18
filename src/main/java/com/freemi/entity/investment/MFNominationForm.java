package com.freemi.entity.investment;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name="bsemf_customers_nominee")
@Proxy(lazy=false)
public class MFNominationForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="SL_NO")
	private long serialNo;
	
	@Column(name="CLIENT_ID")
	private String clientID;
	
	@Column(name="")
	private String isNominate="N";
	
	@Column(name="")
	private String nomineeName="";
	
	@Transient
	private String nomineeAddress1="";
	
	@Transient
	private String nomineeAddress2="";
	
	@Transient
	private String nomineeState="";
	
	@Transient
	private String nomineeCity="";
	
	@Transient
	private String nomineeCountry="";
	
	@Transient
	private String nomineePercentage="";
	
	@Transient
	private String nomineeDOB="";
	
	@Column(name="")
	private String nomineeRelation="";
	
	@Transient
	private String isNomineeMinor="N";
	
	@Transient
	private String nomineeGuardian="";
	
	@OneToOne(fetch= FetchType.LAZY, optional=false, cascade=CascadeType.PERSIST)
	@JoinColumn(name="CLIENT_ID", nullable= false,insertable=false,updatable=false)
	private BseMFInvestForm mfForm;
	
	
	
	public long getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(long serialNo) {
		this.serialNo = serialNo;
	}
	public String getClientID() {
		return clientID;
	}
	public void setClientID(String clientID) {
		this.clientID = clientID;
	}
	public BseMFInvestForm getMfForm() {
		return mfForm;
	}
	public void setMfForm(BseMFInvestForm mfForm) {
		this.mfForm = mfForm;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getIsNominate() {
		return isNominate;
	}
	public void setIsNominate(String isNominate) {
		this.isNominate = isNominate;
	}
	public String getNomineeName() {
		return nomineeName;
	}
	public void setNomineeName(String nomineeName) {
		this.nomineeName = nomineeName;
	}
	
	public String getNomineeDOB() {
		return nomineeDOB;
	}
	public void setNomineeDOB(String nomineeDOB) {
		this.nomineeDOB = nomineeDOB;
	}
	public String getNomineeRelation() {
		return nomineeRelation;
	}
	public void setNomineeRelation(String nomineeRelation) {
		this.nomineeRelation = nomineeRelation;
	}
	public String getIsNomineeMinor() {
		return isNomineeMinor;
	}
	public void setIsNomineeMinor(String isNomineeMinor) {
		this.isNomineeMinor = isNomineeMinor;
	}
	public String getNomineeGuardian() {
		return nomineeGuardian;
	}
	public void setNomineeGuardian(String nomineeGuardian) {
		this.nomineeGuardian = nomineeGuardian;
	}
	public String getNomineeAddress1() {
		return nomineeAddress1;
	}
	public void setNomineeAddress1(String nomineeAddress1) {
		this.nomineeAddress1 = nomineeAddress1;
	}
	public String getNomineeAddress2() {
		return nomineeAddress2;
	}
	public void setNomineeAddress2(String nomineeAddress2) {
		this.nomineeAddress2 = nomineeAddress2;
	}
	public String getNomineeState() {
		return nomineeState;
	}
	public void setNomineeState(String nomineeState) {
		this.nomineeState = nomineeState;
	}
	public String getNomineeCity() {
		return nomineeCity;
	}
	public void setNomineeCity(String nomineeCity) {
		this.nomineeCity = nomineeCity;
	}
	public String getNomineeCountry() {
		return nomineeCountry;
	}
	public void setNomineeCountry(String nomineeCountry) {
		this.nomineeCountry = nomineeCountry;
	}
	public String getNomineePercentage() {
		return nomineePercentage;
	}
	public void setNomineePercentage(String nomineePercentage) {
		this.nomineePercentage = nomineePercentage;
	}
	
	
	
}
