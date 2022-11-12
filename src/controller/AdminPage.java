package controller;

import data.*;
import data.impl.*;
import model.Admin;
import model.Cinema;
import model.CinemaTable;
import model.Movies.Movies;
import model.Movies.Reviews;
import model.Movies.TimeSlots;
import model.Movies.TopMovies;
import model.SystemSettings;

import java.awt.print.Book;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class AdminPage<ReviewDAO> {
	
	public AdminPage(Admin admin){
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
			System.out.println("7: Remove Reviews");
			System.out.println("8: Top 5 Movies ranking");
			System.out.println("9: Sign out");
			try {
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
					 case 7: removeReviews();
						 break;
					 case 8: top5Movies();
						 break;
					 case 9:System.out.println("Signing Out...");
							System.out.printf("\n");
							break;
				}
			} catch(Exception e) {
            	sc.nextLine();
            	choice = 0;
            	System.out.println("Wrong Input!");
            	System.out.println("");
            } 
		}while(choice != 9);
	}

	private void removeReviews() {
		ReviewsDAO rDAO = new ReviewsDaoImpl();
		MoviesDAO mDAO = new MoviesDaoImpl();
		ArrayList<Movies> movies = mDAO.getAllMovies();
		ArrayList<Reviews> reviews = rDAO.getAllReviews();
		ArrayList<Reviews> rList = new ArrayList<Reviews>();
		System.out.println("1: Delete by movie");
		System.out.println("2: Delete by user");
		Scanner sc = new Scanner(System.in);
		try {
			int choice = sc.nextInt();
			switch(choice) {
			case 1:
				listMovies();
				System.out.println("Which movie is the review from? (Enter index)");
				int choice1 = sc.nextInt();
				String movieName = movies.get(choice1 - 1).getMovieName();
				System.out.println("Movie name is " + movieName);
				int count=1;
				for(Reviews r: reviews) {
					if(r.getMovieName().equals(movieName)) {
						rList.add(r);
						System.out.println("Index:" + count);
						count++;
						r.printReview();
					}
				}
				System.out.println("Which review to remove? (Enter index)");
				int choice2 = sc.nextInt();
				rList.get(choice2 - 1).removeReview();
				if(rList.size()!=0) {
					mDAO.editRating(movieName, rList.get(0).updateRating());
				}
				break;
			case 2:
				break;
			default:
				break;
			}
		} catch(Exception e) {
        	sc.nextLine();
        	System.out.println("Wrong Input!");
        	System.out.println("");
        } 
	}

	private void top5Movies() {
		System.out.println("");
		System.out.println("1: Top 5 by Sales");
		System.out.println("2: Top 5 by Ratings");
		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();
		if(choice!=1 && choice!=2) {
			System.out.println("");
			System.out.println("Wrong option!");
			System.out.println("");
			return;
		}
		
        System.out.printf("\n");
        System.out.printf("\t------------------------------------\n"); // \tab
        System.out.printf("\t");
        System.out.printf("\u001B[47m" + "\u001B[30m");
        System.out.printf("          Top 5 Movies            ");
        System.out.printf("\u001B[0m");
        
        switch(choice) {
        case 1:
        	System.out.printf("\n\t------------------------------------");
            System.out.printf("\n\t By Ticket Sales");
            System.out.printf("\n\t------------------------------------");
            System.out.printf("\n");

            TopMovies b = new TopMovies();
            ArrayList<TopMovies> top5Movies = b.top5MoviesBySales();
            int counter = 1;
            for (TopMovies t : top5Movies) {
                System.out.printf("\t \u001B[47m Top %d: \u001B[0m \n", counter);
                System.out.printf("\u001B[33m");
                System.out.println("\t Movie Name: " + t.getMovieName());
                System.out.println("\t Total Tickets Sold: " + t.getNumberOfTickets());
                System.out.println("\t Total Revenue: " + t.getTotalSales());
                System.out.printf("\n");
                System.out.printf("\t------------------------------------\n"); // \tab
                System.out.printf("\u001B[0m");
                counter++;
            }
            break;
        case 2: 
        	System.out.printf("\n\t------------------------------------");
            System.out.printf("\n\t By User Ratings");
            System.out.printf("\n\t------------------------------------");
            System.out.printf("\n");
            
            TopMovies topUserRating = new TopMovies();
            ArrayList<Movies> top5Ratings = topUserRating.top5MoviesByUser();
            int topCounterForRating = 1;
            for (Movies t : top5Ratings) {
                System.out.printf("\t \u001B[47m Top %d: \u001B[0m \n", topCounterForRating);
                System.out.printf("\u001B[33m");
                System.out.println("\t Movie Name: " + t.getMovieName());
                System.out.println("\t Average Rating: " + t.getRating());
                System.out.printf("\n");
                System.out.printf("\t------------------------------------\n"); // \tab
                System.out.printf("\u001B[0m");
                topCounterForRating++;
            } 
            break;
        default: 
        	break;
        }
        System.out.println("");
		
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
			SystemSettingsDAO ss = new SystemSettingsDaoImpl();
			boolean work = false;
			switch (choice) {
				case 1: ss.printPublicHolidays();
					break;
				case 2:
					System.out.println("Enter Date of public holiday (dd/mm/yyyy): ");
					String date = sc.next();
					//check if date is a valid date
					try {
						LocalDate ld = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
					} catch (DateTimeParseException e) {
						System.out.println("Invalid date");
						return;
					}
					work = ss.addHoliday(date);
					if (work) {System.out.println("Holiday added successfully");} else {System.out.println("Holiday already added");}
					break;
				case 3:
					System.out.println("Enter Date to be removed (dd/mm/yyyy): ");
					String rm = sc.next();
					try {
						LocalDate ld = LocalDate.parse(rm, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
					} catch (DateTimeParseException e) {
						System.out.println("Invalid date");
						return;
					}
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
		MoviesDAO mDAO = new MoviesDaoImpl();
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
		int first = 0;
		do {
			if(first == 1){
				System.out.println("What do you want to edit? (1: Name, 2: PGrating, 3: description, 4: Director, 5: Cast, 6: Status, 7: Change End Of Showing Date , 8: Quit)");
			}
			choice = sc.nextLine();
			switch (choice) {
				case "1":
					System.out.println("Enter the new name of the movie");
					String newName = sc.nextLine();
					mDAO.editMovieName(movies.get(name-1).getMovieName(), newName);
					//update name for booking , review and timeslots
					BookingDAO bDAO = new BookingDaoImpl();
					bDAO.updateMovieName(movies.get(name-1).getMovieName(), newName);
					ReviewsDAO rDAO = new ReviewsDaoImpl();
					rDAO.updateMovieName(movies.get(name-1).getMovieName(), newName);
					TimeSlotsDAO tDAO = new TimeSlotsDaoImpl();
					tDAO.updateMovieName(movies.get(name-1).getMovieName(), newName);
					movies.get(name-1).setMovieName(newName);
					break;
				case "2": System.out.println("Enter the new PGrating of the movie");
					String newPGrating = sc.nextLine();
					mDAO.editMoviePGrating(movies.get(name-1).getMovieName(), newPGrating);
					break;
				case "3":
					System.out.println("Enter the new description of the movie");
					String newSynopsis = sc.nextLine();
					mDAO.editMovieSynopsis(movies.get(name-1).getMovieName(), newSynopsis);
					break;
				case "4":
					System.out.println("Enter the new director of the movie");
					String newDirector = sc.nextLine();
					mDAO.editMovieDirector(movies.get(name-1).getMovieName(), newDirector);
					break;
				case "5":
					System.out.println("Enter the new cast of the movie");
					String newCast = sc.nextLine();
					mDAO.editMovieCast(movies.get(name-1).getMovieName(), newCast);
					break;
				case "6":
					System.out.println("Edit movie status in numbers ((1)Coming Soon, (2)Preview, (3)Now Showing, (4)End of Showing) :");
					String newStatus = sc.nextLine();
					if (newStatus.equals("4")) {
						mDAO.removeMovieFromCinema(movies.get(name-1).getMovieName());
						choice = "7";
						System.out.printf("\n");
						System.out.printf("\t");
						System.out.printf("\u001B[31m");
						System.out.println("------Movie has been deleted!------");
						System.out.printf("\u001B[0m");
						System.out.printf("\n");
						choice = "8";
					} else {
						mDAO.editMovieStatus(movies.get(name-1).getMovieName(), newStatus);
					}
					break;
				case "7":
					System.out.println("Enter the new end of showing date of the movie (dd/mm/yyyy)");
					String newEOS = sc.nextLine();
					// if newEOS is before today's date
					LocalDate ld = LocalDate.parse(newEOS, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
					if (ld.isBefore(LocalDate.now())) {
						mDAO.removeMovieFromCinema(movies.get(name-1).getMovieName());
						System.out.printf("\n");
						System.out.printf("\t");
						System.out.printf("\u001B[31m");
						System.out.println("------Movie has been deleted!------");
						System.out.printf("\u001B[0m");
						System.out.printf("\n");
						choice = "8";
					} else {
						mDAO.editMovieEOS(movies.get(name-1).getMovieName(), newEOS);
					}
					break;

			}
			first = 1;
		} while (!choice.equals("8"));
	}

	private void editMoviesShowTimes() {
		TimeSlots ts = new TimeSlots();
		TimeSlotsDAO tsDAO = new TimeSlotsDaoImpl();
		ArrayList<TimeSlots> allTimeSlots = new ArrayList<TimeSlots>();
		allTimeSlots = tsDAO.getAllTimeSlot();
		for (int i = 0; i < allTimeSlots.size(); i++) {
			//if timeslot's date and passed, delete it
			LocalDateTime ldt = LocalDateTime.parse(allTimeSlots.get(i).getDate() + " " + allTimeSlots.get(i).getTime(), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
			if (ldt.isBefore(LocalDateTime.now())) {
				tsDAO.removeTimeSlots(i);
				continue;
			}
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
			//check if date is a valid date
			try {
				LocalDate ld = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			} catch (DateTimeParseException e) {
				System.out.println("Invalid date");
				return;
			}
			System.out.println("Enter the new time : ");
			String time = sc.next();
			MoviesDAO mDAO = new MoviesDaoImpl();
			String EOSchecker = mDAO.getEOS(allTimeSlots.get(index).getMovieName());
			//if showtime is after EOS, then cannot add
			LocalDate ldEOS = LocalDate.parse(EOSchecker, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			LocalDate ldShowtime = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			if (ldEOS.isBefore(ldShowtime)) {
				System.out.println("Movie has ended its run on " + EOSchecker + ". Cannot add showtime after that date.");
			} else {
				tsDAO.editTimeSlots(allTimeSlots.get(index).getCineplex(), allTimeSlots.get(index).getCinemaNum(), allTimeSlots.get(index).getMovieName(),allTimeSlots.get(index).getDate(),allTimeSlots.get(index).getTime(), date, time , allTimeSlots.get(index).getLayout());
			}
		} else if (choice == 2) {
			tsDAO.removeTimeSlots(index);
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

		TimeSlotsDAO tsDAO = new TimeSlotsDaoImpl();
		MoviesDAO mDAO = new MoviesDaoImpl();
		ArrayList<Movies> movies = mDAO.getAllMovies();
		listMovies();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Movie index to add / edit to cinema showtimes: ");
		int movieName = sc.nextInt();
		if(movieName >= movies.size()+1) {
			System.out.println("No movie found!");
			System.out.println("");
			return;
		}
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
			String showDate = sc.nextLine();
			//check if date is a valid date
			try {
				LocalDate ld = LocalDate.parse(showDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			} catch (DateTimeParseException e) {
				System.out.println("Invalid date");
				return;
			}
			System.out.println("Enter Time (00:00-23:59): ");
			String time = sc.nextLine();

			String EOSchecker = mDAO.getEOS(movies.get(movieName-1).getMovieName());
			//if showtime is after EOS, then cannot add
			LocalDate ldEOS = LocalDate.parse(EOSchecker, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			LocalDate ldShowtime = LocalDate.parse(showDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			if (ldEOS.isBefore(ldShowtime)) {
				System.out.println("Movie has ended its run on " + EOSchecker + ". Cannot add showtime after that date.");
			}
			else {
				String[][] layout = cinema.getCineLayout(cineplexNum, cinemaNum);
				TimeSlots t = new TimeSlots(cineplexNum, cinemaNum, movies.get(movieName-1).getMovieName(), showDate, time , layout);
				boolean work = tsDAO.addTimeSlot(t);
				if (work){
					System.out.println("Successfully added!");
				}
				else {
					System.out.println("Time slot already exists!");
				}
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
		MoviesDAO mDAO = new MoviesDaoImpl();
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
		System.out.printf("\t Movie End of showing date (dd/mm/yyyy): ");
		String movieEOS = sc.nextLine();
		//check if date is a valid date
		try {
			LocalDate ld = LocalDate.parse(movieEOS, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		} catch (DateTimeParseException e) {
			System.out.println("Invalid date");
			return;
		}

		Movies movie = new Movies(movieName,movieType,moviePGRating,movieDescription,movieDirectors,movieCast,movieDuration,movieStatus,movieEOS);
		boolean work = mDAO.addMovie(movie);
		if(work == true) {
			System.out.printf("\n");
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
