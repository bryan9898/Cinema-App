package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Cinema;
import model.CinemaTable;

public class CinemaDAO {
	
	private static final String Account_File="Cinema.txt";
	private static File dataFile;
	
	public CinemaDAO() {
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
	
}
