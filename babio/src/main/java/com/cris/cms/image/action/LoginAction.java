package com.cris.cms.image.action;




import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Vector;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.json.JSONArray;
import org.json.JSONObject;

import com.cris.cms.image.actionforms.LoginForm;
import com.cris.cms.image.utility.DBConnection;
import com.cris.cms.image.utility.DBConnectionRemote;
import com.github.sarxos.webcam.Webcam;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

// STEP 1 OF ROTATE IMAGE
//public class LoginAction extends DispatchAction implements WebcamImageTransformer{


public class LoginAction extends DispatchAction{
	
	//private final BufferedImageOp filter = new JHFlipFilter(JHFlipFilter.FLIP_90CW);
	
	
	
	public ActionForward turnOn(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		  System.out.println("Inside Turn On");
		   
		  ActionForward forward = new ActionForward();
		  LoginForm fb = (LoginForm) form;
		   
		   String ret = "";
		    //String[] command = {"CMD", "/C", "dir"};
		    ProcessBuilder probuilder = new ProcessBuilder("./ba","DUMMY","10");

		   
		    try {
		   
		    //You can set up your work directory
		    probuilder.directory(new File("/usr/local"));
		   
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
	
	
	
	
	




public ActionForward startBreath(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception
{

 System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  startBreath   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");


 PrintWriter out = response.getWriter();
 LoginForm lf = (LoginForm) form;
 String crewid = lf.getCrewid().trim();
 String crewstatus = lf.getCrewstatus();
 String signonid = lf.getSignonid();
 String ba_device="";
 DBConnection db = new DBConnection();
 //THREAD
 //Click clk=null;

 
 // GET THE TIMEOUT
 
 try {
	 
	 ResultSet rs = db.executeQuery("SELECT * FROM Devices_Enable A,Device_Details B WHERE A.Device_Type_v='BA' AND B.Device_Type_v='BA' AND Selected_v='Y'");
	 
	 if(rs.next())
	 {

		 lf.setTimeout(rs.getString("Timeout_value_v").trim() );
		 ba_device=rs.getString("Device_Name_v").trim();
	 }
	 else
		 lf.setTimeout("10");		// DEFAULT TIMEOUT 10 SEC
 } 
 catch(Exception e) 
 {
     e.printStackTrace();
	 System.out.println("Timeout Exception " + e);
	        
 }
 finally
 {
	 db.closeCon();
 }
 
 
 // GET THE CAMERA STATUS
 String camstatus = "N";

try {
	 
	 ResultSet rs = db.executeQuery("SELECT * FROM Devices_Enable WHERE Device_Type_v='CAM'");
	 
	 if(rs.next())
		 camstatus = rs.getString("Enable_v").trim();
	 
	 lf.setCamstatus(camstatus);
	 
	 
 } 
 catch(Exception e) 
 {
     e.printStackTrace();
	 System.out.println("Timeout Exception " + e);
	        
 }
 finally
 {
	 db.closeCon();
 }

//System.out.println("camstatus " + camstatus);




try{
	
	// FIND AND KILL PREVIOUS UNCOMPLETED ba PROCESS IF ANY -- THIS IS JUST TO BE SURE, 
	// BECAUSE PARALLEL ba PROCESSES ARE CAUSING PROBLEMS
	
	//click();
	 killProcess("ba");
	 ProcessBuilder pb = null;       
	 if(lf.getBarepeat().equals("true"))
		 pb = new ProcessBuilder("./ba","2",lf.getTimeout());
	 else
		 pb = new ProcessBuilder("./ba","1",lf.getTimeout());
	 
	 
	pb.directory(new File("/usr/local"));
	
	
    java.lang.Process p = pb.start();

    String line="";			
    BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
    boolean clicked = false;

    System.out.println("cHECK 1 : ");
    
    
    if(camstatus.equals("Y"))
    {
    	
    	if(ba_device.equalsIgnoreCase("QTEL"))
    	{
        	while (!(line.contains("&@") ) && (line = r.readLine()) != null) {
        		//System.out.println("Line : " + line);
            	out.println(line);
            	out.flush();
          
            	
            		try{
            			
            			//if(!clicked && line.contains("Analyzing"))
            			if(!clicked && line.contains("Blowing"))
            			{   
            				//CHANGES START
            				//clk = new Click(crewid);
            				//clk.start();
            				
            				click(crewid);
            				clicked = true;
            				//storeInLocalDB(crewid,crewstatus,signonid);
            			}
            			
            			        			
            			if(line.contains("Blow Failure"))
            			{
            				
            				clicked = false;
            				deleteImage();
            			}
            			
            			
            		}catch(NullPointerException e)
            	{
            			System.out.println("CAMERA ERROR: " + e);
            			out.println("CAMERA ERROR");
            			out.flush();
            			break;
            	}
            			
            		
            	//System.out.println("Line : " + line);
            	//result(line);
            }
    	}
    	else
    	{
        	while (!(line.contains("Exhale time") ) && (line = r.readLine()) != null) {
        		//System.out.println("Line : " + line);
            	out.println(line);
            	out.flush();
          
            	
            		try{
            			
            			//if(!clicked && line.contains("Analyzing"))
            			if((!clicked && line.contains("Blowing")) || (!clicked && line.contains("***************************************")))
            			{
            				//clk = new Click(crewid);
            				//clk.start();
            				
            				click(crewid);
            				clicked = true;
            				//storeInLocalDB(crewid,crewstatus,signonid);
            			}
            			
            			        			
            			if(line.contains("Blow Failure"))
            			{
            				
            				clicked = false;
            				deleteImage();
            			}
            			
            			
            		}catch(NullPointerException e)
            	{
            			System.out.println("CAMERA ERROR: " + e);
            			out.println("CAMERA ERROR");
            			out.flush();
            			break;
            	}
            			
            		
            	System.out.println("Line : " + line);
            	//result(line);
            }
    	}

      
    }
    //CAM STATUS='N'
    else
    {
    	if(ba_device.equalsIgnoreCase("KY8000_CMS"))
    	{
    		while (!(line.contains("calon") ) && (line = r.readLine()) != null) {
        		System.out.println("Line : " + line);
            	out.println(line);
            	out.flush();
            	//System.out.println("Line : " + line);
            	//result(line);
            }
    	}else if(ba_device.equalsIgnoreCase("QTEL"))
    	{
    		while (!(line.contains("&@") ) && (line = r.readLine()) != null) {
        		System.out.println("Line : " + line);
            	out.println(line);
            	out.flush();
            	//System.out.println("Line : " + line);
            	//result(line);
            }
    	}else
    	{
    		while (!(line.contains("Exhale time") ) && (line = r.readLine()) != null) {
        		System.out.println("Line : " + line);
            	out.println(line);
            	out.flush();
            	//System.out.println("Line : " + line);
            	//result(line);
            }
    	}
    	
      
    }
         
    System.out.println("cHECK 2 : ");
    
    
    out.println(" image64:");
    if(camstatus.equals("Y"))
    {
    	try {

			compress(crewid);
    		//if(clk != null)
    		//{
    		//	clk.join();
    		//}
    		
    		
    		
    		File file = new File("/var/www/" + crewid + ".jpeg");
    		/*
    		 * Reading a Image file from file system
    		 */
    		FileInputStream imageInFile;
    		
    		
    		if(file.exists())
    		{
    			imageInFile = new FileInputStream(file);
        		byte imageData[] = new byte[(int)file.length()];
        		imageInFile.read(imageData);
    		
        		
    		
    		
    		/*
    		 * Converting Image byte array into Base64 String 
    		 */
    		String imageDataString = Base64.encodeBase64URLSafeString(imageData);	
    		out.println(imageDataString);
    		imageInFile.close();
    		
    		
    		// JUST FOR TESTING
    		//saveImage(crewid,"1" , "2" , imageDataString);
    		}

        	
        	
        
    		
    		
    		
    		System.out.println("Image Successfully Manipulated!");
    	} catch (FileNotFoundException e) {
    		System.out.println("Image not found" + e);
    	} catch (IOException ioe) {
    		System.out.println("Exception while reading the Image " + ioe);
    	}
    }
	
	
    out.println(":image64ends:");
	out.flush();
	    
    System.out.println("cHECK 3 : ");
		
    r.close();
    out.close();
   
        
        
        
}catch(Exception e)
{
	System.out.println("Ex : " + e);
}

System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<  startBreath   <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
System.out.println("\n\n\n\n\n");


return null;




}




// STEP 2 OF ROTATE THE IMAGE
/*

@Override
public BufferedImage transform(BufferedImage image) {

// this will do rotation on image

return filter.filter(image, null);
}

*/



public void click(String crewid)
{
	
	System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  Click   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");


	
	
	try{
	        
		 //ProcessBuilder pb = new ProcessBuilder("/bin/sh","-c","streamer -f jpeg -o /var/www/" + crewid + "_temp.jpeg");  //DM is just a dummy value
		ProcessBuilder pb = new ProcessBuilder("/bin/sh", "-c", "streamer -f jpeg -o /var/www/" + crewid + "_temp.jpeg && chmod 777 /var/www/" + crewid + "_temp.jpeg");
		 pb.directory(new File("/var/www"));		
	     pb.start();
	     
	     
	}catch(Exception e)
	{
		System.out.println("Camera Ex : " + e);
	
	}
	
	
	
	

		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<  Click   <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");

	      
    
  }

//@SuppressWarnings("resource")
public void compress(String crewid) throws FileNotFoundException, IOException
{
		
		
		
		try{
			Thread.sleep(2000);
			
			
			String filename = "/var/www/" + crewid;
			
		 	File imageFile = new File(filename + "_temp.jpeg");

	        File compressedImageFile = new File(filename + ".jpeg");



	        InputStream inputStream = new FileInputStream(imageFile);

	        OutputStream outputStream = new FileOutputStream(compressedImageFile);



	        float imageQuality = 0.1f;



	        //Create the buffered image

	        BufferedImage bufferedImage = ImageIO.read(inputStream);



	        //Get image writers

	        Iterator<ImageWriter> imageWriters = ImageIO.getImageWritersByFormatName("jpeg");



	        if (!imageWriters.hasNext())

	            throw new IllegalStateException("Writers Not Found!!");



	        ImageWriter imageWriter = (ImageWriter) imageWriters.next();

	        ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(outputStream);

	        imageWriter.setOutput(imageOutputStream);



	        ImageWriteParam imageWriteParam = imageWriter.getDefaultWriteParam();



	        //Set the compress quality metrics

	        imageWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);

	        imageWriteParam.setCompressionQuality(imageQuality);



	        //Created image

	        imageWriter.write(null, new IIOImage(bufferedImage, null, null), imageWriteParam);



	        // close all streams

	        inputStream.close();

	        outputStream.close();

	        imageOutputStream.close();

	        imageWriter.dispose();
		
	        
	     // Set executable permission
	        //compressedImageFile.setReadOnly();
//	        File file = new File("/var/www/" + crewid);
//	        boolean isReadable = file.setReadable(true);
//	        boolean isWritable = file.setWritable(true);
//	        boolean isExecutable = file.setExecutable(true);
	        
	        ProcessBuilder pb1 = new ProcessBuilder("/bin/sh","-c","chmod 777 /var/www/" + crewid + ".jpeg");
			pb1.directory(new File("/var/www"));		
		    pb1.start();
		}
		catch(Exception ex)
		{
			System.out.println("Compress Issue" + ex);
			ex.printStackTrace();
		}
		
	
	
}



public void storeInLocalDB(String crewid, String crewstatus, String signonid)
{
	
	

	
	DBConnection db = new DBConnection();
	Connection conn = db.getConnection();
    String INSERT_PICTURE = "insert into BA_Data(crewid_v,crewstatus_v,SIGN_ON_NO_V,photo_b) values (?,?,?,?)";

    FileInputStream fis = null;
    PreparedStatement ps = null;
    try {
    
      File file = new File("/var/www/" + crewid + ".jpeg");
      
      if(file.exists())
      {
      fis = new FileInputStream(file);
      ps = conn.prepareStatement(INSERT_PICTURE);
      ps.setString(1, crewid);
      ps.setString(2, crewstatus);
      ps.setString(3, signonid);
      ps.setBinaryStream(4, fis, (int) file.length());
      ps.executeUpdate();      
      ps.close();
	  fis.close();
      }
    } catch(Exception e)
    {
    	System.out.println("Image Store Exception : " + e);
    }

	
}




public void saveImage(String crewid, String crewstatus, String signonid, String imgstr)
{
	
	

	try{
	DBConnectionRemote db = new DBConnectionRemote();
	Connection conn = db.getConnection("172.16.25.18");
	
	byte[] imgarr = Base64.decodeBase64(imgstr);
	ByteArrayInputStream bis = new ByteArrayInputStream(imgarr);
	
	
	
	
	
	
	System.out.println("FILE EXISTS ------------------------ 1: ");
	
	
    String INSERT_PICTURE = "update BA_Data set photo_b=? ";
    PreparedStatement ps = conn.prepareStatement(INSERT_PICTURE);
    
    ps.setBinaryStream(1, bis);
    int val = ps.executeUpdate();
    
    System.out.println("FILE EXISTS ------------------------ : 2 : " + val);
	}catch(Exception e ){
		e.printStackTrace();
		
	}
   

	
}




public void click_uvc(String crewid, String crewstatus, String signonid)
{
	
	
	
	
	// get default webcam and open it
	Webcam webcam = Webcam.getDefault();

	// STEP 3 OF ROTATE IMAGE
	//webcam.setImageTransformer(this);
	
	webcam.open();

	// get image
	BufferedImage image = webcam.getImage();


	//
	// save image to PNG file
	try{
		ImageIO.write(image, "PNG", new File("/var/www/test.png"));	
	}catch(Exception e)
	{
		System.out.println("CAM File Write Exception : " + e);
	}
	webcam.close();
	
	
	DBConnection db = new DBConnection();
	Connection conn = db.getConnection();
    String INSERT_PICTURE = "insert into BA_Data(crewid_v,crewstatus_v,SIGN_ON_NO_V,photo_b) values (?,?,?,?)";

    FileInputStream fis = null;
    PreparedStatement ps = null;
    try {
    
      File file = new File("/var/www/test.png");
      fis = new FileInputStream(file);
      ps = conn.prepareStatement(INSERT_PICTURE);
      ps.setString(1, crewid);
      ps.setString(2, crewstatus);
      ps.setString(3, signonid);
      ps.setBinaryStream(4, fis, (int) file.length());
      ps.executeUpdate();      
      ps.close();
		fis.close();
    } catch(Exception e)
    {
    	System.out.println("Image Store Exception : " + e);
    }
      
    
  }






public ActionForward initiateBA(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception
{
	
	 System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  initiateBA   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	
	
	 ActionForward forward = new ActionForward();
	
	// DELETE THE PREVIOUS BA IMAGE
	 
	 deleteImage();
		
		
		
	System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<  initiateBA   <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	System.out.println("\n\n\n\n\n");

	 
    forward = mapping.findForward("ba");
    return (forward);



}




public ActionForward initiateReRegistration(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception
{
	
	 System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  initiateReRegistration   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	
	
	 
	 ActionForward forward = new ActionForward();
	
	
	
	System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<  initiateReRegistration   <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	System.out.println("\n\n\n\n\n");

	 
    forward = mapping.findForward("reregcrew");
    return (forward);



}






public ActionForward reRegister(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception
{
	
	 System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  reRegister   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	
	
	 ActionForward forward = new ActionForward();
	 DBConnection db = new DBConnection();
	 
	 LoginForm lf = (LoginForm) form;
	 String crewid = lf.getCrewid().trim();
	 lf.setReregistration("true");
	 
	 System.out.println("Crew ID : " + crewid);
	 
	 int rowcount=0;
	 
	 try{
		 
		 rowcount = db.executeUpdate("DELETE FROM FP_Data WHERE crewid_v='" + crewid + "'");				
		 //System.out.println(rowcount);		
		 
	 }catch(Exception e)
	 {
		 System.out.println("Error : " + e);
	 }
	 finally
	 {		
		 db.closeCon();
	 }
	
	 
	
	System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<  reRegister   <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	System.out.println("\n\n\n\n\n");
	
	 
	forward = mapping.findForward("rereg");
    return (forward);



}





public ActionForward deleteFPData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception
{
	
	 System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  deleteFPData   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	
	
	 DBConnection db = new DBConnection();
	 PrintWriter out = response.getWriter();
	 LoginForm lf = (LoginForm) form;
	 String crewid = lf.getCrewid().trim();
	 lf.setReregistration("true");
	 
	 System.out.println("Crew ID : " + crewid);
	 
	 int rowcount=0;
	 
	 try{
		 
		 rowcount = db.executeUpdate("DELETE FROM FP_Data WHERE crewid_v='" + crewid + "'");	
		 
		 if(rowcount > 0)
			 out.println("success");
		 else
			 out.println("fail");
     	out.flush();
		 
	 }catch(Exception e)
	 {
		 System.out.println("Error : " + e);
	 }
	 finally
	 {		
		 db.closeCon();
	 }
	
	 
	
	System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<  deleteFPData   <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	System.out.println("\n\n\n\n\n");
	
	 
    return null;



}



public ActionForward initiateBio(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception
{
	
	 System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  initiateBio   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	
	
	 ActionForward forward = new ActionForward();
	 DBConnection db = new DBConnection();
	 DBConnectionRemote dbr;
	 Connection conn;
	 LoginForm lf = (LoginForm) form;
	 String crewid = lf.getCrewid().trim();
	 
	 System.out.println("Crew ID : " + crewid);
	 System.out.println("Reregistration : " + lf.getReregistration());
	 
	 
	 //Statement st = db.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	 ResultSet rs=null;
	 ResultSet rsr=null;
	 
	 
	 
	 //ResultSet rs = stmt.executeQuery("SELECT * FROM your_table");


		 
					
	 try{
		 rs = db.executeQuery("SELECT * FROM FP_Data WHERE crewid_v='" + crewid + "'");
		
		 
		 	 
		
		 if(rs.next())	// IF FP EXIST'S IN LOCAL DB
		 {
			 rs.last();
			 int count = rs.getRow();
			 
			 if(count == 2) // IF THERE ARE EXACTLY 2 RECORDS
			 {
				 rs.first();
				 System.out.println("Verification");
				 String finger = rs.getString("finger_v");
				 System.out.println("First Finger : " + finger);
				 lf.setFirst_finger(finger);
				 
				 rs.next();
				 finger = rs.getString("finger_v");
				 System.out.println("Second Finger : " + finger);
				 lf.setSecond_finger(finger);
				 
	 
				 
				 forward = mapping.findForward("biover");
			 }
			 else // IF THERE ARE NOT EXACTLY 2 RECORDS , DATA IS INCONSISTENT - DELETE EXISTING DATA AND GO FOR FRESH REGISTRATION
			 {
				 db.executeUpdate("DELETE FROM FP_Data WHERE crewid_v='" + crewid + "'");	
				 System.out.println("Registration 1");
				 forward = mapping.findForward("bioreg");
				 
			 }
			 
			
			 
			
		 }else if(!lf.getReregistration().equals("true"))		// IF FP DOESNT EXIST IN LOCAL DB , CHECK IN CENTRAL DB
		 {
			 
			 rs = db.executeQuery("SELECT peer_ip_v FROM peers");
			 
			 if(rs.next())  // IF CENTRAL SERVER IP IS CONFIGURED IN THIS THIN CLIENT
			 {
				 try{
								 
					 String output = getFPData(rs.getString("peer_ip_v"),crewid);
					 
					
					 
					 if(output.equals("[null]"))	// IF FP NOT FOUND ON CENTRAL SERVER - START REGISTRATION
					 {
						 System.out.println("WS returned null");
						 forward = mapping.findForward("bioreg");
					 }
					 else							// IF FP FOUND ON CENTRAL SERVER
					 {
						 
						 	// PARSE THE JASON OUTPUT
						 
								 parseJSONOutput(output,crewid);
							     
								 lf.setFirst_finger(finger_no[0]);
								 lf.setSecond_finger(finger_no[1]);
								 
								 
								 forward = mapping.findForward("biover");
						 
					 }
						 
								 
					 } 
					 catch(Exception e) 
					 {
					     
						 e.printStackTrace();
						 System.out.println("could not connect to remote");
						 forward = mapping.findForward("bioreg");
					        
					 } 	 
								 
								 
						 
						 
						 
			 }else	// IF CENTRAL SERVER IP IS NOT CONFIGURED IN THIS THIN CLIENT
			 {
				
				 System.out.println("Centeral Server not configured");
				 forward = mapping.findForward("bioreg");
			 }
			 
			
		 }else	// IF IT IS A CASE OF RE-REGISTRATION
		 {
				
			 System.out.println("Re - Registration ");
			 forward = mapping.findForward("bioreg");
		 }
		 
	 }catch(Exception e)
	 {
		 System.out.println("Error : " + e);
	 }
	 finally
	 {
		 rs.close();
		 db.closeCon();
		 
	 }
	
	 
	
	System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<  initiateBio   <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	System.out.println("\n\n\n\n\n");
	
	 
    
    return (forward);



}

String[] finger_no = new String[2];
String[] finger_print = new String[2];


public void parseJSONOutput(String output, String crewid)
{
	 DBConnection db = new DBConnection();
	 Connection conn;
	 
	 // PARSE THE JASON OUTPUT
	 
	 try {
	 //System.out.println("Output from Server .... \n");
	 //System.out.println(output);
	 output =  "{\"fpdata\":" + output + "}";
	 
	 //System.out.println(output);
	 JSONObject jsonobject = new JSONObject(output);		
	 JSONArray tsmarray = (JSONArray) jsonobject.get("fpdata");
	 
	 
	 
	 // EXTRACT THE VALUES
	    finger_no[0] = tsmarray.getJSONObject(0).getString("left_fingre_no");
	    finger_no[1] = tsmarray.getJSONObject(0).getString("right_fingre_no");
	    
	    finger_print[0] = tsmarray.getJSONObject(0).getString("left_print");
	    finger_print[1]	= tsmarray.getJSONObject(0).getString("right_print");
	    
	    
	    byte[] imgarr = null;
	    ByteArrayInputStream bis = null;
	    
	    
	    conn = db.getConnection();  // LOCAL DB
		PreparedStatement ps = null;
		String query = "insert into FP_Data(crewid_v ,finger_v ,fingerprint_B,Device_Name_v,synched) values (?, ?, ?, ?, ?)";
		 
		 
		 
			 
			 for(int i=0; i<2; i++){
			    	System.out.println("Finger No : " + finger_no[i]);
			    	System.out.println("Finger Pr : " + finger_print[i]);
			    	
			    	imgarr = Base64.decodeBase64(finger_print[i]);
				    bis = new ByteArrayInputStream(imgarr);
					
					
			    	
			    	conn.setAutoCommit(false);
			        
			        ps = conn.prepareStatement(query);
			        ps.setString(1, crewid);
			        ps.setString(2, finger_no[i]);
			        ps.setBinaryStream(3, bis);
			        ps.setString(4, "Bio");
			        ps.setString(5, "Y");
			        ps.executeUpdate();
			        conn.commit();
			       
			        imgarr = null;
			        bis = null;
			        
			    }
			 
			 ps.close();
		     conn.close();
		
		 } 
		 catch(Exception e)
		 {
			 System.out.println("Error : " + e);
		 }
		 finally 
		 {
		       
		        
		 } 
		 
	
}





public String getFPData(String webservice_ip, String crewid)
{
	String output="";
	
	 try{
		 System.out.println("Connecting to WS " );
		 
		 String myurl = "http://" + webservice_ip + "/cmsfpws/getfpdata/'" + crewid +"'" ;                                
		 Client client = Client.create();
		 WebResource webresource = client.resource(myurl);					 
		 ClientResponse response1 = webresource.accept("application/json").get(ClientResponse.class);
		 
		 if(response1.getStatus() != 200)
		 {
			 return "failed";
			 
		 }
		 
		 
		 output = response1.getEntity(String.class);
		 
		 System.out.println("Server Output : " + output);

		 
		 

					 
		 } 
		 catch(Exception e) 
		 {
		     
			 e.printStackTrace();
			 System.out.println("could not connect to remote");
			 
		        
		 } 	 
	
	 return output;
}




	
public void result(String res)
{
	System.out.println("Check 1 : " + res);

	if(res.contains("mg/100ml"))
	{
		//System.out.println("Check 2 : " + res);
		String val = res.substring(0, res.indexOf("mg/100ml"));
		//System.out.println("result : " + val);
	}
	
	
	
}
	






public ActionForward BioReg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception
{
	
	 System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  BioReg   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	
	
	PrintWriter out = response.getWriter();
	DBConnection db = new DBConnection();
	
	
	 LoginForm lf = (LoginForm) form;
	 String crewid = lf.getCrewid().trim();
	 
	 
	 System.out.println("Crew ID : " + crewid);
	 String finger = lf.getFinger().trim();
	 System.out.println("Finger : " + finger);
	 String reregistration = lf.getReregistration();
	 System.out.println("reregistration : " + reregistration);
	 try {
		 
			 ResultSet rs = db.executeQuery("SELECT * FROM Devices_Enable WHERE Device_Type_v='BIO'");
			 
			 if(rs.next())
				 lf.setTimeout(rs.getString("Timeout_value_v").trim() + "000");
			 else
				 lf.setTimeout("10000");		// DEFAULT TIMEOUT 10 SEC
		 } 
		 catch(Exception e) 
		 {
		     e.printStackTrace();
			 System.out.println("Timeout Exception " + e);
			        
		 }finally
		 {
			 db.closeCon();
		 }
	  	 
	 
	
	
	try{
		
		killProcess("bio");
		String dummy_value="N"; // USED FOR DIFFERENCIATING THE RE-REGISTRATION CASE
		
		if(reregistration.equals("true"))
		{
			dummy_value = "R"; // SPECIFIES THE RE-REGISTRATION CASE
		}
	        
		 ProcessBuilder pb = new ProcessBuilder("./bio","R",crewid,finger,dummy_value,lf.getTimeout());  //DM is just a dummy value
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
	}finally
	 {
		out.close();
	 }

	
	
	System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<  BioReg   <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	System.out.println("\n\n\n\n\n");


return null;




}


public ActionForward BioVer(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception
{
	
	 System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  BioVer   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	
	
	
	 ResultSet rs=null;
	 LoginForm lf = (LoginForm) form;
	 String crewid = lf.getCrewid().trim();
	 DBConnection db = new DBConnection();
	 
	 System.out.println("Crew ID : " + crewid);
	 String first_finger = lf.getFirst_finger().trim();
	 System.out.println("First Finger : " + first_finger);
	
	 
	 String second_finger = lf.getSecond_finger().trim();
	 System.out.println("Second Finger : " + second_finger);
	
	
	 PrintWriter out = response.getWriter();
	
	 
	 try {
		 
		 rs = db.executeQuery("SELECT * FROM Devices_Enable WHERE Device_Type_v='BIO'");
		 
		 if(rs.next())
			 lf.setTimeout(rs.getString("Timeout_value_v").trim() + "000");
		 else
			 lf.setTimeout("10000");		// DEFAULT TIMEOUT 10 SEC
	 } 
	 catch(Exception e) 
	 {
	     e.printStackTrace();
		 System.out.println("Timeout Exception " + e);
		        
	 }finally
	 {
		 db.closeCon();
	 }
  	  	 
 

	 
	 
	 
	
	try{
		        
		killProcess("bio");
		 ProcessBuilder pb = new ProcessBuilder("./bio","V",crewid,first_finger,second_finger,lf.getTimeout());
		 pb.directory(new File("/usr/local"));
		
		
	    java.lang.Process p = pb.start();
	
	    String line;			
	    BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
	  
		    
	    while ((line = r.readLine()) != null) {
	    	
        		out.println(line);
        		out.flush();
        		
        		
        		if(line.contains("<<NO MATCH>>")) // IF VERIFICATION FAILS, DOWNLOAD THE LATEST FINGRE PRINTS
        		{
        			
        			 rs = db.executeQuery("SELECT peer_ip_v FROM peers");
        			 
        			 if(rs.next())  // IF CENTRAL SERVER IP IS CONFIGURED IN THIS THIN CLIENT
        			 {
        				 try{
        								 
        					 String output = getFPData(rs.getString("peer_ip_v"),crewid);
        					 
        					 if(!output.equals("[null]"))	// IF FP NOT FOUND ON CENTRAL SERVER - START REGISTRATION
        					 {
        						// PARSE THE JASON OUTPUT
								 parseJSONOutput(output,crewid);
							     
        					 }
        					
        					 } 
        					 catch(Exception e) 
        					 {        					     
        						 e.printStackTrace();
        						 System.out.println("could not connect to remote");        					        
        					 } 	 
        							        			
        			 }
        		}
        		System.out.println("Line : " + line);
	    }
	   
	        r.close();
	        
	        
	        
	}catch(Exception e)
	{
		System.out.println("Ex : " + e);
	}finally
	 {
		 out.close();
	 }
 	 
	
	
	System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<  BioVer   <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	System.out.println("\n\n\n\n\n");


return null;




}





public ActionForward Execute(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
 throws Exception{
	
	System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>> LoginAction - Execute1 >>>>>>>>>>>>>>>>>>>>>>");
	 ActionForward forward = new ActionForward();
	
	 LoginForm fb = (LoginForm) form;
	  System.out.println("Crew Id : " + fb.getCrewid());
	 
	  
	  DBConnection db = new DBConnection();
		 
		 int rowcount=0;
		 
		 try{
			 
			 rowcount = db.executeUpdate("DELETE FROM FP_Data WHERE crewid_v='TEST'");				
			 
			
			 
		 }catch(Exception e)
		 {
			 System.out.println("Error : " + e);
		 }
		 finally
		 {		
			 db.closeCon();
		 }
		
		 
		 
	
	
	System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<< LoginAction - Execute <<<<<<<<<<<<<<<<<<<<<<");
	  
    forward = mapping.findForward("welcome");
    return (forward);
}










public ActionForward isBioEnabled(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception
{

 System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  isBioEnabled   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

 response.setContentType("text/javascript");
 PrintWriter out = response.getWriter();
 String jsonPoutput ="";
 String callBackJavaScripMethodName="";
 DBConnection db = new DBConnection();  



try{

    		String line="N";			
    		callBackJavaScripMethodName = request.getParameter("callback");
    		
    		
    		  		
    		String query = "SELECT * FROM Devices_Enable WHERE Device_Type_v='BIO'";    		
    		
    		ResultSet rs = db.executeQuery(query);
    		
    		while(rs.next())
    		{
    			line = rs.getString("Enable_v");
    			
    			jsonPoutput += "{\"data\":'" + line + "'}";
    			jsonPoutput = callBackJavaScripMethodName + "("+ jsonPoutput + ");";
    			System.out.println("jsonPoutput : " + jsonPoutput);
    		}
       
    		 out.println(jsonPoutput);
        	 out.flush();
        	
        	
        	System.out.println("Line : " + line);
        	result(""+line);
      
       
      
   
        	rs.close();
        
        
        
}catch(Exception e)
{
	System.out.println("Ex : " + e);
	jsonPoutput += "{\"data\":'N'}";
	jsonPoutput = callBackJavaScripMethodName + "("+ jsonPoutput + ");";
	 out.println(jsonPoutput);
	 out.flush();
	
	
}finally 
{
	 db.closeCon();
	 out.close();
}
	 


System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<  isBioEnabled   <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
System.out.println("\n\n\n\n\n");


return null;




}








public ActionForward isBAEnabled(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception
{

 System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  isBAEnabled   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

 response.setContentType("text/javascript");
 PrintWriter out = response.getWriter();
 String jsonPoutput ="";
 String callBackJavaScripMethodName ="";
 DBConnection db = new DBConnection();    	



try{

    		String line="N";			
    	    callBackJavaScripMethodName = request.getParameter("callback");
    		
    		
    			
    		String query = "SELECT * FROM Devices_Enable WHERE Device_Type_v='BA'";    		
    		
    		ResultSet rs = db.executeQuery(query);
    		
    		while(rs.next())
    		{
    			line = rs.getString("Enable_v");
    			
    			jsonPoutput += "{\"data\":'" + line + "'}";
    			jsonPoutput = callBackJavaScripMethodName + "("+ jsonPoutput + ");";
    			System.out.println("jsonPoutput : " + jsonPoutput);
    		}
       
    		 out.println(jsonPoutput);
        	 out.flush();
        	
        	
        	System.out.println("Line : " + line);
        	result(""+line);
      
       
      
   
        	rs.close();
        
        
        
}catch(Exception e)
{
	System.out.println("Ex : " + e);
	jsonPoutput += "{\"data\":'N'}";
	jsonPoutput = callBackJavaScripMethodName + "("+ jsonPoutput + ");";
	 out.println(jsonPoutput);
	 out.flush();
}finally
{
	 db.closeCon();
	 out.close();
}


System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<  isBAEnabled   <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
System.out.println("\n\n\n\n\n");


return null;




}




public ActionForward isCAMEnabled(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception
{

 System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  isCAMEnabled   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

 response.setContentType("text/javascript");
 PrintWriter out = response.getWriter();
 String jsonPoutput ="";
 String callBackJavaScripMethodName ="";
 DBConnection db = new DBConnection();    	



try{

    		String line="N";			
    	    callBackJavaScripMethodName = request.getParameter("callback");
    		
    		
    			
    		String query = "SELECT * FROM Devices_Enable WHERE Device_Type_v='CAM'";    		
    		
    		ResultSet rs = db.executeQuery(query);
    		
    		while(rs.next())
    		{
    			line = rs.getString("Enable_v");
    			
    			jsonPoutput += "{\"data\":'" + line + "'}";
    			jsonPoutput = callBackJavaScripMethodName + "("+ jsonPoutput + ");";
    			System.out.println("jsonPoutput : " + jsonPoutput);
    		}
       
    		 out.println(jsonPoutput);
        	 out.flush();
        	
        	
        	System.out.println("Line : " + line);
        	result(""+line);
      
       
      
   
        	rs.close();
        
        
        
}catch(Exception e)
{
	System.out.println("Ex : " + e);
	jsonPoutput += "{\"data\":'N'}";
	jsonPoutput = callBackJavaScripMethodName + "("+ jsonPoutput + ");";
	 out.println(jsonPoutput);
	 out.flush();
}finally
{
	 db.closeCon();
	 out.close();
}


System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<  isCAMEnabled   <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
System.out.println("\n\n\n\n\n");


return null;




}





public ActionForward getLEDstatus(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception
{

 //System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  getLEDstatus   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

 response.setContentType("text/javascript");
 PrintWriter out = response.getWriter();
 String jsonPoutput ="";
 String callBackJavaScripMethodName="";
 DBConnection db= new DBConnection();	
 ResultSet rs;

 
 int res=1000000;
 
try{
         
	callBackJavaScripMethodName = request.getParameter("callback");          
     
     

               
         rs = db.executeQuery("SELECT B.DEVICE_TYPE_V, B.IDENTIFIER_V, A.ENABLE_V FROM\n" +
                              "Devices_Enable A, Device_Details B\n" +
                              "WHERE A.DEVICE_TYPE_V = B.DEVICE_TYPE_V and Selected_v='Y'");	 	
		
			
         while(rs.next())
         {
             if(rs.getString("DEVICE_TYPE_V").equals("BA"))            // IF BA DEVICE 
             {
               if(rs.getString("ENABLE_V").equals("Y"))                // IF BA IS ENABLED
               {
                   if(executeCommand(rs.getString("IDENTIFIER_V")))    // IF BA IS CONNECTED
                       res+=110000;
                   else
                       res+=100000;
                   
                   
               }                 
               
             }
             else if(rs.getString("DEVICE_TYPE_V").equals("BIO"))       // IF BIO DEVICE 
             {
               if(rs.getString("ENABLE_V").equals("Y"))                // IF BIO IS ENABLED
               {
                   if(executeCommand(rs.getString("IDENTIFIER_V")))    // IF BIO IS CONNECTED
                       res+=1100;
                   else
                       res+=1000;
               }
               
             }
             else 												       // IF CAM DEVICE 
             {
               if(rs.getString("ENABLE_V").equals("Y"))                // IF CAM IS ENABLED
               {
                   if(executeCommand(rs.getString("IDENTIFIER_V")))    // IF CAM IS CONNECTED
                       res+=11;
                   else
                       res+=10;
               }
               
             }
                 
               
         }
        
		rs.close();				
		db.closeCon();
   } 
catch(Exception e) 
{     
	
        e.printStackTrace();
        System.out.println("could not connect to remote");
        
}finally
{
	 db.closeCon();
	
} 	
     

jsonPoutput += "{\"data\":'" + res + "'}";
jsonPoutput = callBackJavaScripMethodName + "("+ jsonPoutput + ");";
out.println(jsonPoutput);
out.flush();
//System.out.println("jsonPoutput : " + jsonPoutput);


//System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<  getLEDstatus   <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
//System.out.println("\n\n\n\n\n");


return null;




}




private boolean executeCommand(String arg) {

    
    boolean connected=false;
	StringBuffer output = new StringBuffer();

	Process p=null;
	BufferedReader reader = null;
	try {
		p = Runtime.getRuntime().exec("lsusb");
		p.waitFor();
		reader =  new BufferedReader(new InputStreamReader(p.getInputStream()));

                    String line = "";			
		while ((line = reader.readLine())!= null) {
                        if(line.contains(arg))
                        {
                            connected=true;
                            break;
                        }
                                          
			output.append(line + "\n");
		}
		reader.close();
	} catch (Exception e) {
		e.printStackTrace();
	}finally
	{		
		p.destroy();
		
	} 	
            
	return connected;

}






public ActionForward killProcess(String process)throws Exception
{

 System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  killProcess   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

 
 ProcessBuilder pb = null;
 Process pr = null;	
	try {
	    Vector<String> commands = new Vector<String>();
	    commands.add("pidof");
	    commands.add(process);
	    pb = new ProcessBuilder(commands);
	    
	    pr = pb.start();
	    pr.waitFor();
	    if (pr.exitValue() == 0){ 
	    BufferedReader outReader = new BufferedReader(new InputStreamReader(pr.getInputStream()));
	    for (String pid : outReader.readLine().trim().split(" ")) {
	        //log.info("Killing pid: "+pid);
	        Runtime.getRuntime().exec("kill " + pid).waitFor();
	    }
	    outReader.close();
	    
	    }
	   
	    
	} catch (Exception e) {
	    e.printStackTrace();
	}finally
	{		
		pr.destroy();
		
	} 	



System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<  killProcess   <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
System.out.println("\n\n\n\n\n");


return null;




}


public boolean deleteImage()
{
	// DELETE THE PREVIOUS BA IMAGE
	try{
		
	        
		 ProcessBuilder pb = new ProcessBuilder("/bin/sh","-c","rm -f *.jpeg");  //DM is just a dummy value
		 pb.directory(new File("/var/www"));
		
		
	    java.lang.Process p = pb.start();
	
	    String line;			
	    BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        r.close();
	        
	}catch(Exception e)
	{
		System.out.println("Ex : " + e);
		return false;
	}
	
	return true;
	

}




}
















