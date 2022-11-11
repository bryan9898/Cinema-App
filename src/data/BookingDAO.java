package data;

import model.Account;
import model.Movies.Bookings;
import model.Movies.TimeSlots;
import model.Movies.TopMovies;

import java.util.ArrayList;

/**
 * This interface is used to define the methods that are used to access the Booking Data
 * @version 1.0
 * @since 06 Nov 2022
 */
public interface BookingDAO {

    /**
     * This method is to add a booking into the booking file.
     * @param id The booking id to add
     * @param a The account to associated with the booking
     * @param timeSlots The time slot that the booking is made for
     * @param allSeats all the seats that is being booked for
     * @param totalPrice the total price of the booking
     * @return true if the booking is added else false
     */
    Boolean bookSeats(String id, Account a, TimeSlots timeSlots, Integer[][] allSeats, double totalPrice);

    /**
     * This method is to view all the bookings made by the account
     * @param a The account to view the bookings
     * @return The list of bookings made by the account
     */
    ArrayList<Bookings> viewBookings(Account a);

    /**
     * This method is to get all bookings made by all accounts
     * @return The list of all bookings made by all accounts
     */
    ArrayList<Bookings> getAllBookings();

    /**
     * This method is to get top 5 movies based on the number of tickets sold.
     * @return a list of top 5 movies based on the number of tickets sold
     */
    public ArrayList<TopMovies> top5MoviesBySales();

    /**
     * This method is to update Movie Name in the booking file.
     * @param movieName The old movie name to be updated
     * @param newName The new movie name to update to
     */
    void updateMovieName(String movieName, String newName);
}
