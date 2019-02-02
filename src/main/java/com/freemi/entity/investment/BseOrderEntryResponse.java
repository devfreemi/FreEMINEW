package com.freemi.entity.investment;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bsemf_transactions_response")
public class BseOrderEntryResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="SL_NO")
	private long serial;
	
	@Column(name="UNIQUE_REF_NO")
	private String uniqueReferenceNo;
	
	@Column(name="INTERNAL_REF_NO")
	private String intRefNo;
	
	@Column(name="TRANSACTION_TYPE")
	private String transactionType;

	@Column(name="ORDER_OR_REG_NO")
	private String orderNoOrSipRegNo;
	
	@Column(name="USER_ID")
	private String userId;
	
	@Column(name="MEMBER_ID")
	private String memberId;
	
	@Column(name="CLIENT_ID")
	private String clientCode;
	
	@Column(name="BSE_REMARKS")
	private String bsereMarks;
	
	@Column(name="SUCCESS_FLAG")
	private String successFlag;
	
	@Column(name="CREATED_ON")
	private Date createdOn;
	
	@Column(name="CREATED_BY")
	private String createdBy;

	public long getSerial() {
		return serial;
	}

	public void setSerial(long serial) {
		this.serial = serial;
	}

	public String getIntRefNo() {
		return intRefNo;
	}

	public void setIntRefNo(String intRefNo) {
		this.intRefNo = intRefNo;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getUniqueReferenceNo() {
		return uniqueReferenceNo;
	}

	public void setUniqueReferenceNo(String uniqueReferenceNo) {
		this.uniqueReferenceNo = uniqueReferenceNo;
	}

	public String getOrderNoOrSipRegNo() {
		return orderNoOrSipRegNo;
	}

	public void setOrderNoOrSipRegNo(String orderNoOrSipRegNo) {
		this.orderNoOrSipRegNo = orderNoOrSipRegNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getClientCode() {
		return clientCode;
	}

	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}

	public String getBsereMarks() {
		return bsereMarks;
	}

	public void setBsereMarks(String bsereMarks) {
		this.bsereMarks = bsereMarks;
	}

	public String getSuccessFlag() {
		return successFlag;
	}

	public void setSuccessFlag(String successFlag) {
		this.successFlag = successFlag;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

}
