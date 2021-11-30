package com.freemi.entity.general;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

public class Otpform implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "Key data is empty.") @NotEmpty(message="Key data is empty")
	private String key;
	
	private String key2;
	
	@NotNull(message = "Keytype is empty.") @NotEmpty(message="Keytype is empty")
	@Pattern(regexp = "M|E", message = "Invalid validation category provided")
	private String keytype;
	
	@NotNull(message = "Module data is empty.") @NotEmpty(message="Module data is empty")
	@Pattern(regexp = "MF", message = "Invalid module category provided")
	private String module;
	
	@NotNull(message = "Submodule data is empty.") @NotEmpty(message="Submodule data is empty")
	@Pattern(regexp = "R", message = "Invalid submodule category provided")
	private String submodule;
	
	private String otp;
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	public String getKey2() {
		return key2;
	}

	public void setKey2(String key2) {
		this.key2 = key2;
	}

	public String getKeytype() {
		return keytype;
	}

	public void setKeytype(String keytype) {
		this.keytype = keytype;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getSubmodule() {
		return submodule;
	}

	public void setSubmodule(String submodule) {
		this.submodule = submodule;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
