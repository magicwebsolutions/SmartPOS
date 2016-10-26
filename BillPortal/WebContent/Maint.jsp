	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
int gettotal = 0;
String fromdate=null;
String todate=null;
boolean flag = false;

String returnFlag= null;
String modifyreturnFlag= null;
String deletereturnFlag = null;
String passwordreturnFlag = null;

if(request.getAttribute("gettotal")!=null && request.getAttribute("gettotal")!="" ){
	gettotal = (Integer)request.getAttribute("gettotal");
	flag =true;	
}
if(request.getAttribute("fromdate")!=null && request.getAttribute("fromdate")!="" ){
	fromdate = (String)request.getAttribute("fromdate");
	flag =true;	
}
if(request.getAttribute("todate")!=null && request.getAttribute("todate")!="" ){
	todate = (String)request.getAttribute("todate");
	flag =true;	
}

if(request.getAttribute("returnFlag")!=null && request.getAttribute("returnFlag")!="")
{
	returnFlag = (String)request.getAttribute("returnFlag");
	//out.println("Flag Check-->"+request.getAttribute("returnFlag"));
}

if(request.getAttribute("modifyreturnFlag")!=null && request.getAttribute("modifyreturnFlag")!="")
{
	modifyreturnFlag = (String)request.getAttribute("modifyreturnFlag");
	//out.println("Flag Check-->"+request.getAttribute("returnFlag"));
}

if(request.getAttribute("deletereturnFlag")!=null && request.getAttribute("deletereturnFlag")!="")
{
	deletereturnFlag = (String)request.getAttribute("deletereturnFlag");
	//out.println("Flag Check-->"+request.getAttribute("returnFlag"));
}

if(request.getAttribute("passwordreturnFlag")!=null && request.getAttribute("passwordreturnFlag")!="")
{
	passwordreturnFlag = (String)request.getAttribute("passwordreturnFlag");
	//out.println("Flag Check-->"+request.getAttribute("returnFlag"));
}
%>

<html>

	<head>
			<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
			<title>Maintenance</title>
 			<link rel="stylesheet" href="Screen_Style.css"> 		
 			<script src="Scripts/FrontControl.js"></script>
 			<link href="Scripts/jquery-ui-1.11.4.custom/jquery-ui.css" rel="stylesheet">
      		<script src="Scripts/jquery-2.1.4.min.js"></script>
     		<script src="Scripts/jquery-ui-1.11.4.custom/jquery-ui.js"></script>
   			<%--<link href="Scripts/jquery-ui-1.11.4.custom/jquery-ui.css" rel="stylesheet">--%>
 			<link href="Scripts/jquery-ui.css" rel="stylesheet">
 			<link href="Scripts/jquery-ui-1.11.4.custom/jquery-ui.css" rel="stylesheet">
     		<script src="Scripts/jquery-2.1.4.min.js"></script>
      		<script src="Scripts/jquery-ui-1.11.4.custom/jquery-ui.js"></script>
      		<script src="Scripts/jquery-ui.js"></script>
      		<style>.labelClass{float:left;width:250px;}</style>
      		
      		
     
	</head>
	
	<script>	
	$(document).on("keydown", function (e) {
    if (e.which === 8 && !$(e.target).is("input:not([readonly]):not([type=radio]):not([type=checkbox]), textarea, [contentEditable], [contentEditable=true]")) {
        e.preventDefault();
    }
});
	var flag=<%=flag %>;
	var returnFlag = '<%=returnFlag%>';
	var modifyreturnFlag = '<%=modifyreturnFlag%>'
	var deletereturnFlag = '<%=deletereturnFlag%>'
	var passwordreturnFlag = '<%=passwordreturnFlag%>'
	
	
	if(returnFlag == "SUCCESS"){
	alert("ITEMS ADDED SUCCESSFULLY");
	}	
	if(modifyreturnFlag == "SUCCESS"){
	alert("ITEMS MODIFIED SUCCESSFULLY");
	}
	if(deletereturnFlag == "SUCCESS"){
	alert("ITEMS DELETED SUCCESSFULLY");
	}
	if(passwordreturnFlag == "SUCCESS"){
	alert("PASSWORD CHANGED SUCCESSFULLY");
	}
	
	
	
	function viewPoint(){
		if(flag==true){
			//alert("inside");
			ViewPointOfSale();
		}
	}
	
	function openbill()
	{
		window.open("/BillPortal/Billhandler?Event=NEWBILL","_parent");
	}
	
	function openmaint()
{
//alert("Maintenance screen is clicked");
window.open("Maint.jsp","_self");
}
</script>
	<body ONLOAD="loadScreen();viewPoint();">

		<div id="header"> 
			<b><font size="5">Billing System Maintenance</font></b>
			<input type="button" id="BILLING" name="BILLING" value="BILLING" onclick="openbill();" style="margin-left:92%; margin-top:-1.95%"></input>
			<input type="button" id="REFRESH" name="REFRESH" value="REFRESH" onclick="openmaint();" style="margin-left:80%; margin-top:-1.95%"></input>
		</div>

		<div id="nav">
			<font color=white>
				<ul>
					<li><a href="#" onclick="ViewAddItem();"><font color=white><b>ADD ITEM</b></font></a></li>
					<li><a href="#" onclick="ViewEditItem();"><font color=white><b>MODIFY ITEM</b></font></a></li>
					<li><a href="#" onclick="ViewViewItem();"><font color=white><b>VIEW ITEM</b></font></a></li>
					<li><a href="#" onclick="ViewDeleteItem();"><font color=white><b>DELETE ITEM</b></font></a></li>
					<li><a href="#" onclick="ViewPassordChange();"> <font color=white><b>ADMIN CONTROL</b></font></a></li>				
				</ul>
			</font>
		</div>
		
		<div id="section"></div>
		
		<div id="section1">
			<form action="/BillPortal/MasterServlet" method=post ID="ADDITEMFORM">
			<h1 style="margin-left: 1%;"> Adding New Items to the Billing:</h1>
			<input type="hidden" name="EVENT" maxlength=25 value="ADDITEM"></input>
			
						
				<span class="labelClass" style="margin-left: 2%;">Item Name</span>
				<input type="text" class="texboxallign" id ="I_name" name="I_name" maxlength=50 style= "width:40%"></input><p>
				
				<span class="labelClass" style="margin-left: 2%;">Maximum Retail Price(MRP)</span>
				<input type="text" class="texboxallign" id="M_R_P"name="M_R_P" maxlength=25 style= "width:10%"></input><p>
				
				<span class="labelClass" style="margin-left: 2%;">Our Price</span>
				<input type="text" class="texboxallign" id="rate"name="rate" maxlength=25 style= "width:10%"></input><p>
				
				<span class="labelClass" style="margin-left: 2%;">Unit</span>
				<input type="text" id="UNIT_1"  name="UNIT_1" maxlength=25 style= "width:10%"></input><p>
				
				<span class="labelClass" style="margin-left: 2%;">Dealer </span>
				<input type="text" id="Dealer_1" name="Dealer_1" maxlength=25 style= "width:30%"> </input><p><P><p><P><p><P>
							
				<input type="hidden" name="ITEM_DATE" maxlength=25></input>
				<input type="hidden" name="Dealer" maxlength=25></input>
				
				<Input type="Button" value="ADD ITEM" onclick="AddItem();" style= "width:10%;margin-left: 10%; "  style= "height:30%;" ></input>
				<Input type="Button" value="CLEAR" onclick="Clear()" style= "width:10%; margin-left: 10%;"></input><p>
					
			</form>
		</div>

		<div id="section2">
			<form action="/BillPortal/MasterServlet" method=post  ID="MODIFYITEMFORM">
			<h1 style="margin-left: 1%;">Editing Items in the billing:</h1>
			
			<input type="hidden" name="EVENT" maxlength=25 value="SEARCHITEM"></input>
			
			<span class="labelClass" style="margin-left: 2%;"> Search </span>
			<input type="text"   name="item_Name_2" id="item_Name_2" size="50" onkeyup="checkItemName(event);" onblur="loadalldetails();"></input><p>
			
			<span class="labelClass" style="margin-left: 2%;">Item No </span>
			<input type="label"  id="I_no_2" name="I_no_2" readonly  maxlength=25 style= "width:10%"></input><p>
			
			<span class="labelClass" style="margin-left: 2%;">Item Name </span>
			<input type="text" id="I_name_2" name="I_name_2" maxlength=50 size="50"></input><p>
			
			<span class="labelClass" style="margin-left: 2%;">Maximum Retail Price(MRP)</span> 
			<input type="text" id="M_R_P_2" name="M_R_P_2" maxlength=25></input><p>
			
			<span class="labelClass" style="margin-left: 2%;">Our Price</span> 
			<input type="text" id="rate_2" name="rate_2" maxlength=25></input><p>
			
			<span class="labelClass" style="margin-left: 2%;">Unit</span>
			<input type="text" id="I_Unit_2" name="I_Unit_2" maxlength=25></input><p>
			
			<span class="labelClass" style="margin-left: 2%;">Dealer </span>
			<input type="text" id="I_Dealer_2" name="I_Dealer_2" maxlength=25></input><p>
			
			<Input type="Button" value="MODIFY" onclick="EditItem()
			" style= "width:10%; height: 15%; margin-right: 10%; margin-left: 5%;" ></input>
			<Input type="reset" value="CLEAR" style= "width:10%;"></input>
			</form>
		</div>
		
		
		<div id="section3">	
			<form action="/BillPortal/MasterServlet" method=post  ID="VIEWITEMFORM">
			<h1 style="margin-left: 1%;">View Items in Database</h1>
			
			<input type="hidden" name="EVENT" maxlength=25 value="VIEWITEM"></input>
			
			<span class="labelClass" style="margin-left: 2%;"> Search </span>
			<input type="text"   name="item_Name_3" id="item_Name_3"; size="50" onkeyup="checkItemName_View(event);"></input>						
			<Input type="Button" value="View Item Details" onclick="loadalldetails_View();" ></Input>
			<Input type="reset" value="CLEAR" style= "width:10%;"></input><p>
						
			<P><span class="labelClass" style="margin-left: 2%;">Item No </span>
			<input type="TEXT"  id="I_no_3" name="I_no_3" readonly  maxlength=25 style= "width:10%"></input><p>
			
			<span class="labelClass" style="margin-left: 2%;">Item Name </span>
			<input type="text" id="I_name_3" name="I_name_3" readonly maxlength=50 size="50"></input><p>
			
			<span class="labelClass" style="margin-left: 2%;">Maximum Retail Price(MRP)</span> 
			<input type="text" id="M_R_P_3" name="M_R_P_3" readonly  maxlength=25></input><p>
			
			<span class="labelClass" style="margin-left: 2%;">Our Price</span> 
			<input type="text" id="rate_3" name="rate_3" readonly maxlength=25></input><p>
			
			<span class="labelClass" style="margin-left: 2%;">Unit</span>
			<input type="text" id="I_Unit_3" name="I_Unit_3" readonly maxlength=25></input><p>
			
			<span class="labelClass" style="margin-left: 2%;">Dealer </span>
			<input type="text" id="I_Dealer_3" name="I_Dealer_3" readonly maxlength=25></input><p>
			
			<!--<Input type="Button" value="MODIFY" " style= "width:10%; height: 15%; margin-right: 10%; margin-left: 5%;" ></input>
			<Input type="reset" value="CLEAR" style= "width:10%;"></input> -->
				
			
			</form>
		</div>
		
	<div id="section4">
		<form action="/BillPortal/MasterServlet" method=post  ID="DELETEITEMFORM">
			<h1 style="margin-left: 2%;">Delete Items in Database</h1>
			<input type="hidden" name="EVENT" maxlength=25 value="DELETEITEM"></input>
			
			<span class="labelClass"style="margin-left: 2%;">Search  </span>
			<input type="text"   name="item_Name_4" id="item_Name_4"; size="50" onkeyup="checkItemName_Delete(event);" onblur="loadalldetails_Delete();"></input>						
									
			<P><span class="labelClass" style="margin-left: 2%;">Item No </span>
			<input type="text"  id="I_no_4" name="I_no_4" readonly  maxlength=25 style= "width:10%"></input><p>
			
			<span class="labelClass" style="margin-left: 2%;">Item Name </span>
			<input type="text" id="I_name_4" name="I_name_4" maxlength=50 size="50"></input><p>
			
			<span class="labelClass" style="margin-left: 2%;">Maximum Retail Price(MRP)</span> 
			<input type="text" id="M_R_P_4" name="M_R_P_4" maxlength=25></input><p>
			
			<span class="labelClass" style="margin-left: 2%;">Our Price</span> 
			<input type="text" id="rate_4" name="rate_4" maxlength=25></input><p>
			
			<span class="labelClass" style="margin-left: 2%;">Unit</span>
			<input type="text" id="I_Unit_4" name="I_Unit_4" maxlength=25></input><p>
			
			<span class="labelClass" style="margin-left: 2%;">Dealer </span>
			<input type="text" id="I_Dealer_4" name="I_Dealer_4" maxlength=25></input><p>
			
			<Input type="Button" value="DELETE" onclick="DeleteItem()" " style= "width:10%; height: 15%; margin-right: 10%; margin-left: 5%;" ></input>
			<Input type="reset" value="CLEAR" " style= "width:10%; height: 15%; margin-right: 10%; margin-left: 5%;" ></input>
		</form>
	</div>
		
		<div id="section5">
		
			<form action="/BillPortal/MasterServlet" method=post ID="GETPASSCHANGEFORM">
			<input type="hidden" name="EVENT" maxlength=25 value="GETPASSCHANGE"></input>
			<h1 style="margin-left: 1%;">Password Change</h1>
		
			<span class="labelClass" style="margin-left: 2%;">Enter Hint</span>
			<input type="password" id="Hint_maint" name="Hint_maint" maxlength=25></input><p>
			
			<span class="labelClass" style="margin-left: 2%;">Enter New Password</span>
			<input type="password" id="new_pass1" name="new_pass1" maxlength=25></input><p>
			
			<span class="labelClass" style="margin-left: 2%;">Re-Enter New Passord</span>
			<input type="password" id="new_pass2" name="new_pass2" maxlength=25></input><p>
			
			<Input type="Button" id="Change_Password" name="Change_Password" value="Change Password" onclick="Changepass();" style= "width:12%;margin-left: 25%; "  style= "height:30%;" ></input>
			
		</form>
		
		</div>
		
	

		<div id="footer">
			<b><font size="5">Copyright © MagicWebSolutions</font></b>
		</div>

</body>
</html>