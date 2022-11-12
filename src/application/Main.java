package application;
import java.util.Scanner;

import controller.ClientPage;
import data.AccountDAO;
import data.MoviesDAO;
import data.impl.AccountDaoImpl;
import data.impl.AdminDAOImpl;
import data.impl.MoviesDaoImpl;
import model.Admin;
import controller.AdminPage;
import model.User;


//MOvie Booking and LIsting Management Application
// If using eclipse, install ANSI Escape in Console plugin in eclipse marketplace

/**
 * Main class for the UI.
 * Makes a new Main class for main page of the UI.
 * @version 1.0
 * @since 06 Nov 2022
 */
public class Main {
	
	/**
	 * Default Constructor for Main class.
	 */
	Main(){
		
	}
	/**
	 * Log in page when console is initialized.
	 */
	public static void login(){
		Scanner sc = new Scanner(System.in);
		String user;
		String pass;
		System.out.printf("\n");
		System.out.printf("\t------------------------------------\n"); // \tab
		System.out.printf("\t");
		System.out.printf("\u001B[47m" + "\u001B[30m");
		System.out.printf("               Login                ");
		System.out.printf("\u001B[0m");
		System.out.printf("\n\t------------------------------------");
		System.out.printf("\n\t User Name : ");
		user = sc.next();
		System.out.printf("\t Password : ");
		pass = sc.next();
		AccountDAO acc = new AccountDaoImpl();
		boolean work = acc.checkAcc(user, pass);
		if(work == true) {
			System.out.println("\t Successfully Logged In!");
			User user1 = new User(user.toLowerCase(), pass);
			ClientPage cp = new ClientPage(user1);
		} else {
			AccountDAO adminAcc = new AdminDAOImpl();
			boolean AdminLogin = adminAcc.checkAcc(user,pass);
			if(AdminLogin == true) {
				System.out.println("\t Successfully Logged In!");
				Admin admin1 = new Admin(user.toLowerCase(), pass);
				AdminPage ap = new AdminPage(admin1);
			} else {
				System.out.println("\t Wrong Username or Password");
			}
		}
	}
	/*
	public static void AdminLogin(){
		Scanner sc = new Scanner(System.in);
		String user;
		String pass;
		System.out.printf("\n");
		System.out.printf("\t------------------------------------\n"); // \tab
		System.out.printf("\t");
		System.out.printf("\u001B[44m" + "\u001B[30m");
		System.out.printf("             Admin Login             ");
		System.out.printf("\u001B[0m");
		System.out.printf("\n\t------------------------------------");
		System.out.printf("\n\t User Name : ");
		user = sc.next();
		System.out.printf("\t Password : ");
		pass = sc.next();
		AccountDAO acc = new AdminDAOImpl();
		boolean work = acc.checkAcc(user,pass);
		if(work == true) {
			System.out.println("\t Successfully Logged In!");
			AdminPage ap = new AdminPage();
		} else {
			System.out.println("\t Wrong Username or Password");
		}
	} */
	
	/**
	 * Function to register an account for user.
	 */
	public static void register(){
		Scanner sc = new Scanner(System.in);
		String user;
		String pass;
		String cPass;
		String contact;
		String email;
		System.out.printf("\n");
		System.out.printf("\t------------------------------------\n"); // \tab
		System.out.printf("\t");
		System.out.printf("\u001B[47m" + "\u001B[30m");
		System.out.printf("              Register              ");
		System.out.printf("\u001B[0m");
		System.out.printf("\n\t------------------------------------");

		System.out.printf("\n");
		System.out.printf("\t User Name : ");
		user = sc.next();
		System.out.printf("\t Password (At least 8 characters long with a special character): ");
		pass = sc.next();
		System.out.printf("\t Confirm Password : ");
		cPass = sc.next();
		System.out.printf("\t Contact Number : ");
		contact = sc.next();
		System.out.printf("\t Email Address : ");
		email = sc.next();

		System.out.printf("\u001B[31m");
		int fail = 0;
		if(!pass.equals(cPass)) {
			System.out.println("\n\t Passwords do not match!");
			fail= 1;
		}

		//check for strong password
		if(pass.length() < 8) {
			System.out.println("\n\t Password must be at least 8 characters long!");
			fail = 1;
		}

		//check for special character
		if(!pass.matches(".*[!@#$%^&*()_+].*")) {
			System.out.println("\n\t Password must contain a special character!");
			fail = 1;
		}

		//phone number matches 8 digits and starts with 6 or 8 or 9
		if(!contact.matches("(6|8|9)[0-9]{7}")) {
			System.out.println("\n\t Invalid Phone Number!");
			fail = 1;
		}

		//email address matches the regex
		if(!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
			System.out.println("\n\t Invalid Email Address!");
			fail = 1;
		}
		System.out.printf("\u001B[0m");
		if(fail == 1){
			System.out.printf("\u001B[31m");
			System.out.println("\n\t Failed to create account");
			System.out.printf("\u001B[0m");
		} else {
			AccountDAO acc = new AccountDaoImpl();
			boolean work = acc.createAccount(user.toLowerCase(), cPass, contact , email);
			if(work == true) {
				System.out.printf("\u001B[36m");
				System.out.println("\n\tAccount Created!");
				System.out.printf("\u001B[0m");
			} else {
				System.out.printf("\u001B[31m");
				System.out.println("\n\t Please Change UserName");
				System.out.printf("\u001B[0m");
			}
		}

		
	}

	/**
	 * Main function of the code.
	 * @param args To initialize main function.
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		 String company = "" + "\u001B[47m" + "\u001B[30m"
				 	+ "                 Movie Booking Application                  " + "\u001B[0m"
				 	+ "\n" + "\u001B[47m" + "\u001B[30m"
	                + "     Nanyang Technological University 50 Nanyang Avenue     " + "\u001B[0m"
				 	+ "\n" + "\u001B[47m" + "\u001B[30m"
	                + "                  Book with us right now!		            " + "\u001B[0m"
				 	+ "\n";

		
		System.out.println(company);

		int choice = 0;
		Scanner sc = new Scanner(System.in);
		
		do {
			System.out.println("1: Login");
			System.out.println("2: Register");
			System.out.println("3: Quit");
			try {
				choice = sc.nextInt();
				switch (choice) {
				 case 1: login();
				 		break;
				 case 2: register();
					    break;
				 case 3: System.out.println("Quiting...");
				    break;
				}
			} catch(Exception e) {
            	sc.nextLine();
            	choice = 0;
            	System.out.println("Wrong Input!");
            	System.out.println("");
            } 
		}while(choice != 3);
	}

}
