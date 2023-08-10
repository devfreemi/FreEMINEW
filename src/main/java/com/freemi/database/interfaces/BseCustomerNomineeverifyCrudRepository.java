package com.freemi.database.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.freemi.entity.investment.Nomineeverify;

public interface BseCustomerNomineeverifyCrudRepository extends JpaRepository<Nomineeverify,String>{
	
	public Nomineeverify findOneByClientID(String clientID);
	
	/*
	 * @Query(value =
	 * "UPDATE bsemf_customers_nominee b SET b.NOMINEE_REGISTRATION_TRIGGERED= :trigger, b.NOMINEE_REGISTRATION_STATUS_CODE= :statuscode, b.NOMINEE_REGISTRATION_REMARKS= :remarks WHERE b.CLIENT_ID= :client ;"
	 * , nativeQuery = true) public String
	 * updatenomineeregistrationstatus(@Param("client") String
	 * clientid, @Param("trigger") String trigger, @Param("statuscode") String
	 * statuscode, @Param("remarks") String remarks);
	 */	
	
}
