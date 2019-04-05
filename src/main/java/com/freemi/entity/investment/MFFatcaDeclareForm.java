package com.freemi.entity.investment;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Proxy;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="bsemf_customers_fatca_declaration")
@Proxy(lazy=false)
public class MFFatcaDeclareForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="SL_NO")
	private long serialNo;
	
	@Column(name="CLIENT_ID")
	private String clientID;
	
	@Transient
	private boolean usCitizenshipCheck;
	
	@Column(name="US_CITIZEN")
	private String uscanadaCitizen="N";
	
	@Column(name="TAX_STATUS")
	private String taxStatus="";
	
	@Column(name="FATHER_NAME")
	private String fatherName="";
	
	@Column(name="SPOUSE_NAME")
	private String spouseName="";
	
	@Column(name="DATA_SOURCE")
	private String dataSource="E";
	
	@Column(name="ADDRESS_TYPE")
	private String addressType="2";
	
	@Column(name="PLACE_OF_BIRTH")
	@NotBlank(message="Please mention your place of birth")
	private String placeOfBirth;
	
	@Column(name="COUNTRY_OF_BIRTH")
	private String countryOfBirth;
	
	@Column(name="RESIDENCE_COUNRTY_1")
	private String residenceCountry1;
	
	@Column(name="IDENTIIFCATION_DOC_TYPE_1")
	private String identificationDocType;
	
	@Column(name="WEALTH_SOURCE")
	@NotBlank(message="Please declare your wealth source as part of FATCA")
	private String wealthSource;
	
	@Column(name="CORPORATE_SERVICE_SECTOR")
	private String corporateServiceSector="";
	
	@Column(name="INCOME_SLAB")
	@NotBlank(message="Income slab declatration required for FATCA")
	private String incomeSlab;
	
	@Column(name="NET_WORTH")
	private Double netWordth;
	
	@Column(name="DATE_OF_NETWORTH")
	private Date dateOfNetworth;
	
	@Column(name="POLITICALLY_EXPOSED_PERSON")
	@NotBlank(message="Please declare your political view as required for FATCA")
	private String politicalExposedPerson="";
	
	@Column(name="OCCUPATION_CODE")
	private String occupationCode;
	
	@Column(name="OCCUPATION_TYPE")
	private String occupationType;
	
	@Column(name="EXCHANGE_NAME")
	private String exchangeName="B";
	
	@Column(name="EXEMPTION_CODE")
	private String exemptionCode="";
	
	@Column(name="UBO_APPLICABLE")
	private String uboApplicable="N";
	
	@Column(name="NEW_UPDATE_INDICATOR")
	private String newUpdateIndicator="N";
	
	@Column(name="LOG_NAME")
	private String logName;
	
	@Column(name="FATCA_ADDITIONAL_DOCUMENT_UPLOAD")
	private String fatcaAdditionalDocUpdate;
	
	@Column(name="FATCA_DECLARE_UPLOAD_COMPLETE")
	private boolean fatcaUploaded=false;
	
	@Column(name="DECLARATION_DATE")
	private Date daclarationDate;
	
	@Column(name="SYSTEM_IP")
	private String systemip="";
	
	@Column(name="SYSTEM_TYPE")
	private String systemDetails="";
	
	@Column(name="UPDATED_ON")
	private Date updateDate;
	
	@Column(name="CREATED_BY")
	private String createdBy="";
	
	@Column(name="UPDATED_BY")
	private String updatedBy;
	
	
	@OneToOne(fetch= FetchType.LAZY, optional=false, cascade=CascadeType.PERSIST)
	@JoinColumn(name="CLIENT_ID", nullable= false,insertable=false,updatable=false)
	private BseMFInvestForm mfForm;
	
	public long getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(long serialNo) {
		this.serialNo = serialNo;
	}
	public String getClientID() {
		return clientID;
	}
	public void setClientID(String clientID) {
		this.clientID = clientID;
	}
	public BseMFInvestForm getMfForm() {
		return mfForm;
	}
	public void setMfForm(BseMFInvestForm mfForm) {
		this.mfForm = mfForm;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getTaxStatus() {
		return taxStatus;
	}
	public void setTaxStatus(String taxStatus) {
		this.taxStatus = taxStatus;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getSpouseName() {
		return spouseName;
	}
	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}
	public String getDataSource() {
		return dataSource;
	}
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}
	public String getAddressType() {
		return addressType;
	}
	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}
	public String getPlaceOfBirth() {
		return placeOfBirth;
	}
	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}
	public String getCountryOfBirth() {
		return countryOfBirth;
	}
	public void setCountryOfBirth(String countryOfBirth) {
		this.countryOfBirth = countryOfBirth;
	}
	public String getResidenceCountry1() {
		return residenceCountry1;
	}
	public void setResidenceCountry1(String residenceCountry1) {
		this.residenceCountry1 = residenceCountry1;
	}
	public String getIdentificationDocType() {
		return identificationDocType;
	}
	public void setIdentificationDocType(String identificationDocType) {
		this.identificationDocType = identificationDocType;
	}
	public String getWealthSource() {
		return wealthSource;
	}
	public void setWealthSource(String wealthSource) {
		this.wealthSource = wealthSource;
	}
	public String getCorporateServiceSector() {
		return corporateServiceSector;
	}
	public void setCorporateServiceSector(String corporateServiceSector) {
		this.corporateServiceSector = corporateServiceSector;
	}
	public String getIncomeSlab() {
		return incomeSlab;
	}
	public void setIncomeSlab(String incomeSlab) {
		this.incomeSlab = incomeSlab;
	}
	public Double getNetWordth() {
		return netWordth;
	}
	public void setNetWordth(Double netWordth) {
		this.netWordth = netWordth;
	}
	public Date getDateOfNetworth() {
		return dateOfNetworth;
	}
	public void setDateOfNetworth(Date dateOfNetworth) {
		this.dateOfNetworth = dateOfNetworth;
	}
	public String getPoliticalExposedPerson() {
		return politicalExposedPerson;
	}
	public void setPoliticalExposedPerson(String politicalExposedPerson) {
		this.politicalExposedPerson = politicalExposedPerson;
	}
	public String getOccupationCode() {
		return occupationCode;
	}
	public void setOccupationCode(String occupationCode) {
		this.occupationCode = occupationCode;
	}
	public String getExemptionCode() {
		return exemptionCode;
	}
	public void setExemptionCode(String exemptionCode) {
		this.exemptionCode = exemptionCode;
	}
	public String getUboApplicable() {
		return uboApplicable;
	}
	public void setUboApplicable(String uboApplicable) {
		this.uboApplicable = uboApplicable;
	}
	public String getNewUpdateIndicator() {
		return newUpdateIndicator;
	}
	public void setNewUpdateIndicator(String newUpdateIndicator) {
		this.newUpdateIndicator = newUpdateIndicator;
	}
	public String getLogName() {
		return logName;
	}
	public void setLogName(String logName) {
		this.logName = logName;
	}
	public String getFatcaAdditionalDocUpdate() {
		return fatcaAdditionalDocUpdate;
	}
	public void setFatcaAdditionalDocUpdate(String fatcaAdditionalDocUpdate) {
		this.fatcaAdditionalDocUpdate = fatcaAdditionalDocUpdate;
	}
	public boolean isFatcaUploaded() {
		return fatcaUploaded;
	}
	public void setFatcaUploaded(boolean fatcaUploaded) {
		this.fatcaUploaded = fatcaUploaded;
	}
	public Date getDaclarationDate() {
		return daclarationDate;
	}
	public void setDaclarationDate(Date daclarationDate) {
		this.daclarationDate = daclarationDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getExchangeName() {
		return exchangeName;
	}
	public void setExchangeName(String exchangeName) {
		this.exchangeName = exchangeName;
	}
	public String getOccupationType() {
		return occupationType;
	}
	public void setOccupationType(String occupationType) {
		this.occupationType = occupationType;
	}
	public String getSystemip() {
		return systemip;
	}
	public void setSystemip(String systemip) {
		this.systemip = systemip;
	}
	public String getSystemDetails() {
		return systemDetails;
	}
	public void setSystemDetails(String systemDetails) {
		this.systemDetails = systemDetails;
	}
	public boolean isUsCitizenshipCheck() {
		return usCitizenshipCheck;
	}
	public void setUsCitizenshipCheck(boolean usCitizenshipCheck) {
		this.usCitizenshipCheck = usCitizenshipCheck;
	}
	public String getUscanadaCitizen() {
		return uscanadaCitizen;
	}
	public void setUscanadaCitizen(String uscanadaCitizen) {
		this.uscanadaCitizen = uscanadaCitizen;
	}
		
}
