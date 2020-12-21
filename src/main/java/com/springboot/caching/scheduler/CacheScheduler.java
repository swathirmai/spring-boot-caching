package com.springboot.caching.scheduler;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.springboot.caching.service.CacheService;
import com.springboot.caching.util.CachingFields;
import com.springboot.caching.util.CachingFieldsUtil;

@Component
public class CacheScheduler {

	private static final Logger log = LoggerFactory.getLogger(CacheScheduler.class);

	@Autowired
	private CachingFieldsUtil cachingFieldsUtil;

	@Autowired
	private CacheService cacheService;

	//@Scheduled(cron = "${caching.schedule}")
	@Scheduled(cron = "0 0/5 * * * ?")
	public void dbCachingRefresh() throws Exception {

		try {
			List<CachingFields> cachingFields = cacheService.getDbTableData();
			cachingFieldsUtil.setCachingFields(cachingFields);

		} catch (SQLException e) {
			log.error("Error in CacheScheduler - dbCachingRefresh" + e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error("Error in CacheScheduler - dbCachingRefresh" + e.getMessage());
			throw e;
		}
	}

	public void setCachingData() throws Exception {

		try {
			List<CachingFields> cachingFields = cacheService.getDbTableData();
			cachingFieldsUtil.setCachingFields(cachingFields);

		} catch (SQLException e) {
			log.error("Error in CacheScheduler - setCachingData" + e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error("Error in CacheScheduler - setCachingData" + e.getMessage());
			throw e;
		}
	}

}
