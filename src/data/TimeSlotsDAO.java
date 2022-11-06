package data;

import model.Cinema;
import model.Movies.TimeSlots;

import java.util.ArrayList;

public interface TimeSlotsDAO {

    public boolean addTimeSlot(TimeSlots ts);
    public ArrayList<TimeSlots> getAllTimeSlot();
    public void editTimeSlots(String cineplex, String cinemaNum, String movieName, String previousDate, String previousTime, String date, String time, String[][] layout);
    public void removeTimeSlots(int index);
    public ArrayList<TimeSlots> getTimeSlots(String movieName);
    public void updateTimeSlots(TimeSlots timeSlots );


    void updateMovieName(String movieName, String newName);

    ArrayList<TimeSlots> getTimeSlotsByCinema(String cinemaCode);
}
