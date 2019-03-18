package com.freemi.entity.birla;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SavePostPurchaseMultiRequestInput {
	
	@JsonProperty("SavePostPurchaseMultiRequest")
	private PostSaveJson postSaveJson;
	
	
	public PostSaveJson getPostSaveJson() {
		return postSaveJson;
	}

	public void setPostSaveJson(PostSaveJson postSaveJson) {
		this.postSaveJson = postSaveJson;
	}

	public static class PostSaveJson{

	@JsonProperty("FolioNo")
	private String folioNo;
	
	@JsonProperty("TransNo")
	private String transNo;
	
	@JsonProperty("PaymentStatus")
	private String paymentStatus;
	
	@JsonProperty("PGRequest")
	private String pgRequest;
	
	@JsonProperty("PGResponse")
	private String pgResponse;
	
	@JsonProperty("Amount")
	private String amount;
	
	@JsonProperty("AdditionalInfo")
	private String additionalInfo = null;
	
	@JsonProperty("PGReferenceNo")
	private String pgReferenceNo;
	
	@JsonProperty("UserId")
	private String userId;
	
	@JsonProperty("Password")
	private String password;
	
	@JsonProperty("Source")
	private String source = null;
	
	@JsonProperty("UDP")
	private String udp;
	
	@JsonProperty("IFACode")
	private String ifaCode = null;
	
	@JsonProperty("EUINCode")
	private String euinCode = null;
	
	@JsonProperty("FilterFor")
	private String filterFor = null;
	
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
	
	@JsonProperty("Channel")
	private String channel = null;
	
	@JsonProperty("PageURL")
	private String pageURL = null;
	
	@JsonProperty("Campaign")
	private String campaign = null;
	
	@JsonProperty("Trans_No")
	private String trans_No = null;
	
	@JsonProperty("TransactionType")
	private String transactionType = null;
	
	@JsonProperty("ServerIpAddress")
	private String serverIpAddress = null;
	
	@JsonProperty("ModuleName")
	private String moduleName = null;
	
	@JsonProperty("MethodName")
	private String methodName = null;
	
	@JsonProperty("AuthCode")
	private String authCode = null;
	
	@JsonProperty("GoogleAdID")
	private String googleAdID = null;
	
	@JsonProperty("DeviceId")
	private String deviceId = null;
	
	@JsonProperty("UserName")
	private String userName = null;

	public String getFolioNo() {
		return folioNo;
	}

	public void setFolioNo(String folioNo) {
		this.folioNo = folioNo;
	}

	public String getTransNo() {
		return transNo;
	}

	public void setTransNo(String transNo) {
		this.transNo = transNo;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getPgRequest() {
		return pgRequest;
	}

	public void setPgRequest(String pgRequest) {
		this.pgRequest = pgRequest;
	}

	public String getPgResponse() {
		return pgResponse;
	}

	public void setPgResponse(String pgResponse) {
		this.pgResponse = pgResponse;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public String getPgReferenceNo() {
		return pgReferenceNo;
	}

	public void setPgReferenceNo(String pgReferenceNo) {
		this.pgReferenceNo = pgReferenceNo;
	}

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

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getUdp() {
		return udp;
	}

	public void setUdp(String udp) {
		this.udp = udp;
	}

	public String getIfaCode() {
		return ifaCode;
	}

	public void setIfaCode(String ifaCode) {
		this.ifaCode = ifaCode;
	}

	public String getEuinCode() {
		return euinCode;
	}

	public void setEuinCode(String euinCode) {
		this.euinCode = euinCode;
	}

	public String getFilterFor() {
		return filterFor;
	}

	public void setFilterFor(String filterFor) {
		this.filterFor = filterFor;
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

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getPageURL() {
		return pageURL;
	}

	public void setPageURL(String pageURL) {
		this.pageURL = pageURL;
	}

	public String getCampaign() {
		return campaign;
	}

	public void setCampaign(String campaign) {
		this.campaign = campaign;
	}

	public String getTrans_No() {
		return trans_No;
	}

	public void setTrans_No(String trans_No) {
		this.trans_No = trans_No;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getServerIpAddress() {
		return serverIpAddress;
	}

	public void setServerIpAddress(String serverIpAddress) {
		this.serverIpAddress = serverIpAddress;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getGoogleAdID() {
		return googleAdID;
	}

	public void setGoogleAdID(String googleAdID) {
		this.googleAdID = googleAdID;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
}
