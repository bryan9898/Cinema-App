package model;

/**
 * Represents an Admin registered in the system.
 * @version 1.0
 * @since 06 Nov 2022
 */


public class Admin extends Account {
	/**
	 * Creates a new Admin with the given username.
	 * The password will be hashed.
	 * The contact information will be a phone number.
	 * The email will be the email of the account.
	 * @param username This is the user's username.
	 * @param password This is the user's password.
	 */

	/**
	 * gets the username of the account.
	 * @return the username of the account.
	 */
	public String getUsername() {
		return super.username;
	}

	/**
	 * Gets the Password of the account.
	 * @return The Password of the account.
	 */
	public String getPassword() {
		return super.password;
	}

	/**
	 * This method is the constructor for the Admin class.
	 */
	public Admin() {

	}


	/**
	 * This method is the constructor for the Admin class with parameters.s
	 * @param username This is the user's username.
	 * @param password This is the user's password.
	 */
	public Admin(String username, String password) {
		super.username = username;
		super.password = password;
	}


	
}
