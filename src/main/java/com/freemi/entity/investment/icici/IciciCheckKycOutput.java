package com.freemi.entity.investment.icici;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IciciCheckKycOutput implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("STATUS")
	private String status;
	
	@JsonProperty("F_PAN_NAME")
	private String f_pan_name;
	
	@JsonProperty("F_PAN_STATUS")
	private String f_pan_status;
	
	@JsonProperty("S_PAN_NAME")
	private String s_pan_name;
	
	@JsonProperty("S_PAN_STATUS")
	private String s_pan_status;
	
	@JsonProperty("T_PAN_NAME")
	private String t_pan_name;
	
	@JsonProperty("T_PAN_STATUS")
	private String t_pan_status;
	
	@JsonProperty("FATCA_FLAG_1")
	private String fatca_flag_1;
	
	@JsonProperty("FATCA_FLAG_2")
	private String fatca_flag_2;
	
	@JsonProperty("FATCA_FLAG_3")
	private String fatca_flag_3;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getF_pan_name() {
		return f_pan_name;
	}

	public void setF_pan_name(String f_pan_name) {
		this.f_pan_name = f_pan_name;
	}

	public String getF_pan_status() {
		return f_pan_status;
	}

	public void setF_pan_status(String f_pan_status) {
		this.f_pan_status = f_pan_status;
	}

	public String getS_pan_name() {
		return s_pan_name;
	}

	public void setS_pan_name(String s_pan_name) {
		this.s_pan_name = s_pan_name;
	}

	public String getS_pan_status() {
		return s_pan_status;
	}

	public void setS_pan_status(String s_pan_status) {
		this.s_pan_status = s_pan_status;
	}

	public String getT_pan_name() {
		return t_pan_name;
	}

	public void setT_pan_name(String t_pan_name) {
		this.t_pan_name = t_pan_name;
	}

	public String getT_pan_status() {
		return t_pan_status;
	}

	public void setT_pan_status(String t_pan_status) {
		this.t_pan_status = t_pan_status;
	}

	public String getFatca_flag_1() {
		return fatca_flag_1;
	}

	public void setFatca_flag_1(String fatca_flag_1) {
		this.fatca_flag_1 = fatca_flag_1;
	}

	public String getFatca_flag_2() {
		return fatca_flag_2;
	}

	public void setFatca_flag_2(String fatca_flag_2) {
		this.fatca_flag_2 = fatca_flag_2;
	}

	public String getFatca_flag_3() {
		return fatca_flag_3;
	}

	public void setFatca_flag_3(String fatca_flag_3) {
		this.fatca_flag_3 = fatca_flag_3;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
