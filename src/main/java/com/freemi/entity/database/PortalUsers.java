package com.freemi.entity.database;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name="portalusers")
@Proxy(lazy=false)
public class PortalUsers implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="SL_NO")
	private int serial;
	
	@Column(name="MOBILE")
	private String mobile;
	
	@Column(name="FULLNAME")
	private String fullname;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="REQUESTING_IP")
	private String systemIp;
	
	@Column(name="SYSTEM_DETAILS")
	private String systemDetails;
	
	@Column(name="REGISTER_TIME")
	private Date registrationTime;

	public int getSerial() {
		return serial;
	}

	public void setSerial(int serial) {
		this.serial = serial;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSystemIp() {
		return systemIp;
	}

	public void setSystemIp(String systemIp) {
		this.systemIp = systemIp;
	}

	public String getSystemDetails() {
		return systemDetails;
	}

	public void setSystemDetails(String systemDetails) {
		this.systemDetails = systemDetails;
	}

	public Date getRegistrationTime() {
		return registrationTime;
	}

	public void setRegistrationTime(Date registrationTime) {
		this.registrationTime = registrationTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
