package com.company.showtime.service;

import com.company.showtime.model.Cinema;
import com.company.showtime.model.Film;

import java.util.List;

public interface ApiServiceInterface {
    List<Film> filmsNowShowing(String jsonResponseBody);
    List<Cinema> nearbyCinemas(String jsonResponseBody);
    List<Film> cinemaShowTimes(String jsonResponseBody);
    List<Cinema> closestShowing(String jsonResponseBody);
}
