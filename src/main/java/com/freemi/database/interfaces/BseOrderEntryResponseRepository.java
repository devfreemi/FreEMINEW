package com.freemi.database.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.freemi.entity.investment.BseOrderEntryResponse;

public interface BseOrderEntryResponseRepository extends JpaRepository<BseOrderEntryResponse,Long>{
	
	public List<BseOrderEntryResponse> findAllByClientCode(String clientID);
	
}
