package com.freemi.repository.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.freemi.entity.investment.BseMFInvestForm;

public interface BseCustomerCrudRespository extends JpaRepository<BseMFInvestForm, Long>{

	public List<BseMFInvestForm> getByClientID(String customerId);
}
