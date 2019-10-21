package com.freemi.database.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.freemi.entity.investment.MFinitiatedTrasactions;

@Repository
public interface MFInitiatedTransactionCrudRepository extends JpaRepository<MFinitiatedTrasactions,Long>{
	
	
}
