package com.freemi.entity.general;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

@Component
public class ForgotPassword {

	@NotNull(message="Data null ") @NotEmpty(message="Data empty") @Size(min=10,max=10) @Pattern(regexp="[6-9][0-9]{9}", message="Inavlid mobile number format!")
	public String usermobile;

	public String getUsermobile() {
		return usermobile;
	}
	public void setUsermobile(String usermobile) {
		this.usermobile = usermobile;
	}

	
}
