package model;

public class User extends Account {
    @Required
    protected String contact;
    /**
     * The email of the associated account.
     */
    @Required
    protected String email;

    /**
     * Creates a new User with the given username.
     * The password will be hashed.
     * The contact information will be a phone number.
     * The email will be the email of the account.
     * @param username This is the user's username.
     * @param password This is the user's password.
     * @param contact This is the user's contact information.
     * @param email This is the user's email.
     */

     public User(String username, String password, String contact, String email) {
            this.username = username;
            this.password = password;
            this.contact = contact;
            this.email = email;
     }

    public User(String username, String password) {
        this.username = username;
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
}
