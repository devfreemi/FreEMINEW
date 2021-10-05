package com.freemi.entity.loan;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TrackloanChildDoc implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("Child_Doc_Id")
	private String childdocid;
	
	@JsonProperty("Child_Doc_Desc")
	private String childdocdesc;

	public String getChilddocid() {
		return childdocid;
	}

	public void setChilddocid(String childdocid) {
		this.childdocid = childdocid;
	}

	public String getChilddocdesc() {
		return childdocdesc;
	}

	public void setChilddocdesc(String childdocdesc) {
		this.childdocdesc = childdocdesc;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
