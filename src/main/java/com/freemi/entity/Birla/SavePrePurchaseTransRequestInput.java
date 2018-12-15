package com.freemi.entity.Birla;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SavePrePurchaseTransRequestInput implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@JsonProperty("SavePrePurchaseTransRequest")
	private SavePrePurchaseTransRequest savePrePurchaseTransRequestObject;

	public SavePrePurchaseTransRequest getSavePrePurchaseTransRequestObject() {
		return savePrePurchaseTransRequestObject;
	}

	public void setSavePrePurchaseTransRequestObject(SavePrePurchaseTransRequest savePrePurchaseTransRequestObject) {
		this.savePrePurchaseTransRequestObject = savePrePurchaseTransRequestObject;
	}



	public static class SavePrePurchaseTransRequest {
		@JsonProperty("UserId")
		private String userId;
		
		@JsonProperty("Password")
		private String password;
		
		@JsonProperty("FolioNo")
		private String folioNo;
		
		@JsonProperty("PrimeFolioNo")
		private String primeFolioNo = null;
		
		@JsonProperty("ReferenceNo")
		private String referenceNo;
		
		@JsonProperty("AdvisorRefNo")
		private String advisorRefNo;
		
		@JsonProperty("PaymentReferencNo")
		private String paymentReferencNo;
		
		@JsonProperty("SchemeCode")
		private String schemeCode;
		
		@JsonProperty("SchemeOption")
		private String schemeOption;
		
		@JsonProperty("UDP")
		private String udp;
		
		@JsonProperty("Amount")
		private float amount;
		
		@JsonProperty("BrokerCode")
		private String brokerCode;
		
		@JsonProperty("SubBrokerCode")
		private String subBrokerCode;
		
		@JsonProperty("EUIN")
		private String euin;
		
		@JsonProperty("PaymentBankName")
		private String paymentBankName;
		
		@JsonProperty("TransactionType")
		private String transactionType;
		
		@JsonProperty("PaymentGateway")
		private String paymentGateway;
		
		@JsonProperty("BankCode")
		private String bankCode;
		
		@JsonProperty("AuthCode")
		private String authCode;
		
		@JsonProperty("UserName")
		private String userName;
		
		@JsonProperty("IPAddress")
		private String ipAddress;
		
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
		
		@JsonProperty("OS")
		private String os;
		
		@JsonProperty("IMEI")
		private String imei;
		
		@JsonProperty("ClientIpAddress")
		private String clientIpAddress;
		
		@JsonProperty("DeviceId")
		private String deviceId;
		
		@JsonProperty("GoogleAdID")
		private String googleAdID;



	}

}
