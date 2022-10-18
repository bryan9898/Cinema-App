package model;

import data.AccountDAO;
import data.CinemaDAO;

public class Cinema {
	private String cineplexCode;
	private String cinemaCode;
	private String layout;

	private static String[][] type1 = {
			{" ","A","B","C","D","E","F","G","H"," ","I","J","K","L","M","N","O","P"," "},
			{"1"," "," ","O","O","O","O","O","O"," ","O","O","O","O","O","O","O","O"," "},
			{"2"," "," ","O","O","O","O","O","O"," ","O","O","O","O","O","O","O","O"," "},
			{"3"," "," ","O","O","O","O","O","O"," ","O","O","O","O","O","O","O","O"," "},
			{"4"," "," ","O","O","O","O","O","O"," ","O","O","O","O","O","O","O","O"," "},
			{"5","O","O","O","O","O","O","O","O"," ","O","O","O","O","O","O","O","O"," "},
			{"6","O","O","O","O","O","O","O","O"," ","O","O","O","O","O","O","O","O"," "},
			{"7","[","]","[","]","[","]","[","]"," ","[","]","[","]","[","]","[","]"," "},
			{"8","[","]","[","]","[","]","[","]"," ","[","]","[","]","[","]","[","]"," "},
			{"9","[","]","[","]","[","]","[","]"," ","[","]","[","]","[","]","[","]"," "}
	};
	
	private static String[][] type2 = {
			{" ","A","B","C","D","E","F","G","H"," ","I","J","K","L"," "},
			{"1"," "," ","O","O","O","O","O","O"," ","O","O","O","O"," "},
			{"2"," "," ","O","O","O","O","O","O"," ","O","O","O","O"," "},
			{"3"," "," ","O","O","O","O","O","O"," ","O","O","O","O"," "},
			{"4"," "," ","O","O","O","O","O","O"," ","O","O","O","O"," "},
			{"5","O","O","O","O","O","O","O","O"," ","O","O","O","O"," "},
			{"6","O","O","O","O","O","O","O","O"," ","O","O","O","O"," "},
			{"7","[","]","[","]","[","]","[","]"," ","[","]","[","]"," "},
			{"8","[","]","[","]","[","]","[","]"," ","[","]","[","]"," "},
	};
	
	//platinum class
	private static String[][] type3 = {
			{" ","A","B"," ","C","D"," ","E","F"},
			{"1","O","O"," ","O","O"," ","O","O"},
			{"2","O","O"," ","O","O"," ","O","O"},
			{"3","O","O"," ","O","O"," ","O","O"},
			{"4","O","O"," ","O","O"," ","O","O"},
			{"5","O","O"," ","O","O"," ","O","O"},
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
		CinemaDAO cinemaDAO = new CinemaDAO();
		this.layout  = cinemaDAO.getLayout(this.cineplexCode, this.cinemaCode);
	}
	
	public Cinema(String cineplexCode, String cinemaCode,String layout) {
		super();
		this.cineplexCode = cineplexCode;
		this.cinemaCode = cinemaCode;
		this.layout = layout;
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

    		System.out.printf("     -----------------------------------------------      %n");
    		System.out.printf("                          Screen                          %n");
    		System.out.printf("     -----------------------------------------------      %n");
    		CinemaTable st = new CinemaTable();
    		st.setHeaders(type1[0]);
    		for(int i = 1;i<type1.length;i++) {
    			st.addRow(type1[i]);
    		}
            st.print();
    		System.out.printf("     -----------------------------------------------      %n");
    		System.out.printf("                         Entrance                         %n");
    		System.out.printf("     -----------------------------------------------      %n");
    	}else if(this.layout.equals("2")) {

    		System.out.printf("-----------------------------------------------      %n");
    		System.out.printf("                     Screen                          %n");
    		System.out.printf("-----------------------------------------------      %n");
    		CinemaTable st = new CinemaTable();
    		st.setHeaders(type2[0]);
    		for(int i = 1;i<type2.length;i++) {
    			st.addRow(type2[i]);
    		}
    		st.print();
    		System.out.printf("-----------------------------------------------      %n");
    		System.out.printf("                    Entrance                         %n");
    		System.out.printf("-----------------------------------------------      %n");
    	}else if(this.layout.equals("3")) {

    		System.out.printf("Platinum Class%n");
    		System.out.printf("---------------------------     %n");
    		System.out.printf("           Screen               %n");
    		System.out.printf("---------------------------     %n");
    		CinemaTable st = new CinemaTable();
    		st.setHeaders(type3[0]);
    		for(int i = 1;i<type3.length;i++) {
    			st.addRow(type3[i]);
    		}
    		st.print();
    		System.out.printf("---------------------------     %n");
    		System.out.printf("          Entrance              %n");
    		System.out.printf("---------------------------     %n");
    	}
	}

	public int getNumOfCinema(int cineplex){
		CinemaDAO cinemaDAO = new CinemaDAO();
		switch (cineplex) {
			case 1: return cinemaDAO.getNumOfCinema("NTU");
			case 2: return cinemaDAO.getNumOfCinema("SEK");
			case 3: return cinemaDAO.getNumOfCinema("BIS");
		}
		return 0;
	}
	
	
	
}
