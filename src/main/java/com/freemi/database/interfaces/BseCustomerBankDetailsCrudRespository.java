package com.freemi.database.interfaces;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.freemi.entity.database.UserBankDetails;

@Service
public interface BseCustomerBankDetailsCrudRespository extends JpaRepository<UserBankDetails, String>{

	
	public UserBankDetails findOneByClientID(String clientCode);
	public List<UserBankDetails> getByClientID(String clientCode);
	
	@Transactional
	@Modifying
	@Query("update UserBankDetails u set u.eMandateComplete= :eMandateComplete, u.eMandateStartDate= :startDate, u.eMandateEndDate= :endDate, u.eMandateRefNo= :mandateRefNo, u.eMandateProcessDate= :mandateProcessDate where u.clientID= :clientCode and u.accountNumber= :accNumber")
	public int updateEmandateStatus(@Param("clientCode") String clientCode,@Param("accNumber") String accNumber, @Param("eMandateComplete") boolean emandateStatus,@Param("startDate") Date emandateStartDate,@Param("endDate") Date emandateEndtDate,@Param("mandateRefNo") String mandateUpdateRefNo,@Param("mandateProcessDate") Date mandateProcessDate  );
	
}
