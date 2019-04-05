package com.freemi.database.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.freemi.entity.investment.MFNominationForm;

public interface BseCustomerNomineeCrudRepository extends JpaRepository<MFNominationForm,String>{
	
	public MFNominationForm findOneByClientID(String clientID);
	
	/*@Transactional
	@Modifying
	@Query("update MFFatcaDeclareForm b set b.fatcaUploaded='fatuploadStatus' where b.clientID=:clientid")
	public int updateFatcaDeclarationStatus(@Param("fatuploadStatus") String clientid, @Param("clientid") String clientId);*/
	
}
