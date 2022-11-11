package data.impl;

import data.AccountDAO;
import model.Account;
import model.Admin;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class represents the admin data access object.
 * The admin data access object is used to access the admin data.
 * @version 1.0
 * @since 06 Nov 2022
 */
public class AdminDAOImpl implements AccountDAO {
    /**
     * The admin file path.
     */
    private static final String Account_File="admin.txt";
    /**
     * The File object to access the admin file.
     */
    private static File dataFile;

    /**
     * The constructor to make a admin data access object.
     */
    public AdminDAOImpl() {
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
     * This method is to get all the admin data from the admin file.
     * @return The list of admin accounts
     */
    private static ArrayList<Admin> getAllAdminAcc() {
        Scanner in;
        String record = null;
        String[] fields;
        ArrayList<Admin> accounts = new ArrayList<Admin>();
        try {
            in = new Scanner(dataFile);
            while (in.hasNextLine()) {
                record=in.nextLine();
                fields=record.split(";");
                String username = fields[0];
                String password = fields[1];
                Admin a = new Admin(username,password);
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
     * This method is to check if the admin account exists in the admin file.
     * @param user The username of the admin account
     * @param pass The password of the admin account
     * @return True if the admin account exists, false otherwise
     */
    public boolean checkAcc(String user, String pass) {
        ArrayList<Admin> accounts=getAllAdminAcc();
        for (Admin a:accounts) {
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
     * This method is to add a new admin account to the admin file in the future (Future feature).
     * @param user The username of the admin account
     * @param cPass The password of the admin account
     * @param contact The contact number of the admin account
     * @param email The email of the admin account
     * @return True if the admin account is added successfully, false otherwise
     */
    @Override
    public boolean createAccount(String user, String cPass, String contact, String email) {
        return false;
    }
}
