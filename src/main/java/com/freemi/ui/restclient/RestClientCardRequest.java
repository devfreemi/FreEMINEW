package com.freemi.ui.restclient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.freemi.common.util.CommonConstants;
import com.google.gson.JsonObject;

@Service
public class RestClientCardRequest {
	private static final Logger logger = LogManager.getLogger(RestClientCardRequest.class);

	@Autowired
	private Environment env;

	public List<String> geticicicardrequesthistory(String mobileno){
		final String url = env.getProperty(CommonConstants.BASE_ICCS_URL)+"/get-icici-cc-request-history/"+mobileno;
		ResponseEntity<String[]> result = null;
		List<String> data =new ArrayList<String>();
		logger.info("Request URL- "+ url);
		RestTemplate restTemplate = new RestTemplate();
		JsonObject form = new  JsonObject();
		
		try {
		form.addProperty("mobile", mobileno);
		HttpHeaders headers = new HttpHeaders();
		setcommonheaderdetails(headers);
		
		HttpEntity<String> entity = new HttpEntity<String>(form.toString(),headers);
		result= restTemplate.postForEntity(url, entity, String[].class);
		
		String[] objects = result.getBody();
		logger.info("Total return data- "+ objects.length);
		if(objects.length>0) {
			data = Arrays.asList(objects);
			logger.info(data);
		}
		}catch(Exception e) {
			logger.error("Error requesting card request history",e);
		}
		
		return data;
	}




	protected void setcommonheaderdetails(HttpHeaders headers) {
		headers.set("Authorization",env.getProperty(CommonConstants.ICCS_AUTH_TOKEN));
		headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
	}

}
