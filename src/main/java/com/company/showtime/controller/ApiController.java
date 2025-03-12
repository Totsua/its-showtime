package com.company.showtime.controller;

import com.company.showtime.entities.Cinema;
import com.company.showtime.entities.Film;
import com.company.showtime.exceptions.CustomException;
import com.company.showtime.service.ApiService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ApiController {

    // Create instance of the ApiService
    @Autowired
    ApiService apiService;


    /**
     * The method that directs the user to the "cinemas" page with the information it gets from the
     * API. Here we give the API the method "nearbyCinemas", it returns a String array with the response
     * body [0] and status code [1].
     *
     * Everytime the page is entered, the API is called, be cautious of this.
     *
     * @param model - used to send data to the frontend
     * @return to the "cinemas" page.
     */
    @GetMapping("cinemas")
    public String getCinemasNearby(Model model){
        // Contact api to get a String JSON response body
        String[] responseBody = apiService.ApiCaller("cinemasNearby",0);
        // Call the service layer to deal with the response body
        List<Cinema> nearbyCinemas = apiService.nearbyCinemas(responseBody);
        // Add the List to the Model
        model.addAttribute("cinemas", nearbyCinemas);
        // Return to the html page "cinemas"
        return "cinemas";
    }

    /**
     * The method that directs the user to the "filmsNowShowing" page with the information it gets from the
     * API.
     * Here we give the API the method "filmsNowShowing", it returns a String array with the response
     * body [0] and status code [1].
     *
     * Everytime the page is entered, the API is called, be cautious of this.
     *
     * @param model - used to send data to the frontend.
     * @return to the "filmsNowShowing" page.
     */
    @GetMapping("filmsNowShowing")
    public String getFilmsNowShowing(Model model) throws CustomException {
        // Contact the api to get a String response body
        String[] responseBody = apiService.ApiCaller("filmsNowShowing",0);
        // Call the service layer to deal with the response body
        List<Film> currentShownFilms = apiService.filmsNowShowing(responseBody);
        // Add the List to the Model
        model.addAttribute("filmsNowShowing",currentShownFilms);
        // Return to the html page "filmsNowShowing"
        return "filmsNowShowing";
    }


    /**
     * The method that directs the user to the "cinemaShowTimes" page with the information it gets from the
     * API.
     * Here we give the API the method "cinemaShowTimes" with the chosen cinema Id from the frontend,
     * it returns a String array with the response body [0] and status code [1].
     *
     * Everytime the page is entered, the API is called, be cautious of this.
     *
     * @param request - taking in the "cinemaId" from the frontend.
     * @param model - used to send data to the frontend.
     * @return to the "cinemaShowTimes" page.
     */
    @GetMapping("/cinemaShowTimes")
    public String cinemaShowTimes(HttpServletRequest request, Model model) throws CustomException {
        // Take in the parameter from the request
        int cinemaId = Integer.parseInt(request.getParameter("cinemaId"));
        String cinemaName = String.valueOf(request.getParameter("cinemaName"));
        System.out.println(cinemaName);
        // Contact api to get a String JSON response body
        String[] responseBody = apiService.ApiCaller("cinemaShowTimes", cinemaId);
        // Call the service layer to deal with the response body
        List<Film> cinemaShowTimes = apiService.cinemaShowTimes(responseBody);
        // Add the List to the Model
        model.addAttribute("cinemaShowTimes", cinemaShowTimes);
        // Return to the cinemaShowTimes page
        return "cinemaShowTimes";
    }

    /**
     * The method that directs the user to the "closestShowing" page with the information it gets from the
     * API.
     * Here we give the API the method "closestShowing" with the chosen film Id from the frontend,
     * it returns a String array with the response body [0] and status code [1].
     *
     * Everytime the page is entered, the API is called, be cautious of this.
     *
     * @param request - taking in the "filmId" from the frontend.
     * @param model - used to send data to the frontend.
     * @return to the "closestShowing" page.
     */
    @GetMapping("/closestShowing")
    public String closestShowing(HttpServletRequest request, Model model){
        // Take in the parameter from the request
        int filmId = Integer.parseInt(request.getParameter("filmId"));
        // Contact api to get a String JSON response body
        String[] responseBody = apiService.ApiCaller("closestShowing", filmId);
        // Call the service layer to deal with the response body
        List<Cinema> closestShowing = apiService.closestShowing(responseBody);
        // Add the List to the Model
        model.addAttribute("closestShowing", closestShowing);
        // Return to the "closestShowing" page
        return "closestShowing";
    }


}
