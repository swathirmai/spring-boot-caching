package com.springboot.caching.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.springboot.caching.util.CachingFields;


public class H2GetOrder {

	private static final String QUERY = "select orderid, status, productid, productname, orderdate,quantity, price from Order where orderid =?";

	public CachingFields getOrderRecord(String orderid) throws SQLException {
		CachingFields cachingfields = new CachingFields();
		Connection connection = H2JDBCUtils.getConnection();
		try {

			PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1, Integer.valueOf(orderid));
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String orderidval = rs.getString("orderid");
				String status = rs.getString("status");
				String productid = rs.getString("productid");
				String productname = rs.getString("productname");
				String orderdate = rs.getString("orderdate");
				String quantity = rs.getString("quantity");
				String price = rs.getString("price");

				cachingfields.setOrderid(orderidval);
				cachingfields.setStatus(status);
				cachingfields.setProductid(productid);
				cachingfields.setProductname(productname);
				cachingfields.setOrderdate(orderdate);
				cachingfields.setQuantity(quantity);
				cachingfields.setPrice(price);

			}
		} catch (SQLException e) {
			H2JDBCUtils.printSQLException(e);
		} finally {
			connection.close();
		}
		return cachingfields;

	}
}
