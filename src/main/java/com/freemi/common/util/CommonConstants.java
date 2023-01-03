package com.freemi.common.util;

import java.util.ArrayList;
import java.util.List;

public class CommonConstants {

//	Service URL Properties
	public static final String URL_SERVICE_PROFILE="url.service.profile";
	public static final String URL_SERVICE_MF_BSE="url.service.mf.bse";
	
	public static final String URL_SERVICE_MF_BSE_V1= "investment.bse.serviceurl";
	public static final String URL_SERVICE_MF_BSE_UCC_REGISTRATION_V2= "url.service.mf.bse.ucc.registration";
	public static final String URL_SERVICE_MAHINDRA_FD="url.service.fd.mahindra";
	
//	Environment properties
	public static final String CDN_URL="link.content.cdn";
	public static final String BSE_ENABLED="investment.bse.enabled";
	public static final String BSE_AOF_GENERATION_FOLDR="investment.bse.aoffolder";
	public static final String BSE_AOF_LOGO_LOCATION="investment.bse.aoffile.logo";
	
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
	
	public static final String URL_SERVICE_COMMUNICATION= "backend.service.communication.baseurl";
	public static final String SUPPORT_TEAM_MAIL_ID = "mail.id.support.team";
	public static final String MAIL_SUPPORT_TEAM_INVESTMENT_TRIGGER = "investment.bse.mail.support";
	
	public static final String MAIL_SUPPORT_TEAM_MF_REGISTRATION = "mfregistration.bse.mail.support";
	
	public static final String DEVELOPER_TEAM_MAIL_ID="mail.id.developer.team";
	
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
//	public static final String BSE_USER_ID = "sumanta1";
	public static final String BSE_MEMBER_ID = "26273";
//	public static final String BSE_CLIENT_CODE = "2627301";
	
	public static final String BSE_TRANS_MODE_DEMAT="D";
	public static final String BSE_TRANS_MODE_PHYSICAL="P";
	
	public static final String BSE_XIP="X";	//nach mandate
	public static final String BSE_ISIP="I"; //BSE biller
	public static final String BSE_ENACH="N";	//E-NACH
	
	public static final String LDAP_USER_REGISTRATION_ENABLED = "ldap.user.registration.enabled";
	
	public static final String BSE_CALL_TEST_ENABLED = "investment.bse.testphase.enabled";
	public static final String BSE_SAVE_TRANSACTION = "Y";
	
	public static final String BSE_OTP_ENABLED = "Y";
	
	public static final Integer TASK_SUCCESS=100;
	public static final Integer TASK_FAILURE=101;
	public static final Integer TASK_UPDATED=102;
	public static final Integer TASK_SKIPPED=103;
	public static final Integer NO_ACTION=104;
	public static final Integer TASK_INVALID=105;
	
	public static final String TASK_SUCCESS_S="100";
	public static final String TASK_FAILURE_S="101";
	public static final String TASK_UPDATED_S="102";
	public static final String TASK_SKIPPED_S="103";
	public static final String NO_ACTION_S="104";
	public static final String TASK_INVALID_S="105";
	
	public static final Integer TASK_API_RESPONSE_SUCCESS=110;
	public static final Integer TASK_API_RESPONSE_ERROR=111;
	
//	BSE API related reponse code
	public static final String BSE_API_SERVICE_DISABLED = "000";
	public static final String BSE_API_SERVICE_ERROR = "999";
	
	
//	Others
	public static final String GENERATE_PASSWORD_BY_FREEMI = "GENERATE_PASS_MF_REGISTER";
	
	
	public static final String OTP_CHECK = "sms.otp.check.enabled";
	public static final String OTP_TEST_ENABLED = "otp.test.enabled";
	
	/*SMS RELATED CONSTANTS*/
	
	
	public static final String SMS_OTP_REGISTRATION = "sms.otp.registration";
	public static final String SMS_SEND_ENABLED ="sms.server.send.enabled";
	public static final String SMS_ROUTE_TRANSACTIONAL="sms.route.transactional";
	public static final String SMS_ROUTE_PROMOTIONAL="sms.route.promotional";
	public static final String SMS_SENDER_ID = "sms.sender";
	public static final String SERVICE_PROVIDER_AUTHKEY="provider_authkey";
	public static final String SERVICE_PROVIDER_URL="sms.server.baseurl";
	public static final String MSG91_API_URL = "msg91.api.url";
	public static final String MSG91_API_AUTHKEY = "msg91.api.authkey";
	public static final String MSG91_API_SENDERID = "msg91.api.senderid";
	
	
	/*-----------------------------------------------------------------------------*/
	
	public static final int HTTP_CLIENT_CALL_SUCCESS= 100;
	public static final int HTTP_CLIENT_CALL_FAIL= 101;
	public static final int SERVICE_CATEGORY_DISABLED = 900;
	
	
	public static List<String> protectedUrl = new ArrayList<String>(){
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			add("/products/profile");
			add("/products/my-dashboard");
			add("/products/mutual-funds/view-purchase-history");
			add("/products/fixed-deposit/view-purchase-history");
			add("/products/mutual-funds/pending-payments");
			add("/products/mutual-funds/cancelOrder.do");
			add("/products/fixed-deposit-renew");
//			add("/products/registry-planner-purchase");
		}
	};
	
	
//	Date Formats
	public static final String TIMESTAMP_FORMAT_SNS_REPORT ="yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
	

	public static final String BASE_ICCS_URL = "api.iccs.baseurl";
	public static final String ICCS_AUTH_TOKEN = "api.iccs.auth.token";
	
	
	public static final String HDFC_BACKEND_SERVICE_BASEURL_PROPERTIES = "backend.service.hdfc.baseurl";
	public static final String HDFC_BACKEND_SERVICE_TEST_ENABLED="backend.service.hdfc.test.enabled";
	
}
