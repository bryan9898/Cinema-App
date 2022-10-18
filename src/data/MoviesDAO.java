package data;

import model.Account;
import model.Movies.Movies;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class MoviesDAO {

    private static final String Account_File="Movies.txt";
    private File dataFile;


    public MoviesDAO() {
        Path dPath = FileSystems.getDefault().getPath("Resources/Data/",Account_File);
        dataFile = new File(dPath.toString());
    }

    public boolean addMovie(Movies movie) {
        boolean existing=false;
        ArrayList<Movies> movies=getAllMovies();
        for (Movies a:movies) {
            if (a.getMovieName().equals(movie.getMovieName())){
                existing=true;
                break;
            }
        }
        if (!existing) {
            movies.add(movie);
            synToFile(movies);
        }
        return !existing;
    }

    public ArrayList<Movies> getAllMovies() {
        Scanner in;
        String record = null;
        String[] fields;
        ArrayList<Movies> MV = new ArrayList<Movies>();
        try {
            in = new Scanner(dataFile);
            while (in.hasNextLine()) {
                record=in.nextLine();
                fields=record.split(";;");
                String movieName = fields[0];
                String movieType = fields[1];
                String moviePGRating = fields[2];
                String description = fields[3];
                String director = fields[4];
                String cast = fields[5];
                String rating = fields[6];
                String movieLength = fields[7];
                String showStatus = fields[8];
                Movies a = new Movies(movieName, movieType, moviePGRating, description, director, cast, movieLength,showStatus);
                a.setRating(Double.parseDouble(rating));
                MV.add(a);
            }
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("No record found!");
            //e.printStackTrace();
        }
        return MV;
    }



    private void synToFile(ArrayList<Movies> movies) {
        if (movies==null)
            return;

        try {
            FileWriter out = new FileWriter(dataFile);
            for (Movies a: movies) {
                out.append(a.getMovieName()+";;"+a.getType()+";;"+a.getPGrating()+";;"+a.getDescription()+";;"+a.getDirector()+";;"+a.getCast()+";;"+a.getRating()+";;"+a.getRuntime()+";;"+a.getShowStatus()+"\r\n");
            }
            out.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
