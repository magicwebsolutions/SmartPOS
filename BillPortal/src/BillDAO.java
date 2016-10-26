import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.mysql.jdbc.CallableStatement;


public class BillDAO {
	public String loadUnit(String dataString){
		//int count = 0;
		StringBuffer Unit_Buffer = null;
		String Unit ="";
		System.out.println("dataString in loadUnit-->"+dataString);
		Connection conn =null;
		String query = "SELECT ITEM_NO,ITEM_UNIT,ITEM_MRP,ITEM_RATE AS SELL_PRICE FROM ITEM_MASTER WHERE ITEM_NAME = ?";
		ResultSet rSet = null;
		int i =0;
		PreparedStatement psmt = null;
		try{
			//Unit  = new StringBuffer();
			Unit_Buffer = new StringBuffer();
			conn = DBConnection.getConnection();
			
			 psmt = conn.prepareStatement(query);
			System.out.println("Query for for search item--->"+query);
			psmt.setString(1,dataString);
		
			 rSet=psmt.executeQuery();
			while(rSet.next()){
				Unit_Buffer.append(rSet.getString("ITEM_NO"));
				Unit_Buffer.append("~");
				Unit_Buffer.append(rSet.getString("ITEM_UNIT"));
				Unit_Buffer.append("~");
				Unit_Buffer.append(rSet.getString("SELL_PRICE"));
				Unit_Buffer.append("~");
				Unit_Buffer.append(rSet.getString("ITEM_MRP"));
				Unit_Buffer.append("$");		
			}
			Unit = Unit_Buffer.toString();
			
		}catch(Exception e){
			System.out.println("Something went wrong during Unit........");
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
	
	public String loadSellPrice(String dataString){
		//int count = 0;
		StringBuffer Unit_Buffer = null;
		String sellPrice ="0";
		System.out.println("dataString in loadUnit-->"+dataString);
		Connection conn =null;
		String query = "SELECT ITEM_RATE AS SELL_PRICE FROM ITEM_MASTER WHERE ITEM_NAME = ?";
		ResultSet rSet = null;
		int i =0;
		PreparedStatement psmt = null;
		try{
			//Unit  = new StringBuffer();
			Unit_Buffer = new StringBuffer();
			conn = DBConnection.getConnection();
			
			 psmt = conn.prepareStatement(query);
			System.out.println("Query for for search item--->"+query);
			psmt.setString(1,dataString);
		
			 rSet=psmt.executeQuery();
			while(rSet.next()){
				sellPrice = rSet.getString("SELL_PRICE");
					
			}
			
			
		}catch(Exception e){
			System.out.println("Something went wrong during Unit........");
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
		System.out.println("Unit in Unit method-->"+sellPrice);
		return sellPrice;
		
	}
	public String validateItem(String dataString){
		int count = 0;
		String returnFlag = "FALSE";
		System.out.println("dataString in loadPrice-->"+dataString);
		Connection conn =null;
		String query = "SELECT COUNT(*)AS COUNT FROM ITEM_MASTER WHERE ITEM_NAME = ? AND ITEM_NO IS NOT NULL";
		ResultSet rSet = null;
		int i =0;
		PreparedStatement psmt = null;
		try{
			//Unit  = new StringBuffer();
			conn = DBConnection.getConnection();
			
			 psmt = conn.prepareStatement(query);
			System.out.println("Query for for search item--->"+query);
			psmt.setString(1,dataString);
		
			 rSet=psmt.executeQuery();
			while(rSet.next()){
				
				count=rSet.getInt("COUNT");
						
			}
			
			
		}catch(Exception e){
			System.out.println("Something went wrong during Unit........");
			e.printStackTrace();
			
		}finally{
			
				if(count==0){
					returnFlag = "FALSE";
				}else if(count>0){
					returnFlag = "TRUE";
				}
			
		}
		System.out.println("returnFlag in validate item method-->"+returnFlag);
		return returnFlag;
		
	}
	
	
	public String print_Save(String dataString){
		int count = 0;
		String returnFlag = "FALSE";
		System.out.println("dataString in loadPrice-->"+dataString);
		Connection conn =null;
		String query = "SELECT COUNT(*)AS COUNT FROM ITEM_MASTER WHERE ITEM_NAME = ? AND ITEM_NO IS NOT NULL";
		ResultSet rSet = null;
		int i =0;
		PreparedStatement psmt = null;
		try{
			//Unit  = new StringBuffer();
			conn = DBConnection.getConnection();
			
			 psmt = conn.prepareStatement(query);
			System.out.println("Query for for search item--->"+query);
			psmt.setString(1,dataString);
		
			 rSet=psmt.executeQuery();
			while(rSet.next()){
				
				count=rSet.getInt("COUNT");
						
			}
			
			
		}catch(Exception e){
			System.out.println("Something went wrong during Unit........");
			e.printStackTrace();
			
		}finally{
			
				if(count==0){
					returnFlag = "FALSE";
				}else if(count>0){
					returnFlag = "TRUE";
				}
			
		}
		System.out.println("returnFlag in validate item method-->"+returnFlag);
		return returnFlag;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String loadall(String dataString){
		//int count = 0;
		StringBuffer Unit_Buffer = null;
		String Unit ="";
		System.out.println("dataString in loadUnit-->"+dataString);
		Connection conn =null;
		String query = "SELECT ITEM_NO,ITEM_NAME FROM ITEM_MASTER WHERE ITEM_NAME = ?";
		ResultSet rSet = null;
		
		int i =0;
		PreparedStatement psmt = null;
		try{
			//Unit  = new StringBuffer();
			Unit_Buffer = new StringBuffer();
			conn = DBConnection.getConnection();
			
			 psmt = conn.prepareStatement(query);
			System.out.println("Query for for search loadall--->"+query);
			psmt.setString(1,dataString);
			//psmt.setString(2,dataString);
			/*psmt.setString(3,dataString);
			psmt.setString(4,dataString);
			psmt.setString(5,dataString);*/
		
			 rSet=psmt.executeQuery();
			while(rSet.next()){
				
				Unit_Buffer.append(rSet.getString("ITEM_NO"));
				Unit_Buffer.append("~");
				Unit_Buffer.append(rSet.getString("ITEM_NAME"));
				/*Unit_Buffer.append("~");
				Unit_Buffer.append(rSet.getString("ITEM_MRP"));
				Unit_Buffer.append("~");
				Unit_Buffer.append(rSet.getString("ITEM_UNIT"));
				Unit_Buffer.append("~");
				Unit_Buffer.append(rSet.getString("ITEM_DEALER"));	*/				
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
	
	public String add_Billrecord(HashMap lMap){
		System.out.println("<---------Inside add_Billrecord----->");
		System.out.println("HashMap in add_Billrecord::"+lMap);
		Connection conn =null;
		PreparedStatement psmt =null;
		String itemName[]=null;
		String deleteStatus =null;
		int billInset = 0;
		String return_Flag = null;
		int totalNumberBills = 0;
		int totalInserted = 0;
		String billMasFlag = null;
		JPrint jPrinterObj =null;
		boolean pdf_flag = false;
		try{
			deleteStatus=deleteBillrecord();
			if(deleteStatus.equals("TRUE")){
			itemName = (String[]) lMap.get("itemName");
			if(itemName!=null){
				totalNumberBills=itemName.length;
			}
			lMap.put("TOTAL_BILL_COUNT",totalNumberBills);
			for(int i=0;i<totalNumberBills;i++){
				lMap.put("CURRENT_COUNT",i);
				billInset = processBillInfo(lMap);
				if(totalInserted==1){
					totalInserted = totalInserted+billInset;
				}else{
					totalInserted = totalInserted+billInset;
				}
			}
			System.out.println("After insserting in bill_info count::"+totalInserted);
			if(totalNumberBills>0 && totalNumberBills==totalInserted){
				billMasFlag = addBillMaster(lMap);
			}
			else{
				billMasFlag = "False";
			}
			if(billMasFlag.equals("True")){
				//call pdf generation and download..
				jPrinterObj =new JPrint();
				
				pdf_flag = jPrinterObj.printJob(lMap);
				System.out.println("BILL GENERATION STATUS::"+pdf_flag);
				return_Flag ="TRUE";
			}
			else{
				return_Flag ="FAIL";
			}
			}
			else{
				deleteStatus = "FALSE";
				return_Flag ="FAIL";
			}
		}catch(Exception e){
			e.printStackTrace();
			
		}finally{
			try{
				//System.out.println("total record inserted in bill_info table----->"+total_recd_entd);
				if(psmt!=null){
					psmt.close();
					System.out.println("<-----prepared statement closed--->");
				}if(conn!=null){
					conn.close();
					System.out.println("<-----connection statement closed--->");
					
				}
				
			}catch(Exception e){
				System.out.println("<-----Something went wrong in finally--->");
				e.printStackTrace();
				
			}
		}
		return return_Flag;
		/*
		String return_Flag="False";
		int bill_Seq = 0;
		int total_Cnt =0;
		int no_Of_Rows = 0;
		int total_recd_entd = 0;
		String data  ="";
		HashMap data_Map = null;
		
		String insert_Qry = "";
		
		ArrayList list = (ArrayList)lMap.get("BILL_LIST");
		
		try{
			insert_Qry = "INSERT INTO BILL_INFO(BILL_SEQ,BILL_ID,ITEM_ID,ITEM_NAME,ITEM_QTY,ITEM_UNIT,ITEM_PRICE,BILL_DATE) VALUES(?,?,?,?,?,?,?,SYSDATE())";
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(insert_Qry);
			System.out.println(list.get(0).toString());
			total_Cnt =Integer.valueOf(lMap.get("TOTAL_COUNT").toString());
			System.out.println("Total count in BILLDAO add_Billrecord-->"+total_Cnt);
			System.out.println("Array list size-->"+(list.size()-1));
			if(total_Cnt>0){
				if(total_Cnt==(list.size())){
				for(int i=0;i<total_Cnt;i++){
					//data_Map = new HashMap();
					data_Map = (HashMap)list.get(i);
					System.out.println("HashMap in order-->"+data_Map);
					//BILL_SEQ
					bill_Seq = (data_Map.get("BILL_SEQ")=="" || data_Map.get("BILL_SEQ")==null)? (Integer)0 : (Integer)data_Map.get("BILL_SEQ");
					psmt.setInt(1, bill_Seq);
					
					//BILL_ID
					data =((String)lMap.get("BILL_NUMBER")=="" || (String)lMap.get("BILL_NUMBER")==null)? "0":((String)lMap.get("BILL_NUMBER")).trim();
					psmt.setInt(2,bill_Seq);
					
					//ITEM_ID
					data =((String)data_Map.get("Item_id_"+i)=="" || (String)data_Map.get("Item_id_"+i)==null)? "0":((String)data_Map.get("Item_id_"+i)).trim();
					psmt.setInt(3, Integer.parseInt(data));
					
					//ITEM_NAME
					data =((String)data_Map.get("Item_Name_"+i)=="" || (String)data_Map.get("Item_Name_"+i)==null)? "-":((String)data_Map.get("Item_Name_"+i)).trim();
					psmt.setString(4, data);
					
					//ITEM_QTY
					data =((String)data_Map.get("Quantity_"+i)=="" || (String)data_Map.get("Quantity_"+i)==null)? "0":((String)data_Map.get("Quantity_"+i)).trim();
					psmt.setInt(5, Integer.parseInt(data));
					
					//Unit_
					data =((String)data_Map.get("Unit_"+i)=="" || (String)data_Map.get("Unit_"+i)==null)? "-":((String)data_Map.get("Unit_"+i)).trim();
					psmt.setString(6,data);
					
					//Price_
					data =((String)data_Map.get("Price_"+i)=="" || (String)data_Map.get("Price_"+i)==null)? "0":((String)data_Map.get("Price_"+i)).trim();
					psmt.setInt(7, Integer.parseInt(data));
					//8--SYSDATE
					no_Of_Rows = psmt.executeUpdate();
					if(no_Of_Rows>0){
						total_recd_entd = total_recd_entd+no_Of_Rows;
						no_Of_Rows = 0;
						System.out.println("........ONE RECORD INSERTED.....");
					}else{
						total_recd_entd = 0;
						System.out.println("........NO RECORDS ENTERED.....");
					}
					
				}
			}
				
			}
			else{
				System.out.println("........NO RECORDS FROM HASHMAP.....");
				System.out.println("........NO DB HITS.....");
				total_recd_entd = 0;
			}
			if(total_Cnt == total_recd_entd){
				System.out.println(total_recd_entd+"::records successfully inserted return_flag=TRUE");
				billMasFlag = addBillMaster(lMap);
				if(billMasFlag.equals("True")){
				return_Flag="TRUE";
				}else{
					return_Flag="False";
				}
			}
			else{
				System.out.println("records not inserted return_flag=FALSE");
				deleteStatus = deleteBillrecord();
				System.out.println("records deleted in billinfo table-->"+deleteStatus);
				return_Flag="False";
			}
		}catch(Exception e){
			return_Flag="False";
			System.out.println("Something went wrong--->"+e);
			e.printStackTrace();
			
		}finally{
			try{
				System.out.println("total record inserted in bill_info table----->"+total_recd_entd);
				if(psmt!=null){
					psmt.close();
					System.out.println("<-----prepared statement closed--->");
				}if(conn!=null){
					conn.close();
					System.out.println("<-----connection statement closed--->");
					
				}
				
			}catch(Exception e){
				System.out.println("<-----Something went wrong in finally--->");
				e.printStackTrace();
				
			}
			
		}
		return return_Flag;
		*/
	}
	public String deleteBillrecord(){
		System.out.println("<---------Inside deleteBillrecord----->");
		String return_Flag="False";
		int bill_Seq = 0;
		int total_Cnt =0;
		int no_Of_Rows = 0;
		int total_recd_entd = 0;
		String data  ="";
		HashMap data_Map = null;
		Connection conn =null;
		ResultSet lRst = null;
		PreparedStatement psmt =null;
		String insert_Qry = "";
		int rec_Count = 0;
		try{
			insert_Qry ="SELECT COUNT(*) AS COUNT FROM BILL_INFO";
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(insert_Qry);
			lRst = psmt.executeQuery();
			if(lRst!=null){
				while(lRst.next()){
					rec_Count = lRst.getInt("COUNT");
					
				}
				
			}
			if(rec_Count>0){
			insert_Qry = "DELETE FROM BILL_INFO";
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(insert_Qry);
			no_Of_Rows = psmt.executeUpdate();
			if(no_Of_Rows >0){
				System.out.println(no_Of_Rows+"::records successfully deleted return_flag=TRUE");
				return_Flag="TRUE";
			}
			}else{
				System.out.println("No rows to delete..");
				return_Flag="TRUE";
			}
		}catch(Exception e){
			return_Flag="False";
			System.out.println("Something went wrong--->"+e);
			e.printStackTrace();
			
		}finally{
			try{
				
				if(lRst!=null){
					lRst.close();
				}
				if(psmt!=null){
					psmt.close();
					System.out.println("<-----prepared statement closed--->");
				}if(conn!=null){
					conn.close();
					System.out.println("<-----connection statement closed--->");
					
				}
				
			}catch(Exception e){
				System.out.println("<-----Something went wrong in finally--->");
				e.printStackTrace();
				
			}
			
		}
		return return_Flag;
		
	}
	//added for billmaster insertion
	public String addBillMaster(HashMap lMap){
		System.out.println("<---------Inside addBillMaster----->");
		String return_Flag="False";
		//ArrayList list=(ArrayList)lMap.get("BILL_LIST");
		int totalCnt = 0;
		String billAmt = "";
		String inputDt = "";
		String inputID = "";
		String user = "";
		String billNo = "";
		/*
		if(list.get(1)!=null){
			user = (String)list.get(1);
		}*/
		int bill_Seq = 0;
		int total_Cnt =0;
		int no_Of_Rows = 0;
		int total_recd_entd = 0;
		String data  ="";
		HashMap data_Map = null;
		Connection conn =null;
		ResultSet lRst = null;
		PreparedStatement psmt =null;
		String insert_Qry = "";
		int rec_Count = 0;
		try{
			totalCnt = Integer.valueOf(lMap.get("TOTAL_BILL_COUNT").toString());
			billAmt = (String)lMap.get("totalBilledAmt");
			inputID = (String)lMap.get("INPUTTER_ID");
			billNo = (String)lMap.get("billNumber");
			//inputDt = (String)lMap.get("");
			insert_Qry ="INSERT INTO BILL_MASTER(BILL_NO,BILL_AMT,INPUT_DT,INPUT_ID,TOTAL_IEMS)VALUES(?,?,SYSDATE(),?,?)";
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(insert_Qry);
			psmt.setString(1,billNo);
			psmt.setString(2,billAmt );
			psmt.setString(3,inputID);
			psmt.setInt(4,totalCnt);
			//psmt.setString(5, );
			no_Of_Rows = psmt.executeUpdate();
			if(no_Of_Rows>0){
				System.out.println("No. of records inserted in bill_master table--->"+no_Of_Rows);
				return_Flag = "True";
			}else{
				return_Flag = "False";
			}
		}catch(Exception e){
			return_Flag="False";
			System.out.println("Something went wrong--->"+e);
			e.printStackTrace();
			
		}finally{
			try{
				
				if(lRst!=null){
					lRst.close();
				}
				if(psmt!=null){
					psmt.close();
					System.out.println("<-----prepared statement closed--->");
				}if(conn!=null){
					conn.close();
					System.out.println("<-----connection statement closed--->");
					
				}
				
			}catch(Exception e){
				System.out.println("<-----Something went wrong in finally--->");
				e.printStackTrace();
				
			}
			
		}
		return return_Flag;
		
	}
public String getBillNumber(){
	System.out.println("inside getBillNumber...");
	String bill_Num = "0";
	Connection conn =null;
	ResultSet lRst = null;
	boolean flag = false;
	
	java.sql.CallableStatement call = null;
	try{
		conn = DBConnection.getConnection();
		call = conn.prepareCall("{?=call BILL_NUMBER()}");
		call.registerOutParameter(1,java.sql.Types.VARCHAR);
		flag = call.execute();
		System.out.println("BILL NUMBER FLAG::"+flag);
		if(flag){
			System.out.println("----------BILL NUMBER GENERATED----");
			bill_Num = call.getString(1);
		}else{
			System.out.println("----------BILL NUMBER NOT GENERATED----");
			bill_Num = call.getString(1);
		}
	}catch(Exception e){
	e.printStackTrace();	
	}
	System.out.println("Billnumber is -->"+bill_Num);
	return bill_Num;
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
		//psmt.setString(1,NullCheck(dataMap));
	
		 rSet=psmt.executeQuery();
		 if(rSet!=null){
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

public int processBillInfo(HashMap lMap){
	System.out.println("inside processbillinfo::lMap::"+lMap);
	int noUpdate = 0;
	String insert_Qry=null;
	Connection conn=null;
	PreparedStatement psmt =null;
	int i =0;
	try{
		i = (Integer)lMap.get("CURRENT_COUNT");
		
		System.out.println("Current sequence::"+i);
		insert_Qry = "INSERT INTO BILL_INFO(BILL_SEQ,BILL_ID,ITEM_ID,ITEM_NAME,ITEM_QTY,ITEM_UNIT,ITEM_PRICE,BILL_DATE,BILLED_AMT,SELL_PRICE) VALUES(?,?,?,?,?,?,?,SYSDATE(),?,?)";
		conn = DBConnection.getConnection();
		psmt = conn.prepareStatement(insert_Qry);
		psmt.setInt(1,i+1);

		psmt.setString(2,(String)lMap.get("billNumber"));
		psmt.setString(3,getArrayIndexValue((String[])lMap.get("itemId"),i));
		psmt.setString(4,getArrayIndexValue((String[])lMap.get("itemName"),i));
		psmt.setString(5,getArrayIndexValue((String[])lMap.get("quantity"),i));
		psmt.setString(6,getArrayIndexValue((String[])lMap.get("Unit"),i));
		psmt.setString(7,getArrayIndexValue((String[])lMap.get("MRP"),i));
		psmt.setString(8,getArrayIndexValue((String[])lMap.get("total"),i));
		psmt.setString(9,getArrayIndexValue((String[])lMap.get("sellprice"),i));
		noUpdate = psmt.executeUpdate();
		if(noUpdate==1){
			System.out.println("total record inserted::"+noUpdate);
		}
		else{
			System.out.println("No rows updated..");
			noUpdate =0;
		}
	}catch(Exception  e){
		e.printStackTrace();
		
	}finally{
		try{
			if(psmt!=null){
				psmt.close();
			}
			if(conn!=null){
				conn.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	return noUpdate;
}

public String getArrayIndexValue(String array[],int index){
	System.out.println("index::"+index);
	String returnStr = null;
	if(array==null){
		returnStr = null;
	}
	else if(array[index].toString()!=null && array[index].toString()!="" ){
		System.out.println("NOT NULL VALUE::"+array[index].toString());
		returnStr= array[index].toString();
		
	}
	else{
		returnStr = null;
	}
	return returnStr;
}
public StringBuffer returnQuickSearch(String dataMap){
	int count = 0;
	StringBuffer itemBuffer = null;
	System.out.println("itemname in returnQuickSearch-->"+dataMap);
	Connection conn =null;
	String query = "SELECT ITEM_NAME,ITEM_UNIT,ITEM_MRP,ITEM_RATE FROM ITEM_MASTER WHERE ITEM_NAME LIKE ('%"+dataMap+"%')";
	ResultSet rSet = null;
	int i =0;
	PreparedStatement psmt = null;
	try{
		itemBuffer  = new StringBuffer();
		conn = DBConnection.getConnection();
		
		 psmt = conn.prepareStatement(query);
		System.out.println("Query for for search item--->"+query);
		//psmt.setString(1,NullCheck(dataMap));
	
		 rSet=psmt.executeQuery();
		 if(rSet!=null){
		while(rSet.next()){
			
			if(count==0)
			{
				itemBuffer.append(rSet.getString("ITEM_NAME"));
				itemBuffer.append("~");
				itemBuffer.append(rSet.getString("ITEM_UNIT"));
				itemBuffer.append("~");
				itemBuffer.append(rSet.getString("ITEM_RATE"));
				itemBuffer.append("~");
				itemBuffer.append(rSet.getString("ITEM_MRP"));
				itemBuffer.append("#");
				count++;
			}
			else{
				itemBuffer.append(rSet.getString("ITEM_NAME"));
				itemBuffer.append("~");
				itemBuffer.append(rSet.getString("ITEM_UNIT"));
				itemBuffer.append("~");
				itemBuffer.append(rSet.getString("ITEM_RATE"));
				itemBuffer.append("~");
				itemBuffer.append(rSet.getString("ITEM_MRP"));
				itemBuffer.append("#");
				}
			
			
			
		}
		 }
		
	}catch(Exception e){
		System.out.println("Something went wrong during itemBuffer........");
		e.printStackTrace();
		
	}finally{
		
			try {
				if(itemBuffer.length()>0 && itemBuffer.charAt(itemBuffer.length()-1)=='#'){
					itemBuffer.deleteCharAt(itemBuffer.length()-1);
				}
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

}
