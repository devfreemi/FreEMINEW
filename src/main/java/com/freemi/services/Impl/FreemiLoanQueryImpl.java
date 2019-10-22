package com.freemi.services.Impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freemi.database.interfaces.FreemiLoanQueryRepository;
import com.freemi.entity.database.FreemiLoanQuery;
import com.freemi.services.interfaces.FreemiLoanQueryService;

@Service
public class FreemiLoanQueryImpl implements FreemiLoanQueryService{
	private static final Logger logger = LogManager.getLogger(FreemiLoanQueryImpl.class);
	
	@Autowired
	private FreemiLoanQueryRepository freemiLoanQueryRepository;
	
	public boolean saveQueryRequest(FreemiLoanQuery loanForm){
		boolean flag = true;
		try{
			freemiLoanQueryRepository.save(loanForm);
			logger.info("Loan query committed");
		}catch(Exception e){
			logger.error("Error saving loan query data-"+ e.getMessage());
			flag=false;
		}
		
		return flag;
	}

}
