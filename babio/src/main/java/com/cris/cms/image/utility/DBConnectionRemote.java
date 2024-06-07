package com.cris.cms.image.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class DBConnectionRemote {
	
	private Connection conn=null;
	private ResultSet res = null;
	
	public Connection getConnection(String server)
	{
		 

		String url = "jdbc:mysql://" + server + ":3306/";
		  String dbName = "babio";
		  String driver = "com.mysql.cj.jdbc.Driver";
		  String userName = "replicator"; 
		  String password = "Babio@123";
		  
		  
		  try {
			  
		  System.out.println("URL : " + url);  
		  Class.forName(driver).newInstance();
		  
		  //Properties props = new Properties();
		  //props.put("connectTimeout", "5");
		  //conn = DriverManager.getConnection(url,props);
		  conn = DriverManager.getConnection(url+dbName,userName,password);
		  
		  } catch (Exception e) {
			  e.printStackTrace();
			  }
		
		  return conn;
		
	}
	
	

	public ResultSet executeQuery(String query, String server) 
	{
		 Connection conn = null;
		 
		 
		  
		  try {
			  
			  conn = getConnection(server);
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


	public int executeUpdate(String query, String server) 
	{
		 Connection conn = null;
		 
		 int val = 0;
		  
		  try {
			  
			  conn = getConnection(server);
			  //Statement st = conn.createStatement();
			//CHANGES
			  Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			//CHANGES OVER
			  
			  val = st.executeUpdate(query);
			  
			  if(val==1)
				  System.out.print("Successfully inserted value");
		  
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