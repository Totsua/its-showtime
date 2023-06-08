package com.company.showtime.dao;

import com.company.showtime.dao.wrappers.CinemaWrapper;
import com.company.showtime.dao.wrappers.FilmWrapper;
import com.company.showtime.entities.Cinema;
import com.company.showtime.entities.Film;
import com.company.showtime.exceptions.CustomException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class ApiDAOImpl implements ApiDAO{
    /**
     * The method to get a List of Cinemas based on what API method is used.
     * The CinemaWrapper is contacted with the parameters given.
     * @param jsonResponse -  the JSON response body obtained from the API.
     * @param method - the method called to obtain the API data.
     * @return a List of Cinema Objects
     */
    @Override
    public List<Cinema> getCinemaList(String jsonResponse, String method) {
        // Call the cinemaWrapper to unmarhsall the info
        List<Cinema> cinemas = new ArrayList<>();
        if(method.equalsIgnoreCase("cinemasNearby")) {
            cinemas = CinemaWrapper.cinemaWrapper(jsonResponse,"cinemasNearby");
        }
        else if(method.equalsIgnoreCase("closestShowing")) {
            cinemas = CinemaWrapper.cinemaWrapper(jsonResponse,"closestShowing");
        }
        // Return the list
        return cinemas;
    }
    /**
     * The method to get a List of Films based on what API method is used.
     * The FilmWrapper is contacted with the parameters given.
     * @param jsonResponse -  the JSON response body obtained from the API.
     * @param method - the method called to obtain the API data.
     * @return a List of Film Objects
     */
    @Override
    public List<Film> getFilmList(String jsonResponse, String method) throws CustomException {
        // Instantiate a List object
        List<Film> allFilms = new ArrayList<>();

        // Call the filmWrapper to unmarhshall the info depending on what method is used
        if(method.equalsIgnoreCase("filmsNowShowing")) {
            allFilms = FilmWrapper.filmWrapper(jsonResponse, "filmsNowShowing");
        }
        else if(method.equalsIgnoreCase("cinemaShowTimes")){
            allFilms = FilmWrapper.filmWrapper(jsonResponse,"cinemaShowTimes");
        }

        return allFilms;
    }




}
