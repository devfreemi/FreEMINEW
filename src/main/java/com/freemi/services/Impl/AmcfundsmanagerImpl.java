package com.freemi.services.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.freemi.database.interfaces.BseSelectedCategoryFundsRepository;
import com.freemi.database.interfaces.FreEMISelectedFundsRepository;
import com.freemi.entity.investment.BseMFSelectedFunds;
import com.freemi.entity.investment.FreemiSelectedFunds;
import com.freemi.services.interfaces.Amcfundmanager;

@Service
public class AmcfundsmanagerImpl implements Amcfundmanager {

	@Autowired
	Environment env;

	@Autowired
	BseSelectedCategoryFundsRepository bseSelectedCategoryFundsRepository;

	@Autowired
	FreEMISelectedFundsRepository freemiselectedfundsrepository ;

	private static final Logger logger = LogManager.getLogger(AmcfundsmanagerImpl.class);
	
	@Cacheable(value = "mutualfundexplorerdata_new", unless = "#result == null")
	public List<BseMFSelectedFunds> getAllSelectedFunds() {
		logger.info("getAllSelectedFunds(): get all selected funds- ");

		List<BseMFSelectedFunds> fundDetails =null;
		BseMFSelectedFunds fund =null;
		FreemiSelectedFunds selectedfund = null;
		List<FreemiSelectedFunds> freemiselectedfunds =null;
		HashMap<String, BseMFSelectedFunds> maintainlist = new HashMap<String, BseMFSelectedFunds>();
		try{
//			fundDetails = bseSelectedCategoryFundsRepository.getAllByTopFundsView("Y");
			freemiselectedfunds = freemiselectedfundsrepository.findAllByAmcenabledAndFundactivefortransactionAndTopfundsview("Y","Y","Y");
			
			
			fundDetails = reconcilefunddata(fundDetails, freemiselectedfunds, maintainlist);
			
			
		}catch(Exception e){
			logger.error("Failed to query database to fetch funds",e);
		}

		return fundDetails;
	}

	private List<BseMFSelectedFunds> reconcilefunddata(List<BseMFSelectedFunds> fundDetails,
			List<FreemiSelectedFunds> freemiselectedfunds, HashMap<String, BseMFSelectedFunds> maintainlist) {
		BseMFSelectedFunds fund;
		FreemiSelectedFunds selectedfund;
		if(freemiselectedfunds!=null) {
			for(int i=0;i<freemiselectedfunds.size();i++) {
				selectedfund = freemiselectedfunds.get(i);
				if(maintainlist.containsKey(selectedfund.getSchemeName()) ){
					fund = maintainlist.get(selectedfund.getSchemeName());
					
				}else {
					fund = new BseMFSelectedFunds();
					
//						MAP DATA
					fund.setSchemeName(selectedfund.getSchemeName());
					fund.setRtaAgent(selectedfund.getRtaagent());
					fund.setIsin(selectedfund.getIsin());
//						fund.setTopFundsView(selectedfund.get);
					fund.setFundCatergory(selectedfund.getFundCatergory());
				}
					
				if(selectedfund.getReinvestmentflag().equalsIgnoreCase("Z")) {	
					//Growth fund related details mapping
					if(selectedfund.getInvestmenttype().equalsIgnoreCase("DEFAULT")) {
						fund.setGrowthSchemeCode(selectedfund.getSchemecode());
						fund.setLumpsumminPurchaseAmt(selectedfund.getLumpsumminPurchaseAmt());
						fund.setLumpsummaxPurchaseAmt(Integer.toString(selectedfund.getLumpsummaximumpurchaseamnt()));
						fund.setSettlementType(selectedfund.getSettlementType());
						fund.setSipFrequency(selectedfund.getSipFrequency());
						fund.setSipDates(selectedfund.getSipDates());
						fund.setSipMinInstallAmnt(selectedfund.getSipMinInstallAmnt());
						fund.setSipMaxInstallAmntl(selectedfund.getSipMaxInstallAmntl());
//							fund.setMinSipInstallments(selectedfund.get);
						fund.setNav(selectedfund.getNav());
						fund.setgNavDate(selectedfund.getNavdate());
						fund.setMinSipInstallments(selectedfund.getMinSipInstallments());
					}
					
				}else if(selectedfund.getReinvestmentflag().equalsIgnoreCase("Y")) {
					//Reinvestment fund related details mapping
					if(selectedfund.getInvestmenttype().equalsIgnoreCase("DEFAULT")) {
						fund.setReinvSchemeCode(selectedfund.getSchemecode());
						fund.setReinvNav(selectedfund.getNav());
						fund.setrNavDate(selectedfund.getNavdate());
					}
					
				}else if(selectedfund.getReinvestmentflag().equalsIgnoreCase("N")) {
					//Dividend payout fund related mapping
				}else {
					logger.info("Invalid catefory reinvestmentlag- "+ selectedfund.getReinvestmentflag());
				}
				
				if(maintainlist.containsKey(selectedfund.getSchemeName())) {
					maintainlist.replace(selectedfund.getSchemeName(), fund);	//Replace does nothing if no value found
				}else {
					maintainlist.put(selectedfund.getSchemeName(), fund);
				}
			}
			logger.info("Total active funds for explorer- "+ maintainlist.size());
			/*
			 * for (String key: maintainlist.keySet()){ System.out.println(key); }
			 */ 
			
			fundDetails = new ArrayList<BseMFSelectedFunds>();
			// Java 8 code to convert map values to list
			fundDetails
		    = maintainlist.values().stream().collect(
		        Collectors.toCollection(ArrayList::new));
		}
		return fundDetails;
	}

	@Override
	public List<BseMFSelectedFunds> getSelectedfunddetails(List<String> fundlist) {
		List<BseMFSelectedFunds> fundDetails =null;
		List<FreemiSelectedFunds> freemiselectedfunds =null;
		HashMap<String, BseMFSelectedFunds> maintainlist = new HashMap<String, BseMFSelectedFunds>();
		
		try{
			freemiselectedfunds = freemiselectedfundsrepository.findByAmcenabledAndFundactivefortransactionAndFreemifundcodeIn("Y","Y",fundlist);
			logger.info("Total fetched records- "+ freemiselectedfunds.size());
			fundDetails = reconcilefunddata(fundDetails, freemiselectedfunds, maintainlist);
			
		}catch(Exception e){
			logger.error("Failed to query database to fetch selected category funds",e);
		}
		
		return fundDetails;
	}

}
