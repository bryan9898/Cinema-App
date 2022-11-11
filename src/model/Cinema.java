package model;

import data.AccountDAO;
import data.CinemaDAO;
import data.impl.CinemaDaoImpl;

import java.util.Arrays;


/**
 * Represents a Cinema registered in the system.
 * @version 1.0
 * @since 06 Nov 2022
 */
public class Cinema {

	/**
	 * Makes a new Cinema with cineplex code, cinema code and cinema layout.
	 * @param cineplexCode This is the cineplex code of the cinema.
	 * @param cinemaCode This is the cinema code of the cinema.
	 *The cineplex code + Cinema code is unique for each cinema.
	 * @param layout is the layout of the cinema.
	 */

	/**
	 * The cineplex code of the cinema.
	 */
	private String cineplexCode;

	/**
	 * The cinema code of the cinema.
	 */
	private String cinemaCode;

	/**
	 * The layout of the cinema.
	 */
	private String layout;

	public Cinema(){

	}

	/**
	 * creates a new cinema Object with the given cineplex code, cinema code.
	 * @param cineplexCode This is the cineplex code of the cinema.
	 * @param cinemaCode This is the cinema code of the cinema.
	 */
	public Cinema(int cineplexCode, String cinemaCode) {
		super();
		switch (cineplexCode) {
			case 1: this.cineplexCode = "NTU"; break;
			case 2: this.cineplexCode = "SEK"; break;
			case 3: this.cineplexCode = "BIS"; break;
		}
		this.cinemaCode = cinemaCode;
		CinemaDAO cinemaDAO = new CinemaDaoImpl();
		this.layout  = cinemaDAO.getLayout(this.cineplexCode, this.cinemaCode);
	}


	/**
	 * creates a new cinema Object with the given cineplex code, cinema code.
	 * @param cineplexCode This is the cineplex code of the cinema.
	 * @param cinemaCode This is the cinema code of the cinema.
	 * @param layout This is the layout of the cinema.
	 */
	public Cinema(String cineplexCode, String cinemaCode,String layout) {
		super();
		this.cineplexCode = cineplexCode;
		this.cinemaCode = cinemaCode;
		this.layout = layout;
	}

	/**
	 * Prints Layout type 2 of the cinema.
	 */
	public void printType2() {
		String[][] type2Full = getLayoutIn2DArray("2");
		System.out.printf("-----------------------------------------------      %n");
		System.out.printf("                     Screen                          %n");
		System.out.printf("-----------------------------------------------      %n");
		CinemaTable st = new CinemaTable();
		st.setHeaders(type2Full[0]);
		for(int i = 1;i<type2Full.length;i++) {
			st.addRow(type2Full[i]);
		}
		st.print();
		System.out.printf("-----------------------------------------------      %n");
		System.out.printf("                    Entrance                         %n");
		System.out.printf("-----------------------------------------------      %n");
	}

	/**
	 * Prints Layout type 3 of the cinema.
	 */
	public void printType3() {
		String[][] type3Full = getLayoutIn2DArray("3");
		System.out.printf("Platinum Class%n");
		System.out.printf("---------------------------     %n");
		System.out.printf("           Screen               %n");
		System.out.printf("---------------------------     %n");
		CinemaTable st = new CinemaTable();
		st.setHeaders(type3Full[0]);
		for(int i = 1;i<type3Full.length;i++) {
			st.addRow(type3Full[i]);
		}
		st.print();
		System.out.printf("---------------------------     %n");
		System.out.printf("          Entrance              %n");
		System.out.printf("---------------------------     %n");
	}

	/**
	 * Prints Layout type 1 of the cinema.
	 */
	public void printType1() {
		String[][] type1Full = getLayoutIn2DArray("1");
		System.out.printf("     -----------------------------------------------      %n");
		System.out.printf("                          Screen                          %n");
		System.out.printf("     -----------------------------------------------      %n");
		CinemaTable st = new CinemaTable();
		st.setHeaders(type1Full[0]);
		for(int i = 1;i<type1Full.length;i++) {
			st.addRow(type1Full[i]);
		}
		st.print();
		System.out.printf("     -----------------------------------------------      %n");
		System.out.printf("                         Entrance                         %n");
		System.out.printf("     -----------------------------------------------      %n");
	}

	/**
	 * gets the cineplex code of the cinema.
	 * @return cineplex code of the cinema.
	 */
	public String getCineplexCode() {
		return cineplexCode;
	}

	/**
	 * sets the cineplex code of the cinema.
	 * @param cineplexCode This is the cineplex code of the cinema to set to.
	 */
	public void setCineplexCode(String cineplexCode) {
		this.cineplexCode = cineplexCode;
	}

	/**
	 * gets the cinema code of the cinema.
	 * @return cinema code of the cinema.
	 */
	public String getCinemaCode() {
		return cinemaCode;
	}

	/**
	 * sets the cinema code of the cinema.
	 * @param cinemaCode This is the cinema code of the cinema to set to.
	 */
	public void setCinemaCode(String cinemaCode) {
		this.cinemaCode = cinemaCode;
	}

	/**
	 * gets the layout of the cinema.
	 * @return layout of the cinema.
	 */
	public  String getLayout() {
		return layout;
	}

	/**
	 * sets the layout of the cinema.
	 * @param layout of the cinema.
	 */
	public void setLayout(String layout) {
		this.layout = layout;
	}

	/**
	 * Prints the layout of the cinema.
	 */
	public void printCinemaLayout(){
        //st.setRightAlign(true);
        //st.setShowVerticalLines(true);

    	if(this.layout.equals("1")) {

    		printType1();
		}
		else if(this.layout.equals("2")) {
			printType2();
		}
		else if(this.layout.equals("3")) {
			printType3();
		}

	}

	/**
	 * gets the number of cinemas the cineplex has.
	 * @param cineplex This is the cineplex code of the cinema.
	 * @return number of cinemas the cineplex has.
	 */
	public int getNumOfCinema(int cineplex){
		CinemaDAO cinemaDAO = new CinemaDaoImpl();
		switch (cineplex) {
			case 1: return cinemaDAO.getNumOfCinema("NTU");
			case 2: return cinemaDAO.getNumOfCinema("SEK");
			case 3: return cinemaDAO.getNumOfCinema("BIS");
		}
		return 0;
	}


	/**
	 * gets the layout of the cinema in 2D array.
	 * @param cineplex This is the cineplex code of the cinema.
	 * @param cinemaNum This is the cinema code of the cinema.
	 * @return layout of the cinema in 2D array.
	 */
	public String[][] getCineLayout(int cineplex, String cinemaNum) {

		CinemaDAO cinemaDAO = new CinemaDaoImpl();
		String layout = null;
		if(cineplex == 1) {
			layout = cinemaDAO.getLayout("NTU", cinemaNum);}
		else if(cineplex == 2) {
			layout = cinemaDAO.getLayout("SEK", cinemaNum);}
		else if(cineplex == 3) {
			layout = cinemaDAO.getLayout("BIS", cinemaNum);}
		if(layout.equals("1")) {
			return getLayoutIn2DArray("1");
		}else if(layout.equals("2")) {
			return getLayoutIn2DArray("2");
		}else if(layout.equals("3")) {
			return getLayoutIn2DArray("3");
		}
		return null;
	}

	/**
	 * gets the layout of the cinema in 2D array.
	 * @param cineplex is the cineplex code
	 * @param cinemaNum is the cinema code
	 * @return layout of the cinema in 2D array.
	 */
	public String[][] getCineLayout(String cineplex, String cinemaNum) {


		CinemaDAO cinemaDAO = new CinemaDaoImpl();
		String layout = null;
		layout = cinemaDAO.getLayout(cineplex, cinemaNum);
		if(layout.equals("1")) {
			return getLayoutIn2DArray("1");
		}else if(layout.equals("2")) {
			return getLayoutIn2DArray("2");
		}else if(layout.equals("3")) {
			return getLayoutIn2DArray("3");
		}
		return null;
	}

	/**
	 * Get the type of layout of the cinema.
	 * @param cineplex This is the cineplex code of the cinema.
	 * @param cinemaNum This is the cinema code of the cinema.
	 * @return layout of the cinema in 2D array.
	 */
	public int getType(String cineplex, String cinemaNum) {
		CinemaDAO cinemaDAO = new CinemaDaoImpl();
		String layout = null;
		layout = cinemaDAO.getLayout(cineplex, cinemaNum);
		if(layout.equals("1")) {
			return 1;
		}else if(layout.equals("2")) {
			return 2;
		}else if(layout.equals("3")) {
			return 3;
		}
		return 0;
	}

	/**
	 * gets the layout of the cinema in 2D array.
	 * @param layout This is the type of the cinema.
	 * @return layout of the cinema in 2D array.
	 */
	public String[][] getLayoutIn2DArray(String layout) {
		String[][] type1 = {
				{"/","A","B","C","D","E","F","G","H","/","J","K","L","M","N","O","P","Q","/"},
				{"1","/","/","O","O","O","O","O","O","/","O","O","O","O","O","O","O","O","/"},
				{"2","/","/","O","O","O","O","O","O","/","O","O","O","O","O","O","O","O","/"},
				{"3","/","/","O","O","O","O","O","O","/","O","O","O","O","O","O","O","O","/"},
				{"4","/","/","O","O","O","O","O","O","/","O","O","O","O","O","O","O","O","/"},
				{"5","O","O","O","O","O","O","O","O","/","O","O","O","O","O","O","O","O","/"},
				{"6","O","O","O","O","O","O","O","O","/","O","O","O","O","O","O","O","O","/"},
				{"7","[","]","[","]","[","]","[","]","/","[","]","[","]","[","]","[","]","/"},
				{"8","[","]","[","]","[","]","[","]","/","[","]","[","]","[","]","[","]","/"},
				{"9","[","]","[","]","[","]","[","]","/","[","]","[","]","[","]","[","]","/"}
		};

		String[][] type2 = {
				{"/","A","B","C","D","E","F","G","H","/","J","K","L","M","/"},
				{"1","/","/","O","O","O","O","O","O","/","O","O","O","O","/"},
				{"2","/","/","O","O","O","O","O","O","/","O","O","O","O","/"},
				{"3","/","/","O","O","O","O","O","O","/","O","O","O","O","/"},
				{"4","/","/","O","O","O","O","O","O","/","O","O","O","O","/"},
				{"5","O","O","O","O","O","O","O","O","/","O","O","O","O","/"},
				{"6","O","O","O","O","O","O","O","O","/","O","O","O","O","/"},
				{"7","[","]","[","]","[","]","[","]","/","[","]","[","]","/"},
				{"8","[","]","[","]","[","]","[","]","/","[","]","[","]","/"},
		};

		//platinum class
		String[][] type3 = {
				{"/","A","B","/","D","E","/","G","H"},
				{"1","O","O","/","O","O","/","O","O"},
				{"2","O","O","/","O","O","/","O","O"},
				{"3","O","O","/","O","O","/","O","O"},
				{"4","O","O","/","O","O","/","O","O"},
				{"5","O","O","/","O","O","/","O","O"},
		};

		if(layout.equals("1")) {
			return type1;
		}else if(layout.equals("2")) {
			return type2;
		}else if(layout.equals("3")) {
			return type3;
		}
		return null;
	}

}
