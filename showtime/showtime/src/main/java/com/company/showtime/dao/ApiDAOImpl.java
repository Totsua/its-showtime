package com.company.showtime.dao;

import com.company.showtime.dao.wrappers.CinemaWrapper;
import com.company.showtime.dao.wrappers.FilmWrapper;
import com.company.showtime.model.Cinema;
import com.company.showtime.model.Film;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ApiDAOImpl implements ApiDAO{
    @Override
    public List<Cinema> getCinemaList(String JSONResponse, String method) {
        // Call the cinemaWrapper to unmarhsall the info
        List<Cinema> cinemas = new ArrayList<>();
        if(method.equalsIgnoreCase("cinemasNearby")) {
            cinemas = CinemaWrapper.cinemaWrapper(JSONResponse,"cinemasNearby");
        }
        else if(method.equalsIgnoreCase("closestShowing")) {
            cinemas = CinemaWrapper.cinemaWrapper(JSONResponse,"closestShowing");
        }
        // Return the list
        return cinemas;
    }

    @Override
    public List<Film> getFilmList(String jsonResponse, String method) {
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
