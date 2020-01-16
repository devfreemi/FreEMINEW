package com.freemi.database.respository.mahindra;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.freemi.entity.investment.mahindra.Mahindrapurchasehistory;

public interface MMPurchaseHistoryRepository extends JpaRepository<Mahindrapurchasehistory, String> {
	
	public boolean existsByMobile(String mobile);
	
	public List<Mahindrapurchasehistory> findAllByMobile(String mobile);
	
	public List<Mahindrapurchasehistory> findAllByCustomerid(String customerid);
	
	
}
