package controller;

import data.TimeSlotsDAO;
import data.impl.MoviesDaoImpl;
import data.impl.TimeSlotsDaoImpl;
import data.impl.ReviewsDaoImpl;
import model.Account;
import model.Movies.Bookings;
import model.Movies.Movies;
import model.Movies.Reviews;
import model.Movies.TimeSlots;
import model.Movies.TopMovies;

import java.awt.print.Book;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import data.MoviesDAO;
import data.ReviewsDAO;
import model.User;

/**
 * Class for the client side.
 * Makes a new ClientPage class for client side functions.
 * @version 1.0
 * @since 06 Nov 2022
 */
public class ClientPage {

	/**
	 * Construct a ClientPage class and initializes the client side display on console.
	 * @param a User class that successfully logged in.
	 */
    public ClientPage(User a){
        System.out.printf("\n");
        System.out.printf("\n");
        System.out.printf("\t------------------------------------\n"); // \tab
        System.out.printf("\t");
        System.out.printf("\u001B[46m" + "\u001B[30m");
        System.out.printf("           Welcome "+ a.getUsername() + "!           ");
        System.out.printf("\u001B[0m");
        System.out.printf("\n\t------------------------------------");
        System.out.printf("\n");
        int choice = 0;

        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("1: List Movies");
            System.out.println("2: Search Movie");
            System.out.println("3: Book seats By Movie");
            System.out.println("4: Book seats By Cinema");
            System.out.println("5: View all Bookings");
            System.out.println("6: Top 5 Movies ranking");
            System.out.println("7: Add Review");
            System.out.println("8: View Reviews for Movie");
            System.out.println("9: Sign out");
            try {
            	choice = sc.nextInt();
            	switch (choice) {
                case 1: listMovies(); break;
                case 2: searchMovie(); break;
                case 3: bookSeats(a); break;
                case 4: bookSeatsByCinema(a); break;
                case 5: viewBookings(a); break;
                case 6: top5Movies(); break;
                case 7: addReview(a);
                    break;
                case 8: viewReview();
                	break;
                case 9:System.out.println("Signing Out...");
                    System.out.printf("\n");
                    break;
                default: break;
            	}
            } catch(Exception e) {
            	sc.nextLine();
            	choice = 0;
            	System.out.println("Wrong Input!");
            	System.out.println("");
            } 
        }while(choice != 9);
    }

    /**
     * Function to allow users to book seats by Cinema.
     * @param a User that logged in.
     */
    private void bookSeatsByCinema(User a) {
        System.out.println("Choose Cinema (1 for NTU , 2 for SEK , 3 for BIS):");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        String cinemaCode = "";
        switch (choice) {
            case 1: cinemaCode = "NTU"; break;
            case 2: cinemaCode = "SEK"; break;
            case 3: cinemaCode = "BIS"; break;
            default: break;
        }
        System.out.println("Choose Movie:");
        TimeSlotsDAO timeSlotsDAO = new TimeSlotsDaoImpl();
        ArrayList<TimeSlots> timeSlots = timeSlotsDAO.getTimeSlotsByCinema(cinemaCode);

        for (int i = 0; i < timeSlots.size(); i++) {
            //if timeslot's date and passed, delete it
            LocalDateTime ldt = LocalDateTime.parse(timeSlots.get(i).getDate() + " " + timeSlots.get(i).getTime(), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
            if (ldt.isBefore(LocalDateTime.now())) {
                timeSlotsDAO.removeTimeSlots(i);
                continue;
            }
        }

        //get unique movie names
        ArrayList<String> movieNames = new ArrayList<>();
        for (TimeSlots ts : timeSlots) {
            if (!movieNames.contains(ts.getMovieName())) {
                movieNames.add(ts.getMovieName());
            }
        }
        //movie objects
        ArrayList <Movies> movies = new ArrayList<>();
        MoviesDAO moviesDAO = new MoviesDaoImpl();
        int counter = 0;
        for (String movieName : movieNames) {
            movies.add(moviesDAO.getMovie(movieName));
            System.out.println("--------------------------------------");
            System.out.printf("%d: ", counter);
            movies.get(counter).printAll();
            counter++;
        }
        System.out.println("--------------------------------------");
        int movieChoice = sc.nextInt();
        String movieName = movieNames.get(movieChoice);
        //print timeslot for movie
        int counter2 = 0;
        ArrayList<TimeSlots> timeSlotsByCinema = new ArrayList<TimeSlots>();
        for (TimeSlots ts : timeSlots) {
            if (ts.getMovieName().equals(movieName)) {
                timeSlotsByCinema.add(ts);
                System.out.println("--------------------------------------");
                System.out.printf("%d: ", counter2);
                ts.printAll();
                counter2++;
            }
        }

        System.out.println("--------------------------------------");
        System.out.println("0 means seat is available , X means seat is taken , [] means couple seats (Both seats will be selected) ");
        System.out.println("\n");
        System.out.println("Enter Time Slot Index: ");
        int index2 = sc.nextInt();
        System.out.println("Enter Number of Seats: ");
        int seats = sc.nextInt();
        int row = 0, seatInt = 0 ;

        Integer[][] allSeats = new Integer[seats][2];
        for (int i = 0; i < seats; i++) {
            System.out.println("Enter Seat Number (A/B/C): ");
            String seat = sc.next();
            System.out.println("Enter Row Number (1/2/3): ");
            row = sc.nextInt();
            seat = seat.replaceAll("\\s","");
            seatInt = Integer.valueOf(seat.toUpperCase().charAt(0))- 64;
            //check if seat already taken
            try {
                if (timeSlotsByCinema.get(index2).getSeats()[row][seatInt].equals("O") || timeSlotsByCinema.get(index2).getSeats()[row][seatInt].equals("[") || timeSlotsByCinema.get(index2).getSeats()[row][seatInt].equals("]")) {
                    int seatBooked = 0;
                    for (int j = 0; j < i; j++) {
                        if (allSeats[j][0] == row && allSeats[j][1] == seatInt) {
                            System.out.println("You already booked this seat");
                            i--;
                            seatBooked = 1;
                        }
                    }
                    if (seatBooked == 0) {
                        if(timeSlotsByCinema.get(index2).getSeats()[row][seatInt].equals("[")){
                            allSeats[i][0] = row;
                            allSeats[i][1] = seatInt;
                            allSeats[i+1][0] = row;
                            allSeats[i+1][1] = seatInt+1;
                            i++;
                        }else if(timeSlotsByCinema.get(index2).getSeats()[row][seatInt].equals("]")) {
                            allSeats[i][0] = row;
                            allSeats[i][1] = seatInt - 1;
                            allSeats[i + 1][0] = row;
                            allSeats[i + 1][1] = seatInt;
                            i++;
                        }else{
                            // check if there is a single unoccupied seat between two selected seats
                            for (int j = 0; j < i; j++) {
                                if (allSeats[j][0] == row && allSeats[j][1] == seatInt - 2 && !timeSlotsByCinema.get(index2).getSeats()[row][seatInt-1].equals("/") || allSeats[j][0] == row && allSeats[j][1] == seatInt + 2 && !timeSlotsByCinema.get(index2).getSeats()[row][seatInt-1].equals("/")) {
                                    seatBooked = 1;
                                }
                            }
                            int seatBooked2 = 0;
                            if(seatBooked == 1) {
                                for (int j = 0; j < i; j++) {
                                    if (allSeats[j][0] == row && allSeats[j][1] == seatInt - 1 || allSeats[j][0] == row && allSeats[j][1] == seatInt + 1) {
                                        seatBooked2 = 1;
                                    }
                                }
                                if (seatBooked2 == 1) {
                                    allSeats[i][0] = row;
                                    allSeats[i][1] = seatInt;
                                } else {
                                    System.out.println("There is a single unoccupied seat between two selected seats!");
                                    i--;
                                }
                            } else {
                                allSeats[i][0] = row;
                                allSeats[i][1] = seatInt;
                            }
                        }
                    }
                } else {
                    System.out.println("Seat unavailable");
                    i--;
                }
            }catch (Exception e){
                System.out.println("Seat unavailable");
                i--;
            }
        }

        System.out.println("Any Child (0-12) Discount? Input 0 if none :  ");
        int child = sc.nextInt();
        System.out.println("Any Senior (60+) Discount? Input 0 if none :  ");
        int senior = sc.nextInt();
        System.out.println("Any Student Discount? Input 0 if none :  ");
        int student = sc.nextInt();
        Bookings b = new Bookings();
        boolean success = b.bookSeats(a,timeSlotsByCinema.get(index2),seats,allSeats,child,senior,student);
        if (success) {
            for (int i = 0; i < seats; i++) {
                timeSlotsByCinema.get(index2).setSeats(allSeats[i][0],allSeats[i][1]);
            }
            timeSlotsDAO.updateTimeSlots(timeSlotsByCinema.get(index2));
            System.out.println("Booking Successful!");
        } else {
            System.out.println("Booking Failed!");
        }

    }

    /**
     * Function to view reviews.
     */
    private void viewReview() {
		MoviesDAO mDAO = new MoviesDaoImpl();
		ReviewsDAO rDAO = new ReviewsDaoImpl();
		ArrayList<Movies> movies = mDAO.getAllMovies();
		ArrayList<Reviews> reviews = rDAO.getAllReviews();
		boolean check=true;
    	this.listMovies();
    	System.out.println("Which movie would you like to see reviews? (Enter index)");
    	Scanner sc = new Scanner(System.in);
    	int index = sc.nextInt();
    	String movieName = movies.get(index-1).getMovieName();
    	for (Reviews r : reviews) {
    		if(r.getMovieName().equals(movieName)) {
    			r.printReview();
    			check=false;
    		}
    	}
    	if(check) {
    		System.out.println("");
    		System.out.println("There are no reviews yet");
    		System.out.println("");
    	}
	}

    /**
     * Function to add review.
     * @param a User that is logged in.
     */
	private void addReview(Account a) {
    	MoviesDAO mDAO = new MoviesDaoImpl();
		ArrayList<Movies> movies = mDAO.getAllMovies();
    	this.listMovies();
    	System.out.println("Which movie would you like to leave a review for? (Enter index)");
    	Scanner sc = new Scanner(System.in);
    	int index = sc.nextInt();
    	String movieName = movies.get(index-1).getMovieName();
		System.out.println("Enter the rating (from 1.0 to 5.0):");
		double rating = sc.nextDouble();
		if(rating<1) {
			rating = 1;
		}
		if(rating>5) {
			rating = 5;
		}
		System.out.println("Enter your review:");
		Scanner sc1 = new Scanner(System.in);
		String review = sc1.nextLine();
		Reviews newReview = new Reviews(review, rating, movieName, a.getUsername());
		newReview.printReview();
		newReview.addReview();
		mDAO.editRating(movieName, newReview.updateRating());
	}

	/**
	 * Function to display top 5 movies either by sales or ratings.
	 */
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

	/**
	 * Function to view all boooking made by user.
	 * @param a User that logged in.
	 */
    private void viewBookings(User a) {
        System.out.printf("\n");
        System.out.printf("\t------------------------------------\n"); // \tab
        System.out.printf("\t");
        System.out.printf("\u001B[47m" + "\u001B[30m");
        System.out.printf("          List Of Bookings          ");
        System.out.printf("\u001B[0m");
        System.out.printf("\n\t------------------------------------");
        System.out.printf("\n\t User : "+a.getUsername());
        System.out.printf("\n\t------------------------------------");
        Bookings b = new Bookings();
        ArrayList<Bookings> bookings = new ArrayList<Bookings>();
        bookings = b.viewBookings(a);
        for (Bookings booking : bookings) {
            System.out.printf("\n");
            System.out.printf("\u001B[36m");
            System.out.println("\tBooking ID: " + booking.getId());
            System.out.println("\tMovie Name: " + booking.getMovieName());
            System.out.println("\tCineplex: " +booking.getCineplex());
            System.out.println("\tCinema Number: " +booking.getCinemaNum());
            System.out.println("\tDate: " +booking.getDate());
            System.out.println("\tTime: " +booking.getTime());
            for (int i = 0; i < booking.getAllSeats().length; i++) {
                char columnLetter = (char) (booking.getAllSeats()[i][1] + 64);
                System.out.println("\t\t Seat "+(i+1)+": Column: "+columnLetter+" , Row: "+booking.getAllSeats()[i][0]);
            }
            System.out.println("\tTotal Price: " + booking.getTotalPrice());
            System.out.printf("\u001B[0m");
            System.out.printf("\n\t------------------------------------");
            System.out.printf("\n");
        }
    }

    /**
     * Function to book seats for user.
     * @param a User that logged in.
     */
    private void bookSeats(User a) {
        MoviesDAO movieDAO = new MoviesDaoImpl();
        ArrayList<Movies> movies = movieDAO.getAllMovies();
        System.out.printf("\n");
        System.out.printf("\n");
        System.out.printf("\t------------------------------------\n"); // \tab
        System.out.printf("\t");
        System.out.printf("\u001B[47m" + "\u001B[30m");
        System.out.printf("           List Of Movies           ");
        System.out.printf("\u001B[0m");
        System.out.printf("\n\t------------------------------------");
        System.out.printf("\n");
        int counter = 0;

        for (Movies m : movies) {
            System.out.println("--------------------------------------");
            System.out.printf("%d: ", counter);
            m.printAll();
            counter++;
        }
        System.out.println("--------------------------------------");
        System.out.println("\n");
        System.out.println("Enter Movie Index: ");
        Scanner sc = new Scanner(System.in);
        int index = sc.nextInt();
        if (index > movies.size()-1 || index < 0) {System.out.println("No such movie index!");return;}

        if (movies.get(index).getMovieStatus().equals("1")) {
            System.out.println("Movie is not showing yet!");
            return;
        }

        TimeSlotsDAO tsDAO = new TimeSlotsDaoImpl();
        ArrayList<TimeSlots> fullTimeSlots = new ArrayList<TimeSlots>();
        //delete all time slots that show is over
        ArrayList<TimeSlots> allTimeSlots = tsDAO.getAllTimeSlot();
        for (int i = 0; i < allTimeSlots.size(); i++) {
            //if timeslot's date and passed, delete it
            LocalDateTime ldt = LocalDateTime.parse(allTimeSlots.get(i).getDate() + " " + allTimeSlots.get(i).getTime(), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
            if (ldt.isBefore(LocalDateTime.now())) {
                tsDAO.removeTimeSlots(i);
                continue;
            }
        }

        fullTimeSlots = tsDAO.getTimeSlots(movies.get(index).getMovieName());
        int counter2 = 0;
        for (TimeSlots t : fullTimeSlots) {
            System.out.println("--------------------------------------");
            System.out.printf("%d: ", counter2);
            t.printAll();
            counter2++;
        }
        System.out.println("--------------------------------------");
        System.out.println("0 means seat is available , X means seat is taken , [] means couple seats (Both seats will be selected) ");
        System.out.println("\n");
        System.out.println("Enter Time Slot Index: ");
        int index2 = sc.nextInt();
        System.out.println("Enter Number of Seats: ");
        int seats = sc.nextInt();
        int row = 0, seatInt = 0 ;
        Integer[][] allSeats = new Integer[seats][2];
        for (int i = 0; i < seats; i++) {
            System.out.println("Enter Seat Number (A/B/C): ");
            String seat = sc.next();
            System.out.println("Enter Row Number (1/2/3): ");
            row = sc.nextInt();
            seat = seat.replaceAll("\\s","");
            seatInt = Integer.valueOf(seat.toUpperCase().charAt(0))- 64;
            //check if seat already taken
            try {
                if (fullTimeSlots.get(index2).getSeats()[row][seatInt].equals("O") || fullTimeSlots.get(index2).getSeats()[row][seatInt].equals("[") || fullTimeSlots.get(index2).getSeats()[row][seatInt].equals("]")) {
                    int seatBooked = 0;
                    for (int j = 0; j < i; j++) {
                        if (allSeats[j][0] == row && allSeats[j][1] == seatInt) {
                            System.out.println("You already booked this seat");
                            i--;
                            seatBooked = 1;
                        }
                    }
                    if (seatBooked == 0) {
                        if(fullTimeSlots.get(index2).getSeats()[row][seatInt].equals("[")){
                            allSeats[i][0] = row;
                            allSeats[i][1] = seatInt;
                            allSeats[i+1][0] = row;
                            allSeats[i+1][1] = seatInt+1;
                            i++;
                        }else if(fullTimeSlots.get(index2).getSeats()[row][seatInt].equals("]")) {
                            allSeats[i][0] = row;
                            allSeats[i][1] = seatInt - 1;
                            allSeats[i + 1][0] = row;
                            allSeats[i + 1][1] = seatInt;
                            i++;
                        }else{
                            // check if there is a single unoccupied seat between two selected seats selected by user
                            for (int j = 0; j < i; j++) {
                                if (allSeats[j][0] == row && allSeats[j][1] == seatInt - 2 && !fullTimeSlots.get(index2).getSeats()[row][seatInt-1].equals("/") || allSeats[j][0] == row && allSeats[j][1] == seatInt + 2 && !fullTimeSlots.get(index2).getSeats()[row][seatInt-1].equals("/")) {
                                    seatBooked = 1;
                                }
                            }
                            int seatBooked2 = 0;
                            if(seatBooked == 1) {
                                for (int j = 0; j < i; j++) {
                                    if (allSeats[j][0] == row && allSeats[j][1] == seatInt - 1 || allSeats[j][0] == row && allSeats[j][1] == seatInt + 1) {
                                        seatBooked2 = 1;
                                    }
                                }
                                if (seatBooked2 == 1) {
                                    allSeats[i][0] = row;
                                    allSeats[i][1] = seatInt;
                                } else {
                                    System.out.println("There is a single unoccupied seat between two selected seats!");
                                    i--;
                                }
                            } else {
                                allSeats[i][0] = row;
                                allSeats[i][1] = seatInt;
                            }
                        }
                    }
                } else {
                    System.out.println("Seat unavailable");
                    i--;
                }
            }catch (Exception e){
                System.out.println("Seat unavailable");
                i--;
            }
        }

        System.out.println("Any Child (0-12) Discount? Input 0 if none :  ");
        int child = sc.nextInt();
        System.out.println("Any Senior (60+) Discount? Input 0 if none :  ");
        int senior = sc.nextInt();
        System.out.println("Any Student Discount? Input 0 if none :  ");
        int student = sc.nextInt();
        Bookings b = new Bookings();
        boolean success = b.bookSeats(a,fullTimeSlots.get(index2),seats,allSeats,child,senior,student);
        if (success) {
            for (int i = 0; i < seats; i++) {
                fullTimeSlots.get(index2).setSeats(allSeats[i][0],allSeats[i][1]);
            }
            tsDAO.updateTimeSlots(fullTimeSlots.get(index2));
            System.out.println("Booking Successful!");
        } else {
            System.out.println("Booking Failed!");
        }

    }

    /**
     * Function to search for a particualr movie name.
     */
    private void searchMovie() {
        System.out.println("Enter Movie Name");
        Scanner sc = new Scanner(System.in);
        String movieName = sc.next();
        Movies movie = new Movies();
        movie.searchMovie(movieName);
    }

    /**
     * Function to list all the movies.
     */
    public void listMovies(){
        Movies m = new Movies();
        m.listMovies();
    }


}
