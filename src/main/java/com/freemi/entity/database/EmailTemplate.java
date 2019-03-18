package com.freemi.entity.database;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name="email_templates")
@Proxy(lazy=false)
public class EmailTemplate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="email_id")
	private int emailId;
	
	@Column(name="email_template_name")
	private String emailTemplateName;
	
	@Column(name="email_subject")
	private String emailSubject;
	
	@Column(name="email_replacers")
	private String emailRepalcers;
	
	@Column(name="email_content")
	private String emailBody;
	
	@Column(name="email_enabled")	
	private boolean emailTemplateEnabled;


	public void setEmailId(int emailId) {
		this.emailId = emailId;
	}

	public String getEmailTemplateName() {
		return emailTemplateName;
	}

	public void setEmailTemplateName(String emailTemplateName) {
		this.emailTemplateName = emailTemplateName;
	}

	public String getEmailSubject() {
		return emailSubject;
	}

	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}

	public String getEmailRepalcers() {
		return emailRepalcers;
	}

	public void setEmailRepalcers(String emailRepalcers) {
		this.emailRepalcers = emailRepalcers;
	}

	public String getEmailBody() {
		return emailBody;
	}

	public void setEmailBody(String emailBody) {
		this.emailBody = emailBody;
	}

	public boolean isEmailTemplateEnabled() {
		return emailTemplateEnabled;
	}

	public void setEmailTemplateEnabled(boolean emailTemplateEnabled) {
		this.emailTemplateEnabled = emailTemplateEnabled;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

}
