package com.company.showtime.service;

import com.company.showtime.dao.ApiDAO;
import com.company.showtime.entities.Cinema;
import com.company.showtime.entities.Film;
import com.company.showtime.exceptions.CustomException;
import com.company.showtime.service.wrappers.CinemaWrapper;
import com.company.showtime.service.wrappers.FilmWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ApiServiceImpl implements ApiService {

    // Create instance of the ApiDAO
    @Autowired
    ApiDAO apiDAO;
//    public ApiServiceImpl(ApiDAO apiDAO) {this.apiDAO = apiDAO;}

    /**
     * Method to call the DAO to parse JSON response body and return a List of Film Objects depending on the
     * called method.
     * If the status code isn't 200 from the api- it'll return the List saying
     * that there are none.
     * @param jsonResponseBody - the JSON response body and server code
     * @return the List of Film Objects
     */
    @Override
    public List<Film> filmsNowShowing(String[] jsonResponseBody) throws CustomException {
       // Define/Instantiate variables
        List<Film> allFilmsNowShowing = new ArrayList<>();
        String responseBody = jsonResponseBody[0];
        String statusCode = jsonResponseBody[1];
        // Check the status code to see if the DAO is called to
        // unmarhsall the response body for the desired info
        if (statusCode.equals("200")){
            allFilmsNowShowing = FilmWrapper.filmWrapper(responseBody,"filmsNowShowing");
        }
        else{
            Film nothing = new Film(statusCode+ ": Looks like there are none.");
            allFilmsNowShowing.add(nothing);
        }
        // Return the list
        return allFilmsNowShowing;
    }

    /**
     * Method to call the DAO to parse JSON response body and return a List of Cinema Objects depending on the
     * called method.
     * If the status code isn't 200 from the api- it'll return the List saying
     * that there are none.
     * @param jsonResponseBody - the JSON response body and server code
     * @return the List of Cinema Objects
     */
    @Override
    public List<Cinema> nearbyCinemas(String[] jsonResponseBody) {
        // Define/Instantiate variables
        List<Cinema> nearbyCinemas = new ArrayList<>();
        String responseBody = jsonResponseBody[0];
        String statusCode = jsonResponseBody[1];
        // Check the status code to see if the DAO is called to
        // unmarhsall the response body for the desired info
        if (statusCode.equals("200")){
            nearbyCinemas = CinemaWrapper.cinemaWrapper(responseBody,"cinemasNearby");
        }
        else{
            Cinema nothing = new Cinema(statusCode+ ": Looks like there are none.");
            nearbyCinemas.add(nothing);
        }
        // Return the list
        return nearbyCinemas;
    }

    /**
     * Method to call the DAO to parse JSON response body and return a List of Film Objects depending on the
     * called method.
     * If the status code isn't 200 from the api- it'll return the List saying
     * that there are none.
     * @param jsonResponseBody - the JSON response body and server code
     * @return the List of Film Objects
     */
    @Override
    public List<Film> cinemaShowTimes(String[] jsonResponseBody) throws CustomException {
        // Define/Instantiate variables
        List<Film> cinemaShowTimes = new ArrayList<>();
        String responseBody = jsonResponseBody[0];
        String statusCode = jsonResponseBody[1];
        // Check the status code to see if the DAO is called to
        // unmarhsall the response body for the desired info
        if(statusCode.equals("200")){
            cinemaShowTimes = FilmWrapper.filmWrapper(responseBody,"cinemaShowTimes");
        }
        else{
            Film nothing = new Film(statusCode+": Looks like there are none.");
            cinemaShowTimes.add(nothing);
        }
        // Return the list
        return cinemaShowTimes;
    }

    /**
     * Method to call the DAO to parse JSON response body and return a List of Cinema Objects depending on the
     * called method.
     * If the status code isn't 200 from the api- it'll return the List saying
     * that there are none.
     * @param jsonResponseBody - the JSON response body and server code
     * @return the List of Cinema Objects
     */
    @Override
    public List<Cinema> closestShowing(String[] jsonResponseBody) {
        // Define/Instantiate variables
        List<Cinema> closestShowingList = new ArrayList<>();
        String responseBody = jsonResponseBody[0];
        String statusCode = jsonResponseBody[1];
        // Check the status code to see if the DAO is called to
        // unmarhsall the response body for the desired info
        if(statusCode.equals("200")){
            closestShowingList = CinemaWrapper.cinemaWrapper(responseBody,"closestShowing");
        }
        else{
            Cinema nothing = new Cinema(statusCode+": Looks like there are none.");
            closestShowingList.add(nothing);
        }
        // Return the list
        return closestShowingList;
    }



    /**
     * The Heart of the operation, The API Caller.
     * It should be in the service layer but for the time being it's here.
     *
     * The method constructs an endpoint with the given parameters, connects to the API
     * and returns the response body[0] and server code[1] as a String array.
     *
     * At this moment in time the API key connects to dummy information in the API.
     * The dummy information is 10000 requests per month whilst the live information is 75 per month.
     *
     * The API Headers are fixed so that the user cannot interfere with them. The only change in the
     * header is the device time which is instantiated on every request via LocalDateTime,
     * formatted and placed into the header.
     *
     * @param method - the method that is being called in the API
     * @param id - the id given for a chosen film or cinema, is given as 0 if there is none.
     * @return the response body[0] and status code[1] as a String array.
     */
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
                .header("Authorization", "")
                .header("client", "")
                .header("x-api-key", "")
                // The api only allows connections from the date it's being called
                .header("device-datetime", isoDateTime)
                .header("territory", "XX")
                //  In the format latitude, longitude
                .header("geolocation", "-22.0;14.0")
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


            System.out.println("Status Code: " + statusCode);

        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return returnedJsonString;
    }
}
