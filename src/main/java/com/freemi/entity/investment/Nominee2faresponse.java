package com.freemi.entity.investment;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Proxy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Table(name="bsemf_nominee_2fa_status")
@Proxy(lazy=false)
public class Nominee2faresponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="SL_NO")
	private int serial;
	
	@JsonIgnore
	@Column(name="CLIENT_ID")
	private String clientid;
	
	@JsonIgnore
	@Column(name="NOMINEE_SL_NO")
	private String nomineeserial;
	
	@JsonProperty("Filler1")
	@Transient
	private String filler1;
	
	@JsonProperty("Filler2")
	@Transient
	private String filler2;
	
	@JsonProperty("Filler3")
	@Transient
	private String filler3;
	
	@JsonProperty("Type")
	@Transient
	private String type;
	
	@JsonProperty("LoopbackReturnUrl")
	@Transient
	private String loopbackurl;
	
	@JsonProperty("ErrorDescription")
	@Column(name="REMARKS")
	private String errordescription;
	
	@JsonProperty("StatusCode")
	@Column(name="STATUS_AFTER_2FA")
	private String statuscode;
	
	@JsonProperty("InternalRefrenceNo")
	@Column(name="INTERNAL_REF_NO")
	private String internalrefno;
	
	@JsonProperty("ReturnUrl")
	@Transient
	private String returnrul;

	public int getSerial() {
		return serial;
	}

	public void setSerial(int serial) {
		this.serial = serial;
	}

	public String getClientid() {
		return clientid;
	}

	public void setClientid(String clientid) {
		this.clientid = clientid;
	}

	public String getNomineeserial() {
		return nomineeserial;
	}

	public void setNomineeserial(String nomineeserial) {
		this.nomineeserial = nomineeserial;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLoopbackurl() {
		return loopbackurl;
	}

	public void setLoopbackurl(String loopbackurl) {
		this.loopbackurl = loopbackurl;
	}

	public String getErrordescription() {
		return errordescription;
	}

	public void setErrordescription(String errordescription) {
		this.errordescription = errordescription;
	}

	public String getStatuscode() {
		return statuscode;
	}

	public void setStatuscode(String statuscode) {
		this.statuscode = statuscode;
	}

	public String getInternalrefno() {
		return internalrefno;
	}

	public void setInternalrefno(String internalrefno) {
		this.internalrefno = internalrefno;
	}

	public String getReturnrul() {
		return returnrul;
	}

	public void setReturnrul(String returnrul) {
		this.returnrul = returnrul;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
