package data;

import model.Account;
import model.Movies.TimeSlots;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class BookingDAO {

    private static final String Account_File="Booking.txt";
    private File dataFile;


    public BookingDAO() {
        Path dPath = FileSystems.getDefault().getPath("Resources/Data/",Account_File);
        dataFile = new File(dPath.toString());
    }

    public void bookSeats(String id,Account a, TimeSlots timeSlots, Integer[][] allSeats, double totalPrice) {
        String record = id+";"+ a.getUsername() + ";" + timeSlots.getCineplex() + ";" + timeSlots.getCinemaNum() + ";" + timeSlots.getCinemaNum() + ";" + timeSlots.getDate() + ";" + timeSlots.getTime() + ";" +  totalPrice + ";";
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
