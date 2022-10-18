package model.Movies;

import data.CinemaDAO;
import data.TimeSlotsDAO;

public class TimeSlots {
    private String time;
    private String movieName;
    private String cinemaNum;
    private String date;
    private String cineplex;

    public TimeSlots(int cineplex, String cinemaNum, String movieName, String date, String time) {
        this.time = time;
        this.movieName = movieName;
        switch (cineplex) {
            case 1: this.cineplex = "NTU"; break;
            case 2: this.cineplex = "SEK"; break;
            case 3: this.cineplex = "BIS"; break;
        }
        this.cinemaNum = cinemaNum;
        this.date = date;
    }

    public TimeSlots(String cineplex, String cinemaNum, String movieName, String date, String time) {
        this.time = time;
        this.movieName = movieName;
        this.cinemaNum = cinemaNum;
        this.date = date;
        this.cineplex = cineplex;
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

    public boolean addTimeSlot(){
        TimeSlotsDAO ts = new TimeSlotsDAO();
        return ts.addTimeSlot(this);
    }
}
