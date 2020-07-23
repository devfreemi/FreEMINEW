package com.freemi.database.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.freemi.entity.database.EmailBounceReport;

@Service
public interface EmailBounceCrudRepository extends JpaRepository<EmailBounceReport,Integer>{
	
	public boolean existsByBouncedmailid(String bouncedmailidcheck);
	
	
}
