package com.freemi.entity.database;

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
@Table(name="email_bounce_report")
@Proxy(lazy=false)
public class EmailBounceReport implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long serialno;
	
	@Column(name="NOTIFICATION_TYPE")
	private String notificationType;
	
	@Column(name="ERROR_CODE")
	private String errorCode;
	
	@Column(name="MAIL_TRIGGER_SOURCE_IP")
	private String mailTriggerSourceIp;
	
	@Column(name="SOURCE_MAIL_ID")
	private String sourceMailId;
	
	@Column(name="DESTINATION_MAIL_ID")
	private String bouncedMailId;
	
	@Column(name="TIMESTAMP")
	private Date timestamp;

	public Long getSerialno() {
		return serialno;
	}

	public void setSerialno(Long serialno) {
		this.serialno = serialno;
	}

	public String getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getMailTriggerSourceIp() {
		return mailTriggerSourceIp;
	}

	public void setMailTriggerSourceIp(String mailTriggerSourceIp) {
		this.mailTriggerSourceIp = mailTriggerSourceIp;
	}

	public String getSourceMailId() {
		return sourceMailId;
	}

	public void setSourceMailId(String sourceMailId) {
		this.sourceMailId = sourceMailId;
	}

	public String getBouncedMailId() {
		return bouncedMailId;
	}

	public void setBouncedMailId(String bouncedMailId) {
		this.bouncedMailId = bouncedMailId;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
