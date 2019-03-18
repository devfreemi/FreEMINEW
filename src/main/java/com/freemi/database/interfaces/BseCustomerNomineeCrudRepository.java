package com.freemi.database.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.freemi.entity.investment.MFNominationForm;

public interface BseCustomerNomineeCrudRepository extends JpaRepository<MFNominationForm,String>{
	
	public MFNominationForm findOneByClientID(String clientID);
	
}
