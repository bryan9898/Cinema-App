package data.impl;

import data.AccountDAO;
import model.Account;
import model.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class represents the user data access object.
 * The user data access object is used to access the user data.
 * @version 1.0
 * @since 06 Nov 2022
 */
public class AccountDaoImpl implements AccountDAO {

    /**
     * The user file path.
     */
    private static final String Account_File="Accounts.txt";
    /**
     * The File object to access the user file.
     */
    private File dataFile;

    /**
     * The constructor to make a user data access object.
     */
    public AccountDaoImpl() {
        Path dPath = FileSystems.getDefault().getPath("Resources/Data/",Account_File);
        dataFile = new File(dPath.toString());
    }


    /**
     * This method is to calculate the hash value of a string using the SHA-256 algorithm.
     * @param base The string to hash
     * @return The hash value of the string
     */
    private static String getHash(final String base) {
        try{
            final MessageDigest digest = MessageDigest.getInstance("SHA-256");
            final byte[] hash = digest.digest(base.getBytes("UTF-8"));
            final StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                final String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    /**
     * This method is to create a new user account.
     * @param user The new user name to create
     * @param cPass The password of the new user which will be hashed using the getHash method
     * @param contact The contact number of the new user, this will be a 8 digit singapore number
     * @param email The email of the new user
     * @return true if the user is created successfully, false if the user already exists
     */
    public boolean createAccount(String user, String cPass, String contact, String email) {
        boolean existing=false;
        ArrayList<User> accounts=getAllAccounts();
        User newAccount=new User(user,cPass,contact,email);
        for (Account a:accounts) {
            if (a.getUsername().equals(newAccount.getUsername())){
                existing=true;
                break;
            }
        }
        if (!existing) {
            String pass = newAccount.getPassword();
            pass = getHash(pass);
            newAccount.setPassword(pass.toString());
            accounts.add(newAccount);
            synToFile(accounts);
        }
        return !existing;
    }

    /**
     * This method is to get all the user accounts from the user file.
     * @return An ArrayList of all the user accounts
     */
    private ArrayList<User> getAllAccounts() {
        Scanner in;
        String record = null;
        String[] fields;
        ArrayList<User> accounts = new ArrayList<User>();
        try {
            in = new Scanner(dataFile);
            while (in.hasNextLine()) {
                record=in.nextLine();
                fields=record.split(";");
                String username = fields[0];
                String password = fields[1];
                String contact = fields[2];
                String email = fields[3];
                User a = new User(username,password,contact,email);
                accounts.add(a);
            }
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("No record found!");
            //e.printStackTrace();
        }
        return accounts;
    }


    /**
     * This method is to get the user account of a user.
     * @param name The username of the user
     * @return The user object of the user
     */
    public User getAccount(String name) {
        ArrayList<User> accounts=getAllAccounts();
        User account=null;
        for (User a:accounts) {
            if (a.getUsername().equals(name)){
                account=a;
                break;
            }
        }
        return account;
    }

    /**
     * This method is to check if the username and password is correct in the user file.
     * @param user The username of the user
     * @param pass The password of the user
     * The password will be hashed using the getHash method
     * @return true if the username and password is correct, false if the username and password is incorrect
     */
    public boolean checkAcc(String user, String pass) {
        ArrayList<User> accounts=getAllAccounts();
        for (User a:accounts) {
            if (a.getUsername().equalsIgnoreCase(user)){
                String check = pass;
                check = getHash(check);
                if(a.getPassword().equals(check)){
                    return true;
                }else {
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * This method is to save the user data in the ArrayList to the user file.
     * @param accountList The ArrayList of user accounts to synchronize
     */
    private void synToFile(ArrayList<User> accountList) {
        if (accountList==null)
            return;

        try {
            FileWriter out = new FileWriter(dataFile);
            for (User a: accountList) {
                out.append(a.getUsername()+";"+a.getPassword()+";"+a.getContact()+";"+a.getEmail()+"\r\n");
            }
            out.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
