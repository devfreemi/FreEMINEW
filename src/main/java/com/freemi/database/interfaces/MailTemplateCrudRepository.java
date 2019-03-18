package com.freemi.database.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.freemi.entity.database.EmailTemplate;

public interface MailTemplateCrudRepository extends JpaRepository<EmailTemplate,Integer>{
	
	public EmailTemplate findOneByEmailTemplateName(String templateName);
	
	
	
}
