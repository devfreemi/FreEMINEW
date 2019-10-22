package com.freemi.services.Impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.freemi.database.interfaces.EmailUnsubscribeCrudRepository;
import com.freemi.entity.database.EmailUnsubscribeForm;
import com.freemi.services.interfaces.EmailServiceInterface;

@Component
public class EmailServiceImpl implements EmailServiceInterface {
	
	@Autowired
	EmailUnsubscribeCrudRepository emailUnsubscribeCrudRepository;
	
	private static final Logger logger = LogManager.getLogger(EmailServiceImpl.class);

	@Override
	public boolean addUserToUbsubscribeList(EmailUnsubscribeForm unsubscribeForm) {
		logger.info("Unsubscribe mail id: Request received to add mail id to unsubscribe list.. " + unsubscribeForm.getEmail());
		boolean flag=true;
		try{
		emailUnsubscribeCrudRepository.save(unsubscribeForm);
			logger.info("Unsubscribe user updated..");
			
		}catch(Exception e){
			flag = false;
			logger.error("Failed to update customer ID to unsunbscribe mail list: ",e);
		}
		return flag;
	}

	@Override
	public boolean isUserAlreadyUnsbscribed(String emailId) {
		// TODO Auto-generated method stub
		return false;
	}

}
