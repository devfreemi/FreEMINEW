package com.freemi.database.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.freemi.entity.investment.MFFatcaDeclareForm;

public interface BseCustomerFATCACrudRepository extends JpaRepository<MFFatcaDeclareForm,String>{
	
	public MFFatcaDeclareForm findOneByClientID(String clientID);
	
	@Transactional
	@Modifying
	@Query("update MFFatcaDeclareForm b set b.fatcaUploaded= :fatuploadStatus where b.clientID=:clientid")
	public int updateFatcaDeclarationStatus(@Param("fatuploadStatus") boolean fatcaUpload, @Param("clientid") String clientId);
	
	@Transactional
	@Modifying
	@Query("update MFFatcaDeclareForm b set b.fatcaUploaded= :fatuploadStatus, b.uploadResponse= :responsemsg where b.clientID=:clientid")
	public int updateFatcaDeclarationStatus(@Param("fatuploadStatus") boolean fatcaUpload,@Param("responsemsg") String responsemsg, @Param("clientid") String clientId);
	
}
