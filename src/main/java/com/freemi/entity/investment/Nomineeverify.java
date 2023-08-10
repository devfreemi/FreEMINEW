package com.freemi.entity.investment;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="bsemf_customers_nominee_registration")
@Proxy(lazy=false)
public class Nomineeverify implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="SL_NO")
	@JsonIgnore
	private long serialNo;
	
	@Column(name="CLIENT_ID")
	private String clientID;
	
	@Column(name="NOMINEEAUTHMODE")
	private String authmode;
	
	@Column(name="NOMINEEOPT")
	private String nomineeopt;
	
	@Column(name="NOMINEE_REGISTRATION_TRIGGERED")
	private String nomineeverifytrigger;
	
	@Column(name="NOMINEE_REGISTRATION_STATUS_CODE")
	private String nomineeverifystatus;
	
	@Column(name="NOMINEE_REGISTRATION_REMARKS")
	private String remarks;

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

	public String getAuthmode() {
		return authmode;
	}

	public void setAuthmode(String authmode) {
		this.authmode = authmode;
	}

	public String getNomineeopt() {
		return nomineeopt;
	}

	public void setNomineeopt(String nomineeopt) {
		this.nomineeopt = nomineeopt;
	}

	public String getNomineeverifytrigger() {
		return nomineeverifytrigger;
	}

	public void setNomineeverifytrigger(String nomineeverifytrigger) {
		this.nomineeverifytrigger = nomineeverifytrigger;
	}

	public String getNomineeverifystatus() {
		return nomineeverifystatus;
	}

	public void setNomineeverifystatus(String nomineeverifystatus) {
		this.nomineeverifystatus = nomineeverifystatus;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
