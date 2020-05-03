package com.freemi.database.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.freemi.entity.investment.Allotmentstatement;

public interface AllotmentStmntCrudRepository extends JpaRepository<Allotmentstatement,Integer>{
	
    	public boolean existsByRtatransno(String rtatransno );
	
}
