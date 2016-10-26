import java.util.*;
import java.sql.*;
public class LoginDAO {
	public String validateUser(HashMap l_Map){
		String returnFlag = "FALSE";
		String Loginquery ="";
		String hint_1=(String) l_Map.get("Hint");
		int count=0;
		try{
			Connection conn = DBConnection.getConnection();
			PreparedStatement psmt = null;
			ResultSet rSet=null;
		System.out.println("Entering validate user hashmap values---->"+l_Map);
		if(l_Map.get("flag").equals("Mainlogin"))
		{
		System.out.println("Entering Main login validation---->"+l_Map);
		
		Loginquery = "SELECT COUNT(*)as count FROM user_table WHERE USER_NAME= ? AND PASSWORD = ?";
		psmt=conn.prepareStatement(Loginquery);
		psmt.setString(1,(String)l_Map.get("USER_NAME") );
		psmt.setString(2,(String)l_Map.get("USER_PWD") );
		rSet = psmt.executeQuery();
		while(rSet.next()){
			count=rSet.getInt("count");
		}
		if(count>0){
			returnFlag ="TRUE";
		}
		}
		else if(l_Map.containsValue("Sublogin"))
		{
		System.out.println("Entering Sub login validation---->"+l_Map);
		System.out.println("Entering Sub login validation password---->"+l_Map.get("Password"));
		System.out.println("Entering Sub login validation RePassword---->"+l_Map.get("RePassword"));
		System.out.println("Entering Sub login validation Hint---->"+l_Map.get("Hint"));
		String key=null;
		Loginquery = "UPDATE user_table SET PASSWORD = ? WHERE HINT=?";
		String Hint="SELECT HINT FROM user_table";
		psmt=conn.prepareStatement(Hint);
		rSet=psmt.executeQuery();
		while(rSet.next()){
			key=rSet.getString("HINT");
			System.out.println("key is"+key);
		}
		if(hint_1.equalsIgnoreCase(key))
		{
		psmt=conn.prepareStatement(Loginquery);
		psmt.setString(1,(String)l_Map.get("Password") );
		psmt.setString(2,key);
		System.out.println("The value of Hint"+key);
		int a = psmt.executeUpdate();
		if(a==1)
		{
			returnFlag ="UPDATE_PASSWORD";
			System.out.println("Updated Password Successfully---->"+a);
		}
		}
		else
		{
			returnFlag ="wronghint";
			System.out.println("Updated Password unSuccessfully---->"+returnFlag);
			
			
		}
		}
		
		}
		catch(Exception e){
		System.out.println("inside exception......");
		e.printStackTrace();
		}
		System.out.println("returnFlag in loginDAO......"+returnFlag);
		return returnFlag;
		
	}

}
