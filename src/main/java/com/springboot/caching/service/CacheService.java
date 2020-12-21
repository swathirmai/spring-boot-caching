package com.springboot.caching.service;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.caching.dao.CacheDao;
import com.springboot.caching.util.CachingFields;
import com.springboot.caching.util.CachingFieldsUtil;

@Service
public class CacheService {

	private static final Logger log = LoggerFactory.getLogger(CacheService.class);

	@Autowired
	private CachingFieldsUtil cachingFieldsUtil;

	@Autowired
	private CacheDao cacheDao;

	public List<CachingFields> getDbTableData() throws Exception {

		List<CachingFields>  cachingFields = null;

		try {
			cachingFields = cacheDao.getDbTableData();
		} catch (SQLException e) {
			log.error("Error in CacheService - getDbTableData" + e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error("Error in CacheService - getDbTableData" + e.getMessage());
			throw e;
		}
		return cachingFields;
	}

	public List<CachingFields> getCacheData() throws Exception {

		List<CachingFields>  cachingFields = null;

		try {
			cachingFields = cachingFieldsUtil.getCachingFields();
		} catch (Exception e) {
			log.error("Error in CacheService - getCacheData" + e.getMessage());
			throw e;
		}
		return cachingFields;
	}

	public List<CachingFields> getCacheDataByKey(String key) throws Exception {

		List<CachingFields>  cachingFields = null;
		List<CachingFields>  cachingKeyFields = null;

		try {
			cachingFields = cachingFieldsUtil.getCachingFields();
			cachingKeyFields  = cachingFields.stream().filter(val->key.equals(val.getStatus())).collect(Collectors.toList());
		} catch (Exception e) {
			log.error("Error in CacheService - getCacheDataByKey" + e.getMessage());
			throw e;
		}
		return cachingKeyFields;
	}

}
