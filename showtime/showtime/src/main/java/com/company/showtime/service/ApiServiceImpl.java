package com.company.showtime.service;

import com.company.showtime.dao.ApiDAOImpl;
import com.company.showtime.model.Cinema;
import com.company.showtime.model.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiServiceImpl implements ApiServiceInterface {

    @Autowired
    ApiDAOImpl apiDAO;


    // Method to get a list of all the films currently being shown
    @Override
    public List<Film> filmsNowShowing(String jsonResponseBody) {
        // Call the DAO to unmarshall the response body for the desired info
        List<Film> allFilmsNowShowing = apiDAO.getFilmList(jsonResponseBody, "filmsNowShowing");
        // Return the list
        return allFilmsNowShowing;
    }

    @Override
    public List<Cinema> nearbyCinemas(String jsonResponseBody) {
        // Call the DAO to unmarshall the response body for the desired info
        List<Cinema> nearbyCinemas = apiDAO.getNearbyCinemas(jsonResponseBody, "cinemasNearby");
        // Return the list
        return nearbyCinemas;
    }

    @Override
    public List<Film> cinemaShowTimes(String jsonResponseBody){
        // Call the DAO to unmarhsall the response body for the desired info
        List<Film> cinemaShowTimes = apiDAO.getFilmList(jsonResponseBody, "cinemaShowTimes");
        // Return the list
        return cinemaShowTimes;
    }

    @Override
    public List<Cinema> closestShowing(String jsonResponseBody) {
        // Call the DAO to unmarhsall the response body for the desired info
        List<Cinema> closestShowing = apiDAO.getNearbyCinemas(jsonResponseBody, "closetShowing");
        // Return the list
        return closestShowing;
    }
}
