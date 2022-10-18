package data;

import model.Account;
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

public class TimeSlotsDAO {

    private static final String Account_File="MovieTimeSlots.txt";
    private File dataFile;

    public TimeSlotsDAO() {
        Path dPath = FileSystems.getDefault().getPath("Resources/Data/",Account_File);
        dataFile = new File(dPath.toString());
    }


    public boolean addTimeSlot(TimeSlots ts){
        boolean existing=false;
        ArrayList<TimeSlots> timeslots=getAllTimeSlot();
        for (TimeSlots a:timeslots) {
            if (a.getCinemaNum().equals(ts.getCinemaNum()) && a.getDate().equals(ts.getDate()) && a.getTime().equals(ts.getTime())) {
                existing=true;
                break;
            }
        }
        if (!existing) {
            timeslots.add(ts);
            synToFile(timeslots);
        }
        return !existing;
    }

    public ArrayList<TimeSlots> getAllTimeSlot() {
        Scanner in;
        String record = null;
        String[] fields;
        ArrayList<TimeSlots> ts = new ArrayList<TimeSlots>();
        try {
            in = new Scanner(dataFile);
            while (in.hasNextLine()) {
                record=in.nextLine();
                fields=record.split(";");
                String cineplex = fields[0];
                String cinemaNum = fields[1];
                String movieName = fields[2];
                String date = fields[3];
                String time = fields[4];
                TimeSlots a = new TimeSlots(cineplex, cinemaNum, movieName,  date, time);
                ts.add(a);
            }
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("No record found!");
            //e.printStackTrace();
        }
        return ts;
    }


    private void synToFile(ArrayList<TimeSlots> ts) {
        if (ts==null)
            return;

        try {
            FileWriter out = new FileWriter(dataFile);
            for (TimeSlots a: ts) {
                out.append(a.getCineplex()+";"+a.getCinemaNum()+";"+a.getMovieName()+";"+a.getDate()+";"+a.getTime()+"\r\n");
            }
            out.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
