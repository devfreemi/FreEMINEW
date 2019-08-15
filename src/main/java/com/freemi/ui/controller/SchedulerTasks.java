package com.freemi.ui.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//@RestController
@Component
public class SchedulerTasks {

	private static final Logger logger = LogManager.getLogger(SchedulerTasks.class);
	
	@Autowired
    CacheManager cacheManager;
	
	/*
	 * @Autowired BseEntryManager bseEntryManager;
	 */
	
//	@Scheduled(fixedRate = 15000)
	@Scheduled(cron = "0 7 * * *")
	@CacheEvict(value = { "mutualfundexplorerdata" })
	public void clearCache() {
		logger.info("clear cache-mutualfundexplorerdata");
		
	}
}
