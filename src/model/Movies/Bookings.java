package model.Movies;

import data.BookingDAO;
import data.MoviesDAO;
import data.impl.BookingDaoImpl;
import data.impl.MoviesDaoImpl;
import model.Account;
import model.Cinema;
import model.Price.PlatinumPrice;
import model.Price.Price;
import model.Price.StandardPrice;
import model.SystemSettings;
import model.User;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Represents a Cinema registered in the system.
 * Makes a new Booking with booking id, cineplex, cinema code, movie name, date, time, total price, seats chosen, username.
 * @version 1.0
 * @since 06 Nov 2022
 */

public class Bookings {
	
	/**
	 * The id for the booking.
	 */
    private String id;
    
    /**
	 * Name of the cineplex.
	 */
    private  String cineplex;
    
    /**
	 * Code number for the cinema.
	 */
    private String cinemaNum;
    
    /**
	 * Name of the movie.
	 */
    private  String movieName;
    
    /**
	 * Date of the booking.
	 */
    private  String date;
    
    /**
	 * Time of the booking.
	 */
    private  String time;
    
    /**
	 * Total price for the booking.
	 */
    private  double totalPrice;
    
    /**
	 * 2D array for all the seats selected.
	 */
    private Integer[][] allSeats;
    
    /**
	 * Username of user making the booking.
	 */
    private String username;

    public Bookings() {
    }

    public Bookings(String id, String username, String cineplex, String cinemaNum, String movieName, String date, String time, double totalPrice, Integer[][] allSeats) {
        this.id = id;
        this.username = username;
        this.cineplex = cineplex;
        this.cinemaNum = cinemaNum;
        this.movieName = movieName;
        this.date = date;
        this.time = time;
        this.totalPrice = totalPrice;
        this.allSeats = allSeats;
    }

    public String getId() {
    	return id;
    }
    
    public void setMovieName(String movieName) {
    	this.movieName = movieName;
    }
    
    public String getUsername() {
        return username;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getCineplex() {
        return cineplex;
    }

    public String getCinemaNum() {
        return cinemaNum;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public Integer[][] getAllSeats() {
        return allSeats;
    }

    public boolean bookSeats(User a, TimeSlots timeSlots, int seats, Integer[][] allSeats, int child, int senior, int student){
        SystemSettings systemSettings = new SystemSettings();
        Cinema cinema = new Cinema();
        StandardPrice sp = new StandardPrice();
        PlatinumPrice pp = new PlatinumPrice();
        int type = cinema.getType(timeSlots.getCineplex(),timeSlots.getCinemaNum());
        double price = 0.0;
        double surcharge = 0.0;
        System.out.printf("\n");
        if(type == 3){ //Platinum price
            price = pp.getTicketPrice();
        } else {
            price = sp.getTicketPrice();
        }
        Boolean checkHoliday = systemSettings.checkHoliday(timeSlots.getDate());
        System.out.printf("\u001B[31m");
        if(checkHoliday){
            System.out.println(timeSlots.getDate()+" is a Public Holiday, surcharge of $"+sp.getPublicHolidaySurcharge()+" will be added to each ticket price");
            surcharge += Double.parseDouble(sp.getPublicHolidaySurcharge());
        } else{
            Calendar c = Calendar.getInstance();
            SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
            try{
                Date dt1 = format1.parse(timeSlots.getDate());
                DateFormat format2 = new SimpleDateFormat("EEEE");
                String finalDay = format2.format(dt1);
                if (finalDay.equals("Saturday") || finalDay.equals("Sunday")) {
                    System.out.println(timeSlots.getDate()+" is a weekend, surcharge of $"+sp.getWeekendSurcharge()+" will be added to each ticket price");
                    surcharge += Double.parseDouble(sp.getWeekendSurcharge());
                }
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        MoviesDAO mv = new MoviesDaoImpl();
        if(mv.check3D(timeSlots.getMovieName())){
            System.out.println(timeSlots.getMovieName()+" is a 3D movie, surcharge of $"+sp.getSurcharge3D()+" will be added to each ticket price");
            surcharge += Double.parseDouble(sp.getSurcharge3D());
        }

        //if time is after 6pm, surcharge will be added to the ticket price
        if(Integer.parseInt(timeSlots.getTime().substring(0,2)) >= 18){
            System.out.println("The movie is showing after 6pm, surcharge of $"+sp.getAfter6pmSurcharge()+" will be added to each ticket price");
            surcharge += Double.parseDouble(sp.getAfter6pmSurcharge());
        }
        System.out.printf("\u001B[0m");
        double childPrice = Double.parseDouble(sp.getChildPriceDiscount()) * child;
        double seniorPrice = Double.parseDouble(sp.getSeniorPriceDiscount()) * senior;
        double studentPrice = Double.parseDouble(sp.getStudentPriceDiscount()) * student;

        double totalPrice = ((price + surcharge) * seats)  - childPrice - seniorPrice - studentPrice;

        System.out.printf("\n");
        System.out.printf("\n");
        System.out.printf("\t------------------------------------\n"); // \tab
        System.out.printf("\t");
        System.out.printf("\u001B[47m" + "\u001B[30m");
        System.out.printf("           Price Summary            ");
        System.out.printf("\u001B[0m");
        System.out.printf("\n\t------------------------------------");
        System.out.printf("\n");

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH)+1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        //int second = cal.get(Calendar.SECOND);
        String id = timeSlots.getCineplex() + year + String.format("%02d", month) + String.format("%02d", day) + String.format("%02d", hour) + String.format("%02d", minute);
        System.out.printf("\u001B[36m"); System.out.printf("\tBooking ID: %s \n",id); System.out.printf("\u001B[0m");
        for(int i = 0; i < seats; i++){
            System.out.printf("\u001B[36m");
            char rowLetter = (char) (allSeats[i][1] + 64);
            System.out.println("\t Seat "+(i+1)+": $"+price + " (Row "+allSeats[i][0]+", Column "+rowLetter+")");
            if(child > 0){
                System.out.println("\t 1 Child Discount: -$"+sp.getChildPriceDiscount());
                child--;
            } else
            if(senior > 0){
                System.out.println("\t 1 Senior Discount: -$"+sp.getSeniorPriceDiscount());
                senior--;
            } else
            if(student > 0){
                System.out.println("\t 1 student Discount: -$"+sp.getStudentPriceDiscount());
                student--;
            }
            System.out.printf("\u001B[0m");
        }
        System.out.printf("\n\n");
        System.out.printf("\u001B[36m");
        System.out.println("\t Total Surcharge: +$" + surcharge*seats);
        System.out.println("\t Total Price: $" + totalPrice);
        System.out.printf("\u001B[0m");

        System.out.printf("\n");

        System.out.printf("Type Yes to confirm booking: ");
        Scanner sc = new Scanner(System.in);
        String confirm = sc.nextLine();
        if(confirm.equalsIgnoreCase("Yes")){
            BookingDAO bookingDAO = new BookingDaoImpl();
            Boolean bookingStatus = bookingDAO.bookSeats(id,a,timeSlots,allSeats,totalPrice);
            if (bookingStatus){
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public ArrayList<Bookings> viewBookings(User a) {
        BookingDAO bookingDAO = new BookingDaoImpl();
        return bookingDAO.viewBookings(a);

    }



}
