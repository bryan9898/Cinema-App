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


//MOvie Booking and LIsting Management Application
// If using eclipse, install ANSI Escape in Console plugin in eclipse marketplace

public class Main {
	
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
			ClientPage cp = new ClientPage(user);
		} else {
			System.out.println("\t Wrong Username or Password");
		}
	}
	
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
	}
	
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
		System.out.printf("\t Password : ");
		pass = sc.next();
		System.out.printf("\t Confirm Password : ");
		cPass = sc.next();
		System.out.printf("\t Contact Number : ");
		contact = sc.next();
		System.out.printf("\t Email Address : ");
		email = sc.next();
		AccountDAO acc = new AccountDaoImpl();
		boolean work = acc.createAccount(user, cPass, contact , email);
		if(work == false) {
			System.out.println("Please Change UserName");
		} else {
			System.out.println("Account Created!");
		}
		
	}

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
			System.out.println("2: Admin Login");
			System.out.println("3: Register");
			System.out.println("4: Quit");
			try {
				choice = sc.nextInt();
				switch (choice) {
				 case 1: login();
				 		break;
				 case 2:AdminLogin();
				 		break;
				 case 3: register();
					    break;
				 case 4: System.out.println("Quiting...");
				    break;
				}
			} catch(Exception e) {
            	sc.nextLine();
            	choice = 0;
            	System.out.println("Wrong Input!");
            	System.out.println("");
            } 
		}while(choice != 4);
	}

}
