package com.company.showtime.service;

import com.company.showtime.entities.Cinema;
import com.company.showtime.entities.Film;
import com.company.showtime.exceptions.CustomException;

import java.util.List;

public interface ApiService {
    List<Film> filmsNowShowing(String[] jsonResponseBody) throws CustomException;
    List<Cinema> nearbyCinemas(String[] jsonResponseBody);
    List<Film> cinemaShowTimes(String[] jsonResponseBody) throws CustomException;
    List<Cinema> closestShowing(String[] jsonResponseBody);
    String[] ApiCaller(String method, int id);
}
