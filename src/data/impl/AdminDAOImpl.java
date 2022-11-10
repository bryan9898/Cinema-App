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

public class AdminDAOImpl implements AccountDAO {

    private static final String Account_File="admin.txt";
    private static File dataFile;


    public AdminDAOImpl() {
        Path dPath = FileSystems.getDefault().getPath("Resources/Data/",Account_File);
        dataFile = new File(dPath.toString());
    }

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

    public Admin getAccount(String name) {
        ArrayList<Admin> accounts=getAllAdminAcc();
        Admin account=null;
        for (Admin a:accounts) {
            if (a.getUsername().equals(name)){
                account=a;
                break;
            }
        }
        return account;
    }


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

    @Override
    public boolean createAccount(String user, String cPass, String contact, String email) {
        return false;
    }
}
