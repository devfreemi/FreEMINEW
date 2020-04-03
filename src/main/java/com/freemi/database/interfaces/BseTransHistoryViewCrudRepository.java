package com.freemi.database.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.freemi.entity.investment.BsemfTransactionHistory;

public interface BseTransHistoryViewCrudRepository extends JpaRepository<BsemfTransactionHistory,String>{
	
	public List<BsemfTransactionHistory> findAllByClienId(String clientID);
	
	public BsemfTransactionHistory findOneByClienIdAndTransctionTypeAndTransactionId(String clientId,String category, String tran);
	
	public boolean existsByClienIdAndTransactionIdAndOrderNo(String clientid, String transactionid, String orderno);
	
}
