
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>

<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html style="height: 100%; width:100%;">
<head>
<title>Hello World</title>
<script type="text/javascript" src="scripts/jquery.js"></script>
<script language="javascript" type="text/javascript" src="scripts/datePicker.js"></script>
<style>/* Stylesheet 1: */




.button {
   border-top: 1px solid #96d1f8;
   background: #81c1eb;
   background: -webkit-gradient(linear, left top, left bottom, from(#3e779d), to(#81c1eb));
   background: -webkit-linear-gradient(top, #3e779d, #81c1eb);
   background: -moz-linear-gradient(top, #3e779d, #81c1eb);
   background: -ms-linear-gradient(top, #3e779d, #81c1eb);
   background: -o-linear-gradient(top, #3e779d, #81c1eb);
   padding: 20px 40px;
   -webkit-border-radius: 29px;
   -moz-border-radius: 29px;
   border-radius: 29px;
   -webkit-box-shadow: rgba(0,0,0,1) 0 1px 0;
   -moz-box-shadow: rgba(0,0,0,1) 0 1px 0;
   box-shadow: rgba(0,0,0,1) 0 1px 0;
   text-shadow: rgba(0,0,0,.4) 0 1px 0;
   color: white;
   font-size: 15px;
   font-family: Georgia, Serif;
   text-decoration: none;
   vertical-align: middle;
   width: 300px;
   height: 50px;
   }
.button:hover {
   border-top-color: #28597a;
   background: #28597a;
   color: #ccc;
   }
.button:active {
   border-top-color: #2a4c63;
   background: #2a4c63;
   }
   
   
   
a {
    color: #000000;
    text-decoration: underline;
}

a:hover {
    text-decoration: none;
}

</style>
</head>
<body style="height: 100%; width:100%;">

<html:form action="welcome" style="height: 100%; width:100%;">
<html:hidden property="crewid" />
<html:hidden property="result" />     
  <table   style="height:100%; width:70%;"  border="0" align="center" >
		<tr style="background-color: #84c754;">					
		<td style="height:10%; width:100%;" align="center" >
		
			<table   style="height:100%; width:100%; " border="0" align="center" cellpadding="1">
				<tr style="background-color: #84c754;">					
					
					<td style="height:10%; width:60%;"align="center" >
								<h2>BA Report</h2> 			
					</td>
					
				</tr>	
			</table>
					
		</td>					
		</tr>	
		<tr >					
		<td style="height:20%; width:100%;" align="center" >
		
			<table   style="height:100%; width:100%; " border="0" align="center" cellpadding="1">
			
			
				<tr >					
					<td width="25%" height="100%" align="center" class="fontdark">From Date 
					<html:text name="ReportForm" property="fromdate" readonly="true" maxlength="12" size="12" />
					<a href="#"><img src="images/bluecal.gif" alt="Date Picker" width="16"
                                    height="17" border="0" onclick="javascript:NewCalForDate('fromdate','ddmmyyyy',true,24)" /></a>
                    </td>
					<td width="25%" height="100%" align="center" class="fontdark">To date
					<html:text name="ReportForm" property="todate" readonly="true" maxlength="12" size="12" />
					<a href="#"><img src="images/bluecal.gif" alt="Date Picker" width="16"
                                    height="17" border="0" onclick="javascript:NewCalForDate('todate','ddmmyyyy',true,24)" /></a>
                    </td>
				</tr>	
				<tr >					
					<td width="25%" height="100%" align="center" class="fontdark" colspan="2">Status
						<html:radio name="ReportForm" property="status" value="all" /> All 
						<html:radio name="ReportForm" property="status" value="passed" /> Passed
						<html:radio name="ReportForm" property="status" value="failed" /> failed
                    </td>
				</tr>	
				<tr>
					<td style="height:10%; width:20%;"align="center" colspan="2">
								&nbsp;
					</td>	
				</tr>
			</table>
					
		</td>					
		</tr>	
		
		
		<tr >					
			<td align="center" >
				 <div id="report"></div>
		 
			</td>					
		</tr>	
		<tr style="width:100%;">					
			<td  valign="top" align="center" >										
				<html:button property="method" value="Generate" styleClass="button" onclick="validate()" />				
			</td>					
		</tr>	
  </table>    
	 


  

<script>




function trim(str)
{
   return str.replace(/^\s*|\s*$/g,"");
}

function validate()
{
	if(trim(document.forms[0].fromdate.value) == "")
		alert("Please Enter From Date");
	else if(trim(document.forms[0].todate.value) == "")
		alert("Please Enter To Date");
	else
		generate();
}




function generate()
{
	var st;
	for(i=0;i<3;i++)
		{
		if(document.forms[0].status[i].checked)
			st = document.forms[0].status[i].value;
		
		}
	

	
		var url="report.do?method=getBAReport&fromdate=" + document.forms[0].fromdate.value + "&todate=" + document.forms[0].todate.value + "&status=" + st  ;
						if (window.XMLHttpRequest){ // Non-IE browsers
							reqFeature = new XMLHttpRequest();
						try{
							reqFeature.open("GET", url, true);
							}catch (e){
							alert(e);
							}
							reqFeature.onreadystatechange = receiveOutput;
							reqFeature.send(null);
							}
							else if (window.ActiveXObject){ // IE
							reqFeature = new ActiveXObject("Microsoft.XMLHTTP");
							if (reqFeature){
							//alert('IE');
							reqFeature.open("GET", url, true);
							reqFeature.onreadystatechange = receiveOutput;
							reqFeature.send(null);
						}
					}	
	
	
}


function receiveOutput(){

			var status;
			try{
				status=reqFeature.status;
				if (reqFeature.readyState == 4){ // Complete					
						if (reqFeature.status == 200)
						{ // OK response
							xmlhtp = reqFeature.responseText;	
							
							document.getElementById("report").innerHTML=xmlhtp;
								
						}
					}
				}
				catch(e)
				{
					status="Not found";
				}
		}










</script>


 </html:form>
</body>
</html>

