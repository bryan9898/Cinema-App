package data.impl;

import data.ReviewsDAO;
import model.Movies.Reviews;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class represents the reviews data access object.
 * The reviews data access object is used to access the reviews data.
 * @version 1.0
 * @since 06 Nov 2022
 */
public class ReviewsDaoImpl implements ReviewsDAO {
    /**
     * The reviews file path.
     */
    private static final String Reviews_File="Reviews.txt";
    /**
     * The File object to access the reviews file.
     */
    private File dataFile;

    /**
     * The constructor to make a reviews data access object.
     */
    public ReviewsDaoImpl() {
        Path dPath = FileSystems.getDefault().getPath("Resources/Data/",Reviews_File);
        dataFile = new File(dPath.toString());
    }

    /**
     * This method is to remove a review from the reviews file.
     * @param reviews The review object to remove
     */
    public void removeReview(Reviews reviews) {
    	ArrayList<Reviews> x = getAllReviews();
    	int count=0;
    	for(Reviews r:x) {
    		if(r.getMovieName().equals(reviews.getMovieName()) && r.getReview().equals(reviews.getReview()) && r.getRating()==reviews.getRating() && r.getUsername().equals(reviews.getUsername())) {
    			break;
    		}
    		count++;
    	}
    	x.remove(count);
    	synToFile(x);
    }

    /**
     * This method is to add a review into the reviews file.
     * @param reviews The review object to add
     */
    public void addReview(Reviews reviews) {
        ArrayList<Reviews> x=getAllReviews();
        x.add(reviews);
        synToFile(x);
    }

    /**
     * This method is to save the reviews data into the reviews file.
     * @param x The array list of reviews to save
     */
    private void synToFile(ArrayList<Reviews> x) {
        if (x==null)
            return;

        try {
            FileWriter out = new FileWriter(dataFile);
            for (Reviews a: x) {
                out.append(a.getMovieName()+";;"+a.getUsername()+";;"+String.valueOf(a.getRating())+";;"+a.getReview()+"\r\n");
            }
            out.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * This method is to get all the reviews from the reviews file.
     * @return The array list of reviews
     */
    public ArrayList<Reviews> getAllReviews(){
        ArrayList<Reviews> reviews = new ArrayList<Reviews>();
        Scanner in;
        String record = null;
        String[] fields;
        try {
            in = new Scanner(dataFile);
            while (in.hasNextLine()) {
                record=in.nextLine();
                fields=record.split(";;");
                String movieName = fields[0];
                String username = fields[1];
                String rating1 = fields[2];
                double rating = Double.parseDouble(rating1);
                String review = fields[3];
                Reviews a = new Reviews(review, rating, movieName, username);
                reviews.add(a);
            }
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("No record found!");
            //e.printStackTrace();
        }
        return reviews;
    }

    /**
     * This method is to edit a movie Name in the reviews file.
     * @param movieName The old movie name to edit
     * @param newName The new movie name to change to
     */
    public void updateMovieName(String movieName, String newName){
        ArrayList<Reviews> reviews = getAllReviews();
        for (Reviews r: reviews) {
            if (r.getMovieName().equals(movieName)) {
                r.setMovieName(newName);
            }
        }
        synToFile(reviews);
    }
}
