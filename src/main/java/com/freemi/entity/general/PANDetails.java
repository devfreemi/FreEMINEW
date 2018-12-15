package com.freemi.entity.general;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PANDetails {

	@JsonProperty("ValidatePANResult")
	private ValidatePANResult validatePANResult;

	public ValidatePANResult getValidatePANResult() {
		return validatePANResult;
	}

	public void setValidatePANResult(ValidatePANResult validatePANResult) {
		this.validatePANResult = validatePANResult;
	}
	
	
}
