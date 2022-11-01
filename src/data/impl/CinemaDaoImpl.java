package data.impl;

import data.CinemaDAO;
import model.Cinema;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class CinemaDaoImpl implements CinemaDAO {
    private static final String Account_File="Cinema.txt";
    private static File dataFile;

    public CinemaDaoImpl() {
        Path dPath = FileSystems.getDefault().getPath("Resources/Data/",Account_File);
        dataFile = new File(dPath.toString());
    }

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

    public String getLayout(String cineplexCode, String cinemaCode) {
        ArrayList<Cinema> cinemas = getLayouts();

        for (Cinema a:cinemas) {
            if (a.getCineplexCode().equals(cineplexCode) && a.getCinemaCode().equals(cinemaCode)) {
                return a.getLayout();
            }
        }
        return null;
    }

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
