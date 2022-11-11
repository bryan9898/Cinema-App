package data;

import model.Cinema;
import model.Movies.TimeSlots;

import java.util.ArrayList;

/**
 * This interface is used to define the methods that are used to access the Time Slots Data
 * @version 1.0
 * @since 06 Nov 2022
 */
public interface TimeSlotsDAO {

    /**
     * This method is to add new time slots into the time slots file.
     * @param ts The time slots to add
     * @return true if the time slots is added else false
     */
    public boolean addTimeSlot(TimeSlots ts);
    /**
     * This method is to get all the time slots from the time slots file.
     * @return The array list of time slots
     */
    public ArrayList<TimeSlots> getAllTimeSlot();
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
    public void editTimeSlots(String cineplex, String cinemaNum, String movieName, String previousDate, String previousTime, String date, String time, String[][] layout);
    /**
     * This method is to delete the time slots in the time slots file.
     * @param index The index of the time slots to delete
     */
    public void removeTimeSlots(int index);
    /**
     * This method is to get all the time slots from the time slots file.
     * @param movieName The movie name of the time slots to get
     * @return The array list of time slots
     */
    public ArrayList<TimeSlots> getTimeSlots(String movieName);
    /**
     * This method is to update the time slots in the time slots file.
     * @param timeSlots The time slots to update
     */
    public void updateTimeSlots(TimeSlots timeSlots );

    /**
     * This method is to update the Movie Name in the time slots file.
     * @param movieName The old movie name to be updated
     * @param newName The new movie name to update to
     */
    void updateMovieName(String movieName, String newName);
    /**
     * This method is to get the time slots from the time slots file based on the cineplex
     * @param cinemaCode The cineplex code of the time slots to get
     * @return The array list of time slots associated with the cineplex code
     */
    ArrayList<TimeSlots> getTimeSlotsByCinema(String cinemaCode);
}
