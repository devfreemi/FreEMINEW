package com.freemi.entity.general;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class RegistryWish implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String wishType;
	private String investType;
	private String tenure ;
	private String amount;
	private String person;	
	private String date;
	private Long schemeId;
	
	public String getWishType() {
		return wishType;
	}
	public void setWishType(String wishType) {
		this.wishType = wishType;
	}
	public String getInvestType() {
		return investType;
	}
	public void setInvestType(String investType) {
		this.investType = investType;
	}
	public String getTenure() {
		return tenure;
	}
	public void setTenure(String tenure) {
		this.tenure = tenure;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person = person;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Long getSchemeId() {
		return schemeId;
	}
	public void setSchemeId(Long schemeId) {
		this.schemeId = schemeId;
	}
}
