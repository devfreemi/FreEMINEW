package com.freemi.controller.interfaces;

import org.springframework.stereotype.Component;

import com.freemi.entity.database.FreemiLoanQuery;

@Component
public interface FreemiServiceInterface {
	
	public boolean savePersonalLoanQuery(FreemiLoanQuery loanForm);
	public boolean saveHomeLoanQuery(FreemiLoanQuery loanForm);
	public boolean saveCreditCardLoanQuery(FreemiLoanQuery loanForm);

}
