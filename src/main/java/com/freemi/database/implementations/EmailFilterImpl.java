package com.freemi.database.implementations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.freemi.controller.interfaces.EmailFilterInterface;
import com.freemi.database.interfaces.EmailBounceReportCrudRepository;
import com.freemi.entity.database.EmailBounceReport;

@Component
public class EmailFilterImpl implements EmailFilterInterface {
	
	@Autowired
	EmailBounceReportCrudRepository emailBounceReportCrudRepository;
	
	private static final Logger logger = LogManager.getLogger(EmailFilterImpl.class);

	@Override
	public void saveEmailBounceReport(EmailBounceReport emailBounceObj) {
	
		logger.info("saveEmailBounceReport(): Saving email bounce report to database");
		try {
			emailBounceReportCrudRepository.save(emailBounceObj);
			
		}catch(Exception e) {
			logger.error("saveEmailBounceReport(): Error saving to database",e);
		}
		
	}

	@Override
	public void addEMailToBounceList() {
		// TODO Auto-generated method stub
		
	}

}
