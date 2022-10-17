package pages;

import java.util.Scanner;

public class AdminPage {
	
	public AdminPage(){
		System.out.printf("\n");
		System.out.printf("\t------------------------------------%n"); // \tab
		System.out.printf("\t             Admin Page             %n");
		System.out.printf("\t------------------------------------%n");	
		System.out.printf("\n");
		int choice = 0;
		Scanner sc = new Scanner(System.in);
		
		do {
			System.out.println("1: Edit System Settings");
			System.out.println("2: List Movies");
			System.out.println("3: Add Movies");
			System.out.println("4: Edit Movies <Search name>");
			System.out.println("5: Sign out");
			choice = sc.nextInt();
			switch (choice) {
			 case 1: 
			 		break;
			 case 2:
			 		break;
			 case 3: 
				    break;
			 case 4: 
			        break;
			 case 5:System.out.println("Signing Out...");
			 		System.out.printf("\n");
			 		break;
			}
		}while(choice != 5);
	}
	
}
