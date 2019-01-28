package com.freemi.entity.birla;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetSchemeMasterDetailsOutput {

	@JsonProperty("ReturnCode")
	private String returnCode;
	
	@JsonProperty("ReturnMsg")
	private String returnMsg;
	
	@JsonProperty("SchemeDetails")
	ArrayList<Object> schemeDetails = new ArrayList<Object>();
	
	@JsonProperty("TransType")
	private String transType = null;
	
	

	public String getReturnCode() {
		return returnCode;
	}


	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}



	public String getReturnMsg() {
		return returnMsg;
	}



	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}



	public ArrayList<Object> getSchemeDetails() {
		return schemeDetails;
	}



	public void setSchemeDetails(ArrayList<Object> schemeDetails) {
		this.schemeDetails = schemeDetails;
	}



	public String getTransType() {
		return transType;
	}



	public void setTransType(String transType) {
		this.transType = transType;
	}



	public static class SchemeDetails{

		@JsonProperty("Active")
		private String active;
		
		@JsonProperty("AssetClass")
		private String assetClass;
		
		@JsonProperty("Category")
		private String category;
		
		@JsonProperty("Dividend")
		private String dividend;
		
		@JsonProperty("DividendFlag")
		private String dividendFlag;
		
		@JsonProperty("EndDate")
		private String endDate;
		
		@JsonProperty("FundName")
		private String fundName;
		
		@JsonProperty("FundType")
		private String fundType;
		
		@JsonProperty("IsCSIPApplicable")
		private String isCSIPApplicable;
		
		@JsonProperty("IsDirect")
		private String isDirect;
		
		@JsonProperty("IsETF")
		private String isETF;
		
		@JsonProperty("IsInstantRedeem")
		private String isInstantRedeem;
		
		@JsonProperty("IsLiquid")
		private String isLiquid;
		
		@JsonProperty("MIN_ADD_INV")
		private String min_ADD_INV;
		
		@JsonProperty("MIN_INV")
		private String min_INV;
		
		@JsonProperty("MaxInvAmt")
		private String maxInvAmt;
		
		@JsonProperty("NFO")
		private String nfo;
		
		@JsonProperty("RTACode")
		private String rtaCode;
		
		@JsonProperty("SCHEME_CODE")
		private String scheme_CODE;
		
		@JsonProperty("SCHEME_NAME")
		private String scheme_NAME;
		
		@JsonProperty("SCHEME_OPTION")
		private String scheme_OPTION;
		
		@JsonProperty("SIPActive")
		private String sipActive;
		
		@JsonProperty("SIPMin_INV")
		private String sipMin_INV;
		
		@JsonProperty("STPFrom")
		private String stpFrom;
		
		@JsonProperty("STPTo")
		private String stpTo;
		
		@JsonProperty("SWPFlag")
		private String swpFlag;
		
		@JsonProperty("SolutionBucket")
		private String solutionBucket;
		
		@JsonProperty("StartDate")
		private String startDate;
		
		@JsonProperty("SubCategory")
		private String subCategory;
		
		@JsonProperty("SwitchFrom")
		private String switchFrom;
		
		@JsonProperty("SwitchTo")
		private String switchTo;
		
		@JsonProperty("TriggerFrom")
		private String triggerFrom;
		
		@JsonProperty("TriggerTo")
		private String triggerTo;
		
		@JsonProperty("Fundcode")
		private String fundcode;
		
		@JsonProperty("Frequency")
		private String frequency;

		public String getActive() {
			return active;
		}

		public void setActive(String active) {
			this.active = active;
		}

		public String getAssetClass() {
			return assetClass;
		}

		public void setAssetClass(String assetClass) {
			this.assetClass = assetClass;
		}

		public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = category;
		}

		public String getDividend() {
			return dividend;
		}

		public void setDividend(String dividend) {
			this.dividend = dividend;
		}

		public String getDividendFlag() {
			return dividendFlag;
		}

		public void setDividendFlag(String dividendFlag) {
			this.dividendFlag = dividendFlag;
		}

		public String getEndDate() {
			return endDate;
		}

		public void setEndDate(String endDate) {
			this.endDate = endDate;
		}

		public String getFundName() {
			return fundName;
		}

		public void setFundName(String fundName) {
			this.fundName = fundName;
		}

		public String getFundType() {
			return fundType;
		}

		public void setFundType(String fundType) {
			this.fundType = fundType;
		}

		public String getIsCSIPApplicable() {
			return isCSIPApplicable;
		}

		public void setIsCSIPApplicable(String isCSIPApplicable) {
			this.isCSIPApplicable = isCSIPApplicable;
		}

		public String getIsDirect() {
			return isDirect;
		}

		public void setIsDirect(String isDirect) {
			this.isDirect = isDirect;
		}

		public String getIsETF() {
			return isETF;
		}

		public void setIsETF(String isETF) {
			this.isETF = isETF;
		}

		public String getIsInstantRedeem() {
			return isInstantRedeem;
		}

		public void setIsInstantRedeem(String isInstantRedeem) {
			this.isInstantRedeem = isInstantRedeem;
		}

		public String getIsLiquid() {
			return isLiquid;
		}

		public void setIsLiquid(String isLiquid) {
			this.isLiquid = isLiquid;
		}

		public String getMin_ADD_INV() {
			return min_ADD_INV;
		}

		public void setMin_ADD_INV(String min_ADD_INV) {
			this.min_ADD_INV = min_ADD_INV;
		}

		public String getMin_INV() {
			return min_INV;
		}

		public void setMin_INV(String min_INV) {
			this.min_INV = min_INV;
		}

		public String getMaxInvAmt() {
			return maxInvAmt;
		}

		public void setMaxInvAmt(String maxInvAmt) {
			this.maxInvAmt = maxInvAmt;
		}

		public String getNfo() {
			return nfo;
		}

		public void setNfo(String nfo) {
			this.nfo = nfo;
		}

		public String getRtaCode() {
			return rtaCode;
		}

		public void setRtaCode(String rtaCode) {
			this.rtaCode = rtaCode;
		}

		public String getScheme_CODE() {
			return scheme_CODE;
		}

		public void setScheme_CODE(String scheme_CODE) {
			this.scheme_CODE = scheme_CODE;
		}

		public String getScheme_NAME() {
			return scheme_NAME;
		}

		public void setScheme_NAME(String scheme_NAME) {
			this.scheme_NAME = scheme_NAME;
		}

		public String getScheme_OPTION() {
			return scheme_OPTION;
		}

		public void setScheme_OPTION(String scheme_OPTION) {
			this.scheme_OPTION = scheme_OPTION;
		}

		public String getSipActive() {
			return sipActive;
		}

		public void setSipActive(String sipActive) {
			this.sipActive = sipActive;
		}

		public String getSipMin_INV() {
			return sipMin_INV;
		}

		public void setSipMin_INV(String sipMin_INV) {
			this.sipMin_INV = sipMin_INV;
		}

		public String getStpFrom() {
			return stpFrom;
		}

		public void setStpFrom(String stpFrom) {
			this.stpFrom = stpFrom;
		}

		public String getStpTo() {
			return stpTo;
		}

		public void setStpTo(String stpTo) {
			this.stpTo = stpTo;
		}

		public String getSwpFlag() {
			return swpFlag;
		}

		public void setSwpFlag(String swpFlag) {
			this.swpFlag = swpFlag;
		}

		public String getSolutionBucket() {
			return solutionBucket;
		}

		public void setSolutionBucket(String solutionBucket) {
			this.solutionBucket = solutionBucket;
		}

		public String getStartDate() {
			return startDate;
		}

		public void setStartDate(String startDate) {
			this.startDate = startDate;
		}

		public String getSubCategory() {
			return subCategory;
		}

		public void setSubCategory(String subCategory) {
			this.subCategory = subCategory;
		}

		public String getSwitchFrom() {
			return switchFrom;
		}

		public void setSwitchFrom(String switchFrom) {
			this.switchFrom = switchFrom;
		}

		public String getSwitchTo() {
			return switchTo;
		}

		public void setSwitchTo(String switchTo) {
			this.switchTo = switchTo;
		}

		public String getTriggerFrom() {
			return triggerFrom;
		}

		public void setTriggerFrom(String triggerFrom) {
			this.triggerFrom = triggerFrom;
		}

		public String getTriggerTo() {
			return triggerTo;
		}

		public void setTriggerTo(String triggerTo) {
			this.triggerTo = triggerTo;
		}

		public String getFundcode() {
			return fundcode;
		}

		public void setFundcode(String fundcode) {
			this.fundcode = fundcode;
		}

		public String getFrequency() {
			return frequency;
		}

		public void setFrequency(String frequency) {
			this.frequency = frequency;
		}
		
		
		
		
	}
}
