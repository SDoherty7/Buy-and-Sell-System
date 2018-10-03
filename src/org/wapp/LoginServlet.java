package org.wapp;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*; 
import javax.servlet.annotation.WebServlet;

import java.sql.*;
import oracle.jdbc.driver.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {  
   private static final long serialVersionUID = 1L;   

   public void doPost(HttpServletRequest req, HttpServletResponse res)
      throws ServletException, IOException {
         res.setContentType("text/html");
         PrintWriter out = res.getWriter();
         		
         String username = req.getParameter("username");
         String password = req.getParameter("password");
         
         
         Connection con = null;
         Statement stmt = null;
         ResultSet rs = null;
         String JDBCUrl = "jdbc:oracle:thin:@ee417.c7clh2c6565n.eu-west-1.rds.amazonaws.com:1521:EE417";
         String username1 = "ee_user";
         String password1 = "ee_pass";
         
         try {
             System.out.println("\nConnecting to the SSD Database......");
             Class.forName("oracle.jdbc.driver.OracleDriver");
             con = DriverManager.getConnection(JDBCUrl, username1, password1);
         }
         catch (Exception e) {
             System.out.println("\nAn error has occurred during the connection phase!  This is most likely due to your CLASSPATH being set wrong and the"
                     + " Oracle classes unable to be found.  Otherwise the database itself may be down.  Try telneting to port 1521 and see if it is up!");
             e.printStackTrace();
             System.exit(0);
         }   

	 	 try {
		     System.out.println("\nConnection Successful..... creating statement....");
		       String sql ="SELECT USERNAME,PASSWORD FROM SDOHERTYWEBAPP where USERNAME=? and PASSWORD=?";
		       PreparedStatement stmt1 = con.prepareStatement(sql);
		       stmt1.setString(1,username);
		       stmt1.setString(2,password);
		       ResultSet rs1= stmt1.executeQuery();
		       if(rs1.next()) {
		           out.println("<html><head><title>Login Page</title></head>");
		           out.println("<body><h1>Login Page</h1>");
		    	   out.println("User logged in successfully");
		    	   String id = rs1.getString("USERNAME");
		    	   out.println("User  " + id);
		    	   HttpSession session = req.getSession();
		    	   session.setAttribute("theUser", id);
		    	   res.sendRedirect("./Home");
		       }
		       else{
		    	   req.setAttribute("error","Invalid Username or Password");
		    	   RequestDispatcher rd=req.getRequestDispatcher("/index.jsp");            
		    	   rd.include(req, res);
		       }
	 	 	}
	      catch (SQLException e) {
		     System.out.println("\nAn error has occurred during the Statement/ResultSet phase.  Please check the syntax and study the Exception details!");
	             while (e != null) {
		         System.out.println(e.getMessage());
	                 e = e.getNextException();
		     }
	             System.exit(0);
	         }
	
	         finally {
		     try {    
		     if (rs != null) rs.close();
			 if (stmt != null) stmt.close();
			 if (con != null) con.close();
		     }
		     catch (Exception ex) {
		         System.out.println("An error occurred while closing down connection/statement"); 
	             }
	         }
         
         out.println("</body></html>");
         out.close();
   }
}