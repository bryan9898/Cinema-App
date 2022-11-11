package data.impl;

import data.BookingDAO;
import model.Account;
import model.Movies.Bookings;
import model.Movies.TimeSlots;
import model.Movies.TopMovies;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class represents the booking data access object.
 * The booking data access object is used to access the booking data.
 * @version 1.0
 * @since 06 Nov 2022
 */
public class BookingDaoImpl implements BookingDAO {
    /**
     * The booking file path.
     */
    private static final String Account_File="Booking.txt";
    /**
     * The File object to access the booking file.
     */
    private File dataFile;

    /**
     * The constructor to make a booking data access object.
     */
    public BookingDaoImpl() {
        Path dPath = FileSystems.getDefault().getPath("Resources/Data/",Account_File);
        dataFile = new File(dPath.toString());
    }

    /**
     * This method is to add a booking into the booking file.
     * @param id The booking id to add
     * @param a The account to associated with the booking
     * @param timeSlots The time slot that the booking is made for
     * @param allSeats all the seats that is being booked for
     * @param totalPrice the total price of the booking
     * @return true if the booking is added else false
     */
    public Boolean bookSeats(String id, Account a, TimeSlots timeSlots, Integer[][] allSeats, double totalPrice) {
        String record = id+";"+ a.getUsername() + ";" + timeSlots.getCineplex() + ";" + timeSlots.getCinemaNum() + ";" + timeSlots.getMovieName() + ";" + timeSlots.getDate() + ";" + timeSlots.getTime() + ";" +  totalPrice + ";";
        for (int i = 0; i < allSeats.length; i++) {
            for (int j = 0; j < allSeats[i].length; j++) {
                record += allSeats[i][j] + ",";
            }
        }
        record = record.substring(0, record.length() - 1);
        record += "\n";
        try {
            FileWriter fw = new FileWriter(dataFile, true);
            fw.write(record);
            fw.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * This method is to view all the bookings made by the account.
     * @param a The account to associated with the booking
     * @return a list of all the bookings made by the account
     */
    public ArrayList<Bookings> viewBookings(Account a) {
        ArrayList<Bookings> bookings = getAllBookings();
        ArrayList<Bookings> userBookings = new ArrayList<>();
        for (Bookings b : bookings) {
            if (b.getUsername().equals(a.getUsername())) {
                userBookings.add(b);
            }
        }
        return userBookings;
    }


    /**
     * This method is to get all the bookings from the booking file.
     * @return a  list of all the bookings from the booking file
     */
    public ArrayList<Bookings> getAllBookings() {
        Scanner in;
        String record = null;
        String[] fields;
        ArrayList<Bookings> bookings = new ArrayList<Bookings>();
        try {
            in = new Scanner(dataFile);
            while (in.hasNextLine()) {
                record=in.nextLine();
                fields=record.split(";");
                String id = fields[0];
                String username = fields[1];
                String cineplex = fields[2];
                String cinemaNum = fields[3];
                String movieName = fields[4];
                String date = fields[5];
                String time = fields[6];
                double totalPrice = Double.parseDouble(fields[7]);
                String seats = fields[8];
                String[] seat = seats.split(",");
                Integer[][] allSeats = new Integer[seat.length/2][2];
                for (int i = 0; i < seat.length; i+=2) {
                    allSeats[i/2][0] = Integer.parseInt(seat[i]);
                    allSeats[i/2][1] = Integer.parseInt(seat[i+1]);
                }
                bookings.add(new Bookings(id, username, cineplex, cinemaNum, movieName, date, time, totalPrice, allSeats));
            }
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("No bookings found!");
        }
        return bookings;
    }

    /**
     * This method is to get top 5 movies based on the number of tickets sold.
     * @return a list of top 5 movies based on the number of tickets sold
     */
    public ArrayList<TopMovies> top5MoviesBySales() {
        ArrayList<Bookings> bookings = getAllBookings();
        ArrayList<TopMovies> top5 = new ArrayList<>();
        ArrayList<TopMovies> allMovies = new ArrayList<TopMovies>();
        for (Bookings b : bookings) {
            boolean found = false;
            for (TopMovies m : allMovies) {
                if (m.getMovieName().equals(b.getMovieName())) {
                    m.setNumberOfTickets(m.getNumberOfTickets() + b.getAllSeats().length);
                    m.setTotalSales(m.getTotalSales() + b.getTotalPrice());
                    found = true;
                }
            }
            if (!found) {
                allMovies.add(new TopMovies(b.getMovieName(), b.getAllSeats().length, b.getTotalPrice())); // movie name , number of seats, total sales
            }
        }

        for (int i = 0; i < 5; i++) {
            if(allMovies.size() == 0){
                break;
            }
            TopMovies max = allMovies.get(0);
            for (TopMovies t : allMovies) {
                if (t.getTotalSales() > max.getTotalSales()) {
                    max = t;
                }
            }
            allMovies.remove(max);
            top5.add(max);
        }

        return top5;
    }

    /**
     * This method is to update Movie Name in the booking file.
     * @param movieName The old movie name to be updated
     * @param newName The new movie name to update to
     */
    public void updateMovieName(String movieName, String newName) {
        ArrayList<Bookings> bookings = getAllBookings();
        for (Bookings b : bookings) {
            if (b.getMovieName().equals(movieName)) {
                b.setMovieName(newName);
            }
        }
        try {
            FileWriter fw = new FileWriter(dataFile, false);
            for (Bookings b : bookings) {
                String record = b.getId() + ";" + b.getUsername() + ";" + b.getCineplex() + ";" + b.getCinemaNum() + ";" + b.getMovieName() + ";" + b.getDate() + ";" + b.getTime() + ";" + b.getTotalPrice() + ";";
                for (int i = 0; i < b.getAllSeats().length; i++) {
                    for (int j = 0; j < b.getAllSeats()[i].length; j++) {
                        record += b.getAllSeats()[i][j] + ",";
                    }
                }
                record = record.substring(0, record.length() - 1);
                record += "\n";
                fw.write(record);
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

}
