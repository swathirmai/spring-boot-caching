package com.springboot.caching.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.springboot.caching.util.CachingFields;

public class H2InsertOrder {
	
	private static final String INSERT_EMPLOYEE_SQL = "INSERT INTO Order"
			+ "  (orderid, status, productid, productname, orderdate,quantity,price) VALUES "
			+ " (?, ?, ?, ?, ?, ?, ?);";

	public void insertRecord(CachingFields cachingfields) throws SQLException {
		Connection connection = H2JDBCUtils.getConnection();
		try {			
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEE_SQL);
			preparedStatement.setString(1, cachingfields.getOrderid());
			preparedStatement.setString(2, cachingfields.getStatus());
			preparedStatement.setString(3, cachingfields.getProductid());
			preparedStatement.setString(4, cachingfields.getProductname());
			preparedStatement.setString(5, cachingfields.getOrderdate());
			preparedStatement.setString(6, cachingfields.getQuantity());
			preparedStatement.setString(7, cachingfields.getPrice());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			//throw new EmployeeServNotFoundException(e.getMessage());
		} finally {
			connection.close();
		}
	}
}
