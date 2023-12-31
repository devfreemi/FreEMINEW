package com.freemi.ui.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler2 {
	
	 @ExceptionHandler(value = { ResourceNotFoundException.class })
	    @ResponseStatus(value=HttpStatus.NOT_FOUND)
	    public String badRequest(HttpServletRequest request,Exception ex)
	    {
	        return "pagenotfound";
	    }

}
