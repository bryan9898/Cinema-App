package model.Movies;

import data.BookingDAO;
import data.MoviesDAO;
import data.impl.BookingDaoImpl;
import data.impl.MoviesDaoImpl;

import java.util.ArrayList;

/**
 * Represents a movie in the top 5 ranking.
 * Makes a new TopMovies class with movie name, numer of tickets sold, total sales and rating.
 * @version 1.0
 * @since 06 Nov 2022
 */
public class TopMovies extends Movies {
	
	/**
	 * Name of the movie.
	 */
    private String MovieName;
    
    /**
     * Number of tickets sold.
     */
    private int numberOfTickets;
    
    /**
     * Total sales generated by the movie.
     */
    private double totalSales;
    
    /**
     * Rating for the movie.
     */
    private double rating;

    /**
     * Creates a TopMovies class with the given parameters.
     * @param MovieName Name of the movie.
     * @param numberOfTickets Number of tickets sold.
     * @param totalSales Total sales generated by the movie.
     */
    public TopMovies(String MovieName, int numberOfTickets, double totalSales) {
        this.MovieName = MovieName;
        this.numberOfTickets = numberOfTickets;
        this.totalSales = totalSales;
    }

    /**
     * Default constructor for the TopMovies class.
     */
    public TopMovies() {

    }

    /**
     * Creates a TopMovies class with the given parameters.
     * @param movieName Name of the movie.
     * @param rating Rating for the movie.
     */
    public TopMovies(String movieName, double rating) {
        this.MovieName = movieName;
        this.rating = rating;
    }

    /**
     * Gets the name of the movie.
     * @return name of the movie.
     */
    public String getMovieName() {
        return MovieName;
    }

    /**
     * Sets the name of the movie.
     * @param movieName Name of the movie.
     */
    public void setMovieName(String movieName) {
        MovieName = movieName;
    }

    /**
     * Gets the number of tickets sold.
     * @return number of tickets sold.
     */
    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    /**
     * Sets the number of tickets sold.
     * @param numberOfTickets Number of tickets sold.
     */
    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    /**
     * Gets the total sales generated by the movie.
     * @return Total sales generated by the movie.
     */
    public double getTotalSales() {
        return totalSales;
    }

    /**
     * Sets the total sales generated by the movie.
     * @param totalSales Total sales generated by the movie.
     */
    public void setTotalSales(double totalSales) {
        this.totalSales = totalSales;
    }

    /**
     * Returns the top 5 movies by sales.
     * @return Arraylist of the top 5 movies by sales.
     */
    public ArrayList<TopMovies> top5MoviesBySales() {
        BookingDAO bookingDAO = new BookingDaoImpl();
        return bookingDAO.top5MoviesBySales();
    }

    /**
     * Returns the top 5 movies by ratings.
     * @return Arraylist of the top 5 movies by ratings.
     */
    public ArrayList<Movies> top5MoviesByUser() {
        MoviesDAO moviesDAO = new MoviesDaoImpl();
        return moviesDAO.top5MoviesByUser();
    }
}