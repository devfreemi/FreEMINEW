package com.freemi.entity.investment;

import java.io.Serializable;
import java.time.LocalDate;

public class MFInvestmentDates implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String invFrequency;
	private String sipPeriodFromMonth=Integer.toString((LocalDate.now().plusDays(16).getMonth().getValue())+1);
	private String sipPeriodFromYear=Integer.toString((LocalDate.now().plusDays(16).getYear()));
	private String sipPeriodToMonth="12";
	private String sipPeriodToYear="2049";
	private String monthlyInvestTriggerDate=Integer.toString((LocalDate.now().plusDays(16).getDayOfMonth()));
	private String sipAmount;
	public String getInvFrequency() {
		return invFrequency;
	}
	public void setInvFrequency(String invFrequency) {
		this.invFrequency = invFrequency;
	}
	public String getSipPeriodFromMonth() {
		return sipPeriodFromMonth;
	}
	public void setSipPeriodFromMonth(String sipPeriodFromMonth) {
		this.sipPeriodFromMonth = sipPeriodFromMonth;
	}
	public String getSipPeriodFromYear() {
		return sipPeriodFromYear;
	}
	public void setSipPeriodFromYear(String sipPeriodFromYear) {
		this.sipPeriodFromYear = sipPeriodFromYear;
	}
	public String getSipPeriodToMonth() {
		return sipPeriodToMonth;
	}
	public void setSipPeriodToMonth(String sipPeriodToMonth) {
		this.sipPeriodToMonth = sipPeriodToMonth;
	}
	public String getSipPeriodToYear() {
		return sipPeriodToYear;
	}
	public void setSipPeriodToYear(String sipPeriodToYear) {
		this.sipPeriodToYear = sipPeriodToYear;
	}
	public String getMonthlyInvestTriggerDate() {
		return monthlyInvestTriggerDate;
	}
	public void setMonthlyInvestTriggerDate(String monthlyInvestTriggerDate) {
		this.monthlyInvestTriggerDate = monthlyInvestTriggerDate;
	}
	public String getSipAmount() {
		return sipAmount;
	}
	public void setSipAmount(String sipAmount) {
		this.sipAmount = sipAmount;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	

}
