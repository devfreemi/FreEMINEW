package com.freemi.entity.investment.mahindra;

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
@Table(name="mahindra_fd_saveleaddetails")
@Proxy(lazy=false)
public class MahindraFDSaveLeadDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "SL_NO")
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long serial;
	
	@Column(name = "CUSTOMER_REF_ID")
	private String customerId;
	
	@Column(name = "MOBILE")
	private String mobile;
	
	@Transient
	private String fullName;
	
	@Transient
	private String dob;
	
	@Transient
	private String emailId;
	
	@Transient
	private String salutation;
	
	@Transient
	private String pan;
	
	@Column(name = "SAVE_AMOUNT")
	private Integer saveAmount; 
	
	@Column(name="CP_LOCATION_CODE")
	private String cpLocationCode="";
	
	@Column(name="LEAD_TYPE")
	private String leadType="";
	
	@Column(name="SCP_CODE")
	private String scpCode="";
	
	@Column(name="REF_TYPE")
	private String refType="";
	
	@Column(name="REF_CUST_CODE")
	private String refCustCode="";
	
	@Column(name="REF_RM_CODE")
	private String refRmCode="";
	
	@Column(name="REF_OTHER_CODE")
	private String refOtherCode="";
	
	@Column(name="CP_TRANS_REF_NO")
	private String cpTransRefNo="";
	
	@Column(name="MF_SYS_REF_NO")
	private String mfSysRefNo="";
	
	@Column(name="API_CALL_COMPLETE")
	private String apiCallComplete="N";
	
	@Column(name="UPDATED_BY")
	private String updatedBy="PORTAL_TRANSACTION";

	public Long getSerial() {
		return serial;
	}

	public void setSerial(Long serial) {
		this.serial = serial;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getSalutation() {
		return salutation;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public Integer getSaveAmount() {
		return saveAmount;
	}

	public void setSaveAmount(Integer saveAmount) {
		this.saveAmount = saveAmount;
	}

	public String getCpLocationCode() {
		return cpLocationCode;
	}

	public void setCpLocationCode(String cpLocationCode) {
		this.cpLocationCode = cpLocationCode;
	}

	public String getLeadType() {
		return leadType;
	}

	public void setLeadType(String leadType) {
		this.leadType = leadType;
	}

	public String getScpCode() {
		return scpCode;
	}

	public void setScpCode(String scpCode) {
		this.scpCode = scpCode;
	}

	public String getRefType() {
		return refType;
	}

	public void setRefType(String refType) {
		this.refType = refType;
	}

	public String getRefCustCode() {
		return refCustCode;
	}

	public void setRefCustCode(String refCustCode) {
		this.refCustCode = refCustCode;
	}

	public String getRefRmCode() {
		return refRmCode;
	}

	public void setRefRmCode(String refRmCode) {
		this.refRmCode = refRmCode;
	}

	public String getRefOtherCode() {
		return refOtherCode;
	}

	public void setRefOtherCode(String refOtherCode) {
		this.refOtherCode = refOtherCode;
	}

	public String getCpTransRefNo() {
		return cpTransRefNo;
	}

	public void setCpTransRefNo(String cpTransRefNo) {
		this.cpTransRefNo = cpTransRefNo;
	}

	public String getMfSysRefNo() {
		return mfSysRefNo;
	}

	public void setMfSysRefNo(String mfSysRefNo) {
		this.mfSysRefNo = mfSysRefNo;
	}

	public String getApiCallComplete() {
		return apiCallComplete;
	}

	public void setApiCallComplete(String apiCallComplete) {
		this.apiCallComplete = apiCallComplete;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

}
