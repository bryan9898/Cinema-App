package model.Movies;

import java.util.ArrayList;

import data.MoviesDAO;
import data.ReviewsDAO;
import data.impl.ReviewsDaoImpl;

public class Reviews {
	
	private String review;
	private double rating;
	private String movieName;
	private String username;
	
	public Reviews(String review, double rating, String movieName, String username) {
		this.review=review;
		this.rating=rating;
		this.movieName=movieName;
		this.username=username;
	}
	
	public Reviews() {
		
	}
	
	public String getReview() {
		return review;
	}
	
	public double getRating() {
		return rating;
	}
	
	public String getMovieName() {
		return movieName;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setReview(String review) {
		this.review = review;
	}
	
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void printReview() {
		System.out.println("User: "+ username);
		System.out.println("Movie: " + movieName);
		System.out.println("Rating: " + rating);
		System.out.println("Review: " + review);
		System.out.println("");
	}
	
	//data access
	public void addReview() {
		ReviewsDAO reviewsDAO = new ReviewsDaoImpl();
		reviewsDAO.addReview(this);
	}
	
	public void removeReview() {
		ReviewsDAO reviewsDAO = new ReviewsDaoImpl();
		reviewsDAO.removeReview(this);
	}
	
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

