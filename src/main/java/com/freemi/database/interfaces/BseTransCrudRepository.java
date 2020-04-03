package com.freemi.database.interfaces;

import java.util.Date;
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

        
    @Transactional
    @Modifying
    @Query("update SelectMFFund b set b.transactionType='CXL', b.ordercancelled= 'Y', b.sipcancelapiresponse =:apiresponse, b.sipcancelrefid =:refno,b.sipcanceldate =:processtime where b.clientID= :clientId and b.transactionID =:transactionNumber")
    public int changesipcancelstatus(@Param("apiresponse") String cancelsipapireponse,@Param("refno") String cancelsiprequestrefno,@Param("processtime") Date processtime,  @Param("clientId") String clientId,@Param("transactionNumber") String transactionNumber);
    
	
    /*
    	    @Transactional
    	    @Modifying
    	    @Query("update SelectMFFund b set b.ordercancelled= 'Y', b.sipcancelapiresponse =:apiresponse, b.sipcancelrefid =:refno where b.clientID= :clientId and b.transactionID =:transactionNumber")
    	    public int changesipcancelstatus(@Param("apiresponse") String cancelsipapireponse,@Param("refno") String cancelsiprequestrefno,  @Param("clientId") String clientId,@Param("transactionNumber") String transactionNumber);
    	    
    */
    /*
    @Transactional
    @Modifying
    @Query(value =  "update SelectMFFund b set b.ordercancelled= 'Y' where b.clientID= :clientId and b.transactionID =:transactionNumber")
    public int changesipcancelstatus(@Param("clientId") String clientId,@Param("transactionNumber") String transactionNumber);
    */
	
}

