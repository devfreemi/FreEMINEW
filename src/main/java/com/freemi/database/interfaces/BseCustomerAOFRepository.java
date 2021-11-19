package com.freemi.database.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.freemi.entity.investment.BseAOFDocument;

@Repository
public interface BseCustomerAOFRepository extends JpaRepository<BseAOFDocument,Integer>{
	
	public BseAOFDocument findOneByClientid(String clientid);
}
