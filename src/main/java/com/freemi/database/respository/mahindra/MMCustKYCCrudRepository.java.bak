package com.freemi.database.respository.mahindra;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.freemi.entity.investment.mahindra.MahindraCustomerKYCDetails;

public interface MMCustKYCCrudRepository extends JpaRepository<MahindraCustomerKYCDetails, Integer> {
	
	public boolean existsByMobile(String mobile);
	
	public boolean existsByKycid(String kycid);
	
	public boolean existsByCustomerIdAndActive(String mobile,String activeStatus);
	
	public MahindraCustomerKYCDetails findOneByCustomerIdAndActive(String custoemrid,String activeStatus);
	
	@Query(value = "select m.kycFatherNamePrefix,m.kycFatherFirstName,m.kycFatherMiddleName,m.kycFatherLastName,m.kycMotherNamePrefix,m.kycMotherFirstName,m.kycMotherMiddletName,m.kycMotherLastName from MahindraCustomerKYCDetails m where m.customerId= :customerid and m.active='Y'")
	public List<Object[]> getCustomerKycDetails(@Param("customerid") String customerid);

}
