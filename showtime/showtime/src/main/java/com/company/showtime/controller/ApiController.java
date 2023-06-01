package com.company.showtime.controller;

import com.company.showtime.model.Cinema;
import com.company.showtime.model.Film;
import com.company.showtime.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
public class ApiController {

    // Create instance of the ApiService
    @Autowired
    ApiService apiService;
    public ApiController(ApiService apiService){this.apiService = apiService;}

    // Method to get list of films currently showing
    @GetMapping("/filmsNowShowing")
    // Method to get all films currently being shown
    public List<Film> getFilmsNowShowing() {
        // Contact the api to get a String response body
        String responseBody = ApiCaller("filmsNowShowing",0);
        // Call the service layer to deal with the response body
        List<Film> currentShownFilms = apiService.filmsNowShowing(responseBody);
        // Return the List
        return currentShownFilms;
    }

    // Method to get list of nearby cinemas
    @GetMapping("/allNearbyCinemas")
    public List<Cinema> getCinemasNearby(){
        // Contact api to get a String JSON reponse body
        String responseBody = ApiCaller("cinemasNearby",0);
        // Call the service layer to deal with the response body
        List<Cinema> nearbyCinemas = apiService.nearbyCinemas(responseBody);
        // Return the List
        return nearbyCinemas;
    }

    // Method to get list of showtimes for a specific cinema
    @GetMapping("/cinemaShowTimes{id}")
    public List<Film> cinemaShowTimes(@RequestParam int cinemaId){
        // Contact api to get a String JSON response body
        String responseBody = ApiCaller("cinemaShowTimes", cinemaId);
        // Call the service layer to deal with the response body
        List<Film> cinemaShowTimes = apiService.cinemaShowTimes(responseBody);
        // Return the List
        return cinemaShowTimes;
    }

    // Method to get list of closest viewings of a specific film
    @GetMapping("/closestShowing{id}")
    public List<Cinema> closestShowing(@RequestParam int filmId){
        // Contact api to get a String JSON response body
        String responseBody = ApiCaller("closestShowing", filmId);
        // Call the service layer to deal with the response body
        List<Cinema> closestShowing = apiService.closestShowing(responseBody);
        // Return the List
        return closestShowing;
    }

    // Method to contact api
    public String ApiCaller(String method, int id){
        // Instantiate a string for the response body, the api endpoint and todays date
        String returnedJsonString = null;
        String apiEndpoint =  "https://api-gate2.movieglu.com/" +method+ "/?n=10";
        String date = LocalDate.now().toString();

        // Need to create a datetime in the format yyyy-MM-dd'T'HH:mm:ss.SSS'Z' - for the API
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        String isoDateTime = now.format(formatter);

        // Change the endpoint depending on what method is being used
        if (method.equalsIgnoreCase("cinemaShowTimes")){
            apiEndpoint = "https://api-gate2.movieglu.com/cinemaShowTimes/?cinema_id=" +id+ "&date=" + date;
        }
         else if(method.equalsIgnoreCase("closestShowing")){
            apiEndpoint = apiEndpoint.concat("&film_id="+id);
        }

        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .connectTimeout(Duration.ofSeconds(10))
                .build();

        // Create the HTTP request using headers of the api and values to connect
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiEndpoint))
                .header("api-version", "v200")
                .header("Authorization", "Basic SFFOVTpLcDlWdjA2T3dNTXk=")
                // 	Basic SFFOVTpLcDlWdjA2T3dNTXk= SFFOVV9YWDpnVlB2YmwyTU5uRWg=
                .header("client", "HQNU")
                .header("x-api-key", "m3b1FPChyh88PhVS1eCyV2GImMzNkwaN4meYVa0b")
                // m3b1FPChyh88PhVS1eCyV2GImMzNkwaN4meYVa0b kqumzp9Mle41eZ8cpLMfK6AGOVs8Kgaj9LtHpyh6
                //
                // The device datetime must be today's date and will be taken from 8am
                // The api only allows connections from the date it's being called
                .header("device-datetime", isoDateTime)
                .header("territory", "UK")
                // "geolocation" could be obtained via google maps api
                // It is in the format latitude, longitude
                // Currently only using London coordinates
                .header("geolocation", "51.50;-0.13")
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
            returnedJsonString = responseBody;


            //TESTING - Service Layer NEEDS TO BE MODIFIED
            if(statusCode!= 200){
                returnedJsonString = String.valueOf(statusCode);
            }



            System.out.println("Status Code: " + statusCode);
            //System.out.println("Headers: " + headers);
           // System.out.println("Response Body: " + responseBody);
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return returnedJsonString;
    }



}
