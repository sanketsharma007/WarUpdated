package com.cris.cms.image.action;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.cris.cms.image.actionforms.LoginForm;
import com.cris.cms.image.utility.AESencrp;
import com.cris.cms.image.utility.DBConnection;

public class LoginAction extends DispatchAction{
	
	
	public ActionForward turnOn(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		  System.out.println("Inside Turn On");
		   
		  ActionForward forward = new ActionForward();
		  LoginForm fb = (LoginForm) form;
		   
		   String ret = "";
		    //String[] command = {"CMD", "/C", "dir"};
		    ProcessBuilder probuilder = new ProcessBuilder("./ba");

		   
		    try {
		       
		  
		   
		    //You can set up your work directory
		    probuilder.directory(new File("/home/odi"));
		   
		    Process process = probuilder.start();
		   
		    //Read out dir output
		    InputStream is = process.getInputStream();
		    InputStreamReader isr = new InputStreamReader(is);
		    BufferedReader br = new BufferedReader(isr);
		    String line;
		    //System.out.printf("Output of running %s is:\n", Arrays.toString(command));
		    while ((line = br.readLine()) != null) {
		        System.out.println(line);
		        ret += line + "\n";
		    }
		    int exitValue = process.waitFor();
		    
		    fb.setOutput(ret);
		    
		    System.out.println("\n\nExit Value is " + exitValue);
		   
		    } catch (InterruptedException e) {
		        // TODO Auto-generated catch block
		        e.printStackTrace();
		    }
		    catch (Exception e) {
		        // TODO Auto-generated catch block
		        e.printStackTrace();
		    }
		   
		    forward = mapping.findForward("success");
		    return (forward);
	}
	
	
	
	
	



public ActionForward login(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception
{
	
	 System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  login   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	
	
	
	
	LoginForm lf = (LoginForm) form;
	
	  ActionForward forward = new ActionForward();
	  
	  
	  DBConnection db = new DBConnection();
	  
	 String password = lf.getPassword(); 
	 
	
		try{
			       
			
			
			
			ResultSet rs = db.executeQuery("SELECT * FROM users WHERE username_v='" + lf.getUsername() + "'");
		        
			if(rs.next())
			{
				String decryppass = AESencrp.decrypt(rs.getString("password_v"));
				lf.setName(rs.getString("name_v"));
				
				if(password.equals(decryppass))
				{					
					request.getSession().setAttribute("name", lf.getName());
					request.getSession().setAttribute("username", lf.getUsername());
					lf.setPassword(null);
					lf.setMessage(null);
					forward = mapping.findForward("success_login");
				}
				else
				{
					forward = mapping.findForward("fail");
					lf.setMessage("Invalid Username / Password");
				}
			}else
			{
				forward = mapping.findForward("fail");
				lf.setMessage("Invalid Username / Password");
			}
			
			
		        
		}catch(Exception e)
		{
			System.out.println("Ex : " + e);
		}
		
		
	
	System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<  login   <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	System.out.println("\n\n\n\n\n");

	
	db.closeCon();
	
	 
	 return (forward);


}


	



public ActionForward changePassword(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception
{
	
	 System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  changePassword   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	
	
	 PrintWriter out = response.getWriter();
	 LoginForm lf = (LoginForm) form;
	
	  ActionForward forward = new ActionForward();
	  
	  
	  DBConnection db = new DBConnection();
	  
	 String password = lf.getPassword(); 
	 String oldpassword = lf.getOldpassword();
	 String re_password = lf.getRe_password();
	 
	 String username = (String) request.getSession().getAttribute("username");
	 
	

		try{
			       
			
			
			
			ResultSet rs = db.executeQuery("SELECT * FROM users WHERE username_v='" + username + "'");
		        
			if(rs.next())
			{
				String decryppass = AESencrp.decrypt(rs.getString("password_v"));
				lf.setName(rs.getString("name_v"));
				
				if(oldpassword.equals(decryppass))
				{	
					
					db.executeUpdate("UPDATE users SET password_v='" + AESencrp.encrypt(password) + "' WHERE username_v='" + username + "'");
					request.getSession().invalidate();
					lf.setPassword(null);
					lf.setOldpassword(null);
					lf.setRe_password(null);
					
					out.println("SUCCESS");
		        	out.flush();	
		        	db.closeCon();
		        	return null;
				}
				else
				{
					out.println("Old password does not match");
		        	out.flush();	
		        	db.closeCon();
		        	lf.setPassword(null);
					lf.setOldpassword(null);
					lf.setRe_password(null);
		        	return null;
				}
			}else
			{
				out.println("Invalid Username / Password");
	        	out.flush();	
	        	db.closeCon();
	        	lf.setPassword(null);
				lf.setOldpassword(null);
				lf.setRe_password(null);
	        	return null;
			}
			
			
		        
		}catch(Exception e)
		{
			System.out.println("Ex : " + e);
		}
		
		
	
	System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<  changePassword   <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	System.out.println("\n\n\n\n\n");

	
	db.closeCon();
	
	lf.setPassword(null);
	lf.setOldpassword(null);
	lf.setRe_password(null);
	return null;


}


	



public ActionForward saveNetworkConfig(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception
{
	
	 System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  saveNetworkConfig   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	
	
	 PrintWriter out = response.getWriter();
	 LoginForm lf = (LoginForm) form;
	
	  ActionForward forward = new ActionForward();
	  
	  
	  DBConnection db = new DBConnection();
	  
	 String ipaddress = lf.getIpaddress();
	 String subnetmask = lf.getSubnetmask();
	 String gateway = lf.getGateway();
	 String netinterface = lf.getNetinterface();
	 
	
	 
	 try {

			String content = "auto lo " + netinterface 
						   + "\niface lo inet loopback\n\n "
						   + "iface " + netinterface + " inet static\n "
						   + "\taddress " + ipaddress +"\n"
						   + "\tnetmask " + subnetmask + "\n"
						   + "\tgateway " + gateway + "\n";

			File file = new File("/etc/network/interfaces");

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();
			fw.close();

			
        	
        	
			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
			out.println("FAIL");
	    	out.flush();
		}
	 
	 try {

			String content = "auto lo " + netinterface 
						   + "\niface lo inet loopback\n\n "
						   + "iface " + netinterface + " inet static\n "
						   + "\taddress " + ipaddress +"\n"
						   + "\tnetmask " + subnetmask + "\n"
						   + "\tgateway " + gateway + "\n";

			File file2 = new File("/home/babio/interfaces");

			// if file doesnt exists, then create it
			if (!file2.exists()) {
				file2.createNewFile();
			}

			FileWriter fw2 = new FileWriter(file2.getAbsoluteFile());
			BufferedWriter bw2 = new BufferedWriter(fw2);
			bw2.write(content);
			bw2.close();
			fw2.close();
		
			
     	
     	
			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
			out.println("FAIL");
	    	out.flush();
		}
	 
	 
	 
	 try{
			
			
	        
		 ProcessBuilder pb = new ProcessBuilder("./restartnetwork.sh");
		 pb.directory(new File("/usr/local"));
		
		
	    java.lang.Process p = pb.start();
	
	    String line;			
	    BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
	  
		    
	        while ((line = r.readLine()) != null) {
	        	out.println(line);
	        	out.flush();
	        	
	        	
	        	System.out.println("Line : " + line);
	        	
	        }
	       
	      
	   
	        r.close();
	        
	        
	        
	}catch(Exception e)
	{
		System.out.println("Ex : " + e);
	}
	
		
		out.println("SUCCESS");
    	out.flush();	
		
	 
	 

	return null;


}


	
	
	



	


public ActionForward config(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception
{
	
	 System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  config   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	
	
	
	ArrayList dev = new ArrayList();
	LoginForm lf = (LoginForm) form;
	
	  ActionForward forward = new ActionForward();
	  
	  
	  DBConnection db = new DBConnection();
	  
	  

		try{
			       
			
			
			
			ResultSet rs = db.executeQuery("SELECT Enable_v,Timeout_value_v FROM Devices_Enable WHERE Device_Type_v='" + lf.getDevice_type() + "'");
		        
			while(rs.next())
			{
				lf.setDevice_status(rs.getString("Enable_v").trim());
				lf.setTimeout(rs.getString("Timeout_value_v").trim());		
			}
			
			
		        
		}catch(Exception e)
		{
			System.out.println("Ex : " + e);
		}
		
		
		
		
	
	
	try{
		       
		
		
		
		ResultSet rs = db.executeQuery("SELECT Device_Name_v,Selected_v FROM Device_Details WHERE Device_Type_v='" + lf.getDevice_type() + "'");

		while(rs.next())
		{
			if(rs.getString("Selected_v").trim().equals("Y"))
			dev.add(new org.apache.struts.util.LabelValueBean(rs.getString("Device_Name_v").trim() , rs.getString("Device_Name_v").trim()));
			
		}

		rs.beforeFirst();
		
		while(rs.next())
		{
			if(!rs.getString("Selected_v").trim().equals("Y"))
			dev.add(new org.apache.struts.util.LabelValueBean(rs.getString("Device_Name_v").trim() , rs.getString("Device_Name_v").trim()));			
			//System.out.println("Device " + rs.getString("Device_Name_v").trim());
			
		}
		
		lf.setRow(dev);
	        
	}catch(Exception e)
	{
		System.out.println("Ex : " + e);
	}
	
	
	System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<  config   <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	System.out.println("\n\n\n\n\n");

	
	db.closeCon();
	
	 forward = mapping.findForward("success");
	 return (forward);


}







public ActionForward configSMS(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception
{

 System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  configSMS   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");




LoginForm lf = (LoginForm) form;
ArrayList smsselect = new ArrayList();
ArrayList smsselectvalue = new ArrayList();
ArrayList smsname = new ArrayList();
ArrayList smsmobile = new ArrayList();
ArrayList smsdesignation = new ArrayList();
ArrayList smsgroup = new ArrayList();

lf.setSms_name(null);
lf.setSms_mobile(null);
lf.setSms_designation(null);
lf.setSmsselect(null);
ActionForward forward = new ActionForward();  
DBConnection db = new DBConnection();
  
  

	try{
		       
		
		
		
		ResultSet rs = db.executeQuery("SELECT * FROM sms");
	        
		while(rs.next())
		{
			System.out.println(rs.getString("name_v"));
			smsselect.add("off");
			smsselectvalue.add("N");
			smsname.add(rs.getString("name_v"));			
			smsmobile.add(rs.getString("mobile_v"));
			smsdesignation.add(rs.getString("designation_v"));
			smsgroup.add(rs.getString("group_v"));
			
		}
		System.out.println("SAelect Size " + smsselect.size());
		lf.setSmsselect((String[]) smsselect.toArray(new String[smsselect.size()]));
		lf.setSmsselectvalue((String[]) smsselectvalue.toArray(new String[smsselectvalue.size()]));
		lf.setSmsname((String[]) smsname.toArray(new String[smsname.size()]));
		lf.setSmsmobile((String[]) smsmobile.toArray(new String[smsmobile.size()]));
		lf.setSmsdesignation((String[]) smsdesignation.toArray(new String[smsdesignation.size()]));
		lf.setSmsgroup((String[]) smsgroup.toArray(new String[smsgroup.size()]));
		
	        
	}catch(Exception e)
	{
		System.out.println("Ex : " + e);
	}
	

	
System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<  config   <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
System.out.println("\n\n\n\n\n");


db.closeCon();

 forward = mapping.findForward("sms");
 return (forward);


}











public ActionForward managePeers(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception
{
	
	 System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  managePeers   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	
	
	
	ArrayList peers = new ArrayList();
	LoginForm lf = (LoginForm) form;
	
	  ActionForward forward = new ActionForward();
	  
	  
	  DBConnection db = new DBConnection();
	  
	  	
	
	try{
		       
		
		
		
		ResultSet rs = db.executeQuery("SELECT peer_ip_v FROM peers");
	        
		while(rs.next())
		{
			peers.add(new org.apache.struts.util.LabelValueBean(rs.getString("peer_ip_v").trim() , rs.getString("peer_ip_v").trim()));
			//dev.add(rs.getString("Device_Name_v").trim());
			//System.out.println("Device " + rs.getString("Device_Name_v").trim());
			
		}
		
		lf.setPeers(peers);
	        
	}catch(Exception e)
	{
		System.out.println("Ex : " + e);
	}
	
	
	System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<  managePeers   <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	System.out.println("\n\n\n\n\n");

	
	db.closeCon();
	
	 forward = mapping.findForward("ManagePeers");
	 return (forward);


}







public ActionForward saveData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception
{
	
	 System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  saveData   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	
	
	PrintWriter out = response.getWriter();
	LoginForm lf = (LoginForm) form;	
	ActionForward forward = new ActionForward();
	DBConnection db = new DBConnection();
	String query="";
	
	
	int rs = 0;
	
		System.out.println("Status  : " + lf.getDevice_status().trim() );
		System.out.println("Type  : " + lf.getDevice_type().trim());
		System.out.println("Name  : " + lf.getDevice_name().trim());
		System.out.println("Timeout  : " + lf.getTimeout().trim());
	 
		
		  
	String old_enable_status = null;
	String old_timeout_value = "0";
	String ip_address = null;
	String old_device_name = "n/a";
	String image_version = null;
	
	
	
		// GET THE IP ADDRESS
		try{
			       
			ip_address = getCurrentIp().toString();
			ip_address = ip_address.substring(ip_address.indexOf("/") + 1);
		        
		}catch(Exception e)
		{
			System.out.println("Ex IP ADDRESS : " + e);
		}
		


		// EXTRACT THE OLD SETTINGS 		
		
		try{
			       
			
			ResultSet rs1 = db.executeQuery("SELECT Enable_v,Timeout_value_v FROM Devices_Enable WHERE Device_Type_v='" + lf.getDevice_type() + "'");
		        
			while(rs1.next())
			{
				old_enable_status = rs1.getString("Enable_v");
				old_timeout_value = rs1.getString("Timeout_value_v");
			}
			
		        
		}catch(Exception e)
		{
			System.out.println("Ex SELECT DEVICES ENABLE: " + e);
		}
		

		// EXTRACT THE OLD DEVICE NAME 
		try{
			       
			
			ResultSet rs2 = db.executeQuery("SELECT Device_Name_v FROM Device_Details WHERE Device_Type_v='" + lf.getDevice_type() + "' AND Selected_v='Y'");
		        
			while(rs2.next())
			{
				old_device_name = rs2.getString("Device_Name_v");
				
			}
			
		        
		}catch(Exception e)
		{
			System.out.println("Ex SELECT DEVICE DETAILS: " + e);
		}
		
		
		// EXTRACT THE IMAGE VERSION 
		try{
			       
			
			ResultSet rs3 = db.executeQuery("SELECT image_version_v FROM Image_Version");
		        
			while(rs3.next())
			{
				image_version = rs3.getString("image_version_v");
				
			}
			
		        
		}catch(Exception e)
		{
			System.out.println("Ex SELECT DEVICE DETAILS: " + e);
		}
		
		
		
		

		
		try{
			
			 query = "INSERT INTO Devices_Enable_His (Device_Type_v,Enable_v,Timeout_value_v,ipaddress_v,Device_Name_v,Remarks_v,image_version_v) VALUES ('" + lf.getDevice_type() + "','" + old_enable_status + "','" + old_timeout_value + "','" + ip_address + "','" + old_device_name + "','" + "dummyremarks" + "','" + image_version + "')";
			 System.out.println("Query  : " + query);
			 rs = db.executeUpdate(query);
			
			if(rs <= 0)
			{
				out.println("Data could not be saved. Please contact helpdesk");
	        	out.flush();
	        	db.closeCon();
	        	return null;
			}
	        
		}catch(Exception e)
		{
			System.out.println("Ex INSERT HIS : " + e);
		}
		
		
		
		
		
	 
		// UPDATE THE NEW SETTING
		try{
			 query = "UPDATE Devices_Enable SET Enable_v = '" + lf.getDevice_status().trim()  + "', Timeout_value_v='" + lf.getTimeout().trim() + "'  WHERE Device_Type_v = '" +  lf.getDevice_type().trim()+ "'";
			 System.out.println("Query  : " + query);
			 rs = db.executeUpdate(query);
			
			if(rs <= 0)
			{
				out.println("Data could not be saved. Please contact helpdesk");
	        	out.flush();
	        	db.closeCon();
	        	return null;
			}
	        
		}catch(Exception e)
		{
			System.out.println("Ex : " + e);
		}
		
		
		if(lf.getDevice_status().trim().equals("Y"))			
		{
		
				try{
						// SET ALL DEVICES SELECTION TO 'N'
					
					 query = "UPDATE Device_Details SET Selected_v = 'N' WHERE Device_Type_v = '" +  lf.getDevice_type().trim()+ "'";
					 System.out.println("Query  : " + query);
					 rs = db.executeUpdate(query);
					 
					
						if(rs <= 0)
						{
							out.println("Data could not be saved. Please contact helpdesk");
				        	out.flush();	
				        	db.closeCon();
				        	return null;
						}
				        
						
						// NOW SET THE SELECTED DEVICE TO 'Y'
						 System.out.println("Name  : " + lf.getDevice_name().trim());
						 System.out.println("Type  : " + lf.getDevice_type().trim());
						
						 query = "UPDATE Device_Details SET Selected_v = 'Y' WHERE Device_Name_v = '" + lf.getDevice_name().trim() + "' AND Device_Type_v = '" +  lf.getDevice_type().trim()+ "'";
						 System.out.println("Query  : " + query);
						 rs = db.executeUpdate(query);
						 
						 
						 
						if(rs <= 0)
						{
							out.println("Data could not be saved. Please contact helpdesk");
				        	out.flush();
				        	db.closeCon();
				        	return null;
						}
						
						
					}catch(Exception e)
					{
						System.out.println("Ex : " + e);
					}
				
				
				
				
				System.out.println("Status  : " + lf.getDevice_status().trim() );
				System.out.println("Type  : " + lf.getDevice_type().trim());
				System.out.println("Name  : " + lf.getDevice_name().trim());
			 
				
				String apptype=lf.getDevice_type().trim();
				String devicename=lf.getDevice_name().trim();
				
				
				
				try{
					
					
			        
					 ProcessBuilder pb = new ProcessBuilder("./removeapp.sh",apptype,devicename);
					 pb.directory(new File("/usr/local"));
					
					
				    java.lang.Process p = pb.start();
				
				    String line;			
				    BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
				  
					    
				        while ((line = r.readLine()) != null) {
				        	out.println(line);
				        	out.flush();
				        	
				        	
				        	System.out.println("Line : " + line);
				        	
				        }
				       
				      
				   
				        r.close();
				        
				        
				        
				}catch(Exception e)
				{
					System.out.println("Ex : " + e);
				}
				
				
				
				try{
					
					
			        
					 ProcessBuilder pb = new ProcessBuilder("./copyapp.sh",apptype,devicename);
					 pb.directory(new File("/usr/local"));
					
					
				    java.lang.Process p = pb.start();
				
				    String line;			
				    BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
				  
					    
				        while ((line = r.readLine()) != null) {
				        	out.println(line);
				        	out.flush();
				        	
				        	
				        	System.out.println("Line : " + line);
				        	
				        }
				       
				      
				   
				        r.close();
				        
				        
				        
				}catch(Exception e)
				{
					System.out.println("Ex : " + e);
				}
				
				
				
				
				
		}
	
	
		// UPDATE THE NEW SETTING
		try{
			 //query = "SELECT mobile_v FROM sms UPDATE Devices_Enable SET Enable_v = '" + lf.getDevice_status().trim()  + "', Timeout_value_v='" + lf.getTimeout().trim() + "'  WHERE Device_Type_v = '" +  lf.getDevice_type().trim()+ "'";
			 query = "SELECT mobile_v FROM sms";
			 System.out.println("Query  : " + query);
			 ResultSet resset = db.executeQuery(query);
			 String status = "";
			 String smsurl="";
			 
			 if(lf.getDevice_status().trim().equals("Y"))
				 status = " Enabled";
			 else
				 status = " disabled";
			 
			 
			 java.util.Date date = new java.util.Date();
			 String dd = new Timestamp(date.getTime()) + "";
			 String ip = getCurrentIp().toString();
			 ip = ip
					 .substring(ip.indexOf("/") + 1);
			 String msg = lf.getDevice_type().trim() + status + " in Kiosk : " + ip + " " + dd;
			 System.out.println("MSG : " + msg);
			 
			    
		        
		        
		        
			 while(resset.next())
			 {
				 
				smsurl="http://10.60.200.168/CMSSMS/JSP/sms/WebSMS.do?hmode=SMS_Rec_URL_CRIS&user=CMS&pass=CMS123&mobile=91" + resset.getString("mobile_v") + "&msg="+URLEncoder.encode(msg,"UTF-8");
				System.out.println("url : " + smsurl);
				excutePost(smsurl);
				 
			 }
			
			    
		}catch(Exception e)
		{
			System.out.println("Ex : " + e);
		}

		
	out.println("Data Saved Successfully");
	out.flush();	
	
	
	System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<  saveData   <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	System.out.println("\n\n\n\n\n");

	
return null;




}


public InetAddress getCurrentIp() {
    try {
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface
                .getNetworkInterfaces();
        while (networkInterfaces.hasMoreElements()) {
            NetworkInterface ni = (NetworkInterface) networkInterfaces
                    .nextElement();
            Enumeration<InetAddress> nias = ni.getInetAddresses();
            while(nias.hasMoreElements()) {
                InetAddress ia= (InetAddress) nias.nextElement();
                if (!ia.isLinkLocalAddress() 
                 && !ia.isLoopbackAddress()
                 && ia instanceof Inet4Address) {
                    return ia;
                }
            }
        }
    } catch (SocketException e) {
        e.printStackTrace();
    }
    return null;
}


public void excutePost(String targetURL) {
	  
	try{
		 URL yahoo = new URL(targetURL);
	     URLConnection yc = yahoo.openConnection();
	     BufferedReader in = new BufferedReader(
	                             new InputStreamReader(
	                             yc.getInputStream()));
	     String inputLine;

	     while ((inputLine = in.readLine()) != null) 
	         System.out.println(inputLine);
	     in.close();
	}catch(Exception e)
	{
		e.printStackTrace();
	}
	
}



public ActionForward addPeer(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception
{
	
	 System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  addPeer   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	
	
	
	LoginForm lf = (LoginForm) form;
	ArrayList peers = new ArrayList();
	ActionForward forward = new ActionForward();
	DBConnection db = new DBConnection();
	String query="";
	int rs = 0;

		
	 
		try{
			 query = "INSERT INTO peers VALUES('" + lf.getPeer_ip().trim()  + "')";
			 System.out.println("Query  : " + query);
			 rs = db.executeUpdate(query);
			
			if(rs <= 0)
			{
				// SEND ERROR - PENDING 
			}
	        
		}catch(Exception e)
		{
			System.out.println("Ex : " + e);
		}
		
		

		try{
			       
			
			
			
			ResultSet res = db.executeQuery("SELECT peer_ip_v FROM peers");
		        
			while(res.next())
			{
				peers.add(new org.apache.struts.util.LabelValueBean(res.getString("peer_ip_v").trim() , res.getString("peer_ip_v").trim()));
				//dev.add(rs.getString("Device_Name_v").trim());
				//System.out.println("Device " + rs.getString("Device_Name_v").trim());
				
			}
			
			lf.setPeers(peers);
		        
		}catch(Exception e)
		{
			System.out.println("Ex : " + e);
		}
		
		
		
		

	lf.setPeer_ip("");
	System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<  addPeer   <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	System.out.println("\n\n\n\n\n");
	
	forward = mapping.findForward("ManagePeers");
    return (forward);



}






public ActionForward removeMember(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception
{
	
	 System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  removeMember   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	
	
	
	LoginForm lf = (LoginForm) form;
	
	String numbers[] = lf.getSmsmobile();
	
	String selectvalue[] = lf.getSmsselectvalue();
	
	
	ActionForward forward = new ActionForward();
	DBConnection db = new DBConnection();
	String query="";
	String querypart="";
	int rs = 0;

	System.out.println("numbers  lENGTH: " + numbers.length);
	System.out.println("selectvalue  lENGTH: " + selectvalue.length);
	
	boolean commaflag = false;
	for(int i=0;i<selectvalue.length;i++)
	{
		System.out.println("Checked  : " + selectvalue[i]);
		if(selectvalue[i].equals("Y"))
		{ 
			if(commaflag)
				querypart += ",";			
			querypart += "'" + numbers[i] + "'";
			commaflag=true;
			 	
		}
		
	}
	System.out.println("querypart  : " + querypart);
	
	
		try{
			 query = "DELETE FROM sms WHERE mobile_v IN (" + querypart + ")";
			 System.out.println("Query  : " + query);
			 rs = db.executeUpdate(query);
			
			if(rs <= 0)
			{
				// SEND ERROR - PENDING 
			}
	        
		}catch(Exception e)
		{
			System.out.println("Ex : " + e);
		}
		
				
		
	System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<  removeMember   <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	System.out.println("\n\n\n\n\n");
	
	forward = mapping.findForward("firesms");
    return (forward);



}



public ActionForward addMember(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception
{
	
	 System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  addMember   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	
	
	
	LoginForm lf = (LoginForm) form;
	
	
	ActionForward forward = new ActionForward();
	DBConnection db = new DBConnection();
	String query="";
	
	int rs = 0;

	
	
		try{
			 query = "INSERT INTO sms VALUES('" + lf.getSms_name() + "','" + lf.getSms_mobile() + "','" + lf.getSms_designation() + "','ALL')";
			 System.out.println("Query  : " + query);
			 rs = db.executeUpdate(query);
			
			if(rs <= 0)
			{
				// SEND ERROR - PENDING 
			}
	        
		}catch(Exception e)
		{
			System.out.println("Ex : " + e);
		}
		
				
		
	System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<  addMember   <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	System.out.println("\n\n\n\n\n");
	
	forward = mapping.findForward("firesms");
    return (forward);



}








public ActionForward removePeer(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception
{
	
	 System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  removePeer   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	
	
	
	LoginForm lf = (LoginForm) form;
	ArrayList peers = new ArrayList();
	ActionForward forward = new ActionForward();
	DBConnection db = new DBConnection();
	String query="";
	int rs = 0;

		
	 
		try{
			 query = "DELETE FROM peers WHERE peer_ip_v='" + lf.getPeer_ip_select().trim()  + "'";
			 System.out.println("Query  : " + query);
			 rs = db.executeUpdate(query);
			
			if(rs <= 0)
			{
				// SEND ERROR - PENDING 
			}
	        
		}catch(Exception e)
		{
			System.out.println("Ex : " + e);
		}
		
		

		try{
			       
			
			
			
			ResultSet res = db.executeQuery("SELECT peer_ip_v FROM peers");
		        
			while(res.next())
			{
				peers.add(new org.apache.struts.util.LabelValueBean(res.getString("peer_ip_v").trim() , res.getString("peer_ip_v").trim()));
				//dev.add(rs.getString("Device_Name_v").trim());
				//System.out.println("Device " + rs.getString("Device_Name_v").trim());
				
			}
			
			lf.setPeers(peers);
		        
		}catch(Exception e)
		{
			System.out.println("Ex : " + e);
		}
		
		
		
		

	lf.setPeer_ip("");
	System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<  removePeer   <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	System.out.println("\n\n\n\n\n");
	
	forward = mapping.findForward("ManagePeers");
    return (forward);



}




public ActionForward loginpage(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
 throws Exception{
	
	System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>> LoginAction - loginpage >>>>>>>>>>>>>>>>>>>>>>");
	 ActionForward forward = new ActionForward();
	
	 LoginForm fb = (LoginForm) form;
	  System.out.println("Crew Id : " + fb.getCrewid());
	 fb.setPassword(null);
	 fb.setUsername(null);
	
	
	System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<< LoginAction - loginpage <<<<<<<<<<<<<<<<<<<<<<");
	  
    forward = mapping.findForward("welcome");
    return (forward);
}






public ActionForward changepassscreen(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
 throws Exception{
	
	System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>> LoginAction - changepassscreen >>>>>>>>>>>>>>>>>>>>>>");
	 ActionForward forward = new ActionForward();
	
	 LoginForm lf = (LoginForm) form;
	 lf.setPassword(null);
		lf.setOldpassword(null);
		lf.setRe_password(null);
	
	System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<< LoginAction - changepassscreen <<<<<<<<<<<<<<<<<<<<<<");
	  
    forward = mapping.findForward("CHANGEPASSWORD");
    return (forward);
}




public ActionForward configureNetwork(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
 throws Exception{
	
	System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>> LoginAction - configureNetwork >>>>>>>>>>>>>>>>>>>>>>");
	 ActionForward forward = new ActionForward();
	
	
	
	System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<< LoginAction - configureNetwork <<<<<<<<<<<<<<<<<<<<<<");
	  
    forward = mapping.findForward("CONFIGURENETWORK");
    return (forward);
}






public ActionForward Execute(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
 throws Exception{
	
	System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>> LoginAction - Execute 1>>>>>>>>>>>>>>>>>>>>>>");
	 ActionForward forward = new ActionForward();
	
	 LoginForm fb = (LoginForm) form;
	  System.out.println("Crew Id : " + fb.getCrewid());
	 
	 
	
	System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<< LoginAction - Execute <<<<<<<<<<<<<<<<<<<<<<");
	  
    forward = mapping.findForward("success_login");
    return (forward);
}





public ActionForward logout(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
 throws Exception{
	
	System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>> LoginAction - logout >>>>>>>>>>>>>>>>>>>>>>");
	 ActionForward forward = new ActionForward();
	 LoginForm fb = (LoginForm) form;
	 fb.setMessage("");
	
	
	
	System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<< LoginAction - logout <<<<<<<<<<<<<<<<<<<<<<");
	  
    forward = mapping.findForward("welcome");
    return (forward);
}








}
















