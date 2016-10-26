<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%
   String bill_Number = "0";
   if(request.getAttribute("BILL_Number")!=null &&request.getAttribute("BILL_Number")!=""){
	   bill_Number = (String)request.getAttribute("BILL_Number");
   }
  
   String billStatus = "";
   if(request.getAttribute("BILL_STATUS")!=null &&request.getAttribute("BILL_STATUS")!=""){
	   billStatus = (String)request.getAttribute("BILL_STATUS");
   }
   %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Billing</title>
<link rel="stylesheet" type="text/css" href="Screen_Style.css">
<script src="Scripts/Billvalidator.js"></script>
<link href="Scripts/jquery-ui-1.11.4.custom/jquery-ui.css" rel="stylesheet">
<script src="Scripts/jquery-2.1.4.min.js"></script>
<script src="Scripts/jquery-ui-1.11.4.custom/jquery-ui.js"></script>

<script src="Scripts/jquery-2.1.4.min.js"></script>
     		<script src="Scripts/jquery-ui-1.11.4.custom/jquery-ui.js"></script>
   			<%--<link href="Scripts/jquery-ui-1.11.4.custom/jquery-ui.css" rel="stylesheet">--%>
 			<link href="Scripts/jquery-ui.css" rel="stylesheet">
 			<link href="Scripts/jquery-ui-1.11.4.custom/jquery-ui.css" rel="stylesheet">
     		<script src="Scripts/jquery-2.1.4.min.js"></script>
      		<script src="Scripts/jquery-ui-1.11.4.custom/jquery-ui.js"></script>
      		<script src="Scripts/jquery-ui.js"></script>


</head>

<body>
<form action="/BillPortal/Billhandler" method="post" ID="BILLFORM">
	<div id="title">
	<input type="hidden" id="Event" name="Event" size="50" value=""></input>
	<input type="hidden" id="BillNumber" name="BillNumber" size="50" value=<%=bill_Number%>></input>
	<font size="5"><b>SBA Stores, Ramapuram, Chennai</b></font>
	<font size="5"><b style="margin-left: 50%;">Smarty POS Billing System</b></font>
	
	</div>
	
	<div id="header_Bill">
	<font size="5"><b>Bill No::<%=bill_Number%></b></font>	
	<input type="button" id="Maintain" name="Maintain" value="Maintenance" onclick="openmaint();" style="margin-left:92%; margin-top:-1.50%;color: blue"></input>
	<input type="button" id="contact" name="Contact" value="Contact Us..!" onclick="opencontact();" style="margin-left:83%; margin-top:-1.50%; color: blue"></input>
	</div>
	
	<div id="content">
	<table>
	<tr>
	<td width="1%">
	<label for="item_No" style="margin-left: -3%;"><b>No</b></label>
	</td>
	<td width="6%">
	<label for="item_Name" style="margin-left: 16%;" ><b>ITEM NAME</b></label>
	</td>
	<td width="3%">
	<label for="Quantity" style="margin-left: -38%;"><b>QTY</b></label>
	</td>
	<td width="3%">
	<label for="Unit" style="margin-left: -38%;"><b>UNIT</b></label>
	</td>
	<td width="5%">
	<label for="SELL_Price" style="margin-left: -22%;"><b>PRICE</b></label>
	</td>
	<td width="5%">
	<label for="Price" style="margin-left: -61%;"><b>MRP</b></label>
	</td>
	<td width="5%">
	<label for="currTotal" style="margin-left: -92%;"><b>TOTAL</b></label>
	</td>
	</tr>
	</table>

	<table>
	<tr style="width:100%,position:absolute,display:block">
	<td width="3%">
	<label width="3%">1</label>
	</td>
	<td width="5%">
	<input type="hidden" id="item_ID" name="item_ID" size="50" value=""></input>
	<input type="text" id="item_Name" name="item_Name" size="30" onkeyup="checkItemName(event);" onblur="loadUnit();" onchange="loadUnit();" tabindex="1"></input>
	</td>
	<td width="5%">
	<input type="text" id="Quantity"  name="Quantity" onkeyup="loadSellPrice();changePrice(this);"  onblur="checkDecimal(this,'10','3');" tabindex="2" size="15"></input>
	</td>
	<td width="5%">
	<input type="text" id="Unit" name="Unit" readonly onkeydown="return false;" size="15"></input>
	</td>
	<td width="5%">
	<input type="text" id="SELL_Price" name="SELL_Price" readonly onkeydown="return false;" size="20"></input>
	</td>
	<td width="5%">
	<input type="text" id="Price" name="Price" readonly onkeydown="return false;" size="20"></input>
	</td>
	
	<td width="5%">
	<input type="text" id="currTotal" name="currTotal" readonly onkeydown="return false;"></input>
	</td>
	<td>
	
	<input type="button" id="Add_Button" name="Add_Button" value="Add Item" onclick="AddGrid();" tabindex="3" style="display:block,width:5%,position:absolute"></input>
	
	<input type="button" id="Modify_Button" name="Modify_Button" value="Clear" onclick="clearGrid();" tabindex="4" style="display:block,width:5%,position:absolute"></input>

	<input type="button" id="Update_Button" name="Update_Button" value="Update" onclick="updateRow();" tabindex="3" style="display:block,width:5%,position:absolute"></input>
	
	<input type="button" id="Delete_Button" name="Delete_Button" value="Delete" onclick="deleteRow();" tabindex="4" style="display:block,width:5%,position:absolute"></input>
	</TD>
	</tr>
	</table>
	<label><b>BILL ENTRIES:</b></label>
	<div id="scrolldiv">
	<table id="Dynamic_Bill_Grid" class="scroller" border="1" width="90%">
	<THEAD bgcolor="#cce5ff" style="overflow:yes,width:90px,display:block">
	<tr>
	<td width="10%"><b>SELECT</b></td>
	<td width="30%"><b>ITEM NAME</b></td>
	<td width="15%"><b>QUANTITY</b></td>
	<td width="15%"><b>UNIT</b></td>
	<td width="10%"><b>PRICE</b></td>
	<td width="10%"><b>MRP</b></td>
	<td width="10%"><b>TOTAL</b></td>
	<tr>
	</THEAD>
	<TBODY id="Dynamic_Bill_tbody" class="scrollclass">
	</TBODY>
	</table>
	</div>
	
	<div id="bottomleft">
		<div id="sh_buttons">
		<input type="submit" id="myButton1"  value="PRINT BILL" onclick="open1();" accesskey="P">
		<input type="button" id="myButton2" value="CLEAR BILL" accesskey="C"> 
		<input type="button" id="myButton3" value="ADD ITEM" onclick="openadditem();" accesskey="A">
		<input type="button" id="myButton4" value="MODIFY ITEM" onclick="openedititem();"  accesskey="M">
		<input type="button" id="Quick_Search" name="Quick_Search" value="SEARCH ITEM" onclick="openQuickSerach();" accesskey="S">
		</div>
			
		
		
		<div id="sh_comments">
		<table>
		<tr>
		<td>
		<b>Short Cut Keys</b></td>
		<td>
		<td>
		||</td>
		<td>
		<font color="white"><b>Add Item - </b></font></td>
		<td><b><font color="white">ALT+A</font></b></td>
		<td><b>|</b></td>
		<td>
		<font color="white"><b>Modify Item - </b></font>
		</td>
		<td><b><font color="white">ALT+M</font></b></td>
		<td><b>|</b></td>
		<td>
		<font color="white"><b>Print Bill - </b></font>
		</td>
		<td><b><font color="white">ALT+P</font></b></td>
		<td><b>|</b></td>
		<td>
		<font color="white"><b>Clear Bill - </b></font>
		</td>
		<td><b><font color="white"> ALT+C</font></b></td>
		<td><b>|</b></td>
		<td>
		<font color="white"><b>Search Item - </b></font></td>
		<td><b><font color="white">ALT+S</font></b></td>
		</tr>
		</table>
		</div>	
	</div>	
		
	
	<label for="TOTAL_BILL_COUNT"><strong style="font-size:200%;">TOTAL ITEMS: 0</strong></label>
	<input type="hidden" id="TOTAL_BILL_COUNT" name="TOTAL_BILL_COUNT" value="" style="background-color : red;"></input>
	<div id="bottomright">
	<b style="margin-left:2%"> <font size="5">TOTAL</font></b>
	<label for="Bill_Total" width="100%" height="100%" style="margin-left: 30%; height: 15%;"><bold><font size='5'>0.00</font></bold></label>
	<input type="hidden" id="Bill_Total" name="Bill_Total" width="100%" height="100%" style="margin-left: 25%; height: 15%;"><br>
	<label><b style="margin-left:2%"><font size="5">PAID</font></b></label>
	<input type="text" align="right" id="total_amount" name="total_amount" width="200" height="25%" onblur="checkDecimal(this,'10','2');AmtReturn();" style="margin-left: 31%;"><p>
	<b style="margin-left:2%"><font size="5">BALANCE</font></b>
	<input type="text" id="Amt_returned" name="Amt_returned" width="200" height="25%" style="margin-left: 16%;">
	</div>
	
	<script>
	
	$(document).on("keydown", function (e) {
    if (e.which === 8 && !$(e.target).is("input:not([readonly]):not([type=radio]):not([type=checkbox]), textarea, [contentEditable], [contentEditable=true]")) {
        e.preventDefault();
    }
});
	
	var bill_Status = '<%=billStatus%>';
	if(bill_Status=="TRUE"){
	window.alert("Bill Printed");
	}else if(bill_Status=="FAIL"){
	window.alert("Bill not Printed");
	}
	else if(bill_Status=="NO_BILL_END"){
	window.alert("No bill entry found..");
	}else{
	//do nothing...
	}
	//prevent default action
	document.onkeydown=function(){
	switch(event.keyCode){
	case 116:
	 document.getElementById("Event").value="NO_EVENT";
	 event.returnValue=false;
	return false;
	
	}
	}
	window.onpropertychange=function(){
	altRows("Dynamic_Bill_Grid");
	}
	function open1()
	{
	var total_amount = document.getElementById("total_amount").value;
	var billedAmt = document.getElementById("Bill_Total").value;
	 var radio = document.getElementsByTagName("input");
		 var flag = "false";
	for(var i in radio){
	if(radio[i].type=="radio"){
		flag = "true";
		}
	}
	if(flag=="false"){
	window.alert("No records found please insert record");
	 document.getElementById("item_Name").focus();
	event.preventDefault;
	event.returnValue=false;
	return false;
	}
	if(total_amount=="" || total_amount==null){
	window.alert("Please enter paid amount");
	 document.getElementById("total_amount").focus();
	event.preventDefault;
	event.returnValue=false;
	return false;
	}
	event.value = "PRINT_SAVE";
	document.getElementById("Event").value="PRINT_SAVE";
	document.forms[0].submit;
	}
	

	function resetForm()
	{
		
	   	  var x = confirm("Do you want to clear!");
	   	  if(x == true){
	   	$("#Dynamic_Bill_tbody").empty();
	   	document.getElementById("Bill_Total").value="";
	   	document.getElementById("total_amount").value="";
	   	document.getElementById("Amt_returned").value="";
	   	totalCount = 0;
	   	var label = document.querySelector('label[for="TOTAL_BILL_COUNT"]');
		// change it's content
		label.textContent = 'TOTAL COUNT: '+totalCount;
		var label1 = document.querySelector('label[for="Bill_Total"]');
		// change it's content
		label1.innerHTML = '<bold><font size="5">0.00</font></bold>';
	   	return false;
	   	}else{
	   	//do nothing
	   	}
	   //	document.getElementById("Dynamic_Bill_tbody").value="";
	}
	
	function openmaint()
	{
	window.open("Maint.jsp",'_self');
	}
	
	function openadditem()
	{
	 window.open("additem.jsp","","width=800,height=500");
	}
	
	function opencontact()	{
	 window.open("contactus.jsp","","width=800,height=500");
	}
	
	function openedititem()
	{
	 window.open("Edit_item.jsp","","width=800,height=500");
	}
	
	//for closing entire window
	
	//for closing entire window
	$(document).ready(function(){
	document.getElementById("Update_Button").style.visibility='hidden';
	document.getElementById("Delete_Button").style.visibility='hidden';
	});
	$('#Quantity').keypress(function(e)
{
var key =e.which||e.keyCode;
if(!(key>=48&&key<=57)&&(key!==8)&&(key!==9)&&(key!==37)&&(key!==39)&&(key!==46))
{
e.preventDefault();

document.getElementById("Quantity").value=0;
return false;
}

});
$('#total_amount').keypress(function(e)
{
var key =e.which||e.keyCode;
if(!(key>=48&&key<=57)&&(key!==8)&&(key!==9)&&(key!==37)&&(key!==39)&&(key!==46))
{
e.preventDefault();

document.getElementById("total_amount").value=0;
return false;
}

});
//for clear button
$('#myButton2').click(function(event) {
    event.preventDefault(); //
    resetForm();
});

</script>
</body>
</html>