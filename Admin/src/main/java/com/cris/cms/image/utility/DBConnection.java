package com.cris.cms.image.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import java.sql.Statement;

public class DBConnection {
	
	private Connection conn=null;
	private ResultSet res = null;
	
	private Connection getConnection()
	{
		 

		  String url = "jdbc:mysql://localhost:3306/";
		  String dbName = "babio";
		  String driver = "com.mysql.cj.jdbc.Driver";
		  String userName = "root"; 
		  String password = "Babio@123";
		  
		  
		  try {
			  	  
		  Class.forName(driver).newInstance();
		  conn = DriverManager.getConnection(url+dbName,userName,password);
		  
		  } catch (Exception e) {
			  e.printStackTrace();
			  }
		
		  return conn;
		
	}
	

	public ResultSet executeQuery(String query) 
	{
		 Connection conn = null;
		 
		 
		  
		  try {
			  
			  conn = getConnection();
			  //Statement st = conn.createStatement();
			//CHANGES
			  Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			//CHANGES OVER
			  res = st.executeQuery(query);
		  
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
		  
		  return res;
		
	}


	public int executeUpdate(String query) 
	{
		 Connection conn = null;
		 
		 int val = 0;
		  
		  try {
			  
			  conn = getConnection();
			  //Statement st = conn.createStatement();
			//CHANGES
			  Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			//CHANGES OVER
			  
			  val = st.executeUpdate(query);
	
		  
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
		 
		
		
		  return val;
		
	}
	

	public void closeCon()
	{
		 
		  
		  try {
			  
			 conn.close();
			 res.close();
		  
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
		 
		
	}
	

}