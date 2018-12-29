package com.freemi.repository.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.freemi.entity.database.UserBankDetails;

@Service
public interface BseCustomerBankDetailsCrudRespository extends JpaRepository<UserBankDetails, Long>{

	public List<UserBankDetails> getByClientID(String customerId);
}
