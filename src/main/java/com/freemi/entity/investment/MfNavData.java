package com.freemi.entity.investment;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name="daily_nav_sumanta")
@Proxy(lazy=false)
public class MfNavData  {

	/**
	 * 
	 */
//	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="nav_id")
	private long serial; 
	
	@Column(name="nav_date")
	private Date navdate;
	
	@Column(name="nav")
	private Double navvalue;
	
	@Column(name="isind_no")
	private String isin;

	public Date getNavdate() {
		return navdate;
	}

	public void setNavdate(Date navdate) {
		this.navdate = navdate;
	}

	public Double getNavvalue() {
		return navvalue;
	}

	public void setNavvalue(Double navvalue) {
		this.navvalue = navvalue;
	}

	
	/* public static long getSerialversionuid() { return serialVersionUID; } */
	 

	public long getSerial() {
		return serial;
	}

	public void setSerial(long serial) {
		this.serial = serial;
	}

	public String getIsin() {
		return isin;
	}

	public void setIsin(String isin) {
		this.isin = isin;
	}
	
	

}
