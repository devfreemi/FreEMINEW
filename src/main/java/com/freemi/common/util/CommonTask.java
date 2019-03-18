package com.freemi.common.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
				logger.info("Ip address of requestor- "+ remoteAddr);
			}else{
				logger.info("Ip address of requestor from X- "+ remoteAddr);
			}
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
}
