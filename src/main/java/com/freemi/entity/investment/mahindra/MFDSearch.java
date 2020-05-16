package com.freemi.entity.investment.mahindra;

import java.io.Serializable;

public class MFDSearch implements Serializable {
    
    
	/**
     * 
     */
    private static final long serialVersionUID = 1L;
	private String mobileNumber;
	private String panNumber;
	
	
	public static long getSerialversionuid() {
	    return serialVersionUID;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getPanNumber() {
		return panNumber;
	}
	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

}
