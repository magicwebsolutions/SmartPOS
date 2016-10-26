import java.sql.Connection;
import java.sql.DriverManager;
public class DBConnection {
	public static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	public static String JDBC_URL = "jdbc:mysql://localhost:3306/BILLING";
	public static String USER_NAME = "root";
	public static String PASSWORD = "1234";
	public static String query = "";
	public static Connection conn =null;
	public static Connection getConnection(){
		try{
		Class.forName(JDBC_DRIVER).newInstance();	
		conn = DriverManager.getConnection(JDBC_URL,USER_NAME,PASSWORD);
		//String query ="Select id from user_name";
		System.out.println("My Query Output-->"+query);
		System.out.println("connection estalished-->"+conn.getCatalog());
		System.out.println("connection estalished-->"+conn.getMetaData());
		System.out.println("connection estalished-->"+conn.getMetaData());
		
		
		}catch(Exception e){
			e.printStackTrace();
			
		}
return conn;
}
}
