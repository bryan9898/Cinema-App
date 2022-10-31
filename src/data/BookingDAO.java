package data;

import model.Account;
import model.Movies.Bookings;
import model.Movies.Movies;
import model.Movies.TimeSlots;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class BookingDAO {

    private static final String Account_File="Booking.txt";
    private File dataFile;


    public BookingDAO() {
        Path dPath = FileSystems.getDefault().getPath("Resources/Data/",Account_File);
        dataFile = new File(dPath.toString());
    }

    public Boolean bookSeats(String id,Account a, TimeSlots timeSlots, Integer[][] allSeats, double totalPrice) {
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

}
