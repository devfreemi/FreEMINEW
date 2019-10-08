package com.freemi.ui.controller.api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.freemi.common.util.CommonConstants;
import com.freemi.common.util.CommonTask;
import com.freemi.controller.interfaces.EmailFilterInterface;
import com.freemi.entity.database.EmailBounceReport;

@RestController
public class EmailBasedOperations {
	
	@Autowired
	EmailFilterInterface emailFilterInterface;
	

	private static final Logger logger = LogManager.getLogger(EmailBasedOperations.class);


	@PostMapping(value="/api/aws/awssnsreport")
	@CrossOrigin(origins="*")
	public String awsSNSReport(/*@RequestBody String blogData, BindingResult result,*/ HttpServletRequest request, HttpServletResponse response){
		logger.info("Email bounce report received..");
		Map<String, String> map = new HashMap<String, String>();
		String body = null;

		try {

			Enumeration headerNames = request.getHeaderNames();
			while (headerNames.hasMoreElements()) {
				String key = (String) headerNames.nextElement();
				String value = request.getHeader(key);

				//				logger.info("Header: "+  key + " ->"+ value);
				map.put(key, value);
			}

			body = request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);
			logger.info("awsSNSReport(): Data in request body " + body);
		}catch(Exception e) {
			logger.error("Error reading headers/body",e);
		}


		if(map.containsKey("x-amz-sns-message-type")) {
			logger.info("awsSNSReport(): SNS report type - "+ map.get("x-amz-sns-message-type"));

			if(map.get("x-amz-sns-message-type").equalsIgnoreCase("Bounce"))
			{
				logger.info("awsSNSReport(): Mail bounce report received. Add to invalid list of accounts...");

				emailBounceReportProcess(body);
			}
		}else {
			logger.info("awsSNSReport(): x-amz-sns-message-type is not found in request header...");
		}


		return "RECEIVED";


	}


	private void emailBounceReportProcess(String body) {
		
		EmailBounceReport bounceMailData = null;
		try{

			//					JsonObject jsonObj = (JsonObject) new JsonParser().parse(body);
			//					logger.info("Value- "+ jsonObj.get("emailAddress"));
			Map<String, Object> jsonDocument = new ObjectMapper().readValue(body, new TypeReference<Map<String, Object>>() {});

			if(jsonDocument.containsKey("notificationType")) {

				Map<String, Object> bounceObject = (Map<String, Object>) jsonDocument.get("bounce");
				Map<String, Object> mailData = (Map<String, Object>) jsonDocument.get("mail");

				List<Object> bouncedRecipientsList = (List<Object>) bounceObject.get("bouncedRecipients");
				logger.info("bouncedRecipients size- "+ bouncedRecipientsList.size());
				//						List<Object> resultsList = (List<Object>) jsonDocument2.get("bouncedRecipients");

				for(int i=0;i<bouncedRecipientsList.size();i++) {
					Map<String, Object> resultsMap = (Map<String, Object>) bouncedRecipientsList.get(i);
					bounceMailData = new EmailBounceReport();
					String emailAddress = (String) resultsMap.get("emailAddress");
					logger.info("Add Bounced Email Address to reject list - "+ emailAddress);
					
					bounceMailData.setNotificationType(jsonDocument.get("notificationType").toString());
					
					bounceMailData.setBouncedMailId(resultsMap.get("emailAddress").toString());
					bounceMailData.setErrorCode(resultsMap.get("status").toString());
					bounceMailData.setMailTriggerSourceIp(mailData.get("sourceIp").toString());
					bounceMailData.setSourceMailId(mailData.get("source").toString());
					bounceMailData.setTimestamp(CommonTask.dateFormatter1(CommonConstants.TIMESTAMP_FORMAT_SNS_REPORT ,mailData.get("timestamp").toString()));
					
//					Call to save
					emailFilterInterface.saveEmailBounceReport(bounceMailData);
					

				}

			}else {
				logger.info("bounce Key not found...");
			}

		}catch(Exception e){
			logger.error("awsSNSReport(): Error reading body..",e);
		}
	}
	
	/*public static void main(String[] args) throws ParseException {
		
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
//		Date date = new Date("2019-10-05T21:55:29.891Z");
		Date date = dateFormat.parse("2019-10-05T21:55:29.891Z");
		System.out.println(date);
	}*/

}
