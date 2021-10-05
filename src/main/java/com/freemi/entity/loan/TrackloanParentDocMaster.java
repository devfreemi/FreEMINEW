package com.freemi.entity.loan;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TrackloanParentDocMaster implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("Parent_Doc")
	private TrackloanParentDoc[] parentdoc;

	public TrackloanParentDoc[] getParentdoc() {
		return parentdoc;
	}

	public void setParentdoc(TrackloanParentDoc[] parentdoc) {
		this.parentdoc = parentdoc;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
