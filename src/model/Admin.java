package model;

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


	
}
