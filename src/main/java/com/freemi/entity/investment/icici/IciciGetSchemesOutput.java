package com.freemi.entity.investment.icici;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IciciGetSchemesOutput implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@JsonProperty("SCH_NAME")
	private String sch_name;

	@JsonProperty("SCH_TYPE")
	private String sch_type;

	@JsonProperty("SCH_CODE")
	private String sch_code;

	@JsonProperty("STATUS")
	private String status;

	@JsonProperty("NIPO_AMT")
	private float nipo_amt;

	@JsonProperty("T_SCHEME")
	private String t_scheme;

	@JsonProperty("NIPO_ADDN_AMT")
	private float nipo_addn_amt;

	@JsonProperty("CUT_OFF")
	private String cut_off;

	@JsonProperty("MUST_REINVEST")
	private String must_reinvest;

	@JsonProperty("ALLOW_AP")
	private String allow_ap;

	@JsonProperty("ALLOW_FP")
	private String allow_fp;

	@JsonProperty("PUR_ALLOWED")
	private String pur_allowed;

	@JsonProperty("PUR_FROM_DAY")
	private float pur_from_day;

	@JsonProperty("PUR_TO_DAY")
	private float pur_to_day;

	@JsonProperty("FROM_STP_ALLOWED")
	private String from_stp_allowed;

	@JsonProperty("FROM_DAILY_STP_ALLOWED")
	private String from_daily_stp_allowed;

	@JsonProperty("MIN_STP_AMT")
	private float min_stp_amt;

	@JsonProperty("MIN_DAILY_STP_AMT")
	private float min_daily_stp_amt;

	@JsonProperty("NO_OF_STP_INST")
	private float no_of_stp_inst;

	@JsonProperty("IPO")
	private String ipo;

	@JsonProperty("ADDPUR")
	private String addpur;

	@JsonProperty("MICR_DATE")
	private String micr_date = null;

	public String getSch_name() {
		return sch_name;
	}

	public void setSch_name(String sch_name) {
		this.sch_name = sch_name;
	}

	public String getSch_type() {
		return sch_type;
	}

	public void setSch_type(String sch_type) {
		this.sch_type = sch_type;
	}

	public String getSch_code() {
		return sch_code;
	}

	public void setSch_code(String sch_code) {
		this.sch_code = sch_code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public float getNipo_amt() {
		return nipo_amt;
	}

	public void setNipo_amt(float nipo_amt) {
		this.nipo_amt = nipo_amt;
	}

	public String getT_scheme() {
		return t_scheme;
	}

	public void setT_scheme(String t_scheme) {
		this.t_scheme = t_scheme;
	}

	public float getNipo_addn_amt() {
		return nipo_addn_amt;
	}

	public void setNipo_addn_amt(float nipo_addn_amt) {
		this.nipo_addn_amt = nipo_addn_amt;
	}

	public String getCut_off() {
		return cut_off;
	}

	public void setCut_off(String cut_off) {
		this.cut_off = cut_off;
	}

	public String getMust_reinvest() {
		return must_reinvest;
	}

	public void setMust_reinvest(String must_reinvest) {
		this.must_reinvest = must_reinvest;
	}

	public String getAllow_ap() {
		return allow_ap;
	}

	public void setAllow_ap(String allow_ap) {
		this.allow_ap = allow_ap;
	}

	public String getAllow_fp() {
		return allow_fp;
	}

	public void setAllow_fp(String allow_fp) {
		this.allow_fp = allow_fp;
	}

	public String getPur_allowed() {
		return pur_allowed;
	}

	public void setPur_allowed(String pur_allowed) {
		this.pur_allowed = pur_allowed;
	}

	public float getPur_from_day() {
		return pur_from_day;
	}

	public void setPur_from_day(float pur_from_day) {
		this.pur_from_day = pur_from_day;
	}

	public float getPur_to_day() {
		return pur_to_day;
	}

	public void setPur_to_day(float pur_to_day) {
		this.pur_to_day = pur_to_day;
	}

	public String getFrom_stp_allowed() {
		return from_stp_allowed;
	}

	public void setFrom_stp_allowed(String from_stp_allowed) {
		this.from_stp_allowed = from_stp_allowed;
	}

	public String getFrom_daily_stp_allowed() {
		return from_daily_stp_allowed;
	}

	public void setFrom_daily_stp_allowed(String from_daily_stp_allowed) {
		this.from_daily_stp_allowed = from_daily_stp_allowed;
	}

	public float getMin_stp_amt() {
		return min_stp_amt;
	}

	public void setMin_stp_amt(float min_stp_amt) {
		this.min_stp_amt = min_stp_amt;
	}

	public float getMin_daily_stp_amt() {
		return min_daily_stp_amt;
	}

	public void setMin_daily_stp_amt(float min_daily_stp_amt) {
		this.min_daily_stp_amt = min_daily_stp_amt;
	}

	public float getNo_of_stp_inst() {
		return no_of_stp_inst;
	}

	public void setNo_of_stp_inst(float no_of_stp_inst) {
		this.no_of_stp_inst = no_of_stp_inst;
	}

	public String getIpo() {
		return ipo;
	}

	public void setIpo(String ipo) {
		this.ipo = ipo;
	}

	public String getAddpur() {
		return addpur;
	}

	public void setAddpur(String addpur) {
		this.addpur = addpur;
	}

	public String getMicr_date() {
		return micr_date;
	}

	public void setMicr_date(String micr_date) {
		this.micr_date = micr_date;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
