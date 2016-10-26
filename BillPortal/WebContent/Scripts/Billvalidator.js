var totalCount = 0;
var requestOBJ;
var total_amt=0;
var Amt_paid = 0;
var Amt_returned = 0;
var Item_No=0;
var MinQtyPrice=0;
var checkItemFromTable;
function checkItemName(event)
{	
var keyc = event.KeyCode || event.which;
document.getElementById("item_Name").innerHTML=keyc;
var value = document.getElementById("item_Name").value;
//alert(value);
if(value==null || value==""){
var item_id = document.getElementById("item_ID").value;
var unit = document.getElementById("Unit").value;
var price = document.getElementById("Price").value;
var QTY = document.getElementById("Quantity").value;
var sellPrice = document.getElementById("SELL_Price").value;
var currTotal = document.getElementById("currTotal").value;
if(item_id!=null || item_id!=""){
document.getElementById("item_ID").value="";
}
if(QTY!=null || QTY!=""){
document.getElementById("Quantity").value="";
}
if(unit!=null || unit!=""){
document.getElementById("Unit").value="";
}
if(price!=null || price!=""){
document.getElementById("Price").value="";
}
if(sellPrice!=null || sellPrice!=""){
document.getElementById("SELL_Price").value="";
}
if(currTotal!=null || currTotal!=""){
document.getElementById("currTotal").value="";
}
MinQtyPrice = 0;
return false;
}

var URL = "Billhandler?Event=LOADITEM_NAME&value="+value;
if(window.XMLHttpRequest){
requestOBJ = new XMLHttpRequest();
}else if(window.ActiveXObject){
	requestOBJ= new ActivXCObject("Microsoft.XMLHTTP");
}
try{
requestOBJ.onreadystatechange=processData;
//alert(URL);
requestOBJ.open("POST",URL,true);
requestOBJ.send();
}catch(e){
alert("Something went wrong-GOutham");
}
}
function processData(){
var itemarray="";
var Tempvar="";
var pos;
if(requestOBJ.readyState==4){

if(requestOBJ.status==200){

var responsetext= requestOBJ.responseText;
//alert(responsetext);
responsetext= responsetext.replace("<xml>","");
responsetext= responsetext.replace("<xml>","");
if(responsetext.indexOf("~")>0){
itemarray = responsetext.split("~");
checkItemFromTable = itemarray;
//alert("checkItemFromTable--"+checkItemFromTable);
 for(var i=0;i<itemarray.length;i++){

 Tempvar += "'";
 //alert(Tempvar);
 Tempvar += itemarray[i];
 Tempvar += "',";
 if(i==itemarray.length-1){
 pos = Tempvar.lastIndexOf(",");
 Tempvar = Tempvar.substring(0,pos);
//alert(TempArray);
 }
}
}else{
if(responsetext!=""){
itemarray = new Array();
 itemarray.push(responsetext);
//document.getElementById("Search").value = responsetext;
}

}

            $( "#item_Name" ).autocomplete({
               minLength:3,
               delay:500,   
               source: itemarray,//this will added our billing items to dropdown
               autoFocus:true
            });
}
}
}


//AddGridmethod
function AddGrid(){
	//alert("kannan");
	totalCount = totalCount+1;
	var label = document.querySelector('label[for="TOTAL_BILL_COUNT"]');
// change it's content
	//label.textContent = 'Total Items : '+totalCount;
	//label.innerText = 'Total Items : '+totalCount;
	label.innerHTML = 'TOTAL ITEMS: '+totalCount;
	var t_Row = "";
	Item_No=Item_No+1;
	var Item_ID=document.getElementById("item_ID").value;
	var Item_Name=document.getElementById("item_Name").value;
	var Quantity=document.getElementById("Quantity").value;
	var Unit=document.getElementById("Unit").value;
	var Price=document.getElementById("Price").value;
	var sell_price = document.getElementById("SELL_Price").value;
	var CurrTotPrice = document.getElementById("currTotal").value;
	var t_radio = "t_radio";
	var t_Item_No = "t_ItemNo";
	var t_Item_Name = "t_ItemName";
	var t_Quantity = "t_Quantity";
	var t_Unit = "t_Unit";
	var t_Price = "t_Price";
	var t_sell_Price = "t_sellPrice";
	var t_item_ID = "t_itemID";
	var t_currTotal = "t_currTotal";
	
	if(Item_Name==null || Item_Name==""){
		alert("Please enter Item Name..");
		document.getElementById("item_Name").focus();
		return false;
	}
	if(Quantity==null || Quantity==""){
		alert("Please enter Quantity..");
		document.getElementById("Quantity").focus();
		return false;
	}
	if(Quantity<=0){
		alert("Please enter Quantity greater than one..");
		document.getElementById("Quantity").focus();
		return false;
	}
	if(Unit==null || Unit==""){
		alert("Please enter Unit..");
		document.getElementById("Unit").focus();
		return false;
	}
	if(sell_price==null || sell_price==""){
		alert("Please enter selling Price..");
		document.getElementById("SELL_Price").focus();
		return false;
	}
	if(Price==null || Price==""){
		alert("Please enter Price..");
		document.getElementById("Price").focus();
		return false;
	}
	//alert("FTORRES");
	//alert("item Name::"+Item_Name);
	//alert("FTORRES");
	t_Row+="<tr id ='t_row_"+Item_No+"'>";	
	t_Row+="<td width='100px'><input type='radio' name='"+t_radio+"' id='"+t_radio+"_"+Item_No+"' onclick='populateRow(this);'></td>";
	t_Row+="<td width='100px' style='display:none'><input type='hidden' id='"+t_Item_No+"_"+Item_No+"' name='"+t_Item_No+"' size='50' value="+Item_No+"></input></td>";
	t_Row+="<td width='320px'>"+Item_Name+"<input type='hidden' id='"+t_Item_Name+"_"+Item_No+"' name='"+t_Item_Name+"' size='100' value="+Item_Name+"></input></td>";
	t_Row+="<td width='150px'>"+Quantity+"<input type='hidden' id='"+t_Quantity+"_"+Item_No+"' name='"+t_Quantity+"' size='50' value="+Quantity+"></input></td>";
	t_Row+="<td width='150px'>"+Unit+"<input type='hidden' id='"+t_Unit+"_"+Item_No+"' name='"+t_Unit+"'' size='50' value="+Unit+"></input></td>";
	t_Row+="<td width='150px'>"+sell_price+"<input type='hidden' id='"+t_sell_Price+"_"+Item_No+"' name='"+t_sell_Price+"' size='50' value="+sell_price+"></input></td>";
	t_Row+="<td width='150px'>"+Price+"<input type='hidden' id='"+t_Price+"_"+Item_No+"' name='"+t_Price+"' size='50' value="+Price+"></input></td>";
	t_Row+="<td width='150px' style='display:none'><input type='hidden' id='"+t_item_ID+"_"+Item_No+"' name='"+t_item_ID+"' size='50' value="+Item_ID+"></input></td>";
	t_Row+="<td width='150px'>"+CurrTotPrice+"<input type='hidden' id='"+t_currTotal+"_"+Item_No+"' name='"+t_currTotal+"' size='50' value="+CurrTotPrice+"></input></td>";
	t_Row+="</tr>";
	//alert("TABLE_ROW-->"+t_Row);
	var GridExist= document.getElementById("Dynamic_Bill_tbody").innerHTML;
	document.getElementById("Dynamic_Bill_tbody").innerHTML=GridExist+t_Row;
	document.getElementById("TOTAL_BILL_COUNT").value=Item_No;
	
	// for item name issue start
	document.getElementById(t_Item_Name+"_"+Item_No).value=Item_Name;
	//alert("kannan::"+document.getElementById(t_Item_Name+'_'+Item_No).value);
	//for item name issue end
	document.getElementById("TOTAL_BILL_COUNT").value=Item_No;
	//alert("total count-->"+document.getElementById(t_sell_Price+"_"+Item_No).value);
	clearGrid();
	//(document.getElementById("Price_"+Item_No+"").value);
	//alert(document.getElementById("Price_"+Item_No+"").value);
	total_amt = document.getElementById("Bill_Total").value;
	//alert("total_amt::"+total_amt);
	if(total_amt==null || total_amt==""){
	total_amt = 0;
	total_amt = parseFloat(total_amt)+parseFloat(document.getElementById(t_currTotal+"_"+Item_No).value);
	}else{
	total_amt = parseFloat(total_amt)+parseFloat(document.getElementById(t_currTotal+"_"+Item_No).value);
	}
	total_amt = total_amt.toFixed(2);
	document.getElementById("Bill_Total").value = total_amt;
	var label1 = document.querySelector('label[for="Bill_Total"]');
	label1.innerHTML = '<bold><font size="5">'+total_amt+'</font></bold>';
	document.getElementById("item_Name").focus();
}

function clearGrid(){
document.getElementById("item_Name").value="";
document.getElementById("Quantity").value="";
document.getElementById("Unit").value="";
document.getElementById("Price").value="";
document.getElementById("SELL_Price").value="";
document.getElementById("item_ID").value="";
document.getElementById("currTotal").value="";
}

function loadUnit(){
var URL =""; 
var item_Name=document.getElementById("item_Name").value;
if(item_Name==""){
return false;
}
URL = "Billhandler?Event=LOADUNIT&ITEM_NAME="+item_Name;
if(window.XMLHTTPRequest){
requestOBJ=new XMLHTTPRequest();
}else if(window.ActiveXObject){
	requestOBJ= new ActivXCObject("Microsoft.XMLHTTP");
}
try{
requestOBJ.onreadystatechange=processUnit;
requestOBJ.open("GET",URL,true);
requestOBJ.send();

}catch(e){
alert("Somethink went wrong...");
}
}
function processUnit(){
if(requestOBJ.readyState==4){

if(requestOBJ.status==200){
var item_Array;
var responsetext= requestOBJ.responseText;
//alert(responsetext);

//alert("responsetext-->"+responsetext);
item_Array = responsetext.split("$");
item_Array = item_Array[0].split("~");
//alert("responsetext"+item_Array.length);
if(item_Array.length>1){
document.getElementById("item_ID").value=item_Array[0];
document.getElementById("Unit").value=item_Array[1];
MinQtyPrice = item_Array[2];
//alert("MinQtyPrice::"+MinQtyPrice);
document.getElementById("Price").value=item_Array[3];
document.getElementById("SELL_Price").value=item_Array[2];
}else{
document.getElementById("item_ID").value="";
document.getElementById("Unit").value="";
document.getElementById("Price").value="";
document.getElementById("SELL_Price").value=0;
MinQtyPrice =0;

}
}
}
}

function changePrice(obj){
var quantity = obj.value;
var OrgQtyPrice = 0;
if(document.getElementById("Quantity").value!=null && document.getElementById("Quantity").value!=""){

OrgQtyPrice = MinQtyPrice*quantity;
OrgQtyPrice = OrgQtyPrice.toFixed(2);
//alert("EHEs");
document.getElementById("currTotal").value = OrgQtyPrice;
}else{
if(document.getElementById("Quantity").value==null && document.getElementById("Quantity").value==""){
document.getElementById("currTotal").value="";
}
}
}
var reqObj;
function tableMatch(obj){
var URL =""; 
var item_Name=obj.value;
if(item_Name==""){
return false;
}
URL = "Billhandler?Event=VALIDATE_ITEM&ITEM_NAME="+item_Name;
if(window.XMLHTTPRequest){
reqObj=new XMLHTTPRequest();
}else if(window.ActiveXObject){
	reqObj= new ActiveXCObject("Microsoft.XMLHTTP");
}
//alert("HEHEHE");
try{
//alert("HEHEHE");
reqObj.onreadystatechange=CheckTble;
//alert("HEHEHE");
reqObj.open("GET",URL,true);
//alert("HEHEHE");
reqObj.send();
//alert("HEHEHE");

}catch(e){
//console.log(e);
alert("Somethink went wrong...");
}
}

function CheckTble(){
//alert("calling check table...");
if(reqObj.readyState==4){
//alert("calling check table...");
if(reqObj.status==200){
//alert("calling check table...");
var item_Array="";
var responsetext= reqObj.responseText;
//alert("responsetext-->"+responsetext);
if(responsetext=="TRUE"){
return true;
//do nothing
}
else if(responsetext=="FALSE"){
if(document.getElementById("item_Name").value==null || document.getElementById("item_Name").value==""){
return false;
//do nothing
}else{
document.getElementById("item_Name").value=="";
alert("No Record Match Found Please Enter correct keyword");
document.getElementById("item_Name").focus();
return false;
}
}
}
}
}
function altRows(id){
if(document.getElementsByTagName("table")){
var table= document.getElementById(id);
var rows = table.getElementsByTagName("tr");
for(var i=0;i<rows.length;i++){
if(i%2===0){
rows[i].className = "evenrowcolor";
}
else{
rows[i].className = "oddrowcolor";
}
}

}
}
function populateRow(obj){
var radis = document.getElementsByTagName('radio');
var output = "";
document.getElementById("Add_Button").style.visibility='hidden';
document.getElementById("Modify_Button").style.visibility='hidden';
document.getElementById("Update_Button").style.visibility='';
document.getElementById("Delete_Button").style.visibility='';
//document.getElementById("ADD_TD").style.visibility='hidden';
//document.getElementById("CLEAR_TD").style.visibility='hidden';
//document.getElementById("ADD_TD").style.width="0%";
//document.getElementById("CLEAR_TD").style.width="0%";
var radioid = obj.id;
//alert(radioid);
var radiogrid  = radioid.split("_");
 var currentRow = radiogrid[2];
 //var parentName = document.getElementById(radioid).parentNode.parentNode.id;
 var parentName_1 = document.getElementById(radioid).parentNode.parentNode;
 //alert("parentName_1-->"+parentName_1);
//alert(currentRow.toString());
//var item_Name ="t_ItemName_"+currentRow+"";
//var item_Price ="t_Price_"+currentRow+"";
//var item_qty = "t_Quantity_"+currentRow+"";
//var item_Unit = "t_Unit_"+currentRow+"";
//var item_Sell_price  = "t_sellPrice_"+currentRow+"";
//var item_Id = "t_itemID_"+currentRow+"";
document.getElementById("item_Name").value = parentName_1.childNodes[2].childNodes[0].nodeValue;
document.getElementById("Quantity").value = parentName_1.childNodes[3].childNodes[0].nodeValue;
document.getElementById("Unit").value = parentName_1.childNodes[4].childNodes[0].nodeValue;
document.getElementById("SELL_Price").value = parentName_1.childNodes[5].childNodes[0].nodeValue;
document.getElementById("Price").value = parentName_1.childNodes[6].childNodes[0].nodeValue;
document.getElementById("item_ID").value = parentName_1.childNodes[7].childNodes[0].nodeValue;
//alert(parentName_1.childNodes[8].childNodes[1].id);
document.getElementById("currTotal").value = parentName_1.childNodes[8].childNodes[0].nodeValue;

//alert(document.getElementById("item_Name").value);
//document.getElementById("item_Name").value = document.getElementById(item_Name).value;
//document.getElementById("Quantity").value = document.getElementById(item_qty).value;
//document.getElementById("Unit").value = document.getElementById(item_Unit).value;
//document.getElementById("Price").value = document.getElementById(item_Price).value;
//document.getElementById("item_ID").value = document.getElementById(item_Id).value;
//document.getElementById("SELL_Price").value = document.getElementById(item_Sell_price).value;
//chkRadioBtn(obj);
//alert(document.getElementById("item_Name").value);

}
function clearRadio(obj){
obj.id.checked=false;
}
function chkRadioBtn(obj){
var radio_id = obj.id;
var output = "";
var all_Radio_obj = document.getElementsByTagName("input");
//alert(all_Radio_obj);
for(var i in all_Radio_obj){
if(all_Radio_obj[i].type=="radio"){
//alert("radio button");
all_Radio_obj[i].checked=false;
break;
}
}
//alert("output-->"+output);
document.getElementById(radio_id).checked=true;


}
function AmtReturn(){
var bill_Amt = 0;
var usr_Amt = 0;
var Amt_return = 0;
bill_Amt = document.getElementById("Bill_Total").value;
usr_Amt = document.getElementById("total_amount").value;
if(bill_Amt!=null && bill_Amt!="" && usr_Amt!=null && usr_Amt!=""){
if(usr_Amt>bill_Amt){
Amt_return = usr_Amt-bill_Amt;
}else if(usr_Amt==bill_Amt){
Amt_return = usr_Amt-bill_Amt;
}else{
Amt_return = usr_Amt-bill_Amt;
}
document.getElementById("Amt_returned").value=Amt_return.toFixed(2);
}else{
document.getElementById("Amt_returned").value=Amt_return.toFixed(2);
}
}
function deleteRow(){
var radio = document.getElementsByName("t_radio");
var clicked_radio_id = "";

for(var i in radio){

if(radio[i].checked==true){
	clicked_radio_id = radio[i].id;
}

}
//for updating total count
	totalCount = totalCount-1;
	var label = document.querySelector('label[for="TOTAL_BILL_COUNT"]');
// change it's content
	//label.textContent = 'Total Count : '+totalCount;
	label.innerHTML = 'TOTAL ITEMS: '+totalCount;
//for updating total count
//alert(clicked_radio_id);
var parentTR=document.getElementById(clicked_radio_id).parentNode.parentNode.rowIndex;
//alert(parentTR);
document.getElementById("Dynamic_Bill_Grid").deleteRow(parentTR);
clearGrid();
document.getElementById("Add_Button").style.visibility='';
document.getElementById("Modify_Button").style.visibility='';
document.getElementById("Update_Button").style.visibility='hidden';
document.getElementById("Delete_Button").style.visibility='hidden';

clearRadio(clicked_radio_id);
var totalByName =document.getElementsByName("t_currTotal");
var totalAMT = 0;
for(var x =0;x<totalByName.length;x++){
//alert(parseInt(totalByName[x].value));
 totalAMT =  parseFloat(totalAMT)+ parseFloat(totalByName[x].value);
}
totalAMT = totalAMT.toFixed(2);
document.getElementById("Bill_Total").value=totalAMT;
var label1 = document.querySelector('label[for="Bill_Total"]');
label1.innerHTML = '<bold><font size="5">'+totalAMT+'</font></bold>';

}
function updateRow(){
var item_Name = document.getElementById("item_Name").value;
var Quantity = document.getElementById("Quantity").value;
var Unit = document.getElementById("Unit").value;
var Price = document.getElementById("Price").value;
var sellPrice = document.getElementById("SELL_Price").value;
var itemId = document.getElementById("item_ID").value;
 var currTotal = document.getElementById("currTotal").value;
 
var totalAMT =0;
if(item_Name==null || item_Name==""){
		alert("Please enter Item Name..");
		document.getElementById("item_Name").focus();
		return false;
	}
	if(Quantity==null || Quantity==""){
		alert("Please enter Quantity..");
		document.getElementById("Quantity").focus();
		return false;
	}
	if(Unit==null || Unit==""){
		alert("Please enter Unit..");
		document.getElementById("Unit").focus();
		return false;
	}
	if(sellPrice==null || sellPrice==""){
		alert("Please enter Price..");
		document.getElementById("SELL_Price").focus();
		return false;
	}
	if(Price==null || Price==""){
		alert("Please enter Price..");
		document.getElementById("Price").focus();
		return false;
	}
	if(currTotal==null || currTotal==""){
		alert("Please enter Total..");
		document.getElementById("currTotal").focus();
		return false;
	}
	var radio = document.getElementsByTagName("input");
	var clicked_radio_id = "";

for(var i in radio){
if(radio[i].type=="radio"){
if(radio[i].checked==true){
	clicked_radio_id = radio[i].id;
}
}
}
var parentName_Tr= document.getElementById(clicked_radio_id).parentNode.parentNode;

//var billTotal = document.getElementById().value;
parentName_Tr.childNodes[2].childNodes[0].nodeValue = document.getElementById("item_Name").value;
 parentName_Tr.childNodes[3].childNodes[0].nodeValue = document.getElementById("Quantity").value;
parentName_Tr.childNodes[4].childNodes[0].nodeValue = document.getElementById("Unit").value;
parentName_Tr.childNodes[5].childNodes[0].nodeValue = document.getElementById("SELL_Price").value;
parentName_Tr.childNodes[6].childNodes[0].nodeValue = document.getElementById("Price").value;
parentName_Tr.childNodes[7].childNodes[0].nodeValue = document.getElementById("item_ID").value;
parentName_Tr.childNodes[8].childNodes[0].nodeValue = document.getElementById("currTotal").value;
//Added for inputType hidden
var itemTdId = parentName_Tr.childNodes[2].childNodes[1].id;
document.getElementById(itemTdId).value= document.getElementById("item_Name").value;
var node3 =parentName_Tr.childNodes[3].childNodes[1].id;
document.getElementById(node3).value = document.getElementById("Quantity").value;
var node4 =parentName_Tr.childNodes[4].childNodes[1].id;
document.getElementById(node4).value = document.getElementById("Unit").value;
var node5 =parentName_Tr.childNodes[5].childNodes[1].id;
document.getElementById(node5).value = document.getElementById("SELL_Price").value;
var node6 =parentName_Tr.childNodes[6].childNodes[1].id;
document.getElementById(node6).value = document.getElementById("Price").value;
var node8 =parentName_Tr.childNodes[8].childNodes[1].id;
document.getElementById(node8).value = document.getElementById("currTotal").value;
clearGrid();
document.getElementById("Add_Button").style.visibility='';
document.getElementById("Modify_Button").style.visibility='';
document.getElementById("Update_Button").style.visibility='hidden';
document.getElementById("Delete_Button").style.visibility='hidden';
clearRadio();
//FOR TOTAL AMOUNT ENTERED
var totalByName =document.getElementsByName("t_currTotal");
for(var x =0;x<totalByName.length;x++){
//alert(parseInt(totalByName[x].value));
 totalAMT =  parseFloat(totalAMT)+ parseFloat(totalByName[x].value);
}
totalAMT = totalAMT.toFixed(2);
document.getElementById("Bill_Total").value=totalAMT;

var label1 = document.querySelector('label[for="Bill_Total"]');
label1.innerHTML = '<bold><font size="5">'+totalAMT+'</font></bold>';


}
function clearRadio(){
var tag_Element = document.getElementsByTagName("input");
for(var i in tag_Element){
if(tag_Element[i].type=="radio"){
	tag_Element[i].checked=false;
}
}
}
function alterGrid(obj){
var del_row_No_arr = obj.split("_");
var del_row_No = parseInt(del_row_No_arr[2]);
var tr_length = document.getElementById("Dynamic_Bill_Grid").rows.length;
tr_length =tr_length-1;
if(tr_length==Item_No){
//do nothing
//alert("inside do nothing..");
}else{
if(del_row_No==Item_No){
alert("you have deleted last row..");
}else{
alert("you have deleted some other row");
}
}
}

/*Added for Quickview of item*/
var requestOBJ_VW;
function getItemName_view(event){
var keyc = event.KeyCode || event.which;
var value = document.getElementById("view_item_name").value;
var URL = "MasterServlet?Event=LOAD_NAME&value="+value;
if(window.XMLHttpRequest){
requestOBJ_VW = new XMLHttpRequest();
}else if(window.ActiveXObject){
	requestOBJ_VW= new ActivXCObject("Microsoft.XMLHTTP");
}
try{
requestOBJ_VW.onreadystatechange=processDataQuickView;
requestOBJ_VW.open("GET",URL,true);
requestOBJ_VW.send();
}catch(e){
alert("Something went wrong in Quick View");
}
}
function processDataQuickView()
{
var itemarray="";
var Tempvar="";
var pos;
if(requestOBJ_VW.readyState==4){

if(requestOBJ_VW.status==200){

var responsetext= requestOBJ_VW.responseText;
responsetext= responsetext.replace("<xml>","");
responsetext= responsetext.replace("<xml>","");
if(responsetext.indexOf("~")>0){
itemarray = responsetext.split("~");
for(var i=0;i<itemarray.length;i++){

 Tempvar += "'";
 Tempvar += itemarray[i];
 Tempvar += "',";
 if(i==itemarray.length-1){
 pos = Tempvar.lastIndexOf(",");
 Tempvar = Tempvar.substring(0,pos);
 }
}
}else{
if(responsetext!=""){

 itemarray.push(responsetext);
}
}
            $( "#view_item_name" ).autocomplete({
               minLength:3,   
               delay:500,   
               source: itemarray,//this will added our billing items to dropdown
               autoFocus:true
            });
}
}
}

/*Added for Quickview Ends*/

function loadSellPrice(){
var URL =""; 
var item_Name=document.getElementById("item_Name").value;
if(item_Name==""){
return false;
}
URL = "Billhandler?Event=LOADSELLPRICE&ITEM_NAME="+item_Name;
if(window.XMLHTTPRequest){
requestOBJ=new XMLHTTPRequest();
}else if(window.ActiveXObject){
	requestOBJ= new ActivXCObject("Microsoft.XMLHTTP");
}
try{
requestOBJ.onreadystatechange=processSellPrice;
requestOBJ.open("GET",URL,true);
requestOBJ.send();

}catch(e){
alert("Somethink went wrong...");
}
}
function processSellPrice(){
if(requestOBJ.readyState==4){

if(requestOBJ.status==200){
var responsetext= requestOBJ.responseText;
if(responsetext==null || responsetext==""){
MinQtyPrice =0;
}
else{
MinQtyPrice = responsetext;
}
}
}
}

function checkInteger(obj){
var idOfField = obj.id;
var idValue  = document.getElementById(idOfField).value;
if(idValue==null|| idValue==""){

}else{
var intValue=parseInt(idValue);
//alert(intValue);
if(intValue==NaN){
//alert(intValue);
window.alert("Please enter valid number..");
document.getElementById(idOfField).value=0;
return false;
}else{
//do nothing...
}
}
}

function checkDecimal(field_id,wh,dec)
{
	var alphabet="0123456789.";
	var val=0;
	if(document.getElementById(field_id.id))
	{
	//alert("inside");
	  val = document.getElementById(field_id.id).value;
      document.getElementById(field_id.id).value = val.replace(/^0+(?!\.|$)/, '');

	  val = document.getElementById(field_id.id).value;
	}
	var len = val.length;
	
		if(val.charAt(0)=="."){
			val = "0"+val.toString();
		}
		len = val.length;
//	alert(val);
	var decval=val.split(".");
	if(decval[1]=="")
	{
		document.getElementById(field_id.id).value=decval[0];
	}
	var deccheck=0;

	for(var i=0;i<len;i++)
	{
	
		var tempchar =val.charAt(i);
		if(alphabet.indexOf(tempchar)<0)
		{
	
			window.alert("Enter a valid Number");
			document.getElementById(field_id.id).value = "";
			document.getElementById(field_id.id).focus();
			return false;
		}

		if(tempchar==".")
		{
			deccheck++;
		}
		if(deccheck>1)
		{
		
			window.alert("Enter a valid Number");
			document.getElementById(field_id.id).value = "";
			document.getElementById(field_id.id).focus();
			return false;
		}
	}
	if(val.length>0)
	{
		
		if(decval[0].length>wh)
		{
			window.alert("Only "+wh+" integer places are allowed")
			document.getElementById(field_id.id).focus();
			return false;
		}
		else if(val.indexOf(".")>0)
		{
			if(decval[1].length>dec)
			{
				window.alert("Only "+dec+" decimal places are allowed")
				document.getElementById(field_id.id).focus();
				return false;
			}
		}
	}
	return true;
}
function openQuickSerach(){
	 window.open("QuickSearch.jsp","","width=500,height=400");

}
