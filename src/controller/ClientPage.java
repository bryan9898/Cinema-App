package controller;

import model.Account;
import model.Movies.Bookings;
import model.Movies.Movies;
import model.Movies.Reviews;
import model.Movies.TimeSlots;
import model.Movies.TopMovies;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.Scanner;

import data.MoviesDAO;

public class ClientPage {

    public ClientPage(String user){
        System.out.printf("\n");
        System.out.printf("\n");
        System.out.printf("\t------------------------------------\n"); // \tab
        System.out.printf("\t");
        System.out.printf("\u001B[46m" + "\u001B[30m");
        System.out.printf("           Welcome "+ user + "!           ");
        System.out.printf("\u001B[0m");
        System.out.printf("\n\t------------------------------------");
        System.out.printf("\n");
        int choice = 0;
        Account a = new Account();
        a.setSession(user);
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("1: List Movies");
            System.out.println("2: Search Movie");
            System.out.println("3: Book seats");
            System.out.println("4: View all Bookings");
            System.out.println("5: Top 5 Movies ranking");
            System.out.println("6: Add Review");
            System.out.println("7: Sign out");
            choice = sc.nextInt();
            switch (choice) {
                case 1: listMovies(); break;
                case 2: searchMovie(); break;
                case 3: bookSeats(a); break;
                case 4: viewBookings(a); break;
                case 5: top5Movies(); break;
                case 6: addReview(a);
                    break;
                case 7:System.out.println("Signing Out...");
                    System.out.printf("\n");
                    break;
                default: break;
            }
        }while(choice != 7);
    }

    private void addReview(Account a) {
    	MoviesDAO mDAO = new MoviesDAO();
		ArrayList<Movies> movies = mDAO.getAllMovies();
    	this.listMovies();
    	System.out.println("Which movie would you like to leave a review for? (Enter index)");
    	Scanner sc = new Scanner(System.in);
    	int index = sc.nextInt();
    	String movieName = movies.get(index-1).getMovieName();
		System.out.println("Enter the rating:");
		double rating = sc.nextDouble();
		System.out.println("Enter your review:");
		Scanner sc1 = new Scanner(System.in);
		String review = sc1.nextLine();
		Reviews newReview = new Reviews(review, rating, movieName, a.getUsername());
		newReview.printReview();
		newReview.addReview();
		mDAO.editRating(movieName, newReview.updateRating());
	}

	private void top5Movies() {
        System.out.printf("\n");
        System.out.printf("\t------------------------------------\n"); // \tab
        System.out.printf("\t");
        System.out.printf("\u001B[47m" + "\u001B[30m");
        System.out.printf("          Top 5 Movies            ");
        System.out.printf("\u001B[0m");
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

        System.out.printf("\n\t------------------------------------");
        System.out.printf("\n\t By User Ratings");
        System.out.printf("\n\t------------------------------------");
        /*
        TopMovies topUserRating = new TopMovies();
        ArrayList<TopMovies> top5Ratings = b.top5MoviesByUser();
        int topCounterForRating = 1;
        for (TopMovies t : top5Movies) {
            System.out.printf("\t \u001B[47m Top %d: \u001B[0m \n", topCounterForRating);
            System.out.printf("\u001B[33m");
            System.out.println("\t Movie Name: " + t.getMovieName());
            System.out.println("\t Total Tickets Sold: " + t.getNumberOfTickets());
            System.out.println("\t Total Revenue: " + t.getTotalSales());
            System.out.printf("\n");
            System.out.printf("\t------------------------------------\n"); // \tab
            System.out.printf("\u001B[0m");
            topCounterForRating++;
        } */

    }

    private void viewBookings(Account a) {
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
            System.out.println("\tBooking ID: " + booking.getBookingID());
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

    private void bookSeats(Account a) {
        Movies movie = new Movies();
        ArrayList<Movies> movies = movie.getAllMovies();
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

        TimeSlots ts = new TimeSlots();
        ArrayList<TimeSlots> fullTimeSlots = new ArrayList<TimeSlots>();


        fullTimeSlots = ts.getTimeSlots(movies.get(index).getMovieName());
        int counter2 = 0;
        for (TimeSlots t : fullTimeSlots) {
            System.out.println("--------------------------------------");
            System.out.printf("%d: ", counter2);
            t.printAll();
            counter2++;
        }
        System.out.println("--------------------------------------");
        System.out.println("0 means seat is available , X means seat is taken , [] means couple seats ");
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
                        allSeats[i][0] = row;
                        allSeats[i][1] = seatInt;
                    }
                } else {
                    System.out.println("Seat unavailable");
                    i--;
                }
            }catch (Exception e){
                System.out.println(e);
                System.out.println("Seat unavailable / fail");
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
            ts.updateTimeSlots(fullTimeSlots.get(index2));
            System.out.println("Booking Successful!");
        } else {
            System.out.println("Booking Failed!");
        }

    }

    private void searchMovie() {
        System.out.println("Enter Movie Name");
        Scanner sc = new Scanner(System.in);
        String movieName = sc.next();
        Movies movie = new Movies();
        movie.searchMovie(movieName);
    }

    public void listMovies(){
        Movies m = new Movies();
        m.listMovies();
    }


}
