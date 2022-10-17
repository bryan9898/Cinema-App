package model;

public class Cinema {
	private String cineplexCode;
	private String cinemaCode;
	private static String layout="3";
	
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
	
	
	public Cinema() {
		
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

	public static String getLayout() {
		return layout;
	}

	public static void setLayout(String layout) {
		Cinema.layout = layout;
	}

	public static void printCinemaLayout(){
        //st.setRightAlign(true);
        //st.setShowVerticalLines(true);
    	if(layout == "1") {
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
    	}else if(layout == "2") {
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
    	}else if(layout == "3") {
    		System.out.printf("Platnium Class%n");
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
	
	
	
}
