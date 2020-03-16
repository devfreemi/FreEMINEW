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
@Table(name="mahindra_cusotmer_kyc_documents")
@Proxy(lazy=false)
public class MahindraKycDocuments implements Serializable {

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
	
	@Column(name = "IMAGE_UPLOAD_ID")
	private String imageuniqid;
	
	@Column(name = "IMAGE_SEQUENCE")
	private int imagesequence;
	
	@Column(name = "IMAGE_TYPE_CODE")
	private String imageType;
	
	@Column(name="IMAGE_SUB_TYPE")
	private String imagesubtype;
	
	@Column(name = "IMAGE_REFERENCE_NO")
	private String imagerefrenceno;
	
	@Column(name = "KYC_IMAGE_EXPIRY_DATE")
	private String imagexpirydate;
	
	@Column(name = "UPLOADED_DOCUMENT")
	private byte[] image;
	
	@Column(name = "IMAGE_EXTENSION")
	private String imageextension;
	
	@Column(name = "COUNTRY_CODE")
	private String countrycode="IN";
	
	@Column(name = "IMAGE_FOR_IDENT_NO")
	private String imageidennoForreigntaxdetails;
	
	@Column(name = "UPLOADED_TO_MAHINDRA")
	private String uploadedtomahindra="N";
	
	@Column(name = "CREATED_BY")
	private String createdby="WEB_SELF_TRANS";
	
	@Column(name="IS_ACTIVE")
	private String active="Y";
	

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

	public String getImageuniqid() {
		return imageuniqid;
	}

	public void setImageuniqid(String imageuniqid) {
		this.imageuniqid = imageuniqid;
	}

	public int getImagesequence() {
		return imagesequence;
	}

	public void setImagesequence(int imagesequence) {
		this.imagesequence = imagesequence;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	public String getImagesubtype() {
		return imagesubtype;
	}

	public void setImagesubtype(String imagesubtype) {
		this.imagesubtype = imagesubtype;
	}

	public String getImagerefrenceno() {
		return imagerefrenceno;
	}

	public void setImagerefrenceno(String imagerefrenceno) {
		this.imagerefrenceno = imagerefrenceno;
	}

	public String getImagexpirydate() {
		return imagexpirydate;
	}

	public void setImagexpirydate(String imagexpirydate) {
		this.imagexpirydate = imagexpirydate;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getImageextension() {
		return imageextension;
	}

	public void setImageextension(String imageextension) {
		this.imageextension = imageextension;
	}

	public String getCountrycode() {
		return countrycode;
	}

	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}

	public String getImageidennoForreigntaxdetails() {
		return imageidennoForreigntaxdetails;
	}

	public void setImageidennoForreigntaxdetails(String imageidennoForreigntaxdetails) {
		this.imageidennoForreigntaxdetails = imageidennoForreigntaxdetails;
	}

	public String getUploadedtomahindra() {
		return uploadedtomahindra;
	}

	public void setUploadedtomahindra(String uploadedtomahindra) {
		this.uploadedtomahindra = uploadedtomahindra;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
