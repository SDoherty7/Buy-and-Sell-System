package org.wapp;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*; 
import javax.servlet.annotation.WebServlet;
import org.wappbean.Item;
import java.sql.*;
import oracle.jdbc.driver.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/Home")
public class Home extends HttpServlet {  
   private static final long serialVersionUID = 1L;   

   public void doGet(HttpServletRequest req, HttpServletResponse res)
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
         
         
         String search = req.getParameter("search");
         String category1 =(String) req.getParameter("category");
         
         System.out.println("Category = " + category1);
         
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
		     if(search == null && category1 == null){
		       String sql ="SELECT ID, TITLE, CATEGORY, DESCRIPTION, BID, USERNAME  FROM SDOHERTYADVERTISEDATA";
		       PreparedStatement stmt1 = con.prepareStatement(sql);
		       ResultSet rs1 = stmt1.executeQuery();
		        List<Item> list = new ArrayList<Item>();
		        while (rs1.next()) {
		        	int id = rs1.getInt("ID");
		            String title = rs1.getString("TITLE");
		            String category = rs1.getString("CATEGORY");
		            String description = rs1.getString("DESCRIPTION");
		            int bid = rs1.getInt("BID");
		            String username = rs1.getString("USERNAME");
		            Item item = new Item(title, category, description, bid, id, username);
		            item.setTitle(title);
		            item.setCategory(category);
		            item.setDescription(description);
		            item.setBid(bid);
		            item.setId(id);
		            item.setUsername(username);
		            list.add(item);
		        }
		        req.setAttribute("listAdverts", list);
		        
		        // Forward to /WEB-INF/Home.jsp
		        RequestDispatcher dispatcher = req.getServletContext()
		                .getRequestDispatcher("/Home.jsp");
		        dispatcher.forward(req, res);
		     }
	 	else if(search != null){
	    	 String sql ="SELECT * FROM SDOHERTYADVERTISEDATA where TITLE LIKE ? OR DESCRIPTION LIKE ?";
		       PreparedStatement stmt1 = con.prepareStatement(sql);
		       stmt1.setString(1,"%" + search +"%");
		       stmt1.setString(2,"%" + search +"%");
		       
		       ResultSet rs1 = stmt1.executeQuery();
		       List<Item> list = new ArrayList<Item>();
		        while (rs1.next()) {
		        	int id = rs1.getInt("ID");
		            String title = rs1.getString("TITLE");
		            String category = rs1.getString("CATEGORY");
		            String description = rs1.getString("DESCRIPTION");
		            int bid = rs1.getInt("BID");
		            String username = rs1.getString("USERNAME");
		            Item item = new Item(title, category, description, bid, id, username);
		            item.setTitle(title);
		            item.setCategory(category);
		            item.setDescription(description);
		            item.setBid(bid);
		            item.setId(id);
		            item.setUsername(username);
		            list.add(item);
		        } 
		        	req.setAttribute("listAdverts", list);
		        
		        // Forward to /WEB-INF/Home.jsp
		        RequestDispatcher dispatcher = req.getServletContext()
		                .getRequestDispatcher("/Home.jsp");
		        dispatcher.forward(req, res);
	     	}	 
		     
	 	else if(category1 != null){
	    	 String sql ="SELECT * FROM SDOHERTYADVERTISEDATA where CATEGORY LIKE ? ";
		       PreparedStatement stmt1 = con.prepareStatement(sql);
		       stmt1.setString(1,category1 +"%");
		       
		       		
		       ResultSet rs1 = stmt1.executeQuery();
		       List<Item> list = new ArrayList<Item>();
		        while (rs1.next()) {
		        	int id = rs1.getInt("ID");
		            String title = rs1.getString("TITLE");
		            String category = rs1.getString("CATEGORY");
		            String description = rs1.getString("DESCRIPTION");
		            int bid = rs1.getInt("BID");
		            String username = rs1.getString("USERNAME");
		            Item item = new Item(title, category, description, bid, id, username);
		            item.setTitle(title);
		            item.setCategory(category);
		            item.setDescription(description);
		            item.setBid(bid);
		            item.setId(id);
		            item.setUsername(username);
		            list.add(item);
		        } 
		        	req.setAttribute("listAdverts", list);
		        
		        // Forward to /WEB-INF/Home.jsp
		        RequestDispatcher dispatcher = req.getServletContext()
		                .getRequestDispatcher("/Home.jsp");
		        dispatcher.forward(req, res);
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
   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse res)
           throws ServletException, IOException {
       doGet(req, res);
   }
   
}