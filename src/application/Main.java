package application;
import java.util.Scanner;

import model.Account;


//MOvie Booking and LIsting Management Application

public class Main {
	
	public static void login(){
		Scanner sc = new Scanner(System.in);
		String user;
		String pass;
		System.out.printf("\t------------------------------------%n"); // \tab
		System.out.printf("\t               Login                %n");
		System.out.printf("\t------------------------------------%n");
		System.out.printf("\t User Name : ");
		user = sc.next();
		System.out.printf("\t Password : ");
		pass = sc.next();
		Account acc = new Account();
		boolean work = acc.checkAccount(user,pass);
		if(work == true) {
			System.out.println("Successfully Logged In!");
		} else {
			System.out.println("Wrong Username or Password");
		}
	}
	
	public static void register(){
		Scanner sc = new Scanner(System.in);
		String user;
		String pass;
		String cPass;
		String contact;
		String email;
		System.out.printf("\t------------------------------------%n"); // \tab
		System.out.printf("\t              Register              %n");
		System.out.printf("\t------------------------------------%n");
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
		Account newAcc = new Account(user, cPass, contact , email);
		boolean work = newAcc.createAccount();
		if(work == false) {
			System.out.println("Please Change UserName");
		} else {
			System.out.println("Account Created!");
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		 String company = ""
	                + "\t  MOvie Booking and LIsting Management\n"
	                + "    Nanyang Technological University 50 Nanyang Avenue\n"
	                + "\t\tBook with us right now!\n"
	                + " \n";
		
		System.out.println(company);
		
		int choice = 0;
		Scanner sc = new Scanner(System.in);
		
		do {
			System.out.println("1: Login");
			System.out.println("2: Register");
			choice = sc.nextInt();
			switch (choice) {
			 case 1: login();
			 		break;
			 case 2: register();
				    break;
			}
		}while(choice >1 || choice <=0);
	}

}
