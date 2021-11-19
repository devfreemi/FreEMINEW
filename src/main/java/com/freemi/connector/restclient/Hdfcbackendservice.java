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
import com.freemi.entity.general.Hdfccity;
import com.freemi.entity.general.Hdfcpincode;
import com.freemi.entity.general.Searchlocationdetials;
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
    
    
    public Hdfccity[] cityGet(Map<String, String> params,String stateid) {
    	final String url = env.getProperty(CommonConstants.HDFC_BACKEND_SERVICE_BASEURL_PROPERTIES)+"/get-state-cities/"+stateid;
		RestTemplate restTemplate = new RestTemplate();
		
		Hdfccity[] citylist = null;
		
		try {
		    HttpHeaders headers = new HttpHeaders();	
//		    setcommonheaderdetails(headers);
		    
		    HttpEntity<String> entity = new HttpEntity<String>(params.get("state"), headers);
		    
		    logger.info("Calling URL - "+ url);
		    citylist =  restTemplate.postForObject(url, entity, Hdfccity[].class);
		    
		}catch(Exception e) {
		    logger.error("Error processing request..",e);
		}
		return citylist;
    }
    
    public Hdfcpincode[] getcitypincode(Searchlocationdetials data, Map<String, String> params,String stateid, String cityid) {
    	final String url = env.getProperty(CommonConstants.HDFC_BACKEND_SERVICE_BASEURL_PROPERTIES)+"/get-state-cities-pincode";
		RestTemplate restTemplate = new RestTemplate();
		logger.info("Connecting URL- "+ url);
		Hdfcpincode[] pincodelist = null;
		try {
		    HttpHeaders headers = new HttpHeaders();	
		    headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
//		    setcommonheaderdetails(headers);
		    
		    ObjectMapper objectMapper = new ObjectMapper();
		    String jsondata  = objectMapper.writeValueAsString(params);
		    logger.info("JSON- "+ jsondata);
//		    logger.info("Calling URL - "+ url);
		    HttpEntity<String> entity = new HttpEntity<String>(jsondata, headers);
		    pincodelist =  restTemplate.postForObject(url, entity, Hdfcpincode[].class);
		    logger.info("Total results- "+ pincodelist.length);
		   
		    
		    
		}catch(Exception e) {
		    logger.error("Error processing request..",e);
		}
		return pincodelist;
    }
    
    
	/*
	 * @Cacheable(value = "searchstring", unless = "#result == null") public
	 * Hdfcemployerlist[] getemployerlist(Map<String, String> params, String
	 * searchstring) { final String url =
	 * env.getProperty(CommonConstants.HDFC_BACKEND_SERVICE_BASEURL_PROPERTIES)+
	 * "/get-employer-list"; RestTemplate restTemplate = new RestTemplate();
	 * logger.info("Connecting URL- "+ url); Hdfcemployerlist[] hdfcemployerlist =
	 * null; try { HttpHeaders headers = new HttpHeaders();
	 * headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE); //
	 * setcommonheaderdetails(headers);
	 * 
	 * ObjectMapper objectMapper = new ObjectMapper(); String jsondata =
	 * objectMapper.writeValueAsString(params); logger.info("JSON- "+ jsondata);
	 * HttpEntity<String> entity = new HttpEntity<String>(jsondata, headers);
	 * logger.info("Calling URL - "+ url); hdfcemployerlist =
	 * restTemplate.postForObject(url, entity, Hdfcemployerlist[].class);
	 * logger.info("Total results- "+ hdfcemployerlist.length);
	 * 
	 * }catch(Exception e) { logger.error("Error processing request..",e); } return
	 * hdfcemployerlist; }
	 */
    
    
    
    private HttpHeaders setcommonheaderdetails(HttpHeaders headers) {

    	headers.set("content-Type", MediaType.APPLICATION_JSON_VALUE);
    	return headers;
    }
	
}
