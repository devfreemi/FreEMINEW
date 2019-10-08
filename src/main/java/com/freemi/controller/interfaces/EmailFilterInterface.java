package com.freemi.controller.interfaces;

import com.freemi.entity.database.EmailBounceReport;

public interface EmailFilterInterface {
	
	public void saveEmailBounceReport(EmailBounceReport emailBounceObj);
	
	public void addEMailToBounceList();

}
