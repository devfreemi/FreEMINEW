package com.freemi.database.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.freemi.entity.investment.BseMFInvestForm;

public interface BseCustomerCrudRespository extends JpaRepository<BseMFInvestForm, Long>{

	/* public List<BseMFInvestForm> findByPan1(String pan1); */
	public List<BseMFInvestForm> findByPan1AndAccountActive(String pan1, String activestatus);
	
	/* public List<BseMFInvestForm> getByClientID(String customerId); */
	public List<BseMFInvestForm> getByClientIDAndAccountActive(String customerId, String activestatus);
	
	/* public BseMFInvestForm getByMobile(String mobile); */
	public BseMFInvestForm getByMobileAndAccountActive(String mobile, String activestatus);
	public BseMFInvestForm getByMobileAndPan1AndAccountActive(String mobile,String pan, String activestatus);
	
	/* public boolean existsByMobile(String mobile); */
	public boolean existsByMobileAndAccountActive(String mobile,String status);
	public boolean existsByPan1(String pan);
	public boolean existsByClientID(String clientID);
	
	public BseMFInvestForm findOneByClientID(String clientId);
	
	/*
	 * @Query("select c.pan1 from BseMFInvestForm c where c.mobile= :mobile") public
	 * String getCustomerPanNumberFromMobile(@Param("mobile") String mobile);
	 */
	
	@Query("select c.pan1 from BseMFInvestForm c where c.mobile= :mobile and c.accountActive= :active" )
	public String getCustomerPanNumberFromMobileAndActive(@Param("mobile") String mobile, @Param("active") String accountStatus);
	
	@Query("select c.clientID from BseMFInvestForm c where c.mobile= :mobile and c.accountActive = 'Y' ")
	public String getClientIdFromMobile(@Param("mobile") String mobile );
	
	
	@Query("select c.clientID from BseMFInvestForm c where c.pan1= :pan and c.accountActive = 'Y' ")
	public String getClientIdFromPan(@Param("pan") String pan );
	
	@Query("select c.bseregistrationSuccess from BseMFInvestForm c where c.pan1= :pan and c.accountActive = 'Y' ")
	public String getBseRegistrationStatus(@Param("pan") String pan );
	
	@Query("select c.bseregistrationSuccess, c.aofuploadComplete from BseMFInvestForm c where c.mobile= :mobile and c.accountActive = 'Y'")
	public String getBseRegistrationAOFStatus(@Param("mobile") String mobile);
	
	@Transactional
	@Modifying
	@Query("update BseMFInvestForm b set b.bseregistrationSuccess='Y' where b.clientID=:clientid and b.accountActive = 'Y'")
	public int updateBseRegistrationStatus(@Param("clientid") String clientid);
	
	@Transactional
	@Modifying
	@Query("update BseMFInvestForm b set b.customerSignature1= :sign1,b.customerSignature2= :sign2  where b.clientID= :clientId and b.pan1= :pan and b.accountActive = 'Y'")
	public int uploadCustomerSignature(@Param("clientId") String clientId,@Param("pan") String pan,@Param("sign1") String signature1, @Param("sign2") String signature2);
	
	@Query("select c.aofuploadComplete from BseMFInvestForm c where c.mobile= :mobile and c.accountActive = 'Y'")
	public String getAofUploadStatus(@Param("mobile") String mobile);
	
	@Transactional
	@Modifying
	@Query("update BseMFInvestForm b set b.aofuploadComplete= 'Y' where b.mobile= :mobile and b.accountActive = 'Y'")
	public int updateAofUploadStatus(@Param("mobile") String mobile);
	
	
}
