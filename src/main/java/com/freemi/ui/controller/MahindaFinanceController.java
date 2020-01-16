package com.freemi.ui.controller;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.freemi.common.util.CommonConstants;
import com.freemi.common.util.InvestFormConstants;
import com.freemi.entity.investment.BsemfTransactionHistory;
import com.freemi.entity.investment.mahindra.MahindraApiResponse;
import com.freemi.entity.investment.mahindra.MahindraFDEntity;
import com.freemi.entity.investment.mahindra.MahindraFDRenewalEntity;
import com.freemi.entity.investment.mahindra.MahindraFDTransactionStatus;
import com.freemi.entity.investment.mahindra.MahindraResponse;
import com.freemi.entity.investment.mahindra.MahindraSchemesList;
import com.freemi.entity.investment.mahindra.Mahindrapurchasehistory;
import com.freemi.services.interfaces.MahindraFDServiceInterface;
import com.google.gson.Gson;

import javafx.collections.transformation.SortedList;
import okhttp3.internal.http.HttpDate;

@Controller
public class MahindaFinanceController {

    @Autowired
    private Environment environment;

    @Autowired
    MahindraFDServiceInterface mahindraFDServiceInterface;

    private static final Logger logger = LogManager.getLogger(MahindaFinanceController.class);

    @RequestMapping(value = {"/fixed-deposit/view-purchase-history"}, method = RequestMethod.GET)
    public String fdViewPurchaseHistory(Model model, HttpServletRequest request, HttpSession session) {

	logger.info("@@ FIXED DEPOSITY purchase history controller @@");
	String returnUrl = "fixed-deposit/fd-purchase-history";
	List<Mahindrapurchasehistory> purchaseHistoryList = null;
	try {
	    if (session.getAttribute("userid") != null || session.getAttribute("token") != null) {
		//					List<BsemfTransactionHistory> purchaseHistoryList = bseEntryManager.getAllPurchaseHistory(clientId);
		purchaseHistoryList = mahindraFDServiceInterface.getPurchaseHistory(session.getAttribute("userid").toString());
		if (purchaseHistoryList != null) {
		    // map.addAttribute("PURCHASE_LIST", "SUCCESS");
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
		/*
		 * map.addAttribute("TRANS_STATUS", transStatus);
		 * map.addAttribute("TRANS_ID",transId);
		 */
	    }
	} catch (Exception e) {
	    logger.error("fdViewPurchaseHistory(): Error processing request",e);
	    returnUrl = "redirect:/login";
	}

	model.addAttribute("contextcdn", environment.getProperty(CommonConstants.CDN_URL));
	return returnUrl;
    }


    @RequestMapping(value = {"/fixed-deposit","/fixed-deposit/"}, method = RequestMethod.GET)
    public String mmfdHomeGet(Model model, HttpServletRequest request, HttpSession session) {

	logger.info("Fixed depoit- "+ request.getRequestURI());
	model.addAttribute("fdform", new MahindraFDEntity());

	model.addAttribute("contextcdn", environment.getProperty(CommonConstants.CDN_URL));

	return "fixed-deposit/mahindra/mahindra-fd-home";
    }

    @RequestMapping(value = "/fixed-deposit/capture-fd-request", method = RequestMethod.POST)
    public String postFdpurchaseRequest(@ModelAttribute("fdform")MahindraFDEntity fdEntity,  Model model, HttpServletRequest request,RedirectAttributes attrs, HttpSession session) {

	logger.info("Fixed depoit- "+ request.getRequestURI() + " : Mobile: "+ fdEntity.getMobile());
	//		model.addAttribute("fdform", fdEntity);

	try {
	    attrs.addFlashAttribute("fdform", fdEntity);

	}catch(Exception e){
	    logger.error("Error ",e);
	}
	model.addAttribute("contextcdn", environment.getProperty(CommonConstants.CDN_URL));

	return "redirect:/fixed-deposit/mahindra-fd-purchase";
    }

    @RequestMapping(value = "/fixed-deposit/mahindra-fd-purchase", method = RequestMethod.GET)
    public String postFdpurchaseRequest1(@ModelAttribute("fdform")MahindraFDEntity fdEntity,  Model model, HttpServletRequest request, HttpSession session) {


	try {
	    logger.info("Fixed depoit- "+ request.getRequestURI() + " : Mobile: "+ fdEntity.getMobile());

	    if(fdEntity.getMobile() !=null) {

		fdEntity = mahindraFDServiceInterface.getRegisteredDetails(fdEntity);
		//				System.out.println("DOB of user- "+ fdEntity!=null?fdEntity.getDob().toString():"No data");
		//				System.out.println("Account number - "+ fdEntity.getAccountNumber());
		session.setAttribute("fdform", fdEntity);
	    }

	    if(session.getAttribute("fdform")!=null) {
		logger.info("Session data found...");
		fdEntity = (MahindraFDEntity) session.getAttribute("fdform");

		if(session.getAttribute("email")!=null) {
		    fdEntity.setEmail(session.getAttribute("email").toString());
		}

		model.addAttribute("fdform", fdEntity);
	    }else {
		logger.info("No data found to process for fd request in session... Redirecting to fill data again...");
		return "redirect:/fixed-deposit";
	    }

	    //			Fetch scheme details

	    getSchemeDetails(fdEntity.getScheme(), fdEntity.getCategory(), model);

	}catch(Exception e){
	    logger.error("Error getting page- ",e);
	}

	model.addAttribute("contextcdn", environment.getProperty(CommonConstants.CDN_URL));
	model.addAttribute("states", InvestFormConstants.states);

	return "fixed-deposit/mahindra/mahindra-fd-purchase";
    }

    protected void getSchemeDetails(String scheme, String category, Model model) {

	SortedSet<Integer> tenureList = null;
	SortedSet<String> intfrequency = null;

	List<MahindraSchemesList> schemesList = mahindraFDServiceInterface.getSchemesListBasedOnCriteria(null, scheme, category);
	if(schemesList!=null) {
	    String json = new Gson().toJson(schemesList);
	    model.addAttribute("schemeslist", schemesList);
	    model.addAttribute("schemeslistjson", json);
	    tenureList = new TreeSet<Integer>();
	    for(int i=0;i<schemesList.size();i++) {
		tenureList.add(schemesList.get(i).getTenuremonthto());
	    }

	    intfrequency = new TreeSet<String>();
	    for(int i=0;i<schemesList.size();i++) {
		//					tenureList.add(schemesList.get(i).getTenuremonthto());
		intfrequency.add(schemesList.get(i).getInterestfrequency());
	    }

	    logger.info("MMFD Tenure lists - "+ tenureList + " : frequency "+ intfrequency);
	    model.addAttribute("tenurelist", tenureList);
	    model.addAttribute("interestFrequency", intfrequency);
	    /*
	     * if(fdEntity.getScheme().equals("NC")) { SortedSet<Integer> interestFrequency
	     * = null; for(int i=0;i<schemesList.size();i++) {
	     * tenureList.add(schemesList.get(i).getTenuremonthto()); } }
	     */
	    /*
	     * if(fdEntity.getAddressDistrict1()!=null) {
	     * model.addAttribute("availdistrict", fdEntity.getAddressDistrict1()); }
	     */
	}else {
	    logger.warn("No schemes list found for category. Transaction may not be completed..");
	}
    }



    @RequestMapping(value = "/fixed-deposit/mahindra-fd-purchase", method = RequestMethod.POST)
    public String postsaveFdpurchaseRequest(@ModelAttribute("fdform")@Valid MahindraFDEntity fdEntity, BindingResult bindResult, Model model, HttpServletRequest request, HttpSession session,RedirectAttributes attrs) {

	logger.info("postsaveFdpurchaseRequest(): .........................Request Received to save mahindra fixed deposit for customer- "+ fdEntity.getMobile());
	String returnUrl ="fixed-deposit/mahindra/mahindra-fd-purchase";
	boolean taskdone=true;
	if (bindResult.hasErrors()) {
	    logger.error("postsaveFdpurchaseRequest(): Error processing Mahindra FD request. Form validation error: ", bindResult.getFieldError().getDefaultMessage());
	    model.addAttribute("error", bindResult.getFieldError().getDefaultMessage());

	    //			Fetch scheme details
	    getSchemeDetails(fdEntity.getScheme(), fdEntity.getCategory(), model);
	    model.addAttribute("states", InvestFormConstants.states);

	    model.addAttribute("contextcdn", environment.getProperty(CommonConstants.CDN_URL));
	    return returnUrl;
	}

	/*
	 * try { System.out.println("Trying form tax foreign size- "+
	 * fdEntity.getForeignTaxDetails()!=null?fdEntity.getForeignTaxDetails().size():
	 * "NULL"); System.out.println("Citizenshirp = "+ fdEntity.getCitizenship());
	 * }catch(Exception e) { e.printStackTrace(); }
	 */

	MahindraResponse response1;
	MahindraResponse response2;
	MahindraResponse response3;
	MahindraResponse response4;
	MahindraResponse response5;
	MahindraResponse response6 = null;



	//		System.out.println("Interest rate- "+ fdEntity.getInterestRate());
	try {
	    logger.info("Submitted DOB- "+ fdEntity.getDob().toString());
	    logger.info("Submitted Nomiee DOB- "+ fdEntity.getNomineedob().toString());

	    String getcpTransactionId=null;
	    getcpTransactionId = fdEntity.getCpTrnasRefNo();
	    if(getcpTransactionId.isEmpty()) {
		logger.info("CP Transaction ID is null.. Generating new one...");
		getcpTransactionId = mahindraFDServiceInterface.generateCpTransctionId();
		fdEntity.setCpTrnasRefNo(getcpTransactionId);
	    }
	    logger.info("CP Trans ID for the transaction - "+ getcpTransactionId);

	    response1 =  mahindraFDServiceInterface.saveCustomerDetails(fdEntity,null);
	    logger.info("postsaveFdpurchaseRequest(): Save customer details status- "+ response1.getStatusCode() + " : "+ response1.getStatusMsg() );
	    String customerid = response1.getResultData1();

	    //			Test purpose
	    //			String saveLeadDetailsResponseCode =  Long.toString(Calendar.getInstance().getTimeInMillis());

	    if(response1.getStatusCode() == CommonConstants.TASK_SUCCESS || response1.getStatusCode() == CommonConstants.TASK_SKIPPED || response1.getStatusCode() == CommonConstants.TASK_UPDATED) {

		String saveLeadDetailsResponseCode =  null;
		response2 = mahindraFDServiceInterface.saveCustomerAddress(fdEntity, customerid);
		logger.info("Customer address save status- "+ response2.getStatusCode() + " : Addree ID to map for transaction-"+ response2.getResultData1());
		String addid= response2.getResultData1();

		response3 = mahindraFDServiceInterface.saveBankDetails(fdEntity, customerid);
		String bankDetailsId= response3.getResultData1();

		logger.info("Customer address save status- "+ response3.getStatusCode() + " : Bank details ID to map for transaction- "+ response3.getResultData1());

		logger.info("Is nominee chosen- "+ fdEntity.isNomineechosen());
		if(fdEntity.isNomineechosen()) {
		    response6 = mahindraFDServiceInterface.saveCustomerNominee(fdEntity, customerid);

		}else{
		    logger.info("Nominee not chosen.. Processing will be ignored...");
		}


		response5= mahindraFDServiceInterface.saveCustomerKYCDetails(fdEntity, customerid);
		logger.info("postsaveFdpurchaseRequest(): Save KYC details status- "+ response5.getStatusCode() + " : "+ response5.getStatusMsg());


		if(fdEntity.getTaxResidentOtherCountry().equals("YES")) {
		    logger.info("Customer chosenn to provide TAX resident details outside India");
		    if(fdEntity.getForeignTaxDetails()!=null && fdEntity.getForeignTaxDetails().size()>0) {
			mahindraFDServiceInterface.saveOtherCountryTaxDetails(fdEntity, customerid);
		    }else {
			logger.info("No records inserted fot TAX details outside India.. Skipping save process..");
		    }
		}else {
		    logger.info("No Tax resident details outside India..");
		}




		response4= mahindraFDServiceInterface.saveFDPurchaseDetails(fdEntity, null, saveLeadDetailsResponseCode,addid,bankDetailsId,(response6!=null? response6.getResultData1():null), customerid,getcpTransactionId );
		logger.info("postsaveFdpurchaseRequest(): Save purchase details status- "+ response4.getStatusCode() + " : "+ response4.getStatusMsg() );

		if(response4.getStatusCode()== CommonConstants.TASK_SUCCESS) {

		    logger.info("FD Application request successful. Upload KYC dcouments to database and upload to Mahindra..");
		    //					Save  KYC dosuments...
		    fdEntity.setMfSysRefNo(response4.getMfSysRefNo());
		    mahindraFDServiceInterface.mahindrauploadDocument(fdEntity.getMobile(), customerid, fdEntity,getcpTransactionId,response4.getMfSysRefNo(),response4.getApplicationNo());
		    logger.info("Mahindra FD Application success and document upload process complete... Redirect to payment status page with required parameters for mfSysRefNo: "+ response4.getMfSysRefNo());

		    attrs.addFlashAttribute("mobile", fdEntity.getMobile());
		    attrs.addFlashAttribute("applNo", response4.getApplicationNo());
		    attrs.addFlashAttribute("cpTransRefNo",getcpTransactionId);
		    attrs.addFlashAttribute("mfSysRefNo", response4.getMfSysRefNo());
		    attrs.addFlashAttribute("purchasestatus", "PROCESS_PAY");

		    returnUrl="redirect:/fixed-deposit/purchase-status";


		}else {
		    logger.info("Failed to save transaction details...");
		    taskdone =false;
		    if(response4.getStatusCode()== CommonConstants.TASK_API_RESPONSE_ERROR) {
			if(response4.getStatusMsg().equals("API_RESPONSE_ERROR_SAVE_DETAILS")) {
			    model.addAttribute("error", "Unable to connect to services. Kindly try after sometine.");
			}else if(response4.getStatusMsg().equals("API_RESPONSE_ERROR_SAVETRANSACTION_DETAILS")) {
			    model.addAttribute("error", "Failed to complete your transaction. Kindly try again.");
			}else {
			    model.addAttribute("error", "Processing incomplete. Kindly contact admin/try again.");
			}
		    }else if(response4.getStatusCode()== CommonConstants.TASK_FAILURE) {
			if(response4.getStatusMsg().equals("INTERNAL_ERROR")) {
			    model.addAttribute("error", "Failed to save your request. Kindly try again");
			}else {
			    model.addAttribute("error", "Internal error. Please try again.");
			}

		    }else {
			model.addAttribute("error", "Unable to process request currently. Admin will be notified.");
		    }
		}
	    }else {
		logger.info("Failed to save basic customer details.. Skipping rest of the process");
		model.addAttribute("error", "Unable to save customer details. Please try again");
		taskdone = false;
	    }
	}catch(Exception e){
	    logger.error("Error getting page- ",e);
	}

	if(!taskdone) {
	    logger.info("Task not done. Refetch data...");
	    getSchemeDetails(fdEntity.getScheme(), fdEntity.getCategory(), model);
	    model.addAttribute("states", InvestFormConstants.states);
	}

	model.addAttribute("contextcdn", environment.getProperty(CommonConstants.CDN_URL));
	model.addAttribute("states", InvestFormConstants.states);

	return returnUrl;
    }


    @RequestMapping(value = {"/fixed-deposit/purchase-status","/fixed-deposit/purchase-status/"}, method = RequestMethod.GET)
    public String mmfdPurchaseStatus(@ModelAttribute(name = "mobile") String mobile,@ModelAttribute(name = "cpTransRefNo") String cpTransRefNo,@ModelAttribute(name = "applNo") String applNo,@ModelAttribute(name = "mfSysRefNo") String mfSysRefNo,  @ModelAttribute(name = "purchasestatus") String purchasestatus, @RequestParam(name = "id", required = false) String id, @RequestParam(name = "mobileid", required = false) String mobileid,@RequestParam(name = "dir", required = false)String direction,@RequestParam(name = "msg", required = false)String pgMsg, Model model, HttpServletRequest request, HttpSession session) {

	logger.info("mmfdPurchaseStatus(): Fixed deposit purchase status - "+ request.getRequestURI() +" : mobile: "+ mobile +  " : purchaseSatus: "+ purchasestatus + " : APPL_NO- "+ applNo + " : mfSysRefNo" +mfSysRefNo );
	logger.info("mmfdPurchaseStatus(): "+ id + " -> "+ direction + " -> "+ mobileid);
	MahindraResponse response5;
	try {
	    if(purchasestatus!=null && purchasestatus.equals("PROCESS_PAY")) {
		model.addAttribute("VIEW_STATUS", "PAYLINK");
		logger.info("FD application success.. Proceed to fetch payment link");
		model.addAttribute("transactionid",applNo);
		model.addAttribute("paymentcomplete","No");

		String callbackUrl = URI.create(request.getRequestURL().toString()).resolve(request.getContextPath()).toString()+ "/fixed-deposit/purchase-status?id="+ applNo+ "&mobileid=" + mobile;
		logger.info("Callback URL formed- "+ callbackUrl);
		response5 = mahindraFDServiceInterface.getMMFDpaymentLink(null, mobile, callbackUrl,cpTransRefNo, mfSysRefNo, applNo);
		if(response5.getStatusCode() == CommonConstants.TASK_SUCCESS) {
		    model.addAttribute("paymentLink", response5.getPaymentLink());
		    //					model.addAttribute("paymentLink", "http://localhost:8080/products/fixed-deposit/purchase-status?id="+applNo+"&mobileid="+mobile+"&dir=psc");

		}else {
		    model.addAttribute("VIEW_STATUS", "EXCEPTION");
		}
	    }else if(id!=null && direction!=null && direction.equalsIgnoreCase("psc")) {

		Enumeration<String> params = request.getParameterNames(); 
		while(params.hasMoreElements()){
		    String paramName = params.nextElement();
		    logger.info("Received Parameter Name - "+paramName+", Value - "+request.getParameter(paramName));
		}

		logger.info("Request received after payment is complete and redicted to callback URL for application no- "+ id + " : msg-> "+ pgMsg);
		logger.info("Verify the payment status for the application no-");
		model.addAttribute("VIEW_STATUS", "PAYMENT_CALLBACK_STATUS");
		logger.info("Response from API gateway..");
		MahindraResponse apiresp6= mahindraFDServiceInterface.verifyPaymentResponse(null, mobileid, "TO_MAP_FROM_RESPONSE", id);
		logger.info("Purchase complete for MF request ID- "+ id + " : " + apiresp6.getStatusCode() + " : "+ apiresp6.getStatusMsg());

		mahindraFDServiceInterface.mailAcknowledmentDocumentPostSuccessApplication(id, null, mobileid,session.getAttribute("email")!=null?session.getAttribute("email").toString():null,"");

		model.addAttribute("transactionid",id);
		model.addAttribute("paymentcomplete","Yes");

	    }else {
		logger.info("No data received for peocessing....");
		model.addAttribute("VIEW_STATUS", "ERROR");
		model.addAttribute("transactionid","NA");
		model.addAttribute("paymentcomplete","NA");
	    }



	}catch(Exception e) {
	    logger.error("mmfdPurchaseStatus(): Error processing payment complete status",e);
	    model.addAttribute("VIEW_STATUS", "EXCEPTION");

	}
	model.addAttribute("contextcdn", environment.getProperty(CommonConstants.CDN_URL));

	return "fixed-deposit/mahindra/fixed-deposit-purchase-complete";
    }

    @RequestMapping(value = {"/fixed-deposit-renew","/fixed-deposit-renew/"}, method = RequestMethod.GET)
    public String renewapplication(@Param("mobile")String mobile, @Param("appno")String applicationno,@Param("scheme")String scheme,@Param("category")String category, @ModelAttribute("renewform")MahindraFDRenewalEntity renewalentity, Model model, HttpServletRequest request, HttpSession session) {


	getSchemeDetails(scheme,category, model);
	model.addAttribute("states", InvestFormConstants.states);
	String datafound="Y";
	renewalentity=   mahindraFDServiceInterface.getFDRenewalDetails(null, mobile, applicationno);
	if(renewalentity!=null) {

	}else {
	    datafound="NOT_EXIT";
	}
	model.addAttribute("DATA_FOUND", datafound); 
	//	   model.addAttribute("renewform", renewalentity);

	return "fixed-deposit/mahindra/mahindra-fd-purchase";

    }

    @ModelAttribute("mmoccupation")
    public Map<String, String> getOccupationList() {
	Map<String, String> occupationList = new TreeMap<String,String>();
	occupationList.put("B-01", "Business");
	occupationList.put("O-01", "Others - Professional");
	occupationList.put("O-02", "Others - Self Employed");
	occupationList.put("O-03", "Others - Retired");
	occupationList.put("O-04", "Others - Housewife");
	occupationList.put("O-05", "Others - Student");
	occupationList.put("S-01", "Service - Public Sector");
	occupationList.put("S-02", "Service - Private Sector");
	occupationList.put("S-03","Service - Government Sector");
	occupationList.put("X-01", "Not Categorized");
	//		occupationList.put("Unknown / Not Applicable", "Unknown / Not Applicable");
	return occupationList;
    }

    @ModelAttribute("relationMaster")
    public Map<String, String> getrelationshipmaster() {
	Map<String, String> relationlist = new TreeMap<String,String>();
	relationlist.put("GRM","GRAND MOTHER");
	relationlist.put("BRO","BROTHER");
	relationlist.put("NEI","NEICE");
	relationlist.put("DAU","DAUGHTER");
	relationlist.put("FAT","FATHER");
	relationlist.put("GRD","GRAND DAUGHTER");
	relationlist.put("HUS","HUSBAND");
	relationlist.put("MOT","MOTHER");
	relationlist.put("NEP","NEPHEW");
	relationlist.put("OTH","OTHERS");
	relationlist.put("GRS","GRAND SON");
	relationlist.put("SON","SON");
	relationlist.put("SIS","SISTER");
	relationlist.put("WIF","WIFE");

	return relationlist;

    }

    @ModelAttribute("ckycovdmaster")
    public Map<String, String> getckycovdmaster() {
	Map<String, String> imagetype = new TreeMap<String,String>();
	imagetype.put("A","Passport");
	imagetype.put("B","Voter ID");
	imagetype.put("C","PAN");
	imagetype.put("D","Driving License");
	imagetype.put("E","UID");
	imagetype.put("F","NREGA Job Card");
	imagetype.put("Z","CKYC Identifier");
	imagetype.put("MFPH","Photograph");
	imagetype.put("MFCC","Bank Cancelled Cheque");
	imagetype.put("MF15GH","Form 15GH");
	imagetype.put("MFTRC","Tax Residency Certificate");

	return imagetype;

    }

    @ModelAttribute("ckyccountrymaster")
    public Map<String, String> getckyccountrymaster() {
	Map<String, String> countrylist = new TreeMap<String,String>();
	countrylist.put("AF","Afghanistan");
	countrylist.put("AX","Ã…land Islands");
	countrylist.put("AL","Albania");
	countrylist.put("DZ","Algeria");
	countrylist.put("AS","American Samoa");
	countrylist.put("AD","Andorra");
	countrylist.put("AO","Angola");
	countrylist.put("AI","Anguilla");
	countrylist.put("AQ","Antarctica");
	countrylist.put("AG","Antigua and Barbuda");
	countrylist.put("AR","Argentina");
	countrylist.put("AM","Armenia");
	countrylist.put("AW","Aruba");
	countrylist.put("AU","Australia");
	countrylist.put("AT","Austria");
	countrylist.put("AZ","Azerbaijan");
	countrylist.put("BS","Bahamas (the)");
	countrylist.put("BH","Bahrain");
	countrylist.put("BD","Bangladesh");
	countrylist.put("BB","Barbados");
	countrylist.put("BY","Belarus");
	countrylist.put("BE","Belgium");
	countrylist.put("BZ","Belize");
	countrylist.put("BJ","Benin");
	countrylist.put("BM","Bermuda");
	countrylist.put("BT","Bhutan");
	countrylist.put("BO","Bolivia (Plurinational State of)");
	countrylist.put("BQ","Bonaire, Sint Eustatius and Saba");
	countrylist.put("BA","Bosnia and Herzegovina");
	countrylist.put("BW","Botswana");
	countrylist.put("BV","Bouvet Island");
	countrylist.put("BR","Brazil");
	countrylist.put("IO","British Indian Ocean Territory (the)");
	countrylist.put("BN","Brunei Darussalam");
	countrylist.put("BG","Bulgaria");
	countrylist.put("BF","Burkina Faso");
	countrylist.put("BI","Burundi");
	countrylist.put("CV","Cabo Verde");
	countrylist.put("KH","Cambodia");
	countrylist.put("CM","Cameroon");
	countrylist.put("CA","Canada");
	countrylist.put("KY","Cayman Islands (the)");
	countrylist.put("CF","Central African Republic (the)");
	countrylist.put("TD","Chad");
	countrylist.put("CL","Chile");
	countrylist.put("CN","China");
	countrylist.put("CX","Christmas Island");
	countrylist.put("CC","Cocos (Keeling) Islands (the)");
	countrylist.put("CO","Colombia");
	countrylist.put("KM","Comoros (the)");
	countrylist.put("CD","Congo (the Democratic Republic of the)");
	countrylist.put("CG","Congo (the)");
	countrylist.put("CK","Cook Islands (the)");
	countrylist.put("CR","Costa Rica");
	countrylist.put("CI","CÃ´te dIvoire");
	countrylist.put("HR","Croatia");
	countrylist.put("CU","Cuba");
	countrylist.put("CW","CuraÃ§ao");
	countrylist.put("CY","Cyprus");
	countrylist.put("CZ","Czechia");
	countrylist.put("DK","Denmark");
	countrylist.put("DJ","Djibouti");
	countrylist.put("DM","Dominica");
	countrylist.put("DO","Dominican Republic (the)");
	countrylist.put("EC","Ecuador");
	countrylist.put("EG","Egypt");
	countrylist.put("SV","El Salvador");
	countrylist.put("GQ","Equatorial Guinea");
	countrylist.put("ER","Eritrea");
	countrylist.put("EE","Estonia");
	countrylist.put("SZ","Eswatini");
	countrylist.put("ET","Ethiopia");
	countrylist.put("FK","Falkland Islands (the) [Malvinas]");
	countrylist.put("FO","Faroe Islands (the)");
	countrylist.put("FJ","Fiji");
	countrylist.put("FI","Finland");
	countrylist.put("FR","France");
	countrylist.put("GF","French Guiana");
	countrylist.put("PF","French Polynesia");
	countrylist.put("TF","French Southern Territories (the)");
	countrylist.put("GA","Gabon");
	countrylist.put("GM","Gambia (the)");
	countrylist.put("GE","Georgia");
	countrylist.put("DE","Germany");
	countrylist.put("GH","Ghana");
	countrylist.put("GI","Gibraltar");
	countrylist.put("GR","Greece");
	countrylist.put("GL","Greenland");
	countrylist.put("GD","Grenada");
	countrylist.put("GP","Guadeloupe");
	countrylist.put("GU","Guam");
	countrylist.put("GT","Guatemala");
	countrylist.put("GG","Guernsey");
	countrylist.put("GN","Guinea");
	countrylist.put("GW","Guinea-Bissau");
	countrylist.put("GY","Guyana");
	countrylist.put("HT","Haiti");
	countrylist.put("HM","Heard Island and McDonald Islands");
	countrylist.put("VA","Holy See (the)");
	countrylist.put("HN","Honduras");
	countrylist.put("HK","Hong Kong");
	countrylist.put("HU","Hungary");
	countrylist.put("IS","Iceland");
	countrylist.put("IN","India");
	countrylist.put("ID","Indonesia");
	countrylist.put("IR","Iran (Islamic Republic of)");
	countrylist.put("IQ","Iraq");
	countrylist.put("IE","Ireland");
	countrylist.put("IM","Isle of Man");
	countrylist.put("IL","Israel");
	countrylist.put("IT","Italy");
	countrylist.put("JM","Jamaica");
	countrylist.put("JP","Japan");
	countrylist.put("JE","Jersey");
	countrylist.put("JO","Jordan");
	countrylist.put("KZ","Kazakhstan");
	countrylist.put("KE","Kenya");
	countrylist.put("KI","Kiribati");
	countrylist.put("KP","Korea (the Democratic People's Republic of)");
	countrylist.put("KR","Korea (the Republic of)");
	countrylist.put("KW","Kuwait");
	countrylist.put("KG","Kyrgyzstan");
	countrylist.put("LA","Lao People's Democratic Republic (the)");
	countrylist.put("LV","Latvia");
	countrylist.put("LB","Lebanon");
	countrylist.put("LS","Lesotho");
	countrylist.put("LR","Liberia");
	countrylist.put("LY","Libya");
	countrylist.put("LI","Liechtenstein");
	countrylist.put("LT","Lithuania");
	countrylist.put("LU","Luxembourg");
	countrylist.put("MO","Macao");
	countrylist.put("MK","Macedonia (the former Yugoslav Republic of)");
	countrylist.put("MG","Madagascar");
	countrylist.put("MW","Malawi");
	countrylist.put("MY","Malaysia");
	countrylist.put("MV","Maldives");
	countrylist.put("ML","Mali");
	countrylist.put("MT","Malta");
	countrylist.put("MH","Marshall Islands (the)");
	countrylist.put("MQ","Martinique");
	countrylist.put("MR","Mauritania");
	countrylist.put("MU","Mauritius");
	countrylist.put("YT","Mayotte");
	countrylist.put("MX","Mexico");
	countrylist.put("FM","Micronesia (Federated States of)");
	countrylist.put("MD","Moldova (the Republic of)");
	countrylist.put("MC","Monaco");
	countrylist.put("MN","Mongolia");
	countrylist.put("ME","Montenegro");
	countrylist.put("MS","Montserrat");
	countrylist.put("MA","Morocco");
	countrylist.put("MZ","Mozambique");
	countrylist.put("MM","Myanmar");
	countrylist.put("NA","Namibia");
	countrylist.put("NR","Nauru");
	countrylist.put("NP","Nepal");
	countrylist.put("NL","Netherlands (the)");
	countrylist.put("NC","New Caledonia");
	countrylist.put("NZ","New Zealand");
	countrylist.put("NI","Nicaragua");
	countrylist.put("NE","Niger (the)");
	countrylist.put("NG","Nigeria");
	countrylist.put("NU","Niue");
	countrylist.put("NF","Norfolk Island");
	countrylist.put("MP","Northern Mariana Islands (the)");
	countrylist.put("NO","Norway");
	countrylist.put("OM","Oman");
	countrylist.put("PK","Pakistan");
	countrylist.put("PW","Palau");
	countrylist.put("PS","Palestine, State of");
	countrylist.put("PA","Panama");
	countrylist.put("PG","Papua New Guinea");
	countrylist.put("PY","Paraguay");
	countrylist.put("PE","Peru");
	countrylist.put("PH","Philippines (the)");
	countrylist.put("PN","Pitcairn");
	countrylist.put("PL","Poland");
	countrylist.put("PT","Portugal");
	countrylist.put("PR","Puerto Rico");
	countrylist.put("QA","Qatar");
	countrylist.put("RE","RÃ©union");
	countrylist.put("RO","Romania");
	countrylist.put("RU","Russian Federation (the)");
	countrylist.put("RW","Rwanda");
	countrylist.put("BL","Saint BarthÃ©lemy");
	countrylist.put("SH","Saint Helena, Ascension and Tristan da Cunha");
	countrylist.put("KN","Saint Kitts and Nevis");
	countrylist.put("LC","Saint Lucia");
	countrylist.put("MF","Saint Martin (French part)");
	countrylist.put("PM","Saint Pierre and Miquelon");
	countrylist.put("VC","Saint Vincent and the Grenadines");
	countrylist.put("WS","Samoa");
	countrylist.put("SM","San Marino");
	countrylist.put("ST","Sao Tome and Principe");
	countrylist.put("SA","Saudi Arabia");
	countrylist.put("SN","Senegal");
	countrylist.put("RS","Serbia");
	countrylist.put("SC","Seychelles");
	countrylist.put("SL","Sierra Leone");
	countrylist.put("SG","Singapore");
	countrylist.put("SX","Sint Maarten (Dutch part)");
	countrylist.put("SK","Slovakia");
	countrylist.put("SI","Slovenia");
	countrylist.put("SB","Solomon Islands");
	countrylist.put("SO","Somalia");
	countrylist.put("ZA","South Africa");
	countrylist.put("GS","South Georgia and the South Sandwich Islands");
	countrylist.put("SS","South Sudan");
	countrylist.put("ES","Spain");
	countrylist.put("LK","Sri Lanka");
	countrylist.put("SD","Sudan (the)");
	countrylist.put("SR","Suriname");
	countrylist.put("SJ","Svalbard and Jan Mayen");
	countrylist.put("SE","Sweden");
	countrylist.put("CH","Switzerland");
	countrylist.put("SY","Syrian Arab Republic");
	countrylist.put("TW","Taiwan (Province of China)");
	countrylist.put("TJ","Tajikistan");
	countrylist.put("TZ","Tanzania, United Republic of");
	countrylist.put("TH","Thailand");
	countrylist.put("TL","Timor-Leste");
	countrylist.put("TG","Togo");
	countrylist.put("TK","Tokelau");
	countrylist.put("TO","Tonga");
	countrylist.put("TT","Trinidad and Tobago");
	countrylist.put("TN","Tunisia");
	countrylist.put("TR","Turkey");
	countrylist.put("TM","Turkmenistan");
	countrylist.put("TC","Turks and Caicos Islands (the)");
	countrylist.put("TV","Tuvalu");
	countrylist.put("UG","Uganda");
	countrylist.put("UA","Ukraine");
	countrylist.put("AE","United Arab Emirates (the)");
	countrylist.put("GB","United Kingdom of Great Britain and Northern Ireland (the)");
	countrylist.put("UM","United States Minor Outlying Islands (the)");
	countrylist.put("US","United States of America (the)");
	countrylist.put("UY","Uruguay");
	countrylist.put("UZ","Uzbekistan");
	countrylist.put("VU","Vanuatu");
	countrylist.put("VE","Venezuela (Bolivarian Republic of)");
	countrylist.put("VN","Viet Nam");
	countrylist.put("VG","Virgin Islands (British)");
	countrylist.put("VI","Virgin Islands (U.S.)");
	countrylist.put("WF","Wallis and Futuna");
	countrylist.put("EH","Western Sahara*");
	countrylist.put("YE","Yemen");
	countrylist.put("ZM","Zambia");
	countrylist.put("ZW","Zimbabwe");
	return countrylist;

    }



}
