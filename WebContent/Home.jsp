<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Buy and Sell System</title>
<link rel="stylesheet" type="text/css" href="style.css"/>
</head>
<body>
<div id="container">
<div id="header">
 <h2>Adverts</h2>
 </div>
 <form method="get" action="/SDwebapp/Home" name="searchfilter">
<br/>Search by keyword: <input type="text" name="search"/>
<input type="submit" value="Search"/>
</form>

<form method="get" action="/SDwebapp/Home" name="categoryfilter">
<br/>Filter by Category: <select name="category">
  <option value="Books">Books</option>
  <option value="Electronics">Electronics</option>
  <option value="Home">Home</option>
  <option value="Toys">Toys</option>
  <option value="Clothes">Clothes</option>
  <option value="Motoring">Motoring</option>
  <option value="Sports">Sports</option>
</select>
<input type="submit" value="Filter"/>
</form>

 <form method="get" action="/SDwebapp/Home" name="viewall">
<br/>View all items: 
<input type="submit" value="viewall"/>
</form>
<br/>
<a href="./item.html">Advertise a new item</a><br/><br/>

<%
	String resp_msg=(String)request.getAttribute("resp");  
	if(resp_msg!=null)
	out.println("<font color=red size=4px>"+resp_msg+"</font>");
%>
			  
			  
  <table>
  <tr>
          <th>Item</th>
          <th>Category</th>
          <th>Description</th>
          <th>Current Bid</th>
          <th>Item ID</th>
          <th>Seller</th>
          <th>Remove</th>
          <th>Place Bid</th>
       </tr>
       <c:forEach items="${listAdverts}" var="item" >
          <tr>
             <td>${item.title}</td>
             <td>${item.category}</td>
             <td>${item.description}</td>
             <td>${item.bid}</td>
             <td>${item.id}</td>
             <td>${item.username}</td>
             <td>
               <form method="post" action="/SDwebapp/Delete" name="deleteitem">
				<input type="hidden" name="item" value=${item.id}>
				<br/>
				<input type="Submit" value="Delete"/>
				</form>
             </td>
             <td>
                <form method="post" action="/SDwebapp/PlaceBid" name="biddingvalue">
				<br/>Place a bid: <input type="text" size="6" name="bid"/>
				<input type="hidden" name="item" value=${item.id}>
				<br/>
				<input type="Submit" value="Bid"/>
				</form>
             </td>
          </tr>
       </c:forEach>
</table>
</div>
</body>
</html>