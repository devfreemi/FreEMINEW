package com.freemi.database.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.freemi.entity.investment.BseAOFDocument;

@Repository
public interface BseCustomerAOFRepository extends JpaRepository<BseAOFDocument,Integer>{
	
	public BseAOFDocument findOneByClientid(String clientid);
	
	
	@Transactional
	@Modifying
	@Query("update BseAOFDocument b set b.aofuploaded= :success, b.aofuploadresponsecode= :responsecode, b.aofuploadresponsemsg= :message where b.clientid= :clientid")
	public int updateAofUploadStatus(@Param("clientid") String clientid, @Param("success") String success,@Param("responsecode") String responsecode, @Param("message") String message );
}
