package com.company.showtime.service;

import com.company.showtime.entities.Cinema;
import com.company.showtime.entities.Film;
import com.company.showtime.exceptions.CustomException;

import java.util.List;

public interface ApiService {
    List<Film> filmsNowShowing() throws CustomException;
    List<Cinema> nearbyCinemas();
    List<Film> cinemaShowTimes(String cinemaId) throws CustomException;
    List<Cinema> closestShowing(String filmId) throws CustomException;
    String[] ApiCaller(String method, int id);
}
