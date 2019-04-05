package com.freemi.ui.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.freemi.common.util.CommonConstants;

@Controller
@ControllerAdvice
public class ErrorHandlers implements ErrorController{
	
	@Autowired
	Environment env;
	
	 private static final String ERROR_PATH = "/error";
	
	private static final Logger logger = LogManager.getLogger(ErrorHandlers.class);
	
	@RequestMapping(value = "/error/{code}")
    public String handleError(@PathVariable int code) {
		logger.info("Request received to handle error!");
        String rtn = null;
        switch (code) {
            case 404:
                rtn = "errors/error-404";
                break;
            case 500:
                rtn = "Internal server error";
                break;
            default:
                rtn = "error";
                break;
        }
        return rtn;
    }

    @RequestMapping(value = ERROR_PATH)
    public void handleError(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	logger.info("No page found error!");
        response.sendRedirect("error/404");
    }

    /**
     * implement ErrorController to handle 404
     */
    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    @ExceptionHandler(Exception.class)
    public void handleException(HttpServletRequest request, HttpServletResponse response, Exception e) throws IOException {
        response.sendRedirect("error/500");
    }

}
