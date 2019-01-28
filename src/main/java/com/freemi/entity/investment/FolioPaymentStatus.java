package com.freemi.entity.investment;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="bsemf_transactions_payment_status")
public class FolioPaymentStatus implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="")
	private long serial;
	
	
	@Transient
	public String folioNumber;
	
	@Id
	@Column(name="TRANSACTION_ID")
	public String transNumber;
	
	@Column(name="PAYMENT_STATUS")
	private String paymentStatus;
	
	@Column(name="PAYMENT_REFERENCE_ID")
	private String returnCode;
	
	
	public String getFolioNumber() {
		return folioNumber;
	}
	public void setFolioNumber(String folioNumber) {
		this.folioNumber = folioNumber;
	}
	public String getTransNumber() {
		return transNumber;
	}
	public void setTransNumber(String transNumber) {
		this.transNumber = transNumber;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public String getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	
	
	

}
