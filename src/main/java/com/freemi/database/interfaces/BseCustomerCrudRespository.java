package com.freemi.database.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.freemi.entity.investment.MFCustomers;

public interface BseCustomerCrudRespository extends JpaRepository<MFCustomers, Long>{

	/* public List<BseMFInvestForm> findByPan1(String pan1); */
	public List<MFCustomers> findByPan1AndAccountActive(String pan1, String activestatus);
	
	/* public List<BseMFInvestForm> getByClientID(String customerId); */
	public List<MFCustomers> getByClientIDAndAccountActive(String customerId, String activestatus);
	
	/* public BseMFInvestForm getByMobile(String mobile); */
	public MFCustomers getByMobileAndAccountActive(String mobile, String activestatus);
	public MFCustomers getByMobileAndPan1AndAccountActive(String mobile,String pan, String activestatus);
	
	/* public boolean existsByMobile(String mobile); */
	public boolean existsByMobileAndAccountActive(String mobile,String status);
	
	public boolean existsByPan1(String pan);
	public boolean existsByPan1AndMobileNot(String pan,String mobile);
	
//	public boolean existsByPan1AndAccountActive(String pan,String activeStatus);
	
	public boolean existsByClientID(String clientID);
	
	public MFCustomers findOneByClientID(String clientId);
	
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
	
	@Query("select c.bseregistrationSuccess from BseMFInvestForm c where c.mobile= :mobile and c.accountActive = :activestatus ")
	public String getBseRegistrationStatusByMobile(@Param("mobile") String mobile, @Param("activestatus") String activestatus );
	
//	@Query("select c.bseregistrationSuccess, c.aofuploadComplete from BseMFInvestForm c where c.mobile= :mobile and c.accountActive = 'Y'")
	
//	@Query(value = "select c.BSE_REGISTRATION_SUCCESS, c.RESGISTRATION_RESPONSE,f.FATCA_DECLARE_UPLOAD_COMPLETE, f.UPLOAD_RESPONSE, c.AOF_UPLOAD_COMPLETE, c.AOF_UPLOAD_RESPONSE from bsemf_customers c, bsemf_customers_fatca_declaration f WHERE c.CLIENT_ID=f.CLIENT_ID and c.MOBILE_NO=:mobile and c.ACCOUNT_ACTIVE = 'Y';",nativeQuery = true)
//	public List<Object[]> getBseRegistrationAOFStatus(@Param("mobile") String mobile);
	@Query(value = "select c.BSE_REGISTRATION_SUCCESS, c.RESGISTRATION_RESPONSE, COALESCE(f.FATCA_DECLARE_UPLOAD_COMPLETE,'0') AS 'FATCA_DECLARE_UPLOAD_COMPLETE', COALESCE(f.UPLOAD_RESPONSE, 'Not yet submitted') AS 'UPLOAD_RESPONSE', c.AOF_UPLOAD_COMPLETE, c.AOF_UPLOAD_RESPONSE from bsemf_customers c LEFT JOIN bsemf_customers_fatca_declaration f on c.CLIENT_ID=f.CLIENT_ID where c.MOBILE_NO=:mobile and c.ACCOUNT_ACTIVE = 'Y';", nativeQuery = true)
	public String getBseRegistrationAOFStatus(@Param("mobile") String mobile);
	
	@Transactional
	@Modifying
	@Query("update BseMFInvestForm b set b.bseregistrationSuccess='Y' where b.clientID=:clientid and b.accountActive = 'Y'")
	public int updateBseRegistrationStatus(@Param("clientid") String clientid);
	
	@Transactional
	@Modifying
	@Query("update MFCustomers b set b.bseregistrationSuccess= :success, b.registrationResponse= :responsemsg where b.clientID=:clientid and b.accountActive = 'Y'")
	public int updateBseRegistrationStatusAndRegistrationResponse(@Param("clientid") String clientid,@Param("responsemsg") String responsemsg, @Param("success") String success);
	
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
	
	@Transactional
	@Modifying
	@Query("update BseMFInvestForm b set b.aofuploadComplete= :success, b.aofUploadResponse= :message where b.mobile= :mobile and b.accountActive = 'Y'")
	public int updateAofUploadStatus(@Param("mobile") String mobile, @Param("success") String success,@Param("message") String message );
	
	
}
