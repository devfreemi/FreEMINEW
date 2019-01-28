package com.freemi.entity.birla;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SavePostPurchaseTransactionInput implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("SavePostPurchaseTransRequest")
	private SavePostPurchaseTransRequest savePostPurchaseTransRequestObject;

	public SavePostPurchaseTransRequest getSavePostPurchaseTransRequestObject() {
		return savePostPurchaseTransRequestObject;
	}

	public void setSavePostPurchaseTransRequestObject(SavePostPurchaseTransRequest savePostPurchaseTransRequestObject) {
		this.savePostPurchaseTransRequestObject = savePostPurchaseTransRequestObject;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static class SavePostPurchaseTransRequest{

		@JsonProperty("UserId")
		private String userId;

		@JsonProperty("Password")
		private String password;

		@JsonProperty("TransNo")
		private String transNo;

		@JsonProperty("FolioNo")
		private String folioNo;

		@JsonProperty("PaymentStatus")
		private String paymentStatus;

		@JsonProperty("PGResponse")
		private String pgResponse = null;

		@JsonProperty("PaymentBankName")
		private String paymentBankName;

		@JsonProperty("TransactionType")
		private String transactionType;

		@JsonProperty("Amount")
		private float amount;

		@JsonProperty("AdditionalInfo")
		private String additionalInfo;

		@JsonProperty("SchemeCode")
		private String schemeCode;

		@JsonProperty("PaymentReferencNo")
		private String paymentReferencNo;

		@JsonProperty("UDP")
		private String udp;

		@JsonProperty("ClientIpAddress")
		private String clientIpAddress;

		@JsonProperty("OS")
		private String os;

		@JsonProperty("IMEI")
		private String imei;

		@JsonProperty("UDP1")
		private String udp1;

		@JsonProperty("UDP2")
		private String udp2;

		@JsonProperty("UDP3")
		private String udp3;

		@JsonProperty("UDP4")
		private String udp4;

		@JsonProperty("UDP5")
		private String udp5;

		@JsonProperty("DeviceId")
		private String deviceId;

		@JsonProperty("GoogleAdID")
		private String googleAdID;

		@JsonProperty("AuthCode")
		private String authCode;

		@JsonProperty("UserName")
		private String userName;

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getTransNo() {
			return transNo;
		}

		public void setTransNo(String transNo) {
			this.transNo = transNo;
		}

		public String getFolioNo() {
			return folioNo;
		}

		public void setFolioNo(String folioNo) {
			this.folioNo = folioNo;
		}

		public String getPaymentStatus() {
			return paymentStatus;
		}

		public void setPaymentStatus(String paymentStatus) {
			this.paymentStatus = paymentStatus;
		}

		public String getPgResponse() {
			return pgResponse;
		}

		public void setPgResponse(String pgResponse) {
			this.pgResponse = pgResponse;
		}

		public String getPaymentBankName() {
			return paymentBankName;
		}

		public void setPaymentBankName(String paymentBankName) {
			this.paymentBankName = paymentBankName;
		}

		public String getTransactionType() {
			return transactionType;
		}

		public void setTransactionType(String transactionType) {
			this.transactionType = transactionType;
		}

		public float getAmount() {
			return amount;
		}

		public void setAmount(float amount) {
			this.amount = amount;
		}

		public String getAdditionalInfo() {
			return additionalInfo;
		}

		public void setAdditionalInfo(String additionalInfo) {
			this.additionalInfo = additionalInfo;
		}

		public String getSchemeCode() {
			return schemeCode;
		}

		public void setSchemeCode(String schemeCode) {
			this.schemeCode = schemeCode;
		}

		public String getPaymentReferencNo() {
			return paymentReferencNo;
		}

		public void setPaymentReferencNo(String paymentReferencNo) {
			this.paymentReferencNo = paymentReferencNo;
		}

		public String getUdp() {
			return udp;
		}

		public void setUdp(String udp) {
			this.udp = udp;
		}

		public String getClientIpAddress() {
			return clientIpAddress;
		}

		public void setClientIpAddress(String clientIpAddress) {
			this.clientIpAddress = clientIpAddress;
		}

		public String getOs() {
			return os;
		}

		public void setOs(String os) {
			this.os = os;
		}

		public String getImei() {
			return imei;
		}

		public void setImei(String imei) {
			this.imei = imei;
		}

		public String getUdp1() {
			return udp1;
		}

		public void setUdp1(String udp1) {
			this.udp1 = udp1;
		}

		public String getUdp2() {
			return udp2;
		}

		public void setUdp2(String udp2) {
			this.udp2 = udp2;
		}

		public String getUdp3() {
			return udp3;
		}

		public void setUdp3(String udp3) {
			this.udp3 = udp3;
		}

		public String getUdp4() {
			return udp4;
		}

		public void setUdp4(String udp4) {
			this.udp4 = udp4;
		}

		public String getUdp5() {
			return udp5;
		}

		public void setUdp5(String udp5) {
			this.udp5 = udp5;
		}

		public String getDeviceId() {
			return deviceId;
		}

		public void setDeviceId(String deviceId) {
			this.deviceId = deviceId;
		}

		public String getGoogleAdID() {
			return googleAdID;
		}

		public void setGoogleAdID(String googleAdID) {
			this.googleAdID = googleAdID;
		}

		public String getAuthCode() {
			return authCode;
		}

		public void setAuthCode(String authCode) {
			this.authCode = authCode;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}


	}



}
