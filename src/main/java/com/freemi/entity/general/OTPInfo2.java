package com.freemi.entity.general;

import java.io.Serializable;
import java.util.Map;


public class OTPInfo2 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer referencecode;
	
	private String emailid;
	
	private String otp;
	
	private String module;
	
	private String requestedfrommodule;
	
	private String subModule;
	
	private int result;
	
	private String action;  // success(S) / error(E) / generation(G)

	private String sessionId;
	
    private String serverTimestamp;
	
	private String messeageid;
	
	private String mobileverified="N";
	
	private String requestingsystemip;
	
	private String systemdetails;	
	
	private Map<String, Object> msgdata;

	public Integer getReferencecode() {
		return referencecode;
	}

	public void setReferencecode(Integer referencecode) {
		this.referencecode = referencecode;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
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

	public String getRequestedfrommodule() {
		return requestedfrommodule;
	}

	public void setRequestedfrommodule(String requestedfrommodule) {
		this.requestedfrommodule = requestedfrommodule;
	}

	public String getSubModule() {
		return subModule;
	}

	public void setSubModule(String subModule) {
		this.subModule = subModule;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getServerTimestamp() {
		return serverTimestamp;
	}

	public void setServerTimestamp(String serverTimestamp) {
		this.serverTimestamp = serverTimestamp;
	}

	public String getMesseageid() {
		return messeageid;
	}

	public void setMesseageid(String messeageid) {
		this.messeageid = messeageid;
	}

	public String getMobileverified() {
		return mobileverified;
	}

	public void setMobileverified(String mobileverified) {
		this.mobileverified = mobileverified;
	}

	public String getRequestingsystemip() {
		return requestingsystemip;
	}

	public void setRequestingsystemip(String requestingsystemip) {
		this.requestingsystemip = requestingsystemip;
	}

	public String getSystemdetails() {
		return systemdetails;
	}

	public void setSystemdetails(String systemdetails) {
		this.systemdetails = systemdetails;
	}

	public Map<String, Object> getMsgdata() {
		return msgdata;
	}

	public void setMsgdata(Map<String, Object> msgdata) {
		this.msgdata = msgdata;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
