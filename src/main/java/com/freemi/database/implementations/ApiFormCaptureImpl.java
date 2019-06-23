package com.freemi.database.implementations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freemi.controller.interfaces.ApiFormCaptureInterface;
import com.freemi.database.interfaces.BlogAdvisorFormCrudRepository;
import com.freemi.entity.database.BlogAdvisorForm;

@Service
public class ApiFormCaptureImpl implements ApiFormCaptureInterface {

	private static final Logger logger = LogManager.getLogger(ApiFormCaptureImpl.class);
	
	@Autowired
	BlogAdvisorFormCrudRepository BlogAdvisorFormCrudRepository;
	
	
	
	@Override
	public String captureAdvisorSupport(BlogAdvisorForm form) {
		
		String response = "SUCCESS";
		try{
			BlogAdvisorFormCrudRepository.save(form);
		}catch(Exception e){
			logger.error("captureAdvisorSupport(): Error Saving data to database",e);
			response = "ERROR";
		}
		
		return response;
	}

}
