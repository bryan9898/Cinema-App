package controller;

import data.MoviesDAO;
import model.Admin;
import model.Cinema;
import model.Movies.Movies;
import model.Movies.TimeSlots;
import model.SystemSettings;

import java.util.ArrayList;
import java.util.Scanner;

public class AdminPage {
	
	public AdminPage(){
		System.out.printf("\n");
		System.out.printf("\n");
		System.out.printf("\t------------------------------------\n"); // \tab
		System.out.printf("\t");
		System.out.printf("\u001B[46m" + "\u001B[30m");
		System.out.printf("             Admin Page            ");
		System.out.printf("\u001B[0m");
		System.out.printf("\n\t------------------------------------");
		System.out.printf("\n");
		int choice = 0;
		Scanner sc = new Scanner(System.in);
		
		do {
			System.out.println("1: Edit System Settings");
			System.out.println("2: List Movies");
			System.out.println("3: Add New Movies");
			System.out.println("4: Add Movies to Cinema");
			System.out.println("5: Edit / Remove Movies Show Times");
			System.out.println("6: Edit Movies <Search name>");
			System.out.println("7: Sign out");
			choice = sc.nextInt();
			switch (choice) {
				 case 1: editSystemSettings(); break;
				 case 2: listMovies();
						break;
				 case 3: addMovies();
						break;
				 case 4: addMoviesToCinema();
						break;
				 case 5: editMoviesShowTimes();
						break;
				 case 6: editMovies();
					 	break;
				 case 7:System.out.println("Signing Out...");
						System.out.printf("\n");
						break;
			}
		}while(choice != 7);
	}

	private void editSystemSettings() {
		System.out.printf("\n");
		System.out.printf("\n");
		System.out.printf("\t------------------------------------\n"); // \tab
		System.out.printf("\t");
		System.out.printf("\u001B[47m" + "\u001B[30m");
		System.out.printf("        Edit System Settings        ");
		System.out.printf("\u001B[0m");
		System.out.printf("\n\t------------------------------------");
		System.out.printf("\n");
		Scanner sc = new Scanner(System.in);
		int choice;
		do {
			System.out.println("1: Print list of public holidays");
			System.out.println("2: Add Date of Public Holidays ");
			System.out.println("3: Remove Date of Public Holidays");
			System.out.println("4: Edit Normal Price Tickets");
			System.out.println("5: Edit Platinum Price Tickets");
			System.out.println("6: Quit");
			choice = sc.nextInt();
			SystemSettings ss = new SystemSettings();
			boolean work = false;
			switch (choice) {
				case 1: ss.printPublicHolidays();
					break;
				case 2:
					System.out.println("Enter Date of public holiday: ");
					String date = sc.next();
					work = ss.addHoliday(date);
					if (work) {System.out.println("Holiday added successfully");} else {System.out.println("Holiday already added");}
					break;
				case 3:
					System.out.println("Enter Date to be removed: ");
					String rm = sc.next();
					work = ss.removeHoliday(rm);
					if (work) {System.out.println("Holiday Removed successfully");} else {System.out.println("Holiday not found");}
					break;
				case 4: System.out.println("Enter New Price: ");
						String price = sc.next();
						work = ss.updateTicketPrice(price);
						if (work) {System.out.println("Price updated successfully!");} else {System.out.println("Price already the same!");}
					break;
				case 5: System.out.println("Enter New Platinum Price: ");
						String price2 = sc.next();
						work = ss.updatePlatinumPrice(price2);
						if (work) {System.out.println("Price updated successfully!");} else {System.out.println("Price already the same!");}
						break;
				case 6:
					System.out.println("Quitting...");
					System.out.printf("\n");
					break;
			}
		} while (choice != 6);
	}

	private void editMovies() {

		Movies m = new Movies();
		MoviesDAO mDAO = new MoviesDAO();
		ArrayList<Movies> movies = mDAO.getAllMovies();
		m.listMovies();
		System.out.printf("\n");
		System.out.printf("\n");
		System.out.printf("\t------------------------------------\n"); // \tab
		System.out.printf("\t");
		System.out.printf("\u001B[47m" + "\u001B[30m");
		System.out.printf("             Edit Movies           ");
		System.out.printf("\u001B[0m");
		System.out.printf("\n\t------------------------------------");
		System.out.printf("\n");
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the index of the movie you want to edit : ");
		int name = sc.nextInt();

		String choice;
		do {
			System.out.println("What do you want to edit? (1: Name, 2: PGrating, 3: description, 4: Director, 5: Cast, 6: Status, 7: Quit)");
			choice = sc.nextLine();
			switch (choice) {
				case "1":
					System.out.println("Enter the new name of the movie");
					String newName = sc.nextLine();
					m.editMovieName(movies.get(name-1).getMovieName(), newName);
					movies.get(name-1).setMovieName(newName);
					break;
				case "2": System.out.println("Enter the new PGrating of the movie");
					String newPGrating = sc.nextLine();
					m.editMoviePGrating(movies.get(name-1).getMovieName(), newPGrating);
					break;
				case "3":
					System.out.println("Enter the new description of the movie");
					String newSynopsis = sc.nextLine();
					m.editMovieSynopsis(movies.get(name-1).getMovieName(), newSynopsis);
					break;
				case "4":
					System.out.println("Enter the new director of the movie");
					String newDirector = sc.nextLine();
					m.editMovieDirector(movies.get(name-1).getMovieName(), newDirector);
					break;
				case "5":
					System.out.println("Enter the new cast of the movie");
					String newCast = sc.nextLine();
					m.editMovieCast(movies.get(name-1).getMovieName(), newCast);
					break;
				case "6":
					System.out.println("Edit movie status in numbers ((1)Coming Soon, (2)Preview, (3)Now Showing, (4)End of Showing) :");
					String newStatus = sc.nextLine();
					if (newStatus.equals("4")) {
						m.removeMovieFromCinema(movies.get(name-1).getMovieName());
						choice = "7";
						System.out.printf("\n");
						System.out.printf("\t");
						System.out.printf("\u001B[31m");
						System.out.println("------Movie has been deleted!------");
						System.out.printf("\u001B[0m");
						System.out.printf("\n");
					} else {
						m.editMovieStatus(movies.get(name-1).getMovieName(), newStatus);
					}
					break;
			}
		} while (!choice.equals("7"));
	}

	private void editMoviesShowTimes() {
		TimeSlots ts = new TimeSlots();
		ArrayList<TimeSlots> allTimeSlots = new ArrayList<TimeSlots>();
		allTimeSlots = ts.showTimeSlots();
		for (int i = 0; i < allTimeSlots.size(); i++) {
			System.out.printf("\n");
			System.out.printf("\t");
			System.out.printf("\u001B[47m" + "\u001B[30m");
			System.out.printf(i + ") " + allTimeSlots.get(i).getMovieName());
			System.out.printf("\u001B[0m");
			System.out.printf("\n");
			System.out.printf("\u001B[36m");
			System.out.println("Cineplex : " + allTimeSlots.get(i).getCineplex());
			System.out.println("Cinema : " + allTimeSlots.get(i).getCinemaNum());
			System.out.println("Date : " + allTimeSlots.get(i).getDate() + " " + allTimeSlots.get(i).getTime());
			System.out.printf("\u001B[0m");
		}

		Scanner sc = new Scanner(System.in);
		System.out.printf("\n");
		System.out.println("Enter the index of the movie you want to edit / remove");
		int index = sc.nextInt();
		if(index >= allTimeSlots.size() || index < 0) {
			System.out.println("Invalid index");
			return;
		}
		System.out.println("Type 1 to edit, 2 to remove");
		int choice = sc.nextInt();
		if (choice == 1) {
			System.out.println("Enter the new Date: ");
			String date = sc.next();
			System.out.println("Enter the new time : ");
			String time = sc.next();
			ts.editTimeSlots(allTimeSlots.get(index).getCineplex(), allTimeSlots.get(index).getCinemaNum(), allTimeSlots.get(index).getMovieName(),allTimeSlots.get(index).getDate(),allTimeSlots.get(index).getTime(), date, time , allTimeSlots.get(index).getLayout());
		} else if (choice == 2) {
			ts.removeTimeSlots(index);
		}
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

		MoviesDAO mDAO = new MoviesDAO();
		ArrayList<Movies> movies = mDAO.getAllMovies();
		listMovies();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Movie index to add / edit to cinema showtimes: ");
		int movieName = sc.nextInt();
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
			String[][] layout = cinema.getCineLayout(cineplexNum, cinemaNum);
			TimeSlots t = new TimeSlots(cineplexNum, cinemaNum, movies.get(movieName-1).getMovieName(), showtime, time , layout);
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
			System.out.printf("\n");
			System.out.printf("\t");
			System.out.printf("\u001B[36m");
			System.out.println("------Successfully Added new movie!------");
			System.out.printf("\u001B[0m");
			System.out.printf("\n");
		} else {
			System.out.printf("\n");
			System.out.printf("\t");
			System.out.printf("\u001B[31m");
			System.out.println("------A similar movie has already been added!------");
			System.out.printf("\u001B[0m");
			System.out.printf("\n");
		}

	}


}
