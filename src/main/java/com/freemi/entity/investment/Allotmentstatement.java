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
@Table(name="bsemf_allotment_statement")
public class Allotmentstatement implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="serial")
    private Integer serial;
    
    @Column(name="amountcheck")
    private Double amountcheck;
    
    @Column(name="allottednav")
    private Double allottednav;
    
    @Column(name="foliono")
    private String foliono;
    
    @Column(name="schemecode")
    private String schemecode;
    
    @Column(name="amount")
    private Double amount;
    
    @Column(name="clientcode")
    private String clientcode;
    
    @Column(name="allottedunit")
    private Double allottedunit;
    
    @Column(name="beneficiaryid")
    private String beneficiaryid;
    
    @Column(name="dpcflag")
    private String dpcflag;
    
    @Column(name="ordertype")
    private String ordertype;
    
    @Column(name="dptrans")
    private String dptrans;
    
    @Column(name="membercode")
    private String membercode;
    
    @Column(name="settno")
    private String settno;
    
    @Column(name="setttype")
    private String setttype;
    
    @Column(name="euin")
    private String euin;
    
    @Column(name="subordertype")
    private String subordertype;
    
    @Column(name="euindecl")
    private String euindecl;
    
    @Column(name="internalrefno")
    private String internalrefno;
    
    @Column(name="kycflag")
    private String kycflag;
    
    @Column(name="remarks")
    private String remarks;
    
    @Column(name="ordertype2")
    private String ordertype2;
    
    @Column(name="sipregnno")
    private String sipregnno;
    
    @Column(name="sipregndate")
    private Date sipregndate;
    
    @Column(name="subbrcode")
    private String subbrcode;
    
    @Column(name="orderdate")
    private Date orderdate;
    
    @Column(name="rtaschemecode")
    private String rtaschemecode;
    
    @Column(name="validflag")
    private String validflag;
    
    @Column(name="rtatransno")
    private String rtatransno;
    
    @Column(name="beneficiaryid2")
    private String beneficiary2;
    
    @Column(name="reportdate")
    private Date reportdate;
    
    @Column(name="isin")
    private String isin;
    
    @Column(name="qty")
    private String qty;
    
    
    public Integer getSerial() {
        return serial;
    }
    public void setSerial(Integer serial) {
        this.serial = serial;
    }
    public Double getAmountcheck() {
        return amountcheck;
    }
    public void setAmountcheck(Double amountcheck) {
        this.amountcheck = amountcheck;
    }
    public Double getAllottednav() {
        return allottednav;
    }
    public void setAllottednav(Double allottednav) {
        this.allottednav = allottednav;
    }
    public String getFoliono() {
        return foliono;
    }
    public void setFoliono(String foliono) {
        this.foliono = foliono;
    }
    public String getSchemecode() {
        return schemecode;
    }
    public void setSchemecode(String schemecode) {
        this.schemecode = schemecode;
    }
    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    public String getClientcode() {
        return clientcode;
    }
    public void setClientcode(String clientcode) {
        this.clientcode = clientcode;
    }
    public Double getAllottedunit() {
        return allottedunit;
    }
    public void setAllottedunit(Double allottedunit) {
        this.allottedunit = allottedunit;
    }
    public String getBeneficiaryid() {
        return beneficiaryid;
    }
    public void setBeneficiaryid(String beneficiaryid) {
        this.beneficiaryid = beneficiaryid;
    }
    public String getDpcflag() {
        return dpcflag;
    }
    public void setDpcflag(String dpcflag) {
        this.dpcflag = dpcflag;
    }
    public String getOrdertype2() {
        return ordertype2;
    }
    public void setOrdertype2(String ordertype2) {
        this.ordertype2 = ordertype2;
    }
    public String getDptrans() {
        return dptrans;
    }
    public void setDptrans(String dptrans) {
        this.dptrans = dptrans;
    }
    public String getMembercode() {
        return membercode;
    }
    public void setMembercode(String membercode) {
        this.membercode = membercode;
    }
    public String getSettno() {
        return settno;
    }
    public void setSettno(String settno) {
        this.settno = settno;
    }
    public String getSetttype() {
        return setttype;
    }
    public void setSetttype(String setttype) {
        this.setttype = setttype;
    }
    public String getEuin() {
        return euin;
    }
    public void setEuin(String euin) {
        this.euin = euin;
    }
    public String getSubordertype() {
        return subordertype;
    }
    public void setSubordertype(String subordertype) {
        this.subordertype = subordertype;
    }
    public String getEuindecl() {
        return euindecl;
    }
    public void setEuindecl(String euindecl) {
        this.euindecl = euindecl;
    }
    public String getInternalrefno() {
        return internalrefno;
    }
    public void setInternalrefno(String internalrefno) {
        this.internalrefno = internalrefno;
    }
    public String getKycflag() {
        return kycflag;
    }
    public void setKycflag(String kycflag) {
        this.kycflag = kycflag;
    }
    public String getRemarks() {
        return remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    public String getOrdertype() {
        return ordertype;
    }
    public void setOrdertype(String ordertype) {
        this.ordertype = ordertype;
    }
    public String getSipregnno() {
        return sipregnno;
    }
    public void setSipregnno(String sipregnno) {
        this.sipregnno = sipregnno;
    }
    public Date getSipregndate() {
        return sipregndate;
    }
    public void setSipregndate(Date sipregndate) {
        this.sipregndate = sipregndate;
    }
    public String getSubbrcode() {
        return subbrcode;
    }
    public void setSubbrcode(String subbrcode) {
        this.subbrcode = subbrcode;
    }
    public Date getOrderdate() {
        return orderdate;
    }
    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }
    public String getRtaschemecode() {
        return rtaschemecode;
    }
    public void setRtaschemecode(String rtaschemecode) {
        this.rtaschemecode = rtaschemecode;
    }
    public String getValidflag() {
        return validflag;
    }
    public void setValidflag(String validflag) {
        this.validflag = validflag;
    }
    public String getRtatransno() {
        return rtatransno;
    }
    public void setRtatransno(String rtatransno) {
        this.rtatransno = rtatransno;
    }
    
    public String getBeneficiary2() {
        return beneficiary2;
    }
    public void setBeneficiary2(String beneficiary2) {
        this.beneficiary2 = beneficiary2;
    }
    public Date getReportdate() {
        return reportdate;
    }
    public void setReportdate(Date reportdate) {
        this.reportdate = reportdate;
    }
    public String getIsin() {
        return isin;
    }
    public void setIsin(String isin) {
        this.isin = isin;
    }
    public String getQty() {
        return qty;
    }
    public void setQty(String qty) {
        this.qty = qty;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    
    

}
