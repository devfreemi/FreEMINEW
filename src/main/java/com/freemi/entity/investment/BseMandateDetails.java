package com.freemi.entity.investment;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;


@Entity
@Table(name="bsemf_mandate_registration")
@Proxy(lazy=false)
public class BseMandateDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="SL_NO")
	private int serial;
	
	@Column(name="MANDATE_TYPE")
	private String mandateType="";
	
	@Column(name="CLIENT_CODE")
	private String clientCode="";
	
	@Column(name="BANK_ACC_NO")
	private String accountNumber="";
	
	@Column(name="IFSC_CODE")
	private String ifscCode;
	
	@Column(name="SIP_START_DATE")
	private Date sipStartDate;
	
	@Column(name="SIP_END_DATE")
	private Date sipEndDate;
	
	@Column(name="SIP_AMOUNT")
	private String amount;
	
	@Column(name="MANDATE_REGISTRATION_STATUS")
	private boolean mandateComplete=false;
	
	@Column(name="MANDATE_REGISTRATION_REF_NO")
	private String mandateId="";
	
	@Column(name="MANDATE_REGISTRATION_RESPONSE")
	private String mandateResponse;
	
	@Column(name="CREATED_BY")
	private String createdBy;
	
	@Column(name="CREATION_DATE")
	private Date creationDate;
	
	@Column(name="UPDATED_BY")
	private String updatedBy;
	
	@Column(name="UPDATE_TIME")
	private String updateTime;
	
	@Column(name="MANDATE_ACTIVE")
	private String mandateActive="Y";

	public int getSerial() {
		return serial;
	}

	public void setSerial(int serial) {
		this.serial = serial;
	}

	public String getClientCode() {
		return clientCode;
	}

	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Date getSipStartDate() {
		return sipStartDate;
	}

	public void setSipStartDate(Date sipStartDate) {
		this.sipStartDate = sipStartDate;
	}

	public Date getSipEndDate() {
		return sipEndDate;
	}

	public void setSipEndDate(Date sipEndDate) {
		this.sipEndDate = sipEndDate;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}


	public boolean isMandateComplete() {
		return mandateComplete;
	}

	public void setMandateComplete(boolean mandateComplete) {
		this.mandateComplete = mandateComplete;
	}

	public String getMandateId() {
		return mandateId;
	}

	public void setMandateId(String mandateId) {
		this.mandateId = mandateId;
	}

	public String getMandateResponse() {
		return mandateResponse;
	}

	public void setMandateResponse(String mandateResponse) {
		this.mandateResponse = mandateResponse;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getMandateType() {
		return mandateType;
	}

	public void setMandateType(String mandateType) {
		this.mandateType = mandateType;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getMandateActive() {
		return mandateActive;
	}

	public void setMandateActive(String mandateActive) {
		this.mandateActive = mandateActive;
	}
	
	

}
