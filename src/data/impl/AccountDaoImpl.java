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

public class AccountDaoImpl implements AccountDAO {


    private static final String Account_File="Accounts.txt";
    private File dataFile;

    public AccountDaoImpl() {
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

    public boolean checkAcc(String user, String pass) {
        ArrayList<User> accounts=getAllAccounts();
        for (User a:accounts) {
            if (a.getUsername().equals(user)){
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
