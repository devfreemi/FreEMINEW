package com.freemi.database.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.freemi.entity.investment.MFKarvyValueByCategory;

public interface BseKarvyByCategoryRepository extends JpaRepository<MFKarvyValueByCategory,Integer>{
	
	public List<MFKarvyValueByCategory> getAllByPan(String pan);
}