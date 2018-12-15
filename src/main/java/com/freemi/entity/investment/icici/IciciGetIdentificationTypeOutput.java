package com.freemi.entity.investment.icici;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IciciGetIdentificationTypeOutput implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("ID_CODE")
	private String id_code;

	@JsonProperty("ID_CODE_DESC")
	private String id_code_desc;

	public String getId_code() {
		return id_code;
	}

	public void setId_code(String id_code) {
		this.id_code = id_code;
	}

	public String getId_code_desc() {
		return id_code_desc;
	}

	public void setId_code_desc(String id_code_desc) {
		this.id_code_desc = id_code_desc;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
