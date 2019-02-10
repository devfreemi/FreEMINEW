package com.freemi.entity.bse;

public class BseAOFUploadRequest {
	
	private String Flag="";
	private String UserId="";
	private String EncryptedPassword="";
	private String MemberCode="";
	private String ClientCode="";
	
	private String FileName="";
	
	private String DocumentType;
	private byte[] pFileBytes;
	private String Filler1="";
	private String Filler2="";
	
	public String getFlag() {
		return Flag;
	}
	public void setFlag(String flag) {
		Flag = flag;
	}
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public String getEncryptedPassword() {
		return EncryptedPassword;
	}
	public void setEncryptedPassword(String encryptedPassword) {
		EncryptedPassword = encryptedPassword;
	}
	public String getMemberCode() {
		return MemberCode;
	}
	public void setMemberCode(String memberCode) {
		MemberCode = memberCode;
	}
	public String getClientCode() {
		return ClientCode;
	}
	public void setClientCode(String clientCode) {
		ClientCode = clientCode;
	}
	public String getFileName() {
		return FileName;
	}
	public void setFileName(String fileName) {
		FileName = fileName;
	}
	public String getDocumentType() {
		return DocumentType;
	}
	public void setDocumentType(String documentType) {
		DocumentType = documentType;
	}
	public byte[] getpFileBytes() {
		return pFileBytes;
	}
	public void setpFileBytes(byte[] pFileBytes) {
		this.pFileBytes = pFileBytes;
	}
	public String getFiller1() {
		return Filler1;
	}
	public void setFiller1(String filler1) {
		Filler1 = filler1;
	}
	public String getFiller2() {
		return Filler2;
	}
	public void setFiller2(String filler2) {
		Filler2 = filler2;
	}
	
	
	
	

}
