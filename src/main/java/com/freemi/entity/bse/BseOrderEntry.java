package com.freemi.entity.bse;

import java.io.Serializable;

public class BseOrderEntry implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
//	NEW/MOD/CXL
	private String TransCode;
	
//	YYYYMMDD<usercode>000001
	private String TransNo;
	private String OrderId="";
	private String UserID;
	private String MemberId;
	private String ClientCode;
	private String SchemeCd;
	
//	P/R
	private String BuySell;
	
//	FRESH/ADDITIONAL
	private String BuySellType;
	
//	C/N/P	Always P for us
	private String DPTxn="P";
	
	private double OrderVal=0.0;
	private String Qty="";
	private String AllRedeem="N";
	private String FolioNo="";
	private String Remarks="";
	private String KYCStatus="";
	private String RefNo="";
	private String SubBrCode="";
	private String EUIN="";
	private String EUINVal="";
	private String MinRedeem="";
	private String DPC="";
	private String IPAdd="";
	private String Password="";
	private String PassKey="";
	private String Parma1="";
	private String Param2="";
	private String Param3="";
	public String getTransCode() {
		return TransCode;
	}
	public void setTransCode(String transCode) {
		TransCode = transCode;
	}
	public String getTransNo() {
		return TransNo;
	}
	public void setTransNo(String transNo) {
		TransNo = transNo;
	}
	public String getOrderId() {
		return OrderId;
	}
	public void setOrderId(String orderId) {
		OrderId = orderId;
	}
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
		UserID = userID;
	}
	public String getMemberId() {
		return MemberId;
	}
	public void setMemberId(String memberId) {
		MemberId = memberId;
	}
	public String getClientCode() {
		return ClientCode;
	}
	public void setClientCode(String clientCode) {
		ClientCode = clientCode;
	}
	public String getSchemeCd() {
		return SchemeCd;
	}
	public void setSchemeCd(String schemeCd) {
		SchemeCd = schemeCd;
	}
	public String getBuySell() {
		return BuySell;
	}
	public void setBuySell(String buySell) {
		BuySell = buySell;
	}
	public String getBuySellType() {
		return BuySellType;
	}
	public void setBuySellType(String buySellType) {
		BuySellType = buySellType;
	}
	public String getDPTxn() {
		return DPTxn;
	}
	public void setDPTxn(String dPTxn) {
		DPTxn = dPTxn;
	}
	
	public double getOrderVal() {
		return OrderVal;
	}
	public void setOrderVal(double orderVal) {
		OrderVal = orderVal;
	}
	public String getQty() {
		return Qty;
	}
	public void setQty(String qty) {
		Qty = qty;
	}
	public String getAllRedeem() {
		return AllRedeem;
	}
	public void setAllRedeem(String allRedeem) {
		AllRedeem = allRedeem;
	}
	public String getFolioNo() {
		return FolioNo;
	}
	public void setFolioNo(String folioNo) {
		FolioNo = folioNo;
	}
	public String getRemarks() {
		return Remarks;
	}
	public void setRemarks(String remarks) {
		Remarks = remarks;
	}
	public String getKYCStatus() {
		return KYCStatus;
	}
	public void setKYCStatus(String kYCStatus) {
		KYCStatus = kYCStatus;
	}
	public String getRefNo() {
		return RefNo;
	}
	public void setRefNo(String refNo) {
		RefNo = refNo;
	}
	public String getSubBrCode() {
		return SubBrCode;
	}
	public void setSubBrCode(String subBrCode) {
		SubBrCode = subBrCode;
	}
	public String getEUIN() {
		return EUIN;
	}
	public void setEUIN(String eUIN) {
		EUIN = eUIN;
	}
	public String getEUINVal() {
		return EUINVal;
	}
	public void setEUINVal(String eUINVal) {
		EUINVal = eUINVal;
	}
	public String getMinRedeem() {
		return MinRedeem;
	}
	public void setMinRedeem(String minRedeem) {
		MinRedeem = minRedeem;
	}
	public String getDPC() {
		return DPC;
	}
	public void setDPC(String dPC) {
		DPC = dPC;
	}
	public String getIPAdd() {
		return IPAdd;
	}
	public void setIPAdd(String iPAdd) {
		IPAdd = iPAdd;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getPassKey() {
		return PassKey;
	}
	public void setPassKey(String passKey) {
		PassKey = passKey;
	}
	public String getParma1() {
		return Parma1;
	}
	public void setParma1(String parma1) {
		Parma1 = parma1;
	}
	public String getParam2() {
		return Param2;
	}
	public void setParam2(String param2) {
		Param2 = param2;
	}
	public String getParam3() {
		return Param3;
	}
	public void setParam3(String param3) {
		Param3 = param3;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
