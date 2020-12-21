package com.springboot.caching.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class H2CreateOrder {

	private static final String createTableSQL = "create table Order (\r\n" + "  orderid  int(3) primary key,\r\n"
			+ "  status(20),\r\n"
			+ "  productid varchar(20),\r\n" 
			+ "  productname varchar(20),\r\n" 
			+ "  orderdate varchar(20),\r\n" 
			+ "  quantiry varchar(20),\r\n" 
			+ "  price varchar(20)\r\n"   + "  );";

	public static void main(String[] argv) throws SQLException {
		H2CreateOrder createOrder = new H2CreateOrder();
		createOrder.createTable();
	}

	public void createTable() throws SQLException {

		Connection connection = H2JDBCUtils.getConnection();
		try {			
			Statement statement = connection.createStatement();
			System.out.println("createTableSQL statement : "  +createTableSQL);
			statement.execute(createTableSQL);

		} catch (SQLException e) {
			H2JDBCUtils.printSQLException(e);
		} finally {
			connection.close();
			
		}
	}
}