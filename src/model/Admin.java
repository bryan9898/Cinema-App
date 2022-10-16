package model;

import data.AdminDAO;

public class Admin extends Account {
	
	@Required
	private String username;
	@Required
	private String password;
	
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public Admin() {

	}
	
	public Admin(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public boolean checkAccount(String user, String pass) {
		this.username = user;
		this.password = pass;
		AdminDAO AdminDAO=new AdminDAO();
		return AdminDAO.checkAcc(this);
	}
	
}
