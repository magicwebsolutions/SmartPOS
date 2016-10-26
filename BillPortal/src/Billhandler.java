

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

import java.util.*;

/**
 * Servlet implementation class for Servlet: Billhandler
 *
 */
 public class Billhandler extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Billhandler() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String event = "";
		String return_Val = "";
		String item_Name="";
		String billNo = "";
		System.out.println("Entering in doget method of BillHandlerservlet.........");
		try{
			
			event = request.getParameter("Event");
			item_Name =request.getParameter("ITEM_NAME");
			/*
			if(event==null){
				BillDAO obj = new BillDAO();
				billNo = obj.getBillNumber();
				System.out.println("inside NEWBILL EVENT...billnumber::"+billNo);
				request.setAttribute("BILL_Number",billNo);
				request.getRequestDispatcher("Billing_Screen.jsp").forward(request, response);
			}
			*/
			if(event.equalsIgnoreCase("LOADUNIT")){
				BillDAO DAO = new BillDAO();
				return_Val = DAO.loadUnit(item_Name);
			}
			 if(event.equalsIgnoreCase("NEWBILL")){
				System.out.println("inside NEWBILL EVENT...");
				BillDAO obj = new BillDAO();
				billNo = obj.getBillNumber();
				System.out.println("inside NEWBILL EVENT...billnumber::"+billNo);
				request.setAttribute("BILL_Number",billNo);
				request.getRequestDispatcher("Billing_Screen.jsp").forward(request, response);
			}
			if(event.equalsIgnoreCase("LOADSELLPRICE")){
				BillDAO DAO = new BillDAO();
				return_Val = DAO.loadSellPrice(item_Name);
			}
			if(event.equalsIgnoreCase("PREV_BILL")){
				System.out.println("GouthamMonica");
				BillDAO DAO = new BillDAO();
				return_Val = DAO.loadSellPrice(item_Name);
			}
			
			
			
			if(event.equalsIgnoreCase("LOADALL")){
				BillDAO DAO = new BillDAO();
				return_Val = DAO.loadall(item_Name);
				
			}
			if(event.equalsIgnoreCase("VALIDATE_ITEM")){
				BillDAO DAO = new BillDAO();
				return_Val = DAO.validateItem(item_Name);
			}
			if(event.equalsIgnoreCase("PRINT_SAVE")){
				String val  =request.getParameter("Item_Name_1");
				System.out.println("val-->"+val);
				
				 System.out.println("PRINT_SAVE");
			
				Enumeration item_name  =request.getParameterNames();
				 while(item_name.hasMoreElements()){
					 String ID = (String)item_name.nextElement();
					 System.out.println("ID-->"+ID);
				 }
				
				BillDAO DAO = new BillDAO();
			
				
			}
			
		}catch(Exception e)
		{
			System.out.println("heck somethink went wrong.........");
			e.printStackTrace();
		}
		finally{
			response.setContentType("text/plain");
			PrintWriter out = response.getWriter();
			out.println(return_Val);
			
		}
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Inside dopost of billhandler servlet....");
		String event ="";
		int total_count =0;
		String Bill_no = "1601100000";
		int bill_seq =0;
		String item_name = "";
		String item_id = "";
		String item_Qty = "";
		String item_Unit = "";
		String item_price = "";
		ArrayList ordered_List = null;
		HashMap data_Map = null;
		String return_Flag = "";
		HttpSession session = request.getSession();
		session.setAttribute("USER_NAME", "PARADOX");
		String user = (String)session.getAttribute("USER_NAME");
		HashMap lMap = new HashMap();
		String totalAmt = "";
		String billNo = "0";
		StringBuffer itemBuffer = new StringBuffer();
		try{
			ordered_List = new ArrayList();
			
			event = request.getParameter("Event");
			System.out.println("Event posted is:::"+event);
			/*if(event==null){
				BillDAO obj = new BillDAO();
				billNo = obj.getBillNumber();
				System.out.println("inside NEWBILL EVENT...billnumber::"+billNo);
				request.setAttribute("BILL_Number",billNo);
				request.getRequestDispatcher("Billing_Screen.jsp").forward(request, response);
			}*/
			if(event.equals("PRINT_SAVE")){
				//NEW CHANGE START HERE
				String billNumber = null;
				String itemId[] =null;
				String itemName[]=null;
				String quantity[] =null;
				String Unit[] =null;
				String price[]=null;
				String MRP[] = null;
				String total[] =null;
				String billTotal=null;
				String totalBilledAmt = null;
				//NEW CHANGE ENDS HERE
					billNumber=request.getParameter("BillNumber");
					System.out.println("billNumber::"+billNumber);
					lMap.put("billNumber", billNumber);
					itemId=request.getParameterValues("t_itemID");
					System.out.println("itemId::"+itemId);
					lMap.put("itemId", itemId);
					itemName=request.getParameterValues("t_ItemName");
					System.out.println("itemName::"+itemName);
					for(int i =0;i<itemName.length;i++){
						System.out.println("itemName values::"+itemName[i]);
					}
					lMap.put("itemName", itemName);
					quantity=request.getParameterValues("t_Quantity");
					System.out.println("t_Quantity::"+quantity);
					lMap.put("quantity", quantity);
					Unit=request.getParameterValues("t_Unit");
					System.out.println("Unit::"+Unit);
					lMap.put("Unit", Unit);
					total=request.getParameterValues("t_currTotal");
					System.out.println("total row Amount::"+total);
					lMap.put("total", total);
					MRP=request.getParameterValues("t_Price");
					System.out.println("MRP::"+MRP);
					lMap.put("MRP", MRP);
					price=request.getParameterValues("t_sellPrice");
					System.out.println("SELLING_price::"+price);
					lMap.put("sellprice", price);
					totalBilledAmt = request.getParameter("Bill_Total");
					System.out.println("totalBilledAmt ::"+totalBilledAmt);
					lMap.put("totalBilledAmt", totalBilledAmt);
					lMap.put("INPUTTER_ID",user);
					System.out.println("HashMap for BILLDAO::"+lMap);
					BillDAO obj = new BillDAO();
					return_Flag = obj.add_Billrecord(lMap);
					if(return_Flag.equals("TRUE")){
						request.setAttribute("BILL_STATUS", "TRUE");
					//CREATING NEW BILL NUMBER..
						billNo = obj.getBillNumber();
						System.out.println("inside NEWBILL EVENT...billnumber::"+billNo);
						request.setAttribute("BILL_Number",billNo);
						request.getRequestDispatcher("Billing_Screen.jsp").forward(request, response);
					}
					else{
						//CREATING NEW BILL NUMBER..
						billNo = obj.getBillNumber();
						System.out.println("inside NEWBILL EVENT...billnumber::"+billNo);
						request.setAttribute("BILL_Number",billNo);
						request.setAttribute("BILL_STATUS", "FAIL");
						request.getRequestDispatcher("Billing_Screen.jsp").forward(request, response);
					}
					
			}
				/*
				if(request.getParameter("TOTAL_BILL_COUNT")!=null)
				total_count  =Integer.parseInt(request.getParameter("TOTAL_BILL_COUNT"));
				lMap.put("TOTAL_COUNT",total_count);
				totalAmt = request.getParameter("total_amount");
				lMap.put("TOTAL_AMOUNT",totalAmt);
				lMap.put("INPUTTER_ID",user);
				lMap.put("BILL_NUMBER",billNo);
				
			
				System.out.println("Total no of bill items Inside dopost of billhandler servlet...."+total_count);
				if(total_count>0){
					//ordered_List.add(0,total_count);
					//ordered_List.add(1,user);
					for(int i = 0;i<total_count;i++){
						data_Map = new HashMap();
						data_Map.put("BILL_SEQ",i);
						data_Map.put("BILL_NUM",Bill_no);
						item_name = request.getParameter("Item_Name_"+i);
						data_Map.put("Item_Name_"+i, item_name);
						item_Qty = request.getParameter("Quantity_"+i);
						data_Map.put("Quantity_"+i,item_Qty);
						item_Unit = request.getParameter("Unit_"+i);
						data_Map.put("Unit_"+i,item_Unit);
						item_price = request.getParameter("Price_"+i);
						data_Map.put("Price_"+i,item_price);
						data_Map.put("Item_id_"+i,"1234");
						ordered_List.add(data_Map);
						
					}
					lMap.put("BILL_LIST",ordered_List);
					System.out.println("ArrayList with HashMap values--->"+ordered_List);
					System.out.println("ArrayList size--->"+ordered_List.size());
					BillDAO obj = new BillDAO();
					return_Flag = obj.add_Billrecord(lMap);
					if(return_Flag.equals("TRUE")){
						request.setAttribute("BILL_STATUS", "TRUE");
						request.getRequestDispatcher("Billing_Screen.jsp").forward(request, response);
					}
					else{
						request.setAttribute("BILL_STATUS", "FAIL");
						request.getRequestDispatcher("Billing_Screen.jsp").forward(request, response);
					}
					
					
				}else{
					System.out.println("Total no of bill items Inside dopost of billhandler servlet...."+total_count);
					System.out.println("There is Nothing Inside bill depost of billhandler servlet....");
					request.setAttribute("BILL_STATUS", "NO_BILL_END");
					request.getRequestDispatcher("Billing_Screen.jsp").forward(request, response);
					
				}
				
				
				
			}
			*/else if(event.equalsIgnoreCase("NEWBILL")){
				System.out.println("inside NEWBILL EVENT...");
				BillDAO obj = new BillDAO();
				billNo = obj.getBillNumber();
				System.out.println("inside NEWBILL EVENT...billnumber::"+billNo);
				request.setAttribute("BILL_Number",billNo);
				request.getRequestDispatcher("Billing_Screen.jsp").forward(request, response);
			}
			
			//AJAXCALL FOR LOADING ALL UNIT
			else if(event.equalsIgnoreCase("LOADITEM_NAME")){
			System.out.println("Ajaxcall in GET method of MasterServlet-->"+request.getParameter("value"));
			String value = request.getParameter("value");
			response.setContentType("text/plain");
			PrintWriter out = response.getWriter();
			BillDAO DAO = new BillDAO();
			itemBuffer.append(DAO.itemBuffer(value));
			System.out.println("itemBuffer-->"+itemBuffer);
			out.println(itemBuffer.toString());
			}
			//Added for QuickSearch Screen
			else if(event.equalsIgnoreCase("LOAD_QUICKVIEW_GRID")){
				System.out.println("Ajaxcall in doPost  method of Billhandler-->"+request.getParameter("value"));
				String value = request.getParameter("value");
				response.setContentType("text/plain");
				PrintWriter out = response.getWriter();
				BillDAO DAO = new BillDAO();
				itemBuffer.append(DAO.returnQuickSearch(value));
				System.out.println("itemBuffer-->"+itemBuffer);
				out.println(itemBuffer.toString());
				}
		}catch(Exception e){
			System.out.println("Something went wrong...."+e);
			e.printStackTrace();
			
		}
	}   	  	    
}