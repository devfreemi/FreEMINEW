package com.freemi.database.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.freemi.entity.investment.RegistryFunds;

@Repository
public interface RegistryFundsCrudRepository extends JpaRepository<RegistryFunds,Integer>{
	
	public RegistryFunds findOneBySchemeCode(String schemecode);
}
