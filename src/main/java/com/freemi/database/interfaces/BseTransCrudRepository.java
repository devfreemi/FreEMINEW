package com.freemi.database.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.freemi.entity.investment.SelectMFFund;

public interface BseTransCrudRepository extends JpaRepository<SelectMFFund, Long> {
	
	public List<SelectMFFund> getByClientID(String customerId);
	public boolean existsByTransactionID(String generatedId);
	
	public SelectMFFund findOneByTransactionIDAndClientID(String transactionNumber, String clientId);
	
	@Transactional
	@Modifying
	@Query("update SelectMFFund b set b.isActive= 'N' where b.clientID= :clientId and b.transactionID =:transactionNumber")
	public int disablePurchaseTransaction(@Param("clientId") String clientId,@Param("transactionNumber") String transactionNumber);
}
