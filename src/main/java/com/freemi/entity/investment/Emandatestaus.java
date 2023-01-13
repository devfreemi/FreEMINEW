package com.freemi.entity.investment;

import java.io.Serializable;

public class Emandatestaus implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String mandateid;
	
	private String clientcode;
	
	private Double amount;
	
	private String approveddate;
	
	private String backaccountno;
	
	private String bankbranch;
	
	private String clientname;
	
	private String collectiontype;
	
	private String mandatetype;
	
	private String memebercode;
	
	private String registrationdate;
	
	private String remarks;
	
	private String status;
	
	private String umrnno;
	
	private String uploaddate;
	
	private String requeststatus;
	
	private String requestmsg;

	public String getMandateid() {
		return mandateid;
	}

	public void setMandateid(String mandateid) {
		this.mandateid = mandateid;
	}

	public String getClientcode() {
		return clientcode;
	}

	public void setClientcode(String clientcode) {
		this.clientcode = clientcode;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getApproveddate() {
		return approveddate;
	}

	public void setApproveddate(String approveddate) {
		this.approveddate = approveddate;
	}

	public String getBackaccountno() {
		return backaccountno;
	}

	public void setBackaccountno(String backaccountno) {
		this.backaccountno = backaccountno;
	}

	public String getBankbranch() {
		return bankbranch;
	}

	public void setBankbranch(String bankbranch) {
		this.bankbranch = bankbranch;
	}

	public String getClientname() {
		return clientname;
	}

	public void setClientname(String clientname) {
		this.clientname = clientname;
	}

	public String getCollectiontype() {
		return collectiontype;
	}

	public void setCollectiontype(String collectiontype) {
		this.collectiontype = collectiontype;
	}

	public String getMandatetype() {
		return mandatetype;
	}

	public void setMandatetype(String mandatetype) {
		this.mandatetype = mandatetype;
	}

	public String getMemebercode() {
		return memebercode;
	}

	public void setMemebercode(String memebercode) {
		this.memebercode = memebercode;
	}

	public String getRegistrationdate() {
		return registrationdate;
	}

	public void setRegistrationdate(String registrationdate) {
		this.registrationdate = registrationdate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUmrnno() {
		return umrnno;
	}

	public void setUmrnno(String umrnno) {
		this.umrnno = umrnno;
	}

	public String getUploaddate() {
		return uploaddate;
	}

	public void setUploaddate(String uploaddate) {
		this.uploaddate = uploaddate;
	}
	public String getRequeststatus() {
		return requeststatus;
	}

	public void setRequeststatus(String requeststatus) {
		this.requeststatus = requeststatus;
	}

	public String getRequestmsg() {
		return requestmsg;
	}

	public void setRequestmsg(String requestmsg) {
		this.requestmsg = requestmsg;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
