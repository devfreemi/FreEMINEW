package com.freemi.database.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.freemi.entity.investment.BseMandateDetails;

public interface BseMandateCrudRepository extends JpaRepository<BseMandateDetails,Integer>{
	
	public List<BseMandateDetails> findAllByClientCodeAndAccountNumber(String clientID, String accountNumber);
	
	/*@Transactional
	@Modifying
	@Query("update BseMandateDetails b set b.mandateId=:mandateId, b.mandateComplete='Y' where b.clientCode=:clientId")
	public int updateMandateRegisterResponse(@Param("clientId") String clientId,@Param("refno") String refNo, @Param("startDate") Date StartDate, @Param("endDate") Date endDate, @Param("amount") String amount, @Param("mandateId") String mandateId, @Param("msg") String reponseMsg);*/
	
	public boolean existsByClientCodeAndMandateTypeAndMandateActive(String clientId, String mandateType, String active);
	public List<BseMandateDetails> findAllByClientCodeAndMandateTypeAndMandateActive(String clientId, String mandateType, String active);
	public BseMandateDetails findOneByClientCodeAndMandateType(String clientId, String mandateType);
	public BseMandateDetails findOneByClientCodeAndAccountNumberAndMandateActive(String clientId, String accountNumber,String mandateStatus);
	public List<BseMandateDetails> findAllByClientCodeAndAccountNumberAndMandateTypeAndMandateActive(String clientId, String accountNumber,String mandatetype,String mandateStatus);
}
