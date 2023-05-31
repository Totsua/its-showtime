package com.company.showtime.dao.wrappers;

import com.company.showtime.model.Cinema;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;

public class CinemaWrapper {
    public static List<Cinema> cinemaWrapper(String jsonResponseBody, String method) {
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
                else if (method.equalsIgnoreCase("closetShowing")){
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
            System.out.println(e.getMessage());
        }
        // Return the Cinema list
        return cinemas;

    }
}
