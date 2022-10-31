package model.Movies;

import data.ReviewsDAO;

public class Reviews {
	
	private String review;
	private int rating;
	private String movieName;
	private String username;
	
	public Reviews(String review, int rating, String movieName, String username) {
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
	
	public int getRating() {
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
		System.out.println(username + ": " + review);
	}
	

}

