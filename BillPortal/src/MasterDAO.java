import java.util.HashMap;
import java.sql.*;


public class MasterDAO {
	/*public String AddItem(HashMap AdddataMap){
		String returnFlag = "FAIL";
		int itemid = 0;
		System.out.println("dataMap in AddItem-->"+AdddataMap);
		Connection conn =null;
		String query = "INSERT INTO ITEM_MASTER VALUES(?,?,?,?,?,SYSDATE(),?)";
		//String query2 =	"SELECT MAX(ITEM_NO) ITEM_NO FROM ITEM_MASTER";
		int rSet = 0;
		int i =0;
		PreparedStatement psmt = null;
		System.out.println("dataMap in masterDAO by goutham-->"+AdddataMap);
		try{
			conn = DBConnection.getConnection();
			
			 psmt = conn.prepareStatement(query);			 
			 //psmt = conn.prepareStatement(query2);
			 
			 rSet = psmt.executeUpdate();
			 //while(rSet.next()){
				 //System.out.println("Inside item_id generator query"+rSet);
				 //itemid = rSet.getInt("ITEM_NO");
				 //System.out.println("ItemId is -->"+itemid);
				//}
			 //psmt.setInt(1,(rSet.getInt(itemid)));
			psmt.setInt(1,Integer.parseInt(NullCheck((String)AdddataMap.get("Item_No"))));
			psmt.setString(2,NullCheck((String)AdddataMap.get("Item_Name")));
			psmt.setString(3,NullCheck((String)AdddataMap.get("UNIT")));
			psmt.setString(4,NullCheck((String)AdddataMap.get("M_R_P")));
			psmt.setString(5,NullCheck((String)AdddataMap.get("rate")));
			psmt.setString(6,NullCheck((String)AdddataMap.get("Dealer")));			
			 i=psmt.executeUpdate();
			 if(i>0){
				 System.out.println(i+"No of rows Inserted ......");
				 returnFlag = "SUCCESS"; 
			 }else{
				 System.out.println("no of rows Inserted see error log......");
				 returnFlag = "FAIL";
			 }
			
			
		}catch(Exception e){
			System.out.println("Something went wrong during Add item........");
			e.printStackTrace();
			
		}finally{
			
				try {
					if(psmt!=null){
					psmt.close();
					}
					if(conn!=null){
						conn.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				
				
			}
			
		}
		System.out.println("returnFlag in AddItem-->"+returnFlag);
		return returnFlag;
		
	}*/
	
public String AddItem(HashMap AdddataMap)
	{
		String returnFlag = "FAIL";
		int itemid = 0 ;
		int sai;
		Connection conn =null;
		ResultSet rSet = null;
		
		PreparedStatement psmt = null;
		PreparedStatement psmt2 = null;
			try{
			
			System.out.println("Inside Try block of add method");
			String query_for_add="SELECT MAX(ITEM_NO) ITEM_NO FROM ITEM_MASTER";
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(query_for_add);
			rSet = psmt.executeQuery();
			if(rSet.next()){
				System.out.println("inside first IF rset");
				 System.out.println("Inside item_id generator query"+rSet);
				 itemid = rSet.getInt(1);
				 System.out.println("ItemId is -->"+itemid);
				}
			 if(itemid>0)
			 {
				 int i =0;
				 System.out.println("Inside item_id greater than 0 condition");
				 sai= itemid+1;
				 System.out.println("Value of Sai-->"+sai);
				 String query_for_add2="INSERT INTO ITEM_MASTER VALUES(?,UPPER(?),?,UPPER(?),SYSDATE(),UPPER(?),?)";
				 conn = DBConnection.getConnection();
				 psmt2 = conn.prepareStatement(query_for_add2);
				 	psmt2.setInt(1,sai);
				 	psmt2.setString(2,NullCheck((String)AdddataMap.get("Item_Name")));
				 	psmt2.setString(3,NullCheck((String)AdddataMap.get("M_R_P")));
				 	psmt2.setString(4,NullCheck((String)AdddataMap.get("UNIT")));
				 	psmt2.setString(5,NullCheck((String)AdddataMap.get("Dealer")));
				 	psmt2.setString(6,NullCheck((String)AdddataMap.get("rate")));
				 	i=psmt2.executeUpdate();
				 	if(i>0)
				 		
				 	{
				 		returnFlag = "SUCCESS";
				 	}
				 				 }
			 else
			 {
				 System.out.println("Inside Else");
				 }
		}
			 
				catch(Exception e){
			System.out.println("Something went wrong during Add item........");
			e.printStackTrace();
			
		}finally{
			
				try {
					if(psmt!=null){
					psmt.close();
					}
					if(conn!=null){
						conn.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				
				
			}
		
			
		}
		System.out.println("returnFlag in AddItem-->"+returnFlag);
		return returnFlag;	
}


public String AddItem_popup(HashMap AdddataMap)
{
	String returnFlag_popup = "FAIL";
	int itemid = 0 ;
	int sai;
	System.out.println("dataMap in AddItem-->"+AdddataMap);
	Connection conn =null;
	ResultSet rSet = null;
	
	PreparedStatement psmt = null;
	PreparedStatement psmt2 = null;
	System.out.println("dataMap in masterDAO by goutham-->"+AdddataMap);
	try{
		
		System.out.println("Inside Try block of add method");
		String query_for_add="SELECT MAX(ITEM_NO) ITEM_NO FROM ITEM_MASTER";
		conn = DBConnection.getConnection();
		psmt = conn.prepareStatement(query_for_add);
		rSet = psmt.executeQuery();
		if(rSet.next()){
			System.out.println("inside first IF rset");
			 System.out.println("Inside item_id generator query"+rSet);
			 itemid = rSet.getInt(1);
			 System.out.println("ItemId is -->"+itemid);
			}
		 if(itemid>0)
		 {
			 int i =0;
			 System.out.println("Inside item_id greater than 0 condition");
			 sai= itemid+1;
			 System.out.println("Value of Sai-->"+sai);
			 String query_for_add2="INSERT INTO ITEM_MASTER VALUES(?,UPPER(?),?,UPPER(?),SYSDATE(),UPPER(?),?)";
			 conn = DBConnection.getConnection();
			 psmt2 = conn.prepareStatement(query_for_add2);
			 	psmt2.setInt(1,sai);
			 	psmt2.setString(2,NullCheck((String)AdddataMap.get("Item_Name")));
			 	psmt2.setString(3,NullCheck((String)AdddataMap.get("M_R_P")));
			 	psmt2.setString(4,NullCheck((String)AdddataMap.get("UNIT")));
			 	psmt2.setString(5,NullCheck((String)AdddataMap.get("Dealer")));
			 	psmt2.setString(6,NullCheck((String)AdddataMap.get("rate")));
			 	i=psmt2.executeUpdate();
			 	if(i>0)			 		
			 	{
			 		returnFlag_popup = "SUCCESS";
			 	}
			 				 }
		 else
		 {
			 System.out.println("Inside Else");
			 }
	}
		 
			catch(Exception e){
		System.out.println("Something went wrong during Add item........");
		e.printStackTrace();
		
	}finally{
		
			try {
				if(psmt!=null){
				psmt.close();
				}
				if(conn!=null){
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			
		}
	
		
	}
	System.out.println("returnFlag in AddItem-->"+returnFlag_popup);
	return returnFlag_popup;	
}

	

private int getInt(int sai) {
	// TODO Auto-generated method stub
	return 0;
}


public String NullCheck(String input){
		if(input==null || input==""){
			input="000000";
			
		}
		return input;
		
	}



public String EditItem(HashMap editdatamap) {
	String modifyreturnFlag = "FAIL";
	System.out.println("dataMap in EditItem-->"+editdatamap);
	Connection conn =null;
	String query = "UPDATE ITEM_MASTER SET ITEM_NAME=UPPER(?),ITEM_UNIT=UPPER(?),ITEM_MRP=?,ITEM_RATE=?,ITEM_DATE=SYSDATE(),ITEM_DEALER=UPPER(?) WHERE ITEM_NO=?"; 
	ResultSet rSet = null;
	int i =0;
	PreparedStatement psmt = null;
	try{
		conn = DBConnection.getConnection();
		psmt = conn.prepareStatement(query);
		System.out.println(psmt);
		System.out.println("After connection Establish");		 			 
		
		psmt.setString(1,NullCheck((String)editdatamap.get("Item_Name_2")));
		psmt.setString(2,NullCheck((String)editdatamap.get("UNIT_2")));
		psmt.setString(3,NullCheck((String)editdatamap.get("M_R_P_2")));
		psmt.setString(4,NullCheck((String)editdatamap.get("rate_2")));
		psmt.setString(5,NullCheck((String)editdatamap.get("Dealer_2")));
		psmt.setInt(6,Integer.parseInt(NullCheck((String)editdatamap.get("Item_No_2"))));
		i=psmt.executeUpdate();
		 if(i>0){
			 System.out.println(i+"No of rows Inserted ......");
			 modifyreturnFlag = "SUCCESS"; 
		 }else{
			 System.out.println("No rows Inserted for edit table see error log......");
			 modifyreturnFlag = "FAIL";
		 }
		
		
	}catch(Exception e){
		System.out.println("Something went wrong during Add item........");
		e.printStackTrace();
		
	}finally{
		
			try {
				if(psmt!=null){
				psmt.close();
				}
				if(conn!=null){
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			
		}
		
	}
	System.out.println("returnFlag in EditItem-->"+modifyreturnFlag);
	return modifyreturnFlag;
	
}



public String DeleteItem(HashMap Deletedatamap){
String deletereturnFlag = "FAIL";
Connection conn =null;
String query = "DELETE FROM ITEM_MASTER WHERE ITEM_NO=?";
ResultSet rSet = null;
int i =0;
PreparedStatement psmt = null;
try{
	conn = DBConnection.getConnection();			
	 psmt = conn.prepareStatement(query);			 			 
	psmt.setInt(1,Integer.parseInt(NullCheck((String)Deletedatamap.get("Item_No_4"))));
	 i=psmt.executeUpdate();
	 if(i>0){
		 System.out.println(i+"No of rows Deleted ......");
		 deletereturnFlag = "SUCCESS"; 
	 }else{
		 System.out.println("no of rows Deleted see error log......");
		 deletereturnFlag = "FAIL";
	 }
	
	
}catch(Exception e){
	System.out.println("Something went wrong during Delete item........");
	e.printStackTrace();
	
}finally{
	
		try {
			if(psmt!=null){
			psmt.close();
			}
			if(conn!=null){
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		
	}
	
}
System.out.println("returnFlag in Delete method-->"+deletereturnFlag);
return deletereturnFlag;
}

public HashMap gettotbill(HashMap gettotbillmap) {
	String returnFlag = "FAIL";
	
	System.out.println("gettotbillmap in EditItem-->"+gettotbillmap);
	HashMap gettotal = new HashMap();
	Connection conn =null;
	String query = "SELECT SUM(BILL_AMT) TOT_AMT FROM BILL_MASTER WHERE INPUT_DT between ? and ?"; 
	ResultSet rSet = null;
	int i =0;
	int sum=0;
	PreparedStatement psmt = null;
	try{
		conn = DBConnection.getConnection();
		psmt = conn.prepareStatement(query);
		System.out.println(psmt);
		System.out.println(query);
		System.out.println("After connection Establish");
		psmt.setString(1,((String)gettotbillmap.get("fromdate")));			
		psmt.setString(2,((String)gettotbillmap.get("todate")));
		rSet=psmt.executeQuery();
		while(rSet.next())
		{
			sum=rSet.getInt(1);
			System.out.println("Total is "+sum);
			System.out.println("gettotbillmap in GetTotalBillAmount-->"+gettotbillmap);			
		}
		gettotal.put("tot", sum);
		
							
	}catch(Exception e){
		System.out.println("Something went wrong during Bill Total........");
		e.printStackTrace();
		
	}finally{
		
			try {
				if(psmt!=null){
				psmt.close();
				}
				if(conn!=null){
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			
		}
		
	}
	System.out.println("gettotbillmap in EditItem-->"+gettotbillmap);
	return gettotal;
	
}












public StringBuffer itemBuffer(String dataMap){
		int count = 0;
		StringBuffer itemBuffer = null;
		System.out.println("dataMap in AddItem-->"+dataMap);
		Connection conn =null;
		String query = "SELECT ITEM_NAME FROM ITEM_MASTER WHERE ITEM_NAME LIKE ('%"+dataMap+"%')";
		ResultSet rSet = null;
		int i =0;
		PreparedStatement psmt = null;
		try{
			itemBuffer  = new StringBuffer();
			conn = DBConnection.getConnection();
			
			 psmt = conn.prepareStatement(query);
			System.out.println("Query for for search item--->"+query);
			 rSet=psmt.executeQuery();
			while(rSet.next()){
				
				if(count==0)
				{
					itemBuffer.append(rSet.getString("ITEM_NAME"));
					count++;
				}
				else{
					itemBuffer.append("~");
					itemBuffer.append(rSet.getString("ITEM_NAME"));
					}
				
				
				
			}
			
			
		}catch(Exception e){
			System.out.println("Something went wrong during itemBuffer........");
			e.printStackTrace();
			
		}finally{
			
				try {
					if(rSet!=null){
						rSet.close();
						
					}
					if(psmt!=null){
					psmt.close();
					}
					if(conn!=null){
						conn.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				
				
			}
			
		}
		System.out.println("itemBuffer in itemBuffer method-->"+itemBuffer);
		return itemBuffer;
		
	}

public String loadall_maint(String dataString){
	StringBuffer Unit_Buffer = null;
	String Unit ="";
	System.out.println("dataString in loadUnit-->"+dataString);
	Connection conn =null;
	String query = "SELECT ITEM_NO,ITEM_NAME,ITEM_MRP,ITEM_RATE,ITEM_UNIT,ITEM_DEALER FROM ITEM_MASTER WHERE ITEM_NAME = ?";
	ResultSet rSet = null;
	int i =0;
	PreparedStatement psmt = null;
	try{
		Unit_Buffer = new StringBuffer();
		conn = DBConnection.getConnection();
		
		 psmt = conn.prepareStatement(query);
		System.out.println("Query for for search item--->"+query);
		psmt.setString(1,dataString);
	
		 rSet=psmt.executeQuery();
		while(rSet.next()){
			
			Unit_Buffer.append(rSet.getString("ITEM_NO"));
			Unit_Buffer.append("~");
			Unit_Buffer.append(rSet.getString("ITEM_NAME"));
			Unit_Buffer.append("~");
			Unit_Buffer.append(rSet.getString("ITEM_MRP"));
			Unit_Buffer.append("~");
			Unit_Buffer.append(rSet.getString("ITEM_RATE"));
			Unit_Buffer.append("~");
			Unit_Buffer.append(rSet.getString("ITEM_UNIT"));
			Unit_Buffer.append("~");
			Unit_Buffer.append(rSet.getString("ITEM_DEALER"));
					
		}
		Unit = Unit_Buffer.toString();			
	}catch(Exception e){
		System.out.println("Something went wrong during Unit. OF LOADALL.......");
		e.printStackTrace();
		
	}finally{
		
			try {
				if(rSet!=null){
					rSet.close();
					
				}
				if(psmt!=null){
				psmt.close();
				}
				if(conn!=null){
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			
		}
		
	}
	System.out.println("Unit in Unit method-->"+Unit);
	return Unit;
	
}


public String changepass(HashMap changepass) {
	String passwordreturnFlag = "FAIL";
	Connection conn =null;
	ResultSet rSet = null;
	int i =0;
	String db_hint = null;
	
	PreparedStatement psmt = null;
	PreparedStatement psmt1 = null;
	try{
		System.out.println("Inside try");
		String hint_query="SELECT HINT FROM USER_TABLE";
		conn = DBConnection.getConnection();
		psmt = conn.prepareStatement(hint_query);
		rSet=psmt.executeQuery();
		
		if(rSet.next())
		{
			db_hint=rSet.getString(1);
			System.out.println("Hint provided-->"+db_hint);
		}
		if(db_hint!= null)
		{
			System.out.println("inside 2nd if");
			String l_db_hint=db_hint;
			System.out.println("l_db_hint-->"+l_db_hint);
			if(((String)changepass.get("new_pass1")).equalsIgnoreCase(((String)changepass.get("new_pass2"))))
			{
			String update_pass="UPDATE USER_TABLE SET PASSWORD=?,DATE=SYSDATE() WHERE HINT=?";
			conn = DBConnection.getConnection();
			psmt1 = conn.prepareStatement(update_pass);
			psmt1.setString(1,NullCheck((String)changepass.get("new_pass1")));
			psmt1.setString(2,l_db_hint);
			i=psmt1.executeUpdate();
			}
			
		}
			
		 if(i>0){
			 System.out.println("Password Updated successFully ......");
			 passwordreturnFlag = "SUCCESS"; 
		 }else{
			 System.out.println("Password Update Fail......");
			 passwordreturnFlag = "FAIL";
		 }
		
		
	}catch(Exception e){
		System.out.println("Something went wrong during change credential........");
		e.printStackTrace();
		
	}finally{
		
			try {
				if(psmt!=null){
				psmt.close();
				}
				if(conn!=null){
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			
		}
		
	}
	System.out.println("returnFlag in credential-->"+passwordreturnFlag);
	return passwordreturnFlag;
	
}



}


