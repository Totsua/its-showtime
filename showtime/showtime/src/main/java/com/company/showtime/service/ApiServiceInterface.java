package com.company.showtime.service;

import com.company.showtime.model.Film;

import java.util.List;

public interface ApiServiceInterface {
    List<Film> filmsNowShowing(String jsonResponseBody);

}
