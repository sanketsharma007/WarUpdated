package com.cris.cms.image.action;


import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;






import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

import com.github.sarxos.webcam.Webcam;

public class Click extends Thread{
	
	private String crewid;
	
	public Click(String id)
	{
		this.crewid = id;
	}
	
	public void run()
	{
		
		
		try{
	        
			 //ProcessBuilder pb = new ProcessBuilder("/bin/sh","-c","streamer -f jpeg -o /var/www" + crewid + ".jpeg");  //DM is just a dummy value
			ProcessBuilder pb = new ProcessBuilder("/bin/sh", "-c", "streamer -f jpeg -o /var/www/" + crewid + "_temp.jpeg && chmod 777 /var/www/" + crewid + "_temp.jpeg"); 
			pb.directory(new File("/var/www"));		
		    pb.start();
		  
		     
		}catch(Exception e)
		{
			System.out.println("Camera Ex : " + e);
		
		}
		
		
		
//		try{
//			System.out.println("Camera Run : " );
//	        
//			
//			// get default webcam and open it
//		Webcam webcam = Webcam.getDefault();
//			
//
//			// STEP 3 OF ROTATE IMAGE
//			//webcam.setImageTransformer(this);
//			
//			webcam.open();
//
//			if(!webcam.isOpen())
//			{
//				return;
//			}
//			
//			
//			// get image
//			BufferedImage image = webcam.getImage();
//
//
//			//
//			// save image to PNG file
//			try{
//				ImageIO.write(image, "jpeg", new File("/var/www/" + crewid + ".jpeg"));	
//			}catch(Exception e)
//			{
//				System.out.println("CAM File Write Exception : " + e);
//			}
//			webcam.close();
//		  
//		     
//		}catch(Exception e)
//		{
//			System.out.println("Camera Ex : " + e);
//		
//		}
//		
		
		
		// COMPRESS THE IMAGE
		
//		try {
//			compress();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}
	
	
	@SuppressWarnings("resource")
	public void compress() throws FileNotFoundException, IOException
	{
		try {
		Thread.sleep(2000);
		String filename = "/var/www/" + crewid;
		
		 	File imageFile = new File(filename + "_temp.jpeg");

	        File compressedImageFile = new File(filename + ".jpeg");



	        InputStream inputStream = new FileInputStream(imageFile);

	        OutputStream outputStream = new FileOutputStream(compressedImageFile);



	        float imageQuality = 0.2f;



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
	        compressedImageFile.setExecutable(true);
		}
		catch(Exception ex)
		{
			System.out.println("Compress Issue" + ex);
			ex.printStackTrace();
		}
		
		
		
	}
	
	
	
	
	
	

}
