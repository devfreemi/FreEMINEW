package com.freemi.entity.general;

import org.springframework.stereotype.Component;

@Component
public class TaxCalculatorForm {

	private String earnerType;
	private int annualIncome;
	private int elss;
	private int ppf;
	private int nsc;
	private int fixedDeposit;
	private int ulip;
	private int lifeInsurance;
	private int healthInsurance;
	private int nps;
	public String getEarnerType() {
		return earnerType;
	}
	public void setEarnerType(String earnerType) {
		this.earnerType = earnerType;
	}
	public int getAnnualIncome() {
		return annualIncome;
	}
	public void setAnnualIncome(int annualIncome) {
		this.annualIncome = annualIncome;
	}
	public int getElss() {
		return elss;
	}
	public void setElss(int elss) {
		this.elss = elss;
	}
	public int getPpf() {
		return ppf;
	}
	public void setPpf(int ppf) {
		this.ppf = ppf;
	}
	public int getNsc() {
		return nsc;
	}
	public void setNsc(int nsc) {
		this.nsc = nsc;
	}
	public int getFixedDeposit() {
		return fixedDeposit;
	}
	public void setFixedDeposit(int fixedDeposit) {
		this.fixedDeposit = fixedDeposit;
	}
	public int getUlip() {
		return ulip;
	}
	public void setUlip(int ulip) {
		this.ulip = ulip;
	}
	public int getLifeInsurance() {
		return lifeInsurance;
	}
	public void setLifeInsurance(int lifeInsurance) {
		this.lifeInsurance = lifeInsurance;
	}
	public int getHealthInsurance() {
		return healthInsurance;
	}
	public void setHealthInsurance(int healthInsurance) {
		this.healthInsurance = healthInsurance;
	}
	public int getNps() {
		return nps;
	}
	public void setNps(int nps) {
		this.nps = nps;
	}
	
	
}
