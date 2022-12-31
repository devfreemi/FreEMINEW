package com.freemi.entity.investment;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ChosenMFund {
	
	@JsonProperty("source")
	private String source;
	
	@JsonProperty("fundlist")
	private List<String> fundlist;
	
	@JsonIgnore
	private RegistryFunds registryFund;

	public RegistryFunds getRegistryFund() {
		return registryFund;
	}

	public void setRegistryFund(RegistryFunds registryFund) {
		this.registryFund = registryFund;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public List<String> getFundlist() {
		return fundlist;
	}

	public void setFundlist(List<String> fundlist) {
		this.fundlist = fundlist;
	}
	
	
}
