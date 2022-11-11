package data;

import model.Movies.Movies;
import model.Movies.TopMovies;

import java.util.ArrayList;

/**
 * This interface is used to define the methods that are used to access the Movie Data
 * @version 1.0
 * @since 06 Nov 2022
 */
public interface MoviesDAO {

    /**
     * This method is to add a movie into the movie file.
     * @param movie The movie to add
     * @return true if the movie is added else false
     */
    public boolean addMovie(Movies movie);
    /**
     * This method is to edit movie name in the movies file.
     * @param name The name of the movie to edit
     * @param newName The new movie name
     */
    public void editMovieName(String name, String newName);
    /**
     * This method is to edit movie Synopsis in the movies file.
     * @param name The name of the movie to edit
     * @param newSynopsis The new movie Synopsis
     */
    public void editMovieSynopsis(String name, String newSynopsis);
    /**
     * This method is to edit movie PGrating in the movies file.
     * @param name The name of the movie to edit
     * @param newPGrating The new movie PGrating
     */
    public void editMoviePGrating(String name, String newPGrating);
    /**
     * This method is to edit movie Director in the movies file.
     * @param name The name of the movie to edit
     * @param newDirector The new movie Director
     */
    public void editMovieDirector(String name, String newDirector);
    /**
     * This method is to edit movie Cast in the movies file.
     * @param name The name of the movie to edit
     * @param newCast The new movie Cast
     */
    public void editMovieCast(String name, String newCast);
    /**
     * This method is to remove a movie from the movies file.
     * @param name The name of the movie to edit
     */
    public void removeMovieFromCinema(String name);
    /**
     * This method is to edit movie Status in the movies file.
     * @param name The name of the movie to edit
     * @param newStatus The new movie status
     */
    public void editMovieStatus(String name, String newStatus);
    /**
     * This method is to search movie by name in the movies file.
     * @param movieName The name of the movie to edit
     * @return A list of movie objects
     */
    public ArrayList<Movies> searchMovie(String movieName);
    /**
     * This method is to get all the movies from the movies file.
     * @return the list of objects of movies
     */
    public ArrayList<Movies> getAllMovies();
    /**
     * This method is to get top 5 movies by ratings
     * @return A list of movie objects
     */
    public ArrayList<Movies> top5MoviesByUser();
    /**
     * This method is to edit movie rating in the movies file.
     * @param movieName The name of the movie to edit
     * @param rating The movie's new rating
     */
    public void editRating(String movieName, double rating);
    /**
     * This method is to check if the movie is a 3D movie
     * @param movieName The name of the movie to check
     * @return true if the movie is a 3D movie, false otherwise
     */
    public boolean check3D(String movieName);

    /**
     * This method is to edit movie End of Showing in the movies file.
     * @param movieName The name of the movie to edit
     * @param newEOS The movie's new EOS
     */
    public void editMovieEOS(String movieName, String newEOS);
    /**
     * This method is to get the movie's End of Showing
     * @param movieName The name of the movie to edit
     * @return The movie's EOS
     */
    public String getEOS(String movieName);

    /**
     * This method is to get the movie's object
     * @param movieName The name of the movie to get
     * @return The movie's object
     */
    Movies getMovie(String movieName);
}
