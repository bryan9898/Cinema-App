package controller;

import model.Account;
import model.Movies.Bookings;
import model.Movies.Movies;
import model.Movies.TimeSlots;

import java.util.ArrayList;
import java.util.Scanner;

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
            System.out.println("7: Sign out");
            choice = sc.nextInt();
            switch (choice) {
                case 1: listMovies(); break;
                case 2: searchMovie(); break;
                case 3: bookSeats(a); break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:System.out.println("Signing Out...");
                    System.out.printf("\n");
                    break;
            }
        }while(choice != 7);
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
                if (fullTimeSlots.get(index2).getSeats()[row][seatInt].equals("O")) {
                    int seatBooked = 0;
                    for (int j = 0; j < allSeats.length; j++) {
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
            System.out.println("Booking Successful!");
            fullTimeSlots.get(index2).setSeats(seatInt, row);
            ts.updateTimeSlots(fullTimeSlots.get(index2));
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
