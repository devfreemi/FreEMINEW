package com.freemi.entity.birla;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
public class StateCityListOutput {
	@JsonProperty("ReturnCode")
	private int returnCode;

	@JsonProperty("ReturnMsg")
	private String returnMsg;

	@JsonProperty("UDP")
	private int udp;


	ArrayList<LsStateCityList> lstcitystate = new ArrayList<LsStateCityList>();


	public int getReturnCode() {
		return returnCode;
	}


	public void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}


	public String getReturnMsg() {
		return returnMsg;
	}


	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}


	public int getUdp() {
		return udp;
	}


	public void setUdp(int udp) {
		this.udp = udp;
	}


	



	/*public class Application {

		@JsonProperty("ReturnCode")
		private float returnCode;

		@JsonProperty("ReturnMsg")
		private String returnMsg;

		@JsonProperty("UDP")
		private float udp;

		ArrayList<Object> lstcitystate = new ArrayList<Object>();

		public float getReturnCode() {
			return returnCode;
		}

		public void setReturnCode(float returnCode) {
			this.returnCode = returnCode;
		}

		public String getReturnMsg() {
			return returnMsg;
		}

		public void setReturnMsg(String returnMsg) {
			this.returnMsg = returnMsg;
		}

		public float getUdp() {
			return udp;
		}

		public void setUdp(float udp) {
			this.udp = udp;
		}

		public ArrayList<Object> getLstcitystate() {
			return lstcitystate;
		}

		public void setLstcitystate(ArrayList<Object> lstcitystate) {
			this.lstcitystate = lstcitystate;
		}



	}*/

	public ArrayList<LsStateCityList> getLstcitystate() {
		return lstcitystate;
	}


	public void setLstcitystate(ArrayList<LsStateCityList> lstcitystate) {
		this.lstcitystate = lstcitystate;
	}



	public static class LsStateCityList{

		@JsonProperty("City")
		ArrayList<Object> city = new ArrayList<Object>();
		
		@JsonProperty("State")
		private String state;

		public ArrayList<Object> getCity() {
			return city;
		}

		public void setCity(ArrayList<Object> city) {
			this.city = city;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}
		
		


	}
}
