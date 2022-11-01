package data;


import model.Cinema;

import java.util.ArrayList;

public interface CinemaDAO {

    public String getLayout(String cineplexCode, String cinemaCode);
    public int getNumOfCinema(String cineplexCode);

}
