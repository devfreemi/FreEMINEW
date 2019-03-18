package com.freemi.ui.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.freemi.entity.investment.MFInvestForm;

@Controller
@Scope("session")
public class InvestmentPortalContoller {

	
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
	
	
}
