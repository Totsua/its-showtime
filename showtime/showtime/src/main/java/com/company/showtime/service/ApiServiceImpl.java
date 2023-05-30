package com.company.showtime.service;

import com.company.showtime.dao.filmwrapper.FilmWrapper;
import com.company.showtime.model.Film;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ApiServiceImpl implements ApiServiceInterface {

    @Autowired
    FilmWrapper filmWrapper ;

    // Method to get a list of all the films currently being shown
    @Override
    public List<Film> filmsNowShowing(String jsonResponseBody) {
        // Call the filmWrapper to unmarshall the response body for the desired info
        List<Film> allFilmsNowShowing = filmWrapper.filmWrapper(jsonResponseBody);
        // Return the list
        return allFilmsNowShowing;

    }
}
