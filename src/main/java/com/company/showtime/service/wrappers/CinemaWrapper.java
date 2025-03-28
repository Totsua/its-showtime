package com.company.showtime.service.wrappers;

import com.company.showtime.entities.Cinema;
import com.company.showtime.exceptions.CustomException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;

public class CinemaWrapper {
    /**
     * The wrapper that parses the JSON response body and creates a list of Cinema Objects based on
     * what method was used to obtain the data.
     * "closestShowing" has more information than "cinemasNearby".
     * @param jsonResponseBody - the JSON response body obtained from the API.
     * @param method - The API method called to obtain the API data.
     * @return a list of Cinema Objects from the JSON response body.
     */
    public static List<Cinema> cinemaWrapper(String jsonResponseBody, String method) throws CustomException {
        // Instantiate List Object of cinemas
        List<Cinema> cinemas = new ArrayList<>();

        // Instantiate ObjectMapper object
        ObjectMapper mapper = new ObjectMapper();
        // Try to parse the response body
        try {
            // Locate and read the JSON Node from the Response body
            JsonNode root = mapper.readTree(jsonResponseBody);
            JsonNode cinemasNode = root.get("cinemas");

            // Enhanced for loop to iterate through the "cinemas" node (multiple cinemas)
            for (JsonNode cinemaNode : cinemasNode) {

                // Set the cinema name, id, address, city, county, postcode and distance
                String cinemaName = cinemaNode.get("cinema_name").asText();
                int cinema_id = cinemaNode.get("cinema_id").asInt();
                String cinemaAddress1 = cinemaNode.get("address").asText();
                String cinemaAddress2 = cinemaNode.get("address2").asText();
                String cinemaAddress = cinemaAddress1 + " " +cinemaAddress2;
                String city = cinemaNode.get("city").asText();
                String county = cinemaNode.get("county").asText();
                String postcode = cinemaNode.get("postcode").asText();
                int distance = cinemaNode.get("distance").asInt();

                if(method.equalsIgnoreCase("cinemasNearby")) {
                    // Create a Cinema object with the gained information
                    Cinema cinema = new Cinema(cinemaName, cinema_id, cinemaAddress, city, postcode, distance);

                    // Add the Cinema to the List
                    cinemas.add(cinema);
                }
                else if (method.equalsIgnoreCase("closestShowing")){
                    // set the date and time
                    String date = cinemaNode.get("date").asText();
                    String time = cinemaNode.get("time").asText();

                    // Create the Cinema Object with the gained information
                    Cinema cinema = new Cinema(cinemaName, cinema_id, cinemaAddress,
                            city, postcode, distance, date, time);

                    // Add the Cinema to the List
                    cinemas.add(cinema);
                }
            }

        }catch(JsonProcessingException e){
            throw new CustomException("-_-Could Not Parse Cinema JSON Data: " + e.getMessage(),e);
        }
        // Return the Cinema list
        return cinemas;

    }

    public static Cinema cinemaDetailsWrapper(String jsonResponseBody) throws CustomException{
        ObjectMapper mapper = new ObjectMapper();
        Cinema cinema;
        try {
            JsonNode cinemaNode = mapper.readTree(jsonResponseBody);

            String cinemaName = cinemaNode.get("cinema_name").asText();
            int cinema_id = cinemaNode.get("cinema_id").asInt();
            String cinemaAddress1 = cinemaNode.get("address").asText();
            String cinemaAddress2 = cinemaNode.get("address2").asText();
            String cinemaAddress = cinemaAddress1 + " " + cinemaAddress2;
            String city = cinemaNode.get("city").asText();
            String county = cinemaNode.get("county").asText();
            String postcode = cinemaNode.get("postcode").asText();
            int distance = cinemaNode.get("distance").asInt();
            cinema = new Cinema(cinemaName, cinema_id, cinemaAddress, city,postcode,distance);

        }catch (JsonProcessingException e){
            throw new CustomException("-_-Could Not Parse Cinema JSON Data: " + e.getMessage(),e);

        }
        return cinema;

    }



}
