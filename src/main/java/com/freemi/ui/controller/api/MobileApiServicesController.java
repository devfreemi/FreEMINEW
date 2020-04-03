package com.freemi.ui.controller.api;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.freemi.common.util.CommonConstants;
import com.freemi.common.util.CommonTask;
import com.freemi.common.util.InvestFormConstants;
import com.freemi.database.interfaces.ProductSchemeDetailService;
import com.freemi.entity.bse.BseApiResponse;
import com.freemi.entity.general.ClientSystemDetails;
import com.freemi.entity.investment.AofSignaure;
import com.freemi.entity.investment.MFAdditionalPurchaseForm;
import com.freemi.entity.investment.MFCustomers;
import com.freemi.entity.investment.MFRedeemForm;
import com.freemi.entity.investment.MfNavData;
import com.freemi.entity.investment.SelectMFFund;
import com.freemi.services.interfaces.BseEntryManager;
import com.freemi.services.interfaces.InvestmentConnectorBseInterface;
import com.freemi.services.interfaces.MailSenderInterface;
import com.freemi.services.interfaces.ProfileRestClientService;
import com.google.gson.Gson;

@RestController
@RequestMapping("/serviceapi")
public class MobileApiServicesController {

    private static final Logger logger = LogManager.getLogger(FormDataController.class);

    @Autowired
    ProductSchemeDetailService productSchemeDetailService;

    @Autowired
    ProfileRestClientService profileRestClientService;

    @Autowired
    private BseEntryManager bseEntryManager;

    @Autowired
    InvestmentConnectorBseInterface investmentConnectorBseInterface;

    @Autowired
    Environment env;

    @Autowired
    MailSenderInterface mailSenderInterface;

    SimpleDateFormat dbformat = new SimpleDateFormat("yyyy-mm-dd");
    SimpleDateFormat apiformat = new SimpleDateFormat("dd/mm/yyyy");

    @PostMapping(value = "/navdata/{isin}", produces = "application/json")
    @ResponseBody
    public String getNavDataForIsisn(@PathVariable(name = "isin") String isin, Model model, HttpServletRequest request,
	    HttpServletResponse httpResponse) {
	logger.info("Request received to fetch NAV data via API for ISIN - " + isin);
	List<MfNavData> navhistorydata = null;
	String json = null;
	try {
	    if (!isin.isEmpty() || !isin.equalsIgnoreCase("null")) {

		navhistorydata = bseEntryManager.getnavdataByISIN(isin);
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
    }

    @RequestMapping(value = "/mutual-funds/form-dropdown-values", method = RequestMethod.POST)
    @ResponseBody
    public Object bseRegisterFormDropDownOptions(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

	logger.info("API request received for - "+ request.getRequestURI());

	Map<String, Map<String,String>> alloptions = new HashMap<String,Map<String,String>>();  
	try {
	    alloptions.put("taxStatus", InvestFormConstants.taxStatus);
	    alloptions.put("holingNature", InvestFormConstants.holdingMode);
	    alloptions.put("dividendPayMode", InvestFormConstants.dividendPayMode);
	    alloptions.put("occupation", InvestFormConstants.occupationList);
	    alloptions.put("bankNames", InvestFormConstants.bankNames);
	    alloptions.put("accountTypes", InvestFormConstants.accountTypes);
	    alloptions.put("states", InvestFormConstants.states);
	    alloptions.put("wealthSource", InvestFormConstants.fatcaWealthSource);
	    alloptions.put("incomeSlab", InvestFormConstants.fatcaIncomeSlab);
	    alloptions.put("politicalView", InvestFormConstants.fatcaPoliticalView);
	    alloptions.put("occupationType", InvestFormConstants.fatcaOccupationType);
	    alloptions.put("nomineeRelation", InvestFormConstants.nomineeRelation);
	    alloptions.put("addressType", InvestFormConstants.fatcaAddressType);

	}catch(Exception e) {
	    logger.error("Error converting",e);
	}
	return alloptions;
    }

    @RequestMapping(value = "/mutual-funds/check-registration-status", method = RequestMethod.POST)
    @ResponseBody
    public Object bseCheckRegistrationstatus(@RequestBody HashMap<String, String> dataHashMap, HttpServletRequest request, HttpServletResponse response, HttpSession session) {

	Map<String,String> responsedata=null;
	try {
	    if(dataHashMap.containsKey("mobile")) {
		String mobile = (String) dataHashMap.get("mobile");
		responsedata = (Map<String, String>) bseEntryManager.getMfRegistrationStatus(mobile, null, null);
	    }
	}catch(Exception e) {
	    logger.error("Error processing request..",e);
	}
	return responsedata;
    }

    @RequestMapping(value = "/mutual-funds/register-customer", method = RequestMethod.POST)
    @ResponseBody
    public Object bseRegisterCustomer(@Valid @RequestBody MFCustomers investForm,BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response, HttpSession session) {

	logger.info("@@ BSE MF STAR - register user @@");
	String responseData = "SUCCESS";
	boolean validationpass = true;
	BseApiResponse apiresponse = new BseApiResponse();
	if(bindingResult.hasErrors()){
	    validationpass = false;
	    logger.info("Invalid data passed- "+ bindingResult.getFieldError().getField()+ " -> " +bindingResult.getFieldError().getDefaultMessage());
	    apiresponse.setStatusCode(Integer.toString(CommonConstants.TASK_FAILURE));
	    apiresponse.setRemarks(bindingResult.getFieldError().getDefaultMessage());
	}else if (!investForm.isUbo()) {
	    validationpass = false;
	    apiresponse.setStatusCode(Integer.toString(CommonConstants.TASK_FAILURE));
	    apiresponse.setRemarks("Please confirm the policy");

	} /*else if(bseEntryManager.isExisitngBSECustomerByMobile(investForm.getMobile())) {

	  }*/else {
	      try {
		  logger.info("bseRegisterCustomer() : Investor DOB: " + investForm.getInvDOB());
		  Date dob = apiformat.parse(investForm.getInvDOB());
		  Calendar dobdt = Calendar.getInstance();
		  dobdt.setTime(dob);

		  Calendar todaydt = Calendar.getInstance();
		  todaydt.setTime(new Date());
		  if (dob.after(new Date())) {
		      validationpass = false;
		      apiresponse.setStatusCode(Integer.toString(CommonConstants.TASK_FAILURE));
		      apiresponse.setRemarks("DOB given is future date!");
		  }
		  int age = todaydt.get(Calendar.YEAR) - dobdt.get(Calendar.YEAR);
		  if (age < 18 || age > 65) {
		      validationpass = false;
		      apiresponse.setStatusCode(Integer.toString(CommonConstants.TASK_FAILURE));
		      apiresponse.setRemarks("Allowed Investment age is 18-65 years");
		  }

		  logger.info("bseRegisterCustomer(): Investor DOB is in desired format. Proceed");

	      } catch (Exception e) {
		  validationpass = false;
		  apiresponse.setStatusCode(Integer.toString(CommonConstants.TASK_FAILURE));
		  apiresponse.setRemarks("Invalid date of birth format");
	      }
	  }


	String mfRegflag = "NOT_COMPLETE";
	Map<String,String> userstatus=null;


	try {
	    //	    Proceed with registration

	    logger.info("KYC verified? "+ investForm.getPan1KycVerified());
	    // Map other required fields for FATCA based on PAN
	    // ----------------------------------------------------------------------------

	    userstatus = (Map<String, String>) bseEntryManager.getMfRegistrationStatus(investForm.getMobile(), null, null);

	    if(userstatus.get("EXISTS").equals("N") || (userstatus.get("EXISTS").equals("Y") && userstatus.get("BSEREGISTERED").equals("N")) ) {

		investForm.getFatcaDetails().setIdentificationDocType("C");
		investForm.getFatcaDetails().setDaclarationDate(new Date());
		investForm.getFatcaDetails().setCreatedBy("SELF REGISTRATION");

		ClientSystemDetails systemDet = CommonTask.getClientSystemDetails(request);
		investForm.getFatcaDetails().setSystemip(systemDet.getClientIpv4Address());
		investForm.getFatcaDetails().setSystemDetails(systemDet.getClientBrowser());
		investForm.getFatcaDetails().setUscanadaCitizen(investForm.getFatcaDetails().isUsCitizenshipCheck() ? "Y" : "N");
		// -----------------------------------------------------------------------------
		investForm.setSystemip(systemDet.getClientIpv4Address());
		investForm.setSystemDetails(systemDet.getClientBrowser());

		mfRegflag = bseEntryManager.saveCustomerDetails(investForm,"NEW_CUSTOMER","Y", "NA" );
		if (!mfRegflag.equalsIgnoreCase("SUCCESS")) {
		    apiresponse.setStatusCode(Integer.toString(CommonConstants.TASK_FAILURE));
		    apiresponse.setRemarks("");
		    if (mfRegflag.equalsIgnoreCase("EXIST")) {
			apiresponse.setRemarks("Customer already exist with given PAN no.");
		    } else if (mfRegflag.equalsIgnoreCase("PAN_DUPLICATE")) {
			apiresponse.setRemarks("PAN already registered. Kindly contact admin in case of discrepancy.");
		    }else if (mfRegflag.equalsIgnoreCase("MOBILE_DUPLICATE")) {
			apiresponse.setRemarks("Mobile no already registered. Login to complete registration.");
		    } else if (mfRegflag.equalsIgnoreCase("BSE_CONN_FAIL")) {
			apiresponse.setRemarks("BSE endpoint connection failure!");
		    } else {
			apiresponse.setRemarks(mfRegflag);
		    }
		}else {
		    
		    logger.info("Registartion successful... Proceed with FATCA registartion...");
		    apiresponse.setStatusCode(Integer.toString(CommonConstants.TASK_SUCCESS));
		    apiresponse.setRemarks("Regisration Successful. Sign the form and upload document..");
		    
		    try {
			logger.info("MF REG - > Update LDAP with PAN and bseclientID....");
			ResponseEntity<String> responsePortal = null;
			responsePortal = profileRestClientService.linkmfaccountDetails(investForm.getMobile(), investForm.getPan1(), bseEntryManager.getClientIdfromMobile(investForm.getMobile()));
			String status = responsePortal.getBody();
			logger.info("LDAP account update response- "+ status);
			if (status!=null && status.equals("SUCCESS")) {
			    logger.info("registerBsepost(): Linking successful for mobile numberwith MF account- "+ investForm.getMobile());
			}  else {
			    logger.info("registerBsepost(): Failed to link MF account details with LDAP account" + status);
			}

		    } catch (Exception e) {
			logger.error("registerBsepost(): bsemfRegisterpost(): Exception proceesing linking PAN details to LDAP account",e);
		    }

		    logger.info("registerBsepost(): Customer registration successful. Drop mail to support team.. ");
		    mailSenderInterface.sendMFRegisterNotification("MF_REG_NOTIFICATION", investForm.getInvName(), investForm.getMobile(), investForm.getEmail(), "NA", investForm.getPan1(), investForm.getPan1KycVerified());

		    logger.info("Pushing customer FATCA details to BSE");
		    BseApiResponse fatresponse = null;

		    fatresponse = bseEntryManager.saveFatcaDetails(investForm,null,null);
			
			if(fatresponse.getResponseCode().equals("101") && fatresponse.getRemarks().contains("101|FAILED: INVALID DATE OF BIRTH FORMAT MM/DD/YYYY") ) {
			    logger.info("FATCA upload failed with default format. Try again with format mm/dd/yyyy");
			    fatresponse = bseEntryManager.saveFatcaDetails(investForm,null,"mm/dd/yyyy");
			}
		    
		    if (fatresponse.getResponseCode().equals("100")) {
			logger.info("registerBsepost(): FATCA save status to database and registration success. Set CUSTOMER_TYPE");
		    } else {
			logger.info("MF registered but FATCA save failed. Return with result..");
//			map.addAttribute("error","Registration success. FATCA declaration failed for- " + fatresponse.getRemarks());

		    }
		}

	    }else if(userstatus.get("EXISTS").equals("Y") && userstatus.get("BSEREGISTERED").equals("Y")) {
		apiresponse.setStatusCode(Integer.toString(CommonConstants.TASK_FAILURE));
		apiresponse.setRemarks("Account is already registered");
	    }
	} catch (Exception e) {
	    logger.error(" Failed to get pending url links. Returning to dashboard", e);
	    apiresponse.setStatusCode(Integer.toString(CommonConstants.TASK_FAILURE));
	    apiresponse.setRemarks("Internal error. Please try after sometime");
	}

	return apiresponse;
    }

    @RequestMapping(value = "/mutual-funds/upload-aof-signature", method = RequestMethod.POST)
    @ResponseBody
    public Object bseUploadAOFSignature(@Valid @RequestBody AofSignaure aofsignature,BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
	String responseData = "SUCCESS";

	return responseData;

    }

    @RequestMapping(value = "/mutual-funds/purchase-lumpsum", method = RequestMethod.POST)
    @ResponseBody
    public Object bsePurchaseLumpsum(@Valid @RequestBody  SelectMFFund selectedFund,BindingResult bindResult, HttpServletRequest request, HttpServletResponse response, HttpSession session) {

	logger.info("API Request received for- "+ request.getRequestURI());

	String responseData = "SUCCESS";

	if(bindResult.hasErrors()){
	    logger.info("Invalid data passed- "+ bindResult.getFieldError().getField()+ " -> " +bindResult.getFieldError().getDefaultMessage());
	    responseData=bindResult.getFieldError().getDefaultMessage();
	    return responseData;
	}

	try {

	} catch (Exception e) {
	    logger.error(" Failed to get pending url links. Returning to dashboard", e);
	    responseData = "INTERNAL_ERROR";
	}

	logger.info(" Return url- " + responseData);
	return responseData;
    }

    @RequestMapping(value = "/mutual-funds/purchase-sip", method = RequestMethod.POST)
    @ResponseBody
    public Object bsePurchaseSIP(@Valid @RequestBody  SelectMFFund selectedFund,BindingResult bindResult, HttpServletRequest request, HttpServletResponse response, HttpSession session) {

	logger.info("@@ BSE MF STAR - register user @@");
	String responseData = "SUCCESS";

	if(bindResult.hasErrors()){
	    logger.info("Invalid data passed- "+ bindResult.getFieldError().getField()+ " -> " +bindResult.getFieldError().getDefaultMessage());
	    responseData=bindResult.getFieldError().getDefaultMessage();
	    return responseData;
	}

	try {


	} catch (Exception e) {
	    logger.error(" Failed to get pending url links. Returning to dashboard", e);
	    responseData = "INTERNAL_ERROR";
	}

	//		map.addAttribute("contextcdn", env.getProperty(CommonConstants.CDN_URL));
	logger.info(" Return url- " + responseData);
	return responseData;
    }


    @RequestMapping(value = "/mutual-funds/purchase-lumpsum-additional", method = RequestMethod.POST)
    @ResponseBody
    public Object bseAdditionalPurchaseLumpsum(@Valid @RequestBody  MFAdditionalPurchaseForm purchaseForm,BindingResult bindResult, HttpServletRequest request, HttpServletResponse response, HttpSession session) {

	logger.info("API Request received for- "+ request.getRequestURI());

	String responseData = "SUCCESS";

	if(bindResult.hasErrors()){
	    logger.info("Invalid data passed- "+ bindResult.getFieldError().getField()+ " -> " +bindResult.getFieldError().getDefaultMessage());
	    responseData=bindResult.getFieldError().getDefaultMessage();
	    return responseData;
	}

	try {

	} catch (Exception e) {
	    logger.error(" Failed to get pending url links. Returning to dashboard", e);
	    responseData = "INTERNAL_ERROR";
	}

	//		map.addAttribute("contextcdn", env.getProperty(CommonConstants.CDN_URL));
	logger.info(" Return url- " + responseData);
	return responseData;
    }


    @RequestMapping(value = "/mutual-funds/order-status", method = RequestMethod.POST)
    @ResponseBody
    public Object bseOrderStatus(
	    /*@Valid @RequestBody MFRedeemForm orderCancelForm,BindingResult bindResult,*/ HttpServletRequest request, HttpServletResponse response, HttpSession session) {

	logger.info("API Request received for- "+ request.getRequestURI());

	String responseData = "SUCCESS";

	/*if(bindResult.hasErrors()){
	    logger.info("Invalid data passed- "+ bindResult.getFieldError().getField()+ " -> " +bindResult.getFieldError().getDefaultMessage());
	    responseData=bindResult.getFieldError().getDefaultMessage();
	    return responseData;
	}
	 */
	try {

	} catch (Exception e) {
	    logger.error(" Failed to get pending url links. Returning to dashboard", e);
	    responseData = "INTERNAL_ERROR";
	}

	//		map.addAttribute("contextcdn", env.getProperty(CommonConstants.CDN_URL));
	logger.info(" Return url- " + responseData);
	return responseData;
    }

    @RequestMapping(value = "/mutual-funds/purchase-cancel-order", method = RequestMethod.POST)
    @ResponseBody
    public Object bsecancelOrder(@Valid @RequestBody MFRedeemForm orderCancelForm,BindingResult bindResult, HttpServletRequest request, HttpServletResponse response, HttpSession session) {

	logger.info("API Request received for- "+ request.getRequestURI());

	String responseData = "SUCCESS";

	if(bindResult.hasErrors()){
	    logger.info("Invalid data passed- "+ bindResult.getFieldError().getField()+ " -> " +bindResult.getFieldError().getDefaultMessage());
	    responseData=bindResult.getFieldError().getDefaultMessage();
	    return responseData;
	}

	try {

	} catch (Exception e) {
	    logger.error(" Failed to get pending url links. Returning to dashboard", e);
	    responseData = "INTERNAL_ERROR";
	}

	//		map.addAttribute("contextcdn", env.getProperty(CommonConstants.CDN_URL));
	logger.info(" Return url- " + responseData);
	return responseData;
    }


    @RequestMapping(value = "/mutual-funds/redeem-funds", method = RequestMethod.POST)
    @ResponseBody
    public Object bseRedeemFunds(@Valid @RequestBody  MFRedeemForm redeemForm,BindingResult bindResult, HttpServletRequest request, HttpServletResponse response, HttpSession session) {

	logger.info("API Request received for- "+ request.getRequestURI());

	String responseData = "SUCCESS";

	if(bindResult.hasErrors()){
	    logger.info("Invalid data passed- "+ bindResult.getFieldError().getField()+ " -> " +bindResult.getFieldError().getDefaultMessage());
	    responseData=bindResult.getFieldError().getDefaultMessage();
	    return responseData;
	}

	try {

	} catch (Exception e) {
	    logger.error(" Failed to get pending url links. Returning to dashboard", e);
	    responseData = "INTERNAL_ERROR";
	}

	//		map.addAttribute("contextcdn", env.getProperty(CommonConstants.CDN_URL));
	logger.info(" Return url- " + responseData);
	return responseData;
    }


    @RequestMapping(value = "/mutual-funds/investor-portfolio-balance", method = RequestMethod.POST)
    @ResponseBody
    public Object bsePorti(
	    /*@Valid @RequestBody  MFRedeemForm redeemForm,BindingResult bindResult, */HttpServletRequest request, HttpServletResponse response, HttpSession session) {

	logger.info("API Request received for- "+ request.getRequestURI());

	String responseData = "SUCCESS";

	/*if(bindResult.hasErrors()){
	    logger.info("Invalid data passed- "+ bindResult.getFieldError().getField()+ " -> " +bindResult.getFieldError().getDefaultMessage());
	    responseData=bindResult.getFieldError().getDefaultMessage();
	    return responseData;
	}*/

	try {

	} catch (Exception e) {
	    logger.error(" Failed to get pending url links. Returning to dashboard", e);
	    responseData = "INTERNAL_ERROR";
	}

	//		map.addAttribute("contextcdn", env.getProperty(CommonConstants.CDN_URL));
	logger.info(" Return url- " + responseData);
	return responseData;
    }


    /* 
    public static void main(String[] args) {
    JsonObject obj = new JsonObject();
    try {
    //	obj.addProperty("holingNature",InvestFormConstants.holdingMode);
     ObjectMapper objectMapper = new ObjectMapper();
    System.out.println(objectMapper.writeValueAsString(InvestFormConstants.holdingMode));

    obj.addProperty("holingNature", objectMapper.writeValueAsString(InvestFormConstants.holdingMode));
    obj.addProperty("dividendPayMode", objectMapper.writeValueAsString(InvestFormConstants.dividendPayMode));
    System.out.println(obj);

    }catch(Exception e) {
        e.printStackTrace();
    }
    }*/

}
