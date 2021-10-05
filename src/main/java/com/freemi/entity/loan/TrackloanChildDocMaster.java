package com.freemi.entity.loan;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TrackloanChildDocMaster implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("Child_Doc")
	private TrackloanChildDoc[] childdoc;

	public TrackloanChildDoc[] getChilddoc() {
		return childdoc;
	}

	public void setChilddoc(TrackloanChildDoc[] childdoc) {
		this.childdoc = childdoc;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

}
