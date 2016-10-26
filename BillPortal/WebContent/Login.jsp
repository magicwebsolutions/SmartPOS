<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SmartyPOS-Login</title>
<script type="text/javascript" src="Scripts/jquery-1.12.0.js"></script>
<script>

$(document).on("keydown", function (e) {
    if (e.which === 8 && !$(e.target).is("input:not([readonly]):not([type=radio]):not([type=checkbox]), textarea, [contentEditable], [contentEditable=true]")) {
        e.preventDefault();
    }
});

$(document).ready(function(){
$('#link').click(function(){
	
	$('#credentials').hide();
	$('#credentials1').show();	
	$('#image').hide();
});

});


$(function(){
    $(window).keydown(function(event){
        if(event.keyCode == 13) {
            event.preventDefault();
            return false;
        }
    });
    $("input[type=text]").keypress(function(ev) {
        if ((ev.which && ev.which === 13) ||
            (ev.keyCode && ev.keyCode === 13)) {
            return false;
        } else {
            return true;
        }
    });
   
    });




function func()
{
	
	document.getElementById("credentials1").style.display='none';
	document.getElementById("credentials").style.display='block';
	document.getElementById("image").style.display='none';
	
	}

function func1()
{
	document.getElementById("credentials1").style.display='none';
	document.getElementById("credentials").style.display='block';
	
	
}

function submitLogin()
{
	var a=document.getElementById("UserName").value;
	var b=document.getElementById("User_pwd").value;
	if(a=='' || a==null)
		{
		   alert("Please enter the Username");
		   return false;
		}
	else if(b=='' || b==null)
	{
	   alert("Please enter the Password");
	   return false;
	}
	document.getElementById("myform").submit();
	}
	
function submitLogin1()
{
	var a=document.getElementById("hint").value;
	var b=document.getElementById("User_pwd1").value;
	var c=document.getElementById("User_pwd2").value;
	if(a=='' || a==null)
		{
		   alert("Please enter the Hint");
		   return false;
		}
	else if(b=='' || b==null)
	{
	   alert("Please enter the Password");
	   return false;
	}
	
	else if(c=='' || c==null)
	{
		alert(b==c);
	   alert("Please enter the changed Password again");
	   return false;
	}
	
	else if(b!=c)
	{
		alert(b);
	   alert("Password not match");
	   return false;
	}
	
	document.getElementById("myform").submit();
	}

function check()
{
	
	
}


function openbill()
{
	
window.open("/BillPortal/Billhandler?Event=NEWBILL","_parent");
//window.location.replace("/BillPortal/Billhandler?Event=NEWBILL");
}

function openmaint()
{
//alert("Maintenance screen is clicked");
window.open("Maint.jsp","_self");
}

</script>


</head>
<body onload="check()">
<form action="/BillPortal/LoginControl" id="myform" method="POST">
<div id="monto" style="width: 35%;height: 100%;position: absolute;
margin-left: -1%;margin-top: -1%;margin-bottom: -1%;">
<img alt="no img" src="bg1.jpg" style="width: 100%;height: 101%;position: absolute">

<div id="credentials">
<!--
<p style="
    position: absolute;
    margin-left: 17%;
    color: #ABED64;
    margin-top: 18%;
"> Invalid User name and Password Name : <%= ((String)request.getAttribute("status"))%> </p>
-->
<label style="
    position: fixed;
    margin-top: 10%;
    margin-left: 1%;
    font-size: 135%;
    color: white;
    "><b>User name</b></label><input type="text"  id="UserName" name="UserName" style="
    margin-top: 29%;
    position: relative;
    margin-left: 39%;
    width: 32%;
    height: 3%;
    ">
    
<label style="
    position: fixed;
    margin-top: 14%;
    margin-left: -24%;
     font-size: 135%;
    color: white;
    "><b>Password</b></label><input type="password" id="User_pwd" name="User_pwd" style="
    margin-top: 7%;
    position: relative;
    margin-left: 39%;
    width: 32%;
    height: 3%;
    ">
    
    <p style="
    position: relative;
    margin-top: 5%;
    text-align: center;
    color: red;
    text-color: orangered;">
    <a href="#" id="link" style="
    color: black;
    margin-left: 11%;"><b> Forgot Password </b></a>
    </p>
    
<p style="
    position: relative;
    margin-left: 38%;">
    
<input type="button" onclick="func1();submitLogin();"  value="submit" id="submit11" style="
	    margin-left: 11%;
    width: 37%;
    font-size: 110%;
    color: indianred;">
</p>

</div>
</div>
<div id="monto1" style="width:65%;height: 100%;position: absolute;
margin-left: 34%;margin-top: -1%;margin-bottom: -1%;">
<img alt="no img" src="bg.jpg" style="width: 100%;height: 101%;position: absolute">

<h1 style="
    position: relative;
    margin-top: 4%;
    margin-left: 0%;
    height: 20%;
    text-align: center;
    font-size: x-large;
    font-style: oblique;
    font-weight: bolder;
    font-family: cursive;
    align: center;
    border: solid:124%;"> 
    <font color="white"><--Welcome To Smarty Billing System--> <br>
    SBA STORES<br>
    Giri Nagar,Ramapuram<br>
    Chennai-600 089</font><br>
    <br></h1>
    
<div id="image" style="margin-top: 2%; display: none;">
    
    <p style="
    position: absolute;
    margin-left: 17%;
    margin-top: 26%;
    ">
    
  <input type="image" src="bill.jpg" alt="no image" value="submit" id="billSubmit11" style="
    width: 57%;
    font-size: 110%;
    color: indianred;
    min-height: 359%;
    margin-left: -23%;
    ">
</p>
       



    <p style="
    position: absolute;
    margin-left: 78%;
    margin-top: :85%;
    margin-top: 25%;
    /* margin-bottom: 42%; */
    ">
    
  <input type="image" src="maintainence.jpg" alt="no image" value="submit" id="maintain11" style="
    width: 89%;
    font-size: 110%;
    color: indianred;
    margin-top: :-19%;
    margin-top: 1%;
    margin-bottom: 120%;
    ">
</p>
    </div>
<div id="credentials1" style="
    position: fixed;display:none
">
<label style="
    position: fixed;
    margin-top: 11%;
    margin-left: -34%;
    font-size: 125%;
    color: #140A96;
    ">Enter the Hint</label><input type="text" id="hint" name="hint" style="
    margin-top: 11%;
    position: fixed;
    margin-left: -18%;
    width: 10%;
    height: 3%;
    ">
    
<label style="
    position: fixed;
    margin-top: 14%;
    margin-left: -34%;
    font-size: 125%;
    color: #140A96;
    ">Enter New Password</label><input type="password" id="User_pwd1" name="User_pwd1" style="
    margin-top: 14%;
    position: fixed;
    margin-left: -18%;
    width: 10%;
    height: 3%;
    ">

<label style="
    position: fixed;
    margin-top: 17%;
    margin-left: -34%;
    font-size: 125%;
    color: #140A96;
    ">ReEnter New Password</label><input type="password" id="User_pwd2" name="User_pwd2" style="
    margin-top: 17%;
    position: fixed;
    margin-left: -18%;
    width: 10%;
    height: 3%;
    ">
<p style="
    position: fixed;
    margin-left: -29%;
    margin-top: 21%;
    ">
    
<input type="button" value="back" id="back11" onclick="func();" style="
    width: 8%;
    font-size: 110%;
    color: indianred;
    position: fixed;
    margin-left: :45%;
    ">
</p><p style="
    position: fixed;
    margin-left: -17%;
    margin-top: -4%;
    ">
    
<input type="button" value="submit" id="submit111" onclick="submitLogin1();"style="
    width: 7%;
    margin-top: 25%;
    font-size: 110%;
    color: indianred;
    position: fixed;
    ">
</p>
</div>
</div>
</form>

<%
if((String)request.getAttribute("status")=="success")
{
	%>
	<script>
    alert("Login :Success");
    document.getElementById("image").style.display='block';
    </script>
    <%
}

else if((String)request.getAttribute("status")=="wronghint")
{
	%>
	<script>
	alert("Hint :Wrong");
    </script>
    <%
}

else if((String)request.getAttribute("status")=="failure")
{
	%>
	<script>
	alert("Username and Password not match");
    </script>
    <%
}

else if((String)request.getAttribute("status")=="updated")
{
	%>
	<script>
	alert("Password changed Successfully");
    </script>
    <%
}
%>
<script>
$('#billSubmit11').click(function(event) {

    event.preventDefault(); //
    openbill();
});
$('#maintain11').click(function(event) {
    event.preventDefault(); //
    openmaint();
});

</script>
</body>
</html>