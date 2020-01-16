package com.freemi.database.respository.mahindra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.freemi.entity.investment.mahindra.MahindraKycDocuments;

public interface MMCustKycDocumentRepository extends JpaRepository<MahindraKycDocuments, Integer> {
	
	public boolean existsByMobile(String mobile);
	
	public boolean existsByCustomeridAndActive(String customerId,String active);
	
	public MahindraKycDocuments findOneByImageTypeAndCustomeridAndActive(String imagetype, String customerid, String active);
	
	@Transactional
	@Modifying
	@Query("update MahindraKycDocuments b set b.uploadedtomahindra='Y' where b.customerid= :customerid and b.imageType =:imagetype and b.active= :active ")
	public int uploaddocuploadstatus(@Param("imagetype") String imagetype, @Param("customerid")String customerid, @Param("active")String active);

}
