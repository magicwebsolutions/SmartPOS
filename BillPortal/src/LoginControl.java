


import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class for Servlet: LoginControl
 *
 */
 public class LoginControl extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public LoginControl() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println();
		String validation_Status ="FALSE";
		String UserName=null;
		String Password=null;
		String Repassword=null;
		String Hint=null;
		HashMap h_map=null;
		PrintWriter out=response.getWriter();
		if(request.getParameter("UserName")!=null && request.getParameter("UserName")!="" && request.getParameter("User_pwd")!=null
				
			&& request.getParameter("User_pwd")!="")
		{
			System.out.println(request.getParameter("hint"));
			UserName= request.getParameter("UserName");
			System.out.println("USERNAME-->"+UserName);
			Password = request.getParameter("User_pwd");
			System.out.println("Password-->"+Password);
			h_map = new HashMap();
			h_map.put("flag", "Mainlogin");
			h_map.put("USER_NAME",UserName);
			h_map.put("USER_PWD",Password);
		}
		else
		{
			System.out.println("Enter sublogin ");
			Hint= request.getParameter("hint");
			System.out.println("Hint-->"+Hint);
			Password= request.getParameter("User_pwd1");
			System.out.println("Password-->"+Password);
			Repassword = request.getParameter("User_pwd2");
			System.out.println("Repassword-->"+Repassword);
			h_map = new HashMap();
			h_map.put("flag", "Sublogin");
			h_map.put("Hint", Hint);
			h_map.put("Password",Password);
			h_map.put("RePassword",Repassword);	
		}
		LoginDAO login =new LoginDAO();
		System.out.println("Going to enter LoginDAO class for checking user...");
		validation_Status = login.validateUser(h_map);
		System.out.println("Status "+validation_Status);
		if(validation_Status=="TRUE"){
			System.out.println("Login successfull for user "+UserName);
			request.setAttribute("status", "success");
			request.getRequestDispatcher("Login.jsp").forward(request, response);

		}
			else if(validation_Status=="wronghint"){
				System.out.println("Login Unsuccessfull for user22222 "+Hint);
				request.setAttribute("status", "wronghint");
				request.getRequestDispatcher("Login.jsp").forward(request, response);
			}
		
		
			else if(validation_Status=="UPDATE_PASSWORD"){
				System.out.println("Login Unsuccessfull for user4444 "+Hint);
				request.setAttribute("status", "updated");
				request.getRequestDispatcher("Login.jsp").forward(request, response);
			}
		
			else{
					System.out.println("Login Unsuccessfull for user333333 "+UserName);
					System.out.println("wrngggggggggggg");
				       out.print("alert('User or password incorrect');");
				       
					request.setAttribute("status", "failure");
					request.getRequestDispatcher("Login.jsp").forward(request, response);

		}
		}
	}   	  	    
