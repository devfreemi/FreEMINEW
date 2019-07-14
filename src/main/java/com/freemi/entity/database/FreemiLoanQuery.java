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
@Table(name="freemi_loan_query")
@Proxy(lazy=false)
public class FreemiLoanQuery implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="REQUEST_NO")
	private int requestNo;
	
	@Column(name="REQUEST_TIME")
	private Date requestTime;
	
	@Column(name="REQUEST_CATEGORY")
	private String requestCategory;
	
	@Column(name="USER_MOBILE")
	private String mobile;
	
	@Column(name="USER_EMAIL")
	private String email;
	
	
	@Column(name="USER_NAME")
	private String name;
	
	@Column(name="LOCATION")
	private String location;
	
	@Column(name="DND_AGREE")
	private String dndAgree="Y";
	
	@Column(name="REQUESTING_SYSTEM_IP")
	private String systemIp;
	
	@Column(name="REQUESTING_CLIENT")
	private String browserClient;
	
	@Column(name="IMEI")
	private String imei;
	
	@Column(name="AGENT_CODE")
	private String agentCode;
	
	
	public int getRequestNo() {
		return requestNo;
	}
	public void setRequestNo(int requestNo) {
		this.requestNo = requestNo;
	}
	public Date getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}
	public String getRequestCategory() {
		return requestCategory;
	}
	public void setRequestCategory(String requestCategory) {
		this.requestCategory = requestCategory;
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
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDndAgree() {
		return dndAgree;
	}
	public void setDndAgree(String dndAgree) {
		this.dndAgree = dndAgree;
	}
	public String getSystemIp() {
		return systemIp;
	}
	public void setSystemIp(String systemIp) {
		this.systemIp = systemIp;
	}
	public String getBrowserClient() {
		return browserClient;
	}
	public void setBrowserClient(String browserClient) {
		this.browserClient = browserClient;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getAgentCode() {
		return agentCode;
	}
	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
