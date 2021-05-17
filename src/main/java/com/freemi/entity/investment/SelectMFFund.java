package com.freemi.entity.investment;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Proxy;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="bsemf_transactions")
@Proxy(lazy=false)
public class SelectMFFund implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="SL_NO")
	private long serialNo;
	
	@NotEmpty(message="Client ID not found.")
	@Column(name="CLIENT_ID")
	private String clientID;
	
	@Column(name="PRODUCT_TYPE")
	private String producttype="MUTUAL_FUND";
	
	@Column(name="PRODUCT_REFERENCE_ID")
	private String productrefid;
	
	@NotEmpty(message="Scheme code is missing.")
	@Column(name="SCHEME_CODE")
	private String schemeCode;
	
	@Transient
	private String investorName;
	
	@Transient
//	@NotEmpty(message="Re-inv Scheme code is missing.")
	private String reinvSchemeCode;
	
	@Column(name="SCHEME_NAME")
	private String schemeName;
	
	@NotEmpty(message="Transaction ID missing. Retry transaction.")
	@Column(name="TRANSACTION_ID")
	private String transactionID;
	
	@Transient
	private String amcCode;
	
	@Transient
	private String amcimg;
	
	@Column(name="TRANSACTION_TYPE")
	private String transactionType="PURCHASE";
	
	@Column(name="INVEST_TYPE")
	private String investype="SIP";	//SIP or lumpsum
	
	@Column(name="SCHEME_OPTION_TYPE")
	private String schemeOptionType;
	
	@Column(name="SIP_FREQUENCY")
	private String sipfrequency;
	
	@Transient
	@Column(name="")
	private String schemeType;

	@NotNull(message= "Please provide investment amount") /* @Min(value = 100,message = "Minimum investment amount is Rs.100") */
	@Column(name="INVEST_AMOUNT")
	private double investAmount;
	
	@Transient
	private double redeemAmount;
	
	@Column(name="REDEEM_ALL")
	private String redeemAll="N";
	
	@Column(name="PAYMENT_METHOD")
	private String paymentMethod;
	
	@Column(name="SIP_DATE")
	private String sipDate;
	
	@Column(name="PORTFOLIO")
	private String portfolio;
	
	@Transient
//	@NotNull(message = "Mobile no is mandatory")
	private String mobile;
	
	@Transient
//	@NotNull(message = "PAN no is mandatory")
	private String pan;
	
	@Column(name="SIP_START_FROM")
	private Date sipStartDate;
	
	@Column(name="NO_OF_INSTALLMENTS")
	private int noOfInstallments;
	
	@Transient
	private int sipStartMonth;
	
	@Transient
	private int sipStartYear;
	
	@Column(name="PAYFIRSTINSTALLMENT")
	private boolean payFirstInstallment;
	
	@Transient
	private boolean eMandateRegRequired;
	
	@Column(name="ORDER_PLACE_TIME")
	private Date orderPlaceTime;
	
	@Column(name="BSE_REF_NO")
	private String bseRefNo;
	
	@Column(name="SIP_MANDATE_CATEGORY")
	private String mandateType="I";
	
	@Column(name="MANDATE_ID")
	private String mandateId;
	
	@Transient
	private String orderNo="";
	
	@Column(name="IS_ACTIVE")
	private String isActive="Y";
	
	@Transient
	private String invCategory="Z";
	
	@Transient
	private String buySellType="FRESH";
	
	@Transient
	private String rtaAgent="";
	
	@Column(name="CLIENT_IP")
	private String clientIp;
	
	@Column(name="CLIENT_BROWSER")
	private String clientBrowser;
	
	@Column(name="IS_ORDER_CANCELLED")
	private String ordercancelled;
	
	@Column(name="CANCEL_DATE")
	private Date sipcanceldate;
	
	@Column(name="CANCEL_RESPONSE")
	private String sipcancelapiresponse;
	
	@Column(name="CANCEL_SIP_UNIQUE_REF_NO")
	private String sipcancelrefid;

	public long getSerialNo() {
	    return serialNo;
	}

	public void setSerialNo(long serialNo) {
	    this.serialNo = serialNo;
	}

	public String getClientID() {
	    return clientID;
	}

	public void setClientID(String clientID) {
	    this.clientID = clientID;
	}

	public String getSchemeCode() {
	    return schemeCode;
	}

	public void setSchemeCode(String schemeCode) {
	    this.schemeCode = schemeCode;
	}

	public String getInvestorName() {
	    return investorName;
	}

	public void setInvestorName(String investorName) {
	    this.investorName = investorName;
	}

	public String getReinvSchemeCode() {
	    return reinvSchemeCode;
	}

	public void setReinvSchemeCode(String reinvSchemeCode) {
	    this.reinvSchemeCode = reinvSchemeCode;
	}

	public String getSchemeName() {
	    return schemeName;
	}

	public void setSchemeName(String schemeName) {
	    this.schemeName = schemeName;
	}

	public String getTransactionID() {
	    return transactionID;
	}

	public void setTransactionID(String transactionID) {
	    this.transactionID = transactionID;
	}

	public String getAmcCode() {
	    return amcCode;
	}

	public void setAmcCode(String amcCode) {
	    this.amcCode = amcCode;
	}

	public String getTransactionType() {
	    return transactionType;
	}

	public void setTransactionType(String transactionType) {
	    this.transactionType = transactionType;
	}

	public String getInvestype() {
	    return investype;
	}

	public void setInvestype(String investype) {
	    this.investype = investype;
	}
	
	public String getSipfrequency() {
	    return sipfrequency;
	}

	public void setSipfrequency(String sipfrequency) {
	    this.sipfrequency = sipfrequency;
	}

	public String getSchemeOptionType() {
	    return schemeOptionType;
	}

	public void setSchemeOptionType(String schemeOptionType) {
	    this.schemeOptionType = schemeOptionType;
	}

	public String getSchemeType() {
	    return schemeType;
	}

	public void setSchemeType(String schemeType) {
	    this.schemeType = schemeType;
	}

	public double getInvestAmount() {
	    return investAmount;
	}

	public void setInvestAmount(double investAmount) {
	    this.investAmount = investAmount;
	}

	public double getRedeemAmount() {
	    return redeemAmount;
	}

	public void setRedeemAmount(double redeemAmount) {
	    this.redeemAmount = redeemAmount;
	}

	public String getRedeemAll() {
	    return redeemAll;
	}

	public void setRedeemAll(String redeemAll) {
	    this.redeemAll = redeemAll;
	}

	public String getPaymentMethod() {
	    return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
	    this.paymentMethod = paymentMethod;
	}

	public String getSipDate() {
	    return sipDate;
	}

	public void setSipDate(String sipDate) {
	    this.sipDate = sipDate;
	}

	public String getPortfolio() {
	    return portfolio;
	}

	public void setPortfolio(String portfolio) {
	    this.portfolio = portfolio;
	}

	public String getMobile() {
	    return mobile;
	}

	public void setMobile(String mobile) {
	    this.mobile = mobile;
	}

	public String getPan() {
	    return pan;
	}

	public void setPan(String pan) {
	    this.pan = pan;
	}

	public Date getSipStartDate() {
	    return sipStartDate;
	}

	public void setSipStartDate(Date sipStartDate) {
	    this.sipStartDate = sipStartDate;
	}

	public int getNoOfInstallments() {
	    return noOfInstallments;
	}

	public void setNoOfInstallments(int noOfInstallments) {
	    this.noOfInstallments = noOfInstallments;
	}

	public int getSipStartMonth() {
	    return sipStartMonth;
	}

	public void setSipStartMonth(int sipStartMonth) {
	    this.sipStartMonth = sipStartMonth;
	}

	public int getSipStartYear() {
	    return sipStartYear;
	}

	public void setSipStartYear(int sipStartYear) {
	    this.sipStartYear = sipStartYear;
	}

	public boolean isPayFirstInstallment() {
	    return payFirstInstallment;
	}

	public void setPayFirstInstallment(boolean payFirstInstallment) {
	    this.payFirstInstallment = payFirstInstallment;
	}

	public boolean iseMandateRegRequired() {
	    return eMandateRegRequired;
	}

	public void seteMandateRegRequired(boolean eMandateRegRequired) {
	    this.eMandateRegRequired = eMandateRegRequired;
	}

	public Date getOrderPlaceTime() {
	    return orderPlaceTime;
	}

	public void setOrderPlaceTime(Date orderPlaceTime) {
	    this.orderPlaceTime = orderPlaceTime;
	}

	public String getBseRefNo() {
	    return bseRefNo;
	}

	public void setBseRefNo(String bseRefNo) {
	    this.bseRefNo = bseRefNo;
	}

	public String getMandateType() {
	    return mandateType;
	}

	public void setMandateType(String mandateType) {
	    this.mandateType = mandateType;
	}

	public String getMandateId() {
	    return mandateId;
	}

	public void setMandateId(String mandateId) {
	    this.mandateId = mandateId;
	}

	public String getOrderNo() {
	    return orderNo;
	}

	public void setOrderNo(String orderNo) {
	    this.orderNo = orderNo;
	}

	public String getIsActive() {
	    return isActive;
	}

	public void setIsActive(String isActive) {
	    this.isActive = isActive;
	}

	public String getInvCategory() {
	    return invCategory;
	}

	public void setInvCategory(String invCategory) {
	    this.invCategory = invCategory;
	}

	public String getBuySellType() {
	    return buySellType;
	}

	public void setBuySellType(String buySellType) {
	    this.buySellType = buySellType;
	}

	public String getRtaAgent() {
	    return rtaAgent;
	}

	public void setRtaAgent(String rtaAgent) {
	    this.rtaAgent = rtaAgent;
	}

	public String getClientIp() {
	    return clientIp;
	}

	public void setClientIp(String clientIp) {
	    this.clientIp = clientIp;
	}

	public String getClientBrowser() {
	    return clientBrowser;
	}

	public void setClientBrowser(String clientBrowser) {
	    this.clientBrowser = clientBrowser;
	}

	public String getOrdercancelled() {
	    return ordercancelled;
	}

	public void setOrdercancelled(String ordercancelled) {
	    this.ordercancelled = ordercancelled;
	}

	public Date getSipcanceldate() {
	    return sipcanceldate;
	}

	public void setSipcanceldate(Date sipcanceldate) {
	    this.sipcanceldate = sipcanceldate;
	}

	public String getSipcancelapiresponse() {
	    return sipcancelapiresponse;
	}

	public void setSipcancelapiresponse(String sipcancelapiresponse) {
	    this.sipcancelapiresponse = sipcancelapiresponse;
	}

	public String getSipcancelrefid() {
	    return sipcancelrefid;
	}

	public void setSipcancelrefid(String sipcancelrefid) {
	    this.sipcancelrefid = sipcancelrefid;
	}

	public static long getSerialversionuid() {
	    return serialVersionUID;
	}

	public String getProducttype() {
	    return producttype;
	}

	public void setProducttype(String producttype) {
	    this.producttype = producttype;
	}

	public String getProductrefid() {
	    return productrefid;
	}

	public void setProductrefid(String productrefid) {
	    this.productrefid = productrefid;
	}

	public String getAmcimg() {
		return amcimg;
	}

	public void setAmcimg(String amcimg) {
		this.amcimg = amcimg;
	}
	
	
    
}
