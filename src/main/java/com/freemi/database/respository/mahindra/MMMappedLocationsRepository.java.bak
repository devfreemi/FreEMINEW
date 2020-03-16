package com.freemi.database.respository.mahindra;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.freemi.entity.investment.mahindra.MahindraFDMappedLocations;

public interface MMMappedLocationsRepository extends JpaRepository<MahindraFDMappedLocations, Integer> {
	
	public List<MahindraFDMappedLocations> findAllByStatecode(String statecode);

}
