package com.freemi.entity.loan;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TrackloanParentdocStatusMaster implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("Parent_DocStatus")
	private TracklonParentDocStatus[] parentdocstatus;

	public TracklonParentDocStatus[] getParentdocstatus() {
		return parentdocstatus;
	}

	public void setParentdocstatus(TracklonParentDocStatus[] parentdocstatus) {
		this.parentdocstatus = parentdocstatus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
