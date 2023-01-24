package com.freemi.entity.bse;

import java.io.Serializable;

public class Mandatecheck implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String clientid;
	
	private String mandateid;

	public String getClientid() {
		return clientid;
	}

	public void setClientid(String clientid) {
		this.clientid = clientid;
	}

	public String getMandateid() {
		return mandateid;
	}

	public void setMandateid(String mandateid) {
		this.mandateid = mandateid;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
