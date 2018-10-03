package org.wapp;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*; 
import javax.servlet.annotation.WebServlet;
import java.sql.*;
import oracle.jdbc.driver.*;

@WebServlet("/AdvertServlet")
public class AdvertServlet extends HttpServlet {  
   private static final long serialVersionUID = 1L;   

   public void doPost(HttpServletRequest req, HttpServletResponse res)
      throws ServletException, IOException {
         res.setContentType("text/html");
         PrintWriter out = res.getWriter();
         
         HttpSession session = req.getSession(true);
         String user = (String) session.getAttribute("theUser");
         if(user==null){
        	 req.setAttribute("error","You are not authorised to perform this action. Please first login or register");
	    	   RequestDispatcher rd=req.getRequestDispatcher("/index.jsp");            
	    	   rd.include(req, res);
         } else{
             String title = req.getParameter("title");
             String category = (String) req.getParameter("category");
             String description = (String) req.getParameter("description");
             
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
    		       String sql ="INSERT INTO SDOHERTYADVERTISEDATA values(?,?,?,?,?,?,null)";
    		       PreparedStatement stmt1 = con.prepareStatement(sql);
    		       
    		       int id = (int) System.currentTimeMillis(); 
    		       stmt1.setInt(1,id);
    		       stmt1.setString(2,title);
    		       stmt1.setString(3,category);
    		       stmt1.setString(4,description);
    		       stmt1.setInt(5,0);
    		       stmt1.setString(6,user);
    		       
    		       int rs1= stmt1.executeUpdate();
    		       
    		       if(rs1 > 0) {
    		           out.println("<html><head><title>Advertisement Submission</title></head>");
    		           out.println("<body><h1>Advertisement Submission</h1>");
    		    	   out.println("Your advert has been successfully submitted  ");
    		    	   out.println("<a href='./Home'>Return Home</a>");
    		       }
    		       else{
    		    	   out.println("Error please try again");
    		    	   res.sendRedirect("./registration.html");
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