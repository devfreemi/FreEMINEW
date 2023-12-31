package com.freemi.ui.controller.api;

import java.net.URI;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.freemi.common.util.CommonConstants;
import com.freemi.common.util.CommonTask;
import com.freemi.database.respository.mahindra.MahindraKycdocupdateform;
import com.freemi.entity.bse.BseOrderPaymentResponse;
import com.freemi.entity.database.UserBankDetails;
import com.freemi.entity.general.UserProfileLdap;
import com.freemi.entity.investment.BseMFSelectedFunds;
import com.freemi.entity.investment.ChosenMFund;
import com.freemi.entity.investment.MFInvestForm;
import com.freemi.entity.investment.MFInvestformAPI;
import com.freemi.entity.investment.MFInvestmentDates;
import com.freemi.entity.investment.MFNominationForm;
import com.freemi.entity.investment.MFmanagementapiresponse;
import com.freemi.entity.investment.MFpurchasefundAPI;
import com.freemi.entity.investment.MfNavData;
import com.freemi.entity.investment.PanValidationStatus;
import com.freemi.entity.investment.RegistryFunds;
import com.freemi.entity.investment.SelectMFFund;
import com.freemi.entity.investment.TransactionStatus;
import com.freemi.entity.investment.mahindra.MahindraFDListItem;
import com.freemi.entity.investment.mahindra.MahindraResponse;
import com.freemi.services.interfaces.Amcfundmanager;
import com.freemi.services.interfaces.BseEntryManager;
import com.freemi.services.interfaces.MahindraFDProfileService;
import com.freemi.services.interfaces.ProfileRestClientService;
import com.freemi.services.interfaces.RegistryManager;
import com.google.gson.Gson;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"*"})
public class MfDataController {

    private static final Logger logger = LogManager.getLogger(MfDataController.class);

    @Autowired
    BseEntryManager bseEntryManager;

    @Autowired
    RegistryManager registryManager;
    
    @Autowired
    Amcfundmanager amcfundmanager;

    @Autowired
    MahindraFDProfileService mahindraFDProfileService;

    @Autowired
    ProfileRestClientService profileRestClientService; 

    @PostMapping(value = "/navdata/{isin}", produces = "application/json")
    //    @CrossOrigin(origins = "https://www.freemi.in")
    @ResponseBody
    public String getNavDataForIsisn(@PathVariable(name = "isin") String isin, Model model, HttpServletRequest request,
	    HttpServletResponse httpResponse) {
	logger.info("Request received to fetch NAV data via API for ISIN - " + isin);
	//		String response ="SUCCESS";
	List<MfNavData> navhistorydata = null;
	//		List<String> data = null;
	String json = null;
	try {
	    if (!isin.isEmpty() || !isin.equalsIgnoreCase("null")) {

		//				JSONObject obj = new JSONObject(navData);
		//		        String pageName = obj.getJSONObject("pageInfo").getString("pageName");
		//		        isin = obj.getString("isin");
		//		        System.out.println(pageName);

		navhistorydata = bseEntryManager.getnavdataByISIN(isin);
		//				System.out.println(navhistorydata);
		logger.info("Returning total nav data- : " + navhistorydata.size());
		json = new Gson().toJson(navhistorydata);

	    } else {
		logger.info("No isin in api call- " + isin);
	    }

	} catch (Exception e) {
	    logger.error("Error reading ISIN nav data..", e);
	}
	httpResponse.setContentType("application/json");
	return json;
	//		return navhistorydata;
    }
    

    @PostMapping(value = "/get-ifsc-details/{ifsc}", produces = "application/json")
    //    @CrossOrigin(origins = "https://www.freemi.in")
    @ResponseBody
    public String getifscdetails(@PathVariable(name = "ifsc") String ifsc, Model model, HttpServletRequest request,
    		HttpServletResponse httpResponse) {
    	logger.info("Request received to fetch IFSC deatils for - " + ifsc);
    	String json=null;
    	try {
    		if (!ifsc.isEmpty() || !ifsc.equalsIgnoreCase("null")) {
    			json = bseEntryManager.getifscdetails(ifsc);
    		} else {
    			logger.info("No isin in api call- " + ifsc);
    		}

    	}catch(HttpClientErrorException e1) {
    		logger.info("URL processing error- "+ e1.getStatusCode());
    		throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
    	} catch (Exception e) {
    		logger.error("Error reading ISIN nav data..", e);
    	}
    	return json;
    }

    
    @RequestMapping(value = "/mutual-funds/register-customer", method = RequestMethod.POST)
    public MFmanagementapiresponse mfregistration(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

	logger.info("@@ Mutual fund registration request received through API @@");
	MFmanagementapiresponse apiresponse = new MFmanagementapiresponse();
	try {
		ObjectMapper mapper = new ObjectMapper();
		MFInvestformAPI registerdata = new  MFInvestformAPI();
		MFInvestForm form = new MFInvestForm();
		MFNominationForm nominee = new MFNominationForm();
		UserBankDetails bankDetails = new UserBankDetails();
		RegistryFunds selectedFund = new RegistryFunds();
		PanValidationStatus panValidationStatus = new PanValidationStatus();
		MFInvestmentDates mfInvestDates = new MFInvestmentDates();
		form.setNominee(nominee);
		form.setBankDetails(bankDetails);
		form.setPanValidationStatus(panValidationStatus);
		form.setMfInvestDates(mfInvestDates);
		form.setSelectedFund(selectedFund);
		registerdata.setRegistrationform(form);
		logger.info("Registration data- "+ mapper.writeValueAsString(registerdata));
		apiresponse.setMessage(CommonConstants.TASK_SUCCESS_S);
		
	} catch (Exception e) {
	    logger.error("bsePendingPayments(): Failed to get pending url links. Returning to dashboard", e);
	    apiresponse.setMessage(CommonConstants.TASK_FAILURE_S);
	}

	//		map.addAttribute("contextcdn", env.getProperty(CommonConstants.CDN_URL));
	logger.info("bsePendingPayments(): Return url- " + apiresponse);
	return apiresponse;
    }
    
    

    @RequestMapping(value = "/mutual-funds/purchase-fund", method = RequestMethod.POST)
    public MFmanagementapiresponse mfpurchasefund(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

	logger.info("@@ Mutual fund registration request received through API @@");
	MFmanagementapiresponse apiresponse = new MFmanagementapiresponse();
	try {
		ObjectMapper mapper = new ObjectMapper();
		SelectMFFund fund = new SelectMFFund();
		MFpurchasefundAPI funds = new MFpurchasefundAPI();
		funds.setSelectedfund(fund);
		logger.info("Fund- "+ mapper.writeValueAsString(funds));
		apiresponse.setMessage(CommonConstants.TASK_SUCCESS_S);
		
	} catch (Exception e) {
	    logger.error("bsePendingPayments(): Failed to get pending url links. Returning to dashboard", e);
	    apiresponse.setMessage(CommonConstants.TASK_FAILURE_S);
	}

	//		map.addAttribute("contextcdn", env.getProperty(CommonConstants.CDN_URL));
	logger.info("bsePendingPayments(): Return url- " + apiresponse);
	return apiresponse;
    }
    
    

    @RequestMapping(value = "/mutual-funds/pending-payments", method = RequestMethod.POST)
    public String bsePendingPayments(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

	logger.info("@@ BSE MF STAR pending payments clearance @@");
	String responseData = "SUCCESS";
	try {
	    if (session.getAttribute("userid") != null || session.getAttribute("token") != null) {
		String orderCallUrl = URI.create(request.getRequestURL().toString()).resolve(request.getContextPath())
			.toString() + "/my-dashboard";
		BseOrderPaymentResponse orderUrlReponse = bseEntryManager
			.getpendingPaymentLinks(session.getAttribute("userid").toString(), orderCallUrl);

		if (orderUrlReponse.getStatusCode().equals(CommonConstants.BSE_API_SERVICE_DISABLED)) {
		    responseData = "SERVICE_DISABLED";
		    logger.info(
			    "bsePendingPayments(): Services are currently disabled by Admin. Please try after sometime");
		} else if (orderUrlReponse.getStatusCode().equalsIgnoreCase("100")) {
		    responseData = orderUrlReponse.getPayUrl();
		} else {
		    logger.info("Unable to process payment request..  Return to dashboard... Response: "
			    + orderUrlReponse.getStatusCode() + " : " + orderUrlReponse.getPayUrl());
		    responseData = "NO_REGISTERED";
		}

		//				map.addAttribute("orderUrl", orderUrlReponse);
	    } else {
		//				returnUrl = "redirect:/login?ref="+ URLEncoder.encode(request.getRequestURL().toString(), StandardCharsets.UTF_8.toString());
		responseData = "NO_SESSION";
	    }

	} catch (Exception e) {
	    logger.error("bsePendingPayments(): Failed to get pending url links. Returning to dashboard", e);
	    responseData = "INTERNAL_ERROR";
	}

	//		map.addAttribute("contextcdn", env.getProperty(CommonConstants.CDN_URL));
	logger.info("bsePendingPayments(): Return url- " + responseData);
	return responseData;
    }


    @RequestMapping(value = "/mutual-funds/cancel-sip", method = RequestMethod.POST)
    public String bseCancekSIP(@RequestBody Map<String, String> requestbody, HttpServletRequest request, HttpServletResponse response, HttpSession session) {

	logger.info("@@ BSE MF STAR cancel SIP @@");
	String responseData = "SUCCESS";
	try {
	    if (session.getAttribute("token") != null && session.getAttribute("userid") != null) {
		if(requestbody.containsKey("orderno") && requestbody.containsKey("clientid") && requestbody.containsKey("transactionid")) {

		    TransactionStatus flag=  bseEntryManager.cancelSIPOrder(session.getAttribute("userid").toString(), requestbody.get("orderno"), requestbody.get("clientid"),requestbody.get("transactionid"), null, "SIP");
		    bseEntryManager.cancelSIPOrderStatus( requestbody.get("orderno"), requestbody.get("clientid"),requestbody.get("transactionid"));
		    if(flag.getSuccessFlag().equals("S")) {
			responseData="SUCCESS";
		    }else {
			responseData=flag.getStatusMsg();
		    }
		}else {
		    responseData="INVALID_DATA";
		}
	    }else {
		responseData="NO_SESSION";
	    }
	} catch (Exception e) {
	    logger.error("bseCancekSIP(): Failed to get pending url links. Returning to dashboard", e);
	    responseData = "INTERNAL_ERROR";
	}
	logger.info("bseCancekSIP(): Return response- " + responseData);
	return responseData;
    }


    @RequestMapping(value = "/registry/get-registry-funds", method = RequestMethod.POST)
    @ResponseBody
    public List<BseMFSelectedFunds> getregistryfunds(@RequestBody ChosenMFund requestbody, HttpServletRequest request, HttpServletResponse response, HttpSession session) {

	List<BseMFSelectedFunds> registryfunds=null;
	try {
		if(requestbody.getFundlist()!=null && requestbody.getFundlist().size()!=0) {
			logger.info("Total fund request- " + requestbody.getFundlist());
			registryfunds =  amcfundmanager.getSelectedfunddetails(requestbody.getFundlist());
		}else{
			logger.info("Fund list is empty.. Nothing to process");
		}
	}catch(Exception e) {
	    logger.error("Error getting registry funds",e);
	}

	return registryfunds;
    }


    @RequestMapping(value = "/fixed-deposit/get-fd-portfolios", method = RequestMethod.POST)
    @ResponseBody
    public Object getcustomerfdfolios(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {

    	logger.info("@@ FIXED DEPOSIT PORTFOLIO fetch @@");
    	List<MahindraFDListItem> result = null;
    	try {
    		if (session.getAttribute("userid") != null && session.getAttribute("token") != null) {

    			String mobile = request.getParameter("mobile");
    			logger.info("Request received to fetch FD details for user- "+ mobile);
    			if(mobile!=null && mobile.equalsIgnoreCase(session.getAttribute("userid").toString())) {

    				UserProfileLdap profile = profileRestClientService.getProfileData(mobile, session.getAttribute("token").toString(), CommonTask.getClientSystemIp(request));
    				logger.info("PAN- "+ profile.getPan());
    				if(profile!=null && profile.getPan1()!=null) {
    					result = mahindraFDProfileService.getMahidraFdList(mobile, profile.getPan1());
    					if(result == null || result.size()==0	) {
    						logger.warn("getcustomerfdfolios(): NO_DATA details. Return error");
    						return "NO_DATA";
    					}
    				}else {
    					logger.warn("getcustomerfdfolios(): No profile details or no PAN details. Return error");
    					return "NO_PAN";
    				}
    			}else {
    				logger.warn("getcustomerfdfolios(): Mobile no do not match with session. Return error");
    				return "INAVLID_REQUEST";
    			}

    		} else {
    			return "NO_SESSION";
    		}
    		//	    result = mahindraFDProfileService.getMahidraFdList("9051472645","CQOPS7539Z");
    	} catch (Exception e) {
    		logger.error("fdportfoliohistory(): Error processing request",e);
    		return "INTERNAL_ERROR";
    	}

    	logger.info("Returning result...");
    	return result;
    }


    @RequestMapping(value = "/fixed-deposit/mahindraapplpaymentstatus", method = RequestMethod.POST)
    @ResponseBody
    public Object fdpaymentstatus(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {

	logger.info("@@ FIXED DEPOSIT PORTFOLIO fetch @@");
	MahindraResponse result = null;
	try {
	    if (session.getAttribute("userid") != null && session.getAttribute("token") != null) {

		String mobile = request.getParameter("mobile");
		String applno = request.getParameter("appl_no");
		String nbfc = request.getParameter("NBFC");

		if(mobile!=null && mobile.equalsIgnoreCase(session.getAttribute("userid").toString())) {

		    try{
			result = mahindraFDProfileService.verifyPaymentStatus(null, mobile, request.getParameter("appl_no"),session.getAttribute("email")!=null?session.getAttribute("email").toString():null);
			if(response!=null) {
			    logger.info("Result received- "+result.getResultData1());
			    logger.info("Send FD payment result in JSON format");
			    //			    jsonObject = new JsonObject();
			    //			    jsonObject.addProperty("apimsg", response.getStatusMsg());
			    //			    result=jsonObject.toString();
			}else {
			    logger.info("Result is null...");
			    return "NO_DATA";
			}

		    }catch(Exception e) {
			logger.error("apiMFBalance(): Error requesting MF balance",e);
			return "INTERNAL_ERROR";
		    }

		}else {
		    return "INAVLID_REQUEST";
		}

	    } else {
		return "NO_SESSION";
	    }
	    //	    result = mahindraFDProfileService.getMahidraFdList("9051472645","CQOPS7539Z");
	} catch (Exception e) {
	    logger.error("fdportfoliohistory(): Error processing request",e);
	    return "INTERNAL_ERROR";
	}

	logger.info("Returning result...");
	return result;
    }

    
    
    @RequestMapping(value = "/fixed-deposit/get-fd-kyc-documents", method = RequestMethod.POST /*, consumes = MediaType.MULTIPART_FORM_DATA_VALUE*/)
    @ResponseBody
    public Object getfdkycdocuments(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
	 MahindraResponse res = new MahindraResponse();
	try {
	    String mobile  = request.getParameter("mobile");
	    if (session.getAttribute("userid") != null && session.getAttribute("token") != null) {
		
		if(mobile!=null && mobile.equalsIgnoreCase(session.getAttribute("userid").toString())) {
		    res= mahindraFDProfileService.getlatestkycdocuments(mobile);
	    }else {
		res.setStatusCode(CommonConstants.TASK_FAILURE);
		res.setStatusMsg("INVALID_REQUEST");
	    }
	    }else {
		res.setStatusCode(CommonConstants.TASK_FAILURE);
		res.setStatusMsg("NO_SESSION");
	    }
	    
	}catch(Exception e) {
	    logger.error("Error processing reuest",e);
	    res.setStatusCode(CommonConstants.TASK_FAILURE);
		res.setStatusMsg("INTERNAL_ERROR");
	}
	return res;
    }
    

    @RequestMapping(value = "/fixed-deposit/updatekycdoc", method = RequestMethod.POST /*, consumes = MediaType.MULTIPART_FORM_DATA_VALUE*/)
    @ResponseBody
    
    public Object updatemfdkycdocuments(
	    @RequestParam(required = false) String mobile,
	    @RequestBody(required = false) MultipartFile kycphotoproof,
	    @RequestBody(required = false) MultipartFile kycpanproof,
	    @RequestBody(required = false) MultipartFile cancelledcheque,
	    @RequestBody(required = false) MultipartFile addressproof ,
	    @RequestParam(required = false) String addressproofType, 
	    @RequestParam(required = false) String addressproofrefno,
	    HttpServletRequest request, HttpServletResponse response,HttpSession session) {

	String result="NA";
	MahindraResponse res = new MahindraResponse();
	
	try {
	    MahindraKycdocupdateform mfdkycdcupdateform = new MahindraKycdocupdateform();
	    if (session.getAttribute("userid") != null && session.getAttribute("token") != null) {
	
		if(mobile!=null && mobile.equalsIgnoreCase(session.getAttribute("userid").toString())) {
	
		    mfdkycdcupdateform.setMobile(mobile);
		    mfdkycdcupdateform.setKycphotoproof(!kycphotoproof.isEmpty()?kycphotoproof.getBytes():null);
		    mfdkycdcupdateform.setKycphotoproofOriginalFilename(!kycphotoproof.isEmpty()?kycphotoproof.getOriginalFilename():null);
		    
		    mfdkycdcupdateform.setKycpanproof(!kycpanproof.isEmpty()?kycpanproof.getBytes():null);
		    mfdkycdcupdateform.setKycpanproofOriginalFilename(!kycpanproof.isEmpty()?kycpanproof.getOriginalFilename():null);
		    
		    mfdkycdcupdateform.setCancelledcheque(!cancelledcheque.isEmpty()?cancelledcheque.getBytes():null);
		    mfdkycdcupdateform.setCanecelledchequeOriginalFilename(!cancelledcheque.isEmpty()?cancelledcheque.getOriginalFilename():null);
		    
		    mfdkycdcupdateform.setAddressproof(!addressproof.isEmpty()?addressproof.getBytes():null);
		    mfdkycdcupdateform.setKycaddressproofOriginalFilename(!addressproof.isEmpty()?addressproof.getOriginalFilename():null);
		    mfdkycdcupdateform.setAddressprooftype(addressproofType);
		    mfdkycdcupdateform.setAddressproofrefid(addressproofrefno);
		    //			    mfdkycdcupdateform.setAddressproofexpiry(addre);
		    
		    res= mahindraFDProfileService.updatecustomerkycdocuments(mfdkycdcupdateform);
		    logger.info("Return staus - "+ res.getStatusMsg());
//		    result = res.getStatusMsg();
	    }else {
	        result="Mobile no do not match! Request rejected.";
	        res.setStatusCode(CommonConstants.TASK_FAILURE);
	        res.setStatusMsg(result);
	    }
	    }else {
		result="Session Expired. Please login again";
		res.setStatusCode(CommonConstants.TASK_FAILURE);
	        res.setStatusMsg(result);
	    }
	
	}catch(Exception e) {
	    logger.error("Error processing reuest",e);
	    res.setStatusCode(CommonConstants.TASK_FAILURE);
	    res.setStatusMsg("INTERNAL_ERROR");
	}
	return res;
    }


}
