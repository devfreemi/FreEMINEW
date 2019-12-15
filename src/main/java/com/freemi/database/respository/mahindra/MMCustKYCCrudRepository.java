package com.freemi.database.respository.mahindra;

import org.springframework.data.jpa.repository.JpaRepository;

import com.freemi.entity.investment.mahindra.MahindraCustomerKYCDetails;

public interface MMCustKYCCrudRepository extends JpaRepository<MahindraCustomerKYCDetails, Integer> {
	
	public boolean existsByMobile(String mobile);
	
	public boolean existsByKycid(String kycid);
	
	public boolean existsByCustomerIdAndActive(String mobile,String activeStatus);

}
