package com.springboot.caching.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.caching.service.CacheService;
import com.springboot.caching.util.CachingFields;
import com.springboot.caching.util.CachingFieldsUtil;
import java.sql.Connection;

@Repository
public class CacheDao {

	private static final Logger log = LoggerFactory.getLogger(CacheDao.class);

	@Autowired
	private CachingFieldsUtil cachingFieldsUtil;

	public List<CachingFields> getDbTableData() throws Exception {
		Connection connection = H2JDBCUtils.getConnection();
		System.out.println("connection details : " + connection.getSchema());
		List<CachingFields>  cachingFields = new ArrayList<>();
		CachingFields chFields = new CachingFields();
		chFields.setOrderid("1234");
		chFields.setOrderdate("12/14/2020");
		chFields.setPrice("478");
		chFields.setProductid("45");
		chFields.setProductname("Chair");
		chFields.setQuantity("1");
		chFields.setStatus("Active");		
		H2GetOrder h2GetOrder = new H2GetOrder();
		H2CreateOrder h2CreateOrder = new H2CreateOrder();
		H2InsertOrder h2InsertOrder = new H2InsertOrder();
		String orderid = "1234";		
		try {
			h2CreateOrder.createTable();
			h2InsertOrder.insertRecord(chFields);
			h2GetOrder.getOrderRecord(orderid);
		} catch (SQLException e) {
			log.error("Error in CacheService - getDbTableData" + e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error("Error in CacheService - getDbTableData" + e.getMessage());
			throw e;
		}
		return null;
	}
}
