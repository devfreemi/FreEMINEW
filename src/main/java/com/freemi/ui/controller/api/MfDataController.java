package com.freemi.ui.controller.api;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.freemi.common.util.CommonConstants;
import com.freemi.entity.bse.BseOrderPaymentRequest;
import com.freemi.entity.bse.BseOrderPaymentResponse;
import com.freemi.entity.investment.MfNavData;
import com.freemi.services.interfaces.BseEntryManager;
import com.google.gson.Gson;

@RestController
@RequestMapping("/api")
public class MfDataController {

	private static final Logger logger = LogManager.getLogger(FormDataController.class);

	@Autowired
	BseEntryManager bseEntryManager;

	@PostMapping(value="/navdata/{isin}",produces = "application/json")
	@CrossOrigin(origins="https://www.freemi.in")
	@ResponseBody
	public String  getNavDataForIsisn(@PathVariable(name="isin") String isin,Model model, HttpServletRequest request, HttpServletResponse httpResponse){
		logger.info("Request received to fetch NAV data via API for ISIN - "+ isin);
		//		String response ="SUCCESS";
		List<MfNavData> navhistorydata = null;
		//		List<String> data = null;
		String json = null;
		try{
			if(!isin.isEmpty() || !isin.equalsIgnoreCase("null")){

				//				JSONObject obj = new JSONObject(navData);
				//		        String pageName = obj.getJSONObject("pageInfo").getString("pageName");
				//		        isin = obj.getString("isin");
				//		        System.out.println(pageName);

				navhistorydata=  bseEntryManager.getnavdataByISIN(isin);
				//				System.out.println(navhistorydata);
				logger.info("Returning total nav data- : "+ navhistorydata.size());
				json = new Gson().toJson(navhistorydata);

			}else{
				logger.info("No isin in api call- " + isin);
			}

		}catch(Exception e){
			logger.error("Error reading ISIN nav data..",e);
		}
		httpResponse.setContentType("application/json");
		return json;
		//		return navhistorydata;
	}


	@RequestMapping(value = "/mutual-funds/pending-payments", method = RequestMethod.POST)
	public String bsePendingPayments(HttpServletRequest request, HttpServletResponse response,HttpSession session) {

		logger.info("@@ BSE MF STAR pending payments clearance @@");
		String responseData = "SUCCESS";
		try {
			if (session.getAttribute("userid") != null || session.getAttribute("token") != null) {
				String orderCallUrl = URI.create(request.getRequestURL().toString()).resolve(request.getContextPath()).toString() + "/my-dashboard";
				BseOrderPaymentResponse orderUrlReponse = bseEntryManager.getpendingPaymentLinks(session.getAttribute("userid").toString(), orderCallUrl);

				if (orderUrlReponse.getStatusCode().equals(CommonConstants.BSE_API_SERVICE_DISABLED)) {
					responseData = "SERVICE_DISABLED";
					logger.info("bsePendingPayments(): Services are currently disabled by Admin. Please try after sometime");
				} else if (orderUrlReponse.getStatusCode().equalsIgnoreCase("100")) {
					responseData = orderUrlReponse.getPayUrl();
				} else {
					logger.info("Unable to process payment request..  Return to dashboard... Response: "+ orderUrlReponse.getStatusCode() + " : "+ orderUrlReponse.getPayUrl());
					responseData = "NO_REGISTERED";
				}

				//				map.addAttribute("orderUrl", orderUrlReponse);
			} else {
				//				returnUrl = "redirect:/login?ref="+ URLEncoder.encode(request.getRequestURL().toString(), StandardCharsets.UTF_8.toString());
				responseData ="NO_SESSION";
			}

		} catch (Exception e) {
			logger.error("bsePendingPayments(): Failed to get pending url links. Returning to dashboard", e);
			responseData ="INTERNAL_ERROR";
		}

		//		map.addAttribute("contextcdn", env.getProperty(CommonConstants.CDN_URL));
		logger.info("bsePendingPayments(): Return url- "+ responseData);
		return responseData;
	}




}
