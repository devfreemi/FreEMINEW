package com.freemi.entity.general;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="login_report")
public class LoginReport {
	
	 public LoginReport(String ipAddress, String userId, Date timestamp, String status, String message) {
//		 this.serial=serial;
		 this.address=ipAddress;
		 this.user=userId;
		 this.timestamp=timestamp;
		 this.status=status;
		 this.message=message;
	}

	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
	private int serial;
	private String address;
	private String user;
	private Date timestamp;
	private String status;
	private String message;
	public int getSerial() {
		return serial;
	}
	public void setSerial(int serial) {
		this.serial = serial;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	

}
