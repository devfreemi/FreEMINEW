package com.freemi.entity.investment;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name="mailback_data_cams_collected_amount_view")
@Proxy(lazy=false)
public class MFCamsValueByCategroy implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="FUNDNAME")
	private String fundName;
	
	@Column(name="FOLIO_NO")
	private String folioNumber;
	
	@Column(name="PAN")
	private String pan;
	
	@Column(name="MOBILE_NO")
	private String mobile;
	
	@Column(name="COLLATED_AMOUNT")
	private Double collaboratedAmount;
	
	@Column(name="ICONS")
	private String amcicon;
	
	@Column(name="AMC_SHORT")
	private String amcShort;
	
	@Column(name="RTA_AGENT")
	private String rtaAgent;
	
	@Transient
	List<MFCamsFolio> camsFolioLists;

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getAmcicon() {
		return amcicon;
	}

	public void setAmcicon(String amcicon) {
		this.amcicon = amcicon;
	}

	public List<MFCamsFolio> getCamsFolioLists() {
		return camsFolioLists;
	}

	public void setCamsFolioLists(List<MFCamsFolio> camsFolioLists) {
		this.camsFolioLists = camsFolioLists;
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
	

}
