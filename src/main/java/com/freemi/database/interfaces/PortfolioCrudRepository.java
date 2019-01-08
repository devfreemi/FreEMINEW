package com.freemi.database.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.freemi.entity.investment.MfPortfolio;

@Service
public interface PortfolioCrudRepository extends JpaRepository<MfPortfolio, Long> {
	
	
	@Query("select p.portfolioNumber from MfPortfolio p where p.amcCode= :amcCode and p.clientId= :clientId")
	public List<String> getSelectedPortFolio(@Param("amcCode") String amcCode, @Param("clientId") String clientId);

}
