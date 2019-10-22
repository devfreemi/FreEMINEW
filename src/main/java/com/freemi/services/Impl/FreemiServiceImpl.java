package com.freemi.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freemi.entity.database.FreemiLoanQuery;
import com.freemi.services.interfaces.FreemiLoanQueryService;
import com.freemi.services.interfaces.FreemiServiceInterface;

@Service
public class FreemiServiceImpl implements FreemiServiceInterface{
	
	@Autowired
	private FreemiLoanQueryService freemiLoanQueryService;
	

	@Override
	public boolean savePersonalLoanQuery(FreemiLoanQuery loanForm) {
		return freemiLoanQueryService.saveQueryRequest(loanForm);
	}

	@Override
	public boolean saveHomeLoanQuery(FreemiLoanQuery loanForm) {
		return freemiLoanQueryService.saveQueryRequest(loanForm);
	}

	@Override
	public boolean saveCreditCardLoanQuery(FreemiLoanQuery loanForm) {
		return freemiLoanQueryService.saveQueryRequest(loanForm);
	}

}
