package com.freemi.ui.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.freemi.common.util.CommonConstants;
import com.freemi.entity.general.HomeLoanForm;
import com.freemi.entity.general.TaxCalculatorForm;

@Controller
@Scope("session")
public class Calculators {
	private static final Logger logger = LogManager.getLogger(Calculators.class);
	
	@Autowired
	private Environment environment;

	@RequestMapping(value = "/tax-calculator", method = RequestMethod.GET)
    public String TaxCalculator(Model model, HttpServletRequest request, HttpServletResponse response) {
		//logger.info("@@@@ Inside Login..");
//		System.out.println("Device in request desktop?- "+ device.isNormal());
//		System.out.println("Device in request mobile?- "+ device.isMobile());
		model.addAttribute("taxCalculatorForm", new TaxCalculatorForm());
		model.addAttribute("contextcdn", environment.getProperty(CommonConstants.CDN_URL));
       System.out.println("@@@@ TaxCalculatorController @@@@");
       return "tax-calculator";
    }
	
	@RequestMapping(value = "/registry-mutual-funds/home-loan", method = RequestMethod.GET)
    public String HomeLoan(ModelMap model) {
		logger.info("/registry-mutual-funds/home-loan");
		model.addAttribute("homeLoanForm", new HomeLoanForm());
       System.out.println("@@@@ HomeLoanController @@@@");
       return "home-loan";
    }
	
	@ModelAttribute("taxpayerType")
	   public Map<String, String> getinvestmentType() {
	      Map<String, String> taxpayerType = new HashMap<String, String>();
	      taxpayerType.put("MF", "Male/Female (Below 60 years)");
	      taxpayerType.put("SC", "Senior Citizen (60 - 80 years)");
	      taxpayerType.put("SSC", "Super Senior Citizen (Above 80 years)");	
	      return taxpayerType;
	   }
	
	/*@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	public String pageNotFound(HttpServletRequest request, Exception ex){
		logger.info("Controlleradvice- page not found"+ request.getRequestURI());
		return "pagenotfound";
	}*/
}
