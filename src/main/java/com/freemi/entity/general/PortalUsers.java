package com.freemi.entity.general;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="portalusers")
public class PortalUsers {

	@Id
	private String id;
	private String email;
	private String fname;
	private String lname;
	private String mobile;
	private String uid;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	
/*	public PortalUsers(String email, String fname, String lname, String mobile, String uid) {
		super();
		this.email = email;
		this.fname = fname;
		this.lname = lname;
		this.mobile = mobile;
		this.uid = uid;
	}*/
	
	@Override
	public String toString() {
		return "PortalUsers [id=" + id + ", email=" + email + ", fname=" + fname + ", lname=" + lname + ", mobile="
				+ mobile + ", uid=" + uid + "]";
	}
	
	
}
