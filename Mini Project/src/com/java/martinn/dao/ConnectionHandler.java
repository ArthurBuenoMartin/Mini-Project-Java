package com.java.martinn.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionHandler {
	
	private static String url = "jdbc:mysql://localhost:3306/martinn";
	private static String user = "root";
	private static String password ="root";
	private static Connection connection;
	
	
	public static Connection getConnection() {
		try {
			Connection connection = DriverManager.getConnection(url,user,password);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return connection;
	}
	
}
