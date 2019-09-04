package com.freemi.ui.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.freemi.common.util.BseAOFGenerator;
import com.freemi.common.util.BseRelatedActions;
import com.freemi.common.util.CommonConstants;
import com.freemi.common.util.CommonTask;
import com.freemi.common.util.InvestFormConstants;
import com.freemi.controller.interfaces.InvestmentConnectorBseInterface;
import com.freemi.controller.interfaces.MailSenderHandler;
import com.freemi.controller.interfaces.ProfileRestClientService;
import com.freemi.database.interfaces.ProductSchemeDetailService;
import com.freemi.database.service.BseEntryManager;
import com.freemi.entity.bse.BseAOFUploadResponse;
import com.freemi.entity.bse.BseApiResponse;
import com.freemi.entity.bse.BseFatcaForm;
import com.freemi.entity.bse.BseFileUpload;
import com.freemi.entity.bse.BseOrderPaymentRequest;
import com.freemi.entity.bse.BseOrderPaymentResponse;
import com.freemi.entity.database.UserBankDetails;
import com.freemi.entity.general.ClientSystemDetails;
import com.freemi.entity.general.Registerform;
import com.freemi.entity.investment.BseFundsScheme;
import com.freemi.entity.investment.BseMFInvestForm;
import com.freemi.entity.investment.BseMFSelectedFunds;
import com.freemi.entity.investment.BseMFTop15lsSip;
import com.freemi.entity.investment.BseMandateDetails;
import com.freemi.entity.investment.BsemfTransactionHistory;
import com.freemi.entity.investment.MFAdditionalPurchaseForm;
import com.freemi.entity.investment.MFRedeemForm;
import com.freemi.entity.investment.MfAllInvestorValueByCategory;
import com.freemi.entity.investment.SelectMFFund;
import com.freemi.entity.investment.TransactionStatus;

@Controller
@Scope("session")
//@SessionAttributes({"purchaseForm","mfRedeemForm"})
public class BsemfController {

	private static final Logger logger = LogManager.getLogger(BsemfController.class);

	@Autowired
	ProductSchemeDetailService productSchemeDetailService;

	/*
	 * @Autowired private DatabaseEntryManager databaseEntryManager ;//=
	 * (DatabaseEntryManager) BeanUtil.getBean(DatabaseEntryService.class);
	 */

	@Autowired
	ProfileRestClientService profileRestClientService;

	@Autowired
	private BseEntryManager bseEntryManager;

	@Autowired
	InvestmentConnectorBseInterface investmentConnectorBseInterface;

	@Autowired
	Environment env;

	@Autowired
	MailSenderHandler mailSenderHandler;

	@RequestMapping(value = "/mutual-funds/register", method = RequestMethod.GET)
	public String registerUserMfGet(
			/* @ModelAttribute("selectedFund")SelectMFFund selectFund , */@RequestParam(name = "mf", required = false) String userType,
			Model map, HttpServletRequest request, HttpSession session, HttpServletResponse response, Device device) {
		// logger.info("@@@@ Inside Login..");
		logger.info("registerUser(): @@@@ BSE New customer register @@@@");

		BseMFInvestForm investForm = new BseMFInvestForm();

		SelectMFFund selectFund = (SelectMFFund) session.getAttribute("selectedFund");

		if (selectFund != null) {
			logger.info("registerUser(): Setting register form with mobile and PAN from selected fund details...");
			investForm.setPan1(selectFund.getPan());
			investForm.setMobile(selectFund.getMobile());
		} else {
			logger.info("registerUser(): registerUserMfGet(): selectFund is null.");
		}

		if (userType.equalsIgnoreCase("01")) {
			if (session.getAttribute("userid") != null) {
				map.addAttribute("LOGGED", "Y");
			}

		}
		if (userType.equalsIgnoreCase("02")) {
			// Marking this register customer in LDAP
			investForm.setProfileRegRequired(true);
		}

		if (userType.equalsIgnoreCase("04")) {
			logger.info("registerUser(): Request received to complete profile registration. The request must come from dashbaord, check if logged in ");
			if (session.getAttribute("userid") != null) {
				investForm = bseEntryManager.getCustomerInvestFormData(session.getAttribute("userid").toString());

				// Convert date format
				try {
					SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-mm-dd");
					SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("dd/mm/yyyy");

					Date dob = simpleDateFormat1.parse(investForm.getInvDOB());
					investForm.setInvDOB(simpleDateFormat2.format(dob));

				} catch (Exception e) {
					logger.error("registerUser(): Failed to convert date format- ", e);
				}

			}
		} else {
			investForm.setDividendPayMode("02");
			investForm.setOccupation("01");
		}

		map.addAttribute("mfInvestForm", investForm);

		map.addAttribute("holingNature", InvestFormConstants.holdingMode);
		map.addAttribute("dividendPayMode", InvestFormConstants.dividendPayMode);
		map.addAttribute("occupation", InvestFormConstants.occupationList);
		map.addAttribute("bankNames", InvestFormConstants.bankNames);
		map.addAttribute("accountTypes", InvestFormConstants.accountTypes);
		map.addAttribute("states", InvestFormConstants.states);
		map.addAttribute("wealthSource", InvestFormConstants.fatcaWealthSource);
		map.addAttribute("incomeSlab", InvestFormConstants.fatcaIncomeSlab);
		map.addAttribute("politicalView", InvestFormConstants.fatcaPoliticalView);
		map.addAttribute("occupationType", InvestFormConstants.fatcaOccupationType);
		map.addAttribute("nomineeRelation", InvestFormConstants.nomineeRelation);
		map.addAttribute("addressType", InvestFormConstants.fatcaAddressType);

		map.addAttribute("contextcdn", env.getProperty(CommonConstants.CDN_URL));

		logger.info("registerUser(): Get device platform during MF registration -" + device.getDevicePlatform());

		return "bsemf/bse-form-new-customer";
		// return "bsemf/test";
	}

	@RequestMapping(value = { "/mutual-funds/mfInvestRegister" }, method = RequestMethod.GET)
	public String defaultPostUrl(@Valid @ModelAttribute("mfInvestForm") BseMFInvestForm investForm,
			BindingResult bindResult, Model map, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes attrs, HttpSession session) {
		return "redirect:/";

	}

	@RequestMapping(value = "/mutual-funds/mfInvestRegister.do", method = RequestMethod.POST)
	public String registerBsepost(@Valid @ModelAttribute("mfInvestForm") BseMFInvestForm investForm,
			BindingResult bindResult, Model map, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes attrs, HttpSession session) {

		logger.info("registerBsepost(): BSE MF STAR Customer Register post controller");
		String returnUrl = "bsemf/bse-registration-status";
		String mfRegflag = "NOT_COMPLETE";
		//		String fatcaFlag = "FAIL";
		String validationerrormsg = "";
		BseApiResponse fatresponse = null;
		boolean validationpass = true;
		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-mm-dd");
		SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("dd/mm/yyyy");

		// String error = "N";

		if (bindResult.hasErrors()) {
			validationpass = false;
			//			logger.error("Error in binding result during registartion.. Return to form- ");
			//			map.addAttribute("error", bindResult.getFieldError().getDefaultMessage());
			validationerrormsg = bindResult.getFieldError().getDefaultMessage();
		} else if (!investForm.isUbo()) {
			validationpass = false;
			validationerrormsg = "Confirm the policy";

		} else if (session.getAttribute("token") != null) {
			if (session.getAttribute("userid") != null) {
				if (!session.getAttribute("userid").toString().equalsIgnoreCase(investForm.getMobile())) {
					validationpass = false;
					validationerrormsg = "Mobile no. must be same as that of logged account. ";
				}
			}
		} else {
			try {
				logger.info("registerBsepost() : Investor DOB: " + investForm.getInvDOB());
				Date dob = simpleDateFormat2.parse(investForm.getInvDOB());
				Calendar dobdt = Calendar.getInstance();
				dobdt.setTime(dob);

				Calendar todaydt = Calendar.getInstance();
				todaydt.setTime(new Date());
				if (dob.after(new Date())) {
					validationpass = false;
					validationerrormsg = "DOB given is future date!";
				}
				int age = todaydt.get(Calendar.YEAR) - dobdt.get(Calendar.YEAR);
				if (age < 18 || age > 65) {
					validationpass = false;
					validationerrormsg = "Allowed Investment age is 18-65 years";
				}

				/*
				 * System.out.println("is date future -"+ dob.after(new Date()));
				 * 
				 * System.out.println("Age- "+ (todaydt.get(Calendar.YEAR) -
				 * dobdt.get(Calendar.YEAR) ));
				 */
				logger.info("registerBsepost(): Investor DOB is in desired format. Proceed");

			} catch (Exception e) {
				validationpass = false;
				validationerrormsg = "Invalid date of birth format";

			}
		}

		if (!validationpass) {
			logger.info("registerBsepost(): Error validating form data: " + validationerrormsg);

			map.addAttribute("error", validationerrormsg);

			map.addAttribute("holingNature", InvestFormConstants.holdingMode);
			map.addAttribute("dividendPayMode", InvestFormConstants.dividendPayMode);
			map.addAttribute("occupation", InvestFormConstants.occupationList);
			map.addAttribute("bankNames", InvestFormConstants.bankNames);
			map.addAttribute("accountTypes", InvestFormConstants.accountTypes);
			map.addAttribute("states", InvestFormConstants.states);
			map.addAttribute("fatca", InvestFormConstants.states);
			map.addAttribute("wealthSource", InvestFormConstants.fatcaWealthSource);
			map.addAttribute("incomeSlab", InvestFormConstants.fatcaIncomeSlab);
			map.addAttribute("politicalView", InvestFormConstants.fatcaPoliticalView);
			map.addAttribute("occupationType", InvestFormConstants.fatcaOccupationType);
			map.addAttribute("nomineeRelation", InvestFormConstants.nomineeRelation);
			map.addAttribute("addressType", InvestFormConstants.fatcaAddressType);
			return "bsemf/bse-form-new-customer";

		}

		try {

			// Check if user required to be registered at portal first
			logger.info("registerBsepost(): Is profile generation required during MF profile registration? - " + investForm.isProfileRegRequired());
			if (investForm.isProfileRegRequired() && env.getProperty(CommonConstants.BSE_CALL_TEST_ENABLED).equalsIgnoreCase("N")) {
				logger.info("registerBsepost(): This is a fresh customer. Register the user first for portal");
				Registerform registerForm = new Registerform();
				registerForm.setMobile(investForm.getMobile());
				registerForm.setEmail(investForm.getEmail());
				registerForm.setFullName(investForm.getInvName());
				registerForm.setPassword(CommonConstants.GENERATE_PASSWORD_BY_FREEMI); // Reserved token for generating
				// token at profile side
				registerForm.setRegistrationref("MF_REG_NEW");

				// RestClient client = new RestClient();
				ResponseEntity<String> responsePortal = null;
				try {
					responsePortal = profileRestClientService.registerUser(registerForm);
					String status = responsePortal.getHeaders().get("STATUS").get(0);

					if (status.equals("SUCCESS")) {
						logger.info(
								"registerBsepost(): Registration successful for mobile number during MF registration- "
										+ investForm.getMobile());
						logger.info(
								"registerBsepost(): User registration successful initiated during new customer registration. Setting parameter to false..");
						investForm.setProfileRegRequired(false);
					} else if (status.equals("DUPLICATE ENTRY")) {
						logger.info("registerBsepost(): Account already exist.");
					} else if (status.equals("ERROR")) {
						logger.info("registerBsepost(): Registration failed. Please try again after sometime");
					} else {
						logger.info(responsePortal.getHeaders().get("STATUS").get(0));
						// model.addAttribute("error", "Unknown response");
						logger.info(
								"registerBsepost(): Registration failed during MF user registration. Please check profile log");
					}

				} catch (HttpStatusCodeException e) {
					logger.error("registerBsepost(): bsemfRegisterpost(): Registartion Link failure", e);
				} catch (JsonProcessingException e) {
					logger.error("registerBsepost(): bsemfRegisterpost():invalid form data", e);
				} catch (Exception e) {
					logger.error("registerBsepost(): bsemfRegisterpost(): Exception proceesing regidtration request.",e);
				}

			} else {
				logger.info(
						"registerBsepost(): BSE Test is enabled... Skipping the process for mobile number profile generation- "
								+ investForm.getMobile());
			}

			// Map other required fields for FATCA based on PAN
			// ----------------------------------------------------------------------------
			investForm.getFatcaDetails().setIdentificationDocType("C");
			investForm.getFatcaDetails().setDaclarationDate(new Date());
			investForm.getFatcaDetails().setCreatedBy("SELF REGISTRATION");

			ClientSystemDetails systemDet = CommonTask.getClientSystemDetails(request);
			investForm.getFatcaDetails().setSystemip(systemDet.getClientIpv4Address());
			investForm.getFatcaDetails().setSystemDetails(systemDet.getClientBrowser());
			investForm.getFatcaDetails()
			.setUscanadaCitizen(investForm.getFatcaDetails().isUsCitizenshipCheck() ? "Y" : "N");
			// -----------------------------------------------------------------------------

			// Save customer registration details
			logger.info("registerBsepost(): Checking if customer already registered with bean flag- "
					+ investForm.getCustomerRegistered());

			if (investForm.getCustomerRegistered().equalsIgnoreCase("N")) {
				investForm.setSystemip(systemDet.getClientIpv4Address());
				investForm.setSystemDetails(systemDet.getClientBrowser());
				mfRegflag = bseEntryManager.saveCustomerDetails(investForm);

				logger.info("registerBsepost(): Customer MF registration status - " + mfRegflag);
				if (investForm.getCustomerRegistered().equalsIgnoreCase("N")
						&& !mfRegflag.equalsIgnoreCase("SUCCESS")) {
					returnUrl = "bsemf/bse-form-new-customer";
					if (mfRegflag.equalsIgnoreCase("EXIST")) {
						map.addAttribute("error", "Customer already exist with given PAN no.");
					} else if (mfRegflag.equalsIgnoreCase("BSE_CONN_FAIL")) {
						map.addAttribute("error", "BSE endpoint connection failure!");
					} else {
						map.addAttribute("error", mfRegflag);
					}

				} else {
					logger.info(
							"registerBsepost(): Customer saved to database. Setting register flag to yes for customer - "
									+ investForm.getMobile());
					investForm.setCustomerRegistered("Y");
					logger.info(
							"registerBsepost(): Customer registration successful. Pushing customer FATCA details to BSE ");

					fatresponse = bseEntryManager.saveFatcaDetails(investForm);
					if (fatresponse.getResponseCode().equals("100")) {
						logger.info("registerBsepost(): FATCA save status to database and registration success ");
						returnUrl = "redirect:/mutual-funds/mf-registration-status";
						attrs.addFlashAttribute("mfInvestForm", investForm);
						attrs.addAttribute("STATUS", "Y");
					} else {
						logger.info("MF registered but FATCA save failed. Return with result..");
						// map.addAttribute("success", "Your registration partially complete. Your FATCA
						// declaration failed for below reason-");
						map.addAttribute("error",
								"Registration success. FATCA declaration failed for- " + fatresponse.getRemarks());
						returnUrl = "bsemf/bse-form-new-customer";

					}
				}

			} else {
				logger.info("registerBsepost(): Customer already registered. This must be a call to with FATCA details fix... Check fatca status and only update fatca");
				fatresponse = bseEntryManager.saveFatcaDetails(investForm);
				if (fatresponse.getResponseCode().equals("100")) {
					logger.info("registerBsepost(): FATCA  complete for customer- " + investForm.getPan1());
					returnUrl = "redirect:/mutual-funds/mf-registration-status";
					session.setAttribute("CUSTOMER_TYPE", "NEW_CUSOTMER");
					attrs.addFlashAttribute("mfInvestForm", investForm);
					attrs.addAttribute("STATUS", "Y");
				} else {
					logger.info("registerBsepost(): MF registered but FATCA save failed. Return with result..");
					// map.addAttribute("success", "Your registration partially complete. Your FATCA
					// declaration failed for below reason-");
					map.addAttribute("error", "Registration success. FATCA declaration failed for- " + fatresponse.getRemarks());
					returnUrl = "bsemf/bse-form-new-customer";

				}

			}

			// flag ="SUCCESS";

			/*
			 * String customerid=BseRelatedActions.generateID(investForm.getInvName(),
			 * investForm.getPan1(), null, investForm.getMobile(),1);
			 * investForm.setClientID(customerid); String flag =
			 * investmentConnectorBseInterface.saveCustomerRegistration(investForm, null);
			 * if(flag.equalsIgnoreCase("false")){ returnUrl= "bsemf/bse-form-new-customer";
			 * map.addAttribute("error", "Customer issue with given PAN no.");
			 * 
			 * }
			 */
		} catch (Exception e) {
			returnUrl = "bsemf/bse-form-new-customer";
			map.addAttribute("error", "Unable to register customer currently.");
			logger.error("registerBsepost(): Unable to save customer registration", e);

		}

		if (returnUrl.equals("bsemf/bse-form-new-customer")) {

			map.addAttribute("holingNature", InvestFormConstants.holdingMode);
			map.addAttribute("dividendPayMode", InvestFormConstants.dividendPayMode);
			map.addAttribute("occupation", InvestFormConstants.occupationList);
			map.addAttribute("bankNames", InvestFormConstants.bankNames);
			map.addAttribute("accountTypes", InvestFormConstants.accountTypes);
			map.addAttribute("states", InvestFormConstants.states);
			map.addAttribute("wealthSource", InvestFormConstants.fatcaWealthSource);
			map.addAttribute("incomeSlab", InvestFormConstants.fatcaIncomeSlab);
			map.addAttribute("politicalView", InvestFormConstants.fatcaPoliticalView);
			map.addAttribute("occupationType", InvestFormConstants.fatcaOccupationType);
			map.addAttribute("nomineeRelation", InvestFormConstants.nomineeRelation);
			map.addAttribute("addressType", InvestFormConstants.fatcaAddressType);

			logger.info("registerBsepost(): Current date format before returning to registration page- - " + investForm.getInvDOB());
			try {
				logger.info("registerBsepost(): Convert DOB to datepicker format dd/mm/yyyy  before returning to page");
				Date dob = simpleDateFormat1.parse(investForm.getInvDOB());
				String convt = simpleDateFormat2.format(dob);
				investForm.setInvDOB(convt);

			} catch (Exception e) {
				logger.error("registerBsepost(): Failed converting DOB format. Setting as it is...");
			}
		}

		return returnUrl;

	}

	@RequestMapping(value = "/mutual-funds/mf-registration-status", method = RequestMethod.GET)
	public String mfRegistrationStatusGet(@ModelAttribute("STATUS") String status,
			@ModelAttribute("mfInvestForm") BseMFInvestForm investForm, ModelMap map, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {

		logger.info("mf-registration-status Get controller");
		logger.info("Type of holding- " + investForm.getHoldingMode());
		String returnUrl = "bsemf/bse-registration-status";

		// investForm.setPan1("as");
		logger.info("mfRegistrationStatusGet(): PAN from investor details: " + investForm.getPan1());
		session.setAttribute("mfRegisterdUser", investForm);
		/*
		 * if(investForm.getMobile()==null){ map.clear(); returnUrl=
		 * "redirect:/mutual-funds/top-performing"; }
		 */
		map.addAttribute("investForm", investForm);
		return returnUrl;

	}

	@CrossOrigin(origins = {"https://www.freemi.in"})
	@RequestMapping(value = "/mutual-funds/uploadsign", method = RequestMethod.POST)
	@ResponseBody
	public String uploadsign(/*
	 * @RequestBody CustomerSignature sign,@ModelAttribute("mfInvestForm")
	 * BseMFInvestForm investForm,
	 */ ModelMap map, HttpServletRequest request, HttpServletResponse response,
	 HttpSession session) {

		logger.info("mfRegisterSignature():  mf-signature-update post controller from URL- " + request.getRequestURI());
		String returnResponse = "SUCCESS";
		String mobile = "";

		String orderCallUrl = URI.create(request.getRequestURL().toString()).resolve(request.getContextPath()).toString();
		//		System.out.println(orderCallUrl);
		logger.info("Referrer URL for - "+ request.getParameter("referrer") );
		BseMFInvestForm investForm = null;
		boolean proceedwithsign = false;
		try {

			if (request.getParameter("referrer").contains(orderCallUrl + "/my-dashboard") ) {
				logger.info( "uploadsign(): Signature request received from my-dashboad. User session must be present valid to proceed.");
				if (session.getAttribute("token") != null && session.getAttribute("userid") != null) {

					try {
						ResponseEntity<String> apiresponse = profileRestClientService.validateUserToken(session.getAttribute("userid").toString(),session.getAttribute("token").toString(), CommonTask.getClientSystemIp(request));
						logger.debug("uploadsign(): RESPONSE- " + apiresponse.getBody());
						if (apiresponse.getBody().equals("VALID")) {
							logger.info("uploadsign(): Session found to be valid.. Proceed with upload");
							proceedwithsign = true;
							mobile = session.getAttribute("userid").toString();
							logger.info("uploadsign(): Upload sign for customer mobile from session- " + session.getAttribute("userid"));
							investForm = bseEntryManager.getCustomerInvestFormData(session.getAttribute("userid").toString());

						} else if (apiresponse.getBody().equals("EXPIRED")) {
							logger.info("uploadsign(): Session has expired for requesting user- "+ mobile);
							returnResponse = "SESSION_EXPIRED";

						} else {
							logger.info("uploadsign(): Session mismtach or invalid.. Not allowing to access.");
							returnResponse = "REQUEST_DENIED";
						}
					} catch (Exception e) {
						logger.error("uploadsign(): Failed to validate session for logged in user..", e);
						returnResponse = "INTERNAL_ERROR";
					}

				} else {
					logger.info("uploadsign(): User session not found. user need to login to proceed.. Request denied for user with mobile no: " + mobile);
					returnResponse = "NO_SESSION";
				}
			} else if (request.getParameter("referrer").contains(orderCallUrl + "/mutual-funds/mf-registration-status")) {
				logger.info( "uploadsign(): Signature request received from mf-registration-status. CUSTOMER_TYPE must be present in session to proceed");

				if (session.getAttribute("token") != null && session.getAttribute("userid") != null) {
					mobile = session.getAttribute("userid").toString();
					logger.info("Mobile number form session- "+ mobile);

					try {
						ResponseEntity<String> apiresponse = profileRestClientService.validateUserToken(session.getAttribute("userid").toString(),session.getAttribute("token").toString(), CommonTask.getClientSystemIp(request));
						logger.debug("uploadsign(): RESPONSE- " + apiresponse.getBody());
						if (apiresponse.getBody().equals("VALID")) {
							logger.info("uploadsign(): Session found to be valid.. Proceed with upload");
							proceedwithsign = true;
							mobile = session.getAttribute("userid").toString();
							logger.info("uploadsign(): Upload sign for customer mobile from session- " + session.getAttribute("userid"));
							investForm = bseEntryManager.getCustomerInvestFormData(session.getAttribute("userid").toString());

						} else if (apiresponse.getBody().equals("EXPIRED")) {
							logger.info("uploadsign(): Session has expired for requesting user- "+ mobile);
							returnResponse = "SESSION_EXPIRED";

						} else {
							logger.info("uploadsign(): Session mismtach or invalid.. Not allowing to access.");
							returnResponse = "REQUEST_DENIED";
						}
					} catch (Exception e) {
						logger.error("uploadsign(): Failed to validate session for logged in user..", e);
						returnResponse = "INTERNAL_ERROR";
					}

				}
				else if (session.getAttribute("CUSTOMER_TYPE") != null) {
					if (session.getAttribute("CUSTOMER_TYPE").toString().equals("NEW_CUSTOMER")) {
						proceedwithsign = true;

						investForm = (BseMFInvestForm) session.getAttribute("mfRegisterdUser");
						logger.info("uploadsign(): investForm from session - " + investForm);

						mobile = investForm.getMobile();

						logger.info("Mobile no from purchase form - "+ mobile);

					} else {
						logger.info("CUSTOMER_TYPE not valid type to accept request");
					}
				} else {
					logger.info("CUSTOMER_TYPE not found to proceed with request..");
					returnResponse = "REQUEST_DENIED";
				}

			} else {
				logger.info("Request received from unregistered URL to register sign");
				returnResponse = "REQUEST_DENIED";
			}

			if (proceedwithsign && !mobile.isEmpty()) {

				logger.info("uploadsign(): sign1 - " + request.getParameter("sign1"));
				logger.info("uploadsign(): sign2 - " + request.getParameter("sign2"));
				//				logger.info("mfRegisterSignature(): savesign: "+  request.getParameter("savesign"));
				logger.info("Referrer URL to sign upload- " + request.getParameter("referrer"));

				String customerSignature1 = request.getParameter("sign1");
				String customerSignature2 = request.getParameter("sign2");

				logger.info("uploadsign(): Request received for- " + mobile);
				//				investForm.setCustomerSignature(customerSignature1);
				investForm.setCustomerSignature1(customerSignature1);
				investForm.setCustomerSignature2(customerSignature2);

				if ((investForm.getHoldingMode().equals("AS") || investForm.getHoldingMode().equals("JO")) && (customerSignature2.isEmpty() || customerSignature2.split(",")[1].length() <=1594 || customerSignature1.equals(customerSignature2) ) ) {
					logger.info("uploadsign(): Signature required from both applicant.");
					returnResponse = "APP2_SIGN_REQUIRED";
				} else if (customerSignature1.equals(customerSignature2)) {
					logger.info("mfRegisterSignature(): both signature same");
					returnResponse = "BOTH_SIGN_SAME";
				} else {

					String result = "";
					String fileName = investForm.getPan1() + ".pdf";
					String flag1 = BseAOFGenerator.aofGenerator(investForm, fileName,
							env.getProperty("investment.bse.aoffile.logo"), "VERIFIED",
							env.getProperty(CommonConstants.BSE_AOF_GENERATION_FOLDR));
					logger.info("uploadsign(): Status of AOF generation- " + flag1);
					if (flag1.equalsIgnoreCase("SUCCESS")) {
						logger.info("uploadsign(): Signed AOF file generation complete for customer- "
								+ investForm.getPan1());
						result = bseEntryManager.upddateCustomerFormSignature(investForm.getMobile(),
								investForm.getPan1(), investForm.getCustomerSignature1(),
								investForm.getCustomerSignature2());
						if (result.equalsIgnoreCase("SUCCESS")) {
							session.setAttribute("PURCHASE_TYPE", "NEW_CUSTOMER");
							map.addAttribute("AOF", "COMPLETE");
						} else {
							map.addAttribute("AOF", "FAIL");
						}
					} else {
						logger.info("uploadsign(): Failed to generate AOF. Check internal system.");
						returnResponse = "INTERNAL_ERROR";
					}
				}
			} else {
				logger.info( "uploadsign(): User BSERegister form not found in session. Request denied. User need to login to complete task. ");
				returnResponse = "REQUEST_DENIED";
			}
		} catch (Exception ex) {
			logger.error("uploadsign(): No registration sesssion found, Rejecting request.", ex);
			returnResponse = "INTERNAL_ERROR";
		}
		logger.info("uploadsign(): returnResponse - " + returnResponse);
		return returnResponse;

	}

	/*
	 * @RequestMapping(value = "/mutual-funds/uploadsignRegisteredCustomer", method
	 * = RequestMethod.POST)
	 * 
	 * @ResponseBody public String mfaofSignUploadRegisteredCustomer(@RequestBody
	 * CustomerSignature sign,@ModelAttribute("mfInvestForm") BseMFInvestForm
	 * investForm, ModelMap map, HttpServletRequest request, HttpServletResponse
	 * response,HttpSession session) {
	 * 
	 * logger.
	 * info("mfaofSignUploadRegisteredCustomer(): mf-signature-udpate post for registered customer incomplete controller"
	 * ); String returnResponse = "SUCCESS";
	 * 
	 * 
	 * try{
	 * 
	 * // BseMFInvestForm investForm = (BseMFInvestForm)
	 * session.getAttribute("mfRegisterdUser"); // logger.info(investForm); if(
	 * session.getAttribute("token")!=null && session.getAttribute("userid")!=null){
	 * logger.
	 * info("mfaofSignUploadRegisteredCustomer(): Upload sign for customer mobile from session- "
	 * + session.getAttribute("userid")); BseMFInvestForm investForm=
	 * bseEntryManager.getCustomerInvestFormData(session.getAttribute("userid").
	 * toString()); session.setAttribute("mfRegisterdUser", investForm);
	 * 
	 * logger.info("mfaofSignUploadRegisteredCustomer(): Invest form- " +
	 * investForm);
	 * 
	 * String customerSignature1 = request.getParameter("sign1"); String
	 * customerSignature2 = request.getParameter("sign2");
	 * 
	 * logger.info("mfaofSignUploadRegisteredCustomer(): sign1 - "+
	 * request.getParameter("sign1"));
	 * logger.info("mfaofSignUploadRegisteredCustomer(): sign2 - "
	 * +request.getParameter("sign2"));
	 * logger.info("mfaofSignUploadRegisteredCustomer(): savesign: "+
	 * request.getParameter("savesign"));
	 * 
	 * logger.
	 * info("mfaofSignUploadRegisteredCustomer(): AOF Sign Request received for- "
	 * +investForm.getMobile());
	 * investForm.setCustomerSignature1(customerSignature1);
	 * investForm.setCustomerSignature2(customerSignature2);
	 * 
	 * String result=""; String fileName=investForm.getPan1()+".pdf";
	 * returnResponse= BseAOFGenerator.aofGenerator(investForm, fileName,
	 * env.getProperty("investment.bse.aoffile.logo"), "VERIFIED",
	 * env.getProperty(CommonConstants.BSE_AOF_GENERATION_FOLDR)); logger.
	 * info("mfaofSignUploadRegisteredCustomer(): AOF Form generation status for PAN-  "
	 * + investForm.getPan1() + " : "+ returnResponse);
	 * if(returnResponse.equalsIgnoreCase("SUCCESS")){ logger.
	 * info("mfaofSignUploadRegisteredCustomer(): Signed AOF file generation complete for customer- "
	 * + investForm.getPan1()); result=
	 * bseEntryManager.upddateCustomerFormSignature(investForm.getMobile(),
	 * investForm.getPan1(), customerSignature1,customerSignature2); logger.
	 * info("mfaofSignUploadRegisteredCustomer():upddateCustomerFormSignature result -  "
	 * + result);
	 * 
	 * if(result.equalsIgnoreCase("SUCCESS")){ map.addAttribute("AOF", "COMPLETE");
	 * }else{ map.addAttribute("AOF", "FAIL"); }
	 * 
	 * }
	 * 
	 * if(result.equalsIgnoreCase("SUCCESS")){ session.setAttribute("PURCHASE_TYPE",
	 * "NEW_CUSTOMER"); map.addAttribute("AOF", "COMPLETE"); } else { logger.
	 * info("mfaofSignUploadRegisteredCustomer(): Failed to generate AOF. Check internal system."
	 * ); returnResponse = "INTERNAL_ERROR"; } }else{ logger.
	 * info("mfaofSignUploadRegisteredCustomer(): Customer session not found. Rejecting request"
	 * ); returnResponse = "NO_SESSION"; } }catch(NullPointerException ex){
	 * logger.error("mfaofSignUploadRegisteredCustomer(): Error reading data.",ex);
	 * returnResponse = "REQUEST_INVALID"; }
	 * logger.info("mfaofSignUploadRegisteredCustomer(): returnResponse - "+
	 * returnResponse); return returnResponse;
	 * 
	 * }
	 */


	@RequestMapping(value = "/mutual-funds/uploadsignedaof", method = RequestMethod.GET)
	@ResponseBody
	public String uploadSignedAOFFile(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

		logger.info("uploadSignedAOFFile(): mf-signature-udpate post controller");
		String result = "SUCCESS";
		String mobile = "";
		String requestedMobile = "";
		BseMFInvestForm investForm;
		/*	if (request.getParameter("referrer").contains(orderCallUrl + "/my-dashboard") ) {
			logger.info( "uploadsign(): Signature request received from my-dashboad. User session must be present valid to proceed.");*/
		if (session.getAttribute("token") != null && session.getAttribute("userid") != null) {

			try {
				ResponseEntity<String> apiresponse = profileRestClientService.validateUserToken(session.getAttribute("userid").toString(),session.getAttribute("token").toString(), CommonTask.getClientSystemIp(request));
				logger.debug("uploadSignedAOFFile(): RESPONSE- " + apiresponse.getBody());
				if (apiresponse.getBody().equals("VALID")) {
					logger.info("uploadSignedAOFFile(): Session found to be valid.. Proceed with upload");
					mobile = session.getAttribute("userid").toString();
					requestedMobile = mobile;

					logger.info("uploadSignedAOFFile(): Upload sign for customer mobile from session- " + session.getAttribute("userid"));
					investForm = bseEntryManager.getCustomerInvestFormData(session.getAttribute("userid").toString());

				} else if (apiresponse.getBody().equals("EXPIRED")) {
					logger.info("uploadSignedAOFFile(): Session has expired for requesting user- "+ mobile);
					result = "SESSION_EXPIRED";

				} else {
					logger.info("uploadSignedAOFFile(): Session mismtach or invalid.. Not allowing to access.");
					result = "REQUEST_DENIED";
				}
			} catch (Exception e) {
				logger.error("uploadSignedAOFFile(): Failed to validate session for logged in user..", e);
				result = "INTERNAL_ERROR";
			}

		}else {
			investForm = (BseMFInvestForm) session.getAttribute("mfRegisterdUser");
			mobile = investForm.getMobile();
			requestedMobile = request.getParameter("mobile");
		}

		try {

			if (mobile != "") {

				logger.info("uploadSignedAOFFile(): Get mobile no- " + request.getParameter("mobdata"));
				if (requestedMobile.equalsIgnoreCase(mobile)) {
					// returnUrl="SUCCESS";
					String clientCode = bseEntryManager.getClientIdfromMobile(requestedMobile);
					String panForAOfFile = bseEntryManager.getCustomerPanfromMobile(requestedMobile);
					// call api to upload pdf

					logger.info("uploadSignedAOFFile(): Call API uploadAOFForm");
					BseAOFUploadResponse aofresp1 = investmentConnectorBseInterface.uploadAOFForm(panForAOfFile,
							env.getProperty(CommonConstants.BSE_AOF_GENERATION_FOLDR), clientCode);
					logger.info("uploadSignedAOFFile(): AOF upload status as received- " + aofresp1.getStatusMessage());
					if (aofresp1.getStatusCode().equalsIgnoreCase("100") || (aofresp1.getStatusCode()
							.equalsIgnoreCase("101")
							&& (aofresp1.getStatusMessage().contains("Exception caught at Service Application")
									|| aofresp1.getStatusMessage().contains("PAN NO ALREADY APPROVED")
									|| aofresp1.getStatusMessage()
									.contains("IMAGE IS ALREADY AVAILABLE AND IMAGE STATUS IS PENDING")))) {
						String updateStatus = bseEntryManager.uploadAOFFormStatus(mobile, "Y");
						logger.info("uploadSignedAOFFile(): AOF upload status to database- " + updateStatus);
					} else {
						result = aofresp1.getStatusMessage();
					}
				} else {
					logger.info("Mobile number do not match with holding session form data. Request rejected. Session mobile no:"+ mobile + " : Requested mobile: " + requestedMobile);
					result = "SESSION_MOB_MISMATCH";
				}

				// String result=
				// bseEntryManager.upddateCustomerFormSignature(investForm.getMobile(),
				// investForm.getPan1(), investForm.getCustomerSignature());

			} else {
				result = "REQUEST_DENIED";
			}
		} catch (Exception e) {
			logger.error("Error with service", e);
			result = "INTERNAL_ERROR";
		}



		logger.info("uploadSignedAOFFile(): returnResponse - " + result);
		return result;

	}

	@RequestMapping(value = "/mutual-funds/top-performing", method = RequestMethod.GET)
	public String getTopPerformingFunds(@RequestParam(name = "info", required = false) String correctInfoAfterLogin,
			Model map, HttpServletRequest request, HttpServletResponse response, HttpSession session) {

		logger.info("getTopPerformingFunds(): MF Funds Inventory");
		// List<MfTopFundsInventory> topFunds = null;
		List<BseMFTop15lsSip> topFunds = null;

		/*
		 * List<ProductSchemeDetail> allFunds =
		 * productSchemeDetailService.findForRegistryBirthDay();
		 * map.addAttribute("mffunds", allFunds); logger.info("Funds size- "+
		 * allFunds.size()); for(int i=0;i<allFunds.size();i++){
		 * logger.info(allFunds.get(i).getFundName()); }
		 * 
		 */
		// logger.info("URL- "+ request.getRequestURL()+ " : "+ request.getContextPath()
		// + " : "+ request.getProtocol() + " : "+ request.getServerName());
		// logger.info(URI.create(request.getRequestURL().toString()).resolve(request.getContextPath()));
		if (correctInfoAfterLogin != null) {
			logger.info("getTopPerformingFunds(): Error code received for transaction - " + correctInfoAfterLogin);
			if (correctInfoAfterLogin.equals("01")) {
				map.addAttribute("error",
						"Provided details mismatch with registered details. Transaction aborted. Please try again.");
			}
		}
		if (session.getAttribute("topFunds") != null) {
			// topFunds = (List<MfTopFundsInventory>) session.getAttribute("topFunds");
			topFunds = (List<BseMFTop15lsSip>) session.getAttribute("topFunds");
			logger.info("getTopPerformingFunds(): Serving top funds from session - " + topFunds.size());
			map.addAttribute("FUNDSFOUND", "Y");
			map.addAttribute("topFunds", topFunds);
			logger.info("getTopPerformingFunds(): Total funds from session - " + topFunds.size());
		} else {
			logger.info("getTopPerformingFunds(): Get top funds from database");
			try {
				// topFunds = bseEntryManager.getTopMfFunds();
				topFunds = bseEntryManager.getTopFunds();
				map.addAttribute("FUNDSFOUND", "Y");
				map.addAttribute("topFunds", topFunds);
				session.setAttribute("topFunds", topFunds);
				logger.info("getTopPerformingFunds(): Total funds received- " + topFunds.size());
			} catch (Exception e) {
				logger.error("getTopPerformingFunds(): Error fetching Funds list. Please try after sometime");
				map.addAttribute("FUNDSFOUND", "N");
			}
		}
		SelectMFFund fundChoice = new SelectMFFund();
		if (session.getAttribute("token") != null) {
			fundChoice.setMobile(session.getAttribute("userid").toString());
			try {
				String panNumber = bseEntryManager.getCustomerPanfromMobile(fundChoice.getMobile());
				fundChoice.setPan(panNumber);
			} catch (Exception e) {
				logger.error("getTopPerformingFunds(): Database connect issue: unable to fetch customer PAN number", e);
			}
		}
		map.addAttribute("selectFund", fundChoice);
		map.addAttribute("contextcdn", env.getProperty(CommonConstants.CDN_URL));
		String returnUrl = "bsemf/top-performing-funds";

		return returnUrl;

	}

	/*
	 * @RequestMapping(value = "/mutual-funds/funds-explorer", method =
	 * RequestMethod.GET) public String getAllFundsExplorer(Model map,
	 * HttpServletRequest request, HttpServletResponse response, HttpSession
	 * session) {
	 * 
	 * logger.info("MF All Funds Inventory");
	 * 
	 * String returnUrl = "bsemf/all-funds";
	 * 
	 * PageRequest p =new PageRequest(0, 200); Pageable pg = p.first();
	 * 
	 * Page<BseFundsScheme> b= bseEntryManager.getpaginatedFundsList(pg);
	 * logger.info("Paginated fundss- "+ b.getSize()); logger.info("Total pages- "+
	 * b.getTotalPages());
	 * 
	 * List<BseFundsScheme> funds = b.getContent();
	 * 
	 * SelectMFFund fundChoice = new SelectMFFund();
	 * if(session.getAttribute("token")!=null){
	 * fundChoice.setMobile(session.getAttribute("userid").toString()); try{ String
	 * panNumber = bseEntryManager.getCustomerPanfromMobile(fundChoice.getMobile());
	 * fundChoice.setPan(panNumber); }catch(Exception e){
	 * logger.error("Database connect issue: unable to fetch customer PAN number",
	 * e); } }
	 * 
	 * map.addAttribute("fundsexplorer", funds); map.addAttribute("selectFund",
	 * fundChoice); map.addAttribute("contextcdn",
	 * env.getProperty(CommonConstants.CDN_URL));
	 * 
	 * return returnUrl;
	 * 
	 * }
	 */

	@RequestMapping(value = "/mutual-funds/funds-explorer", method = RequestMethod.GET)
	public String getSelectFundsExplorer(Model map, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {

		logger.info("getSelectFundsExplorer(): MF All Funds Inventory");

		String returnUrl = "bsemf/select-fund-explorer";
		// System.out.println("Funds explorer- session: "+ session.getId());
		/*
		 * PageRequest p =new PageRequest(0, 200); Pageable pg = p.first();
		 */
		List<BseMFSelectedFunds> funds = null;
		funds = bseEntryManager.getAllSelectedFunds();

		logger.info("getSelectFundsExplorer(): Total selected funds to display- "
				+ (funds != null ? funds.size() : "NULL returned"));
		/*
		 * logger.info("Paginated fundss- "+ b.getSize()); logger.info("Total pages- "+
		 * b.getTotalPages());
		 * 
		 * List<BseFundsScheme> funds = b.getContent();
		 */

		SelectMFFund fundChoice = new SelectMFFund();
		if (session.getAttribute("token") != null) {
			fundChoice.setMobile(session.getAttribute("userid") != null ? session.getAttribute("userid").toString()
					: "USERID NOT FOUND");
			try {
				String panNumber = bseEntryManager.getCustomerPanfromMobile(fundChoice.getMobile());
				fundChoice.setPan(panNumber);
			} catch (Exception e) {
				logger.error("getSelectFundsExplorer(): Database connect issue: unable to fetch customer PAN number",
						e);
			}
		}

		map.addAttribute("fundsexplorer", funds);
		map.addAttribute("selectFund", fundChoice);
		map.addAttribute("contextcdn", env.getProperty(CommonConstants.CDN_URL));

		return returnUrl;

	}

	@RequestMapping(value = "/mutual-funds/getFunds", method = RequestMethod.POST)
	public String getFunds(Model map, HttpServletRequest request, HttpServletResponse response, HttpSession session) {

		logger.info("getFunds(): MF All Funds Inventory");

		String returnUrl = "bsemf/select-fund-explorer";

		PageRequest p = new PageRequest(0, 200);
		Pageable pg = p.first();

		Page<BseFundsScheme> b = bseEntryManager.getpaginatedFundsList(pg);
		logger.info("getFunds(): Paginated fundss- " + b.getSize());
		logger.info("getFunds(): Total pages- " + b.getTotalPages());

		List<BseFundsScheme> funds = b.getContent();

		SelectMFFund fundChoice = new SelectMFFund();
		if (session.getAttribute("token") != null) {
			fundChoice.setMobile(session.getAttribute("userid").toString());
			try {
				String panNumber = bseEntryManager.getCustomerPanfromMobile(fundChoice.getMobile());
				fundChoice.setPan(panNumber);
			} catch (Exception e) {
				logger.error("getFunds(): Database connect issue: unable to fetch customer PAN number", e);
			}
		}

		map.addAttribute("fundsexplorer", funds);
		map.addAttribute("selectFund", fundChoice);
		map.addAttribute("contextcdn", env.getProperty(CommonConstants.CDN_URL));

		return returnUrl;

	}

	@RequestMapping(value = "/mutual-funds/purchase.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String purchasemfbsePost(@ModelAttribute("selectFund") SelectMFFund selectedFund, BindingResult bindResult,
			Model map, HttpServletRequest request, HttpServletResponse response, HttpSession session,
			RedirectAttributes redirectAttrs) {

		logger.info("purchasemfbsePost(): BSE MF STAR Purchse.do controller from url - " + request.getRequestURL()
		+ " : Request type - " + request.getMethod());
		String returnUrl = "redirect:/mutual-funds/funds-explorer";
		logger.info("purchasemfbsePost(): Re-invest code- " + selectedFund.getReinvSchemeCode());

		if (bindResult.hasErrors()) {
			map.addAttribute("error", bindResult.getFieldError().getDefaultMessage());
			return "bsemf/bse-form-new-customer";
		}

		try {
			// Check if existing BSE registered customer or not
			boolean flag = bseEntryManager.isExisitngCustomer(selectedFund.getPan(), selectedFund.getMobile());
			logger.info("purchasemfbsePost(): Is existing customer? - " + selectedFund.getPan() + " : " + flag);

			logger.info("purchasemfbsePost(): Setting session for current selected fund with customer details");
			session.removeAttribute("selectedFund");
			session.setAttribute("selectedFund", selectedFund);
			if (flag && session.getAttribute("token") == null) {

				session.setAttribute("NEXT_URL", "/mutual-funds/purchase");
				redirectAttrs.addAttribute("ref",
						URLEncoder.encode(request.getRequestURL().toString(), StandardCharsets.UTF_8.toString()));
				returnUrl = "redirect:/login?mf=00"; // Already existing customer, just login and fetch customer details
			} else if ((flag && session.getAttribute("token") != null)) {

				// If logged in account PAN and mobile do not match with provided PAN. Revert
				// back to page which should pick up correct details from session now overriding

				String pan = bseEntryManager.getCustomerPanfromMobile(session.getAttribute("userid").toString());

				if (!session.getAttribute("userid").toString().equalsIgnoreCase(selectedFund.getMobile())
						&& !pan.equalsIgnoreCase(selectedFund.getPan())) {
					logger.warn(
							"purchasemfbsePost(): Customer logged in with another mobile than provided during form fillup. Also the PAN number is different. Redirecting back to fund selection page and override information with current details");
					logger.warn("purchasemfbsePost(): Data provided during form fillup:[ " + selectedFund.getPan() + " : " + selectedFund.getMobile() + "] Data from session user:[" + pan + " : "+ session.getAttribute("userid").toString() + "]");

					redirectAttrs.addFlashAttribute("USERINFO", "01");
					returnUrl = "redirect:/mutual-funds/funds-explorer";
				} else {
					returnUrl = "redirect:/mutual-funds/purchase";
				}

			} else {
				// Check if he is registered customer form LDAP. If already registered customer,
				// then only need to register for MF, else create profile password as well

				// RestClient client = new RestClient();
				ResponseEntity<String> responseProfile = null;

				try {
					responseProfile = profileRestClientService.isUserExisitng(selectedFund.getMobile());
					logger.info("purchasemfbsePost(): Response for user existing check- " + responseProfile.getBody());

				} catch (HttpStatusCodeException e) {
					logger.info("purchasemfbsePost(): Failed to check user exisitng status - " + e.getStatusCode());
				} catch (JsonProcessingException e) {
					logger.error("purchasemfbsePost(): Json parsing excetption- ", e);
				} catch (Exception e) {
					logger.error("purchasemfbsePost(): Error processing request- ", e);
				}
				if (responseProfile.getBody().equalsIgnoreCase("Y")) {

					if (session.getAttribute("token") != null) {
						returnUrl = "redirect:/mutual-funds/register?mf=01";
					} else {
						returnUrl = "redirect:/login?mf=01"; // User exist, so just need to register MF profile, do not create profile
					}

				} else if (responseProfile.getBody().equalsIgnoreCase("N")) {
					returnUrl = "redirect:/mutual-funds/register?mf=02"; // Complete fresh customer. Create both profile and register for MF
					session.setAttribute("PURCHASE_TYPE", "NEW_CUSTOMER");
				} else {
					logger.warn("purchasemfbsePost(): Failed to get cutomer status from LDAP");
					returnUrl = "redirect:/mutual-funds/register?mf=03";

				}
				redirectAttrs.addFlashAttribute("selectedFund", selectedFund);
				// returnUrl="redirect:/mutual-funds/register";
			}
		} catch (Exception e) {
			logger.error("purchasemfbsePost(): Failed to check customer in databases/LDAP", e);

		}
		/*
		 * try{ boolean flag = bseEntryManager.savetransactionDetails(selectedFund);
		 * logger.info("Customer purchase transaction status- "+ flag); }catch(Exception
		 * e){
		 * logger.error("Unable to save customer transaction request",e.getMessage()); }
		 */

		return returnUrl;

		/*
		 * redirectAttrs.addFlashAttribute("selectedFund", selectedFund);
		 * returnUrl="redirect:/mutual-funds/register"; return returnUrl;
		 */

	}

	@RequestMapping(value = "/mutual-funds/purchase", method = RequestMethod.GET)
	public String purchaseBseMfAfterLogin(Model map, HttpServletRequest request, HttpServletResponse response,
			HttpSession session, RedirectAttributes redirectAttrs) {

		logger.info("purchaseBseMfAfterLogin(): BSE MF STAR Purchase Get controller");
		String returnUrl = "bsemf/bse-mf-purchase";

		List<BseMFInvestForm> customerData = null;
		SelectMFFund selectedFund = null;
		List<String> customerPortfolios = new ArrayList<String>();

		try {
			logger.info("purchaseBseMfAfterLogin(): PURCHASE_TYPE null? "
					+ (session.getAttribute("PURCHASE_TYPE") != null));
			logger.info("purchaseBseMfAfterLogin(): PURCHASE_TYPE -> " + session.getAttribute("PURCHASE_TYPE"));
		} catch (Exception e) {
			logger.error("purchaseBseMfAfterLogin(): Error reading purchase type customer", e);
		}

		try {
			selectedFund = (SelectMFFund) session.getAttribute("selectedFund");
			if (selectedFund == null) {
				logger.info(
						"purchaseBseMfAfterLogin(): No selected funds details found is session. Returning to fund selectin page.");
				return "redirect:/mutual-funds/funds-explorer";
			}

			if (session.getAttribute("token") != null) {

				logger.info("purchaseBseMfAfterLogin(): Purchase order start for- " + selectedFund.getSchemeName()
				+ " : " + selectedFund.getSchemeCode());

				String pan = bseEntryManager.getCustomerPanfromMobile(session.getAttribute("userid").toString());

				if (!session.getAttribute("userid").toString().equalsIgnoreCase(selectedFund.getMobile())
						|| !pan.equalsIgnoreCase(selectedFund.getPan())) {
					logger.warn(
							"purchaseBseMfAfterLogin(): Customer logged in with another mobile than provided during form fillup. Also the PAN number is different. Redirecting back to fund selection page and override information with current details");
					logger.warn("purchaseBseMfAfterLogin(): Data provided during form fillup:[ " + selectedFund.getPan()
					+ " : " + selectedFund.getMobile() + "] Data from session user:[" + pan + " : "
					+ session.getAttribute("userid").toString() + "]");

					redirectAttrs.addFlashAttribute("USERINFO", "01");
					// returnUrl = "redirect:/mutual-funds/top-performing";
					logger.info("purchaseBseMfAfterLogin(): Further processing prevented. Return");
					return "redirect:/mutual-funds/funds-explorer";
				} else {
					logger.info("purchaseBseMfAfterLogin(): Customer log in data matches with form data.");

					customerData = bseEntryManager.getCustomerByPan(selectedFund.getPan());
					// logger.info("Data size returned- "+ customerData.size());
					// Find customer's portfolio

					if (customerData.get(0).getBseregistrationSuccess().equalsIgnoreCase("N")) {
						map.addAttribute("REG_COMPLETE", "N");
					} else {
						map.addAttribute("REG_COMPLETE", "Y");
					}

					logger.info("purchaseBseMfAfterLogin(): Search for customer portfolio for details: "
							+ selectedFund.getAmcCode() + " :PAN : " + customerData.get(0).getClientID()
							+ " : RTA Agent: " + selectedFund.getRtaAgent());

					customerPortfolios = bseEntryManager.getSelectedAmcPortfolio(selectedFund.getSchemeCode(),
							selectedFund.getPan(), selectedFund.getRtaAgent());

					if (customerPortfolios != null) {
						logger.info("purchaseBseMfAfterLogin(): Portfolio size- " + customerPortfolios != null
								? customerPortfolios.size()
										: "No portfolio data found..");
						/*
						 * if(customerPortfolios.size()== 0){ customerPortfolios.add("NEW"); }else{
						 * selectedFund.setPortfolio(customerPortfolios.get(0)); }
						 */
						if (customerPortfolios.size() > 0) {
							selectedFund.setPortfolio(customerPortfolios.get(0));
						}
					}
					/*
					 * map.addAttribute("amcPortFolio", customerPortfolios);
					 * 
					 * map.addAttribute("customerData", customerData.get(0));
					 * map.addAttribute("GETDATA", "S");
					 */
				}

				/*
				 * try{ //Find customer's portfolio
				 * logger.info("Search for customer portfolio for details: "+
				 * selectedFund.getAmcCode() + " : "+ customerData.get(0).getClientID());
				 * List<String> customerPortfolios =
				 * bseEntryManager.getSelectedAmcPortfolio(selectedFund.getAmcCode(),
				 * customerData.get(0).getClientID());
				 * 
				 * //Generate Transaction ID and check if already existing String transId =
				 * generateTransId();
				 * 
				 * selectedFund.setTransactionID(transId);
				 * 
				 * logger.info("Portfolio size- "+ customerPortfolios.size());
				 * if(customerPortfolios.size()== 0){ customerPortfolios.add("NEW"); }else{
				 * selectedFund.setPortfolio(customerPortfolios.get(0)); }
				 * map.addAttribute("amcPortFolio", customerPortfolios); }catch(Exception e){
				 * logger.error("Failed to get customer portfolio"); e.printStackTrace(); }
				 */

			} else if ((session.getAttribute("PURCHASE_TYPE") != null
					&& session.getAttribute("PURCHASE_TYPE").toString().equalsIgnoreCase("NEW_CUSTOMER"))) {

				logger.info(
						"purchaseBseMfAfterLogin(): Customer is processing to purchase after completing registration without login with mobile- "
								+ selectedFund.getMobile() + " : PAN: " + selectedFund.getPan());
				customerPortfolios.add("NEW");
				customerData = bseEntryManager.getCustomerByPan(selectedFund.getPan());

				if (customerData.get(0).getBseregistrationSuccess().equalsIgnoreCase("N")) {
					logger.info("purchaseBseMfAfterLogin(): registartion process not complete... ");
					map.addAttribute("REG_COMPLETE", "N");
				} else {
					map.addAttribute("REG_COMPLETE", "Y");
				}

			} else {
				logger.info(
						"purchaseBseMfAfterLogin(): MF purchase request required customer to be either logged in or be a fresh customer to access the url. Returning");
				// returnUrl="redirect:/mutual-funds/top-performing";
				return "redirect:/mutual-funds/funds-explorer";
			}

			// Set SIP start date from
			if (selectedFund.getInvestype().equalsIgnoreCase("SIP")) {
				int initialMonth = LocalDate.now().getMonth().getValue();
				int initialYear = LocalDate.now().getYear();
				int currentday = LocalDate.now().getDayOfMonth();
				if (Integer.valueOf(selectedFund.getSipDate()) > currentday) {
					selectedFund.setSipStartMonth(initialMonth);
				} else {
					selectedFund.setSipStartMonth(initialMonth + 1);
				}
				selectedFund.setSipStartYear(initialYear);

				selectedFund.setNoOfInstallments(60); // Default SIP installments to 5 years -- todo dynamic
			}

			if (customerData.size() != 0) {
				selectedFund.setClientID(customerData.get(0).getClientID());
				logger.info("purchaseBseMfAfterLogin(): Investor name- " + customerData.get(0).getInvName());
				// logger.info("Bank details- "+
				// customerData.get(0).getBankDetails().getBankName());

				UserBankDetails userbankDetails = bseEntryManager
						.getCustomerBankDetails(customerData.get(0).getClientID());

				if (userbankDetails != null) {

					List<BseMandateDetails> mandate = bseEntryManager.getCustomerMandateDetails(
							customerData.get(0).getClientID(), userbankDetails.getAccountNumber());
					logger.info("purchaseBseMfAfterLogin(): Total emdandates fetched-  " + mandate.size());
					if (mandate.size() > 0 && mandate.get(0).isMandateComplete()) {
						logger.info("purchaseBseMfAfterLogin(): Emandate found for current customer.");
						selectedFund.setMandateType(mandate.get(0).getMandateType());
						selectedFund.seteMandateRegRequired(false);
						selectedFund.setMandateId(mandate.get(0).getMandateId());
					} else {
						logger.info("purchaseBseMfAfterLogin(): No emnadate found for customer..");
						selectedFund.seteMandateRegRequired(true);
					}

					map.addAttribute("bankacc",
							userbankDetails.getAccountNumber() != null
							? "XXXXXXXXX" + userbankDetails.getAccountNumber()
							.substring(userbankDetails.getAccountNumber().length() - 3)
							: "NOT AVAILABLE");
					map.addAttribute("bankname", userbankDetails.getBankName());
					/* map.addAttribute("ifsc", userbankDetails.getIfscCode()); */
					map.addAttribute("isEmandateComplete", !selectedFund.iseMandateRegRequired()); // should to opposite
					// to
					// emandatecompelete
					// staus
				} else {
					logger.info(
							"purchaseBseMfAfterLogin(): Customer bank details not found. Check server log for details");
				}
			} else {
				logger.info("purchaseBseMfAfterLogin(): No customer detaails found for purchase!");
			}

			// Generate Transaction ID and check if already existing
			String transId = generateTransId();
			logger.info("purchaseBseMfAfterLogin(): Generated transation ID for current order of customer: "
					+ customerData.get(0).getClientID() + " : " + transId);

			selectedFund.setTransactionID(transId);

			map.addAttribute("amcPortFolio", customerPortfolios);
			map.addAttribute("customerData", customerData.get(0));
			map.addAttribute("GETDATA", "S");

			map.addAttribute("paymentMethod", InvestFormConstants.bsePaymentMethod);
			map.addAttribute("calendarmonths", InvestFormConstants.bseInvestMonths);
			map.addAttribute("sipyear", InvestFormConstants.bseInvestStartYear);
			map.addAttribute("selectedFund", selectedFund);

		} catch (Exception e) {
			logger.error("purchaseBseMfAfterLogin(): Unable to query database to fetch customer data- ", e);
			map.addAttribute("GETDATA", "F");
		}
		logger.info("purchaseBseMfAfterLogin(): Returning to view " + returnUrl);
		return returnUrl;

	}

	private String generateTransId() {
		boolean transIdExist = false;
		String transId = null;
		do {
			transId = BseRelatedActions.generateTransactionId();
			transIdExist = bseEntryManager.checkIfTransIdExist(transId);
		} while (transIdExist);
		return transId;
	}

	@RequestMapping(value = "/mutual-funds/mfPurchaseConfirm.do", method = RequestMethod.GET)
	public String purchaseConfirmGet(HttpServletRequest request, HttpServletResponse response) {
		return "return:/products/";

	}

	@RequestMapping(value = "/mutual-funds/mfPurchaseConfirm.do", method = RequestMethod.POST)
	public String purchaseConfirmPost(@Valid @ModelAttribute("selectedFund") SelectMFFund selectedFund,
			BindingResult bindResult, Model map, HttpServletRequest request, HttpServletResponse response,
			HttpSession session, final RedirectAttributes redirectAttrs) {

		logger.info("@@ BSE MF STAR purchase confirm controller @@");
		String returnUrl = "redirect:/mutual-funds/bse-transaction-status";

		/*
		 * if(session.getAttribute("token")==null){ logger.
		 * info("purchaseConfirmPost(): No user session found.. Sending back to login.."
		 * ); return "redirect:/login"; }
		 */

		logger.info("Client ID - " + selectedFund.getClientID());
		logger.info("Pay first install? " + selectedFund.isPayFirstInstallment());
		TransactionStatus transationResult = new TransactionStatus();
		String mandateId = "";
		boolean mandareGenerated = false;

		if (bindResult.hasErrors()) {
			map.addAttribute("errormsg", bindResult.getFieldError().getDefaultMessage());
			map.addAttribute("paymentMethod", InvestFormConstants.bsePaymentMethod);
			map.addAttribute("calendarmonths", InvestFormConstants.bseInvestMonths);
			map.addAttribute("sipyear", InvestFormConstants.bseInvestStartYear);
			return "bsemf/bse-mf-purchase";
		}

		logger.info("Selected scheme code for purchase- " + selectedFund.getSchemeCode());

		// set sip date if chosen
		// boolean f = Integer.valueOf(selectedFund.getSipStartMonth())<10;
		if (selectedFund.getInvestype().equalsIgnoreCase("SIP")) {

			// logger.info(combineDate);
			// validate date if prioor to today -todo

			try {
				String combineDate = (selectedFund.getSipDate().length() == 1 ? "0" + selectedFund.getSipDate()
				: selectedFund.getSipDate())
						+ "/"
						+ ((Integer.valueOf(selectedFund.getSipStartMonth()) < 10)
								? "0" + Integer.toString(Integer.valueOf(selectedFund.getSipStartMonth()))
								: Integer.toString(Integer.valueOf(selectedFund.getSipStartMonth())))
						+ "/" + selectedFund.getSipStartYear();
				selectedFund.setSipStartDate((new SimpleDateFormat("dd/MM/yyyy")).parse(combineDate));
			} catch (Exception e) {
				logger.error("Failed to convert date to required format for SIP.", e);
				map.addAttribute("errormsg", "Failed to process the date!");
				map.addAttribute("paymentMethod", InvestFormConstants.bsePaymentMethod);
				map.addAttribute("calendarmonths", InvestFormConstants.bseInvestMonths);
				map.addAttribute("sipyear", InvestFormConstants.bseInvestStartYear);
				return "bsemf/bse-mf-purchase";
			}
			// selectedFund.setNoOfInstallments(60); //Default SIP installments to 5 years
			// -- todo dynamic
			logger.info("Is emandate registration required?- " + selectedFund.iseMandateRegRequired());
			// Get MANDATE ID
			if (selectedFund.iseMandateRegRequired()) {
				logger.info("Customer emandate registration need to be processed first...");
				/* flag.setEmandateRequired(true); */
				BseApiResponse emandateResponse = bseEntryManager.updateEmdandateStatus(selectedFund.getMobile(),
						selectedFund.getMandateType(), Double.toString(selectedFund.getInvestAmount()));
				if (emandateResponse != null) {
					if (emandateResponse.getStatusCode().equals("100")) {
						mandareGenerated = true;
						mandateId = emandateResponse.getResponseCode();
						logger.info("E-mandate registration completed successfully for Cleint ID- "
								+ selectedFund.getClientID() + " .Mandate ID generater-"
								+ emandateResponse.getResponseCode());
						redirectAttrs.addFlashAttribute("EMANDATE_STATUS", "S");
					} else {
						logger.info("Failed to generate emandate...");
						// redirectAttrs.addFlashAttribute("EMANDATE_STATUS", "F");
						map.addAttribute("errormsg",
								"Mandate registration failed for- " + emandateResponse.getRemarks());
						map.addAttribute("paymentMethod", InvestFormConstants.bsePaymentMethod);
						map.addAttribute("calendarmonths", InvestFormConstants.bseInvestMonths);
						map.addAttribute("sipyear", InvestFormConstants.bseInvestStartYear);
						return "bsemf/bse-mf-purchase";
					}
				} else {
					logger.info("emandate response is null... Returning to confirm page with error..");

					map.addAttribute("errormsg",
							"Error process ing your mandate. SIP registration aborted. Kindly try again.");
					map.addAttribute("paymentMethod", InvestFormConstants.bsePaymentMethod);
					map.addAttribute("calendarmonths", InvestFormConstants.bseInvestMonths);
					map.addAttribute("sipyear", InvestFormConstants.bseInvestStartYear);
					return "bsemf/bse-mf-purchase";
				}
				redirectAttrs.addFlashAttribute("EMANDATE_REMARKS", emandateResponse.getRemarks());
				transationResult.setEmandateStatusCode(emandateResponse.getStatusCode());
				transationResult.setEmandateRegisterRemark(emandateResponse.getRemarks());
			} else {
				logger.info("Emandate not required. Skipping the request. Get existing mandate ID for client."
						+ selectedFund.getClientID());
				mandateId = bseEntryManager.getEmdandateDetails(selectedFund.getMobile(), selectedFund.getClientID(),
						selectedFund.getMandateType(), null);
				logger.info("Exisitng mandate ID for client- " + mandateId);
				if (mandateId == null) {
					map.addAttribute("errormsg", "Unable to fetch your registered mandate details..");
					map.addAttribute("paymentMethod", InvestFormConstants.bsePaymentMethod);
					map.addAttribute("calendarmonths", InvestFormConstants.bseInvestMonths);
					map.addAttribute("sipyear", InvestFormConstants.bseInvestStartYear);
					return "bsemf/bse-mf-purchase";
				} else {
					redirectAttrs.addFlashAttribute("EMANDATE_STATUS", "AVAILABLE");
				}

			}

		} else {
			logger.info("Transaction type is lumpsum. Skip emandate registration and generating SIP date");
		}

		try {

			if (selectedFund.getInvestype().equalsIgnoreCase("SIP")) {
				logger.info("Transaction is SIP based...");
				if ((selectedFund.iseMandateRegRequired() && mandareGenerated)
						|| (!selectedFund.iseMandateRegRequired())) {
					logger.info("Processing SIP order ...");
					transationResult = bseEntryManager.savetransactionDetails(selectedFund, mandateId);
					logger.info("Customer purchase transaction status for SIP- " + transationResult.getSuccessFlag());
				} else {
					logger.info("Skippiing transation process as failed to generate EMANDATE...");
				}
			} else {
				logger.info("Transaction is LUMSUM BASED. Carry out transaction staright forward..");
				transationResult = bseEntryManager.savetransactionDetails(selectedFund, mandateId);
			}

			if (transationResult.getSuccessFlag() != null && transationResult.getSuccessFlag().equalsIgnoreCase("S")) {

				try {
					// Trigger transaction mailer

					BseMFInvestForm userDetails = bseEntryManager.getCustomerInvestFormData(
							session.getAttribute("userid") != null ? session.getAttribute("userid").toString()
									: selectedFund.getMobile());

					logger.info("Transaction processed successfully.. Processing to send mail for transaction id- "
							+ selectedFund.getTransactionID());
					mailSenderHandler.mfpurchasenotofication(selectedFund, userDetails, "purchase");
				} catch (Exception e) {
					logger.error("Failed to send mail to customer after purchase..", e);
				}

				redirectAttrs.addAttribute("TRANS_STATUS", "Y");
				redirectAttrs.addFlashAttribute("TRANS_ID", selectedFund.getTransactionID());
				redirectAttrs.addFlashAttribute("TRANS_MSG", transationResult.getStatusMsg());

				transationResult.setTransactionReference(selectedFund.getTransactionID());
				transationResult.setInvestmentType(selectedFund.getInvestype());
				transationResult.setFundName(selectedFund.getSchemeName());

				if (selectedFund.getInvestype().equalsIgnoreCase("LUMPSUM")) {
					redirectAttrs.addFlashAttribute("FIRST_PAY", "Y");
				} else if (selectedFund.getInvestype().equalsIgnoreCase("SIP")) {
					if (selectedFund.isPayFirstInstallment()) {
						redirectAttrs.addFlashAttribute("FIRST_PAY", "Y");
					} else {
						redirectAttrs.addFlashAttribute("FIRST_PAY", "N");
					}
				}

			} else if (transationResult.getSuccessFlag() != null
					&& transationResult.getSuccessFlag().equalsIgnoreCase("F")) {
				redirectAttrs.addAttribute("TRANS_STATUS", "N");
				redirectAttrs.addFlashAttribute("TRANS_MSG", transationResult.getStatusMsg());
			} else if (transationResult.getSuccessFlag() != null
					&& transationResult.getSuccessFlag().equalsIgnoreCase("D")) {
				redirectAttrs.addAttribute("TRANS_STATUS", "N");
				redirectAttrs.addFlashAttribute("TRANS_MSG",
						"Fund transaction is currently disabled by Admin. Please try after sometime.");
			} else {
				redirectAttrs.addAttribute("TRANS_STATUS", "SF");
				redirectAttrs.addFlashAttribute("TRANS_MSG", transationResult.getStatusMsg());
			}

		} catch (Exception e) {

			logger.error("Unable to save customer transaction request", e);

			// redirectAttrs.addAttribute("TRANS_STATUS", "N");

			map.addAttribute("errormsg", "Internal error! Kindly contact admin to help resolve your issue.");
			map.addAttribute("paymentMethod", InvestFormConstants.bsePaymentMethod);
			map.addAttribute("calendarmonths", InvestFormConstants.bseInvestMonths);
			map.addAttribute("sipyear", InvestFormConstants.bseInvestStartYear);
			return "bsemf/bse-mf-purchase";

		}
		redirectAttrs.addFlashAttribute("TRANS_TYPE", selectedFund.getTransactionType());
		redirectAttrs.addFlashAttribute("CLIENT_CODE", selectedFund.getClientID());
		redirectAttrs.addFlashAttribute("TRANSACTION_REPORT_BEAN", transationResult);
		return returnUrl;

	}

	@RequestMapping(value = "/my-dashboard/additional-purchase", method = RequestMethod.GET)
	public String bsemfAdditionalFundsPurchaseModeGet(@RequestParam("p") String purchasedata,
			@ModelAttribute("TRANS_ID") String transId, Model map, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {

		logger.info("bsemfAdditionalFundsPurchaseModeGet() - Additional purchase request");
		String returnUrl = "bsemf/bsemf-additional-purchase";

		List<String> decodedString = Arrays.asList(Base64Coder.decodeString(purchasedata).toString().split("\\|"));
		logger.debug("Additional purchase -" + decodedString);

		MFAdditionalPurchaseForm purchaseForm = new MFAdditionalPurchaseForm();
		MfAllInvestorValueByCategory customerFundDetails = null;
		//		MFCamsFolio folioDetails = null;

		if (session.getAttribute("userid") != null || session.getAttribute("token") != null) {
			if (purchasedata != null) {

				try {
					String portfolio = decodedString.get(0) != null ? decodedString.get(0) : "NA";
					String rtaCode = decodedString.get(1) != null ? decodedString.get(1) : "NA";
					String investType = decodedString.get(2) != null ? decodedString.get(2) : "SIP";
					String rtaAgent = decodedString.get(3) != null ? decodedString.get(3) : "BLANK";
					String productCode = decodedString.get(4) != null ? decodedString.get(4) : "BLANK";
					//					BseMFSelectedFunds selectedCodeFundDetails = null;
					// BseAllTransactionsView bseSeletedFundDetails=
					// bseEntryManager.getFundDetailsForAdditionalPurchase(portfolio,
					// schemeCode,investType, session.getAttribute("userid").toString());
					logger.info(
							"bsemfAdditionalFundsPurchaseModeGet(): Details of request for additional purchase:[portfolio:rtacode:rtaAgent:productCode:investType]"
									+ portfolio + ":" + rtaCode + ":" + rtaAgent + ":" + productCode + ":"
									+ investType);
					if (rtaAgent.equals("CAMS")) {
						customerFundDetails = bseEntryManager.getCamsFundsDetailsForRedeem(productCode,
								session.getAttribute("userid").toString(), portfolio);
						//						selectedCodeFundDetails = bseEntryManager.getFundsByCode(productCode,null);

						/*
						 * purchaseForm.setPortfolio(folioDetails.getFolioNumber());
						 * purchaseForm.setFundName(folioDetails.getFundName());
						 * purchaseForm.setTotalAvailableAmount(folioDetails.getInvAmount());
						 * purchaseForm.setUnitHolderName(folioDetails.getInvestorName());
						 */
					} else if (rtaAgent.equals("KARVY")) {
						customerFundDetails = bseEntryManager.getKarvyFundsDetailsForRedeem(productCode,
								session.getAttribute("userid").toString(), portfolio);
						/*
						 * selectedCodeFundDetails =
						 * bseEntryManager.getFundsByCode(rtaCode,customerFundDetails.getIsin());
						 * 
						 * purchaseForm.setPortfolio(customerFundDetails.getFolioNumber());
						 * purchaseForm.setFundName(customerFundDetails.getFundDescription());
						 * purchaseForm.setTotalAvailableAmount(customerFundDetails.getInvAmount());
						 * purchaseForm.setUnitHolderName(customerFundDetails.getInvestorName());
						 */
					} else {
						logger.info("Provided RTA AGENT not supported : " + rtaAgent);
					}

					if (customerFundDetails == null) {
						logger.info(
								"Failed to find related BSE scheme details with scheme code / RTA code- " + rtaCode);

					} else {
						purchaseForm.setGrowthSchemeCode(customerFundDetails.getBsemfschemeCode());
						purchaseForm.setPortfolio(customerFundDetails.getFolioNumber());
						purchaseForm.setFundName(customerFundDetails.getFundDescription());
						purchaseForm.setTotalAvailableAmount(customerFundDetails.getInvAmount());
						purchaseForm.setUnitHolderName(customerFundDetails.getInvestorName());
						//						purchaseForm.setGrowthSchemeCode(customerFundDetails.getGrowthSchemeCode());
						//						purchaseForm.setReinvSchemeCode(customerFundDetails.getReinvSchemeCode());
					}

					// purchaseForm.setSchemeCode(bseSeletedFundDetails.getSchemeCode());

					// purchaseForm.setInvestType(folioDetails.getTrasanctionType()!=null?folioDetails.getTrasanctionType():"LUMPSUM");
					purchaseForm.setInvestType("LUMPSUM");

					String transactionId = generateTransId();
					logger.info("Generated transaction ID of initiated transaction for additional purhcase-  "
							+ transactionId);
					purchaseForm.setPurchaseTransid(transactionId);

				} catch (Exception e) {
					logger.error("Failed to fetch selected funds's transaction details", e);
					map.addAttribute("error", "Failed to fetch the curret investment details");
				}
			} else {
				logger.warn("Purchase details missing. Redirecting to my dashboard");
				returnUrl = "redirect:/my-dashboard";
			}
		} else {
			logger.warn("User session not found to carry out additional purcahse. Redirecting to login.");
			returnUrl = "redirect:/login?p=" + purchasedata;
		}

		/*
		 * MFAdditionalPurchaseForm purchaseForm = new MFAdditionalPurchaseForm();
		 * purchaseForm.setPortfolio(portfolio); purchaseForm.setSchemeCode(schemeCode);
		 * purchaseForm.setInvestType(investType.toUpperCase());
		 */

		map.addAttribute("purchaseForm", purchaseForm);
		map.addAttribute("data", purchasedata);
		map.addAttribute("contextcdn", env.getProperty(CommonConstants.CDN_URL));
		return returnUrl;
	}

	@RequestMapping(value = "/mutual-funds/mfInvestAdditionalPurchase.do", method = RequestMethod.GET)
	public String bseAdditionalPurchaseGet(HttpServletRequest request, HttpServletResponse response) {
		return "redirect:/";
	}

	@RequestMapping(value = "/mutual-funds/mfInvestAdditionalPurchase.do", method = RequestMethod.POST)
	public String bseAdditionalPurchasePost(
			@ModelAttribute("purchaseForm") @Valid MFAdditionalPurchaseForm purchaseForm, BindingResult bindResult,
			Model map, HttpServletRequest request, HttpServletResponse response, HttpSession session,
			final RedirectAttributes redirectAttrs) {

		logger.info("@@ BSE MF STAR purchase confirm do controller @@");
		String returnUrl = "redirect:/mutual-funds/bse-transaction-status";
		logger.info("Purchase initiated against Folio no - " + purchaseForm.getPortfolio());
		String clientId = "";
		logger.info("Is cutout policy agreed for purchase?- " + purchaseForm.isAgreePolicy());

		if (bindResult.hasErrors()) {
			logger.error("Error processing redeem request", bindResult.getFieldError().getDefaultMessage());
			map.addAttribute("error", bindResult.getFieldError().getDefaultMessage());
			return "bsemf/bsemf-additional-purchase";
		}
		if (!purchaseForm.isAgreePolicy()) {
			logger.warn("Policy not agreed for transaction.");
			map.addAttribute("error", "Please agree to the policy for transaction.");
			return "bsemf/bsemf-additional-purchase";
		}

		// if(session.getAttribute("purchaseForm")!=null){
		if(session.getAttribute("userid") != null && session.getAttribute("userid")!=null ){
			SelectMFFund fundTransaction = new SelectMFFund();
			try {
				clientId = bseEntryManager.getClientIdfromMobile(session.getAttribute("userid").toString());
				logger.info("Client id - " + clientId + " : transaction type for additional fund- "
						+ purchaseForm.getInvestType());
				fundTransaction.setClientID(clientId);
				fundTransaction.setPortfolio(purchaseForm.getPortfolio());
				fundTransaction.setSchemeCode(purchaseForm.getGrowthSchemeCode());
				fundTransaction.setReinvSchemeCode(purchaseForm.getReinvSchemeCode());
				fundTransaction.setSchemeName(purchaseForm.getFundName());
				
				fundTransaction.setInvCategory(purchaseForm.getFundCategory());
				fundTransaction.setSchemeName(purchaseForm.getFundName());
				fundTransaction.setTransactionID(purchaseForm.getPurchaseTransid());
				fundTransaction.setInvestype(purchaseForm.getInvestType());
				fundTransaction.setTransactionType("PURCHASE");
				
				ClientSystemDetails systemDetails=  CommonTask.getClientSystemDetails(request);
				
				fundTransaction.setClientIp(systemDetails.getClientIpv4Address());
				fundTransaction.setClientBrowser(systemDetails.getClientBrowser());
				
				if (purchaseForm.getInvestType().equalsIgnoreCase("LUMPSUM")) {
					fundTransaction.setBuySellType("ADDITIONAL");
				}

				fundTransaction.setInvestAmount(purchaseForm.getPurchaseAmounts());
				fundTransaction.setPaymentMethod(purchaseForm.getPaymentMode());

				TransactionStatus flag = bseEntryManager.savetransactionDetails(fundTransaction, "");
				logger.info("Customer additional purchase transaction status- " + flag.getSuccessFlag()); // todo
				/*
				 * redirectAttrs.addAttribute("TRANS_STATUS", "Y");
				 * redirectAttrs.addAttribute("TRANS_TYPE", "ADDITIONAL");
				 * redirectAttrs.addFlashAttribute("TRANS_ID",
				 * purchaseForm.getPurchaseTransid());
				 */

				if (flag.getSuccessFlag().equalsIgnoreCase("S")) {

					logger.info("Additional purchase order ID- " + flag.getBseOrderNoFromResponse());
					redirectAttrs.addFlashAttribute("CLIENT_CODE", fundTransaction.getClientID());
					redirectAttrs.addFlashAttribute("TRANS_STATUS", "Y");
					redirectAttrs.addFlashAttribute("TRANS_TYPE", "PURCHASE");

					redirectAttrs.addFlashAttribute("FIRST_PAY", "Y");
					flag.setFundName(purchaseForm.getFundName());

					try {
						// Trigger transaction mailer

						BseMFInvestForm userDetails = bseEntryManager.getCustomerInvestFormData(
								session.getAttribute("userid") != null ? session.getAttribute("userid").toString()
										: fundTransaction.getMobile());

						logger.info("Transaction processed successfully.. Processing to send mail for transaction id- "
								+ fundTransaction.getTransactionID());
						mailSenderHandler.mfpurchasenotofication(fundTransaction, userDetails, "purchase");
					} catch (Exception e) {
						logger.error("Failed to send mail to customer after purchase..", e);
					}

					redirectAttrs.addFlashAttribute("TRANSACTION_REPORT_BEAN", flag);

				} else if (flag.getSuccessFlag().equalsIgnoreCase("SF")) {
					returnUrl = "bsemf/bsemf-additional-purchase";
					map.addAttribute("error", "Transaction successful, but failed to interanlly capture your request.");
					map.addAttribute("FUNDAVAILABLE", "Y");
				} else {
					returnUrl = "bsemf/bsemf-additional-purchase";
					map.addAttribute("error", flag.getStatusMsg());
					map.addAttribute("FUNDAVAILABLE", "Y");
				}

				redirectAttrs.addFlashAttribute("TRANS_ID", purchaseForm.getPurchaseTransid());
				redirectAttrs.addFlashAttribute("CLIENT_CODE", clientId);
			} catch (Exception e) {

				logger.error("Unable to save customer transaction request for additional purchase", e);
				// redirectAttrs.addAttribute("TRANS_STATUS", "N");
				map.addAttribute("error", "Failed to save your request for additional purchase. Please try again.");
				returnUrl = "bsemf/bsemf-additional-purchase";
			}
		}else {
			logger.info("Session not found during purchase post. Redircting back to login page...");
			returnUrl="redirect:/login";
		}
		/*
		 * }else{ logger.warn("Fund session not found for execution. Redirecting out");
		 * returnUrl ="redirect:/products/"; }
		 */


		return returnUrl;

	}

	@RequestMapping(value = "/my-dashboard/funds-redeem", method = RequestMethod.GET)
	public String bseRedeemMfFundsGet(@RequestParam("r") String purchasedata,
			@ModelAttribute("TRANS_STATUS") String transStatus, @ModelAttribute("TRANS_ID") String transId, Model map,
			HttpServletRequest request, HttpServletResponse response, HttpSession session) {

		logger.info("@@ BSE MF STAR MF redeem controller @@");
		String returnUrl = "bsemf/bsemf-redeem";

		List<String> decodedString = Arrays.asList(Base64Coder.decodeString(purchasedata).toString().split("\\|"));
		logger.info("bseRedeemMfFundsGet(): Decoded string from pathparam- " + decodedString);

		MFRedeemForm redeemForm = new MFRedeemForm();
		MfAllInvestorValueByCategory customerFundDetails = null;
		//		MFCamsFolio folioDetails = null;

		try {
			String portfolio = decodedString.get(0) != null ? decodedString.get(0) : "NA";
			String bsemfSchemeCode = decodedString.get(1) != null ? decodedString.get(1) : "NA";
			String investType = decodedString.get(2) != null ? decodedString.get(2) : "NA";

			String rtaAgent = decodedString.get(3) != null ? decodedString.get(3) : "BLANK";
			String channelPartnerCode = decodedString.get(4) != null ? decodedString.get(4) : "BLANK";
			//			BseMFSelectedFunds selectedCodeFundDetails = null;

			logger.info(
					"bsemfAdditionalFundsPurchaseModeGet(): Details of request for additional purchase:[portfolio:rtacode:rtaAgent:productCode:investType]"
							+ portfolio + ":" + bsemfSchemeCode + ":" + rtaAgent + ":" + channelPartnerCode + ":"
							+ investType);
			// BseAllTransactionsView bseSeletedFundDetails=
			// bseEntryManager.getFundDetailsForRedemption(portfolio, schemeCode,investType,
			// session.getAttribute("userid").toString());
			// MFCamsFolio folioDetails =
			// bseEntryManager.getCamsFundsDetailsForRedeem(schemeCode,
			// session.getAttribute("userid").toString(), portfolio);

			//			logger.info("Details requested for redeem- "+ portfolio + " : "+ bsemfSchemeCode + " : "+ rtaAgent + " : "+ channelPartnerCode);

			if (rtaAgent.equals("CAMS")) {
				logger.info("Redeeming CAMS category fund");
				customerFundDetails = bseEntryManager.getCamsFundsDetailsForRedeem(channelPartnerCode,
						session.getAttribute("userid").toString(), portfolio);
				//				selectedCodeFundDetails = bseEntryManager.getFundsByCode(rtaCode,null);

				/*
				 * if(folioDetails == null){
				 * logger.info("No CAMS funds found to process for redeem for portfolio: "+
				 * portfolio); map.addAttribute("FUNDAVAILABLE", "N"); map.addAttribute("error",
				 * "No fund value to redeem. Please select appropriate fund for redemption.");
				 * }else{ map.addAttribute("FUNDAVAILABLE", "Y");
				 * redeemForm.setPortfolio(folioDetails.getFolioNumber());
				 * redeemForm.setFundName(folioDetails.getFundName());
				 * redeemForm.setRedeemAmounts(folioDetails.getInvAmount());
				 * redeemForm.setUnitHolderName(folioDetails.getInvestorName());
				 * 
				 * redeemForm.setInvestType(folioDetails.getTrasanctionType()!=null?folioDetails
				 * .getTrasanctionType():"NA");
				 * redeemForm.setTotalValue(folioDetails.getInvAmount());
				 * 
				 * redeemForm.setSchemeCode(folioDetails.getSchemeCode()); }
				 */
			} else if (rtaAgent.equals("KARVY")) {
				//				karvyFund = bseEntryManager.getKarvyFundsDetailsForRedeem(rtaCode, session.getAttribute("userid").toString(), portfolio);
				logger.info("Redeeming KARVY category fund");
				customerFundDetails = bseEntryManager.getKarvyFundsDetailsForRedeem(channelPartnerCode,
						session.getAttribute("userid").toString(), portfolio);
			} else {
				logger.info("bseRedeemMfFundsGet(): Invalid RTA agent. Cannot process redemption.- " + rtaAgent);
			}

			if (customerFundDetails == null) {
				logger.info(
						"No related funds found to process for portfolio for the customer with folio: " + portfolio);
				map.addAttribute("FUNDAVAILABLE", "N");
				map.addAttribute("error", "No fund value to redeem. Please select appropriate fund for redemption.");
			} else {
				//				selectedCodeFundDetails = bseEntryManager.getFundsByCode(productCode,karvyFund.getIsin());
				map.addAttribute("FUNDAVAILABLE", "Y");
				redeemForm.setPortfolio(customerFundDetails.getFolioNumber());
				redeemForm.setFundName(customerFundDetails.getFundDescription());
				//				redeemForm.setRedeemAmounts(customerFundDetails.getInvAmount());
				redeemForm.setUnitHolderName(customerFundDetails.getInvestorName());

				redeemForm.setInvestType(
						customerFundDetails.getTrasanctionType() != null ? customerFundDetails.getTrasanctionType()
								: "NA");
				redeemForm.setTotalValue(customerFundDetails.getInvAmount());

				try {
					redeemForm.setRedeemAmounts(0.0);
					redeemForm.setMarketValue(Double.valueOf(customerFundDetails.getMarketValue()));
					redeemForm.setCurrentnav(customerFundDetails.getNav());

				} catch (Exception e) {
					logger.error("bseRedeemMfFundsGet(): error setting redeem value ", e);
				}
				redeemForm.setTotalUnits(customerFundDetails.getUnits());
				redeemForm.setSchemeCode(customerFundDetails.getBsemfschemeCode());

				String transactionId = generateTransId();
				logger.info("Generated transaction ID of initiated transaction for redeem  " + transactionId);
				redeemForm.setRedeemTransId(transactionId);

			}

			/*
			 * if(folioDetails == null){ map.addAttribute("FUNDAVAILABLE", "N");
			 * map.addAttribute("error",
			 * "No fund value to redeem. Please select appropriate fund for redemption.");
			 * }else{ map.addAttribute("FUNDAVAILABLE", "Y");
			 * 
			 * redeemForm.setPortfolio(folioDetails.getFolioNumber());
			 * redeemForm.setFundName(folioDetails.getFundName());
			 * redeemForm.setSchemeCode(folioDetails.getSchemeCode()); //
			 * redeemForm.setSchemeCode(schemeCode);
			 * redeemForm.setInvestType(folioDetails.getTrasanctionType()!=null?folioDetails
			 * .getTrasanctionType():"NA");
			 * redeemForm.setTotalValue(folioDetails.getInvAmount());
			 * redeemForm.setRedeemAmounts(folioDetails.getInvAmount());
			 * redeemForm.setUnitHolderName(folioDetails.getInvestorName());
			 * 
			 * }
			 */

		} catch (Exception e) {
			logger.error("Failed to fetch selected funds's transaction details", e);
			map.addAttribute("error", "Failed to fetch the curret investment details");
		}

		map.addAttribute("mfRedeemForm", redeemForm);
		map.addAttribute("contextcdn", env.getProperty(CommonConstants.CDN_URL));
		// return returnUrl;

		return returnUrl;
	}

	@RequestMapping(value = "/mutual-funds/mfInvestRedeem.do", method = RequestMethod.GET)
	public String bsemfFundsRedeemDoGet(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

		return "redirect:/my-dashboard";
	}

	@RequestMapping(value = "/mutual-funds/mfInvestRedeem.do", method = RequestMethod.POST)
	public String bseRedeemMfFundsPost(@ModelAttribute("mfRedeemForm") MFRedeemForm redeemForm, Model map,
			HttpServletRequest request, HttpServletResponse response, final RedirectAttributes redirectAttrs,
			HttpSession session) {

		logger.info("bseRedeemMfFundsPost(): REDEEM REQUEST POST CONTROLLER");

		if (!redeemForm.isAgreePolicy()) {
			logger.warn("Policy not agreed for redeem transaction.");
			map.addAttribute("error", "Please agree to the policy for transaction.");
			map.addAttribute("FUNDAVAILABLE", "Y");
			return "bsemf/bsemf-redeem";
		}
		if (redeemForm.getRedeemAmounts() < 500) {
			logger.warn("Minimum redemption amount should be Rs. 500");
			map.addAttribute("error", "Minimum redemption amount should be Rs. 500");
			map.addAttribute("FUNDAVAILABLE", "Y");
			return "bsemf/bsemf-redeem";
		}

		String returnUrl = "redirect:/mutual-funds/bse-transaction-status";
		TransactionStatus transReport = new TransactionStatus();

		if (session.getAttribute("token") == null) {
			try {
				returnUrl = "redirect:/login?ref="
						+ URLEncoder.encode(request.getRequestURL().toString(), StandardCharsets.UTF_8.toString());
			} catch (UnsupportedEncodingException e) {
				logger.error("Failed to form URL", e);
				returnUrl = "redirect:/login";
			}
		} else {
			// Process redeem
			SelectMFFund fundTransaction = new SelectMFFund();
			try {
				String clientId = bseEntryManager.getClientIdfromMobile(session.getAttribute("userid").toString());
				logger.info("Client id for redeem- " + clientId);
				fundTransaction.setClientID(clientId);
				fundTransaction.setPortfolio(redeemForm.getPortfolio());
				fundTransaction.setSchemeCode(redeemForm.getSchemeCode());
				fundTransaction.setSchemeName(redeemForm.getFundName());
				fundTransaction.setTransactionID(redeemForm.getRedeemTransId());
				fundTransaction.setInvestype(redeemForm.getInvestType());
				fundTransaction.setTransactionType("REDEEM");
				fundTransaction.setSchemeName(redeemForm.getFundName());
				fundTransaction.setInvestAmount(redeemForm.getRedeemAmounts() * (-1));
				fundTransaction.setRedeemAmount(redeemForm.getRedeemAmounts());
				
				ClientSystemDetails systemDetails=  CommonTask.getClientSystemDetails(request);
				
				fundTransaction.setClientIp(systemDetails.getClientIpv4Address());
				fundTransaction.setClientBrowser(systemDetails.getClientBrowser());
				
				logger.info("Redemption amount selected- "
						+ (redeemForm.isRedeemAll() ? "REDEEM ALL" : redeemForm.getRedeemAmounts() * (-1)));

				fundTransaction.setRedeemAll(redeemForm.isRedeemAll() ? "Y" : "N");

				//				Begin Redeem process
				transReport = bseEntryManager.savetransactionDetails(fundTransaction, "");

				logger.info("Customer redeem transaction status- " + transReport.getSuccessFlag());

				if (transReport.getSuccessFlag().equalsIgnoreCase("S")) {

					logger.info("Redeem order ID- " + transReport.getBseOrderNoFromResponse());
					redirectAttrs.addFlashAttribute("CLIENT_CODE", fundTransaction.getClientID());
					redirectAttrs.addFlashAttribute("TRANS_STATUS", "Y");
					redirectAttrs.addFlashAttribute("TRANS_TYPE", "REDEEM");

					redirectAttrs.addFlashAttribute("FIRST_PAY", "N");
					transReport.setFundName(redeemForm.getFundName());

					try {
						// Trigger transaction mailer

						BseMFInvestForm userDetails = bseEntryManager.getCustomerInvestFormData(
								session.getAttribute("userid") != null ? session.getAttribute("userid").toString()
										: fundTransaction.getMobile());

						logger.info("Transaction processed successfully.. Processing to send mail for transaction id- "
								+ fundTransaction.getTransactionID());
						mailSenderHandler.mfpurchasenotofication(fundTransaction, userDetails, "redemption");
					} catch (Exception e) {
						logger.error("Failed to send mail to customer after purchase..", e);
					}

					redirectAttrs.addFlashAttribute("TRANSACTION_REPORT_BEAN", transReport);

				} else {
					returnUrl = "bsemf/bsemf-redeem";
					map.addAttribute("error", "Failed to process your redeem. Please try again.");
					map.addAttribute("FUNDAVAILABLE", "Y");
				}

				redirectAttrs.addFlashAttribute("TRANS_ID", redeemForm.getRedeemTransId());

			} catch (Exception e) {

				logger.error("Unable to save customer transaction request for redeem", e);
				map.addAttribute("FUNDAVAILABLE", "Y");
				map.addAttribute("error", "Failed to process your redeem. Please try again.");
				returnUrl = "bsemf/bsemf-redeem";
			}

		}
		map.addAttribute("contextcdn", env.getProperty(CommonConstants.CDN_URL));
		logger.info("Returning to url after redeem process complete- " + returnUrl);
		return returnUrl;

	}

	@RequestMapping(value = "/my-dashboard/cancel-order", method = RequestMethod.GET)
	public String bsemfCancelOrderGet(@RequestParam("ref") String requestData, Model map, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {

		logger.info("@@ BSE MF STAR MF redeem controller @@");
		String returnUrl = "bsemf/bsemf-cancel-order";

		List<String> decodedString = Arrays.asList(Base64Coder.decodeString(requestData).toString().split("\\|"));
		logger.info(decodedString);

		MFRedeemForm orderCancelForm = new MFRedeemForm();

		try {
			String schemeCode = decodedString.get(0) != null ? decodedString.get(0) : "NA";
			String orderNo = decodedString.get(1) != null ? decodedString.get(1) : "NA";
			String investType = decodedString.get(2) != null ? decodedString.get(2) : "NA";
			String category = decodedString.get(3) != null ? decodedString.get(3) : "NA";
			String transactionNo = decodedString.get(4) != null ? decodedString.get(4) : "NA";

			BsemfTransactionHistory bseSeletedFundDetails = bseEntryManager.getOrderDetailsForCancel(orderNo,
					schemeCode, investType, session.getAttribute("userid").toString(), category, transactionNo);

			if (bseSeletedFundDetails == null) {
				map.addAttribute("FUNDAVAILABLE", "N");
				map.addAttribute("error", "No fund value to cancel. Please select appropriate fund for cancellation.");
			} else {
				map.addAttribute("FUNDAVAILABLE", "Y");
				orderCancelForm.setPortfolio(bseSeletedFundDetails.getOrderNo());
				// orderCancelForm.setFundName(bseSeletedFundDetails.);
				orderCancelForm.setSchemeCode(bseSeletedFundDetails.getSchemeCode());
				orderCancelForm.setInvestType(
						bseSeletedFundDetails.getInvestType() != null ? bseSeletedFundDetails.getInvestType() : "NA");
				orderCancelForm.setTotalValue(Double.valueOf(bseSeletedFundDetails.getInvestAmount()));
				// orderCancelForm.setRedeemAmounts(bseSeletedFundDetails.getSchemeInvestment());
				orderCancelForm.setCancelOrderTransId(bseSeletedFundDetails.getTransactionId());
				String transactionId = generateTransId();
				logger.info("Generated transaction ID of initiated transaction for cancel order-  " + transactionId);
				orderCancelForm.setRedeemTransId(transactionId);
			}

		} catch (Exception e) {
			logger.error("Failed to fetch selected funds's transaction details", e);
			map.addAttribute("error", "Failed to fetch the curret investment details");
		}

		map.addAttribute("mfCencelForm", orderCancelForm);
		map.addAttribute("contextcdn", env.getProperty(CommonConstants.CDN_URL));
		return returnUrl;
	}

	@RequestMapping(value = "/mutual-funds/cancelOrder.do", method = RequestMethod.GET)
	public String bseCancelOrderGet(HttpServletRequest request, HttpServletResponse response) {
		return "redirect:/";
	}

	@RequestMapping(value = "/mutual-funds/cancelOrder.do", method = RequestMethod.POST)
	public String bsecancelOrderPost(@Valid @ModelAttribute("mfCencelForm") MFRedeemForm cancelOrderForm,
			BindingResult bindResult, Model map, HttpServletRequest request, HttpServletResponse response,
			HttpSession session, final RedirectAttributes redirectAttrs) {

		logger.info("bsecancelOrderPost(): @@ BSE MF STAR cancel order do controller @@");
		String returnUrl = "redirect:/mutual-funds/bse-transaction-status";
		logger.info("bsecancelOrderPost(): Redeem initiated against Folio no - " + cancelOrderForm.getPortfolio());

		logger.info("bsecancelOrderPost(): Is policy agreed?- " + cancelOrderForm.isAgreePolicy());

		if (bindResult.hasErrors()) {
			logger.error("bsecancelOrderPost(): Error processing cancel request", bindResult.getFieldError());
			map.addAttribute("FUNDAVAILABLE", "Y");
			map.addAttribute("error", bindResult.getFieldError().getDefaultMessage());
			return "bsemf/bsemf-cancel-order";
		}
		if (!cancelOrderForm.isAgreePolicy()) {
			logger.warn("bsecancelOrderPost(): Policy not agreed for transaction.");
			map.addAttribute("error", "Please agree to the policy for transaction.");
			map.addAttribute("FUNDAVAILABLE", "Y");
			return "bsemf/bsemf-cancel-order";
		}

		SelectMFFund fundTransaction = new SelectMFFund();
		try {
			String clientId = bseEntryManager.getClientIdfromMobile(session.getAttribute("userid").toString());
			logger.info("bsecancelOrderPost(): Client id - " + clientId);

			logger.info("bsecancelOrderPost(): Get transaction details of selected transaciton ID: "
					+ cancelOrderForm.getCancelOrderTransId());

			if (cancelOrderForm.getInvestType().equalsIgnoreCase("SIP")) {
				logger.info("bsecancelOrderPost(): Get all transaction details of the fund. for cancel for SIP.");
				fundTransaction = bseEntryManager.getTransactionDetails(cancelOrderForm.getCancelOrderTransId(),
						clientId);

			}

			fundTransaction.setClientID(clientId);
			fundTransaction.setSchemeCode(cancelOrderForm.getSchemeCode());
			fundTransaction.setSchemeName(cancelOrderForm.getFundName());
			fundTransaction.setTransactionID(cancelOrderForm.getRedeemTransId());
			fundTransaction.setInvestype(cancelOrderForm.getInvestType());
			fundTransaction.setTransactionType("CXL");
			fundTransaction.setInvestAmount(cancelOrderForm.getTotalValue());
			fundTransaction.setOrderNo(cancelOrderForm.getPortfolio());
			ClientSystemDetails systemDetails=  CommonTask.getClientSystemDetails(request);
			
			fundTransaction.setClientIp(systemDetails.getClientIpv4Address());
			fundTransaction.setClientBrowser(systemDetails.getClientBrowser());

			TransactionStatus flag = bseEntryManager.savetransactionDetails(fundTransaction, "");

			/*
			 * if(flag.getSuccessFlag().equalsIgnoreCase("S")){
			 * redirectAttrs.addFlashAttribute("CLIENT_CODE",
			 * fundTransaction.getClientID()); }
			 */
			// logger.info("Customer redeem transaction status- "+ flag.getSuccessFlag());
			// redirectAttrs.addAttribute("TRANS_STATUS", "Y");
			redirectAttrs.addFlashAttribute("TRANS_ID", cancelOrderForm.getRedeemTransId());

			if (flag.getSuccessFlag().equalsIgnoreCase("S")) {
				logger.info("bsecancelOrderPost(): Cancel order ID- " + flag.getBseOrderNoFromResponse());
				logger.info(
						"bsecancelOrderPost(): Order cancelled successfully. Process to update the status to NO for existing transaction number: "
								+ cancelOrderForm.getCancelOrderTransId());
				boolean updateStatus = bseEntryManager.updateCancelledTransactionStatus(null, clientId,
						cancelOrderForm.getPortfolio(), cancelOrderForm.getCancelOrderTransId());
				logger.info("bsecancelOrderPost(): updateCancelledTransactionStatus- " + updateStatus);

				redirectAttrs.addFlashAttribute("CLIENT_CODE", fundTransaction.getClientID());
				redirectAttrs.addFlashAttribute("TRANS_STATUS", "Y");
				redirectAttrs.addFlashAttribute("TRANS_TYPE", "CXL");

				redirectAttrs.addFlashAttribute("FIRST_PAY", "N");
				flag.setFundName(cancelOrderForm.getFundName());

				try {
					// Trigger transaction mailer

					BseMFInvestForm userDetails = bseEntryManager.getCustomerInvestFormData(
							session.getAttribute("userid") != null ? session.getAttribute("userid").toString()
									: fundTransaction.getMobile());

					logger.info(
							"bsecancelOrderPost(): Transaction processed successfully.. Processing to send mail for transaction id- "
									+ fundTransaction.getTransactionID());
					mailSenderHandler.mfpurchasenotofication(fundTransaction, userDetails, "cancel");
				} catch (Exception e) {
					logger.error("bsecancelOrderPost(): Failed to send mail to customer after purchase..", e);
				}

				redirectAttrs.addFlashAttribute("TRANSACTION_REPORT_BEAN", flag);

			} else {
				returnUrl = "bsemf/bsemf-cancel-order";
				map.addAttribute("error", flag.getStatusMsg());
				map.addAttribute("FUNDAVAILABLE", "Y");
			}

		} catch (Exception e) {

			logger.error("Unable to save customer transaction request for cancellation", e);
			map.addAttribute("FUNDAVAILABLE", "Y");
			map.addAttribute("error", "Failed to process your cancel request. Please try again.");
			returnUrl = "bsemf/bsemf-cancel-order";
		}
		map.addAttribute("contextcdn", env.getProperty(CommonConstants.CDN_URL));
		return returnUrl;

	}

	@RequestMapping(value = "/mutual-funds/bse-transaction-complete", method = RequestMethod.GET)
	public String bseMFTransactionCallback(@RequestParam("orderid") String orderid,
			@RequestParam("client") String clientCode, Model map, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {

		logger.info("bseMFTransactionCallback(): @@ BSE MF STAR purchase confirm controller after callback @@");
		String returnUrl = "bsemf/bse-purchase-status";
		// logger.info("Data- "+ clientId + " " + orderNo);
		/*
		 * if(session.getAttribute("token")==null){
		 * logger.info("No session found for requesting user. Invalidating request");
		 * returnUrl="redirect:/login"; }else{
		 */
		if (orderid.equalsIgnoreCase("")) {
			logger.info("bseMFTransactionCallback(): Parameters data not found");
			returnUrl = "redirect:/";
		} else {
			// String getClientId =
			// bseEntryManager.getClientIdfromMobile(session.getAttribute("userid")!=null?session.getAttribute("userid").toString():"NA");
			logger.info("bseMFTransactionCallback(): Transaction complete callback received for order id: " + orderid
					+ " : client: " + clientCode);
			Base64 base64 = new Base64();
			String decodedClientCode = new String(base64.decode(clientCode.getBytes()));

			String resp = investmentConnectorBseInterface.BseOrderPaymentStatus(decodedClientCode, orderid);

			List<String> res = Arrays.asList(resp.split("\\|"));
			/*
			 * if(res.get(0).equals("100")){
			 * 
			 * }else{
			 * 
			 * }
			 */

			// Clear session
			session.removeAttribute("selectedFund");
			// session.removeAttribute("purchaseForm");

			// session.invalidate();

			map.addAttribute("TRANS_STATUS", "COMPLETE");
			map.addAttribute("ORDER_STATUS", res.get(1));
		}
		/* } */

		// map.addAttribute("TRANS_ID",transId);
		logger.info(
				"bseMFTransactionCallback(): Checking order payment status after redirected from BSE callback url for orderid- "
						+ orderid);
		map.addAttribute("contextcdn", env.getProperty(CommonConstants.CDN_URL));
		return returnUrl;
	}

	@RequestMapping(value = "/mutual-funds/bse-transaction-status", method = RequestMethod.GET)
	public String bseMFTransactionStatus(@ModelAttribute("TRANSACTION_REPORT_BEAN") TransactionStatus transReport,
			@ModelAttribute("TRANS_TYPE") String transyType, @ModelAttribute(name = "FIRST_PAY") String firstPayRequire,
			@ModelAttribute("TRANS_STATUS") String transStatus, @ModelAttribute("CLIENT_CODE") String clienCode,
			@ModelAttribute("TRANS_ID") String transId, @ModelAttribute("TRANS_MSG") String transMessage,
			@ModelAttribute("EMANDATE_STATUS") String emandateStatus,
			@ModelAttribute("EMANDATE_REMARKS") String mandateRemarks, Model map, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {

		logger.info("@@ BSE MF STAR Transaction status controller @@");
		String returnUrl = "bsemf/bse-purchase-status";

		if (clienCode.isEmpty()) {
			logger.info("bseMFTransactionStatus(): Client code is empty.. Returning back to home page");
			returnUrl = "redirect:/";
		} else {
			if (transStatus.equalsIgnoreCase("Y") || transStatus.equalsIgnoreCase("SF")) {
				logger.info("Proceeding to generate pay url for order id - " + transReport.getBseOrderNoFromResponse());
				BseOrderPaymentRequest orderUrl = new BseOrderPaymentRequest();
				orderUrl.setClientCode(clienCode);
				orderUrl.setMemberCode(CommonConstants.BSE_MEMBER_ID);
				// orderUrl.setLogOutURL("http://localhost:8080/products/mutual-funds/bse-transaction-complete");

				Base64 base64 = new Base64();
				String encodedClientCode = new String(base64.encode(clienCode.getBytes()));

				String callbackUrl = URI.create(request.getRequestURL().toString()).resolve(request.getContextPath())
						.toString()
						+ "/mutual-funds/bse-transaction-complete?orderid="
						+ (!transReport.getBseOrderNoFromResponse().isEmpty()
								? transReport.getBseOrderNoFromResponse() + "&client=" + encodedClientCode
										: "NA");
				logger.info("Callback url for payment- " + callbackUrl);
				orderUrl.setLogOutURL(callbackUrl);
				// orderUrl.setLogOutURL(Base64.encodeBase64String(callbackUrl.getBytes()));
				BseOrderPaymentResponse orderUrlReponse = investmentConnectorBseInterface.getPaymentUrl(orderUrl);
				map.addAttribute("orderUrl", orderUrlReponse);
			}
			logger.info("Emdanate status in case of SIP -" + emandateStatus);
			map.addAttribute("TRANS_STATUS", transStatus);
			map.addAttribute("TRANS_ID", transId);
			map.addAttribute("MSG", transMessage);
			map.addAttribute("EMANDATE", emandateStatus);
			map.addAttribute("MANDATE_REMARKS", mandateRemarks);
			map.addAttribute("TRANS_TYPE", transyType);
			map.addAttribute("FIRST_PAY", firstPayRequire);
			map.addAttribute("TRANSACTION_REPORT", transReport);

			map.addAttribute("contextcdn", env.getProperty(CommonConstants.CDN_URL));
		}

		logger.info("bseMFTransactionStatus(): Return url- "+ returnUrl);
		return returnUrl;
	}

	@RequestMapping(value = "/mutual-funds/pending-payments", method = RequestMethod.GET)
	public String bsePendingPayments(HttpServletRequest request, HttpServletResponse response,HttpSession session) {

		logger.info("@@ BSE MF STAR pending payments clearance @@");
		String returnUrl = "redirect:/my-dashboard";
		try {
			if (session.getAttribute("userid") != null || session.getAttribute("token") != null) {

				String clientId = bseEntryManager.getClientIdfromMobile(session.getAttribute("userid").toString());
				BseOrderPaymentRequest orderUrl = new BseOrderPaymentRequest();
				orderUrl.setClientCode(clientId);
				orderUrl.setMemberCode(CommonConstants.BSE_MEMBER_ID);
				// orderUrl.setLogOutURL("http://localhost:8080/products/mutual-funds/my-dashboard");
				String orderCallUrl = URI.create(request.getRequestURL().toString()).resolve(request.getContextPath()).toString() + "/my-dashboard";
				logger.info("bsePendingPayments(): Pending order callback url- " + orderCallUrl);
				// logger.info("Pending order callback base64- " +
				// Base64.encodeBase64String(orderCallUrl.getBytes()));
				// orderUrl.setLogOutURL(Base64.encodeBase64String(orderCallUrl.getBytes()));
				orderUrl.setLogOutURL(orderCallUrl.replace("http://", "https://"));
				BseOrderPaymentResponse orderUrlReponse = investmentConnectorBseInterface.getPaymentUrl(orderUrl);
				logger.info("bsePendingPayments(): Pending payments URL fetch request response code- " + orderUrlReponse.getStatusCode());

				if (orderUrlReponse.getStatusCode().equals(CommonConstants.BSE_API_SERVICE_DISABLED)) {
					logger.info("bsePendingPayments(): Services are currently disabled by Admin. Please try after sometime");
				} else if (orderUrlReponse.getStatusCode().equalsIgnoreCase("100")) {
					returnUrl = "redirect:" + orderUrlReponse.getPayUrl();
				} else {
					logger.info("Unable to process payment request..  Return to dashboard...");
				}

				//				map.addAttribute("orderUrl", orderUrlReponse);
			} else {
				returnUrl = "redirect:/login?ref="+ URLEncoder.encode(request.getRequestURL().toString(), StandardCharsets.UTF_8.toString());
			}

		} catch (Exception e) {
			logger.error("bsePendingPayments(): Failed to get pending url links. Returning to dashboard", e);
		}

		//		map.addAttribute("contextcdn", env.getProperty(CommonConstants.CDN_URL));
		logger.info("bsePendingPayments(): Return url- "+ returnUrl);
		return returnUrl;
	}

	@RequestMapping(value = "/mutual-funds/view-purchase-history", method = RequestMethod.GET)
	public String bseMFViewpurchaseHistory(Model map, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {

		logger.info("@@ BSE MF STAR purchase confirm controller @@");
		String returnUrl = "bsemf/bsemf-purchase-history2";

		try {
			if (session.getAttribute("userid").toString() != null || session.getAttribute("token").toString() != null) {
				String clientId = bseEntryManager.getClientIdfromMobile(session.getAttribute("userid").toString());
				if (!clientId.isEmpty()) {
					List<BsemfTransactionHistory> purchaseHistoryList = bseEntryManager.getAllPurchaseHistory(clientId);
					if (purchaseHistoryList != null) {
						// map.addAttribute("PURCHASE_LIST", "SUCCESS");
						if (purchaseHistoryList.size() >= 1) {
							map.addAttribute("PURCHASE_LIST", "SUCCESS");
							map.addAttribute("PURCHASE_ORDERS", purchaseHistoryList);
						} else {
							map.addAttribute("PURCHASE_LIST", "NONE");
						}

					} else {
						map.addAttribute("PURCHASE_LIST", "ERROR");
					}
				} else {
					logger.info("Customer client Id not found");
					map.addAttribute("PURCHASE_LIST", "ID_NOT_FOUND");
				}

			} else {
				returnUrl = "redirect:/login?ref="+URLEncoder.encode(request.getRequestURL().toString(), StandardCharsets.UTF_8.toString());
				/*
				 * map.addAttribute("TRANS_STATUS", transStatus);
				 * map.addAttribute("TRANS_ID",transId);
				 */
			}
		} catch (Exception e) {
			logger.error("bseMFViewpurchaseHistory(): Error processing request",e);
			returnUrl = "redirect:/login";
		}

		map.addAttribute("contextcdn", env.getProperty(CommonConstants.CDN_URL));
		return returnUrl;
	}

	@RequestMapping(value = "/mutual-funds/orderpaymentStatus", method = RequestMethod.GET)
	@ResponseBody
	public String getPurchasepaymentStatus(@RequestParam(name = "client", required = false) String clientId,
			@RequestParam(name = "order", required = false) String orderNo, Model map, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {

		logger.info("Order Payment status requested- ");
		String returnFlag = "FAIL";

		logger.info("Data- " + clientId + " " + orderNo);
		if (session.getAttribute("token") == null) {
			logger.info("No session found for requesting user. Invalidating request");
			returnFlag = "NO_SESSIION";
		} else {
			if (clientId == null || orderNo == null) {
				logger.info("Parameters data not found");
				returnFlag = "INVALID_REQUEST";
			} else {

				String resp = investmentConnectorBseInterface.BseOrderPaymentStatus(clientId, orderNo);

				List<String> res = Arrays.asList(resp.split("\\|"));
				/*
				 * if(res.get(0).equals("100")){
				 * 
				 * }else{
				 * 
				 * }
				 */
				returnFlag = res.get(1);
			}
		}

		map.addAttribute("contextcdn", env.getProperty(CommonConstants.CDN_URL));

		return returnFlag;
	}

	@RequestMapping(value = "/profile/fatca-declaration", method = RequestMethod.GET)
	public String bseFatcaDeclaration(@ModelAttribute("fatcaform") BseFatcaForm fatcaForm, Model map,
			HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		logger.info("@@ BSE MF STAR FATCA FORM GET controller @@");
		String returnUrl = "bsemf/bsemf-fatca";

		map.addAttribute("fatcaform", fatcaForm);
		map.addAttribute("contextcdn", env.getProperty(CommonConstants.CDN_URL));
		return returnUrl;
	}

	/*
	 * @RequestMapping(value = "/uploadfile", method = RequestMethod.GET) public
	 * String uploadfile(@ModelAttribute("fileform") BseFileUpload fileform,Model
	 * map, HttpServletRequest request, HttpServletResponse response, HttpSession
	 * session) { logger.info("@@ BSE MF STAR FILE UPLOADcontroller @@"); String
	 * returnUrl = "bsemf/file-upload";
	 * 
	 * map.addAttribute("contextcdn", env.getProperty(CommonConstants.CDN_URL));
	 * return returnUrl; }
	 */

	@RequestMapping(value = "/uploadaoffile.do", method = RequestMethod.POST)
	public String uploadfileStorePost(@ModelAttribute("fileform") BseFileUpload fileform, Model map,
			HttpServletRequest request, HttpServletResponse response, HttpSession session,
			RedirectAttributes redirectAttributes) {
		logger.info("@@ BSE MF STAR FILE POSt UPLOADcontroller @@");
		String returnUrl = "redirect:/my-dashboard";
		MultipartFile file = fileform.getFile();
		logger.info(file.getOriginalFilename() + " " + file.getContentType());
		if (file.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
			redirectAttributes.addFlashAttribute("FILE_UPLOAD", "F");
			return "redirect:/my-dashboard";

		} else if (!file.getContentType().equalsIgnoreCase("application/pdf")) {
			redirectAttributes.addFlashAttribute("FILE_UPLOAD", "F");
			redirectAttributes.addFlashAttribute("message", "Invalid file type");
			return "redirect:/my-dashboard";
		} else {
			try {

				// Get the file and save it somewhere
				String pan = bseEntryManager.getCustomerPanfromMobile(fileform.getFileowner());
				if (pan != null) {
					byte[] bytes = file.getBytes();
					Path path = Paths.get(env.getProperty(CommonConstants.BSE_AOF_GENERATION_FOLDR) + pan + ".pdf");
					Files.write(path, bytes);
					redirectAttributes.addFlashAttribute("message", "File uploaded successfully");
					redirectAttributes.addFlashAttribute("FILE_UPLOAD", "S");
				} else {
					redirectAttributes.addFlashAttribute("message", "Invalid customer.File upload denied.");
					redirectAttributes.addFlashAttribute("FILE_UPLOAD", "F");
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		map.addAttribute("contextcdn", env.getProperty(CommonConstants.CDN_URL));
		return returnUrl;
	}

	@RequestMapping("/download/aof/{fileName:.+}")
	public void downloadPDFResource(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("fileName") String fileName,
			/* @ModelAttribute("mfInvestForm") BseMFInvestForm investForm, */ /* @RequestHeader String referer, */HttpSession session) {
		logger.info("File download");

		String dataDirectory = env.getProperty(CommonConstants.BSE_AOF_GENERATION_FOLDR);
		// String dataDirectory = "E:\\AOF";
		Path file = Paths.get(dataDirectory, fileName);

		logger.info(file.toString());
		//		String flag = "SUCCESS";

		if (!Files.exists(file)) {

			BseMFInvestForm investForm = (BseMFInvestForm) session.getAttribute("mfRegisterdUser");
			if (investForm != null) {
				BseAOFGenerator.aofGenerator(investForm, fileName, env.getProperty("investment.bse.aoffile.logo"),
						"VERIFIED", dataDirectory);

			} else {
				logger.info("No session data found to generate file!");
			}
		}

		if (Files.exists(file)) {
			// response.setHeader("Content-Encoding", "UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
			// response.setContentLength((int) f.length());

			response.setContentType("application/pdf");
			// response.setContentType("application/octet-stream");
			// response.addHeader("Content-Disposition", "attachment; filename="+fileName);
			try {
				logger.info("Send response");
				Files.copy(file, response.getOutputStream());
				response.getOutputStream().close();
				response.getOutputStream().flush();

			} catch (IOException ex) {
				ex.printStackTrace();
			}
		} else {
			// logger.info("File not found");
			logger.info("No file exists or coud be generated!");

		}

	}

	@ModelAttribute("taxStatus")
	public Map<String, String> getTaxStatus() {
		Map<String, String> taxStatus = new HashMap<String, String>();
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

	/*
	 * @ModelAttribute("holingNature") public Map<String, String> mfHoldingNature()
	 * {
	 * 
	 * // To keep the order as entered below Map<String, String> holdingNature = new
	 * LinkedHashMap<String,String>(); holdingNature.put("SI", "Single");
	 * holdingNature.put("JO", "Joint"); holdingNature.put("AS",
	 * "Anyone or Survivor");
	 * 
	 * 
	 * return holdingNature; }
	 */
	/*
	 * public static void main(String[] main){ Long h =
	 * UUID.randomUUID().getMostSignificantBits(); logger.info(h);
	 * logger.info(Math.abs(h)); }
	 */

	/*
	 * public static void main(String[] args){
	 * logger.info(Encryptors.text("9051472645", "12").encrypt("asdasd"));
	 * 
	 * logger.info(DigestUtils.md5Hex("sdfsdf"));
	 * logger.info(Base64Coder.encodeString("sdfsdf"));
	 * logger.info(Base64Coder.decodeString("c2Rmc2Rm"));
	 * 
	 * 
	 * }
	 */

	/*
	 * private static byte[] hexStringToByteArray(String hex) { Pattern replace =
	 * Pattern.compile("^0x"); String s = replace.matcher(hex).replaceAll("");
	 * 
	 * byte[] b = new byte[s.length() / 2]; for (int i = 0; i < b.length; i++) { int
	 * index = i * 2; int v = Integer.parseInt(s.substring(index, index + 2), 16);
	 * b[i] = (byte) v; } return b; }
	 */

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ModelAndView handleMissingParams(MissingServletRequestParameterException ex) {
		String name = ex.getParameterName();
		logger.info(name + " parameter is missing on requested url. Returning to home url");
		// Actual exception handling
		ModelAndView view = new ModelAndView("redirect:/");
		return view;
	}

	@ExceptionHandler(HttpSessionRequiredException.class)
	public ModelAndView handleMissingSessionHandler(HttpSessionRequiredException ex, HttpServletRequest request) {
		String name = ex.getExpectedAttribute();

		logger.error("Exception was reveived for missing parameters.", ex);
		String returnUrl = "redirect:/";
		logger.info(name + " missing");
		// Actual exception handling
		if (name.equals("selectedFund")) {
			returnUrl = "redirect:/mutual-funds/funds-explorer";
		}
		ModelAndView view = new ModelAndView(returnUrl);
		return view;
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ModelAndView illegalArgumentException(IllegalArgumentException ex, HttpServletRequest request) {

		logger.error("illegalArgumentException was received for missing parameters.- " + request.getRequestURI(), ex);
		String returnUrl = "redirect:/";
		// Actual exception handling
		returnUrl = "redirect:/mutual-funds/funds-explorer";
		ModelAndView view = new ModelAndView(returnUrl);
		return view;
	}

	/*
	 * @ExceptionHandler(ResourceNotFoundException.class)
	 * 
	 * @ResponseStatus(value=HttpStatus.NOT_FOUND) public String
	 * pageNotFound(HttpServletRequest request, Exception ex){
	 * logger.info("Controlleradvice- page not found"+ request.getRequestURI());
	 * return "pagenotfound"; }
	 */

}
