package com.freemi.database.respository.mahindra;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.freemi.entity.investment.mahindra.MahindraNominee;

public interface MMCustNomineeCrudRepository extends JpaRepository<MahindraNominee, Integer> {
	
//	public boolean existsByMobile(String mobile);
	public boolean existsByCustomerIdAndFnameAndActive(String customId,String fname, String active);
	public boolean existsByCustomerIdAndActive(String customId, String active);
	public boolean existsByNomineeDetailsId(String customId);
	List<MahindraNominee> findAllByCustomerIdAndFnameAndActive(String customerId,String fname, String active);
	List<MahindraNominee> findAllByCustomerIdAndActive(String customerId, String active);
	public MahindraNominee findObeByCustomerIdAndActive(String nomineeDetailsId, String active);
	
	/*
	 * public boolean existsByCustomerIdAndIsActive(String customerId,String
	 * active); public MahindraCustomerAddress findOneByCustomerIdAndIsActive(String
	 * customerId,String active); public List<MahindraCustomerAddress>
	 * findAllByCustomerIdAndIsActive(String customerId, String active); public
	 * List<MahindraCustomerAddress> findAllByCustomerId(String customerId);
	 * 
	 * public boolean existsByCustomerAddressId(String cusomerAddressId);
	 */

}
