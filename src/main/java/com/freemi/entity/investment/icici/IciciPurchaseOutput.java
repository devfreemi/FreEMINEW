package com.freemi.entity.investment.icici;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IciciPurchaseOutput implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("TRANID")
	private String tranid;

	@JsonProperty("TRXN_DATE")
	private String trxn_date;

	@JsonProperty("TRXN_TIME")
	private String trxn_time;

	public String getTranid() {
		return tranid;
	}

	public void setTranid(String tranid) {
		this.tranid = tranid;
	}

	public String getTrxn_date() {
		return trxn_date;
	}

	public void setTrxn_date(String trxn_date) {
		this.trxn_date = trxn_date;
	}

	public String getTrxn_time() {
		return trxn_time;
	}

	public void setTrxn_time(String trxn_time) {
		this.trxn_time = trxn_time;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
