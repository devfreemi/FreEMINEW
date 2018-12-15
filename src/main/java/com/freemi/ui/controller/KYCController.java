package com.freemi.ui.controller;

import static org.assertj.core.api.Assertions.in;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.freemi.common.util.CommonConstants;
import com.freemi.common.util.CommonTask;
import com.freemi.controller.implementations.FolioManagementImpl;
import com.freemi.controller.interfaces.FolioManagementContoller;
import com.freemi.entity.Birla.ValidateAadhaarOTPOutput;
import com.freemi.entity.general.AadhaarOTP;
import com.freemi.entity.investment.AadhaarVerifyStatus;
import com.freemi.entity.investment.FolioCreationStatus;
import com.freemi.entity.investment.MFInvestForm;
import com.freemi.entity.investment.PanValidationStatus;
import com.freemi.entity.investment.RazorpayPayObj;
import com.freemi.entity.investment.RazorpayPayObj.RoutePay;
import com.freemi.services.partners.Interfaces.RazorpayPayment;

@Controller
@SessionAttributes({"mfInvestForm"})
public class KYCController {

	private static final Logger logger = LogManager.getLogger(KYCController.class);

	//   @Autowired
	//	private DatabaseEntryManager databaseEntryManager ;//= (DatabaseEntryManager) BeanUtil.getBean(DatabaseEntryService.class);

	@Autowired
	private FolioManagementContoller folioManagementContoller;

	@Autowired
	private RazorpayPayment razorpayPayment;
	
	@Autowired
	private Environment env;

	@RequestMapping(value = "/validatePAN.do", method = RequestMethod.POST)
	public String validatePANPost(@ModelAttribute("mfInvestForm") MFInvestForm mfInvestForm ,@ModelAttribute("aadhaarotp") AadhaarOTP aadhaarotp, Model model, HttpServletRequest request, HttpSession session) {

		logger.info("@@@@ ValidatePANController @@@@");
		String returnUrl = "mfekyc";
		logger.info(mfInvestForm.getPAN());
		try{
			//logger.info("Rating- "+ mfInvestForm.getSelectedFund().getFundRating()== null);
			logger.info(mfInvestForm.getSelectedFund().getFundName()!=null?mfInvestForm.getSelectedFund().getFundName():"");
			logger.info(mfInvestForm.getSelectedFund().getMonthlySavings()!=null?mfInvestForm.getSelectedFund().getMonthlySavings():"");
			logger.info("Wish date- "+ mfInvestForm.getMfInvestDates().getSipPeriodToMonth());
			logger.info("Wish date- year "+ mfInvestForm.getMfInvestDates().getSipPeriodToYear());
			logger.info("Invest type- "+ mfInvestForm.getInvestmentType()) ;

			getInvestmentFormData(mfInvestForm);

			PanValidationStatus panStatus = (PanValidationStatus) session.getAttribute("panStatus");
			//		FolioManagementContoller management = new FolioManagementImpl();

			if(mfInvestForm.getAadhaar()==null && mfInvestForm.getPAN().length()>0){

				panStatus = (PanValidationStatus) folioManagementContoller.validatePANfromPartners(mfInvestForm.getPAN(), session,CommonTask.getClientSystemDetails(request));

				//			panStatus = (PanValidationStatus) management.validatePANfromPartners(mfInvestForm.getPAN(), session,CommonTask.getClientSystemDetails(request));
				//			logger.info("PAN validation status- "+ panStatus.getIsKYCVerified());
				mfInvestForm.setPanValidationStatus(panStatus);
				mfInvestForm.setInvName(panStatus.getPanHolderName()!=null?panStatus.getPanHolderName(): "");
				model.addAttribute("showAadhaar", true);
				if(panStatus.getIsKYCVerified().equalsIgnoreCase("Y")){
					/*try{
					databaseEntryManager.savekycstatus(panStatus);
				}catch(Exception e){
					e.getMessage();
				}*/
					mfInvestForm.setKycType("P");							// For PAN based transaction
					returnUrl ="form-new-customer";
				}else if(panStatus.getIsKYCVerified().equalsIgnoreCase("E")){
					logger.info("Do not show AADHAAR");
					model.addAttribute("panread", false);
					model.addAttribute("showAadhaar", false);
					model.addAttribute("error", panStatus.getMessage());

				}else{
					model.addAttribute("panread", true);
					model.addAttribute("showAadhaar", true);
					model.addAttribute("error", panStatus.getMessage());
				}

				session.setAttribute("panStatus", panStatus);


			}
			else if(mfInvestForm.getAadhaar()!=null && mfInvestForm.getPAN().length()>0 && panStatus.getIsKYCVerified().equalsIgnoreCase("N")){
				// Aadhaar submited. CAll connectors to verify
				logger.info("Validate AADAAR for the customer");

				mfInvestForm.setKycType("A");	
				AadhaarVerifyStatus aadhaarVerify= (AadhaarVerifyStatus) folioManagementContoller.validateAADHAARNmber("", mfInvestForm.getAadhaar(), null, session,CommonTask.getClientSystemDetails(request));
				if(aadhaarVerify.getResponseCode().equalsIgnoreCase("1")){
					mfInvestForm.setAadhaarbaseKYCrefId(aadhaarVerify.getRefId());
					session.setAttribute("aadhaarVerify", aadhaarVerify);
					logger.info("Enable OTP box");
					aadhaarotp.setPan(mfInvestForm.getPAN());
					aadhaarotp.setAadhaar(mfInvestForm.getAadhaar());
					model.addAttribute("otpbox", true);
				}else if(aadhaarVerify.getResponseCode().equalsIgnoreCase("3")){
					logger.info("E-KYC is already complete: [Response] "+ aadhaarVerify.getResponseMessage());
					mfInvestForm.setAadhaarVerifyStatusCode(aadhaarVerify.getResponseCode());
					mfInvestForm.setAadhaarbaseKYCrefId(aadhaarVerify.getRefId());
					returnUrl="form-new-customer";
				}else{
					model.addAttribute("error",aadhaarVerify.getResponseMessage());
					model.addAttribute("showAadhaar", true);
					model.addAttribute("panread", true);
				}

			}
			else if(panStatus.getIsKYCVerified().equalsIgnoreCase("Y")){
				returnUrl="form-new-customer";
			}else if(panStatus.getIsKYCVerified().equalsIgnoreCase("E")){
				model.addAttribute("panread", true);
			}else{
				model.addAttribute("panread", true);
				model.addAttribute("showAadhaar", true);
			}

		}catch(Exception e){
			logger.error(e.getMessage());
			model.addAttribute("error", "There seems to be a technical error. Please try again later.");
		}

		//		mfInvestForm.getNominee().setIsNominate("N");
		//		logger.info(mfInvestForm.getMfInvestDates().getSipPeriodFromMonth());
		mfInvestForm.setFatcaStatus("N");
		return returnUrl;
	}

	@RequestMapping(value = "/validatePAN.do", method = RequestMethod.GET)
	public String loginDisplay(Model model, HttpServletRequest request, HttpSession session) {
		//		return HttpServletResponse.SC_METHOD_NOT_ALLOWED;
		return "redirect:/registry-mutual-funds";
	}

	@RequestMapping(value = "/mfekyc", method = RequestMethod.GET)
	public String mfekyc(Model map, HttpServletRequest request, HttpSession session) {
		map.addAttribute("mfInvestForm", new MFInvestForm());
		map.addAttribute("aadhaarotp", new AadhaarOTP());
		map.addAttribute("contextcdn", env.getProperty(CommonConstants.CDN_URL));
		return "mfekyc";
	}

	@RequestMapping(value = "/validateAadhaarOTP", method = RequestMethod.POST)
	public String validateAadhaarOTP(@ModelAttribute("aadhaarotp") AadhaarOTP aadhaarotp,@ModelAttribute("mfInvestForm") MFInvestForm mfInvestForm, Model model, HttpServletRequest request, HttpSession session) {
		//		model.addAttribute("mfInvestForm", new MFInvestForm());
		logger.info("ValidateOTP Controller");
		//		String otp = (String) request.getAttribute("otp");
		getInvestmentFormData(mfInvestForm);

		AadhaarVerifyStatus aadhaarVerify = (AadhaarVerifyStatus) session.getAttribute("aadhaarVerify");
		ValidateAadhaarOTPOutput output=null;
		String returnUrl="mfekyc";

		FolioManagementContoller controller = new FolioManagementImpl();
		output= (ValidateAadhaarOTPOutput) controller.validateAADHAAROTP(aadhaarVerify.getRefId()!=null?aadhaarVerify.getRefId():"", aadhaarotp.getAadhaar(), aadhaarotp.getOtp(), "", session,CommonTask.getClientSystemDetails(request));
		session.setAttribute("aadhaarDetails", output);
		if(output.getReturnCode().equalsIgnoreCase("1")){
			mfInvestForm.setFatcaStatus("N");
			returnUrl="form-new-customer";
		}else{
			model.addAttribute("error", output.getReturnMsg());
			model.addAttribute("showAadhaar", true);
			model.addAttribute("panread", true);
		}
		//		logger.info(aadhaarotp.getAadhaar());
		//		logger.info(aadhaarotp.getOtp());


		return returnUrl;
	}

	private void getInvestmentFormData(MFInvestForm mfInvestForm) {
		ObjectMapper mapper = new ObjectMapper();
		try{
			logger.info("Investment form data - "+ mapper.writeValueAsString(mfInvestForm));
		}catch(Exception e){
			e.printStackTrace();
		}
	}


	@RequestMapping(value = "/registry-mutual-funds/mfInvestment.do", method = RequestMethod.POST)
	public String registryInvestment(@ModelAttribute("mfInvestForm") MFInvestForm mfInvestForm, Model model, HttpSession session,HttpServletRequest request,RedirectAttributes rAttr) {
		ObjectMapper mapper =new ObjectMapper();
		logger.info("RegistryMFInvestFormController");

		String returnUrl="form-new-customer";

		ValidateAadhaarOTPOutput aadhaarDetails = (ValidateAadhaarOTPOutput) session.getAttribute("aadhaarDetails");

		try{
			logger.debug( mapper.writeValueAsString(mfInvestForm));
			//			logger.info(mfInvestForm.getBankDetails().getAccountNumber());

			FolioCreationStatus folioStatus = (FolioCreationStatus) folioManagementContoller.generateNewFolio(mfInvestForm, aadhaarDetails, session, CommonTask.getClientSystemDetails(request));

			//			Thread.sleep(5000);
			if(folioStatus.isTransactionSuccessful()){
				logger.info("registryInvestment(): Successfully generated folio");
				model.addAttribute("folioStatus", folioStatus);
				session.setAttribute("folioStatus", folioStatus);
				model.addAttribute("mfInvestForm",mfInvestForm);
				model.addAttribute("paymentsucess","N");
				model.addAttribute("postsipstarted","N");


				rAttr.addFlashAttribute("paymentsucess", "N");
				rAttr.addFlashAttribute("postsipstarted", "N");
				returnUrl="redirect:/transactionstatus";

			}else{
				logger.info("registryInvestment(): Error generatng new folio..Further processing stopped.");
				model.addAttribute("error", folioStatus.getMessage());
			}
			rAttr.addFlashAttribute("mfInvestForm", mfInvestForm);
			rAttr.addFlashAttribute("folioStatus", folioStatus);

		}catch(Exception e)
		{
			logger.error("registryInvestment():" +e.getMessage());
			model.addAttribute("error", "Error processing data. Ensure all data provided");
		}

		//		returnUrl="mfPayment";
		//		model.addAttribute("mfInvestForm",mfInvestForm);

		return returnUrl;

	}

	@RequestMapping(value = "/transactionstatus", method = RequestMethod.GET)
	public String mftransactionstatus(@ModelAttribute("mfInvestForm")MFInvestForm mfInvestForm,@ModelAttribute("folioStatus") FolioCreationStatus folioStatus,@ModelAttribute("paymentsucess") String paymentsucess,@ModelAttribute("postsipstarted") String postsipstarted, Model map ){
		logger.info("MfTransactionstatusController");
		logger.info(mfInvestForm.getInvName());
		logger.info(folioStatus.getFolioNumber());
		logger.info("Model attribute- "+ paymentsucess);
		map.addAttribute("mfInvestForm", mfInvestForm);
		map.addAttribute("folioStatus", folioStatus);
		map.addAttribute("paymentsucess", paymentsucess);
		map.addAttribute("postsipstarted",postsipstarted );
		map.addAttribute("investType", mfInvestForm.getSelectedFund().getInvestType());
		map.addAttribute("contextcdn", env.getProperty(CommonConstants.CDN_URL));
		return "mfPayment";
	}


	@RequestMapping(value = "/registry-mutual-funds/processPay.do", method = RequestMethod.POST)
	public String paymentCapture(@ModelAttribute("mfInvestForm") MFInvestForm mfInvestForm, Model model, HttpSession session,HttpServletRequest request, RedirectAttributes rAttr) {

		String razorpayPaymentId=request.getParameter("razorpay_payment_id");
		try{
			int amountinPaisa=Integer.valueOf(mfInvestForm.getSelectedFund().getMonthlySavings());

			if(razorpayPayment.captureInitiatedPayment(razorpayPaymentId,amountinPaisa, "")){
				
				logger.info("Payment capture complate. Proceed to route payment to AMC");
				RazorpayPayObj razorPayObj = new RazorpayPayObj();
				RazorpayPayObj.RoutePay routeDetails = new RoutePay();
				ArrayList<RazorpayPayObj.RoutePay> routeList = new ArrayList<RazorpayPayObj.RoutePay>();
				routeDetails.setRouteAmout(Float.valueOf(mfInvestForm.getSelectedFund().getMonthlySavings()));
				routeDetails.setRazorpayAccountId(CommonConstants.ROUTE_ACCOUNT_ID_ADITYA_BIRLA_SUN_LIFE);

				routeList.add(routeDetails);
				razorPayObj.setRoutepay(routeList);
				razorpayPayment.routePayment(razorPayObj);
				if(razorPayObj.getRoutepay().get(0).getResponseTransferId()!=""){
					
					logger.info("Payment route complate. Proceed to push details for CAMS entry.");
					model.addAttribute("paymentsucess","Y");
					rAttr.addFlashAttribute("paymentsucess","Y");
					//				Complete Aditya Birla transaction
					FolioCreationStatus folioStatus = (FolioCreationStatus) session.getAttribute("folioStatus");

					folioStatus= (FolioCreationStatus) folioManagementContoller.savePostPaymentPurchaseDetails(mfInvestForm,folioStatus, session, CommonTask.getClientSystemDetails(request));

					model.addAttribute("postsipstarted","Y");
					rAttr.addFlashAttribute("folioStatus", folioStatus);
					rAttr.addFlashAttribute("postsipstarted","Y");
					rAttr.addFlashAttribute("paymentroutesucess","Y");

				}else{

					model.addAttribute("paymentsucess","N");
					rAttr.addFlashAttribute("paymentsucess","N");
					rAttr.addFlashAttribute("paymentroutesucess","N");
				}
			}else{
				model.addAttribute("paymentsucess","N");
				rAttr.addFlashAttribute("paymentsucess","N");
			}
		}catch(Exception e){
			e.printStackTrace();
			model.addAttribute("paymentsucess","N");
			rAttr.addFlashAttribute("paymentsucess","N");
		}

		rAttr.addFlashAttribute("investType", mfInvestForm.getSelectedFund().getInvestType());

		return "redirect:/paymentsuccess";

	}

	@RequestMapping(value = "/paymentsuccess", method = RequestMethod.GET)
	public String mfPaymentPrcessed(@ModelAttribute("paymentsucess") String paymentsucess,@ModelAttribute("postsipstarted") String postsipstarted,@ModelAttribute("folioStatus") FolioCreationStatus folioStatus, @ModelAttribute("investType") String investType,Model model ){
		logger.info("MfPaymentProcessedController");
		logger.info(folioStatus.getFolioNumber());
		model.addAttribute("folioStatus", folioStatus);
		model.addAttribute("paymentsucess", paymentsucess);
		model.addAttribute("postsipstarted", postsipstarted);
		model.addAttribute("investType", investType);
		return "mfPayment";
	}


	@RequestMapping(value = "/mfPayment", method = RequestMethod.GET)
	public String mfPaymentGet(){
		logger.info("MfPaymentController");
		return "redirect:/mfekyc";
	}





	@ModelAttribute("taxStatus")
	public Map<String, String> getTaxStatus() {
		Map<String, String> taxStatus = new HashMap<String,String>();
		taxStatus.put("Resident Individual", "Resident Individual");
		taxStatus.put("NRI - NRO", "NRI - NRO");
		taxStatus.put("NRI - NRE", "NRI - NRE");
		taxStatus.put("PIO", "PIO");
		taxStatus.put("Sole Proprietorship", "Sole Proprietorship");
		return taxStatus;
	}


	@ModelAttribute("banNames")
	public Map<String, String> getBankNames() {
		Map<String, String> bankNames = new TreeMap<String,String>();

		bankNames.put("ICICI Bank", "ICICI Bank");
		bankNames.put("State Bank of India", "State Bank of India");
		bankNames.put("A.P. Mahesh Co-operative Urban Bank","A.P. Mahesh Co-operative Urban Bank");
		bankNames.put("Axis Bank","Axis Bank");
		bankNames.put("Bank of Baroda","Bank of Baroda");
		bankNames.put("Airtel Payments Bank","Airtel Payments Bank");
		bankNames.put("Andhra Bank","Andhra Bank");
		bankNames.put("Bank of Bahrein and Kuwait","Bank of Bahrein and Kuwait");
		bankNames.put("Central Bank of India","Central Bank of India");
		bankNames.put("Corporation Bank","Corporation Bank");
		bankNames.put("IDBI","IDBI");
		bankNames.put("Dena Bank","Dena Bank");
		bankNames.put("Himachal Pradesh State Co-operative Bank","Himachal Pradesh State Co-operative Bank");
		bankNames.put("Indian Overseas Bank","Indian Overseas Bank");
		bankNames.put("Indusind Bank","Indusind Bank");
		bankNames.put("Syndicate Bank","Syndicate Bank");
		bankNames.put("Yes Bank","Yes Bank");
		bankNames.put("IDFC Bank","IDFC Bank");
		bankNames.put("Prathama Bank","Prathama Bank");
		bankNames.put("Karnataka Vikas Grameena Bank","Karnataka Vikas Grameena Bank");
		bankNames.put("City Union Bank","City Union Bank");
		bankNames.put("Abhyudaya Co-operative Bank","Abhyudaya Co-operative Bank");
		bankNames.put("Almora Urban Co-operative Bank","Almora Urban Co-operative Bank");
		bankNames.put("Nagpur Nagarik Sahakari Bank","Nagpur Nagarik Sahakari Bank");
		bankNames.put("Jammu and Kashmir Bank","Jammu and Kashmir Bank");
		bankNames.put("Dhanlaxmi Bank","Dhanlaxmi Bank");
		bankNames.put("Shivalik Mercantile Co-operative Bank","Shivalik Mercantile Co-operative Bank");
		bankNames.put("Delhi State Co-operative Bank","Delhi State Co-operative Bank");
		bankNames.put("Punjab National Bank","Punjab National Bank");
		bankNames.put("Thane Bharat Sahakari Bank","Thane Bharat Sahakari Bank");
		bankNames.put("Janata Sahakari Bank (Pune)","Janata Sahakari Bank (Pune)");
		bankNames.put("Saraswat Co-operative Bank","Saraswat Co-operative Bank");
		bankNames.put("Akola Janata Commercial Co-operative Bank","Akola Janata Commercial Co-operative Bank");
		bankNames.put("Canara Bank","Canara Bank");
		bankNames.put("NKGSB Co-operative Bank","NKGSB Co-operative Bank");
		bankNames.put("West Bengal State Co-operative Bank","West Bengal State Co-operative Bank");
		bankNames.put("Karad Urban Co-operative Bank","Karad Urban Co-operative Bank");
		bankNames.put("Development Bank of Singapore","Development Bank of Singapore");
		bankNames.put("Suryoday Small Finance Bank","Suryoday Small Finance Bank");
		bankNames.put("Andhra Pradesh State Co-operative Bank","Andhra Pradesh State Co-operative Bank");
		bankNames.put("Janaseva Sahakari Bank","Janaseva Sahakari Bank");
		bankNames.put("Equitas Small Finance Bank","Equitas Small Finance Bank");
		bankNames.put("United Bank of India","United Bank of India");
		bankNames.put("Vijaya Bank","Vijaya Bank");
		bankNames.put("Shamrao Vithal Co-operative Bank","Shamrao Vithal Co-operative Bank");
		bankNames.put("Pragathi Krishna Gramin Bank","Pragathi Krishna Gramin Bank");
		bankNames.put("Union Bank of India","Union Bank of India");
		bankNames.put("HDFC Bank","HDFC Bank");
		bankNames.put("Karur Vysya Bank","Karur Vysya Bank");
		bankNames.put("Rajasthan State Co-operative Bank","Rajasthan State Co-operative Bank");
		bankNames.put("Indian Bank","Indian Bank");
		bankNames.put("Oriental Bank of Commerce","Oriental Bank of Commerce");
		bankNames.put("The Sindhudurg District Central Co-operative Bank","The Sindhudurg District Central Co-operative Bank");
		bankNames.put("Kallappanna Awade Ichalkaranji Janata Sahakari Bank","Kallappanna Awade Ichalkaranji Janata Sahakari Bank");
		bankNames.put("Capital Small Finance Bank","Capital Small Finance Bank");
		bankNames.put("Apna Sahakari Bank","Apna Sahakari Bank");
		bankNames.put("South Indian Bank","South Indian Bank");
		bankNames.put("Bharat Co-operative Bank","Bharat Co-operative Bank");
		bankNames.put("Dombivli Nagari Sahakari Bank","Dombivli Nagari Sahakari Bank");
		bankNames.put("Rajarambapu Sahakari Bank","Rajarambapu Sahakari Bank");
		bankNames.put("Karnataka Bank","Karnataka Bank");
		bankNames.put("Tamilnadu Mercantile Bank","Tamilnadu Mercantile Bank");
		bankNames.put("Punjab & Sind Bank","Punjab & Sind Bank");
		bankNames.put("Kangra Central Co-operative Bank","Kangra Central Co-operative Bank");
		bankNames.put("UCO Bank","UCO Bank");
		bankNames.put("Bank of India","Bank of India");
		bankNames.put("Bank of Maharashtra","Bank of Maharashtra");
		bankNames.put("CITI Bank","CITI Bank");
		bankNames.put("Utkarsh Small Finance Bank","Utkarsh Small Finance Bank");
		bankNames.put("Allahabad Bank","Allahabad Bank");
		bankNames.put("Maharashtra Gramin Bank","Maharashtra Gramin Bank");
		bankNames.put("Fino Payments Bank","Fino Payments Bank");
		bankNames.put("Nasik Merchants Co-operative Bank","Nasik Merchants Co-operative Bank");
		bankNames.put("Pandharpur Urban Co Op. Bank Pandharpur","Pandharpur Urban Co Op. Bank Pandharpur");
		bankNames.put("Kotak Mahindra Bank","Kotak Mahindra Bank");
		bankNames.put("Rajgurunagar Sahakari Bank","Rajgurunagar Sahakari Bank");
		bankNames.put("Thane Janata Sahakari Bank","Thane Janata Sahakari Bank");
		bankNames.put("Paytm Payments Bank","Paytm Payments Bank");
		bankNames.put("Zoroastrian Co-operative Bank","Zoroastrian Co-operative Bank");
		bankNames.put("Kerala Gramin Bank","Kerala Gramin Bank");
		bankNames.put("AU Small Finance Bank","AU Small Finance Bank");
		bankNames.put("Kozhikode District Co-operatiave Bank","Kozhikode District Co-operatiave Bank");
		bankNames.put("Gurgaon Gramin Bank","Gurgaon Gramin Bank");
		bankNames.put("Gopinath Patil Parsik Janata Sahakari Bank","Gopinath Patil Parsik Janata Sahakari Bank");
		bankNames.put("DCB Bank","DCB Bank");
		bankNames.put("Citizen Credit Co-operative Bank","Citizen Credit Co-operative Bank");
		bankNames.put("Telangana State Co-operative Apex Bank","Telangana State Co-operative Apex Bank");
		bankNames.put("Kurla Nagarik Sahakari Bank","Kurla Nagarik Sahakari Bank");
		bankNames.put("Esaf Small Finance Bank","Esaf Small Finance Bank");
		bankNames.put("Punjab & Maharashtra Co-operative Bank","Punjab & Maharashtra Co-operative Bank");
		bankNames.put("Ujjivan Small Finance Bank","Ujjivan Small Finance Bank");
		bankNames.put("North East Small Finance Bank","North East Small Finance Bank");
		bankNames.put("Reserve Bank of India","Reserve Bank of India");
		bankNames.put("RBL Bank","RBL Bank");
		bankNames.put("Cosmos Co-operative Bank","Cosmos Co-operative Bank");
		bankNames.put("Surat District Co-operative Bank","Surat District Co-operative Bank");
		bankNames.put("Mumbai District Central Co-operative Bank","Mumbai District Central Co-operative Bank");
		bankNames.put("Nainital Bank","Nainital Bank");
		bankNames.put("Akola District Central Co-operative Bank","Akola District Central Co-operative Bank");
		bankNames.put("Nutan Nagarik Sahakari Bank","Nutan Nagarik Sahakari Bank");
		bankNames.put("Bandhan Bank","Bandhan Bank");
		bankNames.put("Laxmi Vilas Bank","Laxmi Vilas Bank");
		bankNames.put("Andhra Pragathi Grameena Bank","Andhra Pragathi Grameena Bank");
		bankNames.put("Federal Bank","Federal Bank");
		bankNames.put("Ahmedabad Mercantile Co-operative Bank","Ahmedabad Mercantile Co-operative Bank");
		bankNames.put("Thane District Central Co-operative Bank","Thane District Central Co-operative Bank");
		bankNames.put("Shri Chhatrapati Rajashri Shahu Urban Co-operative Bank","Shri Chhatrapati Rajashri Shahu Urban Co-operative Bank");
		bankNames.put("Jalgaon Peoples Co-operative Bank","Jalgaon Peoples Co-operative Bank");
		bankNames.put("Mysore Chamarajanagar District Co-operative Bank","Mysore Chamarajanagar District Co-operative Bank");
		bankNames.put("Municipal Co-operative Bank","Municipal Co-operative Bank");
		bankNames.put("Solapur Janata Sahakari Bank","Solapur Janata Sahakari Bank");
		bankNames.put("Catholic Syrian Bank","Catholic Syrian Bank");
		bankNames.put("Standard Chartered Bank","Standard Chartered Bank");
		bankNames.put("Sutex Co-operative.bank","Sutex Co-operative.bank");


		return bankNames;
	}

	@ModelAttribute("accountTypes")
	public Map<String, String> getBankAccountTypes() {
		Map<String, String> accountTypes = new TreeMap<String,String>();
		accountTypes.put(CommonConstants.BANK_ACC_TYPE_SAVINGS, CommonConstants.BANK_ACC_TYPE_SAVINGS);
		accountTypes.put(CommonConstants.BANK_ACC_TYPE_CURRENT, CommonConstants.BANK_ACC_TYPE_CURRENT);
		accountTypes.put(CommonConstants.BANK_ACC_TYPE_OVER_DRAFT, CommonConstants.BANK_ACC_TYPE_OVER_DRAFT);
		//		accountTypes.put("", "");
		return accountTypes;
	}

	@ModelAttribute("stateList")
	public Map<String, String> getStatesIndia() {
		Map<String, String> states = new TreeMap<String,String>();
		states.put("Andhra Pradesh", "Andhra Pradesh");
		states.put("Arunachal Pradesh", "Arunachal Pradesh");
		states.put("Assam", "Assam");
		states.put("Bihar", "Bihar");
		states.put("Chhattisgarh", "Chhattisgarh");
		states.put("Goa", "Goa");
		states.put("Gujarat", "Gujarat");
		states.put("Haryana", "Haryana");
		states.put("Himachal Pradesh ", "Himachal Pradesh ");
		states.put("Jammu and Kashmir ", "Jammu and Kashmir ");
		states.put("Jharkhand", "Jharkhand");
		states.put("Karnataka", "Karnataka");
		states.put("Kerala", "Kerala");
		states.put("Madhya Pradesh", "Madhya Pradesh");
		states.put("Maharashtra", "Maharashtra");
		states.put("Manipur", "Manipur");
		states.put("Meghalaya", "Meghalaya");
		states.put("Mizoram", "Mizoram");
		states.put("Nagaland", "Nagaland");
		states.put("Odisha", "Odisha");
		states.put("Punjab", "Punjab");
		states.put("Rajasthan", "Rajasthan");
		states.put("Sikkim", "Tamil Nadu");
		states.put("Telangana", "Telangana");
		states.put("Tripura", "Tripura");
		states.put("Uttar Pradesh", "Uttar Pradesh");
		states.put("Uttarakhand", "Uttarakhand");
		states.put("West Bengal ", "West Bengal ");

		return states;
	}

	@ModelAttribute("paymentModes")
	public Map<String, String> getpaymentModes() {
		Map<String, String> payments = new TreeMap<String,String>();
		payments.put("OTM", "OTM");
		payments.put("HDFC Bank eMandate", "HDFC Bank eMandate");
		payments.put("Axis Bank eMandate", "Axis Bank eMandate");

		return payments;
	}

	@ModelAttribute("calendarMonths")
	public Map<Integer, String> getCalendarMonths() {
		Map<Integer, String> months = new TreeMap<Integer,String>();
		months.put(1, "January");
		months.put(2, "February");
		months.put(3, "March");
		months.put(4, "April");
		months.put(5, "May");
		months.put(6, "June");
		months.put(7, "July");
		months.put(8, "August");
		months.put(9, "September");
		months.put(10, "October");
		months.put(11, "November");
		months.put(12, "December");

		return months;
	}

	@ModelAttribute("monthDates")
	public Map<Integer, Integer> getmonthDates() {
		Map<Integer, Integer> monthDates = new TreeMap<Integer,Integer>();
		for(int i=1; i<=28;i++){
			monthDates.put(i, i);
		}
		return monthDates;
	}

	@ModelAttribute("investYears")
	public Map<Integer, Integer> getInvestYears() {

		int initialYear = LocalDate.now().getYear();
		Map<Integer, Integer> investYears = new TreeMap<Integer,Integer>();
		for(int i=initialYear; i<=2099;i++){
			investYears.put(i, i);
		}
		return investYears;
	}

	@ModelAttribute("annualIncome")
	public Map<String, String> getAnnualIncomeSlab() {

		Map<String, String> incomeSlab = new TreeMap<String,String>();
		incomeSlab.put("Below 1 Lakh", "Below 1 Lakh");
		incomeSlab.put("> 1 <=5 Lacs", "> 1 <=5 Lacs");
		incomeSlab.put(">5 <=10 Lacs", ">5 <=10 Lacs");
		incomeSlab.put(">10 <= 25 Lacs", ">10 <= 25 Lacs");
		incomeSlab.put("> 25 Lacs < = 1 Crore", "> 25 Lacs < = 1 Crore");
		incomeSlab.put("Above 1 Crore", "Above 1 Crore");
		return incomeSlab;
	}

	@ModelAttribute("occupation")
	public Map<String, String> getOccupationList() {

		Map<String, String> occupationList = new TreeMap<String,String>();
		occupationList.put("Business", "Business");
		occupationList.put("Bureaucrat", "Bureaucrat");
		occupationList.put("Doctor", "Doctor");
		occupationList.put("Housewife", "Housewife");
		occupationList.put("Others", "Others");
		occupationList.put("Private Sector Service", "Private Sector Service");
		occupationList.put("Professional", "Professional");
		occupationList.put("Public Sector / Government Service", "Public Sector / Government Service");
		occupationList.put("Retired","Retired");
		occupationList.put("Service", "Service");
		occupationList.put("Student", "Student");
		occupationList.put("Unknown / Not Applicable", "Unknown / Not Applicable");
		return occupationList;
	}



}
