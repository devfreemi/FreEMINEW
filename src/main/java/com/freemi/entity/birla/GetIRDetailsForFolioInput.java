package com.freemi.entity.birla;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetIRDetailsForFolioInput implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("FolioSchemeRequest")
	private FolioSchemeRequest folioSchemeRequestObject;




	public FolioSchemeRequest getFolioSchemeRequestObject() {
		return folioSchemeRequestObject;
	}




	public void setFolioSchemeRequestObject(FolioSchemeRequest folioSchemeRequestObject) {
		this.folioSchemeRequestObject = folioSchemeRequestObject;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}




	public static class FolioSchemeRequest {

		@JsonProperty("FolioNo")
		private String folioNo;

		@JsonProperty("UserId")
		private String userId;

		@JsonProperty("Password")
		private String password;

		@JsonProperty("UDP")
		private String udp;

		@JsonProperty("UDP1")
		private String udp1;

		@JsonProperty("UDP2")
		private String udp2;

		@JsonProperty("UDP3")
		private String udp3;

		@JsonProperty("UDP4")
		private String udp4;

		@JsonProperty("UDP5")
		private String udp5;

		@JsonProperty("OS")
		private String os;

		@JsonProperty("IMEI")
		private String imei;

		@JsonProperty("ClientIpAddress")
		private String clientIpAddress;

		public String getFolioNo() {
			return folioNo;
		}

		public void setFolioNo(String folioNo) {
			this.folioNo = folioNo;
		}

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

		public String getUdp1() {
			return udp1;
		}

		public void setUdp1(String udp1) {
			this.udp1 = udp1;
		}

		public String getUdp2() {
			return udp2;
		}

		public void setUdp2(String udp2) {
			this.udp2 = udp2;
		}

		public String getUdp3() {
			return udp3;
		}

		public void setUdp3(String udp3) {
			this.udp3 = udp3;
		}

		public String getUdp4() {
			return udp4;
		}

		public void setUdp4(String udp4) {
			this.udp4 = udp4;
		}

		public String getUdp5() {
			return udp5;
		}

		public void setUdp5(String udp5) {
			this.udp5 = udp5;
		}

		public String getOs() {
			return os;
		}

		public void setOs(String os) {
			this.os = os;
		}

		public String getImei() {
			return imei;
		}

		public void setImei(String imei) {
			this.imei = imei;
		}

		public String getClientIpAddress() {
			return clientIpAddress;
		}

		public void setClientIpAddress(String clientIpAddress) {
			this.clientIpAddress = clientIpAddress;
		}



	}
}
