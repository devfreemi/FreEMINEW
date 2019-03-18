package com.freemi.ui.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.freemi.common.util.CommonTask;
import com.freemi.database.service.FreemiServiceInterface;
import com.freemi.entity.database.FreemiLoanQuery;
import com.freemi.entity.general.ClientSystemDetails;

@RestController
public class MobileAppConnect {

	private static final Logger logger = LogManager.getLogger(MobileAppConnect.class);
	
	@Autowired
	FreemiServiceInterface freemiServiceInterface;
	
	@RequestMapping(value = "/api/saveloanquery", method = RequestMethod.POST)
	public String home(HttpServletRequest request, HttpServletResponse rsponse, Model model) {

		logger.info("Loan request received via API..");
		String result="";
		
		try{
			/*FreemiLoanQuery loanRequest = new ObjectMapper().readValue(request.getInputStream(), FreemiLoanQuery.class);
			System.out.println(loanRequest.getAgentCode());
			if(loanRequest.getAgentCode()!=null){
			ClientSystemDetails system= CommonTask.getClientSystemDetails(request);
			loanRequest.setRequestTime(new Date());
			loanRequest.setSystemIp(system.getClientIpv4Address());
			if(freemiServiceInterface.saveHomeLoanQuery(loanRequest)){
				result="SUCCESS";
			}else{
				result="FAILED";
			}
			}else{
				result="MANDATORY FIELDS MISSING";
			}*/
			return "SUCCESS";
			
		}catch(Exception e){
			logger.error("Error to save loan request received via api. "+ e.getMessage());
//			e.printStackTrace();
			result="ERROR";
		}
		
		return result;
	}
}
