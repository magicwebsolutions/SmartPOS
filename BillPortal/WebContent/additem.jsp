<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String returnFlag_popup= null;
if(request.getAttribute("returnFlag_popup")!=null && request.getAttribute("returnFlag_popup")!="")
{
	returnFlag_popup = (String)request.getAttribute("returnFlag_popup");
}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="Screen_Style.css">
<script src="Scripts/FrontControl.js"></script>
<title>Add Item</title>
<script>
var returnFlag_popup = '<%=returnFlag_popup%>';
if(returnFlag_popup == "SUCCESS"){
	alert("ITEMS ADDED SUCCESSFULLY");
	}
	else if (returnFlag_popup == "FAIL"){
	alert("ITEM NOT ADDED PLEASE ADD AGAIN ");
	}

</script>

</head>
<body>

<div id="section7">
			<form action="/BillPortal/MasterServlet" method=post ID="ADDITEMFORMPOPUP">
			<h1 style="margin-left: 1%;"> Adding New Items to the Billing:</h1>
			<input type="hidden" name="EVENT" maxlength=25 value="ADDITEM_POPUP"></input>
			
						
			<span class="labelClass" style="margin-left: 2%;">Item Name</span>
			<input type="text" class="texboxallign" id ="I_name" name="I_name" maxlength=50 style= "width:40%;margin-left: 15.8%;"></input><p>
			
			<span class="labelClass" style="margin-left: 2%;">Maximum Retail Price(MRP)</span>
			<input type="text" class="texboxallign" id="M_R_P"name="M_R_P" maxlength=25 style= "width:10%;margin-left: 1%;"></input><p>
			
			<span class="labelClass" style="margin-left: 2%;">Our Price</span>
			<input type="text" class="texboxallign" id="rate"name="rate" maxlength=25 style= "width:10%;margin-left: 17%;"></input><p>
			
			<span class="labelClass" style="margin-left: 2%;">Unit</span>
			<input type="text" id="UNIT_1"  name="UNIT_1" maxlength=25 style= "width:10%;margin-left: 21.3%;"></input><p>
			
			<span class="labelClass" style="margin-left: 2%;">Dealer </span>
			<input type="text" id="Dealer_1" name="Dealer_1" maxlength=25 style= "width:30%;margin-left: 19.5%;"> </input><p><P><p><P><p><P>
						
			<input type="hidden" name="ITEM_DATE" maxlength=25></input>
			<input type="hidden" name="Dealer" maxlength=25></input>
			
			<Input type="Button" value="ADD ITEM" onclick="AddItem_popup()" style= "width:10%;margin-left: 10%; "  style= "height:30%;" ></input>
			<Input type="reset" value="CLEAR" style= "width:10%; margin-left: 10%;"></input><p>
					
			</form>
		</div>


</body>
</html>