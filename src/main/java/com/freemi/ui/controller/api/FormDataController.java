package com.freemi.ui.controller.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.freemi.entity.database.EmailBounceReport;
import com.freemi.services.interfaces.ApiFormCaptureInterface;
import com.freemi.services.interfaces.MailSenderInterface;

@RestController
@RequestMapping("/api")
public class FormDataController {

	@Autowired
	ApiFormCaptureInterface apiFormCaptureInterface;
	
	@Autowired
	MailSenderInterface mailSenderInterface;

	private static final Logger logger = LogManager.getLogger(FormDataController.class);


	@PostMapping(value="/blog/advisorsupport")
	@CrossOrigin(origins="*")
	@ResponseBody
	public String captureAdvisorSupport(@RequestBody String blogData, BindingResult result, HttpServletRequest request, HttpServletResponse httpResponse){
		logger.info("Request received to process Form data");
		String response ="SUCCESS";
		//		BlogAdvisorForm blogForm = null;

		/*if(result.hasErrors()){
			response = result.getFieldError().getDefaultMessage();
		}else{
			try {
				//			blogForm = new ObjectMapper().readValue(request.getInputStream(), BlogAdvisorForm.class);
				response= apiFormCaptureInterface.captureAdvisorSupport(blogForm);
			} catch (Exception e) {
				logger.error("captureAdvisorSupport() Error in api submit reqest- ",e);
				response = "ERROR";
			}
		}*/

		try{
			if(blogData!=null){
				String[] arr = blogData.split("&");
				if(arr.length>0){
					for(int i=0;i<arr.length;i++){
						if(arr[i].contains("="))
							System.out.println(arr[i].split("=")[0] + " --> "+ arr[i].split("=")[1]);
					}
				}

			}

		}catch(Exception e){
			logger.error("Error reading data..",e);
		}

		System.out.println("Body - "+ blogData);

		return response;
	}



	/*@PostMapping(value="/api/blog/advisorsupport")
	@CrossOrigin(origins="*")
	@ResponseBody
	public String captureAdvisorSupport(@RequestBody @Valid BlogAdvisorForm blogForm, BindingResult result, HttpServletRequest request, HttpServletResponse httpResponse){
		logger.info("Request received to process Form data");
		String response ="SUCCESS";
		//		BlogAdvisorForm blogForm = null;

		if(result.hasErrors()){
			response = result.getFieldError().getDefaultMessage();
		}else{
			try {
				//			blogForm = new ObjectMapper().readValue(request.getInputStream(), BlogAdvisorForm.class);
				response= apiFormCaptureInterface.captureAdvisorSupport(blogForm);
			} catch (Exception e) {
				logger.error("captureAdvisorSupport() Error in api submit reqest- ",e);
				response = "ERROR";
			}
		}

		return response;
	}*/


	@RequestMapping(value = "/aws-mail-bounce-report", method = RequestMethod.POST)
	@ResponseBody
	public HttpStatus awsbouncereport(HttpServletRequest request, HttpServletResponse response) {
		logger.info("@@@@ AWS mail send failure trigger @@@@");

		try {
			logger.info("AWS mail notificaiton details: x-amz-sns-message-type- "+ request.getHeader("x-amz-sns-message-type") + " : "+ request.getHeader("x-amz-sns-message-id") + " : " +   request.getHeader("User-Agent"));
			final String json = IOUtils.toString(request.getInputStream());	
			logger.info("Request body -" + json);

			//Get the message type header.
			String messagetype = request.getHeader("x-amz-sns-message-type");

			// Process the message based on type.
			if (messagetype.equalsIgnoreCase("Notification")) {
//				ObjectMapper objectMapper = new ObjectMapper();
//				objectMapper.configure(Feature.AUTO_CLOSE_SOURCE, true);
				
//				Awsmailbounce user = objectMapper.readValue(request.getInputStream(), Awsmailbounce.class);
				
				JSONObject obj = new JSONObject(json);
//		        String pageName = obj.getJSONObject("notificationType").getString("emailAddress");
				String notificationType = obj.getString("notificationType");
//		        System.out.println(notificationType);

				if(notificationType.equalsIgnoreCase("Bounce")) {
					String jsondata;
					EmailBounceReport bouncereport = new EmailBounceReport();
					logger.info("Logging mail bounce report to DB...");
					jsondata = obj.getJSONObject("bounce").getString("bounceType");
			        bouncereport.setBouncetype(jsondata);
			        jsondata = obj.getJSONObject("bounce").getString("bounceSubType");
			        bouncereport.setBouncesubtype(jsondata);
			        
			        JSONArray pageName1 = obj.getJSONObject("bounce").getJSONArray("bouncedRecipients");
			        bouncereport.setBouncedmailid(pageName1.getJSONObject(0).getString("emailAddress").toLowerCase());
			        bouncereport.setErrorcode(pageName1.getJSONObject(0).getString("status"));
			        
			        bouncereport.setSourcemailid(obj.getJSONObject("mail").getString("source"));
			        bouncereport.setMailtriggersourceip(obj.getJSONObject("mail").getString("sourceIp"));
			        bouncereport.setAwsaccountid(obj.getJSONObject("mail").getString("sendingAccountId"));
					
			        mailSenderInterface.emailbouncereportcapture(bouncereport);
			        logger.info("AWS Bounce report capture complete...");
			        
				}
				
				
			} else if (messagetype.equals("SubscriptionConfirmation")) {

			} else if (messagetype.equals("UnsubscribeConfirmation")) {
			} else {

				logger.info(">>Unknown message type.");
			}
			logger.info(">>Done processing message: " );


		}catch(Exception e) {
			logger.error("Failed to parse AWS Bounce report",e);
		} 
		return HttpStatus.OK;
	}
	/*
	 * public static void main(String[] args) { String json =
	 * "{\"notificationType\":\"Bounce\",\"bounce\":{\"bounceType\":\"Permanent\",\"bounceSubType\":\"Suppressed\",\"bouncedRecipients\":[{\"emailAddress\":\"t7880@optonline.net\",\"action\":\"failed\",\"status\":\"5.1.1\",\"diagnosticCode\":\"Amazon SES has suppressed sending to this address because it has a recent history of bouncing as an invalid address. For more information about how to remove an address from the suppression list, see the Amazon SES Developer Guide: http://docs.aws.amazon.com/ses/latest/DeveloperGuide/remove-from-suppressionlist.html \"}],\"timestamp\":\"2020-07-17T18:52:18.490Z\",\"feedbackId\":\"010001735e1eb86b-8c0430e9-0768-48ff-85e9-aeac100d1521-000000\",\"reportingMTA\":\"dns; amazonses.com\"},\"mail\":{\"timestamp\":\"2020-07-17T18:52:17.000Z\",\"source\":\"no-reply@mailservice.freemi.in\",\"sourceArn\":\"arn:aws:ses:us-east-1:114426698134:identity/no-reply@mailservice.freemi.in\",\"sourceIp\":\"13.235.208.141\",\"sendingAccountId\":\"114426698134\",\"messageId\":\"010001735e1eb714-2708eae8-6999-4c9c-9ee6-ddc31f9cbc72-000000\",\"destination\":[\"t7880@optonline.net\"]}}"
	 * ; JSONObject obj = new JSONObject(json); // String pageName =
	 * obj.getJSONObject("notificationType").getString("emailAddress"); String
	 * pageName = obj.getString("notificationType"); System.out.println("value- "+
	 * pageName); pageName = obj.getJSONObject("bounce").getString("bounceType");
	 * System.out.println(pageName); pageName =
	 * obj.getJSONObject("bounce").getString("bounceSubType");
	 * System.out.println(pageName); JSONArray pageName1 =
	 * obj.getJSONObject("bounce").getJSONArray("bouncedRecipients") ;
	 * System.out.println("Mail - " +
	 * pageName1.getJSONObject(0).getString("emailAddress")); pageName =
	 * obj.getJSONObject("mail").getString("source");
	 * System.out.println("Bounce from - "+ pageName);
	 * 
	 * }
	 */
	

}
