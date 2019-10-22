package com.freemi.services.interfaces;

import org.springframework.stereotype.Component;

import com.freemi.entity.database.EmailUnsubscribeForm;

@Component
public interface EmailServiceInterface {
	
	public boolean addUserToUbsubscribeList(EmailUnsubscribeForm unsubscribeForm);
	
	public boolean isUserAlreadyUnsbscribed(String emailId);
	
	
	
}
