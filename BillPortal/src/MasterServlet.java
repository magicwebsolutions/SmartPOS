

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.Session;

import com.sun.corba.se.spi.orbutil.fsm.Action;

/**
 * Servlet implementation class for Servlet: MasterServlet
 *
 */
 public class MasterServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public MasterServlet() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// TODO Auto-generated method stub
		StringBuffer itemBuffer = new StringBuffer();
		String event = "";
		String return_Val = "";
		String item_Name="";
		PrintWriter out = null;
		try{
			out = response.getWriter();
			
			event = request.getParameter("Event");
			item_Name =request.getParameter("ITEM_NAME");
		System.out.println("Ajaxcall in GET method of MasterServlet-->"+request.getParameter("value"));
		String value = request.getParameter("value");
		if(event.equalsIgnoreCase("LOAD_NAME")){
		response.setContentType("text/plain");
		MasterDAO DAO = new MasterDAO();
		itemBuffer.append(DAO.itemBuffer(value));
		System.out.println("itemBuffer-->"+itemBuffer);
		return_Val=itemBuffer.toString();
		}
		
		if(event.equalsIgnoreCase("LOADALL_MAINT")){
			response.setContentType("text/plain");
			MasterDAO DAO1 = new MasterDAO();
			return_Val = DAO1.loadall_maint(item_Name);
		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		finally{
			
			out.println(return_Val);
		}
		}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HashMap AdddataMap =new HashMap();
		HashMap editdatamap = new HashMap();
		HashMap Deletedatamap = new HashMap();
		HashMap gettotbillmap = new HashMap();
		HashMap changepass = new HashMap();
		
		String returnFlag = "FAIL";
		String modifyreturnFlag = "FAIL";
		String deletereturnFlag = "FAIL";
		String passwordreturnFlag = "FAIL";
		String returnFlag_popup="FAIL";		
		String Event = request.getParameter("EVENT");				
		MasterDAO OBJ = new MasterDAO();
		if(Event.equalsIgnoreCase("ADDITEM"))
		{
			System.out.println("Inside Master Servlet Add item event");
			String Item_Name = request.getParameter("I_name");
			String M_R_P = request.getParameter("M_R_P");
			String rate = request.getParameter("rate");
			String UNIT = request.getParameter("UNIT_1");
			String Item_Date = request.getParameter("ITEM_DATE");
			String Dealer = request.getParameter("Dealer_1");
			if(Dealer.equalsIgnoreCase("")||Dealer.equalsIgnoreCase(null))
			{
				System.out.println("inside null check");
				Dealer="SBA";
			}
			AdddataMap.put("Event",Event);
			AdddataMap.put("Item_Name",Item_Name);
			AdddataMap.put("M_R_P",M_R_P);
			AdddataMap.put("rate",rate);
			AdddataMap.put("UNIT",UNIT);
			AdddataMap.put("ITEM_DATE",Item_Date);
			AdddataMap.put("Dealer",Dealer);			
			returnFlag = OBJ.AddItem(AdddataMap);
			System.out.println("return falg in master servlet::"+returnFlag);
			if(returnFlag.equalsIgnoreCase("SUCCESS")){
				request.setAttribute("returnFlag", returnFlag);
				request.getRequestDispatcher("Maint.jsp").forward(request,response);			
			}
			else{
				request.setAttribute("returnFlag", returnFlag);
				request.getRequestDispatcher("Maint.jsp").forward(request,response);
				}
			
		}
		/* FOR ADD ITEM POP UP*/
		else if(Event.equalsIgnoreCase("ADDITEM_POPUP"))
		{
			System.out.println("Inside Master Servlet Add item-POPUP event");
			String Item_Name = request.getParameter("I_name");
			String M_R_P = request.getParameter("M_R_P");
			String rate = request.getParameter("rate");
			String UNIT = request.getParameter("UNIT_1");
			String Item_Date = request.getParameter("ITEM_DATE");
			String Dealer = request.getParameter("Dealer_1");
			System.out.println("Dealer Name-->popup"+Dealer);
			if(Dealer.equalsIgnoreCase("")||Dealer.equalsIgnoreCase(null))
			{
				System.out.println("inside null check-popup");
				Dealer="SBA";
			}
			AdddataMap.put("Event",Event);
			AdddataMap.put("Item_Name",Item_Name);
			AdddataMap.put("M_R_P",M_R_P);
			AdddataMap.put("rate",rate);
			AdddataMap.put("UNIT",UNIT);
			AdddataMap.put("ITEM_DATE",Item_Date);
			AdddataMap.put("Dealer",Dealer);
			returnFlag_popup = OBJ.AddItem_popup(AdddataMap);
			System.out.println("return flag in POPUP master servlet::"+returnFlag_popup);
			if(returnFlag_popup.equalsIgnoreCase("SUCCESS")){
				request.setAttribute("returnFlag_popup", returnFlag_popup);
				request.getRequestDispatcher("additem.jsp").forward(request,response);			
			}
			else{
				request.setAttribute("returnFlag_popup", returnFlag_popup);
				}
			
		}
		else if((Event.equalsIgnoreCase("SEARCHITEM")))
		{
			String Search_Item_name = request.getParameter("item_Name_3");
			String Item_No_2 = request.getParameter("I_no_2");
			String Item_Name_2 = request.getParameter("I_name_2");
			String M_R_P_2 = request.getParameter("M_R_P_2");
			String rate_2 = request.getParameter("rate_2");
			String UNIT_2 = request.getParameter("I_Unit_2");
			String Dealer_2 = request.getParameter("I_Dealer_2");
			
			if(Dealer_2.equalsIgnoreCase("")||Dealer_2.equalsIgnoreCase(null))
			{
				System.out.println("inside null check");
				Dealer_2="SBA";
			}
			
			
			System.out.println("Search_Item_name-->"+Search_Item_name);
			
			editdatamap.put("Event",Event);
			editdatamap.put("Item_No_2",Item_No_2);
			editdatamap.put("Item_Name_2",Item_Name_2);
			editdatamap.put("M_R_P_2",M_R_P_2);
			editdatamap.put("rate_2",rate_2);
			editdatamap.put("UNIT_2",UNIT_2);
			//editdatamap.put("Item_Date_2",Item_Date_2);
			editdatamap.put("Dealer_2",Dealer_2);
			modifyreturnFlag= OBJ.EditItem(editdatamap);
			
			if(modifyreturnFlag.equalsIgnoreCase("SUCCESS")){
				request.setAttribute("modifyreturnFlag", modifyreturnFlag);
				request.getRequestDispatcher("Maint.jsp").forward(request,response);			
			}
			else{
				response.sendRedirect("Maint.jsp");
			}
		}
		/*Modify Item JSP form*/
		
		else if((Event.equalsIgnoreCase("POPUP_MODIFYITEM")))
		{
			String Search_Item_name = request.getParameter("item_Name_3");
			String Item_No_2 = request.getParameter("I_no_2");
			String Item_Name_2 = request.getParameter("I_name_2");
			String M_R_P_2 = request.getParameter("M_R_P_2");
			String rate_2 = request.getParameter("rate_2");
			String UNIT_2 = request.getParameter("I_Unit_2");
			//String Item_Date_2 = request.getParameter("ITEM_DATE");
			String Dealer_2 = request.getParameter("I_Dealer_2");
			
			if(Dealer_2.equalsIgnoreCase("")||Dealer_2.equalsIgnoreCase(null))
			{
				System.out.println("inside null check");
				Dealer_2="SBA";
			}
			
			
			System.out.println("Search_Item_name-->"+Search_Item_name);
			
			editdatamap.put("Event",Event);
			editdatamap.put("Item_No_2",Item_No_2);
			editdatamap.put("Item_Name_2",Item_Name_2);
			editdatamap.put("M_R_P_2",M_R_P_2);
			editdatamap.put("rate_2",rate_2);
			editdatamap.put("UNIT_2",UNIT_2);
			editdatamap.put("Dealer_2",Dealer_2);
			modifyreturnFlag= OBJ.EditItem(editdatamap);
			
			if(modifyreturnFlag.equalsIgnoreCase("SUCCESS")){
				request.setAttribute("modifyreturnFlag", modifyreturnFlag);
				request.getRequestDispatcher("Edit_item.jsp").forward(request,response);			
			}
			else{
				request.setAttribute("modifyreturnFlag", modifyreturnFlag);
			}
		}
		
		else if((Event.equalsIgnoreCase("DELETEITEM")))
		{
			String Item_No_4 = request.getParameter("I_no_4");
			Deletedatamap.put("Item_No_4",Item_No_4);
			deletereturnFlag= OBJ.DeleteItem(Deletedatamap);
			if(deletereturnFlag.equalsIgnoreCase("SUCCESS")){
				request.setAttribute("deletereturnFlag", deletereturnFlag);
				request.getRequestDispatcher("Maint.jsp").forward(request,response);			
			}
			else{
				response.sendRedirect("Maint.jsp");
			}
			
		}
		
		/*else if ((Event.equalsIgnoreCase("GETTOTALBILL")))
		{
			String fromdate = request.getParameter("from_date");
			String todate = request.getParameter("to_date");
					
					
			gettotbillmap.put("Event",Event);
			gettotbillmap.put("fromdate",fromdate);
			gettotbillmap.put("todate",todate);
			
			gettotbillmap= OBJ.gettotbill(gettotbillmap);
			
			
			
			int gettotal = (Integer)gettotbillmap.get("tot");
            request.setAttribute("gettotal", gettotal);
            request.setAttribute("fromdate", fromdate);
            request.setAttribute("todate", todate);
            request.getRequestDispatcher("Maint.jsp").forward(request, response);
			
		}*/
		else if((Event.equalsIgnoreCase("GETPASSCHANGE")))
		{
			System.out.println("Inside MasterServlet");
			String Hint_maint = request.getParameter("Hint_maint");
			String new_pass1 = request.getParameter("new_pass1");
			String new_pass2 = request.getParameter("new_pass2");
						
			
					
			changepass.put("Event",Event);
			changepass.put("Hint_maint",Hint_maint);
			changepass.put("new_pass1",new_pass1);
			changepass.put("new_pass2",new_pass2);
			
			passwordreturnFlag= OBJ.changepass(changepass);
			
			if(passwordreturnFlag.equalsIgnoreCase("SUCCESS")){
				request.setAttribute("passwordreturnFlag", passwordreturnFlag);
				request.getRequestDispatcher("Maint.jsp").forward(request,response);			
			}
			else{
				response.sendRedirect("Maint.jsp");
			}
		}
		
	}   	  	    
}