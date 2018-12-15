package com.freemi.ui.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.freemi.controller.interfaces.FreemiServiceInterface;
import com.freemi.database.service.DatabaseEntryManager;

@RestController
public class CampaignProducts {

private static final Logger logger = LogManager.getLogger(CampaignProducts.class);
	/*
	@Autowired
	private DatabaseEntryManager databaseEntryManager ;//= (DatabaseEntryManager) BeanUtil.getBean(DatabaseEntryService.class);
	*/
	@Autowired
	FreemiServiceInterface freemiServiceInterface;
	
	@RequestMapping(value = "/api/campaign/loanrequest", method = RequestMethod.POST)
	public String home(HttpServletRequest request, HttpServletResponse rsponse, Model model) {
		//logger.info("@@@@ Inside Login..");
		logger.info("@@@@ HomeController @@@@");
		return "index";
	}
}
