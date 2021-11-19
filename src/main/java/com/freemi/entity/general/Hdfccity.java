package com.freemi.entity.general;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@Proxy(lazy =false)
@Table(name = "hdfc_city")
public class Hdfccity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="LMC_CITYID_C")
	private Integer lmccityid;
	
	@Column(name="LMC_CITYNAME_C")
	private String lmccityname;
	
	@Column(name="LMC_STATE_N")
	private Integer lmcstate;

	public Integer getLmccityid() {
		return lmccityid;
	}

	public void setLmccityid(Integer lmccityid) {
		this.lmccityid = lmccityid;
	}

	public String getLmccityname() {
		return lmccityname;
	}

	public void setLmccityname(String lmccityname) {
		this.lmccityname = lmccityname;
	}

	public Integer getLmcstate() {
		return lmcstate;
	}

	public void setLmcstate(Integer lmcstate) {
		this.lmcstate = lmcstate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
