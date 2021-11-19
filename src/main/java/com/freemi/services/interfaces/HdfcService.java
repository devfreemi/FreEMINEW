package com.freemi.services.interfaces;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.freemi.entity.general.Datarquestresponse;
import com.freemi.entity.general.Searchlocationdetials;
import com.freemi.entity.general.Select2Results;

@Service
public interface HdfcService {

	public Datarquestresponse getrequestedloanhistory(String mobile, String userid, String starttime, String endtime);
	
	public Object getloanstatus(String mobile, String loanreferenceid, String ackno, String loanrequeststatus, String bank);
	
	public List<Select2Results> searchcity(Map<String, String> filters, String stateid);
	
	public Map<String, String> searchcity2(Map<String, String> filters, String stateid);
	
	public List<Select2Results> searchcitypincode(Searchlocationdetials data, Map<String, String> filters,
			String stateid, String cityid);
	
	public Map<String, String> searchcitypincode2(Searchlocationdetials data, Map<String, String> filters,
			String stateid, String cityid);
}
