package data.impl;

import data.CinemaDAO;
import model.Cinema;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class represents the cinema data access object.
 * The cinema data access object is used to access the cinema data.
 * @version 1.0
 * @since 06 Nov 2022
 */
public class CinemaDaoImpl implements CinemaDAO {
    /**
     * The cinema file path.
     */
    private static final String Account_File="Cinema.txt";
    /**
     * The File object to access the cinema file.
     */
    private static File dataFile;

    /**
     * The constructor to make a cinema data access object.
     */
    public CinemaDaoImpl() {
        Path dPath = FileSystems.getDefault().getPath("Resources/Data/",Account_File);
        dataFile = new File(dPath.toString());
    }

    /**
     * This method is to get all Cinema objects from the cinema file.
     * @return the list of cinema objects
     */
    public static ArrayList<Cinema> getLayouts(){
        Scanner in;
        String record = null;
        String[] fields;

        ArrayList<Cinema> cinemas = new ArrayList<Cinema>();
        try {
            in = new Scanner(dataFile);
            while (in.hasNextLine()) {
                record=in.nextLine();
                fields=record.split(";");
                String cineplexCode = fields[0];
                String cinemaCode = fields[1];
                String layout = fields[2];

                Cinema a = new Cinema(cineplexCode,cinemaCode,layout);
                cinemas.add(a);
            }
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("No record found!");
            //e.printStackTrace();
        }
        return cinemas;
    }

    /**
     * This method is to get the layout of a cinema
     * @param cineplexCode The cineplex code
     * @param cinemaCode The cinema code
     * @return the layout of the cinema
     */
    public String getLayout(String cineplexCode, String cinemaCode) {
        ArrayList<Cinema> cinemas = getLayouts();

        for (Cinema a:cinemas) {
            if (a.getCineplexCode().equals(cineplexCode) && a.getCinemaCode().equals(cinemaCode)) {
                return a.getLayout();
            }
        }
        return null;
    }

    /**
     * This method is to get the number of cinemas in a cineplex
     * @param cineplexCode The cineplex code
     * @return the number of cinemas in the cineplex
     */
    public int getNumOfCinema(String cineplexCode) {
        Scanner in;
        String record = null;
        String[] fields;
        int numOfCinema = 0;
        try {
            in = new Scanner(dataFile);
            while (in.hasNextLine()) {
                record=in.nextLine();
                fields=record.split(";");
                if (fields[0].equals(cineplexCode)) {
                    numOfCinema++;
                }
            }
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("No record found!");
        }
        return numOfCinema;
    }

}
