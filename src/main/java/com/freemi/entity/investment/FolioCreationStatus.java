package com.freemi.entity.investment;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name="registry_purchase_records")
@Proxy(lazy=false)
public class FolioCreationStatus {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private String id;
	
	@Column(name="PAN_NO")
	private String pan;
	
	@Column(name="FOLIO_NUMBER")
	private String folioNumber;
	
	@Transient
	private String returnCode;
	
	@Transient
	private String refNumber;
	
	@Transient
	private String message;
	
	@Transient
	private boolean transactionSuccessful;

	@Transient
	private String camsEntryDate;
	
	@Transient
	private String camsTransDate;
	
	@Transient
	private String camsExpiryDate;
	
	@Transient
	private String camsStatus;
	
	@Transient
	private String camsTransNo;
	
	@Transient
	private String camsReturnCode;
	
	@Transient
	private String camsReturnMessage;

	@Column(name="SCHEME_CODE")
	private String schemeCode;
	
	@Transient
	private String schemeReferenceId;
	
	@Transient
	private String schemeTransNo;

//	@Column(name="SAVE_SIP_SUCCESSFUL")
	@Transient
	private boolean savePreSIPSuccessful;
	
	@Transient
	private boolean savePostSIPSuccessfull;
	
	@Transient
	private boolean camsEntrySuccessful;
	
	
	@Transient
	private String savePostSIPMessage;
	
	@Transient
	private String payTrxnRefNo;
	
	@Column(name="URN_NUMBER")
	private String urnNumber;
	
	@Transient
	private String savePreSIPMessage;
	
	@Column(name="SAVE_SIP_CODE")
	private String savePreSIPCode;

	@Column(name="TRANS_NUMBER")
	private String transNo;
	
	@Column(name="TRANS_REF_NO")
	private String transRefNo;
	
	@Column(name="PURCHASE_DATE")
	private Date purchaseDate;
	
	@Column(name="IS_ACTIVE")
	private String isActive;
	
	@Column(name="PURCHASE_REFERENCE_NO")
	private String freemiPurchaseRefNo;
	
	
	
	

	public Date getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getFreemiPurchaseRefNo() {
		return freemiPurchaseRefNo;
	}
	public void setFreemiPurchaseRefNo(String freemiPurchaseRefNo) {
		this.freemiPurchaseRefNo = freemiPurchaseRefNo;
	}
	public String getFolioNumber() {
		return folioNumber;
	}
	public void setFolioNumber(String folioNumber) {
		this.folioNumber = folioNumber;
	}
	public String getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	public String getRefNumber() {
		return refNumber;
	}
	public void setRefNumber(String refNumber) {
		this.refNumber = refNumber;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public boolean isTransactionSuccessful() {
		return transactionSuccessful;
	}
	public void setTransactionSuccessful(boolean transactionSuccessful) {
		this.transactionSuccessful = transactionSuccessful;
	}
	public String getCamsEntryDate() {
		return camsEntryDate;
	}
	public void setCamsEntryDate(String camsEntryDate) {
		this.camsEntryDate = camsEntryDate;
	}
	public String getCamsTransDate() {
		return camsTransDate;
	}
	public void setCamsTransDate(String camsTransDate) {
		this.camsTransDate = camsTransDate;
	}
	public String getCamsExpiryDate() {
		return camsExpiryDate;
	}
	public void setCamsExpiryDate(String camsExpiryDate) {
		this.camsExpiryDate = camsExpiryDate;
	}
	public String getCamsStatus() {
		return camsStatus;
	}
	public void setCamsStatus(String camsStatus) {
		this.camsStatus = camsStatus;
	}
	public String getCamsReturnCode() {
		return camsReturnCode;
	}
	public void setCamsReturnCode(String camsReturnCode) {
		this.camsReturnCode = camsReturnCode;
	}
	public String getCamsReturnMessage() {
		return camsReturnMessage;
	}
	public void setCamsReturnMessage(String camsReturnMessage) {
		this.camsReturnMessage = camsReturnMessage;
	}
	public String getSchemeCode() {
		return schemeCode;
	}
	public void setSchemeCode(String schemeCode) {
		this.schemeCode = schemeCode;
	}
	public String getSchemeReferenceId() {
		return schemeReferenceId;
	}
	public void setSchemeReferenceId(String schemeReferenceId) {
		this.schemeReferenceId = schemeReferenceId;
	}
	public String getSchemeTransNo() {
		return schemeTransNo;
	}
	public void setSchemeTransNo(String schemeTransNo) {
		this.schemeTransNo = schemeTransNo;
	}
	public String getTransNo() {
		return transNo;
	}
	public void setTransNo(String transNo) {
		this.transNo = transNo;
	}
	public String getTransRefNo() {
		return transRefNo;
	}
	public void setTransRefNo(String transRefNo) {
		this.transRefNo = transRefNo;
	}
	public boolean isSavePreSIPSuccessful() {
		return savePreSIPSuccessful;
	}
	public void setSavePreSIPSuccessful(boolean savePreSIPSuccessful) {
		this.savePreSIPSuccessful = savePreSIPSuccessful;
	}
	
	
	public boolean isSavePostSIPSuccessfull() {
		return savePostSIPSuccessfull;
	}
	public void setSavePostSIPSuccessfull(boolean savePostSIPSuccessfull) {
		this.savePostSIPSuccessfull = savePostSIPSuccessfull;
	}
	public String getSavePostSIPMessage() {
		return savePostSIPMessage;
	}
	public void setSavePostSIPMessage(String savePostSIPMessage) {
		this.savePostSIPMessage = savePostSIPMessage;
	}
	public String getUrnNumber() {
		return urnNumber;
	}
	public void setUrnNumber(String urnNumber) {
		this.urnNumber = urnNumber;
	}
	public String getSavePreSIPMessage() {
		return savePreSIPMessage;
	}
	public void setSavePreSIPMessage(String savePreSIPMessage) {
		this.savePreSIPMessage = savePreSIPMessage;
	}
	public String getSavePreSIPCode() {
		return savePreSIPCode;
	}
	public void setSavePreSIPCode(String savePreSIPCode) {
		this.savePreSIPCode = savePreSIPCode;
	}
	public String getCamsTransNo() {
		return camsTransNo;
	}
	public void setCamsTransNo(String camsTransNo) {
		this.camsTransNo = camsTransNo;
	}
	public boolean isCamsEntrySuccessful() {
		return camsEntrySuccessful;
	}
	public void setCamsEntrySuccessful(boolean camsEntrySuccessful) {
		this.camsEntrySuccessful = camsEntrySuccessful;
	}
	public String getPayTrxnRefNo() {
		return payTrxnRefNo;
	}
	public void setPayTrxnRefNo(String payTrxnRefNo) {
		this.payTrxnRefNo = payTrxnRefNo;
	}
	



}
