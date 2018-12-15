package com.freemi.ui.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.freemi.entity.general.HomeLoanForm;
import com.freemi.entity.general.TaxCalculatorForm;

@Controller
@Scope("session")
public class Calculators {

	@RequestMapping(value = "/tax-calculator", method = RequestMethod.GET)
    public String TaxCalculator(ModelMap model) {
		//logger.info("@@@@ Inside Login..");
		model.addAttribute("taxCalculatorForm", new TaxCalculatorForm());
       System.out.println("@@@@ TaxCalculatorController @@@@");
       return "tax-calculator";
    }
	
	@RequestMapping(value = "/registry-mutual-funds/home-loan", method = RequestMethod.GET)
    public String HomeLoan(ModelMap model) {
		//logger.info("@@@@ Inside Login..");
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
}
