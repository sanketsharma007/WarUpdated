package com.cris.cms.image.action;




import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.cris.cms.image.actionforms.ReportForm;
import com.cris.cms.image.utility.DBConnection;

public class ReportAction extends DispatchAction{
	
	



public ActionForward BA(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception
{
	
	 System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  BA   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	
	ActionForward forward = new ActionForward();	
	ReportForm lf = (ReportForm) form;	
	lf.setStatus("all");
	lf.setFromdate("");
	lf.setTodate("");
		
	forward = mapping.findForward("BAReportPage");
	
	System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<  BA   <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	

	 return (forward);


}





public ActionForward getBAReport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception
{
	
	 System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  getBAReport   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	
	
	PrintWriter out = response.getWriter();
	ReportForm lf = (ReportForm) form;	
	ActionForward forward = new ActionForward();
	DBConnection db = new DBConnection();
	String query="";
	String output="";
	query = "SELECT * FROM BA_Data ";
	
	ResultSet rs = null;

		System.out.println("Status  : " + lf.getStatus());
		System.out.println("From Date  : " + lf.getFromdate() );
		System.out.println("To Date  : " + lf.getTodate());
		
		
		
		if(!lf.getStatus().equals("all"))
		{
			if(lf.getStatus().equals("passed"))
				query += "WHERE Result_i = 0 ";
			if(lf.getStatus().equals("failed"))
				query += "WHERE Result_i > 0 ";
			
				query += " AND DATE_FORMAT(Date_Time_d,'%Y-%m-%d') BETWEEN STR_TO_DATE('" + lf.getFromdate() + "','%d-%m-%Y') AND STR_TO_DATE('" + lf.getTodate() + "','%d-%m-%Y')";
			
		}else if(!lf.getFromdate().equals("") && !lf.getTodate().equals(""))
		{			
			query += " WHERE DATE_FORMAT(Date_Time_d,'%Y-%m-%d') BETWEEN STR_TO_DATE('" + lf.getFromdate() + "','%d-%m-%Y') AND STR_TO_DATE('" + lf.getTodate() + "','%d-%m-%Y')";
		}
		
		
		output = "<table style='width:100%;'>";
		
		 output += "<tr style='background-color: grey;' style='width:100%;' >";
		 output += "<td colspan='3' align='center'>" + lf.getFromdate() + "    To    " + lf.getFromdate() + "</td>";
		
		 output += "</tr>";
	 
		 
		 output += "<tr style='background-color: grey;'>";
		 output += "<td>Crew Id</td>";
		 output += "<td>Reading</td>";
		 output += "<td>Date</td>";
		 output += "</tr>";
	 
		try{
			 
			 System.out.println("Query  : " + query);
			 rs = db.executeQuery(query);
			
			 
			 while(rs.next())
			 {
				 //System.out.println("crew  : " + rs.getString("crewid_v"));
				 output += "<tr style='background-color: lightgrey;'>";
				 output += "<td>" + rs.getString("crewid_v") + "</td>";
				 output += "<td>" + rs.getString("Result_i") + "</td>";
				 output += "<td>" + rs.getString("Date_Time_d") + "</td>";
				 output += "</tr>";
				 
			 }
			 
			 output += "</table>";	 
			 System.out.println("output  : " + output);
			 
			
		}catch(Exception e)
		{
			System.out.println("Ex : " + e);
		}
		
		
		
	out.println(output);
	out.flush();	
	
	lf.setFromdate("");
	lf.setTodate("");
	System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<  getBAReport   <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	System.out.println("\n\n\n\n\n");


return null;




}



	
	
	










}
















