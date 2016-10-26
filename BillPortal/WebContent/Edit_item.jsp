<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String modifyreturnFlag= null;
if(request.getAttribute("modifyreturnFlag")!=null && request.getAttribute("modifyreturnFlag")!="")
{
	modifyreturnFlag = (String)request.getAttribute("modifyreturnFlag");
}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="Screen_Style.css">
<script src="Scripts/FrontControl.js"></script>
<link href="Scripts/jquery-ui-1.11.4.custom/jquery-ui.css" rel="stylesheet">
<script src="Scripts/jquery-2.1.4.min.js"></script>
<script src="Scripts/jquery-ui-1.11.4.custom/jquery-ui.js"></script>
<title>Edit Item</title>
</head>

<script type="text/javascript">
var modifyreturnFlag = '<%=modifyreturnFlag%>'
if(modifyreturnFlag == "SUCCESS"){
	alert("ITEMS MODIFIED SUCCESSFULLY");
	}
else if (modifyreturnFlag == "FAIL"){
	alert("ITEMS NOT MODIFIED..! PLEASE MODIFY AGAIN");
	}
</script>


<body>


<div id="section8">
			<form action="/BillPortal/MasterServlet" method=post  ID="MODIFYITEMFORMPOPUP">
			<h1 style="margin-left: 1%;">Editing Items in the billing:</h1>
			
			<input type="hidden" name="EVENT" maxlength=25 value="POPUP_MODIFYITEM"></input>
			
			<span class="labelClass" style="margin-left: 2%;"> Search </span>
			<input type="text"   name="item_Name_2" id="item_Name_2" size="50" onkeyup="checkItemName(event);" onblur="loadalldetails();" style= "width:40%; margin-left: 20%;"></input><p>
			
			<span class="labelClass" style="margin-left: 2%;">Item No </span>
			<input type="label"  id="I_no_2" name="I_no_2" readonly  maxlength=25 style="width:10% ;margin-left: 19%;"></input><p>
			
			<span class="labelClass" style="margin-left: 2%;">Item Name </span>
			<input type="text" id="I_name_2" name="I_name_2" maxlength=50 size="50" style="width:40%; margin-left: 17%;"></input><p>
			
			<span class="labelClass" style="margin-left: 2%;">Maximum Retail Price(MRP)</span> 
			<input type="text" id="M_R_P_2" name="M_R_P_2" maxlength=25 style="width:10%; margin-left: 2%;" onblur="null_validation();"></input><p>
			
			<span class="labelClass" style="margin-left: 2%;">Our Price</span> 
			<input type="text" id="rate_2" name="rate_2" maxlength=25 style="width:10%; margin-left: 18%;"></input><p>
			
			<span class="labelClass" style="margin-left: 2%;">Unit</span>
			<input type="text" id="I_Unit_2" name="I_Unit_2" maxlength=25 style="width:10%; margin-left: 22%;"></input><p>
			
			<span class="labelClass" style="margin-left: 2%;">Dealer </span>
			<input type="text" id="I_Dealer_2" name="I_Dealer_2" maxlength=25 style="width:10%; margin-left: 20%;"></input><p>
			
			<Input type="Button" value="MODIFY" onclick="EditItem_popup()" style= "width:10%; height: 15%; margin-right: 10%; margin-left: 5%;" ></input>
			<Input type="reset" value="CLEAR" style= "width:10%;"></input>
			</form>
		</div>

</body>
</html>