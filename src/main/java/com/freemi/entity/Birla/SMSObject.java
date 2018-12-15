package com.freemi.entity.Birla;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SMSObject {
	private String sender;
	private String route;
	private String country;
	
	@JsonProperty("sms")
	Sms smsObject;
	// Getter Methods 

	public String getSender() {
		return sender;
	}

	public String getRoute() {
		return route;
	}

	public String getCountry() {
		return country;
	}


	// Setter Methods 

	public void setSender( String sender ) {
		this.sender = sender;
	}

	public void setRoute( String route ) {
		this.route = route;
	}

	public void setCountry( String country ) {
		this.country = country;
	}

	public Sms getSmsObject() {
		return smsObject;
	}

	public void setSmsObject(Sms smsObject) {
		this.smsObject = smsObject;
	}

	public static class Sms {
		private String message;
		private String to;


		// Getter Methods 

		public String getMessage() {
			return message;
		}

		public String getTo() {
			return to;
		}

		// Setter Methods 

		public void setMessage( String message ) {
			this.message = message;
		}

		public void setTo( String to ) {
			this.to = to;
		}
	}

}
