package model;

import data.AccountDAO;


public class Account {
	@Required
	protected String username;
	@Required
	protected String password;
	@Required
	protected String contact;
	@Required
	protected String email;

	public Account(String username, String password,String contact,String email) {
		super();
		this.username = username;
		this.password = password;
		this.contact = contact;
		this.email = email;
	}
	
	public Account() {
		
	}	
	
	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getContact() {
		return contact;
	}



	public void setContact(String contact) {
		this.contact = contact;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getUsername() {
		return username;
	}


	public void setSession(String user) {
		this.username = user;
	}
}
