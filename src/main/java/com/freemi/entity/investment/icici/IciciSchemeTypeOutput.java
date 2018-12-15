package com.freemi.entity.investment.icici;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IciciSchemeTypeOutput implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("SCH_TYPE")
	private String sch_type;

	@JsonProperty("TYPE_NAME")
	private String type_name;

	public String getSch_type() {
		return sch_type;
	}

	public void setSch_type(String sch_type) {
		this.sch_type = sch_type;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
