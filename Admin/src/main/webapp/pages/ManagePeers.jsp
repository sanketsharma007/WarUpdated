
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
  <table   style="height:100%; width:100%;"  border="0" align="center" >
		<tr style="background-color: #84c754;">					
		<td style="height:10%; width:100%;" align="center" >
		
			<table   style="height:100%; width:100%; " border="0" align="center" cellpadding="1">
				<tr style="background-color: #84c754;">					
					<td style="height:10%; width:20%;"align="center" >
								CMS Image Ubuntu-Version 22.04 	
					</td>	
					<td style="height:10%; width:60%;"align="center" >
								<h2>Manage Peer IP</h2> 			
					</td>
					<td style="height:10%; width:20%;"align="center" >
								
					</td>		
				</tr>	
			</table>
					
		</td>					
		</tr>	
		<tr style="height:90%; width:100%;">					
			<td align="center" >
				 <table   style="width:40%;height:80%" border="0" align="center" >
					
					<tr style="height:80%">
						
						<td style="width:80%;height:100%" valign="top" align="center" colspan="2">
						
								 <table    border="0" align="center" >
							
							
									<tr >
										<td align="right" colspan="2">
											&nbsp;								
										</td>	
																	
								 	</tr>
								 	
									<tr >
										<td align="right" >
											Existing Peers :								
										</td>	
										<td align="left" >
											<html:select  property="peer_ip_select" onchange="enableStatus()" size="10">										
												<html:optionsCollection  name="LoginForm" property="peers" value="value" label="label" />		
											</html:select>										
										</td>								
								 	</tr>
									<tr >
										<td align="right" >
											Add Peer :								
										</td>	
										<td align="left" >
											<html:text  name="LoginForm" property="peer_ip"/>										
																			
										</td>								
								 	</tr>								 	
								 	
								 </table>
						
						</td>
					</tr>
					<tr style="height:20%; width:100%;" valign="top">		
									<td  valign="top" align="center" >										
									 <html:button property="method" value="Add" styleClass="button" onclick="addPeer()" />				
									</td>	
									<td  valign="top" align="center" >										
									 <html:button property="method" value="Remove" styleClass="button" onclick="removePeer()" />				
									</td>	
									<td  valign="top" align="center" >										
									 <html:button property="method" value="OK" styleClass="button" onclick="ok()" />				
									</td>	
					</tr>		
					
					 
				</table>    
		 
			</td>					
		</tr>	
		
  </table>    
	 


  

<script>

function ValidateIPaddress(ipaddress)   
{  
 if (/^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/.test(ipaddress))  
  {  
    return (true)  
  }  
alert("You have entered an invalid IP address!")  
return (false)  
}  

function ok()
{
	
		 document.forms[0].action ="welcome.do?method=Execute";
		 document.forms[0].submit();
	 
}


function enableStatus()
{
	if(document.forms[0].device_status.value=="N")
		document.forms[0].device_name.disabled=true;
	else
		document.forms[0].device_name.disabled=false;
}




function addPeer()
{
	
	
	var peer_ip = document.forms[0].peer_ip.value;
	
	if(ValidateIPaddress(peer_ip))
		{
		 document.forms[0].action ="welcome.do?method=addPeer&peer_ip=" + peer_ip;
		 document.forms[0].submit();
		}
	
}




function removePeer()
{
	
	var peer_ip_select = document.forms[0].peer_ip_select.value;
	
	
	if(ValidateIPaddress(peer_ip_select))
		{
		 document.forms[0].action ="welcome.do?method=removePeer&peer_ip_select=" + peer_ip_select;
		 document.forms[0].submit();
		}
	
}





function trim(str)
{
   return str.replace(/^\s*|\s*$/g,"");
}



</script>


 </html:form>
</body>
</html>

