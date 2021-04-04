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
@Table(name="otp_validation")
public class Mobileotpverifier implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long id;
	
	@Column(name = "mobile_number")
	private String mobile;
	
	private String otp;
	
	private String module;

	@Column(name = "sub_module")
	private String submodule;
	
	private String action;  // success(S) / error(E) / generation(G)

	@Column(name = "session_id")
	private String sessionid;
	
	@Column(name="otpverified")
	private String otpverified="N";
	
	@Column(name="created_on")
    private Date servertimestamp;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getSubmodule() {
		return submodule;
	}

	public void setSubmodule(String submodule) {
		this.submodule = submodule;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getSessionid() {
		return sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}

	public String getOtpverified() {
		return otpverified;
	}

	public void setOtpverified(String otpverified) {
		this.otpverified = otpverified;
	}

	public Date getServertimestamp() {
		return servertimestamp;
	}

	public void setServertimestamp(Date servertimestamp) {
		this.servertimestamp = servertimestamp;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Mobileotpverifier() {}
	public Mobileotpverifier(String mobile, String otp, String action, String module, 
			String subModule, Date serverTS, String sessionId) {
		this.mobile = mobile;
		this.action = action;
		this.otp = otp;
		this.module = module;
		this.submodule = subModule;
		this.servertimestamp = serverTS;
		this.sessionid = sessionId;
	}
	

}
