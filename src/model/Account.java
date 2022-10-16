package model;

import data.AccountDAO;
import model.Required;

public class Account {
	@Required
	private String username;
	@Required
	private String password;
	@Required
	private String contact;
	@Required
	private String email;
	
	
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
	
	public boolean checkAccount(String user, String pass) {
		this.username = user;
		this.password = pass;
		AccountDAO accountDao=new AccountDAO();
		return accountDao.checkAcc(this);
	}
	
	public boolean createAccount() {
		AccountDAO accountDao=new AccountDAO();
		return accountDao.createAccount(this);
	}
	
}
