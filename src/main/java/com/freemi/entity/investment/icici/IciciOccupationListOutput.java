package com.freemi.entity.investment.icici;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IciciOccupationListOutput implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@JsonProperty("OCC_CODE")
	private String occ_code;

	@JsonProperty("OCC_NAME")
	private String occ_name;

	public String getOcc_code() {
		return occ_code;
	}

	public void setOcc_code(String occ_code) {
		this.occ_code = occ_code;
	}

	public String getOcc_name() {
		return occ_name;
	}

	public void setOcc_name(String occ_name) {
		this.occ_name = occ_name;
	}

}
