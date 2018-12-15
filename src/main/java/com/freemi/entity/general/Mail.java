package com.freemi.entity.general;

import java.io.Serializable;
import java.util.Map;

import org.springframework.stereotype.Component;


@Component
//@PropertySource("classpath:app-config.properties")
public class Mail implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
/*	@Autowired
	private Environment env;*/
	
	private String from;
	private String to;
	private String cc;
	private String bcc;
	private String subject;
	private String content;
	private Map<String, Object> model;
	
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
//		this.from= env.getProperty("mail.admin");
//		this.from="admin@yfreemi.com";
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getCc() {
		return cc;
	}
	public void setCc(String cc) {
		this.cc = cc;
	}
	public String getBcc() {
		return bcc;
	}
	public void setBcc(String bcc) {
		this.bcc = bcc;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Map<String, Object> getModel() {
		return model;
	}
	public void setModel(Map<String, Object> model) {
		this.model = model;
	}
	
	
}
