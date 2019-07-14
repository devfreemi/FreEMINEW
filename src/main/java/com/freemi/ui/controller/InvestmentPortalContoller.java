package com.freemi.ui.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.freemi.entity.investment.MFInvestForm;

@Controller
@Scope("session")
public class InvestmentPortalContoller {
	
	private static final Logger logger = LogManager.getLogger(InvestmentPortalContoller.class);

	
	@RequestMapping(value = "/registry-mutual-funds/invstNewInvestor", method = RequestMethod.GET)
	public String home(Model model, HttpServletRequest request, HttpSession session) {
		//logger.info("@@@@ Inside Login..");
		System.out.println("@@@@ Investment for Exisitng customer @@@@");
		model.addAttribute("invstform", new MFInvestForm());
		
//		return "form-new-customer";
		return "redirect:/registry-mutual-funds";
	}
	
	
	@RequestMapping(value = "/registry-mutual-funds/invest-existing-customer", method = RequestMethod.GET)
	public String registryExistingCustomer(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session, @ModelAttribute("mfInvestForm") MFInvestForm mfInvestForm) {
		//logger.info("@@@@ Inside Login..");
		System.out.println("@@@@ Investment for Exisitng customer @@@@");
//		model.addAttribute("invstform", new MFInvestForm());
		
//		return "form-existing-customer";
		return "redirect:/registry-mutual-funds";
	}
	
	
	/*@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	public String pageNotFound(HttpServletRequest request, Exception ex){
		logger.info("Controlleradvice- page not found"+ request.getRequestURI());
		return "pagenotfound";
	}*/
	
	
}
