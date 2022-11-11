package model.Movies;

import data.MoviesDAO;
import data.impl.MoviesDaoImpl;

import java.util.ArrayList;

/**
 * Represents a movie registered in the system.
 * Makes a new Movies class with movie name, movie type, PG rating, movie description, director, cast names, rating of movie, runtime, end of showing date and the status of the show.
 * @version 1.0
 * @since 06 Nov 2022
 */
public class Movies {
	
	/**
	 * This line adds colour to the console.
	 */
	private static final String ANSI_RESET = "\u001B[0m";
	
	/**
	 * This line adds colour to the console.
	 */
	private static final String ANSI_YELLOW = "\u001B[33m";
	
	/**
	 * Name of the movie.
	 */
	private String MovieName;
	
	/**
	 * Type of the movie. i.e. blockbuster or 3D.
	 */
	private String type;
	
	/**
	 * PG rating for the movie.
	 */
	private String PGrating;
	
	/**
	 * Description for the movie.
	 */
	private String Description;
	
	/**
	 * Director for the movie.
	 */
	private String Director;
	
	/**
	 * Names for the cast.
	 */
	private String Cast;
	
	/**
	 * Rating for the movie based on the reviews.
	 */
	private double rating;
	
	/**
	 * Runtime for the movie.
	 */
	private String runtime; // in mins
	
	/**
	 * Date for the end of showing
	 */
	private String EOS ;

	/**
	 * Show status for the movie.
	 */
	private String showStatus;
	
	/**
	 * Creates a Movie class with the given parameters.
	 * @param MovieName Name of the movie.
	 * @param type Type of the movie. i.e. blockbuster or 3D.
	 * @param PGrating PG rating for the movie.
	 * @param Description Description for the movie.
	 * @param Director Director for the movie.
	 * @param Cast Names for the cast.
	 * @param runtime Runtime for the movie.
	 * @param showStatus Show status for the movie.
	 * @param EOS Date for the end of showing
	 */
	public Movies(String MovieName,String type,String PGrating,String Description,String Director, String Cast,String runtime,String showStatus,String EOS) {
		this.MovieName=MovieName;
		this.type=type;
		this.PGrating=PGrating;
		this.Description=Description;
		this.Director=Director;
		this.Cast=Cast;
		this.rating = 0.0;
		this.runtime=runtime;
		this.showStatus = showStatus;
		this.EOS = EOS;
	}

	/**
	 * Default constructor for the Movies class.
	 */
	public Movies() {

	}

	/**
	 * Sets the movie name.
	 * @param movieName name of the movie.
	 */
	public void setMovieName(String movieName) {
		MovieName = movieName;
	}

	/**
	 * Sets the movie type.
	 * @param type Type of the movie.
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Sets the PG rating for the movie.
	 * @param PGrating PG rating for the movie.
	 */
	public void setPGrating(String PGrating) {
		this.PGrating = PGrating;
	}

	/**
	 * Sets the description for the movie.
	 * @param description Description for the movie.
	 */
	public void setDescription(String description) {
		Description = description;
	}

	/**
	 * Sets the director name for the movie.
	 * @param director Director name for the movie.
	 */
	public void setDirector(String director) {
		Director = director;
	}

	/**
	 * Sets the cast names for the movie.
	 * @param cast Cast names for the movie.
	 */
	public void setCast(String cast) {
		Cast = cast;
	}

	/**
	 * Sets the runtime for the movie.
	 * @param runtime Runtime for the movie.
	 */
	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}

	/**
	 * Sets the show status for the movie.
	 * @param showStatus Show Status for the movie.
	 */
	public void setShowStatus(String showStatus) {
		this.showStatus = showStatus;
	}

	/**
	 * Sets the rating for the movie.
	 * @param rating Rating for the movies.
	 */
	public void setRating(double rating) {
		this.rating= rating;
	}

	/**
	 * Gets the movie name.
	 * @return movie name.
	 */
	public String getMovieName() {
		return MovieName;
	}

	/**
	 * Gets the type of movie.
	 * @return type of the moive.
	 */
	public String getType() {
		return type;
	}

	/**
	 * Gets the PG rating for the movie.
	 * @return PG rating for the movie.
	 */
	public String getPGrating() {
		return PGrating;
	}

	/**
	 * Gets the description for the movie.
	 * @return description for the movie.
	 */
	public String getDescription() {
		return Description;
	}

	/**
	 * Gets the director name.
	 * @return directors name.
	 */
	public String getDirector() {
		return Director;
	}

	/**
	 * Gets the cast names.
	 * @return names of the cast.
	 */
	public String getCast() {
		return Cast;
	}

	/**
	 * Gets the rating for the movies.
	 * @return rating for the movies.
	 */
	public double getRating() {
		return rating;
	}

	/**
	 * Gets the runtime for the movie.
	 * @return runtime for the movie.
	 */
	public String getRuntime() {
		return runtime;
	}

	/**
	 * Gets the show status for the movie.
	 * @return show status for the movie.
	 */
	public String getShowStatus() {
		return showStatus;
	}

	/**
	 * Gets the end of showing date.
	 * @return end of showing date.
	 */
	public String getEOS() {
		return EOS;
	}

	/**
	 * Prints all the parameters in the Movie class.
	 */
	public void printAll() {

		System.out.println("Movie Name: "+ ANSI_YELLOW +MovieName + ANSI_RESET);
		System.out.println("Movie Type: "+ANSI_YELLOW +type + ANSI_RESET);
		System.out.println("Movie PGrating: "+ANSI_YELLOW+PGrating+ ANSI_RESET);
		System.out.println("Movie Description: "+ANSI_YELLOW+Description+ ANSI_RESET);
		System.out.println("Movie Director: "+ANSI_YELLOW+Director+ ANSI_RESET);
		System.out.println("Movie Cast: "+ANSI_YELLOW+Cast+ ANSI_RESET);
		if(rating == 0) {
			System.out.println("Movie Rating: "+ANSI_YELLOW+"N.A"+ ANSI_RESET);
		}
		else {
			System.out.println("Movie Rating: "+ANSI_YELLOW+rating+ ANSI_RESET);
		}
		System.out.println("Movie Runtime: "+ANSI_YELLOW+runtime+ ANSI_RESET);
		String showStatusWord = "";
		switch (showStatus) {
			case "1": showStatusWord = "Coming Soon"; break;
			case "2": showStatusWord = "Preview"; break;
			case "3": showStatusWord = "Now Showing"; break;
			case "4": showStatusWord = "End of Showing"; break;
		}
		System.out.println("Movie ShowStatus: "+ANSI_YELLOW+showStatusWord+ ANSI_RESET);
		System.out.println("Movie EOS date: "+ANSI_YELLOW+EOS+ ANSI_RESET);
	}

	/**
	 * Lists out all the movies initialized in the system.
	 */
	public void listMovies() {
		MoviesDAO mDAO = new MoviesDaoImpl();
		ArrayList<Movies> movies = mDAO.getAllMovies();

		System.out.printf("\n");
		System.out.printf("\n");
		System.out.printf("\t------------------------------------\n"); // \tab
		System.out.printf("\t");
		System.out.printf("\u001B[47m" + "\u001B[30m");
		System.out.printf("           List Of Movies           ");
		System.out.printf("\u001B[0m");
		System.out.printf("\n\t------------------------------------");
		System.out.printf("\n");
		
		int i = 1;

		for (Movies m : movies) {
			System.out.println("--------------------------------------");
			System.out.println("Index: " + i);
			i++;
			m.printAll();
		}
		System.out.println("--------------------------------------");
		System.out.println("\n");
	}

	/**
	 * Searches for movies based on the keywords and prints them out.
	 * @param movieName keyword for the search.
	 */
	public void searchMovie(String movieName) {
		MoviesDAO moviesDAO = new MoviesDaoImpl();
		ArrayList<Movies> movies = moviesDAO.searchMovie(movieName);
		for (Movies m : movies) {
			System.out.println("--------------------------------------");
			m.printAll();
		}
		System.out.println("--------------------------------------");
		System.out.println("\n");
	}

	/**
	 * Gets status of the movie.
	 * @return status of the movie.
	 */
    public String getMovieStatus() {
		return showStatus;
    }

    /**
     * Sets the end of showing date for the movie.
     * @param newEOS new end of showing date
     */
	public void setEOS(String newEOS) {
		this.EOS = newEOS;
	}
}
