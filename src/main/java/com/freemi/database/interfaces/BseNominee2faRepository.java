package com.freemi.database.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.freemi.entity.investment.Nominee2faresponse;

@Repository
public interface BseNominee2faRepository extends JpaRepository<Nominee2faresponse,Integer>{
	
	
}
