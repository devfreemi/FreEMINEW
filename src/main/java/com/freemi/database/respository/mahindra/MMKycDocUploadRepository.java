package com.freemi.database.respository.mahindra;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.freemi.entity.investment.mahindra.MahindraKycDocUploadStatus;

public interface MMKycDocUploadRepository extends JpaRepository<MahindraKycDocUploadStatus, Integer> {
	
    /*
    public boolean existsByMobile(String mobile);
    
    public boolean existsByCustomeridAndActive(String customerId,String active);
    
    public MahindraKycDocuments findOneByImageTypeAndCustomeridAndActive(String imagetype, String customerid, String active);
    
    @Transactional
    @Modifying
    @Query("update MahindraKycDocuments b set b.uploadedtomahindra='Y' where b.customerid= :customerid and b.imageType =:imagetype and b.active= :active ")
    public int uploaddocuploadstatus(@Param("imagetype") String imagetype, @Param("customerid")String customerid, @Param("active")String active);
    */
    
    public List<MahindraKycDocUploadStatus> findAllByApplicationnoAndUploadstatus(String applicationno,String uploadStatus);
    
    @Transactional
    @Modifying
    @Query("update MahindraKycDocUploadStatus b set b.uploadstatus= :uploadstatus, b.apiresponse= :message where b.customerid= :customerid and b.serial =:serial ")
    public int reuploaddocstatus(@Param("uploadstatus") String uploadstatus,@Param("message") String message, @Param("serial")int serial, @Param("customerid")String customerid);
    
}
