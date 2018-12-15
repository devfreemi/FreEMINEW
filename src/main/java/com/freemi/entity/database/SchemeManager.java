package com.freemi.entity.database;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Proxy;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the scheme_manager database table.
 * 
 */
@Entity
@Table(name="scheme_manager")
@NamedQuery(name="SchemeManager.findAll", query="SELECT s FROM SchemeManager s")
@Proxy(lazy=false)
public class SchemeManager implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="manager_id")
	private Long managerId;

	@Column(name="manager_name")
	private String managerName;
	
	@Column(name="manager_photo")
	private String managerPhoto;

	@Column(name="managing_fromdate")
	@Temporal(TemporalType.DATE)
	private Date managingFromdate;

	@Column(name="total_experience")
	private String totalExperience;

	//bi-directional many-to-one association to MapSchemeManager
	@OneToMany(mappedBy="schemeManager")
	private List<MapSchemeManager> mapSchemeManagers;

	public SchemeManager() {
	}

	public Long getManagerId() {
		return this.managerId;
	}

	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}

	public String getManagerName() {
		return this.managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getManagerPhoto() {
		return this.managerPhoto;
	}

	public void setManagerPhoto(String managerPhoto) {
		this.managerPhoto = managerPhoto;
	}

	public Date getManagingFromdate() {
		return this.managingFromdate;
	}

	public void setManagingFromdate(Date managingFromdate) {
		this.managingFromdate = managingFromdate;
	}

	public String getTotalExperience() {
		return this.totalExperience;
	}

	public void setTotal_Experience(String totalExperience) {
		this.totalExperience = totalExperience;
	}

	public List<MapSchemeManager> getMapSchemeManagers() {
		return this.mapSchemeManagers;
	}

	public void setMapSchemeManagers(List<MapSchemeManager> mapSchemeManagers) {
		this.mapSchemeManagers = mapSchemeManagers;
	}

	public MapSchemeManager addMapSchemeManager(MapSchemeManager mapSchemeManager) {
		getMapSchemeManagers().add(mapSchemeManager);
		mapSchemeManager.setSchemeManager(this);

		return mapSchemeManager;
	}

	public MapSchemeManager removeMapSchemeManager(MapSchemeManager mapSchemeManager) {
		getMapSchemeManagers().remove(mapSchemeManager);
		mapSchemeManager.setSchemeManager(null);

		return mapSchemeManager;
	}

}