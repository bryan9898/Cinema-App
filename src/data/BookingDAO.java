package data;

import model.Account;
import model.Movies.Bookings;
import model.Movies.TimeSlots;
import model.Movies.TopMovies;

import java.util.ArrayList;

public interface BookingDAO {

    Boolean bookSeats(String id, Account a, TimeSlots timeSlots, Integer[][] allSeats, double totalPrice);

    ArrayList<Bookings> viewBookings(Account a);

    ArrayList<Bookings> getAllBookings();

    public ArrayList<TopMovies> top5MoviesBySales();

}
