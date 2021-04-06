package com.freemi.ui.restclient;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RestclientSmsSenderImpl {
	private static final Logger logger = LogManager.getLogger(RestclientSmsSenderImpl.class);

	@Autowired
	private Environment env;

	public String sendsmsviaflow(String mobileno,String flowid, String senderid, Map<String, String> msgdata, String authkey, String other1, String other2) {

		try {
			String checksmsenabledstatus=env.getProperty("sms.server.send.enabled");
			if(checksmsenabledstatus!=null && checksmsenabledstatus.equals("Y")) {

				logger.info("Requesting sms for flowid- "+ flowid + " ->mobile -> " + mobileno);
				final String url = env.getProperty("sms.server.baseurl");
				logger.info("Reuqesting url- "+ url);
				RestTemplate restTemplate = new RestTemplate();

				msgdata.put("flow_id", flowid);
				msgdata.put("mobiles", ("91"+mobileno));
				msgdata.put("sender", env.getProperty("sms.sender.id"));
				
				ObjectMapper mapper = new ObjectMapper();
				String formdata= mapper.writeValueAsString(msgdata);

				HttpHeaders headers = new HttpHeaders();	
				headers.set("authkey", env.getProperty("sms.provider.authkey"));
				headers.set("content-type", "application/JSON");

				HttpEntity<String> entity = new HttpEntity<String>(formdata,headers);

				ResponseEntity<String> response = null;
				response = restTemplate.postForEntity(url, entity, String.class);
				logger.info("Response from logging api - "+ response.getHeaders() + " -> "+ response.getBody());
				//			System.out.println("Response from logging api - "+ response.getHeaders() + " -> "+ response.getBody());
			}else {
				logger.info("SMS sending is disabled... Skipping SMS sending trigger");
			}
		}catch(Exception e) {
			logger.error("Failed to send SMS",e);
		}

		return null;
	}

	/*
	 * public static void main(String[] args) {
	 * 
	 * RestclientSmsSenderImpl s = new RestclientSmsSenderImpl();
	 * 
	 * Map<String, String> data= new HashMap<String, String>(); data.put("otp",
	 * "123456"); data.put("var", "5"); s.sendsmsviaflow("9051472645",
	 * "606aefefd9d15c5e4515a4e0", "FREEMI", data, null,null, null); InetAddress g;
	 * try { g = InetAddress.getLocalHost(); System.out.println(g.getHostAddress());
	 * } catch (UnknownHostException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * System.out.println("Complete...");
	 * 
	 * }
	 */
}
