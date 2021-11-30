package com.freemi.ui.controller;

import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;

import com.freemi.common.util.CommonConstants;

@Controller
@ControllerAdvice
public class ErrorHandlers implements ErrorController{
	
	@Autowired
	Environment env;
	
	@Autowired
	  private ErrorAttributes errorAttributes;
	
	private static final Logger logger = LogManager.getLogger(ErrorHandlers.class);
	
	@ExceptionHandler(value=ResourceNotFoundException.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	public String pageNotFound(HttpServletRequest request, Exception ex){
		logger.info("Controlleradvice- page not found"+ request.getRequestURI());
		return "pagenotfound";
	}
	
	
	@RequestMapping("/error")
    public String handleError(Model map, HttpServletRequest request) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		logger.error("Error received http code- "+ status + " from page- "+ request.getHeader("Referer"));
//		logger.error("Error reason: "+ request.getAttribute(RequestDispatcher.ERROR_MESSAGE) + " --> "+request.getAttribute(RequestDispatcher.ERROR_EXCEPTION_TYPE) + " --> "+ request.getAttribute(RequestDispatcher.ERROR_EXCEPTION));
		
			ServletWebRequest servletWebRequest = new ServletWebRequest(request);
		 Map<String, Object> errorAttributes = this.errorAttributes.getErrorAttributes(servletWebRequest, true);
	      final StringBuilder errorDetails = new StringBuilder();
	      errorAttributes.forEach((attribute, value) -> {
	          errorDetails.append("<tr><td>")
	                      .append(attribute)
	                      .append("</td><td><pre>")
	                      .append(value)
	                      .append("</pre></td></tr>");
	      });
	      
	      logger.error("Error reason: "+ errorAttributes);
		if (status != null) {
	        Integer statusCode = Integer.valueOf(status.toString());
	     
	        if(statusCode == HttpStatus.NOT_FOUND.value()) {
	            return "errors/error-404";
	        }
	        else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
	            return "errors/error-500";
	        }/*else if(statusCode == HttpStatus.FORBIDDEN.value()){
	        	return "redirect:/login";
	        }*/
	    }
		map.addAttribute("contextcdn", env.getProperty(CommonConstants.CDN_URL));
		
        return "errors/error";
    }
	

	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return "/error";
	}

	

}
