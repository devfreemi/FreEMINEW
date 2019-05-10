package com.freemi.database.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.freemi.entity.database.EmailUnsubscribeForm;


public interface EmailUnsubscribeCrudRepository extends JpaRepository<EmailUnsubscribeForm,Integer>{
	
	public EmailUnsubscribeForm findOneByEmail(String email);
	
}
