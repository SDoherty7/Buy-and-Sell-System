<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Buy and Sell System</title>
<link rel="stylesheet" type="text/css" href="style.css"/>
<script type="text/javascript">
  function formValid() {
	   var field1 = document.forms["loginform"]["username"].value;
	   var field2 = document.forms["loginform"]["password"].value;
	    if (field1 == "" || field2 == "") {
	        alert("Please fill in all fields");
	        return false;
	    }
  }
  </script>
</head>
<body>
<div id="container">
<div id="header">
<h1> Buy and Sell System </h1>
</div>
<%
String login_msg=(String)request.getAttribute("error");  
if(login_msg!=null)
out.println("<font color=red size=4px>"+login_msg+"</font>");
%>
<form method="post" action="/SDwebapp/LoginServlet" onsubmit="return formValid()" name="loginform">
<br/>Username: <input type="text" name="username"/>
<br/>Password: <input type="password"  name="password"/>
<br/>
<input type="submit" value="Login"/>
<a href="./register.html">Register as a new user</a>
</form>

</div>

</body>
</html>