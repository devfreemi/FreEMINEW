package com.freemi.database.respository.mahindra;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.freemi.entity.investment.mahindra.MahindraSchemesList;

public interface MMSchemesCrudRepository extends JpaRepository<MahindraSchemesList, Integer> {
	
	public List<MahindraSchemesList> findAllBySchemetypecodeAndCustomercategoryAndActiveinactiveflagAndFundactive(String schemeTypeCode,String customerCategory,String mmfdActiveFlag, String freemiactiveflag );
	
	public List<MahindraSchemesList> findAllByTenuremonthtoAndSchemetypecodeAndCustomercategory(String tenure, String schemeTypeCode,String customerCategory );
	
	
}
