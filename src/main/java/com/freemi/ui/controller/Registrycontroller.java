package com.freemi.ui.controller;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.freemi.common.util.CommonConstants;
import com.freemi.common.util.CommonTask;
import com.freemi.common.util.InvestFormConstants;
import com.freemi.entity.bse.BseOrderPaymentRequest;
import com.freemi.entity.bse.BseOrderPaymentResponse;
import com.freemi.entity.general.ClientSystemDetails;
import com.freemi.entity.general.MethodRequestResponse;
import com.freemi.entity.investment.RegistryFunds;
import com.freemi.entity.investment.RegistryWish;
import com.freemi.entity.investment.SelectMFFund;
import com.freemi.entity.investment.TransactionStatus;
import com.freemi.services.interfaces.BseEntryManager;
import com.freemi.services.interfaces.InvestmentConnectorBseInterface;
import com.freemi.services.interfaces.RegistryManager;

//@Controller
@Scope("session")
public class Registrycontroller {

    private static final Logger logger = LogManager.getLogger(Registrycontroller.class);

    @Autowired
    RegistryManager registryManager;

    @Autowired
    BseEntryManager bseEntryManager;

    @Autowired
    InvestmentConnectorBseInterface investmentConnectorBseInterface;

    @Autowired
    Environment env;


    @RequestMapping(value = "/registry-planner", method = RequestMethod.GET)
    public String registerUserGet(Model map,@ModelAttribute("registryWishForm") RegistryWish regisplanner, HttpServletRequest request, HttpServletResponse response, HttpSession session) {

	regisplanner.setWishType("GOAL");
	regisplanner.setRegistryfundcode("R-501");
	regisplanner.setRegistryname("Birthday Anniversary");

	return "registry/registry-planner";
    }


    @RequestMapping(value = "/registry-planner-capture", method = RequestMethod.POST)
    public String getRegistryFundsSubmit(@Valid @ModelAttribute("registryWishForm") RegistryWish regisplanner,BindingResult bindResult, ModelMap map, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
	logger.info("@@@@ registryFunds.doPostController @@@@");

	if (bindResult.hasErrors()) {
	    logger.info("getRegistryFundsSubmit(): Form has error- "+ bindResult.getFieldError().getDefaultMessage());
	    map.addAttribute("error", bindResult.getFieldError().getDefaultMessage());
	    return "registry/registry-planner";
	}

	MethodRequestResponse methodresponse = null;

	try {
	    logger.info("Requested fund- "+ regisplanner.getAmount() + " --> "+ regisplanner.getSchemeCode());

	    ClientSystemDetails systemdetails = CommonTask.getClientSystemDetails(request);

	    regisplanner.setSystemdetails(systemdetails.getClientIpv4Address());
	    regisplanner.setClientbrowserdetails(systemdetails.getClientBrowser());

	    methodresponse = registryManager.saveRegistryplannerrequest(regisplanner);
	}catch(Exception e) {
	    logger.error("Error processing request",e);
	}
	return "redirect:/registry-planner-purchase?rqid="+CommonTask.encryptText(methodresponse.getRemarks());
    }


    @RequestMapping(value = "/registry-planner-purchase", method = RequestMethod.GET)
    public String purchaseRegistryPlannerGet(@RequestParam(value = "rqid",required = true) String plannerid,ModelMap map, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
	logger.info("@@@@ Registry planner. Get Controller @@@@");

	SelectMFFund selectedFund = new SelectMFFund();
	String returnurl="registry/registry-planner-purchase";
	try {
	    String transid = CommonTask.decryptText(plannerid);
	    RegistryWish registrywish = registryManager.getregistryDetails(transid);

	    if(registrywish!=null) {
		selectedFund.setMobile(registrywish.getMobile());
		selectedFund.setPan(registrywish.getPan());
		selectedFund.setInvestAmount(registrywish.getMonthlySavings());
		selectedFund.setSchemeCode(registrywish.getSchemeCode());
		selectedFund.setNoOfInstallments(registrywish.getTenure());
		selectedFund.setProductrefid(registrywish.getTransactionid());
		selectedFund.setProducttype("REGISTRY");

		RegistryFunds fund = registryManager.serachregistryfund(registrywish.getSchemeCode());
		if(fund!=null) {
		    map.addAttribute("fundsipdates",Arrays.asList(fund.getSipdates().split(",")));
		    session.setAttribute("registryfundsipdates", Arrays.asList(fund.getSipdates().split(",")));
		}
		map.addAttribute("registrywish", registrywish);
		map.addAttribute("plannerdetails", selectedFund);
	    }else {
		logger.info("No record found with mentioned transactionid... Return back to registry planner");
	    }
	}catch(Exception e) {
	    logger.error("Error processing request",e);
	    returnurl="redirect:/registry-planner?msg=01";
	}
	
	return returnurl;
    }

    @RequestMapping(value = "/registry-planner-purchase.do", method = RequestMethod.POST)
    public String purchaseRegistryPlannerPost(@ModelAttribute("plannerdetails")SelectMFFund selectedFund, ModelMap map, HttpServletRequest request, HttpServletResponse response, HttpSession session, final RedirectAttributes redirectAttrs) {
	logger.info("@@@@ Registryplanner .purchase PostController @@@@");

	String returnurl="redirect:/registry-planner-status";
	TransactionStatus transationResult = new TransactionStatus();
	
	
	try {
	    
	    if(selectedFund.getSipDate() == null || selectedFund.getSipDate().isEmpty()) {
		map.addAttribute("error", "Select your monthly contribution date");
		returnurl="registry/registry-planner-purchase";
	    }else {
	    
	    if((bseEntryManager.isExisitngBSECustomerByMobile(selectedFund.getMobile()))) {
		String clientid = bseEntryManager.getClientIdfromMobile(selectedFund.getMobile());
		selectedFund.setClientID(clientid);
		String profileStatus=bseEntryManager.investmentProfileStatus(selectedFund.getMobile());
		logger.info("purchaseConfirmPost(): Profile status of customer- "+ selectedFund.getMobile() + " : "+ profileStatus);
		
		if(profileStatus.equals("PROFILE_READY")) {
		    selectedFund.setInvestype("SIP");
		    selectedFund.setProducttype("REGISTRY");
		    if(selectedFund.getPortfolio() == null) {
			selectedFund.setPortfolio("NEW");
		    }
		    
		    String combineDate = (selectedFund.getSipDate().length() == 1 ? "0" + selectedFund.getSipDate(): selectedFund.getSipDate())
			    + "/"
			    + ((Integer.valueOf(selectedFund.getSipStartMonth()) < 10)
				    ? "0" + Integer.toString(Integer.valueOf(selectedFund.getSipStartMonth()))
				    : Integer.toString(Integer.valueOf(selectedFund.getSipStartMonth())))
			    + "/" + selectedFund.getSipStartYear();
		    logger.info("SIP start from- " + combineDate);
		    selectedFund.setSipStartDate((new SimpleDateFormat("dd/MM/yyyy")).parse(combineDate));

		    ClientSystemDetails requestingsytemDetails = CommonTask.getClientSystemDetails(request);
		    selectedFund.setClientIp(requestingsytemDetails.getClientIpv4Address());
		    selectedFund.setClientBrowser(requestingsytemDetails.getClientBrowser());


		    selectedFund.setSipfrequency("MONYHLY");
		    String transactionId = bseEntryManager.generateTransId();
		    selectedFund.setTransactionID(transactionId);
		    
		    transationResult = registryManager.purchaseregistrysip(selectedFund);
		    logger.info("Customer purchase transaction status for SIP- " + transationResult.getSuccessFlag());


		    if (transationResult.getSuccessFlag() != null && transationResult.getSuccessFlag().equalsIgnoreCase("S")) {

			/*   try {
			// Trigger transaction mailer

			MFCustomers userDetails = bseEntryManager.getCustomerInvestFormData(
				session.getAttribute("userid") != null ? session.getAttribute("userid").toString()
					: selectedFund.getMobile());

			logger.info("Transaction processed successfully.. Processing to send mail for transaction id- " + selectedFund.getTransactionID());
			mailSenderInterface.mfpurchasenotofication(selectedFund, userDetails, "purchase");
			   } catch (Exception e) {
			logger.error("Failed to send mail to customer after purchase..", e);
			   }
			 */

			/*    redirectAttrs.addAttribute("TRANS_STATUS", "Y");
			    redirectAttrs.addFlashAttribute("TRANS_ID", selectedFund.getTransactionID());
			    redirectAttrs.addFlashAttribute("TRANS_MSG", transationResult.getStatusMsg());

			    transationResult.setTransactionReference(selectedFund.getTransactionID());
			    transationResult.setInvestmentType(selectedFund.getInvestype());
			    transationResult.setFundName(selectedFund.getSchemeName());*/
			/*
					    if (selectedFund.getInvestype().equalsIgnoreCase("LUMPSUM")) {
						redirectAttrs.addFlashAttribute("FIRST_PAY", "Y");
					    } else if (selectedFund.getInvestype().equalsIgnoreCase("SIP")) {
						if (selectedFund.isPayFirstInstallment()) {
						    redirectAttrs.addFlashAttribute("FIRST_PAY", "Y");
						} else {
						    redirectAttrs.addFlashAttribute("FIRST_PAY", "N");
						}
					    }
			 */

			redirectAttrs.addFlashAttribute("TRANS_ID", selectedFund.getTransactionID());
			redirectAttrs.addFlashAttribute("TRANS_MSG", transationResult.getStatusMsg());
			redirectAttrs.addFlashAttribute("ORDER_NO", transationResult.getBseOrderNoFromResponse());
			redirectAttrs.addFlashAttribute("CLIENT_CODE", selectedFund.getClientID());
		    } else if (transationResult.getSuccessFlag() != null
			    && transationResult.getSuccessFlag().equalsIgnoreCase("F")) {
			map.addAttribute("error", transationResult.getStatusMsg());
			returnurl = "registry/registry-planner-purchase";
		    } else if (transationResult.getSuccessFlag() != null
			    && transationResult.getSuccessFlag().equalsIgnoreCase("D")) {
			map.addAttribute("error", "Fund transaction is currently disabled by Admin. Please try after sometime.");
			returnurl="registry/registry-planner-purchase";
		    } else {
			logger.info("Transaction failed...");
			map.addAttribute("error", transationResult.getStatusMsg());
			returnurl="registry/registry-planner-purchase";
		    }
		}else {
		    logger.info("Customer profile is not complete for transaction...");
		    map.addAttribute("error", "You need to complete profile registration. Go to dashbaord to check status");
		    returnurl="registry/registry-planner-purchase";
		}
	    }else {
		logger.info("Customer do not exist by details...");
		returnurl="registry/registry-planner-purchase";
		map.addAttribute("error", "Your profile do not exist. Please complete the registration first.");
	    }
	    }
	}catch(Exception e) {
	    logger.error("Error processing request",e);
	    returnurl = "registry/registry-planner-purchase";
	    map.addAttribute("error", "Internal error. Please try again or contact admin.");

	}
	map.addAttribute("fundsipdates", session.getAttribute("registryfundsipdates"));

	//	map.addAttribute("plannerdetails", selectedFund);
	return returnurl;
    }

    @RequestMapping(value = "/registry-planner-status", method = RequestMethod.GET)
    public String registryplannerStatus(
	    @ModelAttribute("CLIENT_CODE") String clienCode,
	    @ModelAttribute("ORDER_NO") String orderno,
	    @ModelAttribute("TRANS_ID") String transId, @ModelAttribute("TRANS_MSG") String transMessage,
	    Model map, HttpServletRequest request,
	    HttpServletResponse response, HttpSession session) {
	String returnurl="registry/registry-planner-status";
	logger.info("@@ BSE MF STAR Transaction status controller @@");

	/*if (clienCode.isEmpty()) {
	    logger.info("registryplannerStatus(): Client code is empty.. Returning back to home page");
	    returnurl = "redirect:/registry-planner";
	} else {*/
	    logger.info("registryplannerStatus(): Proceeding to generate pay url for order id - " + orderno);
	    BseOrderPaymentRequest orderUrl = new BseOrderPaymentRequest();
	    orderUrl.setClientCode(clienCode);
	    orderUrl.setMemberCode(CommonConstants.BSE_MEMBER_ID);

	    String callbackUrl = URI.create(request.getRequestURL().toString()).resolve(request.getContextPath())
		    .toString()
		    + "/registry-planner-complete?orderid="
		    + (!orderno.isEmpty()
			    ? orderno + "&client=" + CommonTask.encryptText(clienCode)
			    : "NA");
	    logger.info("Callback url for payment- " + callbackUrl);
	    orderUrl.setLogOutURL(callbackUrl);
	    BseOrderPaymentResponse orderUrlReponse = investmentConnectorBseInterface.getPaymentUrl(orderUrl);
	    map.addAttribute("orderUrl", orderUrlReponse);
	    map.addAttribute("TRANS_ID", transId);
	    map.addAttribute("MSG", transMessage);
	    map.addAttribute("TRANS_STATUS", "Payment Pending");
	    
	    session.removeAttribute("registryfundsipdates");
	    map.addAttribute("contextcdn", env.getProperty(CommonConstants.CDN_URL));
//	}
	return returnurl;
    }


    @RequestMapping(value = "/registry-planner-complete", method = RequestMethod.GET)
    public String registryplannerComplete(
	    @RequestParam("orderid") String orderid,
	    @RequestParam("client") String clientCode, Model map, HttpServletRequest request,
	    HttpServletResponse response, HttpSession session) {

	String returnurl="registry/registry-planner-status";
	logger.info("@@ BSE MF STAR Transaction status controller @@");

	try {
	    if (orderid.equalsIgnoreCase("")) {
		logger.info("registryplannerComplete(): Parameters data not found");
		returnurl = "redirect:/registry-planner";
	    } else {
		logger.info("bseMFTransactionCallback(): Transaction complete callback received for order id: " + orderid
			+ " : client: " + clientCode);

		String resp = investmentConnectorBseInterface.BseOrderPaymentStatus(CommonTask.decryptText(clientCode), orderid);

		List<String> res = Arrays.asList(resp.split("\\|"));
		logger.info("Payment status response- "+ res);
		map.addAttribute("TRANS_STATUS", res.get(1));
		map.addAttribute("ORDER_STATUS", res.get(1));
		
		
	    }

	}catch(Exception e) {
	    logger.error("Failed processing request...",e);

	    logger.info( "registryplannerComplete(): Checking order payment status after redirected from BSE callback url for orderid- " + orderid);
	    map.addAttribute("contextcdn", env.getProperty(CommonConstants.CDN_URL));
	}
	return returnurl;
    }

    @ModelAttribute("investmentType")
    public Map<String, String> getinvestmentType() {
	return InvestFormConstants.registryInvestmentype;
    }


    @ModelAttribute("officeRole")
    public Map<String, String> officeRoles() {
	return InvestFormConstants.registryOfficeRole;
    }

    @ModelAttribute("transportType")
    public Map<String, String> transportType() {
	return InvestFormConstants.registryTransaporttype;
    }

    @ModelAttribute("calendarmonths")
    public Map<Integer, String> getcalendarmonths() {
	return InvestFormConstants.bseInvestMonths;
    }
    @ModelAttribute("sipyear")
    public Map<Integer, String> getsipyear() {
	return InvestFormConstants.bseInvestStartYear;
    }
}
