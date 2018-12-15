package com.freemi.repository.implementations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freemi.database.service.FreemiLoanQueryService;
import com.freemi.entity.database.FreemiLoanQuery;
import com.freemi.repository.interfaces.FreemiLoanQueryRepository;

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
