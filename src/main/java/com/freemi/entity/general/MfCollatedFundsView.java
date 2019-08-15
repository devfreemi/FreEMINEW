package com.freemi.entity.general;

import java.util.List;

import com.freemi.entity.investment.MFCamsFolio;
import com.freemi.entity.investment.MfAllInvestorValueByCategory;

public class MfCollatedFundsView {
	
	
	private String fundName;
	
	private String folioNumber;
	private String pan;
	private String mobile;
	private Double collaboratedAmount;
	private Double collaboratedMarketValue;
	private String amcicon;
	private String amcShort;
	private String rtaAgent;
	
	List<MFCamsFolio> camsFolioLists;
	List<MfAllInvestorValueByCategory> karvyFolioList;

	public String getFundName() {
		return fundName;
	}

	public void setFundName(String fundName) {
		this.fundName = fundName;
	}

	public String getFolioNumber() {
		return folioNumber;
	}

	public void setFolioNumber(String folioNumber) {
		this.folioNumber = folioNumber;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Double getCollaboratedAmount() {
		return collaboratedAmount;
	}

	public void setCollaboratedAmount(Double collaboratedAmount) {
		this.collaboratedAmount = collaboratedAmount;
	}

	public String getAmcicon() {
		return amcicon;
	}

	public void setAmcicon(String amcicon) {
		this.amcicon = amcicon;
	}

	public String getAmcShort() {
		return amcShort;
	}

	public void setAmcShort(String amcShort) {
		this.amcShort = amcShort;
	}

	public String getRtaAgent() {
		return rtaAgent;
	}

	public void setRtaAgent(String rtaAgent) {
		this.rtaAgent = rtaAgent;
	}

	public List<MFCamsFolio> getCamsFolioLists() {
		return camsFolioLists;
	}

	public void setCamsFolioLists(List<MFCamsFolio> camsFolioLists) {
		this.camsFolioLists = camsFolioLists;
	}

	public List<MfAllInvestorValueByCategory> getKarvyFolioList() {
		return karvyFolioList;
	}

	public void setKarvyFolioList(List<MfAllInvestorValueByCategory> karvyFolioList) {
		this.karvyFolioList = karvyFolioList;
	}

	public Double getCollaboratedMarketValue() {
		return collaboratedMarketValue;
	}

	public void setCollaboratedMarketValue(Double collaboratedMarketValue) {
		this.collaboratedMarketValue = collaboratedMarketValue;
	}


	
	

}
