package com.freemi.database.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.freemi.entity.investment.BseBankid;

@Repository
public interface BseBankidRepository extends JpaRepository<BseBankid,Integer>{
	
	public BseBankid getByTransactiontypeAndRazorpaybankname(String transtype, String bankname);
	
}
