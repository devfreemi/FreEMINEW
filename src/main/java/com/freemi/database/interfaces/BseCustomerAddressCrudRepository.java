package com.freemi.database.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.freemi.entity.investment.AddressDetails;

public interface BseCustomerAddressCrudRepository extends JpaRepository<AddressDetails,String>{
	
	public AddressDetails findOneByClientID(String clientID);
	
}
