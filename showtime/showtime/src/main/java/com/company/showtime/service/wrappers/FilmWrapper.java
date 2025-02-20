package com.company.showtime.service.wrappers;

import com.company.showtime.entities.Film;
import com.company.showtime.exceptions.CustomException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class FilmWrapper {
    /**
     * The wrapper that parses the JSON response body and creates a list of Film Objects based on
     * what method was used to obtain the data.
     * "filmsNowShowing" has less information than "cinemaShowTimes".
     * @param jsonResponseBody - the JSON response body obtained from the API.
     * @param method - The API method called to obtain the API data.
     * @throws - CustomException if there is a JsonProcessingException -- This is still in the TESTING phase.
     * @return a list of Film Objects from the JSON response body.
     */
    public static List<Film> filmWrapper(String jsonResponseBody, String method) throws CustomException {
        List<Film> films = new ArrayList<>();

        // Instantiate a ObjectMapper object
        ObjectMapper mapper = new ObjectMapper();
        // Try to parse the response body
        try {
            // Locate and read the JSON Node from the Response Body
            JsonNode root = mapper.readTree(jsonResponseBody);
            JsonNode filmsNode = root.get("films");

            // Iterate through the "films" Node (multiple films)
            for (JsonNode filmNode : filmsNode) {

                // Set the film name, id and synopsis
                String filmName = filmNode.get("film_name").asText();
                int filmId = filmNode.get("film_id").asInt();

                // Locate the "age_rating" node within the "films" node
                JsonNode ageRatingNode = filmNode.get("age_rating");
                // Locate the one Node within the "age_rating" node
                JsonNode ratingNode = ageRatingNode.get(0);
                // Set the age rating of the film
                String filmAgeRating = ratingNode.get("rating").asText();

                if(method.equalsIgnoreCase("filmsNowShowing")){
                    String filmSynposis = filmNode.get("synopsis_long").asText();
                    // Instantiate a new film object and place it into a list
                    Film film = new Film(filmId, filmName, filmSynposis, filmAgeRating);
                    films.add(film);
                }
                else if(method.equalsIgnoreCase("cinemaShowTimes")){
                    // Locate the "showings" Node and the "Standard" node within it,
                    // it doesn't need for a loop as there is only one node in each film
                    JsonNode showingsNode = filmNode.get("showings");
                    JsonNode standardNode = showingsNode.get("Standard");

                    // Locate the "times" node within the "Standard" node
                    JsonNode TimesNode = standardNode.get("times");

                    // Instantiate a List for the showtimes
                    List<String> showtimes = new ArrayList<>();

                    // Enhanced for loop to get the start and end time viewings
                    // Each start-end time is in its own node
                    for (JsonNode timesNode : TimesNode){
                        String startTime = timesNode.get("start_time").asText();
                        String endTime = timesNode.get("end_time").asText();
                        showtimes.add(startTime +"-"+ endTime);
                    }
                    // Instantiate a new Film object and place it into a list
                    Film film = new Film(filmId,filmName,filmAgeRating,showtimes);
                    films.add(film);
                }
            }

        } catch (
                JsonProcessingException e) {
            throw new CustomException("-_-Could Not Parse Film JSON Data: " + e.getMessage(),e);
        }
        // Return the films list
        return films;
    }
}
