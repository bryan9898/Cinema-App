package data.impl;

import data.TimeSlotsDAO;
import model.Cinema;
import model.Movies.TimeSlots;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * This class represents the time slots data access object.
 * The time slots data access object is used to access the time slots data.
 * @version 1.0
 * @since 06 Nov 2022
 */
public class TimeSlotsDaoImpl implements TimeSlotsDAO {

    /**
     * The time slots file path.
     */
    private static final String Account_File="MovieTimeSlots.txt";
    /**
     * The File object to access the time slots file.
     */
    private File dataFile;

    /**
     * The constructor to make a time slots data access object.
     */
    public TimeSlotsDaoImpl() {
        Path dPath = FileSystems.getDefault().getPath("Resources/Data/",Account_File);
        dataFile = new File(dPath.toString());
    }

    /**
     * This method is to add new time slots into the time slots file.
     * @param ts The time slots to add
     * @return true if the time slots is added else false
     */
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

    /**
     * This method is to get all the time slots from the time slots file.
     * @return The array list of time slots
     */
    public ArrayList<TimeSlots> getAllTimeSlot() {
        Scanner in;
        String record = null;
        String[] fields;
        ArrayList<TimeSlots> ts = new ArrayList<>();
        try {
            in = new Scanner(dataFile);
            while (in.hasNextLine()) {
                record=in.nextLine();
                if (record.equals("")) break;
                fields=record.split(";");
                String cineplex = fields[0];
                String cinemaNum = fields[1];
                String movieName = fields[2];
                String date = fields[3];
                String time = fields[4];
                String layout = fields[5];
                Cinema cinema = new Cinema();
                String[][] lay1 = cinema.getCineLayout(cineplex, cinemaNum);
                Scanner scan = new Scanner(layout);
                for (int i=0;i<lay1.length;i++) {
                    for(int j=0; j<lay1[i].length; j++){
                        if(scan.hasNext()){
                            lay1[i][j] = scan.next();
                        } else {break;}
                    }
                }
                scan.close();
                String[][] copy = new String[lay1.length][lay1[0].length];
                for (int i = 0; i < lay1.length; i++) {
                    copy[i] = Arrays.copyOf(lay1[i], lay1[i].length);
                }

                TimeSlots a = new TimeSlots(cineplex,cinemaNum,movieName,date,time,copy);

                ts.add(a);
            }
            in.close();
            return ts;
        } catch (FileNotFoundException e) {
            System.out.println("No record found!");
            //e.printStackTrace();
        }
        return ts;
    }

    /**
     * This method is to save the time slots array list into the time slots file.
     * @param ts The time slots array list to save
     */
    private void synToFile(ArrayList<TimeSlots> ts) {
        if (ts==null)
            return;

        try {
            FileWriter out = new FileWriter(dataFile);
            for (TimeSlots a: ts) {
                out.append(a.getCineplex()+";"+a.getCinemaNum()+";"+a.getMovieName()+";"+a.getDate()+";"+a.getTime()+";");
                String[][] lay1 = a.getLayout();
                for (int i = 0; i < lay1.length; i++) {
                    for (int j = 0; j < lay1[i].length; j++) {
                        out.append(lay1[i][j] + " ");
                    }

                }
                out.append("\r\n");
            }
            out.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * This method is to edit the time slots in the time slots file.
     * @param cineplex The cineplex of the time slots to edit
     * @param cinemaNum The cinema number of the time slots to edit
     * @param movieName The movie name of the time slots to edit
     * @param previousDate The date of the time slots to edit
     * @param previousTime The time of the time slots to edit
     * @param date The new date of the time slots
     * @param time The new time of the time slots
     * @param layout The layout of the time slots
     */
    public void editTimeSlots(String cineplex, String cinemaNum, String movieName, String previousDate, String previousTime, String date, String time, String[][] layout) {
        ArrayList<TimeSlots> ts = getAllTimeSlot();
        for (TimeSlots a:ts) {
            if (a.getCineplex().equals(cineplex) && a.getCinemaNum().equals(cinemaNum) && a.getMovieName().equals(movieName) && a.getDate().equals(previousDate) && a.getTime().equals(previousTime)) {
                ts.remove(a);
                TimeSlots newTs = new TimeSlots(cineplex, cinemaNum, movieName, date, time, layout);
                ts.add(newTs);
                synToFile(ts);
                break;
            }
        }
    }

    /**
     * This method is to delete the time slots in the time slots file.
     * @param index The index of the time slots to delete
     */
    public void removeTimeSlots(int index) {
        ArrayList<TimeSlots> ts = getAllTimeSlot();
        ts.remove(index);
        synToFile(ts);
    }

    /**
     * This method is to get all the time slots from the time slots file.
     * @param movieName The movie name of the time slots to get
     * @return The array list of time slots
     */
    public ArrayList<TimeSlots> getTimeSlots(String movieName) {
        ArrayList<TimeSlots> ts = getAllTimeSlot();
        ArrayList<TimeSlots> result = new ArrayList<TimeSlots>();
        for (TimeSlots a:ts) {
            if (a.getMovieName().equals(movieName)) {
                result.add(a);
            }
        }
        return result;
    }

    /**
     * This method is to update the time slots in the time slots file.
     * @param timeSlots The time slots to update
     */
    public void updateTimeSlots(TimeSlots timeSlots ) {
        try {
            FileWriter out = new FileWriter(dataFile, true);
            out.write(timeSlots.getCineplex()+";"+timeSlots.getCinemaNum()+";"+timeSlots.getMovieName()+";"+timeSlots.getDate()+";"+timeSlots.getTime()+";");
            String[][] lay1 = timeSlots.getLayout();
            for (int i = 0; i < lay1.length; i++) {
                for (int j = 0; j < lay1[i].length; j++) {
                    out.write(lay1[i][j] + " ");
                }
            }
            out.append("\r\n");

            out.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader(dataFile));
            Path dPath = FileSystems.getDefault().getPath("Resources/Data/","myTempFile.txt");
            File tempFile = new File(dPath.toString());
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String lineToRemove = timeSlots.getCineplex()+";"+timeSlots.getCinemaNum()+";"+timeSlots.getMovieName()+";"+timeSlots.getDate()+";"+timeSlots.getTime()+";";
            String currentLine;
            int removed = 0;
            while((currentLine = reader.readLine()) != null) {
                // trim newline when comparing with lineToRemove
                String trimmedLine = currentLine.trim();
                if(trimmedLine.contains(lineToRemove) && removed == 0) {
                    removed++;
                    continue;
                }
                writer.write(currentLine + System.getProperty("line.separator"));
            }
            writer.close();
            reader.close();
            Files.move(dPath, dataFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is to update the Movie Name in the time slots file.
     * @param movieName The old movie name to be updated
     * @param newName The new movie name to update to
     */
    public void updateMovieName(String movieName, String newName){
        ArrayList<TimeSlots> ts = getAllTimeSlot();
        for (TimeSlots a:ts) {
            if (a.getMovieName().equals(movieName)) {
                a.setMovieName(newName);
                synToFile(ts);
            }
        }
    }

    /**
     * This method is to get the time slots from the time slots file based on the cineplex
     * @param cinemaCode The cineplex code of the time slots to get
     * @return The array list of time slots associated with the cineplex code
     */
    public ArrayList<TimeSlots> getTimeSlotsByCinema(String cinemaCode){
        ArrayList<TimeSlots> ts = getAllTimeSlot();
        ArrayList<TimeSlots> result = new ArrayList<TimeSlots>();
        for (TimeSlots a:ts) {
            if (a.getCineplex().equals(cinemaCode)) {
                result.add(a);
            }
        }
        return result;
    }

}
