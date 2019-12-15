package com.freemi.ui.controller;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
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
import com.freemi.entity.investment.mahindra.MahindraFDTransactionStatus;
import com.freemi.entity.investment.mahindra.MahindraResponse;
import com.freemi.entity.investment.mahindra.MahindraSchemesList;
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
		List<MahindraFDTransactionStatus> purchaseHistoryList = null;
		try {
			if (session.getAttribute("userid").toString() != null || session.getAttribute("token").toString() != null) {
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

			getSchemeDetails(fdEntity, model);

		}catch(Exception e){
			logger.error("Error getting page- ",e);
		}

		model.addAttribute("contextcdn", environment.getProperty(CommonConstants.CDN_URL));
		model.addAttribute("states", InvestFormConstants.states);

		return "fixed-deposit/mahindra/mahindra-fd-purchase";
	}

	protected void getSchemeDetails(MahindraFDEntity fdEntity, Model model) {
		List<MahindraSchemesList> schemesList = mahindraFDServiceInterface.getSchemesListBasedOnCriteria(null, fdEntity.getScheme(), fdEntity.getCategory());
		SortedSet<Integer> tenureList = null;
		SortedSet<String> intfrequency = null;
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

		logger.info("postsaveFdpurchaseRequest(): Request Received to save mahindra fixed deposit for customer- "+ fdEntity.getMobile());
		String returnUrl ="fixed-deposit/mahindra/mahindra-fd-purchase";
		boolean taskdone=true;
		if (bindResult.hasErrors()) {
			logger.error("postsaveFdpurchaseRequest(): Error processing Mahindra FD request. Form validation error: ", bindResult.getFieldError().getDefaultMessage());
			model.addAttribute("error", bindResult.getFieldError().getDefaultMessage());
			
			//			Fetch scheme details
			getSchemeDetails(fdEntity, model);
			model.addAttribute("states", InvestFormConstants.states);

			model.addAttribute("contextcdn", environment.getProperty(CommonConstants.CDN_URL));
			return returnUrl;
		}

		MahindraResponse response1;
		MahindraResponse response2;
		MahindraResponse response3;
		MahindraResponse response4;
		MahindraResponse response5;
//		System.out.println("Interest rate- "+ fdEntity.getInterestRate());
		try {
			System.out.println("DOB- "+ fdEntity.getDob().toString());
			String getTransactionId = mahindraFDServiceInterface.generateTransactionId(null);

			response1 =  mahindraFDServiceInterface.saveCustomerDetails(fdEntity,getTransactionId);
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
				
				response5= mahindraFDServiceInterface.saveCustomerKYCDetails(fdEntity, customerid);
				logger.info("postsaveFdpurchaseRequest(): Save KYC details status- "+ response5.getStatusCode() + " : "+ response5.getStatusMsg());
				
				response4= mahindraFDServiceInterface.saveFDPurchaseDetails(fdEntity, getTransactionId, saveLeadDetailsResponseCode,addid,bankDetailsId, customerid );
				logger.info("postsaveFdpurchaseRequest(): Save purchase details status- "+ response4.getStatusCode() + " : "+ response4.getStatusMsg() );

				if(response4.getStatusCode()== CommonConstants.TASK_SUCCESS) {
					logger.info("Mahindra FD Application success... Redirect to payment status page with required parameters for mfSysRefNo: "+ response4.getMfSysRefNo());
					attrs.addFlashAttribute("mobile", fdEntity.getMobile());
					attrs.addFlashAttribute("applNo", response4.getApplicationNo());
					attrs.addFlashAttribute("cpTransRefNo",getTransactionId);
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
			getSchemeDetails(fdEntity, model);
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

	/*
	 * @ModelAttribute("tenureList") public List<String> getTenureList() {
	 * List<String> tenureList = new ArrayList<String>(); tenureList.add("15");
	 * tenureList.add("20"); tenureList.add("24"); tenureList.add("33");
	 * tenureList.add("40"); return tenureList; }
	 */
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



}
