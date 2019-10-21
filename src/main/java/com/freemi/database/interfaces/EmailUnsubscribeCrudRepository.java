package com.freemi.database.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.freemi.entity.database.EmailUnsubscribeForm;

@Service
public interface EmailUnsubscribeCrudRepository extends JpaRepository<EmailUnsubscribeForm,Integer>{
	
	public EmailUnsubscribeForm findOneByEmail(String email);
	
}
