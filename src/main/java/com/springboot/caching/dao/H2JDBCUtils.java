package com.springboot.caching.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.springframework.core.env.Environment;

import org.springframework.beans.factory.annotation.Autowired;

public class H2JDBCUtils {
	
	 @Autowired
	 private static Environment env;
	 
	 private static String jdbcURL = "jdbc:h2:~/testOrderSample";
     private static String jdbcUsername = "swathiOrderSample";
     private static String jdbcPassword = "Sourya$3";
	 
	 //private static String jdbcURL = env.getProperty("spring.datasource.url");
	 //private static String jdbcUsername = env.getProperty("spring.datasource.username");
	 //private static String jdbcPassword = env.getProperty("spring.datasource.password");

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
