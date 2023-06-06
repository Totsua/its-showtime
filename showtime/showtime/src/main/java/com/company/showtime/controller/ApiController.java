package com.company.showtime.controller;

import com.company.showtime.model.Cinema;
import com.company.showtime.model.Film;
import com.company.showtime.service.ApiService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class ApiController {

    // Create instance of the ApiService
    @Autowired
    ApiService apiService;
    public ApiController(ApiService apiService){this.apiService = apiService;}

    @GetMapping("cinemas")
    public String getCinemasNearby(Model model){
        // Contact api to get a String JSON response body
        String[] responseBody = ApiCaller("cinemasNearby",0);
        // Call the service layer to deal with the response body
        List<Cinema> nearbyCinemas = apiService.nearbyCinemas(responseBody);
        // Add the List to the Model
        model.addAttribute("cinemas", nearbyCinemas);
        // Return the Model to the html page "cinemas"
        return "cinemas";
    }
    // Method to get list of films currently showing
    @GetMapping("filmsNowShowing")
    // Method to get all films currently being shown
    public String getFilmsNowShowing(Model model) {
        // Contact the api to get a String response body
        String[] responseBody = ApiCaller("filmsNowShowing",0);
        // Call the service layer to deal with the response body
        List<Film> currentShownFilms = apiService.filmsNowShowing(responseBody[0]);
        // Add the List to the Model
        model.addAttribute("filmsNowShowing",currentShownFilms);
        // Return the Model to the html page "filmsNowShowing"
        return "filmsNowShowing";
    }


    // Method to get list of showtimes for a specific cinema
    @GetMapping("/cinemaShowTimes")
    public String cinemaShowTimes(HttpServletRequest request, Model model){
        // Take in the parameter from the request
        int cinemaId = Integer.parseInt(request.getParameter("cinemaId"));
        // Contact api to get a String JSON response body
        String[] responseBody = ApiCaller("cinemaShowTimes", cinemaId);
        // Call the service layer to deal with the response body
        List<Film> cinemaShowTimes = apiService.cinemaShowTimes(responseBody[0]);
        // Add the List to the Model
        model.addAttribute("cinemaShowTimes", cinemaShowTimes);
        // Return the Model to the cinemaShowTimes page
        return "cinemaShowTimes";
    }

    // Method to get list of closest viewings of a specific film
    @GetMapping("/closestShowing")
    public String closestShowing(HttpServletRequest request, Model model){
        // Take in the parameter from the request
        int filmId = Integer.parseInt(request.getParameter("filmId"));
        // Contact api to get a String JSON response body
        String[] responseBody = ApiCaller("closestShowing", filmId);
        // Call the service layer to deal with the response body
        List<Cinema> closestShowing = apiService.closestShowing(responseBody[0]);
        // Add the List to the Model
        model.addAttribute("closestShowing", closestShowing);
        // Return the Model to the "closestShowing" page
        return "closestShowing";
    }

    // Method to contact api
    public String[] ApiCaller(String method, int id){
        // Instantiate a string for the response body, the api endpoint and todays date
        String[] returnedJsonString = null;
        String apiEndpoint =  "https://api-gate2.movieglu.com/" +method+ "/?n=10";

        // Need to create a datetime in the format yyyy-MM-dd'T'HH:mm:ss.SSS'Z' - for the API
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        String isoDateTime = now.format(formatter);

        // Change the endpoint depending on what method is being used
        if (method.equalsIgnoreCase("cinemaShowTimes")){
            String date = LocalDate.now().toString();
            apiEndpoint = "https://api-gate2.movieglu.com/cinemaShowTimes/?cinema_id=" +id+ "&date=" + date;
        }
         else if(method.equalsIgnoreCase("closestShowing")){
            apiEndpoint = apiEndpoint.concat("&film_id="+id);
        }

         // Instantiate a HTTP Client
        HttpClient client = HttpClient.newBuilder()
                .build();

        // Create the HTTP request using headers of the api and values to connect
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiEndpoint))
                .header("api-version", "v200")
                .header("Authorization", "Basic SFFOVV9YWDpnVlB2YmwyTU5uRWg=")
                // 	Basic SFFOVTpLcDlWdjA2T3dNTXk= SFFOVV9YWDpnVlB2YmwyTU5uRWg=
                .header("client", "HQNU")
                .header("x-api-key", "kqumzp9Mle41eZ8cpLMfK6AGOVs8Kgaj9LtHpyh6")
                // m3b1FPChyh88PhVS1eCyV2GImMzNkwaN4meYVa0b kqumzp9Mle41eZ8cpLMfK6AGOVs8Kgaj9LtHpyh6
                //
                // The device datetime must be today's date
                // The api only allows connections from the date it's being called
                .header("device-datetime", isoDateTime)
                .header("territory", "XX")
                // "geolocation" could be obtained via google maps api
                // It is in the format latitude, longitude
                // Currently only using London coordinates
                .header("geolocation", "-22.0;14.0")
                // 51.50;0.12 -22.0;14.0
                .GET()
                .build();

        // try to connect, catch any errors that might occur
        try {
            // connect to the url and get a response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            // Status code of the request
            int statusCode = response.statusCode();
            // Response headers
            HttpHeaders headers = response.headers();
            // String of the response
            String responseBody = response.body();
            // Assign response body string to variable outside of scope
            returnedJsonString = new String[]{responseBody, String.valueOf(statusCode)};


            //TESTING - Service Layer NEEDS TO BE MODIFIED




            System.out.println("Status Code: " + statusCode);
            //System.out.println("Headers: " + headers);
            //System.out.println("Response Body: " + responseBody);
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return returnedJsonString;
    }



}
