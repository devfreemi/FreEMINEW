package com.freemi.entity.Birla;

import java.io.Serializable;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SavePreSIPMultipleSchemesInput implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("PreSIPMultipleSchemes")
	private PreSIPMultipleSchemes preSIPMultipleSchemesObject;
	
	

	public PreSIPMultipleSchemes getPreSIPMultipleSchemesObject() {
		return preSIPMultipleSchemesObject;
	}
	public void setPreSIPMultipleSchemesObject(PreSIPMultipleSchemes preSIPMultipleSchemesObject) {
		this.preSIPMultipleSchemesObject = preSIPMultipleSchemesObject;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public static class PreSIPMultipleSchemes {
		
		@JsonProperty("UserId")
		private String userId;
		
		@JsonProperty("Password")
		private String password;
		
		@JsonProperty("AuthCode")
		private String authCode="";
		
		@JsonProperty("PrimeFolioNo")
		private String primeFolioNo;
		
		@JsonProperty("ActiveFolioNo")
		private String activeFolioNo;
		
		@JsonProperty("TotalFistInstallmentAmount")
		private String totalFistInstallmentAmount="";
		
		@JsonProperty("BankName")
		private String bankName;
		
		@JsonProperty("BankCity")
		private String bankCity;
		
		@JsonProperty("BankBranch")
		private String bankBranch;
		
		@JsonProperty("IFSCCode")
		private String ifscCode;
		
		@JsonProperty("AcctNumber")
		private String acctNumber;
		
		@JsonProperty("AcctType")
		private String acctType;
		
		@JsonProperty("UsPerson")
		private String usPerson;
		
		@JsonProperty("DeclarationCheck")
		private String declarationCheck;
		
		@JsonProperty("OTMorBiller")
		private String otmOrBiller=null;
		
		@JsonProperty("PaymentType")
		private String paymentType=null;
		
		@JsonProperty("PaymentBankName")
		private String paymentBankName=null;
		
		@JsonProperty("Paymentgateway")
		private String paymentgateway=null;
		
		@JsonProperty("AdditionalInfo")
		private String additionalInfo=null;
		
		@JsonProperty("externalReferenceNo")
		private String ExternalReferenceNo;
		
		@JsonProperty("NEFTTransRefNo")
		private String neftTransRefNo = null;
		
		@JsonProperty("Goal")
		private String goal="";
		
		@JsonProperty("IsNEFT")
		private String isNEFT = null;
		
		@JsonProperty("Image")
		private String image = null;
		
		@JsonProperty("ImageType")
		private String imageType = null;
		
		@JsonProperty("ImageSize")
		private String imageSize = null;
		
		@JsonProperty("MarchantCode")
		private String marchantCode = null;
		
		@JsonProperty("SchemeDetails")
		ArrayList<SchemeDetails> schemeDetails = new ArrayList<SchemeDetails>();
		
		@JsonProperty("BrokerCode")
		private String brokerCode;
		
		@JsonProperty("SubBrokerCode")
		private String subBrokerCode;
		
		@JsonProperty("EUIN")
		private String euin;
		
		@JsonProperty("ISFirstInstallment")
		private String isFirstInstallment;
		
		@JsonProperty("UserName")
		private String userName;
		
		@JsonProperty("TransRefNo")
		private String transRefNo;
		
		@JsonProperty("AdvisorRefNo")
		private String advisorRefNo;
		
		@JsonProperty("PaymentReferencNo")
		private String paymentReferencNo;
		
		
		

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

		public String getAuthCode() {
			return authCode;
		}

		public void setAuthCode(String authCode) {
			this.authCode = authCode;
		}

		public String getPrimeFolioNo() {
			return primeFolioNo;
		}

		public void setPrimeFolioNo(String primeFolioNo) {
			this.primeFolioNo = primeFolioNo;
		}

		public String getActiveFolioNo() {
			return activeFolioNo;
		}

		public void setActiveFolioNo(String activeFolioNo) {
			this.activeFolioNo = activeFolioNo;
		}

		public String getTotalFistInstallmentAmount() {
			return totalFistInstallmentAmount;
		}

		public void setTotalFistInstallmentAmount(String totalFistInstallmentAmount) {
			this.totalFistInstallmentAmount = totalFistInstallmentAmount;
		}

		public String getBankName() {
			return bankName;
		}

		public void setBankName(String bankName) {
			this.bankName = bankName;
		}

		public String getBankCity() {
			return bankCity;
		}

		public void setBankCity(String bankCity) {
			this.bankCity = bankCity;
		}

		public String getBankBranch() {
			return bankBranch;
		}

		public void setBankBranch(String bankBranch) {
			this.bankBranch = bankBranch;
		}

		public String getIfscCode() {
			return ifscCode;
		}

		public void setIfscCode(String ifscCode) {
			this.ifscCode = ifscCode;
		}		public String getAcctNumber() {
			return acctNumber;
		}

		public void setAcctNumber(String acctNumber) {
			this.acctNumber = acctNumber;
		}		public String getAcctType() {
			return acctType;
		}

		public void setAcctType(String acctType) {
			this.acctType = acctType;
		}

		public String getUsPerson() {
			return usPerson;
		}

		public void setUsPerson(String usPerson) {
			this.usPerson = usPerson;
		}

		public String getDeclarationCheck() {
			return declarationCheck;
		}

		public void setDeclarationCheck(String declarationCheck) {
			this.declarationCheck = declarationCheck;
		}

		public String getOtmOrBiller() {
			return otmOrBiller;
		}

		public void setOtmOrBiller(String otmOrBiller) {
			this.otmOrBiller = otmOrBiller;
		}

		public String getPaymentType() {
			return paymentType;
		}

		public void setPaymentType(String paymentType) {
			this.paymentType = paymentType;
		}

		public String getPaymentBankName() {
			return paymentBankName;
		}

		public void setPaymentBankName(String paymentBankName) {
			this.paymentBankName = paymentBankName;
		}

		public String getPaymentgateway() {
			return paymentgateway;
		}

		public void setPaymentgateway(String paymentgateway) {
			this.paymentgateway = paymentgateway;
		}

		public String getAdditionalInfo() {
			return additionalInfo;
		}

		public void setAdditionalInfo(String additionalInfo) {
			this.additionalInfo = additionalInfo;
		}

		public String getExternalReferenceNo() {
			return ExternalReferenceNo;
		}

		public void setExternalReferenceNo(String externalReferenceNo) {
			ExternalReferenceNo = externalReferenceNo;
		}

		public String getNeftTransRefNo() {
			return neftTransRefNo;
		}

		public void setNeftTransRefNo(String neftTransRefNo) {
			this.neftTransRefNo = neftTransRefNo;
		}

		public String getGoal() {
			return goal;
		}

		public void setGoal(String goal) {
			this.goal = goal;
		}

		public String getIsNEFT() {
			return isNEFT;
		}

		public void setIsNEFT(String isNEFT) {
			this.isNEFT = isNEFT;
		}

		public String getImage() {
			return image;
		}

		public void setImage(String image) {
			this.image = image;
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

		public String getMarchantCode() {
			return marchantCode;
		}

		public void setMarchantCode(String marchantCode) {
			this.marchantCode = marchantCode;
		}


		public ArrayList<SchemeDetails> getSchemeDetails() {
			return schemeDetails;
		}

		public void setSchemeDetails(ArrayList<SchemeDetails> schemeDetails) {
			this.schemeDetails = schemeDetails;
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

		public String getIsFirstInstallment() {
			return isFirstInstallment;
		}

		public void setIsFirstInstallment(String isFirstInstallment) {
			this.isFirstInstallment = isFirstInstallment;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getTransRefNo() {
			return transRefNo;
		}

		public void setTransRefNo(String transRefNo) {
			this.transRefNo = transRefNo;
		}

		public String getAdvisorRefNo() {
			return advisorRefNo;
		}

		public void setAdvisorRefNo(String advisorRefNo) {
			this.advisorRefNo = advisorRefNo;
		}

		public String getPaymentReferencNo() {
			return paymentReferencNo;
		}

		public void setPaymentReferencNo(String paymentReferencNo) {
			this.paymentReferencNo = paymentReferencNo;
		}

		public static class SchemeDetails{
			
			@JsonProperty("SchemeCode")
			private String schemeCode;
			
			@JsonProperty("SchemeOption")
			private String schemeOption;
			
			@JsonProperty("SIPInvAmt")
			private String sipInvAmt;
			
			@JsonProperty("Frequency")
			private String frequency;
			
			@JsonProperty("InvestmentDate")
			private String investmentDate;
			
			@JsonProperty("SIPFromPeriod")
			private String sipFromPeriod;
			
			@JsonProperty("SIPToPeriod")
			private String sipToPeriod;
			
			@JsonProperty("SipWeekDay")
			private String sipWeekDay;
			
			@JsonProperty("FirstInstallmentAmount")
			private String firstInstallmentAmount;
			
			@JsonProperty("SchemeReferenceNo")
			private String schemeReferenceNo=null;
			
			@JsonProperty("ISCSIP")
			private String isCSIP=null;
			
			@JsonProperty("InvestorDOB")
			private String investorDOB=null;
			
			@JsonProperty("InvestorGender")
			private String investorGender=null;
			
			@JsonProperty("NomineeName")
			private String nomineeName=null;
			
			@JsonProperty("NomineeDOB")
			private String nomineeDOB=null;
			
			@JsonProperty("NomineeRelation")
			private String nomineeRelation=null;
			
			@JsonProperty("GuardianName")
			private String guardianName=null;
			
			@JsonProperty("UDP")
			private String udp = null;
			
			@JsonProperty("IsMinor")
			private String isMinor;

			public String getSchemeCode() {
				return schemeCode;
			}

			public void setSchemeCode(String schemeCode) {
				this.schemeCode = schemeCode;
			}

			public String getSchemeOption() {
				return schemeOption;
			}

			public void setSchemeOption(String schemeOption) {
				this.schemeOption = schemeOption;
			}

			public String getSipInvAmt() {
				return sipInvAmt;
			}

			public void setSipInvAmt(String sipInvAmt) {
				this.sipInvAmt = sipInvAmt;
			}

			public String getFrequency() {
				return frequency;
			}

			public void setFrequency(String frequency) {
				this.frequency = frequency;
			}

			public String getInvestmentDate() {
				return investmentDate;
			}

			public void setInvestmentDate(String investmentDate) {
				this.investmentDate = investmentDate;
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

			public String getFirstInstallmentAmount() {
				return firstInstallmentAmount;
			}

			public void setFirstInstallmentAmount(String firstInstallmentAmount) {
				this.firstInstallmentAmount = firstInstallmentAmount;
			}

			public String getSchemeReferenceNo() {
				return schemeReferenceNo;
			}

			public void setSchemeReferenceNo(String schemeReferenceNo) {
				this.schemeReferenceNo = schemeReferenceNo;
			}

			public String getIsCSIP() {
				return isCSIP;
			}

			public void setIsCSIP(String isCSIP) {
				this.isCSIP = isCSIP;
			}

			public String getInvestorDOB() {
				return investorDOB;
			}

			public void setInvestorDOB(String investorDOB) {
				this.investorDOB = investorDOB;
			}

			public String getInvestorGender() {
				return investorGender;
			}

			public void setInvestorGender(String investorGender) {
				this.investorGender = investorGender;
			}

			public String getNomineeName() {
				return nomineeName;
			}

			public void setNomineeName(String nomineeName) {
				this.nomineeName = nomineeName;
			}

			public String getNomineeDOB() {
				return nomineeDOB;
			}

			public void setNomineeDOB(String nomineeDOB) {
				this.nomineeDOB = nomineeDOB;
			}

			public String getNomineeRelation() {
				return nomineeRelation;
			}

			public void setNomineeRelation(String nomineeRelation) {
				this.nomineeRelation = nomineeRelation;
			}

			public String getGuardianName() {
				return guardianName;
			}

			public void setGuardianName(String guardianName) {
				this.guardianName = guardianName;
			}

			public String getUdp() {
				return udp;
			}

			public void setUdp(String udp) {
				this.udp = udp;
			}

			public String getIsMinor() {
				return isMinor;
			}

			public void setIsMinor(String isMinor) {
				this.isMinor = isMinor;
			}
			
			
		}

	}

}
