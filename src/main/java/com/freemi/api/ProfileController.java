package com.freemi.api;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.freemi.controller.interfaces.ProfileRestClientService;
import com.freemi.entity.general.Login;
import com.freemi.entity.general.LoginResponse;
import com.freemi.entity.general.Registerform;
import com.freemi.entity.general.UserProfile;
import com.freemi.ui.restclient.RestClient;

@RestController
@CrossOrigin(origins="*")
public class ProfileController {
	
	@Autowired
	ProfileRestClientService profileRestClientService; 

	private static final Logger logger = LogManager.getLogger(ProfileController.class);

	@PostMapping(value="/api/login")
	public Object apiLogin(HttpServletRequest request, HttpServletResponse httpResponse){
		logger.info("Request received to process login via API.");

		Login loginForm = null;
		LoginResponse loginResponse = new LoginResponse();

		try {
			loginForm = new ObjectMapper().readValue(request.getInputStream(), Login.class);

			logger.info("Login request for user id via api- "+ loginForm.getUsermobile());


//			RestClient client = new RestClient();
			ResponseEntity<String> response = null;

			try{
				response= profileRestClientService.login(loginForm.getUsermobile(), loginForm.getUserpassword(), loginForm.getSystemip());

				//				logger.debug(response.getStatusCodeValue());
				if(response.getStatusCodeValue() == 200){
					loginResponse.setEmail(response.getHeaders().get("email").get(0));
					loginResponse.setFname(response.getHeaders().get("fname").get(0));
					loginResponse.setUserid(response.getHeaders().get("userid").get(0));
					httpResponse.setHeader("Authorization", response.getHeaders().get("Authorization").get(0));
					loginResponse.setStatus(Integer.toString(response.getStatusCodeValue()));
				}
				//				logger.info(response.getHeaders().get("Authorization").get(0));
			}
			catch(HttpStatusCodeException  e){
				logger.error("HttpStatusCodeException");
				loginResponse.setStatus(Integer.toString(e.getRawStatusCode()));

			}catch(Exception e){
				logger.error("Error- "+ e.getMessage());
				loginResponse.setStatus(e.getMessage());
			}


		} catch (IOException e) {
			logger.error("Error in api- "+ e.getMessage());
			loginResponse.setStatus("INVALID");
		}

		return loginResponse;
	}


	@PostMapping(value="/api/registeruser")
	public Object apiregisteruser(HttpServletRequest request, HttpServletResponse httpResponse){
		logger.info("Request received to process login via API.");

//		Login loginForm = null;
		Registerform registerForm = new Registerform();

		String STATUS="";

		try {
			registerForm = new ObjectMapper().readValue(request.getInputStream(), Registerform.class);

			logger.info("Login request for user id via api- "+ registerForm.getMobile());


//			RestClient client = new RestClient();
			ResponseEntity<String> response = null;

			response = profileRestClientService.registerUser(registerForm);
			String status = response.getHeaders().get("STATUS").get(0);
			if(status.equals("SUCCESS")){
				//					model.addAttribute("success", "Registration successful. Login to your account");
				STATUS="SUCCESS";
			}else if(status.equals("DUPLICATE ENTRY")){
				//					model.addAttribute("error", "Account already exist.");
				STATUS="DUPLICATE";
			}else if(status.equals("ERROR")){
				//					model.addAttribute("error", "Registration failed. Please try again after sometime");

				STATUS="ERROR";
			}else{
				logger.info("Reponse for register via api- "+response.getHeaders().get("STATUS").get(0));
				//					model.addAttribute("error", "Unknown response");
				STATUS="ERROR";
			}

		}catch(HttpStatusCodeException  e){
			logger.error("Register failure via api for user "+registerForm.getMobile() + " : HTTP CODE : "+e.getStatusCode() );
			//				model.addAttribute("error", "Unable to process request curretnly");
		} catch (JsonProcessingException e) {
			//				model.addAttribute("error","Invalid form data");
			STATUS ="INVALID DATA";
		}catch(Exception e){
			//				model.addAttribute("error","Error processing request");
			STATUS="ERROR PROCESSING REQUEST";
		}

		return STATUS;

	}
	
	@PostMapping(value="/api/getprofile")
	public Object apigetProfile(HttpServletRequest request, HttpServletResponse httpResponse){
		
		logger.error("Request received to fectch profiel data via api");
//		RestClient client = new RestClient();
		ResponseEntity<String> response = null;
		
		UserProfile profile=null;
		String mobile=request.getHeader("mobile");
		String token=request.getHeader("Authorization");
		String ip=request.getHeader("requestingIp");
		
		if(mobile!=null && token!=null && ip!=null){
			logger.error("Processing request for profile data fetch for user- " + mobile);
			
		try {
			response = profileRestClientService.getProfileData(mobile, token,ip);
			profile = new ObjectMapper().readValue(response.getBody(), UserProfile.class);

		}catch(HttpStatusCodeException  e){
			logger.error("Error fetching profile data via api request" + e.getStatusCode());
			httpResponse.setStatus(e.getRawStatusCode());
		} catch (JsonProcessingException e) {
			logger.error("Error processing request data via api request" +e.getMessage());
		}catch(Exception e){
			logger.error("Unable to process request to fetch data request via api \n" + e.getMessage());
		}
		}else{
			logger.info("Missing required parameters");
			httpResponse.setStatus(401);
		}
		
		return profile;
		
	}
	


}
