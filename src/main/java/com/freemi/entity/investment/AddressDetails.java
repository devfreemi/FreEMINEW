package com.freemi.entity.investment;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Proxy;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="bsemf_customers_address")
@Proxy(lazy=false)
public class AddressDetails implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
//	@Transient
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="SL_NO")
	private long Serial;
	
	@Id
	@Column(name="CLIENT_ID")
	private String clientID;
	
	@Column(name="ADDRESS_1")
	private String address1="";
	
	@Column(name="ADDRESS_2")
	private String address2="";
	
	@Column(name="ADDRESS_3")
	private String address3="";
	
	@Column(name="CITY")
	private String city="";
	
	@Column(name="PINCODE")
	@Pattern(regexp="[0-9]{6}",message="Enter valid ZIP code")
	@Size(max=6, message="PIN code must be 6 digit")
	private String pinCode="";
	
	@NotEmpty(message="Mention your state")
	@Column(name="STATE")
	private String state="";
	
	@Column(name="COUNTRY")
	private String country="INDIA";
	
	@Transient
	private String communicationCode;
	
	@OneToOne(fetch= FetchType.LAZY, optional=false, cascade=CascadeType.ALL)
	@JoinColumn(name="CLIENT_ID", nullable= false,insertable=false,updatable=false)
	private BseMFInvestForm mfForm;

	public long getSerial() {
		return Serial;
	}

	public void setSerial(long serial) {
		Serial = serial;
	}

	public String getClientID() {
		return clientID;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCommunicationCode() {
		return communicationCode;
	}

	public void setCommunicationCode(String communicationCode) {
		this.communicationCode = communicationCode;
	}

	public BseMFInvestForm getMfForm() {
		return mfForm;
	}

	public void setMfForm(BseMFInvestForm mfForm) {
		this.mfForm = mfForm;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
