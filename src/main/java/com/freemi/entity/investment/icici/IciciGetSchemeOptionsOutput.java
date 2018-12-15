package com.freemi.entity.investment.icici;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IciciGetSchemeOptionsOutput {

	@JsonProperty("VALUE")
	private String value;

	@JsonProperty("DISPLAY")
	private String display;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}	

	
}
