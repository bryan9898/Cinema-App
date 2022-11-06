package model;

import data.AccountDAO;

/**
	 Represents an Account registered in the system.
	 @version 1.0
	 @since 06 Nov 2022
 */
public class Account {
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
	@Required
	protected String contact;
	/**
	 * The email of the associated account.
	 */
	@Required
	protected String email;

	/**
	 * Creates a new Account with the given username.
	 * The password will be hashed.
	 * The contact information will be a phone number.
	 * The email will be the email of the account.
	 * @param username This is the user's username.
	 * @param password This is the user's password.
	 * @param contact This is the user's contact information , which should be a phone number.
	 * @param email This is the user's email.
	 */
	public Account(String username, String password,String contact,String email) {
		super();
		this.username = username;
		this.password = password;
		this.contact = contact;
		this.email = email;
	}
	
	public Account() {
		
	}	

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
	 * Gets the Contact of the account.
	 * @return contact The Contact of the account.
	 */
	public String getContact() {
		return contact;
	}

	/**
	 * Gets the Email of the account.
	 * @return email The Email associated to the account.
	 */
	public String getEmail() {
		return email;
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
