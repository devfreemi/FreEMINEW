package com.freemi.entity.loan;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TrackloanEnquiryResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("CR_CC_COMMENTS")
	private String crcccomments="";
	
	@JsonProperty("MONTHLY_INCOME")
	private String monthlyincome="";
	
	@JsonProperty("ONHOLD_STATUS")
	private String onholdstatus="";
	
	@JsonProperty("responseCode")
	private String responsecode="";
	
	@JsonProperty("i_errorMsg")
	private String ierrormsg="";
	
	@JsonProperty("CUSTNAME")
	private String customername="";
	
	@JsonProperty("LOANAMT")
	private String loanamount="";
	
	@JsonProperty("POSIDEX_DEDUPE_STATUS")
	private String posidexdedupestatus="";
	
	@JsonProperty("ADDITIONAL_DOCS")
	private String additionaldoc="";
	
	@JsonProperty("CUSTOMER_CATEGORY")
	private String customercategory="";
	
	@JsonProperty("timestamp")
	private String timestamp="";
	
	@JsonProperty("POST_DOC")
	private String postdoc="";
	
	@JsonProperty("Filler1")
	private String filler1="";
	
	@JsonProperty("LOSNO")
	private String losno="";
	
	@JsonProperty("i_errorcode")
	private String errorcode="";
	
	@JsonProperty("Filler2")
	private String filler2="";
	
	@JsonProperty("Filler3")
	private String filler3="";
	
	@JsonProperty("ParentDocMaster")
	private TrackloanParentDocMaster parentdocmaster;
	
	@JsonProperty("ONHOLD_REASON")
	private String onholdreason="";
	
	@JsonProperty("Parent_Doc_flag")
	private String parentdocflag="";
	
	@JsonProperty("responseDesc")
	private String responsedesc="";
	
	@JsonProperty("strProductName")
	private String strproductname="";
	
	@JsonProperty("DISCREPENCY")
	private String discrepancy="";
	
	@JsonProperty("Parent_DocStatusMst")
	private TrackloanParentdocStatusMaster parentdocstatusmaster;
	
	@JsonProperty("strRefNo")
	private String strrefno="";
	
	@JsonProperty("Parent_DocStatus_flag")
	private String parentdocstatusflag="";
	
	@JsonProperty("CREDIT_STATUS")
	private String creditstatus="";
	
	@JsonProperty("CPV_STATUS")
	private String cpvstatus="";
	
	@JsonProperty("ACTIVITY")
	private String activity="";
	
	@JsonProperty("EMIAAMT")
	private String emiamount="";
	
	@JsonProperty("status")
	private String status="";

	public String getCrcccomments() {
		return crcccomments;
	}

	public void setCrcccomments(String crcccomments) {
		this.crcccomments = crcccomments;
	}

	public String getMonthlyincome() {
		return monthlyincome;
	}

	public void setMonthlyincome(String monthlyincome) {
		this.monthlyincome = monthlyincome;
	}

	public String getOnholdstatus() {
		return onholdstatus;
	}

	public void setOnholdstatus(String onholdstatus) {
		this.onholdstatus = onholdstatus;
	}

	public String getResponsecode() {
		return responsecode;
	}

	public void setResponsecode(String responsecode) {
		this.responsecode = responsecode;
	}

	public String getIerrormsg() {
		return ierrormsg;
	}

	public void setIerrormsg(String ierrormsg) {
		this.ierrormsg = ierrormsg;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public String getLoanamount() {
		return loanamount;
	}

	public void setLoanamount(String loanamount) {
		this.loanamount = loanamount;
	}

	public String getPosidexdedupestatus() {
		return posidexdedupestatus;
	}

	public void setPosidexdedupestatus(String posidexdedupestatus) {
		this.posidexdedupestatus = posidexdedupestatus;
	}

	public String getAdditionaldoc() {
		return additionaldoc;
	}

	public void setAdditionaldoc(String additionaldoc) {
		this.additionaldoc = additionaldoc;
	}

	public String getCustomercategory() {
		return customercategory;
	}

	public void setCustomercategory(String customercategory) {
		this.customercategory = customercategory;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getPostdoc() {
		return postdoc;
	}

	public void setPostdoc(String postdoc) {
		this.postdoc = postdoc;
	}

	public String getFiller1() {
		return filler1;
	}

	public void setFiller1(String filler1) {
		this.filler1 = filler1;
	}

	public String getLosno() {
		return losno;
	}

	public void setLosno(String losno) {
		this.losno = losno;
	}

	public String getErrorcode() {
		return errorcode;
	}

	public void setErrorcode(String errorcode) {
		this.errorcode = errorcode;
	}

	public String getFiller2() {
		return filler2;
	}

	public void setFiller2(String filler2) {
		this.filler2 = filler2;
	}

	public String getFiller3() {
		return filler3;
	}

	public void setFiller3(String filler3) {
		this.filler3 = filler3;
	}

	public TrackloanParentDocMaster getParentdocmaster() {
		return parentdocmaster;
	}

	public void setParentdocmaster(TrackloanParentDocMaster parentdocmaster) {
		this.parentdocmaster = parentdocmaster;
	}

	public String getOnholdreason() {
		return onholdreason;
	}

	public void setOnholdreason(String onholdreason) {
		this.onholdreason = onholdreason;
	}

	public String getParentdocflag() {
		return parentdocflag;
	}

	public void setParentdocflag(String parentdocflag) {
		this.parentdocflag = parentdocflag;
	}

	public String getResponsedesc() {
		return responsedesc;
	}

	public void setResponsedesc(String responsedesc) {
		this.responsedesc = responsedesc;
	}

	public String getStrproductname() {
		return strproductname;
	}

	public void setStrproductname(String strproductname) {
		this.strproductname = strproductname;
	}

	public String getDiscrepancy() {
		return discrepancy;
	}

	public void setDiscrepancy(String discrepancy) {
		this.discrepancy = discrepancy;
	}

	public TrackloanParentdocStatusMaster getParentdocstatusmaster() {
		return parentdocstatusmaster;
	}

	public void setParentdocstatusmaster(TrackloanParentdocStatusMaster parentdocstatusmaster) {
		this.parentdocstatusmaster = parentdocstatusmaster;
	}

	public String getStrrefno() {
		return strrefno;
	}

	public void setStrrefno(String strrefno) {
		this.strrefno = strrefno;
	}

	public String getParentdocstatusflag() {
		return parentdocstatusflag;
	}

	public void setParentdocstatusflag(String parentdocstatusflag) {
		this.parentdocstatusflag = parentdocstatusflag;
	}

	public String getCreditstatus() {
		return creditstatus;
	}

	public void setCreditstatus(String creditstatus) {
		this.creditstatus = creditstatus;
	}

	public String getCpvstatus() {
		return cpvstatus;
	}

	public void setCpvstatus(String cpvstatus) {
		this.cpvstatus = cpvstatus;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getEmiamount() {
		return emiamount;
	}

	public void setEmiamount(String emiamount) {
		this.emiamount = emiamount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
