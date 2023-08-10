package com.freemi.entity.bse;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Nomineerecords implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("ClientCode")
	private String clientcode;
	
	@JsonProperty("NominationOpt")
	private String nominationopt="N";
	
	@JsonProperty("NominationAuthMode")
	private String nominationauthmode="O";
	
	@JsonProperty("NomineePAN1")
	private String nomineepan1;
	
	@JsonProperty("NomineeGuardianPAN1")
	private String nomineeguardianpan1;
	
	@JsonProperty("NomineePAN2")
	private String nomineepan2;
	
	@JsonProperty("NomineeGuardianPAN2")
	private String nomineeguardianpan2;
	
	@JsonProperty("NomineePAN3")
	private String nomineepan3;
	
	@JsonProperty("NomineeGuardianPAN3")
	private String nomineeguardianpan3;
	
	@JsonProperty("SecondHolderEmail")
	private String secondholderemail;
	
	@JsonProperty("SecondHolderEmailDeclaration")
	private String secondholderemaildeclaration;
	
	@JsonProperty("SecondHolderMobileNo")
	private String secondholdermobile;
	
	@JsonProperty("SecondHolderMobileNoDeclaration")
	private String secondholdermobiledeclaration;
	
	@JsonProperty("ThirdHolderEmail")
	private String thirdholderemail;
	
	@JsonProperty("ThirdHolderEmailDeclaration")
	private String thirdholderemaildeclaration;
	
	@JsonProperty("ThirdHolderMobileNo")
	private String thirdholdermobile;
	
	@JsonProperty("ThirdHolderMobileNoDeclaration")
	private String thirdholdermobiledeclaration;

	public String getClientcode() {
		return clientcode;
	}

	public void setClientcode(String clientcode) {
		this.clientcode = clientcode;
	}

	public String getNominationopt() {
		return nominationopt;
	}

	public void setNominationopt(String nominationopt) {
		this.nominationopt = nominationopt;
	}

	public String getNominationauthmode() {
		return nominationauthmode;
	}

	public void setNominationauthmode(String nominationauthmode) {
		this.nominationauthmode = nominationauthmode;
	}

	public String getNomineepan1() {
		return nomineepan1;
	}

	public void setNomineepan1(String nomineepan1) {
		this.nomineepan1 = nomineepan1;
	}

	public String getNomineeguardianpan1() {
		return nomineeguardianpan1;
	}

	public void setNomineeguardianpan1(String nomineeguardianpan1) {
		this.nomineeguardianpan1 = nomineeguardianpan1;
	}

	public String getNomineepan2() {
		return nomineepan2;
	}

	public void setNomineepan2(String nomineepan2) {
		this.nomineepan2 = nomineepan2;
	}

	public String getNomineeguardianpan2() {
		return nomineeguardianpan2;
	}

	public void setNomineeguardianpan2(String nomineeguardianpan2) {
		this.nomineeguardianpan2 = nomineeguardianpan2;
	}

	public String getNomineepan3() {
		return nomineepan3;
	}

	public void setNomineepan3(String nomineepan3) {
		this.nomineepan3 = nomineepan3;
	}

	public String getNomineeguardianpan3() {
		return nomineeguardianpan3;
	}

	public void setNomineeguardianpan3(String nomineeguardianpan3) {
		this.nomineeguardianpan3 = nomineeguardianpan3;
	}

	public String getSecondholderemail() {
		return secondholderemail;
	}

	public void setSecondholderemail(String secondholderemail) {
		this.secondholderemail = secondholderemail;
	}

	public String getSecondholderemaildeclaration() {
		return secondholderemaildeclaration;
	}

	public void setSecondholderemaildeclaration(String secondholderemaildeclaration) {
		this.secondholderemaildeclaration = secondholderemaildeclaration;
	}

	public String getSecondholdermobile() {
		return secondholdermobile;
	}

	public void setSecondholdermobile(String secondholdermobile) {
		this.secondholdermobile = secondholdermobile;
	}

	public String getSecondholdermobiledeclaration() {
		return secondholdermobiledeclaration;
	}

	public void setSecondholdermobiledeclaration(String secondholdermobiledeclaration) {
		this.secondholdermobiledeclaration = secondholdermobiledeclaration;
	}

	public String getThirdholderemail() {
		return thirdholderemail;
	}

	public void setThirdholderemail(String thirdholderemail) {
		this.thirdholderemail = thirdholderemail;
	}

	public String getThirdholderemaildeclaration() {
		return thirdholderemaildeclaration;
	}

	public void setThirdholderemaildeclaration(String thirdholderemaildeclaration) {
		this.thirdholderemaildeclaration = thirdholderemaildeclaration;
	}

	public String getThirdholdermobile() {
		return thirdholdermobile;
	}

	public void setThirdholdermobile(String thirdholdermobile) {
		this.thirdholdermobile = thirdholdermobile;
	}

	public String getThirdholdermobiledeclaration() {
		return thirdholdermobiledeclaration;
	}

	public void setThirdholdermobiledeclaration(String thirdholdermobiledeclaration) {
		this.thirdholdermobiledeclaration = thirdholdermobiledeclaration;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
