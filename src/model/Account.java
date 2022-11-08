package model;

import data.AccountDAO;

/**
	 Represents an Account registered in the system.
	 @version 1.0
	 @since 06 Nov 2022
 */
public abstract class Account {
	/**
	 * The username of the account.
	 */
	@Required
	protected String username;

	/**
	 * The password of the account, which will be hashed.
	 */
	@Required
	protected String password;
	/**
	 * The Contact information of the user.
	 */


	/**
	 * Creates a new Account with the given username.
	 * The password will be hashed.
	 * The contact information will be a phone number.
	 * The email will be the email of the account.
	 * @param username This is the user's username.
	 * @param password This is the user's password.
	 */


	/**
	 * Gets the Password of the account.
	 * @return The Password of the account.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the Password of the account.
	 * @param password The Password of the account.
	 */
	public void setPassword(String password) {
		this.password = password;
	}


	/**
	 * Gets the username of the account.
	 * @return The username of the account.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the Username of the account.
	 */
	public void setUsername(String user) {
		this.username = user;
	}
}
