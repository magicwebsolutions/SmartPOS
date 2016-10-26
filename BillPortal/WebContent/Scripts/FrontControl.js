function ViewAddItem(){
document.getElementById("section").style.visibility ="hidden";
document.getElementById("section1").style.visibility ="";
document.getElementById("section2").style.visibility ="hidden";
document.getElementById("section3").style.visibility ="hidden";
document.getElementById("section4").style.visibility ="hidden";
document.getElementById("section5").style.visibility ="hidden";
}

function ViewEditItem(){
document.getElementById("section").style.visibility ="hidden";
document.getElementById("section1").style.visibility ="hidden";
document.getElementById("section2").style.visibility ="";
document.getElementById("section3").style.visibility ="hidden";
document.getElementById("section4").style.visibility ="hidden";
document.getElementById("section5").style.visibility ="hidden";
}

function ViewViewItem(){
document.getElementById("section").style.visibility ="hidden";
document.getElementById("section1").style.visibility ="hidden";
document.getElementById("section2").style.visibility ="hidden";
document.getElementById("section3").style.visibility ="";
document.getElementById("section4").style.visibility ="hidden";
document.getElementById("section5").style.visibility ="hidden";
}

function ViewDeleteItem(){
document.getElementById("section").style.visibility ="hidden";
document.getElementById("section1").style.visibility ="hidden";
document.getElementById("section2").style.visibility ="hidden";
document.getElementById("section3").style.visibility ="hidden";
document.getElementById("section4").style.visibility ="";
document.getElementById("section5").style.visibility ="hidden";
}
function ViewPassordChange(){
document.getElementById("section").style.visibility ="hidden";
document.getElementById("section1").style.visibility ="hidden";
document.getElementById("section2").style.visibility ="hidden";
document.getElementById("section3").style.visibility ="hidden";
document.getElementById("section4").style.visibility ="hidden";
document.getElementById("section5").style.visibility ="";
}
function mandate_check_add(){
var a=document.getElementById("I_name").value;
var b=document.getElementById("M_R_P").value;
var c=document.getElementById("rate").value;
var d=document.getElementById("UNIT_1").value;
if(a=="")
{
alert("Please Enter the Item Name and then submit");
return false;
}
else if(b=="")
{
alert("Please Enter the MRP and then submit");
return false;
}
else if(c=="")
{
alert("Please Enter the Price and then submit");
return false;
}
else if(d=="")
{
alert("Please Enter the Unit and then submit");
return false;
}
return true;
}


function mandate_check_edit(){
var e=document.getElementById("I_name_2").value;
var f=document.getElementById("M_R_P_2").value;
var g=document.getElementById("rate_2").value;
var h=document.getElementById("I_Unit_2").value;
if(e=="")
{
alert("Please Enter the Item Name and then submit");
return false;
}
else if(f=="")
{
alert("Please Enter the MRP and then submit");
return false;
}
else if(g=="")
{
alert("Please Enter the Price and then submit");
return false;
}
else if(h=="")
{
alert("Please Enter the Unit and then submit");
return false;
}
return true;
}





function AddItem_popup(){
if(mandate_check_add())
{
document.getElementById("ADDITEMFORMPOPUP").submit();
}
else
{
return false;
}
}
function EditItem_popup(){
if(mandate_check_edit())
{
document.getElementById("MODIFYITEMFORMPOPUP").submit();
}
else
{
return false;
}
}

function AddItem(){
if(mandate_check_add())
{
document.getElementById("ADDITEMFORM").submit();
}
else
{
return false;
}
}

function EditItem(){
if(mandate_check_edit())
{
document.getElementById("MODIFYITEMFORM").submit();
}
else
{
return false;
}

}
function gettotal(){
document.getElementById("GETTOTALBILLFORM").submit();
}

function DeleteItem(){
document.getElementById("DELETEITEMFORM").submit();
}

function Changepass(){
document.getElementById("GETPASSCHANGEFORM").submit();
}
function loadScreen(){
var status = "<%=returnFlag%>";
document.getElementById("section1").style.visibility ="hidden";
document.getElementById("section2").style.visibility ="hidden";
document.getElementById("section3").style.visibility ="hidden";
document.getElementById("section4").style.visibility ="hidden";
document.getElementById("section5").style.visibility ="hidden";
document.getElementById("section6").style.visibility ="hidden";
if(status=="SUCCESS"){
alert("Record added successfully");
}
if(status=="FAIL"){
alert("Record not added");
}
}
/* Used for Edit Item in Maintenance Screen */
var requestOBJ;
function checkItemName(event)
{
var keyc = event.KeyCode || event.which;
var value = document.getElementById("item_Name_2").value;
//alert(value);
var URL = "MasterServlet?Event=LOAD_NAME&value="+value;
if(window.XMLHttpRequest){
requestOBJ = new XMLHttpRequest();
}else if(window.ActiveXObject){
	requestOBJ= new ActivXCObject("Microsoft.XMLHTTP");
}
try{
requestOBJ.onreadystatechange=processData2;
requestOBJ.open("GET",URL,true);
requestOBJ.send();
}catch(e){
alert("Something went wrong");
}
}
function processData2(){
var itemarray="";
var Tempvar="";
var pos;
if(requestOBJ.readyState==4){

if(requestOBJ.status==200){

var responsetext= requestOBJ.responseText;
responsetext= responsetext.replace("<xml>","");
responsetext= responsetext.replace("<xml>","");
if(responsetext.indexOf("~")>0){
//alert("responsetext::"+responsetext);
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
            $( "#item_Name_2" ).autocomplete({
               minLength:3,   
               delay:500,   
               source: itemarray,//this will added our billing items to dropdown
               autoFocus:true
            });
}
}
}

function loadalldetails()
{
var URL="";
var item_Name=document.getElementById("item_Name_2").value;
if(item_Name==""){
return false;
}
URL = "MasterServlet?Event=LOADALL_MAINT&ITEM_NAME="+item_Name;
if(window.XMLHTTPRequest){
requestOBJ=new XMLHTTPRequest();
}else if(window.ActiveXObject){
	requestOBJ= new ActivXCObject("Microsoft.XMLHTTP");
}
try{
requestOBJ.onreadystatechange=processUnit2;
requestOBJ.open("GET",URL,true);
requestOBJ.send();

}catch(e){
alert("Somethink went wrong IN LOADALL_MAINT FUNCTION IN BILLVALIDATOR...");
}
}
function processUnit2(){
if(requestOBJ.readyState==4){

if(requestOBJ.status==200){
var item_Array="";
var responsetext= requestOBJ.responseText;
//alert(responsetext);
item_Array = responsetext.split("~");
//alert(item_Array);
document.getElementById("I_no_2").value=item_Array[0];
document.getElementById("I_name_2").value=item_Array[1];
document.getElementById("M_R_P_2").value=item_Array[2];
document.getElementById("rate_2").value=item_Array[3];
document.getElementById("I_Unit_2").value=item_Array[4];
document.getElementById("I_Dealer_2").value=item_Array[5];
}
}
}
/* End of search in Edit Item*/

/* Start of Search in View Item*/
var requestOBJ_View;
function checkItemName_View(event)
{
var keyc = event.KeyCode || event.which;
var value = document.getElementById("item_Name_3").value;
var URL = "MasterServlet?Event=LOAD_NAME&value="+value;
if(window.XMLHttpRequest){
requestOBJ_View = new XMLHttpRequest();
}else if(window.ActiveXObject){
	requestOBJ_View= new ActivXCObject("Microsoft.XMLHTTP");
}
try{
requestOBJ_View.onreadystatechange=processData3;
requestOBJ_View.open("GET",URL,true);
requestOBJ_View.send();
}catch(e){
alert("Something went wrong");
}
}
function processData3(){
var itemarray="";
var Tempvar="";
var pos;
if(requestOBJ_View.readyState==4){

if(requestOBJ_View.status==200){

var responsetext= requestOBJ_View.responseText;
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
            $( "#item_Name_3" ).autocomplete({
               minLength:3,   
               delay:500,   
               source: itemarray,//this will added our billing items to dropdown
               autoFocus:true
            });
}
}
}



function loadalldetails_View()
{
var URL="";
var item_Name=document.getElementById("item_Name_3").value;
if(item_Name==""){
return false;
}
URL = "MasterServlet?Event=LOADALL_MAINT&ITEM_NAME="+item_Name;
if(window.XMLHTTPRequest){
requestOBJ_View=new XMLHTTPRequest();
}else if(window.ActiveXObject){
	requestOBJ_View= new ActivXCObject("Microsoft.XMLHTTP");
}
try{
requestOBJ_View.onreadystatechange=processUnit3;
requestOBJ_View.open("GET",URL,true);
requestOBJ_View.send();

}catch(e){
alert("Somethink went wrong IN LOADALL FUNCTION IN BILLVALIDATOR...");
}
}
function processUnit3(){
if(requestOBJ_View.readyState==4){

if(requestOBJ_View.status==200){
var item_Array="";
var responsetext= requestOBJ_View.responseText;
item_Array = responsetext.split("~");
document.getElementById("I_no_3").value=item_Array[0];
document.getElementById("I_name_3").value=item_Array[1];
document.getElementById("M_R_P_3").value=item_Array[2];
document.getElementById("rate_3").value=item_Array[3];
document.getElementById("I_Unit_3").value=item_Array[4];
document.getElementById("I_Dealer_3").value=item_Array[5];
}
}
}
/*End of View Item contenct */


/* Start of Delete Item content*/

var requestOBJ;
function checkItemName_Delete(event)
{
var keyc = event.KeyCode || event.which;
var value = document.getElementById("item_Name_4").value;
var URL = "MasterServlet?Event=LOAD_NAME&value="+value;
if(window.XMLHttpRequest){
requestOBJ = new XMLHttpRequest();
}else if(window.ActiveXObject){
	requestOBJ= new ActivXCObject("Microsoft.XMLHTTP");
}
try{
requestOBJ.onreadystatechange=processData4;
requestOBJ.open("GET",URL,true);
requestOBJ.send();
}catch(e){
alert("Something went wrong");
}
}
function processData4(){
var itemarray="";
var Tempvar="";
var pos;
if(requestOBJ.readyState==4){

if(requestOBJ.status==200){

var responsetext= requestOBJ.responseText;
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
            $( "#item_Name_4" ).autocomplete({
               minLength:3,   
               delay:500,   
               source: itemarray,//this will added our billing items to dropdown
               autoFocus:true
            });
}
}
}



function loadalldetails_Delete()
{
var URL="";
var item_Name=document.getElementById("item_Name_4").value;
if(item_Name==""){
return false;
}
URL = "MasterServlet?Event=LOADALL_MAINT&ITEM_NAME="+item_Name;
if(window.XMLHTTPRequest){
requestOBJ=new XMLHTTPRequest();
}else if(window.ActiveXObject){
	requestOBJ= new ActivXCObject("Microsoft.XMLHTTP");
}
try{
requestOBJ.onreadystatechange=processUnit4;
requestOBJ.open("GET",URL,true);
requestOBJ.send();

}catch(e){
alert("Somethink went wrong IN LOADALL FUNCTION IN BILLVALIDATOR...");
}
}
function processUnit4(){
if(requestOBJ.readyState==4){

if(requestOBJ.status==200){
var item_Array="";
var responsetext= requestOBJ.responseText;
item_Array = responsetext.split("~");
document.getElementById("I_no_4").value=item_Array[0];
document.getElementById("I_name_4").value=item_Array[1];
document.getElementById("M_R_P_4").value=item_Array[2];
document.getElementById("rate_4").value=item_Array[3];
document.getElementById("I_Unit_4").value=item_Array[4];
document.getElementById("I_Dealer_4").value=item_Array[5];
}
}
}




