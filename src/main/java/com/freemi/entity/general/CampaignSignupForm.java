package com.freemi.entity.general;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="campaign_signup")
public class CampaignSignupForm {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String serialno;
	
	@Column(name="MOBILE")
	private String mobile;
	
	@Column(name="LOCATION")
	private String location;
	
	@Column(name="USER_AGENT")
	private String userrequestingagent;
	
	@Column(name="USERSYSTEMIP")
	private String usersystemip;
	
	@Column(name="SIGNUPTIME")
	private Date timeStamp;
	
	
	public String getSerialno() {
		return serialno;
	}
	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getUserrequestingagent() {
		return userrequestingagent;
	}
	public void setUserrequestingagent(String userrequestingagent) {
		this.userrequestingagent = userrequestingagent;
	}
	public String getUsersystemip() {
		return usersystemip;
	}
	public void setUsersystemip(String usersystemip) {
		this.usersystemip = usersystemip;
	}
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	
	
	
}
