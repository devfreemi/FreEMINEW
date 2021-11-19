package com.freemi.services.Impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.freemi.common.util.OtpService;
import com.freemi.connector.restclient.Communicationrestclient;
import com.freemi.entity.general.ClientSystemDetails;
import com.freemi.entity.general.OTPInfo;
import com.freemi.entity.general.OTPInfo2;
import com.freemi.entity.general.Otpform;
import com.freemi.entity.general.Otprequeststatus;
import com.freemi.services.interfaces.Verifydetailsinterface;

@Service
public class DetailsverifyImpl implements Verifydetailsinterface {

	private static final Logger logger = LogManager.getLogger(DetailsverifyImpl.class);
	@Autowired 
	OtpService otpService;

//	@Autowired
//	OTPInfoRepository otpInfoRepository;

	@Autowired
	Environment env;

	@Autowired
	Communicationrestclient communicationclient;

	
	@Override
	public Otprequeststatus generatemobileotp(Otpform otpform,ClientSystemDetails systemdetails,String sessionid ) {
		logger.info("Request received to send OTP for mobile- "+ otpform.getKey() + " : session: "+ sessionid);
		OTPInfo otpinfo = new OTPInfo();
		
		otpinfo.setMobileNumber(otpform.getKey());
		otpinfo.setSessionId(sessionid);
		otpinfo.setModule(otpform.getModule());
		otpinfo.setSubModule(otpform.getSubmodule());

		otpinfo.setRequestingsystemip(systemdetails.getClientIpv4Address());
		otpinfo.setSystemdetails(systemdetails.getClientBrowser());
		otpinfo.setRequestedfrommodule("101");
		if(otpinfo.getModule().equals("MF")) {
			if(otpinfo.getSubModule().equals("R")) {
				otpinfo.setModule("MUTUAL_FUND");
				otpinfo.setSubModule("REGISTRATION");
				otpinfo.setMesseageid("103");
			}
		}
		return communicationclient.sendOTP(otpinfo); 
	}

	
	@Override
	public Otprequeststatus verifymobileotp(Otpform otpform,ClientSystemDetails systemdetails,String sessionid ) {
		
		OTPInfo otpinfo = new OTPInfo();
		otpinfo.setMobileNumber(otpform.getKey());
		otpinfo.setSessionId(sessionid);
		otpinfo.setModule(otpform.getModule());
		otpinfo.setSubModule(otpform.getSubmodule());
		otpinfo.setOtp(otpform.getOtp());
		otpinfo.setRequestingsystemip(systemdetails.getClientIpv4Address());
		otpinfo.setSystemdetails(systemdetails.getClientBrowser());
		
		otpinfo.setRequestedfrommodule("101");
		if(otpinfo.getModule().equals("MF")) {
			if(otpinfo.getSubModule().equals("R")) {
				otpinfo.setModule("MUTUAL_FUND");
				otpinfo.setSubModule("REGISTRATION");
				otpinfo.setMesseageid("103");
			}
		}
		
		return communicationclient.checkOTP(otpinfo);
	}


	@Override
	public Otprequeststatus generateemailotp(Otpform otpform,ClientSystemDetails systemdetails,String sessionid ) {

		OTPInfo2 otpinfo = new OTPInfo2();
		logger.info("Session ID- "+ sessionid);
		otpinfo.setEmailid(otpform.getKey());
		
		otpinfo.setSessionId(sessionid);
		otpinfo.setModule(otpform.getModule());
		otpinfo.setSubModule(otpform.getSubmodule());

		otpinfo.setRequestingsystemip(systemdetails.getClientIpv4Address());
		otpinfo.setSystemdetails(systemdetails.getClientBrowser());
		
		otpinfo.setRequestedfrommodule("101");
		if(otpinfo.getModule().equals("MF")) {
			if(otpinfo.getSubModule().equals("R")) {
				otpinfo.setModule("MUTUAL_FUND");
				otpinfo.setSubModule("REGISTRATION");
				otpinfo.setMesseageid("103");
			}
		}
		
		
		return communicationclient.sendemailOTP(otpinfo);
	}
	
	@Override
	public Otprequeststatus verifyemailotp(Otpform otpform,ClientSystemDetails systemdetails,String sessionid ) {


		OTPInfo2 otpinfo = new OTPInfo2();
		logger.info("Session ID- "+ sessionid);
		otpinfo.setEmailid(otpform.getKey());
		otpinfo.setOtp(otpform.getOtp());
		otpinfo.setSessionId(sessionid);
		otpinfo.setModule(otpform.getModule());
		otpinfo.setSubModule(otpform.getSubmodule());

		otpinfo.setRequestingsystemip(systemdetails.getClientIpv4Address());
		otpinfo.setSystemdetails(systemdetails.getClientBrowser());
		
		otpinfo.setRequestedfrommodule("101");
		if(otpinfo.getModule().equals("MF")) {
			if(otpinfo.getSubModule().equals("R")) {
				otpinfo.setModule("MUTUAL_FUND");
				otpinfo.setSubModule("REGISTRATION");
				otpinfo.setMesseageid("103");
			}
		}
		
		return communicationclient.checkemailOTP(otpinfo);
	}


	



}
