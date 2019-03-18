package com.freemi.controller.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freemi.database.service.FreemiLoanQueryService;
import com.freemi.database.service.FreemiServiceInterface;
import com.freemi.entity.database.FreemiLoanQuery;

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
