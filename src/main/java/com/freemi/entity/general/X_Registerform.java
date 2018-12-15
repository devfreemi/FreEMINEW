package com.freemi.entity.general;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

//@PropertySource("classpath:connection.properties")
@Entity
@Table(name="customers")
public class X_Registerform {
	
	@Size(min=3, max=10)
	@NotEmpty(message="cannot be empty")
	private String userName;
	
	@Id
	@Column(name="USERID")
	private String userId;
	
	@Column(name="FNAME")
	private String firstName;
	
	@Column(name="LNAME")
	private String lastName;
	
	@Column(name="PROFILEURL")
	private String url;
	private String base;
	
	private String password;
	
	private String pool;
	
	@Email(message="Enter a valid mail")
	@Column(name="EMAIL")
	private String email;
	
	@Pattern(regexp="^[0-9]{5}+$", message="Invalid number format")
	@Column(name="MOBILE")
	private String mobile;
	
	
	private String fullName;
	
	
	
	
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPool() {
		return pool;
	}
	public void setPool(String pool) {
		this.pool = pool;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
		

}
