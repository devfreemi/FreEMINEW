package com.freemi.entity.general;

public class ClientSystemDetails {
	
	private String clientIpv4Address="";
	private String clientOS="";
	private String clientBrowser="";
	private String imei="";
	
	public String getClientIpv4Address() {
		return clientIpv4Address;
	}
	public void setClientIpv4Address(String clientIpv4Address) {
		this.clientIpv4Address = clientIpv4Address;
	}
	public String getClientOS() {
		return clientOS;
	}
	public void setClientOS(String clientOS) {
		this.clientOS = clientOS;
	}
	public String getClientBrowser() {
		return clientBrowser;
	}
	public void setClientBrowser(String clientBrowser) {
		this.clientBrowser = clientBrowser;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	
	
}
