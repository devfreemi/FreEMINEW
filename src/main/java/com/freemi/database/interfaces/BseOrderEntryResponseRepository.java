package com.freemi.database.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.freemi.entity.investment.BseOrderEntryResponse;

public interface BseOrderEntryResponseRepository extends JpaRepository<BseOrderEntryResponse,Long>{
	
}
