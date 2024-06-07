
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>

<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html style="height: 100%; width:100%;">
<head>
<title>CMS Admin</title>
<script type="text/javascript" src="scripts/jquery.js"></script>
<style>/* Stylesheet 1: */




.button {
   border-top: 1px solid #96d1f8;
   background: #81c1eb;
   background: -webkit-gradient(linear, left top, left bottom, from(#3e779d), to(#81c1eb));
   background: -webkit-linear-gradient(top, #3e779d, #81c1eb);
   background: -moz-linear-gradient(top, #3e779d, #81c1eb);
   background: -ms-linear-gradient(top, #3e779d, #81c1eb);
   background: -o-linear-gradient(top, #3e779d, #81c1eb);
   padding: 5px 10px;
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
   width: 250px;
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
  <table   style="height:100%; width:100%;"  border="0" align="center" >
		<tr style="background-color: #84c754;">					
		<td style="height:10%; width:100%;" align="center" >
		
			<table   style="height:100%; width:100%; " border="0" align="center" cellpadding="1">
				<tr style="background-color: #84c754;">					
					<td style="height:10%; width:20%;"align="center" >
								CMS Image Ubuntu-Version 22.04 	
					</td>	
					<td style="height:10%; width:60%;"align="center" >
								<h2>Administration</h2> 			
					</td>
					<td style="height:10%; width:20%;"align="center" >
								
					</td>		
				</tr>	
			</table>
					
		</td>					
		</tr>	
		<tr style="height:90%; width:100%;">					
			<td align="center" >
				 <table   style="width:100%;height:100%" border="0" align="center" >
					
					<tr style="height:70%">
						<td style="width:20%;height:100%" valign="top" align="center" >
						
						
							<table   style="width:100%;" border="0" align="center" >					
								<tr >
									<td  valign="top" align="center" >										
									 <html:button property="method" value="Biometric" styleClass="button" onclick="bioConfig()" />				
									</td>	
								</tr>
								<tr >
									<td  valign="top" align="center" >										
									 <html:button property="method" value="Breath Analyzer" styleClass="button" onclick="BAConfig()" />				
									</td>	
								</tr>	
								
								<tr >
									<td  valign="top" align="center" >										
									 <html:button property="method" value="Cam" styleClass="button" onclick="CamConfig()" />				
									</td>	
								</tr>	
								

								<tr >
									<td  valign="top" align="center" >										
									 <html:button property="method" value="SMS" styleClass="button" onclick="sms()" />				
									</td>	
								</tr>	


								<%-- <tr >
									<td  valign="top" align="center" >										
									 <html:button property="method" value="Peers" styleClass="button" onclick="peers()" />				
									</td>	
								</tr>		
								 --%>
								
								<tr >
									<td  valign="top" align="center" >										
									 <html:button property="method" value="Change Password" styleClass="button" onclick="changepassword()" />				
									</td>	
								</tr>		
								<%-- <tr >
									<td  valign="top" align="center" >										
									 <html:button property="method" value="Report" styleClass="button"  onclick="baReport()" />				
									</td>	
								</tr>	 --%>
								
								<tr >
									<td  valign="top" align="center" >										
									 <html:button property="method" value="Network Configuration" styleClass="button"  onclick="netconfig()" />				
									</td>	
								</tr>	
								
								<tr >
									<td  valign="top" align="center" >										
									&nbsp;				
									</td>	
								</tr>		
								
								<tr >
									<td  valign="top" align="center" >										
									&nbsp;				
									</td>	
								</tr>		
								
								
								<tr >
									<td  valign="top" align="center" >										
									 <html:button property="method" value="Logout" styleClass="button" style="background: grey" onclick="logout()" />				
									</td>	
								</tr>		
								
							</table>    
		 
						
						
						
						</td>
						<td style="width:80%;height:100%" valign="middle" align="center" >
						 			
						</td>
					</tr>
					
					
					 
				</table>    
		 
			</td>					
		</tr>	
		
  </table>    
	 


  

<script>


function bioConfig()
{
	
		 document.forms[0].action ="welcome.do?method=config&device_type=BIO";
		 document.forms[0].submit();
	
}


function BAConfig()
{
	
		 document.forms[0].action ="welcome.do?method=config&device_type=BA";
		 document.forms[0].submit();
	
}

function CamConfig()
{
	
		 document.forms[0].action ="welcome.do?method=config&device_type=CAM";
		 document.forms[0].submit();
	
}



function sms()
{
	
		 document.forms[0].action ="welcome.do?method=configSMS";
		 document.forms[0].submit();
	
}

function netconfig()
{
	
		 document.forms[0].action ="welcome.do?method=configureNetwork";
		 document.forms[0].submit();
	
}





function peers()
{
	
		 document.forms[0].action ="welcome.do?method=managePeers";
		 document.forms[0].submit();
	
}


function peers()
{
	
		 document.forms[0].action ="welcome.do?method=managePeers";
		 document.forms[0].submit();
	
}


function baReport()
{
	
		 document.forms[0].action ="report.do?method=BA";
		 document.forms[0].submit();
	
}


function logout()
{
	
		 document.forms[0].action ="welcome.do?method=logout";
		 document.forms[0].submit();
	
}



function register()
{
	
	window.open("http://localhost:8080/babio/welcome.do?method=initiateReRegistration");
	
}



function changepassword()
{
	 document.forms[0].action ="welcome.do?method=changepassscreen";
	 document.forms[0].submit();
	
	
}




function trim(str)
{
   return str.replace(/^\s*|\s*$/g,"");
}



</script>


 </html:form>
</body>
</html>

