package com.freemi.entity.loan;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TracklonParentDocStatus implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("Parent_Doc_Id")
	private String  parentdocid;
	
	@JsonProperty("Status")
	private String status;
	
	@JsonProperty("Description")
	private String description;
	
	@JsonProperty("Parent_Doc_Desc")
	private String parentdocdesc;
	
	@JsonProperty("Remarks")
	private String remarks;
	
	@JsonProperty("Child_Doc_Desc")
	private String childdocdesc;
	
	@JsonProperty("Child_Doc_Id")
	private String childdocid;

	public String getParentdocid() {
		return parentdocid;
	}

	public void setParentdocid(String parentdocid) {
		this.parentdocid = parentdocid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getParentdocdesc() {
		return parentdocdesc;
	}

	public void setParentdocdesc(String parentdocdesc) {
		this.parentdocdesc = parentdocdesc;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getChilddocdesc() {
		return childdocdesc;
	}

	public void setChilddocdesc(String childdocdesc) {
		this.childdocdesc = childdocdesc;
	}

	public String getChilddocid() {
		return childdocid;
	}

	public void setChilddocid(String childdocid) {
		this.childdocid = childdocid;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
