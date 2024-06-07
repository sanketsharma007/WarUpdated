
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
<body style="height: 100%; width:100%;" onload="onLoad()">

<html:form action="welcome" style="height: 100%; width:100%;">
<html:hidden property="message" />
     
  <table   style="height:100%; width:100%;"  border="0" align="center" >
		<tr style="background-color: #84c754;">					
		<td style="height:10%; width:100%;" align="center" >
		
			<table   style="height:100%; width:100%; " border="0" align="center" cellpadding="1">
				<tr style="background-color: #84c754;">					
					<td style="height:10%; width:20%;"align="center" >
								CMS Image Ubuntu-Version 22.04 	
					</td>	
					<td style="height:10%; width:60%;"align="center" >
								<h2>Network Configuration</h2> 			
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
					
					<tr >
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr >
						<td></td>
						<td   align="center" >
						
						 	 	<table   style="width:50%;height:50%" border="0" align="center" >
					
									<tr >
										<td  align="right" >	Network Interface :	</td>
										<td  align="center" >
										 	<html:select  name="LoginForm" property="netinterface">												
													
													<html:option value="eth0" >eth0</html:option>
													<html:option value="eth1" >eth1 </html:option>
													<html:option value="eth2" >eth2 </html:option>
													<html:option value="eth3" >eth3 </html:option>
													<html:option value="eth4" >eth4 </html:option>
													<html:option value="eth5" >eth5 </html:option>
													<html:option value="eth6" >eth6 </html:option>
													<html:option value="eth7" >eth7 </html:option>
													<html:option value="eth8" >eth8 </html:option>
													<html:option value="eth9" >eth9 </html:option>
													
													
																					
											</html:select>	
										</td>
									</tr>
									
									<tr >
										<td  align="right" >	IP Address :	</td>
										<td  align="center" >
										 	<html:text property="ipaddress" name="LoginForm">	</html:text>
										</td>
									</tr>
									<tr >
										<td  align="right" >	Subnet Mask :	</td>
										<td  align="center" >
										
										 	<html:text property="subnetmask" name="LoginForm">	</html:text>
										</td>
									</tr>
									<tr >
										<td  align="right" >	Gateway :	</td>
										<td  align="center" >
										
										 	<html:text property="gateway" name="LoginForm">	</html:text>
										</td>
									</tr>
																	
									<tr >
										<td  align="center" >										
											<html:button property="method" value="Save" styleClass="button" onclick="saveConfig()" />				
										</td>	
										<td  align="center" >										
											 <html:button property="backbtn" value="OK" styleClass="button" onclick="ok()" />				
										</td>	
									</tr>	
									
									<tr >
										<td  align="center" colspan="2">		
																		
											<div style="color:red"> <bean:write name="LoginForm" property="message"  />			</div>	
										</td>	
									</tr>	
									 
								</table>    
						</td>
						<td></td>
					</tr>
										
					<tr >
						<td></td>
						<td></td>
						<td></td>
					</tr>	
					
				
					 
				</table>    
		 
			</td>					
		</tr>	
		
  </table>    
	 


  

<script>



function onLoad()
{
	
}



function ok()
{
	
		 document.forms[0].action ="welcome.do?method=Execute";
		 document.forms[0].submit();
	 
}



function saveConfig()
{
	document.forms[0].method.disabled="true";
	if(ValidateIPaddress(document.forms[0].ipaddress.value, "IP Address"))
	{
		if(ValidateIPaddress(document.forms[0].subnetmask.value," subnet mask"))
		{
			if(ValidateIPaddress(document.forms[0].gateway.value," default gateway"))
			{
				var ipaddress = document.forms[0].ipaddress.value;
				var subnetmask = document.forms[0].subnetmask.value;
				var gateway = document.forms[0].gateway.value;
				var netinterface = document.forms[0].netinterface.value;
				
				
				
					var url="welcome.do?method=saveNetworkConfig&ipaddress=" + ipaddress + "&subnetmask=" + subnetmask + "&gateway=" + gateway + "&netinterface=" + netinterface ;
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
						
						
						
							if(xmlhtp.indexOf("SUCCESS") > -1)
							{
								alert("Network configuration saved successfully");
								
							}
							else if(xmlhtp.indexOf("FAIL") > -1)
							{
								alert("Network configuration could not be saved ");
							}
								
								
						}
					}
				}
				catch(e)
				{
					status="Not found";
				}
		}






function ValidateIPaddress(ipaddress,item)   
{  
	 if (/^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/.test(ipaddress))  
	  {  
	    return (true);  
	  }  
	alert("You have entered an invalid " + item + "!");  
	return (false);  
}  





function trim(str)
{
   return str.replace(/^\s*|\s*$/g,"");
}



</script>


 </html:form>
</body>
</html>

