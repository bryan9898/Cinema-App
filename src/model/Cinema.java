package model;

import data.AccountDAO;
import data.CinemaDAO;
import data.impl.CinemaDaoImpl;

public class Cinema {
	private String cineplexCode;
	private String cinemaCode;
	private String layout;

	private static String[][] type1 = {
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
	
	private static String[][] type2 = {
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
	private static String[][] type3 = {
			{"/","A","B","/","D","E","/","G","H"},
			{"1","O","O","/","O","O","/","O","O"},
			{"2","O","O","/","O","O","/","O","O"},
			{"3","O","O","/","O","O","/","O","O"},
			{"4","O","O","/","O","O","/","O","O"},
			{"5","O","O","/","O","O","/","O","O"},
	};

	public Cinema(){

	}

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
	
	public Cinema(String cineplexCode, String cinemaCode,String layout) {
		super();
		this.cineplexCode = cineplexCode;
		this.cinemaCode = cinemaCode;
		this.layout = layout;
	}

	public void printType2() {
		String[][] type2Full = {
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
	public void printType3() {
		 String[][] type3Full = {
				{"/","A","B","/","D","E","/","G","H"},
				{"1","O","O","/","O","O","/","O","O"},
				{"2","O","O","/","O","O","/","O","O"},
				{"3","O","O","/","O","O","/","O","O"},
				{"4","O","O","/","O","O","/","O","O"},
				{"5","O","O","/","O","O","/","O","O"},
		};
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

	public void printType1() {
		String[][] type1Full = {
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
	
	public String getCineplexCode() {
		return cineplexCode;
	}

	public void setCineplexCode(String cineplexCode) {
		this.cineplexCode = cineplexCode;
	}

	public String getCinemaCode() {
		return cinemaCode;
	}

	public void setCinemaCode(String cinemaCode) {
		this.cinemaCode = cinemaCode;
	}

	public  String getLayout() {
		return layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}

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

	public int getNumOfCinema(int cineplex){
		CinemaDAO cinemaDAO = new CinemaDaoImpl();
		switch (cineplex) {
			case 1: return cinemaDAO.getNumOfCinema("NTU");
			case 2: return cinemaDAO.getNumOfCinema("SEK");
			case 3: return cinemaDAO.getNumOfCinema("BIS");
		}
		return 0;
	}


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
			return type1;
		}else if(layout.equals("2")) {
			return type2;
		}else if(layout.equals("3")) {
			return type3;
		}
		return null;
	}

	public String[][] getCineLayout(String cineplex, String cinemaNum) {
		CinemaDAO cinemaDAO = new CinemaDaoImpl();
		String layout = null;
		layout = cinemaDAO.getLayout(cineplex, cinemaNum);
		if(layout.equals("1")) {
			return type1;
		}else if(layout.equals("2")) {
			return type2;
		}else if(layout.equals("3")) {
			return type3;
		}
		return null;
	}

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
}
