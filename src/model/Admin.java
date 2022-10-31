package model;

import data.AdminDAO;

public class Admin extends Account {
	
	public String getUsername() {
		return super.username;
	}

	public String getPassword() {
		return super.password;
	}

	public Admin() {

	}
	
	public Admin(String username, String password) {
		super.username = username;
		super.password = password;
	}
	
	public boolean checkAccount(String user, String pass) {
		super.username = user;
		super.password = pass;
		AdminDAO AdminDAO=new AdminDAO();
		return AdminDAO.checkAcc(this);
	}
	
}
