package com.freemi.entity.investment;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="bsemf_aof_record")
public class BseAOFDocument implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="SL_NO")
	private Integer serial;
	
	@Column(name="CLIENT_ID")
	private String clientid;
	
	@Column(name="FILE_UPLOAD_NAME_FORMAT")
	private String fileuploadname;
	
	@Column(name="AOF_PDF_OBJECT")
	@Lob
	private byte[] aofpdf;
	
	@Column(name="AOF_TIFF_FORMAT")
	private String aoftiff;
	
	@Column(name="AOF_UPLOADED")
	private String aofuploaded="N";
	
	@Column(name="AOF_UPLOAD_RESPONSE_CODE")
	private String aofuploadresponsecode;
	
	@Column(name="AOF_UPLOAD_RESPONSE_MESSAGE")
	private String aofuploadresponsemsg;
	
	@Column(name="FILE_GENERATIOIN_STATUS")
	private String filegenerationstatus;
	
	public Integer getSerial() {
		return serial;
	}

	public void setSerial(Integer serial) {
		this.serial = serial;
	}

	public String getClientid() {
		return clientid;
	}

	public void setClientid(String clientid) {
		this.clientid = clientid;
	}

	public String getFileuploadname() {
		return fileuploadname;
	}

	public void setFileuploadname(String fileuploadname) {
		this.fileuploadname = fileuploadname;
	}

	public byte[] getAofpdf() {
		return aofpdf;
	}

	public void setAofpdf(byte[] aofpdf) {
		this.aofpdf = aofpdf;
	}

	public String getAoftiff() {
		return aoftiff;
	}

	public void setAoftiff(String aoftiff) {
		this.aoftiff = aoftiff;
	}

	public String getAofuploaded() {
		return aofuploaded;
	}

	public void setAofuploaded(String aofuploaded) {
		this.aofuploaded = aofuploaded;
	}

	public String getAofuploadresponsecode() {
		return aofuploadresponsecode;
	}

	public void setAofuploadresponsecode(String aofuploadresponsecode) {
		this.aofuploadresponsecode = aofuploadresponsecode;
	}

	public String getAofuploadresponsemsg() {
		return aofuploadresponsemsg;
	}

	public void setAofuploadresponsemsg(String aofuploadresponsemsg) {
		this.aofuploadresponsemsg = aofuploadresponsemsg;
	}
	
	public String getFilegenerationstatus() {
		return filegenerationstatus;
	}

	public void setFilegenerationstatus(String filegenerationstatus) {
		this.filegenerationstatus = filegenerationstatus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
