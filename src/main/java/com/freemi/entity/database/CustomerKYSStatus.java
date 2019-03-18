package com.freemi.entity.database;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Proxy;

import java.util.Date;


/**
 * The persistent class for the customer_ekyc_status database table.
 * 
 */
@Entity
@Table(name="customer_ekyc_status")
@Proxy(lazy=false)
public class CustomerKYSStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ekyc_id")
	private int ekycId;

	@Column(name="FACTA_VERIFIED_STATUS")
	private char factaVerifiedStatus;

	@Column(name="IMEI_NUMBER")
	private String imeiNumber;

	@Column(name="KYC_VERIFIED_STATUS")
	private char kycVerifiedStatus;

	@Column(name="NAME_OF_PANHOLDER")
	private String nameOfPanholder;

	@Column(name="PAN_NO")
	private String panNo;

	@Column(name="REQUESTOR_IP")
	private String requestorIp;

	@Column(name="REQUESTOR_OS")
	private String requestorOs;

	@Column(name="VERIFIED_FROM")
	private String verifedFrom;

	@Temporal(TemporalType.DATE)
	@Column(name="VERIFICATION_DATE")
	private Date verificationDate;

	public CustomerKYSStatus() {
	}

	public int getEkycId() {
		return this.ekycId;
	}

	public void setEkycId(int ekycId) {
		this.ekycId = ekycId;
	}

	public char getFactaVerifiedStatus() {
		return this.factaVerifiedStatus;
	}

	public void setFactaVerifiedStatus(char factaVerifiedStatus) {
		this.factaVerifiedStatus = factaVerifiedStatus;
	}

	public String getImeiNumber() {
		return this.imeiNumber;
	}

	public void setImeiNumber(String imeiNumber) {
		this.imeiNumber = imeiNumber;
	}

	public char getKycVerifiedStatus() {
		return this.kycVerifiedStatus;
	}

	public void setKycVerifiedStatus(char kycVerifiedStatus) {
		this.kycVerifiedStatus = kycVerifiedStatus;
	}

	public String getNameOfPanholder() {
		return this.nameOfPanholder;
	}

	public void setNameOfPanholder(String nameOfPanholder) {
		this.nameOfPanholder = nameOfPanholder;
	}

	public String getPanNo() {
		return this.panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	public String getRequestorIp() {
		return requestorIp;
	}

	public void setRequestorIp(String requestorIp) {
		this.requestorIp = requestorIp;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getRequestorOs() {
		return this.requestorOs;
	}

	public void setRequestorOs(String requestorOs) {
		this.requestorOs = requestorOs;
	}

	public String getVerifedFrom() {
		return this.verifedFrom;
	}

	public void setVerifedFrom(String verifedFrom) {
		this.verifedFrom = verifedFrom;
	}

	public Date getVerificationDate() {
		return this.verificationDate;
	}

	public void setVerificationDate(Date verificationDate) {
		this.verificationDate = verificationDate;
	}

}