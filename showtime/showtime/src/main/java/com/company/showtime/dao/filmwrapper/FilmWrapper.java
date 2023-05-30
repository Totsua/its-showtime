package com.company.showtime.dao.filmwrapper;

import com.company.showtime.model.Film;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class FilmWrapper {



    public List<Film> filmWrapper (String jsonResponseBody) {
        List<Film> films = new ArrayList<>();

        // Instantiate a ObjectMapper object
        ObjectMapper mapper = new ObjectMapper();
        // Try to parse the response body
        try {
            JsonNode root = mapper.readTree(jsonResponseBody);
            JsonNode filmsNode = root.get("films");

            // Enhanced for loop to get the film name, id and synopsis
            for (JsonNode filmNode : filmsNode) {
                String filmName = filmNode.get("film_name").asText();
                int filmId = filmNode.get("film_id").asInt();
                String filmSynposis = filmNode.get("synopsis_long").asText();
                // Age rating is in a nested array
                JsonNode ageRatingNode = filmNode.get("age_rating");
                if (ageRatingNode.isArray()) {
                    // Go through the array to get the age rating of the film
                    for (JsonNode ratingNode : ageRatingNode) {
                        String filmAgeRating = ratingNode.get("rating").asText();
                        // Instantiate a new film object and place it into a list
                        Film film = new Film(filmId,filmName,filmSynposis,filmAgeRating);
                        films.add(film);
                        // public Film(int filmID, String filmName, String filmSynopsis, String filmAgeRating)
                        //System.out.println("Movie: " + filmName + ", Age Rating: " + rating + ", Synopsis: " +
                        //        synposis + "\n");
                    }
                }

            }

        } catch (
                JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        // Return the films list
        return films;
    }
}
