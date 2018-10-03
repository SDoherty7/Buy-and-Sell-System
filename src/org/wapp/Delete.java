package org.wapp;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*; 
import javax.servlet.annotation.WebServlet;

import org.wappbean.Item;
import java.sql.*;
import oracle.jdbc.driver.*;

@WebServlet("/Delete")
public class Delete extends HttpServlet {  
   private static final long serialVersionUID = 1L;   

   public void doPost(HttpServletRequest req, HttpServletResponse res)
      throws ServletException, IOException {
         res.setContentType("text/html");
         PrintWriter out = res.getWriter();
         out.println("<html><head><title>Place Bid</title></head>");
         out.println("<body>");
         
         HttpSession session = req.getSession(true);
         String user = (String) session.getAttribute("theUser");
         if(user==null){
        	 req.setAttribute("error","You are not authorised to perform this action. Please first login or register");
	    	   RequestDispatcher rd=req.getRequestDispatcher("/index.jsp");            
	    	   rd.include(req, res);
         } else{
         
	         String id = (String) req.getParameter("item");
	         
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
			     String sql ="SELECT USERNAME FROM SDOHERTYADVERTISEDATA WHERE ID=?";
			       PreparedStatement stmt1 = con.prepareStatement(sql);
			       stmt1.setString(1,id);
			       
			       ResultSet rs1 = stmt1.executeQuery();
			       if (rs1.next()) {
			        	String user1 = rs1.getString("USERNAME");
			        	if(user1.equals(user)){
			        		String sql1 ="DELETE FROM SDOHERTYADVERTISEDATA WHERE ID=?";
						       PreparedStatement stmt2 = con.prepareStatement(sql1);
						       stmt2.setString(1,id);
						       
						       int rs2= stmt2.executeUpdate();
						       
						       if(rs2 > 0) {
						    	   req.setAttribute("resp","The item has been removed feel free to contact the highest bidder  ");
						    	   RequestDispatcher rd=req.getRequestDispatcher("/Home.jsp");            
						    	   rd.include(req, res);
						       }
						       else{
						    	   req.setAttribute("resp","Error");
						    	   RequestDispatcher rd=req.getRequestDispatcher("/Home.jsp");            
						    	   rd.include(req, res);
						       }
			        		
			        	}
			        	else{
			        		req.setAttribute("resp","Invalid Permissions");
					    	   RequestDispatcher rd=req.getRequestDispatcher("/Home.jsp");            
					    	   rd.include(req, res);
			        	}
			       }
			       else{
			    	   req.setAttribute("resp","Error");
			    	   RequestDispatcher rd=req.getRequestDispatcher("/Home.jsp");            
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
	         
         }
         out.println("</body></html>");
         out.close();
   }
}