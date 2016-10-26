<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="Quick_Search.css">
<title>Search Item</title>
</head>
<body bgcolor="lightblue" onload="focusSearch();">
<style>
body {
	background: lightblue;
	color: #444;
	font: 100%/30px 'Helvetica Neue', helvetica, arial, sans-serif;
	text-shadow: 0 1px 0 #fff;
}

strong {
	font-weight: bold; 
}

em {
	font-style: italic; 
}

table {
	background: #f5f5f5;
	border-collapse: separate;
	box-shadow: inset 0 1px 0 #fff;
	font-size: 12px;
	line-height: 40%;
	margin: 30px auto;
	text-align: left;
	width: 100%;
}	

th {
	background: #f1f1f1, linear-gradient(#777, #444);
	border-left: 1px solid #555;
	border-right: 1px solid #777;
	border-top: 1px solid #555;
	border-bottom: 1px solid #333;
	box-shadow: inset 0 1px 0 #999;
	color: #fff;
  font-weight: bold;
	padding: 10px 15px;
	position: relative;
	text-shadow: 0 1px 0 #000;	
}

th:after {
	background: linear-gradient(rgba(255,255,255,0), rgba(255,255,255,.08));
	content: '';
	display: block;
	height: 25%;
	left: 0;
	margin: 1px 0 0 0;
	position: absolute;
	top: 25%;
	width: 100%;
}

th:first-child {
	border-left: 1px solid #777;	
	box-shadow: inset 1px 1px 0 #999;
}

th:last-child {
	box-shadow: inset -1px 1px 0 #999;
}

td {
	border-right: 1px solid #fff;
	border-left: 1px solid #e8e8e8;
	border-top: 1px solid #fff;
	border-bottom: 1px solid #e8e8e8;
	padding: 10px 15px;
	position: relative;
	transition: all 300ms;
}

td:first-child {
	box-shadow: inset 1px 0 0 #fff;
}	

td:last-child {
	border-right: 1px solid #e8e8e8;
	box-shadow: inset -1px 0 0 #fff;
}	

tr {
	background: #f1f1f1;	
}

tr:nth-child(odd) td {
	background: #f1f1f1;	
}

tr:last-of-type td {
	box-shadow: inset 0 -1px 0 #fff; 
}

tr:last-of-type td:first-child {
	box-shadow: inset 1px -1px 0 #fff;
}	

tr:last-of-type td:last-child {
	box-shadow: inset -1px -1px 0 #fff;
}	

tbody:hover td {
	color: transparent;
	text-shadow: 0 0 3px #aaa;
}

tbody:hover tr:hover td {
	color: #444;
	text-shadow: 0 1px 0 #fff;
}

</style>

<label> <center><B>QUICK VIEW</B></center></label>
<Table>
<TR>
<TD><label for="Quick_Search"><B>QUICK VIEW</B></label></TD>
<TD><input type=text name="Quick_Search" id="Quick_Search" class="textbox1" onkeydown="checkItemName(event);"></TD>
<TD><input type=button name="close" id="close" value="close" onclick="window.close();" accesskey="q"></TD>
</TR>
</table>
<Table id="Ajax_Tble">
<thead>
<TR>
<td><bold>S.No</bold></td>
<td><bold>Item Name</bold></td>
<td>Unit</td>
<td>Price</td>
<td>MRP</td>
</TR>
</thead>
<tbody id="Quick_Search_tbody">
</tbody>
</Table>
<div id="no_Record_div" style="display:none">
<label><font size="4" color="red">No Record Found</font></label>
</div>
<script>
function checkItemName(event)
{	
//var item_name = document.getElementById("Quick_Search").value;
var keyc = event.KeyCode || event.which;
document.getElementById("Quick_Search").innerHTML=keyc;
var item_name = document.getElementById("Quick_Search").value;
//alert(value);
if(item_name==null || item_name==""){
	document.getElementById("Quick_Search_tbody").innerHTML="";
	//do nothing
}
else if(item_name.length<2){
	
	//do nothing
}
else{
	//alert("item Name::"+item_name);
var URL = "Billhandler?Event=LOAD_QUICKVIEW_GRID&value="+item_name;
if(window.XMLHttpRequest){
requestOBJ = new XMLHttpRequest();
}else if(window.ActiveXObject){
	requestOBJ= new ActivXCObject("Microsoft.XMLHTTP");
}
try{
requestOBJ.onreadystatechange=formGrid;
//alert(URL);
requestOBJ.open("POST",URL,true);
requestOBJ.send();
}catch(e){
alert("Something went wrong");
}
}
}
function formGrid(){
var itemarray="";
var singleGrid = "";
var singleGridArray = "";
var pos;
var count = 1;
var t_Row = "";
if(requestOBJ.readyState==4){

if(requestOBJ.status==200){
	//alert("ok");
var responsetext= requestOBJ.responseText;
//alert(responsetext);
responsetext= responsetext.replace("<xml>","");
responsetext= responsetext.replace("<xml>","");
if(responsetext.indexOf("~")>0){
itemarray = responsetext.split("#");
for(var i=0;i<itemarray.length;i++){
	singleGrid = itemarray[i];
	if(singleGrid!=null){
		singleGridArray = singleGrid.split("~");
		t_Row+="<tr id ='t_row_"+count+"'>";
		t_Row+="<td>"+count+"</td>";
		t_Row+="<td>"+singleGridArray[0]+"</td>";
		t_Row+="<td>"+singleGridArray[1]+"</td>";
		t_Row+="<td>"+singleGridArray[2]+"</td>";
		t_Row+="<td>"+singleGridArray[3]+"</td>";
		t_Row+="</tr>";
		//alert("TABLE_ROW-->"+t_Row);
		//var GridExist= document.getElementById("Dynamic_Bill_tbody").innerHTML;
		document.getElementById("Quick_Search_tbody").innerHTML=t_Row;
		document.getElementById("no_Record_div").style.display="none";
		count++;
		}else{
			//alert("no record");
			count=0;
			document.getElementById("no_Record_div").style.display="";
			document.getElementById("Quick_Search").focus();
			//do nothing
			}
}
 
}else{
	count=0;
	document.getElementById("no_Record_div").style.display="";
	//document.getElementById("Quick_Search").focus();

}       
}
}
}

function focusSearch(){
	document.getElementById("Quick_Search").focus();
}
</script>
</body>
</html>