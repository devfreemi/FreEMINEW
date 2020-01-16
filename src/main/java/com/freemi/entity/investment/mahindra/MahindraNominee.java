package com.freemi.entity.investment.mahindra;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name="mahindra_customers_nominee")
@Proxy(lazy=false)
public class MahindraNominee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "SL_NO")
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer serial;
	
	@Column(name = "CUSTOMER_REF_ID")
	private String customerId;
	
	@Column(name="NOMINEE_DETAILS_UNIQUE_ID")
	private String nomineeDetailsId;
	
	@Column(name="NOMINEE_FULLNAME")
	private String fullname;
	
	@Column(name="NOMINEE_PREFIX")
	private String nomineeprefix;
	
	@Column(name="NOMINEE_FNAME")
	private String fname;
	
	@Column(name="NOMINEE_MNAME")
	private String mname;
	
	@Column(name="NOMINEE_LNAME")
	private String lname;
	
	@Column(name="NOMINEE_RELATION")
	private String relation;
	
	@Column(name="NOMINEE_MINOR")
	private String minor;
	
	@Column(name="NOMINEE_GUARDIAN")
	private String nomineeguardian;
	
	@Column(name="NOMINEE_DOB")
	private Date nomineedob;
	
	@Column(name = "MOBILE")
	private String nomineemobile;
	
	@Column(name="EMAILID")
	private String nomineeemail;
	
	@Column(name = "ADDRESS1_1")
	private String address1;
	
	@Column(name = "ADDRESS1_2")
	private String address2;
	
	@Column(name = "ADDRESS1_3")
	private String address3;
	
	@Column(name = "ADDRESS_COUNTRY_1")
	private String country1;
	
	@Column(name = "ADDRESS_STATE_1")
	private String state1;
	
	@Column(name = "ADDRESS_DISTRICT_1")
	private String district1;
	
	@Column(name = "ADDRESS_CITY_1")
	private String city1;
	
	@Column(name = "ADDRESS_PINCODE_1")
	private String pincode1;
	
	@Column(name = "IS_ACTIVE")
	private String active="Y";

	public Integer getSerial() {
		return serial;
	}

	public void setSerial(Integer serial) {
		this.serial = serial;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getNomineeDetailsId() {
		return nomineeDetailsId;
	}

	public void setNomineeDetailsId(String nomineeDetailsId) {
		this.nomineeDetailsId = nomineeDetailsId;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getNomineeprefix() {
		return nomineeprefix;
	}

	public void setNomineeprefix(String nomineeprefix) {
		this.nomineeprefix = nomineeprefix;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getMinor() {
		return minor;
	}

	public void setMinor(String minor) {
		this.minor = minor;
	}

	public String getNomineeguardian() {
		return nomineeguardian;
	}

	public void setNomineeguardian(String nomineeguardian) {
		this.nomineeguardian = nomineeguardian;
	}

	public Date getNomineedob() {
		return nomineedob;
	}

	public void setNomineedob(Date nomineedob) {
		this.nomineedob = nomineedob;
	}

	public String getNomineemobile() {
		return nomineemobile;
	}

	public void setNomineemobile(String nomineemobile) {
		this.nomineemobile = nomineemobile;
	}

	public String getNomineeemail() {
		return nomineeemail;
	}

	public void setNomineeemail(String nomineeemail) {
		this.nomineeemail = nomineeemail;
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

	public String getCountry1() {
		return country1;
	}

	public void setCountry1(String country1) {
		this.country1 = country1;
	}

	public String getState1() {
		return state1;
	}

	public void setState1(String state1) {
		this.state1 = state1;
	}

	public String getDistrict1() {
		return district1;
	}

	public void setDistrict1(String district1) {
		this.district1 = district1;
	}

	public String getCity1() {
		return city1;
	}

	public void setCity1(String city1) {
		this.city1 = city1;
	}

	public String getPincode1() {
		return pincode1;
	}

	public void setPincode1(String pincode1) {
		this.pincode1 = pincode1;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
