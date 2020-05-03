package com.freemi.ui.controller;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulerTasks {

	private static final Logger logger = LogManager.getLogger(SchedulerTasks.class);
	
	@Autowired
    CacheManager cacheManager;
	
	/*
	 * @Autowired BseEntryManager bseEntryManager;
	 */
	
//	@Scheduled(fixedRate = 15000)
	@Scheduled(cron = "0 0 7 * * ?")
	@CacheEvict(value = { "mutualfundexplorerdata","isinnavhistory" })
	public void clearCache() {
		logger.info("clear cache- mutualfundexplorerdata,isinnavhistory");
		
	}
	
	/*
	 * @Scheduled(fixedDelay = 10 * 60 * 1000, initialDelay = 500)
	 * 
	 * @CacheEvict(value = { "mffundhistory" }) public void
	 * clearMfDataHistoryCache() { logger.info("clear cache- mffundhistory");
	 * 
	 * }
	 */
	
    /*public static void main(String[] args) {
        
        String s="100|Allotment Statement Details|1000.0000|3890.9139|15230617/96|HDLFGN-GR|1000.0000|DEBASISH87|0.2570||Y|NRM|P|26273|2021006|T1||NRM|N|PSH871586332517499|null||NRM|0|||2020-04-13|HLFGN|Y|329546559||2020-04-16|INF179KB1HK0|0|2500.0000|104.0662|5192879/48|EPEG|2500.0000|DEEPTI790K|24.0230||Y|ISP|P|26273|2021006|T3|E241233|NRM|Y||null||ISP|5106046|20/04/2019||2020-04-13|TEPEG|Y|48602245||2020-04-16|INF277K01451|0|2000.0000|21.4484|1038871259|B303G|2000.0000|DEBASISH87|93.2470||Y|ISP|P|26273|2021006|T1||NRM|N||null||ISP|8684349|09/04/2020||2020-04-13|B303G|Y|227394973||2020-04-16|INF209K01603|0|2500.0000|377.3280|11408916/90|32|2500.0000|SOUMYO89|6.6260||Y|XSP|P|26273|2021006|T3|E241233|NRM|Y||null||XSP|4445520|09/02/2019||2020-04-13|H32|Y|328718728||2020-04-16|INF179K01BB8|0|2500.0000|31.2288|22837380|103G|2500.0000|DEEPTI790K|80.0540||Y|ISP|P|26273|2021006|T3||NRM|N||null||ISP|5403960|30/05/2019||2020-04-13|L103G|Y|257838228||2020-04-16|INF200K01180|0";
        String[] st = s.split("\\|");
        System.out.println(st.length);
        System.out.println(Arrays.asList(st));
        List<String> data = Arrays.asList(st);
        int counter=0;
        
        for(int i=2;i<Arrays.asList(st).size();i++) {
        counter+=1;
        System.out.print(data.get(i) + "-");
        if(counter%32==0) {
    //	        counter=1;
            System.out.println();
        }
        
    //		System.out.println(i+1 + " -> "+ data.get(i));
    	
        }
        
    }*/
}
