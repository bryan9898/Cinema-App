package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

import model.Account;

public class AccountDAO {
	private static final String Account_File="Accounts.txt";
	private File dataFile;
	
	
	public AccountDAO() {
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
	
	public boolean createAccount(Account account) {
		boolean existing=false;
		ArrayList<Account> accounts=getAllAccounts();
		for (Account a:accounts) {
			if (a.getUsername().equals(account.getUsername())){
				existing=true;
				break;
			}
		}
		if (!existing) {
			String pass = account.getPassword();
			pass = getHash(pass);
			account.setPassword(pass.toString());
			accounts.add(account);
			synToFile(accounts);
		}
		return !existing;
	}
	
	public ArrayList<Account> getAllAccounts() {
		Scanner in;
		String record = null;
		String[] fields;
		ArrayList<Account> accounts = new ArrayList<Account>();
		try {
			in = new Scanner(dataFile);
			while (in.hasNextLine()) {
				record=in.nextLine();
				fields=record.split(";");
				String username = fields[0];
				String password = fields[1];
				String contact = fields[2];
				String email = fields[3];
				Account a = new Account(username,password,contact,email);
				accounts.add(a);
			}
			in.close();
		} catch (FileNotFoundException e) {	
			System.out.println("No record found!");
			//e.printStackTrace();
		}
		return accounts;
	}
	
	public Account getAccount(String name) {
		ArrayList<Account> accounts=getAllAccounts();
		Account account=null;
		for (Account a:accounts) {
			if (a.getUsername().equals(name)){
				account=a;
				break;
			}
		}
		return account;
	}
	
	public boolean checkAcc(Account account) {
		ArrayList<Account> accounts=getAllAccounts();
		for (Account a:accounts) {
			if (a.getUsername().equals(account.getUsername())){
				String check = account.getPassword();
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

	
	private void synToFile(ArrayList<Account> accountList) {
		if (accountList==null)
			return;
		
		try {
			FileWriter out = new FileWriter(dataFile);
			for (Account a: accountList) {
				out.append(a.getUsername()+";"+a.getPassword()+";"+a.getContact()+";"+a.getEmail()+"\r\n");
			}
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}
