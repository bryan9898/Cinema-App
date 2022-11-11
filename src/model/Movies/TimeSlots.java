package model.Movies;

import data.TimeSlotsDAO;
import model.CinemaTable;

import java.util.ArrayList;

/**
 * Represents a timeslot for a movie viewing registered in the system.
 * Makes a new Timeslots class with time, name of movie, cinema number, date, cineplex and layout of the movie.
 * @version 1.0
 * @since 06 Nov 2022
 */
public class TimeSlots {
	
	/**
	 * Time of the showing.
	 */
    private String time;
    
    /**
     * Name of the movie showing.
     */
    private String movieName;
    
    /**
     * Number of the cinema showing.
     */
    private String cinemaNum;
    
    /**
     * Date of the showing.
     */
    private String date;
    
    /**
     * Name of the cineplex.
     */
    private String cineplex;
    
    /**
     * 2D array of the layout of the seats.
     */
    private String[][] layout;

    /**
     * This adds colour to the console.
     */
    private static final String ANSI_RESET = "\u001B[0m";
    
    /**
     * This adds colour to the console.
     */
    private static final String ANSI_YELLOW = "\u001B[33m";
    
    /**
     * Creates a TimeSlots class with the given parameters.
     * @param cineplex Name of the cineplex.
     * @param cinemaNum Number of the cinema showing.
     * @param movieName Name of the movie showing.
     * @param date Date of the showing.
     * @param time Time of the showing.
     * @param layout 2D array of the layout of the seats.
     */
    public TimeSlots(int cineplex, String cinemaNum, String movieName, String date, String time,String[][] layout) {
        this.time = time;
        this.movieName = movieName;
        switch (cineplex) {
            case 1: this.cineplex = "NTU"; break;
            case 2: this.cineplex = "SEK"; break;
            case 3: this.cineplex = "BIS"; break;
        }
        this.cinemaNum = cinemaNum;
        this.date = date;
        this.layout = layout;
    }

    /**
     * Creates a TimeSlots class with the given parameters.
     * @param cineplex Name of the cineplex.
     * @param cinemaNum Number of the cinema showing.
     * @param movieName Name of the movie showing.
     * @param date Date of the showing.
     * @param time Time of the showing.
     * @param layout 2D array of the layout of the seats.
     */
    public TimeSlots(String cineplex, String cinemaNum, String movieName, String date, String time,String[][] layout) {
        this.time = time;
        this.movieName = movieName;
        this.cinemaNum = cinemaNum;
        this.date = date;
        this.cineplex = cineplex;
        this.layout = layout;
    }

    /**
     * Creates a TimeSlots class with the given parameters.
     * @param cineplex Name of the cineplex.
     * @param cinemaNum Number of the cinema showing.
     * @param movieName Name of the movie showing.
     * @param date Date of the showing.
     * @param time Time of the showing.
     */
    public TimeSlots(String cineplex, String cinemaNum, String movieName, String date, String time) {
        this.time = time;
        this.movieName = movieName;
        this.cinemaNum = cinemaNum;
        this.date = date;
        this.cineplex = cineplex;
    }

    /**
     * Default constructor for the TimeSlots class.
     */
    public TimeSlots() {

    }

    /**
     * Gets the time for the showing.
     * @return time for the showing.
     */
    public String getTime() {
        return time;
    }

    /**
     * Gets the name of the movie.
     * @return name of the movie.
     */
    public String getMovieName() {
        return movieName;
    }

    /**
     * Gets the number of the cinema.
     * @return Cinema number.
     */
    public String getCinemaNum() {
        return cinemaNum;
    }

    /**
     * Gets the date of the cinema.
     * @return Date of the cinema.
     */
    public String getDate() {
        return date;
    }

    /**
     * Gets the name of the cineplex.
     * @return Name of the cineplex.
     */
    public String getCineplex() {
        return cineplex;
    }

    /**
     * Sets the time for showing.
     * @param time Time for the showing.
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Sets the name for the movie.
     * @param movieName Name for the movie.
     */
    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    /**
     * Sets the number of the cinema.
     * @param cinemaNum Number of the cinema.
     */
    public void setCinemaNum(String cinemaNum) {
        this.cinemaNum = cinemaNum;
    }

    /**
     * Sets the date of the showing.
     * @param date Date of the showing.
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Sets the name of the cineplex.
     * @param cineplex Name of the cineplex.
     */
    public void setCineplex(String cineplex) {
        this.cineplex = cineplex;
    }

    /**
     * Gets the layout of the cinema.
     * @return Layout of the cinema.
     */
    public String[][] getLayout() {
        return this.layout;
    }

    /**
     * Sets the layout of the cinema.
     * @param layout Layout of the cinema.
     */
    public void setLayout(String[][] layout) {
        this.layout = layout;
    }

    /**
     * Print all the parameters of the Timeslot.
     */
    public void printAll() {
        System.out.println(ANSI_YELLOW + "Cineplex: " + ANSI_RESET + cineplex);
        System.out.println(ANSI_YELLOW + "Cinema: " + ANSI_RESET + cinemaNum);
        System.out.println(ANSI_YELLOW + "Movie: " + ANSI_RESET + movieName);
        System.out.println(ANSI_YELLOW + "Date: " + ANSI_RESET + date);
        System.out.println(ANSI_YELLOW + "Time: " + ANSI_RESET + time);

        System.out.printf("-------------------------------------------      %n");
        System.out.printf("                 Screen                          %n");
        System.out.printf("-------------------------------------------      %n");
        CinemaTable st = new CinemaTable();
        st.setHeaders(layout[0]);
        for(int i = 1;i<layout.length;i++) {
            st.addRow(layout[i]);
        }
        st.print();
        System.out.printf("-------------------------------------------      %n");
        System.out.printf("                  Entrance                       %n");
        System.out.printf("-------------------------------------------      %n");
    }

    /**
     * Sets the seat to be booked.
     * @param row Row number
     * @param seat Seat number
     */
    public void setSeats(int row,int seat ) {
        layout[row][seat] = "X";
    }

    /**
     * Gets the layout of the cinema.
     * @return Layout of the cinema.
     */
    public String[][] getSeats() {
        return layout;
    }


}
