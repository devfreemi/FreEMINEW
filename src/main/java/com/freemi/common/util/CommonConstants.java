package com.freemi.common.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CommonConstants {

//	Service URL Properties
	public static final String URL_SERVICE_PROFILE="url.service.profile";
	
//	Environment properties
	public static final String CDN_URL="link.content.cdn";
	public static final String BSE_ENABLED="investment.bse.enabled";
	public static final String BSE_AOF_GENERATION_FOLDR="investment.bse.aoffolder";
	
	
//	@Value("${spring.mail.host}")
	public static final String MAIL_SERVER_HOST="spring.mail.host";
	public static final String MAIL_SERVER_PORT="spring.mail.port";
	public static final String MAIL_SERVER_USERNAME="spring.mail.username";
	public static final String MAIL_SERVER_USERPASSWORD="spring.mail.password";
	public static final String MAIL_SERVER_PROTOCOL="spring.mail.protocol";
	public static final String MAIL_ACCOUNT_ID="spring.mail.account";
	
	public static final String IS_MAIL_ENABLED = "mail.smtp.auth.required"; 
	public static final String AUTHENTICATION_REQUIRED="mail.smtp.auth";
	public static final String TLS_TRANSPORT_REQUIRED="mail.smtp.starttls.enable";
	
	/*Mail type*/
	public static final String USER_CREATION="USER.CREATION";
	public static final String EMAIL_SEND_ENABLED ="mail.enabled";
	
	
	public static final String ENV_BIRLA_INVESTMENT_ENABLED="investment.api.birla.enabaled";
//	public static final String INVESTMENT_ENABLED="Y";
	
	
	
	
	public static final String BROKER_CODE = "ARN-141396";
	public static final String EUIN_CODE = "E241233";
	
	//Investor profile
	public static final String STATUS_CODE_RESIDENT_INDIVIDUAL = "01";
	public static final String STATUS_CODE_NRI_NRO = "11";
	public static final String STATUS_CODE_NORI_NRE = "21";
	public static final String STATUS_CODE_PIO = "70";
	
	public static final String HOLDING_NATURE_NEW_SINGFLE_PAN = "SI";
	public static final String HOLDING_NATURE_MULTIPLE_PAN = "AS";
	
	public static final String BANK_ACC_TYPE_SAVINGS = "Savings Account";
	public static final String BANK_ACC_TYPE_CURRENT = "Current Account";
	public static final String BANK_ACC_TYPE_OVER_DRAFT = "Over Draft Account";
	
	public static final String OCCUPATION_SERVICE="Service";
	
	
	
//	Razorpay Route Account IDs
	
	public static final String ROUTE_ACCOUNT_ID_ADITYA_BIRLA_SUN_LIFE = "acc_B5mk3S3di8DNk5";
	
	
	public static final String ENCRYPTION_SECUENCE ="freemipass";
	public static final String ENCRYPTION_SALT ="5c0744940b5c369b";
	
	
//	BSE DETAILS
	public static final String BSE_USER_ID = "2627301";
	public static final String BSE_MEMBER_ID = "26273";
//	public static final String BSE_CLIENT_CODE = "2627301";
	
	public static final String BSE_TRANS_MODE_DEMAT="D";
	public static final String BSE_TRANS_MODE_PHYSICAL="P";
	
	public static final String BSE_XIP="X";
	public static final String BSE_ISP="I";
	
	public static final String BSE_CALL_TEST_ENABLED = "investment.bse.testphase.enabled";
	public static final String BSE_SAVE_TRANSACTION = "Y";
	
	public static final String BSE_OTP_ENABLED = "Y";
	
//	BSE API related reponse code
	public static final String BSE_API_SERVICE_DISABLED = "000";
	public static final String BSE_API_SERVICE_ERROR = "999";
	
	
//	Others
	public static final String GENERATE_PASSWORD_BY_FREEMI = "GENERATE_PASS_MF_REGISTER";
	
	
	
	
	/*SMS RELATED CONSTANTS*/
	
	
	public static final String SMS_OTP_REGISTRATION = "sms.otp.registration";
	public static final String SMS_SEND_ENABLED ="sms.server.send.enabled";
	public static final String SMS_ROUTE_TRANSACTIONAL="sms.route.transactional";
	public static final String SMS_ROUTE_PROMOTIONAL="sms.route.promotional";
	public static final String SMS_SENDER_ID = "sms.sender";
	public static final String SERVICE_PROVIDER_AUTHKEY="provider_authkey";
	public static final String SERVICE_PROVIDER_URL="sms.server.baseurl";
	
	
	/*-----------------------------------------------------------------------------*/
	
	
	public static List<String> protectedUrl = new ArrayList<String>(){
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			add("/products/profile");
			add("/products/my-dashboard");
			add("/products/mutual-funds/view-purchase-history");
			
		}
	};
	
	
}
