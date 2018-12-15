package com.freemi.entity.database;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the nav_history database table.
 * 
 */
@Entity
@Table(name="nav_history")
@NamedQuery(name="NavHistory.findAll", query="SELECT n FROM NavHistory n")
public class NavHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="nav_id")
	private Long navId;

	@Column(name="broker_code")
	private String brokerCode;

	@Column(name="created_by")
	private String createdBy;

	@Column(name="created_on")
	private Timestamp createdOn;

	private Date date;

	private Double nav;

	@Column(name="scheme_code")
	private String schemeCode;

	@Column(name="updated_by")
	private String updatedBy;

	@Column(name="updated_on")
	private Timestamp updatedOn;

	//bi-directional many-to-one association to ProductSchemeDetail
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="product_id")
	private ProductSchemeDetail productSchemeDetail;

	public NavHistory() {
	}

	public Long getNavId() {
		return this.navId;
	}

	public void setNavId(Long navId) {
		this.navId = navId;
	}

	public String getBrokerCode() {
		return this.brokerCode;
	}

	public void setBrokerCode(String brokerCode) {
		this.brokerCode = brokerCode;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getNav() {
		return this.nav;
	}

	public void setNav(Double nav) {
		this.nav = nav;
	}

	public String getSchemeCode() {
		return this.schemeCode;
	}

	public void setSchemeCode(String schemeCode) {
		this.schemeCode = schemeCode;
	}

	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getUpdatedOn() {
		return this.updatedOn;
	}

	public void setUpdatedOn(Timestamp updatedOn) {
		this.updatedOn = updatedOn;
	}

	public ProductSchemeDetail getProductSchemeDetail() {
		return this.productSchemeDetail;
	}

	public void setProductSchemeDetail(ProductSchemeDetail productSchemeDetail) {
		this.productSchemeDetail = productSchemeDetail;
	}

}