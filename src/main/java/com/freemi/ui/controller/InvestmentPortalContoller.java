package com.freemi.ui.controller;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.freemi.common.util.CommonConstants;
import com.freemi.common.util.CommonTask;
import com.freemi.entity.general.UserProfileLdap;
import com.freemi.entity.investment.MFInvestForm;
import com.freemi.entity.investment.mahindra.MFDSearchResult;
import com.freemi.entity.investment.mahindra.Mahindrapurchasehistory;
import com.freemi.services.interfaces.MahindraFDProfileService;
import com.freemi.services.interfaces.ProfileRestClientService;

@Controller
@Scope("session")
public class InvestmentPortalContoller {

    @Autowired
    MahindraFDProfileService mahindraFDProfileService;
    
    @Autowired
    ProfileRestClientService profileRestClientService; 

    @Autowired
    Environment env;

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

    @RequestMapping(value = "/fixed-deposit/view-purchase-history", method = RequestMethod.GET)
    public Object gamahindrafdpuchasehistory(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {

	logger.info("@@ FIXED DEPOSIT purchase history controller @@");
	String returnUrl = "fixed-deposit/fd-purchase-history";
	List<Mahindrapurchasehistory> purchaseHistoryList = null;
	try {
	    if (session.getAttribute("userid") != null || session.getAttribute("token") != null) {

		purchaseHistoryList = mahindraFDProfileService.getPurchaseHistory(session.getAttribute("userid").toString(),null);
		if (purchaseHistoryList != null) {
		    if (purchaseHistoryList.size() >= 1) {
			model.addAttribute("PURCHASE_LIST", "SUCCESS");
			model.addAttribute("PURCHASE_ORDERS", purchaseHistoryList);
		    } else {
			model.addAttribute("PURCHASE_LIST", "NONE");
		    }

		} else {
		    model.addAttribute("PURCHASE_LIST", "ERROR");
		}

	    } else {
		returnUrl = "redirect:/login?ref="+URLEncoder.encode(request.getRequestURL().toString(), StandardCharsets.UTF_8.toString());
	    }
	} catch (Exception e) {
	    logger.error("fdViewPurchaseHistory(): Error processing request",e);
	    returnUrl = "redirect:/login";
	}

	model.addAttribute("contextcdn", env.getProperty(CommonConstants.CDN_URL));
	return returnUrl;
    }


   

}
