package data.impl;

import data.MoviesDAO;
import model.Movies.Movies;
import model.Movies.TopMovies;

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

public class MoviesDaoImpl implements MoviesDAO {
    private static final String Account_File="Movies.txt";
    private File dataFile;


    public MoviesDaoImpl() {
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
                String EOS = fields[9];

                Movies a = new Movies(movieName, movieType, moviePGRating, description, director, cast, movieLength,showStatus,EOS);
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
                    Path dPath = FileSystems.getDefault().getPath("Resources/Data/","myTempFile.txt");
                    File tempFile = new File(dPath.toString());
                    BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

                    String lineToRemove = MV.get(i).getMovieName()+";;"+MV.get(i).getType()+";;"+MV.get(i).getPGrating()+";;"+MV.get(i).getDescription()+";;"+MV.get(i).getDirector()+";;"+MV.get(i).getCast()+";;"+MV.get(i).getRating()+";;"+MV.get(i).getRuntime()+";;"+MV.get(i).getShowStatus()+";;"+MV.get(i).getEOS();
                    String currentLine;
                    int removed = 0;
                    while((currentLine = reader.readLine()) != null) {
                        // trim newline when comparing with lineToRemove
                        String trimmedLine = currentLine.trim();
                        if(trimmedLine.contains(lineToRemove) && removed == 0) {
                            removed++;
                            continue;
                        }
                        writer.write(currentLine + System.getProperty("line.separator"));
                    }
                    writer.close();
                    reader.close();
                    Files.move(dPath, dataFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    MV.remove(MV.get(i));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return MV;
    }



    private void synToFile(ArrayList<Movies> movies) {
        if (movies==null)
            return;

        try {
            FileWriter out = new FileWriter(dataFile);
            for (Movies a: movies) {
                out.append(a.getMovieName()+";;"+a.getType()+";;"+a.getPGrating()+";;"+a.getDescription()+";;"+a.getDirector()+";;"+a.getCast()+";;"+a.getRating()+";;"+a.getRuntime()+";;"+a.getShowStatus()+";;"+a.getEOS()+"\r\n");
            }
            out.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void editMovieName(String name, String newName) {
        ArrayList<Movies> movies=getAllMovies();
        for (Movies a:movies) {
            if (a.getMovieName().equals(name)) {
                a.setMovieName(newName);
                break;
            }
        }
        synToFile(movies);
    }

    public void editMovieSynopsis(String name, String newSynopsis) {
        ArrayList<Movies> movies=getAllMovies();
        for (Movies a:movies) {
            if (a.getMovieName().equals(name)) {
                a.setDescription(newSynopsis);
                break;
            }
        }
        synToFile(movies);
    }

    public void editMoviePGrating(String name, String newPGrating) {
        ArrayList<Movies> movies=getAllMovies();
        for (Movies a:movies) {
            if (a.getMovieName().equals(name)) {
                a.setPGrating(newPGrating);
                break;
            }
        }
        synToFile(movies);
    }

    public void editMovieDirector(String name, String newDirector) {
        ArrayList<Movies> movies=getAllMovies();
        for (Movies a:movies) {
            if (a.getMovieName().equals(name)) {
                a.setDirector(newDirector);
                break;
            }
        }
        synToFile(movies);
    }

    public void editMovieCast(String name, String newCast) {
        ArrayList<Movies> movies=getAllMovies();
        for (Movies a:movies) {
            if (a.getMovieName().equals(name)) {
                a.setCast(newCast);
                break;
            }
        }
        synToFile(movies);
    }

    public void removeMovieFromCinema(String name) {
        ArrayList<Movies> movies=getAllMovies();
        for (Movies a:movies) {
            if (a.getMovieName().equals(name)) {
                movies.remove(a);
                break;
            }
        }
        synToFile(movies);
    }

    public void editMovieStatus(String name, String newStatus) {
        ArrayList<Movies> movies=getAllMovies();
        for (Movies a:movies) {
            if (a.getMovieName().equals(name)) {
                a.setShowStatus(newStatus);
                break;
            }
        }
        synToFile(movies);
    }

    public ArrayList<Movies> searchMovie(String movieName) {
        ArrayList<Movies> movies=getAllMovies();
        ArrayList<Movies> MV = new ArrayList<Movies>();
        for (Movies a:movies) {
            if ( Pattern.compile(Pattern.quote(movieName), Pattern.CASE_INSENSITIVE).matcher(a.getMovieName()).find()) {
                MV.add(a);
            }
        }
        return MV;
    }

    public boolean check3D(String movieName) {
        ArrayList<Movies> movies=getAllMovies();
        for (Movies a:movies) {
            if (a.getMovieName().equals(movieName)) {
                if (a.getType().equals("3D")) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void editMovieEOS(String movieName, String newEOS) {
        ArrayList<Movies> movies=getAllMovies();
        for (Movies a:movies) {
            if (a.getMovieName().equals(movieName)) {
                a.setEOS(newEOS);
                break;
            }
        }
        synToFile(movies);
    }

    @Override
    public String getEOS(String movieName) {
        ArrayList<Movies> movies=getAllMovies();
        for (Movies a:movies) {
            if (a.getMovieName().equals(movieName)) {
               return a.getEOS();
            }
        }
        return "null";
    }

    public ArrayList<TopMovies> top5MoviesByUser() {
        ArrayList<Movies> movies=getAllMovies();
        ArrayList<TopMovies> top5 = new ArrayList<TopMovies>();
        for (Movies a:movies) {
            if (a.getRating()!=0) {
                TopMovies b = new TopMovies(a.getMovieName(), a.getRating());
                top5.add(b);
            }
        }
        return top5;
    }

    public void editRating(String movieName, double rating) {
        ArrayList<Movies> movies=getAllMovies();
        for (Movies a:movies) {
            if (a.getMovieName().equals(movieName)) {
                a.setRating(rating);
                break;
            }
        }
        synToFile(movies);
    }
}
