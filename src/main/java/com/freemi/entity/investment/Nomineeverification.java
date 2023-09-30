package com.freemi.entity.investment;

import java.io.Serializable;

import com.freemi.entity.bse.Nomineerecords;

public class Nomineeverification implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String mobileno;
	
	private String clientid;
	
	private String customerid;
	
	private String mutualfundaccountexist="N";
	
	private MFNominationForm nomineedetails;
	
	private String nomineedetailsexist="N";
	
	private String registrationtype;
	
	private String filler1;
	
	private String filler2;
	
	private String filler3;
	
	private Nomineerecords param;
	
	private String holdingmode;

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public String getClientid() {
		return clientid;
	}

	public void setClientid(String clientid) {
		this.clientid = clientid;
	}

	public String getCustomerid() {
		return customerid;
	}

	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}

	public MFNominationForm getNomineedetails() {
		return nomineedetails;
	}

	public void setNomineedetails(MFNominationForm nomineedetails) {
		this.nomineedetails = nomineedetails;
	}
	
	public String getNomineedetailsexist() {
		return nomineedetailsexist;
	}

	public void setNomineedetailsexist(String nomineedetailsexist) {
		this.nomineedetailsexist = nomineedetailsexist;
	}
	
	public String getRegistrationtype() {
		return registrationtype;
	}

	public void setRegistrationtype(String registrationtype) {
		this.registrationtype = registrationtype;
	}

	public String getFiller1() {
		return filler1;
	}

	public void setFiller1(String filler1) {
		this.filler1 = filler1;
	}

	public String getFiller2() {
		return filler2;
	}

	public void setFiller2(String filler2) {
		this.filler2 = filler2;
	}

	public String getFiller3() {
		return filler3;
	}

	public void setFiller3(String filler3) {
		this.filler3 = filler3;
	}

	public String getMutualfundaccountexist() {
		return mutualfundaccountexist;
	}

	public void setMutualfundaccountexist(String mutualfundaccountexist) {
		this.mutualfundaccountexist = mutualfundaccountexist;
	}

	public Nomineerecords getParam() {
		return param;
	}

	public void setParam(Nomineerecords param) {
		this.param = param;
	}

	public String getHoldingmode() {
		return holdingmode;
	}

	public void setHoldingmode(String holdingmode) {
		this.holdingmode = holdingmode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
