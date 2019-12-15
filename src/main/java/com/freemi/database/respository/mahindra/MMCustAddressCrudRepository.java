package com.freemi.database.respository.mahindra;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.freemi.entity.investment.mahindra.MahindraCustomerAddress;

public interface MMCustAddressCrudRepository extends JpaRepository<MahindraCustomerAddress, Integer> {
	
	public boolean existsByMobile(String mobile);
	
	public boolean existsByCustomerIdAndIsActive(String customerId,String active);
	public MahindraCustomerAddress findOneByCustomerIdAndIsActive(String customerId,String active);
	public List<MahindraCustomerAddress> findAllByCustomerIdAndIsActive(String customerId, String active);
	public List<MahindraCustomerAddress> findAllByCustomerId(String customerId);
	
	public boolean existsByCustomerAddressId(String cusomerAddressId);

}
