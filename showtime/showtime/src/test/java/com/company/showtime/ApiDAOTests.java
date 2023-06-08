package com.company.showtime;

import com.company.showtime.dao.ApiDAOImpl;
import com.company.showtime.entities.Film;
import com.company.showtime.exceptions.CustomException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class ApiDAOTests {
    private ApiDAOImpl apiDAO;

    public ApiDAOTests() {
        apiDAO = new ApiDAOImpl();
        this.apiDAO = apiDAO;
    }

    // Test to see if the DAO can correctly get the list of Film objects correctly from a JSON
    @Test
    @DisplayName("Correct Film List Returned From Json Test")
    public void correctFilmListTest() throws CustomException {
        List<Film> films = apiDAO.getFilmList(CORRECTFILMSJSON, "filmsNowShowing");
        assertEquals(3, films.size());
    }


    // Learnt from baeldung.com - Junit Assert Exceptions
    // Test to see if correct error is thrown in filmWrapper
    @Test
    @DisplayName("Correct Error From Wrong Json Test")
    public void CorrectErrorFilmListTest(){
        Exception exception = assertThrows(CustomException.class, () -> {
            apiDAO.getFilmList(INCORRECTFILMJSON,"filmsNowShowing");
        });
        String expectedMessage = "-_-Could Not Parse Film JSON Data";
        String actualMessage = exception.getMessage();
        System.out.println(actualMessage);
        assertTrue(actualMessage.contains(expectedMessage));
    }


    private String INCORRECTFILMJSON = "{OneFilm\": [{\"film_id\": 7772,\"imdb_id\": 82971,\"imdb_title_id\":" +
            " \"tt0082971\",\"film_name\": \"Raiders of the Lost Ark\",\"age_rating\": [{\"rating\": \"PG \",}]," +
            "\"synopsis_long\": \"Indiana Jones doing Indiana Jones things\"],}";




    public String CORRECTFILMSJSON = "{\n" +
            "    \"films\": [\n" +
            "        {\n" +
            "            \"film_id\": 7772,\n" +
            "            \"imdb_id\": 82971,\n" +
            "            \"imdb_title_id\": \"tt0082971\",\n" +
            "            \"film_name\": \"Raiders of the Lost Ark\",\n" +
            "            \"other_titles\": null,\n" +
            "            \"release_dates\": [\n" +
            "                {\n" +
            "                    \"release_date\": \"1992-07-01\",\n" +
            "                    \"notes\": \"XXX\"\n" +
            "                }\n" +
            "            ],\n" +
            "            \"age_rating\": [\n" +
            "                {\n" +
            "                    \"rating\": \"PG \",\n" +
            "                    \"age_rating_image\": \"https://assets.movieglu.com/age_rating_logos/xx/pg.png\",\n" +
            "                    \"age_advisory\": \"Contains moderate violence and mild language\"\n" +
            "                }\n" +
            "            ],\n" +
            "            \"film_trailer\": \"https://trailer.movieglu.com/7772_high.mp4\",\n" +
            "            \"synopsis_long\": \"As the Third Reich continues its reign of terror, Adolf Hitler is on a quest for the legendary Ark os the Covenenant- resting place of the Ten Commandments- whose supernatural powers, legend says, can wipe out entire armies.\\n\\nThe U.S. Government turns to Dr. Indiana Jones, for the mission.  Relentlessly pursued by Hitler's henchmen, Indy infiltrartes their massive digging operation in a race against time to discover the Well od the Souls, where the Ark has lain undisturbed for centuries.\",\n" +
            "            \"images\": {\n" +
            "                \"poster\": {\n" +
            "                    \"1\": {\n" +
            "                        \"image_orientation\": \"portrait\",\n" +
            "                        \"region\": \"UK\",\n" +
            "                        \"medium\": {\n" +
            "                            \"film_image\": \"https://image.movieglu.com/7772/GBR_007772h0.jpg\",\n" +
            "                            \"width\": 200,\n" +
            "                            \"height\": 300\n" +
            "                        }\n" +
            "                    }\n" +
            "                },\n" +
            "                \"still\": {\n" +
            "                    \"1\": {\n" +
            "                        \"image_orientation\": \"landscape\",\n" +
            "                        \"medium\": {\n" +
            "                            \"film_image\": \"https://image.movieglu.com/7772/007772h2.jpg\",\n" +
            "                            \"width\": 300,\n" +
            "                            \"height\": 200\n" +
            "                        }\n" +
            "                    }\n" +
            "                }\n" +
            "            }\n" +
            "        },\n" +
            "        {\n" +
            "            \"film_id\": 184126,\n" +
            "            \"imdb_id\": 3659388,\n" +
            "            \"imdb_title_id\": \"tt3659388\",\n" +
            "            \"film_name\": \"The Martian\",\n" +
            "            \"other_titles\": null,\n" +
            "            \"release_dates\": [\n" +
            "                {\n" +
            "                    \"release_date\": \"2015-09-30\",\n" +
            "                    \"notes\": \"XXX\"\n" +
            "                }\n" +
            "            ],\n" +
            "            \"age_rating\": [\n" +
            "                {\n" +
            "                    \"rating\": \"12A \",\n" +
            "                    \"age_rating_image\": \"https://assets.movieglu.com/age_rating_logos/xx/12a.png\",\n" +
            "                    \"age_advisory\": \"infrequent strong language, injury detail\"\n" +
            "                }\n" +
            "            ],\n" +
            "            \"film_trailer\": \"https://trailer.movieglu.com/184126_uk_high.mp4\",\n" +
            "            \"synopsis_long\": \"During a manned mission to Mars, Astronaut Mark Watney is presumed dead after a fierce storm and left behind by his crew. But Watney has survived and finds himself stranded and alone on the hostile planet. With only meager supplies, he must draw upon his ingenuity, wit and spirit to subsist and find a way to signal to Earth that he is alive. Millions of miles away, NASA and a team of international scientists work tirelessly to bring \\\"the Martian\\\" home, while his crewmates concurrently plot a daring, if not impossible, rescue mission. As these stories of incredible bravery unfold, the world comes together to root for Watney's safe return.\",\n" +
            "            \"images\": {\n" +
            "                \"poster\": {\n" +
            "                    \"1\": {\n" +
            "                        \"image_orientation\": \"portrait\",\n" +
            "                        \"region\": \"UK\",\n" +
            "                        \"medium\": {\n" +
            "                            \"film_image\": \"https://image.movieglu.com/184126/GBR_184126h0.jpg\",\n" +
            "                            \"width\": 200,\n" +
            "                            \"height\": 300\n" +
            "                        }\n" +
            "                    }\n" +
            "                },\n" +
            "                \"still\": {\n" +
            "                    \"1\": {\n" +
            "                        \"image_orientation\": \"landscape\",\n" +
            "                        \"medium\": {\n" +
            "                            \"film_image\": \"https://image.movieglu.com/184126/184126h2.jpg\",\n" +
            "                            \"width\": 300,\n" +
            "                            \"height\": 200\n" +
            "                        }\n" +
            "                    }\n" +
            "                }\n" +
            "            }\n" +
            "        },\n" +
            "        {\n" +
            "            \"film_id\": 59906,\n" +
            "            \"imdb_id\": 469494,\n" +
            "            \"imdb_title_id\": \"tt0469494\",\n" +
            "            \"film_name\": \"There Will Be Blood\",\n" +
            "            \"other_titles\": null,\n" +
            "            \"release_dates\": [\n" +
            "                {\n" +
            "                    \"release_date\": \"2008-02-08\",\n" +
            "                    \"notes\": \"XXX\"\n" +
            "                }\n" +
            "            ],\n" +
            "            \"age_rating\": [\n" +
            "                {\n" +
            "                    \"rating\": \"15 \",\n" +
            "                    \"age_rating_image\": \"https://assets.movieglu.com/age_rating_logos/xx/15.png\",\n" +
            "                    \"age_advisory\": \"Contains strong violence\"\n" +
            "                }\n" +
            "            ],\n" +
            "            \"film_trailer\": null,\n" +
            "            \"synopsis_long\": \"Set on the incendiary frontier of California's turn-of-the-century petroleum boom, the story chronicles the rise of Daniel Plainview, who transforms himself from a down-and-out silver miner raising a son on his own into a self-made oil tycoon. When Plainview gets a mysterious tip that there's a little town out West where an ocean of oil is oozing out of the ground, he heads with his son, H.W., to take their chances in dust-worn Little Boston. In this hardscrabble town, where the main excitement centers around the holy-roller church of charismatic preacher Eli Sunday, Plainview and H.W. make their lucky strike. But even as the well raises all of their fortunes, nothing will remain the same as conflicts escalate and every human value--love, hope, community, belief, ambition and even the bond between father and son--is imperiled by corruption, deception and the flow of oil.\",\n" +
            "            \"images\": {\n" +
            "                \"poster\": {\n" +
            "                    \"1\": {\n" +
            "                        \"image_orientation\": \"portrait\",\n" +
            "                        \"region\": \"global\",\n" +
            "                        \"medium\": {\n" +
            "                            \"film_image\": \"https://image.movieglu.com/59906/059906h1.jpg\",\n" +
            "                            \"width\": 200,\n" +
            "                            \"height\": 300\n" +
            "                        }\n" +
            "                    }\n" +
            "                },\n" +
            "                \"still\": {\n" +
            "                    \"1\": {\n" +
            "                        \"image_orientation\": \"landscape\",\n" +
            "                        \"medium\": {\n" +
            "                            \"film_image\": \"https://image.movieglu.com/59906/059906h2.jpg\",\n" +
            "                            \"width\": 300,\n" +
            "                            \"height\": 200\n" +
            "                        }\n" +
            "                    }\n" +
            "                }\n" +
            "            }\n" +
            "        }\n" +
            "    ],\n" +
            "    \"status\": {\n" +
            "        \"count\": 3,\n" +
            "        \"state\": \"OK\",\n" +
            "        \"method\": \"filmsNowShowing\",\n" +
            "        \"message\": null,\n" +
            "        \"request_method\": \"GET\",\n" +
            "        \"version\": \"HQNU_XXv200\",\n" +
            "        \"territory\": \"XX\",\n" +
            "        \"device_datetime_sent\": \"2023-06-07T17:22:55.770Z\",\n" +
            "        \"device_datetime_used\": \"2023-06-07 17:22:55\"\n" +
            "    }\n" +
            "}";
}
