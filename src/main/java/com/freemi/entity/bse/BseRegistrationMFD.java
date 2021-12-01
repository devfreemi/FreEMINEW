package com.freemi.entity.bse;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BseRegistrationMFD implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String ClientCode="";
	
	private String registrationtype="NEW";
	private String ClientHolding="";
	
	private String ClientTaxstatu="";
	
	private String ClientOccupationcod="";
	
	private String ClientAppname1="";		//Deprecated in new API
	private String applicant1fname="";
	private String applicant1mname="";
	private String applicant1lname="";
	
	private String ClientDob="";
	
	private String ClientGender="";
	private String applicant1panexempt="";
	private String applicant1panexemptcategory="";
	
	private String ClientPan="";
	
	private String ClientType="";
	/**
	mandatory if value is "D" in client type
	*/
	
	private String ClientDefaultdp="";
	/**
	mandatory if value is "C" in client default DP */
	
	private String ClienCdsldpid="";
	/**
	mandatory if value is "C" in client default DP
	*/
	
	private String ClientCdslcltid="";
	/**
	mandatory if value is "N" in client default DP */
	
	private String ClientNsdldpid="";
	/**
	mandatory if value is "N" in client default DP */
	
	private String ClientNsdlcltid="";
	/**
	mandatory for all the cases varchar2 SB/CB/NE/NO Account Type
	*/
	private String applicant1kyctype="";
	private String applicant1ckycno="";
	private String applicant1kraexemptno="";
	
	private String applicant1aadhaarupdated="";
	private String paperlessflag="";
	
	
	private String ClientAppname2="";		//Deprecated in new API
	private String applicant2fname="";
	private String applicant2mname="";
	private String applicant2lname="";
	private String applicant2dob="";
	private String applicant2panexempt="";
	private String applicant2panexemptcategory="";
	private String applicant2kyctype="";
	private String applicant2ckycno="";
	private String applicant2kraexemptrefno="";
	
	
	private String ClientAppname3="";		//Deprecated in new API
	private String applicant3fname="";
	private String applicant3mname="";
	private String applicant3lname="";
	private String applicant3dob="";
	private String applicant3panexempt="";
	private String applicant3panexemptcategory="";
	private String applicant3kyctype="";
	private String applicant3ckycno="";
	private String applicant3kraexemptrefno="";
	
	//Guarfan details in case of minor nominee
	
	private String ClientGuardian="";		//Deprecated in new API	
	private String guardianfname="";
	private String guardianmname="";
	private String guardianlanme="";
	private String guardiandob="";
	
	private String ClientGuardianpan="";
	private String guardianpanexempt="";
	private String guardianpanexemptcateegory="";
	private String guardiankyctype="";
	private String guardianckycno="";
	private String guardiankraexemptrefno="";
	
	//1st Nominee
	
	private String ClientNominee="";
	
	private String ClientNomineeRelation="";
	private String nominee1applicable="";
	private String nominee1minorflag="";
	private String nominee1dob="";
	private String nominee1guardianname="";
	
	private String ClientAcctype1="";
	/**
	mandatory for all the cases
	*/
	
	private String ClientAccno1="";
	/**
	mandatory for all the cases */
	
	private String ClientMicrno1="";
	/**
	mandatory for all the cases */
	
	private String ClientNeftIfsccode1="";
	/**
	mandatory for all the cases -only one bank can be default bank */
	
	private String DefaultBankFlag="";
	/**
	mandatory for all the cases varchar2 SB/CB/NE/NO Account Type
	*/
	
	private String ClientAcctype2="";
	/**
	mandatory for all the cases
	*/
	
	private String ClientAccno2="";
	/**
	mandatory for all the cases */
	
	private String ClientMicrno2="";
	/**
	mandatory for all the cases */
	
	private String ClientNeftIfsccode2="";
	/**
	mandatory for all the cases -only one bank can be default bank */
	
	private String DefaultBankFlag2="";

	/**
	mandatory for all the cases varchar2 SB/CB/NE/NO Account Type
	*/
	
	private String ClientAcctype3="";
	/**
	mandatory for all the cases
	*/
	
	private String ClientAccno3="";
	/**
	mandatory for all the cases */
	
	private String ClientMicrno3="";
	/**
	mandatory for all the cases */
	
	private String ClientNeftIfsccode3="";
	/**
	mandatory for all the cases -only one bank can be default bank */
	
	private String DefaultBankFlag3="";
	/**
	mandatory for all the cases varchar2 SB/CB/NE/NO Account Type
	*/
	
	private String ClientAcctype4="";
	/**
	mandatory for all the cases
	*/
	
	private String ClientAccno4="";
	/**
	mandatory for all the cases */
	
	private String ClientMicrno4="";
	/**
	mandatory for all the cases */
	
	private String ClientNeftIfsccode4="";
	/**
	mandatory for all the cases -only one bank can be default bank */
	
	private String DefaultBankFlag4="";
	/**
	mandatory for all the cases varchar2 SB/CB/NE/NO Account Type
	*/
	
	private String ClientAcctype5="";
	/**
	mandatory for all the cases
	*/
	
	private String ClientAccno5="";
	/**
	mandatory for all the cases */
	
	private String ClientMicrno5="";
	/**
	mandatory for all the cases */
	
	private String ClientNeftIfsccode5="";
	/**
	mandatory for all the cases -only one bank can be default bank */
	
	private String DefaultBankFlag5="";
	/**
	mandatory for all the cases
	*/
	
	private String ClientChequename5="";
	
	
	private String ClientAdd1="";
	
	private String ClientAdd2="";
	
	private String ClientAdd3="";
	
	private String ClientCity="";
	
	private String ClientState="";
	
	private String ClientPincode="";
	
	private String ClientCountry="";
	
	private String ClientResiphone="";
	
	private String ClientResifax="";
	
	private String ClientOfficephone="";
	
	private String ClientOfficefax="";
	
	private String ClientEmail="";
	/**
	Communication Mode 
	P : Physical
	E :Electronic
	M : MOBILE
	*/
	
	private String ClientCommmode="";
	/**01/02/03/04/05 **/
	
	private String ClientDivpaymode="";
	
	private String ClientPan2="";
	
	private String ClientPan3="";
	
	private String MapinNo="";
	
	private String Cm_foradd1="";
	
	private String Cm_foradd2="";
	
	private String Cm_foradd3="";
	
	private String Cm_forcity="";
	
	private String Cm_forpincode="";
	
	private String Cm_forstate="";
	
	private String Cm_forcountry="";
	
	private String Cm_forresiphone="";
	
	private String Cm_forresifax="";
	
	private String Cm_foroffphone="";
	
	private String Cm_forofffax="";
	
	private String Cm_mobile="";
	
	
	public String getClientCode() {
		return ClientCode;
	}
	public void setClientCode(String clientCode) {
		ClientCode = clientCode;
	}
	
	public String getRegistrationtype() {
		return registrationtype;
	}
	public void setRegistrationtype(String registrationtype) {
		this.registrationtype = registrationtype;
	}
	public String getClientHolding() {
		return ClientHolding;
	}
	public void setClientHolding(String clientHolding) {
		ClientHolding = clientHolding;
	}
	public String getClientTaxstatu() {
		return ClientTaxstatu;
	}
	public void setClientTaxstatu(String clientTaxstatu) {
		ClientTaxstatu = clientTaxstatu;
	}
	public String getClientOccupationcod() {
		return ClientOccupationcod;
	}
	public void setClientOccupationcod(String clientOccupationcod) {
		ClientOccupationcod = clientOccupationcod;
	}
	public String getClientAppname1() {
		return ClientAppname1;
	}
	public void setClientAppname1(String clientAppname1) {
		ClientAppname1 = clientAppname1;
	}
	public String getApplicant1fname() {
		return applicant1fname;
	}
	public void setApplicant1fname(String applicant1fname) {
		this.applicant1fname = applicant1fname;
	}
	public String getApplicant1mname() {
		return applicant1mname;
	}
	public void setApplicant1mname(String applicant1mname) {
		this.applicant1mname = applicant1mname;
	}
	public String getApplicant1lname() {
		return applicant1lname;
	}
	public void setApplicant1lname(String applicant1lname) {
		this.applicant1lname = applicant1lname;
	}
	public String getClientDob() {
		return ClientDob;
	}
	public void setClientDob(String clientDob) {
		ClientDob = clientDob;
	}
	public String getClientGender() {
		return ClientGender;
	}
	public void setClientGender(String clientGender) {
		ClientGender = clientGender;
	}
	public String getApplicant1panexempt() {
		return applicant1panexempt;
	}
	public void setApplicant1panexempt(String applicant1panexempt) {
		this.applicant1panexempt = applicant1panexempt;
	}
	public String getApplicant1panexemptcategory() {
		return applicant1panexemptcategory;
	}
	public void setApplicant1panexemptcategory(String applicant1panexemptcategory) {
		this.applicant1panexemptcategory = applicant1panexemptcategory;
	}
	public String getClientPan() {
		return ClientPan;
	}
	public void setClientPan(String clientPan) {
		ClientPan = clientPan;
	}
	public String getClientType() {
		return ClientType;
	}
	public void setClientType(String clientType) {
		ClientType = clientType;
	}
	public String getClientDefaultdp() {
		return ClientDefaultdp;
	}
	public void setClientDefaultdp(String clientDefaultdp) {
		ClientDefaultdp = clientDefaultdp;
	}
	public String getClienCdsldpid() {
		return ClienCdsldpid;
	}
	public void setClienCdsldpid(String clienCdsldpid) {
		ClienCdsldpid = clienCdsldpid;
	}
	public String getClientCdslcltid() {
		return ClientCdslcltid;
	}
	public void setClientCdslcltid(String clientCdslcltid) {
		ClientCdslcltid = clientCdslcltid;
	}
	public String getClientNsdldpid() {
		return ClientNsdldpid;
	}
	public void setClientNsdldpid(String clientNsdldpid) {
		ClientNsdldpid = clientNsdldpid;
	}
	public String getClientNsdlcltid() {
		return ClientNsdlcltid;
	}
	public void setClientNsdlcltid(String clientNsdlcltid) {
		ClientNsdlcltid = clientNsdlcltid;
	}
	public String getApplicant1kyctype() {
		return applicant1kyctype;
	}
	public void setApplicant1kyctype(String applicant1kyctype) {
		this.applicant1kyctype = applicant1kyctype;
	}
	public String getApplicant1ckycno() {
		return applicant1ckycno;
	}
	public void setApplicant1ckycno(String applicant1ckycno) {
		this.applicant1ckycno = applicant1ckycno;
	}
	public String getApplicant1kraexemptno() {
		return applicant1kraexemptno;
	}
	public void setApplicant1kraexemptno(String applicant1kraexemptno) {
		this.applicant1kraexemptno = applicant1kraexemptno;
	}
	public String getApplicant1aadhaarupdated() {
		return applicant1aadhaarupdated;
	}
	public void setApplicant1aadhaarupdated(String applicant1aadhaarupdated) {
		this.applicant1aadhaarupdated = applicant1aadhaarupdated;
	}
	public String getPaperlessflag() {
		return paperlessflag;
	}
	public void setPaperlessflag(String paperlessflag) {
		this.paperlessflag = paperlessflag;
	}
	public String getClientAppname2() {
		return ClientAppname2;
	}
	public void setClientAppname2(String clientAppname2) {
		ClientAppname2 = clientAppname2;
	}
	public String getApplicant2fname() {
		return applicant2fname;
	}
	public void setApplicant2fname(String applicant2fname) {
		this.applicant2fname = applicant2fname;
	}
	public String getApplicant2mname() {
		return applicant2mname;
	}
	public void setApplicant2mname(String applicant2mname) {
		this.applicant2mname = applicant2mname;
	}
	public String getApplicant2lname() {
		return applicant2lname;
	}
	public void setApplicant2lname(String applicant2lname) {
		this.applicant2lname = applicant2lname;
	}
	public String getApplicant2dob() {
		return applicant2dob;
	}
	public void setApplicant2dob(String applicant2dob) {
		this.applicant2dob = applicant2dob;
	}
	public String getApplicant2panexempt() {
		return applicant2panexempt;
	}
	public void setApplicant2panexempt(String applicant2panexempt) {
		this.applicant2panexempt = applicant2panexempt;
	}
	public String getApplicant2panexemptcategory() {
		return applicant2panexemptcategory;
	}
	public void setApplicant2panexemptcategory(String applicant2panexemptcategory) {
		this.applicant2panexemptcategory = applicant2panexemptcategory;
	}
	public String getApplicant2kyctype() {
		return applicant2kyctype;
	}
	public void setApplicant2kyctype(String applicant2kyctype) {
		this.applicant2kyctype = applicant2kyctype;
	}
	public String getApplicant2ckycno() {
		return applicant2ckycno;
	}
	public void setApplicant2ckycno(String applicant2ckycno) {
		this.applicant2ckycno = applicant2ckycno;
	}
	public String getApplicant2kraexemptrefno() {
		return applicant2kraexemptrefno;
	}
	public void setApplicant2kraexemptrefno(String applicant2kraexemptrefno) {
		this.applicant2kraexemptrefno = applicant2kraexemptrefno;
	}
	public String getClientAppname3() {
		return ClientAppname3;
	}
	public void setClientAppname3(String clientAppname3) {
		ClientAppname3 = clientAppname3;
	}
	public String getApplicant3fname() {
		return applicant3fname;
	}
	public void setApplicant3fname(String applicant3fname) {
		this.applicant3fname = applicant3fname;
	}
	public String getApplicant3mname() {
		return applicant3mname;
	}
	public void setApplicant3mname(String applicant3mname) {
		this.applicant3mname = applicant3mname;
	}
	public String getApplicant3lname() {
		return applicant3lname;
	}
	public void setApplicant3lname(String applicant3lname) {
		this.applicant3lname = applicant3lname;
	}
	public String getApplicant3dob() {
		return applicant3dob;
	}
	public void setApplicant3dob(String applicant3dob) {
		this.applicant3dob = applicant3dob;
	}
	public String getApplicant3panexempt() {
		return applicant3panexempt;
	}
	public void setApplicant3panexempt(String applicant3panexempt) {
		this.applicant3panexempt = applicant3panexempt;
	}
	public String getApplicant3panexemptcategory() {
		return applicant3panexemptcategory;
	}
	public void setApplicant3panexemptcategory(String applicant3panexemptcategory) {
		this.applicant3panexemptcategory = applicant3panexemptcategory;
	}
	public String getApplicant3kyctype() {
		return applicant3kyctype;
	}
	public void setApplicant3kyctype(String applicant3kyctype) {
		this.applicant3kyctype = applicant3kyctype;
	}
	public String getApplicant3ckycno() {
		return applicant3ckycno;
	}
	public void setApplicant3ckycno(String applicant3ckycno) {
		this.applicant3ckycno = applicant3ckycno;
	}
	public String getApplicant3kraexemptrefno() {
		return applicant3kraexemptrefno;
	}
	public void setApplicant3kraexemptrefno(String applicant3kraexemptrefno) {
		this.applicant3kraexemptrefno = applicant3kraexemptrefno;
	}
	public String getClientGuardian() {
		return ClientGuardian;
	}
	public void setClientGuardian(String clientGuardian) {
		ClientGuardian = clientGuardian;
	}
	public String getGuardianfname() {
		return guardianfname;
	}
	public void setGuardianfname(String guardianfname) {
		this.guardianfname = guardianfname;
	}
	public String getGuardianmname() {
		return guardianmname;
	}
	public void setGuardianmname(String guardianmname) {
		this.guardianmname = guardianmname;
	}
	public String getGuardianlanme() {
		return guardianlanme;
	}
	public void setGuardianlanme(String guardianlanme) {
		this.guardianlanme = guardianlanme;
	}
	public String getGuardiandob() {
		return guardiandob;
	}
	public void setGuardiandob(String guardiandob) {
		this.guardiandob = guardiandob;
	}
	public String getClientGuardianpan() {
		return ClientGuardianpan;
	}
	public void setClientGuardianpan(String clientGuardianpan) {
		ClientGuardianpan = clientGuardianpan;
	}
	public String getGuardianpanexempt() {
		return guardianpanexempt;
	}
	public void setGuardianpanexempt(String guardianpanexempt) {
		this.guardianpanexempt = guardianpanexempt;
	}
	public String getGuardianpanexemptcateegory() {
		return guardianpanexemptcateegory;
	}
	public void setGuardianpanexemptcateegory(String guardianpanexemptcateegory) {
		this.guardianpanexemptcateegory = guardianpanexemptcateegory;
	}
	public String getGuardiankyctype() {
		return guardiankyctype;
	}
	public void setGuardiankyctype(String guardiankyctype) {
		this.guardiankyctype = guardiankyctype;
	}
	public String getGuardianckycno() {
		return guardianckycno;
	}
	public void setGuardianckycno(String guardianckycno) {
		this.guardianckycno = guardianckycno;
	}
	public String getGuardiankraexemptrefno() {
		return guardiankraexemptrefno;
	}
	public void setGuardiankraexemptrefno(String guardiankraexemptrefno) {
		this.guardiankraexemptrefno = guardiankraexemptrefno;
	}
	public String getClientNominee() {
		return ClientNominee;
	}
	public void setClientNominee(String clientNominee) {
		ClientNominee = clientNominee;
	}
	public String getClientNomineeRelation() {
		return ClientNomineeRelation;
	}
	public void setClientNomineeRelation(String clientNomineeRelation) {
		ClientNomineeRelation = clientNomineeRelation;
	}
	public String getNominee1applicable() {
		return nominee1applicable;
	}
	public void setNominee1applicable(String nominee1applicable) {
		this.nominee1applicable = nominee1applicable;
	}
	public String getNominee1minorflag() {
		return nominee1minorflag;
	}
	public void setNominee1minorflag(String nominee1minorflag) {
		this.nominee1minorflag = nominee1minorflag;
	}
	public String getNominee1dob() {
		return nominee1dob;
	}
	public void setNominee1dob(String nominee1dob) {
		this.nominee1dob = nominee1dob;
	}
	public String getNominee1guardianname() {
		return nominee1guardianname;
	}
	public void setNominee1guardianname(String nominee1guardianname) {
		this.nominee1guardianname = nominee1guardianname;
	}
	public String getClientAcctype1() {
		return ClientAcctype1;
	}
	public void setClientAcctype1(String clientAcctype1) {
		ClientAcctype1 = clientAcctype1;
	}
	public String getClientAccno1() {
		return ClientAccno1;
	}
	public void setClientAccno1(String clientAccno1) {
		ClientAccno1 = clientAccno1;
	}
	public String getClientMicrno1() {
		return ClientMicrno1;
	}
	public void setClientMicrno1(String clientMicrno1) {
		ClientMicrno1 = clientMicrno1;
	}
	public String getClientNeftIfsccode1() {
		return ClientNeftIfsccode1;
	}
	public void setClientNeftIfsccode1(String clientNeftIfsccode1) {
		ClientNeftIfsccode1 = clientNeftIfsccode1;
	}
	public String getDefaultBankFlag() {
		return DefaultBankFlag;
	}
	public void setDefaultBankFlag(String defaultBankFlag) {
		DefaultBankFlag = defaultBankFlag;
	}
	public String getClientAcctype2() {
		return ClientAcctype2;
	}
	public void setClientAcctype2(String clientAcctype2) {
		ClientAcctype2 = clientAcctype2;
	}
	public String getClientAccno2() {
		return ClientAccno2;
	}
	public void setClientAccno2(String clientAccno2) {
		ClientAccno2 = clientAccno2;
	}
	public String getClientMicrno2() {
		return ClientMicrno2;
	}
	public void setClientMicrno2(String clientMicrno2) {
		ClientMicrno2 = clientMicrno2;
	}
	public String getClientNeftIfsccode2() {
		return ClientNeftIfsccode2;
	}
	public void setClientNeftIfsccode2(String clientNeftIfsccode2) {
		ClientNeftIfsccode2 = clientNeftIfsccode2;
	}
	public String getDefaultBankFlag2() {
		return DefaultBankFlag2;
	}
	public void setDefaultBankFlag2(String defaultBankFlag2) {
		DefaultBankFlag2 = defaultBankFlag2;
	}
	public String getClientAcctype3() {
		return ClientAcctype3;
	}
	public void setClientAcctype3(String clientAcctype3) {
		ClientAcctype3 = clientAcctype3;
	}
	public String getClientAccno3() {
		return ClientAccno3;
	}
	public void setClientAccno3(String clientAccno3) {
		ClientAccno3 = clientAccno3;
	}
	public String getClientMicrno3() {
		return ClientMicrno3;
	}
	public void setClientMicrno3(String clientMicrno3) {
		ClientMicrno3 = clientMicrno3;
	}
	public String getClientNeftIfsccode3() {
		return ClientNeftIfsccode3;
	}
	public void setClientNeftIfsccode3(String clientNeftIfsccode3) {
		ClientNeftIfsccode3 = clientNeftIfsccode3;
	}
	public String getDefaultBankFlag3() {
		return DefaultBankFlag3;
	}
	public void setDefaultBankFlag3(String defaultBankFlag3) {
		DefaultBankFlag3 = defaultBankFlag3;
	}
	public String getClientAcctype4() {
		return ClientAcctype4;
	}
	public void setClientAcctype4(String clientAcctype4) {
		ClientAcctype4 = clientAcctype4;
	}
	public String getClientAccno4() {
		return ClientAccno4;
	}
	public void setClientAccno4(String clientAccno4) {
		ClientAccno4 = clientAccno4;
	}
	public String getClientMicrno4() {
		return ClientMicrno4;
	}
	public void setClientMicrno4(String clientMicrno4) {
		ClientMicrno4 = clientMicrno4;
	}
	public String getClientNeftIfsccode4() {
		return ClientNeftIfsccode4;
	}
	public void setClientNeftIfsccode4(String clientNeftIfsccode4) {
		ClientNeftIfsccode4 = clientNeftIfsccode4;
	}
	public String getDefaultBankFlag4() {
		return DefaultBankFlag4;
	}
	public void setDefaultBankFlag4(String defaultBankFlag4) {
		DefaultBankFlag4 = defaultBankFlag4;
	}
	public String getClientAcctype5() {
		return ClientAcctype5;
	}
	public void setClientAcctype5(String clientAcctype5) {
		ClientAcctype5 = clientAcctype5;
	}
	public String getClientAccno5() {
		return ClientAccno5;
	}
	public void setClientAccno5(String clientAccno5) {
		ClientAccno5 = clientAccno5;
	}
	public String getClientMicrno5() {
		return ClientMicrno5;
	}
	public void setClientMicrno5(String clientMicrno5) {
		ClientMicrno5 = clientMicrno5;
	}
	public String getClientNeftIfsccode5() {
		return ClientNeftIfsccode5;
	}
	public void setClientNeftIfsccode5(String clientNeftIfsccode5) {
		ClientNeftIfsccode5 = clientNeftIfsccode5;
	}
	public String getDefaultBankFlag5() {
		return DefaultBankFlag5;
	}
	public void setDefaultBankFlag5(String defaultBankFlag5) {
		DefaultBankFlag5 = defaultBankFlag5;
	}
	public String getClientChequename5() {
		return ClientChequename5;
	}
	public void setClientChequename5(String clientChequename5) {
		ClientChequename5 = clientChequename5;
	}
	public String getClientAdd1() {
		return ClientAdd1;
	}
	public void setClientAdd1(String clientAdd1) {
		ClientAdd1 = clientAdd1;
	}
	public String getClientAdd2() {
		return ClientAdd2;
	}
	public void setClientAdd2(String clientAdd2) {
		ClientAdd2 = clientAdd2;
	}
	public String getClientAdd3() {
		return ClientAdd3;
	}
	public void setClientAdd3(String clientAdd3) {
		ClientAdd3 = clientAdd3;
	}
	public String getClientCity() {
		return ClientCity;
	}
	public void setClientCity(String clientCity) {
		ClientCity = clientCity;
	}
	public String getClientState() {
		return ClientState;
	}
	public void setClientState(String clientState) {
		ClientState = clientState;
	}
	public String getClientPincode() {
		return ClientPincode;
	}
	public void setClientPincode(String clientPincode) {
		ClientPincode = clientPincode;
	}
	public String getClientCountry() {
		return ClientCountry;
	}
	public void setClientCountry(String clientCountry) {
		ClientCountry = clientCountry;
	}
	public String getClientResiphone() {
		return ClientResiphone;
	}
	public void setClientResiphone(String clientResiphone) {
		ClientResiphone = clientResiphone;
	}
	public String getClientResifax() {
		return ClientResifax;
	}
	public void setClientResifax(String clientResifax) {
		ClientResifax = clientResifax;
	}
	public String getClientOfficephone() {
		return ClientOfficephone;
	}
	public void setClientOfficephone(String clientOfficephone) {
		ClientOfficephone = clientOfficephone;
	}
	public String getClientOfficefax() {
		return ClientOfficefax;
	}
	public void setClientOfficefax(String clientOfficefax) {
		ClientOfficefax = clientOfficefax;
	}
	public String getClientEmail() {
		return ClientEmail;
	}
	public void setClientEmail(String clientEmail) {
		ClientEmail = clientEmail;
	}
	public String getClientCommmode() {
		return ClientCommmode;
	}
	public void setClientCommmode(String clientCommmode) {
		ClientCommmode = clientCommmode;
	}
	public String getClientDivpaymode() {
		return ClientDivpaymode;
	}
	public void setClientDivpaymode(String clientDivpaymode) {
		ClientDivpaymode = clientDivpaymode;
	}
	public String getClientPan2() {
		return ClientPan2;
	}
	public void setClientPan2(String clientPan2) {
		ClientPan2 = clientPan2;
	}
	public String getClientPan3() {
		return ClientPan3;
	}
	public void setClientPan3(String clientPan3) {
		ClientPan3 = clientPan3;
	}
	public String getMapinNo() {
		return MapinNo;
	}
	public void setMapinNo(String mapinNo) {
		MapinNo = mapinNo;
	}
	public String getCm_foradd1() {
		return Cm_foradd1;
	}
	public void setCm_foradd1(String cm_foradd1) {
		Cm_foradd1 = cm_foradd1;
	}
	public String getCm_foradd2() {
		return Cm_foradd2;
	}
	public void setCm_foradd2(String cm_foradd2) {
		Cm_foradd2 = cm_foradd2;
	}
	public String getCm_foradd3() {
		return Cm_foradd3;
	}
	public void setCm_foradd3(String cm_foradd3) {
		Cm_foradd3 = cm_foradd3;
	}
	public String getCm_forcity() {
		return Cm_forcity;
	}
	public void setCm_forcity(String cm_forcity) {
		Cm_forcity = cm_forcity;
	}
	public String getCm_forpincode() {
		return Cm_forpincode;
	}
	public void setCm_forpincode(String cm_forpincode) {
		Cm_forpincode = cm_forpincode;
	}
	public String getCm_forstate() {
		return Cm_forstate;
	}
	public void setCm_forstate(String cm_forstate) {
		Cm_forstate = cm_forstate;
	}
	public String getCm_forcountry() {
		return Cm_forcountry;
	}
	public void setCm_forcountry(String cm_forcountry) {
		Cm_forcountry = cm_forcountry;
	}
	public String getCm_forresiphone() {
		return Cm_forresiphone;
	}
	public void setCm_forresiphone(String cm_forresiphone) {
		Cm_forresiphone = cm_forresiphone;
	}
	public String getCm_forresifax() {
		return Cm_forresifax;
	}
	public void setCm_forresifax(String cm_forresifax) {
		Cm_forresifax = cm_forresifax;
	}
	public String getCm_foroffphone() {
		return Cm_foroffphone;
	}
	public void setCm_foroffphone(String cm_foroffphone) {
		Cm_foroffphone = cm_foroffphone;
	}
	public String getCm_forofffax() {
		return Cm_forofffax;
	}
	public void setCm_forofffax(String cm_forofffax) {
		Cm_forofffax = cm_forofffax;
	}
	public String getCm_mobile() {
		return Cm_mobile;
	}
	public void setCm_mobile(String cm_mobile) {
		Cm_mobile = cm_mobile;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
