package com.freemi.common.util;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;

import com.freemi.entity.general.ClientSystemDetails;
import com.freemi.entity.general.InvestmentFormGeneral;

public class CommonTask {
	private static final Logger logger = LogManager.getLogger(CommonTask.class);

	public static String getClientSystemIp(HttpServletRequest request) {
		String remoteAddr = "";

		if (request != null) {
			remoteAddr = request.getHeader("X-FORWARDED-FOR");
			if (remoteAddr == null || "".equals(remoteAddr)) {
				remoteAddr = request.getRemoteAddr();
			}
		}

		return remoteAddr;
	}

	public static String getSystemOS(){

		return  System.getProperty("os.name").toUpperCase();
	}
	
	public static ClientSystemDetails getClientSystemDetails(HttpServletRequest request){
		String remoteAddr = "";
		ClientSystemDetails systemDetails = new ClientSystemDetails();
		if (request != null) {
			remoteAddr = request.getHeader("X-FORWARDED-FOR");
			if (remoteAddr == null || "".equals(remoteAddr)) {
				remoteAddr = request.getRemoteAddr();
				logger.info("Ip address of requester- "+ remoteAddr);
			}else{
				logger.info("Ip address of requestor from X- "+ remoteAddr);
			}
		}else {
			logger.info("HttpServletRequest is null. Check implementation");
		}
		systemDetails.setClientIpv4Address(remoteAddr);
		systemDetails.setClientOS(System.getProperty("os.name").toUpperCase());
		systemDetails.setClientBrowser(request.getHeader("User-Agent"));
		
		
		return systemDetails;
		
		
	}
	
	public static boolean validateInvestForm(InvestmentFormGeneral formData){
		
		/*int count=0;
		boolean status=false;
		if(formData.getPAN()==null)
			count++;*/
		
		
		return false;
		
	}
	
	
	public static Date dateFormatter1(String dateFormat, String date) {
		DateFormatter format = new DateFormatter(dateFormat);
		Date formattedDate = null;
		try {
			formattedDate = format.parse(date, Locale.ENGLISH);
		} catch (ParseException e) {
			logger.error("dateFormatter1(): Error formatting date", e);
		}
		return formattedDate;
	}
	
	public static String generateMMTransactionId(){
		return Long.toString((Math.abs(UUID.randomUUID().getMostSignificantBits())));
	}
	
	 public static String encryptPassword(String plaintext){
		TextEncryptor t = Encryptors.text(CommonConstants.ENCRYPTION_SECUENCE, CommonConstants.ENCRYPTION_SALT);
		String s="NA";
		try {
		    s = t.encrypt(plaintext);
		}catch(Exception e) {
		    logger.error("Error encrypting text. ",e);
		}
		return s;
	}
	 
	 public static String decryptPassword(String encryptedText){
		TextEncryptor t = Encryptors.text(CommonConstants.ENCRYPTION_SECUENCE, CommonConstants.ENCRYPTION_SALT);
		
		String s="NA";
		try {
		    s =t.decrypt(encryptedText);
		    logger.debug("Decrypted text- " + s);
		}catch(Exception e) {
		    logger.error("Error decrypting text. ",e);
		}
		
		return s;
	}
	
}
