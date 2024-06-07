package com.cris.cmsfpws;

import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.commons.codec.binary.Base64;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class CMSClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		
		
		 try {
			 
			 
			 
			 DBConnection db = new DBConnection();	
			 String device_name = "Bio";
			 ResultSet rs_devicename = db.executeQuery("SELECT Device_Name_v FROM Device_Details WHERE Device_Type_v='BIO' AND Selected_v='Y'");
			 
			 if(rs_devicename.next())
			 {
				 device_name=rs_devicename.getString("Device_Name_v").trim();
			 }
			 rs_devicename.close();
			 
			 
			 ResultSet peer_rs = db.executeQuery("SELECT peer_ip_v FROM peers");
			 
			 if(peer_rs.next())
			 {
				 
				 String ws_ip=peer_rs.getString("peer_ip_v");
				 
				 ResultSet rs_crewid=null;
				 ResultSet rs=null;
				 //rs_crewid = db.executeQuery("SELECT * FROM FP_Data WHERE synched='N' LIMIT 10");
				 rs_crewid = db.executeQuery("SELECT DISTINCT crewid_v FROM FP_Data WHERE synched='N' LIMIT 10");
				 String myurl="";
				 
				 String id = "";
				 String f1 = "";
				 String f2 = "";
				 String print1 = "";	
				 String print2 = "";
				 String synch_status="N";	
				 int count=0;
				 while(rs_crewid.next())
				 {
					 
					 
					rs = db.executeQuery("SELECT * FROM FP_Data WHERE crewid_v ='" + rs_crewid.getString("crewid_v") + "'");
					
					int bloblength=0;
					String imageDataString ="";
					// GET FIRST FINGER
					if(rs.next())
					{
					
					id = rs.getString("crewid_v");
					f1 = rs.getString("finger_v");
					synch_status = rs.getString("synched");
					
					Blob blob1 = rs.getBlob("fingerprint_B");
					bloblength = (int) blob1.length();
					byte[] imageData1 = blob1.getBytes(1, bloblength);
		    		
		    		
		    		/*
		    		 * Converting Image byte array into Base64 String 
		    		 */
		    		imageDataString = Base64.encodeBase64URLSafeString(imageData1);	
		    		
		    		print1 = imageDataString;
		    		blob1.free();
		    		
					 }
		    		// GET SECOND FINGER
		    		 if(rs.next())
					 {
						
						f2 = rs.getString("finger_v");
						
						
						Blob blob2 = rs.getBlob("fingerprint_B");
						bloblength = (int) blob2.length();
						byte[] imageData2 = blob2.getBytes(1, bloblength);
			    		
			    		
			    		/*
			    		 * Converting Image byte array into Base64 String 
			    		 */
			    		imageDataString = Base64.encodeBase64URLSafeString(imageData2);	
			    				    		
			    		print2 = imageDataString;
			    		blob2.free();
						
					 }
		    		 

		    		 if(synch_status.equals("R"))
		    			 myurl = "http://" + ws_ip + "/cmsfpws/updatefpdata/" + id + "@1@" + f1 + "@2@" + f2 +  "@3@" + print1 + "@4@" + print2 + "@5@" + device_name + "@6@";
		    		 else
		    			 myurl = "http://" + ws_ip + "/cmsfpws/insertfpdata/" + id + "@1@" + f1 + "@2@" + f2 +  "@3@" + print1 + "@4@" + print2 + "@5@" + device_name + "@6@" ;


					 Client client = Client.create();
					 WebResource webresource = client.resource(myurl);
					 
					 ClientResponse response1 = webresource.accept("application/json").get(ClientResponse.class);
					 
					 if(response1.getStatus() != 200)
					 {
						 throw new RuntimeException("Failed : HTTP error code : "
									+ response1.getStatus());
						 
					 }
					 
					 
					 String output = response1.getEntity(String.class);
					 System.out.println("Output from Server .... \n");
					
					 
					 
					 
					 if(output.equals("[null]"))
						 System.out.println("null string");	 
					 else
					 {
						 
						 int rowcount = db.executeUpdate("UPDATE FP_Data SET synched='Y' WHERE crewid_v='" + rs_crewid.getString("crewid_v") + "'");
						 System.out.println(count++ + " Synched: " + rowcount);  
						    
					 }
					 
					
				 } // WHILE ENDS
				 
				 
				 
				 
				 
				 
				 
				 
				 
				 
				 
				 
				 
				 
			 }
			 
		
			
			

			 
			 
			 
			
			  } catch (Exception e) {

				e.printStackTrace();

			  } 
		
		
		
		
		
		
	}

}
