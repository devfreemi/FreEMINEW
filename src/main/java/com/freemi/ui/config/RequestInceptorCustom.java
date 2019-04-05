package com.freemi.ui.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.freemi.common.util.CommonConstants;

public class RequestInceptorCustom  extends HandlerInterceptorAdapter {
	
	private static final Logger logger = LogManager.getLogger(RequestInceptorCustom.class);
	
	@Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
		
       /* // set few parameters to handle ajax request from different host
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
        response.addHeader("Access-Control-Max-Age", "1000");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type");
        response.addHeader("Cache-Control", "private");*/
	 	
	 	logger.info("Validating request received for url -"+ request.getRequestURI());
	 	boolean flag=true;

        String reqUri = request.getRequestURI();
       /* String serviceName = reqUri.substring(reqUri.lastIndexOf("/") + 1,
                reqUri.length());
                if (serviceName.equals("SOMETHING")) {

                }*/
        if(reqUri.contains(/*"/mutual-funds/mfPurchaseConfirm.do"*/"tax-calculator")){
//        	System.out.println("Registration");
        	HttpSession session = request.getSession();
        	if(session.getAttribute("token")==null){
        		logger.info("pre-handle- User sesssion not found. Rejecting purchase transaction");
        		request.setAttribute("LOGGED", "FALSE");
        		flag = false;
        	}else{
        		request.setAttribute("LOGGED", "TRUE");
        		logger.info("User session is found. User is logged in");
        	}
        }
        
//        if()
        return super.preHandle(request, response, handler);
//        return flag;
    }
	
	
	@Override
	public void postHandle(
	  HttpServletRequest request, 
	  HttpServletResponse response,
	  Object handler, 
	  ModelAndView modelAndView) throws Exception {
	    try{
	    	
		String logged = request.getAttribute("LOGGED").toString(); 
		if(logged== "FALSE"){
			modelAndView.setViewName("redirect:/login");
		}
		if(modelAndView!=null){
			logger.info("[postHandle][" + " " + "]- "+modelAndView.getViewName());
		}else{
			logger.info("[postHandle][modelAndviwe is null]");
		}
	    }catch(NullPointerException e){
	    	logger.error("attribute null");
	    }
	    
	}

}
