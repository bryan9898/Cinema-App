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

public class TimeSlotsDaoImpl implements TimeSlotsDAO {

    private static final String Account_File="MovieTimeSlots.txt";
    private File dataFile;

    public TimeSlotsDaoImpl() {
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

    public void removeTimeSlots(int index) {
        ArrayList<TimeSlots> ts = getAllTimeSlot();
        ts.remove(index);
        synToFile(ts);
    }


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

    public void updateMovieName(String movieName, String newName){
        ArrayList<TimeSlots> ts = getAllTimeSlot();
        for (TimeSlots a:ts) {
            if (a.getMovieName().equals(movieName)) {
                a.setMovieName(newName);
                synToFile(ts);
            }
        }
    }

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
