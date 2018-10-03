package org.wappbean;

public class User {

	String username;
	String password;
	String firstname;
	String surname;
	String email;
	
	
	
	public User(String username, String password, String firstname,
			String surname, String email) {
		super();
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.surname = surname;
		this.email = email;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getFirstname() {
		return firstname;
	}



	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}



	public String getSurname() {
		return surname;
	}



	public void setSurname(String surname) {
		this.surname = surname;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}
	
}
