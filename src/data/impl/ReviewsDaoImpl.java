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

public class ReviewsDaoImpl implements ReviewsDAO {
    private static final String Reviews_File="Reviews.txt";
    private File dataFile;

    public ReviewsDaoImpl() {
        Path dPath = FileSystems.getDefault().getPath("Resources/Data/",Reviews_File);
        dataFile = new File(dPath.toString());
    }
    
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

    public void addReview(Reviews reviews) {
        ArrayList<Reviews> x=getAllReviews();
        x.add(reviews);
        synToFile(x);
    }

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
