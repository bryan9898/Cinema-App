package model.Movies;

import data.TimeSlotsDAO;
import model.CinemaTable;

import java.util.ArrayList;

public class TimeSlots {
    private String time;
    private String movieName;
    private String cinemaNum;
    private String date;
    private String cineplex;
    private String[][] layout;

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_YELLOW = "\u001B[33m";
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

    public TimeSlots(String cineplex, String cinemaNum, String movieName, String date, String time,String[][] layout) {
        this.time = time;
        this.movieName = movieName;
        this.cinemaNum = cinemaNum;
        this.date = date;
        this.cineplex = cineplex;
        this.layout = layout;
    }

    public TimeSlots(String cineplex, String cinemaNum, String movieName, String date, String time) {
        this.time = time;
        this.movieName = movieName;
        this.cinemaNum = cinemaNum;
        this.date = date;
        this.cineplex = cineplex;
    }

    public TimeSlots() {

    }

    public String getTime() {
        return time;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getCinemaNum() {
        return cinemaNum;
    }

    public String getDate() {
        return date;
    }

    public String getCineplex() {
        return cineplex;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public void setCinemaNum(String cinemaNum) {
        this.cinemaNum = cinemaNum;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCineplex(String cineplex) {
        this.cineplex = cineplex;
    }

    public String[][] getLayout() {
        return this.layout;
    }

    public void setLayout(String[][] layout) {
        this.layout = layout;
    }

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

    public void setSeats(int row,int seat ) {
        layout[row][seat] = "X";
    }


    public String[][] getSeats() {
        return layout;
    }
}
