package com.freemi.ui.controller.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.freemi.entity.database.BlogAdvisorForm;
import com.freemi.services.interfaces.ApiFormCaptureInterface;

@RestController
@RequestMapping("/api")
public class FormDataController {

	@Autowired
	ApiFormCaptureInterface apiFormCaptureInterface;

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
}
