package com.freemi.entity.investment;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bsemf_transcounter")
public class BseDailyTransCounter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="SL_NO")
	private long serial;

	@Column(name="DATE")
	private Date date;
	
	@Column(name="LAST_COUNT")
	private long dayCounter;

	public long getSerial() {
		return serial;
	}

	public void setSerial(long serial) {
		this.serial = serial;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public long getDayCounter() {
		return dayCounter;
	}

	public void setDayCounter(long dayCounter) {
		this.dayCounter = dayCounter;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
