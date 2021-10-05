package com.freemi.services.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freemi.connector.restclient.Hdfcbackendservice;
import com.freemi.entity.general.Datarquestresponse;
import com.freemi.entity.loan.Trackloanstatusapiresponse;
import com.freemi.services.interfaces.HdfcService;

@Service	
public class HdfcServiceImpl implements HdfcService {

	private static final Logger logger = LoggerFactory.getLogger(HdfcServiceImpl.class);
	
	@Autowired
	Hdfcbackendservice hdfcbackendservice;
	
	@Override
	public Datarquestresponse getrequestedloanhistory(String mobile, String userid, String starttime, String endtime) {
		Datarquestresponse response = new Datarquestresponse();
		List<String[]> result = null;
		try {
			logger.info("Request received to fetch HDFC loan application history for mobile- " + mobile);
			Map<String, String> data = new HashMap<String, String>();
			data.put("mobile", mobile);
			result = hdfcbackendservice.getloanrequestslist(data);
			
			if(result!=null && result.size()>0) {
				response.setStatus("1");
			}else if(result!=null && result.size() == 0) {
				response.setStatus("1");
				response.setMsg("NO_DATA");
			} else {
				response.setStatus("0");
				response.setMsg("SERVICE_CONN_FAILURE");
			}
			response.setData(result);
			
		}catch(Exception e) {
			logger.info("Failed to process hdfc loan request history fetch",e);
			response.setStatus("0");
			response.setMsg("INTERNAL_ERROR");
		}
		
		return response;
	}

	@Override
	public Object getloanstatus(String mobile, String loanreferenceid, String ackno, String loanrequeststatus, String bank) {
		
		Trackloanstatusapiresponse response = new Trackloanstatusapiresponse();
		try {
			logger.info("Request received to fetch HDFC loan application history for mobile- " + mobile);
			Map<String, String> data = new HashMap<String, String>();
			data.put("mobile", mobile);
			data.put("refno", loanreferenceid);
			if(bank.equalsIgnoreCase("HDFC")) {
				response = hdfcbackendservice.trackloanstatus(data);
			}else {
				logger.info("Passed bank details not supported- "+ bank);
				response.setApplicationstatus("Invalid bank reference. Request not processed");
			}
		}catch(Exception e) {
			logger.info("Failed to process hdfc loan request history fetch",e);
			response.setApplicationstatus("Failed to process request. Kindly contact admin if issue persist.");
		}
		
		return response;
	}

}
