package com.freemi.entity.bse;

import java.io.Serializable;

public class BseEMandateRegistration implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ClientCode="";
	private String Amount="";
	private String MandateType="";
	private String AccountNo="";
	private String AccType="";
	private String IFSCCODE="";
	private String MICRCODE="";
	private String STARTDATE="";
	private String ENDDATE="";
	
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getClientCode() {
		return ClientCode;
	}
	public void setClientCode(String clientCode) {
		ClientCode = clientCode;
	}
	public String getAmount() {
		return Amount;
	}
	public void setAmount(String amount) {
		Amount = amount;
	}
	public String getMandateType() {
		return MandateType;
	}
	public void setMandateType(String mandateType) {
		MandateType = mandateType;
	}
	public String getAccountNo() {
		return AccountNo;
	}
	public void setAccountNo(String accountNo) {
		AccountNo = accountNo;
	}
	public String getAccType() {
		return AccType;
	}
	public void setAccType(String accType) {
		AccType = accType;
	}
	public String getIFSCCODE() {
		return IFSCCODE;
	}
	public void setIFSCCODE(String iFSCCODE) {
		IFSCCODE = iFSCCODE;
	}
	public String getMICRCODE() {
		return MICRCODE;
	}
	public void setMICRCODE(String mICRCODE) {
		MICRCODE = mICRCODE;
	}
	public String getSTARTDATE() {
		return STARTDATE;
	}
	public void setSTARTDATE(String sTARTDATE) {
		STARTDATE = sTARTDATE;
	}
	public String getENDDATE() {
		return ENDDATE;
	}
	public void setENDDATE(String eNDDATE) {
		ENDDATE = eNDDATE;
	}
	
	
}
