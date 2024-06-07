
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>

<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>


<!doctype html>
<html style="height: 100%; width:100%;">
<head>
<title>CMS</title>
<link href="jquery-ui.css" rel="stylesheet">
<style>/* Stylesheet 1: */

#group2 {
     visibility: hidden;
     position: absolute;
     left: 0px;
     top: 0px;
     width:100%;
     height:100%;
     text-align:center;
     z-index: 1000;
     
}



textarea {
 
color: black;
font-size: 12px;
align: center;
border: none;
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

table.stat th, table.stat td {
  font-size : 65%;
  font-family : "Myriad Web",Verdana,Helvetica,Arial,sans-serif;
  background : #efe none; color : #630; }



div.int-box { display: table-cell; vertical-align: middle; }

</style>
</head>
<body style="height: 100%; width:100%;" onload="onLoad()">

<html:form action="welcome" style="height: 100%; width:100%;">
<html:hidden property="crewid" />
<html:hidden property="barepeat" />     
<html:hidden property="camstatus" />
<html:hidden property="crewname" />
<html:hidden property="crewdivision" />
<html:hidden property="crewzone" />


	
	
  

<div id="group1">
<table   style="height:100%; width:100%; " border="0" align="center" cellpadding="1">
	<tr style="background-color: #84c754;" >					
		<td style="height:10%; width:100%;" align="center" colspan="2">
		
			<table   style="height:100%; width:100%; ">
				<tr style="background-color: #84c754;">					
					<td style="height:10%; width:30%;"align="center" >
						<table id="stat">
							<tr>
								<td><bean:write name="LoginForm" property="crewid" /></td>
							</tr>
							<tr>
								<td><bean:write name="LoginForm" property="crewname" /></td>
							</tr>
							
						</table>
								 	
					</td>	
					<td style="height:10%; width:40%;"align="center" >
								<h2>Breath Analyzer Test</h2> 			
					</td>
					<td style="height:10%; width:30%;"align="center" >
						<table id="stat">
							<tr>
								<td align="left">Division</td>
								<td><bean:write name="LoginForm" property="crewdivision" /></td>
							</tr>
							<tr>
								<td align="left">Zone</td>
								<td><bean:write name="LoginForm" property="crewzone" /></td>
							</tr>
							
						</table>
					</td>		
				</tr>	
			</table>
					
		</td>					
	</tr>	
	
 	
 	
	<tr>
		<td style="height:250px; width:100%;" valign="middle" align="center" colspan="2">
			<div id="output" class="int-box">Click on the button to start the breath test</div>		
		</td>
	</tr>
	
	<tr> 
		<td style="height:30%; width:100%;" valign="top" align="center" colspan="2">
			<div  id="wait"  ></div>			
		</td>
	</tr>
	
		
	<tr>
		<td style="height:30%; width:50%;" align="center" valign="top"  >
			<html:button property="method" value="Start Breath Test" styleClass="button" onclick="startBreath()" />	    			
		</td>	
		<td style="height:30%; width:50%;" align="center" valign="top"  >
			<html:button property="back" value="Back" styleClass="button" onclick="ReturnToCMS()" />	    			
		</td>	
		
		
	</tr>
	
</table>
</div>






<div id="group2">
<table   style="height:100%; width:100%; " border="0" align="center" cellpadding="1">
	<tr style="background-color: #84c754;" >					
		<td style="height:10%; width:100%;" align="center" colspan="2">
		
			<table   style="height:100%; width:100%; ">
				<tr style="background-color: #84c754;">					
					<td style="height:10%; width:30%;"align="center" >
						<table id="stat">
							<tr>
								<td><bean:write name="LoginForm" property="crewid" /></td>
							</tr>
							<tr>
								<td><bean:write name="LoginForm" property="crewname" /></td>
							</tr>
							
						</table>
								 	
					</td>	
					<td style="height:10%; width:40%;"align="center" >
								<h2>Breath Analyzer Test</h2> 			
					</td>
					<td style="height:10%; width:30%;"align="center" >
						<table id="stat">
							<tr>
								<td align="left">Division</td>
								<td><bean:write name="LoginForm" property="crewdivision" /></td>
							</tr>
							<tr>
								<td align="left">Zone</td>
								<td><bean:write name="LoginForm" property="crewzone" /></td>
							</tr>
							
						</table>
					</td>		
				</tr>	
			</table>					
		</td>					
	</tr>	
	
 	
 	
	<tr>
		
		<td style="height:100%; width:100%;" valign="middle" align="center" colspan="2">
			<div id="result" ></div>		
		</td>
	
	</tr>
	
		
	<tr>
		<td style="height:30%; width:50%;" align="center" valign="top" colspan="2" >
			<html:button property="ok" value="OK" styleClass="button" onclick="okreturn()" />	    			
		</td>		
	</tr>
	
</table>
</div>



<script>


var reqFeature;
var xmlhtp ;
var res;
var size=0;
var barepeat = "false";





function startBreath()
{
	
	
	document.forms[0].method.disabled="true";
	document.forms[0].back.disabled="true";
	//document.forms[0].method.style.background="grey";
	//document.getElementById("output").style.color="red";
	document.getElementById("output").innerHTML="Please Wait .... ";
	document.getElementById("wait").innerHTML='<img src="images/ajax-loader.gif">';
	
	blinkFont();

		var url="welcome.do?method=startBreath&barepeat="+document.forms[0].barepeat.value;
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
			var camstatus = document.forms[0].camstatus.value;
						
			var picurl;
			
			if(camstatus.indexOf("Y") > -1)
				picurl="http://localhost/test.jpeg";
			else
				picurl="http://localhost/default/test.png";
			
			
			
			try{
				status=reqFeature.status;
				if (reqFeature.readyState == 3){ // Complete					
						if (reqFeature.status == 200)
						{ // OK response
							
							document.getElementById("wait").innerHTML='';
							//document.getElementById("output").style.color="green";
							xmlhtp = reqFeature.responseText;	
							
							
							if(xmlhtp.indexOf("mg/100ml") > -1 || xmlhtp.indexOf("mg/100mL") > -1)
							{
								res=xmlhtp.substring(xmlhtp.indexOf("TAYAL"));                                             


								var res1 = parseFloat(res.substring(res.indexOf("Result")+14,res.indexOf("mg/100mL")));

								
								if(res1 > 0)
								{
								
									document.getElementById("group1").style.visibility="hidden";
									document.getElementById("group2").style.visibility="visible";
									document.getElementById("result").innerHTML="<table bgcolor='red'><tr><td><img src='" + picurl + "'/></td><td> <textarea rows='10' cols='30' style='background-color: red'>" + res + " </textarea></td></tr></table>";
									document.forms[0].barepeat.value="false";
										
								
								}else
								{
									document.getElementById("group1").style.visibility="hidden";
									document.getElementById("group2").style.visibility="visible";
									document.getElementById("result").innerHTML="<table><tr><td><img src='" + picurl + "'/></td><td> <textarea rows='10' cols='30'>" + res + " </textarea></td></tr></table>";
									document.forms[0].barepeat.value="false";

								}
										
								
							}
							
							document.getElementById("output").innerHTML=xmlhtp.substring(size);
							size=xmlhtp.length;
							
							//document.getElementById("output").scrollTop = document.getElementById("output").scrollHeight; 
							
							
							if(xmlhtp.indexOf("ERROR") > -1)
							{
								res="Device ERROR : Device is not connected. Connect the device properly and try again. If still problem persists, contact CMS Helpdesk";
								window.opener.postMessage({ message: res, result: true }, "*");
							}
							
							if(xmlhtp.indexOf("TimeOut") > -1)
							{
								res="TimeOut";
								window.opener.postMessage({ message: res, result: true }, "*");
							}
							
							if(xmlhtp.indexOf("Please Start Blowing") > -1 || xmlhtp.indexOf("Please blow into the BA device") > -1)
							{
								
								clearInterval(timer); 
								document.getElementById("output").style.background="green";  
								
								 if(document.forms[0].barepeat.value.indexOf("true") > -1 )
								 {
									
									 //document.getElementById("output").innerHTML="Blowing Failure. Please blow contineously for 3 seconds into the BA device";
									 document.getElementById("output").style.background="red";
									 
									 //startBreath();
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




function trim(str)
{
   return str.replace(/^\s*|\s*$/g,"");
}


var timer;

function blinkFont()
{
	 clearInterval(timer); 
  document.getElementById("output").style.background="red";  
  timer=setTimeout("setblinkFont()",300)
}
function setblinkFont()
{
	 clearInterval(timer); 
  document.getElementById("output").style.background="";  
  timer=setTimeout("blinkFont()",300)
}



function onLoad()
{
	 clearInterval(timer); 
	
	 if(document.forms[0].barepeat.value.indexOf("true") > -1 )
	 {		
		 document.forms[0].method.disabled="true";
		 document.forms[0].back.disabled="true";
		 startBreath();		 
     }
	 document.getElementById("group1").style.visibility="visible";
		document.getElementById("group2").style.visibility="hidden";
  
}

function okreturn()
{
	document.forms[0].crewid.value="";
	document.forms[0].crewname.value="";
	document.forms[0].crewdivision.value="";
	document.forms[0].crewzone.value="";
	window.opener.postMessage({ message: res, result: true }, "*");
	
}


</script>
 </html:form>
</body>
</html>
