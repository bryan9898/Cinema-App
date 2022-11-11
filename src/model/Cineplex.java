package model;

/**
 * This represents a cineplex in the cinema booking system.
 * The cineplex has a cineplex code and a list of cinemas.
 * @version 1.0
 * @since 06 Nov 2022
 */

public class Cineplex {

	/**
	 * The cineplex code of the cineplex.
	 */
	private String cineplexCode;
	/**
	 * The Cineplex name of the cineplex.
	 */
	private String cineName;
	/**
	 * The number of cinemas in the cineplex.
	 */
	private int numOfCinemas;

	/**
	 * The constructor to make a cineplex object
	 */
	public Cineplex(){
	}
	
	/**
	 * This method is to get the cineplex code of the cineplex.
	 * @return cineplexCode
	 */
	public String getCineplexCode() {
		return cineplexCode;
	}

	/**
	 * This method is to set the cineplex code of the cineplex.
	 * @param cineplexCode The cineplex code to set
	 */
	public void setCineplexCode(String cineplexCode) {
		this.cineplexCode = cineplexCode;
	}

	/**
	 * This method is to get the cineplex name of the cineplex.
	 * @return cineName
	 */
	public String getCineName() {
		return cineName;
	}

	/**
	 * This method is to set the cineplex name of the cineplex.
	 * @param cineName the cineName to set
	 */
	public void setCineName(String cineName) {
		this.cineName = cineName;
	}

	/**
	 * This method is to get the number of cinemas in the cineplex.
	 * @return numOfCinemas
	 */
	public int getNumOfCinemas() {
		return numOfCinemas;
	}

	/**
	 * This method is to set the number of cinemas in the cineplex.
	 * @param numOfCinemas the numOfCinemas to set
	 */
	public void setNumOfCinemas(int numOfCinemas) {
		this.numOfCinemas = numOfCinemas;
	}
	
	
}
