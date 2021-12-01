package com.freemi.entity.investment;

import java.io.Serializable;
import java.util.Map;

public class Bseregistrationstatus implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String accountexist="N";
	
	private String mobile;
	
	private String profileuniqueid;
	
	private String pan;
	
	private String bseregisterstatus="N";
	
	private String bseregisterresponse="0";
	
	private String fatcadeclared="N";
	
	private String fatcadeclareresponse;
	
	private String aofuploadstatus;
	
	private String aofuploadresponse;
	
	private String fullstatus;
	
	private String other1;
	
	private String other2;
	
	private Map<String, String> mapdata;

	public String getAccountexist() {
		return accountexist;
	}

	public void setAccountexist(String accountexist) {
		this.accountexist = accountexist;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getProfileuniqueid() {
		return profileuniqueid;
	}

	public void setProfileuniqueid(String profileuniqueid) {
		this.profileuniqueid = profileuniqueid;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getBseregisterstatus() {
		return bseregisterstatus;
	}

	public void setBseregisterstatus(String bseregisterstatus) {
		this.bseregisterstatus = bseregisterstatus;
	}

	public String getBseregisterresponse() {
		return bseregisterresponse;
	}

	public void setBseregisterresponse(String bseregisterresponse) {
		this.bseregisterresponse = bseregisterresponse;
	}

	public String getFatcadeclared() {
		return fatcadeclared;
	}

	public void setFatcadeclared(String fatcadeclared) {
		this.fatcadeclared = fatcadeclared;
	}

	public String getFatcadeclareresponse() {
		return fatcadeclareresponse;
	}

	public void setFatcadeclareresponse(String fatcadeclareresponse) {
		this.fatcadeclareresponse = fatcadeclareresponse;
	}

	public String getAofuploadstatus() {
		return aofuploadstatus;
	}

	public void setAofuploadstatus(String aofuploadstatus) {
		this.aofuploadstatus = aofuploadstatus;
	}

	public String getAofuploadresponse() {
		return aofuploadresponse;
	}

	public void setAofuploadresponse(String aofuploadresponse) {
		this.aofuploadresponse = aofuploadresponse;
	}

	public String getFullstatus() {
		return fullstatus;
	}

	public void setFullstatus(String fullstatus) {
		this.fullstatus = fullstatus;
	}

	public String getOther1() {
		return other1;
	}

	public void setOther1(String other1) {
		this.other1 = other1;
	}

	public String getOther2() {
		return other2;
	}

	public void setOther2(String other2) {
		this.other2 = other2;
	}

	public Map<String, String> getMapdata() {
		return mapdata;
	}

	public void setMapdata(Map<String, String> mapdata) {
		this.mapdata = mapdata;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
