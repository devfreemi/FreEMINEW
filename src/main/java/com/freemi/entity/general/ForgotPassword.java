package com.freemi.entity.general;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

@Component
public class ForgotPassword {

	@NotNull @NotEmpty @Size(min=10,max=10)
	public String usermobile;

	public String getUsermobile() {
		return usermobile;
	}
	public void setUsermobile(String usermobile) {
		this.usermobile = usermobile;
	}

	
}
