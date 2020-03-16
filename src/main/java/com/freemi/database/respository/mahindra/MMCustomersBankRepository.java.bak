package com.freemi.database.respository.mahindra;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.freemi.entity.investment.mahindra.MahindraCustomerBankDetails;

@Repository
public interface MMCustomersBankRepository extends JpaRepository<MahindraCustomerBankDetails, Integer> {
	
	public boolean existsByMobile(String mobile);
	
	public boolean existsByCustomerIdAndIsActive(String customerId, String active);
	public List<MahindraCustomerBankDetails> findByCustomerIdAndIsActive(String customerId, String active);
	
	public MahindraCustomerBankDetails findOneByAccountNumber(String accountNumber);
	
	public boolean existsByBankDetailsId(String bankDetailsId);

}
