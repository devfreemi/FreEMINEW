package com.freemi.database.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.freemi.entity.database.EmailBounceReport;

public interface EmailBounceReportCrudRepository extends JpaRepository<EmailBounceReport,Integer>{
	
	public boolean existsByBouncedMailId(String mailId);
	
	
}
