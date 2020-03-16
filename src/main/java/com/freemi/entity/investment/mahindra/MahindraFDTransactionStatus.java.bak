package com.freemi.entity.investment.mahindra;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name="mahindra_fd_transaction_status")
@Proxy(lazy=false)
public class MahindraFDTransactionStatus implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "SL_NO")
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer serial;
	
	@Column(name = "CUSTOMER_REF_ID")
	private String customerId;
	
	@Column(name = "TRANSATION_REF_ID")
	private String transactionId;
	
	
	@Column(name= "SAVE_TRSACTION_REQUEST_BODY")
	private String saveTransactionRequestBody;
	
	@Column(name= "SAVE_TRANSACTION_STATUS")
	private String transactionStatus;
	
	@Column(name= "SAVE_TRANSACTION_APPLICATION_NO")
	private String transactionApplicationNumber;
	
	@Column(name= "IS_TRANSACTION_COMPLETE")
	private String isTransactionComplete;
	
	@Column(name= "IS_PAYMENT_COMPLETE")
	private String isPaymentComplete="N";
	
	@Column(name= "PAYMENT_VERIFY_STATUS")
	private String paymentResponse;
	
	@Column(name= "PAYMENT_STATUS_RESPONSE_CODE")
	private String paymentStatusCode;
	
	@Column(name= "UPDATED_BY")
	private String updatedBy="PORTAL_TRANSACTION";
	
	@Column(name= "IS_ACTIVE")
	private String active="Y";
	

	public Integer getSerial() {
		return serial;
	}

	public void setSerial(Integer serial) {
		this.serial = serial;
	}


	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getSaveTransactionRequestBody() {
		return saveTransactionRequestBody;
	}

	public void setSaveTransactionRequestBody(String saveTransactionRequestBody) {
		this.saveTransactionRequestBody = saveTransactionRequestBody;
	}

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public String getTransactionApplicationNumber() {
		return transactionApplicationNumber;
	}

	public void setTransactionApplicationNumber(String transactionApplicationNumber) {
		this.transactionApplicationNumber = transactionApplicationNumber;
	}

	public String getIsTransactionComplete() {
		return isTransactionComplete;
	}

	public void setIsTransactionComplete(String isTransactionComplete) {
		this.isTransactionComplete = isTransactionComplete;
	}

	public String getIsPaymentComplete() {
		return isPaymentComplete;
	}

	public void setIsPaymentComplete(String isPaymentComplete) {
		this.isPaymentComplete = isPaymentComplete;
	}
	
	public String getPaymentResponse() {
		return paymentResponse;
	}

	public void setPaymentResponse(String paymentResponse) {
		this.paymentResponse = paymentResponse;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getPaymentStatusCode() {
		return paymentStatusCode;
	}

	public void setPaymentStatusCode(String paymentStatusCode) {
		this.paymentStatusCode = paymentStatusCode;
	}

}
