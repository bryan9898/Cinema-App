package model.Movies;

import data.BookingDAO;
import data.MoviesDAO;

import java.util.ArrayList;

public class TopMovies extends Movies {
    private String MovieName;
    private int numberOfTickets;
    private double totalSales;
    private double rating;

    public TopMovies(String MovieName, int numberOfTickets, double totalSales) {
        this.MovieName = MovieName;
        this.numberOfTickets = numberOfTickets;
        this.totalSales = totalSales;
    }

    public TopMovies() {

    }

    public TopMovies(String movieName, double rating) {
        this.MovieName = movieName;
        this.rating = rating;
    }

    public String getMovieName() {
        return MovieName;
    }

    public void setMovieName(String movieName) {
        MovieName = movieName;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    public double getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(double totalSales) {
        this.totalSales = totalSales;
    }


    public ArrayList<TopMovies> top5MoviesBySales() {
        BookingDAO bookingDAO = new BookingDAO();
        return bookingDAO.top5MoviesBySales();
    }


    public ArrayList<TopMovies> top5MoviesByUser() {
        MoviesDAO moviesDAO = new MoviesDAO();
        return moviesDAO.top5MoviesByUser();
    }
}