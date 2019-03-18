package com.freemi.entity.database;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;


/**
 * The persistent class for the product_scheme_details database table.
 * 
 */
@Entity
@Table(name="product_scheme_details")
@NamedQuery(name="ProductSchemeDetail.findAll", query="SELECT p FROM ProductSchemeDetail p")
@Proxy(lazy=false)
public class ProductSchemeDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="product_sc_id")
	private Long productScId;
	
	@Column(name="active")
	private String active;
	
	@Column(name="adjusted_nav")
	private double adjusted_Nav;

	@Column(name="asset_class")
	private String assetClass;
	
	@Column(name="aum")
	private double aum;

	@Column(name="aum_date")
	private String AUMDate;
	
	@Column(name="bench_mark")
	private String benchMark;
	
	@Column(name="category")
	private String category;
	
	@Column(name="change_dividend_flag")
	private String changeDividendFlag;

	@Column(name="csip_first_int_max_amount")
	private double csipFirstIntMaxAmount;

	@Column(name="csip_first_int_min_amount")
	private double csipFirstIntMinAmount;
	
	@Column(name="csipmin_inv")
	private double CSIPMin_INV;
	
	@Column(name="csipmin_inv_q")
	private double CSIPMin_INV_Q;
	
	@Column(name="dividend")
	private String dividend;
	
	@Column(name="dividend_flag")
	private String dividendFlag;
	
	@Column(name="end_date")
	private String endDate;
	
	@Column(name="exit_load")
	private String exitLoad;
	
	@Column(name="first_int_min_amount")
	private double first_Int_Min_Amount;
	
	@Column(name="frequency")
	private String frequency;
	
	@Column(name="fund_code")
	private String fundCode;
	
	@Column(name="fund_highlights")
	private String fundHighlights;
	
	@Column(name="fund_name")
	private String fundName;
	
	@Column(name="fund_type")
	private String fundType;

	
	@Column(name="inception_date")
	private String inceptionDate;
	
	@Column(name="index_id")
	private int index_Id;
	
	@Column(name="investment_objective")
	private String investmentObjective;

	@Column(name="investor_suitability")
	private String investorSuitability;

	@Column(name="is_closed_ended")
	private String isClosedEnded;

	@Column(name="iscsipavailable")
	private String isCSIPAvailable;
	
	@Column(name="is_direct")
	private String isDirect;
	
	@Column(name="is_etf")
	private String isETF;
	
	@Column(name="is_focused")
	private String isFocused;
	
	@Column(name="is_liquid")
	private String isLiquid;
	
	@Column(name="is_primary")
	private String isPrimary;
	
	@Column(name="last_dividend")
	private double lastDividend;
	
	@Column(name="load_comment")
	private String loadComment;
	
	@Column(name="maturity_date")	
	private String maturityDate;
	
	@Column(name="max_inv_amt")
	private double maxInvAmt;

	@Column(name="min_inv")
	private double minInv;
	
	@Column(name="nav")
	private double nav;
	
	@Column(name="nav_change")
	private double nav_change;
	
	@Column(name="nav_date")	
	private String nav_Date;
	
	@Column(name="nfo")
	private String nfo;
	
	@Column(name="preturn_10y")
	private double PReturn_10Y;
	
	@Column(name="preturn_15y")
	private double PReturn_15Y;
	
	@Column(name="preturn_1d")
	private double PReturn_1D;
	
	@Column(name="preturn_1m")
	private double PReturn_1M;
	
	@Column(name="preturn_1w")
	private double PReturn_1W;
	
	@Column(name="preturn_1y")
	private double PReturn_1Y;
	
	@Column(name="preturn_20y")
	private double PReturn_20Y;
	
	@Column(name="preturn_2y")
	private double PReturn_2Y;
	
	@Column(name="preturn_3m")
	private double PReturn_3M;
	
	@Column(name="preturn_3y")
	private double PReturn_3Y;
	
	@Column(name="preturn_5y")
	private double PReturn_5Y;
	
	@Column(name="preturn_6m")
	private double PReturn_6M;
	
	@Column(name="preturn_7y")
	private double PReturn_7Y;
	
	@Column(name="preturn_inception")
	private double PReturn_Inception;
	
	@Column(name="preturn_ytd")
	private double preturn_YTD;
	
	@Column(name="primary_key")
	private String primary_Key;
	
	@Column(name="regularordirect")
	private String regularORDirect;
	
	@Column(name="related_schemes")
	private String relatedSchemes;
	
	@Column(name="riskometer_value")
	private String riskometer_value;
	
	@Column(name="rtacode")
	private String RTACode;

	@Column(name="scheme_code")
	private String schemeCode;

	@Column(name="scheme_name")
	private String schemeName;

	@Column(name="scheme_option")
	private String schemeOption;

	@Column(name="scheme_short_name")
	private String schemeShortName;

	@Column(name="sip_scheme_option")
	private String sipSchemeOption;
	
	@Column(name="sipactive")
	private String SIPActive;
	
	@Column(name="sipflag")
	private String SIPFlag;
	
	@Column(name="sipmin_inv")
	private double SIPMin_INV;
	
	@Column(name="sipmin_inv_q")
	private double SIPMin_INV_Q;
	
	@Column(name="solution_bucket")
	private String solution_Bucket;
	
	@Column(name="start_date")
	private String startDate;
	
	@Column(name="sub_category")
	private String sub_Category;
	
	@Column(name="switch_to_date")	
	private String switchToDate;
	
	@Column(name="updated_date")
	private String updatedDate;
	
	@Column(name="weekly_inv_max")
	private double weekly_Inv_Max;
	
	@Column(name="weekly_inv_min")
	private double weekly_Inv_Min;

	//bi-directional many-to-one association to MapSchemeManager
	@OneToMany(mappedBy="productSchemeDetail")
	private List<MapSchemeManager> mapSchemeManagers;

	public ProductSchemeDetail() {
	}

	public Long getProductScId() {
		return this.productScId;
	}

	public void setProductScId(Long productScId) {
		this.productScId = productScId;
	}

	public String getActive() {
		return this.active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public double getAdjusted_Nav() {
		return this.adjusted_Nav;
	}

	public void setAdjusted_Nav(double adjusted_Nav) {
		this.adjusted_Nav = adjusted_Nav;
	}

	public String getAssetClass() {
		return this.assetClass;
	}

	public void setAssetClass(String assetClass) {
		this.assetClass = assetClass;
	}

	public double getAum() {
		return this.aum;
	}

	public void setAum(double aum) {
		this.aum = aum;
	}

	public String getAUMDate() {
		return this.AUMDate;
	}

	public void setAUMDate(String AUMDate) {
		this.AUMDate = AUMDate;
	}

	public String getBenchMark() {
		return this.benchMark;
	}

	public void setBenchMark(String benchMark) {
		this.benchMark = benchMark;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getChangeDividendFlag() {
		return this.changeDividendFlag;
	}

	public void setChangeDividendFlag(String changeDividendFlag) {
		this.changeDividendFlag = changeDividendFlag;
	}

	public double getCsipFirstIntMaxAmount() {
		return this.csipFirstIntMaxAmount;
	}

	public void setCsipFirstIntMaxAmount(double csipFirstIntMaxAmount) {
		this.csipFirstIntMaxAmount = csipFirstIntMaxAmount;
	}

	public double getCsipFirstIntMinAmount() {
		return this.csipFirstIntMinAmount;
	}

	public void setCsipFirstIntMinAmount(double csipFirstIntMinAmount) {
		this.csipFirstIntMinAmount = csipFirstIntMinAmount;
	}

	public double getCSIPMin_INV() {
		return this.CSIPMin_INV;
	}

	public void setCSIPMin_INV(double CSIPMin_INV) {
		this.CSIPMin_INV = CSIPMin_INV;
	}

	public double getCSIPMin_INV_Q() {
		return this.CSIPMin_INV_Q;
	}

	public void setCSIPMin_INV_Q(double CSIPMin_INV_Q) {
		this.CSIPMin_INV_Q = CSIPMin_INV_Q;
	}

	public String getDividend() {
		return this.dividend;
	}

	public void setDividend(String dividend) {
		this.dividend = dividend;
	}

	public String getDividendFlag() {
		return this.dividendFlag;
	}

	public void setDividendFlag(String dividendFlag) {
		this.dividendFlag = dividendFlag;
	}

	public String getEndDate() {
		return this.endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getExitLoad() {
		return this.exitLoad;
	}

	public void setExitLoad(String exitLoad) {
		this.exitLoad = exitLoad;
	}

	public double getFirst_Int_Min_Amount() {
		return this.first_Int_Min_Amount;
	}

	public void setFirst_Int_Min_Amount(double first_Int_Min_Amount) {
		this.first_Int_Min_Amount = first_Int_Min_Amount;
	}

	public String getFrequency() {
		return this.frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getFundCode() {
		return this.fundCode;
	}

	public void setFundCode(String fundCode) {
		this.fundCode = fundCode;
	}

	public String getFundHighlights() {
		return this.fundHighlights;
	}

	public void setFundHighlights(String fundHighlights) {
		this.fundHighlights = fundHighlights;
	}

	public String getFundName() {
		return this.fundName;
	}

	public void setFundName(String fundName) {
		this.fundName = fundName;
	}

	public String getFundType() {
		return this.fundType;
	}

	public void setFundType(String fundType) {
		this.fundType = fundType;
	}

	public String getInceptionDate() {
		return this.inceptionDate;
	}

	public void setInceptionDate(String inceptionDate) {
		this.inceptionDate = inceptionDate;
	}

	public int getIndex_Id() {
		return this.index_Id;
	}

	public void setIndex_Id(int index_Id) {
		this.index_Id = index_Id;
	}

	public String getInvestmentObjective() {
		return this.investmentObjective;
	}

	public void setInvestmentObjective(String investmentObjective) {
		this.investmentObjective = investmentObjective;
	}

	public String getInvestorSuitability() {
		return this.investorSuitability;
	}

	public void setInvestorSuitability(String investorSuitability) {
		this.investorSuitability = investorSuitability;
	}

	public String getIsClosedEnded() {
		return this.isClosedEnded;
	}

	public void setIsClosedEnded(String isClosedEnded) {
		this.isClosedEnded = isClosedEnded;
	}

	public String getIsCSIPAvailable() {
		return this.isCSIPAvailable;
	}

	public void setIsCSIPAvailable(String isCSIPAvailable) {
		this.isCSIPAvailable = isCSIPAvailable;
	}

	public String getIsDirect() {
		return this.isDirect;
	}

	public void setIsDirect(String isDirect) {
		this.isDirect = isDirect;
	}

	public String getIsETF() {
		return this.isETF;
	}

	public void setIsETF(String isETF) {
		this.isETF = isETF;
	}

	public String getIsFocused() {
		return this.isFocused;
	}

	public void setIsFocused(String isFocused) {
		this.isFocused = isFocused;
	}

	public String getIsLiquid() {
		return this.isLiquid;
	}

	public void setIsLiquid(String isLiquid) {
		this.isLiquid = isLiquid;
	}

	public String getIsPrimary() {
		return this.isPrimary;
	}

	public void setIsPrimary(String isPrimary) {
		this.isPrimary = isPrimary;
	}

	public double getLastDividend() {
		return this.lastDividend;
	}

	public void setLastDividend(double lastDividend) {
		this.lastDividend = lastDividend;
	}

	public String getLoadComment() {
		return this.loadComment;
	}

	public void setLoadComment(String loadComment) {
		this.loadComment = loadComment;
	}

	public String getMaturityDate() {
		return this.maturityDate;
	}

	public void setMaturityDate(String maturityDate) {
		this.maturityDate = maturityDate;
	}

	public double getMaxInvAmt() {
		return this.maxInvAmt;
	}

	public void setMaxInvAmt(double maxInvAmt) {
		this.maxInvAmt = maxInvAmt;
	}

	public double getMinInv() {
		return this.minInv;
	}

	public void setMinInv(double minInv) {
		this.minInv = minInv;
	}

	public double getNav() {
		return this.nav;
	}

	public void setNav(double nav) {
		this.nav = nav;
	}

	public double getNAV_Change() {
		return this.nav_change;
	}

	public void setNAV_Change(double nav_change) {
		this.nav_change = nav_change;
	}

	public String getNav_Date() {
		return this.nav_Date;
	}

	public void setNav_Date(String nav_Date) {
		this.nav_Date = nav_Date;
	}

	public String getNfo() {
		return this.nfo;
	}

	public void setNfo(String nfo) {
		this.nfo = nfo;
	}

	public double getPReturn_10Y() {
		return this.PReturn_10Y;
	}

	public void setPReturn_10Y(double PReturn_10Y) {
		this.PReturn_10Y = PReturn_10Y;
	}

	public double getPReturn_15Y() {
		return this.PReturn_15Y;
	}

	public void setPReturn_15Y(double PReturn_15Y) {
		this.PReturn_15Y = PReturn_15Y;
	}

	public double getPReturn_1D() {
		return this.PReturn_1D;
	}

	public void setPReturn_1D(double PReturn_1D) {
		this.PReturn_1D = PReturn_1D;
	}

	public double getPReturn_1M() {
		return this.PReturn_1M;
	}

	public void setPReturn_1M(double PReturn_1M) {
		this.PReturn_1M = PReturn_1M;
	}

	public double getPReturn_1W() {
		return this.PReturn_1W;
	}

	public void setPReturn_1W(double PReturn_1W) {
		this.PReturn_1W = PReturn_1W;
	}

	public double getPReturn_1Y() {
		return this.PReturn_1Y;
	}

	public void setPReturn_1Y(double PReturn_1Y) {
		this.PReturn_1Y = PReturn_1Y;
	}

	public double getPReturn_20Y() {
		return this.PReturn_20Y;
	}

	public void setPReturn_20Y(double PReturn_20Y) {
		this.PReturn_20Y = PReturn_20Y;
	}

	public double getPReturn_2Y() {
		return this.PReturn_2Y;
	}

	public void setPReturn_2Y(double PReturn_2Y) {
		this.PReturn_2Y = PReturn_2Y;
	}

	public double getPReturn_3M() {
		return this.PReturn_3M;
	}

	public void setPReturn_3M(double PReturn_3M) {
		this.PReturn_3M = PReturn_3M;
	}

	public double getPReturn_3Y() {
		return this.PReturn_3Y;
	}

	public void setPReturn_3Y(double PReturn_3Y) {
		this.PReturn_3Y = PReturn_3Y;
	}

	public double getPReturn_5Y() {
		return this.PReturn_5Y;
	}

	public void setPReturn_5Y(double PReturn_5Y) {
		this.PReturn_5Y = PReturn_5Y;
	}

	public double getPReturn_6M() {
		return this.PReturn_6M;
	}

	public void setPReturn_6M(double PReturn_6M) {
		this.PReturn_6M = PReturn_6M;
	}

	public double getPReturn_7Y() {
		return this.PReturn_7Y;
	}

	public void setPReturn_7Y(double PReturn_7Y) {
		this.PReturn_7Y = PReturn_7Y;
	}

	public double getPReturn_Inception() {
		return this.PReturn_Inception;
	}

	public void setPReturn_Inception(double PReturn_Inception) {
		this.PReturn_Inception = PReturn_Inception;
	}

	public double getPreturn_YTD() {
		return this.preturn_YTD;
	}

	public void setPreturn_YTD(double preturn_YTD) {
		this.preturn_YTD = preturn_YTD;
	}

	public String getPrimary_Key() {
		return this.primary_Key;
	}

	public void setPrimary_Key(String primary_Key) {
		this.primary_Key = primary_Key;
	}

	public String getRegularORDirect() {
		return this.regularORDirect;
	}

	public void setRegularORDirect(String regularORDirect) {
		this.regularORDirect = regularORDirect;
	}

	public String getRelatedSchemes() {
		return this.relatedSchemes;
	}

	public void setRelatedSchemes(String relatedSchemes) {
		this.relatedSchemes = relatedSchemes;
	}

	public String getRiskometer_value() {
		return this.riskometer_value;
	}

	public void setRiskometer_value(String riskometer_value) {
		this.riskometer_value = riskometer_value;
	}

	public String getRTACode() {
		return this.RTACode;
	}

	public void setRTACode(String RTACode) {
		this.RTACode = RTACode;
	}

	public String getSchemeCode() {
		return this.schemeCode;
	}

	public void setSchemeCode(String schemeCode) {
		this.schemeCode = schemeCode;
	}

	public String getSchemeName() {
		return this.schemeName;
	}

	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}

	public String getSchemeOption() {
		return this.schemeOption;
	}

	public void setSchemeOption(String schemeOption) {
		this.schemeOption = schemeOption;
	}

	public String getSchemeShortName() {
		return this.schemeShortName;
	}

	public void setSchemeShortName(String schemeShortName) {
		this.schemeShortName = schemeShortName;
	}

	public String getSipSchemeOption() {
		return this.sipSchemeOption;
	}

	public void setSipSchemeOption(String sipSchemeOption) {
		this.sipSchemeOption = sipSchemeOption;
	}

	public String getSIPActive() {
		return this.SIPActive;
	}

	public void setSIPActive(String SIPActive) {
		this.SIPActive = SIPActive;
	}

	public String getSIPFlag() {
		return this.SIPFlag;
	}

	public void setSIPFlag(String SIPFlag) {
		this.SIPFlag = SIPFlag;
	}

	public double getSIPMin_INV() {
		return this.SIPMin_INV;
	}

	public void setSIPMin_INV(double SIPMin_INV) {
		this.SIPMin_INV = SIPMin_INV;
	}

	public double getSIPMin_INV_Q() {
		return this.SIPMin_INV_Q;
	}

	public void setSIPMin_INV_Q(double SIPMin_INV_Q) {
		this.SIPMin_INV_Q = SIPMin_INV_Q;
	}

	public String getSolution_Bucket() {
		return this.solution_Bucket;
	}

	public void setSolution_Bucket(String solution_Bucket) {
		this.solution_Bucket = solution_Bucket;
	}

	public String getStartDate() {
		return this.startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getSub_Category() {
		return this.sub_Category;
	}

	public void setSub_Category(String sub_Category) {
		this.sub_Category = sub_Category;
	}

	public String getSwitchToDate() {
		return this.switchToDate;
	}

	public void setSwitchToDate(String switchToDate) {
		this.switchToDate = switchToDate;
	}

	public String getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public double getWeekly_Inv_Max() {
		return this.weekly_Inv_Max;
	}

	public void setWeekly_Inv_Max(double weekly_Inv_Max) {
		this.weekly_Inv_Max = weekly_Inv_Max;
	}

	public double getWeekly_Inv_Min() {
		return this.weekly_Inv_Min;
	}

	public void setWeekly_Inv_Min(double weekly_Inv_Min) {
		this.weekly_Inv_Min = weekly_Inv_Min;
	}

	public List<MapSchemeManager> getMapSchemeManagers() {
		return this.mapSchemeManagers;
	}

	public void setMapSchemeManagers(List<MapSchemeManager> mapSchemeManagers) {
		this.mapSchemeManagers = mapSchemeManagers;
	}

	public MapSchemeManager addMapSchemeManager(MapSchemeManager mapSchemeManager) {
		getMapSchemeManagers().add(mapSchemeManager);
		mapSchemeManager.setProductSchemeDetail(this);

		return mapSchemeManager;
	}

	public MapSchemeManager removeMapSchemeManager(MapSchemeManager mapSchemeManager) {
		getMapSchemeManagers().remove(mapSchemeManager);
		mapSchemeManager.setProductSchemeDetail(null);

		return mapSchemeManager;
	}

}