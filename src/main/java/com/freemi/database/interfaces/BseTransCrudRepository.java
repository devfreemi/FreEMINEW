package com.freemi.database.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.freemi.entity.investment.SelectMFFund;

public interface BseTransCrudRepository extends JpaRepository<SelectMFFund, Long> {
	
	public List<SelectMFFund> getByClientID(String customerId);
	public boolean existsByTransactionID(String generatedId);
}
