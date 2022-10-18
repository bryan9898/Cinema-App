package pages;

import model.Admin;
import model.Cinema;
import model.Movies.Movies;
import model.Movies.TimeSlots;

import java.util.Scanner;

public class AdminPage {
	
	public AdminPage(){
		System.out.printf("\n");
		System.out.printf("\n");
		System.out.printf("\t------------------------------------\n"); // \tab
		System.out.printf("\t");
		System.out.printf("\u001B[47m" + "\u001B[30m");
		System.out.printf("             Admin Page            ");
		System.out.printf("\u001B[0m");
		System.out.printf("\n\t------------------------------------");
		System.out.printf("\n");
		int choice = 0;
		Scanner sc = new Scanner(System.in);
		
		do {
			System.out.println("1: Edit System Settings");
			System.out.println("2: List Movies");
			System.out.println("3: Add Movies");
			System.out.println("4: Add/Edit Movies to Cinema");
			System.out.println("5: Edit Movies <Search name>");
			System.out.println("6: Sign out");
			choice = sc.nextInt();
			switch (choice) {
			 case 1:
			 		break;
			 case 2: listMovies();
			 		break;
			 case 3: addMovies();
			 		break;
			 case 4: addMoviesToCinema();
			        break;
			 case 5:
				 	break;
			 case 6:System.out.println("Signing Out...");
					System.out.printf("\n");
					break;
			}
		}while(choice != 6);
	}

	public void addMoviesToCinema(){
		System.out.printf("\n");
		System.out.printf("\t------------------------------------\n"); // \tab
		System.out.printf("\t");
		System.out.printf("\u001B[47m" + "\u001B[30m");
		System.out.printf("        Add Movies to Cinema       ");
		System.out.printf("\u001B[0m");
		System.out.printf("\n\t------------------------------------");
		System.out.printf("\n");

		listMovies();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Movie Name to add / edit to cinema showtimes: ");
		String movieName = sc.nextLine();
		System.out.println("Enter Cineplex Num (1)NTU (2)SEK (3)BIS: ");
		int cineplexNum = sc.nextInt();
		Cinema c = new Cinema();
		int num = c.getNumOfCinema(cineplexNum);
		if (num == 0){
			System.out.println("No cinema found!");
		}
		else {
			System.out.println("Enter Cinema Num (1-" + num + "): ");
			sc.nextLine();
			String cinemaNum = sc.nextLine();
			Cinema cinema = new Cinema(cineplexNum, cinemaNum);
			System.out.println("Cinema Layout : \n ");
			cinema.printCinemaLayout();
			System.out.println("Enter Date (DD/MM/YYYY): ");
			String showtime = sc.nextLine();
			System.out.println("Enter Time (00:00-23:59): ");
			String time = sc.nextLine();
			TimeSlots t = new TimeSlots(cineplexNum, cinemaNum, movieName, showtime, time);
			boolean work = t.addTimeSlot();
			if (work){
				System.out.println("Successfully added!");
			}
			else {
				System.out.println("Time slot already exists!");
			}
		}


	}

	public void listMovies(){
		Movies m = new Movies();
		m.listMovies();
	}

	public void addMovies(){
		Scanner sc = new Scanner(System.in);
		String movieName;

		System.out.printf("\n");
		System.out.printf("\t------------------------------------\n"); // \tab
		System.out.printf("\t");
		System.out.printf("\u001B[47m" + "\u001B[30m");
		System.out.printf("             Add Movies              ");
		System.out.printf("\u001B[0m");
		System.out.printf("\n\t------------------------------------");
		System.out.printf("\n");

		System.out.printf("\t Movie Name : ");
		movieName = sc.nextLine();
		System.out.printf("\t Movie Type (Blockbuster/3D etc) : ");
		String movieType = sc.nextLine();
		System.out.printf("\t Movie PGrating : ");
		String moviePGRating = sc.nextLine();
		System.out.printf("\t Movie Description : ");
		String movieDescription = sc.nextLine();
		System.out.printf("\t Movie Directors (eg Steven,Martin): ");
		String movieDirectors = sc.nextLine();
		System.out.printf("\t Movie Cast (eg Tom,Brad): ");
		String movieCast = sc.nextLine();
		System.out.printf("\t Movie Duration (eg 160 mins) : ");
		String movieDuration = sc.nextLine();
		System.out.printf("\t Movie Status in numbers ((1)Coming Soon, (2)Preview, (3)Now Showing, (4)End of Showing) : ");
		String movieStatus = sc.nextLine();
		Movies movie = new Movies(movieName,movieType,moviePGRating,movieDescription,movieDirectors,movieCast,movieDuration,movieStatus);
		boolean work = movie.addMovie();
		if(work == true) {
			System.out.println("------Successfully Added new movie!------");
		} else {
			System.out.println("------A similar movie has already been added!------");
		}

	}


}
