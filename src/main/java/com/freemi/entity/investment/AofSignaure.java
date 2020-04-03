package com.freemi.entity.investment;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class AofSignaure implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    @NotNull(message = "Mobile no is manadatory")
    private String mobile;
    private String clientid;
    
    @NotNull(message = "PAN is manadatory")
    private String pan;
    
    @NotNull(message = "Signature 1 is mandatory")
    private String sign1;
    private String sign2;
    
    private String imei;
    private String obj1;
    private String obj2;
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getClientid() {
        return clientid;
    }
    public void setClientid(String clientid) {
        this.clientid = clientid;
    }
    public String getPan() {
        return pan;
    }
    public void setPan(String pan) {
        this.pan = pan;
    }
    public String getSign1() {
        return sign1;
    }
    public void setSign1(String sign1) {
        this.sign1 = sign1;
    }
    public String getSign2() {
        return sign2;
    }
    public void setSign2(String sign2) {
        this.sign2 = sign2;
    }
    public String getImei() {
        return imei;
    }
    public void setImei(String imei) {
        this.imei = imei;
    }
    public String getObj1() {
        return obj1;
    }
    public void setObj1(String obj1) {
        this.obj1 = obj1;
    }
    public String getObj2() {
        return obj2;
    }
    public void setObj2(String obj2) {
        this.obj2 = obj2;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    

}
