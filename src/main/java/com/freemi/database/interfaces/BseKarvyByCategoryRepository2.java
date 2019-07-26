package com.freemi.database.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.freemi.entity.investment.MFKarvyValueByCategory2;

public interface BseKarvyByCategoryRepository2 extends JpaRepository<MFKarvyValueByCategory2,String>{
	
//	public List<MFKarvyValueByCategory2> getAllByPan(String pan);
	
	
	@Query("select v from MFKarvyValueByCategory2 v where v.pan= :pan order by v.productCode,v.folioNo,v.schemeCode,v.transactionDate asc")
	public List<MFKarvyValueByCategory2> getAllByPan(@Param("pan") String pan);
}
