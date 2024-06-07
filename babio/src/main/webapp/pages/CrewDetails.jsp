
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>

<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>


   <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html style="height: 100%; width:100%;">
<head>
<title>Crew Details</title>
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
   font-size: 24px;
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
   
   
   
a {
    color: #000000;
    text-decoration: underline;
}

a:hover {
    text-decoration: none;
}

</style>
</head>
<body style="height: 100%; width:100%;" >

<html:form action="welcome" style="height: 100%; width:100%;">
<html:hidden property="crewid" />    
  <table   style="height:100%; width:100%;"  border="0" align="center" >
		<tr style="background-color: #84c754;">					
		<td style="height:10%; width:100%;" align="center" >
		
			<table   style="height:100%; width:100%; " border="0" align="center" cellpadding="1">
				<tr style="background-color: #84c754;">					
					<td style="height:10%; width:20%;"align="center" >
								Crew ID :    <bean:write name="LoginForm" property="crewid" /> 	
					</td>	
					<td style="height:10%; width:60%;"align="center" >
								<h2>BA / Bio </h2> 			
					</td>
					<td style="height:10%; width:20%;"align="center" >
								
					</td>		
				</tr>	
			</table>
					
		</td>					
		</tr>	
		<tr style="height:90%; width:100%;">					
			<td align="center" >
				 <table   style="width:60%;height:100%" border="0" align="center" >
					
					<tr style="height:70%">
						<td valign="middle" align="center" >
						<html:button property="method" value="Biometric" styleClass="button" onclick="goToBio()" />									
						</td>
						<td valign="middle" align="center" >
						<html:button property="method" value="BreathAnalyzer" styleClass="button" onclick="goToBA()" />									
						</td>
					</tr>
					
					
					 
				</table>    
		 
			</td>					
		</tr>	
		
  </table>    
	 


  

<script>



window.addEventListener("message", function(ev) {
    if (ev.data.message == "requestResult") {
        // ev.source is the opener
        ev.source.postMessage({ message: "deliverResult", result: true }, "*");
    }   
});

function goToBA()
{
	if(document.forms[0].crewid.value==""){
		alert("Please enter crewid");
	 	document.forms[0].crewid.value="";
	
	 }
	 else
	 {
		 document.forms[0].action ="welcome.do?method=initiateBA";
		 document.forms[0].submit();
	 }
}


function goToBio()
{
		if(document.forms[0].crewid.value==""){
			alert("Please enter crewid");
		 	document.forms[0].crewid.value="";
		
		 }
		 else
		 {
			 document.forms[0].action ="welcome.do?method=initiateBio";
			 document.forms[0].submit();
		 }
	
}



function myClear()
{
	
	 alert("ckl");
	 document.getElementById("output").innerHTML="";		
}



function turnOn()
{
	
	 alert("hello");
	 document.forms[0].action ="welcome.do?method=startBreath";
	 document.forms[0].submit();	
	
}



var reqFeature;
var xmlhtp ;




function startBreath()
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

