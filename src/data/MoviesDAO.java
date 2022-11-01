package data;

import model.Movies.Movies;
import model.Movies.TopMovies;

import java.util.ArrayList;

public interface MoviesDAO {

    public boolean addMovie(Movies movie);
    public void editMovieName(String name, String newName);
    public void editMovieSynopsis(String name, String newSynopsis);
    public void editMoviePGrating(String name, String newPGrating);
    public void editMovieDirector(String name, String newDirector);
    public void editMovieCast(String name, String newCast);
    public void removeMovieFromCinema(String name);
    public void editMovieStatus(String name, String newStatus);
    public ArrayList<Movies> searchMovie(String movieName);
    public ArrayList<Movies> getAllMovies();
    public ArrayList<TopMovies> top5MoviesByUser();
    public void editRating(String movieName, double rating);
    public boolean check3D(String movieName);


    public void editMovieEOS(String movieName, String newEOS);

    public String getEOS(String movieName);
}
