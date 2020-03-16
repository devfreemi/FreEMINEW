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
@Table(name="mahindra_fd_kyc_documents_upload_status")
@Proxy(lazy=false)
public class MahindraKycDocUploadStatus implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "SL_NO")
	private Integer serial;
	
	@Column(name = "MOBILE")
	private String mobile;
	
	@Column(name = "CUSTOMER_ID")
	private String customerid;
	
	@Column(name = "CP_TRANS_REF_ID")
	private String cptransrefid;
	
	@Column(name="MF_TRANS_REF_ID")
	private String mftransRefId;
	
	@Column(name = "APPL_NO")
	private String applicationno;
	
	@Column(name="HOLDING_TYPE")
	private String holdingtype="01";
	
	@Column(name = "IMAGE_UPLOAD_ID")
	private String imageid;
	
	@Column(name = "IMAGE_TYPE")
	private String imagetype;
	
	@Column(name = "IMAGE_SUBTYPE")
	private String imagesubtype;
	
	@Column(name="UPLOADED_TO_MAHINDRA_VIA_API")
	private String uploadstatus="N";
	
	@Column(name = "API_RESPONSE")
	private String apiresponse;
	
	@Column(name = "CREATED_BY")
	private String createdby="WEB_SELF_TRANS";

	public Integer getSerial() {
		return serial;
	}

	public void setSerial(Integer serial) {
		this.serial = serial;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCustomerid() {
		return customerid;
	}

	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}

	public String getCptransrefid() {
		return cptransrefid;
	}

	public void setCptransrefid(String cptransrefid) {
		this.cptransrefid = cptransrefid;
	}

	public String getMftransRefId() {
	    return mftransRefId;
	}

	public void setMftransRefId(String mftransRefId) {
	    this.mftransRefId = mftransRefId;
	}

	public String getApplicationno() {
	    return applicationno;
	}

	public void setApplicationno(String applicationno) {
	    this.applicationno = applicationno;
	}
	
	public String getHoldingtype() {
	    return holdingtype;
	}

	public void setHoldingtype(String holdingtype) {
	    this.holdingtype = holdingtype;
	}

	public String getImageid() {
		return imageid;
	}

	public void setImageid(String imageid) {
		this.imageid = imageid;
	}

	public String getUploadstatus() {
		return uploadstatus;
	}

	public void setUploadstatus(String uploadstatus) {
		this.uploadstatus = uploadstatus;
	}

	public String getApiresponse() {
		return apiresponse;
	}

	public void setApiresponse(String apiresponse) {
		this.apiresponse = apiresponse;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public String getImagetype() {
	    return imagetype;
	}

	public void setImagetype(String imagetype) {
	    this.imagetype = imagetype;
	}

	public String getImagesubtype() {
	    return imagesubtype;
	}

	public void setImagesubtype(String imagesubtype) {
	    this.imagesubtype = imagesubtype;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
