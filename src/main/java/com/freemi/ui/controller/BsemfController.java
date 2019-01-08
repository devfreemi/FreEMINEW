package com.freemi.ui.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.freemi.common.util.CommonConstants;
import com.freemi.common.util.InvestFormConstants;
import com.freemi.database.interfaces.ProductSchemeDetailService;
import com.freemi.database.service.BseEntryManager;
import com.freemi.entity.database.MfTopFundsInventory;
import com.freemi.entity.investment.BseMFInvestForm;
import com.freemi.entity.investment.SelectMFFund;



@Controller
@Scope("session")
@SessionAttributes("selectedFund")
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
		investForm.setDividendPayMode("02");
		investForm.setOccupation("01");

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
	public String bsemfRegisterpost(@ModelAttribute("mfInvestForm") @Valid BseMFInvestForm investForm,BindingResult bindResult, Model map, HttpServletRequest request, HttpServletResponse response) {

		logger.info("BSE MF STAR Register post controller");
		String returnUrl = "bsemf/bse-registration-status";

		if(bindResult.hasErrors()){
			map.addAttribute("error", bindResult.getFieldError().getDefaultMessage());
			return "bsemf/bse-form-new-customer";
		}

		try{
			String flag = bseEntryManager.saveCustomerDetails(investForm);
			logger.info("Customer registration status- "+ flag);
			if(flag.equalsIgnoreCase("EXIST")){
				returnUrl= "bsemf/bse-form-new-customer";
				map.addAttribute("error", "Customer already exist with given PAN no.");
			}
		}catch(Exception e){
			returnUrl= "bsemf/bse-form-new-customer";
			map.addAttribute("error", "Unable to register customer currently.");
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

	@RequestMapping(value = "/mutual-funds/top-performing", method = RequestMethod.GET)
	public String AllMfFundsView(Model map, HttpServletRequest request, HttpServletResponse response, HttpSession session) {

		logger.info("MF Funds Inventory");
		List<MfTopFundsInventory> topFunds = null;
		/*List<ProductSchemeDetail> allFunds = productSchemeDetailService.findForRegistryBirthDay();
		map.addAttribute("mffunds", allFunds);
		System.out.println("Funds size- "+ allFunds.size());
		for(int i=0;i<allFunds.size();i++){
			System.out.println(allFunds.get(i).getFundName());
		}

		 */
		if(session.getAttribute("topFunds")!=null){
			topFunds = (List<MfTopFundsInventory>) session.getAttribute("topFunds");
			logger.info("Serving top funds from session - "+ topFunds.size());
			map.addAttribute("FUNDSFOUND", "Y");
			map.addAttribute("topFunds", topFunds);
			System.out.println("Total funds");
		}else{
			logger.info("Get top funds from database");
			try{
				topFunds =  bseEntryManager.getTopMfFunds();
				map.addAttribute("FUNDSFOUND", "Y");
				map.addAttribute("topFunds", topFunds);
				session.setAttribute("topFunds", topFunds);
				System.out.println("Total funds received- "+ topFunds.size());
			}catch(Exception e){
				logger.error("Error fetching Funds list. Please try after sometime");
				map.addAttribute("FUNDSFOUND", "N");
			}
		}
		SelectMFFund fundChoice = new SelectMFFund();
		if(session.getAttribute("token")!=null){
			fundChoice.setMobile(session.getAttribute("userid").toString());
			try{
				String panNumber = bseEntryManager.getCustomerPanfromMobile(fundChoice.getMobile());
				fundChoice.setPan(panNumber);
			}catch(Exception e){
				logger.error("Database connect issue: unable to fetch customer PAN number", e.getMessage());
				e.printStackTrace();
			}
		}
		map.addAttribute("selectFund", fundChoice);
		map.addAttribute("contextcdn", env.getProperty(CommonConstants.CDN_URL));
		String returnUrl = "bsemf/mutual-funds-explorer";

		return returnUrl;

	}


	@RequestMapping(value = "/mutual-funds/view-order-history", method = RequestMethod.GET)
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
	public String bsemfTrasactionPost(@ModelAttribute("selectFund") SelectMFFund selectedFund,BindingResult bindResult, Model map, HttpServletRequest request, HttpServletResponse response, HttpSession session) {

		logger.info("BSE MF STAR Register post controller");
		String returnUrl = "redirect:/mutual-funds/top-performing";

		if(bindResult.hasErrors()){
			map.addAttribute("error", bindResult.getFieldError().getDefaultMessage());
			return "bsemf/bse-form-new-customer";
		}
		
		try{
		//Check if existing BSE registered customer or not
		boolean flag = bseEntryManager.isExisitngCustomer(selectedFund.getPan(), selectedFund.getMobile());
		logger.info("Is existing customer? - "+ selectedFund.getPan()+ " : "+ flag);
		session.setAttribute("selectFund", selectedFund);
		if(flag && session.getAttribute("token")==null){

			session.setAttribute("NEXT_URL", "/mutual-funds/purchase");
			returnUrl="redirect:/login";
		}else if((flag && session.getAttribute("token")!=null)){
			returnUrl="redirect:/mutual-funds/purchase";
		}else{
			returnUrl="redirect:/mutual-funds/register";
		}
		}catch(Exception e){
			logger.error("Failed to check customer in databaes",e);
			
		}
		/*try{
		boolean flag = bseEntryManager.savetransactionDetails(selectedFund);
		logger.info("Customer purchase transaction status- "+ flag);
		}catch(Exception e){
			logger.error("Unable to save customer transaction request",e.getMessage());
		}*/

		return returnUrl;

	}


	@RequestMapping(value = "/mutual-funds/purchase", method = RequestMethod.GET)
	public String bseMfPurchaseAfterLogin(Model map, HttpServletRequest request, HttpServletResponse response, HttpSession session) {

		logger.info("BSE MF STAR Register post controller");
		String returnUrl = "bsemf/bse-mf-purchase";

		//Check if existing BSE registered customer or not
		SelectMFFund selectedFund = (SelectMFFund) session.getAttribute("selectFund");
		logger.info("Purchase order start for- "+ selectedFund.getSchemeName() + " : "+ selectedFund.getSchemeCode());
		//		session.removeAttribute("selectFund");

		List<BseMFInvestForm> customerData = null;

		if(session.getAttribute("token")!=null){
			try{
				customerData =  bseEntryManager.getCustomerByPan(selectedFund.getPan());
				//				System.out.println("Data size returned- "+ customerData.size());
				selectedFund.setClientID(customerData.get(0).getClientID());
				logger.info("Investor name- "+ customerData.get(0).getInvName());
				map.addAttribute("customerData", customerData.get(0));
				map.addAttribute("GETDATA", "S");
			}catch(Exception e){
				logger.error("Unable to query database to fetch customer data- ",e.getLocalizedMessage());
				map.addAttribute("GETDATA", "F");
			}

			try{
				//Find customer's portfolio
				logger.info("Search for customer portfolio for details: "+ selectedFund.getAmcCode() + " : "+ customerData.get(0).getClientID());
				List<String> customerPortfolios = bseEntryManager.getSelectedAmcPortfolio(selectedFund.getAmcCode(), customerData.get(0).getClientID());

				//Generate Transaction ID and check if already existing
				boolean transIdExist=false;
				String transId =null;
				do{
					transId = Long.toString((Math.abs(UUID.randomUUID().getMostSignificantBits())));
					transIdExist = bseEntryManager.checkIfTransIdExist(transId);
				}while(transIdExist);

				selectedFund.setTransactionID(transId);

				System.out.println("Portfolio size- "+ customerPortfolios.size());
				if(customerPortfolios.size()== 0){
					customerPortfolios.add("NEW");
				}else{
					selectedFund.setPortfolio(customerPortfolios.get(0));
				}
				map.addAttribute("amcPortFolio", customerPortfolios);
			}catch(Exception e){
				logger.error("Failed to get customer portfolio");
				e.printStackTrace();
			}


		}else{
			returnUrl="redirect:/mutual-funds/top-performing";
		}
		map.addAttribute("paymentMethod", InvestFormConstants.bsePaymentMethod);
		map.addAttribute("selectedFund", selectedFund);
		return returnUrl;

	}

	@RequestMapping(value = "/mutual-funds/mfPurchaseConfirm.do", method = RequestMethod.POST)
	public String bsePurchaseConfirmPost(@ModelAttribute("selectedFund") SelectMFFund selectedFund,BindingResult bindResult, Model map, HttpServletRequest request, HttpServletResponse response, HttpSession session,final RedirectAttributes redirectAttrs) {

		logger.info("@@ BSE MF STAR purchase confirm controller @@");
		String returnUrl = "redirect:/mutual-funds/bse-transaction-status";
		System.out.println("Client ID - "+ selectedFund.getClientID());


		try{
			boolean flag = bseEntryManager.savetransactionDetails(selectedFund);
			logger.info("Customer purchase transaction status- "+ flag);
			redirectAttrs.addAttribute("TRANS_STATUS", "Y");
			redirectAttrs.addFlashAttribute("TRANS_ID", selectedFund.getTransactionID());
		}catch(Exception e){

			logger.error("Unable to save customer transaction request",e.getMessage());
			redirectAttrs.addAttribute("TRANS_STATUS", "N");
		}

		return returnUrl;

	}

	@RequestMapping(value = "/mutual-funds/bse-transaction-status", method = RequestMethod.GET)
	public String bseMFTransactionStatus(@ModelAttribute("TRANS_STATUS") String transStatus,@ModelAttribute("TRANS_ID") String transId,Model map, HttpServletRequest request, HttpServletResponse response, HttpSession session) {

		logger.info("@@ BSE MF STAR purchase confirm controller @@");
		String returnUrl = "bsemf/bse-purchase-status";
		map.addAttribute("TRANS_STATUS", transStatus);
		map.addAttribute("TRANS_ID",transId);
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
	/*
	public static void main(String[] main){
		Long h = UUID.randomUUID().getMostSignificantBits();
		System.out.println(h);
		System.out.println(Math.abs(h));
	}*/

}
