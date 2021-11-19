package com.freemi.entity.general;

import java.io.Serializable;

public class Select2Results implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
//	private Integer id;
	
	/*
	 * Select2Results(Object id,Object text){ this.id =id; this.text=text; }
	 */
	
	private Object id;
	private Object text;
	
	
	
	
	public Object getId() {
		return id;
	}
	public void setId(Object id) {
		this.id = id;
	}
	public Object getText() {
		return text;
	}
	public void setText(Object text) {
		this.text = text;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
