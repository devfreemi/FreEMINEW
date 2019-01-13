package com.freemi.database.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.freemi.entity.investment.BseAllTransactionsView;

public interface BseTransactionsView extends JpaRepository<BseAllTransactionsView, String> {
	
	public List<BseAllTransactionsView> findAllByClientID(String clientid);
	
	public BseAllTransactionsView findOneByPortfoilioAndSchemeCodeAndClientIDAndInvestType(String portfolio, String schemeCode, String clientId, String investType);

}
