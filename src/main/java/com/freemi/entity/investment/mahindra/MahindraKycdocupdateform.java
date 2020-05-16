package com.freemi.entity.investment.mahindra;

import java.io.Serializable;

import javax.validation.constraints.NotNull;



public class MahindraKycdocupdateform implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    @NotNull(message = "Mobile no cannot be empty")
    private String mobile;
    private byte[] kycphotoproof;
    private String kycphotoproofOriginalFilename;
    
    private byte[] kycpanproof;
    private String kycpanproofOriginalFilename;
    
    private byte[] cancelledcheque;
    private String canecelledchequeOriginalFilename;
    
    private byte[] addressproof;
    private String kycaddressproofOriginalFilename;
    private String addressprooftype;
    private String addressproofrefid;
    private String addressproofexpiry;
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public byte[] getKycphotoproof() {
        return kycphotoproof;
    }
    public void setKycphotoproof(byte[] kycphotoproof) {
        this.kycphotoproof = kycphotoproof;
    }
    public byte[] getKycpanproof() {
        return kycpanproof;
    }
    public void setKycpanproof(byte[] kycpanproof) {
        this.kycpanproof = kycpanproof;
    }
    public byte[] getCancelledcheque() {
        return cancelledcheque;
    }
    public void setCancelledcheque(byte[] cancelledcheque) {
        this.cancelledcheque = cancelledcheque;
    }
    public byte[] getAddressproof() {
        return addressproof;
    }
    public void setAddressproof(byte[] addressproof) {
        this.addressproof = addressproof;
    }
    public String getAddressprooftype() {
        return addressprooftype;
    }
    public void setAddressprooftype(String addressprooftype) {
        this.addressprooftype = addressprooftype;
    }
    public String getAddressproofrefid() {
        return addressproofrefid;
    }
    public void setAddressproofrefid(String addressproofrefid) {
        this.addressproofrefid = addressproofrefid;
    }
    public String getAddressproofexpiry() {
        return addressproofexpiry;
    }
    public void setAddressproofexpiry(String addressproofexpiry) {
        this.addressproofexpiry = addressproofexpiry;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    public String getKycphotoproofOriginalFilename() {
        return kycphotoproofOriginalFilename;
    }
    public void setKycphotoproofOriginalFilename(String kycphotoproofOriginalFilename) {
        this.kycphotoproofOriginalFilename = kycphotoproofOriginalFilename;
    }
    public String getKycpanproofOriginalFilename() {
        return kycpanproofOriginalFilename;
    }
    public void setKycpanproofOriginalFilename(String kycpanproofOriginalFilename) {
        this.kycpanproofOriginalFilename = kycpanproofOriginalFilename;
    }
    public String getCanecelledchequeOriginalFilename() {
        return canecelledchequeOriginalFilename;
    }
    public void setCanecelledchequeOriginalFilename(String canecelledchequeOriginalFilename) {
        this.canecelledchequeOriginalFilename = canecelledchequeOriginalFilename;
    }
    public String getKycaddressproofOriginalFilename() {
        return kycaddressproofOriginalFilename;
    }
    public void setKycaddressproofOriginalFilename(String kycaddressproofOriginalFilename) {
        this.kycaddressproofOriginalFilename = kycaddressproofOriginalFilename;
    }
    
    
    
    
}
