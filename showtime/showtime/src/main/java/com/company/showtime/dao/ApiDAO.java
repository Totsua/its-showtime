package com.company.showtime.dao;

import com.company.showtime.model.Cinema;
import com.company.showtime.model.Film;

import java.util.List;

public interface ApiDAO {
    List<Cinema> getNearbyCinemas(String jsonResponse, String method);
    List<Film> getFilmList(String jsonResponse, String method);
}
