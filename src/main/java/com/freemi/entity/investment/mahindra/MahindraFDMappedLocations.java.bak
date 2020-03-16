package com.freemi.entity.investment.mahindra;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name="mahindra_fd_kyc_location")
@Proxy(lazy=false)
public class MahindraFDMappedLocations implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "SL_NO")
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer serial;
	
	@Column(name = "FD_CKYC_Pin_Code")
	private Integer pincode;
	
	@Column(name = "FD_CKYC_District_Desc")
	private String district;
	
	@Column(name = "FD_CKYC_State_Code")
	private String statecode;

	public Integer getSerial() {
		return serial;
	}

	public void setSerial(Integer serial) {
		this.serial = serial;
	}

	public Integer getPincode() {
		return pincode;
	}

	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getStatecode() {
		return statecode;
	}

	public void setStatecode(String statecode) {
		this.statecode = statecode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
