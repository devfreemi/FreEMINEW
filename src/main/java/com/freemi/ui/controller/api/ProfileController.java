package com.freemi.ui.controller.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.freemi.common.util.CommonTask;
import com.freemi.entity.general.HttpClientResponse;
import com.freemi.entity.general.Login;
import com.freemi.entity.general.LoginResponse;
import com.freemi.entity.general.MfCollatedFundsView;
import com.freemi.entity.general.Registerform;
import com.freemi.entity.general.UserProfile;
import com.freemi.entity.investment.MFKarvyFundsView;
import com.freemi.entity.investment.MfAllInvestorValueByCategory;
import com.freemi.services.interfaces.BseEntryManager;
import com.freemi.services.interfaces.ProfileRestClientService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

@RestController
@CrossOrigin(origins= {"https://www.freemi.in","http://localhost:8080"})
@RequestMapping("/api")
public class ProfileController {

	@Autowired
	ProfileRestClientService profileRestClientService; 

//	@Autowired
//	private Environment environment;

	@Autowired
	BseEntryManager bseEntryManager;

	private static final Logger logger = LogManager.getLogger(ProfileController.class);

	@PostMapping(value="/login")
	public Object apiLogin(HttpServletRequest request, HttpServletResponse httpResponse){
		logger.info("Request received to process login via API.");

		Login loginForm = null;
		LoginResponse loginResponse = new LoginResponse();

		try {
			loginForm = new ObjectMapper().readValue(request.getInputStream(), Login.class);

			logger.info("Login request for user id via api- "+ loginForm.getUsermobile());


			//			RestClient client = new RestClient();
			ResponseEntity<String> response = null;

			try{
				response= profileRestClientService.login(loginForm.getUsermobile(), loginForm.getUserpassword(), loginForm.getSystemip());

				//				logger.debug(response.getStatusCodeValue());
				if(response.getStatusCodeValue() == 200){
					loginResponse.setEmail(response.getHeaders().get("email").get(0));
					loginResponse.setFname(response.getHeaders().get("fname").get(0));
					loginResponse.setUserid(response.getHeaders().get("userid").get(0));
					httpResponse.setHeader("Authorization", response.getHeaders().get("Authorization").get(0));
					loginResponse.setStatus(Integer.toString(response.getStatusCodeValue()));
				}
				//				logger.info(response.getHeaders().get("Authorization").get(0));
			}
			catch(HttpStatusCodeException  e){
				logger.error("HttpStatusCodeException");
				loginResponse.setStatus(Integer.toString(e.getRawStatusCode()));

			}catch(Exception e){
				logger.error("Error- "+ e.getMessage());
				loginResponse.setStatus(e.getMessage());
			}


		} catch (IOException e) {
			logger.error("Error in api- "+ e.getMessage());
			loginResponse.setStatus("INVALID");
		}

		return loginResponse;
	}


	@PostMapping(value="/registeruser")
	public Object apiregisteruser(HttpServletRequest request, HttpServletResponse response){
		logger.info("Request received to process login via API.");

		//		Login loginForm = null;
		Registerform registerForm = new Registerform();

		String STATUS="";

		try {
			registerForm = new ObjectMapper().readValue(request.getInputStream(), Registerform.class);

			logger.info("Login request for user id via api- "+ registerForm.getMobile());


			//			RestClient client = new RestClient();
			HttpClientResponse httpResponse =  profileRestClientService.registerUser(registerForm,CommonTask.getClientSystemDetails(request));
			String status = httpResponse.getResponseEntity().getHeaders().get("STATUS").get(0);
			if(status.equals("SUCCESS")){
				//					model.addAttribute("success", "Registration successful. Login to your account");
				STATUS="SUCCESS";
			}else if(status.equals("DUPLICATE ENTRY")){
				//					model.addAttribute("error", "Account already exist.");
				STATUS="DUPLICATE";
			}else if(status.equals("ERROR")){
				//					model.addAttribute("error", "Registration failed. Please try again after sometime");

				STATUS="ERROR";
			}else{
				logger.info("Reponse for register via api- "+httpResponse.getResponseEntity().getHeaders().get("STATUS").get(0));
				//					model.addAttribute("error", "Unknown response");
				STATUS="ERROR";
			}

		}catch(HttpStatusCodeException  e){
			logger.error("Register failure via api for user "+registerForm.getMobile() + " : HTTP CODE : "+e.getStatusCode() );
			//				model.addAttribute("error", "Unable to process request curretnly");
		} catch (JsonProcessingException e) {
			//				model.addAttribute("error","Invalid form data");
			STATUS ="INVALID DATA";
		}catch(Exception e){
			//				model.addAttribute("error","Error processing request");
			STATUS="ERROR PROCESSING REQUEST";
		}

		return STATUS;

	}

	@PostMapping(value="/getprofile")
	public Object apigetProfile(HttpServletRequest request, HttpServletResponse httpResponse){

		logger.error("Request received to fectch profile data via api");
		//		RestClient client = new RestClient();
		ResponseEntity<String> response = null;

		UserProfile profile=null;
		String mobile=request.getHeader("mobile");
		String token=request.getHeader("Authorization");
		String ip=request.getHeader("requestingIp");

		if(mobile!=null && token!=null && ip!=null){
			logger.error("Processing request for profile data fetch for user- " + mobile);

			try {
				response = profileRestClientService.getProfileData(mobile, token,ip);
				profile = new ObjectMapper().readValue(response.getBody(), UserProfile.class);

			}catch(HttpStatusCodeException  e){
				logger.error("Error fetching profile data via api request" + e.getStatusCode());
				httpResponse.setStatus(e.getRawStatusCode());
			} catch (JsonProcessingException e) {
				logger.error("Error processing request data via api request" +e.getMessage());
			}catch(Exception e){
				logger.error("Unable to process request to fetch data request via api \n" + e.getMessage());
			}
		}else{
			logger.info("Missing required parameters");
			httpResponse.setStatus(401);
		}

		return profile;

	}


	@PostMapping(value="/mf/getmfportfoliototal")
	@ResponseBody
	public String apiMFBalance(HttpServletRequest request, HttpServletResponse httpResponse,HttpSession session){
		
		
		logger.info("Request received to fectch profile data via api for mobile-" + request.getParameter("mobile"));
		String result=null;
		JsonObject jsonObject = null;
		
		try {

			logger.info("apiMFBalance(): Requesting MF balance from database for account-" + request.getParameter("mobile"));

			String mobile=request.getParameter("mobile");


			if(session.getAttribute("token") == null || session.getAttribute("userid") == null){
				logger.info("User session not found to process request..");
				return "NO_SESSION";
			}else{

				if(mobile.equals(session.getAttribute("userid").toString())) {

					try{
						result = bseEntryManager.getCustomerInvestmentValue(null,request.getParameter("mobile"));
						logger.info("Result received- "+ result);
						
						if(result!=null) {
							if((result.split(",")[0]).equalsIgnoreCase(request.getParameter("pan"))) {
								logger.info("Send mf data result in JSON format");
								jsonObject = new JsonObject();
								jsonObject.addProperty("invvalue", result.split(",")[1]);
								jsonObject.addProperty("marketvalue", result.split(",")[2]);
								result=jsonObject.toString();
							}else if((result.split(",")[0]).equalsIgnoreCase("null")) {
								result="NO_DATA";
							}else {
								result="PAN_INVALID";
							}
						}else {
							logger.info("Result is null...");
							result="NO_DATA";
						}
					
					}catch(Exception e) {
						logger.error("apiMFBalance(): Error requesting MF balance",e);
						result= "INTERNAL_ERROR";
					}
				}else {
					logger.info("Passed mobile and sessio mobile data do not match");
					result= "REQUEST_DENIED";
				}
			}
		}catch(Exception e) {
			logger.error("apiMFBalance(): Error processing request",e);
			result= "INTERNAL_ERROR";
		}
		
		logger.info("apiMFBalance(): Retrunung result- "+ result);
		return result;
	}


	@PostMapping(value="/mf/getmfprofileData")
	@ResponseBody
	public String apiMFgetProfileData(/*@PathVariable("mobile") String mobile,*/HttpServletRequest request, HttpServletResponse httpResponse,HttpSession session){

		String json=null;
		try {

			logger.error("Request received to fectch profile data via api for mobile-" + request.getParameter("mobile"));
			double totalAsset= 0.0;
			double totalmarketVal= 0.0;
			

			String mobile=request.getParameter("mobile");

			List<MfCollatedFundsView> listFunds = new ArrayList<MfCollatedFundsView>();

			if(session.getAttribute("token") == null || session.getAttribute("userid") == null){
				logger.info("User session not found to process request..");
				return "NO_SESSION";
			}else{

				//			mobile=session.getAttribute("userid").toString();
				if(mobile.equals(session.getAttribute("userid").toString())) {

					try{
						List<MfAllInvestorValueByCategory> allMFFunds= null;
						List<MfAllInvestorValueByCategory> categorykarvyFunds= null;
						List<MFKarvyFundsView> karvyview = new ArrayList<MFKarvyFundsView>();
						logger.info("Search for ALL related folios for customer.");
						List<String> uniquefundShort = new ArrayList<String>();
						allMFFunds = bseEntryManager.getCustomersAllFoliosByCategory(mobile, null);

						if(allMFFunds !=null){
							logger.info("Total Folio(s) found of customer - " +mobile + " : " + allMFFunds.size());
							
							if(allMFFunds.size() >0) {
							
							for(int j=0;j<allMFFunds.size();j++){

								uniquefundShort.add(allMFFunds.get(j).getAmcShort());

								totalAsset+=allMFFunds.get(j).getInvAmount();

								try {
									totalmarketVal+=Double.valueOf(allMFFunds.get(j).getMarketValue());
								}catch(Exception e) {
									logger.error("Error adding market value -  "+ allMFFunds.get(j).getMarketValue());
								}
							}

							logger.info("Total asset after ALL MF calculation- "+ totalAsset);


							Set<String> uniqueAmcs = new HashSet<>(uniquefundShort);
							uniquefundShort = new ArrayList<String>(uniqueAmcs);
							logger.info("Distinct AMCs invested in of customer - "+ uniquefundShort);

							for(int x=0;x<uniquefundShort.size();x++){
								MFKarvyFundsView selectedAMC = new MFKarvyFundsView();
								MfCollatedFundsView currentFund = new MfCollatedFundsView();
								categorykarvyFunds= new ArrayList<MfAllInvestorValueByCategory>();
								Double totalAMCInvestedVal=0.0;
								Double amcMarketValue=0.0;
								for(int y=0;y<allMFFunds.size();y++){
									logger.debug(uniquefundShort.get(x) + " -> "+ allMFFunds.get(y).getAmcShort());
									if(uniquefundShort.get(x).equals(allMFFunds.get(y).getAmcShort())){

										if(currentFund.getAmcicon()==null){
											currentFund.setAmcicon(allMFFunds.get(y).getAmcicon());
										}
										if(currentFund.getAmcShort() ==null){
											currentFund.setAmcShort(allMFFunds.get(y).getAmcShort());
										}if(currentFund.getFolioNumber()==null){
											currentFund.setFolioNumber(allMFFunds.get(y).getFolioNumber());
										}
										if(currentFund.getRtaAgent()==null){
											currentFund.setRtaAgent(allMFFunds.get(y).getRtaAgent());
										}
										if(currentFund.getFundName()==null){
											currentFund.setFundName(allMFFunds.get(y).getAmcName());
											logger.debug("Set common fund name- "+ allMFFunds.get(y).getAmcName());
										}

										//								Calculate AMC total invested value and its current market value
										totalAMCInvestedVal+=allMFFunds.get(y).getInvAmount();
										try {
											amcMarketValue+=!allMFFunds.get(y).getMarketValue().equals("NA")?Double.valueOf(allMFFunds.get(y).getMarketValue()):0.0;
										}catch(Exception e) {
											logger.error("unable to calculate market value. Adding 0 to add",e);
											amcMarketValue+=0;
										}

										if(selectedAMC.getAmcicon()==null){
											selectedAMC.setAmcicon(allMFFunds.get(y).getAmcicon());
										}
										if(selectedAMC.getFunName()==null){
											selectedAMC.setFunName(allMFFunds.get(y).getFundDescription());;
										}


										categorykarvyFunds.add(allMFFunds.get(y));


									}
									selectedAMC.setTotalInvestment(totalAMCInvestedVal);
									selectedAMC.setTotalMarketValue(amcMarketValue);
									selectedAMC.setCategorizedFund(categorykarvyFunds);

									currentFund.setCollaboratedAmount(totalAMCInvestedVal);
									currentFund.setCollaboratedMarketValue(amcMarketValue);
									currentFund.setKarvyFolioList(categorykarvyFunds);

								}

								karvyview.add(selectedAMC);
								listFunds.add(currentFund);

							}
							
							

							logger.info("Total funds by category- " + karvyview.size());

							//					map.addAttribute("allfundsdata", allMFFunds);
							json = new Gson().toJson(listFunds);
							}else{
								logger.info("No investment data found..");
								json="NO_DATA";
							}
						}
						
					}catch(Exception e){
						logger.error("Error handling Karvy folio query from controller",e);
					}

				}else {
					logger.info("User session mobile number do not match with passed mobile number!");
					return "REQUEST_DENIED";
				}


			}
		}catch(Exception e) {
			logger.error("Internal error ",e);
			
			json="INTERNAL_ERROR";
		}
//		httpResponse.setContentType("application/json");

		return json;

		//		return "[{\"fundName\":\"TATA MUTUAL FUND\",\"folioNumber\":\"4864792/50\",\"collaboratedAmount\":20000.0,\"collaboratedMarketValue\":19848.348,\"amcicon\":\"tata.png\",\"amcShort\":\"TATAMutualFund_MF\",\"rtaAgent\":\"CAMS\",\"karvyFolioList\":[{\"serial\":76,\"rtaAgent\":\"CAMS\",\"pan\":\"CQOPS7539C\",\"folioNumber\":\"4864792/50\",\"investorName\":\"Debasish Sarkar\",\"channelProductCode\":\"TEPEG\",\"invAmount\":20000.0,\"units\":151.92,\"isin\":\"INF277K01451\",\"fundDescription\":\"TATA EQUITY PE FUND - GROWTH\",\"bsemfschemeCode\":\"EPEG\",\"amcName\":\"TATA MUTUAL FUND\",\"amcShort\":\"TATAMutualFund_MF\",\"amcicon\":\"tata.png\",\"nav\":\"130.65\",\"navdate\":\"20/09/2019\",\"marketValue\":\"19848.3480\"}]},{\"fundName\":\"RELIANCE MUTUAL FUND\",\"folioNumber\":\"499176723743\",\"collaboratedAmount\":62771.5,\"collaboratedMarketValue\":12168.5551,\"amcicon\":\"Reliance-mf.png\",\"amcShort\":\"RelianceMutualFund_MF\",\"rtaAgent\":\"KARVY\",\"karvyFolioList\":[{\"serial\":28,\"rtaAgent\":\"KARVY\",\"pan\":\"CQOPS7539C\",\"folioNumber\":\"499176723743\",\"investorName\":\"DEBASISH  SARKAR\",\"channelProductCode\":\"RMFLFIG\",\"invAmount\":12000.0,\"units\":2.597,\"isin\":\"INF204K01UN9\",\"fundDescription\":\"RELIANCE LIQUID FUND - GROWTH PLAN - GROWTH OPTION\",\"bsemfschemeCode\":\"RELLFTPI-GR\",\"amcName\":\"RELIANCE MUTUAL FUND\",\"amcShort\":\"RelianceMutualFund_MF\",\"amcicon\":\"Reliance-mf.png\",\"nav\":\"4685.62\",\"navdate\":\"20/09/2019\",\"marketValue\":\"12168.5551\"},{\"serial\":27,\"rtaAgent\":\"KARVY\",\"pan\":\"CQOPS7539C\",\"folioNumber\":\"499176723743\",\"investorName\":\"DEBASISH  SARKAR\",\"channelProductCode\":\"RMFGFGP\",\"invAmount\":50771.5,\"units\":45.623,\"isin\":\"INF204K01323\",\"fundDescription\":\"RELIANCE GROWTH FUND - GROWTH PLAN - GROWTH OPTION\",\"bsemfschemeCode\":\"GFGP\",\"amcName\":\"RELIANCE MUTUAL FUND\",\"amcShort\":\"RelianceMutualFund_MF\",\"amcicon\":\"Reliance-mf.png\",\"nav\":\"NA\",\"navdate\":\"NA\",\"marketValue\":\"NA\"}]},{\"fundName\":\"Aditya Birla Sunlife Mutual Fund\",\"folioNumber\":\"1038581433\",\"collaboratedAmount\":187000.0,\"collaboratedMarketValue\":187497.8307,\"amcicon\":\"aditya-birla.jpg\",\"amcShort\":\"BirlaSunLifeMutualFund_MF\",\"rtaAgent\":\"CAMS\",\"karvyFolioList\":[{\"serial\":55,\"rtaAgent\":\"CAMS\",\"pan\":\"CQOPS7539C\",\"folioNumber\":\"1038581433\",\"investorName\":\"Debasish Sarkar\",\"channelProductCode\":\"B02G\",\"invAmount\":120000.0,\"units\":3966.94,\"isin\":\"INF209K01108\",\"fundDescription\":\"ADITYA BIRLA SUN LIFE TAX RELIEF 96-GROWTH\",\"bsemfschemeCode\":\"02G\",\"amcName\":\"Aditya Birla Sunlife Mutual Fund\",\"amcShort\":\"BirlaSunLifeMutualFund_MF\",\"amcicon\":\"aditya-birla.jpg\",\"nav\":\"30.06\",\"navdate\":\"20/09/2019\",\"marketValue\":\"119246.2164\"},{\"serial\":56,\"rtaAgent\":\"CAMS\",\"pan\":\"CQOPS7539C\",\"folioNumber\":\"1038871259\",\"investorName\":\"Debasish Sarkar\",\"channelProductCode\":\"B313G\",\"invAmount\":2000.0,\"units\":51.12,\"isin\":\"INF209K01751\",\"fundDescription\":\"ADITYA BIRLA SUN LIFE REGULAR SAVINGS FUND-GROWTH\",\"bsemfschemeCode\":\"B313G\",\"amcName\":\"Aditya Birla Sunlife Mutual Fund\",\"amcShort\":\"BirlaSunLifeMutualFund_MF\",\"amcicon\":\"aditya-birla.jpg\",\"nav\":\"39.48\",\"navdate\":\"20/09/2019\",\"marketValue\":\"2018.2176\"},{\"serial\":54,\"rtaAgent\":\"CAMS\",\"pan\":\"CQOPS7539C\",\"folioNumber\":\"1037558756\",\"investorName\":\"DEBASISH  SARKAR\",\"channelProductCode\":\"B1180B\",\"invAmount\":65000.0,\"units\":2341.23,\"isin\":\"INF209K011W7\",\"fundDescription\":\"ADITYA BIRLA SUN LIFE BANKING AND FINANCIAL SERVICES FUND - REGULAR PLAN - GROWTH\",\"bsemfschemeCode\":\"1180B-GR\",\"amcName\":\"Aditya Birla Sunlife Mutual Fund\",\"amcShort\":\"BirlaSunLifeMutualFund_MF\",\"amcicon\":\"aditya-birla.jpg\",\"nav\":\"28.29\",\"navdate\":\"20/09/2019\",\"marketValue\":\"66233.3967\"}]},{\"fundName\":\"HDFC MUTUAL FUND\",\"folioNumber\":\"15230618/93\",\"collaboratedAmount\":10000.0,\"collaboratedMarketValue\":10495.5564,\"amcicon\":\"hdfc.png\",\"amcShort\":\"HDFCMutualFund_MF\",\"rtaAgent\":\"CAMS\",\"karvyFolioList\":[{\"serial\":58,\"rtaAgent\":\"CAMS\",\"pan\":\"CQOPS7539C\",\"folioNumber\":\"15230618/93\",\"investorName\":\"Debasish Sarkar\",\"channelProductCode\":\"HLFGN\",\"invAmount\":5000.0,\"units\":1.39,\"isin\":\"INF179KB1HK0\",\"fundDescription\":\"HDFC LIQUID FUND - GROWTH\",\"bsemfschemeCode\":\"HDLFGN-GR\",\"amcName\":\"HDFC MUTUAL FUND\",\"amcShort\":\"HDFCMutualFund_MF\",\"amcicon\":\"hdfc.png\",\"nav\":\"3775.38\",\"navdate\":\"20/09/2019\",\"marketValue\":\"5247.7782\"},{\"serial\":57,\"rtaAgent\":\"CAMS\",\"pan\":\"CQOPS7539C\",\"folioNumber\":\"15230617/96\",\"investorName\":\"Debasish Sarkar\",\"channelProductCode\":\"HLFGN\",\"invAmount\":5000.0,\"units\":1.39,\"isin\":\"INF179KB1HK0\",\"fundDescription\":\"HDFC LIQUID FUND - GROWTH\",\"bsemfschemeCode\":\"HDLFGN-GR\",\"amcName\":\"HDFC MUTUAL FUND\",\"amcShort\":\"HDFCMutualFund_MF\",\"amcicon\":\"hdfc.png\",\"nav\":\"3775.38\",\"navdate\":\"20/09/2019\",\"marketValue\":\"5247.7782\"}]},{\"fundName\":\"SBI MUTUAL FUND\",\"folioNumber\":\"17173983\",\"collaboratedAmount\":62893.22,\"collaboratedMarketValue\":72427.5754,\"amcicon\":\"sbi-mutual-funds.png\",\"amcShort\":\"SBIMutualFund_MF\",\"rtaAgent\":\"CAMS\",\"karvyFolioList\":[{\"serial\":59,\"rtaAgent\":\"CAMS\",\"pan\":\"CQOPS7539C\",\"folioNumber\":\"17173983\",\"investorName\":\"Debasish Sarkar\",\"channelProductCode\":\"L086G\",\"invAmount\":0.0,\"units\":0.0,\"isin\":\"INF200K01LJ4\",\"fundDescription\":\"SBI MAGNUM ULTRA SHORT DURATION FUND REGULAR GROWTH\",\"bsemfschemeCode\":\"SBI086G-GR\",\"amcName\":\"SBI MUTUAL FUND\",\"amcShort\":\"SBIMutualFund_MF\",\"amcicon\":\"sbi-mutual-funds.png\",\"nav\":\"4300.25\",\"navdate\":\"20/09/2019\",\"marketValue\":\"0.0000\"},{\"serial\":73,\"rtaAgent\":\"CAMS\",\"pan\":\"CQOPS7539C\",\"folioNumber\":\"17173983\",\"investorName\":\"Debasish Sarkar\",\"channelProductCode\":\"L464G\",\"invAmount\":62893.22,\"units\":4028.23,\"isin\":\"INF200KA1473\",\"fundDescription\":\"SBI BANKING \\u0026 FINANCIAL SERVICES FUND - REGULAR PLAN - GROWTH\",\"bsemfschemeCode\":\"SB464G-GR\",\"amcName\":\"SBI MUTUAL FUND\",\"amcShort\":\"SBIMutualFund_MF\",\"amcicon\":\"sbi-mutual-funds.png\",\"nav\":\"17.98\",\"navdate\":\"20/09/2019\",\"marketValue\":\"72427.5754\"}]},{\"fundName\":\"ICICI Prudential Asset Management Company Ltd\",\"folioNumber\":\"7427385/06\",\"collaboratedAmount\":67133.77,\"collaboratedMarketValue\":72696.5601,\"amcicon\":\"icici-prudential.jpg\",\"amcShort\":\"ICICIPrudentialMutualFund_MF\",\"rtaAgent\":\"CAMS\",\"karvyFolioList\":[{\"serial\":60,\"rtaAgent\":\"CAMS\",\"pan\":\"CQOPS7539C\",\"folioNumber\":\"7427385/06\",\"investorName\":\"Debasish Sarkar\",\"channelProductCode\":\"P1565\",\"invAmount\":0.0,\"units\":0.0,\"isin\":\"INF109K01VQ1\",\"fundDescription\":\"ICICI PRUDENTIAL LIQUID FUND - GROWTH\",\"bsemfschemeCode\":\"ICICI1565-GR\",\"amcName\":\"ICICI Prudential Asset Management Company Ltd\",\"amcShort\":\"ICICIPrudentialMutualFund_MF\",\"amcicon\":\"icici-prudential.jpg\",\"nav\":\"284.14\",\"navdate\":\"20/09/2019\",\"marketValue\":\"0.0000\"},{\"serial\":74,\"rtaAgent\":\"CAMS\",\"pan\":\"CQOPS7539C\",\"folioNumber\":\"7427385/06\",\"investorName\":\"Debasish Sarkar\",\"channelProductCode\":\"PEDWRG\",\"invAmount\":67133.77,\"units\":2015.43,\"isin\":\"INF109K01BH2\",\"fundDescription\":\"ICICI PRUDENTIAL BALANCED ADVANTAGE FUND - GROWTH\",\"bsemfschemeCode\":\"EDWRG\",\"amcName\":\"ICICI Prudential Asset Management Company Ltd\",\"amcShort\":\"ICICIPrudentialMutualFund_MF\",\"amcicon\":\"icici-prudential.jpg\",\"nav\":\"36.07\",\"navdate\":\"20/09/2019\",\"marketValue\":\"72696.5601\"}]},{\"fundName\":\"MIRAE ASSET MANAGEMENT\",\"folioNumber\":\"77716778431\",\"collaboratedAmount\":20000.0,\"collaboratedMarketValue\":20105.2073,\"amcicon\":\"mirae.jpg\",\"amcShort\":\"MIRAEASSET\",\"rtaAgent\":\"KARVY\",\"karvyFolioList\":[{\"serial\":29,\"rtaAgent\":\"KARVY\",\"pan\":\"CQOPS7539C\",\"folioNumber\":\"77716778431\",\"investorName\":\"DEBASISH SARKAR\",\"channelProductCode\":\"117EBRG\",\"invAmount\":20000.0,\"units\":378.986,\"isin\":\"INF769K01101\",\"fundDescription\":\"MIRAE ASSET EMERGING BLUECHIP FUND - REGULAR PLAN - GROWTH OPTION\",\"bsemfschemeCode\":\"MAFEBRG-GR\",\"amcName\":\"MIRAE ASSET MANAGEMENT\",\"amcShort\":\"MIRAEASSET\",\"amcicon\":\"mirae.jpg\",\"nav\":\"53.05\",\"navdate\":\"20/09/2019\",\"marketValue\":\"20105.2073\"}]}]";

	}





}
