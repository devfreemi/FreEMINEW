package com.freemi.entity.investment;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name="customer_ekyc_status")
@Proxy(lazy=false)
public class PanValidationStatus implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private int ekycId;
	
	@Column(name="PAN_NO")
	private String panNumber;
	
	@Column(name="NAME_OF_PANHOLDER")
	private String panHolderName;
	
	@Column(name="INVESTOR_EMAIL")
	private String panHolderEmail;
	
	@Column(name="FACTA_VERIFIED_STATUS")
	private String isFactaVerified;
	
	@Column(name="KYC_VERIFIED_STATUS")
	private String isKYCVerified;
	
	@Column(name="IS_EXISTING_CUSTOMER")
	private String isExistingCustomer;
	
	@Transient
	@Column(insertable = false, updatable = false)
	private String message=null;
	
	@Transient
	@Column(insertable = false, updatable = false)
	private String returnCode;
	
	@Column(name="REQUESTOR_IP")
	private String requestorIp;

	@Column(name="REQUESTOR_OS")
	private String requestorOs;

	@Column(name="VERIFIED_FROM")
	private int verifedFrom;

	@Temporal(TemporalType.DATE)
	@Column(name="VERIFICATION_DATE")
	private Date verificationDate;
	
	
	
	public String getPanNumber() {
		return panNumber;
	}
	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}
	public String getPanHolderName() {
		return panHolderName;
	}
	public void setPanHolderName(String panHolderName) {
		this.panHolderName = panHolderName;
	}
	public String getPanHolderEmail() {
		return panHolderEmail;
	}
	public void setPanHolderEmail(String panHolderEmail) {
		this.panHolderEmail = panHolderEmail;
	}
	public String getIsFactaVerified() {
		return isFactaVerified;
	}
	public void setIsFactaVerified(String isFactaVerified) {
		this.isFactaVerified = isFactaVerified;
	}
	public String getIsKYCVerified() {
		return isKYCVerified;
	}
	public void setIsKYCVerified(String isKYCVerified) {
		this.isKYCVerified = isKYCVerified;
	}
	public String getIsExistingCustomer() {
		return isExistingCustomer;	
	}
	public void setIsExistingCustomer(String isExistingCustomer) {
		this.isExistingCustomer = isExistingCustomer;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	public int getEkycId() {
		return ekycId;
	}
	public void setEkycId(int ekycId) {
		this.ekycId = ekycId;
	}
	public String getRequestorIp() {
		return requestorIp;
	}
	public void setRequestorIp(String requestorIp) {
		this.requestorIp = requestorIp;
	}
	public String getRequestorOs() {
		return requestorOs;
	}
	public void setRequestorOs(String requestorOs) {
		this.requestorOs = requestorOs;
	}
	
	public int getVerifedFrom() {
		return verifedFrom;
	}
	public void setVerifedFrom(int verifedFrom) {
		this.verifedFrom = verifedFrom;
	}
	public Date getVerificationDate() {
		return verificationDate;
	}
	public void setVerificationDate(Date verificationDate) {
		this.verificationDate = verificationDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
