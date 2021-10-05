package com.freemi.connector.restclient;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.freemi.common.util.CommonConstants;
import com.freemi.entity.loan.Trackloanstatusapiresponse;

@Service
public class Hdfcbackendservice {

	private static final Logger logger = LogManager.getLogger(Hdfcbackendservice.class);

    @Autowired
    private Environment env;
    
    public List<String[]> getloanrequestslist(Map<String, String> requestdata){
    	
    	final String url = env.getProperty(CommonConstants.HDFC_BACKEND_SERVICE_BASEURL_PROPERTIES) + "/get-hdfc-loan-request-history";
    	logger.info("HDFC getloanrequestslist(): "+ url);
    	RestTemplate restTemplate = new RestTemplate();
    	List<String[]> requesthistory = null;
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    	    HttpHeaders headers = new HttpHeaders();	
    	    setcommonheaderdetails(headers);
    	    
    	    String formdata= mapper.writeValueAsString(requestdata);
    	    logger.info("getloanrequestslist(): Request payload "+ formdata);
    	    HttpEntity<String> entity = new HttpEntity<String>(formdata,headers);
    	    
    	    if(env.getProperty(CommonConstants.HDFC_BACKEND_SERVICE_TEST_ENABLED).equals("N")) {
    	    	requesthistory =  restTemplate.postForObject(url, entity, List.class);
    	    }
    	    
//    	    logger.info("submitloanapplication(): Loan request response- "+ mapper.writeValueAsString(requesthistory));
    	    
    	}catch(Exception e) {
    	    logger.error("submitloanapplication(): submitloanapplicationError processing request..",e);
    	}
    	return requesthistory;
    	
    	
    }
    
    

    public Trackloanstatusapiresponse trackloanstatus(Map<String, String> requestdata){
    	
    	final String url = env.getProperty(CommonConstants.HDFC_BACKEND_SERVICE_BASEURL_PROPERTIES) + "/track-loan-status";
    	logger.info("HDFC trackloanstatus(): "+ url);
    	RestTemplate restTemplate = new RestTemplate();
    	Trackloanstatusapiresponse response = new Trackloanstatusapiresponse();
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    	    HttpHeaders headers = new HttpHeaders();	
    	    setcommonheaderdetails(headers);
    	    
    	    String formdata= mapper.writeValueAsString(requestdata);
    	    logger.info("gethdfcloanstatus(): Request payload "+ formdata);
    	    HttpEntity<String> entity = new HttpEntity<String>(formdata,headers);
    	    
    	    if(env.getProperty(CommonConstants.HDFC_BACKEND_SERVICE_TEST_ENABLED).equals("N")) {
    	    	response =  restTemplate.postForObject(url, entity, Trackloanstatusapiresponse.class);
    	    }
    	    
    	}catch(Exception e) {
    	    logger.error("HDFC trackloanstatus(): submitloanapplicationError processing request..",e);
    	    response.setApplicationstatus("Failed to connect service. Please try after sometime");
    	    response.setApplicationstatus("Failure");
    	}
    	return response;
    	
    	
    }
    
    
    
    private HttpHeaders setcommonheaderdetails(HttpHeaders headers) {

    	headers.set("content-Type", MediaType.APPLICATION_JSON_VALUE);
    	return headers;
    }
	
}
