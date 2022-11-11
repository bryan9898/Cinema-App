package data;

/**
 * This interface is used to define the methods that are used to access the Cinema Data
 * @version 1.0
 * @since 06 Nov 2022
 */
public interface CinemaDAO {

    /**
     * This method is to get the layout of a cinema
     * @param cineplexCode The cineplex code
     * @param cinemaCode The cinema code
     * @return the layout of the cinema
     */
    public String getLayout(String cineplexCode, String cinemaCode);
    /**
     * This method is to get the number of cinemas in a cineplex
     * @param cineplexCode The cineplex code
     * @return the number of cinemas in the cineplex
     */
    public int getNumOfCinema(String cineplexCode);

}
