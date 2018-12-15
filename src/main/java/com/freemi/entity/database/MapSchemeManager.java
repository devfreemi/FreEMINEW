package com.freemi.entity.database;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Proxy;



/**
 * The persistent class for the map_scheme_manager database table.
 * 
 */
@Entity
@Table(name="map_scheme_manager")
@NamedQuery(name="MapSchemeManager.findAll", query="SELECT m FROM MapSchemeManager m")
@Proxy(lazy=false)
public class MapSchemeManager implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="map_scheme_manager")
	private Long mapSchemeManager;

	//bi-directional many-to-one association to ProductSchemeDetail
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="product_scheme_id")
	private ProductSchemeDetail productSchemeDetail;

	//bi-directional many-to-one association to SchemeManager
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="manager_id")
	private SchemeManager schemeManager;

	public MapSchemeManager() {
	}

	public Long getMapSchemeManager() {
		return this.mapSchemeManager;
	}

	public void setMapSchemeManager(Long mapSchemeManager) {
		this.mapSchemeManager = mapSchemeManager;
	}

	public ProductSchemeDetail getProductSchemeDetail() {
		return this.productSchemeDetail;
	}

	public void setProductSchemeDetail(ProductSchemeDetail productSchemeDetail) {
		this.productSchemeDetail = productSchemeDetail;
	}

	public SchemeManager getSchemeManager() {
		return this.schemeManager;
	}

	public void setSchemeManager(SchemeManager schemeManager) {
		this.schemeManager = schemeManager;
	}

}