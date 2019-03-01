package com.freemi.database.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.freemi.entity.investment.BseMFInvestForm;

public interface BseCustomerCrudRespository extends JpaRepository<BseMFInvestForm, Long>{

	public List<BseMFInvestForm> findByPan1(String pan1);
	public List<BseMFInvestForm> getByClientID(String customerId);
	public BseMFInvestForm getByMobile(String mobile);
	public boolean existsByMobile(String mobile);
	public boolean existsByPan1(String pan);
	public boolean existsByClientID(String clientID);
	
	public BseMFInvestForm findOneByClientID(String clientId);
	
	@Query("select c.pan1 from BseMFInvestForm c where c.mobile= :mobile")
	public String getCustomerPanNumberFromMobile(@Param("mobile") String mobile);
	
	@Query("select c.clientID from BseMFInvestForm c where c.mobile= :mobile")
	public String getClientIdFromMobile(@Param("mobile") String mobile );
	
	@Query("select c.clientID from BseMFInvestForm c where c.pan1= :pan")
	public String getClientIdFromPan(@Param("pan") String pan );
	
	@Query("select c.bseregistrationSuccess from BseMFInvestForm c where c.pan1= :pan")
	public String getBseRegistrationStatus(@Param("pan") String pan );
	
	@Query("select c.aofuploadComplete from BseMFInvestForm c where c.mobile= :mobile")
	public String getBseRegistrationAOFStatus(@Param("mobile") String mobile);
	
	@Transactional
	@Modifying
	@Query("update BseMFInvestForm b set b.bseregistrationSuccess='Y' where b.clientID=:clientid")
	public int updateBseRegistrationStatus(@Param("clientid") String clientid);
	
	@Transactional
	@Modifying
	@Query("update BseMFInvestForm b set b.customerSignature= :sign where b.clientID= :clientId and b.pan1= :pan")
	public int uploadCustomerSignature(@Param("clientId") String clientId,@Param("pan") String pan,@Param("sign") String signature);
	
	@Query("select c.aofuploadComplete from BseMFInvestForm c where c.mobile= :mobile")
	public String getAofUploadStatus(@Param("mobile") String mobile);
	
	@Transactional
	@Modifying
	@Query("update BseMFInvestForm b set b.aofuploadComplete= 'Y' where b.mobile= :mobile")
	public int updateAofUploadStatus(@Param("mobile") String mobile);
	
	
}
