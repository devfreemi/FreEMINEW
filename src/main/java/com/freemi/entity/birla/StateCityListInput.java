package com.freemi.entity.birla;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StateCityListInput implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("StateCityRequest")
	private StateCityRequest stateCityRequestObject;

	public StateCityRequest getStateCityRequestObject() {
		return stateCityRequestObject;
	}

	public void setStateCityRequestObject(StateCityRequest stateCityRequestObject) {
		this.stateCityRequestObject = stateCityRequestObject;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static class StateCityRequest {
		
		@JsonProperty("UserId")
		private String userId="";
		
		@JsonProperty("Password")
		private String password="";
		
		@JsonProperty("UDP")
		private String udp;
		
		@JsonProperty("ClientIpAddress")
		private String clientIpAddress="";
		
		@JsonProperty("IMEI")
		private String imei="";
		
		@JsonProperty("OS")
		private String os="";

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getUdp() {
			return udp;
		}

		public void setUdp(String udp) {
			this.udp = udp;
		}

		public String getClientIpAddress() {
			return clientIpAddress;
		}

		public void setClientIpAddress(String clientIpAddress) {
			this.clientIpAddress = clientIpAddress;
		}

		public String getImei() {
			return imei;
		}

		public void setImei(String imei) {
			this.imei = imei;
		}

		public String getOs() {
			return os;
		}

		public void setOs(String os) {
			this.os = os;
		}
		
		
	}


}
