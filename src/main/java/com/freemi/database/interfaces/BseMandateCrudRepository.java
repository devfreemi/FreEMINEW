package com.freemi.database.interfaces;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.freemi.entity.investment.BseMandateDetails;

public interface BseMandateCrudRepository extends JpaRepository<BseMandateDetails,Integer>{
	
	public List<BseMandateDetails> findAllByClientCodeAndAccountNumber(String clientID, String accountNumber);
	
	/*@Transactional
	@Modifying
	@Query("update BseMandateDetails b set b.mandateId=:mandateId, b.mandateComplete='Y' where b.clientCode=:clientId")
	public int updateMandateRegisterResponse(@Param("clientId") String clientId,@Param("refno") String refNo, @Param("startDate") Date StartDate, @Param("endDate") Date endDate, @Param("amount") String amount, @Param("mandateId") String mandateId, @Param("msg") String reponseMsg);*/
}
