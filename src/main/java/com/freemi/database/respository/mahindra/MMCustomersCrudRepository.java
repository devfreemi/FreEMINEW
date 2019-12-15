package com.freemi.database.respository.mahindra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.freemi.entity.investment.mahindra.MahindraCustomerDetails;

public interface MMCustomersCrudRepository extends JpaRepository<MahindraCustomerDetails, Integer> {
	
	public boolean existsByMobile(String mobile);
	
	public MahindraCustomerDetails findOneByMobile(String mobile);
	
	@Query("select c.customerId from MahindraCustomerDetails c where c.mobile= :mobile")
	public String getCustomerIdromMobile(@Param("mobile") String mobile);

}
