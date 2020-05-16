package com.freemi.ui.restclient;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.freemi.common.util.CommonConstants;
import com.freemi.database.respository.mahindra.MahindraKycdocupdateform;
import com.freemi.entity.investment.mahindra.MFDSearch;
import com.freemi.entity.investment.mahindra.MFDSearchResult;
import com.freemi.entity.investment.mahindra.MahindraFDListItem;
import com.freemi.entity.investment.mahindra.MahindraResponse;
import com.freemi.entity.investment.mahindra.Mahindrapurchasehistory;
import com.freemi.services.interfaces.MahindraFDProfileService;
import com.google.gson.JsonObject;

@Service
public class MahindraFDClient implements MahindraFDProfileService {

    @Autowired
    Environment env;

    private static final String AUTH_TOKEN="Basic ZmRhZG1pbjpGcmVlbWkxMjM0";

    private static final Logger logger = LogManager.getLogger(MahindraFDClient.class);

    @Override
    public List<Mahindrapurchasehistory> getPurchaseHistory(String mobile, String pan) {

	final String url = env.getProperty(CommonConstants.URL_SERVICE_MAHINDRA_FD) + "/api/mahindrafdservice/get-purchase-history";
	logger.info(url);
	JsonObject form = new  JsonObject();
	RestTemplate restTemplate = new RestTemplate();
	List<Mahindrapurchasehistory> result =null;
	form.addProperty("mobile", mobile);
	try {

	    HttpHeaders headers = new HttpHeaders();	
	    setcommonheaderdetails(headers);
	    HttpEntity<String> entity = new HttpEntity<String>(form.toString(),headers);
	    Mahindrapurchasehistory[] r =  restTemplate.postForObject(url, entity, Mahindrapurchasehistory[].class);
	    if(r.length>0) {
		result= Arrays.asList(r);
	    }
	}catch(Exception e) {
	    logger.error("Error processing request..",e);
	}
	return result;

    }


    protected void setcommonheaderdetails(HttpHeaders headers) {
	headers.set("Authorization",AUTH_TOKEN);
	headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
    }


    @Override
    public List<MahindraFDListItem> getMahidraFdList(String mobile, String pan) {
	final String url = env.getProperty(CommonConstants.URL_SERVICE_MAHINDRA_FD) + "/api/mfdmaint/get-fd-portfolios";
	logger.info(url);
	
	List<MahindraFDListItem> res = null;
	try {
	    HttpHeaders headers = new HttpHeaders();	
	    setcommonheaderdetails(headers);
	    MFDSearch search = new MFDSearch();
//	    JsonObject form = new  JsonObject();
	    RestTemplate restTemplate = new RestTemplate();
//	    form.addProperty("mobileNumber", mobile);
	    
//	    form.addProperty("panNumber", pan);
	    search.setMobileNumber(mobile);
	    search.setPanNumber(pan);
	    
	    HttpEntity<Object> entity = new HttpEntity<Object>(search,headers);

	    MahindraFDListItem[] result =  restTemplate.postForObject(url, entity, MahindraFDListItem[].class);
	    if(result.length>0) {
		res = Arrays.asList(result);
	    }
	    logger.info("FD list respone received- " + result!=null?result.length:"NA");
	}catch(Exception e) {
	    logger.error("Error fetching FD purchase details",e);
	}
	return res;
    }


    @Override
    public MahindraResponse verifyPaymentStatus(String customerId, String mobile, String applicationNo,
	    String emailid) {
	return null;
    }


    @Override
    public MahindraResponse updatecustomerkycdocuments(MahindraKycdocupdateform docform) {
	final String url = env.getProperty(CommonConstants.URL_SERVICE_MAHINDRA_FD) + "/api/mfdmaint/update-kyc-documents";
	logger.info(url);
	
	MahindraResponse res = null;
	try {
	    HttpHeaders headers = new HttpHeaders();	
	    setcommonheaderdetails(headers);
	    RestTemplate restTemplate = new RestTemplate();
	    
	    HttpEntity<Object> entity = new HttpEntity<Object>(docform,headers);

	    res =  restTemplate.postForObject(url, entity,MahindraResponse.class);
	   
	    logger.info("FD list respone received- " + res.getStatusCode());
	}catch(Exception e) {
	    logger.error("Error fetching FD purchase details",e);
	    res = new MahindraResponse();
	    res.setStatusCode(CommonConstants.TASK_FAILURE);
	    res.setStatusMsg("SERVICE_NOT_RESPONDING");
	}
	return res;
    }


    @Override
    public MahindraResponse getlatestkycdocuments(String mobile) {
	final String url = env.getProperty(CommonConstants.URL_SERVICE_MAHINDRA_FD) + "/api/mfdmaint/get-uploaded-kyc-documents";
	logger.info(url);
	
	MahindraResponse res = null;
	try {
	    HttpHeaders headers = new HttpHeaders();	
	    setcommonheaderdetails(headers);
	    RestTemplate restTemplate = new RestTemplate();
	    JsonObject form = new  JsonObject();
	    form.addProperty("mobile", mobile);
	    
	    HttpEntity<Object> entity = new HttpEntity<Object>(form.toString(),headers);

	    res =  restTemplate.postForObject(url, entity,MahindraResponse.class);
	   
	    logger.info("FD KYC documents respone received- " + res.getStatusCode());
	}catch(Exception e) {
	    logger.error("Error fetching FD purchase details",e);
	    res = new MahindraResponse();
	    res.setStatusCode(CommonConstants.TASK_FAILURE);
	    res.setStatusMsg("SERVICE_NOT_RESPONDING");
	}
	return res;
    }

}
