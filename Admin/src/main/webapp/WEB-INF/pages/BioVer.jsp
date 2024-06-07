
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
   width: 200px;
   height: 40px;
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
   

.circleBase {
    border-radius: 50%;
    width: 35px;
    height: 35px;
    background: white;
    border: 1px solid blue;
    behavior: url(PIE.htc); /* remove if you don't care about IE8 */
    
}

.type1 {
    
    position:absolute; TOP:265px; LEFT:395px; 
}

.type2 {
   
    position:absolute; TOP:225px; LEFT:442px; 
}


.type3 {
   
    position:absolute; TOP:210px; LEFT:500px; 
}

.type4 {
   
    position:absolute; TOP:228px; LEFT:560px; 
}

.type5 {
   
    position:absolute; TOP:320px; LEFT:600px; 
}

.type6 {
   
    position:absolute; TOP:320px; LEFT:650px; 
}

.type7{
   
    position:absolute; TOP:228px; LEFT:685px; 
}

.type8 {
   
    position:absolute; TOP:210px; LEFT:750px; 
}

.type9 {
   
    position:absolute; TOP:225px; LEFT:810px; 
}

.type10 {
    
    position:absolute; TOP:260px; LEFT:855px; 
}

</style>
</head>
<body style="height: 100%; width:100%;" onload="onLoad()">

<html:form action="welcome" style="height: 100%; width:100%;">
<html:hidden property="crewid" />  
<html:hidden property="finger" /> 
  
<table   style="height:100%; width:100%; " border="0" align="center" cellpadding="1">
	<tr style="background-color: #84c754;">					
		<td style="height:10%; width:100%;"align="center" >
		
			<table   style="height:100%; width:100%; " border="0" align="center" cellpadding="1">
				<tr style="background-color: #84c754;">					
					<td style="height:10%; width:20%;"align="center" >
								Crew ID :    <bean:write name="LoginForm" property="crewid" /> 	
					</td>	
					<td style="height:10%; width:60%;"align="center" >
								<h1>Finger Print Verification</h1> 			
					</td>
					<td style="height:10%; width:20%;"align="center" >
								
					</td>				
				</tr>	
			</table>
					
		</td>					
	</tr>	
	
	<tr >					
		<td style="height:10%; width:100%;" align="center" >
			
			<textarea  id="output"  cols="100" rows="1" style="border:0;  text-align:center;">Please place your finger on the sensor</textarea>   
	    </td>									
	</tr>
				
				
	<tr style="height:60%, width:100%;">
					<td align="center" valign="middle" >
					<div id="L1" class="circleBase type1" onclick="selectFinger('L1')"></div>
					<div id="L2" class="circleBase type2" onclick="selectFinger('L2')"></div>
					<div id="L3" class="circleBase type3" onclick="selectFinger('L3')"></div>
					<div id="L4" class="circleBase type4" onclick="selectFinger('L4')"></div>
					<div id="L5" class="circleBase type5" onclick="selectFinger('L5')"></div>
					<div id="R5" class="circleBase type6" onclick="selectFinger('R5')"></div>
					<div id="R4" class="circleBase type7" onclick="selectFinger('R4')"></div>
					<div id="R3" class="circleBase type8" onclick="selectFinger('R3')"></div>
					<div id="R2" class="circleBase type9" onclick="selectFinger('R2')"></div>
					<div id="R1" class="circleBase type10" onclick="selectFinger('R1')"></div>
					
					<img align="middle" src = "images/hands.png" />
						
					</td>
	</tr>
				
	<tr style="height:20%, width:100%;">
					<td align="center" valign="top">
						<html:button property="method" value="Verify" styleClass="button" onclick="BioReg()" />	    			
					</td>	
					
	</tr>
				 
			
	 	
	
		
</table>    
	 
  
  
  	
   
   			

    
   
 

  

<script>
var reqFeature;
var xmlhtp ;



function trim(str)
{
   return str.replace(/^\s*|\s*$/g,"");
}




function onLoad()
{
	var div;
	var fing = document.forms[0].finger.value;	
	div = document.getElementById(fing);	
	div.style.backgroundColor = 'red';
	BioReg();
}




var reqFeature;
var xmlhtp ;
var res;



function BioReg()
{
	
	if(document.forms[0].method.value == "Return")
	{
	 window.opener.postMessage({ message: res, result: true }, "*");
	}
	else
	{
	
		var url="welcome.do?method=BioVer&finger="+ document.forms[0].finger.value;
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
							if(xmlhtp.indexOf("Verification Complete") > -1)
							{
							document.forms[0].method.value="Return";	
							res="Verification Complete";
							}
							if(xmlhtp.indexOf("Verification Failed") > -1)
							{
							document.forms[0].method.value="Return";
							res="Verification Failed";
							}	
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

