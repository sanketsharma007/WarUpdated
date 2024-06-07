
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





p {
   text-shadow: rgba(0,0,0,.4) 0 1px 0;
   color: red;
   font-size: 15px;
   font-family: Georgia, Serif;
   text-decoration: none;
   vertical-align: middle;
   }


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
<html:hidden property="device_type" />       
  <table   style="height:100%; width:100%;"  border="0" align="center" >
		<tr style="background-color: #84c754;">					
		<td style="height:10%; width:100%;" align="center" >
		
			<table   style="height:100%; width:100%; " border="0" align="center" cellpadding="1">
				<tr style="background-color: #84c754;">					
					<td style="height:10%; width:20%;"align="center" >
								CMS Image Ubuntu-Version 22.04 	
					</td>	
					<td style="height:10%; width:60%;"align="center" >
								<h2> <bean:write name="LoginForm" property="device_type"/> Configuration</h2> 			
					</td>
					<td style="height:10%; width:20%;"align="center" >
								
					</td>		
				</tr>	
			</table>
					
		</td>					
		</tr>	
		<tr style="height:90%; width:100%;">					
			<td align="center" >
				 <table   style="width:60%;height:80%" border="0" align="center" >
					
					<tr style="height:80%">
						
						<td style="width:100%;height:100%" valign="top" align="center" colspan="2">
						
								 <table    border="0" align="center" >
							
									<tr >
										<td align="left" colspan="2">
											<p> <blink > Select the device here and enable/disable <bean:write name="LoginForm" property="device_type"/> from CMS application</blink></p>										
										</td>								
								 	</tr>
									
									<tr >
										<td align="right" colspan="2">
											&nbsp;								
										</td>	
																	
								 	</tr>
								 									 	
									<tr >
										<td align="right" >
											Select Device :								
										</td>	
										<td align="left" >
											<html:select  name="LoginForm" property="device_name">										
												<html:optionsCollection  name="LoginForm" property="row" value="value" label="label" />										
											</html:select>										
										</td>								
								 	</tr>	
								 	
								 	<logic:notEqual name="LoginForm" property="device_type" value="BA">
								 	<tr >
										<td align="right" >
											Timeout :								
										</td>	
										<td align="left" >
										
											<html:select  name="LoginForm" property="timeout">												
													
													<html:option value="10" >10</html:option>
													<html:option value="11" >11 </html:option>
													<html:option value="12" >12 </html:option>
													<html:option value="13" >13 </html:option>
													<html:option value="14" >14 </html:option>
													<html:option value="15" >15 </html:option>
													<html:option value="16" >16 </html:option>
													<html:option value="17" >17 </html:option>
													<html:option value="18" >18 </html:option>
													<html:option value="19" >19 </html:option>
													<html:option value="20" >20 </html:option>
													<html:option value="21" >21 </html:option>
													<html:option value="22" >22 </html:option>
													<html:option value="23" >23 </html:option>
													<html:option value="24" >24 </html:option>
													<html:option value="25" >25 </html:option>
													<html:option value="26" >26 </html:option>
													<html:option value="27" >27 </html:option>
													<html:option value="28" >28 </html:option>													
													<html:option value="29" >29 </html:option>													
													<html:option value="30" >30 </html:option>
													
																					
											</html:select>										
										</td>								
								 	</tr>	
								 	</logic:notEqual>
								 	
								 	<logic:equal name="LoginForm" property="device_type" value="BA">
								 	<tr >
										<td align="right" >
											Timeout :								
										</td>	
										<td align="left" >
										
											<html:select  name="LoginForm" property="timeout">												
													
													
													<html:option value="20" >20 </html:option>
													<html:option value="21" >21 </html:option>
													<html:option value="22" >22 </html:option>
													<html:option value="23" >23 </html:option>
													<html:option value="24" >24 </html:option>
													<html:option value="25" >25 </html:option>
													<html:option value="26" >26 </html:option>
													<html:option value="27" >27 </html:option>
													<html:option value="28" >28 </html:option>													
													<html:option value="29" >29 </html:option>													
													<html:option value="30" >30 </html:option>
													
																					
											</html:select>										
										</td>								
								 	</tr>	
								 	</logic:equal>								 	
								 								 	
								 	
								 </table>
						
						</td>
					</tr>
					
					<tr style="height:20%; width:100%;" valign="top">		
									<td  valign="top" align="center" >										
									 <html:button property="method" value="Save" styleClass="button" onclick="saveData()" />				
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




function saveData()
{
	
	//var device_status = document.forms[0].device_status.value;
	var device_status = "Y";
	var device_name = document.forms[0].device_name.value;
	var device_type = document.forms[0].device_type.value;
	var timeout = document.forms[0].timeout.value;
	
	
		var url="welcome.do?method=saveData&device_type=" + device_type + "&device_name=" + device_name + "&device_status=" + device_status + "&timeout=" + timeout;
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
							alert(xmlhtp);
								
						}
					}
				}
				catch(e)
				{
					status="Not found";
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

