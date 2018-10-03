# Buy-and-Sell-System
An application I designed as part of a Web Application Development Module undertaken in my final year of study in DCU. The requirements of the assignment was to create a web application that included:
- Facility to allow users to register  
- Registered users can place advertisements on the system under certain categories
- Users can search for items or by category and place bids on items

The application was initially run and tested locally using a Tomcat server which was installed and integrated with the Eclipse IDE. Upon completion the application was uploaded to a DCU Tomcat server where it could be accesses remotely (this server has since been reset). An Oracle 11g databse was available to use to store the SQL data.

### Some more requirements and features which are all present in the app
#### Client Tier
- Presentation is performed using standard HTML and limited use of CSS style sheets
- HTML and CSS should be manually created and a WYSIWYG editor should NOT be used.
- Forms should be used where necessary as a front-end interface to your system
#### Application Tier
- Core business logic should be written using Servlets and/or JSPs
- JDBC should be used to connect to the database tier
- Application should be properly constructed in an Object-Oriented component-based structure
- The Session API should be used where necessary to perform session tracking.
- Persistent data should be stored in the database via JDBC where necessary
-You are not allowed to use a framework for development.  This includes Struts, Spring, Grails, RoR or any other framework.  The purpose of this assignment is to learn the underlying technologies and it not focused (yet) on development using frameworks. 
####  Database Tier
- Persistent data storage should be via an existing Oracle 11g database. 
- JDBC drivers are provided from within the course notes
- Multiple tables should be used and given proper relationships.


## Project Report
	The SQL used to create the tables 
	For the user database:   CREATE TABLE SDOHERTYWEBAPP 
							        (FULLNAME VARCHAR(80) NOT NULL,
									 USERNAME VARCHAR(30) NOT NULL,
									 PASSWORD VARCHAR(40) NOT NULL,
							         EMAIL VARCHAR(80) NOT NULL,
							         PHONE VARCHAR(25),
							         PRIMARY KEY (USERNAME))
							         
	For the adverts:   CREATE TABLE SDOHERTYADVERTISEDATA
						        (ID INTEGER,
								 TITLE VARCHAR(80) NOT NULL,
								 CATEGORY VARCHAR(30) NOT NULL,
								 DESCRIPTION VARCHAR(2000) NOT NULL,
						         BID INTEGER,
						         USERNAME VARCHAR(30) NOT NULL,
								 BIDDER VARCHAR(30),
							 	 PRIMARY KEY (ID),
							   	 CONSTRAINT CONSTSDADVERT
						    	 FOREIGN KEY (USERNAME)
						     	 REFERENCES SDOHERTYWEBAPP
						     	 ON DELETE SET NULL) 						        
   
The index/welcoming page of my application is a login page in which users must first login before accessing other features of the application
A servlet checks if the entered user credentials match any records in the SQL database. If there is a match then the user is logged in and they can continue to the home page of the application. A HTTP session is created and assigned to the logged in user to allow them to access restricted parts of the application.	

If incorrect credentials are entered the user is redirected to the login page and informed with an error message, displayed above the login. There is a link in the login page to access a registration page from which a user can sign up. The registration page submits the entered information to a servlet which in turn inserts a new row in the user database for that user. The user is then free to login.
	

The home page consists of a list of adverts currently in the database along with a number of filtering options. The user can search for items with a keyword or by category. 
There is a text field beside each item that allows users to place bids on items. If a user places a bid on an item the item in the database is updated with that bid and the name of the user that placed the bid. In order to update the correct item a Java Bean is created for the item which contains all of its attributes. Using this Java Bean the servlet can distinguish the items and access the required information.
There is a delete option beside each item that allows the item to be removed from the site. Only the user that advertised the item may delete it, all other users are presented with an invalid permissions warning. This warning is possible with JSPs. The servlet sends any error messages to the JSP and it is displayed on screen. These event alerts are present throughout the application and ensure the user is well informed of the events. 
There is a link to an advertisement submission page in which users can advertise items under certain categories.
	
The application makes use of a HTTP session to keep track of logged in users. If a user tries to access a page and does not have an active session then they are redirected to the homepage where they are forced to login.
The login page displays warnings if an invalid username or password is entered A warning will also appear on the login page if the user attempts to access a restricted section of the application. There are warning messages which appear if an unauthorised user attempts to delete an item.

Form Validation is used to make sure all required fields of all of the forms are filledin. This prevents incomplete forms being sent to the server and invalid SQL statements being processed.
	
### JSPs/HTML files and their associated servlets:
index.jsp -> LoginServlet.java
register.html -> RegServlet.java
Home.jsp -> Home.java, Delete.java, PaceBid.java
item.html -> AdvertServlet.java
