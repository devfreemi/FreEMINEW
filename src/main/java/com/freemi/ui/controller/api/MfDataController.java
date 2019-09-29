package com.freemi.ui.controller.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.freemi.database.service.BseEntryManager;
import com.freemi.entity.investment.MfNavData;
import com.google.gson.Gson;

@RestController
public class MfDataController {

	private static final Logger logger = LogManager.getLogger(FormDataController.class);
	
	@Autowired
	BseEntryManager bseEntryManager;
	
	@PostMapping(value="/api/navdata/{isin}",produces = "application/json")
	@CrossOrigin(origins="https://www.freemi.in")
	@ResponseBody
	public String  getNavDataForIsisn(@PathVariable(name="isin") String isin,Model model, HttpServletRequest request, HttpServletResponse httpResponse){
		logger.info("Request received to fetch NAV data via API..");
//		String response ="SUCCESS";
		List<MfNavData> navhistorydata = null;
//		List<String> data = null;
		String json = null;
		try{
			if(!isin.isEmpty()){
				
//				JSONObject obj = new JSONObject(navData);
//		        String pageName = obj.getJSONObject("pageInfo").getString("pageName");
//		        isin = obj.getString("isin");
//		        System.out.println(pageName);
		        
		        navhistorydata=  bseEntryManager.getnavdataByISIN(isin);
//				System.out.println(navhistorydata);
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
	
	
}
