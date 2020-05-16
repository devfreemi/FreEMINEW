package com.freemi.entity.investment.mahindra;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MFDApplStatus {
	String applNum;
	String cpTransRefNum;
	String mfSysRefNum;
	Integer i1;
	Integer i2;
	Integer i3;
	Integer i4;
	String paymentComplete;
	String txnStatus;
	String date;


	public MFDApplStatus() {}
	
	public String getCpTransRefNum() {
		return cpTransRefNum;
	}


	public void setCpTransRefNum(String cpTransRefNum) {
		this.cpTransRefNum = cpTransRefNum;
	}


	public String getMfSysRefNum() {
		return mfSysRefNum;
	}


	public void setMfSysRefNum(String mfSysRefNum) {
		this.mfSysRefNum = mfSysRefNum;
	}


	public String getTxnStatus() {
		return txnStatus;
	}


	public void setTxnStatus(String txnStatus) {
		this.txnStatus = txnStatus;
	}

	public MFDApplStatus(
			String cpTransRefNum,
			String mfSysRefNum,
			String applNum,
			LocalDateTime date,
			String paymentComplete,
			String txnStatus) {
		this.cpTransRefNum = cpTransRefNum;
		this.mfSysRefNum = mfSysRefNum;
		this.applNum = applNum;
		this.date =  DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm").format(date);
		
		this.paymentComplete = paymentComplete;
		this.txnStatus = (txnStatus==null) ? "NA" : txnStatus;
	}

		
	

	public String getApplNum() {
		return applNum;
	}


	public void setApplNum(String applNum) {
		this.applNum = applNum;
	}


	public Integer getI1() {
		return i1;
	}


	public void setI1(Integer i1) {
		this.i1 = i1;
	}


	public Integer getI2() {
		return i2;
	}


	public void setI2(Integer i2) {
		this.i2 = i2;
	}


	public Integer getI3() {
		return i3;
	}


	public void setI3(Integer i3) {
		this.i3 = i3;
	}


	public Integer getI4() {
		return i4;
	}


	public void setI4(Integer i4) {
		this.i4 = i4;
	}


	public String getPaymentComplete() {
		return paymentComplete;
	}


	public void setPaymentComplete(String paymentComplete) {
		this.paymentComplete = paymentComplete;
	}


	


	
	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}

	public void setUploadInfo(
								Integer i1,
								Integer i2,
								Integer i3,
								Integer i4) {
		this.i1 = i1;
		this.i2 = i2;
		this.i3 = i3;
		this.i4 = i4;
	}
}
