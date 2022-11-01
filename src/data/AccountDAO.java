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

public interface AccountDAO {


	boolean checkAcc(String user, String pass);
	boolean createAccount(String user, String cPass, String contact, String email);



	// for admin


}
