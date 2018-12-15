package com.freemi.entity.general;

import org.springframework.stereotype.Component;

@Component
public class HomeLoanForm {

	private int principal;
	private float interest;
	private int tenure;
	public int getPrincipal() {
		return principal;
	}
	public void setPrincipal(int principal) {
		this.principal = principal;
	}
	public float getInterest() {
		return interest;
	}
	public void setInterest(float interest) {
		this.interest = interest;
	}
	public int getTenure() {
		return tenure;
	}
	public void setTenure(int tenure) {
		this.tenure = tenure;
	}
	
	
}
