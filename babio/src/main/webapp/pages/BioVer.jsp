<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>

<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>


   <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html style="height: 100%; width:100%;">
<head>
<title>CMS</title>
<style>/* Stylesheet 1: */



textarea {
font-family: Georgia, Serif; 
color: black;
font-size: 25px;
align: center;
border: 0;
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
    position:absolute; TOP:510px; LEFT:250px; 
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
    
    position:absolute; TOP:265px; LEFT:103px; 
}

.type2 {
   
    position:absolute; TOP:230px; LEFT:150px; 
}


.type3 {
   
    position:absolute; TOP:215px; LEFT:210px; 
}

.type4 {
   
    position:absolute; TOP:235px; LEFT:270px; 
}

.type5 {
   
    position:absolute; TOP:325px; LEFT:310px; 
}

.type6 {
   
    position:absolute; TOP:325px; LEFT:355px; 
}

.type7{
   
    position:absolute; TOP:233px; LEFT:392px; 
}

.type8 {
   
    position:absolute; TOP:215px; LEFT:460px; 
}

.type9 {
   
    position:absolute; TOP:230px; LEFT:520px; 
}

.type10 {
    
    position:absolute; TOP:265px; LEFT:565px; 
}

.imgclass {
    
    position:absolute; TOP:218px; LEFT:100px; 
    opacity: 0.4;
}

</style>
</head>
<body style="height: 100%; width:100%;" onload="onLoad()">

<html:form action="welcome" style="height: 100%; width:100%;">
<html:hidden property="crewid" />  
<html:hidden property="first_finger" /> 
<html:hidden property="second_finger" />
<html:hidden property="reregistration" />
  
<table   style="height:100%; width:100%; " border="0" align="center" cellpadding="1">
	<tr style="background-color: #84c754;">					
		<td style="height:10%; width:100%;"align="center" >
		
			<table   style="height:100%; width:100%; " border="0" align="center" cellpadding="1">
				<tr style="background-color: #84c754;">					
					<td style="height:10%; width:20%;"align="center" >
								Crew ID :    <bean:write name="LoginForm" property="crewid" /> 	
					</td>	
					<td style="height:10%; width:20%;"align="center" >
								<div id="blink1" > <h1>Verification</h1></div> 			
					</td>
					<td style="height:10%; width:60%;"align="center" >
								
					</td>				
				</tr>	
			</table>
					
		</td>					
	</tr>	
	
	<tr >					
		<td style="height:10%; width:100%;" align="left" >
			
			<textarea  id="output"  cols="100" rows="1" style="border:1;  text-align:left;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Please place your finger on the sensor</textarea>   
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
					
					<img align="middle" class="imgclass" src = "images/hands.png" />
						
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
	var first_finger = document.forms[0].first_finger.value;	
	
	div = document.getElementById(first_finger);	
	div.style.backgroundColor = 'red';
	
	var second_finger = document.forms[0].second_finger.value;
	
	div = document.getElementById(second_finger);	
	div.style.backgroundColor = 'red';
	
	blinkFont();
	
	
	BioVer();
}

function blinkFont()
{
  document.getElementById("blink1").style.color="red"
  setTimeout("setblinkFont()",300)
}
function setblinkFont()
{
  document.getElementById("blink1").style.color=""
  setTimeout("blinkFont()",300)
}



var reqFeature;
var xmlhtp ;
var res;



function BioVer()
{
	
	
	
		var url="welcome.do?method=BioVer&first_finger="+ document.forms[0].first_finger.value + "&second_finger="+ document.forms[0].second_finger.value;
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
				//alert("Status Value: "+status);
				if (reqFeature.readyState == 4){ // Complete	
					    //window.alert("Checking HTTP Status: ");
						if (reqFeature.status == 200)
						{ // OK response
							//alert("Inside Status==200");
							xmlhtp = reqFeature.responseText;
							
							//CHANGES
							//alert(xmlhtp);
							//document.getElementById("output").innerHTML=xmlhtp;
							//document.getElementById("output").scrollTop = document.getElementById("output").scrollHeight; 
							
							
							if(xmlhtp.indexOf("ERROR") > -1)
							{							
								res="Device Error";
								window.opener.postMessage({ message: res, result: true }, "*");
							}
							else if(xmlhtp.indexOf("TIMEOUT") > -1)
							{								
								res="Operation Timed Out. Please Try again.";
								window.opener.postMessage({ message: res, result: true }, "*");
							}else if(xmlhtp.indexOf("<<MATCH>>") > -1)
							{	//CHANGES
								//window.alert(xmlhtp);
								
								if(document.forms[0].reregistration.value == "true")
									{
									
									res="Re-Registration Process is successfull";
									window.opener.postMessage({ message: res, result: true }, "*");
									}
								else
									{
									res="Verified";
									
									if(document.forms[0].crewid.value == "TEST")
									{
										alert("Fingerprint Matched");
									}
									window.opener.postMessage({ message: res, result: true }, "*");
									}
								
							}else if(xmlhtp.indexOf("<<NO MATCH>>") > -1)
							{		
								if(document.forms[0].reregistration.value == "true")
								{
								
									res="Re-Registration Process is failed. Please try again";
									window.opener.postMessage({ message: res, result: true }, "*");
								
								}
								else
								{
									res="Verification Failed. Please try again";
									window.opener.postMessage({ message: res, result: true }, "*");
								}
								
								
								
							}	
						}
					}
				}
				catch(e)
				{
					status="Not found";
				}
		}





function ReturnToCMS(){
	
	window.opener.postMessage({ message: "cancel", result: true }, "*");

}






</script>
 </html:form>
</body>
</html>

