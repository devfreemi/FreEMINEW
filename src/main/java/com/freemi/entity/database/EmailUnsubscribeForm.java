package com.freemi.entity.database;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="email_unsubscribed_list")
public class EmailUnsubscribeForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="SL_NO")
	private String serialno;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="MAILER_CATEGORY")
	private String mailerCategory="";
	
	@Column(name="REASON_CODE")
	private String reasonCode;
	
	@Column(name="REASON_COMMENT")
	private String comment="";
	
	@Column(name="USERSYSTEMIP")
	private String usersystemip;
	
	@Column(name="SYSTEM_INFO")
	private String systeminfo;
	
	@Column(name="UNSUBSCRIBE_TIME")
	private Date timeStamp;

	public String getSerialno() {
		return serialno;
	}

	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getUsersystemip() {
		return usersystemip;
	}

	public void setUsersystemip(String usersystemip) {
		this.usersystemip = usersystemip;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getSysteminfo() {
		return systeminfo;
	}

	public void setSysteminfo(String systeminfo) {
		this.systeminfo = systeminfo;
	}

	public String getMailerCategory() {
		return mailerCategory;
	}

	public void setMailerCategory(String mailerCategory) {
		this.mailerCategory = mailerCategory;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
