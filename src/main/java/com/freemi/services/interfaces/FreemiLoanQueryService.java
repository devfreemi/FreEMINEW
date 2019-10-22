package com.freemi.services.interfaces;

import org.springframework.stereotype.Service;

import com.freemi.entity.database.FreemiLoanQuery;

@Service
public interface FreemiLoanQueryService {
	
	public boolean saveQueryRequest(FreemiLoanQuery loanForm);

}
