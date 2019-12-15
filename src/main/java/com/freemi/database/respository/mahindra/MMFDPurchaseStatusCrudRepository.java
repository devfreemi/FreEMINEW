package com.freemi.database.respository.mahindra;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.freemi.entity.investment.mahindra.MahindraFDTransactionStatus;

public interface MMFDPurchaseStatusCrudRepository extends JpaRepository<MahindraFDTransactionStatus, Integer> {
	
//	public boolean existsByMobile(String mobile);
	
	public boolean existsByCustomerIdAndTransactionId(String customerId, String transactionId);
	
	public MahindraFDTransactionStatus findOneByCustomerIdAndTransactionId(String customerId, String transactionId);
	
	public MahindraFDTransactionStatus findOneByCustomerIdAndTransactionApplicationNumber(String customerId, String applicationNo);
	
	@Transactional
	@Modifying
	@Query("update MahindraFDTransactionStatus b set b.paymentResponse= :paymentverifyresponse where b.customerId= :customerid and b.transactionApplicationNumber =:applicationno ")
	public int updatePGVerifyResponse(@Param("paymentverifyresponse") String paymentverifyresponse, @Param("customerid") String customerid,@Param("applicationno") String applicationNo );
	
	
	@Transactional
	@Modifying
	@Query("update MahindraFDTransactionStatus b set b.paymentStatusCode= :paymentstatuscode,b.isPaymentComplete= :paymentsuccess  where b.customerId= :customerid and b.transactionApplicationNumber =:applicationno ")
	public int updatepaymentStaus(@Param("paymentstatuscode") String paymentstatuscode, @Param("paymentsuccess") String paymentsuccess, @Param("customerid") String customerid,@Param("applicationno") String applicationNo );
	
	@Query("select b.serial, b.transactionId, b.transactionApplicationNumber, b.isTransactionComplete, b.isPaymentComplete from  MahindraFDTransactionStatus b where b.customerId= :customerid")
	public List<MahindraFDTransactionStatus> getTransactionList(@Param("customerid") String customerid );
	
	public List<MahindraFDTransactionStatus> findAllByCustomerId(String customerid);
}
