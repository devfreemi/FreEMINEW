package com.freemi.services.interfaces;

import org.springframework.stereotype.Service;

import com.freemi.entity.general.Datarquestresponse;

@Service
public interface HdfcService {

	public Datarquestresponse getrequestedloanhistory(String mobile, String userid, String starttime, String endtime);
	
	public Object getloanstatus(String mobile, String loanreferenceid, String ackno, String loanrequeststatus, String bank);
}
