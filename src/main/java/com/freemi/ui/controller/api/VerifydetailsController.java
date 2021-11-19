package com.freemi.ui.controller.api;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.freemi.common.util.CommonTask;
import com.freemi.entity.general.ClientSystemDetails;
import com.freemi.entity.general.Otpform;
import com.freemi.entity.general.Otprequeststatus;
import com.freemi.services.interfaces.Verifydetailsinterface;




@RestController
@RequestMapping("/")
public class VerifydetailsController {

	private static final Logger logger = LogManager.getLogger(VerifydetailsController.class);

	@Autowired
	private Environment env;

	private static final String ERROR_S = "1";

	/*
	@Autowired 
	OTPInfoRepository otpInfoRepository;

	 * @Autowired OTPCheck otpCheck;
	 */

	@Autowired
	Verifydetailsinterface verifydetailsinterface; 


	@RequestMapping(value = "/sendotp", method = RequestMethod.POST)
	public @ResponseBody Otprequeststatus sendOTP(@RequestBody @Valid Otpform otpform, BindingResult bindingResult, HttpServletRequest request) {
		logger.info("POST "+ request.getRequestURI()+ " : start : sendOTP()");

		Otprequeststatus requeststatus = new Otprequeststatus();
		//		requeststatus.setKey(mobileNum);

		try {
			if (bindingResult.hasErrors()) {
				logger.error("Otp request form valiation failed- "+ bindingResult.getFieldError().getDefaultMessage());
				requeststatus.setStatuscode(ERROR_S);
				requeststatus.setErrormsg(bindingResult.getFieldError().getDefaultMessage());
			}else if(otpform.getKeytype().equals("M") && !Pattern.compile("[5-9]{1}[0-9]{9}").matcher(otpform.getKey()).matches()){
				logger.error("Otp request form valiation failed- "+ otpform.getKeytype() + " key data format mismatch");
				requeststatus.setStatuscode(ERROR_S);
				requeststatus.setErrormsg("Invalid mobile number format");
			}else if(otpform.getKeytype().equals("E") && !Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$").matcher(otpform.getKey()).matches()){
				logger.error("Otp request form valiation failed- "+ otpform.getKeytype() + " email format not accepted");
				requeststatus.setStatuscode(ERROR_S);
				requeststatus.setErrormsg("Email ID format not accepted.");
			}else {
				ClientSystemDetails systemdetails = new ClientSystemDetails();
				systemdetails = CommonTask.getClientSystemDetails(request);
				
				logger.info("Session ID- "+ request.getSession().getId());
				if(otpform.getKeytype().equals("M")) {
					logger.info("Process OTP for mobile category- "+ otpform.getKeytype());
					
					requeststatus = verifydetailsinterface.generatemobileotp(otpform,systemdetails, request.getSession().getId());
				}else if(otpform.getKeytype().equals("E")) {
					logger.info("Process OTP for email category- "+ otpform.getKeytype());
					requeststatus = verifydetailsinterface.generateemailotp(otpform,systemdetails, request.getSession().getId());
				}else {
					requeststatus.setStatuscode(ERROR_S);
					requeststatus.setErrormsg("Validation category not recognized");
				}
				ObjectMapper mapper = new ObjectMapper();
				logger.info("sendotp response- "+ mapper.writeValueAsString(requeststatus));
			}
		}catch(Exception e) {
			logger.error("Error prcessing request",e);
			requeststatus.setStatuscode(ERROR_S);
			requeststatus.setErrormsg("Internal error while processing request");
		}
		return requeststatus;
	}

	@RequestMapping(value = "/checkotp", method = RequestMethod.POST)
	public @ResponseBody Otprequeststatus checkOTP(@RequestBody @Valid Otpform otpCheckInfo, BindingResult bindingResult, 
			HttpServletRequest request) {
		logger.info("POST "+ request.getRequestURI()+ " : start : checkOTP()");

		Otprequeststatus apiresponse = new Otprequeststatus();
		try {

			if (bindingResult.hasErrors()) {
				logger.error("Otp request form valiation failed- "+ bindingResult.getFieldError().getDefaultMessage());
				apiresponse.setStatuscode(ERROR_S);
				apiresponse.setErrormsg(bindingResult.getFieldError().getDefaultMessage());
			}else if(otpCheckInfo.getKeytype().equals("M") && !Pattern.compile("[5-9]{1}[0-9]{9}").matcher(otpCheckInfo.getKey()).matches()){
				logger.error("Otp request form valiation failed- "+ otpCheckInfo.getKeytype() + " key data format mismatch");
				apiresponse.setStatuscode(ERROR_S);
				apiresponse.setErrormsg("Invalid mobile number format");
			}else if(otpCheckInfo.getOtp()==null || otpCheckInfo.getOtp().isEmpty()) {
				logger.error("Otp request form valiation failed- "+ "OTP field is empty");
				apiresponse.setStatuscode(ERROR_S);
				apiresponse.setErrormsg("OTP data is not passed");
			}else {

				//			logger.info("session id =  "+ sessionId + "->" + mobileNum + " -> " + otp);
				ClientSystemDetails systemdetails = new ClientSystemDetails();
				systemdetails = CommonTask.getClientSystemDetails(request);
				
				if(otpCheckInfo.getKeytype().equals("M")) {

				apiresponse = verifydetailsinterface.verifymobileotp(otpCheckInfo,systemdetails,request.getSession().getId());
				}else if(otpCheckInfo.getKeytype().equals("E")) {
					logger.info("Process OTP for email category- "+ otpCheckInfo.getKeytype());
					
					apiresponse = verifydetailsinterface.verifyemailotp(otpCheckInfo,systemdetails,request.getSession().getId());
				}else {
					apiresponse.setStatuscode(ERROR_S);
					apiresponse.setErrormsg("Validation category not recognized");
				}
			}
		}catch(Exception e) {
			apiresponse.setStatuscode(ERROR_S);
			apiresponse.setErrormsg("Failed to process OTP validation. Please try after sometime");
		}

		return apiresponse;
	}


}
