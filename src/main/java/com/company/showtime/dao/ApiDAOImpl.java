package com.company.showtime.dao;

import com.company.showtime.service.wrappers.CinemaWrapper;
import com.company.showtime.service.wrappers.FilmWrapper;
import com.company.showtime.entities.Cinema;
import com.company.showtime.entities.Film;
import com.company.showtime.exceptions.CustomException;
import org.springframework.stereotype.Repository;

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
        // Instantiate a List object
        List<Cinema> cinemas;
        // Call the cinemaWrapper to unmarshall the info
        cinemas = CinemaWrapper.cinemaWrapper(jsonResponse,method);
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
        List<Film> allFilms;
        // Call the filmWrapper to unmarshall the info depending on what method is used
        allFilms = FilmWrapper.filmWrapper(jsonResponse,method);
        return allFilms;
    }




}
