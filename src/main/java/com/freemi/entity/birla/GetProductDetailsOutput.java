package com.freemi.entity.birla;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetProductDetailsOutput {

	@JsonProperty("ReturnCode")
	private String returnCode;
	
	@JsonProperty("ReturnMsg")
	private String returnMsg;
	
	@JsonProperty("UDP")
	private String udp = null;
	
	@JsonProperty("SchemeDetails")
	ArrayList<Object> schemeDetails = new ArrayList<Object>();

	
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



	public String getUdp() {
		return udp;
	}



	public void setUdp(String udp) {
		this.udp = udp;
	}



	public ArrayList<Object> getSchemeDetails() {
		return schemeDetails;
	}



	public void setSchemeDetails(ArrayList<Object> schemeDetails) {
		this.schemeDetails = schemeDetails;
	}



	public static class SchemeDetails{
		
		@JsonProperty("AUM")
		private float aum;
		
		@JsonProperty("AUMDate")
		private String aumDate;
		
		@JsonProperty("Active")
		private String active;
		
		@JsonProperty("Adjusted_Nav")
		private float adjusted_Nav;
		
		@JsonProperty("AssetClass")
		private String assetClass;
		
		@JsonProperty("BenchMark")
		private String benchMark;
		
		@JsonProperty("Category")
		private String category;
		
		@JsonProperty("Dividend")
		private String dividend;
		
		@JsonProperty("DividendFlag")
		private String dividendFlag;
		
		@JsonProperty("EndDate")
		private String endDate;
		
		@JsonProperty("ExitLoad")
		private String exitLoad;
		
		@JsonProperty("FundHighlights")
		private String fundHighlights;
		
		@JsonProperty("FundName")
		private String fundName;
		
		@JsonProperty("FundType")
		private String fundType;
		
		@JsonProperty("InceptionDate")
		private String inceptionDate;
		
		@JsonProperty("Index_Id")
		private float index_Id;
		
		@JsonProperty("InvestmentObjective")
		private String investmentObjective;
		
		@JsonProperty("InvestorSuitability")
		private String investorSuitability;
		
		@JsonProperty("IsClosedEnded")
		private String isClosedEnded;
		
		@JsonProperty("IsDirect")
		private String isDirect;
		
		@JsonProperty("IsETF")
		private String isETF;
		
		@JsonProperty("IsFocused")
		private String isFocused;
		
		@JsonProperty("IsLiquid")
		private String isLiquid;
		
		@JsonProperty("IsPrimary")
		private String isPrimary;
		
		@JsonProperty("LastDividend")
		private float lastDividend;
		
		@JsonProperty("LoadComment")
		private String loadComment;
		
		@JsonProperty("MIN_INV")
		private String min_INV;
		
		@JsonProperty("ManagerList")
		ArrayList<Object> managerList = new ArrayList<Object>();
		
		@JsonProperty("MaturityDate")
		private String maturityDate;
		
		@JsonProperty("MaxInvAmt")
		private String maxInvAmt;
		
		@JsonProperty("NAV")
		private float nav;
		
		@JsonProperty("NAV_Change")
		private float nav_Change;
		
		@JsonProperty("NFO")
		private String nfo;
		
		@JsonProperty("Nav_Date")
		private String nav_Date;
		
		@JsonProperty("PReturn_10Y")
		private float preturn_10Y;
		
		@JsonProperty("PReturn_15Y")
		private float preturn_15Y;
		
		@JsonProperty("PReturn_1D")
		private float preturn_1D;
		
		@JsonProperty("PReturn_1M")
		private float preturn_1M;
		
		@JsonProperty("PReturn_1W")
		private float preturn_1W;
		
		@JsonProperty("PReturn_1Y")
		private float preturn_1Y;
		
		@JsonProperty("PReturn_20Y")
		private float preturn_20Y;
		
		@JsonProperty("PReturn_2Y")
		private float preturn_2Y;
		
		@JsonProperty("PReturn_3M")
		private float preturn_3M;
		
		@JsonProperty("PReturn_3Y")
		private float preturn_3Y;
		
		@JsonProperty("PReturn_5Y")
		private float preturn_5Y;
		
		@JsonProperty("PReturn_6M")
		private float preturn_6M;
		
		@JsonProperty("PReturn_7Y")
		private float preturn_7Y;
		
		@JsonProperty("PReturn_Inception")
		private float preturn_Inception;
		
		@JsonProperty("Preturn_YTD")
		private float preturn_YTD;
		
		@JsonProperty("Primary_Key")
		private String primary_Key;
		
		@JsonProperty("RTACode")
		private String rtaCode;
		
		@JsonProperty("RegularORDirect")
		private String regularORDirect;
		
		@JsonProperty("RelatedSchemes")
		private String relatedSchemes;
		
		@JsonProperty("Riskometer_value")
		private String riskometer_value;
		
		@JsonProperty("SCHEME_CODE")
		private String scheme_code;
		
		@JsonProperty("SCHEME_NAME")
		private String scheme_name;
		
		@JsonProperty("SCHEME_OPTION")
		private String scheme_option;
		
		@JsonProperty("SCHEME_SHORT_NAME")
		private String scheme_short_name;
		
		@JsonProperty("SIPActive")
		private String sipActive;
		
		@JsonProperty("SIPFlag")
		private String sipFlag;
		
		@JsonProperty("SIPMin_INV")
		private String sipMin_INV;
		
		@JsonProperty("SIPMin_INV_Q")
		private String sipMin_INV_Q;
		
		@JsonProperty("SIP_SCHEME_OPTION")
		private String sip_SCHEME_OPTION;
		
		@JsonProperty("Sip_Weekly_Inv_Max")
		private String sip_Weekly_Inv_Max;
		
		@JsonProperty("Sip_Weekly_Inv_Min")
		private String sip_Weekly_Inv_Min;
		
		@JsonProperty("Solution_Bucket")
		private String solution_Bucket;
		
		@JsonProperty("StartDate")
		private String startDate;
		
		@JsonProperty("Sub_Category")
		private String sub_Category;
		
		@JsonProperty("SwitchToDate")
		private String switchToDate;
		
		@JsonProperty("UpdatedDate")
		private String updatedDate;
		
		@JsonProperty("Weekly_Inv_Max")
		private String weekly_Inv_Max;
		
		@JsonProperty("Weekly_Inv_Min")
		private String weekly_Inv_Min;
		
		@JsonProperty("changeDividendFlag")
		private String changeDividendFlag;
		
		@JsonProperty("isCSIPAvailable")
		private String isCSIPAvailable;
		
		@JsonProperty("Fundcode")
		private String fundcode;
		
		@JsonProperty("Frequency")
		private String frequency;
		
		
		

		public float getAum() {
			return aum;
		}




		public void setAum(float aum) {
			this.aum = aum;
		}




		public String getAumDate() {
			return aumDate;
		}




		public void setAumDate(String aumDate) {
			this.aumDate = aumDate;
		}




		public String getActive() {
			return active;
		}




		public void setActive(String active) {
			this.active = active;
		}




		public float getAdjusted_Nav() {
			return adjusted_Nav;
		}




		public void setAdjusted_Nav(float adjusted_Nav) {
			this.adjusted_Nav = adjusted_Nav;
		}




		public String getAssetClass() {
			return assetClass;
		}




		public void setAssetClass(String assetClass) {
			this.assetClass = assetClass;
		}




		public String getBenchMark() {
			return benchMark;
		}




		public void setBenchMark(String benchMark) {
			this.benchMark = benchMark;
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




		public String getExitLoad() {
			return exitLoad;
		}




		public void setExitLoad(String exitLoad) {
			this.exitLoad = exitLoad;
		}




		public String getFundHighlights() {
			return fundHighlights;
		}




		public void setFundHighlights(String fundHighlights) {
			this.fundHighlights = fundHighlights;
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




		public String getInceptionDate() {
			return inceptionDate;
		}




		public void setInceptionDate(String inceptionDate) {
			this.inceptionDate = inceptionDate;
		}




		public float getIndex_Id() {
			return index_Id;
		}




		public void setIndex_Id(float index_Id) {
			this.index_Id = index_Id;
		}




		public String getInvestmentObjective() {
			return investmentObjective;
		}




		public void setInvestmentObjective(String investmentObjective) {
			this.investmentObjective = investmentObjective;
		}




		public String getInvestorSuitability() {
			return investorSuitability;
		}




		public void setInvestorSuitability(String investorSuitability) {
			this.investorSuitability = investorSuitability;
		}




		public String getIsClosedEnded() {
			return isClosedEnded;
		}




		public void setIsClosedEnded(String isClosedEnded) {
			this.isClosedEnded = isClosedEnded;
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




		public String getIsFocused() {
			return isFocused;
		}




		public void setIsFocused(String isFocused) {
			this.isFocused = isFocused;
		}




		public String getIsLiquid() {
			return isLiquid;
		}




		public void setIsLiquid(String isLiquid) {
			this.isLiquid = isLiquid;
		}




		public String getIsPrimary() {
			return isPrimary;
		}




		public void setIsPrimary(String isPrimary) {
			this.isPrimary = isPrimary;
		}




		public float getLastDividend() {
			return lastDividend;
		}




		public void setLastDividend(float lastDividend) {
			this.lastDividend = lastDividend;
		}




		public String getLoadComment() {
			return loadComment;
		}




		public void setLoadComment(String loadComment) {
			this.loadComment = loadComment;
		}




		public String getMin_INV() {
			return min_INV;
		}




		public void setMin_INV(String min_INV) {
			this.min_INV = min_INV;
		}




		public ArrayList<Object> getManagerList() {
			return managerList;
		}




		public void setManagerList(ArrayList<Object> managerList) {
			this.managerList = managerList;
		}




		public String getMaturityDate() {
			return maturityDate;
		}




		public void setMaturityDate(String maturityDate) {
			this.maturityDate = maturityDate;
		}




		public String getMaxInvAmt() {
			return maxInvAmt;
		}




		public void setMaxInvAmt(String maxInvAmt) {
			this.maxInvAmt = maxInvAmt;
		}




		public float getNav() {
			return nav;
		}




		public void setNav(float nav) {
			this.nav = nav;
		}




		public float getNav_Change() {
			return nav_Change;
		}




		public void setNav_Change(float nav_Change) {
			this.nav_Change = nav_Change;
		}




		public String getNfo() {
			return nfo;
		}




		public void setNfo(String nfo) {
			this.nfo = nfo;
		}




		public String getNav_Date() {
			return nav_Date;
		}




		public void setNav_Date(String nav_Date) {
			this.nav_Date = nav_Date;
		}




		public float getPreturn_10Y() {
			return preturn_10Y;
		}




		public void setPreturn_10Y(float preturn_10y) {
			preturn_10Y = preturn_10y;
		}




		public float getPreturn_15Y() {
			return preturn_15Y;
		}




		public void setPreturn_15Y(float preturn_15y) {
			preturn_15Y = preturn_15y;
		}




		public float getPreturn_1D() {
			return preturn_1D;
		}




		public void setPreturn_1D(float preturn_1d) {
			preturn_1D = preturn_1d;
		}




		public float getPreturn_1M() {
			return preturn_1M;
		}




		public void setPreturn_1M(float preturn_1m) {
			preturn_1M = preturn_1m;
		}




		public float getPreturn_1W() {
			return preturn_1W;
		}




		public void setPreturn_1W(float preturn_1w) {
			preturn_1W = preturn_1w;
		}




		public float getPreturn_1Y() {
			return preturn_1Y;
		}




		public void setPreturn_1Y(float preturn_1y) {
			preturn_1Y = preturn_1y;
		}




		public float getPreturn_20Y() {
			return preturn_20Y;
		}




		public void setPreturn_20Y(float preturn_20y) {
			preturn_20Y = preturn_20y;
		}




		public float getPreturn_2Y() {
			return preturn_2Y;
		}




		public void setPreturn_2Y(float preturn_2y) {
			preturn_2Y = preturn_2y;
		}




		public float getPreturn_3M() {
			return preturn_3M;
		}




		public void setPreturn_3M(float preturn_3m) {
			preturn_3M = preturn_3m;
		}




		public float getPreturn_3Y() {
			return preturn_3Y;
		}




		public void setPreturn_3Y(float preturn_3y) {
			preturn_3Y = preturn_3y;
		}




		public float getPreturn_5Y() {
			return preturn_5Y;
		}




		public void setPreturn_5Y(float preturn_5y) {
			preturn_5Y = preturn_5y;
		}




		public float getPreturn_6M() {
			return preturn_6M;
		}




		public void setPreturn_6M(float preturn_6m) {
			preturn_6M = preturn_6m;
		}




		public float getPreturn_7Y() {
			return preturn_7Y;
		}




		public void setPreturn_7Y(float preturn_7y) {
			preturn_7Y = preturn_7y;
		}




		public float getPreturn_Inception() {
			return preturn_Inception;
		}




		public void setPreturn_Inception(float preturn_Inception) {
			this.preturn_Inception = preturn_Inception;
		}




		public float getPreturn_YTD() {
			return preturn_YTD;
		}




		public void setPreturn_YTD(float preturn_YTD) {
			this.preturn_YTD = preturn_YTD;
		}




		public String getPrimary_Key() {
			return primary_Key;
		}




		public void setPrimary_Key(String primary_Key) {
			this.primary_Key = primary_Key;
		}




		public String getRtaCode() {
			return rtaCode;
		}




		public void setRtaCode(String rtaCode) {
			this.rtaCode = rtaCode;
		}




		public String getRegularORDirect() {
			return regularORDirect;
		}




		public void setRegularORDirect(String regularORDirect) {
			this.regularORDirect = regularORDirect;
		}




		public String getRelatedSchemes() {
			return relatedSchemes;
		}




		public void setRelatedSchemes(String relatedSchemes) {
			this.relatedSchemes = relatedSchemes;
		}




		public String getRiskometer_value() {
			return riskometer_value;
		}




		public void setRiskometer_value(String riskometer_value) {
			this.riskometer_value = riskometer_value;
		}




		public String getScheme_code() {
			return scheme_code;
		}




		public void setScheme_code(String scheme_code) {
			this.scheme_code = scheme_code;
		}




		public String getScheme_name() {
			return scheme_name;
		}




		public void setScheme_name(String scheme_name) {
			this.scheme_name = scheme_name;
		}




		public String getScheme_option() {
			return scheme_option;
		}




		public void setScheme_option(String scheme_option) {
			this.scheme_option = scheme_option;
		}




		public String getScheme_short_name() {
			return scheme_short_name;
		}




		public void setScheme_short_name(String scheme_short_name) {
			this.scheme_short_name = scheme_short_name;
		}




		public String getSipActive() {
			return sipActive;
		}




		public void setSipActive(String sipActive) {
			this.sipActive = sipActive;
		}




		public String getSipFlag() {
			return sipFlag;
		}




		public void setSipFlag(String sipFlag) {
			this.sipFlag = sipFlag;
		}




		public String getSipMin_INV() {
			return sipMin_INV;
		}




		public void setSipMin_INV(String sipMin_INV) {
			this.sipMin_INV = sipMin_INV;
		}




		public String getSipMin_INV_Q() {
			return sipMin_INV_Q;
		}




		public void setSipMin_INV_Q(String sipMin_INV_Q) {
			this.sipMin_INV_Q = sipMin_INV_Q;
		}




		public String getSip_SCHEME_OPTION() {
			return sip_SCHEME_OPTION;
		}




		public void setSip_SCHEME_OPTION(String sip_SCHEME_OPTION) {
			this.sip_SCHEME_OPTION = sip_SCHEME_OPTION;
		}




		public String getSip_Weekly_Inv_Max() {
			return sip_Weekly_Inv_Max;
		}




		public void setSip_Weekly_Inv_Max(String sip_Weekly_Inv_Max) {
			this.sip_Weekly_Inv_Max = sip_Weekly_Inv_Max;
		}




		public String getSip_Weekly_Inv_Min() {
			return sip_Weekly_Inv_Min;
		}




		public void setSip_Weekly_Inv_Min(String sip_Weekly_Inv_Min) {
			this.sip_Weekly_Inv_Min = sip_Weekly_Inv_Min;
		}




		public String getSolution_Bucket() {
			return solution_Bucket;
		}




		public void setSolution_Bucket(String solution_Bucket) {
			this.solution_Bucket = solution_Bucket;
		}




		public String getStartDate() {
			return startDate;
		}




		public void setStartDate(String startDate) {
			this.startDate = startDate;
		}




		public String getSub_Category() {
			return sub_Category;
		}




		public void setSub_Category(String sub_Category) {
			this.sub_Category = sub_Category;
		}




		public String getSwitchToDate() {
			return switchToDate;
		}




		public void setSwitchToDate(String switchToDate) {
			this.switchToDate = switchToDate;
		}




		public String getUpdatedDate() {
			return updatedDate;
		}




		public void setUpdatedDate(String updatedDate) {
			this.updatedDate = updatedDate;
		}




		public String getWeekly_Inv_Max() {
			return weekly_Inv_Max;
		}




		public void setWeekly_Inv_Max(String weekly_Inv_Max) {
			this.weekly_Inv_Max = weekly_Inv_Max;
		}




		public String getWeekly_Inv_Min() {
			return weekly_Inv_Min;
		}




		public void setWeekly_Inv_Min(String weekly_Inv_Min) {
			this.weekly_Inv_Min = weekly_Inv_Min;
		}




		public String getChangeDividendFlag() {
			return changeDividendFlag;
		}




		public void setChangeDividendFlag(String changeDividendFlag) {
			this.changeDividendFlag = changeDividendFlag;
		}




		public String getIsCSIPAvailable() {
			return isCSIPAvailable;
		}




		public void setIsCSIPAvailable(String isCSIPAvailable) {
			this.isCSIPAvailable = isCSIPAvailable;
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




		public static class ManagerList{

			@JsonProperty("Manager_Name")
			private String manager_Name;
			
			@JsonProperty("Manager_Photo")
			private String manager_Photo;
			
			@JsonProperty("ManagingFromdate")
			private String managingFromdate = null;
			
			@JsonProperty("Total_Experience")
			private String total_Experience;

			public String getManager_Name() {
				return manager_Name;
			}

			public void setManager_Name(String manager_Name) {
				this.manager_Name = manager_Name;
			}

			public String getManager_Photo() {
				return manager_Photo;
			}

			public void setManager_Photo(String manager_Photo) {
				this.manager_Photo = manager_Photo;
			}

			public String getManagingFromdate() {
				return managingFromdate;
			}

			public void setManagingFromdate(String managingFromdate) {
				this.managingFromdate = managingFromdate;
			}

			public String getTotal_Experience() {
				return total_Experience;
			}

			public void setTotal_Experience(String total_Experience) {
				this.total_Experience = total_Experience;
			}
			
			
		}

	}

}
