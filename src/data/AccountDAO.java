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

/**
 * This interface is used to define the methods that are used to access the Account Data
 * @version 1.0
 * @since 06 Nov 2022
 */
public interface AccountDAO {

	/**
	 * This method is used to check if the account exists
	 * @param user The account to be checked
	 * @param pass The account to be checked
	 * @return true if the account exists, false otherwise
	 */

	boolean checkAcc(String user, String pass);

	/**
	 * This method is used to create a new account
	 * @param user The account to be created
	 * @param cPass The password to be created
	 * @param contact The contact number of the account to be created , this should be a 8 digit number
	 * @param email The email of the account to be created
	 * @return True if the account is created successfully, false otherwise
	 */
	boolean createAccount(String user, String cPass, String contact, String email);



	// for admin


}
