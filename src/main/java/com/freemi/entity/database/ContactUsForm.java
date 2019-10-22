package com.freemi.entity.database;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="contact_us_request")
public class ContactUsForm implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="serial_id")
	private int requestnumber;
	@Column(name="system_ip")
	private String ipaddress;
	@Column(name="contact_id")
	private String contactid;
	@Column(name="email_id")
	private String email;
	@Column(name="request_topic")
	private String requesttopic;
	@Column(name="feedback")
	private String feedback;
	public int getRequestnumber() {
		return requestnumber;
	}
	public void setRequestnumber(int requestnumber) {
		this.requestnumber = requestnumber;
	}
	
	public String getIpaddress() {
		return ipaddress;
	}
	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}
	public String getContactid() {
		return contactid;
	}
	public void setContactid(String contactid) {
		this.contactid = contactid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRequesttopic() {
		return requesttopic;
	}
	public void setRequesttopic(String requesttopic) {
		this.requesttopic = requesttopic;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
