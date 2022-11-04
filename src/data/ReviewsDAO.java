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

public interface ReviewsDAO {

    public void addReview(Reviews reviews);
    public void removeReview(Reviews reviews);
    public ArrayList<Reviews> getAllReviews();


}
