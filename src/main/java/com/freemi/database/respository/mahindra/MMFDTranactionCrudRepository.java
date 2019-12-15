package com.freemi.database.respository.mahindra;

import org.springframework.data.jpa.repository.JpaRepository;

import com.freemi.entity.investment.mahindra.MahindraFDPurchaseRequest;

public interface MMFDTranactionCrudRepository extends JpaRepository<MahindraFDPurchaseRequest, Integer> {
	
	public boolean existsByMobile(String mobile);
	
	public boolean existsByTransactionId(String transactionId);

}
