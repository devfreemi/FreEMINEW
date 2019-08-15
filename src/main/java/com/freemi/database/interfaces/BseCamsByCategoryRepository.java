package com.freemi.database.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.freemi.entity.investment.MFCamsValueByCategroy;

public interface BseCamsByCategoryRepository extends JpaRepository<MFCamsValueByCategroy,String>{
	
	public List<MFCamsValueByCategroy> findAllByPan(String pan);
	
	@Query("select p.folioNumber from MFCamsValueByCategroy p where p.amcShort= :amcShortName and p.pan= :panNo")
	public List<String> getSelectedPortFolio(@Param("amcShortName") String amcShortCode, @Param("panNo") String pan);
	
	
	
}
