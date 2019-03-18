package com.freemi.entity.investment.icici;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"OCC_CODE",
"OCC_NAME"
})
public class OccupationList {

	@JsonProperty("OCC_CODE")
	private String occcode;
	@JsonProperty("OCC_NAME")
	private String occname;
	
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public String getOcccode() {
		return occcode;
	}

	public void setOcccode(String occcode) {
		this.occcode = occcode;
	}

	public String getOccname() {
		return occname;
	}

	public void setOccname(String occname) {
		this.occname = occname;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperties(Map<String, Object> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}
	
	
	
}
