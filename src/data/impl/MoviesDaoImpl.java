package data.impl;

import data.MoviesDAO;
import data.TimeSlotsDAO;
import model.Movies.Movies;
import model.Movies.TimeSlots;


import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * This class represents the movies data access object.
 * The movies data access object is used to access the movies data.
 * @version 1.0
 * @since 06 Nov 2022
 */
public class MoviesDaoImpl implements MoviesDAO {
    /**
     * The movies file path.
     */
    private static final String Account_File = "Movies.txt";
    /**
     * The File object to access the movies file.
     */
    private File dataFile;

    /**
     * The constructor to make a movies data access object.
     */
    public MoviesDaoImpl() {
        Path dPath = FileSystems.getDefault().getPath("Resources/Data/", Account_File);
        dataFile = new File(dPath.toString());
    }

    /**
     * This method is to add a movies into the movies file.
     * @param movie The movies to add
     * @return true if the movies is added else false
     */
    public boolean addMovie(Movies movie) {
        boolean existing = false;
        ArrayList<Movies> movies = getAllMovies();
        for (Movies a : movies) {
            if (a.getMovieName().equals(movie.getMovieName())) {
                existing = true;
                break;
            }
        }
        if (!existing) {
            movies.add(movie);
            synToFile(movies);
        }
        return !existing;
    }

    /**
     * This method is to get all the movies from the movies file.
     * @return the list of objects of movies
     */
    public ArrayList<Movies> getAllMovies() {
        Scanner in;
        String record = null;
        String[] fields;
        ArrayList<Movies> MV = new ArrayList<Movies>();
        try {
            in = new Scanner(dataFile);
            while (in.hasNextLine()) {
                record = in.nextLine();
                fields = record.split(";;");
                String movieName = fields[0];
                String movieType = fields[1];
                String moviePGRating = fields[2];
                String description = fields[3];
                String director = fields[4];
                String cast = fields[5];
                String rating = fields[6];
                String movieLength = fields[7];
                String showStatus = fields[8];
                String EOS = fields[9];

                Movies a = new Movies(movieName, movieType, moviePGRating, description, director, cast, movieLength, showStatus, EOS);
                a.setRating(Double.parseDouble(rating));
                MV.add(a);
            }
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("No record found!");
            //e.printStackTrace();
        }

        for (int i = 0; i < MV.size(); i++) {
            if (LocalDate.parse(MV.get(i).getEOS(), DateTimeFormatter.ofPattern("dd/MM/yyyy")).isBefore(LocalDate.now())) {
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(dataFile));
                    Path dPath = FileSystems.getDefault().getPath("Resources/Data/", "myTempFile.txt");
                    File tempFile = new File(dPath.toString());
                    BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

                    String lineToRemove = MV.get(i).getMovieName() + ";;" + MV.get(i).getType() + ";;" + MV.get(i).getPGrating() + ";;" + MV.get(i).getDescription() + ";;" + MV.get(i).getDirector() + ";;" + MV.get(i).getCast() + ";;" + MV.get(i).getRating() + ";;" + MV.get(i).getRuntime() + ";;" + MV.get(i).getShowStatus() + ";;" + MV.get(i).getEOS();
                    String currentLine;
                    int removed = 0;
                    while ((currentLine = reader.readLine()) != null) {
                        // trim newline when comparing with lineToRemove
                        String trimmedLine = currentLine.trim();
                        if (trimmedLine.contains(lineToRemove) && removed == 0) {
                            removed++;
                            continue;
                        }
                        writer.write(currentLine + System.getProperty("line.separator"));
                    }
                    writer.close();
                    reader.close();
                    Files.move(dPath, dataFile.toPath(), StandardCopyOption.REPLACE_EXISTING);


                    System.out.println(MV.get(i).getMovieName());
                    TimeSlotsDAO tsDAO = new TimeSlotsDaoImpl();
                    int counter = 0;
                    for (TimeSlots ts : tsDAO.getAllTimeSlot()) {
                        if (ts.getMovieName().equals(MV.get(i).getMovieName())) {
                            tsDAO.removeTimeSlots(counter);
                        }
                        counter++;
                    }

                    MV.remove(MV.get(i));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return MV;
    }


    /**
     * This method is to save the movies list into the movies file.
     * @param movies The list of movies
     */
    private void synToFile(ArrayList<Movies> movies) {
        if (movies == null)
            return;

        try {
            FileWriter out = new FileWriter(dataFile);
            for (Movies a : movies) {
                out.append(a.getMovieName() + ";;" + a.getType() + ";;" + a.getPGrating() + ";;" + a.getDescription() + ";;" + a.getDirector() + ";;" + a.getCast() + ";;" + a.getRating() + ";;" + a.getRuntime() + ";;" + a.getShowStatus() + ";;" + a.getEOS() + "\r\n");
            }
            out.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * This method is to edit movie name in the movies file.
     * @param name The name of the movie to edit
     * @param newName The new movie name
     */
    public void editMovieName(String name, String newName) {
        ArrayList<Movies> movies = getAllMovies();
        for (Movies a : movies) {
            if (a.getMovieName().equals(name)) {
                a.setMovieName(newName);
                break;
            }
        }
        synToFile(movies);
    }

    /**
     * This method is to edit movie Synopsis in the movies file.
     * @param name The name of the movie to edit
     * @param newSynopsis The new movie Synopsis
     */
    public void editMovieSynopsis(String name, String newSynopsis) {
        ArrayList<Movies> movies = getAllMovies();
        for (Movies a : movies) {
            if (a.getMovieName().equals(name)) {
                a.setDescription(newSynopsis);
                break;
            }
        }
        synToFile(movies);
    }

    /**
     * This method is to edit movie PGrating in the movies file.
     * @param name The name of the movie to edit
     * @param newPGrating The new movie PGrating
     */
    public void editMoviePGrating(String name, String newPGrating) {
        ArrayList<Movies> movies = getAllMovies();
        for (Movies a : movies) {
            if (a.getMovieName().equals(name)) {
                a.setPGrating(newPGrating);
                break;
            }
        }
        synToFile(movies);
    }

    /**
     * This method is to edit movie Director in the movies file.
     * @param name The name of the movie to edit
     * @param newDirector The new movie Director
     */
    public void editMovieDirector(String name, String newDirector) {
        ArrayList<Movies> movies = getAllMovies();
        for (Movies a : movies) {
            if (a.getMovieName().equals(name)) {
                a.setDirector(newDirector);
                break;
            }
        }
        synToFile(movies);
    }

    /**
     * This method is to edit movie Cast in the movies file.
     * @param name The name of the movie to edit
     * @param newCast The new movie Cast
     */
    public void editMovieCast(String name, String newCast) {
        ArrayList<Movies> movies = getAllMovies();
        for (Movies a : movies) {
            if (a.getMovieName().equals(name)) {
                a.setCast(newCast);
                break;
            }
        }
        synToFile(movies);
    }

    /**
     * This method is to remove a movie from the movies file.
     * @param name The name of the movie to edit
     */
    public void removeMovieFromCinema(String name) {
        ArrayList<Movies> movies = getAllMovies();
        for (Movies a : movies) {
            if (a.getMovieName().equals(name)) {
                movies.remove(a);
                break;
            }
        }
        TimeSlotsDAO tsDAO = new TimeSlotsDaoImpl();
        int counter = 0;
        for (TimeSlots ts : tsDAO.getAllTimeSlot()) {
            if (ts.getMovieName().equals(name)) {
                tsDAO.removeTimeSlots(counter);
            }
            counter++;
        }
        synToFile(movies);
    }

    /**
     * This method is to edit movie Status in the movies file.
     * @param name The name of the movie to edit
     * @param newStatus The new movie status
     */
    public void editMovieStatus(String name, String newStatus) {
        ArrayList<Movies> movies = getAllMovies();
        for (Movies a : movies) {
            if (a.getMovieName().equals(name)) {
                a.setShowStatus(newStatus);
                break;
            }
        }
        synToFile(movies);
    }

    /**
     * This method is to search movie by name in the movies file.
     * @param movieName The name of the movie to edit
     * @return A list of movie objects
     */
    public ArrayList<Movies> searchMovie(String movieName) {
        ArrayList<Movies> movies = getAllMovies();
        ArrayList<Movies> MV = new ArrayList<Movies>();
        for (Movies a : movies) {
            if (Pattern.compile(Pattern.quote(movieName), Pattern.CASE_INSENSITIVE).matcher(a.getMovieName()).find()) {
                MV.add(a);
            }
        }
        return MV;
    }

    /**
     * This method is to check if the movie is a 3D movie
     * @param movieName The name of the movie to check
     * @return true if the movie is a 3D movie, false otherwise
     */
    public boolean check3D(String movieName) {
        ArrayList<Movies> movies = getAllMovies();
        for (Movies a : movies) {
            if (a.getMovieName().equals(movieName)) {
                if (a.getType().equals("3D")) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This method is to edit movie End of Showing in the movies file.
     * @param movieName The name of the movie to edit
     * @param newEOS The movie's new EOS
     */
    @Override
    public void editMovieEOS(String movieName, String newEOS) {
        ArrayList<Movies> movies = getAllMovies();
        for (Movies a : movies) {
            if (a.getMovieName().equals(movieName)) {
                a.setEOS(newEOS);
                break;
            }
        }
        synToFile(movies);
    }

    /**
     * This method is to get the movie's End of Showing
     * @param movieName The name of the movie to edit
     * @return The movie's EOS
     */
    @Override
    public String getEOS(String movieName) {
        ArrayList<Movies> movies = getAllMovies();
        for (Movies a : movies) {
            if (a.getMovieName().equals(movieName)) {
                return a.getEOS();
            }
        }
        return "null";
    }

    /**
     * This method is to get top 5 movies by ratings
     * @return A list of movie objects
     */
    public ArrayList<Movies> top5MoviesByUser() {
        ArrayList<Movies> movies = getAllMovies();
        ArrayList<Movies> top5 = new ArrayList<Movies>();
        for (int i = 0; i < 5; i++) {
            if (movies.size() == 0) {
                break;
            }
            Movies max = movies.get(0);
            while (max.getRating() == 0) {
                movies.remove(max);
                if (movies.size() == 0) {
                    break;
                }
                max = movies.get(0);
            }
            if (movies.size() == 0) {
                break;
            }

            for (Movies t : movies) {
                if (t.getRating() > max.getRating() && t.getRating() != 0.0) {
                    max = t;
                }
            }
            movies.remove(max);
            top5.add(max);
        }
        return top5;
    }

    /**
     * This method is to edit movie rating in the movies file.
     * @param movieName The name of the movie to edit
     * @param rating The movie's new rating
     */
    public void editRating(String movieName, double rating) {
        ArrayList<Movies> movies = getAllMovies();
        for (Movies a : movies) {
            if (a.getMovieName().equals(movieName)) {
                a.setRating(rating);
                break;
            }
        }
        synToFile(movies);
    }

    /**
     * This method is to get the movie's object
     * @param movieName The name of the movie to get
     * @return The movie's object
     */
    public Movies getMovie(String movieName) {
        ArrayList<Movies> movies = getAllMovies();
        for (Movies a : movies) {
            if (a.getMovieName().equals(movieName)) {
                return a;
            }
        }
        return null;
    }

}
