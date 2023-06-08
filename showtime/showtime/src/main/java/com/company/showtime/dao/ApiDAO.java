package com.company.showtime.dao;

import com.company.showtime.entities.Cinema;
import com.company.showtime.entities.Film;
import com.company.showtime.exceptions.CustomException;

import java.util.List;

public interface ApiDAO {
    List<Cinema> getCinemaList(String jsonResponse, String method);
    List<Film> getFilmList(String jsonResponse, String method) throws CustomException;
}
