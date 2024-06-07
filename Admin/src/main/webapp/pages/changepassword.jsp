
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
								<h2>Change Password</h2> 			
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
										<td  align="right" >	Old Password :	</td>
										<td  align="center" >
										 	<html:password property="oldpassword" name="LoginForm">	</html:password>
										</td>
									</tr>
									<tr >
										<td  align="right" >	New Password :	</td>
										<td  align="center" >
										
										 	<html:password property="password" name="LoginForm">	</html:password>
										</td>
									</tr>
									<tr >
										<td  align="right" >	Confirm Password :	</td>
										<td  align="center" >
										
										 	<html:password property="re_password" name="LoginForm">	</html:password>
										</td>
									</tr>
																	
									<tr >
										<td  align="center" >										
											<html:button property="method" value="Submit" styleClass="button" onclick="checkpass()" />				
										</td>	
										<td  align="center" >										
											 <html:button property="method" value="Cancel" styleClass="button" onclick="ok()" />				
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


function checkpass()
{
	if( document.forms[0].oldpassword.value == "")
		alert("Please enter your old password");
	else if( document.forms[0].password.value == "")
		alert("Please enter a new password");
	else if( document.forms[0].re_password.value == "")
		alert("Please re enter your new password");
	else if( document.forms[0].password.value != document.forms[0].re_password.value)
		alert("New Password and confirm password does not match");
	else
		{
		changepass();
		
		}
	
	
	
		
	
}




function changepass()
{
	
	var oldpassword = document.forms[0].oldpassword.value;
	var password = document.forms[0].password.value;
	var re_password = document.forms[0].re_password.value;
	
	
	
		var url="welcome.do?method=changePassword&oldpassword=" + oldpassword + "&password=" + password + "&re_password=" + re_password;
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
						
						
							if(xmlhtp.indexOf("SUCCESS") > -1)
							{
								alert("Password updated successfully")
								 document.forms[0].action ="welcome.do?method=logout";
								 document.forms[0].submit();
							}
							else
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

