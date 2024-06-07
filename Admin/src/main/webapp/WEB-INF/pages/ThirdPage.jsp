
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
<style>/* Stylesheet 1: */




textarea {
 
color: black;
font-size: 15px;
align: center;
}



.button {
   border-top: 1px solid #96d1f8;
   background: #81c1eb;
   background: -webkit-gradient(linear, left top, left bottom, from(#3e779d), to(#81c1eb));
   background: -webkit-linear-gradient(top, #3e779d, #81c1eb);
   background: -moz-linear-gradient(top, #3e779d, #81c1eb);
   background: -ms-linear-gradient(top, #3e779d, #81c1eb);
   background: -o-linear-gradient(top, #3e779d, #81c1eb);

   -webkit-border-radius: 29px;
   -moz-border-radius: 29px;
   border-radius: 29px;
   -webkit-box-shadow: rgba(0,0,0,1) 0 1px 0;
   -moz-box-shadow: rgba(0,0,0,1) 0 1px 0;
   box-shadow: rgba(0,0,0,1) 0 1px 0;
   text-shadow: rgba(0,0,0,.4) 0 1px 0;
   color: white;
   font-size: 20px;
   font-family: Georgia, Serif;
   text-decoration: none;
   vertical-align: middle;
   width: 300px;
   height: 100px;
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
   


body {
    font: 100% Lucida Sans;
    
    
    height: 100%;
}



</style>
</head>
<body style="height: 100%; width:100%;">

<html:form action="welcome" style="height: 100%; width:100%;">
<html:hidden property="crewid" />    
  

<table   style="height:100%; width:100%; " border="0" align="center" cellpadding="1">
	<tr style="background-color: #84c754;">					
		<td style="height:10%; width:100%;" align="center" >
		
			<table   style="height:100%; width:100%; " border="0" align="center" cellpadding="1">
				<tr style="background-color: #84c754;">					
					<td style="height:10%; width:20%;"align="center" >
								Crew ID :    <bean:write name="LoginForm" property="crewid" /> 	
					</td>	
					<td style="height:10%; width:60%;"align="center" >
								<h2>Thurd Page</h2> 			
					</td>
					<td style="height:10%; width:20%;"align="center" >
								
					</td>		
				</tr>	
			</table>
					
		</td>					
	</tr>	
 	
	<tr>
		<td style="height:60%; width:100%;" valign="middle" align="center" >
			<textarea  id="output"  cols="50" rows="1" style="border:0;  text-align:center;" >Click on the button to start the breath test</textarea>   			
		</td>
	</tr>
				
	<tr>
		<td style="height:30%; width:100%;" align="center" valign="top" >
			<html:button property="method" value="Start Breath Test" styleClass="button" onclick="startBreath()" />	    			
		</td>	
					
	</tr>

</table>


<script>





var reqFeature;
var xmlhtp ;
var res;



function startBreath()
{
	
	

	if(document.forms[0].method.value == "Return")
	{
		
	 window.opener.postMessage({ message: res, result: true }, "*");
	}
	else
	{
	
		var url="welcome.do?method=startBreath";
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


function receiveOutput(){

			var status;
			try{
				status=reqFeature.status;
				if (reqFeature.readyState == 4){ // Complete					
						if (reqFeature.status == 200)
						{ // OK response
							xmlhtp = reqFeature.responseText;						
							document.getElementById("output").innerHTML=xmlhtp;
							document.getElementById("output").scrollTop = document.getElementById("output").scrollHeight; 
							if(xmlhtp.indexOf("mg/100ml") > -1)
							{
							document.forms[0].method.value="Return";	
							res=xmlhtp;
							res=res.substring(res.indexOf("Result:"));
							}
								
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

