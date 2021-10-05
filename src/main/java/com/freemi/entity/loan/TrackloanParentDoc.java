package com.freemi.entity.loan;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TrackloanParentDoc implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("Parent_Doc_Id")
	private String parentdocid;
	
	@JsonProperty("Parent_Doc_Desc")
	private String parentdocdesc;
	
	@JsonProperty("Child_Doc_flag")
	private String childdocflag;
	
	@JsonProperty("ChildDocMaster")
	private TrackloanChildDocMaster childdocmaster;

	public String getParentdocid() {
		return parentdocid;
	}

	public void setParentdocid(String parentdocid) {
		this.parentdocid = parentdocid;
	}

	public String getParentdocdesc() {
		return parentdocdesc;
	}

	public void setParentdocdesc(String parentdocdesc) {
		this.parentdocdesc = parentdocdesc;
	}

	public String getChilddocflag() {
		return childdocflag;
	}

	public void setChilddocflag(String childdocflag) {
		this.childdocflag = childdocflag;
	}

	public TrackloanChildDocMaster getChilddocmaster() {
		return childdocmaster;
	}

	public void setChilddocmaster(TrackloanChildDocMaster childdocmaster) {
		this.childdocmaster = childdocmaster;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	

}
