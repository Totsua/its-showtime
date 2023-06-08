package com.company.showtime.service;

import com.company.showtime.dao.ApiDAO;
import com.company.showtime.entities.Cinema;
import com.company.showtime.entities.Film;
import com.company.showtime.exceptions.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * At this moment in time the Service Layer doesn't contain Business Logic
 *              -- THIS WILL BE AVAILABLE NEXT PATCH --
 */
@Service
public class ApiServiceImpl implements ApiService {

    // Create instance of the ApiDAO
    @Autowired
    ApiDAO apiDAO;
    public ApiServiceImpl(ApiDAO apiDAO) {this.apiDAO = apiDAO;}

    /**
     * Method to call the DAO to parse JSON response body and return a List of Film Objects depending on the
     * called method.
     * @param jsonResponseBody - the JSON response body and server code
     * @return the List of Film Objects
     */
    @Override
    public List<Film> filmsNowShowing(String jsonResponseBody) throws CustomException {
        // Call the DAO to unmarshall the response body for the desired info
        List<Film> allFilmsNowShowing = apiDAO.getFilmList(jsonResponseBody, "filmsNowShowing");
        // Return the list
        return allFilmsNowShowing;
    }

    /**
     * Method to call the DAO to parse JSON response body and return a List of Cinema Objects depending on the
     * called method.
     * TESTING: If the server code isn't 200 from the api- it'll return the List saying
     * that there are none.
     * @param jsonResponseBody - the JSON response body and server code
     * @return the List of Cinema Objects
     */
    @Override
    public List<Cinema> nearbyCinemas(String[] jsonResponseBody) {
        List<Cinema> nearbyCinemas = new ArrayList<>();
        // Call the DAO to unmarshall the response body for the desired info
        String responseBody = jsonResponseBody[0];
        String statusCode = jsonResponseBody[1];
        if (statusCode.equals("200")){
            nearbyCinemas = apiDAO.getCinemaList(responseBody, "cinemasNearby");
        }
        else{
            Cinema nothing = new Cinema(statusCode+ ": Looks like there are none.");
            nearbyCinemas.add(nothing);
        }
        // Return the list
        return nearbyCinemas;
    }

    /**
     * Method to call the DAO to parse JSON response body and return a List of Film Objects depending on the
     * called method.
     * @param jsonResponseBody - the JSON response body and server code
     * @return the List of Film Objects
     */
    @Override
    public List<Film> cinemaShowTimes(String jsonResponseBody) throws CustomException {
        // Call the DAO to unmarhsall the response body for the desired info
        List<Film> cinemaShowTimes = apiDAO.getFilmList(jsonResponseBody, "cinemaShowTimes");
        // Return the list
        return cinemaShowTimes;
    }

    /**
     * Method to call the DAO to parse JSON response body and return a List of Cinema Objects depending on the
     * called method.
     * @param jsonResponseBody - the JSON response body and server code
     * @return the List of Cinema Objects
     */
    @Override
    public List<Cinema> closestShowing(String jsonResponseBody) {
        // Call the DAO to unmarhsall the response body for the desired info
        List<Cinema> closestShowing = apiDAO.getCinemaList(jsonResponseBody, "closestShowing");
        // Return the list
        return closestShowing;
    }
}
