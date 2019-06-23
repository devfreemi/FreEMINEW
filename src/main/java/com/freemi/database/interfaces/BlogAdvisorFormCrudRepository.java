package com.freemi.database.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.freemi.entity.database.BlogAdvisorForm;

public interface BlogAdvisorFormCrudRepository extends JpaRepository<BlogAdvisorForm,Integer>{
	
	
}
