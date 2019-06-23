package com.freemi.entity.database;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Proxy;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="advisor_support")
@Proxy(lazy=false)
public class BlogAdvisorForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String serialno;
	
	@NotEmpty(message="Mobile no mandatory") @Length(min=10, max=10, message = "Invalid mobile no")
	@Column(name="MOBILE")
	private String mobile;
	
	@NotEmpty (message="Email mandatory") @Length(max=128, message="Email too lengthy")
	@Column(name="EMAIL")
	private String email;
	
	@NotEmpty(message="Name mandaory") @Length(max=128, message="Name too lengthy")
	@Column(name="NAME")
	private String name;
	
	
	@Column(name="SYSTEM_IP")
	private String systemip;

	public String getSerialno() {
		return serialno;
	}

	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSystemip() {
		return systemip;
	}

	public void setSystemip(String systemip) {
		this.systemip = systemip;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}

