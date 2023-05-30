package com.company.showtime.controller;

import com.company.showtime.model.Film;
import com.company.showtime.service.ApiServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/currentfilms")
public class ApiController {
    @Autowired
    ApiServiceImpl apiService;

    // Method to get all films currently being shown
    public List<Film> getFilmsNowShowing() {
        // Contact the api to get a String response body
        String responseBody = ApiCaller("filmsNowShowing");
        // Call the service layer to deal with the response body
        List<Film> currentShownFilms = apiService.filmsNowShowing(responseBody);
        return currentShownFilms;
    }





    // Method to contact api
    public String ApiCaller(String method){
        // Instantiate a string for the response body and the api endpoint
        String returnedJsonString = null;
        String apiEndpoint =  "https://api-gate2.movieglu.com/" +method+ "/?n=10";
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .connectTimeout(Duration.ofSeconds(10))
                .build();

        // Create the HTTP request using headers of the api and values to connect
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiEndpoint))
                .header("api-version", "v200")
                .header("Authorization", "Basic SFFOVV9YWDpnVlB2YmwyTU5uRWg=")
                .header("client", "HQNU")
                .header("x-api-key", "kqumzp9Mle41eZ8cpLMfK6AGOVs8Kgaj9LtHpyh6")
                .header("device-datetime", "2023-05-29T09:08:14Z")
                .header("territory", "XX")
                // "geolocation" could be obtained via google maps api
                // It is in the format latitude, longitude
                // Currently only using London coordinates
                .header("geolocation", "51.5,-0.11")
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

            System.out.println("Status Code: " + statusCode);
            System.out.println("Headers: " + headers);
            //System.out.println("Response Body: " + responseBody);
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return returnedJsonString;
    }



}
