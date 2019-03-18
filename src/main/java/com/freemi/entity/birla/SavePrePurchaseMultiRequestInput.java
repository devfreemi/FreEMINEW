package com.freemi.entity.birla;

import java.io.Serializable;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SavePrePurchaseMultiRequestInput implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("SavePrePurchaseMultiRequest")
	private LumpsumScheme lumpsumscheme;
	
	public LumpsumScheme getLumpsumscheme() {
		return lumpsumscheme;
	}


	public void setLumpsumscheme(LumpsumScheme lumpsumscheme) {
		this.lumpsumscheme = lumpsumscheme;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public static class LumpsumScheme{

	@JsonProperty("FolioNo")
	private String folioNo;
	
	@JsonProperty("PrimeFolioNo")
	private String primeFolioNo;
	
	@JsonProperty("ParentReferenceNo")
	private String parentReferenceNo;
	
	@JsonProperty("PaymentBankName")
	private String paymentBankName;
	
	@JsonProperty("PaymentType")
	private String paymentType;
	
	@JsonProperty("Image")
	private String image;
	
	@JsonProperty("ByteImage")
	private String byteImage = null;
	
	@JsonProperty("ImageType")
	private String imageType;
	
	@JsonProperty("ImageSize")
	private String imageSize;
	
	@JsonProperty("NEFTTransRefNo")
	private String neftTransRefNo;
	
	@JsonProperty("MerchantCode")
	private String merchantCode;
	
	@JsonProperty("PaymentGateway")
	private String paymentGateway;
	
	@JsonProperty("BankCode")
	private String bankCode;
	
	@JsonProperty("TotalAmount")
	private String totalAmount;
	
	@JsonProperty("BrokerCode")
	private String brokerCode;
	
	@JsonProperty("SubBrokerCode")
	private String subBrokerCode;
	
	@JsonProperty("EUIN")
	private String euin;
	
	@JsonProperty("SchemeDetails")
	ArrayList<SchemeDetails> schemeDetails = new ArrayList<SchemeDetails>();
	
	@JsonProperty("IsNEFT")
	private String isNEFT = null;
	
	@JsonProperty("IsOTM")
	private String isOTM = null;
	
	@JsonProperty("PaymentStatus")
	private String paymentStatus = null;
	
	@JsonProperty("IsDirectSchemes")
	private boolean isDirectSchemes;
	
	@JsonProperty("PaymentRequest")
	private String paymentRequest = null;
	
	@JsonProperty("PaymentResponse")
	private String paymentResponse = null;
	
	@JsonProperty("RequestTime")
	private String requestTime;
	
	@JsonProperty("ResponseTime")
	private String responseTime;
	
	@JsonProperty("Goal")
	private String goal;
	
	@JsonProperty("ExternalReferenceNo")
	private String externalReferenceNo;
	
	@JsonProperty("AdditionalInfo")
	private String additionalInfo;
	
	@JsonProperty("SIP_BankName")
	private String sip_BankName;
	
	@JsonProperty("SIP_BankCity")
	private String sip_BankCity;
	
	@JsonProperty("SIP_BankBranch")
	private String sip_BankBranch;
	
	@JsonProperty("SIP_IFSCCode")
	private String sip_IFSCCode;
	
	@JsonProperty("SIP_AcctNumber")
	private String sip_AcctNumber;
	
	@JsonProperty("SIP_AcctType")
	private String sip_AcctType;
	
	@JsonProperty("SIP_OTMorBiller")
	private String sip_OTMorBiller = null;
	
	@JsonProperty("SIP_IsFirstInstallment")
	private String sip_IsFirstInstallment;
	
	@JsonProperty("SIP_TotalFistInstallmentAmount")
	private String sip_TotalFistInstallmentAmount = null;
	
	@JsonProperty("SIP_IsDirect")
	private String sip_IsDirect;
	
	@JsonProperty("SIP_EUINdeclaration")
	private boolean sip_EUINdeclaration;
	
	@JsonProperty("SIP_UsPerson")
	private String sip_UsPerson = null;
	
	@JsonProperty("SIP_DeclarationCheck")
	private String sip_DeclarationCheck = null;
	
	@JsonProperty("DBEmailid")
	private String dbEmailid = null;
	
	@JsonProperty("DBMobileNo")
	private String dbMobileNo = null;
	
	@JsonProperty("DBTaxStatusCode")
	private String dbTaxStatusCode = null;
	
	@JsonProperty("DBHolderName")
	private String dbHolderName = null;
	
	@JsonProperty("DBSIPDateValue")
	private String dbSIPDateValue = null;
	
	@JsonProperty("PaymentMode")
	private String paymentMode = null;
	
	@JsonProperty("DBOtmAmount")
	private String dbOtmAmount = null;
	
	@JsonProperty("DBIsOtmFlg")
	private String dbIsOtmFlg = null;
	
	@JsonProperty("IsSkipValidation")
	private boolean isSkipValidation;
	
	@JsonProperty("TransTypeScheme1")
	private String transTypeScheme1 = null;
	
	@JsonProperty("IsCSIPScheme1")
	private String isCSIPScheme1 = null;
	
	@JsonProperty("AdviceRefId")
	private float adviceRefId;
	
	@JsonProperty("LeadId")
	private float leadId;
	
	@JsonProperty("SIPAdviceId")
	private float sipAdviceId;
	
	@JsonProperty("UserId")
	private String userId;
	
	@JsonProperty("Password")
	private String password;
	
	@JsonProperty("Source")
	private String source;
	
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
	private String channel;
	
	@JsonProperty("PageURL")
	private String pageURL;
	
	@JsonProperty("Campaign")
	private String campaign;
	
	@JsonProperty("Trans_No")
	private String trans_No = null;
	
	@JsonProperty("TransactionType")
	private String transactionType = null;
	
	@JsonProperty("ServerIpAddress")
	private String serverIpAddress = null;
	
	@JsonProperty("ModuleName")
	private String moduleName;
	
	@JsonProperty("MethodName")
	private String methodName;
	
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

	public String getPrimeFolioNo() {
		return primeFolioNo;
	}

	public void setPrimeFolioNo(String primeFolioNo) {
		this.primeFolioNo = primeFolioNo;
	}

	public String getParentReferenceNo() {
		return parentReferenceNo;
	}

	public void setParentReferenceNo(String parentReferenceNo) {
		this.parentReferenceNo = parentReferenceNo;
	}

	public String getPaymentBankName() {
		return paymentBankName;
	}

	public void setPaymentBankName(String paymentBankName) {
		this.paymentBankName = paymentBankName;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getByteImage() {
		return byteImage;
	}

	public void setByteImage(String byteImage) {
		this.byteImage = byteImage;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	public String getImageSize() {
		return imageSize;
	}

	public void setImageSize(String imageSize) {
		this.imageSize = imageSize;
	}

	public String getNeftTransRefNo() {
		return neftTransRefNo;
	}

	public void setNeftTransRefNo(String neftTransRefNo) {
		this.neftTransRefNo = neftTransRefNo;
	}

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	public String getPaymentGateway() {
		return paymentGateway;
	}

	public void setPaymentGateway(String paymentGateway) {
		this.paymentGateway = paymentGateway;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getBrokerCode() {
		return brokerCode;
	}

	public void setBrokerCode(String brokerCode) {
		this.brokerCode = brokerCode;
	}

	public String getSubBrokerCode() {
		return subBrokerCode;
	}

	public void setSubBrokerCode(String subBrokerCode) {
		this.subBrokerCode = subBrokerCode;
	}

	public String getEuin() {
		return euin;
	}

	public void setEuin(String euin) {
		this.euin = euin;
	}

	public ArrayList<SchemeDetails> getSchemeDetails() {
		return schemeDetails;
	}

	public void setSchemeDetails(ArrayList<SchemeDetails> schemeDetails) {
		this.schemeDetails = schemeDetails;
	}

	public String getIsNEFT() {
		return isNEFT;
	}

	public void setIsNEFT(String isNEFT) {
		this.isNEFT = isNEFT;
	}

	public String getIsOTM() {
		return isOTM;
	}

	public void setIsOTM(String isOTM) {
		this.isOTM = isOTM;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public boolean isDirectSchemes() {
		return isDirectSchemes;
	}

	public void setDirectSchemes(boolean isDirectSchemes) {
		this.isDirectSchemes = isDirectSchemes;
	}

	public String getPaymentRequest() {
		return paymentRequest;
	}

	public void setPaymentRequest(String paymentRequest) {
		this.paymentRequest = paymentRequest;
	}

	public String getPaymentResponse() {
		return paymentResponse;
	}

	public void setPaymentResponse(String paymentResponse) {
		this.paymentResponse = paymentResponse;
	}

	public String getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}

	public String getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(String responseTime) {
		this.responseTime = responseTime;
	}

	public String getGoal() {
		return goal;
	}

	public void setGoal(String goal) {
		this.goal = goal;
	}

	public String getExternalReferenceNo() {
		return externalReferenceNo;
	}

	public void setExternalReferenceNo(String externalReferenceNo) {
		this.externalReferenceNo = externalReferenceNo;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public String getSip_BankName() {
		return sip_BankName;
	}

	public void setSip_BankName(String sip_BankName) {
		this.sip_BankName = sip_BankName;
	}

	public String getSip_BankCity() {
		return sip_BankCity;
	}

	public void setSip_BankCity(String sip_BankCity) {
		this.sip_BankCity = sip_BankCity;
	}

	public String getSip_BankBranch() {
		return sip_BankBranch;
	}

	public void setSip_BankBranch(String sip_BankBranch) {
		this.sip_BankBranch = sip_BankBranch;
	}

	public String getSip_IFSCCode() {
		return sip_IFSCCode;
	}

	public void setSip_IFSCCode(String sip_IFSCCode) {
		this.sip_IFSCCode = sip_IFSCCode;
	}

	public String getSip_AcctNumber() {
		return sip_AcctNumber;
	}

	public void setSip_AcctNumber(String sip_AcctNumber) {
		this.sip_AcctNumber = sip_AcctNumber;
	}

	public String getSip_AcctType() {
		return sip_AcctType;
	}

	public void setSip_AcctType(String sip_AcctType) {
		this.sip_AcctType = sip_AcctType;
	}

	public String getSip_OTMorBiller() {
		return sip_OTMorBiller;
	}

	public void setSip_OTMorBiller(String sip_OTMorBiller) {
		this.sip_OTMorBiller = sip_OTMorBiller;
	}

	public String getSip_IsFirstInstallment() {
		return sip_IsFirstInstallment;
	}

	public void setSip_IsFirstInstallment(String sip_IsFirstInstallment) {
		this.sip_IsFirstInstallment = sip_IsFirstInstallment;
	}

	public String getSip_TotalFistInstallmentAmount() {
		return sip_TotalFistInstallmentAmount;
	}

	public void setSip_TotalFistInstallmentAmount(String sip_TotalFistInstallmentAmount) {
		this.sip_TotalFistInstallmentAmount = sip_TotalFistInstallmentAmount;
	}

	public String getSip_IsDirect() {
		return sip_IsDirect;
	}

	public void setSip_IsDirect(String sip_IsDirect) {
		this.sip_IsDirect = sip_IsDirect;
	}

	public boolean isSip_EUINdeclaration() {
		return sip_EUINdeclaration;
	}

	public void setSip_EUINdeclaration(boolean sip_EUINdeclaration) {
		this.sip_EUINdeclaration = sip_EUINdeclaration;
	}

	public String getSip_UsPerson() {
		return sip_UsPerson;
	}

	public void setSip_UsPerson(String sip_UsPerson) {
		this.sip_UsPerson = sip_UsPerson;
	}

	public String getSip_DeclarationCheck() {
		return sip_DeclarationCheck;
	}

	public void setSip_DeclarationCheck(String sip_DeclarationCheck) {
		this.sip_DeclarationCheck = sip_DeclarationCheck;
	}

	public String getDbEmailid() {
		return dbEmailid;
	}

	public void setDbEmailid(String dbEmailid) {
		this.dbEmailid = dbEmailid;
	}

	public String getDbMobileNo() {
		return dbMobileNo;
	}

	public void setDbMobileNo(String dbMobileNo) {
		this.dbMobileNo = dbMobileNo;
	}

	public String getDbTaxStatusCode() {
		return dbTaxStatusCode;
	}

	public void setDbTaxStatusCode(String dbTaxStatusCode) {
		this.dbTaxStatusCode = dbTaxStatusCode;
	}

	public String getDbHolderName() {
		return dbHolderName;
	}

	public void setDbHolderName(String dbHolderName) {
		this.dbHolderName = dbHolderName;
	}

	public String getDbSIPDateValue() {
		return dbSIPDateValue;
	}

	public void setDbSIPDateValue(String dbSIPDateValue) {
		this.dbSIPDateValue = dbSIPDateValue;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getDbOtmAmount() {
		return dbOtmAmount;
	}

	public void setDbOtmAmount(String dbOtmAmount) {
		this.dbOtmAmount = dbOtmAmount;
	}

	public String getDbIsOtmFlg() {
		return dbIsOtmFlg;
	}

	public void setDbIsOtmFlg(String dbIsOtmFlg) {
		this.dbIsOtmFlg = dbIsOtmFlg;
	}

	public boolean isSkipValidation() {
		return isSkipValidation;
	}

	public void setSkipValidation(boolean isSkipValidation) {
		this.isSkipValidation = isSkipValidation;
	}

	public String getTransTypeScheme1() {
		return transTypeScheme1;
	}

	public void setTransTypeScheme1(String transTypeScheme1) {
		this.transTypeScheme1 = transTypeScheme1;
	}

	public String getIsCSIPScheme1() {
		return isCSIPScheme1;
	}

	public void setIsCSIPScheme1(String isCSIPScheme1) {
		this.isCSIPScheme1 = isCSIPScheme1;
	}

	public float getAdviceRefId() {
		return adviceRefId;
	}

	public void setAdviceRefId(float adviceRefId) {
		this.adviceRefId = adviceRefId;
	}

	public float getLeadId() {
		return leadId;
	}

	public void setLeadId(float leadId) {
		this.leadId = leadId;
	}

	public float getSipAdviceId() {
		return sipAdviceId;
	}

	public void setSipAdviceId(float sipAdviceId) {
		this.sipAdviceId = sipAdviceId;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static class SchemeDetails{
		
		@JsonProperty("SchemeReferenceId")
		private String schemeReferenceId;
		
		@JsonProperty("SchemeCode")
		private String schemeCode;
		
		@JsonProperty("SchemeName")
		private String schemeName = null;
		
		@JsonProperty("SchemeOption")
		private String schemeOption;
		
		@JsonProperty("Amount")
		private String amount;
		
		@JsonProperty("UDP")
		private String udp;
		
		@JsonProperty("IsETF")
		private String isETF;
		
		@JsonProperty("DPName")
		private String dpName;
		
		@JsonProperty("DPID")
		private String dpID;
		
		@JsonProperty("DPAccNo")
		private String dpAccNo;
		
		@JsonProperty("SchemeTransNo")
		private String schemeTransNo;
		
		@JsonProperty("PayOption")
		private String payOption;
		
		@JsonProperty("IsLiquid")
		private String isLiquid;
		
		@JsonProperty("TransType")
		private String transType;
		
		@JsonProperty("SIPFrequency")
		private String sipFrequency = null;
		
		@JsonProperty("SIPInvestmentDate")
		private String sipInvestmentDate = null;
		
		@JsonProperty("SIPFromPeriod")
		private String sipFromPeriod = null;
		
		@JsonProperty("SIPToPeriod")
		private String sipToPeriod = null;
		
		@JsonProperty("SipWeekDay")
		private String sipWeekDay = null;
		
		@JsonProperty("SIPFirstInstallmentAmount")
		private String sipFirstInstallmentAmount = null;
		
		@JsonProperty("ISCSIP")
		private String isCSIP = null;
		
		@JsonProperty("CSIPInvestorDOB")
		private String csipInvestorDOB = null;
		
		@JsonProperty("CSIPInvestorGender")
		private String csipInvestorGender = null;
		
		@JsonProperty("CSIPNomineeName")
		private String csipNomineeName = null;
		
		@JsonProperty("CSIPNomineeDOB")
		private String csipNomineeDOB = null;
		
		@JsonProperty("CSIPNomineeRelation")
		private String csipNomineeRelation = null;
		
		@JsonProperty("CSIPGuardianName")
		private String csipGuardianName = null;
		
		@JsonProperty("CSIPIsMinor")
		private String csipIsMinor = null;
		
		@JsonProperty("SIPDate")
		private String sipDate = null;
		
		@JsonProperty("SIPTransType")
		private String sipTransType = null;
		
		@JsonProperty("FromMonth")
		private String fromMonth = null;
		
		@JsonProperty("FromYear")
		private String fromYear = null;
		
		@JsonProperty("ToMonth")
		private String toMonth = null;
		
		@JsonProperty("ToYear")
		private String toYear = null;
		
		@JsonProperty("InvstmtDate_Value")
		private String invstmtDate_Value = null;
		
		@JsonProperty("WeeklyInvstmtDate_Value")
		private String weeklyInvstmtDate_Value = null;
		
		@JsonProperty("DBIsLiquid")
		private String dbIsLiquid = null;
		
		@JsonProperty("DBNFO")
		private String dbNFO = null;
		
		@JsonProperty("DBSIPInvestmentAmount")
		private String dbSIPInvestmentAmount = null;
		
		@JsonProperty("DBSip_Weekly_Inv_Min")
		private String dbSip_Weekly_Inv_Min = null;
		
		@JsonProperty("DBSip_Weekly_Inv_Max")
		private String dbSip_Weekly_Inv_Max = null;
		
		@JsonProperty("DBFirst_Int_Min_Amount")
		private String dbFirst_Int_Min_Amount = null;
		
		@JsonProperty("DBFirst_Int_Max_Amount")
		private String dbFirst_Int_Max_Amount = null;
		
		@JsonProperty("DBConfirmSchName")
		private String dbConfirmSchName = null;
		
		@JsonProperty("SCHEME_CATEGORY")
		private String scheme_category = null;
		
		@JsonProperty("DBSchemeCodeDBSchemeCode")
		private String dbSchemeCodeDBSchemeCode = null;
		
		@JsonProperty("DBSchemeCode")
		private String dbSchemeCode = null;
		
		@JsonProperty("DBIsDirectScheme")
		private String dbIsDirectScheme = null;

		public String getSchemeReferenceId() {
			return schemeReferenceId;
		}

		public void setSchemeReferenceId(String schemeReferenceId) {
			this.schemeReferenceId = schemeReferenceId;
		}

		public String getSchemeCode() {
			return schemeCode;
		}

		public void setSchemeCode(String schemeCode) {
			this.schemeCode = schemeCode;
		}

		public String getSchemeName() {
			return schemeName;
		}

		public void setSchemeName(String schemeName) {
			this.schemeName = schemeName;
		}

		public String getSchemeOption() {
			return schemeOption;
		}

		public void setSchemeOption(String schemeOption) {
			this.schemeOption = schemeOption;
		}

		public String getAmount() {
			return amount;
		}

		public void setAmount(String amount) {
			this.amount = amount;
		}

		public String getUdp() {
			return udp;
		}

		public void setUdp(String udp) {
			this.udp = udp;
		}

		public String getIsETF() {
			return isETF;
		}

		public void setIsETF(String isETF) {
			this.isETF = isETF;
		}

		public String getDpName() {
			return dpName;
		}

		public void setDpName(String dpName) {
			this.dpName = dpName;
		}

		public String getDpID() {
			return dpID;
		}

		public void setDpID(String dpID) {
			this.dpID = dpID;
		}

		public String getDpAccNo() {
			return dpAccNo;
		}

		public void setDpAccNo(String dpAccNo) {
			this.dpAccNo = dpAccNo;
		}

		public String getSchemeTransNo() {
			return schemeTransNo;
		}

		public void setSchemeTransNo(String schemeTransNo) {
			this.schemeTransNo = schemeTransNo;
		}

		public String getPayOption() {
			return payOption;
		}

		public void setPayOption(String payOption) {
			this.payOption = payOption;
		}

		public String getIsLiquid() {
			return isLiquid;
		}

		public void setIsLiquid(String isLiquid) {
			this.isLiquid = isLiquid;
		}

		public String getTransType() {
			return transType;
		}

		public void setTransType(String transType) {
			this.transType = transType;
		}

		public String getSipFrequency() {
			return sipFrequency;
		}

		public void setSipFrequency(String sipFrequency) {
			this.sipFrequency = sipFrequency;
		}

		public String getSipInvestmentDate() {
			return sipInvestmentDate;
		}

		public void setSipInvestmentDate(String sipInvestmentDate) {
			this.sipInvestmentDate = sipInvestmentDate;
		}

		public String getSipFromPeriod() {
			return sipFromPeriod;
		}

		public void setSipFromPeriod(String sipFromPeriod) {
			this.sipFromPeriod = sipFromPeriod;
		}

		public String getSipToPeriod() {
			return sipToPeriod;
		}

		public void setSipToPeriod(String sipToPeriod) {
			this.sipToPeriod = sipToPeriod;
		}

		public String getSipWeekDay() {
			return sipWeekDay;
		}

		public void setSipWeekDay(String sipWeekDay) {
			this.sipWeekDay = sipWeekDay;
		}

		public String getSipFirstInstallmentAmount() {
			return sipFirstInstallmentAmount;
		}

		public void setSipFirstInstallmentAmount(String sipFirstInstallmentAmount) {
			this.sipFirstInstallmentAmount = sipFirstInstallmentAmount;
		}

		public String getIsCSIP() {
			return isCSIP;
		}

		public void setIsCSIP(String isCSIP) {
			this.isCSIP = isCSIP;
		}

		public String getCsipInvestorDOB() {
			return csipInvestorDOB;
		}

		public void setCsipInvestorDOB(String csipInvestorDOB) {
			this.csipInvestorDOB = csipInvestorDOB;
		}

		public String getCsipInvestorGender() {
			return csipInvestorGender;
		}

		public void setCsipInvestorGender(String csipInvestorGender) {
			this.csipInvestorGender = csipInvestorGender;
		}

		public String getCsipNomineeName() {
			return csipNomineeName;
		}

		public void setCsipNomineeName(String csipNomineeName) {
			this.csipNomineeName = csipNomineeName;
		}

		public String getCsipNomineeDOB() {
			return csipNomineeDOB;
		}

		public void setCsipNomineeDOB(String csipNomineeDOB) {
			this.csipNomineeDOB = csipNomineeDOB;
		}

		public String getCsipNomineeRelation() {
			return csipNomineeRelation;
		}

		public void setCsipNomineeRelation(String csipNomineeRelation) {
			this.csipNomineeRelation = csipNomineeRelation;
		}

		public String getCsipGuardianName() {
			return csipGuardianName;
		}

		public void setCsipGuardianName(String csipGuardianName) {
			this.csipGuardianName = csipGuardianName;
		}

		public String getCsipIsMinor() {
			return csipIsMinor;
		}

		public void setCsipIsMinor(String csipIsMinor) {
			this.csipIsMinor = csipIsMinor;
		}

		public String getSipDate() {
			return sipDate;
		}

		public void setSipDate(String sipDate) {
			this.sipDate = sipDate;
		}

		public String getSipTransType() {
			return sipTransType;
		}

		public void setSipTransType(String sipTransType) {
			this.sipTransType = sipTransType;
		}

		public String getFromMonth() {
			return fromMonth;
		}

		public void setFromMonth(String fromMonth) {
			this.fromMonth = fromMonth;
		}

		public String getFromYear() {
			return fromYear;
		}

		public void setFromYear(String fromYear) {
			this.fromYear = fromYear;
		}

		public String getToMonth() {
			return toMonth;
		}

		public void setToMonth(String toMonth) {
			this.toMonth = toMonth;
		}

		public String getToYear() {
			return toYear;
		}

		public void setToYear(String toYear) {
			this.toYear = toYear;
		}

		public String getInvstmtDate_Value() {
			return invstmtDate_Value;
		}

		public void setInvstmtDate_Value(String invstmtDate_Value) {
			this.invstmtDate_Value = invstmtDate_Value;
		}

		public String getWeeklyInvstmtDate_Value() {
			return weeklyInvstmtDate_Value;
		}

		public void setWeeklyInvstmtDate_Value(String weeklyInvstmtDate_Value) {
			this.weeklyInvstmtDate_Value = weeklyInvstmtDate_Value;
		}

		public String getDbIsLiquid() {
			return dbIsLiquid;
		}

		public void setDbIsLiquid(String dbIsLiquid) {
			this.dbIsLiquid = dbIsLiquid;
		}

		public String getDbNFO() {
			return dbNFO;
		}

		public void setDbNFO(String dbNFO) {
			this.dbNFO = dbNFO;
		}

		public String getDbSIPInvestmentAmount() {
			return dbSIPInvestmentAmount;
		}

		public void setDbSIPInvestmentAmount(String dbSIPInvestmentAmount) {
			this.dbSIPInvestmentAmount = dbSIPInvestmentAmount;
		}

		public String getDbSip_Weekly_Inv_Min() {
			return dbSip_Weekly_Inv_Min;
		}

		public void setDbSip_Weekly_Inv_Min(String dbSip_Weekly_Inv_Min) {
			this.dbSip_Weekly_Inv_Min = dbSip_Weekly_Inv_Min;
		}

		public String getDbSip_Weekly_Inv_Max() {
			return dbSip_Weekly_Inv_Max;
		}

		public void setDbSip_Weekly_Inv_Max(String dbSip_Weekly_Inv_Max) {
			this.dbSip_Weekly_Inv_Max = dbSip_Weekly_Inv_Max;
		}

		public String getDbFirst_Int_Min_Amount() {
			return dbFirst_Int_Min_Amount;
		}

		public void setDbFirst_Int_Min_Amount(String dbFirst_Int_Min_Amount) {
			this.dbFirst_Int_Min_Amount = dbFirst_Int_Min_Amount;
		}

		public String getDbFirst_Int_Max_Amount() {
			return dbFirst_Int_Max_Amount;
		}

		public void setDbFirst_Int_Max_Amount(String dbFirst_Int_Max_Amount) {
			this.dbFirst_Int_Max_Amount = dbFirst_Int_Max_Amount;
		}

		public String getDbConfirmSchName() {
			return dbConfirmSchName;
		}

		public void setDbConfirmSchName(String dbConfirmSchName) {
			this.dbConfirmSchName = dbConfirmSchName;
		}

		public String getScheme_category() {
			return scheme_category;
		}

		public void setScheme_category(String scheme_category) {
			this.scheme_category = scheme_category;
		}

		public String getDbSchemeCodeDBSchemeCode() {
			return dbSchemeCodeDBSchemeCode;
		}

		public void setDbSchemeCodeDBSchemeCode(String dbSchemeCodeDBSchemeCode) {
			this.dbSchemeCodeDBSchemeCode = dbSchemeCodeDBSchemeCode;
		}

		public String getDbSchemeCode() {
			return dbSchemeCode;
		}

		public void setDbSchemeCode(String dbSchemeCode) {
			this.dbSchemeCode = dbSchemeCode;
		}

		public String getDbIsDirectScheme() {
			return dbIsDirectScheme;
		}

		public void setDbIsDirectScheme(String dbIsDirectScheme) {
			this.dbIsDirectScheme = dbIsDirectScheme;
		}
		
	}
	
	}

}
