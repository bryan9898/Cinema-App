package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

import model.Movies.Reviews;

/**
 * This interface is used to define the methods that are used to access the Reviews Data
 * @version 1.0
 * @since 06 Nov 2022
 */

public interface ReviewsDAO {
    /**
     * This method is to add a review into the reviews file.
     * @param reviews The review object to add
     */
    public void addReview(Reviews reviews);
    /**
     * This method is to remove a review from the reviews file.
     * @param reviews The review object to remove
     */
    public void removeReview(Reviews reviews);
    /**
     * This method is to get all the reviews from the reviews file.
     * @return The array list of reviews
     */
    public ArrayList<Reviews> getAllReviews();

    /**
     * This method is to edit a movie Name in the reviews file.
     * @param movieName The old movie name to edit
     * @param newName The new movie name to change to
     */
    void updateMovieName(String movieName, String newName);
}
