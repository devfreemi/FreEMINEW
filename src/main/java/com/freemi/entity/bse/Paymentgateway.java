package com.freemi.entity.bse;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Paymentgateway implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("LoginId")
	private String loginid="";
	
	@JsonProperty("Password")
	private String password="";
	
	@JsonProperty("membercode")
	private String membercode="";
	
	@JsonProperty("clientcode")
	private String clientcode="";
	
	@JsonProperty("modeofpayment")
	private String modeofpayment="";
	
	@JsonProperty("bankid")
	private String bankid="";
	
	@JsonProperty("accountnumber")
	private String accountno="";
	
	@JsonProperty("ifsc")
	private String ifsc="";
	
	@JsonProperty("ordernumber")
	private String orderno="";
	
	@JsonProperty("totalamount")
	private String totalamount="";
	
	@JsonProperty("internalrefno")
	private String internalrefno="";
	
	@JsonProperty("NEFTreference")
	private String neftref="";
	
	@JsonProperty("mandateid")
	private String mandateid="";
	
	@JsonProperty("vpaid")
	private String vpaid="";
	
	@JsonProperty("loopbackURL")
	private String loopbackurl="";
	
	@JsonProperty("allowloopBack")
	private String allowloopback="";
	
	@JsonProperty("filler1")
	private String filler1="";
	
	@JsonProperty("filler2")
	private String filler2="";
	
	@JsonProperty("filler3")
	private String filler3="";
	
	@JsonProperty("filler4")
	private String filler4="";
	
	@JsonProperty("filler5")
	private String filler5="";

	public String getLoginid() {
		return loginid;
	}

	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMembercode() {
		return membercode;
	}

	public void setMembercode(String membercode) {
		this.membercode = membercode;
	}

	public String getClientcode() {
		return clientcode;
	}

	public void setClientcode(String clientcode) {
		this.clientcode = clientcode;
	}

	public String getModeofpayment() {
		return modeofpayment;
	}

	public void setModeofpayment(String modeofpayment) {
		this.modeofpayment = modeofpayment;
	}

	public String getBankid() {
		return bankid;
	}

	public void setBankid(String bankid) {
		this.bankid = bankid;
	}

	public String getAccountno() {
		return accountno;
	}

	public void setAccountno(String accountno) {
		this.accountno = accountno;
	}

	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	public String getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(String totalamount) {
		this.totalamount = totalamount;
	}

	public String getInternalrefno() {
		return internalrefno;
	}

	public void setInternalrefno(String internalrefno) {
		this.internalrefno = internalrefno;
	}

	public String getNeftref() {
		return neftref;
	}

	public void setNeftref(String neftref) {
		this.neftref = neftref;
	}

	public String getMandateid() {
		return mandateid;
	}

	public void setMandateid(String mandateid) {
		this.mandateid = mandateid;
	}

	public String getVpaid() {
		return vpaid;
	}

	public void setVpaid(String vpaid) {
		this.vpaid = vpaid;
	}

	public String getLoopbackurl() {
		return loopbackurl;
	}

	public void setLoopbackurl(String loopbackurl) {
		this.loopbackurl = loopbackurl;
	}

	public String getAllowloopback() {
		return allowloopback;
	}

	public void setAllowloopback(String allowloopback) {
		this.allowloopback = allowloopback;
	}

	public String getFiller1() {
		return filler1;
	}

	public void setFiller1(String filler1) {
		this.filler1 = filler1;
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

	public String getFiller4() {
		return filler4;
	}

	public void setFiller4(String filler4) {
		this.filler4 = filler4;
	}

	public String getFiller5() {
		return filler5;
	}

	public void setFiller5(String filler5) {
		this.filler5 = filler5;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
