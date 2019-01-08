package com.freemi.database.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.freemi.entity.investment.PanValidationStatus;

@Repository
public interface KycStatusRepository extends JpaRepository<PanValidationStatus, Integer> {
	
	public List<PanValidationStatus> findByPanNumber(String panNumber);

}
