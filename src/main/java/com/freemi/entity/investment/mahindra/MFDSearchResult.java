package com.freemi.entity.investment.mahindra;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class MFDSearchResult implements Serializable {
	/**
     * 
     */
    private static final long serialVersionUID = 1L;

	MahindraCustomerDetails cust;
	
	Map<String,MFDApplStatus> applMap;
	List<MahindraFDListItem> fdList;
	
	public MahindraCustomerDetails getCust() {
		return cust;
	}
	public void setCust(MahindraCustomerDetails cust) {
		this.cust = cust;
	}
	
	public Map<String, MFDApplStatus> getApplMap() {
		return applMap;
	}
	public void setApplMap(Map<String, MFDApplStatus> applMap) {
		this.applMap = applMap;
	}
	public List<MahindraFDListItem> getFdList() {
		return fdList;
	}
	public void setFdList(List<MahindraFDListItem> fdList) {
		this.fdList = fdList;
	}
	public static long getSerialversionuid() {
	    return serialVersionUID;
	}
	
}
