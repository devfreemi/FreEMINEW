package com.freemi.ui.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.freemi.common.util.CommonConstants;
import com.freemi.common.util.InvestFormConstants;
import com.freemi.database.service.BseEntryManager;
import com.freemi.entity.database.ProductSchemeDetail;
import com.freemi.entity.investment.BseMFInvestForm;
import com.freemi.entity.investment.SelectMFFund;
import com.freemi.repository.interfaces.ProductSchemeDetailService;



@Controller
@Scope("session")
public class BsemfController {
	
	private static final Logger logger = LogManager.getLogger(BsemfController.class);
	
	@Autowired
	ProductSchemeDetailService productSchemeDetailService;

	/*
	@Autowired
	private DatabaseEntryManager databaseEntryManager ;//= (DatabaseEntryManager) BeanUtil.getBean(DatabaseEntryService.class);
	*/
	
	@Autowired
	private BseEntryManager bseEntryManager;
	
	@Autowired
	private Environment env;

	@RequestMapping(value = "/mutual-funds/register", method = RequestMethod.GET)
	public String home(Model map, HttpServletRequest request, HttpServletResponse response) {
		//logger.info("@@@@ Inside Login..");
		logger.info("@@@@ BSE New customer register @@@@");
		
		BseMFInvestForm investForm = new BseMFInvestForm();
		
		map.addAttribute("mfInvestForm", investForm);
		map.addAttribute("dividendPayMode", InvestFormConstants.dividendPayMode);
		map.addAttribute("occupation", InvestFormConstants.occupationList);
		map.addAttribute("bankNames", InvestFormConstants.bankNames);
		map.addAttribute("accountTypes", InvestFormConstants.accountTypes);
		map.addAttribute("states", InvestFormConstants.states);
		map.addAttribute("contextcdn", env.getProperty(CommonConstants.CDN_URL));
		
		return "bsemf/bse-form-new-customer";
//		return "bsemf/test";
	}
	
	@RequestMapping(value = "/mutual-funds/mfInvestRegister.do", method = RequestMethod.POST)
	public String bsemfRegisterpost(@ModelAttribute("mfInvestForm") BseMFInvestForm investForm,BindingResult bindResult, Model map, HttpServletRequest request, HttpServletResponse response) {
	
		logger.info("BSE MF STAR Register post controller");
		String returnUrl = "bsemf/bse-registration-status";
		
		if(bindResult.hasErrors()){
			map.addAttribute("error", bindResult.getFieldError().getDefaultMessage());
			return "bsemf/bse-form-new-customer";
		}
		
		try{
		boolean flag = bseEntryManager.saveCustomerDetails(investForm);
		logger.info("Customer registration status- "+ flag);
		}catch(Exception e){
			logger.error("Unable to save customer registration",e.getMessage());
			e.printStackTrace();
		}
		
		return returnUrl;
	
	}
	
	@RequestMapping(value = "/mutual-funds/mf-registration-status", method = RequestMethod.GET)
	public String mfRegistrationStatusGet(@ModelAttribute("mfInvestForm") BseMFInvestForm investForm, Model map, HttpServletRequest request, HttpServletResponse response) {
	
		logger.info("BSE MF STAR Register post controller");
		String returnUrl = "bsemf/bse-registration-status";
		
		return returnUrl;
	
	}
	
	@RequestMapping(value = "/mutual-funds/all-funds", method = RequestMethod.GET)
	public String AllMfFundsView( Model map, HttpServletRequest request, HttpServletResponse response) {
	
		logger.info("MF Funds Inventory");
		
		List<ProductSchemeDetail> allFunds = productSchemeDetailService.findForRegistryBirthDay();
		map.addAttribute("mffunds", allFunds);
		System.out.println("Funds size- "+ allFunds.size());
		for(int i=0;i<allFunds.size();i++){
			System.out.println(allFunds.get(i).getFundName());
		}
		map.addAttribute("selectFund", new SelectMFFund());
		map.addAttribute("contextcdn", env.getProperty(CommonConstants.CDN_URL));
		String returnUrl = "bsemf/mutual-funds-explorer";
		
		return returnUrl;
	
	}
	
	
	@RequestMapping(value = "/mutual-funds/view-0rder-history", method = RequestMethod.GET)
	public String viewOrderHistory( Model map, HttpServletRequest request, HttpServletResponse response) {
	
		logger.info("MF Funds Inventory");
		
		List<SelectMFFund> orderHistory = bseEntryManager.getMFOrderHistory("DEBASISH87");
		List<BseMFInvestForm> customerDetails = bseEntryManager.getCustomerDetails("DEBASISH87");
		
		System.out.println("Funds size- "+ orderHistory.size());
		System.out.println("Cusotmer details- "+ customerDetails.size());
		System.out.println("Customer PAN- "+ customerDetails.get(0).getPan1());
		
//		System.out.println("Customer purchase totals- "+ customerDetails.get(0).getFundPurchaseDetails().size());
		
		for(int i=0;i<orderHistory.size();i++){
			System.out.println(orderHistory.get(i).getInvestAmount());
		}
		
		map.addAttribute("contextcdn", env.getProperty(CommonConstants.CDN_URL));
		String returnUrl = "bsemf/order-history";
		
		return returnUrl;
	
	}
	
	
	@RequestMapping(value = "/mutual-funds/purchase.do", method = RequestMethod.POST)
	public String bsemfTrasactionPost(@ModelAttribute("selectFund") SelectMFFund selectedFund,BindingResult bindResult, Model map, HttpServletRequest request, HttpServletResponse response) {
	
		logger.info("BSE MF STAR Register post controller");
		String returnUrl = "bsemf/bse-registration-status";
		
		if(bindResult.hasErrors()){
			map.addAttribute("error", bindResult.getFieldError().getDefaultMessage());
			return "bsemf/bse-form-new-customer";
		}
		
		try{
		boolean flag = bseEntryManager.savetransactionDetails(selectedFund);
		logger.info("Customer purchase transaction status- "+ flag);
		}catch(Exception e){
			logger.error("Unable to save customer transaction request",e.getMessage());
		}
		
		return returnUrl;
	
	}
	
	
	@RequestMapping("/download/{fileName:.+}")
    public void downloadPDFResource( HttpServletRequest request,
                                     HttpServletResponse response,
                                     @PathVariable("fileName") String fileName, @RequestHeader String referer)
    {
		System.out.println("File download");
        //If user is not authorized - he should be thrown out from here itself
		if(referer != null && !referer.isEmpty()) {
            //do nothing
            //or send error
        } 
		
		
        //Authorized user will download the file
        /*String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/downloads/pdf/");*/
		
		
		
		String dataDirectory = "E:\\logs\\PDF";
        Path file = Paths.get(dataDirectory, fileName);
        System.out.println(file.toString());
        if (Files.exists(file))
        {
            response.setContentType("application/pdf");
            response.addHeader("Content-Disposition", "attachment; filename="+fileName);
            try
            {
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
                System.out.println("Send response");
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }else{
        	System.out.println("File not found");
        }
        System.out.println("Complete");
    }
	

	@ModelAttribute("taxStatus")
	public Map<String, String> getTaxStatus() {
		Map<String, String> taxStatus = new HashMap<String,String>();
		taxStatus.put("01", "INDIVIDUAL");
		taxStatus.put("03", "HUF");
		taxStatus.put("04", "COMPANY");
		taxStatus.put("06", "PARTNERSHIP FIRM");
		taxStatus.put("07", "BODY CORPORATE");
		taxStatus.put("08", "TRUST");
		taxStatus.put("09", "SOCIETY");
		taxStatus.put("13", "SOLE PROPRIETORSHIP");
		return taxStatus;
	}
	
	
	@ModelAttribute("holingNature")
	public Map<String, String> mfHoldingNature() {
		
		// To keep the order as entered below
		Map<String, String> holdingNature = new LinkedHashMap<String,String>();
		holdingNature.put("SI", "Single");
		holdingNature.put("JO", "Joint");
		holdingNature.put("AS", "Anyone or Survivor");
		
		return holdingNature;
	}

	
}
