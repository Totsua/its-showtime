package com.company.showtime.service;

import com.company.showtime.dao.ApiDAO;
import com.company.showtime.model.Cinema;
import com.company.showtime.model.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApiServiceImpl implements ApiService {

    // Create instance of the ApiDAO
    @Autowired
    ApiDAO apiDAO;
    public ApiServiceImpl(ApiDAO apiDAO) {this.apiDAO = apiDAO;}

    // Method to get a list of all the films currently being shown
    @Override
    public List<Film> filmsNowShowing(String jsonResponseBody) {
        // Call the DAO to unmarshall the response body for the desired info
        List<Film> allFilmsNowShowing = apiDAO.getFilmList(jsonResponseBody, "filmsNowShowing");
        // Return the list
        return allFilmsNowShowing;
    }

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
        List<Cinema> closestShowing = apiDAO.getCinemaList(jsonResponseBody, "closestShowing");
        // Return the list
        return closestShowing;
    }
}
