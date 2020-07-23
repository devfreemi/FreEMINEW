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
	@Column(name = "SL_NO")
	private Long serialno;
	
	@Column(name="BOUNCE_TYPE")
	private String bouncetype;
	
	@Column(name="BOUNCE_SUB_TYPE")
	private String bouncesubtype;
	
	@Column(name="ERROR_CODE")
	private String errorcode;
	
	@Column(name="MAIL_TRIGGER_SOURCE_IP")
	private String mailtriggersourceip;
	
	@Column(name="BOUNCE_FROM_MAIL_ID")
	private String sourcemailid;
	
	@Column(name="DESTINATION_MAIL_ID")
	private String bouncedmailid;
	
	@Column(name = "AWS_ACCOUNT_ID")
	private String awsaccountid;
	
	@Column(name="TIMESTAMP")
	private Date timestamp;

	public Long getSerialno() {
		return serialno;
	}

	public void setSerialno(Long serialno) {
		this.serialno = serialno;
	}

	public String getBouncetype() {
		return bouncetype;
	}

	public void setBouncetype(String bouncetype) {
		this.bouncetype = bouncetype;
	}

	public String getBouncesubtype() {
		return bouncesubtype;
	}

	public void setBouncesubtype(String bouncesubtype) {
		this.bouncesubtype = bouncesubtype;
	}

	public String getErrorcode() {
		return errorcode;
	}

	public void setErrorcode(String errorcode) {
		this.errorcode = errorcode;
	}

	public String getMailtriggersourceip() {
		return mailtriggersourceip;
	}

	public void setMailtriggersourceip(String mailtriggersourceip) {
		this.mailtriggersourceip = mailtriggersourceip;
	}

	public String getSourcemailid() {
		return sourcemailid;
	}

	public void setSourcemailid(String sourcemailid) {
		this.sourcemailid = sourcemailid;
	}

	public String getBouncedmailid() {
		return bouncedmailid;
	}

	public void setBouncedmailid(String bouncedmailid) {
		this.bouncedmailid = bouncedmailid;
	}

	public String getAwsaccountid() {
		return awsaccountid;
	}

	public void setAwsaccountid(String awsaccountid) {
		this.awsaccountid = awsaccountid;
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
