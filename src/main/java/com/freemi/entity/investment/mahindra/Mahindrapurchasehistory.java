package com.freemi.entity.investment.mahindra;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name="mahindra_purchase_history")
@Proxy(lazy=false)
public class Mahindrapurchasehistory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="CUSTOMER_REF_ID")
	private String customerid;
	
	@Column(name="MOBILE")
	private String mobile;
	
	@Id
	@Column(name="CP_TRANS_REF_NO")
	private String transactionrefid;
	
	@Column(name="SCHEME_CODE")
	private String schemecode;
	
	@Column(name="TRANSACTION_TYPE")
	private String transactiontype;
	
	@Column(name="CATEGORY")
	private String customercategory;
	
	@Column(name="PURCHASE_AMOUNT")
	private Double purchaseamount;
	
	@Column(name="SCHEMETYPE_CODE")
	private String schemetype;
	
	@Column(name="INTEREST_FREQUENCY")
	private String interestfrequency;
	
	@Column(name="TENURE")
	private String tenure;
	
	@Column(name="INTEREST_RATE")
	private String interestrate;
	
	@Column(name="IS_AUTO_RENEWAL")
	private String isautorenewal;
	
	@Column(name="SAVE_TRANSACTION_APPLICATION_NO")
	private String applicationno;
	
	@Column(name="IS_TRANSACTION_COMPLETE")
	private String istransactioncomplete;
	
	@Column(name="IS_PAYMENT_COMPLETE")
	private String ispaymentcomplete;
	
	@Column(name="TRANSACTION_INITIATION_TIME")
	private Date transactiondate;

	public String getCustomerid() {
		return customerid;
	}

	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTransactionrefid() {
		return transactionrefid;
	}

	public void setTransactionrefid(String transactionrefid) {
		this.transactionrefid = transactionrefid;
	}

	public String getSchemecode() {
		return schemecode;
	}

	public void setSchemecode(String schemecode) {
		this.schemecode = schemecode;
	}

	public String getTransactiontype() {
		return transactiontype;
	}

	public void setTransactiontype(String transactiontype) {
		this.transactiontype = transactiontype;
	}

	public String getCustomercategory() {
		return customercategory;
	}

	public void setCustomercategory(String customercategory) {
		this.customercategory = customercategory;
	}

	public Double getPurchaseamount() {
		return purchaseamount;
	}

	public void setPurchaseamount(Double purchaseamount) {
		this.purchaseamount = purchaseamount;
	}

	public String getSchemetype() {
		return schemetype;
	}

	public void setSchemetype(String schemetype) {
		this.schemetype = schemetype;
	}

	public String getInterestfrequency() {
		return interestfrequency;
	}

	public void setInterestfrequency(String interestfrequency) {
		this.interestfrequency = interestfrequency;
	}

	public String getTenure() {
		return tenure;
	}

	public void setTenure(String tenure) {
		this.tenure = tenure;
	}

	public String getInterestrate() {
		return interestrate;
	}

	public void setInterestrate(String interestrate) {
		this.interestrate = interestrate;
	}

	public String getIsautorenewal() {
		return isautorenewal;
	}

	public void setIsautorenewal(String isautorenewal) {
		this.isautorenewal = isautorenewal;
	}

	public String getApplicationno() {
		return applicationno;
	}

	public void setApplicationno(String applicationno) {
		this.applicationno = applicationno;
	}

	public String getIstransactioncomplete() {
		return istransactioncomplete;
	}

	public void setIstransactioncomplete(String istransactioncomplete) {
		this.istransactioncomplete = istransactioncomplete;
	}

	public String getIspaymentcomplete() {
		return ispaymentcomplete;
	}

	public void setIspaymentcomplete(String ispaymentcomplete) {
		this.ispaymentcomplete = ispaymentcomplete;
	}

	public Date getTransactiondate() {
		return transactiondate;
	}

	public void setTransactiondate(Date transactiondate) {
		this.transactiondate = transactiondate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
