package com.freemi.entity.general;

import java.util.List;

import javax.persistence.JoinColumn;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ValidatePANResult<Folios> {

	@Autowired
	@JsonProperty("Folis")
	@JoinColumn(name="feedback_id")
	private List<Folios> folioList;
	private char IsEKYCVerified;
	private char IsExistingInvestor;
	private String NameAsPerPAN;
	private String PANNumber;
	private String ReturnCode;
	private String ReturnMsg;
	private String UDP;
	private String isFATCAVerified;
	
	public List<Folios> getFolios() {
		return folioList;
	}
	public void setFolios(List<Folios> folioList) {
		this.folioList = folioList;
	}
	public char getIsEKYCVerified() {
		return IsEKYCVerified;
	}
	public void setIsEKYCVerified(char isEKYCVerified) {
		IsEKYCVerified = isEKYCVerified;
	}
	public char getIsExistingInvestor() {
		return IsExistingInvestor;
	}
	public void setIsExistingInvestor(char isExistingInvestor) {
		IsExistingInvestor = isExistingInvestor;
	}
	public String getNameAsPerPAN() {
		return NameAsPerPAN;
	}
	public void setNameAsPerPAN(String nameAsPerPAN) {
		NameAsPerPAN = nameAsPerPAN;
	}
	public String getPANNumber() {
		return PANNumber;
	}
	public void setPANNumber(String pANNumber) {
		PANNumber = pANNumber;
	}
	public String getReturnCode() {
		return ReturnCode;
	}
	public void setReturnCode(String returnCode) {
		ReturnCode = returnCode;
	}
	public String getReturnMsg() {
		return ReturnMsg;
	}
	public void setReturnMsg(String returnMsg) {
		ReturnMsg = returnMsg;
	}
	public String getUDP() {
		return UDP;
	}
	public void setUDP(String uDP) {
		UDP = uDP;
	}
	public String getIsFATCAVerified() {
		return isFATCAVerified;
	}
	public void setIsFATCAVerified(String isFATCAVerified) {
		this.isFATCAVerified = isFATCAVerified;
	}
	
	
	
}
