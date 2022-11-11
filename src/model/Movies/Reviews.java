package model.Movies;

import java.util.ArrayList;

import data.MoviesDAO;
import data.ReviewsDAO;
import data.impl.ReviewsDaoImpl;

/**
 * Represents a review tagged to a movie.
 * Makes a new Reviews class with review, rating, name of movie and username.
 * @version 1.0
 * @since 06 Nov 2022
 */
public class Reviews {
	
	/**
	 * Review left by the user.
	 */
	private String review;
	
	/**
	 * Rating left by the user.
	 */
	private double rating;
	
	/**
	 * Name of the movie.
	 */
	private String movieName;
	
	/**
	 * Username of the user.
	 */
	private String username;
	
	/**
	 * Constructs Reviews class the the give parameters.
	 * @param review Review left by user.
	 * @param rating Rating left by user.
	 * @param movieName Name of the movie.
	 * @param username Username of the user.
	 */
	public Reviews(String review, double rating, String movieName, String username) {
		this.review=review;
		this.rating=rating;
		this.movieName=movieName;
		this.username=username;
	}
	
	/**
	 * Basic constructor for the Reviews class.
	 */
	public Reviews() {
		
	}
	
	/**
	 * Gets the content of the review.
	 * @return contents of the review.
	 */
	public String getReview() {
		return review;
	}
	
	/**
	 * Gets the rating of the review.
	 * @return rating of the review.
	 */
	public double getRating() {
		return rating;
	}
	
	/**
	 * Gets the movie name.
	 * @return name of the movie.
	 */
	public String getMovieName() {
		return movieName;
	}
	
	/**
	 * Gets the username.
	 * @return username of the user.
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Sets the contents of the review.
	 * @param review contents of the review.
	 */
	public void setReview(String review) {
		this.review = review;
	}
	
	/**
	 * Sets rating for the review.
	 * @param rating Rating for the review.
	 */
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	/**
	 * Sets the name of the movie.
	 * @param movieName Name of the movie.
	 */
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	
	/**
	 * Sets the username for the review.
	 * @param username Username for the review.
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * Print the parametes of the review.
	 */
	public void printReview() {
		System.out.println("User: "+ username);
		System.out.println("Movie: " + movieName);
		System.out.println("Rating: " + rating);
		System.out.println("Review: " + review);
		System.out.println("");
	}
	
	//data access
	/**
	 * Adds a review to the movie.
	 */
	public void addReview() {
		ReviewsDAO reviewsDAO = new ReviewsDaoImpl();
		reviewsDAO.addReview(this);
	}
	
	/**
	 * Removes a review from the movie.
	 */
	public void removeReview() {
		ReviewsDAO reviewsDAO = new ReviewsDaoImpl();
		reviewsDAO.removeReview(this);
	}
	
	/**
	 * Calculate the new total rating for the movie.
	 * @return new rating of the movie after review.
	 */
	public double updateRating() {
		double newRating=0;
		int count=0;
		ReviewsDAO reviewsDAO = new ReviewsDaoImpl();
		ArrayList<Reviews> x=reviewsDAO.getAllReviews();
		for (Reviews a:x) {
			if (a.getMovieName().equals(this.movieName)) {
				count++;
				newRating+=a.getRating();
			}
		}
		newRating = newRating / count;
		return newRating;
	}
	

}

