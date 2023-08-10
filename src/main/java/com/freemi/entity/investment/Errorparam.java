package com.freemi.entity.investment;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Errorparam implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("CM_MEMBERID")
	private String cmmemberid;
	
	@JsonProperty("CM_Code")
	private String cmcode;
	
	@JsonProperty("CM_NOM_OPT")
	private String cmoptout;
	
	@JsonProperty("CM_NOM_AUTH_MODE")
	private String cmauthmode;
	
	@JsonProperty("CM_NOM_PAN1")
	private String cmnompan1;
	
	@JsonProperty("CM_NOM_GUARD_PAN1")
	private String cmnomguarpan1;
	
	@JsonProperty("CM_NOM_PAN2")
	private String cmnom2;
	
	@JsonProperty("CM_NOM_GUARD_PAN2")
	private String cmnomguarpan2;
	
	@JsonProperty("CM_NOM_PAN3")
	private String cmnom3;
	
	@JsonProperty("CM_NOM_GUARD_PAN3")
	private String cmnomguarpan3;
	
	@JsonProperty("CM_EMAIL2")
	private String cmemail2;
	
	@JsonProperty("CM_EMAILIDFlag2")
	private String cmemail2flag;
	
	@JsonProperty("CM_MOBILE2")
	private String cmmobile2;
	
	@JsonProperty("CM_MOBILEIDFlag2")
	private String cmmobile2flag;
	
	@JsonProperty("CM_EMAIL3")
	private String cmemail3;
	
	@JsonProperty("CM_EMAILIDFlag3")
	private String cmemail3flag;
	
	@JsonProperty("CM_MOBILE3")
	private String cmmobile3;
	
	@JsonProperty("CM_MOBILEIDFlag3")
	private String cmmobile3flag;
	
	@JsonProperty("ErrRemarks")
	private String errorremark;

	public String getCmmemberid() {
		return cmmemberid;
	}

	public void setCmmemberid(String cmmemberid) {
		this.cmmemberid = cmmemberid;
	}

	public String getCmcode() {
		return cmcode;
	}

	public void setCmcode(String cmcode) {
		this.cmcode = cmcode;
	}

	public String getCmoptout() {
		return cmoptout;
	}

	public void setCmoptout(String cmoptout) {
		this.cmoptout = cmoptout;
	}

	public String getCmauthmode() {
		return cmauthmode;
	}

	public void setCmauthmode(String cmauthmode) {
		this.cmauthmode = cmauthmode;
	}

	public String getCmnompan1() {
		return cmnompan1;
	}

	public void setCmnompan1(String cmnompan1) {
		this.cmnompan1 = cmnompan1;
	}

	public String getCmnomguarpan1() {
		return cmnomguarpan1;
	}

	public void setCmnomguarpan1(String cmnomguarpan1) {
		this.cmnomguarpan1 = cmnomguarpan1;
	}

	public String getCmnom2() {
		return cmnom2;
	}

	public void setCmnom2(String cmnom2) {
		this.cmnom2 = cmnom2;
	}

	public String getCmnomguarpan2() {
		return cmnomguarpan2;
	}

	public void setCmnomguarpan2(String cmnomguarpan2) {
		this.cmnomguarpan2 = cmnomguarpan2;
	}

	public String getCmnom3() {
		return cmnom3;
	}

	public void setCmnom3(String cmnom3) {
		this.cmnom3 = cmnom3;
	}

	public String getCmnomguarpan3() {
		return cmnomguarpan3;
	}

	public void setCmnomguarpan3(String cmnomguarpan3) {
		this.cmnomguarpan3 = cmnomguarpan3;
	}

	public String getCmemail2() {
		return cmemail2;
	}

	public void setCmemail2(String cmemail2) {
		this.cmemail2 = cmemail2;
	}

	public String getCmemail2flag() {
		return cmemail2flag;
	}

	public void setCmemail2flag(String cmemail2flag) {
		this.cmemail2flag = cmemail2flag;
	}

	public String getCmmobile2() {
		return cmmobile2;
	}

	public void setCmmobile2(String cmmobile2) {
		this.cmmobile2 = cmmobile2;
	}

	public String getCmmobile2flag() {
		return cmmobile2flag;
	}

	public void setCmmobile2flag(String cmmobile2flag) {
		this.cmmobile2flag = cmmobile2flag;
	}

	public String getCmemail3() {
		return cmemail3;
	}

	public void setCmemail3(String cmemail3) {
		this.cmemail3 = cmemail3;
	}

	public String getCmemail3flag() {
		return cmemail3flag;
	}

	public void setCmemail3flag(String cmemail3flag) {
		this.cmemail3flag = cmemail3flag;
	}

	public String getCmmobile3() {
		return cmmobile3;
	}

	public void setCmmobile3(String cmmobile3) {
		this.cmmobile3 = cmmobile3;
	}

	public String getCmmobile3flag() {
		return cmmobile3flag;
	}

	public void setCmmobile3flag(String cmmobile3flag) {
		this.cmmobile3flag = cmmobile3flag;
	}

	public String getErrorremark() {
		return errorremark;
	}

	public void setErrorremark(String errorremark) {
		this.errorremark = errorremark;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
}
