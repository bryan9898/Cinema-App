package model.Movies;

import data.MoviesDAO;
import data.impl.MoviesDaoImpl;

import java.util.ArrayList;

public class Movies {
	private static final String ANSI_RESET = "\u001B[0m";
	private static final String ANSI_YELLOW = "\u001B[33m";
	private String MovieName;
	private String type;
	private String PGrating;
	private String Description;
	private String Director;
	private String Cast;
	private double rating;
	private String runtime; // in mins
	private String EOS ;

	private String showStatus;
	
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

	public Movies() {

	}

	public void setMovieName(String movieName) {
		MovieName = movieName;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setPGrating(String PGrating) {
		this.PGrating = PGrating;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public void setDirector(String director) {
		Director = director;
	}

	public void setCast(String cast) {
		Cast = cast;
	}

	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}

	public void setShowStatus(String showStatus) {
		this.showStatus = showStatus;
	}

	public void setRating(double rating) {
		this.rating= rating;
	}

	public String getMovieName() {
		return MovieName;
	}

	public String getType() {
		return type;
	}

	public String getPGrating() {
		return PGrating;
	}

	public String getDescription() {
		return Description;
	}

	public String getDirector() {
		return Director;
	}

	public String getCast() {
		return Cast;
	}

	public double getRating() {
		return rating;
	}

	public String getRuntime() {
		return runtime;
	}

	public String getShowStatus() {
		return showStatus;
	}

	public String getEOS() {return EOS;}

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



    public String getMovieStatus() {
		return showStatus;
    }

	public void setEOS(String newEOS) {
		this.EOS = newEOS;
	}
}
