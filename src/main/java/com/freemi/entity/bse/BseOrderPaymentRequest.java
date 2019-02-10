package com.freemi.entity.bse;

public class BseOrderPaymentRequest {

	private String MemberCode;
	private String ClientCode;
	private String LogOutURL;
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
	public String getLogOutURL() {
		return LogOutURL;
	}
	public void setLogOutURL(String logOutURL) {
		LogOutURL = logOutURL;
	}
	
	
}
