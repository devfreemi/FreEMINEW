package com.freemi.entity.general;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name="hdfc_zipcode")
@Proxy(lazy = false)
public class Hdfcpincode implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="CITYZIPID")
	private Integer zipcodeid;
	
	@Column(name="CITY")
	private Integer cityid;
	
	@Column(name="")
	private Integer zipcode;
	
	@Column(name="")
	private Integer stateid;

	public Integer getZipcodeid() {
		return zipcodeid;
	}

	public void setZipcodeid(Integer zipcodeid) {
		this.zipcodeid = zipcodeid;
	}

	public Integer getCityid() {
		return cityid;
	}

	public void setCityid(Integer cityid) {
		this.cityid = cityid;
	}

	public Integer getZipcode() {
		return zipcode;
	}

	public void setZipcode(Integer zipcode) {
		this.zipcode = zipcode;
	}

	public Integer getStateid() {
		return stateid;
	}

	public void setStateid(Integer stateid) {
		this.stateid = stateid;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	

}
