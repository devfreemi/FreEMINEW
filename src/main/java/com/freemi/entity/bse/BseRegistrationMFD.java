package com.freemi.entity.bse;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BseRegistrationMFD implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty
	private String ClientCode="";
	@JsonProperty
	private String ClientHolding="";
	@JsonProperty
	private String ClientTaxstatu="";
	@JsonProperty
	private String ClientOccupationcod="";
	@JsonProperty
	private String ClientAppname1="";
	@JsonProperty
	private String ClientAppname2="";
	@JsonProperty
	private String ClientAppname3="";
	@JsonProperty
	private String ClientDob="";
	@JsonProperty
	private String ClientGender="";
	@JsonProperty
	private String ClientGuardian="";
	@JsonProperty
	private String ClientPan="";
	@JsonProperty
	private String ClientNominee="";
	@JsonProperty
	private String ClientNomineeRelation="";
	@JsonProperty
	private String ClientGuardianpan="";
	@JsonProperty
	private String ClientType="";
	/**
	mandatory if value is "D" in client type
	*/
	@JsonProperty
	private String ClientDefaultdp="";
	/**
	mandatory if value is "C" in client default DP */
	@JsonProperty
	private String ClienCdsldpid="";
	/**
	mandatory if value is "C" in client default DP
	*/
	@JsonProperty
	private String ClientCdslcltid="";
	/**
	mandatory if value is "N" in client default DP */
	@JsonProperty
	private String ClientNsdldpid="";
	/**
	mandatory if value is "N" in client default DP */
	@JsonProperty
	private String ClientNsdlcltid="";
	/**
	mandatory for all the cases varchar2 SB/CB/NE/NO Account Type
	*/
	@JsonProperty
	private String ClientAcctype1="";
	/**
	mandatory for all the cases
	*/
	@JsonProperty
	private String ClientAccno1="";
	/**
	mandatory for all the cases */
	@JsonProperty
	private String ClientMicrno1="";
	/**
	mandatory for all the cases */
	@JsonProperty
	private String ClientNeftIfsccode1="";
	/**
	mandatory for all the cases -only one bank can be default bank */
	@JsonProperty
	private String DefaultBankFlag="";
	/**
	mandatory for all the cases varchar2 SB/CB/NE/NO Account Type
	*/
	@JsonProperty
	private String ClientAcctype2="";
	/**
	mandatory for all the cases
	*/
	@JsonProperty
	private String ClientAccno2="";
	/**
	mandatory for all the cases */
	@JsonProperty
	private String ClientMicrno2="";
	/**
	mandatory for all the cases */
	@JsonProperty
	private String ClientNeftIfsccode2="";
	/**
	mandatory for all the cases -only one bank can be default bank */
	@JsonProperty
	private String DefaultBankFlag2="";

	/**
	mandatory for all the cases varchar2 SB/CB/NE/NO Account Type
	*/
	@JsonProperty
	private String ClientAcctype3="";
	/**
	mandatory for all the cases
	*/
	@JsonProperty
	private String ClientAccno3="";
	/**
	mandatory for all the cases */
	@JsonProperty
	private String ClientMicrno3="";
	/**
	mandatory for all the cases */
	@JsonProperty
	private String ClientNeftIfsccode3="";
	/**
	mandatory for all the cases -only one bank can be default bank */
	@JsonProperty
	private String DefaultBankFlag3="";
	/**
	mandatory for all the cases varchar2 SB/CB/NE/NO Account Type
	*/
	@JsonProperty
	private String ClientAcctype4="";
	/**
	mandatory for all the cases
	*/
	@JsonProperty
	private String ClientAccno4="";
	/**
	mandatory for all the cases */
	@JsonProperty
	private String ClientMicrno4="";
	/**
	mandatory for all the cases */
	@JsonProperty
	private String ClientNeftIfsccode4="";
	/**
	mandatory for all the cases -only one bank can be default bank */
	@JsonProperty
	private String DefaultBankFlag4="";
	/**
	mandatory for all the cases varchar2 SB/CB/NE/NO Account Type
	*/
	@JsonProperty
	private String ClientAcctype5="";
	/**
	mandatory for all the cases
	*/
	@JsonProperty
	private String ClientAccno5="";
	/**
	mandatory for all the cases */
	@JsonProperty
	private String ClientMicrno5="";
	/**
	mandatory for all the cases */
	@JsonProperty
	private String ClientNeftIfsccode5="";
	/**
	mandatory for all the cases -only one bank can be default bank */
	@JsonProperty
	private String DefaultBankFlag5="";
	/**
	mandatory for all the cases
	*/
	@JsonProperty
	private String ClientChequename5="";
	
	@JsonProperty
	private String ClientAdd1="";
	@JsonProperty
	private String ClientAdd2="";
	@JsonProperty
	private String ClientAdd3="";
	@JsonProperty
	private String ClientCity="";
	@JsonProperty
	private String ClientState="";
	@JsonProperty
	private String ClientPincode="";
	@JsonProperty
	private String ClientCountry="";
	@JsonProperty
	private String ClientResiphone="";
	@JsonProperty
	private String ClientResifax="";
	@JsonProperty
	private String ClientOfficephone="";
	@JsonProperty
	private String ClientOfficefax="";
	@JsonProperty
	private String ClientEmail="";
	/**
	Communication Mode 
	P : Physical
	E :Electronic
	M : MOBILE
	*/
	@JsonProperty
	private String ClientCommmode="";
	/**01/02/03/04/05 **/
	@JsonProperty
	private String ClientDivpaymode="";
	@JsonProperty
	private String ClientPan2="";
	@JsonProperty
	private String ClientPan3="";
	@JsonProperty
	private String MapinNo="";
	@JsonProperty
	private String Cm_foradd1="";
	@JsonProperty
	private String Cm_foradd2="";
	@JsonProperty
	private String Cm_foradd3="";
	@JsonProperty
	private String Cm_forcity="";
	@JsonProperty
	private String Cm_forpincode="";
	@JsonProperty
	private String Cm_forstate="";
	@JsonProperty
	private String Cm_forcountry="";
	@JsonProperty
	private String Cm_forresiphone="";
	@JsonProperty
	private String Cm_forresifax="";
	@JsonProperty
	private String Cm_foroffphone="";
	@JsonProperty
	private String Cm_forofffax="";
	@JsonProperty
	private String Cm_mobile="";
	
	public BseRegistrationMFD() {
		System.out.println("@@@ Calling Default Constractor @@@@");
	}
	
	public BseRegistrationMFD(String ClientCode) {
		System.out.println("@@@ Calling Client Code Constractor @@@@");
		this.ClientCode=ClientCode;
	}
	
	public BseRegistrationMFD(String ClientCode, String ClientHolding, String ClientTaxstatu, String ClientOccupationcod, String ClientAppname1,
			 String ClientDob, String ClientGender,String ClientPan, String ClientType,String ClientAcctype1,String ClientAccno1,String ClientMicrno1, String ClientNeftIfsccode1,
			 String DefaultBankFlag,String ClientAdd1,String ClientCity,String ClientState,String ClientPincode,String ClientCountry,String ClientEmail,
			 String ClientCommmode,String ClientDivpaymode,String Cm_mobile) {
		System.out.println("@@@ Calling mandatory Param Constractor @@@@");
		this.ClientCode=ClientCode;
		this.ClientHolding=ClientHolding;
		this.ClientTaxstatu = ClientTaxstatu;		
		this.ClientOccupationcod = ClientOccupationcod;
		this.ClientAppname1 = ClientAppname1;		
		this.ClientDob= ClientDob;
		this.ClientGender = ClientGender;
		this.ClientPan = ClientPan;
	    this.ClientType=ClientType;		
		this.ClientAcctype1=ClientAcctype1;
		this.ClientAccno1=ClientAccno1;
		this.ClientMicrno1=ClientMicrno1;
		this.ClientNeftIfsccode1=ClientNeftIfsccode1;
		this.DefaultBankFlag=DefaultBankFlag;		
		this.ClientAdd1=ClientAdd1;		
		this.ClientCity=ClientCity;
		this.ClientState=ClientState;
		this.ClientPincode=ClientPincode;
		this.ClientCountry=ClientCountry;		
		this.ClientEmail=ClientEmail;
		this.ClientCommmode=ClientCommmode;
		this.ClientDivpaymode=ClientDivpaymode;		
		this.Cm_mobile=Cm_mobile;
		
		
	}
	public BseRegistrationMFD(String ClientCode, String ClientHolding, String ClientTaxstatu, String ClientOccupationcod, String ClientAppname1,
			String ClientAppname2, String ClientAppname3, String ClientDob, String ClientGender, String ClientGuardian, String ClientPan, String ClientNominee,
            String ClientNomineeRelation, String ClientGuardianpan,String ClientType,
            String ClientDefaultdp, String ClienCdsldpid,String ClientCdslcltid,String ClientNsdldpid,String ClientNsdlcltid,String ClientAcctype1,String ClientAccno1,String ClientMicrno1,
            String ClientNeftIfsccode1,String DefaultBankFlag,String ClientAcctype2,String ClientAccno2,String ClientMicrno2,String ClientNeftIfsccode2,String DefaultBankFlag2,
            String ClientAcctype3,String ClientAccno3,String ClientMicrno3,String ClientNeftIfsccode3,String DefaultBankFlag3,String ClientAcctype4, String ClientAccno4,String ClientMicrno4,String ClientNeftIfsccode4,
            String DefaultBankFlag4,String ClientAcctype5, String ClientAccno5,String ClientMicrno5,String ClientNeftIfsccode5,String DefaultBankFlag5,String ClientChequename5, String ClientAdd1,String ClientAdd2,String ClientAdd3,
            String ClientCity,String ClientState,String ClientPincode,String ClientCountry,String ClientResiphone,String ClientResifax,String ClientOfficephone,String ClientOfficefax,String ClientEmail,String ClientCommmode,
            String ClientDivpaymode,String ClientPan2,String ClientPan3,String MapinNo,String Cm_foradd1,String Cm_foradd2,String Cm_foradd3,String Cm_forcity,
            String Cm_forpincode,String Cm_forstate,String Cm_forcountry,String Cm_forresiphone,String Cm_forresifax,String Cm_foroffphone,String Cm_forofffax,String Cm_mobile) {
		this.ClientCode=ClientCode;
		this.ClientHolding=ClientHolding;
		this.ClientTaxstatu = ClientTaxstatu;		
		this.ClientOccupationcod = ClientOccupationcod;
		this.ClientAppname1 = ClientAppname1;
		this.ClientAppname2= ClientAppname2;
		this.ClientAppname3= ClientAppname3;
		this.ClientDob= ClientDob;
		this.ClientGender = ClientGender;
		this.ClientGuardian=ClientGuardian;
		this.ClientPan=ClientPan;
		this.ClientNominee=ClientNominee;
		this.ClientNomineeRelation = ClientNomineeRelation;
		this.ClientGuardianpan=ClientGuardianpan;
		this.ClientType=ClientType;
		
		this.ClientDefaultdp=ClientDefaultdp;
		this.ClienCdsldpid=ClienCdsldpid;
		this.ClientCdslcltid=ClientCdslcltid;
		this.ClientNsdldpid=ClientNsdldpid;
		this.ClientNsdlcltid=ClientNsdlcltid;
		this.ClientAcctype1=ClientAcctype1;
		this.ClientAccno1=ClientAccno1;
		this.ClientMicrno1=ClientMicrno1;
		this.ClientNeftIfsccode1=ClientNeftIfsccode1;
		this.DefaultBankFlag=DefaultBankFlag;
		this.ClientAcctype2=ClientAcctype2;
		this.ClientAccno2=ClientAccno2;
		this.ClientMicrno2=ClientMicrno2;
		this.ClientNeftIfsccode2=ClientNeftIfsccode2;
		this.DefaultBankFlag2=DefaultBankFlag2;
		this.ClientAcctype3=ClientAcctype3;
		this.ClientAccno3=ClientAccno3;
		this.ClientMicrno3=ClientMicrno3;
		this.ClientNeftIfsccode3=ClientNeftIfsccode3;		
		this.DefaultBankFlag3=DefaultBankFlag3;
		
		this.ClientAcctype4=ClientAcctype4;
		this.ClientAccno4=ClientAccno4;
		this.ClientMicrno4=ClientMicrno4;
		this.ClientNeftIfsccode4=ClientNeftIfsccode4;
		this.DefaultBankFlag4=DefaultBankFlag4;
		this.ClientAcctype5=ClientAcctype5;
		this.ClientAccno5=ClientAccno5;
		this.ClientMicrno5=ClientMicrno5;
		this.ClientNeftIfsccode5=ClientNeftIfsccode5;
		this.DefaultBankFlag5=DefaultBankFlag5;
		this.ClientChequename5 = ClientChequename5;
		this.ClientAdd1=ClientAdd1;
		this.ClientAdd2=ClientAdd2;
		this.ClientAdd3=ClientAdd3;
		this.ClientCity=ClientCity;
		this.ClientState=ClientState;
		this.ClientPincode=ClientPincode;
		this.ClientCountry=ClientCountry;
		this.ClientResiphone=ClientResiphone;
		this.ClientResifax=ClientResifax;
		this.ClientOfficefax=ClientOfficefax;
		this.ClientEmail=ClientEmail;
		this.ClientCommmode=ClientCommmode;
		this.ClientDivpaymode=ClientDivpaymode;
		this.ClientPan2=ClientPan2;
		this.ClientPan3=ClientPan3;
		this.MapinNo=MapinNo;
		this.Cm_foradd1=Cm_foradd1;
		this.Cm_foradd2=Cm_foradd2;
		this.Cm_foradd3=Cm_foradd3;
		this.Cm_forcity=Cm_forcity;
		this.Cm_forpincode=Cm_forpincode;
		this.Cm_forstate=Cm_forstate;
		this.Cm_forcountry=Cm_forcountry;
		this.Cm_forresiphone=Cm_forresiphone;
		this.Cm_forresifax=Cm_forresifax;
		this.Cm_foroffphone=Cm_foroffphone;
		this.Cm_forofffax=Cm_forofffax;
		this.Cm_mobile=Cm_mobile;
		
		
	}
	
	
	public String getClientCode() {
		return ClientCode;
	}
	public void setClientCode(String clientCode) {
		ClientCode = clientCode;
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
	public String getClientAppname2() {
		return ClientAppname2;
	}
	public void setClientAppname2(String clientAppname2) {
		ClientAppname2 = clientAppname2;
	}
	public String getClientAppname3() {
		return ClientAppname3;
	}
	public void setClientAppname3(String clientAppname3) {
		ClientAppname3 = clientAppname3;
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
	public String getClientGuardian() {
		return ClientGuardian;
	}
	public void setClientGuardian(String clientGuardian) {
		ClientGuardian = clientGuardian;
	}
	public String getClientPan() {
		return ClientPan;
	}
	public void setClientPan(String clientPan) {
		ClientPan = clientPan;
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
	public String getClientGuardianpan() {
		return ClientGuardianpan;
	}
	public void setClientGuardianpan(String clientGuardianpan) {
		ClientGuardianpan = clientGuardianpan;
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
	@Override
	public String toString() {
		return "ClientMFD [ClientCode=" + ClientCode + ", ClientHolding=" + ClientHolding + ", ClientTaxstatu="
				+ ClientTaxstatu + ", ClientOccupationcod=" + ClientOccupationcod + ", ClientAppname1=" + ClientAppname1
				+ ", ClientAppname2=" + ClientAppname2 + ", ClientAppname3=" + ClientAppname3 + ", ClientDob="
				+ ClientDob + ", ClientGender=" + ClientGender + ", ClientGuardian=" + ClientGuardian + ", ClientPan="
				+ ClientPan + ", ClientNominee=" + ClientNominee + ", ClientNomineeRelation=" + ClientNomineeRelation
				+ ", ClientGuardianpan=" + ClientGuardianpan + ", ClientType=" + ClientType + ", ClientDefaultdp="
				+ ClientDefaultdp + ", ClienCdsldpid=" + ClienCdsldpid + ", ClientCdslcltid=" + ClientCdslcltid
				+ ", ClientNsdldpid=" + ClientNsdldpid + ", ClientNsdlcltid=" + ClientNsdlcltid + ", ClientAcctype1="
				+ ClientAcctype1 + ", ClientAccno1=" + ClientAccno1 + ", ClientMicrno1=" + ClientMicrno1
				+ ", ClientNeftIfsccode1=" + ClientNeftIfsccode1 + ", DefaultBankFlag=" + DefaultBankFlag
				+ ", ClientAcctype2=" + ClientAcctype2 + ", ClientAccno2=" + ClientAccno2 + ", ClientMicrno2="
				+ ClientMicrno2 + ", ClientNeftIfsccode2=" + ClientNeftIfsccode2 + ", DefaultBankFlag2="
				+ DefaultBankFlag2 + ", ClientAcctype3=" + ClientAcctype3 + ", ClientAccno3=" + ClientAccno3
				+ ", ClientMicrno3=" + ClientMicrno3 + ", ClientNeftIfsccode3=" + ClientNeftIfsccode3
				+ ", DefaultBankFlag3=" + DefaultBankFlag3 + ", ClientAcctype4=" + ClientAcctype4 + ", ClientAccno4="
				+ ClientAccno4 + ", ClientMicrno4=" + ClientMicrno4 + ", ClientNeftIfsccode4=" + ClientNeftIfsccode4
				+ ", DefaultBankFlag4=" + DefaultBankFlag4 + ", ClientAcctype5=" + ClientAcctype5 + ", ClientAccno5="
				+ ClientAccno5 + ", ClientMicrno5=" + ClientMicrno5 + ", ClientNeftIfsccode5=" + ClientNeftIfsccode5
				+ ", DefaultBankFlag5=" + DefaultBankFlag5 + ", ClientAdd1=" + ClientAdd1 + ", ClientAdd2=" + ClientAdd2
				+ ", ClientAdd3=" + ClientAdd3 + ", ClientCity=" + ClientCity + ", ClientState=" + ClientState
				+ ", ClientPincode=" + ClientPincode + ", ClientCountry=" + ClientCountry + ", ClientResiphone="
				+ ClientResiphone + ", ClientResifax=" + ClientResifax + ", ClientOfficephone=" + ClientOfficephone
				+ ", ClientOfficefax=" + ClientOfficefax + ", ClientEmail=" + ClientEmail + ", ClientCommmode="
				+ ClientCommmode + ", ClientDivpaymode=" + ClientDivpaymode + ", ClientPan2=" + ClientPan2
				+ ", ClientPan3=" + ClientPan3 + ", MapinNo=" + MapinNo + ", Cm_foradd1=" + Cm_foradd1 + ", Cm_foradd2="
				+ Cm_foradd2 + ", Cm_foradd3=" + Cm_foradd3 + ", Cm_forcity=" + Cm_forcity + ", Cm_forpincode="
				+ Cm_forpincode + ", Cm_forstate=" + Cm_forstate + ", Cm_forcountry=" + Cm_forcountry
				+ ", Cm_forresiphone=" + Cm_forresiphone + ", Cm_forresifax=" + Cm_forresifax + ", Cm_foroffphone="
				+ Cm_foroffphone + ", Cm_forofffax=" + Cm_forofffax + ", Cm_mobile=" + Cm_mobile + "]";
	}

	public String getClientChequename5() {
		return ClientChequename5;
	}

	public void setClientChequename5(String clientChequename5) {
		ClientChequename5 = clientChequename5;
	}


}
